package com.example.moscowproblems.DatabaseClasses

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.moscowproblems.Models.ProblemModel
import java.io.File
import java.lang.IllegalStateException
import java.util.*
import java.util.concurrent.Executors

class ProblemsRepository private constructor(context: Context) {

    private var filesDirectory: File = context.applicationContext.filesDir

    private var dataBaseWithProblems: DataBaseForProblems = Room.databaseBuilder(
            context.applicationContext,
            DataBaseForProblems::class.java,
            "mydatabasewithproblems")
        .addMigrations(MigrationForDataBaseWithExecutor())
        .build()

    private var problemDao = dataBaseWithProblems.getDao()

    private var executorObject = Executors.newSingleThreadExecutor()

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

    fun addNewProblem(newProblemModel: ProblemModel){
        executorObject.execute{
            problemDao.addNewProblem(newProblemModel)
        }
    }

    fun updateProblemInBase(updateProblemModel: ProblemModel){
        executorObject.execute{
            problemDao.updateProblem(updateProblemModel)
        }
    }

    fun getPhotoFile(problemData: ProblemModel): File{
        return File(filesDirectory, problemData.photo_file_name)
    }

}