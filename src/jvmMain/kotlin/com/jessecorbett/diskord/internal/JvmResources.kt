package com.jessecorbett.diskord.internal

import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.cio.CIO
import io.ktor.util.KtorExperimentalAPI

@OptIn(KtorExperimentalAPI::class)
internal fun websocketClient(): HttpClientEngineFactory<HttpClientEngineConfig> = CIO

@OptIn(KtorExperimentalAPI::class)
internal fun httpClient(): HttpClientEngineFactory<HttpClientEngineConfig> = CIO
