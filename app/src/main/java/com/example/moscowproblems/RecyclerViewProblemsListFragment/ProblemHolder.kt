package com.example.moscowproblems.RecyclerViewProblemsListFragment

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.moscowproblems.Models.ProblemModel
import com.example.moscowproblems.R

class ProblemHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {

    private var titleTextView: TextView = view.findViewById(R.id.id_item_title_problems_list)
    private var dateTextView: TextView = view.findViewById(R.id.id_item_date_problems_list)
    private var problemSolvedImage = view.findViewById<ImageView>(R.id.id_item_image_solved_problems_list)

    init {
        view.setOnClickListener(this)
    }

    fun bind(problemData: ProblemModel){
        titleTextView.text = problemData.title

        val dateFormat = android.text.format.DateFormat.format("HH:mm:ss EEEE, d MMMM, yyy", problemData.date)

        dateTextView.text = dateFormat.toString()
        if (!problemData.isSolved){
            problemSolvedImage.visibility = View.INVISIBLE
        } else {
            problemSolvedImage.visibility = View.VISIBLE
        }
    }

    override fun onClick(v: View?) {
        if (v != null) {
            Toast.makeText(v.context, "Пррблема ${titleTextView.text} нажата", Toast.LENGTH_SHORT).show()
        }
    }

}