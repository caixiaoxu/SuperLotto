package com.lsy.lottodata.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lsy.lottodata.db.entity.enums.EnumLottoType

/**
 * @author Xuwl
 * @date 2021/6/24
 *
 */
@Entity(tableName = "LottoTicket")
data class LottoTicket(
    @PrimaryKey val no: String,
    @ColumnInfo val type: EnumLottoType,//0单式票，1复式票
    @ColumnInfo val nper: String,
    @ColumnInfo val date: java.util.Date,
) {
    override fun hashCode(): Int {
        return super.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false
        other as LottoTicket
        if (other.no != no || other.type != type || other.nper != nper || other.date != date) return false
        return true
    }
}
