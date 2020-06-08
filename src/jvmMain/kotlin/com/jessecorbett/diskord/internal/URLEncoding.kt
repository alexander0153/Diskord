package com.jessecorbett.diskord.internal

import java.net.URLEncoder

internal fun urlEncode(input: String): String = URLEncoder.encode(input, "UTF-8")
