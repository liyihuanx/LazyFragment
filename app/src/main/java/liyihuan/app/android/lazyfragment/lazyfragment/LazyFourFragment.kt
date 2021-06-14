package liyihuan.app.android.lazyfragment.lazyfragment

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import kotlinx.android.synthetic.main.fragment_four.*
import kotlinx.coroutines.*
import liyihuan.app.android.lazyfragment.LazyAdapter
import liyihuan.app.android.lazyfragment.LazyBean
import liyihuan.app.android.lazyfragment.R
import liyihuan.app.android.lazyfragment.baselazy.LazyRecyclerFragment
import liyihuan.app.android.lazyfragment.manager.LazyManager
import liyihuan.app.android.lazyfragment.refresh.SmartRecyclerView

/**
 * @ClassName: LazyFourFragment
 * @Description: java类作用描述
 * @Author: liyihuan
 * @Date: 2021/6/9 23:03
 */
class LazyFourFragment : LazyRecyclerFragment<LazyBean>(){
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
            LazyBean("LazyFourFragment - 1"),
            LazyBean("LazyFourFragment - 2"),
            LazyBean("LazyFourFragment - 3"),
            LazyBean("LazyFourFragment - 4"),
            LazyBean("LazyFourFragment - 5"),
            LazyBean("LazyFourFragment - 6"),
            LazyBean("LazyFourFragment - 7"),
            LazyBean("LazyFourFragment - 8"),
            LazyBean("LazyFourFragment - 9"),
            LazyBean("LazyFourFragment - 10"),
            LazyBean("LazyFourFragment - 11"),
            LazyBean("LazyFourFragment - 12"),
            LazyBean("LazyFourFragment - 13"),
            LazyBean("LazyFourFragment - 14"),
            LazyBean("LazyFourFragment - 15"),
            LazyBean("LazyFourFragment - 16"),
            LazyBean("LazyFourFragment - 17")
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
        return R.layout.fragment_four
    }

    override fun onFragmentPause() {
        super.onFragmentPause()
        Log.d("QWER", "LazyFourFragment: 停止加载")
    }

    override fun onFragmentResume() {
        super.onFragmentResume()
        Log.d("QWER", "LazyFourFragment: 加载数据")
    }

    override val getTagName: String
        get() = "LazyFourFragment"

}