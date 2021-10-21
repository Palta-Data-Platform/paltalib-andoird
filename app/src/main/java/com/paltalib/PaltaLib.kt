package com.paltalib

import android.content.Context
import com.amplitude.api.Amplitude
import com.amplitude.api.AmplitudeClient
import com.amplitude.api.Identify
import com.amplitude.api.Revenue
import org.json.JSONObject
import java.util.concurrent.TimeUnit

/**
 * This is the main PaltaLib class that manages Amplitude SDK instances. <br><br>
 */
class PaltaLib private constructor() {

    private val amplitudeInstances = mutableListOf<AmplitudeClient>()
    private val targets = mutableListOf<Target>()

    companion object {
        val instance = PaltaLib()
    }

    /**
     * Log event with the specified event type, event properties, groups, timestamp, with optional
     * out of session flag. If out of session is true, then the sessionId will be -1 for the event,
     * indicating that it is not part of the current session. Note: this might be useful when
     * logging events for notifications received.
     * <b>Note:</b> this is asynchronous and happens on a background thread.
     *
     * @param eventType       the event type
     * @param eventProperties the event properties
     * @param groups          the groups
     * @param timestamp       the timestamp in millisecond since epoch
     * @param outOfSession    the out of session
     * @see <a href="https://github.com/amplitude/Amplitude-Android#setting-event-properties">
     *     Setting Event Properties</a>
     * @see <a href="https://github.com/amplitude/Amplitude-Android#setting-groups">
     *     Setting Groups</a>
     * @see <a href="https://github.com/amplitude/Amplitude-Android#tracking-sessions">
     *     Tracking Sessions</a>
     */
    fun logEvent(
        eventType: String,
        eventProperties: JSONObject? = null,
        groups: JSONObject? = null,
        timestamp: Long? = null,
        outOfSession: Boolean = false
    ) {

        timestamp?.let {
            amplitudeInstances.forEach {
                it.logEvent(eventType, eventProperties, groups, timestamp, outOfSession)
                println("The element is $it")
            }
        } ?: amplitudeInstances.forEach {
            it.logEvent(
                eventType,
                eventProperties,
                groups,
                System.currentTimeMillis(),
                outOfSession
            )
            println("The element is $it")
        }
    }

    /**
     * Sets the user id (can be null).
     * If startNewSession is true, ends the session for the previous user and starts a new
     * session for the new user id.
     *
     * @param userId the user id
     * @return the AmplitudeClient
     */
    fun setUserId(userId: String?, startNewSession: Boolean = false): PaltaLib {
        amplitudeInstances.forEach {
            it.setUserId(userId, startNewSession)
        }
        return this
    }

    /**
     * Identify. Use this to send an {@link com.amplitude.api.Identify} object containing
     * user property operations to Amplitude server. If outOfSession is true, then the identify
     * event is sent with a session id of -1, and does not trigger any session-handling logic.
     *
     * @param identify an {@link Identify} object
     * @param outOfSession whther to log the identify event out of session
     */
    fun identify(identify: Identify, outOfSession: Boolean = false) {
        amplitudeInstances.forEach {
            it.identify(
                identify,
                outOfSession
            )
        }
    }

    fun groupIdentify(
        groupType: String,
        groupName: Any,
        groupIdentify: Identify,
        outOfSession: Boolean = false
    ) {
        amplitudeInstances.forEach {
            it.groupIdentify(
                groupType,
                groupName,
                groupIdentify,
                outOfSession
            )
        }
    }


    /**
     * Log revenue with a productId, quantity, price, and receipt data for revenue verification.
     *
     * @param productId        the product id
     * @param quantity         the quantity
     * @param price            the price
     * @param receipt          the receipt
     * @param receiptSignature the receipt signature
     * @deprecated - use {@code logRevenueV2} instead
     * @see <a href="https://github.com/amplitude/Amplitude-Android#tracking-revenue">
     *     Tracking Revenue</a>
     */
    @Deprecated(
        "", ReplaceWith(
            "instance.logRevenueV2(revenue)",
        )
    )
    fun logRevenue(
        productId: String? = null, quantity: Int = 1,
        price: Double, receipt: String? = null, receiptSignature: String? = null
    ) {
        amplitudeInstances.forEach {
            it.logRevenue(
                productId,
                quantity,
                price,
                receipt,
                receiptSignature
            )
        }
    }

    /**
     * Log revenue v2. Create a {@link Revenue} object to hold your revenue data and properties,
     * and log it as a revenue event using this method.
     *
     * @param revenue a {@link Revenue} object
     */
    fun logRevenueV2(revenue: Revenue?) {
        amplitudeInstances.forEach {
            it.logRevenueV2(revenue)
        }
    }

    /**
     * Sets user properties. This is a convenience wrapper around the
     * {@link Identify} API to set multiple user properties with a single
     * command. <b>Note:</b> the replace parameter is deprecated and has no effect.
     *
     * @param userProperties the user properties
     * @param replace        the replace - has no effect
     * @deprecated
     */
    fun setUserProperties(userProperties: JSONObject?, replace: Boolean = false) {
        amplitudeInstances.forEach {
            it.setUserProperties(userProperties)
        }
    }

    /**
     * Sets event upload max batch size. This controls the maximum number of events sent with
     * each upload request.
     *
     * @param eventUploadMaxBatchSize the event upload max batch size
     * @return the AmplitudeClient
     */
    fun setEventUploadMaxBatchSize(eventUploadMaxBatchSize: Int): PaltaLib {
        amplitudeInstances.forEach {
            it.setEventUploadMaxBatchSize(eventUploadMaxBatchSize)
        }
        return this
    }

    /**
     * Add configuration for new Apmlitude instance
     *
     * @param context Android context
     * @param target amplitude instance configuration
     */

    fun addTarget(context: Context, target: Target) {
        if (targets.contains(target)) {
            return
        }

        val amplitudeInstance = Amplitude.getInstance(target.name)
        val targetSettings = target.settings

        amplitudeInstance.initialize(context, targetSettings.apiKey)
        amplitudeInstance.trackSessionEvents(targetSettings.trackingSessionEvents)
            .setEventUploadThreshold(targetSettings.eventUploadThreshold)
            .setEventUploadMaxBatchSize(targetSettings.eventUploadMaxBatchSize)
            .setEventMaxCount(targetSettings.eventMaxCount)
            .setEventUploadPeriodMillis(
                TimeUnit.SECONDS.toMillis(targetSettings.eventUploadPeriodSeconds.toLong()).toInt()
            )

        target.serverURL?.let {
            amplitudeInstance.setServerUrl(it)
        }

        amplitudeInstances.add(amplitudeInstance)
    }
}
