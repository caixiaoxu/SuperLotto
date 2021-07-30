package com.lsy.superlotto

import android.content.Intent
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.viewpager2.widget.ViewPager2
import com.lsy.lottodata.db.entity.enums.EnumLottoType
import com.lsy.superlotto.adapter.LottoAdapter
import com.lsy.baselib.base.BaseActivity
import com.lsy.historylotto.HistoryActivity
import com.lsy.superlotto.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(MainViewModel::class) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBindingView(R.layout.activity_main, BR.viewModel)
        binding.bgLottery.setOnClickListener {
            ActivityCompat.startActivity(this, Intent(this, HistoryActivity::class.java), null)
        }
        initViewPager2()
    }

    fun initViewPager2() {
        binding.vp2SelfLotto.adapter = LottoAdapter(this)
        binding.vp2SelfLotto.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                viewModel.lottoType =
                    if (0 == position) EnumLottoType.SINGLE else EnumLottoType.DOUBLE
                viewModel.refreshWinResult()
            }
        })
    }
}