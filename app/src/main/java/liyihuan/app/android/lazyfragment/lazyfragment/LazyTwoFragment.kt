package liyihuan.app.android.lazyfragment.lazyfragment

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import kotlinx.android.synthetic.main.fragment_two.*
import kotlinx.coroutines.*
import liyihuan.app.android.lazyfragment.LazyAdapter
import liyihuan.app.android.lazyfragment.LazyBean
import liyihuan.app.android.lazyfragment.R
import liyihuan.app.android.lazyfragment.baselazy.LazyRecyclerFragment
import liyihuan.app.android.lazyfragment.manager.LazyManager
import liyihuan.app.android.lazyfragment.refresh.SmartRecyclerView

/**
 * @ClassName: LazyTwoFragment
 * @Description: java类作用描述
 * @Author: liyihuan
 * @Date: 2021/6/9 23:00
 */
class LazyTwoFragment : LazyRecyclerFragment<LazyBean>() {

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
            LazyBean("LazyTwoFragment - 1"),
            LazyBean("LazyTwoFragment - 2"),
            LazyBean("LazyTwoFragment - 3"),
            LazyBean("LazyTwoFragment - 4"),
            LazyBean("LazyTwoFragment - 5"),
            LazyBean("LazyTwoFragment - 6"),
            LazyBean("LazyTwoFragment - 7"),
            LazyBean("LazyTwoFragment - 8"),
            LazyBean("LazyTwoFragment - 9"),
            LazyBean("LazyTwoFragment - 10"),
            LazyBean("LazyTwoFragment - 11"),
            LazyBean("LazyTwoFragment - 12"),
            LazyBean("LazyTwoFragment - 13"),
            LazyBean("LazyTwoFragment - 14"),
            LazyBean("LazyTwoFragment - 15"),
            LazyBean("LazyTwoFragment - 16"),
            LazyBean("LazyTwoFragment - 17")
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
        return R.layout.fragment_two
    }

    override fun onFragmentPause() {
        super.onFragmentPause()
        Log.d("QWER", "LazyTwoFragment: 停止加载")
    }

    override fun onFragmentResume() {
        super.onFragmentResume()
        Log.d("QWER", "LazyTwoFragment: 加载数据")
    }

    override val getTagName: String
        get() = "LazyTwoFragment"


}