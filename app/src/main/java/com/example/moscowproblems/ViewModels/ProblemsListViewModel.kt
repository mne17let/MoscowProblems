package com.example.moscowproblems.ViewModels

import androidx.lifecycle.ViewModel
import com.example.moscowproblems.Models.ProblemModel

class ProblemsListViewModel : ViewModel(){

    val problemsList = mutableListOf<ProblemModel>()


    init {
        for (i in 1..100){
            val newProblem = ProblemModel()

            newProblem.title = "#Problem $i"

            newProblem.isSolved = if (i % 2 == 0){
                true
            } else {
                false
            }

            problemsList.add(newProblem)
        }
    }

}