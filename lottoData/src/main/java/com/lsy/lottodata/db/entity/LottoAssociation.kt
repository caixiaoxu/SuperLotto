package com.lsy.lottodata.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
 * @author Xuwl
 * @date 2021/6/24
 *
 */
@Entity(tableName = "LottoAssociation")
data class LottoAssociation(
    @PrimaryKey val no: String,
    @ColumnInfo val type: Int,//0单式票，1复式票
    @ColumnInfo val nper: String,
    @ColumnInfo val date: Date,
)
