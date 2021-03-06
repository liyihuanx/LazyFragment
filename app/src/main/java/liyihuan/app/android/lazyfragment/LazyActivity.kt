package liyihuan.app.android.lazyfragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_lazy.*
import liyihuan.app.android.lazyfragment.bad.*
import liyihuan.app.android.lazyfragment.baselazy.LazyRecyclerFragment
import liyihuan.app.android.lazyfragment.fragment.*
import liyihuan.app.android.lazyfragment.lazyfragment.*
import java.util.*

class LazyActivity : AppCompatActivity(), View.OnClickListener {

    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lazy)
        initView()
    }

    private val fragmentsList by lazy {
        arrayListOf<Fragment>(
            BadHomeFragment(),
            BadTwoFragment(),
            BadThreeFragment(),
            BadFourFragment(),
            BadMineFragment()
//            LazyHomeFragment(),
//            LazyTwoFragment(),
//            LazyThreeFragment(),
//            LazyFourFragment(),
//            LazyMineFragment()
        )
    }


    private fun initView() {

        val viewPagerAdapter =
            HomeViewPagerAdapter(
                supportFragmentManager,
                fragmentsList
            )
        vp_lazy.adapter = viewPagerAdapter
        vp_lazy.currentItem = 0
        vp_lazy.offscreenPageLimit = 1

        tab_home.setOnClickListener(this)
        tab_two.setOnClickListener(this)
        tab_three.setOnClickListener(this)
        tab_mine.setOnClickListener(this)
        tab_four.setOnClickListener(this)

        vp_lazy.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                clearTabStatus()
                selectTab(position)
                currentIndex = position
            }
        })
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.tab_home -> {
                tabClickEvent(0)
            }
            R.id.tab_two -> {
                tabClickEvent(1)
            }
            R.id.tab_three -> {
                tabClickEvent(2)
            }
            R.id.tab_four -> {
                tabClickEvent(3)
            }
            R.id.tab_mine -> {
                tabClickEvent(4)
            }
        }
    }

    /**
     *  vp_lazy.currentItem = position???position??????????????????????????????onPageSelected
     *  ???????????????currentIndex != position
     *  ???????????????currentIndex == position
     */
    private fun tabClickEvent(position: Int) {
        if (fragmentsList[position] is LazyRecyclerFragment<*>) {
            val lazyRecyclerFragment = fragmentsList[position] as LazyRecyclerFragment<*>
            if (currentIndex == position) {
                lazyRecyclerFragment.doubleClickRefresh()
            }
        }
        // ??????????????????
        vp_lazy.currentItem = position
//        val status = fragmentsList[position].getS
//
//
//        // 1.?????????????????????????????????????????????????????????????????????
//        if ((currentIndex == position && !status.inTop)) {
//            LazyManager.smoothScrollToTop(fragmentsList[position] as LazyRecyclerFragment<*>)
//        } else {
//            // 2.??????????????????????????????????????????????????????????????? xxxx
//            vp_lazy.currentItem = position
//            val currentTimeMillis = System.currentTimeMillis()
//            val clickTime = currentTimeMillis - status.clickTime
//
//            // ???????????????
//            if (status.clickTime == 0L) {
//                status.clickTime = System.currentTimeMillis()
//            } else {
//                if (status.clickTime != 0L && clickTime >= 60 * 60 * 1000) {
//                    LazyManager.smoothScrollToTop(fragmentsList[position] as LazyRecyclerFragment<*>)
//                }
//            }
//        }
    }


    private fun clearTabStatus() {
        iv_tab_home.setImageResource(R.mipmap.tab_icon_lesson_default)
        iv_tab_two.setImageResource(R.mipmap.tab_icon_homework_default)
        iv_tab_three.setImageResource(R.mipmap.tab_icon_qa_default)
        iv_tab_four.setImageResource(R.mipmap.tab_icon_mine_default)
        iv_tab_mine.setImageResource(R.mipmap.tab_icon_mine_default)
        tv_tab_home.setTextColor(getColor(R.color.default_color_9))
        tv_tab_two.setTextColor(getColor(R.color.default_color_9))
        tv_tab_three.setTextColor(getColor(R.color.default_color_9))
        tv_tab_four.setTextColor(getColor(R.color.default_color_9))
        tv_tab_mine.setTextColor(getColor(R.color.default_color_9))
    }

    private fun selectTab(position: Int) {
        when (position) {
            0 -> {
                iv_tab_home.setImageResource(R.mipmap.tab_icon_lesson_selected)
                tv_tab_home.setTextColor(getColor(R.color.c_f26355))
            }
            1 -> {
                iv_tab_two.setImageResource(R.mipmap.tab_icon_homework_selected)
                tv_tab_two.setTextColor(getColor(R.color.c_f26355))
            }
            2 -> {
                iv_tab_three.setImageResource(R.mipmap.tab_icon_qa_selected)
                tv_tab_three.setTextColor(getColor(R.color.c_f26355))
            }
            3 -> {
                iv_tab_four.setImageResource(R.mipmap.tab_icon_mine_selected)
                tv_tab_four.setTextColor(getColor(R.color.c_f26355))
            }
            4 -> {
                iv_tab_mine.setImageResource(R.mipmap.tab_icon_mine_selected)
                tv_tab_mine.setTextColor(getColor(R.color.c_f26355))
            }
        }
    }
}