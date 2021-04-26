package com.assignment.zalora.data.entities

import java.io.Serializable

data class Cat(
    val breeds: List<Breed>,
    val height: Int,
    val id: String,
    val url: String,
    val width: Int
) : Serializable