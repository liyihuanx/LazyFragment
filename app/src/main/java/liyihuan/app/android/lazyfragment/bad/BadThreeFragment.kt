package liyihuan.app.android.lazyfragment.bad

import kotlinx.android.synthetic.main.fragment_bad_common.*
import liyihuan.app.android.lazyfragment.R

/**
 * @ClassName: BadHomeFragment
 * @Description: java类作用描述
 * @Author: liyihuan
 * @Date: 2021/6/20 22:06
 */
class BadThreeFragment : BadLazyFragment(){
    override fun getLayoutId(): Int {
        return R.layout.fragment_bad_common
    }

    override val getTagName: String by lazy {
        "BadThreeFragment"
    }

    override fun initView() {
    }

    override fun onLoadData() {
        super.onLoadData()
        tvFragmentName.text = getTagName
    }
}