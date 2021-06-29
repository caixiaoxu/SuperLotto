package com.lsy.superlotto

import android.os.Bundle
import com.lsy.superlotto.adapter.LottoAdapter
import com.lsy.superlotto.base.BaseActivity
import com.lsy.superlotto.databinding.ActivityMainBinding
import com.lsy.superlotto.viewEvent.MainLayoutEvent

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(MainViewModel::class) {
    private lateinit var adapter: LottoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBindingView(R.layout.activity_main, viewModel)
        binding.setVariable(BR.event, MainLayoutEvent())
//        setBindingView(R.layout.activity_main, BR.viewModel, viewModel)
        initViewPager2()
    }

    fun initViewPager2() {
        adapter = LottoAdapter(this)
        binding.vp2SelfLotto.adapter = adapter
        viewModel.ticket.observe(this) {
            adapter.setList(it)
        }
    }
}