package com.example.moscowproblems.FragmentClasses

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moscowproblems.Models.ProblemModel
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
        createAndSetEmptyAdapter()
        setObserverForProblemList()
    }

    fun createAndSetManagerForRecycler(view: View){
        recyclerViewWithProblemsList = view.findViewById(R.id.id_problems_list_recyclerview)
        recyclerViewWithProblemsList.layoutManager = LinearLayoutManager(context)
    }

    fun createAndSetAdapter(allProblems: List<ProblemModel>){
        //val fullListForRecycler = viewModelForProblemsList.fullListForRecycler
        adapterForProblemsListRecyclerView = ProblemsListAdapter(allProblems)
        recyclerViewWithProblemsList.adapter = adapterForProblemsListRecyclerView
    }

    fun createAndSetEmptyAdapter(){
        val emptyAdapter = ProblemsListAdapter(emptyList())
        recyclerViewWithProblemsList.adapter = emptyAdapter
    }

    fun setObserverForProblemList(){
        val viewLifecycleOwnerVariable = viewLifecycleOwner
        val observerObject = MyObserver()
        viewModelForProblemsList.fullListForRecyclerLiveData.observe(viewLifecycleOwnerVariable,observerObject)
    }

    inner class MyObserver: Observer<List<ProblemModel>>{
        override fun onChanged(getListWithProblems: List<ProblemModel>) {
            createAndSetAdapter(getListWithProblems)
        }

    }
}