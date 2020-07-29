package com.winto.develop.ThreeTones.util;

import com.esri.arcgisruntime.data.Feature;
import com.esri.arcgisruntime.data.FeatureQueryResult;
import com.winto.develop.ThreeTones.bean.TreePoint;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DataUtil {

    public static List<TreePoint> getLandList() {
        List<TreePoint> reasonPointList = new ArrayList<>();
        int parentId = 0;

        TreePoint cate1_1 = new TreePoint("" + 1, "农用地", "" + parentId, "0", 1);
        TreePoint cate1_2 = new TreePoint("" + 2, "湿地", "" + parentId, "0", 2);
        TreePoint cate1_3 = new TreePoint("" + 3, "建设用地", "" + parentId, "0", 3);
        TreePoint cate1_4 = new TreePoint("" + 4, "未利用地", "" + parentId, "0", 4);
        reasonPointList.add(cate1_1);
        reasonPointList.add(cate1_2);
        reasonPointList.add(cate1_3);
        reasonPointList.add(cate1_4);

        TreePoint cate1_1_1 = new TreePoint("" + 5, "种植园用地", "" + 1, "0", 1);
        TreePoint cate1_1_2 = new TreePoint("" + 6, "商业服务用地", "" + 1, "0", 2);
        reasonPointList.add(cate1_1_1);
        reasonPointList.add(cate1_1_2);

        TreePoint cate1_1_1_1 = new TreePoint("" + 13, "水田", "" + 5, "1", 1);
        TreePoint cate1_1_1_2 = new TreePoint("" + 14, "水浇地", "" + 5, "1", 2);
        reasonPointList.add(cate1_1_1_1);
        reasonPointList.add(cate1_1_1_2);

        TreePoint cate1_1_2_1 = new TreePoint("" + 15, "水田", "" + 6, "1", 1);
        TreePoint cate1_1_2_2 = new TreePoint("" + 16, "水浇地", "" + 6, "1", 2);
        reasonPointList.add(cate1_1_2_1);
        reasonPointList.add(cate1_1_2_2);

        TreePoint cate1_2_1 = new TreePoint("" + 7, "种植园用地", "" + 2, "0", 3);
        TreePoint cate1_2_2 = new TreePoint("" + 8, "商业服务用地", "" + 2, "0", 4);
        reasonPointList.add(cate1_2_1);
        reasonPointList.add(cate1_2_2);

        TreePoint cate1_2_1_1 = new TreePoint("" + 17, "水田", "" + 7, "1", 1);
        TreePoint cate1_2_1_2 = new TreePoint("" + 18, "水浇地", "" + 7, "1", 2);
        reasonPointList.add(cate1_2_1_1);
        reasonPointList.add(cate1_2_1_2);

        TreePoint cate1_2_2_1 = new TreePoint("" + 19, "水田", "" + 8, "1", 1);
        TreePoint cate1_2_2_2 = new TreePoint("" + 20, "水浇地", "" + 8, "1", 2);
        reasonPointList.add(cate1_2_2_1);
        reasonPointList.add(cate1_2_2_2);

        TreePoint cate1_3_1 = new TreePoint("" + 9, "种植园用地", "" + 3, "0", 5);
        TreePoint cate1_3_2 = new TreePoint("" + 10, "商业服务用地", "" + 3, "0", 6);
        reasonPointList.add(cate1_3_1);
        reasonPointList.add(cate1_3_2);

        TreePoint cate1_3_1_1 = new TreePoint("" + 21, "水田", "" + 9, "1", 1);
        TreePoint cate1_3_1_2 = new TreePoint("" + 22, "水浇地", "" + 9, "1", 2);
        reasonPointList.add(cate1_3_1_1);
        reasonPointList.add(cate1_3_1_2);

        TreePoint cate1_3_2_1 = new TreePoint("" + 23, "水田", "" + 10, "1", 1);
        TreePoint cate1_3_2_2 = new TreePoint("" + 24, "水浇地", "" + 10, "1", 2);
        reasonPointList.add(cate1_3_2_1);
        reasonPointList.add(cate1_3_2_2);

        TreePoint cate1_4_1 = new TreePoint("" + 11, "种植园用地", "" + 4, "0", 7);
        TreePoint cate1_4_2 = new TreePoint("" + 12, "商业服务用地", "" + 4, "0", 8);
        reasonPointList.add(cate1_4_1);
        reasonPointList.add(cate1_4_2);

        TreePoint cate1_4_1_1 = new TreePoint("" + 25, "水田", "" + 11, "1", 1);
        TreePoint cate1_4_1_2 = new TreePoint("" + 26, "水浇地", "" + 11, "1", 2);
        reasonPointList.add(cate1_4_1_1);
        reasonPointList.add(cate1_4_1_2);

        TreePoint cate1_4_2_1 = new TreePoint("" + 27, "水田", "" + 12, "1", 1);
        TreePoint cate1_4_2_2 = new TreePoint("" + 28, "水浇地", "" + 12, "1", 2);
        reasonPointList.add(cate1_4_2_1);
        reasonPointList.add(cate1_4_2_2);

        return reasonPointList;
    }

    public static List<String> getLandInfoList(FeatureQueryResult result) {
        if (result == null) {
            return null;
        }
        List<String> landInfoListBean = new ArrayList<>();

        Iterator<Feature> iterator = result.iterator();
        Feature feature;
        while (iterator.hasNext()) {
            feature = iterator.next();
            Map<String, Object> attributes = feature.getAttributes();

            landInfoListBean.add("要素代码：" + attributes.get("ysdm"));
            landInfoListBean.add("图斑地类面积：" + attributes.get("tbdlmj"));
            landInfoListBean.add("图斑代码：暂无");
            landInfoListBean.add("耕地坡度级别：" + attributes.get("gdpdjb"));
            landInfoListBean.add("地类代码：暂无");
            landInfoListBean.add("图斑细化代码：" + attributes.get("tbxhdm"));
            landInfoListBean.add("地类名称：" + attributes.get("dlmc"));
            landInfoListBean.add("图斑细化名称：" + attributes.get("tbxhmc"));
            landInfoListBean.add("权属性质：" + attributes.get("qsxz"));
            landInfoListBean.add("种植属性代码：" + attributes.get("zzsxdm"));
            landInfoListBean.add("权属单位代码：" + attributes.get("qsdwdm"));
            landInfoListBean.add("种植属性名称：" + attributes.get("zzsxmc"));
            landInfoListBean.add("权属单位名称：" + attributes.get("qsdwmc"));
            landInfoListBean.add("耕地级别：暂无");
            landInfoListBean.add("图斑面积：" + attributes.get("tbmj"));
            landInfoListBean.add("数据年份：" + attributes.get("sjnf"));
            landInfoListBean.add("扣除地类系数：暂无");
            landInfoListBean.add("扣除地类面积：暂无");
        }
        return landInfoListBean;
    }
}
