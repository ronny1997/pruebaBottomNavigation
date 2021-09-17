package com.ronny.bigparty.core.exception

import javax.inject.Inject

sealed class Failure {
    class NetworkConnection: Failure()
    class ServerError : Failure()
    data class CustomError(val errorCode: Int, val errorMessage: String?) : Failure()
}