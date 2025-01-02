package com.hanx.recycleviewtetete.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.hanx.recycleviewtetete.network.Task

@Dao
interface Taskdao {
    @Delete
    suspend fun delete(task: Task)
    @Query("SELECT * FROM tasks")
    suspend fun getAll(): List<Task>
    @Insert
    suspend fun insert(task: Task)
}