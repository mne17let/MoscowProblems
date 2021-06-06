package com.example.moscowproblems.RecyclerViewProblemsListFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moscowproblems.Models.AdvertisementModel
import com.example.moscowproblems.Models.ProblemModel
import com.example.moscowproblems.R

class ProblemsListAdapter(var fullModelList: List<Any>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return when (fullModelList[position]){
            is ProblemModel -> R.layout.item_problems_list
            is AdvertisementModel -> R.layout.item_advertisement_list
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
            R.layout.item_advertisement_list -> AdvertisementHolder(viewForHolder)
            else -> ProblemHolder(viewForHolder)
        }
        return problemHolderInstance
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentModelInList = fullModelList[position]
        if (holder is ProblemHolder){
            holder.bind(currentModelInList as ProblemModel)
        } else if (holder is AdvertisementHolder){
            holder.bind(currentModelInList as AdvertisementModel)
        }
    }

    override fun getItemCount(): Int {
        return fullModelList.size
    }
}