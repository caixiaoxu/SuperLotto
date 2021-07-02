package com.lsy.superlotto.adapter

import android.content.Context
import com.lsy.lottodata.db.entity.SelfLottoNumber
import com.lsy.superlotto.Common
import com.lsy.superlotto.R
import com.lsy.superlotto.base.BaseAdapter
import com.lsy.superlotto.databinding.ItemSingleLottoBinding

/**
 * @author Xuwl
 * @date 2021/6/25
 *
 */
class SingleLottoAdapter(mContext: Context?, mList: List<SelfLottoNumber>?) :
    BaseAdapter<ItemSingleLottoBinding, SelfLottoNumber>(mContext, mList) {

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

            val backArr = item.back.split(Common.LOTTO_SPLIT)
            if (2 != backArr.size) {
                return
            }

            binding?.tvLotteryNum1?.text = frontArr[0]
            binding?.tvLotteryNum2?.text = frontArr[1]
            binding?.tvLotteryNum3?.text = frontArr[2]
            binding?.tvLotteryNum4?.text = frontArr[3]
            binding?.tvLotteryNum5?.text = frontArr[4]

            binding?.tvLotteryNum6?.text = backArr[0]
            binding?.tvLotteryNum7?.text = backArr[1]

        }
    }
}