package com.winto.develop.ThreeTones.bean;

import com.winto.develop.ThreeTones.base.BaseResponse;

import java.util.List;

public class NoticeListBean extends BaseResponse {

    /**
     * count : 0
     * code : 0
     * msg : 成功
     * data : [{"noticeid":2,"content":"<p>&nbsp; &nbsp; &nbsp; 履行全民所有土地、矿产、森林、草原、湿地、水、海洋等自然资源资产所有者职责和所有国土空间用途管制职责。拟订自然资源和国土空间规划及测绘、极地、深海等法律法规草案，制定部门规章并监督检查执行情况。负责自然资源调查监测评价。制定自然资源调查监测评价的指标体系和统计标准，建立统一规范的自然资源调查监测评价制度。实施自然资源基础调查、专项调查和监测。负责自然资源调查监测评价成果的监督管理和信息发布。指导地方自然资源调查监测评价工作。<\/p>","title":"自然资源国土空间规划","image":null,"titleimage":null,"istitle":0,"createtime":"2020-06-29T00:00:00"},{"noticeid":3,"content":"<p>&nbsp; &nbsp; &nbsp; 履行全民所有土地、矿产、森林、草原、湿地、水、海洋等自然资源资产所有者职责和所有国土空间用途管制职责。拟订自然资源和国土空间规划及测绘、极地、深海等法律法规草案，制定部门规章并监督检查执行情况。负责自然资源调查监测评价。制定自然资源调查监测评价的指标体系和统计标准，建立统一规范的自然资源调查监测评价制度。实施自然资源基础调查、专项调查和监测。负责自然资源调查监测评价成果的监督管理和信息发布。指导地方自然资源调查监测评价工作。<\/p>","title":"全县工作会议通知","image":null,"titleimage":"<img src=\"https://iknow-pic.cdn.bcebos.com/d8f9d72a6059252df2d2346a3b9b033b5ab5b992\" style=\"float: l","istitle":0,"createtime":"2020-06-28T00:00:00"},{"noticeid":4,"content":"<p>&nbsp; &nbsp; &nbsp; 履行全民所有土地、矿产、森林、草原、湿地、水、海洋等自然资源资产所有者职责和所有国土空间用途管制职责。拟订自然资源和国土空间规划及测绘、极地、深海等法律法规草案，制定部门规章并监督检查执行情况。负责自然资源调查监测评价。制定自然资源调查监测评价的指标体系和统计标准，建立统一规范的自然资源调查监测评价制度。实施自然资源基础调查、专项调查和监测。负责自然资源调查监测评价成果的监督管理和信息发布。指导地方自然资源调查监测评价工作。<\/p>","title":"三调数据库建设方案","image":null,"titleimage":null,"istitle":0,"createtime":"2020-06-27T00:00:00"}]
     */

    private int count;
    private int code;
    private String msg;
    private List<DataBean> data;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean extends BaseResponse{
        /**
         * noticeid : 2
         * content : <p>&nbsp; &nbsp; &nbsp; 履行全民所有土地、矿产、森林、草原、湿地、水、海洋等自然资源资产所有者职责和所有国土空间用途管制职责。拟订自然资源和国土空间规划及测绘、极地、深海等法律法规草案，制定部门规章并监督检查执行情况。负责自然资源调查监测评价。制定自然资源调查监测评价的指标体系和统计标准，建立统一规范的自然资源调查监测评价制度。实施自然资源基础调查、专项调查和监测。负责自然资源调查监测评价成果的监督管理和信息发布。指导地方自然资源调查监测评价工作。</p>
         * title : 自然资源国土空间规划
         * image : null
         * titleimage : null
         * istitle : 0
         * createtime : 2020-06-29T00:00:00
         */

        private int noticeid;
        private String content;
        private String title;
        private Object image;
        private String titleimage;
        private int istitle;
        private String createtime;

        public int getNoticeid() {
            return noticeid;
        }

        public void setNoticeid(int noticeid) {
            this.noticeid = noticeid;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Object getImage() {
            return image;
        }

        public void setImage(Object image) {
            this.image = image;
        }

        public String getTitleimage() {
            return titleimage;
        }

        public void setTitleimage(String titleimage) {
            this.titleimage = titleimage;
        }

        public int getIstitle() {
            return istitle;
        }

        public void setIstitle(int istitle) {
            this.istitle = istitle;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }
    }
}
