package com.lsy.historylotto

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.lsy.lottodata.db.DBManager

/**
 * @author Xuwl
 * @date 2021/7/30
 *
 */
class HistoryViewModel : ViewModel() {
    val lotteryLiveData = Pager(PagingConfig(pageSize = 20)) {
        DBManager.db.lotteryNumberDao().getAllForDataSource()
    }.liveData.cachedIn(viewModelScope)
}