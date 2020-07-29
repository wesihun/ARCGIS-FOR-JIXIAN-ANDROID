package com.winto.develop.ThreeTones.wight;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.winto.develop.ThreeTones.R;
import com.winto.develop.ThreeTones.bean.UserDepartmentListBean;
import com.winto.develop.ThreeTones.util.ToastUtil;
import com.winto.develop.ThreeTones.wight.wheelview.OnItemSelectedListener;
import com.winto.develop.ThreeTones.wight.wheelview.WheelView;

import java.util.ArrayList;
import java.util.List;

public class DepartmentPicker extends Dialog implements View.OnClickListener {

    private View view;
    private WheelView wv_menu;
    private WheelView wv_submenu;
    private TextView mTvConfirm;
    private TextView mTvCancel;
    private OnConfirmListener onConfirmListener;

    private List<UserDepartmentListBean.DataBean> menuList;
    private List<UserDepartmentListBean.DataBean.SubMenueBean> subMenuList;

    private List<String> menuStrList;
    private List<String> subMenuStrList;

    public DepartmentPicker(Context context) {
        super(context, R.style.transparentWindowStyle);
        view = LayoutInflater.from(context).inflate(R.layout.layout_department_picker, null);
        initView();
        setListener();
        this.setContentView(view);
        this.setCanceledOnTouchOutside(true);
        //从底部弹出
        Window window = this.getWindow();
        if (window != null) {
            window.setGravity(Gravity.BOTTOM);  //此处可以设置dialog显示的位置
            window.setWindowAnimations(R.style.windowAnimationStyle);  //添加动画
            WindowManager.LayoutParams params = window.getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(params);
        }
    }

    private void initView() {
        wv_menu = view.findViewById(R.id.wv_menu);
        wv_submenu = view.findViewById(R.id.wv_submenu);
        mTvConfirm = view.findViewById(R.id.tv_confirm);
        mTvCancel = view.findViewById(R.id.tv_cancel);

        /*
         * 设置可见条目数量
         * 注：因为WheelView是圆形，最上面和最下面刚好在圆顶和圆底，
         * 所以最上面和最下面两个看不到，因此可见数量要比设置的少2个
         */
        wv_menu.setVisibleItemCount(9);
        wv_submenu.setVisibleItemCount(9);
    }

    public void setMenuData(List<UserDepartmentListBean.DataBean> menuList) {
        this.menuList = menuList;
        menuStrList = new ArrayList<>();
        for (int i = 0; i < menuList.size(); i++) {
            menuStrList.add(menuList.get(i).getMenuename());
        }
        wv_menu.setItems(menuStrList);
        wv_menu.setCurrentItem(0);
        setSubMenuData(menuList.get(0).getSubMenue());
    }

    public void setSubMenuData(List<UserDepartmentListBean.DataBean.SubMenueBean> subMenuList) {
        this.subMenuList = subMenuList;
        subMenuStrList = new ArrayList<>();
        for (int i = 0; i < subMenuList.size(); i++) {
            subMenuStrList.add(subMenuList.get(i).getMenuename());
        }
        if (subMenuStrList.size() == 0) {
            subMenuStrList.add("");
            wv_submenu.setItems(subMenuStrList);
        } else {
            wv_submenu.setItems(subMenuStrList);
        }
        wv_submenu.setCurrentItem(0);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv_confirm) {
            if (onConfirmListener != null) {
                int menuId = 0;
                String menuName = "";
                if (menuList != null && menuList.size() > 0) {
                    menuId = menuList.get(wv_menu.getCurrentItem()).getMenueid();
                    menuName = menuList.get(wv_menu.getCurrentItem()).getMenuename();
                }

                String subMenuName = "";
                if (subMenuList != null && subMenuList.size() > 0) {
                    menuId = subMenuList.get(wv_submenu.getCurrentItem()).getMenueid();
                    subMenuName = subMenuList.get(wv_submenu.getCurrentItem()).getMenuename();
                }

                if (menuList != null && menuList.size() > 0 && subMenuList != null && subMenuList.size() > 0) {
                    onConfirmListener.onConfirm(menuId, menuName, subMenuName);
                } else {
                    ToastUtil.show(getContext(), "未获取到部门信息");
                }
            }
        }
        cancel();
    }

    /**
     * 回调接口
     */

    public interface OnConfirmListener {
        void onConfirm(int menuId, String menuName, String subMenuName);
    }

    public void setOnConfirmListener(OnConfirmListener onConfirmListener) {
        this.onConfirmListener = onConfirmListener;
    }

    /**
     * 设置监听
     */
    private void setListener() {

        wv_menu.setOnItemSelectedListener(index -> {
            if (menuList.size() > 0) {
                List<UserDepartmentListBean.DataBean.SubMenueBean> subMenu = menuList.get(index).getSubMenue();
                Log.e("TAG", "setListener: " + subMenu.size());
                setSubMenuData(subMenu);
            }
        });

        wv_submenu.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {

            }
        });

        mTvConfirm.setOnClickListener(this);
        mTvCancel.setOnClickListener(this);
    }

}
