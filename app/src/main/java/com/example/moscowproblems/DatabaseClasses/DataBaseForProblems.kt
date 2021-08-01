package com.example.moscowproblems.DatabaseClasses

import androidx.room.*
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.moscowproblems.Models.ProblemModel

@Database (entities = [ProblemModel::class], version = 2)
@TypeConverters (TypeConverterForDataBase::class)
abstract class DataBaseForProblems: RoomDatabase() {

    abstract fun getDao(): ProblemDAO
}