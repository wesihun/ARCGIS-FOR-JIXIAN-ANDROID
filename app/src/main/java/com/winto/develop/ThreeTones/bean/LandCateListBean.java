package com.winto.develop.ThreeTones.bean;

import com.winto.develop.ThreeTones.base.BaseResponse;

import java.util.List;

public class LandCateListBean extends BaseResponse {

    /**
     * menueid : 1
     * menuename : 土地利用调查现状
     * parentmenueid : 0
     * firstcategory : null
     * secondcategory : null
     * subMenue : [{"menueid":2,"menuename":"农用地","parentmenueid":1,"firstcategory":null,"secondcategory":null,"subMenue":[{"menueid":3,"menuename":"耕地","parentmenueid":2,"firstcategory":"01","secondcategory":"","subMenue":[{"menueid":10,"menuename":"水田","parentmenueid":3,"firstcategory":"01","secondcategory":"0101","subMenue":[],"createtime":"2020-04-20 09:02:04"},{"menueid":11,"menuename":"水浇地","parentmenueid":3,"firstcategory":"01","secondcategory":"0102","subMenue":[],"createtime":"2020-04-20 09:02:04"},{"menueid":12,"menuename":"旱地","parentmenueid":3,"firstcategory":"01","secondcategory":"0103","subMenue":[],"createtime":"2020-04-20 09:02:04"}],"createtime":"2020-04-20 08:44:48"},{"menueid":4,"menuename":"园地","parentmenueid":2,"firstcategory":"02","secondcategory":"","subMenue":[{"menueid":13,"menuename":"果园","parentmenueid":4,"firstcategory":"02","secondcategory":"0201","subMenue":[],"createtime":"2020-04-20 09:02:04"},{"menueid":14,"menuename":"茶园","parentmenueid":4,"firstcategory":"02","secondcategory":"0202","subMenue":[],"createtime":"2020-04-20 09:02:04"},{"menueid":15,"menuename":"橡胶园","parentmenueid":4,"firstcategory":"02","secondcategory":"0203","subMenue":[],"createtime":"2020-04-20 09:02:04"},{"menueid":16,"menuename":"其他园地","parentmenueid":4,"firstcategory":"02","secondcategory":"0204","subMenue":[],"createtime":"2020-04-20 09:02:04"}],"createtime":"2020-04-20 08:44:48"},{"menueid":5,"menuename":"林地","parentmenueid":2,"firstcategory":"03","secondcategory":"","subMenue":[{"menueid":17,"menuename":"乔木林地","parentmenueid":5,"firstcategory":"03","secondcategory":"0301","subMenue":[],"createtime":"2020-04-20 09:02:04"},{"menueid":18,"menuename":"竹林地","parentmenueid":5,"firstcategory":"03","secondcategory":"0302","subMenue":[],"createtime":"2020-04-20 09:02:04"},{"menueid":19,"menuename":"红树林地","parentmenueid":5,"firstcategory":"03","secondcategory":"0303","subMenue":[],"createtime":"2020-04-20 09:02:04"},{"menueid":20,"menuename":"森林沼泽","parentmenueid":5,"firstcategory":"03","secondcategory":"0304","subMenue":[],"createtime":"2020-04-20 09:02:04"},{"menueid":21,"menuename":"灌木林地","parentmenueid":5,"firstcategory":"03","secondcategory":"0305","subMenue":[],"createtime":"2020-04-20 09:02:04"},{"menueid":22,"menuename":"灌丛沼泽","parentmenueid":5,"firstcategory":"03","secondcategory":"0306","subMenue":[],"createtime":"2020-04-20 09:02:04"},{"menueid":23,"menuename":"其他林地","parentmenueid":5,"firstcategory":"03","secondcategory":"0307","subMenue":[],"createtime":"2020-04-20 09:02:04"}],"createtime":"2020-04-20 08:44:48"},{"menueid":6,"menuename":"草地","parentmenueid":2,"firstcategory":"04","secondcategory":"","subMenue":[{"menueid":24,"menuename":"天然牧草地","parentmenueid":6,"firstcategory":"04","secondcategory":"0401","subMenue":[],"createtime":"2020-04-20 09:02:04"},{"menueid":25,"menuename":"沼泽草地","parentmenueid":6,"firstcategory":"04","secondcategory":"0402","subMenue":[],"createtime":"2020-04-20 09:02:04"},{"menueid":26,"menuename":"人工牧草地","parentmenueid":6,"firstcategory":"04","secondcategory":"0403","subMenue":[],"createtime":"2020-04-20 09:02:04"}],"createtime":"2020-04-20 08:44:48"},{"menueid":7,"menuename":"交通运输用地","parentmenueid":2,"firstcategory":"10","secondcategory":"","subMenue":[{"menueid":27,"menuename":"农村道路","parentmenueid":7,"firstcategory":"10","secondcategory":"1006","subMenue":[],"createtime":"2020-04-20 09:02:04"}],"createtime":"2020-04-20 08:44:48"},{"menueid":8,"menuename":"水域及水利设施用地","parentmenueid":2,"firstcategory":"11","secondcategory":"","subMenue":[{"menueid":28,"menuename":"水库水面","parentmenueid":8,"firstcategory":"11","secondcategory":"1103","subMenue":[],"createtime":"2020-04-20 09:02:04"}]}]}]
     */

