package com.example.thebestcalculator.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.GridLayoutManager
import com.example.thebestcalculator.databinding.ActivityMainBinding
import com.example.thebestcalculator.ui.item.ItemData

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    lateinit var adapter: MainAdapter

    val bottons = listOf(
        ItemData("Ac"),
        ItemData("⌫"),
        ItemData("%"),
        ItemData("×"),


        ItemData("7"),
        ItemData("8"),
        ItemData("9"),
        ItemData("÷"),

        ItemData("4"),
        ItemData("5"),
        ItemData("6"),
        ItemData("-"),

        ItemData("1"),
        ItemData("2"),
        ItemData("3"),
        ItemData("+"),

        ItemData("0"),
        ItemData("."),
        ItemData("=")
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
                return if (bottons[position].text == "0") 2 else 1
            }
        }
        binding.buttons.layoutManager = manager


        binding.buttons.adapter = adapter

        adapter.submitList(bottons)


    }
}