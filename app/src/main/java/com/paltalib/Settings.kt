package com.paltalib

data class Settings(
    val apiKey: String, val eventUploadThreshold: Int, val eventUploadMaxBatchSize: Int,
    val eventMaxCount: Int, val eventUploadPeriodSeconds: Int,
    val minTimeBetweenSessionsMillis: Long, val trackingSessionEvents: Boolean
)
