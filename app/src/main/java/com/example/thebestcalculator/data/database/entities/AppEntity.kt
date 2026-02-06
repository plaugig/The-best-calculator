package com.example.thebestcalculator.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity("calculator")
data class AppEntity(
    @PrimaryKey
    val id: Int = 1,
    @ColumnInfo("expression")
    val expression: String,
    @ColumnInfo("expression")
    val result: String
)