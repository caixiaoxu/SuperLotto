package com.lsy.historylotto

import android.os.Bundle
import com.lsy.baselib.base.BaseActivity
import com.lsy.historylotto.databinding.HistoryActivityHistoryBinding

/**
 * @author Xuwl
 * @date 2021/7/30
 *
 */
class HistoryActivity :
    BaseActivity<HistoryActivityHistoryBinding, HistoryViewModel>(HistoryViewModel::class) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBindingView(R.layout.history_activity_history, BR.vm)
    }
}