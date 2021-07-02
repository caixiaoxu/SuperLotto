package com.lsy.superlotto.fragments

import com.lsy.lottodata.db.entity.enums.EnumLottoType
import com.lsy.superlotto.R
import com.lsy.superlotto.databinding.FragmentTicketBinding
import com.lsy.superlotto.dialog.AddTicketDialog

/**
 * @author Xuwl
 * @date 2021/6/24
 *
 */
class DoubleTicketFragment :
    BaseTicketFramgnet<FragmentTicketBinding, DoubleTicketViewModel>(DoubleTicketViewModel::class) {
    protected val addTicketDialog: AddTicketDialog by lazy { AddTicketDialog(EnumLottoType.DOUBLE) }

    override fun layoutViewId(): Int = R.layout.fragment_ticket
    override fun initView() {

        binding?.btnAddTicket?.setOnClickListener { addTicket() }
        binding?.ibAddTicket?.setOnClickListener { addTicket() }
    }

    private fun addTicket() {
        addTicketDialog.show(childFragmentManager,
            addTicketDialog::class.java.simpleName) { ticket, list ->
            mMainViewModel.addLottoTicket(ticket, list)
        }
    }

    override fun initData() {
    }
}