package liyihuan.app.android.lazyfragment;

import android.content.Context
import android.util.DisplayMetrics
import androidx.recyclerview.widget.LinearSmoothScroller


/**
 * @ClassName: TopSmoothScroller
 * @Description: java类作用描述
 * @Author: liyihuan
 * @Date: 2021/6/8 21:58
 */
class TopSmoothScroller(context: Context?) :  LinearSmoothScroller(context){

    // TODO 以后可以修改成根据滑动的距离去调整上移的速度
    companion object {
        fun get() : TopSmoothScroller {
            return Holder.instance
        }
    }

    private object Holder{
        val instance = TopSmoothScroller(AppCache.getContext())
    }

    /**
     * 修改速度
     */
    override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics?): Float {
        return 5f / displayMetrics!!.densityDpi
    }

    override fun getHorizontalSnapPreference(): Int {
        return SNAP_TO_START
    }

    override fun getVerticalSnapPreference(): Int {
        return SNAP_TO_START
    }
}