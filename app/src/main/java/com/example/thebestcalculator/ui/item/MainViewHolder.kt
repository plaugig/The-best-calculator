package com.example.thebestcalculator.ui.item

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.thebestcalculator.databinding.BottonBinding

class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var binding = BottonBinding.bind(itemView)

    fun bind(item: ItemData) {
        binding.button.text = item.text


    }

}