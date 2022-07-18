package com.paltabrain

object Dependencies {

    object Build {
        const val compileSdk = 32
        const val minSdk = 23
        const val targetSdk = 32
    }

    object Android {
        const val coreKtx = "androidx.core:core-ktx:${Version.coreKtx}"
        const val activityCompose = "androidx.activity:activity-compose:${Version.activityCompose}"
        const val material3 = "androidx.compose.material3:material3:${Version.material3}"

        const val composeUi = "androidx.compose.ui:ui:${Version.composeVersion}"
        const val composeUiTooling = "androidx.compose.ui:ui-tooling:${Version.composeVersion}"
        const val composeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview:${Version.composeVersion}"
        const val composeUiTestManifest = "androidx.compose.ui:ui-test-manifest:${Version.composeVersion}"

        const val lifecycleCommon = "androidx.lifecycle:lifecycle-common:${Version.lifecycleCommon}"
        const val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Version.lifecycleCommon}"
        const val lifecycleViewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.lifecycleCommon}"
        const val lifecycleViewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:${Version.lifecycleCommon}"

        const val kotlinCoroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.kotlinCoroutinesCore}"
        const val kotlinCoroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.kotlinCoroutinesCore}"

        const val billingKtx = "com.android.billingclient:billing-ktx:${Version.billingKtx}"

        const val json = "org.json:json:${Version.json}"
    }

    object Version {
        const val activityCompose = "1.4.0"
        const val coreKtx = "1.8.0"
        const val kotlinCoroutinesCore = "1.6.1"
        const val lifecycleCommon = "2.4.1"
        const val material3 = "1.0.0-alpha13"

//        const val billingKtx = "5.0.0"
        const val billingKtx = "4.1.0"
        const val json = "20220320"
        const val composeVersion = "1.1.1"
    }

}