package liyihuan.app.android.lazyfragment

import android.app.Application

/**
 * @ClassName: LazyApplication
 * @Description: java类作用描述
 * @Author: liyihuan
 * @Date: 2021/6/8 22:56
 */
class LazyApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        AppCache.setContext(this)
    }
}