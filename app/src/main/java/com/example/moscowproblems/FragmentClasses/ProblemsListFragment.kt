package com.example.moscowproblems.FragmentClasses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moscowproblems.R
import com.example.moscowproblems.ViewModels.ProblemsListViewModel

class ProblemsListFragment : Fragment(R.layout.fragment_list_problems){

    private lateinit var recyclerViewWithProblemsList: RecyclerView

    private val viewModelForProblemsList by lazy{
        ViewModelProvider(this).get(ProblemsListViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerViewWithProblemsList = view.findViewById(R.id.id_problems_list_recyclerview)
        recyclerViewWithProblemsList.layoutManager = LinearLayoutManager(context)

    }

}