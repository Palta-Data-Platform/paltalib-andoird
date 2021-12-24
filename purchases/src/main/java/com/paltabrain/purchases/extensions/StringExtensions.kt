package com.paltabrain.purchases.extensions

import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.nio.charset.Charset


fun String.toBodyRequest(contentType: MediaType? = null): RequestBody {
    val charset: Charset = Charsets.UTF_8
    val finalContentType: MediaType? = contentType
    val bytes = toByteArray(charset)
    return bytes.toRequestBody(finalContentType, 0, bytes.size)
}
