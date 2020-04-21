package com.wise.develop.WiseChat.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.wise.develop.WiseChat.MainApplication;
import com.wise.develop.WiseChat.R;
import com.wise.develop.WiseChat.base.BaseActivity;
import com.wise.develop.WiseChat.base.BaseObserver;
import com.wise.develop.WiseChat.bean.UserInfoBean;
import com.wise.develop.WiseChat.http.HttpAction;
import com.wise.develop.WiseChat.util.ToastUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zyp on 2019/8/20 0020.
 * class note:欢迎页
 */

public class LoginActivity extends BaseActivity {

    private EditText et_username;
    private EditText et_password;
    private Button btn_login;
    private TextView tv_register;


    @Override
    protected void initView() {
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        btn_login = findViewById(R.id.btn_login);
        tv_register = findViewById(R.id.tv_register);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initClick() {
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toClass(context, RegisterActivity.class, 200);
            }
        });
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_login;
    }

    private void login() {
        String username = et_username.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            ToastUtil.show(this, "用户名不能为空");
            return;
        }

        String password = et_password.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            ToastUtil.show(this, "密码不能为空");
            return;
        }

        Map<String, Object> params = new HashMap<>();
        params.put("userName", username);
        params.put("password", password);
        HttpAction.getInstance().login(params).subscribe(new BaseObserver<UserInfoBean>() {
            @Override
            public void onSuccess(UserInfoBean bean) {
                UserInfoBean.DataBean user = bean.getData();
                MainApplication.getContext().setToken(user.getUserToken());
                MainApplication.getContext().setUId(user.getId());
                MainApplication.getContext().setUserInfo(user);
                toClass(context, MainActivity.class);
                finish();
            }

            @Override
            public void onError(String message) {
                ToastUtil.show(context, message);
            }
        });
    }

    private void setPage(String userName, String password) {
        et_username.setText(userName);
        et_password.setText(password);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (data != null && requestCode == 200) {
                String userName = data.getStringExtra("userName");
                String password = data.getStringExtra("password");
                setPage(userName, password);
            }
        }
    }
}
