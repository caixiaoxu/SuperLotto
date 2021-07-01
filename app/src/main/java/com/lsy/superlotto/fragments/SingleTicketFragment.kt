package com.lsy.superlotto.fragments

import androidx.recyclerview.widget.LinearLayoutManager
import com.lsy.lottodata.db.entity.enums.EnumLottoType
import com.lsy.superlotto.R
import com.lsy.superlotto.adapter.SingleLottoAdapter
import com.lsy.superlotto.databinding.FragmentSingleTicketBinding
import com.lsy.superlotto.dialog.AddTicketDialog

/**
 * @author Xuwl
 * @date 2021/6/24
 *
 */
class SingleTicketFragment :
    BaseTicketFramgnet<FragmentSingleTicketBinding, SingleTicketViewModel>(SingleTicketViewModel::class) {
    protected val addTicketDialog: AddTicketDialog by lazy { AddTicketDialog(EnumLottoType.SINGLE) }
    private lateinit var singleLottoAdapter: SingleLottoAdapter

    override fun layoutViewId(): Int = R.layout.fragment_single_ticket

    override fun initView() {
        binding?.rvSingleLotto?.let { rv ->
            rv.layoutManager = LinearLayoutManager(context)
            rv.adapter = SingleLottoAdapter(context, null).apply {
                singleLottoAdapter = this
            }
        }
        binding?.viewSingleEmpty?.btnAddTicket?.setOnClickListener {
            addTicketDialog.show(childFragmentManager,
                addTicketDialog::class.java.simpleName) { ticket, list ->
                mMainViewModel.addLottoTicket(ticket, list)
            }
        }
    }

    override fun initData() {
        viewModel.test.postValue("测试")
        mMainViewModel.singleTicketList.observe(this) {
            viewModel.mSingleList.postValue(it)
        }
        viewModel.mSingleList.observe(this) { list ->
            binding?.rvSingleLotto?.adapter?.let { adapter ->
                (adapter as SingleLottoAdapter).setList(list)
            }
        }
    }
}