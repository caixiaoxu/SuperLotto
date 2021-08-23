package com.lsy.lottodata.db.dao

import androidx.paging.PagingSource
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

    @Query("SELECT * FROM Lottery ORDER BY nper DESC")
    fun getAllForDataSource(): PagingSource<Int, LotteryNumber>

    @Query("SELECT * FROM Lottery ORDER BY nper DESC LIMIT 1")
    suspend fun getFirst(): LotteryNumber

    @Query("SELECT COUNT(*) FROM Lottery")
    suspend fun queryTotalCountSync(): Int

    @Query("SELECT COUNT(*) FROM Lottery")
    fun queryTotalCount(): Int

    @Query("SELECT COUNT(*) FROM Lottery WHERE num1=:num OR num2=:num OR num3=:num OR num4=:num OR num5=:num")
    suspend fun queryBeforeCountOfNumSync(num: String): Int

    @Query("SELECT COUNT(*) FROM Lottery WHERE num1=:num OR num2=:num OR num3=:num OR num4=:num OR num5=:num")
    fun queryBeforeCountOfNum(num: String): Int

    @Query("SELECT COUNT(*) FROM Lottery WHERE num6=:num OR num7=:num")
    suspend fun queryAfterCountOfNumSync(num: String): Int

    @Query("SELECT COUNT(*) FROM Lottery WHERE num6=:num OR num7=:num")
    fun queryAfterCountOfNum(num: String): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLotterysTable(vararg lottery: LotteryNumber)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLotterysTable(lotter: List<LotteryNumber>)


}