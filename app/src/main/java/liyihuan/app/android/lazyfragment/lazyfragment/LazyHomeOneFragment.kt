package liyihuan.app.android.lazyfragment.lazyfragment

import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import kotlinx.android.synthetic.main.fragment_lazy_home.*
import kotlinx.coroutines.*
import liyihuan.app.android.lazyfragment.LazyAdapter
import liyihuan.app.android.lazyfragment.LazyBean
import liyihuan.app.android.lazyfragment.R
import liyihuan.app.android.lazyfragment.baselazy.LazyRecyclerFragment
import liyihuan.app.android.lazyfragment.refresh.SmartRecyclerView

/**
 * @ClassName: LazyHomeOneFragment
 * @Description: java类作用描述
 * @Author: liyihuan
 * @Date: 2021/6/16 20:54
 */
class LazyHomeOneFragment : LazyRecyclerFragment<LazyBean>() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_lazy_home
    }


    override val mSmartRecycler: SmartRecyclerView by lazy {
        smartRecyclerView
    }

    override val adapter: BaseQuickAdapter<LazyBean, *> by lazy {
        LazyAdapter()
    }

    override val layoutManager: RecyclerView.LayoutManager by lazy {
        GridLayoutManager(context, 1)
    }

    override val fetcherFuc: (page: Int) -> Unit = {
        loadData()
    }

    private val list by lazy {
        arrayListOf(
            LazyBean("HomeViewPagerAdapter - 1"),
            LazyBean("HomeViewPagerAdapter - 2"),
            LazyBean("HomeViewPagerAdapter - 3"),
            LazyBean("HomeViewPagerAdapter - 4"),
            LazyBean("HomeViewPagerAdapter - 5"),
            LazyBean("HomeViewPagerAdapter - 6"),
            LazyBean("HomeViewPagerAdapter - 7")

        )
    }

    /**
     * 假装请求了接口，耗时500ms
     */
    private fun loadData() {
        GlobalScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                delay(500)
            }
            mSmartRecycler.onFetchDataFinish(list)
        }
    }




    override val getTagName: String
        get() = "LazyHomeOneFragment"
}