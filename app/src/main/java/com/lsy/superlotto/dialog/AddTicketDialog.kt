package com.lsy.superlotto.dialog

import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.lsy.baselib.utils.SizeUtil
import com.lsy.baselib.utils.ToastUtil
import com.lsy.lottodata.db.entity.LottoTicket
import com.lsy.lottodata.db.entity.SelfLottoNumber
import com.lsy.lottodata.db.entity.enums.EnumLottoType
import com.lsy.superlotto.Common
import com.lsy.superlotto.R
import com.lsy.superlotto.adapter.AddLottoAdapter
import com.lsy.superlotto.databinding.DialogAddTicketBinding
import java.util.*


/**
 * @author Xuwl
 * @date 2021/6/30
 *
 */
class AddTicketDialog(private val ticketType: EnumLottoType) : DialogFragment() {
    protected var callBack: ((LottoTicket, MutableList<SelfLottoNumber>) -> Unit)? = null
    private lateinit var binding: DialogAddTicketBinding

    init {
        setStyle(STYLE_NO_TITLE, R.style.dialogFullScreen)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_add_ticket, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.let { window ->
            window.attributes = window.attributes?.apply {
                width = SizeUtil.dp2px(requireContext(), 360f)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvTicketItem.layoutManager = LinearLayoutManager(context)
        binding.rvTicketItem.adapter = AddLottoAdapter(context)
        binding.ibTicketLottoAdd.setOnClickListener {
            if (!(binding.rvTicketItem.adapter as AddLottoAdapter).addOneLottoNumber()) {
                ToastUtil.showToast(requireContext(), "一次最多加10个")
            }
        }
        binding.ibAddTicketClose.setOnClickListener {
            dismiss()
        }
        binding.btnAddTicketFinish.setOnClickListener {
            val nperStr = binding.etTicketNper.text.toString().trim()
            if (TextUtils.isEmpty(nperStr)) {
                context?.let { ToastUtil.showShortToast(it.applicationContext, "请先输入期数") }
                return@setOnClickListener
            }

            val noStr = binding.etTicketNo.text.toString().trim()
            if (TextUtils.isEmpty(noStr)) {
                context?.let { ToastUtil.showShortToast(it.applicationContext, "请先输入编号") }
                return@setOnClickListener
            }

            val list = (binding.rvTicketItem.adapter as AddLottoAdapter).getLottoNumberList()
            val iterator = list.iterator()
            while (iterator.hasNext()) {
                val number = iterator.next()
                if (TextUtils.isEmpty(number.front) || TextUtils.isEmpty(number.back)) {
                    iterator.remove()
                    continue
                }

                val arr = number.front.split(Common.LOTTO_SPLIT)
                val arr1 = number.back.split(Common.LOTTO_SPLIT)
                if ((EnumLottoType.SINGLE == ticketType && (5 != arr.size || 2 != arr1.size))
                    || (EnumLottoType.SINGLE == ticketType && (arr.size < 5 || 2 > arr1.size))
                ) {
                    iterator.remove()
                    continue
                }
                number.apply {
                    nper = nperStr
                    no = noStr
                }
            }
            callBack?.let { it(LottoTicket(noStr, ticketType, nperStr, Date()), list) }
            dismiss()
        }
    }

    fun show(
        manager: FragmentManager, tag: String?,
        callBack: (LottoTicket, MutableList<SelfLottoNumber>) -> Unit,
    ) {
        super.show(manager, tag)
        this.callBack = callBack
    }
}