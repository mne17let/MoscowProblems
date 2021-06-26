package com.example.moscowproblems.DatabaseClasses

import android.app.Application

class MoscowProblemsApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        ProblemsRepository.createObject(this)
    }
}