package liyihuan.app.android.lazyfragment.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import liyihuan.app.android.lazyfragment.baselazy.LazyFragment;
import liyihuan.app.android.lazyfragment.R;


/**
 * @ClassName: TwoFragment
 * @Description: java类作用描述
 * @Author: liyihuan
 * @Date: 2020/10/18 14:41
 */
public class TwoFragment extends LazyFragment {
    private HomeViewPagerAdapter homeViewPagerAdapter;
    private ArrayList<Fragment> fragmentsList;
    private ViewPager vp_indication;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_two;
    }

    @Override
    public void initView(@NotNull View rootView) {
        vp_indication = rootView.findViewById(R.id.vp);
        HomeOneFragment Fragment1 = new HomeOneFragment();
        HomeTwoFragment Fragment2 = new HomeTwoFragment();

        fragmentsList = new ArrayList<>();
        fragmentsList.add(Fragment1);
        fragmentsList.add(Fragment2);
        homeViewPagerAdapter = new HomeViewPagerAdapter(getChildFragmentManager(), fragmentsList);
        vp_indication.setAdapter(homeViewPagerAdapter);
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d("QWER", "TwoFragment-onCreate: ");
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("QWER", "TwoFragment-onActivityCreated: ");

    }



    @Override
    public void onResume() {
        super.onResume();
        Log.d("QWER", "TwoFragment-onResume: ");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("QWER", "TwoFragment-onPause: ");

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("QWER", "TwoFragment-onStop: ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("QWER", "TwoFragment-onDestroyView: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("QWER", "TwoFragment-onDestroy: ");

    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("QWER", "TwoFragment-onDetach: ");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.d("QWER", "TwoFragment-setUserVisibleHint: " + isVisibleToUser);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.d("QWER", "TwoFragment-onHiddenChanged: "+hidden);
    }

    @Override
    public void onFragmentPause() {
        super.onFragmentPause();
        Log.d("QWER", "onFragmentPause: TwoFragment-停止加载数据");
    }

    @Override
    public void onFragmentResume() {
        super.onFragmentResume();
        Log.d("QWER", "onFragmentResume: TwoFragment-正在加载数据");
    }

    @Override
    protected void onFragmentFirstVisible() {
        Log.d("QWER", "onFragmentFirstVisible: TwoFragment");
    }
}
