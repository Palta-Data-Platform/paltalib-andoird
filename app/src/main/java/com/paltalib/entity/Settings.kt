package com.paltalib.entity

data class Settings(val eventUploadThreshold: Int, val eventUploadMaxBatchSize: Int,
    val eventMaxCount: Int, val eventUploadPeriodSeconds: Int,
    val minTimeBetweenSessionsMillis: Long, val trackingSessionEvents: Boolean
)
