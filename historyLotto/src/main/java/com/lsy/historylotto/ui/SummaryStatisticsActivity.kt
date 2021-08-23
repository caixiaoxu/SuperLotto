package com.lsy.historylotto.ui

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.lsy.baselib.base.BaseActivity
import com.lsy.historylotto.BR
import com.lsy.historylotto.R
import com.lsy.historylotto.adapter.SummaryStatisticsAdapter
import com.lsy.historylotto.databinding.HistoryActivitySummaryStatisticsBinding

/**
 * @author Xuwl
 * @date 2021/8/23
 *
 */
class SummaryStatisticsActivity :
    BaseActivity<HistoryActivitySummaryStatisticsBinding, SummaryStatisticsViewModel>(
        SummaryStatisticsViewModel::class) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBindingView(R.layout.history_activity_summary_statistics, BR.vm)
        binding.ibStatisticsBack.setOnClickListener {
            onBackPressed()
        }
        initList()
        initObserver()
        initData()
    }

    private fun initList() {
        binding.rvStatisticsBeforeList.adapter = SummaryStatisticsAdapter(this)
        binding.rvStatisticsAfterList.adapter = SummaryStatisticsAdapter(this)
    }

    fun initData(){
        viewModel.queryBeforeLotteryStatistics()
        viewModel.queryAfterLotteryStatistics()
    }

    private fun initObserver() {
        viewModel.beforeStatistics.observe(this) {
            (binding.rvStatisticsBeforeList.adapter as SummaryStatisticsAdapter).setList(it)
        }
        viewModel.afterStatistics.observe(this) {
            (binding.rvStatisticsAfterList.adapter as SummaryStatisticsAdapter).setList(it)
        }
    }
}