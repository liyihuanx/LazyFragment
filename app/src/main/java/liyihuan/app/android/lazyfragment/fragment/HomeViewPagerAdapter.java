package liyihuan.app.android.lazyfragment.fragment;

import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * @ClassName: HomeViewPagerAdapter
 * @Description: java类作用描述
 * @Author: liyihuan
 * @Date: 2020/10/18 16:20
 */
public class HomeViewPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragmentsList;
    private FragmentManager mFm;

    public HomeViewPagerAdapter(@NonNull FragmentManager fm, ArrayList<Fragment> fragmentsList) {
        super(fm);
        this.fragmentsList = fragmentsList;
        this.mFm = fm;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentsList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentsList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //得到缓存的fragment
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        this.mFm.beginTransaction().show(fragment).commitAllowingStateLoss();
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        Fragment fragment = fragmentsList.get(position);
        mFm.beginTransaction().hide(fragment).commitAllowingStateLoss();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return super.isViewFromObject(view, object);
    }
}
