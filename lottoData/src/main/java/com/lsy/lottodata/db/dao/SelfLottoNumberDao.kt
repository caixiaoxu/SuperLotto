package com.lsy.lottodata.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lsy.lottodata.db.entity.SelfLottoNumber

/**
 * @author Xuwl
 * @date 2021/6/24
 *
 */
@Dao
interface SelfLottoNumberDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLottoNumber(vararg nums: SelfLottoNumber)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLottoNumber(nums: MutableList<SelfLottoNumber>)

    @Query("SELECT * FROM SelfLotto WHERE `no` = :no And nper = :nper")
    suspend fun getLottoWithNoAndNper(no: String, nper: String): List<SelfLottoNumber>
}