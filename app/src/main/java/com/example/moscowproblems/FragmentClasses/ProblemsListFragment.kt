package com.example.moscowproblems.FragmentClasses

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moscowproblems.CallBacks.CallbackForActivity
import com.example.moscowproblems.Models.ProblemModel
import com.example.moscowproblems.RecyclerViewProblemsListFragment.ProblemsListAdapter
import com.example.moscowproblems.R
import com.example.moscowproblems.ViewModels.ProblemsListViewModel

class ProblemsListFragment : Fragment(R.layout.fragment_list_problems){

    private lateinit var recyclerViewWithProblemsList: RecyclerView
    private lateinit var adapterForProblemsListRecyclerView: ProblemsListAdapter

    private var myCallbackForActivityForCLickOnProblemInList: CallbackForActivity? = null

    private val viewModelForProblemsList by lazy{
        ViewModelProvider(this).get(ProblemsListViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        createAndSetManagerForRecycler(view)
        createAndSetAdapter()
        setObserverForProblemList()
    }

    fun createAndSetManagerForRecycler(view: View){
        recyclerViewWithProblemsList = view.findViewById(R.id.id_problems_list_recyclerview)
        recyclerViewWithProblemsList.layoutManager = LinearLayoutManager(context)
    }

    fun createAndSetAdapter(){
        //val fullListForRecycler = viewModelForProblemsList.fullListForRecycler

        adapterForProblemsListRecyclerView = ProblemsListAdapter(MyInterfaceForListAdapter())
        adapterForProblemsListRecyclerView.myCallBackForHolder = myCallbackForActivityForCLickOnProblemInList
        recyclerViewWithProblemsList.adapter = adapterForProblemsListRecyclerView
    }

    fun createAndSetEmptyAdapter(){
        //val emptyAdapter = ProblemsListAdapter(MyInterfaceForListAdapter())
        //recyclerViewWithProblemsList.adapter = emptyAdapter
    }

    fun setObserverForProblemList(){
        val viewLifecycleOwnerVariable = viewLifecycleOwner
        val observerObject = MyObserver()
        viewModelForProblemsList.fullListForRecyclerLiveData.observe(viewLifecycleOwnerVariable, observerObject)
    }

    inner class MyObserver: Observer<List<ProblemModel>>{
        override fun onChanged(updatedListWithProblems: List<ProblemModel>) {
            adapterForProblemsListRecyclerView.submitList(updatedListWithProblems)
            //createAndSetAdapter(getListWithProblems)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        myCallbackForActivityForCLickOnProblemInList = activity as CallbackForActivity
    }

    override fun onDetach() {
        super.onDetach()
        myCallbackForActivityForCLickOnProblemInList = null
    }

    class MyInterfaceForListAdapter: DiffUtil.ItemCallback<ProblemModel>(){
        override fun areItemsTheSame(oldItem: ProblemModel, newItem: ProblemModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ProblemModel, newItem: ProblemModel): Boolean {
            return oldItem.equals(newItem)
        }

    }
}