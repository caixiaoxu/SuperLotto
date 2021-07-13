package com.lsy.superlotto

import androidx.lifecycle.*
import com.lsy.lottodata.db.DBManager
import com.lsy.lottodata.db.entity.LotteryNumber
import com.lsy.lottodata.db.entity.LottoTicket
import com.lsy.lottodata.db.entity.SelfLottoNumber
import com.lsy.lottodata.db.entity.enums.EnumLottoType
import com.lsy.lottodata.pydocking.NetLottery
import com.lsy.superlotto.utils.LottoUtil
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

    var lottoType = EnumLottoType.SINGLE
    val resultMap: EnumMap<EnumLottoType, String> = EnumMap(EnumLottoType::class.java)
    val winString: MutableLiveData<String> = MutableLiveData()

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

    /**
     * 中奖结果
     * @param list 号码
     */
    fun dealSingleWinResult(list: List<SelfLottoNumber>) {
        if (null == lottery.value) {
            return
        }

        val winFront = arrayOf(
            lottery.value!!.num1.toInt(),
            lottery.value!!.num2.toInt(),
            lottery.value!!.num3.toInt(),
            lottery.value!!.num4.toInt(),
            lottery.value!!.num5.toInt()
        )

        val winBack = arrayOf(
            lottery.value!!.num6.toInt(),
            lottery.value!!.num7.toInt()
        )

        val frontLists = ArrayList<ArrayList<Int>>()
        val backLists = ArrayList<ArrayList<Int>>()
        list.forEach { lotto ->
            try {
                val frontArr = lotto.front.split(Common.LOTTO_SPLIT)
                val backArr = lotto.back.split(Common.LOTTO_SPLIT)
                val frontList = arrayListOf(frontArr[0].toInt(), frontArr[1].toInt(),
                    frontArr[2].toInt(), frontArr[3].toInt(), frontArr[4].toInt()
                )
                frontList.sort()
                val backList = arrayListOf(backArr[0].toInt(), backArr[1].toInt())
                backList.sort()
                frontLists.add(frontList)
                backLists.add(backList)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        val winFrontCounts = IntArray(frontLists.size)
        val frontIndexs = IntArray(frontLists.size)
        winFront.forEach { f ->
            frontLists.forEachIndexed { index, list ->
                while (frontIndexs[index] < list.size) {
                    if (f == list[frontIndexs[index]]) {
                        winFrontCounts[index]++
                        frontIndexs[index]++
                    } else if (f > list[frontIndexs[index]]) {
                        frontIndexs[index]++
                    } else {
                        break
                    }
                }
            }
        }

        val winBackCounts = IntArray(backLists.size)
        val backIndexs = IntArray(backLists.size)
        winBack.forEach { f ->
            backLists.forEachIndexed { index, list ->
                while (backIndexs[index] < list.size) {
                    if (f == list[backIndexs[index]]) {
                        winBackCounts[index]++
                        backIndexs[index]++
                    } else if (f > list[backIndexs[index]]) {
                        backIndexs[index]++
                    } else {
                        break
                    }
                }
            }
        }

        val winClass = IntArray(10)
        for (i in winFrontCounts.indices) {
            val fc = winFrontCounts[i]
            val bc = winBackCounts[i]
            val cls = LottoUtil.getWinClass(fc, bc)
            winClass[cls]++
        }

        val sb = StringBuilder()
        for (i in 1 until winClass.size) {
            if (winClass[i] > 0) {
                sb.append("${i}等奖-> ${winClass[i]}注\n")
            }
        }
        resultMap[EnumLottoType.SINGLE] =
            if (sb.isEmpty()) "未中奖" else sb.substring(0, sb.length - 1)
        winString.postValue(resultMap[lottoType])
    }
}