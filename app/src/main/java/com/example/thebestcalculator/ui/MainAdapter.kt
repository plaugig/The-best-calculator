package com.example.thebestcalculator.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.thebestcalculator.R
import com.example.thebestcalculator.ui.item.ItemData
import com.example.thebestcalculator.ui.item.MainViewHolder

class MainAdapter(
    private val listener: MainTaskListener,
    private val items: List<ItemData>
) : RecyclerView.Adapter<MainViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.botton,
            parent,
            false
        )

        return MainViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: MainViewHolder,
        position: Int
    ) {
       holder.bind(items[position], listener)
    }

    override fun getItemCount(): Int = items.size
}