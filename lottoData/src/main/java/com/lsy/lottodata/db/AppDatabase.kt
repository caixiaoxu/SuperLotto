package com.lsy.lottodata.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lsy.lottodata.db.dao.LotteryNumberDao
import com.lsy.lottodata.db.dao.LottoAssociationDao
import com.lsy.lottodata.db.dao.SelfLottoNumberDao
import com.lsy.lottodata.db.entity.LotteryNumber

/**
 * @author Xuwl
 * @date 2021/6/23
 *
 */
@Database(entities = [LotteryNumber::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun lotteryNumberDao(): LotteryNumberDao
    abstract fun lottoAssociationDao(): LottoAssociationDao
    abstract fun selfLottoNumberDao(): SelfLottoNumberDao
}