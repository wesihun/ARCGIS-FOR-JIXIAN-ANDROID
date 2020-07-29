package com.winto.develop.ThreeTones.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.winto.develop.ThreeTones.R;
import com.winto.develop.ThreeTones.util.DensityUtil;

/**
 * Created by zyp on 2018/3/12.
 * note:首页详情弹窗
 */
public class SearchDialog extends Dialog {

    private Context context;
    private OnBtnClickListener listener;
    private ImageView iv_dismiss;

    public SearchDialog(Context context) {
        super(context, R.style.BottomDialogTheme);
        this.context = context;
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_search, null);
        iv_dismiss = view.findViewById(R.id.iv_dismiss);
        setContentView(view);
        setAttribute();
        initClick();
    }

    private void setAttribute() {
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        Window window = this.getWindow();
        if (window == null) {
            return;
        }

        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.BOTTOM;
        lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
        lp.height = DensityUtil.getScreenHeight(context) - DensityUtil.dip2px(context, 40);

        // 设置显示位置
        onWindowAttributesChanged(lp);
    }

    private void initClick() {
        iv_dismiss.setOnClickListener(v -> dismiss());
    }

    public interface OnBtnClickListener {
        void onItemClick(String text);
    }

    public void setOnBtnClickListener(OnBtnClickListener listener) {
        this.listener = listener;
    }
}
