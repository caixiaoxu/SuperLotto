package com.lsy.superlotto.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lsy.lottodata.db.DBManager
import com.lsy.lottodata.db.entity.LottoTicket
import com.lsy.lottodata.db.entity.SelfLottoNumber
import com.lsy.lottodata.db.entity.enums.EnumLottoType
import kotlinx.coroutines.launch

/**
 * @author Xuwl
 * @date 2021/7/2
 *
 */
open class BaseTicketViewModel(val ticketType: EnumLottoType) : ViewModel() {
    val mTicketList: MutableLiveData<List<LottoTicket>> = MutableLiveData()
    val mLottoList: MutableLiveData<List<SelfLottoNumber>> = MutableLiveData()

    fun getTypeLottoList(ticketList: List<LottoTicket>) {
        viewModelScope.launch {
            val list = ArrayList<SelfLottoNumber>()
            ticketList.forEach {
                list.addAll(DBManager.db.selfLottoNumberDao().getLottoWithNoAndNper(it.no, it.nper))
            }
            mLottoList.postValue(list)
        }
    }
}