package com.winto.develop.ThreeTones.dialog;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.winto.develop.ThreeTones.R;
import com.winto.develop.ThreeTones.adapter.TownListAdapter;
import com.winto.develop.ThreeTones.adapter.VillageListAdapter;
import com.winto.develop.ThreeTones.base.BaseObserver;
import com.winto.develop.ThreeTones.bean.TownListBean;
import com.winto.develop.ThreeTones.http.HttpAction;
import com.winto.develop.ThreeTones.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class ChooseTownPopup extends PopupWindow {
    private Context context;
    private View parent;
    private View view;

    private TextView btn_reset;
    private TextView btn_confirm;
    private View view_dismiss;

    private OnButtonClickListener buttonClickListener;
    private TownListAdapter townAdapter;
    private VillageListAdapter villageAdapter;
    private RecyclerView rv_town_list;
    private RecyclerView rv_village_list;

    private List<TownListBean> townList;
    private List<TownListBean.SubAdministrationsBean> villageList;

    private TownListBean chooseTown;
    private TownListBean.SubAdministrationsBean chooseVillage;

    private String chooseName;
    private String proviceCode;

    public ChooseTownPopup(Context context, View parent) {
        this.context = context;
        this.parent = parent;
        initPopup();
        initView();
        initAdapter();
        initClick();
        getTownList();
    }

    private void initPopup() {
        view = View.inflate(context, R.layout.popup_choose_label, null);
        setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        setBackgroundDrawable(new BitmapDrawable());
        setFocusable(true);
        setOutsideTouchable(true);
        setContentView(view);
    }

    private void initView() {
        btn_reset = view.findViewById(R.id.btn_reset);
        btn_confirm = view.findViewById(R.id.btn_confirm);
        view_dismiss = view.findViewById(R.id.view_dismiss);
        rv_town_list = view.findViewById(R.id.rv_town_list);
        rv_village_list = view.findViewById(R.id.rv_country_list);
    }

    private void initAdapter() {
        townList = new ArrayList<>();
        townAdapter = new TownListAdapter(context, townList);
        rv_town_list.setLayoutManager(new LinearLayoutManager(context));
        rv_town_list.setAdapter(townAdapter);

        villageList = new ArrayList<>();
        villageAdapter = new VillageListAdapter(context, villageList);
        rv_village_list.setLayoutManager(new LinearLayoutManager(context));
        rv_village_list.setAdapter(villageAdapter);
    }

    private void initClick() {
        view_dismiss.setOnClickListener(view -> dismiss());

        btn_reset.setOnClickListener(view -> {
            if (buttonClickListener != null) {
                buttonClickListener.onClearBtnClick();
            }
        });

        btn_confirm.setOnClickListener(view -> {
            if (buttonClickListener != null) {
                buttonClickListener.onConfirmBtnClick(chooseTown, chooseVillage, chooseName, proviceCode);
            }
        });

        townAdapter.setOnItemClickListener((town, position) -> {
            chooseName = town.getName();
            townAdapter.setClickColor(position);
            refreshVillage(town);
            villageAdapter.setClickColor(-1);
            chooseTown = town;
            proviceCode = town.getTreeCode().substring(0, 9);
        });

        villageAdapter.setOnItemClickListener((village, position) -> {
            chooseName = village.getName();
            villageAdapter.setClickColor(position);
            chooseVillage = village;
            proviceCode = village.getTreeCode();
        });
    }

    public void show() {
        showAsDropDown(parent, 0, 0, Gravity.CENTER);
        update();
    }

    private void getTownList() {
        HttpAction.getInstance().getAdministration().subscribe(new BaseObserver<List<TownListBean>>() {
            @Override
            public void onSuccess(List<TownListBean> bean) {
                if (bean == null || bean.size() == 0) {
                    return;
                }
                townList.clear();
                townList.addAll(bean);
                townAdapter.notifyDataSetChanged();
                townAdapter.setClickColor(0);
                chooseTown = bean.get(0);
                chooseName = bean.get(0).getName();
                proviceCode = bean.get(0).getTreeCode().substring(0, 9);
                refreshVillage(bean.get(0));
            }

            @Override
            public void onError(String message) {
                ToastUtil.show(context, message);
            }
        });
    }

    private void refreshVillage(TownListBean town) {
        villageList.clear();
        villageList.addAll(town.getSubAdministrations());
        villageAdapter.notifyDataSetChanged();
    }

    public interface OnButtonClickListener {
        void onClearBtnClick();

        void onConfirmBtnClick(TownListBean town, TownListBean.SubAdministrationsBean village, String name, String proviceCode);
    }

    public void setOnButtonClickListener(OnButtonClickListener listener) {
        this.buttonClickListener = listener;
    }
}
