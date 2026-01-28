package com.example.thebestcalculator.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.thebestcalculator.data.database.entities.AppEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface AppDao {

@Insert(onConflict = OnConflictStrategy.REPLACE)
suspend fun saveCurrentInput(input: AppEntity)

@Query("SELECT * FROM calculator WHERE id = 1")
fun observeCurrentInput(): Flow<AppEntity?>

@Query("DELETE FROM calculator")
suspend fun clearCurrentInput()
}