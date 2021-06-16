package liyihuan.app.android.lazyfragment.lazyfragment

import android.util.Log
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_two.*
import liyihuan.app.android.lazyfragment.R
import liyihuan.app.android.lazyfragment.baselazy.BaseLazyFragment
import liyihuan.app.android.lazyfragment.fragment.HomeViewPagerAdapter
import liyihuan.app.android.lazyfragment.manager.LazyManager
import java.util.*

/**
 * @ClassName: LazyTwoFragment
 * @Description: java类作用描述
 * @Author: liyihuan
 * @Date: 2021/6/9 23:00
 */
class LazyTwoFragment : BaseLazyFragment() {

    private lateinit var homeViewPagerAdapter: HomeViewPagerAdapter
    private lateinit var fragmentsList: ArrayList<Fragment>
    override fun getLayoutId(): Int {
        return R.layout.fragment_two
    }

    override fun initView() {

        fragmentsList = ArrayList()
        fragmentsList.add(LazyHomeOneFragment())
        fragmentsList.add(LazyHomeTwoFragment())

        homeViewPagerAdapter = HomeViewPagerAdapter(childFragmentManager, fragmentsList)
        vpTwo.adapter = homeViewPagerAdapter

        LazyManager.register("LazyTwoFragment",this)

    }

    override fun onFragmentPause() {
        super.onFragmentPause()
        Log.d("QWER", "LazyTwoFragment: 停止加载")
    }

    override fun onFragmentResume() {
        Log.d("QWER", "LazyTwoFragment: 加载数据")
    }


}