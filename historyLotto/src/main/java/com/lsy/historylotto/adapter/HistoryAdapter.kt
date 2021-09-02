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
    private var total: Int? = null
    private val beforeNumMap = HashMap<String, Double>(35)
    private val afterNumMap = HashMap<String, Double>(12)

    private fun getTotoal(): Int {
        return total ?: DBManager.db.lotteryNumberDao().queryTotalCount()
    }

    private fun getBeforeNumProbability(num: String): Double {
        return beforeNumMap[num] ?: DBManager.db.lotteryNumberDao()
            .queryBeforeCountOfNum(num) * 1000 / getTotoal() / 10.0
    }

    private fun getAfterNumProbability(num: String): Double {
        return afterNumMap[num] ?: DBManager.db.lotteryNumberDao()
            .queryAfterCountOfNum(num) * 1000 / getTotoal() / 10.0
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
            binding.setVariable(BR.vm, item)
            if (0 == model) {
                setLotteryLists(binding,
                    item.num1, item.num2, item.num3, item.num4, item.num5, item.num6, item.num7)
            } else {
                setLotteryLists(binding,
                    "${getBeforeNumProbability(item.num1)}%",
                    "${getBeforeNumProbability(item.num2)}%",
                    "${getBeforeNumProbability(item.num3)}%",
                    "${getBeforeNumProbability(item.num4)}%",
                    "${getBeforeNumProbability(item.num5)}%",
                    "${getAfterNumProbability(item.num6)}%",
                    "${getAfterNumProbability(item.num7)}%")
            }
        }
    }

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