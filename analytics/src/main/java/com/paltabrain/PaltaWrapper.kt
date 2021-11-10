package com.paltabrain

import android.app.Activity
import android.app.Application
import com.amplitude.api.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

interface PaltaWrapper {

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
     */
    fun logEvent(
        eventType: String,
        eventProperties: JSONObject? = null,
        groups: JSONObject? = null,
        timestamp: Long? = System.currentTimeMillis(),
        outOfSession: Boolean = false
    )

    /**
     * Log event with the specified event type, event properties, groups, timestamp,  with optional
     * sout of ession flag. If out of session is true, then the sessionId will be -1 for the event,
     * indicating that it is not part of the current session. Note: this might be useful when
     * logging events for notifications received.
     * **Note:** this is version is synchronous and blocks the main thread until done.
     *
     * @param eventType       the event type
     * @param eventProperties the event properties
     * @param groups          the groups
     * @param timestamp       the timestamp in milliseconds since epoch
     * @param outOfSession    the out of session
     */
    fun logEventSync(
        eventType: String,
        eventProperties: JSONObject? = null,
        groups: JSONObject? = null,
        timestamp: Long? = System.currentTimeMillis(),
        outOfSession: Boolean = false
    )

    /**
     * Sets the user id (can be null).
     * If startNewSession is true, ends the session for the previous user and starts a new
     * session for the new user id.
     *
     * @param userId the user id
     * @return the PaltaLib
     */
    fun setUserId(userId: String?, startNewSession: Boolean = false): PaltaAnalytics

    /**
     * Identify. Use this to send an {@link com.amplitude.api.Identify} object containing
     * user property operations to PlataLib server. If outOfSession is true, then the identify
     * event is sent with a session id of -1, and does not trigger any session-handling logic.
     *
     * @param identify an {@link Identify} object
     * @param outOfSession whther to log the identify event out of session
     */
    fun identify(identify: Identify?, outOfSession: Boolean = false)

    fun groupIdentify(
        groupType: String?,
        groupName: Any?,
        groupIdentify: Identify?,
        outOfSession: Boolean = false
    )

    /**
     * Log revenue with a productId, quantity, price, and receipt data for revenue verification.
     *
     * @param productId        the product id
     * @param quantity         the quantity
     * @param price            the price
     * @param receipt          the receipt
     * @param receiptSignature the receipt signature
     * @deprecated - use {@code logRevenueV2} instead
     */
    @Deprecated(
        "", ReplaceWith(
            "instance.logRevenueV2(revenue)",
        )
    )
    fun logRevenue(
        productId: String? = null, quantity: Int = 1,
        price: Double, receipt: String? = null, receiptSignature: String? = null
    )

    /**
     * Log revenue v2. Create a {@link Revenue} object to hold your revenue data and properties,
     * and log it as a revenue event using this method.
     *
     * @param revenue a {@link Revenue} object
     */
    fun logRevenueV2(revenue: Revenue?)

    /**
     * Sets user properties. This is a convenience wrapper around the
     * {@link Identify} API to set multiple user properties with a single
     * command. <b>Note:</b> the replace parameter is deprecated and has no effect.
     *
     * @param userProperties the user properties
     * @param replace        the replace - has no effect
     * @deprecated
     */
    fun setUserProperties(userProperties: JSONObject?, replace: Boolean = false)

    /**
     * Sets event upload max batch size. This controls the maximum number of events sent with
     * each upload request.
     *
     * @param eventUploadMaxBatchSize the event upload max batch size
     * @return the PlataLib
     */
    fun setEventUploadMaxBatchSize(eventUploadMaxBatchSize: Int): PaltaAnalytics


    /**
     * Enable foreground tracking for the SDK. This is <b>HIGHLY RECOMMENDED</b>, and will allow
     * for accurate session tracking.
     *
     * @param app the Android application
     * @return the PlataLib
     */
    fun enableForegroundTracking(app : Application) : PaltaAnalytics

    /**
     * Whether to set a new device ID per install. If true, then the SDK will always generate a new
     * device ID on app install (as opposed to re-using an existing value like ADID).
     *
     * @param newDeviceIdPerInstall whether to set a new device ID on app install.
     * @return the PaltaLib
     * @deprecated
     */
    fun enableNewDeviceIdPerInstall(newDeviceIdPerInstall: Boolean): PaltaAnalytics

    /**
     * Whether to use the Android advertising ID (ADID) as the user's device ID.
     *
     * @return the AmplitudeClient
     */
    fun useAdvertisingIdForDeviceId(): PaltaAnalytics

    /**
     * Use Android app set id as the user's device ID.
     *
     * @return the PaltaLib
     */
    fun useAppSetIdForDeviceId(): PaltaAnalytics

