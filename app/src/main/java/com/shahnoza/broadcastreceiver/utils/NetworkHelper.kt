package com.shahnoza.broadcastreceiver.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class NetworkHelper constructor(private val context: Context) {

fun isNetworConnected():Boolean{
var result = false
val connectivityManager=context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N){
    val network = connectivityManager.activeNetwork?:return false
    val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?:return false
    result=when{
        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)->true
        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)->true
        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)->true
        else->false
    }
}
else{
    connectivityManager.run {
        connectivityManager.activeNetworkInfo?.run {
            result=when(type){
                ConnectivityManager.TYPE_WIFI->true // wifi
                ConnectivityManager.TYPE_MOBILE->true //  mobil internet
                ConnectivityManager.TYPE_ETHERNET->true // simli internet
                else->true
            }
        }
    }
}
    return result
}

}