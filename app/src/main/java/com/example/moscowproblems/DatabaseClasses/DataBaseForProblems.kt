package com.example.moscowproblems.DatabaseClasses

import androidx.room.*
import com.example.moscowproblems.Models.ProblemModel

@Database (entities = [ProblemModel::class], version = 1)
@TypeConverters (TypeConverterForDataBase::class)
abstract class DataBaseForProblems: RoomDatabase() {

    abstract fun getDao(): ProblemDAO
}