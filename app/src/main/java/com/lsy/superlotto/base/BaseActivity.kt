package com.lsy.superlotto.base

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import com.lsy.superlotto.BR
import kotlin.reflect.KClass

/**
 * @author Xuwl
 * @date 2021/6/21
 *
 */
open abstract class BaseActivity<T : ViewDataBinding, VM : ViewModel>(vmClasz: KClass<VM>) :
    AppCompatActivity() {
    protected val viewModel by ViewModelLazy<VM>(vmClasz,
        { viewModelStore },
        { defaultViewModelProviderFactory })

    protected lateinit var binding: T

    protected fun setBindView(layoutRes: Int) {
        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.lifecycleOwner = this
    }

    protected fun setBindingView(layoutRes: Int, viewModel: ViewModel) {
        setBindingView(layoutRes, BR.viewModel, viewModel)
    }

    protected fun setBindingView(layoutRes: Int, variableId: Int, viewModel: ViewModel) {
        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.setVariable(BR.viewModel, viewModel)
        binding.lifecycleOwner = this
    }
}