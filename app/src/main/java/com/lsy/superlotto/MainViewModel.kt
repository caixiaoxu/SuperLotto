package com.lsy.superlotto

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lsy.lottodata.db.DBManager
import com.lsy.lottodata.db.entity.LotteryNumber
import com.lsy.lottodata.db.entity.LottoTicket
import com.lsy.lottodata.pydocking.NetLottery
import kotlinx.coroutines.launch

/**
 * @author Xuwl
 * @date 2021/6/21
 *
 */
class MainViewModel : ViewModel() {
    val lottery: MutableLiveData<LotteryNumber> = MutableLiveData()
    val ticket: MutableLiveData<List<LottoTicket>> = MutableLiveData()

    init {
        loadLatestData()
    }

    /**
     * 加载最新数据
     */
    fun loadLatestData() {
        viewModelScope.launch() {
            //初始化表
            DBManager.createLotteryTable()
            //采集最新数据
            NetLottery.callLottery(DBManager.dbPath, DBManager.TABLE_LOTTERY_NAME)
            //取出最新的开奖号码
            val first = DBManager.db.lotteryNumberDao().getFirst()
            val lotteryNper = first.nper.toIntOrNull() ?: -1
            //取出最新的彩票
            val latestNperStr = DBManager.db.lottoTicketDao().getLatestNper()
            val latestNper = latestNperStr?.toIntOrNull() ?: -1
            //两个都不存在
            if (-1 == lotteryNper && -1 == latestNper) {
                return@launch
            }
            if (lotteryNper == latestNper) {
                //两个期数相同，表示开奖号码和彩票对上了
                lottery.postValue(first)
                val latestList = DBManager.db.lottoTicketDao().getLatestList(latestNperStr!!)
                ticket.postValue(latestList)
            } else {
                if (lotteryNper > latestNper) {
                    //开奖号码期数最新(最新期没有买彩票)
                    lottery.postValue(first)
                } else {
                    //彩票期数最新(最新期还没开奖)
                    val latestList = DBManager.db.lottoTicketDao().getLatestList(latestNperStr!!)
                    ticket.postValue(latestList)
                }
            }
        }
    }

    /**
     * 添加乐透彩票
     */
    fun addLottoTicket() {

    }
}