package com.example.moscowproblems

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.moscowproblems.CallBacks.CallbackForActivity
import com.example.moscowproblems.FragmentClasses.ProblemFragment
import com.example.moscowproblems.FragmentClasses.ProblemsListFragment
import com.example.moscowproblems.Models.ProblemModel
import java.util.*

class MainActivity : AppCompatActivity(), CallbackForActivity {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setStartFragment()
    }

    fun setStartFragment(){
        val currentFragment = supportFragmentManager.findFragmentById(R.id.id_frame_container)

        if (currentFragment == null){
            val fragmentProblemList = ProblemsListFragment()
            supportFragmentManager.beginTransaction()
                    .add(R.id.id_frame_container, fragmentProblemList)
                    .commit()
        }
    }

    override fun onProblemInListClick(id: UUID) {
        Toast.makeText(this, "Нажат элемент с id $id", Toast.LENGTH_SHORT).show()
    }
}