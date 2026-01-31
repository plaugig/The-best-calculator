package com.example.thebestcalculator.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thebestcalculator.data.calculator.AppData
import com.example.thebestcalculator.domain.Interactor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val interactor: Interactor
) : ViewModel() {

    private val _conditionText = MutableStateFlow("")
    val conditionText: StateFlow<String> = _conditionText.asStateFlow()

    private val _resultText = MutableStateFlow("")
    val resultText: StateFlow<String> = _resultText.asStateFlow()

    fun addSymbol(symbol: String){
        _conditionText.value += symbol
    }

    fun clear(){
        _conditionText.value = ""
        _resultText.value = ""
    }

    fun removeLast(){
        if (_conditionText.value.isNotEmpty()){
            _conditionText.value = _conditionText.value.dropLast(1)
        }
    }

    fun calculate(){
        viewModelScope.launch {
            val expression = _conditionText.value
            val result = interactor.calculate(expression)
            _resultText.value = result
        }
    }

}