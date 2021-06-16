package liyihuan.app.android.lazyfragment.manager

import androidx.fragment.app.Fragment
import liyihuan.app.android.lazyfragment.TopSmoothScroller
import liyihuan.app.android.lazyfragment.baselazy.BaseLazyFragment
import liyihuan.app.android.lazyfragment.baselazy.LazyRecyclerFragment
import liyihuan.app.android.lazyfragment.refresh.SmartRecyclerView

/**
 * @ClassName: RegisterFragment
 * @Description: java类作用描述
 * @Author: liyihuan
 * @Date: 2021/6/14 20:49
 */
object LazyManager {

    // TODO 感觉可以写到LazyRecyclerviewFragment中，少写一个类

    /**
     * 存放<TAG,Fragment的实例>
     */
    val fragmentMap: HashMap<String, Fragment> = HashMap()

    /**
     * 存放<TAG,Fragment的状态>
     */
    val fragmentStatus: HashMap<Fragment, LazyStatus> = HashMap()


    /**
     * 给每个继承了LazyRecyclerFragment的类初始化用
     */
    @JvmStatic
    fun register(tag: String, fragment: Fragment, lazyStatus: LazyStatus? = null) {
        if (fragment is BaseLazyFragment) {
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
        val smartRecycler = getSmartRecycler(fragment)

//        runBlocking {
//            val job = launch {
//                if (!status.inTop) {
//                    TopSmoothScroller.get().targetPosition = 0
//                    smartRecycler.recyclerView.layoutManager!!.startSmoothScroll(TopSmoothScroller.get())
//                    status.inTop = true
//                }
//            }
//            job.join()
//            smartRecycler.smartRefreshHelper.refresh(false)
//
//        }
        TopSmoothScroller.get().targetPosition = 0
        smartRecycler.recyclerView.layoutManager!!.startSmoothScroll(TopSmoothScroller.get())
        status.inTop = true
        status.clickTime = System.currentTimeMillis()
        smartRecycler.smartRefreshHelper.fakeRefresh()
    }


    @JvmStatic
    fun getSmartRecycler(fragment: LazyRecyclerFragment<*>): SmartRecyclerView {
        return fragment.mSmartRecycler
    }

}