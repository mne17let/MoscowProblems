package com.example.moscowproblems.Models

import java.util.*

data class ProblemModel(val id: UUID = UUID.randomUUID(),
                        var title: String = "",
                        var date: Date = Date(),
                        var isSolved: Boolean = false,
                        var haveButton : String = "Yes")