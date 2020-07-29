package com.winto.develop.ThreeTones.activity;

import android.net.http.SslError;
import android.os.Build;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.winto.develop.ThreeTones.R;
import com.winto.develop.ThreeTones.base.BaseActivity;
import com.winto.develop.ThreeTones.bean.NoticeListBean;
import com.winto.develop.ThreeTones.util.DateUtil;
import com.winto.develop.ThreeTones.util.StatusBarHelper;

/**
 * Created by zyp on 2019/8/20 0020.
 * class note:公告详情
 */

public class NoticeDetailActivity extends BaseActivity {

    private ImageView iv_back;
    private RelativeLayout rl_title;
    private TextView tv_title;
    private TextView tv_time;
    private WebView wv_web;

    private NoticeListBean.DataBean notice;

    @Override
    protected void initIntentData() {
        notice = (NoticeListBean.DataBean) getIntent().getSerializableExtra("notice");
    }

    @Override
    protected void initView() {
        iv_back = findViewById(R.id.iv_back);
        rl_title = findViewById(R.id.rl_title);
        tv_title = findViewById(R.id.tv_title);
        tv_time = findViewById(R.id.tv_time);
        wv_web = findViewById(R.id.wv_web);

        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) rl_title.getLayoutParams();
        params.topMargin = StatusBarHelper.getStatusBarHeight();
        rl_title.setLayoutParams(params);

        tv_title.setText(notice.getTitle());
        tv_time.setText(DateUtil.formatTime(notice.getCreatetime()));

        wv_web.getSettings().setJavaScriptEnabled(true); // 启用js
        wv_web.getSettings().setBlockNetworkImage(false); // 解决图片不显示
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            wv_web.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        wv_web.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                super.onReceivedSslError(view, handler, error);
                handler.proceed();
            }
        });


        wv_web.loadData(notice.getContent(), "text/html", "UTF-8");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initClick() {
        iv_back.setOnClickListener(v -> finish());
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_notice;
    }
}