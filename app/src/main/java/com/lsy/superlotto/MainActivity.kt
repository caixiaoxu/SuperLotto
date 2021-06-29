package com.lsy.superlotto

import android.os.Bundle
import androidx.activity.viewModels
import com.lsy.superlotto.adapter.LottoAdapter
import com.lsy.superlotto.base.BaseActivity
import com.lsy.superlotto.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(MainViewModel::class) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBindingView(R.layout.activity_main, viewModel)
//        setBindingView(R.layout.activity_main, BR.viewModel, viewModel)
        initViewPager2()
    }

    fun initViewPager2() {
        binding.vp2SelfLotto.adapter = LottoAdapter(this)
    }
}