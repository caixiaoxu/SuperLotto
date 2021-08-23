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
    private val beforeNumMap = HashMap<String, Int>(35)
    private val afterNumMap = HashMap<String, Int>(12)

    private fun getTotoal(): Int {
        return total ?: DBManager.db.lotteryNumberDao().queryTotalCount()
    }

    private fun getBeforeNumProbability(num: String): Int {
        return beforeNumMap[num] ?: DBManager.db.lotteryNumberDao()
            .queryBeforeCountOfNum(num) * 100 / getTotoal()
    }

    private fun getAfterNumProbability(num: String): Int {
        return afterNumMap[num] ?: DBManager.db.lotteryNumberDao()
            .queryAfterCountOfNum(num) * 100 / getTotoal()
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
                binding.historyTvItemHistoryBeforeLottery.text =
                    "${item.num1},${item.num2},${item.num3},${item.num4},${item.num5}"
                binding.historyTvItemHistoryAfterLottery.text =
                    "${item.num6},${item.num7}"
            } else {
                binding.historyTvItemHistoryBeforeLottery.text =
                    "${getBeforeNumProbability(item.num1)},${getBeforeNumProbability(item.num2)}," +
                            "${getBeforeNumProbability(item.num3)},${getBeforeNumProbability(item.num4)}," +
                            "${getBeforeNumProbability(item.num5)}"
                binding.historyTvItemHistoryAfterLottery.text =
                    "${getAfterNumProbability(item.num6)},${getAfterNumProbability(item.num7)}"
            }
        }
    }
}