package com.hanx.recycleviewtetete.network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Task(
    val userId: Int,
    val id: Int,
    val title: String,
    val completed: Boolean
)
