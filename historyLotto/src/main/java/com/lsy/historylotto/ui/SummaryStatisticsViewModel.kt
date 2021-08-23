package com.lsy.historylotto.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lsy.historylotto.entity.SummaryStatisticsItemEntity
import com.lsy.lottodata.db.DBManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @author Xuwl
 * @date 2021/8/23
 *
 */
class SummaryStatisticsViewModel : ViewModel() {
    val beforeStatistics = MutableLiveData<ArrayList<SummaryStatisticsItemEntity>>()
    val afterStatistics = MutableLiveData<ArrayList<SummaryStatisticsItemEntity>>()

    fun queryBeforeLotteryStatistics() {
        viewModelScope.launch {
            val list = getItemList(0, 35)
            beforeStatistics.postValue(list)
        }
    }

    fun queryAfterLotteryStatistics() {
        viewModelScope.launch {
            val list = getItemList(1, 12)
            afterStatistics.postValue(list)
        }
    }

    /**
     * 获取Item列表
     * @param type 类型0前区 1后区
     * @param last 取值最大值
     */
    suspend fun getItemList(type: Int, last: Int) =
        withContext(Dispatchers.IO) {
            val list = ArrayList<SummaryStatisticsItemEntity>()
            val total = DBManager.db.lotteryNumberDao().queryTotalCountSync()
            for (i in 1..last) {
                val num = String.format("%02d", i)
                val count =
                    if (0 == type) DBManager.db.lotteryNumberDao().queryBeforeCountOfNumSync(num)
                    else DBManager.db.lotteryNumberDao().queryAfterCountOfNumSync(num)
                val probability = count * 100 / total
                list.add(SummaryStatisticsItemEntity(num, count, probability))
            }
            return@withContext list
        }
}