package com.winto.develop.ThreeTones.fragment;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.winto.develop.ThreeTones.R;
import com.winto.develop.ThreeTones.activity.NoticeDetailActivity;
import com.winto.develop.ThreeTones.adapter.NoticeListAdapter;
import com.winto.develop.ThreeTones.base.BaseFragment;
import com.winto.develop.ThreeTones.base.BaseObserver;
import com.winto.develop.ThreeTones.bean.NoticeListBean;
import com.winto.develop.ThreeTones.http.HttpAction;
import com.winto.develop.ThreeTones.util.GlideUtil;
import com.winto.develop.ThreeTones.util.StatusBarHelper;
import com.winto.develop.ThreeTones.util.ToastUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NoticeFragment extends BaseFragment {
    private RelativeLayout rl_title;
    private Banner banner;
    private RecyclerView rv_list;

    private NoticeListAdapter adapter;
    private List<NoticeListBean.DataBean> noticeList;
    private List<NoticeListBean.DataBean> noticeBannerList;
    private ArrayList<String> list_path;
    private ArrayList<String> list_title;

    @Override
    protected void initView() {
        rl_title = findViewById(R.id.rl_title);
        banner = findViewById(R.id.banner);
        rv_list = findViewById(R.id.rv_list);

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) rl_title.getLayoutParams();
        params.topMargin = StatusBarHelper.getStatusBarHeight();
        rl_title.setLayoutParams(params);

    }

    @Override
    protected void initData() {
        noticeBannerList = new ArrayList<>();
        list_path = new ArrayList<>();
        list_title = new ArrayList<>();
        getNoticeList(1);
        getNoticeList(0);
    }

    private void initBanner() {
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器，图片加载器在下方
        banner.setImageLoader(new MyLoader());
        //设置图片网址或地址的集合
        banner.setImages(list_path);
        //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
        banner.setBannerAnimation(Transformer.Default);
        //设置轮播图的标题集合
        banner.setBannerTitles(list_title);
        //设置轮播间隔时间
        banner.setDelayTime(3000);
        //设置是否为自动轮播，默认是“是”。
        banner.isAutoPlay(true);
        //设置指示器的位置，小点点，左中右。
        banner.setIndicatorGravity(BannerConfig.CENTER)
                //必须最后调用的方法，启动轮播图。
                .start();
    }

    @Override
    protected void initAdapter() {
        noticeList = new ArrayList<>();

        adapter = new NoticeListAdapter(context, noticeList);
        rv_list.setLayoutManager(new LinearLayoutManager(context));
        rv_list.setAdapter(adapter);
    }

    @Override
    protected void initClick() {
        banner.setOnBannerListener(position -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("notice", noticeBannerList.get(position));
            toClass(context, NoticeDetailActivity.class, bundle);
        });

        adapter.setOnItemClickListener((notice, position) -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("notice", notice);
            toClass(context, NoticeDetailActivity.class, bundle);
        });
    }

    private void getNoticeList(int isTitle) {
        Map<String, Object> params = new HashMap<>();
        params.put("istitle", isTitle);
        HttpAction.getInstance().getNoticeList(params).subscribe(new BaseObserver<NoticeListBean>() {
            @Override
            public void onSuccess(NoticeListBean bean) {
                if (bean.getCode() != 0) {
                    ToastUtil.show(context, bean.getMsg());
                    return;
                }
                if (isTitle == 1) {
                    noticeBannerList.clear();
                    noticeBannerList.addAll(bean.getData());
                    for (int i = 0; i < bean.getData().size(); i++) {
                        NoticeListBean.DataBean notice = bean.getData().get(i);
                        list_path.add(TextUtils.isEmpty(notice.getTitleimage()) ? "" : notice.getTitleimage());
                        list_title.add(notice.getTitle());
                    }

                    initBanner();
                } else {
                    noticeList.clear();
                    noticeList.addAll(bean.getData());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(String message) {
                ToastUtil.show(context, message);
            }
        });
    }

    private static class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            GlideUtil.displayImage(context, (String) path, imageView);
        }
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_notice;
    }
}
