package liyihuan.app.android.lazyfragment.bad

import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_bad_common.*
import liyihuan.app.android.lazyfragment.R
import liyihuan.app.android.lazyfragment.fragment.HomeViewPagerAdapter
import java.util.ArrayList

/**
 * @ClassName: BadHomeFragment
 * @Description: java类作用描述
 * @Author: liyihuan
 * @Date: 2021/6/20 22:06
 */
class BadTwoFragment1 : BadLazyFragment(){



    override fun getLayoutId(): Int {
        return R.layout.fragment_bad_common
    }

    override val getTagName: String by lazy {
        "BadTwoFragment1"
    }

    override fun initView() {

    }

    override fun onLoadData() {
        super.onLoadData()
        tvFragmentName.text = getTagName
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
    }

}