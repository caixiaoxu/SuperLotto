package com.lsy.superlotto.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.lsy.lottodata.db.entity.LottoTicket
import com.lsy.superlotto.fragments.DoubleTicketFragment
import com.lsy.superlotto.fragments.SingleTicketFragment

/**
 * @author Xuwl
 * @date 2021/6/24
 *
 */
class LottoAdapter(fragmentActivity: FragmentActivity, tickets: List<LottoTicket>? = null) :
    FragmentStateAdapter(fragmentActivity) {
    private val fragments = ArrayList<Fragment>(10)

    init {
        setList(tickets)
    }

    fun setList(tickets: List<LottoTicket>?) {
        if (null == tickets) {
            fragments.clear()
            return
        }

        if (fragments.size > tickets.size) {
            fragments.subList(0, tickets.size)
        }
        tickets.forEachIndexed { index, ticket ->
            if (fragments.size > index) {
                if (0 == ticket.type && fragments[index] !is SingleTicketFragment) {
                    fragments[index] = SingleTicketFragment()
                } else if (1 == ticket.type && fragments[index] !is DoubleTicketFragment) {
                    fragments[index] = DoubleTicketFragment()
                }
            } else {
                fragments.add(if (1 == ticket.type) DoubleTicketFragment() else SingleTicketFragment())
            }
        }
    }

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]
}