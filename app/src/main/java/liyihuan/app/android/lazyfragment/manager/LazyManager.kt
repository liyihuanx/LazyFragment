package liyihuan.app.android.lazyfragment.manager

import androidx.fragment.app.Fragment
import liyihuan.app.android.lazyfragment.TopSmoothScroller
import liyihuan.app.android.lazyfragment.baselazy.LazyRecyclerFragment
import liyihuan.app.android.lazyfragment.refresh.SmartRecyclerView
import liyihuan.app.android.lazyfragment.refresh.SmartRefreshHelper

/**
 * @ClassName: RegisterFragment
 * @Description: java类作用描述
 * @Author: liyihuan
 * @Date: 2021/6/14 20:49
 */
object LazyManager {

    /**
     * 存放<TAG,Fragment的实例>
     */
    val fragmentMap: HashMap<String, Fragment> = HashMap()

    /**
     * 存放<TAG,Fragment的状态>
     */
    val fragmentStatus: HashMap<Fragment, LazyStatus> = HashMap()


    lateinit var smartRefreshHelper: SmartRefreshHelper<*>

    fun init(smartRefreshHelper: SmartRefreshHelper<*>) {
        this.smartRefreshHelper = smartRefreshHelper
    }


    /**
     * 给每个继承了LazyRecyclerFragment的类初始化用
     */
    @JvmStatic
    fun register(tag: String, fragment: Fragment, lazyStatus: LazyStatus? = null) {
        if (fragment is LazyRecyclerFragment<*>) {
            if (!fragmentMap.containsValue(fragment)) {
                fragmentMap[tag] = fragment
            }

            if (lazyStatus != null) {
                if (!fragmentStatus.containsValue(lazyStatus)) {
                    fragmentStatus[fragment] = lazyStatus
                }
            } else {
                if (!fragmentStatus.containsKey(fragment)) {
                    fragmentStatus[fragment] = LazyStatus()
                }
            }
        } else {
            throw Exception("不能传非LazyRecyclerFragment的类型")
        }
    }

    @JvmStatic
    fun getStatus(fragment: Fragment): LazyStatus {
        return fragmentStatus[fragment]!!
    }

    @JvmStatic
    fun smoothScrollToTop(fragment: LazyRecyclerFragment<*>) {
        val status = getStatus(fragment)
        if (!status.inTop) {
            TopSmoothScroller.get().targetPosition = 0
            val smartRecycler = getSmartRecycler(fragment)
            smartRecycler.recyclerView.layoutManager!!.startSmoothScroll(TopSmoothScroller.get())
//            smartRecycler.smartRefreshHelper.refresh(false)
            status.inTop = true
        }
    }


    @JvmStatic
    fun getSmartRecycler(fragment: LazyRecyclerFragment<*>): SmartRecyclerView {
        return fragment.mSmartRecycler
    }


    @JvmStatic
    fun operateFragment(op: LazyOperate) {
        when (op) {
            LazyOperate.REFRESH -> {
                // 其他的操作。。。。
                smartRefreshHelper.refresh()
                // 其他的操作。。。。
            }

            LazyOperate.LOADMORE -> {


            }
        }
    }


}