package liyihuan.app.android.lazyfragment.baselazy

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.scwang.smartrefresh.layout.api.RefreshHeader
import com.scwang.smartrefresh.layout.header.ClassicsHeader
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

    private val preLoadNumber: Int = 1

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
    }

    /**
     * 进入加载
     */
    override fun onFragmentResume() {
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