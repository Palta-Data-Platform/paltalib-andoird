package com.paltabrain.billing.purchases.models

import org.json.JSONObject


data class SkuDetails(
    private val original: String,
) {
    private val jsonObject = JSONObject(original)

    fun getIntroductoryPriceCycles(): Int {
        return this.jsonObject.optInt("introductoryPriceCycles")
    }

    fun getIntroductoryPriceAmountMicros(): Long {
        return this.jsonObject.optLong("introductoryPriceAmountMicros")
    }

    fun getOriginalPriceAmountMicros(): Long {
        return if (this.jsonObject.has("original_price_micros")) {
            this.jsonObject.optLong("original_price_micros")
        } else {
            getPriceAmountMicros()
        }
    }

    fun getPriceAmountMicros(): Long {
        return this.jsonObject.optLong("price_amount_micros")
    }

    fun getDescription(): String {
        return this.jsonObject.optString("description")
    }

    fun getFreeTrialPeriod(): String {
        return this.jsonObject.optString("freeTrialPeriod")
    }

    fun getIconUrl(): String {
        return this.jsonObject.optString("iconUrl")
    }

    fun getIntroductoryPrice(): String {
        return this.jsonObject.optString("introductoryPrice")
    }

    fun getIntroductoryPricePeriod(): String {
        return this.jsonObject.optString("introductoryPricePeriod")
    }

    fun getOriginalJson(): String {
        return this.original
    }

    fun getOriginalPrice(): String {
        return if (this.jsonObject.has("original_price")) this.jsonObject.optString("original_price") else getPrice()
    }

    fun getPrice(): String {
        return this.jsonObject.optString("price")
    }

    fun getPriceCurrencyCode(): String {
        return this.jsonObject.optString("price_currency_code")
    }

    fun getSku(): String {
        return this.jsonObject.optString("productId")
    }

    fun getSubscriptionPeriod(): String {
        return this.jsonObject.optString("subscriptionPeriod")
    }

    fun getTitle(): String {
        return this.jsonObject.optString("title")
    }

    fun getType(): String {
        return this.jsonObject.optString("type")
    }
}
