package com.lsy.baselib.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * @author Xuwl
 * @date 2021/6/25
 *
 */
abstract class BaseSingleAdapter<T : ViewDataBinding, M>(
    context: Context?, list: List<M>? = null,
) : BaseAdapter<M>(context, list) {

    protected abstract fun onBindItem(binding: T?, item: M?)

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val binding: T? = DataBindingUtil.getBinding(holder.itemView)
        onBindItem(binding, mList[position])
    }
}