package liyihuan.app.android.lazyfragment

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_lazy.*
import liyihuan.app.android.lazyfragment.fragment.*
import liyihuan.app.android.lazyfragment.lazyfragment.*
import java.util.*

class LazyActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lazy)
        initView()
    }

    private val fragmentsList by lazy {
        arrayListOf<Fragment>(
            LazyHomeFragment(),
            LazyTwoFragment(),
            LazyThreeFragment(),
            LazyFourFragment(),
            LazyMineFragment()
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

            }

        })
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.tab_home -> {
                vp_lazy.currentItem = 0
            }
            R.id.tab_two -> {
                vp_lazy.currentItem = 1
            }
            R.id.tab_three -> {
                vp_lazy.currentItem = 2
            }
            R.id.tab_four -> {
                vp_lazy.currentItem = 3
            }
            R.id.tab_mine -> {
                vp_lazy.currentItem = 4
            }

        }
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