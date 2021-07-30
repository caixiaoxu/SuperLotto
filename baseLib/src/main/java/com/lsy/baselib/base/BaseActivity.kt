package com.lsy.baselib.base

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
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

    protected fun setBindingView(layoutRes: Int, variableId: Int) {
        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.setVariable(variableId, viewModel)
        binding.lifecycleOwner = this
    }
}