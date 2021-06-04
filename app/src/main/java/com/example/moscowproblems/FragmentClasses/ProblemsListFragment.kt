package com.example.moscowproblems.FragmentClasses

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moscowproblems.RecyclerViewProblemsListFragment.ProblemsListAdapter
import com.example.moscowproblems.R
import com.example.moscowproblems.ViewModels.ProblemsListViewModel

class ProblemsListFragment : Fragment(R.layout.fragment_list_problems){

    private lateinit var recyclerViewWithProblemsList: RecyclerView
    private lateinit var adapterForProblemsListRecyclerView: ProblemsListAdapter

    private val viewModelForProblemsList by lazy{
        ViewModelProvider(this).get(ProblemsListViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        createAndSetManagerForRecycler(view)
        createAndSetAdapter()
    }

    fun createAndSetManagerForRecycler(view: View){
        recyclerViewWithProblemsList = view.findViewById(R.id.id_problems_list_recyclerview)
        recyclerViewWithProblemsList.layoutManager = LinearLayoutManager(context)
    }

    fun createAndSetAdapter(){
        val problemsList = viewModelForProblemsList.problemsList
        adapterForProblemsListRecyclerView = ProblemsListAdapter(problemsList)
        recyclerViewWithProblemsList.adapter = adapterForProblemsListRecyclerView
    }

}