package com.winto.develop.ThreeTones.bean;

import com.winto.develop.ThreeTones.base.BaseResponse;

public class TreePoint extends BaseResponse {
    private String ID;        // 7241,          //账号id
    private String NNAME;     //名称
    private String PARENTID;   // 0,           //父id     0表示根节点
    private String ISLEAF;     //0,            //是否是子节点  1为子节点
    private int DISPLAY_ORDER; // 1       //同一个级别的显示顺序
    private boolean isExpand = false;  //是否展开了

    //""+id,"分类"+i,"" + parentId,"0",i


    public TreePoint(String ID, String NNAME, String PARENTID, String ISLEAF, int DISPLAY_ORDER) {
        this.ID = ID;
        this.NNAME = NNAME;
        this.PARENTID = PARENTID;
        this.ISLEAF = ISLEAF;
        this.DISPLAY_ORDER = DISPLAY_ORDER;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNNAME() {
        return NNAME;
    }

    public void setNNAME(String NNAME) {
        this.NNAME = NNAME;
    }

    public String getPARENTID() {
        return PARENTID;
    }

    public void setPARENTID(String PARENTID) {
        this.PARENTID = PARENTID;
    }

    public String getISLEAF() {
        return ISLEAF;
    }

    public void setISLEAF(String ISLEAF) {
        this.ISLEAF = ISLEAF;
    }

    public int getDISPLAY_ORDER() {
        return DISPLAY_ORDER;
    }

    public void setDISPLAY_ORDER(int DISPLAY_ORDER) {
        this.DISPLAY_ORDER = DISPLAY_ORDER;
    }

    public boolean isExpand() {
        return isExpand;
    }

    public void setExpand(boolean expand) {
        isExpand = expand;
    }
}
