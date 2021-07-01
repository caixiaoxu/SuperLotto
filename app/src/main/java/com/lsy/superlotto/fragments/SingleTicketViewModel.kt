package com.lsy.superlotto.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lsy.lottodata.db.entity.TicketAndLotto

/**
 * @author Xuwl
 * @date 2021/6/25
 *
 */
class SingleTicketViewModel : ViewModel() {
    val mSingleList: MutableLiveData<List<TicketAndLotto>> = MutableLiveData()
    val test:MutableLiveData<String> = MutableLiveData()
}