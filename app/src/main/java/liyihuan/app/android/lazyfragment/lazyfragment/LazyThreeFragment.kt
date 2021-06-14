package liyihuan.app.android.lazyfragment.lazyfragment

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import kotlinx.android.synthetic.main.fragment_three.*
import kotlinx.coroutines.*
import liyihuan.app.android.lazyfragment.LazyAdapter
import liyihuan.app.android.lazyfragment.LazyBean
import liyihuan.app.android.lazyfragment.R
import liyihuan.app.android.lazyfragment.baselazy.LazyRecyclerFragment
import liyihuan.app.android.lazyfragment.manager.LazyManager
import liyihuan.app.android.lazyfragment.refresh.SmartRecyclerView

/**
 * @ClassName: LazyThreeFragment
 * @Description: java类作用描述
 * @Author: liyihuan
 * @Date: 2021/6/9 23:03
 */
class LazyThreeFragment : LazyRecyclerFragment<LazyBean>(){
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
            LazyBean("LazyThreeFragment - 1"),
            LazyBean("LazyThreeFragment - 2"),
            LazyBean("LazyThreeFragment - 3"),
            LazyBean("LazyThreeFragment - 4"),
            LazyBean("LazyThreeFragment - 5"),
            LazyBean("LazyThreeFragment - 6"),
            LazyBean("LazyThreeFragment - 7"),
            LazyBean("LazyThreeFragment - 8"),
            LazyBean("LazyThreeFragment - 9"),
            LazyBean("LazyThreeFragment - 10"),
            LazyBean("LazyThreeFragment - 11"),
            LazyBean("LazyThreeFragment - 12"),
            LazyBean("LazyThreeFragment - 13"),
            LazyBean("LazyThreeFragment - 14"),
            LazyBean("LazyThreeFragment - 15"),
            LazyBean("LazyThreeFragment - 16"),
            LazyBean("LazyThreeFragment - 17")
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
        return R.layout.fragment_three
    }

    override fun onFragmentPause() {
        super.onFragmentPause()
        Log.d("QWER", "LazyThreeFragment: 停止加载")
    }

    override fun onFragmentResume() {
        super.onFragmentResume()
        Log.d("QWER", "LazyThreeFragment: 加载数据")
    }

    override val getTagName: String
        get() = "LazyThreeFragment"



}