package xyz.nokt.btf.playingwithmaps

import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi

class MapsJob: JobService() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onStopJob(params: JobParameters?): Boolean {
        return false
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onStartJob(params: JobParameters?): Boolean {
        val conManager = baseContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (conManager != null)
        {
            val netCapabilities = conManager.getNetworkCapabilities(conManager.activeNetwork)
            if (netCapabilities != null)
            {
                when {
                    netCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        Log.i("InternetCon", "Connected to Cellular Network")
                        return true
                    }
                    netCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        Log.i("InternetCon", "Connected to WIFI Network")
                        return true
                    }
                    netCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        Log.i("InternetCon", "Connected to ETHERNET")
                        return true
                    }
                }
            }
        }
        return false
    }
}