package com.winto.develop.ThreeTones.util;

import android.util.Log;

import com.winto.develop.ThreeTones.bean.TreePoint;

import java.util.HashMap;
import java.util.Objects;

public class TreeUtils {
    public static int getLevel(TreePoint treePoint, HashMap<String,TreePoint> map){
        if("0".equals(treePoint.getPARENTID())){
            return 0;
        }else{
            return 1+getLevel(Objects.requireNonNull(getTreePoint(treePoint.getPARENTID(), map)),map);
        }
    }

    public static TreePoint getTreePoint(String ID, HashMap<String,TreePoint> map){
        if(map.containsKey(ID)){
            return map.get(ID);
        }
        Log.e("xlc","ID:" + ID);
        return null;
    }
}
