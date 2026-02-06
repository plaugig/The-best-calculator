package com.example.thebestcalculator.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thebestcalculator.domain.MainInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainInteractor: MainInteractor
) : ViewModel() {

    private val _conditionText = MutableStateFlow("")
    val conditionText: StateFlow<String> = _conditionText.asStateFlow()

    private val _resultText = MutableStateFlow("")
    val resultText: StateFlow<String> = _resultText.asStateFlow()

    init {
        viewModelScope.launch {
            mainInteractor.getSaveData().collect { lastData ->
                if (lastData != null) {
                    _conditionText.value = lastData.expression
                    _resultText.value = lastData.result
                }
            }
        }
    }

    fun addSymbol(symbol: String) {
        val currentText = _resultText.value

        val operator = listOf("+", "—", "÷", "卍", "*", "/", "%")

        if (currentText.isNotEmpty()){
            val latChar = currentText.last().toString()

            if (operator.contains(latChar) && operator.contains(symbol)) {
                _resultText.value = currentText.dropLast(1) + symbol
            } else {
                _resultText.value += symbol
            }
        } else {
            if (!operator.contains(symbol)) {
                _resultText.value += symbol
            }
        }
    }

    fun clear() {
        _conditionText.value = ""
        _resultText.value = ""

        viewModelScope.launch {
            mainInteractor.clearHistory()
        }
    }

    fun removeLast() {
        if (_conditionText.value.isNotEmpty()) {
            _conditionText.value = _conditionText.value.dropLast(1)
        }
    }

    fun calculate() {
        viewModelScope.launch {
            val expression = _resultText.value
            val result = mainInteractor.calculate(expression)

            _conditionText.value = expression
            _resultText.value = result
        }
    }



}