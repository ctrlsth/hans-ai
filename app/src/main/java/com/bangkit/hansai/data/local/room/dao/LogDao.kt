package com.bangkit.hansai.data.local.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.bangkit.hansai.data.local.entity.LogEntity

@Dao
interface LogDao {
    @Query("SELECT * FROM log ORDER BY id ASC")
    fun getAll(): LiveData<List<LogEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(log: LogEntity)

    @Update
    suspend fun update(log: LogEntity)

    @Query("DELETE FROM log WHERE id = :logId")
    suspend fun delete(logId: String)

    @Query("DELETE FROM log")
    suspend fun deleteAll()
}