package com.example.moscowproblems.RecyclerViewProblemsListFragment

import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.moscowproblems.Models.ProblemModel
import com.example.moscowproblems.R

class ProblemHolderWithButton(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {

    private var titleTextView: TextView = view.findViewById(R.id.id_item_title_problems_list)
    private var dateTextView: TextView = view.findViewById(R.id.id_item_date_problems_list)
    private var button = view.findViewById<Button>(R.id.id_button_on_the_another_item)

    init {
        view.setOnClickListener(this)
    }


    fun bind(problemData: ProblemModel){
        titleTextView.text = problemData.title
        dateTextView.text = problemData.date.toString()
        button.setOnClickListener{
            Toast.makeText(itemView.context, "Нажата кнопка подробнее у элемента ${problemData.title}", Toast.LENGTH_SHORT)
                    .show()
        }
    }

    override fun onClick(v: View?) {
        if (v != null) {
            Toast.makeText(v.context, "Пррблема ${titleTextView.text} нажата", Toast.LENGTH_SHORT).show()
        }
    }
}