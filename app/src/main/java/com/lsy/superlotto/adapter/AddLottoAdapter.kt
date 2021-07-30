package com.lsy.superlotto.adapter

import android.content.Context
import com.lsy.lottodata.db.entity.SelfLottoNumber
import com.lsy.superlotto.BR
import com.lsy.superlotto.R
import com.lsy.baselib.base.BaseAdapter
import com.lsy.superlotto.databinding.ItemAddTicketBinding

/**
 * @author Xuwl
 * @date 2021/7/1
 *
 */
class AddLottoAdapter(mContext: Context?) :
    BaseAdapter<ItemAddTicketBinding, SelfLottoNumber>(mContext, null) {
    init {
        addOneLottoNumber()
    }

    override fun getItemLayoutId(viewType: Int): Int = R.layout.item_add_ticket

    override fun onBindItem(binding: ItemAddTicketBinding?, item: SelfLottoNumber?) {
        binding?.setVariable(BR.model, item)
    }

    /**
     * 添加一行
     */
    fun addOneLottoNumber(): Boolean {
        if (mList.size < 10) {
            addItem(getInitLottoNumber())
            notifyItemInserted(mList.size - 1)
            return true
        }
        return false
    }

    /**
     * 获取一行初始化的数据
     */
    private fun getInitLottoNumber() = SelfLottoNumber("", "", "", "")

    fun getLottoNumberList() = mList
}