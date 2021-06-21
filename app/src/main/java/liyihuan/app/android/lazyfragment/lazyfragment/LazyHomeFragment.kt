package liyihuan.app.android.lazyfragment.lazyfragment

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.scwang.smartrefresh.layout.api.RefreshHeader
import kotlinx.android.synthetic.main.fragment_lazy_home.*
import kotlinx.coroutines.*
import liyihuan.app.android.lazyfragment.*
import liyihuan.app.android.lazyfragment.baselazy.LazyRecyclerFragment

import liyihuan.app.android.lazyfragment.refresh.IEmptyView
import liyihuan.app.android.lazyfragment.refresh.SmartRecyclerView

/**
 * @ClassName: LazyHomeFragment
 * @Description: java类作用描述
 * @Author: liyihuan
 * @Date: 2021/6/8 21:57
 */
class LazyHomeFragment : LazyRecyclerFragment<LazyBean>() {


    override fun getLayoutId(): Int {
        return R.layout.fragment_lazy_home
    }


    override val mSmartRecycler: SmartRecyclerView by lazy {
        smartRecyclerView
    }

    override val adapter: BaseQuickAdapter<LazyBean, *> by lazy {
        LazyAdapter().apply {
            setOnItemClickListener { adapter, view, position ->
                val intent = Intent(context, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override val layoutManager: RecyclerView.LayoutManager by lazy {
        GridLayoutManager(context, 1)
    }

    override val fetcherFuc: (page: Int) -> Unit = {
        loadData()
    }

    private val list by lazy {
        arrayListOf(
            LazyBean("1"),
            LazyBean("2"),
            LazyBean("3"),
            LazyBean("4"),
            LazyBean("5"),
            LazyBean("6"),
            LazyBean("7"),
            LazyBean("8"),
            LazyBean("9"),
            LazyBean("10"),
            LazyBean("11"),
            LazyBean("12"),
            LazyBean("13"),
            LazyBean("14"),
            LazyBean("15"),
            LazyBean("16"),
            LazyBean("17"),
            LazyBean("18"),
            LazyBean("19"),
            LazyBean("20")
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
        get() = "LazyHomeFragment"

}