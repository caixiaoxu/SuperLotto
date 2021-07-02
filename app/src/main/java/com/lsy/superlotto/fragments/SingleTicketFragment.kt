package com.lsy.superlotto.fragments

import androidx.recyclerview.widget.LinearLayoutManager
import com.lsy.lottodata.db.DBManager
import com.lsy.lottodata.db.entity.LottoTicket
import com.lsy.lottodata.db.entity.SelfLottoNumber
import com.lsy.lottodata.db.entity.enums.EnumLottoType
import com.lsy.superlotto.R
import com.lsy.superlotto.adapter.SingleLottoAdapter
import com.lsy.superlotto.databinding.FragmentTicketBinding
import com.lsy.superlotto.dialog.AddTicketDialog
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

/**
 * @author Xuwl
 * @date 2021/6/24
 *
 */
class SingleTicketFragment :
    BaseTicketFramgnet<FragmentTicketBinding, SingleTicketViewModel>(SingleTicketViewModel::class) {
    protected val addTicketDialog: AddTicketDialog by lazy { AddTicketDialog(EnumLottoType.SINGLE) }
    private lateinit var singleLottoAdapter: SingleLottoAdapter

    override fun layoutViewId(): Int = R.layout.fragment_ticket

    override fun initView() {
        binding?.rvTicketLotto?.let { rv ->
            rv.layoutManager = LinearLayoutManager(context)
            rv.adapter = SingleLottoAdapter(context, null).apply {
                singleLottoAdapter = this
            }
        }
        binding?.btnAddTicket?.setOnClickListener { addTicket() }
        binding?.ibAddTicket?.setOnClickListener { addTicket() }
    }

    private fun addTicket() {
//        addTicketDialog.show(childFragmentManager,
//            addTicketDialog::class.java.simpleName) { ticket, list ->
//        mMainViewModel.addLottoTicket(ticket, list)
        mMainViewModel.addLottoTicket(LottoTicket("112111-111111-111111-111111",
            EnumLottoType.SINGLE, "21075", Date()), arrayListOf(
            SelfLottoNumber("112111-111111-111111-111111", "21075", "01,02,03,04,05", "01,02")
        ))
//        }
    }

    override fun initData() {
        mMainViewModel.singleTicketList.observe(this) {
            viewModel.mTicketList.postValue(it)
            viewModel.getTypeLottoList(it)
        }
        viewModel.mLottoList.observe(this) {
            binding?.rvTicketLotto?.adapter?.let { adapter ->
                (adapter as SingleLottoAdapter).setList(it)
            }
        }
    }
}