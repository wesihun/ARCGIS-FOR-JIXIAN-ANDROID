package com.winto.develop.ThreeTones.http;


import android.text.TextUtils;

import com.winto.develop.ThreeTones.base.BaseResponse;
import com.winto.develop.ThreeTones.bean.BaseResponseBean;
import com.winto.develop.ThreeTones.bean.BrowseCensusListBean;
import com.winto.develop.ThreeTones.bean.DepartmentListBean;
import com.winto.develop.ThreeTones.bean.DocTabListBean;
import com.winto.develop.ThreeTones.bean.DownloadManageListBean;
import com.winto.develop.ThreeTones.bean.LandCateBeanNew;
import com.winto.develop.ThreeTones.bean.LandInfoChartBean;
import com.winto.develop.ThreeTones.bean.LayerDataListBean;
import com.winto.develop.ThreeTones.bean.LeafNodeInfoBean;
import com.winto.develop.ThreeTones.bean.LoginBean;
import com.winto.develop.ThreeTones.bean.ManageListBean;
import com.winto.develop.ThreeTones.bean.NoticeListBean;
import com.winto.develop.ThreeTones.bean.PostListBean;
import com.winto.develop.ThreeTones.bean.RoleListBean;
import com.winto.develop.ThreeTones.bean.SpecialMenuUpdateListBean;
import com.winto.develop.ThreeTones.bean.StatisticalAnalysisBean;
import com.winto.develop.ThreeTones.bean.StatisticalAnalysisMenuListBean;
import com.winto.develop.ThreeTones.bean.TechnicalStandardListBean;
import com.winto.develop.ThreeTones.bean.TownListBean;
import com.winto.develop.ThreeTones.bean.UserDepartmentListBean;
import com.winto.develop.ThreeTones.bean.UserInfoBean;
import com.winto.develop.ThreeTones.bean.UserInfoListBean;
import com.winto.develop.ThreeTones.bean.UserPostListBean;
import com.winto.develop.ThreeTones.bean.UserTownListBean;
import com.winto.develop.ThreeTones.bean.UserVillageListBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;


public class HttpAction {

    public static HttpAction getInstance() {
        return HttpActionHolder.instance;
    }

    private static class HttpActionHolder {
        private static HttpAction instance = new HttpAction();
    }

    private RequestBody getBody(String params) {
        return RequestBody.create(MediaType.parse("text/html; charset=utf-8"), params);
    }

