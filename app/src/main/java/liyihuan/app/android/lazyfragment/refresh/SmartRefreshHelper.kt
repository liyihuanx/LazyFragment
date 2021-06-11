package liyihuan.app.android.lazyfragment.refresh

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.loadmore.SimpleLoadMoreView
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import liyihuan.app.android.lazyfragment.LazyBean
import liyihuan.app.android.lazyfragment.refresh.IEmptyView.Companion.HIDE_LAYOUT
import liyihuan.app.android.lazyfragment.refresh.IEmptyView.Companion.NETWORK_ERROR
import liyihuan.app.android.lazyfragment.refresh.IEmptyView.Companion.NODATA

/**
 * @ClassName: SmartRefreshHelper
 * @Description: 上拉下拉帮助类
 * @Author: liyihuan
 * @Date: 2021/6/8 21:27
 */
open class SmartRefreshHelper<T>(
    private val adapter: BaseQuickAdapter<T, *>,
    private val recycler_view: RecyclerView,
    private val refresh_layout: SmartRefreshLayout,
    private val emptyCustomView: IEmptyView?,

    private val isNeedLoadMore: Boolean = true,
    private val isNeedRefresh: Boolean = true,
    /**
     * 刷新回调
     */
    private val fetcherFuc: (page: Int) -> Unit
) {

    /**
     * 1.无网络，无缓存 --> 加载空视图
     * 2.无网络，有缓存 --> 加载缓存
     * 3.有网络，无缓存 --> 请求接口
     * 4.有网络，有缓存 --> 根据情况返回接口数据或是缓存
     */

    private var isLoadMoreing: Boolean = false
    private var isRefreshing: Boolean = false
    var currentPage = 0

    private var hasCache: Boolean = false

    private var cacheData = ArrayList<T>()

    init {
        if (isNeedRefresh) {
            refresh_layout.setOnRefreshListener {
                refresh(false)
            }
        }

        if (isNeedLoadMore) {
            refresh_layout.setOnLoadMoreListener {
                loadMore()
            }
        }
    }

    fun onFetchDataFinish(data: List<T>?) {
        if (data != null && data.isNotEmpty()) {
            // 如果在加载中
            if (isLoadMoreing) {
                // 页数加一
                currentPage++
                adapter.addData(data)
                refresh_layout.finishLoadMore(true)

            } else if (isRefreshing) {
                adapter.setNewData(data)
                /** 假装有缓存数据 **/
                cacheData.clear()
                cacheData.addAll(data)
                hasCache = true
                /** 假装有缓存数据 **/

                refresh_layout.finishRefresh()
            }

        } else {
            // 如果没有数据
            if (currentPage == 0) {
                refresh_layout.finishRefresh(false)
                // 设置空状态
                refreshEmptyView(NODATA)
            } else {
                refresh_layout.finishLoadMore(false)
            }
        }
        isLoadMoreing = false
        isRefreshing = false
    }


    /**
     * 加载数据失败
     */
    fun onFetchDataError() {
        if (isRefreshing) {
            refresh_layout.finishRefresh(false)
        } else {
            adapter.loadMoreFail()
        }
        val disconnected = !NetUtil.isNetworkAvailable(recycler_view.context)
        if (disconnected) {
            refreshEmptyView(NETWORK_ERROR)
        } else {
            refreshEmptyView(NODATA)
        }
        isLoadMoreing = false
        isRefreshing = false
    }

    /**
     * 刷新空视图状态
     */
    private fun refreshEmptyView(type: Int) {
        if (adapter.data.isEmpty() && recycler_view.childCount == 0) {
            emptyCustomView?.setErrorType(type)
        } else {
            emptyCustomView?.setErrorType(HIDE_LAYOUT)
        }
    }

    /**
     * 加载数据
     */
    private fun loadMore() {
        if (isRefreshing || isLoadMoreing) {
            return
        }
        isLoadMoreing = true
        fetcherFuc(currentPage + 1)
    }

    /**
     * 刷新数据
     */
    fun refresh(isNeedCache: Boolean = true) {
        // 如果在刷新 或者 在加载
        if (isRefreshing || isLoadMoreing) {
            return
        }
        // 判断缓存
        if (hasCache && isNeedCache) {
            // 加载缓存数据
            loadCacheData()
            return
        }

        // 判断网络
        val connectedStatus = NetUtil.isNetworkAvailable(recycler_view.context)
        if (!connectedStatus) {
            refreshEmptyView(NETWORK_ERROR)
            return
        }
        refresh_layout.autoRefresh()
        // 没有跳出方法，这符合条件可以刷新
        isRefreshing = true
        // 当前页数重置
        currentPage = 0
        // 请求接口请求第一页
        fetcherFuc(0)
    }

    private fun loadCacheData() {
        Log.d("QWER", "loadCacheData: ")
    }

    fun pauseRefresh() {
        if (isRefreshing) {
            refresh_layout.finishRefresh()
        }
    }
}