    private int menueid;
    private String menuename;
    private int parentmenueid;
    private Object firstcategory;
    private Object secondcategory;
    private List<SubMenueBeanXX> subMenue;

    public int getMenueid() {
        return menueid;
    }

    public void setMenueid(int menueid) {
        this.menueid = menueid;
    }

    public String getMenuename() {
        return menuename;
    }

    public void setMenuename(String menuename) {
        this.menuename = menuename;
    }

    public int getParentmenueid() {
        return parentmenueid;
    }

    public void setParentmenueid(int parentmenueid) {
        this.parentmenueid = parentmenueid;
    }

    public Object getFirstcategory() {
        return firstcategory;
    }

    public void setFirstcategory(Object firstcategory) {
        this.firstcategory = firstcategory;
    }

    public Object getSecondcategory() {
        return secondcategory;
    }

    public void setSecondcategory(Object secondcategory) {
        this.secondcategory = secondcategory;
    }

    public List<SubMenueBeanXX> getSubMenue() {
        return subMenue;
    }

    public void setSubMenue(List<SubMenueBeanXX> subMenue) {
        this.subMenue = subMenue;
    }

    public static class SubMenueBeanXX extends BaseResponse {
        /**
         * menueid : 2
         * menuename : 农用地
         * parentmenueid : 1
         * firstcategory : null
         * secondcategory : null
         * isExpend
         * subMenue : [{"menueid":3,"menuename":"耕地","parentmenueid":2,"firstcategory":"01","secondcategory":"","subMenue":[{"menueid":10,"menuename":"水田","parentmenueid":3,"firstcategory":"01","secondcategory":"0101","subMenue":[],"createtime":"2020-04-20 09:02:04"},{"menueid":11,"menuename":"水浇地","parentmenueid":3,"firstcategory":"01","secondcategory":"0102","subMenue":[],"createtime":"2020-04-20 09:02:04"},{"menueid":12,"menuename":"旱地","parentmenueid":3,"firstcategory":"01","secondcategory":"0103","subMenue":[],"createtime":"2020-04-20 09:02:04"}],"createtime":"2020-04-20 08:44:48"},{"menueid":4,"menuename":"园地","parentmenueid":2,"firstcategory":"02","secondcategory":"","subMenue":[{"menueid":13,"menuename":"果园","parentmenueid":4,"firstcategory":"02","secondcategory":"0201","subMenue":[],"createtime":"2020-04-20 09:02:04"},{"menueid":14,"menuename":"茶园","parentmenueid":4,"firstcategory":"02","secondcategory":"0202","subMenue":[],"createtime":"2020-04-20 09:02:04"},{"menueid":15,"menuename":"橡胶园","parentmenueid":4,"firstcategory":"02","secondcategory":"0203","subMenue":[],"createtime":"2020-04-20 09:02:04"},{"menueid":16,"menuename":"其他园地","parentmenueid":4,"firstcategory":"02","secondcategory":"0204","subMenue":[],"createtime":"2020-04-20 09:02:04"}],"createtime":"2020-04-20 08:44:48"},{"menueid":5,"menuename":"林地","parentmenueid":2,"firstcategory":"03","secondcategory":"","subMenue":[{"menueid":17,"menuename":"乔木林地","parentmenueid":5,"firstcategory":"03","secondcategory":"0301","subMenue":[],"createtime":"2020-04-20 09:02:04"},{"menueid":18,"menuename":"竹林地","parentmenueid":5,"firstcategory":"03","secondcategory":"0302","subMenue":[],"createtime":"2020-04-20 09:02:04"},{"menueid":19,"menuename":"红树林地","parentmenueid":5,"firstcategory":"03","secondcategory":"0303","subMenue":[],"createtime":"2020-04-20 09:02:04"},{"menueid":20,"menuename":"森林沼泽","parentmenueid":5,"firstcategory":"03","secondcategory":"0304","subMenue":[],"createtime":"2020-04-20 09:02:04"},{"menueid":21,"menuename":"灌木林地","parentmenueid":5,"firstcategory":"03","secondcategory":"0305","subMenue":[],"createtime":"2020-04-20 09:02:04"},{"menueid":22,"menuename":"灌丛沼泽","parentmenueid":5,"firstcategory":"03","secondcategory":"0306","subMenue":[],"createtime":"2020-04-20 09:02:04"},{"menueid":23,"menuename":"其他林地","parentmenueid":5,"firstcategory":"03","secondcategory":"0307","subMenue":[],"createtime":"2020-04-20 09:02:04"}],"createtime":"2020-04-20 08:44:48"},{"menueid":6,"menuename":"草地","parentmenueid":2,"firstcategory":"04","secondcategory":"","subMenue":[{"menueid":24,"menuename":"天然牧草地","parentmenueid":6,"firstcategory":"04","secondcategory":"0401","subMenue":[],"createtime":"2020-04-20 09:02:04"},{"menueid":25,"menuename":"沼泽草地","parentmenueid":6,"firstcategory":"04","secondcategory":"0402","subMenue":[],"createtime":"2020-04-20 09:02:04"},{"menueid":26,"menuename":"人工牧草地","parentmenueid":6,"firstcategory":"04","secondcategory":"0403","subMenue":[],"createtime":"2020-04-20 09:02:04"}],"createtime":"2020-04-20 08:44:48"},{"menueid":7,"menuename":"交通运输用地","parentmenueid":2,"firstcategory":"10","secondcategory":"","subMenue":[{"menueid":27,"menuename":"农村道路","parentmenueid":7,"firstcategory":"10","secondcategory":"1006","subMenue":[],"createtime":"2020-04-20 09:02:04"}],"createtime":"2020-04-20 08:44:48"},{"menueid":8,"menuename":"水域及水利设施用地","parentmenueid":2,"firstcategory":"11","secondcategory":"","subMenue":[{"menueid":28,"menuename":"水库水面","parentmenueid":8,"firstcategory":"11","secondcategory":"1103","subMenue":[],"createtime":"2020-04-20 09:02:04"}]}]
         */

