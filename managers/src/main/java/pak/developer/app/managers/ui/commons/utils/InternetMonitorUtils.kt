package pak.developer.app.managers.ui.commons.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest

class InternetMonitorUtils(private val listener: OnInternetConnectivityListener) {
    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            listener.onInternetAvailable()
            super.onAvailable(network)
        }

        override fun onLost(network: Network) {
            listener.onInternetLost()
            super.onLost(network)
        }

        override fun onUnavailable() {
            listener.onInternetLost()
            super.onUnavailable()
        }
    }


    fun registerNetwork(context: Context) {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkRequest = NetworkRequest.Builder().build()
        connectivityManager.requestNetwork(networkRequest, networkCallback, 1000)
        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)
    }

    fun unregisterNetwork(context: Context) {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }


    interface OnInternetConnectivityListener {
        fun onInternetAvailable()
        fun onInternetLost()
    }
}