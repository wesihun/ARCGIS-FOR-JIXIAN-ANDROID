package com.winto.develop.ThreeTones.activity;

import android.graphics.Bitmap;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.winto.develop.ThreeTones.R;
import com.winto.develop.ThreeTones.base.BaseActivity;
import com.winto.develop.ThreeTones.util.StatusBarHelper;

/**
 * Created by zyp on 2019/8/20 0020.
 * class note:专项调查
 */

public class FilePreviewActivity extends BaseActivity {
    String TAG = "FilePreviewActivity";
    private RelativeLayout rl_title;
    private WebView awv_file_preview;
    private TextView tv_title;

    private String fileName;

    @Override
    protected void initIntentData() {
        fileName = getIntent().getStringExtra("fileName");
    }

    @Override
    protected void initView() {
        rl_title = findViewById(R.id.rl_title);
        awv_file_preview = findViewById(R.id.awv_file_preview);
        tv_title = findViewById(R.id.tv_title);
        tv_title.setText(fileName);

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) rl_title.getLayoutParams();
        params.topMargin = StatusBarHelper.getStatusBarHeight();
        rl_title.setLayoutParams(params);
//        awv_file_preview.setListener(this, this);
/*//        awv_file_preview.loadUrl("http://192.168.1.106:6060/images/1.jpg");
//        awv_file_preview.loadUrl("http://view.officeapps.live.com/op/view.aspx?src=http://192.168.1.106:6060/document/word/testword.docx");
        awv_file_preview.loadUrl("http://view.officeapps.live.com/op/view.aspx?src=http://192.168.1.106:6060/document/pdf/test.pdf");
//        awv_file_preview.loadUrl("http://192.168.1.106:6060/document/pdf/test.pdf");*/
        initWebView("http://view.officeapps.live.com/op/view.aspx?src=http://cc.bajuntong.cn/1.xlsx");
    }

    public void initWebView(String url) {
        WebSettings settings = awv_file_preview.getSettings();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setAllowFileAccessFromFileURLs(true);
        awv_file_preview.loadUrl(url);
        awv_file_preview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.e(TAG, "shouldOverrideUrlLoading: " + url);
                view.loadUrl(url);//此行代码必须添加上去，否则不会显示
                return true;// 在跳转链接时强制在当前webview中加载
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initAdapter() {

    }

    @Override
    protected void initClick() {
        awv_file_preview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Log.e(TAG, "onPageStarted: ");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.e(TAG, "onPageFinished: ");
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.e(TAG, "shouldOverrideUrlLoading: " + url);
                view.loadUrl(url);//此行代码必须添加上去，否则不会显示
                return true;// 在跳转链接时强制在当前webview中加载
            }
        });
    }


    @Override
    protected int setLayout() {
        return R.layout.activity_file_preview;
    }
}