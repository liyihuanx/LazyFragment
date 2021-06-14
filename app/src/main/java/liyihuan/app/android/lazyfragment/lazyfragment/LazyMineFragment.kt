package liyihuan.app.android.lazyfragment.lazyfragment

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import kotlinx.android.synthetic.main.fragment_mine.*
import kotlinx.coroutines.*
import liyihuan.app.android.lazyfragment.LazyAdapter
import liyihuan.app.android.lazyfragment.LazyBean
import liyihuan.app.android.lazyfragment.R
import liyihuan.app.android.lazyfragment.baselazy.LazyRecyclerFragment
import liyihuan.app.android.lazyfragment.manager.LazyManager
import liyihuan.app.android.lazyfragment.refresh.SmartRecyclerView

/**
 * @ClassName: LazyMineFragment
 * @Description: java类作用描述
 * @Author: liyihuan
 * @Date: 2021/6/9 23:03
 */
class LazyMineFragment : LazyRecyclerFragment<LazyBean>() {
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
            LazyBean("LazyMineFragment - 1"),
            LazyBean("LazyMineFragment - 2"),
            LazyBean("LazyMineFragment - 3"),
            LazyBean("LazyMineFragment - 4"),
            LazyBean("LazyMineFragment - 5"),
            LazyBean("LazyMineFragment - 6"),
            LazyBean("LazyMineFragment - 7"),
            LazyBean("LazyMineFragment - 8"),
            LazyBean("LazyMineFragment - 9"),
            LazyBean("LazyMineFragment - 10"),
            LazyBean("LazyMineFragment - 11"),
            LazyBean("LazyMineFragment - 12"),
            LazyBean("LazyMineFragment - 13"),
            LazyBean("LazyMineFragment - 14"),
            LazyBean("LazyMineFragment - 15"),
            LazyBean("LazyMineFragment - 16"),
            LazyBean("LazyMineFragment - 17")
        )
    }

    private fun loadData() {
        GlobalScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                delay(500)
            }
            mSmartRecycler.onFetchDataFinish(list)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_mine
    }

    override fun onFragmentPause() {
        super.onFragmentPause()
        Log.d("QWER", "LazyMineFragment: 停止加载")
    }

    override fun onFragmentResume() {
        super.onFragmentResume()
        Log.d("QWER", "LazyMineFragment: 加载数据")
    }

    override fun onResume() {
        super.onResume()
        Log.d("QWER", "LazyMineFragment: onResume")
    }

    override val getTagName: String
        get() = "LazyMineFragment"

}