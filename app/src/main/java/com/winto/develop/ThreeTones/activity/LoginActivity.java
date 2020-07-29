package com.winto.develop.ThreeTones.activity;

import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.winto.develop.ThreeTones.MainApplication;
import com.winto.develop.ThreeTones.R;
import com.winto.develop.ThreeTones.base.BaseActivity;
import com.winto.develop.ThreeTones.base.BaseObserver;
import com.winto.develop.ThreeTones.bean.LoginBean;
import com.winto.develop.ThreeTones.bean.UserInfoListBean;
import com.winto.develop.ThreeTones.dialog.WaitUI;
import com.winto.develop.ThreeTones.http.HttpAction;
import com.winto.develop.ThreeTones.util.StatusBarHelper;
import com.winto.develop.ThreeTones.util.ToastUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zyp on 2019/8/20 0020.
 * class note:登陆
 */

public class LoginActivity extends BaseActivity {

    private ImageView iv_back;
    private EditText et_username;
    private EditText et_password;
    private Button btn_login;
        private TextView tv_register;
//    private TextView tv_forget_password;
    private String username;
    private String password;

    @Override
    protected void initView() {
        iv_back = findViewById(R.id.iv_back);
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        btn_login = findViewById(R.id.btn_login);
        tv_register = findViewById(R.id.tv_register);

        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) iv_back.getLayoutParams();
        params.topMargin = StatusBarHelper.getStatusBarHeight();
        iv_back.setLayoutParams(params);
    }

    @Override
    protected void initData() {
//        toClass(context,RegisterActivity.class);
    }

    @Override
    protected void initClick() {
        iv_back.setOnClickListener(v -> finish());

        btn_login.setOnClickListener(v -> {
            username = et_username.getText().toString().trim();
            if (TextUtils.isEmpty(username)) {
                ToastUtil.show(context, "用户名不能为空");
                return;
            }

            password = et_password.getText().toString().trim();
            if (TextUtils.isEmpty(password)) {
                ToastUtil.show(context, "密码不能为空");
                return;
            }
            login();
        });

        tv_register.setOnClickListener(v -> toClass(context,RegisterActivity.class));
    }

    private void login() {
        WaitUI.show(context);
        Map<String, Object> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        HttpAction.getInstance().login(params).subscribe(new BaseObserver<LoginBean>() {
            @Override
            public void onSuccess(LoginBean bean) {
                WaitUI.cancel();
                if ("success".equals(bean.getResult())) {
                    toClass(context, MainActivity.class);
                    MainApplication.getContext().setUserName(username);
                    finish();
                } else {
                    ToastUtil.show(context, "登录失败:" + bean.getResult());
                    MainApplication.getContext().setSession(null);
                }
            }

            @Override
            public void onError(String message) {
                WaitUI.cancel();
                ToastUtil.show(context, message);
                MainApplication.getContext().setSession(null);
            }
        });
    }

    private void getUserInfo() {
        HttpAction.getInstance().getUserInfo().subscribe(new BaseObserver<List<UserInfoListBean>>() {
            @Override
            public void onSuccess(List<UserInfoListBean> bean) {

            }

            @Override
            public void onError(String message) {

            }
        });
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_login;
    }
}