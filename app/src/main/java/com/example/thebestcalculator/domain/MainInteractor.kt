package com.example.thebestcalculator.domain

import com.example.thebestcalculator.data.calculator.AppData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainInteractor @Inject constructor(
    private val calculatorUseCase: CalculatorUseCase,
    private val historyUseCase: HistoryUseCase
) {

    fun getSaveData(): Flow<AppData?> {
        return historyUseCase.getSaveData()
    }

    suspend fun calculate(expression: String): String {
        val result = calculatorUseCase.calculate(expression)

        historyUseCase.saveInput(
            expression = expression,
            result = result
        )

        return result
    }

    suspend fun clearHistory() {
        historyUseCase.clearHistory()
    }
}