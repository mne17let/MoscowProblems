package com.example.moscowproblems.RecyclerViewProblemsListFragment

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moscowproblems.R

class ProblemHolder(view: View): RecyclerView.ViewHolder(view) {

    var titleTextView: TextView = view.findViewById(R.id.id_item_title_problems_list)
    var dateTextView: TextView = view.findViewById(R.id.id_item_date_problems_list)

}