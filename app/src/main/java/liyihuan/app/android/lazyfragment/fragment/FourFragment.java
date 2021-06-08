package liyihuan.app.android.lazyfragment.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import liyihuan.app.android.lazyfragment.baselazy.LazyFragment;
import liyihuan.app.android.lazyfragment.R;

/**
 * @ClassName: HomeFragment
 * @Description: java类作用描述
 * @Author: liyihuan
 * @Date: 2020/10/18 14:41
 */
public class FourFragment extends LazyFragment {



    @Override
    public int getLayoutId() {
        return R.layout.fragment_four;
    }

    @Override
    public void initView(@NotNull View rootView) {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d("QWER", "FourFragment-onCreate: ");
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("QWER", "FourFragment-onActivityCreated: ");

    }


    @Override
    public void onResume() {
        super.onResume();
        Log.d("QWER", "FourFragment-onResume: ");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("QWER", "FourFragment-onPause: ");

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("QWER", "FourFragment-onStop: ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("QWER", "FourFragment-onDestroyView: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("QWER", "FourFragment-onDestroy: ");

    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("QWER", "FourFragment-onDetach: ");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.d("QWER", "FourFragment-setUserVisibleHint: " + isVisibleToUser);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.d("QWER", "FourFragment-onHiddenChanged: " + hidden);
    }

    @Override
    public void onFragmentPause() {
        super.onFragmentPause();
        Log.d("QWER", "onFragmentPause: FourFragment-停止加载数据");
    }

    @Override
    public void onFragmentResume() {
        super.onFragmentResume();
        Log.d("QWER", "onFragmentResume: FourFragment-正在加载数据");
    }

    @Override
    protected void onFragmentFirstVisible() {
        Log.d("QWER", "onFragmentFirstVisible: FourFragment");
    }
}
