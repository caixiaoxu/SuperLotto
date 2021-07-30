package com.lsy.baselib.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import kotlin.reflect.KClass

/**
 * @author Xuwl
 * @date 2021/6/24
 *
 */
abstract class BaseFragment<T : ViewDataBinding, VM : ViewModel>(vmClasz: KClass<VM>) : Fragment() {
    protected val viewModel by ViewModelLazy<VM>(vmClasz,
        { viewModelStore },
        { defaultViewModelProviderFactory })
    protected var binding: T? = null

    abstract fun layoutViewId(): Int

    abstract fun variableId(): Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater, layoutViewId(), container, false)
        binding?.setVariable(variableId(), viewModel)
        binding?.lifecycleOwner = this
        initView()
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
    }

    abstract fun initView()

    abstract fun initData();


}