    /**
     * Enable location listening in the SDK. This will add the user's current lat/lon coordinates
     * to every event logged.
     *
     * This function should be called before SDK initialization, e.g. [.initialize].
     *
     * @return the PaltaLib
     */
    fun enableLocationListening(): PaltaAnalytics

    /**
     * Disable location listening in the SDK. This will stop the sending of the user's current
     * lat/lon coordinates.
     *
     * This function should be called before SDK initialization, e.g. [.initialize].
     *
     * @return the PaltaLib
     */
    fun disableLocationListening(): PaltaAnalytics

    /**
     * Sets event upload threshold. The SDK will attempt to batch upload unsent events
     * every eventUploadPeriodMillis milliseconds, or if the unsent event count exceeds the
     * event upload threshold.
     *
     * @param eventUploadThreshold the event upload threshold
     * @return the PaltaLib
     */
    fun setEventUploadThreshold(eventUploadThreshold: Int): PaltaAnalytics

    /**
     * Sets event max count. This is the maximum number of unsent events to keep on the device
     * (for example if the device does not have internet connectivity and cannot upload events).
     * If the number of unsent events exceeds the max count, then the SDK begins dropping events,
     * starting from the earliest logged.
     *
     * @param eventMaxCount the event max count
     * @return the PaltaLib
     */
    fun setEventMaxCount(eventMaxCount: Int): PaltaAnalytics

    /**
     * Sets event upload period millis. The SDK will attempt to batch upload unsent events
     * every eventUploadPeriodMillis milliseconds, or if the unsent event count exceeds the
     * event upload threshold.
     *
     * @param eventUploadPeriodMillis the event upload period millis
     * @return the PaltaClient
     */
    fun setEventUploadPeriodMillis(eventUploadPeriodMillis: Int): PaltaAnalytics

    /**
     * Sets min time between sessions millis.
     *
     * @param minTimeBetweenSessionsMillis the min time between sessions millis
     * @return the PaltaClient
     */
    fun setMinTimeBetweenSessionsMillis(minTimeBetweenSessionsMillis: Long): PaltaAnalytics

    /**
     * Sets a custom server url for event upload.
     * @param serverUrl - a string url for event upload.
     * @return the PaltaClient
     */
    fun setServerUrl(serverUrl: String): PaltaAnalytics

    /**
     * Set Bearer Token to be included in request header.
     * @param token
     * @return the PaltaClient
     */
    fun setBearerToken(token: String): PaltaAnalytics

    /**
     * Sets session timeout millis. If foreground tracking has not been enabled with
     * @{code enableForegroundTracking()}, then new sessions will be started after
     * sessionTimeoutMillis milliseconds have passed since the last event logged.
     *
     * @param sessionTimeoutMillis the session timeout millis
     * @return the PaltaClient
     */
    fun setSessionTimeoutMillis(sessionTimeoutMillis: Long): PaltaAnalytics

    fun setTrackingOptions(trackingOptions: TrackingOptions): PaltaAnalytics

    /**
     * Enable COPPA (Children's Online Privacy Protection Act) restrictions on ADID, city, IP address and location tracking.
     * This can be used by any customer that does not want to collect ADID, city, IP address and location tracking.
     */
    fun enableCoppaControl(): PaltaAnalytics

    /**
     * Disable COPPA (Children's Online Privacy Protection Act) restrictions on ADID, city, IP address and location tracking.
     */
    fun disableCoppaControl(): PaltaAnalytics

    /**
     * Sets opt out. If true then the SDK does not track any events for the user.
     *
     * @param optOut whether or not to opt the user out of tracking
     * @return the PaltaClient
     */
    fun setOptOut(optOut: Boolean): PaltaAnalytics

    /**
     * Library name is default as `amplitude-android`.
     * Notice: You will only want to set it when following conditions are met.
     * 1. You develop your own library which bridges Amplitude Android native library.
     * 2. You want to track your library as one of the data sources.
     */
    fun setLibraryName(libraryName: String?): PaltaAnalytics

    /**
     * Library version is default as the latest Amplitude Android SDK version.
     * Notice: You will only want to set it when following conditions are met.
     * 1. You develop your own library which bridges Amplitude Android native library.
     * 2. You want to track your library as one of the data sources.
     */
    fun setLibraryVersion(libraryVersion: String?): PaltaAnalytics

    /**
     * Returns whether or not the user is opted out of tracking.
     *
     * @return the optOut flag value
     */
    fun isOptedOut(): Boolean

    /**
     * Enable/disable message logging by the SDK.
     *
     * @param enableLogging whether to enable message logging by the SDK.
     * @return the PaltaClient
     */
    fun enableLogging(enableLogging: Boolean): PaltaAnalytics

