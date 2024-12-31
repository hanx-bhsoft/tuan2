package com.hanx.recycleviewtetete.network

import android.util.Log
import io.ktor.client.*
import io.ktor.client.engine.cio.*

import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.headers
import io.ktor.serialization.gson.*

object KtorClient {
    val client: HttpClient by lazy {
        HttpClient(CIO) {
            install(ContentNegotiation) {
                gson() // Use Gson for JSON serialization/deserialization
            }
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.d("KTOR", message)
                    }
                }
                level = LogLevel.ALL
            }
        defaultRequest {
            host = "https://dummy-json.mock.beeceptor.com"
        }
        }

    }
}
