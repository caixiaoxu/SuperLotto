package com.lsy.lottodata.db.entity

import androidx.room.Embedded
import androidx.room.Relation

/**
 * @author Xuwl
 * @date 2021/6/24
 *
 */
data class TicketAndLotto(
    @Embedded val ticket: LottoTicket,
    @Relation(
        parentColumn = "nper",
        entityColumn = "nper"
    )
    val nums: List<LotteryNumber>,
)