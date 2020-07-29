package com.winto.develop.ThreeTones.wight;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.winto.develop.ThreeTones.R;
import com.winto.develop.ThreeTones.wight.wheelview.OnItemSelectedListener;
import com.winto.develop.ThreeTones.wight.wheelview.WheelView;

import java.util.List;

public class TownPicker extends Dialog implements View.OnClickListener {

    private List<String> townList;
    private List<String> villageList;

    private Context context;
    private View view;
    private WheelView wv_county;
    private WheelView wv_town;
    private WheelView wv_village;
    private TextView mTvConfirm;
    private TextView mTvCancel;
    private OnTownChooseListener onTownChooseListener;
    private OnConfirmListener onConfirmListener;
    private String[] county;

    public TownPicker(Context context) {
        super(context, R.style.transparentWindowStyle);
        this.context = context;
        view = LayoutInflater.from(context).inflate(R.layout.layout_town_picker, null);
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
        wv_county = view.findViewById(R.id.wv_county);
        wv_town = view.findViewById(R.id.wv_town);
        wv_village = view.findViewById(R.id.wv_village);
        mTvConfirm = view.findViewById(R.id.tv_confirm);
        mTvCancel = view.findViewById(R.id.tv_cancel);

        /*
         * 设置可见条目数量
         * 注：因为WheelView是圆形，最上面和最下面刚好在圆顶和圆底，
         * 所以最上面和最下面两个看不到，因此可见数量要比设置的少2个
         */
        wv_county.setVisibleItemCount(9);
        wv_town.setVisibleItemCount(9);
        wv_village.setVisibleItemCount(9);

        county = new String[]{"集贤县"};
        wv_county.setItems(county);
        wv_county.setCurrentItem(0);
    }

    public void setTownData(List<String> townList) {
        this.townList = townList;
        wv_town.setItems(this.townList);
        wv_town.setCurrentItem(0);
    }

    public void setVillageData(List<String> villageList) {
        this.villageList = villageList;
        wv_village.setItems(this.villageList);
        wv_village.setCurrentItem(0);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv_confirm) {
            if (onConfirmListener != null) {
                String countyName = county[wv_county.getCurrentItem()];
                String townName = "";
                int townPosition = 0;
                if (townList != null && townList.size() > 0) {
                    townName = townList.get(wv_town.getCurrentItem());
                    townPosition = wv_town.getCurrentItem();
                }
                String villageName = "";
                int villagePosition = 0;
                if (villageList != null && villageList.size() > 0) {
                    villageName = villageList.get(wv_village.getCurrentItem());
                    villagePosition = wv_village.getCurrentItem();
                }

                onConfirmListener.onConfirm(countyName, townName, townPosition, villageName, villagePosition);
            }
        }
        cancel();
    }

    /**
     * 回调接口
     */
    public interface OnTownChooseListener {
        void onTownSelected(String item, int position);
    }

    public void setTownListener(OnTownChooseListener onTownChooseListener) {
        this.onTownChooseListener = onTownChooseListener;
    }

    public interface OnConfirmListener {
        void onConfirm(String countyName, String townName, int townPosition, String villageName, int villagePosition);
    }

    public void setOnConfirmListener(OnConfirmListener onConfirmListener) {
        this.onConfirmListener = onConfirmListener;
    }

    /**
     * 设置监听
     */
    private void setListener() {

        wv_town.setOnItemSelectedListener(index -> {
            if (onTownChooseListener != null && townList.size() != 0) {
                String mCurrentTypeName = townList.get(index);
                onTownChooseListener.onTownSelected(mCurrentTypeName, index);
            }
        });

        wv_village.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {

            }
        });

        mTvConfirm.setOnClickListener(this);
        mTvCancel.setOnClickListener(this);
    }

}
