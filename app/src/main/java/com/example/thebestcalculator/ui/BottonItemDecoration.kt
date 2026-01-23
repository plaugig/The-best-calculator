package com.example.thebestcalculator.ui

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.thebestcalculator.R

class BottonItemDecoration(context: Context) : RecyclerView.ItemDecoration() {

    private val offsetM = context.resources.getDimension(R.dimen.offset_m).toInt()

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)

        val bottom = if (position < 16) offsetM else 0

        val right = when (position) {
            3,7,11,15,18 -> 0
            else -> offsetM

        }
        outRect.set(0,0,right,bottom)
    }

}