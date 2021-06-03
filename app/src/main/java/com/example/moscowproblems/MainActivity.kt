package com.example.moscowproblems

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moscowproblems.FragmentClasses.ProblemFragment
import com.example.moscowproblems.FragmentClasses.ProblemsListFragment
import com.example.moscowproblems.Models.ProblemModel

class MainActivity : AppCompatActivity() {
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
}