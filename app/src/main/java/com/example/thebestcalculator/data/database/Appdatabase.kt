package com.example.thebestcalculator.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.thebestcalculator.data.database.dao.AppDao
import com.example.thebestcalculator.data.database.entities.AppEntity


@Database(
    entities = [AppEntity::class],
    version = 1
)
abstract class Appdatabase: RoomDatabase() {

    abstract fun AppDao(): AppDao

}