package com.sevenpeakssoftware.sayfullah.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Md Sayfullah Al Noman Ranak
 */

@Entity
class Content(
    @PrimaryKey(autoGenerate = true)
    var dbId:Long,
    var id: Long,
    val title: String,
    val changed: Int,
    val content: List<ContentX>,
    val created: Int,
    val dateTime: String,
    val image: String,
    val ingress: String,
    val tags: List<String>
)