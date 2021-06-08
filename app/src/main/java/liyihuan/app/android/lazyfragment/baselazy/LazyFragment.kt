package liyihuan.app.android.lazyfragment.baselazy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * @ClassName: LazyFragment
 * @Description: java类作用描述
 * @Author: liyihuan
 * @Date: 2021/3/23 22:44
 */
abstract class LazyFragment : Fragment() {

    /**
     * 1.第一次加载，不加载当前界面的数据
     * 2.跳转到Activity之后回来不会刷新数据，且缓存的fragment都会走onResume
     * 3.fragment的嵌套加载问题
     */

    var rootview: View? = null
    var isViewCreated = false
    var currentVisibleStatus = false
    var isFirstLoad = true

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootview = inflater.inflate(getLayoutId(), null)

        initView(rootview!!)
        isViewCreated = true

        // isHidden 如果该Fragment对象已经被隐藏，那么它返回true
        if (!isHidden && userVisibleHint) {
            dispatchVisible(true)
        }

        return rootview

    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        // 在界面初始化好后才开始分发可见状态加载数据
        // 但是函数是在生命周期之前调用的，所以第一个显示的fragment界面还没绘制之前，这个函数就已经结束了，所以分发不到事件
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

    private fun dispatchVisible(isVisible: Boolean) {
        currentVisibleStatus = isVisible

        // 解决viewpager嵌套 子fragment会加载数据的问题
        if (isVisible && isParentVisible()) {
            return
        }

        if (isVisible) {
            if (isFirstLoad) {
                onFragmentFirstVisible()
                isFirstLoad = false
            }
            onFragmentResume()
            dispatchChild(true)
        } else {
            onFragmentPause()
            dispatchChild(false)
        }

    }

    /**
     * 父Fragment 是否可见
     * return false 是分发
     * return true 是不分发 ---> 就是不可见的时候 ---> currentVisibleStatus == false
     * 所以刚好和currentVisibleStatus相反
     */
    private fun isParentVisible(): Boolean {
        if (parentFragment is LazyFragment) {

            return (parentFragment as LazyFragment).isSupportVisible()
        }
        return false
    }


    private  fun isSupportVisible(): Boolean {
        return !currentVisibleStatus // 可见 true  不可见 false
    }

    override fun onResume() {
        super.onResume()
        // 跳转Activity回来后，缓存的Fragment会走onResume
        // 如果不是第一次加载就再次加载数据
        if (!isFirstLoad){
            // isHidden = false, userVisibleHint = true, currentVisibleStatus
            // userVisibleHint && !currentVisibleStatus 不可见--> 可见
            if (!isHidden && userVisibleHint && !currentVisibleStatus) {
                dispatchVisible(true)
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        isViewCreated = false
        currentVisibleStatus = false
    }

    private fun dispatchChild(visible: Boolean) {
        val fragments = childFragmentManager.fragments //  List<Fragment>
        fragments.forEach {
            if (it is LazyFragment && !it.isHidden && it.userVisibleHint) {
                it.dispatchVisible(visible)
            }
        }
    }



    /**
     * 加载数据
     * 停止加载数据
     */
    open fun onFragmentResume() {}
    open fun onFragmentPause() {}

    abstract fun getLayoutId(): Int
    abstract fun initView(rootView: View)
    protected abstract fun onFragmentFirstVisible()
}