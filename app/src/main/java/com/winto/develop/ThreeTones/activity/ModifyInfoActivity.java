package com.winto.develop.ThreeTones.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.winto.develop.ThreeTones.MainApplication;
import com.winto.develop.ThreeTones.R;
import com.winto.develop.ThreeTones.base.BaseActivity;
import com.winto.develop.ThreeTones.base.BaseObserver;
import com.winto.develop.ThreeTones.bean.BaseResponseBean;
import com.winto.develop.ThreeTones.bean.UserDepartmentListBean;
import com.winto.develop.ThreeTones.bean.UserInfoBean;
import com.winto.develop.ThreeTones.bean.UserInfoListBean;
import com.winto.develop.ThreeTones.bean.UserPostListBean;
import com.winto.develop.ThreeTones.bean.UserTownListBean;
import com.winto.develop.ThreeTones.bean.UserVillageListBean;
import com.winto.develop.ThreeTones.dialog.WaitUI;
import com.winto.develop.ThreeTones.http.HttpAction;
import com.winto.develop.ThreeTones.util.StatusBarHelper;
import com.winto.develop.ThreeTones.util.ToastUtil;
import com.winto.develop.ThreeTones.wight.DepartmentPicker;
import com.winto.develop.ThreeTones.wight.TownPicker;
import com.winto.develop.ThreeTones.wight.TypePicker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zyp on 2019/8/20 0020.
 * class note:编辑资料
 */

public class ModifyInfoActivity extends BaseActivity {

    private ImageView iv_back;
    private TextView tv_title;
    private TextView tv_commit;
    private RelativeLayout rl_title;
    private EditText et_real_name;
    private EditText et_nick_name;
    private EditText et_id_card;
    private TextView tv_sex;
    private EditText tv_phone_num;
    private TextView tv_local;
    private EditText tv_email;
    private TextView tv_department;
    private EditText et_age;
    private TextView tv_job;
    private UserInfoListBean userInfo;
    private List<UserTownListBean.DataBean> townList;

    private TownPicker townPicker;
    private TypePicker sexPicker;
    private DepartmentPicker departmentPicker;
    private TypePicker postPicker;
    private List<UserVillageListBean.DataBean> villageList;
    private List<UserDepartmentListBean.DataBean> departmentList;
    private List<UserPostListBean.DataBean> postList;

    private String createtime;
    private String password;
    private String postName;
    private int townId;
    private int villageId;
    private int departmentId;
    private int postId;
    private int state;

