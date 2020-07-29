package com.winto.develop.ThreeTones.activity;

import android.content.Intent;
import android.widget.RadioGroup;

import androidx.fragment.app.FragmentTransaction;

import com.winto.develop.ThreeTones.MainApplication;
import com.winto.develop.ThreeTones.R;
import com.winto.develop.ThreeTones.base.BaseActivity;
import com.winto.develop.ThreeTones.base.BaseFragment;
import com.winto.develop.ThreeTones.base.BaseObserver;
import com.winto.develop.ThreeTones.bean.UserInfoListBean;
import com.winto.develop.ThreeTones.fragment.HomeFragment;
import com.winto.develop.ThreeTones.fragment.MyFragment;
import com.winto.develop.ThreeTones.fragment.NoticeFragment;
import com.winto.develop.ThreeTones.http.HttpAction;
import com.winto.develop.ThreeTones.util.ToastUtil;

import java.util.List;

/**
 * Created by zyp on 2020/5/12 0020.
 * class note:欢迎页
 */

public class MainActivity extends BaseActivity {

    private RadioGroup rg_main;
    private String[] titleArray = {"首页", "搜索", "我的"};
    private BaseFragment[] fragmentArray;
    private int currentIndex;

    @Override
    protected void initView() {
        rg_main = findViewById(R.id.rg_main);
        initFragment();
    }

    private void initFragment() {
        fragmentArray = new BaseFragment[]{
                new HomeFragment(),
                new NoticeFragment(),
                new MyFragment()
        };

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        //设置为默认界面 MessageFragment
        ft.replace(R.id.fl_container, fragmentArray[0]);
        ft.commit();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initClick() {
        rg_main.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.rb_home:
                    setIndexSelected(0);
                    break;
                case R.id.rb_find:
                    setIndexSelected(1);
                    break;
                case R.id.rb_statistics:
                    setIndexSelected(2);
                    break;
            }
        });
    }

    private void getUserInfo() {
        HttpAction.getInstance().getUserInfo().subscribe(new BaseObserver<List<UserInfoListBean>>() {
            @Override
            public void onSuccess(List<UserInfoListBean> userList) {
                if (userList == null || userList.size() == 0) {
                    MainApplication.getContext().setSession(null);
                    Intent intent = new Intent();
                    intent.setClass(context, LoginActivity.class);
                    //关键的一句，将新的activity置为栈顶
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    ToastUtil.show(context, "登录状态已失效，请重新登陆");
                    return;
                }
                UserInfoListBean userInfo = userList.get(0);
                MainApplication.getContext().setUserName(userInfo.getUsername());
                MainApplication.getContext().setUserUId(userInfo.getUserid());
                MainApplication.getContext().setDepId(userInfo.getDepartment().getDepartmentid());
                MainApplication.getContext().setDepName(userInfo.getDepartment().getDepartmentname());
                if (userList.size() == 1) {
                    MainApplication.getContext().setRoleName("");
                } else {
                    UserInfoListBean userInfo2 = userList.get(1);
                    MainApplication.getContext().setRoleName(userInfo2.getRole().getRolename());
                }
            }

            @Override
            public void onError(String message) {
                ToastUtil.show(context, message);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getUserInfo();
    }

    //设置Fragment页面
    private void setIndexSelected(int index) {
        if (currentIndex == index) {
            return;
        }
        //开启事务
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.hide(fragmentArray[currentIndex]);
        //判断Fragment是否已经添加
        if (!fragmentArray[index].isAdded()) {
            ft.add(R.id.fl_container, fragmentArray[index]).show(fragmentArray[index]);
        } else {
            //显示新的Fragment
            ft.show(fragmentArray[index]);
        }
        ft.commit();
        currentIndex = index;
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }
}