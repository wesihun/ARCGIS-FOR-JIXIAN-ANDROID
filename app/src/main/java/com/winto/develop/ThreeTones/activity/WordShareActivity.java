package com.winto.develop.ThreeTones.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.winto.develop.ThreeTones.R;
import com.winto.develop.ThreeTones.adapter.MyPagerAdapter;
import com.winto.develop.ThreeTones.base.BaseActivity;
import com.winto.develop.ThreeTones.base.BaseFragment;
import com.winto.develop.ThreeTones.base.BaseObserver;
import com.winto.develop.ThreeTones.bean.DocTabListBean;
import com.winto.develop.ThreeTones.fragment.TechnicalStandardFragment;
import com.winto.develop.ThreeTones.http.HttpAction;
import com.winto.develop.ThreeTones.util.StatusBarHelper;
import com.winto.develop.ThreeTones.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zyp on 2019/8/20 0020.
 * class note:文档共享
 */
public class WordShareActivity extends BaseActivity {

    private ImageView iv_back;
    private RelativeLayout rl_title;
    private TabLayout tl_tab;
    private ViewPager vp_pager;

    @Override
    protected void initView() {
        iv_back = findViewById(R.id.iv_back);
        rl_title = findViewById(R.id.rl_title);
        tl_tab = findViewById(R.id.tl_tab);
        vp_pager = findViewById(R.id.vp_pager);

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) rl_title.getLayoutParams();
        params.setMargins(0, StatusBarHelper.getStatusBarHeight(), 0, 0);
        rl_title.setLayoutParams(params);
    }

    private void initFragment(List<DocTabListBean.DataBean.SubMenueBean> subMenuList) {
        List<BaseFragment> fragmentList = new ArrayList<>();
        List<String> list_Title = new ArrayList<>();
        for (int i = 0; i < subMenuList.size(); i++) {
            TechnicalStandardFragment fragment = new TechnicalStandardFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("menu", subMenuList.get(i));
            fragment.setArguments(bundle);
            fragmentList.add(fragment);
            list_Title.add(subMenuList.get(i).getMenuename());
        }

        vp_pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), context, fragmentList, list_Title));
        tl_tab.setupWithViewPager(vp_pager);
    }

    @Override
    protected void initData() {
        getTabList();
    }

    @Override
    protected void initAdapter() {

    }

    private void getTabList() {
        HttpAction.getInstance().getDocTabList().subscribe(new BaseObserver<DocTabListBean>() {
            @Override
            public void onSuccess(DocTabListBean bean) {
                if (bean.getCode() != 0) {
                    return;
                }
                initFragment(bean.getData().get(0).getSubMenue());
            }

            @Override
            public void onError(String message) {
                ToastUtil.show(context, message);
            }
        });
    }

    @Override
    protected void initClick() {
        iv_back.setOnClickListener(v -> finish());
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_word_share;
    }
}