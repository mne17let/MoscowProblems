package com.example.moscowproblems.DatabaseClasses

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.moscowproblems.Models.ProblemModel
import java.util.*

@Dao
interface ProblemDAO {

    @Query(value = "SELECT * FROM ProblemModel")
    fun getAllProblems(): LiveData<List<ProblemModel>>

    @Query(value = "SELECT * FROM ProblemModel WHERE id = (:idForFun)")
    fun getProblem(idForFun: UUID): LiveData<ProblemModel>
}