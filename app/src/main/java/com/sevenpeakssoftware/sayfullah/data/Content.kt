package com.sevenpeakssoftware.sayfullah.data

data class Content(
    val changed: Int,
    val content: List<ContentX>,
    val created: Int,
    val dateTime: String,
    val id: Int,
    val image: String,
    val ingress: String,
    val tags: List<String>,
    val title: String
)