package liyihuan.app.android.lazyfragment.baselazy

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.scwang.smartrefresh.layout.api.RefreshHeader
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import liyihuan.app.android.lazyfragment.refresh.CommonEmptyView
import liyihuan.app.android.lazyfragment.refresh.IEmptyView
import liyihuan.app.android.lazyfragment.refresh.SmartRecyclerView
import java.util.concurrent.TimeUnit

/**
 * @ClassName: LazyRecyclerView
 * @Description: java类作用描述
 * @Author: liyihuan
 * @Date: 2021/6/8 21:16
 */
abstract class LazyRecyclerFragment<T> : LazyFragment() {

    /**
     * 通用emptyView
     */
    open fun getEmptyView(): IEmptyView? {
        return CommonEmptyView(context)
    }

    open fun setRefreshHeader(): RefreshHeader {
        return ClassicsHeader(context)
    }

    abstract val mSmartRecycler: SmartRecyclerView


    abstract val adapter: BaseQuickAdapter<T, *>

    abstract val layoutManager: RecyclerView.LayoutManager

    /**
     * 刷新回调
     */
    abstract val fetcherFuc: ((page: Int) -> Unit)


    /**
     *  把RecyclerView 和 SmartRefreshHelper 建立联系
     */
    override fun initView() {
        mSmartRecycler.recyclerView.layoutManager = layoutManager
        mSmartRecycler.setReFreshHeader(setRefreshHeader())
        mSmartRecycler.setUp(
            adapter,
            getEmptyView(),
            loadMoreNeed(),
            refreshNeed(),
            fetcherFuc
        )
        /**
         * { #SCROLL_STATE_IDLE = 0 },
         * { #SCROLL_STATE_DRAGGING = 1}
         * { #SCROLL_STATE_SETTLING = 2}.
         */
//        mSmartRecycler.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                super.onScrollStateChanged(recyclerView, newState)
//                // 获取第一个view
//                val view = recyclerView.layoutManager!!.getChildAt(0)
//                if (view != null) {
//                    // 获取这个view的下标
//                    val position = recyclerView.layoutManager!!.getPosition(view)
//
//                }
//            }
//
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//            }
//        })
    }

    /**
     * 进入加载
     */
    override fun onFragmentResume() {
        // 1.左右切换，是第一页数据的时候才重新加载数据
//        if (mSmartRecycler.smartRefreshHelper.currentPage == 0) {
//        }
        mSmartRecycler.startRefresh()
    }

    /**
     * 离开停止刷新
     */
    override fun onFragmentPause() {
        super.onFragmentPause()
        mSmartRecycler.pauseRefresh()
    }


    open fun loadMoreNeed(): Boolean {
        return true
    }

    open fun refreshNeed(): Boolean {
        return true
    }

    open fun isRefreshAtOnStart(): Boolean {
        return true
    }
}