package com.lsy.superlotto.fragments

import com.lsy.superlotto.R
import com.lsy.superlotto.base.BaseFragment
import com.lsy.superlotto.databinding.FragmentDoubleTicketBinding

/**
 * @author Xuwl
 * @date 2021/6/24
 *
 */
class DoubleTicketFragment :
    BaseTicketFramgnet<FragmentDoubleTicketBinding, DoubleTicketViewModel>(DoubleTicketViewModel::class) {
    override fun layoutViewId(): Int = R.layout.fragment_double_ticket
    override fun initView() {
    }

    override fun initData() {
    }
}