package liyihuan.app.android.lazyfragment;

import android.content.Context
import androidx.recyclerview.widget.LinearSmoothScroller


class TopSmoothScroller(context: Context?) :  LinearSmoothScroller(context){
    // 使用方法


    companion object {
        fun get() : TopSmoothScroller {
            return Holder.instance
        }
    }

    private object Holder{
        val instance = TopSmoothScroller(AppCache.getContext())
    }

    override fun getHorizontalSnapPreference(): Int {
        return SNAP_TO_START
    }

    override fun getVerticalSnapPreference(): Int {
        return SNAP_TO_START
    }
}