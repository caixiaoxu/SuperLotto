package com.lsy.lottodata.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

/**
 * @author Xuwl
 * @date 2021/6/24
 *
 */
@Entity(tableName = "SelfLotto")
data class SelfLottoNumber(
    @ColumnInfo val no: String,
    @ColumnInfo val nper: String,
    @ColumnInfo val front: String,
    @ColumnInfo val back: String,
) {}