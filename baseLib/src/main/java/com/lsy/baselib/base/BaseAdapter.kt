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
 * @date 2021/8/23
 *
 */
abstract class BaseAdapter<M>(val mContext: Context?, list: List<M>? = null) :
    RecyclerView.Adapter<BaseAdapter.BaseViewHolder>() {
    protected val mList: MutableList<M> = ArrayList()

    init {
        list?.let {
            setList(list)
        }
    }

    fun setList(list: List<M>?) {
        mList.clear()
        if (!list.isNullOrEmpty()) {
            mList.addAll(list)
        }
        notifyDataSetChanged()
    }

    fun addItem(item: M) {
        mList.add(item)
    }

    abstract fun getItemLayoutId(viewType: Int): Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val binding: ViewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(this.mContext),
            getItemLayoutId(viewType), parent, false)
        return BaseViewHolder(binding.root)
    }

    override fun getItemCount(): Int = mList.size


    class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}
}