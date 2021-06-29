package com.lsy.superlotto.fragments

import androidx.lifecycle.ViewModel
import com.lsy.lottodata.db.entity.SelfLottoNumber

/**
 * @author Xuwl
 * @date 2021/6/25
 *
 */
class SingleTicketViewModel : ViewModel() {
    val singleList: MutableList<SelfLottoNumber> = ArrayList()
}