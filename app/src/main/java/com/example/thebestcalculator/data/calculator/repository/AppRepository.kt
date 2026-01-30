package com.example.thebestcalculator.data.calculator.repository

import com.example.thebestcalculator.data.calculator.AppData
import com.example.thebestcalculator.data.calculator.data.source.LocalAppDataSource
import com.example.thebestcalculator.data.database.entities.AppEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val localAppDataSource: LocalAppDataSource
) {
    fun getSaveInput(): Flow<AppData?> {
        return localAppDataSource.observeInput().map { entity ->
            entity?.let {
                AppData(
                    id = it.id,
                    expression = it.expression,
                    result = it.result
                )
            }
        }
    }

    suspend fun saveInput (data: AppData) {
        val entity = AppEntity(
            id = data.id,
            expression = data.expression,
            result = data.result
        )
        localAppDataSource.saveInput(entity)
    }

    suspend fun clear(){
        localAppDataSource.clearInput()
    }

}