package com.sevenpeakssoftware.sayfullah.data

data class CarBaseResponse(
    val content: List<Content>,
    val serverTime: Int,
    val status: String
)