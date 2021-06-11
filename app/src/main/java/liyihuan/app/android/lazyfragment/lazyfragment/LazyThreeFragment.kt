package liyihuan.app.android.lazyfragment.lazyfragment

import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import kotlinx.android.synthetic.main.fragment_three.*
import kotlinx.coroutines.*
import liyihuan.app.android.lazyfragment.LazyAdapter
import liyihuan.app.android.lazyfragment.LazyBean
import liyihuan.app.android.lazyfragment.R
import liyihuan.app.android.lazyfragment.baselazy.LazyRecyclerFragment
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
        arrayListOf(LazyBean("1"), LazyBean("2"), LazyBean("3"))
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

    override fun onResume() {
        super.onResume()
        Log.d("QWER", "LazyThreeFragment: onResume")
    }
}