package com.example.moscowproblems.DatabaseClasses

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.moscowproblems.Models.ProblemModel
import java.lang.IllegalStateException
import java.util.*

class ProblemsRepository private constructor(context: Context) {

    private var dataBaseWithProblems: DataBaseForProblems = Room.databaseBuilder(
            context.applicationContext,
            DataBaseForProblems::class.java,
            "mydatabasewithproblems")
            .build()

    private var problemDao = dataBaseWithProblems.getDao()

    companion object{
        private var INSTANCE: ProblemsRepository? = null

        fun createObject(context: Context){
            if (INSTANCE == null){
                INSTANCE = ProblemsRepository(context)
            }
        }

        fun getInstance(): ProblemsRepository{
            return INSTANCE ?: throw IllegalStateException("Мужик, проинициализируй переменную объекта")
        }
    }

    fun getAllProblems(): LiveData<List<ProblemModel>> = problemDao.getAllProblems()

    fun getProblemById(id: UUID): LiveData<ProblemModel> = problemDao.getProblem(id)
}