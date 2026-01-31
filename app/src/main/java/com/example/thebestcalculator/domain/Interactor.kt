package com.example.thebestcalculator.domain

import com.example.thebestcalculator.data.calculator.AppData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Interactor @Inject constructor(
    private val useCase: UseCase
) {
    fun getSaveData(): Flow<AppData?> {
        return useCase.getSaveData()
    }

    suspend fun calculate(expression: String): String {
        return useCase.calculate(String())
    }

    suspend fun clearHistory() {
        return useCase.clearHistory()
    }
}