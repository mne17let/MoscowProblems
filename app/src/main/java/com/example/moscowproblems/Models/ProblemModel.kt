package com.example.moscowproblems.Models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class ProblemModel(@PrimaryKey val id: UUID = UUID.randomUUID(),
                        var title: String = "",
                        var date: Date = Date(),
                        var isSolved: Boolean = false
)

