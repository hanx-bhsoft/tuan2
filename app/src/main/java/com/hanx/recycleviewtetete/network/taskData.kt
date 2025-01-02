package com.hanx.recycleviewtetete.network

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
@Entity(tableName = "tasks")
@JsonClass(generateAdapter = true)
data class Task(
    val userId: Int,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String,
    val completed: Boolean
)
