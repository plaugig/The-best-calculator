package com.example.thebestcalculator.ui.item

import android.content.res.ColorStateList
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.thebestcalculator.databinding.BottonBinding
import com.example.thebestcalculator.ui.MainTaskListener

class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var binding = BottonBinding.bind(itemView)

    fun bind(item: ItemData, listener: MainTaskListener) {
        binding.button.text = item.text

        val context = binding.root.context

        binding.button.setTextColor(ContextCompat.getColor(context, item.textColor))

        binding.button.backgroundTintList = ColorStateList.valueOf(
            ContextCompat.getColor(context, item.bgColor)
        )

        binding.button.setOnClickListener {
            listener.onBottonClick(item)
        }

    }

}