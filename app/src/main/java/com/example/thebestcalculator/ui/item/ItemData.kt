package com.example.thebestcalculator.ui.item

import androidx.annotation.ColorRes

data class ItemData (
    val text: String,
    @ColorRes val bgColor: Int,
    @ColorRes val textColor: Int,
    val isDouble: Boolean = false
)