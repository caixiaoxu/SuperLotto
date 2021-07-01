package com.lsy.lottodata.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lsy.lottodata.db.entity.LottoTicket

/**
 * @author Xuwl
 * @date 2021/6/24
 *
 */
@Dao
interface LottoTicketDao {
    @Query("SELECT nper FROM LottoTicket ORDER BY nper DESC LIMIT 1")
    suspend fun getLatestNper(): String?

    @Query("SELECT * FROM LottoTicket WHERE nper = :nper")
    suspend fun getLatestList(nper: String): List<LottoTicket>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLottoTable(tickets: LottoTicket)
}