        private int menueid;
        private String menuename;
        private int parentmenueid;
        private Object firstcategory;
        private Object secondcategory;
        private boolean isExpend = false;
        private List<SubMenueBeanX> subMenue;

        public int getMenueid() {
            return menueid;
        }

        public boolean isExpend() {
            return isExpend;
        }

        public void setExpend(boolean expend) {
            isExpend = expend;
        }

        public void setMenueid(int menueid) {
            this.menueid = menueid;
        }

        public String getMenuename() {
            return menuename;
        }

        public void setMenuename(String menuename) {
            this.menuename = menuename;
        }

        public int getParentmenueid() {
            return parentmenueid;
        }

        public void setParentmenueid(int parentmenueid) {
            this.parentmenueid = parentmenueid;
        }

        public Object getFirstcategory() {
            return firstcategory;
        }

        public void setFirstcategory(Object firstcategory) {
            this.firstcategory = firstcategory;
        }

        public Object getSecondcategory() {
            return secondcategory;
        }

        public void setSecondcategory(Object secondcategory) {
            this.secondcategory = secondcategory;
        }

        public List<SubMenueBeanX> getSubMenue() {
            return subMenue;
        }

        public void setSubMenue(List<SubMenueBeanX> subMenue) {
            this.subMenue = subMenue;
        }

