package com.paltabrain.core.logger

interface Logger {

    companion object {
        const val VERBOSE = 2
        const val DEBUG = 3
        const val INFO = 4
        const val WARN = 5
        const val ERROR = 6
        const val ASSERT = 7
    }

    fun log(priority: Int, tag: String, message: String, throwable: Throwable?)

    fun v(tag: String?, message: String?, throwable: Throwable? = null)
    fun d(tag: String?, message: String?, throwable: Throwable? = null)
    fun i(tag: String?, message: String?, throwable: Throwable? = null)
    fun w(tag: String?, message: String? = null, throwable: Throwable? = null)
    fun e(tag: String?, message: String? = null, throwable: Throwable? = null)
}
