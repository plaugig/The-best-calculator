package com.example.thebestcalculator.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.GridLayoutManager
import com.example.thebestcalculator.R
import com.example.thebestcalculator.databinding.ActivityMainBinding
import com.example.thebestcalculator.ui.item.ItemData

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    lateinit var adapter: MainAdapter

    fun op(value: String) = ItemData(
        text = value,
        bgColor = R.color.blue,
        textColor = R.color.dark_white
    )

    fun ac(value: String) = ItemData(
        text = value,
        bgColor = R.color.grey,
        textColor = R.color.grey_icon
    )

    fun d(value: String , isDouble: Boolean = false) = ItemData(
        text = value,
        bgColor = R.color.dark_grey,
        textColor = R.color.blue_icon,
        isDouble = isDouble
    )

    val bottons = listOf(
        // Ряд 1
        ac("Ac"), ac("⌫"), op("%"), op("卍"),
        // Ряд 2
        d("7"), d("8"), d("9"), op("÷"),
        // Ряд 3
        d("4"), d("5"), d("6"), op("—"),
        // Ряд 4
        d("1"), d("2"), d("3"), op("+"),
        // Ряд 5
        d("0", isDouble = true), d("."), op("=")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        binding.buttons.addItemDecoration(
            BottonItemDecoration(this)
        )

        adapter = MainAdapter()

        val manager = GridLayoutManager(this, 4)
        manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (bottons[position].isDouble) 2 else 1
            }
        }
        binding.buttons.layoutManager = manager


        binding.buttons.adapter = adapter

        adapter.submitList(bottons)


    }
}