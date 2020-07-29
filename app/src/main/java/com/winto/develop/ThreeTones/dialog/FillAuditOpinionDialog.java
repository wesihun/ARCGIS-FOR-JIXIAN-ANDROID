package com.winto.develop.ThreeTones.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.winto.develop.ThreeTones.R;
import com.winto.develop.ThreeTones.util.ToastUtil;


/**
 * Created by zyp on 2018/3/12.
 * note:审核意见填写dialog
 */

public class FillAuditOpinionDialog extends Dialog {

    private OnClickRateDialog onClickRateListener;
    private EditText et_opinion;

    public FillAuditOpinionDialog(Context context) {
        super(context);
        setCustomDialog();
    }

    private void setCustomDialog() {
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_fill_audit_opinion, null);
        et_opinion = mView.findViewById(R.id.et_opinion);

        TextView leftButton = mView.findViewById(R.id.button1);
        TextView rightButton = mView.findViewById(R.id.button2);

        leftButton.setOnClickListener(v -> {
            if (onClickRateListener != null) {
                if (TextUtils.isEmpty(et_opinion.getText().toString().trim())){
                    ToastUtil.show(getContext(),"请填写审核意见");
                    return;
                }
                onClickRateListener.onClickLeft(et_opinion.getText().toString().trim());
                dismiss();
            }
        });

        rightButton.setOnClickListener(v -> {
            if (onClickRateListener != null) {
                if (TextUtils.isEmpty(et_opinion.getText().toString().trim())){
                    ToastUtil.show(getContext(),"请填写审核意见");
                    return;
                }
                onClickRateListener.onClickRight(et_opinion.getText().toString().trim());
                dismiss();
            }
        });

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.setContentView(mView);
    }

    @Override
    public void show() {
        super.show();
        Window window = getWindow();
        if (window == null) {
            return;
        }

        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.CENTER;
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        window.setAttributes(params);
        et_opinion.setText("");
    }

    public interface OnClickRateDialog {
        void onClickLeft(String opinion);

        void onClickRight(String opinion);
    }

    public void setOnClickRateDialog(OnClickRateDialog onClickRateListener) {
        this.onClickRateListener = onClickRateListener;
    }
}
