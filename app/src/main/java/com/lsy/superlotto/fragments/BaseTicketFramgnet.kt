package com.lsy.superlotto.fragments

import androidx.databinding.ViewDataBinding
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import com.lsy.superlotto.MainViewModel
import com.lsy.superlotto.base.BaseFragment
import kotlin.reflect.KClass

/**
 * @author Xuwl
 * @date 2021/6/30
 *
 */
abstract class BaseTicketFramgnet<T : ViewDataBinding, VM : ViewModel>(vmClasz: KClass<VM>) :
    BaseFragment<T, VM>(vmClasz) {
    val mMainViewModel: MainViewModel by activityViewModels()
}