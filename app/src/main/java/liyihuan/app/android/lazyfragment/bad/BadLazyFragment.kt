package liyihuan.app.android.lazyfragment.bad

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import liyihuan.app.android.lazyfragment.baselazy.BaseLazyFragment

/**
 * @ClassName: BadLazyFragment
 * @Description: java类作用描述
 * @Author: liyihuan
 * @Date: 2021/6/20 22:00
 */
abstract class BadLazyFragment : Fragment() {

    // 根布局
    private var rootview: View? = null
    // 布局是否初始化
    private var isViewCreated = false
    // 记录Fragment的可见状态
    private var currentVisibleStatus = false
    // 是否第一次加载
    private var isFirstLoad = true

    // 布局
    abstract fun getLayoutId(): Int
    // 每个fragment名字
    abstract val getTagName: String
    // 初始化view，赋值
    abstract fun initView()
    // 加载数据
    open fun onLoadData() {
        Log.d("QWER", "onLoadData - $getTagName")

    }

    // 离开不加载数据
    open fun onStopLoadData() {
        Log.d("QWER", "onStopLoadData - $getTagName")
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootview = inflater.inflate(getLayoutId(), null)
        isViewCreated = true
        return rootview

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        if (userVisibleHint) {
            dispatchVisible(true)
        }
    }

    /**
     * 父Fragment 是否可见
     * return false 是分发
     * return true 是不分发 ---> 就是不可见的时候 ---> currentVisibleStatus == false
     * 所以刚好和currentVisibleStatus相反
     */
    private fun isParentVisible(): Boolean {
        if (parentFragment is BadLazyFragment) {
            return (parentFragment as BadLazyFragment).isSupportVisible()
        }
        return false
    }

    private fun isSupportVisible(): Boolean {
        return !currentVisibleStatus // 可见 true  不可见 false
    }


    private fun dispatchVisible(isVisibleToUser: Boolean){
        // 保存当前的可见性状态
        currentVisibleStatus = isVisibleToUser

        // 解决viewpager嵌套 子fragment会加载数据的问题
        if (isVisibleToUser && isParentVisible()) {
            return
        }

        if (isVisibleToUser) {
            if (isFirstLoad) {
                isFirstLoad = false
            }

            onLoadData()
            dispatchChild(true)
        } else {
            onStopLoadData()
            dispatchChild(false)
        }
    }

    private fun dispatchChild(visible: Boolean) {
        val fragments = childFragmentManager.fragments //  List<Fragment>
        fragments.forEach {
            if (it is BadLazyFragment && !it.isHidden && it.userVisibleHint) {
                it.dispatchVisible(visible)
            }
        }
    }


    /**
     * fragment 可见性
     */
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isViewCreated) {
            if (!isVisibleToUser && currentVisibleStatus) {
                // 1.可见--> 不可见
                dispatchVisible(false)
            } else if (isVisibleToUser && !currentVisibleStatus) {
                // 2.不可见--> 可见
                dispatchVisible(true)
            }
        }
    }







    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
    }

    override fun onResume() {
        super.onResume()
        // 跳转Activity回来后，缓存的Fragment会走onResume
        // 如果不是第一次加载就再次加载数据
        if (!isFirstLoad) {
            // isHidden = false, userVisibleHint = true, currentVisibleStatus
            // userVisibleHint && !currentVisibleStatus 不可见--> 可见
            if (userVisibleHint && !currentVisibleStatus) {
                dispatchVisible(true)
            }
        }
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
        currentVisibleStatus = false
    }
}
