package com.lsy.lottodata.db.entity

import androidx.room.AutoMigration
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author Xuwl
 * @date 2021/6/24
 *
 */
@Entity(tableName = "SelfLotto")
data class SelfLottoNumber(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo var no: String,
    @ColumnInfo var nper: String,
    @ColumnInfo var front: String,
    @ColumnInfo var back: String,
) {}