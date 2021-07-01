package com.lsy.lottodata.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
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
}