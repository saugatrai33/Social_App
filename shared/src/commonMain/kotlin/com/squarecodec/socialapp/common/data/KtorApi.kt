package com.squarecodec.socialapp.common.data

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.http.path
import io.ktor.http.takeFrom
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.logging.Logger
import kotlinx.serialization.json.Json

private const val BASE_URL = "http://192.168.11.7:8080/"

internal abstract class KtorApi {
    var client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                useAlternativeNames = true
            })
        }

        defaultRequest {
            header(HttpHeaders.CacheControl, "no-cache")
            header(HttpHeaders.ContentType, "application/json; charset=utf-8")
            header(HttpHeaders.Accept, "application/json")
//            header(HttpHeaders.AcceptEncoding, "gzip")
//            header(HEADER_MWAPPS_APPID, appId)
//            header(HEADER_MWAPPS_CLIENT, client)
//            header(HEADER_MWAPPS_CLIENT_VERSION, clientVersion)
        }
    }

    fun HttpRequestBuilder.endPoint(path: String) {
        url {
            takeFrom(BASE_URL)
            path(path)
            contentType(ContentType.Application.Json)
        }
    }

//    private val myLogger = object : Logger {
//        private val urlRegex = Regex("""FROM: .+?$""", RegexOption.MULTILINE)
//        private val delegate = LoggerFactory.getLogger(HttpClient::class.java)!!
//        override fun log(message: String) {
//            val strippedMessage = urlRegex.replace(message, "FROM: ***")
//            delegate.info(strippedMessage)
//        }
//    }
}