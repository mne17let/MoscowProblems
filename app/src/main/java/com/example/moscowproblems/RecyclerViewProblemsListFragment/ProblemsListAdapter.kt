package com.example.moscowproblems.RecyclerViewProblemsListFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moscowproblems.Models.ProblemModel
import com.example.moscowproblems.R

class ProblemsListAdapter(var problemsList: List<ProblemModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return when (problemsList[position].haveButton){
            "No" -> R.layout.item_problems_list
            "Yes" -> R.layout.item_problems_list_with_button
            else -> R.layout.item_problems_list
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val viewForHolder = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        val problemHolderInstance = when (viewType){
            R.layout.item_problems_list -> ProblemHolder(viewForHolder)
            R.layout.item_problems_list_with_button -> ProblemHolderWithButton(viewForHolder)
            else -> ProblemHolder(viewForHolder)
        }
        return problemHolderInstance
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentProblemInList = problemsList[position]
        if (holder is ProblemHolder){
            holder.bind(currentProblemInList)
        } else if (holder is ProblemHolderWithButton){
            holder.bind(currentProblemInList)
        }
    }

    override fun getItemCount(): Int {
        return problemsList.size
    }
}