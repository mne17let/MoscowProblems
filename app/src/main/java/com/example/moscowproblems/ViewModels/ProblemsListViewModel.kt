package com.example.moscowproblems.ViewModels

import androidx.lifecycle.ViewModel
import com.example.moscowproblems.DatabaseClasses.ProblemsRepository
import com.example.moscowproblems.Models.AdvertisementModel
import com.example.moscowproblems.Models.ProblemModel
import com.example.moscowproblems.R

class ProblemsListViewModel : ViewModel(){

    val problemsList = mutableListOf<ProblemModel>()
    val advertismentList = mutableListOf<AdvertisementModel>()

    val problemsRepository : ProblemsRepository = ProblemsRepository.getInstance()
    val fullListForRecyclerLiveData = problemsRepository.getAllProblems()

    //val fullListForRecycler = mutableListOf<Any>()


    init{
        for (i in 1..100){
            val newProblem = ProblemModel()

            newProblem.title = "Problem #$i"

            newProblem.isSolved = if (i % 2 == 0){
                true
            } else {
                false
            }

            problemsRepository.addNewProblem(newProblem)
        }
    }



/*    init {
        for (i in 1..100){
            val newProblem = ProblemModel()

            newProblem.title = "Problem #$i"

            newProblem.isSolved = if (i % 2 == 0){
                true
            } else {
                false
            }

            problemsList.add(newProblem)
        }

        for (i in 1..25){
            val newAd = AdvertisementModel()

            newAd.title = "Реклама № $i"

            newAd.idAdImage = when ((1..3).random()){
                1 -> R.drawable.ad_image_1
                2 -> R.drawable.ad_image_2
                3 -> R.drawable.ad_image_3
                else -> R.drawable.ad_image_1
            }

            advertismentList.add(newAd)
        }

        var problemNumber = 0
        var adNumber = 0
        for (i in 1..problemsList.size + advertismentList.size){
            if (i % 5 == 0){
                fullListForRecycler.add(advertismentList[adNumber])
                adNumber = adNumber + 1
            } else {
                fullListForRecycler.add(problemsList[problemNumber])
                problemNumber += 1
            }
        }
    }*/

}