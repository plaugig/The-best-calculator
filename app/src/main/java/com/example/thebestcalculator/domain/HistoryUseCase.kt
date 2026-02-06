package com.example.thebestcalculator.domain

import com.example.thebestcalculator.data.calculator.AppData
import com.example.thebestcalculator.data.calculator.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by George on 2/6/26.
 */
class HistoryUseCase @Inject constructor(
	private val repository: AppRepository
) {

	fun getSaveData(): Flow<AppData?> {
		return repository.getSaveInput()
	}

	suspend fun saveInput(
		expression: String,
		result: String
	) {
		repository.saveInput(
			AppData(
				expression = expression,
				result = "=$result"
			)
		)
	}

	suspend fun clearHistory(){
		repository.clear()
	}
}