package com.lsy.lottodata.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.lsy.lottodata.db.dao.LotteryNumberDao
import com.lsy.lottodata.db.dao.LottoTicketDao
import com.lsy.lottodata.db.dao.SelfLottoNumberDao
import com.lsy.lottodata.db.entity.LotteryNumber
import com.lsy.lottodata.db.entity.LottoTicket
import com.lsy.lottodata.db.entity.SelfLottoNumber

/**
 * @author Xuwl
 * @date 2021/6/23
 *
 */
@Database(entities = [LotteryNumber::class, LottoTicket::class, SelfLottoNumber::class],
    version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun lotteryNumberDao(): LotteryNumberDao
    abstract fun lottoTicketDao(): LottoTicketDao
    abstract fun selfLottoNumberDao(): SelfLottoNumberDao
}