    /**
     * Sets the logging level. Logging messages will only appear if they are the same severity
     * level or higher than the set log level.
     *
     * @param logLevel the log level
     * @return the AmplitudeClient
     */
    fun setLogLevel(logLevel: Int): PaltaAnalytics

    /**
     * Set log callback, it can help read and collect error message from sdk
     *
     * @param callback
     * @return the PaltaClient
     */
    fun setLogCallback(callback: AmplitudeLogCallback?): PaltaAnalytics

    /**
     * Sets offline. If offline is true, then the SDK will not upload events to Amplitude servers;
     * however, it will still log events.
     *
     * @param offline whether or not the SDK should be offline
     * @return the PaltaClient
     */
    fun setOffline(offline: Boolean): PaltaAnalytics

    /**
     * Enable/disable flushing of unsent events on app close (enabled by default).
     *
     * @param flushEventsOnClose whether to flush unsent events on app close
     * @return the PaltaClient
     */
    fun setFlushEventsOnClose(flushEventsOnClose: Boolean): PaltaAnalytics

    /**
     * Track session events amplitude client. If enabled then the SDK will automatically send
     * start and end session events to mark the start and end of the user's sessions.
     *
     * @param trackingSessionEvents whether to enable tracking of session events
     * @return the PaltaClient
     */
    fun trackSessionEvents(trackingSessionEvents: Boolean): PaltaAnalytics

    /**
     * Turning this flag on will find the best server url automatically based on users' geo location.
     * Note:
     * 1. If you have your own proxy server and use `setServerUrl` API, please leave this off.
     * 2. If you have users in China Mainland, we suggest you turn this on.
     *
     * @param useDynamicConfig whether to enable dynamic config
     * @return the PaltaClient
     */
    fun setUseDynamicConfig(useDynamicConfig: Boolean): PaltaAnalytics

    /**
     * Show Amplitude Event Explorer for the given activity.
     *
     * @param activity root activity
     */
    fun showEventExplorer(activity: Activity)

    /**
     * Gets the current session id.
     *
     * @return The current sessionId value.
     */
    fun getSessionId(): Long

    /**
     * Public method to start a new session if needed.
     *
     * @param timestamp the timestamp
     * @return whether or not a new session was started
     */
    fun startNewSessionIfNeeded(timestamp: Long): Boolean

    /**
     * Clear user properties. This will clear all user properties at once. **Note: the
     * result is irreversible!**
     */
    fun clearUserProperties()

    /**
     * Sets the user's group(s).
     *
     * @param groupType the group type (ex: orgId)
     * @param groupName the group name (ex: 15)
     */
    fun setGroup(groupType: String?, groupName: Any?)

    /**
     * Truncate values in a JSON object. Any string values longer than 1024 characters will be
     * truncated to 1024 characters.
     * Any dictionary with more than 1000 items will be ignored.
     *
     * @param object the object
     * @return the truncated JSON object
     */
    fun truncate(jsonObject: JSONObject?): JSONObject

    /**
     * Truncate values in a JSON array. Any string values longer than 1024 characters will be
     * truncated to 1024 characters.
     *
     * @param array the array
     * @return the truncated JSON array
     * @throws JSONException the json exception
     */
    @Throws(JSONException::class)
    fun truncate(array: JSONArray?): JSONArray

    /**
     * Gets the user's id. Can be null.
     *
     * @return The developer specified identifier for tracking within the analytics system.
     */
    fun getUserId(): String?

    /**
     * Sets a custom device id. **Note: only do this if you know what you are doing!**
     *
     * @param deviceId the device id
     * @return the AmplitudeClient
     */
    fun setDeviceId(deviceId: String?): PaltaAnalytics

    /**
     * Get the current device id. Can be null if deviceId hasn't been initialized yet.
     *
     * @return A unique identifier for tracking within the analytics system.
     */
    fun getDeviceId(): String?

    /**
     * Regenerates a new random deviceId for current user. Note: this is not recommended unless you
     * know what you are doing. This can be used in conjunction with setUserId(null) to anonymize
     * users after they log out. With a null userId and a completely new deviceId, the current user
     * would appear as a brand new user in dashboard.
     */
    fun regenerateDeviceId(): PaltaAnalytics

    /**
     * Force SDK to upload any unsent events.
     */
    fun uploadEvents()

    fun setDeviceIdCallback(callback: AmplitudeDeviceIdCallback?): PaltaAnalytics

    /**
     * Set tracking plan information.
     * @param plan Plan object
     * @return the PaltaClient
     */
    fun setPlan(plan: Plan?): PaltaAnalytics
}