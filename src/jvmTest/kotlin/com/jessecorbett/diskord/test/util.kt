package com.jessecorbett.diskord.test

import kotlinx.coroutines.runBlocking

internal fun <T> waitForTest(block: suspend () -> T): T = runBlocking { block() }
