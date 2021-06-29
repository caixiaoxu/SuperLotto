package com.lsy.lottodata.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author Xuwl
 * @date 2021/6/21
 *
 */
@Entity(tableName = "Lottery")
data class LotteryNumber(
    @PrimaryKey val nper: String,
    @ColumnInfo val num1: String,
    @ColumnInfo val num2: String,
    @ColumnInfo val num3: String,
    @ColumnInfo val num4: String,
    @ColumnInfo val num5: String,
    @ColumnInfo val num6: String,
    @ColumnInfo val num7: String,
) {
}