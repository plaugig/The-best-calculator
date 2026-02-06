package com.example.thebestcalculator.data.calculator.data.source

import com.example.thebestcalculator.data.database.Appdatabase
import com.example.thebestcalculator.data.database.entities.AppEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LocalAppDataSource @Inject constructor(
    private val database: Appdatabase
) {
    suspend fun saveInput(input: AppEntity){
        database.appDao().saveCurrentInput(input)
    }

    fun getInput(): Flow<AppEntity?> {
        return database.appDao().getCurrentInput()
            .distinctUntilChanged()
            .flowOn(Dispatchers.IO)
    }

    suspend fun clearInput() {
        database.appDao().clearCurrentInput()
    }
}