package liyihuan.app.android.lazyfragment.refresh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshHeader;

import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import liyihuan.app.android.lazyfragment.R;

/**
 * @ClassName: SmartRecyclerView
 * @Description: java类作用描述
 * @Author: liyihuan
 * @Date: 2021/6/8 21:49
 */

public class SmartRecyclerView extends FrameLayout {

    public SmartRecyclerView(@NonNull Context context) {
        this(context, null);
    }

    public SmartRecyclerView(@NonNull Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public SmartRecyclerView(@NonNull Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private SmartRefreshHelper smartRefreshHelper;

    private RecyclerView recyclerView;

    private SmartRefreshLayout smartRefreshLayout;
    private FrameLayout flRecyContent;


    public SmartRefreshLayout getSmartRefreshLayout() {
        return smartRefreshLayout;
    }

    public FrameLayout getFlRecyContent() {
        return flRecyContent;
    }

    public SmartRefreshHelper getSmartRefreshHelper() {
        return smartRefreshHelper;
    }


    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_refresh_recyclerview, this, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        flRecyContent = view.findViewById(R.id.flRecyContent);
        smartRefreshLayout = view.findViewById(R.id.refreshLayout);
        addView(view);
    }


    /**
     * 获取　recyclerView
     *
     * @return
     */
    public RecyclerView getRecyclerView() {
        return recyclerView;
    }


    /**
     * 设置刷新头样式
     *
     * @param refreshHeader
     */
    public void setReFreshHeader(RefreshHeader refreshHeader) {
        smartRefreshLayout.setRefreshHeader(refreshHeader);
    }

    /**
     * 手动调用触发刷新
     */
    public void startRefresh() {
        smartRefreshHelper.refresh(true);
    }

    /**
     * 手动停止刷新
     */
    public void pauseRefresh(){
        smartRefreshHelper.pauseRefresh();
    }


    /**
     * 将请求结果交给smartRefreshHelper处理
     */
    public void onFetchDataFinish(List data) {
        smartRefreshHelper.onFetchDataFinish(data);
    }


    /**
     * 初始化
     *
     * @param emptyView     空视图
     * @param adapter       适配器
     * @param fetcherFuc    刷新事件页回调　0开始
     */
    public void setUp(BaseQuickAdapter adapter, IEmptyView emptyView, Boolean loadMoreNeed, Boolean isRefreshNeed, Function1<Integer, Unit> fetcherFuc) {
        if (emptyView != null) {
            flRecyContent.addView(emptyView.getContentView(), 0);
        }

        recyclerView.setAdapter(adapter);
        smartRefreshHelper = new SmartRefreshHelper(adapter, recyclerView, smartRefreshLayout, emptyView, loadMoreNeed, isRefreshNeed, fetcherFuc);
    }

}

