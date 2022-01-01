package dev.tsnanh.common.data.remote.api

import dev.tsnanh.common.data.remote.dtos.AbsenceDTO
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

object MyVKULiteApi {
    private val client = HttpClient(CIO) {
        engine {
            requestTimeout = 30_000L
            endpoint {
                connectTimeout = 30_000L
                socketTimeout = 30_000L
            }
        }
        install(JsonFeature) {
            serializer = KotlinxSerializer(
                kotlinx.serialization.json.Json {
                    isLenient = true
                    prettyPrint = true
                }
            )
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
    }

    private val address = Url("https://daotao.vku.udn.vn/thongbaonghi")

    suspend fun getAbsences(): List<AbsenceDTO> = try {
        val absencesString: String = client.get(address)
        JsonSerializer.decodeFromString(absencesString)
    } catch (e: Exception) {
        println(e)
        emptyList()
    }
}

val JsonSerializer = Json { ignoreUnknownKeys = true }
