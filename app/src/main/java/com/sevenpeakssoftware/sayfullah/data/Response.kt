package com.sevenpeakssoftware.sayfullah.data

sealed class Response
data class Success(val data: Any?) : Response()
data class Loading(val msg: String?) : Response()
data class ErrorIn(val code: Int?, val message: String?) : Response()