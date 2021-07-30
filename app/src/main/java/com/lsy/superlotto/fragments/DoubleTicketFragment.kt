package com.lsy.superlotto.fragments

import androidx.recyclerview.widget.LinearLayoutManager
import com.lsy.lottodata.db.entity.enums.EnumLottoType
import com.lsy.superlotto.BR
import com.lsy.superlotto.R
import com.lsy.superlotto.adapter.DoubleLottoAdapter
import com.lsy.superlotto.adapter.SingleLottoAdapter
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
        binding?.rvTicketLotto?.let { rv ->
            rv.layoutManager = LinearLayoutManager(context)
            rv.adapter = DoubleLottoAdapter(context, null).apply {
                mLotteryNumber = mMainViewModel.lottery.value
            }
        }

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
        mMainViewModel.lottery.observe(this) {
            (binding?.rvTicketLotto?.adapter as? DoubleLottoAdapter)?.mLotteryNumber = it
        }
        mMainViewModel.doubleTicketList.observe(this) {
            viewModel.mTicketList.postValue(it)
            viewModel.getTypeLottoList(it)
        }
        viewModel.mLottoList.observe(this) {
            (binding?.rvTicketLotto?.adapter as? DoubleLottoAdapter)?.setList(it)
            if (!it.isNullOrEmpty()) {
                mMainViewModel.dealDoubleWinResult(it)
            }
        }
    }
}