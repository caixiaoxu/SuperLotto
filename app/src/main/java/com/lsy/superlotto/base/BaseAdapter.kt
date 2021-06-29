package com.lsy.superlotto.base

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
abstract class BaseAdapter<T : ViewDataBinding, M>(
    val mContext: Context?,
    val mList: MutableList<M>,
) :
    RecyclerView.Adapter<BaseAdapter.BaseBindingViewHolder>() {

    abstract fun getItemLayoutId(viewType: Int): Int

    protected abstract fun onBindItem(binding: T?, item: M)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseBindingViewHolder {
        val binding: T = DataBindingUtil.inflate(LayoutInflater.from(this.mContext),
            getItemLayoutId(viewType), parent, false)
        return BaseBindingViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: BaseBindingViewHolder, position: Int) {
        val binding: T? = DataBindingUtil.getBinding(holder.itemView)
    }

    override fun getItemCount(): Int = mList.size

    fun setList(list: MutableList<M>) {
        mList.clear()
        mList.addAll(list)
        notifyDataSetChanged()
    }

    fun addItem(item: M) {
        mList.add(item)
    }

    class BaseBindingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}
}