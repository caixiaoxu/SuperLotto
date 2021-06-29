package com.lsy.superlotto.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.lsy.superlotto.fragments.DoubleTicketFragment
import com.lsy.superlotto.fragments.SingleTicketFragment

/**
 * @author Xuwl
 * @date 2021/6/24
 *
 */
class LottoAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    private val fragments = ArrayList<Fragment>(2)

    init {
        fragments.add(SingleTicketFragment())
        fragments.add(DoubleTicketFragment())
    }

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]
}