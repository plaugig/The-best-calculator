package com.example.thebestcalculator.domain

import com.example.thebestcalculator.data.calculator.AppData
import com.example.thebestcalculator.data.calculator.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import net.objecthunter.exp4j.ExpressionBuilder
import javax.inject.Inject


class CalculatorUseCase @Inject constructor(
private val repository: AppRepository
) {
    fun getSaveData(): Flow<AppData?> {
        return repository.getSaveInput()
    }

    suspend fun calculate(expression: String): String {
        if (expression.isBlank()) return ""

        return try {
            val cleanExpression = expression
                .replace("÷", "/")
                .replace("—", "-")
                .replace("卍", "*")
                .replace("+","+")
                .replace("%", "/100")

            val buildExpression = ExpressionBuilder(cleanExpression).build()
            val result = buildExpression.evaluate()

            val formattedResult = if (result % 1==0.0) {
                result.toLong().toString()
            } else {
                result.toString()
            }

            repository.saveInput(
                AppData(
                    expression = expression,
                    result = "=$formattedResult"
                )
            )
            formattedResult
        } catch (e: Exception){
            " Сосал?) ОШИБКА!"
        }
    }

    suspend fun clearHistory(){
        repository.clear()
    }
}