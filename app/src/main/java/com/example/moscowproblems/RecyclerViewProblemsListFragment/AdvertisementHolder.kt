package com.example.moscowproblems.RecyclerViewProblemsListFragment

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.moscowproblems.Models.AdvertisementModel
import com.example.moscowproblems.Models.ProblemModel
import com.example.moscowproblems.R

class AdvertisementHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {

    private var adTitleTextView: TextView = view.findViewById(R.id.id_item_title_ad_list)
    private var adImageViewVar = view.findViewById<ImageView>(R.id.id_item_image_ad_list)
    private var button = view.findViewById<Button>(R.id.id_button_on_the_ad_item)

    init {
        view.setOnClickListener(this)
    }


    fun bind(adData: AdvertisementModel){
        adTitleTextView.text = adData.title
        adImageViewVar.setImageResource(adData.idAdImage)

        button.setOnClickListener{
            Toast.makeText(itemView.context, "Нажата кнопка подробнее у элемента ${adData.title}", Toast.LENGTH_SHORT)
                    .show()
        }
    }

    override fun onClick(v: View?) {
        if (v != null) {
            Toast.makeText(v.context, "Реклама ${adTitleTextView.text} нажата", Toast.LENGTH_SHORT).show()
        }
    }
}