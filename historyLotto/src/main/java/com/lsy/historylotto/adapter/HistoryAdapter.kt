package com.lsy.historylotto.adapter

import android.content.Context
import android.text.TextUtils
import androidx.recyclerview.widget.DiffUtil
import com.lsy.historylotto.BR
import com.lsy.historylotto.R
import com.lsy.historylotto.databinding.ItemHistoryBinding
import com.lsy.lottodata.db.entity.LotteryNumber

/**
 * @author Xuwl
 * @date 2021/7/30
 *
 */
class HistoryAdapter(context: Context?) :
    BasePagingAdapter<ItemHistoryBinding, LotteryNumber>(context, object :
        DiffUtil.ItemCallback<LotteryNumber>() {
        override fun areItemsTheSame(oldItem: LotteryNumber, newItem: LotteryNumber): Boolean {
            return !TextUtils.isEmpty(oldItem.nper) && oldItem.nper.equals(newItem)
        }

        override fun areContentsTheSame(oldItem: LotteryNumber, newItem: LotteryNumber): Boolean {
            return oldItem == newItem
        }

    }) {
    override fun getItemLayoutId(viewType: Int): Int = R.layout.item_history

    override fun onBindItem(binding: ItemHistoryBinding?, item: LotteryNumber?) {
        if (null != binding && null != item) {
            binding.setVariable(BR.vm, item)
        }
    }
}