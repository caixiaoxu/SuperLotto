package com.lsy.lottodata.db

import androidx.room.TypeConverter
import com.lsy.lottodata.db.entity.enums.EnumLottoType
import java.util.*

/**
 * @author Xuwl
 * @date 2021/6/29
 *
 */
class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun fromEnumLottoType(value: String?): EnumLottoType? {
        return value?.let { EnumLottoType.valueOf(it) }
    }

    @TypeConverter
    fun dateToEnumLottoType(date: EnumLottoType?): String? {
        return date?.name
    }
}