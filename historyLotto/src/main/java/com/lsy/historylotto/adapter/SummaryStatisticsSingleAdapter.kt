package com.lsy.historylotto.adapter

import android.content.Context
import com.lsy.baselib.base.BaseSingleAdapter
import com.lsy.historylotto.BR
import com.lsy.historylotto.R
import com.lsy.historylotto.databinding.HistoryItemStatisticsBinding
import com.lsy.historylotto.entity.SummaryStatisticsItemEntity

/**
 * @author Xuwl
 * @date 2021/8/23
 *
 */
class SummaryStatisticsAdapter(context: Context, list: List<SummaryStatisticsItemEntity>? = null) :
    BaseSingleAdapter<HistoryItemStatisticsBinding, SummaryStatisticsItemEntity>(context, list) {

    override fun getItemLayoutId(viewType: Int): Int = R.layout.history_item_statistics

    override fun onBindItem(
        binding: HistoryItemStatisticsBinding?,
        item: SummaryStatisticsItemEntity?,
    ) {
        binding?.setVariable(BR.data, item)
    }
}