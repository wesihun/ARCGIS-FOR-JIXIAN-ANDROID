package com.winto.develop.ThreeTones.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.winto.develop.ThreeTones.R;
import com.winto.develop.ThreeTones.util.DateUtil;
import com.winto.develop.ThreeTones.wight.wheel.MyDatePickerView;

import java.util.Calendar;
import java.util.Date;

import static com.winto.develop.ThreeTones.wight.wheel.MyDatePickerView.SHOW_YEAR_MONTH_DAY;

/**
 * Created by zyp on 2018/3/12.
 * note:首页详情弹窗
 */
public class ChooseDateDialog extends Dialog {

    private Context context;
    private OnBtnClickListener listener;
    private TextView tv_cancel;
    private TextView tv_ok;
    private MyDatePickerView mv_date_picker;

    private String selectDate;

    public ChooseDateDialog(Context context) {
        super(context, R.style.BottomDialogTheme);
        this.context = context;
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_choose_date, null);
        tv_cancel = view.findViewById(R.id.tv_cancel);
        tv_ok = view.findViewById(R.id.tv_ok);
        mv_date_picker = view.findViewById(R.id.mv_date_picker);
        setContentView(view);
        setDataView();
        initClick();
    }

    private void initClick() {
        tv_cancel.setOnClickListener(v -> dismiss());

        tv_ok.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(selectDate);
            }
            dismiss();
        });

        mv_date_picker.setOnDateSelectedListener(date -> {
            selectDate = DateUtil.dateToStr(DateUtil.YYYY_MM_DD, date);
        });
    }

    private void setDataView() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 1900);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date startDate = calendar.getTime();
        mv_date_picker.configShowUI(SHOW_YEAR_MONTH_DAY);
        mv_date_picker.setDateArea(startDate, date);
        mv_date_picker.updateSelectedDate(date);
    }

    @Override
    public void show() {
        super.show();
        Window window = getWindow();
        if (window == null) {
            return;
        }

        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.BOTTOM;
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        window.setWindowAnimations(R.style.pop_win_anim_style);
        window.setAttributes(params);
    }

    public interface OnBtnClickListener {
        void onItemClick(String text);
    }

    public void setOnBtnClickListener(OnBtnClickListener listener) {
        this.listener = listener;
    }
}
