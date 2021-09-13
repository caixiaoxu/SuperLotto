package com.lsy.historylotto.adapter

import android.content.Context
import android.text.TextUtils
import androidx.recyclerview.widget.DiffUtil
import com.lsy.historylotto.BR
import com.lsy.historylotto.R
import com.lsy.historylotto.databinding.HistoryItemHistoryBinding
import com.lsy.lottodata.db.DBManager
import com.lsy.lottodata.db.entity.LotteryNumber

/**
 * @author Xuwl
 * @date 2021/7/30
 *
 */
class HistoryAdapter(context: Context?) :
    BasePagingAdapter<HistoryItemHistoryBinding, LotteryNumber>(context, object :
        DiffUtil.ItemCallback<LotteryNumber>() {
        override fun areItemsTheSame(oldItem: LotteryNumber, newItem: LotteryNumber): Boolean {
            return !TextUtils.isEmpty(oldItem.nper) && oldItem.nper.equals(newItem)
        }

        override fun areContentsTheSame(oldItem: LotteryNumber, newItem: LotteryNumber): Boolean {
            return oldItem == newItem
        }

    }) {
    private var total = HashMap<String, Int>()
    private val beforeNumMap = HashMap<String, Double>(35)
    private val afterNumMap = HashMap<String, Double>(12)

    private fun getTotoal(nper: String): Int {
        return total[nper] ?: DBManager.db.lotteryNumberDao().queryTotalCountUntilNper(nper)
    }

    private fun getBeforeNumProbability(nper: String, num: String): Double {
        return beforeNumMap[nper + num] ?: DBManager.db.lotteryNumberDao()
            .queryBeforeCountOfNumUntilNper(nper, num) * 10000 / getTotoal(nper) / 100.0
    }

    private fun getAfterNumProbability(nper: String, num: String): Double {
        return afterNumMap[nper + num] ?: DBManager.db.lotteryNumberDao()
            .queryAfterCountOfNumUntilNper(nper, num) * 10000 / getTotoal(nper) / 100.0
    }

    /**
     * 0数字 1概率
     */
    private var model: Int = 0

    fun changeModel() {
        model = if (0 == model) 1 else 0
        notifyDataSetChanged()
    }

    override fun getItemLayoutId(viewType: Int): Int = R.layout.history_item_history

    override fun onBindItem(binding: HistoryItemHistoryBinding?, item: LotteryNumber?) {
        if (null != binding && null != item) {
            binding.historyTvItemHistoryNper.text = item.nper
            if (0 == model) {
                setLotteryLists(binding,
                    item.num1, item.num2, item.num3, item.num4, item.num5, item.num6, item.num7)
            } else {
                setLotteryLists(binding, formatNum(getBeforeNumProbability(item.nper, item.num1)),
                    formatNum(getBeforeNumProbability(item.nper, item.num2)),
                    formatNum(getBeforeNumProbability(item.nper, item.num3)),
                    formatNum(getBeforeNumProbability(item.nper, item.num4)),
                    formatNum(getBeforeNumProbability(item.nper, item.num5)),
                    formatNum(getAfterNumProbability(item.nper, item.num6)),
                    formatNum(getAfterNumProbability(item.nper, item.num7)))
            }
        }
    }

    private fun formatNum(num: Double): String = String.format("%.2f", num)

    private fun setLotteryLists(
        binding: HistoryItemHistoryBinding,
        num1: String,
        num2: String,
        num3: String,
        num4: String,
        num5: String,
        num6: String,
        num7: String,
    ) {
        binding.historyTvItemHistoryBeforeNum1.text = num1
        binding.historyTvItemHistoryBeforeNum2.text = num2
        binding.historyTvItemHistoryBeforeNum3.text = num3
        binding.historyTvItemHistoryBeforeNum4.text = num4
        binding.historyTvItemHistoryBeforeNum5.text = num5
        binding.historyTvItemHistoryAfterNum1.text = num6
        binding.historyTvItemHistoryAfterNum2.text = num7
    }
}