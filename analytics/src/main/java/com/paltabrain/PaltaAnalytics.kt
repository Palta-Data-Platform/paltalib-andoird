package com.paltabrain

import android.app.Activity
import android.app.Application
import android.content.Context
import com.amplitude.api.*
import com.google.gson.Gson
import com.paltabrain.entity.Target
import com.paltabrain.entity.TargetList
import com.paltabrain.extensions.readAssetsFile
import com.paltabrain.network.NetworkClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject
import java.util.concurrent.TimeUnit

class PaltaAnalytics private constructor(): PaltaWrapper {

    private val amplitudeInstances = mutableListOf<AmplitudeClient>()
    private val targets = mutableListOf<Target>()

    companion object {
        val instance = PaltaAnalytics()
    }

    override fun logEvent(
        eventType: String,
        eventProperties: JSONObject?,
        groups: JSONObject?,
        timestamp: Long?,
        outOfSession: Boolean
    ) {
        timestamp?.let {
            amplitudeInstances.forEach {
                it.logEvent(eventType, eventProperties, groups, timestamp, outOfSession)
            }
        } ?: amplitudeInstances.forEach {
            it.logEvent(
                eventType,
                eventProperties,
                groups,
                System.currentTimeMillis(),
                outOfSession
            )
        }
    }

    override fun logEventSync(
        eventType: String,
        eventProperties: JSONObject?,
        groups: JSONObject?,
        timestamp: Long?,
        outOfSession: Boolean
    ) {
        timestamp?.let {
            amplitudeInstances.forEach {
                it.logEventSync(eventType, eventProperties, groups, timestamp, outOfSession)
            }
        } ?: amplitudeInstances.forEach {
            it.logEventSync(
                eventType,
                eventProperties,
                groups,
                System.currentTimeMillis(),
                outOfSession
            )
        }
    }

    override fun setUserId(userId: String?, startNewSession: Boolean): PaltaAnalytics {
        amplitudeInstances.forEach {
            it.setUserId(userId, startNewSession)
        }
        return this
    }

    override fun identify(identify: Identify?, outOfSession: Boolean) {
        amplitudeInstances.forEach {
            it.identify(
                identify,
                outOfSession
            )
        }
    }

