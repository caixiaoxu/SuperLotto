package com.lsy.lottodata.db

import android.content.Context
import androidx.room.Room
import androidx.room.withTransaction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

/**
 * @author Xuwl
 * @date 2021/6/23
 *
 */
object DBManager {
    lateinit var dbPath: String
    lateinit var db: AppDatabase
    const val TABLE_LOTTERY_NAME = "Lottery"

    fun initDB(context: Context) {
        dbPath = File(context.getExternalFilesDir("db"), "SuperLotto.db").absolutePath
        db = Room.databaseBuilder(context, AppDatabase::class.java, dbPath)
            .allowMainThreadQueries() //允许在主线程中查询
            .build()
    }
}