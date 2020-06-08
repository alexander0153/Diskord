package com.jessecorbett.diskord.internal

import java.time.Instant
import java.time.format.DateTimeFormatter

internal fun epochSecondNow(): Long = Instant.now().epochSecond

internal fun epochMillisNow(): Long = Instant.now().toEpochMilli()

internal fun parseRfc1123(timestamp: String): Long =
    DateTimeFormatter.RFC_1123_DATE_TIME.parse(timestamp, Instant::from).epochSecond
