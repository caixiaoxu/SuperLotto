package com.lsy.historylotto

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import com.lsy.baselib.base.BaseActivity
import com.lsy.historylotto.adapter.HistoryAdapter
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
        initSpinner()
        initList()
    }

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

    private fun initList() {
        binding.rvHistoryList.adapter = HistoryAdapter(this)
    }
}