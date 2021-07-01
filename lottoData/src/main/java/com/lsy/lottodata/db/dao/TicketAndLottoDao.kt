package com.lsy.lottodata.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.lsy.lottodata.db.entity.TicketAndLotto

/**
 * @author Xuwl
 * @date 2021/7/1
 *
 */
@Dao
interface TicketAndLottoDao {
    @Transaction
    @Query("SELECT * FROM LottoTicket WHERE nper = :nper")
    suspend fun getTicketWithLotto(nper: String): List<TicketAndLotto>
}