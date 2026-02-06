package com.example.thebestcalculator.domain

import com.example.thebestcalculator.data.calculator.AppData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainInteractor @Inject constructor(
    private val calculatorUseCase: CalculatorUseCase
) {
    fun getSaveData(): Flow<AppData?> {
        return calculatorUseCase.getSaveData()
    }

    suspend fun calculate(expression: String): String {
        return calculatorUseCase.calculate(expression)
    }

    suspend fun clearHistory() {
        calculatorUseCase.clearHistory()
    }
}