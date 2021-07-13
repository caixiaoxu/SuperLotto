package com.lsy.superlotto.utils

/**
 * @author Xuwl
 * @date 2021/7/13
 *
 */
object LottoUtil {

    /**
     * 根据前区相同数和后区相同数，获得中奖等级
     * 一等奖：5+2
     * 二等奖：5+1
     * 三等奖：5+0
     * 四等奖：4+2
     * 五等奖：4+1
     * 六等奖：3+2
     * 七等奖：4+0
     * 八等奖：3+1/2+2
     * 九等奖：3+0/2+1/1+2/0+2
     */
    fun getWinClass(front: Int, back: Int): Int {
        return when (front + back) {
            7 -> 1
            6 -> if (5 == front) 2 else 4
            5 -> if (5 == front) 3 else if (4 == front) 5 else 6
            4 -> if (4 == front) 7 else 8
            3 -> 9
            2 -> if (2 == back) 9 else 0
            else -> 0
        }
    }
}