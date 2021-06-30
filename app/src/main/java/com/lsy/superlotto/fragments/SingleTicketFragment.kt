package com.lsy.superlotto.fragments

import androidx.recyclerview.widget.LinearLayoutManager
import com.lsy.lottodata.db.entity.SelfLottoNumber
import com.lsy.superlotto.BR
import com.lsy.superlotto.R
import com.lsy.superlotto.adapter.SingleLottoAdapter
import com.lsy.superlotto.base.BaseFragment
import com.lsy.superlotto.databinding.FragmentSingleTicketBinding
import com.lsy.superlotto.viewEvent.MainLayoutEvent

/**
 * @author Xuwl
 * @date 2021/6/24
 *
 */
class SingleTicketFragment :
    BaseTicketFramgnet<FragmentSingleTicketBinding, SingleTicketViewModel>(SingleTicketViewModel::class) {
    private lateinit var singleLottoAdapter: SingleLottoAdapter

    override fun layoutViewId(): Int = R.layout.fragment_single_ticket

    override fun initView() {
        binding?.rvSingleLotto?.let { rv ->
            rv.layoutManager = LinearLayoutManager(context)
            rv.adapter = SingleLottoAdapter(context, viewModel.singleList).apply {
                singleLottoAdapter = this
            }
        }
        binding?.viewSingleEmpty?.btnAddTicket?.setOnClickListener {
            mMainViewModel.addLottoTicket()
        }
    }

    override fun initData() {
    }
}