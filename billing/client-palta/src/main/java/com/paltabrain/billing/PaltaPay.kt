package com.paltabrain.billing

import com.paltabrain.billing.interfaces.LifecycleDelegate
import com.paltabrain.core.logger.Logger

internal class PaltaPay(
    private val logger: Logger,

    ) : LifecycleDelegate {

    companion object {
        fun configure(
            logger: Logger,
        ): PaltaPay = PaltaPay(logger)
    }


    @Volatile
    private var state = State()
        @Synchronized get
        @Synchronized set


    override fun onForeground() {
        state = state.copy(
            appInBackground = false,
        )
    }

    override fun onBackground() {
        state = state.copy(
            appInBackground = true,
        )
    }
}

data class State(
    val appInBackground: Boolean = true,
)