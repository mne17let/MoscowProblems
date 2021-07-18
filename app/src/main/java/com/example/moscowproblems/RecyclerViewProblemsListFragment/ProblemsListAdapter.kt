package com.example.moscowproblems.RecyclerViewProblemsListFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moscowproblems.CallBacks.CallbackForActivity
import com.example.moscowproblems.FragmentClasses.ProblemsListFragment
import com.example.moscowproblems.Models.AdvertisementModel
import com.example.moscowproblems.Models.ProblemModel
import com.example.moscowproblems.R

class ProblemsListAdapter(var fullModelList: List<Any>,
                          var myInterfaceForListAdapter: ProblemsListFragment.MyInterfaceForListAdapter)
    : ListAdapter<ProblemModel, ProblemHolder>(myInterfaceForListAdapter) {

    var myCallBackForHolder: CallbackForActivity? = null

    override fun getItemViewType(position: Int): Int {
        return when (fullModelList[position]){
            is ProblemModel -> R.layout.item_problems_list
            is AdvertisementModel -> R.layout.item_advertisement_list
            else -> R.layout.item_problems_list
        }
    }



    /*override fun onCreateViewHolder(
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
    }*/

    /*override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentModelInList = fullModelList[position]
        if (holder is ProblemHolder){
            holder.bind(currentModelInList as ProblemModel)
            holder.callbackForViewHolder = myCallBackForHolder
        } else if (holder is AdvertisementHolder){
            holder.bind(currentModelInList as AdvertisementModel)
        }
    }*/

    override fun getItemCount(): Int {
        return fullModelList.size
    }

    override fun onBindViewHolder(holder: ProblemHolder, position: Int) {
        val currentModelInList = fullModelList[position]
        holder.bind(currentModelInList as ProblemModel)
        holder.callbackForViewHolder = myCallBackForHolder
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProblemHolder {
        val viewForHolder = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        val problemHolderInstance = ProblemHolder(viewForHolder)
        return problemHolderInstance
    }
}