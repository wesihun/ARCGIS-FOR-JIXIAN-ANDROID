package com.winto.develop.ThreeTones.base;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import java.util.Objects;

/**
 * Fragment基类
 * <p>
 * Created by zyp on 2018/2/6.
 */
public abstract class BaseFragment extends Fragment {
    protected Context context;

    /**
     * 当fragment与activity发生关联时调用
     *
     * @param context 与之相关联的activity
     */
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(setLayout(), null);
    }

    /**
     * 绑定布局
     *
     * @return layout布局
     */
    protected abstract int setLayout();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initAdapter();
        initClick();
    }

    /**
     * 初始化组件
     */
    protected abstract void initView();

    protected void initAdapter() {}

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 设置数据等逻辑代码
     */
    protected abstract void initData();

    protected void initClick() {}

    /**
     * 简化findViewById
     */
    protected <T extends View> T findViewById(int resId) {
        return Objects.requireNonNull(getView()).findViewById(resId);
    }

    /**
     * intent跳转
     */
    protected void toClass(Context context, Class<? extends BaseActivity> clazz) {
        Intent intent = new Intent(context, clazz);
        context.startActivity(intent);
    }

    /**
     * intent带值跳转
     */
    protected void toClass(Context context, Class<? extends BaseActivity> clazz, Bundle bundle) {
        Intent intent = new Intent(context, clazz);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    /**
     * 带返回值的跳转
     */
    protected void toClass(Context context, Class<? extends BaseActivity> clazz, Bundle bundle, int requestCode) {
        Intent intent = new Intent(context, clazz);
        intent.putExtras(bundle);
        Objects.requireNonNull(getActivity()).startActivityForResult(intent, requestCode);
    }

    public boolean checkPermission(@NonNull String permission) {
        return ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED;
    }
}