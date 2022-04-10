package com.hk.atm.data


sealed class Response<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T? = null) : Response<T>(data = data)
    class Error<T>(message: String? = null) : Response<T>(message = message)
}