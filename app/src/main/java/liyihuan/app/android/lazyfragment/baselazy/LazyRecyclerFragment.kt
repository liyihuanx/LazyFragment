package liyihuan.app.android.lazyfragment.baselazy

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.scwang.smartrefresh.layout.api.RefreshFooter
import com.scwang.smartrefresh.layout.api.RefreshHeader
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import liyihuan.app.android.lazyfragment.TopSmoothScroller
import liyihuan.app.android.lazyfragment.manager.LazyStatus
import liyihuan.app.android.lazyfragment.refresh.CommonEmptyView
import liyihuan.app.android.lazyfragment.refresh.IEmptyView
import liyihuan.app.android.lazyfragment.refresh.SmartRecyclerView

/**
 * @ClassName: LazyRecyclerView
 * @Description: java类作用描述
 * @Author: liyihuan
 * @Date: 2021/6/8 21:16
 */
abstract class LazyRecyclerFragment<T> : BaseLazyFragment() {


    private val halfPageSize = 1

    lateinit var lazyStatus: LazyStatus

    /**
     * 通用emptyView
     */
    open fun getEmptyView(): IEmptyView? {
        return CommonEmptyView(context)
    }

    open fun setRefreshHeader(): RefreshHeader {
        return ClassicsHeader(context)
    }

    open fun setRefreshFooter(): RefreshFooter {
        return ClassicsFooter(context)
    }

    abstract val mSmartRecycler: SmartRecyclerView

    abstract val adapter: BaseQuickAdapter<T, *>

    abstract val layoutManager: RecyclerView.LayoutManager

    /**
     * 刷新回调
     */
    abstract val fetcherFuc: ((page: Int) -> Unit)

    abstract val getTagName: String

    open var refreshTime = 60 * 60 * 1000L


    override fun initView() {
        lazyStatus = LazyStatus()

        // 把RecyclerView 和 SmartRefreshHelper 建立联系
        mSmartRecycler.recyclerView.layoutManager = layoutManager
        mSmartRecycler.setRefreshHeader(setRefreshHeader())
        mSmartRecycler.setRefreshFooter(setRefreshFooter())

        mSmartRecycler
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
        mSmartRecycler.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                // 获取第一页数据的最后一个view
                val view = recyclerView.layoutManager!!.getChildAt(0)
                if (view != null) {
                    // 获取这个view的下标
                    val position = recyclerView.layoutManager!!.getPosition(view)
                    if (position >= halfPageSize && lazyStatus.inTop) {
                        lazyStatus.inTop = false
                    }
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }


    private fun smoothScrollToTop() {
        TopSmoothScroller.get().targetPosition = 0
        mSmartRecycler.recyclerView.layoutManager!!.startSmoothScroll(TopSmoothScroller.get())
        lazyStatus.inTop = true
        mSmartRecycler.smartRefreshHelper.refresh()
    }

    override fun onFragmentFirstVisible() {
        super.onFragmentFirstVisible()
    }


    /**
     * 进入加载
     *   1. 第一次打开该Fragment
     *   2. 距离上一次切到该Fragment时间比较久
     *   3. 连续点击两次该Fragment
     *   4. 从另一个activity回来
     */
    override fun onFragmentResume() {
        if (lazyStatus.clickTime == 0L) {
            Log.d("QWER", "$getTagName : 第一次打开该Fragment - 加载数据")
            mSmartRecycler.startRefresh()
            lazyStatus.clickTime = System.currentTimeMillis()
        } else {
            val currentTimeMillis = System.currentTimeMillis()
            val defTime = currentTimeMillis - lazyStatus.clickTime
            lazyStatus.clickTime = currentTimeMillis
            if (lazyStatus.clickTime != 0L && defTime >= refreshTime) {
                Log.d("QWER", "$getTagName : 距离上一次切到该Fragment时间比较久 - 加载数据")
                smoothScrollToTop()
            } else {
                Log.d("QWER", "$getTagName : 距离上一次切到该Fragment时间短 - 不加载数据")
            }
        }
    }


    fun doubleClickRefresh() {
        Log.d("QWER", "$getTagName : 连续点击两次该Fragment - 加载数据")
        if (!lazyStatus.inTop) {
            smoothScrollToTop()
        } else {
            mSmartRecycler.startRefresh()
        }
    }


    /**
     * 离开停止刷新
     */
    override fun onFragmentPause() {
        super.onFragmentPause()
        Log.d("QWER", "$getTagName : 离开 - 停止加载")
        // 离开时，清除选中状态
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