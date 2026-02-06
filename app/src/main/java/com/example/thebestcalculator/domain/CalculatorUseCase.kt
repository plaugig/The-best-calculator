package com.example.thebestcalculator.domain

import net.objecthunter.exp4j.ExpressionBuilder
import javax.inject.Inject


class CalculatorUseCase @Inject constructor() {

	fun calculate(expression: String): String {
		if (expression.isBlank()) return ""

		return try {
			val cleanExpression = expression
				.replace("÷", "/")
				.replace("—", "-")
				.replace("卍", "*")
				.replace("+", "+")
				.replace("%", "/100")

			val buildExpression = ExpressionBuilder(cleanExpression).build()
			val result = buildExpression.evaluate()

			val formattedResult = if (result % 1 == 0.0) {
				result.toLong().toString()
			} else {
				result.toString()
			}

			formattedResult
		} catch (e: Exception) {
			"Сосал?) ОШИБКА!"
		}
	}
}