        public static class SubMenueBeanX extends BaseResponse {
            /**
             * menueid : 3
             * menuename : 耕地
             * parentmenueid : 2
             * firstcategory : 01
             * secondcategory :
             * subMenue : [{"menueid":10,"menuename":"水田","parentmenueid":3,"firstcategory":"01","secondcategory":"0101","subMenue":[],"createtime":"2020-04-20 09:02:04"},{"menueid":11,"menuename":"水浇地","parentmenueid":3,"firstcategory":"01","secondcategory":"0102","subMenue":[],"createtime":"2020-04-20 09:02:04"},{"menueid":12,"menuename":"旱地","parentmenueid":3,"firstcategory":"01","secondcategory":"0103","subMenue":[],"createtime":"2020-04-20 09:02:04"}]
             * createtime : 2020-04-20 08:44:48
             */

            private int menueid;
            private String menuename;
            private int parentmenueid;
            private String firstcategory;
            private String secondcategory;
            private String createtime;
            private boolean isExpend = false;
            private List<SubMenueBean> subMenue;

            public int getMenueid() {
                return menueid;
            }

            public boolean isExpend() {
                return isExpend;
            }

            public void setExpend(boolean expend) {
                isExpend = expend;
            }

            public void setMenueid(int menueid) {
                this.menueid = menueid;
            }

            public String getMenuename() {
                return menuename;
            }

            public void setMenuename(String menuename) {
                this.menuename = menuename;
            }

            public int getParentmenueid() {
                return parentmenueid;
            }

            public void setParentmenueid(int parentmenueid) {
                this.parentmenueid = parentmenueid;
            }

            public String getFirstcategory() {
                return firstcategory;
            }

            public void setFirstcategory(String firstcategory) {
                this.firstcategory = firstcategory;
            }

            public String getSecondcategory() {
                return secondcategory;
            }

            public void setSecondcategory(String secondcategory) {
                this.secondcategory = secondcategory;
            }

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }

            public List<SubMenueBean> getSubMenue() {
                return subMenue;
            }

            public void setSubMenue(List<SubMenueBean> subMenue) {
                this.subMenue = subMenue;
            }

            public static class SubMenueBean extends BaseResponse {
                /**
                 * menueid : 10
                 * menuename : 水田
                 * parentmenueid : 3
                 * firstcategory : 01
                 * secondcategory : 0101
                 * subMenue : []
                 * createtime : 2020-04-20 09:02:04
                 */

                private int menueid;
                private String menuename;
                private int parentmenueid;
                private String firstcategory;
                private String secondcategory;
                private String createtime;
                private boolean isExpend;
                private List<?> subMenue;

                public int getMenueid() {
                    return menueid;
                }

                public void setMenueid(int menueid) {
                    this.menueid = menueid;
                }

                public boolean isExpend() {
                    return isExpend;
                }

                public void setExpend(boolean expend) {
                    isExpend = expend;
                }

                public String getMenuename() {
                    return menuename;
                }

                public void setMenuename(String menuename) {
                    this.menuename = menuename;
                }

                public int getParentmenueid() {
                    return parentmenueid;
                }

                public void setParentmenueid(int parentmenueid) {
                    this.parentmenueid = parentmenueid;
                }

                public String getFirstcategory() {
                    return firstcategory;
                }

                public void setFirstcategory(String firstcategory) {
                    this.firstcategory = firstcategory;
                }

                public String getSecondcategory() {
                    return secondcategory;
                }

                public void setSecondcategory(String secondcategory) {
                    this.secondcategory = secondcategory;
                }

                public String getCreatetime() {
                    return createtime;
                }

                public void setCreatetime(String createtime) {
                    this.createtime = createtime;
                }

                public List<?> getSubMenue() {
                    return subMenue;
                }

                public void setSubMenue(List<?> subMenue) {
                    this.subMenue = subMenue;
                }
            }
        }
    }
}
