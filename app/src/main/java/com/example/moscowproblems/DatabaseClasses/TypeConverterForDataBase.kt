package com.example.moscowproblems.DatabaseClasses

import androidx.room.TypeConverter
import java.util.*

class TypeConverterForDataBase {

    @TypeConverter
    fun fromDateToDB(date: Date): Long{
        return date.time
    }

    @TypeConverter
    fun fromDBToDate(timeInMilliseconds: Long): Date{
        return Date(timeInMilliseconds)
    }


    @TypeConverter
    fun fromUUIDToDB(uuid: UUID): String{
        return uuid.toString()
    }

    @TypeConverter
    fun fromDBToUUID(string: String): UUID{
        return UUID.fromString(string)
    }

}