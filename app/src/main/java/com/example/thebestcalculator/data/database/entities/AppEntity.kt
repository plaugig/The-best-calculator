package com.example.thebestcalculator.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity("calculator")
data class AppEntity(
    @PrimaryKey
    val id: Int = 1,
    val expression: String,
    val result: String
)