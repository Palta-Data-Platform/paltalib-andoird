package com.paltabrain.purchases.demo.ui.screens.info

import androidx.lifecycle.ViewModel
import com.paltabrain.billing.data.Purchase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class InfoViewModel : ViewModel() {

    private val _purchases = MutableStateFlow<List<Purchase>>(emptyList())

    val purchases: StateFlow<List<Purchase>> = _purchases.asStateFlow()

    fun updateClicked() {

    }

    fun buySomethingsClicked() {

    }
}