    override fun groupIdentify(
        groupType: String?,
        groupName: Any?,
        groupIdentify: Identify?,
        outOfSession: Boolean
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

    override fun logRevenue(
        productId: String?, quantity: Int,
        price: Double, receipt: String?, receiptSignature: String?
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

    override fun logRevenueV2(revenue: Revenue?) {
        amplitudeInstances.forEach {
            it.logRevenueV2(revenue)
        }
    }

    override fun setUserProperties(userProperties: JSONObject?, replace: Boolean) {
        amplitudeInstances.forEach {
            it.setUserProperties(userProperties)
        }
    }

    override fun setEventUploadMaxBatchSize(eventUploadMaxBatchSize: Int): PaltaAnalytics {
        amplitudeInstances.forEach {
            it.setEventUploadMaxBatchSize(eventUploadMaxBatchSize)
        }
        return this
    }

    override fun enableForegroundTracking(app: Application): PaltaAnalytics {
        amplitudeInstances.forEach {
            it.enableForegroundTracking(app)
        }
        return this
    }

    override fun enableNewDeviceIdPerInstall(newDeviceIdPerInstall: Boolean): PaltaAnalytics {
        amplitudeInstances.forEach {
            it.enableNewDeviceIdPerInstall(newDeviceIdPerInstall)
        }
        return this
    }

    override fun useAdvertisingIdForDeviceId(): PaltaAnalytics {
        amplitudeInstances.forEach {
            it.useAdvertisingIdForDeviceId()
        }
        return this
    }

    override fun useAppSetIdForDeviceId(): PaltaAnalytics {
        amplitudeInstances.forEach {
            it.useAppSetIdForDeviceId()
        }
        return this
    }

    override fun enableLocationListening(): PaltaAnalytics {
        amplitudeInstances.forEach {
            it.enableLocationListening()
        }
        return this
    }


    override fun disableLocationListening(): PaltaAnalytics {
        amplitudeInstances.forEach {
            it.disableLocationListening()
        }
        return this
    }

    override fun setEventUploadThreshold(eventUploadThreshold: Int): PaltaAnalytics {
        amplitudeInstances.forEach {
            it.setEventUploadThreshold(eventUploadThreshold)
        }
        return this
    }

    override fun setEventMaxCount(eventMaxCount: Int): PaltaAnalytics {
        amplitudeInstances.forEach {
            it.setEventMaxCount(eventMaxCount)
        }
        return this
    }

    override fun setEventUploadPeriodMillis(eventUploadPeriodMillis: Int): PaltaAnalytics {
        amplitudeInstances.forEach {
            it.setEventUploadPeriodMillis(eventUploadPeriodMillis)
        }
        return this
    }

    override fun setMinTimeBetweenSessionsMillis(minTimeBetweenSessionsMillis: Long): PaltaAnalytics {
        amplitudeInstances.forEach {
            it.setMinTimeBetweenSessionsMillis(minTimeBetweenSessionsMillis)
        }
        return this
    }

    override fun setServerUrl(serverUrl: String): PaltaAnalytics {
        amplitudeInstances.forEach {
            it.setServerUrl(serverUrl)
        }
        return this
    }

    override fun setBearerToken(token: String): PaltaAnalytics {
        amplitudeInstances.forEach {
            it.setBearerToken(token)
        }
        return this
    }

    override fun setSessionTimeoutMillis(sessionTimeoutMillis: Long): PaltaAnalytics {
        amplitudeInstances.forEach {
            it.setSessionTimeoutMillis(sessionTimeoutMillis)
        }
        return this
    }

    override fun setTrackingOptions(trackingOptions: TrackingOptions): PaltaAnalytics {
        amplitudeInstances.forEach {
            it.setTrackingOptions(trackingOptions)
        }
        return this
    }

    override fun enableCoppaControl(): PaltaAnalytics {
        amplitudeInstances.forEach {
            it.enableCoppaControl()
        }
        return this
    }

    override fun disableCoppaControl(): PaltaAnalytics {
        amplitudeInstances.forEach {
            it.disableCoppaControl()
        }
        return this
    }

    override fun setOptOut(optOut: Boolean): PaltaAnalytics {
        amplitudeInstances.forEach {
            it.setOptOut(optOut)
        }
        return this
    }

    override fun setLibraryName(libraryName: String?): PaltaAnalytics {
        amplitudeInstances.forEach {
            it.setLibraryName(libraryName)
        }
        return this
    }

    override fun setLibraryVersion(libraryVersion: String?): PaltaAnalytics {
        amplitudeInstances.forEach {
            it.setLibraryVersion(libraryVersion)
        }
        return this
    }

    override fun isOptedOut(): Boolean {
        return amplitudeInstances[0].isOptedOut
    }

    override fun enableLogging(enableLogging: Boolean): PaltaAnalytics {
        amplitudeInstances.forEach {
            it.enableLogging(enableLogging)
        }
        return this
    }

    override fun setLogLevel(logLevel: Int): PaltaAnalytics {
        amplitudeInstances.forEach {
            it.setLogLevel(logLevel)
        }
        return this
    }

    override fun setLogCallback(callback: AmplitudeLogCallback?): PaltaAnalytics {
        amplitudeInstances.forEach {
            it.setLogCallback(callback)
        }
        return this
    }

    override fun setOffline(offline: Boolean): PaltaAnalytics {
        amplitudeInstances.forEach {
            it.setOffline(offline)
        }
        return this
    }

    override fun setFlushEventsOnClose(flushEventsOnClose: Boolean): PaltaAnalytics {
        amplitudeInstances.forEach {
            it.setFlushEventsOnClose(flushEventsOnClose)
        }
        return this
    }

    override fun trackSessionEvents(trackingSessionEvents: Boolean): PaltaAnalytics {
        amplitudeInstances.forEach {
            it.trackSessionEvents(trackingSessionEvents)
        }
        return this
    }

    override fun setUseDynamicConfig(useDynamicConfig: Boolean): PaltaAnalytics {
        amplitudeInstances.forEach {
            it.setUseDynamicConfig(useDynamicConfig)
        }
        return this
    }

    override fun showEventExplorer(activity: Activity) {
        amplitudeInstances.forEach {
            it.showEventExplorer(activity)
        }
    }

    override fun getSessionId(): Long {
        return amplitudeInstances[0].sessionId
    }

    override fun startNewSessionIfNeeded(timestamp: Long): Boolean {
        amplitudeInstances.forEach {
            if (!it.startNewSessionIfNeeded(timestamp)) {
                return false
            }
        }
        return true
    }

    override fun clearUserProperties() {
        amplitudeInstances.forEach {
            it.clearUserProperties()
        }
    }

    override fun setGroup(groupType: String?, groupName: Any?) {
        amplitudeInstances.forEach {
            it.setGroup(groupType, groupName)
        }
    }

    override fun truncate(jsonObject: JSONObject?): JSONObject {
        return amplitudeInstances[0].truncate(jsonObject)
    }

    override fun truncate(array: JSONArray?): JSONArray {
        return amplitudeInstances[0].truncate(array)
    }

    override fun getUserId(): String? {
        return amplitudeInstances[0].userId
    }

    override fun setDeviceId(deviceId: String?): PaltaAnalytics {
        amplitudeInstances.forEach {
            it.deviceId = deviceId
        }

        return this
    }

    override fun getDeviceId(): String? {
        return amplitudeInstances[0].deviceId
    }

    override fun regenerateDeviceId(): PaltaAnalytics {
        val initialInstance = amplitudeInstances[0]
        initialInstance.regenerateDeviceId()

        amplitudeInstances.forEach {
            it.deviceId = initialInstance.deviceId
        }
        return this
    }

    override fun uploadEvents() {
        amplitudeInstances.forEach {
            it.uploadEvents()
        }
    }

    override fun setDeviceIdCallback(callback: AmplitudeDeviceIdCallback?): PaltaAnalytics {
        amplitudeInstances.forEach {
            it.setDeviceIdCallback(callback)
        }
        return this
    }

    override fun setPlan(plan: Plan?): PaltaAnalytics {
        amplitudeInstances.forEach {
            it.setPlan(plan)
        }
        return this
    }

    fun initialize(context: Context, apiKey: String) {
        CoroutineScope(Dispatchers.Main).launch {
            var targets: List<Target>? = null
            runCatching {
                targets = NetworkClient().getTargetsAsync(apiKey)
            }.onFailure {
                // load default targets
                val defaultConfig = context.assets.readAssetsFile("defaultconfig.json")
                targets = Gson().fromJson(defaultConfig, TargetList::class.java).targets
            }
            initializeTargets(context, apiKey, targets)
        }
    }

    private fun initializeTargets(context: Context, apiKey: String, targets: List<Target>?) {
        targets?.let {
            it.forEach { target ->
                instance.addTarget(context, apiKey, target)
            }
        }
    }

    /**
     * Add configuration for new Apmlitude instance
     *
     * @param context Android context
     * @param target amplitude instance configuration
     */

    fun addTarget(context: Context, apiKey: String, target: Target) {
        if (targets.contains(target)) {
            return
        }

        val amplitudeInstance = Amplitude.getInstance(target.name)
        val targetSettings = target.settings

        amplitudeInstance.initialize(context, apiKey)
        amplitudeInstance.trackSessionEvents(targetSettings.trackingSessionEvents)
            .setEventUploadThreshold(targetSettings.eventUploadThreshold)
            .setEventUploadMaxBatchSize(targetSettings.eventUploadMaxBatchSize)
            .setEventMaxCount(targetSettings.eventMaxCount)
            .setEventUploadPeriodMillis(
                TimeUnit.SECONDS.toMillis(targetSettings.eventUploadPeriodSeconds.toLong()).toInt()
            )

        target.url?.let {
            amplitudeInstance.setServerUrl(it)
        }

        amplitudeInstances.add(amplitudeInstance)
    }
}