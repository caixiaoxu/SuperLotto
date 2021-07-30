package com.lsy.historylotto.adapter

import android.content.Context
import com.lsy.baselib.base.BaseAdapter
import com.lsy.historylotto.R
import com.lsy.historylotto.databinding.ItemHistoryBinding
import com.lsy.lottodata.db.entity.LotteryNumber

/**
 * @author Xuwl
 * @date 2021/7/30
 *
 */
class HistoryAdapter(context: Context?) :
    BaseAdapter<ItemHistoryBinding, LotteryNumber>(context, null) {
    override fun getItemLayoutId(viewType: Int): Int = R.layout.item_history

    override fun onBindItem(binding: ItemHistoryBinding?, item: LotteryNumber?) {
    }
}