package com.lsy.superlotto

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lsy.lottodata.db.DBManager
import com.lsy.lottodata.db.entity.LotteryNumber
import com.lsy.lottodata.pydocking.NetLottery
import kotlinx.coroutines.launch

/**
 * @author Xuwl
 * @date 2021/6/21
 *
 */
class MainViewModel : ViewModel() {
    val lottery: MutableLiveData<LotteryNumber> by lazy {
        MutableLiveData<LotteryNumber>().also {
            loadLottery()
            showCurLottery()
        }
    }

    fun loadLottery() {
        viewModelScope.launch() {
            DBManager.createLotteryTable()
            NetLottery.callLottery(DBManager.dbPath, DBManager.TABLE_LOTTERY_NAME)
        }
    }

    fun showCurLottery() {
        viewModelScope.launch {
            val first = DBManager.db.lotteryNumberDao().getFirst()
            lottery.postValue(first)
        }
    }
}