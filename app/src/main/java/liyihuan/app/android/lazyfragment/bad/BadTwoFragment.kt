package liyihuan.app.android.lazyfragment.bad

import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_two.*
import liyihuan.app.android.lazyfragment.R
import liyihuan.app.android.lazyfragment.fragment.HomeViewPagerAdapter
import liyihuan.app.android.lazyfragment.lazyfragment.LazyHomeOneFragment
import liyihuan.app.android.lazyfragment.lazyfragment.LazyHomeTwoFragment
import java.util.ArrayList

/**
 * @ClassName: BadHomeFragment
 * @Description: java类作用描述
 * @Author: liyihuan
 * @Date: 2021/6/20 22:06
 */
class BadTwoFragment : BadLazyFragment(){


    private lateinit var homeViewPagerAdapter: HomeViewPagerAdapter
    private lateinit var fragmentsList: ArrayList<Fragment>

    override fun getLayoutId(): Int {
        return R.layout.fragment_two
    }

    override val getTagName: String by lazy {
        "BadTwoFragment"
    }

    override fun initView() {
        fragmentsList = ArrayList()
        fragmentsList.add(BadTwoFragment1())
        fragmentsList.add(BadTwoFragment2())

        homeViewPagerAdapter = HomeViewPagerAdapter(childFragmentManager, fragmentsList)
        vpTwo.adapter = homeViewPagerAdapter
    }

    override fun onLoadData() {
        super.onLoadData()
    }

}