package com.winto.develop.ThreeTones.constant;

public class ConstantData {

    //业务
    private final static String PORT_1 = "8081";
    //地图
    private final static String PORT_2 = "65535";
    //业务
    private final static String PORT_3 = "5050";
    //下载
    private final static String PORT_4 = "20000";

        private final static String IP = "http://192.168.11.220:";  //内网
//    private final static String IP = "http://111.43.10.95:";    //外网

    public final static String HOST1 = IP + PORT_1;
    public final static String HOST2 = IP + PORT_2;
    public final static String HOST3 = IP + PORT_3;
    public final static String HOST4 = IP + PORT_4;

    //地图服务ip
    public final static String MAP_IP = IP + "6080";

    //行政地图服务
    public final static String ADMINISTRATIVE_MAP = MAP_IP + "/arcgis/rest/services/jixian/XZQ_D/MapServer";

    //动态地图服务
    public final static String DYNAMIC_MAP = MAP_IP + "/arcgis/rest/services/jixian/DLTB_Dynamic/MapServer";

    //要素服务
    public final static String FEATURE_MAP = MAP_IP + "/arcgis/rest/services/jixian/DLTB/FeatureServer/0";
}
