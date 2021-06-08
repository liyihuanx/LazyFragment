package liyihuan.app.android.lazyfragment.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import liyihuan.app.android.lazyfragment.baselazy.LazyFragment;
import liyihuan.app.android.lazyfragment.MainActivity;
import liyihuan.app.android.lazyfragment.R;

/**
 * @ClassName: HomeFragment
 * @Description: java类作用描述
 * @Author: liyihuan
 * @Date: 2020/10/18 14:41
 */
public class HomeFragment extends LazyFragment {



    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView(@NotNull View rootView) {
        rootView.findViewById(R.id.tvToMain).setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), MainActivity.class);
            startActivity(intent);
        });
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d("QWER", "HomeFragment-onCreate: ");
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("QWER", "HomeFragment-onActivityCreated: ");

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("QWER", "HomeFragment-onResume: ");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("QWER", "HomeFragment-onPause: ");

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("QWER", "HomeFragment-onStop: ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("QWER", "HomeFragment-onDestroyView: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("QWER", "HomeFragment-onDestroy: ");

    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("QWER", "HomeFragment-onDetach: ");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.d("QWER", "HomeFragment-setUserVisibleHint: " + isVisibleToUser);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.d("QWER", "HomeFragment-onHiddenChanged: "+hidden);
    }

    @Override
    protected void onFragmentFirstVisible() {
        Log.d("QWER", "onFragmentFirstVisible: HomeFragment");
    }


    @Override
    public void onFragmentPause() {
        super.onFragmentPause();
        Log.d("QWER", "onFragmentPause: HomeFragment-停止加载数据");
    }

    @Override
    public void onFragmentResume() {
        super.onFragmentResume();
        Log.d("QWER", "onFragmentResume: HomeFragment-正在加载数据");
    }
}
