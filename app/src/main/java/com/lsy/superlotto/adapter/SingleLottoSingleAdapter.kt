package com.lsy.superlotto.adapter

import android.content.Context
import androidx.appcompat.widget.AppCompatTextView
import com.lsy.lottodata.db.entity.LotteryNumber
import com.lsy.lottodata.db.entity.SelfLottoNumber
import com.lsy.lottodata.db.entity.enums.EnumLottoPosition
import com.lsy.superlotto.Common
import com.lsy.superlotto.R
import com.lsy.baselib.base.BaseSingleAdapter
import com.lsy.superlotto.databinding.ItemSingleLottoBinding
import java.util.*

/**
 * @author Xuwl
 * @date 2021/6/25
 *
 */
class SingleLottoSingleAdapter(mContext: Context?, mList: List<SelfLottoNumber>?) :
    BaseSingleAdapter<ItemSingleLottoBinding, SelfLottoNumber>(mContext, mList) {

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

            setTextAndBGg(binding?.tvLotteryNum1, frontArr[0], hasSame(frontArr[0].toInt(),
                EnumLottoPosition.FRONT))
            setTextAndBGg(binding?.tvLotteryNum2, frontArr[1], hasSame(frontArr[1].toInt(),
                EnumLottoPosition.FRONT))
            setTextAndBGg(binding?.tvLotteryNum3, frontArr[2], hasSame(frontArr[2].toInt(),
                EnumLottoPosition.FRONT))
            setTextAndBGg(binding?.tvLotteryNum4, frontArr[3], hasSame(frontArr[3].toInt(),
                EnumLottoPosition.FRONT))
            setTextAndBGg(binding?.tvLotteryNum5, frontArr[4], hasSame(frontArr[4].toInt(),
                EnumLottoPosition.FRONT))
            setTextAndBGg(binding?.tvLotteryNum6, backArr[0], hasSame(backArr[0].toInt(),
                EnumLottoPosition.BACK))
            setTextAndBGg(binding?.tvLotteryNum7, backArr[1], hasSame(backArr[1].toInt(),
                EnumLottoPosition.BACK))
        }
    }

    private fun hasSame(lotto: Int, pos: EnumLottoPosition): Boolean {
        return ((EnumLottoPosition.FRONT == pos && (lotto == mLotteryNumber?.num1?.toInt()
                || lotto == mLotteryNumber?.num2?.toInt() || lotto == mLotteryNumber?.num3?.toInt()
                || lotto == mLotteryNumber?.num4?.toInt() || lotto == mLotteryNumber?.num5?.toInt()))
                || (EnumLottoPosition.BACK == pos && (lotto == mLotteryNumber?.num6?.toInt()
                || lotto == mLotteryNumber?.num7?.toInt())))
    }

    private fun setTextAndBGg(textView: AppCompatTextView?, text: String, isSame: Boolean) {
        textView?.text = text
        textView?.setBackgroundResource(if (isSame) R.drawable.bg_item_same else R.drawable.bg_item_lottery)
    }
}