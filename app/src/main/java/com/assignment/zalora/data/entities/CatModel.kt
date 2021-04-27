package com.assignment.zalora.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class CatModel(
    @PrimaryKey
    val id: String,
    val url: String,
    val breeds: List<Breed>,
    val height: Int,
    val width: Int
) : Serializable