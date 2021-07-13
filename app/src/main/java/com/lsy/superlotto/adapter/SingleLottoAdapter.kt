package com.lsy.superlotto.adapter

import android.content.Context
import androidx.appcompat.widget.AppCompatTextView
import com.lsy.lottodata.db.entity.LotteryNumber
import com.lsy.lottodata.db.entity.SelfLottoNumber
import com.lsy.superlotto.Common
import com.lsy.superlotto.R
import com.lsy.superlotto.base.BaseAdapter
import com.lsy.superlotto.databinding.ItemSingleLottoBinding
import java.util.*

/**
 * @author Xuwl
 * @date 2021/6/25
 *
 */
class SingleLottoAdapter(mContext: Context?, mList: List<SelfLottoNumber>?) :
    BaseAdapter<ItemSingleLottoBinding, SelfLottoNumber>(mContext, mList) {

    var mLotteryNumber: LotteryNumber? = null

    override fun getItemLayoutId(viewType: Int): Int = R.layout.item_single_lotto

    override fun onBindItem(binding: ItemSingleLottoBinding?, item: SelfLottoNumber?) {
        item?.let {
            if (item.front.isEmpty() || item.back.isEmpty()) {
                return
            }
            val frontArr = item.front.split(Common.LOTTO_SPLIT)
            if (5 != frontArr.size) {
                return
            }
            Collections.sort(frontArr) { o1, o2 -> o1.toInt() - o2.toInt() }

            val backArr = item.back.split(Common.LOTTO_SPLIT)
            if (2 != backArr.size) {
                return
            }
            Collections.sort(backArr) { o1, o2 -> o1.toInt() - o2.toInt() }

            setTextAndBGg(binding?.tvLotteryNum1,
                frontArr[0], mLotteryNumber?.num1?.toInt() == frontArr[0].toInt())
            setTextAndBGg(binding?.tvLotteryNum2,
                frontArr[1], mLotteryNumber?.num2?.toInt() == frontArr[1].toInt())
            setTextAndBGg(binding?.tvLotteryNum3,
                frontArr[2], mLotteryNumber?.num3?.toInt() == frontArr[2].toInt())
            setTextAndBGg(binding?.tvLotteryNum4,
                frontArr[3], mLotteryNumber?.num4?.toInt() == frontArr[3].toInt())
            setTextAndBGg(binding?.tvLotteryNum5,
                frontArr[4], mLotteryNumber?.num5?.toInt() == frontArr[4].toInt())
            setTextAndBGg(binding?.tvLotteryNum6,
                backArr[0], mLotteryNumber?.num6?.toInt() == backArr[0].toInt())
            setTextAndBGg(binding?.tvLotteryNum7,
                backArr[1], mLotteryNumber?.num7?.toInt() == backArr[1].toInt())
        }
    }

    private fun setTextAndBGg(textView: AppCompatTextView?, text: String, isSame: Boolean) {
        textView?.text = text
        textView?.setBackgroundResource(if (isSame) R.drawable.bg_item_same else R.drawable.bg_item_lottery)
    }
}