package com.lsy.superlotto.fragments

import com.lsy.lottodata.db.entity.enums.EnumLottoType
import com.lsy.superlotto.R
import com.lsy.superlotto.databinding.FragmentDoubleTicketBinding
import com.lsy.superlotto.dialog.AddTicketDialog

/**
 * @author Xuwl
 * @date 2021/6/24
 *
 */
class DoubleTicketFragment :
    BaseTicketFramgnet<FragmentDoubleTicketBinding, DoubleTicketViewModel>(DoubleTicketViewModel::class) {
    protected val addTicketDialog: AddTicketDialog by lazy { AddTicketDialog(EnumLottoType.DOUBLE) }

    override fun layoutViewId(): Int = R.layout.fragment_double_ticket
    override fun initView() {

        binding?.viewDoubleEmpty?.btnAddTicket?.setOnClickListener {
            addTicketDialog.show(childFragmentManager,
                addTicketDialog::class.java.simpleName) { ticket, list ->
                mMainViewModel.addLottoTicket(ticket, list)
            }
        }
    }

    override fun initData() {
    }
}