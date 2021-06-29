package com.lsy.lottodata.db.entity

import androidx.room.Embedded
import androidx.room.Relation

/**
 * @author Xuwl
 * @date 2021/6/24
 *
 */
data class TicketAndLottery(
    @Embedded val lottery: LotteryNumber,
    @Relation(
        parentColumn = "nper",
        entityColumn = "nper"
    )
    val ticket: LottoTicket,
)