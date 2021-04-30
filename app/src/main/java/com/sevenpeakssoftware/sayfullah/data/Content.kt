package com.sevenpeakssoftware.sayfullah.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Content(
    val changed: Int,
    val content: List<ContentX>,
    val created: Int,
    val dateTime: String,
    @PrimaryKey
    val id: Long,
    val image: String,
    val ingress: String,
    val tags: List<String>,
    val title: String
)