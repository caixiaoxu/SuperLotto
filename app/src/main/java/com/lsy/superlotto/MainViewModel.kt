package com.lsy.superlotto

import androidx.lifecycle.*
import com.lsy.lottodata.db.DBManager
import com.lsy.lottodata.db.entity.LotteryNumber
import com.lsy.lottodata.db.entity.LottoTicket
import com.lsy.lottodata.db.entity.SelfLottoNumber
import com.lsy.lottodata.db.entity.enums.EnumLottoType
import com.lsy.lottodata.pydocking.NetLottery
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

/**
 * @author Xuwl
 * @date 2021/6/21
 *
 */
class MainViewModel : ViewModel() {
    val lottery: MutableLiveData<LotteryNumber> = MutableLiveData()
    val ticketList: MutableLiveData<List<LottoTicket>> = MutableLiveData()
    val singleTicketList: MutableLiveData<List<LottoTicket>> = MutableLiveData()
    val doubleTicketList: MutableLiveData<List<LottoTicket>> = MutableLiveData()

    init {
        loadLatestData()
    }

    /**
     * 加载最新数据
     */
    fun loadLatestData() {
        viewModelScope.launch() {
            //表里最新的期数
            var last = DBManager.db.lotteryNumberDao().getFirst()

            //采集最新数据
            NetLottery.callLottery(last.nper)

            //取出最新的开奖号码
            last = DBManager.db.lotteryNumberDao().getFirst()
            val lotteryNper = last.nper.toIntOrNull() ?: -1
            //取出最新的彩票
            val latestNperStr = DBManager.db.lottoTicketDao().getLatestNper()
            val latestNper = latestNperStr?.toIntOrNull() ?: -1
            //两个都不存在
            if (-1 == lotteryNper && -1 == latestNper) {
                return@launch
            }
            if (lotteryNper == latestNper) {
                //两个期数相同，表示开奖号码和彩票对上了
                lottery.postValue(last)
                val latestList =
                    DBManager.db.lottoTicketDao().getLatestList(latestNper.toString())
                refreshTickList(latestList)
            } else {
                if (lotteryNper > latestNper) {
                    //开奖号码期数最新(最新期没有买彩票)
                    lottery.postValue(last)
                } else {
                    //彩票期数最新(最新期还没开奖)
                    val latestList =
                        DBManager.db.lottoTicketDao().getLatestList(latestNper.toString())
                    refreshTickList(latestList)
                }
            }
        }
    }

    /**
     * 刷新彩票列表
     */
    private fun refreshTickList(latestList: List<LottoTicket>) {
        ticketList.postValue(latestList)
        val singleList = ArrayList<LottoTicket>()
        val doubleList = ArrayList<LottoTicket>()
        latestList.forEach {
            if (EnumLottoType.DOUBLE == it.type) {
                doubleList.add(it)
            } else {
                singleList.add(it)
            }
        }
        singleTicketList.postValue(singleList)
        doubleTicketList.postValue(doubleList)
    }

    /**
     * 添加乐透彩票
     */
    fun addLottoTicket(ticket: LottoTicket, list: MutableList<SelfLottoNumber>) {
        viewModelScope.launch {
            DBManager.db.lottoTicketDao().insertLottoTable(ticket)
            DBManager.db.selfLottoNumberDao().insertLottoNumber(list)
            loadLatestData()
        }
    }
}