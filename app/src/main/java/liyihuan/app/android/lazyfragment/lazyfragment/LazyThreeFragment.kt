package liyihuan.app.android.lazyfragment.lazyfragment

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
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
    override val mSmartRecycler: SmartRecyclerView
        get() = TODO("Not yet implemented")
    override val adapter: BaseQuickAdapter<LazyBean, *>
        get() = TODO("Not yet implemented")
    override val layoutManager: RecyclerView.LayoutManager
        get() = TODO("Not yet implemented")
    override val fetcherFuc: (page: Int) -> Unit
        get() = TODO("Not yet implemented")

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
}