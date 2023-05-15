package com.pankti.jetpackcomposedemo.democompose.data.network

import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.plugins.observer.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.*
import kotlinx.serialization.json.Json


object KtorClient {

    val client: HttpClient = HttpClient(Android) {

        install(Logging) {
            level = LogLevel.ALL
        }

        install(ContentNegotiation) {
            val converter = KotlinxSerializationConverter(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
                allowSpecialFloatingPointValues = true
                allowStructuredMapKeys = true
                encodeDefaults = true
                explicitNulls = true
            })
            register(ContentType.Application.Json, converter)
        }

        defaultRequest {
            this.headers.append(HttpHeaders.ContentType, ContentType.Application.Json.toString())
        }

        install(ResponseObserver) {
            onResponse {
                println("HttpResponse:$it")
            }
        }
    }
}