    private <T> Flowable<T> applySchedulers(Flowable<T> responseFlowable) {
        return responseFlowable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<LoginBean> login(Map<String, Object> params) {
        return applySchedulers(HttpClient.getHttpService().login(params));
    }

    public Flowable<List<UserInfoListBean>> getUserInfo() {
        return applySchedulers(HttpClient.getHttpService().getUserInfo());
    }

    public Flowable<UserInfoBean> getUserInfoById(Map<String, Object> params) {
        return applySchedulers(HttpClient.getHttpService().getUserInfoById(params));
    }

    public Flowable<List<SpecialMenuUpdateListBean>> getSpecialMenu() {
        return applySchedulers(HttpClient.getHttpService().getSpecialMenu());
    }

    public Flowable<List<SpecialMenuUpdateListBean>> getSpecialMenuUpdate() {
        return applySchedulers(HttpClient.getHttpService().getSpecialMenuUpdate());
    }

    public Flowable<List<TownListBean>> getAdministration() {
        return applySchedulers(HttpClient.getHttpService().getAdministration());
    }

    public Flowable<LayerDataListBean> getLayerData(Map<String, Object> params) {
        return applySchedulers(HttpClient.getHttpService().getLayerData(params));
    }

    public Flowable<BaseResponse> getAnalysisData(Map<String, Object> params) {
        return applySchedulers(HttpClient.getHttpService().getAnalysisData(params));
    }

    public Flowable<BrowseCensusListBean> getBrowseCensus(Map<String, Object> params) {
        return applySchedulers(HttpClient.getHttpService().getBrowseCensus(params));
    }

    public Flowable<UserTownListBean> getTownList() {
        return applySchedulers(HttpClient.getHttpService().getTownList());
    }

    public Flowable<UserVillageListBean> getVillageList(Map<String, Object> params) {
        return applySchedulers(HttpClient.getHttpService().getVillageList(params));
    }

    public Flowable<UserDepartmentListBean> getDepartmentList() {
        return applySchedulers(HttpClient.getHttpService().getDepartmentList());
    }

    public Flowable<UserPostListBean> getPostList() {
        return applySchedulers(HttpClient.getHttpService().getPostList());
    }

    public Flowable<BaseResponseBean> commitUserInfo(Map<String, String> params) {
        return applySchedulers(HttpClient.getHttpService().commitUserInfo(generateRequestBody(params)));
    }

    public Flowable<DownloadManageListBean> getDownloadList(Map<String, Object> params) {
        return applySchedulers(HttpClient.getHttpService().getDownloadList(params));
    }

    public Flowable<ManageListBean> getManageList(Map<String, Object> params) {
        return applySchedulers(HttpClient.getHttpService().getManageList(params));
    }

    public Flowable<BaseResponseBean> examine(Map<String, String> params) {
        return applySchedulers(HttpClient.getHttpService().examine(generateRequestBody(params)));
    }

    public Flowable<DocTabListBean> getDocTabList() {
        return applySchedulers(HttpClient.getHttpService().getDocTabList());
    }

    public Flowable<TechnicalStandardListBean> getDocList(Map<String, Object> params) {
        return applySchedulers(HttpClient.getHttpService().getDocList(params));
    }

    public Flowable<List<LandCateBeanNew>> getMenu() {
        return applySchedulers(HttpClient.getHttpService().getMenu());
    }

    public Flowable<List<StatisticalAnalysisMenuListBean>> getAnalysisMenu() {
        return applySchedulers(HttpClient.getHttpService().getAnalysisMenu());
    }

    public Flowable<LeafNodeInfoBean> getMenuByMenuId(Map<String, Object> params) {
        return applySchedulers(HttpClient.getHttpService().getMenuByMenuId(params));
    }

    public Flowable<List<LandInfoChartBean>> getSecondCategoryCode(Map<String, Object> params) {
        return applySchedulers(HttpClient.getHttpService().getSecondCategoryCode(params));
    }

    public Flowable<StatisticalAnalysisBean> getAnalysisTotalRecord(Map<String, Object> params) {
        return applySchedulers(HttpClient.getHttpService().getAnalysisTotalRecord(params));
    }

    public Flowable<StatisticalAnalysisBean> getAnalysisTotalArea(Map<String, Object> params) {
        return applySchedulers(HttpClient.getHttpService().getAnalysisTotalArea(params));
    }

    public Flowable<NoticeListBean> getNoticeList(Map<String, Object> params) {
        return applySchedulers(HttpClient.getHttpService().getNoticeList(params));
    }

    public Flowable<BaseResponseBean> applyDownload(Map<String, String> params) {
        return applySchedulers(HttpClient.getHttpService().applyDownload(generateRequestBody(params)));
    }

    public Flowable<List<DepartmentListBean>> getDepartment() {
        return applySchedulers(HttpClient.getHttpService().getDepartment());
    }

    public Flowable<List<PostListBean>> getPost() {
        return applySchedulers(HttpClient.getHttpService().getPost());
    }

    public Flowable<List<RoleListBean>> getRole() {
        return applySchedulers(HttpClient.getHttpService().getRole());
    }

    public Flowable<LoginBean> register(Map<String, Object> params) {
        return applySchedulers(HttpClient.getHttpService().register(params));
    }

    /**
     * 转换为 form-data
     */
    private static Map<String, RequestBody> generateRequestBody(Map<String, String> requestDataMap) {
        Map<String, RequestBody> requestBodyMap = new HashMap<>();
        for (String key : requestDataMap.keySet()) {
            MediaType type = MediaType.parse("multipart/form-data");
            RequestBody requestBody = RequestBody.create(type, TextUtils.isEmpty(requestDataMap.get(key)) ? "" : Objects.requireNonNull(requestDataMap.get(key)));
            requestBodyMap.put(key, requestBody);
        }
        return requestBodyMap;
    }
}
