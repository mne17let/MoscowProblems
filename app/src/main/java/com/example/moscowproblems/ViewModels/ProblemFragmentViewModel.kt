package com.example.moscowproblems.ViewModels

import androidx.arch.core.util.Function
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.moscowproblems.DatabaseClasses.ProblemsRepository
import com.example.moscowproblems.Models.ProblemModel
import java.util.*

class ProblemFragmentViewModel: ViewModel() {

    private val problemsRepository: ProblemsRepository = ProblemsRepository.getInstance()
    private val liveDataWithId: MutableLiveData<UUID> = MutableLiveData<UUID>()

    var problemModelLiveData: LiveData<ProblemModel> = Transformations.switchMap(liveDataWithId, MyFunction())

    inner class MyFunction: Function<UUID, LiveData<ProblemModel>>{
        override fun apply(input: UUID): LiveData<ProblemModel> {
            val result = problemsRepository.getProblemById(input)
            return result
        }
    }

    fun loadProblem(id: UUID){
        liveDataWithId.value = id
    }

    fun saveProblem(problemModel: ProblemModel){
        problemsRepository.updateProblemInBase(problemModel)
    }

}