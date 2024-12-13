package com.bangkit.hansai.data.local.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.bangkit.hansai.data.local.entity.RecipeEntity

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipe ORDER BY id ASC")
    fun getAll(): LiveData<List<RecipeEntity>>

    @Query("SELECT * FROM recipe WHERE name = :name")
    fun getByName(name: String): LiveData<List<RecipeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(recipe: List<RecipeEntity>)

    @Update
    suspend fun update(recipe: RecipeEntity)

    @Query("DELETE FROM recipe WHERE id = :recipeId")
    suspend fun delete(recipeId: String)

    @Query("DELETE FROM recipe")
    suspend fun deleteAll()
}