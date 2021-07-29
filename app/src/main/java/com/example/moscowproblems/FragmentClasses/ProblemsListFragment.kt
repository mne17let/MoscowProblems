package com.example.moscowproblems.FragmentClasses

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
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
    private lateinit var textViewWhenListWithProblemsIsEmpty: TextView
    private lateinit var buttonWhenListWithProblemsIsEmpty: Button


    private lateinit var adapterForProblemsListRecyclerView: ProblemsListAdapter

    private var myCallbackForActivityForCLickOnProblemInList: CallbackForActivity? = null

    private val viewModelForProblemsList by lazy{
        ViewModelProvider(this).get(ProblemsListViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_problem_fragment, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.id_menu_problem_fragment_new_problem){
            addNewProblem()
            return true
        } else {
            return super.onOptionsItemSelected(item)
        }
    }

    private fun addNewProblem(){
        val newEmptyProblem = ProblemModel()
        viewModelForProblemsList.addNewProblem(newEmptyProblem)
        myCallbackForActivityForCLickOnProblemInList?.onProblemInListClick(newEmptyProblem.id)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        createAndSetManagerForRecycler(view)
        createAndSetAdapter()
        setObserverForProblemList()
        initViewsVar()
    }

    fun initViewsVar(){
        buttonWhenListWithProblemsIsEmpty = requireView().findViewById(R.id.id_button_when_list_with_problems_is_empty)
        textViewWhenListWithProblemsIsEmpty = requireView().findViewById(R.id.id_textview_when_list_with_problems_is_empty)
    }

    fun setNecessaryVisibility(isEmptyList: Boolean){
        if (isEmptyList){

            buttonWhenListWithProblemsIsEmpty.visibility = View.VISIBLE
            textViewWhenListWithProblemsIsEmpty.visibility = View.VISIBLE

            textViewWhenListWithProblemsIsEmpty.text = isEmptyList.toString()

            buttonWhenListWithProblemsIsEmpty.setOnClickListener{
                addNewProblem()
            }

            recyclerViewWithProblemsList.visibility = View.GONE
        } else {
            buttonWhenListWithProblemsIsEmpty.visibility = View.GONE
            textViewWhenListWithProblemsIsEmpty.visibility = View.GONE

            textViewWhenListWithProblemsIsEmpty.text = isEmptyList.toString()

            recyclerViewWithProblemsList.visibility = View.VISIBLE
        }
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
            setNecessaryVisibility(updatedListWithProblems.isEmpty())
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