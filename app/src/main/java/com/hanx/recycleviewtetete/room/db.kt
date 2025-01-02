package com.hanx.recycleviewtetete.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hanx.recycleviewtetete.network.Task

@Database(entities = [Task::class], version = 1, exportSchema = true)
abstract class Db :RoomDatabase(){
    abstract fun taskDao(): Taskdao
}