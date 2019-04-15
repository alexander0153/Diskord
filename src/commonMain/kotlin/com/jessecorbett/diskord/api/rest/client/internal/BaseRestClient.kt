package com.jessecorbett.diskord.api.rest.client.internal

import com.jessecorbett.diskord.internal.configureHttpClient
import com.jessecorbett.diskord.internal.httpClient
import io.ktor.client.HttpClient
import io.ktor.client.request.*
import io.ktor.client.request.forms.formData
import io.ktor.client.request.forms.submitForm
import io.ktor.client.response.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.content.TextContent
import kotlinx.coroutines.io.readUTF8Line

const val discordApi = "https://discordapp.com/api"

open class BaseRestClient {
    private val contentType = ContentType.parse("application/json")

    protected suspend fun getRequest(url: String, headers: Map<String, String>): Response {
        val result = client.get<HttpResponse>(discordApi + url) { headers.forEach { header(it.key, it.value) } }
        return result.toResponse()
    }

    protected suspend fun postRequest(url: String, jsonBody: String?, headers: Map<String, String>): Response {
        val result = client.post<HttpResponse>(discordApi + url) {
            headers.forEach { header(it.key, it.value) }
            if (jsonBody != null) {
                body = TextContent(jsonBody, contentType)
            }
        }
        return result.toResponse()
    }

    protected suspend fun putRequest(url: String, jsonBody: String?, headers: Map<String, String>): Response {
        val result = client.put<HttpResponse>(discordApi + url) {
            headers.forEach { header(it.key, it.value) }
            if (jsonBody != null) {
                body = TextContent(jsonBody, contentType)
            }
        }
        return result.toResponse()
    }

    protected suspend fun patchRequest(url: String, jsonBody: String?, headers: Map<String, String>): Response {
        val result = client.patch<HttpResponse>(discordApi + url) {
            headers.forEach { header(it.key, it.value) }
            if (jsonBody != null) {
                body = TextContent(jsonBody, contentType)
            }
        }
        return result.toResponse()
    }

    protected suspend fun deleteRequest(url: String, headers: Map<String, String>): Response {
        val result = client.delete<HttpResponse>(discordApi + url) {
            headers.forEach { header(it.key, it.value) }
        }
        return result.toResponse()
    }

    protected suspend fun postForm(url: String, form: Map<String, String>): Response {
        val result = client.submitForm<HttpResponse> {
            url {
                host = discordApi
                path(listOf(url))
            }
            body = formData {
                form.forEach {
                    append(it.key, it.value)
                }
            }
        }
        return result.toResponse()
    }

    private suspend fun HttpResponse.toResponse(): Response {
        var string: String? = null

        while (true) {
            val line = content.readUTF8Line() ?: break
            if (string == null) {
                string = line
            } else {
                string += line
            }
        }

        return Response(status.value, string, headers.names().map { Pair(it, headers[it]) }.toMap())
    }

    private companion object {
        private val client = HttpClient(httpClient(), configureHttpClient())
    }
}
