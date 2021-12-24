package com.paltabrain.attribution

interface PaltaAttributionDelegate {

    fun onReceiveUserID(userID: String?)
    fun onConversionDataSuccess(data: MutableMap<String, Any>?)
    fun onConversionDataFail(error: String?)
    fun onAppOpenAttribution(data: MutableMap<String, String>?)
    fun onAttributionFailure(error: String?)

}