    @Override
    protected void initView() {
        iv_back = findViewById(R.id.iv_back);
        tv_title = findViewById(R.id.tv_title);
        tv_commit = findViewById(R.id.tv_commit);
        rl_title = findViewById(R.id.rl_title);
        et_real_name = findViewById(R.id.et_real_name);
        et_nick_name = findViewById(R.id.et_nick_name);
        et_id_card = findViewById(R.id.et_id_card);
        tv_sex = findViewById(R.id.tv_sex);
        tv_phone_num = findViewById(R.id.tv_phone_num);
        tv_local = findViewById(R.id.tv_local);
        tv_email = findViewById(R.id.tv_email);
        tv_department = findViewById(R.id.tv_department);
        et_age = findViewById(R.id.et_age);
        tv_job = findViewById(R.id.tv_job);

        initTownPicker();
        initDepartmentPicker();

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) rl_title.getLayoutParams();
        params.topMargin = StatusBarHelper.getStatusBarHeight();
        rl_title.setLayoutParams(params);
    }

    @Override
    protected void initData() {
        getUserInfo();
        getTownList();
        getDepartmentList();
        getPostList();
    }

    @Override
    protected void initClick() {
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tv_sex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSexPicker();
            }
        });

        tv_local.setOnClickListener(v -> {
            if (townList == null || townList.size() == 0) {
                getTownList();
            }
            townPicker.show();
        });

        townPicker.setTownListener(new TownPicker.OnTownChooseListener() {
            @Override
            public void onTownSelected(String item, int position) {
                getVillageList(townList.get(position).getId());
            }
        });

        townPicker.setOnConfirmListener(new TownPicker.OnConfirmListener() {
            @Override
            public void onConfirm(String countyName, String townName, int townPosition, String villageName, int villagePosition) {
                setUserLocal(countyName, townName, townPosition, villageName, villagePosition);
            }
        });

        tv_department.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (departmentList == null || departmentList.size() == 0) {
                    getDepartmentList();
                }
                departmentPicker.show();
            }
        });

        departmentPicker.setOnConfirmListener(new DepartmentPicker.OnConfirmListener() {
            @Override
            public void onConfirm(int menuId, String menuName, String subMenuName) {
                setUserDepartment(menuId, menuName, subMenuName);
            }
        });

        tv_job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (postList == null || postList.size() == 0) {
                    getPostList();
                }
                showPostPicker();
            }
        });

        tv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyUserInfo();
            }
        });
    }

    private void getUserInfo() {
        Map<String, Object> params = new HashMap<>();
        params.put("userid", MainApplication.getContext().getUserUId());
        HttpAction.getInstance().getUserInfoById(params).subscribe(new BaseObserver<UserInfoBean>() {
            @Override
            public void onSuccess(UserInfoBean user) {
                if (user.getCode() != 0) {
                    ToastUtil.show(context, "个人信息获取失败");
                    return;
                }
                setUserInfo(user.getData());
            }

            @Override
            public void onError(String message) {
                ToastUtil.show(context, message);
            }
        });
    }

    private void getTownList() {
        HttpAction.getInstance().getTownList().subscribe(new BaseObserver<UserTownListBean>() {
            @Override
            public void onSuccess(UserTownListBean bean) {
                if (bean.getCode() != 0) {
                    ToastUtil.show(context, bean.getMsg());
                    return;
                }
                townList = bean.getData();
                if (townList == null || townList.size() == 0) {
                    ToastUtil.show(context, "暂未获取到乡镇信息");
                    return;
                }

                List<String> townStrList = new ArrayList<>();
                for (int i = 0; i < townList.size(); i++) {
                    townStrList.add(townList.get(i).getName());
                }
                townPicker.setTownData(townStrList);

                getVillageList(bean.getData().get(0).getId());
            }

            @Override
            public void onError(String message) {
                WaitUI.cancel();
                ToastUtil.show(context, message);
            }
        });
    }

    private void getVillageList(int id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        HttpAction.getInstance().getVillageList(params).subscribe(new BaseObserver<UserVillageListBean>() {
            @Override
            public void onSuccess(UserVillageListBean bean) {
                WaitUI.cancel();
                if (bean.getCode() != 0) {
                    ToastUtil.show(context, bean.getMsg());
                    return;
                }

                villageList = bean.getData();

                List<String> villageStrList = new ArrayList<>();

                for (int i = 0; i < villageList.size(); i++) {
                    villageStrList.add(villageList.get(i).getName());
                }
                townPicker.setVillageData(villageStrList);
            }

            @Override
            public void onError(String message) {
                WaitUI.cancel();
                ToastUtil.show(context, message);
            }
        });
    }

    private void getDepartmentList() {
        HttpAction.getInstance().getDepartmentList().subscribe(new BaseObserver<UserDepartmentListBean>() {
            @Override
            public void onSuccess(UserDepartmentListBean bean) {
                if (bean.getCode() != 0) {
                    ToastUtil.show(context, bean.getMsg());
                    return;
                }
                departmentList = bean.getData();
                departmentPicker.setMenuData(departmentList);
            }

            @Override
            public void onError(String message) {
                ToastUtil.show(context, message);
            }
        });
    }

    private void getPostList() {
        HttpAction.getInstance().getPostList().subscribe(new BaseObserver<UserPostListBean>() {
            @Override
            public void onSuccess(UserPostListBean bean) {
                if (bean.getCode() != 0) {
                    ToastUtil.show(context, bean.getMsg());
                    return;
                }
                postList = bean.getData();
            }

            @Override
            public void onError(String message) {
                ToastUtil.show(context, message);
            }
        });
    }

    private void setUserInfo(UserInfoBean.DataBean userInfo) {
        et_real_name.setText(userInfo.getRealname());
        et_nick_name.setText(userInfo.getUsername());
        et_id_card.setText(userInfo.getIDcard());
        tv_sex.setText(userInfo.getGender());
        tv_phone_num.setText(userInfo.getTelephone());
//        tv_local.setText(userInfo.getGender());
        tv_email.setText(userInfo.getMail());
//        tv_department.setText(userInfo.getDepartmentid());
        et_age.setText(String.valueOf(userInfo.getAge()));
        tv_job.setText(userInfo.getPostname());
        createtime = userInfo.getCreatetime();
        password = userInfo.getPassword();
        state = userInfo.getState();
    }

    private void setUserLocal(String countyName, String townName, int townPosition, String villageName, int villagePosition) {
        tv_local.setText(String.format("%s  %s  %s", countyName, townName, villageName));
        if (townList != null && townList.size() > 0) {
            this.townId = townList.get(townPosition).getId();
        }
        if (villageList != null && villageList.size() > 0) {
            this.villageId = villageList.get(villagePosition).getId();
        }
    }

    private void setUserDepartment(int menuId, String menuName, String subMenuName) {
        tv_department.setText(String.format("%s  %s", menuName, subMenuName));
        this.departmentId = menuId;
    }

    private void modifyUserInfo() {
        String real_name = et_real_name.getText().toString().trim();
        String nick_name = et_nick_name.getText().toString().trim();
        String id_card = et_id_card.getText().toString().trim();
        String sex = tv_sex.getText().toString().trim();
        String phone_num = tv_phone_num.getText().toString().trim();
        String local = tv_local.getText().toString().trim();
        String email = tv_email.getText().toString().trim();
        String department = tv_department.getText().toString().trim();
        String age = et_age.getText().toString().trim();
        String job = tv_job.getText().toString().trim();
        if (TextUtils.isEmpty(real_name)) {
            ToastUtil.show(context, "请输入真实姓名");
            return;
        }
        if (TextUtils.isEmpty(nick_name)) {
            ToastUtil.show(context, "请输入昵称");
            return;
        }
        if (TextUtils.isEmpty(id_card)) {
            ToastUtil.show(context, "请输入身份证号");
            return;
        }
        if (TextUtils.isEmpty(sex)) {
            ToastUtil.show(context, "请选择性别");
            return;
        }
        if (TextUtils.isEmpty(phone_num)) {
            ToastUtil.show(context, "请输入电话号码");
            return;
        }
        if ("请选择地区".equals(local) || TextUtils.isEmpty(local)) {
            ToastUtil.show(context, "请选择地区");
            return;
        }
        if (TextUtils.isEmpty(email)) {
            ToastUtil.show(context, "请输入邮箱地址");
            return;
        }
        if ("请选择部门".equals(department) || TextUtils.isEmpty(department)) {
            ToastUtil.show(context, "请选择部门");
            return;
        }
        if (TextUtils.isEmpty(age)) {
            ToastUtil.show(context, "请输入年龄");
            return;
        }
        if ("请选择职位".equals(job) || TextUtils.isEmpty(job)) {
            ToastUtil.show(context, "请选择职位");
            return;
        }
        Map<String, String> params = new HashMap<>();
        params.put("userid", String.valueOf(MainApplication.getContext().getUserUId()));
        params.put("departmentid", String.valueOf(departmentId));
        params.put("postid", String.valueOf(postId));
        params.put("username", nick_name);
        params.put("password", password);
        params.put("realname", real_name);
        params.put("gender", sex);
        params.put("telephone", phone_num);
        params.put("createtime", createtime);
        params.put("state", String.valueOf(state));
        params.put("Age", age);
        params.put("IDcard", id_card);
        params.put("Mail", email);
        params.put("XAreaId", String.valueOf(townId));
        params.put("CAreaId", String.valueOf(villageId));
        params.put("postname", postName);
        HttpAction.getInstance().commitUserInfo(params).subscribe(new BaseObserver<BaseResponseBean>() {
            @Override
            public void onSuccess(BaseResponseBean bean) {
                if (bean.getCode() != 0) {
                    ToastUtil.show(context, bean.getMsg());
                    return;
                }
                MainApplication.getContext().setUserName(nick_name);
                ToastUtil.show(context, "信息修改成功");
                finish();
            }

            @Override
            public void onError(String message) {
                ToastUtil.show(context, message);
            }
        });
    }

    private void initTownPicker() {
        if (townPicker == null) {
            townPicker = new TownPicker(context);
        }
    }

    private void initDepartmentPicker() {
        if (departmentPicker == null) {
            departmentPicker = new DepartmentPicker(context);
        }
    }

    private void showSexPicker() {
        if (sexPicker == null) {
            sexPicker = new TypePicker(context);
        }
        List<String> sexList = new ArrayList<>();
        sexList.add("男");
        sexList.add("女");
        sexPicker.setItemData(sexList);
        sexPicker.show();
        sexPicker.setTypeListener(new TypePicker.OnTypeListener() {
            @Override
            public void onTypeSelected(String type, int position) {
                tv_sex.setText(type);
            }
        });
    }

    private void showPostPicker() {
        if (postPicker == null) {
            postPicker = new TypePicker(context);
        }
        List<String> postStrList = new ArrayList<>();
        if (postList==null||postList.size()==0){
            ToastUtil.show(context,"未获取到职位信息");
            return;
        }
        for (int i = 0; i < postList.size(); i++) {
            postStrList.add(postList.get(i).getPostname());
        }
        postPicker.setItemData(postStrList);
        postPicker.show();
        postPicker.setTypeListener(new TypePicker.OnTypeListener() {
            @Override
            public void onTypeSelected(String type, int position) {
                postName = type;
                tv_job.setText(type);
                postId = postList.get(position).getPostid();
            }
        });
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_modify_info;
    }
}