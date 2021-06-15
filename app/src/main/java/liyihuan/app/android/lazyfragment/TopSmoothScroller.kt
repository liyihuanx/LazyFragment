package liyihuan.app.android.lazyfragment;

import android.content.Context
import android.util.DisplayMetrics
import androidx.recyclerview.widget.LinearSmoothScroller


class TopSmoothScroller(context: Context?) :  LinearSmoothScroller(context){

    companion object {
        fun get() : TopSmoothScroller {
            return Holder.instance
        }
    }

    private object Holder{
        val instance = TopSmoothScroller(AppCache.getContext())
    }

    override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics?): Float {
        return 1f / displayMetrics!!.densityDpi
    }

    override fun getHorizontalSnapPreference(): Int {
        return SNAP_TO_START
    }

    override fun getVerticalSnapPreference(): Int {
        return SNAP_TO_START
    }
}