package com.lsy.lottodata.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lsy.lottodata.db.entity.LotteryNumber

/**
 * @author Xuwl
 * @date 2021/6/23
 *
 */
@Dao
interface LotteryNumberDao {
    @Query("SELECT * FROM Lottery ORDER BY nper")
    suspend fun getAll(): Array<LotteryNumber>

    @Query("SELECT * FROM Lottery ORDER BY nper DESC LIMIT 1")
    suspend fun getFirst(): LotteryNumber

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLotterysTable(vararg lottery: LotteryNumber)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLotterysTable(lotter: List<LotteryNumber>)
}