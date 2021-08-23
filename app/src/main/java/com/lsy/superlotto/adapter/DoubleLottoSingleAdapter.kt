package com.lsy.superlotto.adapter

import android.content.Context
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.lsy.baselib.utils.SizeUtil
import com.lsy.lottodata.db.entity.LotteryNumber
import com.lsy.lottodata.db.entity.SelfLottoNumber
import com.lsy.lottodata.db.entity.enums.EnumLottoPosition
import com.lsy.superlotto.Common
import com.lsy.superlotto.R
import com.lsy.baselib.base.BaseSingleAdapter
import com.lsy.superlotto.databinding.ItemDoubleLottoBinding
import java.util.*

/**
 * @author Xuwl
 * @date 2021/6/25
 *
 */
class DoubleLottoSingleAdapter(mContext: Context?, mList: List<SelfLottoNumber>?) :
    BaseSingleAdapter<ItemDoubleLottoBinding, SelfLottoNumber>(mContext, mList) {

    var mLotteryNumber: LotteryNumber? = null

    override fun getItemLayoutId(viewType: Int): Int = R.layout.item_double_lotto

    override fun onBindItem(binding: ItemDoubleLottoBinding?, item: SelfLottoNumber?) {
        item?.let {
            if (item.front.isEmpty() || item.back.isEmpty()) {
                return
            }
            val frontArr = item.front.split(Common.LOTTO_SPLIT)
            if (5 > frontArr.size) {
                return
            }
            Collections.sort(frontArr) { o1, o2 -> o1.toInt() - o2.toInt() }

            val backArr = item.back.split(Common.LOTTO_SPLIT)
            if (2 > backArr.size) {
                return
            }
            Collections.sort(backArr) { o1, o2 -> o1.toInt() - o2.toInt() }

            binding?.llDoubleBefore?.let { llBefore ->
                initLottoViews(llBefore, frontArr, EnumLottoPosition.FRONT)
            }

            binding?.llDoubleBack?.let { llBack ->
                initLottoViews(llBack, backArr, EnumLottoPosition.BACK)
            }
        }
    }

    private fun initLottoViews(
        linearLayout: LinearLayout,
        arr: List<String>,
        pos: EnumLottoPosition,
    ) {
        linearLayout.removeAllViews()

        val wh = linearLayout.context.resources.getDimensionPixelSize(R.dimen.lottery_wh)
        arr.forEachIndexed { index, f ->
            val textView = getLotteryTextView(linearLayout.context)
            setTextAndBGg(textView, f, hasSame(f.toInt(), pos))
            linearLayout.addView(textView, LinearLayout.LayoutParams(wh, wh).apply {
                marginStart = SizeUtil.dp2px(linearLayout.context, 5f)
            })
        }
    }

    private fun hasSame(lotto: Int, pos: EnumLottoPosition): Boolean {
        return ((EnumLottoPosition.FRONT == pos && (lotto == mLotteryNumber?.num1?.toInt()
                || lotto == mLotteryNumber?.num2?.toInt() || lotto == mLotteryNumber?.num3?.toInt()
                || lotto == mLotteryNumber?.num4?.toInt() || lotto == mLotteryNumber?.num5?.toInt()))
                || (EnumLottoPosition.BACK == pos && (lotto == mLotteryNumber?.num6?.toInt()
                || lotto == mLotteryNumber?.num7?.toInt())))
    }

    private fun setTextAndBGg(textView: TextView?, text: String, isSame: Boolean) {
        textView?.text = text
        textView?.setBackgroundResource(if (isSame) R.drawable.bg_item_same else R.drawable.bg_item_lottery)
    }

    private fun getLotteryTextView(context: Context): TextView {
        return TextView(context).apply {
            setBackgroundResource(R.drawable.bg_item_lottery)
            gravity = Gravity.CENTER
            textSize = 18f
            setTextColor(ResourcesCompat.getColor(context.resources, R.color.black, null))
        }
    }
}