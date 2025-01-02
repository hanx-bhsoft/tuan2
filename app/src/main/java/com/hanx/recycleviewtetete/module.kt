package com.hanx.recycleviewtetete

import android.content.Context
import androidx.room.Room
import com.hanx.recycleviewtetete.room.Db
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Module {
    @Singleton
    @Provides
    fun getDb(@ApplicationContext context: Context): Db = Room.databaseBuilder(context , Db::class.java, "task").build()
     @Provides
     fun getDao(db: Db) = db.taskDao()
}