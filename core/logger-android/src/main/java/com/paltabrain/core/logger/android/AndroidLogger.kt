package com.paltabrain.core.logger.android

import android.util.Log
import com.paltabrain.core.logger.Logger

class AndroidLogger : Logger {

    override fun log(priority: Int, tag: String, message: String, throwable: Throwable?) {
        when (priority) {
            Logger.VERBOSE -> v(tag, message, throwable)
            Logger.DEBUG -> d(tag, message, throwable)
            Logger.INFO -> i(tag, message, throwable)
            Logger.WARN -> w(tag, message, throwable)
            Logger.ERROR -> e(tag, message, throwable)
            Logger.ASSERT -> e(tag, message, throwable)
            else -> e(tag, message, throwable)
        }
    }

    override fun v(tag: String?, message: String?, throwable: Throwable?) {
        Log.v(tag, message, throwable)
    }

    override fun d(tag: String?, message: String?, throwable: Throwable?) {
        Log.d(tag, message, throwable)
    }

    override fun i(tag: String?, message: String?, throwable: Throwable?) {
        Log.i(tag, message, throwable)
    }

    override fun w(tag: String?, message: String?, throwable: Throwable?) {
        Log.w(tag, message, throwable)
    }

    override fun e(tag: String?, message: String?, throwable: Throwable?) {
        Log.e(tag, message, throwable)
    }
}