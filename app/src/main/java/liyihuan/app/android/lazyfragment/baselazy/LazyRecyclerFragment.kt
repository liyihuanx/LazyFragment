package liyihuan.app.android.lazyfragment.baselazy

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.scwang.smartrefresh.layout.api.RefreshFooter
import com.scwang.smartrefresh.layout.api.RefreshHeader
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import liyihuan.app.android.lazyfragment.manager.LazyManager
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
abstract class LazyRecyclerFragment<T> : LazyFragment() {


    val halfPageSize = 1

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



    override fun initView() {
        // 把fragment保存
        LazyManager.register(getTagName,this)

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
                    val status = LazyManager.getStatus(this@LazyRecyclerFragment)
                    if (position >= halfPageSize && status.inTop) {
                        status.inTop = false
                    }
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
            }
        })
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