package com.paltabrain.billing.rc

import com.paltabrain.billing.BillingContext
import com.paltabrain.billing.interfaces.Billing
import com.paltabrain.billing.interfaces.PurchaseCallback
import com.paltabrain.billing.interfaces.ReceiveCustomerInfoCallback
import com.paltabrain.billing.purchases.Offerings
import com.paltabrain.billing.purchases.models.CustomerInfo
import com.paltabrain.billing.purchases.models.PurchasesError
import com.paltabrain.billing.purchases.models.StoreProduct
import com.paltabrain.billing.rc.mappers.*
import com.revenuecat.purchases.Purchases
import com.revenuecat.purchases.Store
import com.revenuecat.purchases.PurchasesConfiguration as RcPurchasesConfiguration
import com.revenuecat.purchases.getCustomerInfoWith
import com.revenuecat.purchases.getOfferingsWith
import com.revenuecat.purchases.CustomerInfo as RcCustomerInfo
import com.revenuecat.purchases.PurchasesError as RcPurchasesError
import com.revenuecat.purchases.interfaces.PurchaseCallback as RcPurchaseCallback
import com.revenuecat.purchases.interfaces.ReceiveCustomerInfoCallback as RcReceiveCustomerInfoCallback
import com.revenuecat.purchases.models.StoreTransaction as RcStoreTransaction

class RCBilling : Billing {

    private val customerInfoMapper = CustomerInfoMapper()
    private val purchasesErrorMapper = PurchasesErrorMapper()
    private val storeProductMapper = StoreProductMapper()
    private val storeTransactionMapper = StoreTransactionMapper()
    private val offeringMapper = OfferingMapper()

    fun attribution(
        attributes: Map<String, String>
    ) {
        Purchases.sharedInstance.setAttributes(attributes)
    }

    fun config(
        revenueCatConfig: PurchasesConfiguration
    ) {
        Purchases.debugLogsEnabled = revenueCatConfig.debugLogsEnabled
        val config = RcPurchasesConfiguration.Builder(
            context = revenueCatConfig.applicationContext.applicationContext,
            apiKey = revenueCatConfig.apiKey,
        )
            .appUserID(revenueCatConfig.appUserId)
            .observerMode(revenueCatConfig.observerMode)
            .build()
        Purchases.configure(config)
    }

    override val appUserID: String
        get() = Purchases.sharedInstance.appUserID

    @Deprecated("Deprecated in RevenueCat")
    override var allowSharingPlayStoreAccount: Boolean
        get() = Purchases.sharedInstance.allowSharingPlayStoreAccount
        set(value) {
            Purchases.sharedInstance.allowSharingPlayStoreAccount = value
        }

    override fun restorePurchases(callback: ReceiveCustomerInfoCallback) {
        val wrapper = object : RcReceiveCustomerInfoCallback {
            override fun onError(error: RcPurchasesError) {
                callback.onError(error = purchasesErrorMapper.mapToData(error))
            }

            override fun onReceived(customerInfo: RcCustomerInfo) {
                val info = customerInfoMapper.mapToData(customerInfo)
                callback.onReceived(info)
            }
        }
        Purchases.sharedInstance.restorePurchases(wrapper)
    }

    override fun getPurchaserInfoWith(
        onError: (error: PurchasesError) -> Unit,
        onSuccess: (customerInfo: CustomerInfo) -> Unit,
    ) {
        Purchases.sharedInstance.getCustomerInfoWith(
            onError = { onError(it.let(purchasesErrorMapper::mapToData)) },
            onSuccess = { onSuccess(it.let(customerInfoMapper::mapToData)) },
        )
    }

    override fun getOfferingsWith(
        onError: (error: PurchasesError) -> Unit,
        onSuccess: (offerings: Offerings) -> Unit,
    ) {
        Purchases.sharedInstance.getOfferingsWith(
            onError = {
                onError(it.let(purchasesErrorMapper::mapToData))
            },
            onSuccess = {
                onSuccess(
                    Offerings(
                        current = it.current?.let(offeringMapper::mapToData),
                        all = it.all.mapValues { offering -> offeringMapper.mapToData(offering.value) }
                    )
                )
            },
        )
    }

    override fun purchaseProduct(
        context: BillingContext,
        storeProduct: StoreProduct,
        callback: PurchaseCallback,
    ) {
        val activity = (context as RcBillingContext).activity
        val storeProductWrapper = storeProduct.let(storeProductMapper::mapToEntity)
        val callbackWrapper = object : RcPurchaseCallback {
            override fun onCompleted(storeTransaction: RcStoreTransaction, customerInfo: RcCustomerInfo) {
                callback.onCompleted(
                    storeTransaction = storeTransaction.let(storeTransactionMapper::mapToData),
                    customerInfo = customerInfo.let(customerInfoMapper::mapToData)
                )
            }

            override fun onError(error: RcPurchasesError, userCancelled: Boolean) {
                callback.onError(
                    error = error.let(purchasesErrorMapper::mapToData),
                    userCancelled = userCancelled,
                )
            }
        }
        Purchases.sharedInstance.purchaseProduct(
            activity = activity,
            storeProduct = storeProductWrapper,
            callback = callbackWrapper,
        )
    }
}
