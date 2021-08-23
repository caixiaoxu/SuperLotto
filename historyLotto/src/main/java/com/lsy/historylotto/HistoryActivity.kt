package com.lsy.historylotto

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.core.app.ActivityCompat
import com.lsy.baselib.base.BaseActivity
import com.lsy.historylotto.adapter.HistoryAdapter
import com.lsy.historylotto.databinding.HistoryActivityHistoryBinding
import com.lsy.historylotto.ui.SummaryStatisticsActivity

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
        initView()
    }

    private fun initView() {
        binding.ibHistoryBack.setOnClickListener {
            onBackPressed()
        }
        binding.tvHistoryStatistics.setOnClickListener {
            ActivityCompat.startActivity(this,
                Intent(this, SummaryStatisticsActivity::class.java),
                null)
        }
        binding.switchChangeListModel.setOnCheckedChangeListener { _, _ ->
            (binding.rvHistoryList.adapter as HistoryAdapter).changeModel()
        }
        initSpinner()
        initList()
    }

    /**
     * 实始化时间下拉框
     */
    private fun initSpinner() {
        binding.spinnerFilterTime.apply {
            //设置下拉框默认的显示第一项
            setSelection(0);
            //给下拉框设置选择监听器，一旦用户选中某一项，就触发监听器的onItemSelected方法
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?, view: View?, position: Int, id: Long,
                ) {
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
        }
    }

    /**
     * 初始化列表
     */
    private fun initList() {
        binding.rvHistoryList.adapter = HistoryAdapter(this)
        viewModel.lotteryLiveData.observe(this) {
            (binding.rvHistoryList.adapter as HistoryAdapter).submitData(lifecycle, it)
        }
    }
}