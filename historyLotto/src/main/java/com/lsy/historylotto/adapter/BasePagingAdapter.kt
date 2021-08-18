package com.lsy.historylotto.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.lsy.lottodata.db.entity.LotteryNumber

/**
 * @author Xuwl
 * @date 2021/6/25
 *
 */
abstract class BasePagingAdapter<T : ViewDataBinding, M : Any>(
    val mContext: Context?,
    diffCallback: DiffUtil.ItemCallback<M>,
) :
    PagingDataAdapter<M, BasePagingAdapter.BasePagingViewHolder>(diffCallback) {

    abstract fun getItemLayoutId(viewType: Int): Int

    protected abstract fun onBindItem(binding: T?, item: M?)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasePagingViewHolder {
        val binding: T = DataBindingUtil.inflate(LayoutInflater.from(this.mContext),
            getItemLayoutId(viewType), parent, false)
        return BasePagingViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: BasePagingViewHolder, position: Int) {
        val binding: T? = DataBindingUtil.getBinding(holder.itemView)
        onBindItem(binding, getItem(position))
    }

    class BasePagingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}
}