package com.bangkit.hansai.data.local.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bangkit.hansai.data.local.entity.LogEntity
import com.bangkit.hansai.data.local.entity.RecipeEntity
import com.bangkit.hansai.data.local.room.dao.LogDao
import com.bangkit.hansai.data.local.room.dao.RecipeDao
import com.bangkit.hansai.utils.Converters

@Database(entities = [LogEntity::class, RecipeEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class HansAiDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
    abstract fun logDao(): LogDao

    companion object {
        @Volatile
        private var instance: HansAiDatabase? = null
        fun getInstance(context: Context): HansAiDatabase =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    HansAiDatabase::class.java, "hansai.db"
                ).allowMainThreadQueries().build()
            }
    }
}