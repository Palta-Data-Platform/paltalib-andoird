package com.paltabrain.billing

import android.content.Context
import com.android.billingclient.api.*
import com.paltabrain.billing.data.BillingResponseCode
import com.paltabrain.billing.data.BillingResult
import com.paltabrain.billing.data.ProductDetails
import com.paltabrain.billing.interfaces.Billing
import com.paltabrain.billing.interfaces.PurchaseCallback
import com.paltabrain.billing.interfaces.ReceiveCustomerInfoCallback
import com.paltabrain.billing.mappers.AndroidBillingProductDetailMapper
import com.paltabrain.billing.mappers.AndroidBillingResultMapper
import com.paltabrain.billing.mappers.AndroidPurchaseMapper
import com.paltabrain.billing.purchases.Offerings
import com.paltabrain.billing.purchases.models.CustomerInfo
import com.paltabrain.billing.purchases.models.PurchasesError
import com.paltabrain.billing.purchases.models.StoreProduct
import java.util.concurrent.atomic.AtomicInteger
import com.android.billingclient.api.BillingClient as AndroidBillingClient
import com.android.billingclient.api.BillingClientStateListener as AndroidBillingClientStateListener
import com.android.billingclient.api.BillingResult as AndroidBillingResult
//import com.android.billingclient.api.ProductDetailsResponseListener as AndroidProductDetailsResponseListener
import com.android.billingclient.api.PurchasesUpdatedListener as AndroidPurchasesUpdatedListener
//import com.android.billingclient.api.ProductDetails as AndroidProductDetails

class AndroidBilling(
    context: Context,
    private val purchasesUpdatedListener: PurchasesUpdatedListener,
    private val localStorage: LocalStorage = InMemoryLocalStorage()
) : Billing {

    private val applicationContext = context.applicationContext

    private val purchaseMapper = AndroidPurchaseMapper()
    private val billingResultMapper = AndroidBillingResultMapper()
    private val productDetailMapper = AndroidBillingProductDetailMapper()

    private val purchasesUpdatedWrapper = AndroidPurchasesUpdatedListener { androidBillingResult, androidPurchases ->
        val billingResult: BillingResult = androidBillingResult.let(billingResultMapper::map)
        val purchases = androidPurchases?.map(purchaseMapper::map)
        purchasesUpdatedListener.onPurchasesUpdated(billingResult, purchases)
        if (billingResult.responseCode == BillingResponseCode.OK) {
            localStorage.savePurchase(purchases.orEmpty())
        }
    }
    private val androidBillingClient: AndroidBillingClient = AndroidBillingClient.newBuilder(applicationContext)
        .setListener(purchasesUpdatedWrapper)
        .enablePendingPurchases() // Not used for subscriptions.
        .build()

    private val isReady: Boolean
        get() = androidBillingClient.isReady



    override val appUserID: String
        get() = ""

    override var allowSharingPlayStoreAccount: Boolean
        get() = false
        set(value) {

        }

    override fun restorePurchases(callback: ReceiveCustomerInfoCallback) {

    }

    override fun getOfferingsWith(onError: (error: PurchasesError) -> Unit, onSuccess: (offerings: Offerings) -> Unit) {

    }

    override fun getPurchaserInfoWith(onError: (error: PurchasesError) -> Unit, onSuccess: (customerInfo: CustomerInfo) -> Unit) {

    }

    override fun purchaseProduct(context: BillingContext, storeProduct: StoreProduct, callback: PurchaseCallback) {

    }


    private fun startConnection(callback: BillingClientStateListener) {
        val wrapperCallback = object : AndroidBillingClientStateListener {
            override fun onBillingServiceDisconnected() {
                callback.onBillingServiceDisconnected()
            }

            override fun onBillingSetupFinished(androidBillingResult: AndroidBillingResult) {
                val billingResult = androidBillingResult.let(billingResultMapper::map)
                callback.onBillingSetupFinished(billingResult)
            }
        }
        if (!androidBillingClient.isReady) {
            androidBillingClient.startConnection(wrapperCallback)
        }
    }

    private fun endConnection() {
        if (androidBillingClient.isReady) {
            androidBillingClient.endConnection()
        }
    }

    private fun queryProductDetails(products: List<Product>, callback: ProductDetailsResponseListener) {
//        val inAppProducts = products.filter { it.type == ProductType.INAPP }.map(::productMap)
//        val subProducts = products.filter { it.type == ProductType.SUBS }.map(::productMap)

//        val queryInAppProductDetailsParams = inAppProducts.takeIf { it.isNotEmpty() }
//            ?.let {
//                QueryProductDetailsParams.newBuilder()
//                    .setProductList(inAppProducts)
//                    .build()
//            }

//        val querySubsProductDetailsParams = subProducts.takeIf { it.isNotEmpty() }
//            ?.let {
//                QueryProductDetailsParams.newBuilder()
//                    .setProductList(subProducts)
//                    .build()
//            }
//        val counterCount = if (querySubsProductDetailsParams == null || queryInAppProductDetailsParams == null) {
//            1
//        } else 2
//        val counter = AtomicInteger(counterCount)
//        val allResultList = mutableListOf<ProductDetails>()

//        val callbackWrapper = AndroidProductDetailsResponseListener { androidResult, androidProducts ->
//            val result = billingResultMapper.map(androidResult)
//            val productsDetails = androidProducts.map(productDetailMapper::map)
//            allResultList.addAll(productsDetails)
//            if (counter.decrementAndGet() == 0) {
//                callback.onProductDetailsResponse(result, allResultList)
//                localStorage.saveProducts(allResultList)
//            }
//        }
//        queryInAppProductDetailsParams?.let { androidBillingClient.queryProductDetailsAsync(it, callbackWrapper) }
//        querySubsProductDetailsParams?.let { androidBillingClient.queryProductDetailsAsync(it, callbackWrapper) }
    }


//    private fun productMap(product: Product): QueryProductDetailsParams.Product {
//        return QueryProductDetailsParams.Product.newBuilder()
//            .setProductId(product.id)
//            .setProductType(
//                when (product.type) {
//                    ProductType.INAPP -> AndroidBillingClient.ProductType.INAPP
//                    ProductType.SUBS -> AndroidBillingClient.ProductType.SUBS
//                }
//            ).build()
//    }

    private fun buy(billingContext: BillingContext, productDetails: ProductDetails) {
        val androidBillingContext = billingContext as AndroidBillingContext
        productDetails as ProductDetails.InApp
//        val originalProductDetails: AndroidProductDetails = buildProductDetail(productDetails.originalJson)
//        val productDetailsParamsList = listOf(
//            BillingFlowParams.ProductDetailsParams.newBuilder()
//                // retrieve a value for "productDetails" by calling queryProductDetailsAsync()
//                .setProductDetails(originalProductDetails)
////                // to get an offer token, call ProductDetails.subscriptionOfferDetails()
////                // for a list of offers that are available to the user
////                .setOfferToken(originalProductDetails.subscriptionOfferDetails.first().offerToken)
//                .build()
//        )
//        val billingFlowParams = BillingFlowParams.newBuilder()
//            .setProductDetailsParamsList(productDetailsParamsList)
//            .build()
//        androidBillingClient.launchBillingFlow(androidBillingContext.activity, billingFlowParams)
    }
}