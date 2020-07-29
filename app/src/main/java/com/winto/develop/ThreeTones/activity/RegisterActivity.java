package com.winto.develop.ThreeTones.activity;

import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.winto.develop.ThreeTones.R;
import com.winto.develop.ThreeTones.base.BaseActivity;
import com.winto.develop.ThreeTones.base.BaseObserver;
import com.winto.develop.ThreeTones.bean.DepartmentListBean;
import com.winto.develop.ThreeTones.bean.LoginBean;
import com.winto.develop.ThreeTones.bean.PostListBean;
import com.winto.develop.ThreeTones.bean.RoleListBean;
import com.winto.develop.ThreeTones.http.HttpAction;
import com.winto.develop.ThreeTones.util.StatusBarHelper;
import com.winto.develop.ThreeTones.util.ToastUtil;
import com.winto.develop.ThreeTones.wight.TypePicker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zyp on 2020/5/20 0020.
 * class note:注册
 */
public class RegisterActivity extends BaseActivity {

    private ImageView iv_back;
    private EditText et_account;
    private EditText et_password;
    private EditText et_name;
    private EditText et_phone;
    private TextView tv_sex;
    private TextView tv_department;
    private TextView tv_job;
    private TextView tv_role;
    private Button btn_login;
    private String account;
    private String password;
    private String name;
    private String phone;
    private String sex;
    private String department;
    private String job;
    private String role;

    private TypePicker picker;

    private List<String> sexStrList = new ArrayList<>();

    private List<DepartmentListBean> departmentList;
    private List<String> departmentStrList = new ArrayList<>();
    private int departmentId;

    private List<PostListBean> jobList;
    private List<String> jobStrList = new ArrayList<>();
    private int jobId;

    private List<RoleListBean> roleList;
    private List<String> roleStrList = new ArrayList<>();
    private int roleId;

    @Override
    protected void initView() {
        iv_back = findViewById(R.id.iv_back);
        et_account = findViewById(R.id.et_account);
        et_password = findViewById(R.id.et_password);
        et_name = findViewById(R.id.et_name);
        et_phone = findViewById(R.id.et_phone);
        tv_sex = findViewById(R.id.tv_sex);
        tv_department = findViewById(R.id.tv_department);
        tv_job = findViewById(R.id.tv_job);
        tv_role = findViewById(R.id.tv_role);
        btn_login = findViewById(R.id.btn_login);

        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) iv_back.getLayoutParams();
        params.topMargin = StatusBarHelper.getStatusBarHeight();
        iv_back.setLayoutParams(params);
    }

    @Override
    protected void initData() {
        sexStrList.add("男");
        sexStrList.add("女");
        getDepartment();
        getPost();
        getRole();
    }

    @Override
    protected void initClick() {
        iv_back.setOnClickListener(v -> finish());

        tv_sex.setOnClickListener(v -> showPicker(sexStrList, tv_sex, 1));

        tv_department.setOnClickListener(v -> showPicker(departmentStrList, tv_department, 2));

        tv_job.setOnClickListener(v -> showPicker(jobStrList, tv_job, 3));

        tv_role.setOnClickListener(v -> showPicker(roleStrList, tv_role, 4));

        btn_login.setOnClickListener(v -> {
            account = et_account.getText().toString().trim();
            if (TextUtils.isEmpty(account)) {
                ToastUtil.show(context, "请输入账号");
                return;
            }

            password = et_password.getText().toString().trim();
            if (TextUtils.isEmpty(password)) {
                ToastUtil.show(context, "请输入密码");
                return;
            }

            name = et_name.getText().toString().trim();
            if (TextUtils.isEmpty(name)) {
                ToastUtil.show(context, "请输入姓名");
                return;
            }

            phone = et_phone.getText().toString().trim();
            if (TextUtils.isEmpty(phone)) {
                ToastUtil.show(context, "请输入电话");
                return;
            }

            sex = tv_sex.getText().toString().trim();
            if ("性别".equals(sex)) {
                ToastUtil.show(context, "请选择性别");
                return;
            }

            department = tv_department.getText().toString().trim();
            if ("部门".equals(department)) {
                ToastUtil.show(context, "请选择部门");
                return;
            }

            job = tv_job.getText().toString().trim();
            if ("岗位".equals(job)) {
                ToastUtil.show(context, "请选择岗位");
                return;
            }

            role = tv_role.getText().toString().trim();
            if ("角色".equals(role)) {
                ToastUtil.show(context, "请选择角色");
                return;
            }
            register();
        });
    }

    private void getDepartment() {
        HttpAction.getInstance().getDepartment().subscribe(new BaseObserver<List<DepartmentListBean>>() {
            @Override
            public void onSuccess(List<DepartmentListBean> bean) {
                departmentList = bean;
                for (int i = 0; i < bean.size(); i++) {
                    departmentStrList.add(bean.get(i).getDepartmentname());
                }
            }

            @Override
            public void onError(String message) {

            }
        });
    }

    private void getPost() {
        HttpAction.getInstance().getPost().subscribe(new BaseObserver<List<PostListBean>>() {
            @Override
            public void onSuccess(List<PostListBean> bean) {
                jobList = bean;
                for (int i = 0; i < bean.size(); i++) {
                    jobStrList.add(bean.get(i).getPostname());
                }
            }

            @Override
            public void onError(String message) {

            }
        });
    }

    private void getRole() {
        HttpAction.getInstance().getRole().subscribe(new BaseObserver<List<RoleListBean>>() {
            @Override
            public void onSuccess(List<RoleListBean> bean) {
                roleList = bean;
                for (int i = 0; i < bean.size(); i++) {
                    roleStrList.add(bean.get(i).getRolename());
                }
            }

            @Override
            public void onError(String message) {

            }
        });
    }

    private void showPicker(List<String> list, TextView textView, int chooseType) {
        if (picker == null) {
            picker = new TypePicker(context);
        }
        if (list == null || list.size() == 0) {
            ToastUtil.show(context, "暂无数据");
        }
        picker.setItemData(list);
        picker.show();
        picker.setTypeListener(new TypePicker.OnTypeListener() {
            @Override
            public void onTypeSelected(String type, int position) {
                textView.setText(type);
                switch (chooseType) {
                    case 1:
                        //性别
                        break;
                    case 2:
                        //部门
                        departmentId = departmentList.get(position).getDepartmentid();
                        break;
                    case 3:
                        //岗位
                        jobId = jobList.get(position).getPostid();
                        break;
                    case 4:
                        //角色
                        roleId = roleList.get(position).getRoleid();
                        break;
                }
            }
        });
    }

    private void register() {
        Map<String, Object> params = new HashMap<>();
        params.put("username", account);
        params.put("password", password);
        params.put("realname", name);
        params.put("gender", sex);
        params.put("telephone", phone);
        params.put("departmentid", departmentId);
        params.put("postid", jobId);
        params.put("roleid", roleId);
        HttpAction.getInstance().register(params).subscribe(new BaseObserver<LoginBean>() {
            @Override
            public void onSuccess(LoginBean bean) {
                String result = bean.getResult();
                if ("fail".equals(result)) {
                    ToastUtil.show(context, "注册失败");
                    return;
                }
                if ("repeat".equals(result)) {
                    ToastUtil.show(context, "用户已存在");
                    return;
                }
                ToastUtil.show(context, "注册成功");
                finish();
            }

            @Override
            public void onError(String message) {
                ToastUtil.show(context, message);
            }
        });
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_register;
    }
}