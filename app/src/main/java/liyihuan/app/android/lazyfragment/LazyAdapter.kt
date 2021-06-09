package liyihuan.app.android.lazyfragment

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

/**
 * @ClassName: LazyAdapter
 * @Description: java类作用描述
 * @Author: liyihuan
 * @Date: 2021/6/8 22:47
 */
class LazyAdapter : BaseQuickAdapter<LazyBean, BaseViewHolder>(R.layout.item_lazy) {
    override fun convert(helper: BaseViewHolder, item: LazyBean) {
        helper.setText(R.id.tvLazyItem, item.textContent)
    }
}