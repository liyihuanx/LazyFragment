package liyihuan.app.android.lazyfragment.refresh

import android.view.View

/**
 * @ClassName: IEmptyView
 * @Description: java类作用描述
 * @Author: liyihuan
 * @Date: 2021/6/8 21:29
 */
interface IEmptyView {

    companion object {
        /**
         * 隐藏
         */
        const val HIDE_LAYOUT = 3

        /**
         * 网络异常
         */
        const val NETWORK_ERROR = 1

        /**
         * 服务器数据空
         */
        const val NODATA = 2
    }
    fun getContentView(): View?
    fun setErrorType(type: Int)
}