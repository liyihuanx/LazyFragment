package liyihuan.app.android.lazyfragment.refresh

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

/**
 * @ClassName: NetUtil
 * @Description: java类作用描述
 * @Author: liyihuan
 * @Date: 2021/6/8 21:29
 */
object NetUtil {
    /**
     * 检查是否有网络
     */
    fun isNetworkAvailable(context: Context): Boolean {
        val info = getNetworkInfo(context)
        return info?.isAvailable ?: false
    }

    /**
     * 获取当前网络类型
     * @param context
     * @return
     */
    fun getNetworkType(context: Context): String {
        try {
            val manager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            @SuppressLint("MissingPermission") val networkInfo =
                manager.activeNetworkInfo
            val type = networkInfo!!.type
            val typeName = networkInfo!!.typeName
            val extraInfo = networkInfo!!.extraInfo
            return if (type == ConnectivityManager.TYPE_WIFI) {
                typeName
            } else {
                extraInfo
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }

    /**
     * 判断当前网络是否是wifi网络.
     *
     * @param context the context
     * @return boolean
     */
    fun isWifiOpen(context: Context): Boolean {
        val activeNetInfo = getNetworkInfo(context)
        return activeNetInfo != null && activeNetInfo.type == ConnectivityManager.TYPE_WIFI
    }

    /**
     * 检查是否连接上WIFI （不一定联网）
     */
    fun isWifiConnect(context: Context): Boolean {
        val info = getNetworkInfo(context)
        if (info != null) {
            if (info.type == ConnectivityManager.TYPE_WIFI && info.isConnected) {
                return true
            }
        }
        return false
    }

    /**
     * 判断wifi是否可用.
     *
     * @param context the context
     * @return true, if is wifi enabled
     */
    fun isWifiEnabled(context: Context): Boolean {
        val activeNetInfo = getNetworkInfo(context)
        return activeNetInfo != null && activeNetInfo.type == ConnectivityManager.TYPE_WIFI && activeNetInfo.isAvailable
    }

    /**
     * 判断当前网络是否是3G/2G移动网络.
     *
     * @param context the context
     * @return boolean
     */
    fun isMobile(context: Context): Boolean {
        val activeNetInfo = getNetworkInfo(context)
        return activeNetInfo != null && activeNetInfo.type == ConnectivityManager.TYPE_MOBILE
    }

    /**
     * 获取当前网络信息.
     *
     * @param context the context
     * @return NetworkInfo
     */
    @SuppressLint("MissingPermission")
    fun getNetworkInfo(context: Context): NetworkInfo? {
        val connectivityManager = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo
    }
}