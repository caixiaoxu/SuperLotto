package com.lsy.superlotto.adapter

import android.content.Context
import com.lsy.lottodata.db.entity.SelfLottoNumber
import com.lsy.superlotto.R
import com.lsy.superlotto.base.BaseAdapter
import com.lsy.superlotto.databinding.ItemSingleLottoBinding

/**
 * @author Xuwl
 * @date 2021/6/25
 *
 */
class SingleLottoAdapter(mContext: Context?, mList: MutableList<SelfLottoNumber>) :
    BaseAdapter<ItemSingleLottoBinding, SelfLottoNumber>(mContext, mList) {

    override fun getItemLayoutId(viewType: Int): Int = R.layout.item_single_lotto

    override fun onBindItem(binding: ItemSingleLottoBinding?, item: SelfLottoNumber) {
    }
}