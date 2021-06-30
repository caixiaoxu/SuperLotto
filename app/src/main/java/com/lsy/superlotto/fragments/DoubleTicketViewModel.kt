package com.lsy.superlotto.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lsy.lottodata.db.entity.LottoTicket
import com.lsy.lottodata.db.entity.SelfLottoNumber

/**
 * @author Xuwl
 * @date 2021/6/25
 *
 */
class DoubleTicketViewModel : ViewModel() {
    val mTicketList: MutableLiveData<List<LottoTicket>> = MutableLiveData()
    val doubleList: MutableList<SelfLottoNumber> = ArrayList()
}