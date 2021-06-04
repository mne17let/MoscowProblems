package com.example.moscowproblems

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moscowproblems.Models.ProblemModel
import com.example.moscowproblems.RecyclerViewProblemsListFragment.ProblemHolder

class ProblemsListAdapter(var problemsList: List<ProblemModel>) : RecyclerView.Adapter<ProblemHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProblemHolder {
        val viewForHolder = LayoutInflater.from(parent.context).inflate(R.layout.item_problems_list, parent, false)
        val problemHolderInstance = ProblemHolder(viewForHolder)
        return problemHolderInstance
    }

    override fun onBindViewHolder(holder: ProblemHolder, position: Int) {
        val currentProblemInList = problemsList[position]

        holder.bind(currentProblemInList)
    }

    override fun getItemCount(): Int {
        return problemsList.size
    }
}