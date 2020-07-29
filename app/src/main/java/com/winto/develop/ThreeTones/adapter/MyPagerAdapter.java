package com.winto.develop.ThreeTones.adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.winto.develop.ThreeTones.base.BaseFragment;

import java.util.List;

public class MyPagerAdapter extends FragmentPagerAdapter {
    private Context context;
    private List<BaseFragment> fragmentList;
    private List<String> list_Title;

    public MyPagerAdapter(FragmentManager fm, Context context, List<BaseFragment> fragmentList, List<String> list_Title) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.context = context;
        this.fragmentList = fragmentList;
        this.list_Title = list_Title;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return list_Title.size();
    }

    //此方法用来显示tab上的名字
    @Override
    public CharSequence getPageTitle(int position) {
        return list_Title.get(position);
    }
}
