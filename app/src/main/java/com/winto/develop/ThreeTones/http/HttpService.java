package com.winto.develop.ThreeTones.http;

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
import com.winto.develop.ThreeTones.constant.ConstantData;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface HttpService {
    @Streaming
    @GET
    Flowable<ResponseBody> downloadFile(@Url String fileUrl);

    @Multipart
    @POST("File/UploadSampleImage")
    Flowable<ResponseBody> uploadFiles(@Part MultipartBody.Part file);

    @Multipart
    @POST()
    Flowable<ResponseBody> uploadFiles(@Url String url, @Part MultipartBody.Part file);

    @Multipart
    @POST()
    Flowable<ResponseBody> UploadCompleteImage(@Url String url, @Part MultipartBody.Part file);

    @Multipart
    @POST("File/UploadSampleImage")
    Flowable<ResponseBody> uploadFiles(@Part List<MultipartBody.Part> parts);

    @GET(ConstantData.HOST1 + "/login")
    Flowable<LoginBean> login(@QueryMap Map<String, Object> params);

    @POST(ConstantData.HOST1 + "/getUserInfo")
    Flowable<List<UserInfoListBean>> getUserInfo();

    @GET(ConstantData.HOST3 + "/arcgis/PersonalCenter/GetPerInfo")
    Flowable<UserInfoBean> getUserInfoById(@QueryMap Map<String, Object> params);

    @POST(ConstantData.HOST1 + "/getSpecialMenue")
    Flowable<List<SpecialMenuUpdateListBean>> getSpecialMenu();

    @POST(ConstantData.HOST1 + "/getSpecialMenueUpdate")
    Flowable<List<SpecialMenuUpdateListBean>> getSpecialMenuUpdate();

    @POST(ConstantData.HOST1 + "/getAdministration")
    Flowable<List<TownListBean>> getAdministration();

    @GET(ConstantData.HOST2 + "/getLayerData")
    Flowable<LayerDataListBean> getLayerData(@QueryMap Map<String, Object> params);

    @GET(ConstantData.HOST2 + "/getAnalysisData")
    Flowable<BaseResponse> getAnalysisData(@QueryMap Map<String, Object> params);

    @GET(ConstantData.HOST3 + "/arcgis/Other/GetLog")
    Flowable<BrowseCensusListBean> getBrowseCensus(@QueryMap Map<String, Object> params);

    @GET(ConstantData.HOST3 + "/arcgis/PersonalCenter/GetAreaInfo1")
    Flowable<UserTownListBean> getTownList();

    @GET(ConstantData.HOST3 + "/arcgis/PersonalCenter/GetAreaInfo2")
    Flowable<UserVillageListBean> getVillageList(@QueryMap Map<String, Object> params);

    @GET(ConstantData.HOST3 + "/arcgis/PersonalCenter/GetDepList")
    Flowable<UserDepartmentListBean> getDepartmentList();

    @GET(ConstantData.HOST3 + "/arcgis/PersonalCenter/GetPostList")
    Flowable<UserPostListBean> getPostList();

    @Multipart
    @POST(ConstantData.HOST3 + "/arcgis/PersonalCenter/PostPerInfo")
    Flowable<BaseResponseBean> commitUserInfo(@PartMap Map<String, RequestBody> params);

    @GET(ConstantData.HOST3 + "/arcgis/PersonalCenter/GetPersonList")
    Flowable<DownloadManageListBean> getDownloadList(@QueryMap Map<String, Object> params);

    @GET(ConstantData.HOST3 + "/arcgis/PersonalCenter/GetManageList")
    Flowable<ManageListBean> getManageList(@QueryMap Map<String, Object> params);

    @Multipart
    @POST(ConstantData.HOST3 + "/arcgis/PersonalCenter/Examine")
    Flowable<BaseResponseBean> examine(@PartMap Map<String, RequestBody> params);

    @GET(ConstantData.HOST3 + "/arcgis/DocumentSharing/GetDocumentTypeList")
    Flowable<DocTabListBean> getDocTabList();

    @GET(ConstantData.HOST3 + "/arcgis/DocumentSharing/GetPageListByCondition")
    Flowable<TechnicalStandardListBean> getDocList(@QueryMap Map<String, Object> params);

    @GET(ConstantData.HOST1 + "/getMenue")
    Flowable<List<LandCateBeanNew>> getMenu();

    @GET(ConstantData.HOST1 + "/getAnalysisMenue")
    Flowable<List<StatisticalAnalysisMenuListBean>> getAnalysisMenu();

    @GET(ConstantData.HOST1 + "/getMenueByMenueId")
    Flowable<LeafNodeInfoBean> getMenuByMenuId(@QueryMap Map<String, Object> params);

    @GET(ConstantData.HOST2 + "/getSecondCategoryCode")
    Flowable<List<LandInfoChartBean>> getSecondCategoryCode(@QueryMap Map<String, Object> params);

    @GET(ConstantData.HOST2 + "/getAnalysisTotalRecord")
    Flowable<StatisticalAnalysisBean> getAnalysisTotalRecord(@QueryMap Map<String, Object> params);

    @GET(ConstantData.HOST2 + "/getAnalysisTotalArea")
    Flowable<StatisticalAnalysisBean> getAnalysisTotalArea(@QueryMap Map<String, Object> params);

    @GET(ConstantData.HOST3 + "/arcgis/Other/GetNoticeList")
    Flowable<NoticeListBean> getNoticeList(@QueryMap Map<String, Object> params);

    @Multipart
    @POST(ConstantData.HOST3 + "/arcgis/DocumentSharing/CreateApply")
    Flowable<BaseResponseBean> applyDownload(@PartMap Map<String, RequestBody> params);

    @GET(ConstantData.HOST1 + "/getDepartment")
    Flowable<List<DepartmentListBean>> getDepartment();

    @GET(ConstantData.HOST1 + "/getPost")
    Flowable<List<PostListBean>> getPost();

    @GET(ConstantData.HOST1 + "/getRole")
    Flowable<List<RoleListBean>> getRole();

    @GET(ConstantData.HOST1 + "/regist")
    Flowable<LoginBean> register(@QueryMap Map<String, Object> params);

}