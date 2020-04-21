package com.wise.develop.WiseChat.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

public class RegisterActivity extends BaseActivity {

    private EditText et_username;
    private EditText et_password;
    private EditText et_password2;
    private Button btn_register;


    @Override
    protected void initView() {
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        et_password2 = findViewById(R.id.et_password2);
        btn_register = findViewById(R.id.btn_register);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initClick() {
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }

    private void register() {
        String username = et_username.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            ToastUtil.show(context, "用户名不能为空");
            return;
        }

        String password = et_password.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            ToastUtil.show(context, "密码不能为空");
            return;
        }

        String password2 = et_password2.getText().toString().trim();
        if (TextUtils.isEmpty(password2)) {
            ToastUtil.show(context, "密码不能为空");
            return;
        }

        if (!password.equals(password2)) {
            ToastUtil.show(context, "两次密码不一致");
            return;
        }

        Map<String, Object> params = new HashMap<>();
        params.put("userName", username);
        params.put("password", password);
        HttpAction.getInstance().register(params).subscribe(new BaseObserver<UserInfoBean>() {
            @Override
            public void onSuccess(UserInfoBean bean) {
                UserInfoBean.DataBean user = bean.getData();
                if (user == null) {
                    ToastUtil.show(context, "注册失败，请重试");
                    return;
                }

                Intent intent = new Intent();
                intent.putExtra("userName", user.getUserName());
                intent.putExtra("password", user.getPassword());
                setResult(RESULT_OK, intent);
                finish();
            }

            @Override
            public void onError(String message) {
                Log.e("TAG", "onError: " + message);
                ToastUtil.show(context, message);
            }
        });
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_register;
    }
}