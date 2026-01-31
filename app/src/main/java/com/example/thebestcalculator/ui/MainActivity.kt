package com.example.thebestcalculator.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.thebestcalculator.R
import com.example.thebestcalculator.databinding.ActivityMainBinding
import com.example.thebestcalculator.ui.item.ItemData
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch



@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MainTaskListener{

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    lateinit var adapter: MainAdapter

    private val viewModel by viewModels<MainViewModel>()

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

        adapter = MainAdapter(this, bottons)

        val manager = GridLayoutManager(this, 4)
        manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (bottons[position].isDouble) 2 else 1
            }
        }
        binding.buttons.layoutManager = manager


        binding.buttons.adapter = adapter

        setupObservers()
    }
    private fun setupObservers(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                launch {
                    viewModel.conditionText.collect { text ->
                        binding.expression.text = text
                    }
                }
                launch {
                    viewModel.resultText.collect { text ->
                        binding.result.text = text
                    }
                }
            }
        }
    }

    override fun onBottonClick(item: ItemData) {
       when (item.text){
           "Ac" -> viewModel.clear()
           "⌫" -> viewModel.removeLast()
           "=" -> viewModel.calculate()
           else -> viewModel.addSymbol(item.text)
       }
    }
}