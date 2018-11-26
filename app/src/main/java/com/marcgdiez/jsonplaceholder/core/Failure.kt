package com.marcgdiez.jsonplaceholder.core

sealed class Failure {
    class NetworkError: Failure()
}