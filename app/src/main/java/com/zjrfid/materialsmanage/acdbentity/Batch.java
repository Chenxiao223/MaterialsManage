package com.zjrfid.materialsmanage.acdbentity;

import java.util.List;

/**
 * Created by Administrator on 2017/3/20.
 */
public class Batch {


    /**
     * statusCode : 200
     * message : 操作成功
     * navTabId :
     * forwordUrl :
     * callbackType :
     * jsonData : {"currentPage":1,"list":[{"CPOSNAME":"报废品仓库","TAXAMOUNT":80,"FMONDEY":68.376,"MOVEFUNITPRICE":0,"CBATCH":"C1110206081300000001","HPIGUID":"00000000000HPINVENTORY00000000000740","MOVEFTAXPRICE":0,"FUNITPRICE":17.094,"MOVETAXAMOUNT":0,"FTAXPRICE":20,"CWHCODE":"01","FTAXRATE":17,"CCOMUNITNAME":"PCS","IBATCH":1,"CINVCODE":"1000000740","CPOSCODE":"0109","UNITS":"PCS","HPROGUID":"000000HPSTACKCHECKLIST00000000003646","IPERTAXRATE":11.624,"FQUANTITYS":4,"HPRGUIDCH":null,"MOVEFMONDEY":0,"CINVSTD":"140/EQ145T_6891车","CINVNAME":"XXXX定根销/后制动蹄销","MOVEIPERTAXRATE":0,"HPSNGUID":null,"MOVEFTAXRATE":0},{"CPOSNAME":"一楼C13货架第三层第一格","TAXAMOUNT":19,"FMONDEY":16.2393,"MOVEFUNITPRICE":0,"CBATCH":"123123","HPIGUID":"00000000000HPINVENTORY00000000000740","MOVEFTAXPRICE":0,"FUNITPRICE":3.2479,"MOVETAXAMOUNT":0,"FTAXPRICE":3.8,"CWHCODE":"01","FTAXRATE":17,"CCOMUNITNAME":"PCS","IBATCH":1,"CINVCODE":"1000000740","CPOSCODE":"010101100230","UNITS":"PCS","HPROGUID":"000000HPSTACKCHECKLIST17021521327638","IPERTAXRATE":2.7607,"FQUANTITYS":5,"HPRGUIDCH":"0000000000HPRDRECORDCH17021521327637","MOVEFMONDEY":0,"CINVSTD":"140/EQ145T_6891车","CINVNAME":"XXXX定根销/后制动蹄销","MOVEIPERTAXRATE":0,"HPSNGUID":"00000000000000SUPPLIER00000000000050","MOVEFTAXRATE":0}],"numPerPage":10,"pageNumShown":10,"totalCount":2}
     */

    private String statusCode;
    private String message;
    private String navTabId;
    private String forwordUrl;
    private String callbackType;
    private JsonDataBean jsonData;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNavTabId() {
        return navTabId;
    }

    public void setNavTabId(String navTabId) {
        this.navTabId = navTabId;
    }

    public String getForwordUrl() {
        return forwordUrl;
    }

    public void setForwordUrl(String forwordUrl) {
        this.forwordUrl = forwordUrl;
    }

    public String getCallbackType() {
        return callbackType;
    }

    public void setCallbackType(String callbackType) {
        this.callbackType = callbackType;
    }

    public JsonDataBean getJsonData() {
        return jsonData;
    }

    public void setJsonData(JsonDataBean jsonData) {
        this.jsonData = jsonData;
    }

    public static class JsonDataBean {
        /**
         * currentPage : 1
         * list : [{"CPOSNAME":"报废品仓库","TAXAMOUNT":80,"FMONDEY":68.376,"MOVEFUNITPRICE":0,"CBATCH":"C1110206081300000001","HPIGUID":"00000000000HPINVENTORY00000000000740","MOVEFTAXPRICE":0,"FUNITPRICE":17.094,"MOVETAXAMOUNT":0,"FTAXPRICE":20,"CWHCODE":"01","FTAXRATE":17,"CCOMUNITNAME":"PCS","IBATCH":1,"CINVCODE":"1000000740","CPOSCODE":"0109","UNITS":"PCS","HPROGUID":"000000HPSTACKCHECKLIST00000000003646","IPERTAXRATE":11.624,"FQUANTITYS":4,"HPRGUIDCH":null,"MOVEFMONDEY":0,"CINVSTD":"140/EQ145T_6891车","CINVNAME":"XXXX定根销/后制动蹄销","MOVEIPERTAXRATE":0,"HPSNGUID":null,"MOVEFTAXRATE":0},{"CPOSNAME":"一楼C13货架第三层第一格","TAXAMOUNT":19,"FMONDEY":16.2393,"MOVEFUNITPRICE":0,"CBATCH":"123123","HPIGUID":"00000000000HPINVENTORY00000000000740","MOVEFTAXPRICE":0,"FUNITPRICE":3.2479,"MOVETAXAMOUNT":0,"FTAXPRICE":3.8,"CWHCODE":"01","FTAXRATE":17,"CCOMUNITNAME":"PCS","IBATCH":1,"CINVCODE":"1000000740","CPOSCODE":"010101100230","UNITS":"PCS","HPROGUID":"000000HPSTACKCHECKLIST17021521327638","IPERTAXRATE":2.7607,"FQUANTITYS":5,"HPRGUIDCH":"0000000000HPRDRECORDCH17021521327637","MOVEFMONDEY":0,"CINVSTD":"140/EQ145T_6891车","CINVNAME":"XXXX定根销/后制动蹄销","MOVEIPERTAXRATE":0,"HPSNGUID":"00000000000000SUPPLIER00000000000050","MOVEFTAXRATE":0}]
         * numPerPage : 10
         * pageNumShown : 10
         * totalCount : 2
         */

        private int currentPage;
        private int numPerPage;
        private int pageNumShown;
        private int totalCount;
        private List<ListBean> list;

        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public int getNumPerPage() {
            return numPerPage;
        }

        public void setNumPerPage(int numPerPage) {
            this.numPerPage = numPerPage;
        }

        public int getPageNumShown() {
            return pageNumShown;
        }

        public void setPageNumShown(int pageNumShown) {
            this.pageNumShown = pageNumShown;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }


        public static class ListBean {
            /**
             * CPOSNAME : 报废品仓库
             * TAXAMOUNT : 80
             * FMONDEY : 68.376
             * MOVEFUNITPRICE : 0
             * CBATCH : C1110206081300000001
             * HPIGUID : 00000000000HPINVENTORY00000000000740
             * MOVEFTAXPRICE : 0
             * FUNITPRICE : 17.094
             * MOVETAXAMOUNT : 0
             * FTAXPRICE : 20
             * CWHCODE : 01
             * FTAXRATE : 17
             * CCOMUNITNAME : PCS
             * IBATCH : 1
             * CINVCODE : 1000000740
             * CPOSCODE : 0109
             * UNITS : PCS
             * HPROGUID : 000000HPSTACKCHECKLIST00000000003646
             * IPERTAXRATE : 11.624
             * FQUANTITYS : 4
             * HPRGUIDCH : null
             * MOVEFMONDEY : 0
             * CINVSTD : 140/EQ145T_6891车
             * CINVNAME : XXXX定根销/后制动蹄销
             * MOVEIPERTAXRATE : 0
             * HPSNGUID : null
             * MOVEFTAXRATE : 0
             */

            private String CPOSNAME;
            private String TAXAMOUNT;
            private String FMONDEY;
            private String MOVEFUNITPRICE;
            private String CBATCH;
            private String HPIGUID;
            private String MOVEFTAXPRICE;
            private String FUNITPRICE;
            private String MOVETAXAMOUNT;
            private String FTAXPRICE;
            private String CWHCODE;
            private String FTAXRATE;
            private String CCOMUNITNAME;
            private String IBATCH;
            private String CINVCODE;
            private String CPOSCODE;
            private String UNITS;
            private String HPROGUID;
            private String IPERTAXRATE;
            private String FQUANTITYS;
            private String HPRGUIDCH;
            private String MOVEFMONDEY;
            private String CINVSTD;
            private String CINVNAME;
            private String MOVEIPERTAXRATE;
            private String HPSNGUID;
            private String MOVEFTAXRATE;
            private String CWHNAME;

            public String getCWHNAME() {
                return CWHNAME;
            }

            public void setCWHNAME(String CWHNAME) {
                this.CWHNAME = CWHNAME;
            }

            public String getCPOSNAME() {
                return CPOSNAME;
            }

            public void setCPOSNAME(String CPOSNAME) {
                this.CPOSNAME = CPOSNAME;
            }

            public String getTAXAMOUNT() {
                return TAXAMOUNT;
            }

            public void setTAXAMOUNT(String TAXAMOUNT) {
                this.TAXAMOUNT = TAXAMOUNT;
            }

            public String getFMONDEY() {
                return FMONDEY;
            }

            public void setFMONDEY(String FMONDEY) {
                this.FMONDEY = FMONDEY;
            }

            public String getMOVEFUNITPRICE() {
                return MOVEFUNITPRICE;
            }

            public void setMOVEFUNITPRICE(String MOVEFUNITPRICE) {
                this.MOVEFUNITPRICE = MOVEFUNITPRICE;
            }

            public String getCBATCH() {
                return CBATCH;
            }

            public void setCBATCH(String CBATCH) {
                this.CBATCH = CBATCH;
            }

            public String getHPIGUID() {
                return HPIGUID;
            }

            public void setHPIGUID(String HPIGUID) {
                this.HPIGUID = HPIGUID;
            }

            public String getMOVEFTAXPRICE() {
                return MOVEFTAXPRICE;
            }

            public void setMOVEFTAXPRICE(String MOVEFTAXPRICE) {
                this.MOVEFTAXPRICE = MOVEFTAXPRICE;
            }

            public String getFUNITPRICE() {
                return FUNITPRICE;
            }

            public void setFUNITPRICE(String FUNITPRICE) {
                this.FUNITPRICE = FUNITPRICE;
            }

            public String getMOVETAXAMOUNT() {
                return MOVETAXAMOUNT;
            }

            public void setMOVETAXAMOUNT(String MOVETAXAMOUNT) {
                this.MOVETAXAMOUNT = MOVETAXAMOUNT;
            }

            public String getFTAXPRICE() {
                return FTAXPRICE;
            }

            public void setFTAXPRICE(String FTAXPRICE) {
                this.FTAXPRICE = FTAXPRICE;
            }

            public String getCWHCODE() {
                return CWHCODE;
            }

            public void setCWHCODE(String CWHCODE) {
                this.CWHCODE = CWHCODE;
            }

            public String getFTAXRATE() {
                return FTAXRATE;
            }

            public void setFTAXRATE(String FTAXRATE) {
                this.FTAXRATE = FTAXRATE;
            }

            public String getCCOMUNITNAME() {
                return CCOMUNITNAME;
            }

            public void setCCOMUNITNAME(String CCOMUNITNAME) {
                this.CCOMUNITNAME = CCOMUNITNAME;
            }

            public String getIBATCH() {
                return IBATCH;
            }

            public void setIBATCH(String IBATCH) {
                this.IBATCH = IBATCH;
            }

            public String getCINVCODE() {
                return CINVCODE;
            }

            public void setCINVCODE(String CINVCODE) {
                this.CINVCODE = CINVCODE;
            }

            public String getCPOSCODE() {
                return CPOSCODE;
            }

            public void setCPOSCODE(String CPOSCODE) {
                this.CPOSCODE = CPOSCODE;
            }

            public String getUNITS() {
                return UNITS;
            }

            public void setUNITS(String UNITS) {
                this.UNITS = UNITS;
            }

            public String getHPROGUID() {
                return HPROGUID;
            }

            public void setHPROGUID(String HPROGUID) {
                this.HPROGUID = HPROGUID;
            }

            public String getIPERTAXRATE() {
                return IPERTAXRATE;
            }

            public void setIPERTAXRATE(String IPERTAXRATE) {
                this.IPERTAXRATE = IPERTAXRATE;
            }

            public String getFQUANTITYS() {
                return FQUANTITYS;
            }

            public void setFQUANTITYS(String FQUANTITYS) {
                this.FQUANTITYS = FQUANTITYS;
            }

            public String getHPRGUIDCH() {
                return HPRGUIDCH;
            }

            public void setHPRGUIDCH(String HPRGUIDCH) {
                this.HPRGUIDCH = HPRGUIDCH;
            }

            public String getMOVEFMONDEY() {
                return MOVEFMONDEY;
            }

            public void setMOVEFMONDEY(String MOVEFMONDEY) {
                this.MOVEFMONDEY = MOVEFMONDEY;
            }

            public String getCINVSTD() {
                return CINVSTD;
            }

            public void setCINVSTD(String CINVSTD) {
                this.CINVSTD = CINVSTD;
            }

            public String getCINVNAME() {
                return CINVNAME;
            }

            public void setCINVNAME(String CINVNAME) {
                this.CINVNAME = CINVNAME;
            }

            public String getMOVEIPERTAXRATE() {
                return MOVEIPERTAXRATE;
            }

            public void setMOVEIPERTAXRATE(String MOVEIPERTAXRATE) {
                this.MOVEIPERTAXRATE = MOVEIPERTAXRATE;
            }

            public String getHPSNGUID() {
                return HPSNGUID;
            }

            public void setHPSNGUID(String HPSNGUID) {
                this.HPSNGUID = HPSNGUID;
            }

            public String getMOVEFTAXRATE() {
                return MOVEFTAXRATE;
            }

            public void setMOVEFTAXRATE(String MOVEFTAXRATE) {
                this.MOVEFTAXRATE = MOVEFTAXRATE;
            }
        }






//        public static class ListBean {
//            /**
//             * CPOSNAME : 报废品仓库
//             * TAXAMOUNT : 80
//             * FMONDEY : 68.376
//             * MOVEFUNITPRICE : 0
//             * CBATCH : C1110206081300000001
//             * HPIGUID : 00000000000HPINVENTORY00000000000740
//             * MOVEFTAXPRICE : 0
//             * FUNITPRICE : 17.094
//             * MOVETAXAMOUNT : 0
//             * FTAXPRICE : 20
//             * CWHCODE : 01
//             * FTAXRATE : 17
//             * CCOMUNITNAME : PCS
//             * IBATCH : 1
//             * CINVCODE : 1000000740
//             * CPOSCODE : 0109
//             * UNITS : PCS
//             * HPROGUID : 000000HPSTACKCHECKLIST00000000003646
//             * IPERTAXRATE : 11.624
//             * FQUANTITYS : 4
//             * HPRGUIDCH : null
//             * MOVEFMONDEY : 0
//             * CINVSTD : 140/EQ145T_6891车
//             * CINVNAME : XXXX定根销/后制动蹄销
//             * MOVEIPERTAXRATE : 0
//             * HPSNGUID : null
//             * MOVEFTAXRATE : 0
//             */
//
//            private String CPOSNAME;
//            private int TAXAMOUNT;
//            private double FMONDEY;
//            private int MOVEFUNITPRICE;
//            private String CBATCH;
//            private String HPIGUID;
//            private int MOVEFTAXPRICE;
//            private double FUNITPRICE;
//            private int MOVETAXAMOUNT;
//            private int FTAXPRICE;
//            private String CWHCODE;
//            private int FTAXRATE;
//            private String CCOMUNITNAME;
//            private int IBATCH;
//            private String CINVCODE;
//            private String CPOSCODE;
//            private String UNITS;
//            private String HPROGUID;
//            private double IPERTAXRATE;
//            private int FQUANTITYS;
//            private Object HPRGUIDCH;
//            private int MOVEFMONDEY;
//            private String CINVSTD;
//            private String CINVNAME;
//            private int MOVEIPERTAXRATE;
//            private Object HPSNGUID;
//            private int MOVEFTAXRATE;
//
//            public String getCPOSNAME() {
//                return CPOSNAME;
//            }
//
//            public void setCPOSNAME(String CPOSNAME) {
//                this.CPOSNAME = CPOSNAME;
//            }
//
//            public int getTAXAMOUNT() {
//                return TAXAMOUNT;
//            }
//
//            public void setTAXAMOUNT(int TAXAMOUNT) {
//                this.TAXAMOUNT = TAXAMOUNT;
//            }
//
//            public double getFMONDEY() {
//                return FMONDEY;
//            }
//
//            public void setFMONDEY(double FMONDEY) {
//                this.FMONDEY = FMONDEY;
//            }
//
//            public int getMOVEFUNITPRICE() {
//                return MOVEFUNITPRICE;
//            }
//
//            public void setMOVEFUNITPRICE(int MOVEFUNITPRICE) {
//                this.MOVEFUNITPRICE = MOVEFUNITPRICE;
//            }
//
//            public String getCBATCH() {
//                return CBATCH;
//            }
//
//            public void setCBATCH(String CBATCH) {
//                this.CBATCH = CBATCH;
//            }
//
//            public String getHPIGUID() {
//                return HPIGUID;
//            }
//
//            public void setHPIGUID(String HPIGUID) {
//                this.HPIGUID = HPIGUID;
//            }
//
//            public int getMOVEFTAXPRICE() {
//                return MOVEFTAXPRICE;
//            }
//
//            public void setMOVEFTAXPRICE(int MOVEFTAXPRICE) {
//                this.MOVEFTAXPRICE = MOVEFTAXPRICE;
//            }
//
//            public double getFUNITPRICE() {
//                return FUNITPRICE;
//            }
//
//            public void setFUNITPRICE(double FUNITPRICE) {
//                this.FUNITPRICE = FUNITPRICE;
//            }
//
//            public int getMOVETAXAMOUNT() {
//                return MOVETAXAMOUNT;
//            }
//
//            public void setMOVETAXAMOUNT(int MOVETAXAMOUNT) {
//                this.MOVETAXAMOUNT = MOVETAXAMOUNT;
//            }
//
//            public int getFTAXPRICE() {
//                return FTAXPRICE;
//            }
//
//            public void setFTAXPRICE(int FTAXPRICE) {
//                this.FTAXPRICE = FTAXPRICE;
//            }
//
//            public String getCWHCODE() {
//                return CWHCODE;
//            }
//
//            public void setCWHCODE(String CWHCODE) {
//                this.CWHCODE = CWHCODE;
//            }
//
//            public int getFTAXRATE() {
//                return FTAXRATE;
//            }
//
//            public void setFTAXRATE(int FTAXRATE) {
//                this.FTAXRATE = FTAXRATE;
//            }
//
//            public String getCCOMUNITNAME() {
//                return CCOMUNITNAME;
//            }
//
//            public void setCCOMUNITNAME(String CCOMUNITNAME) {
//                this.CCOMUNITNAME = CCOMUNITNAME;
//            }
//
//            public int getIBATCH() {
//                return IBATCH;
//            }
//
//            public void setIBATCH(int IBATCH) {
//                this.IBATCH = IBATCH;
//            }
//
//            public String getCINVCODE() {
//                return CINVCODE;
//            }
//
//            public void setCINVCODE(String CINVCODE) {
//                this.CINVCODE = CINVCODE;
//            }
//
//            public String getCPOSCODE() {
//                return CPOSCODE;
//            }
//
//            public void setCPOSCODE(String CPOSCODE) {
//                this.CPOSCODE = CPOSCODE;
//            }
//
//            public String getUNITS() {
//                return UNITS;
//            }
//
//            public void setUNITS(String UNITS) {
//                this.UNITS = UNITS;
//            }
//
//            public String getHPROGUID() {
//                return HPROGUID;
//            }
//
//            public void setHPROGUID(String HPROGUID) {
//                this.HPROGUID = HPROGUID;
//            }
//
//            public double getIPERTAXRATE() {
//                return IPERTAXRATE;
//            }
//
//            public void setIPERTAXRATE(double IPERTAXRATE) {
//                this.IPERTAXRATE = IPERTAXRATE;
//            }
//
//            public int getFQUANTITYS() {
//                return FQUANTITYS;
//            }
//
//            public void setFQUANTITYS(int FQUANTITYS) {
//                this.FQUANTITYS = FQUANTITYS;
//            }
//
//            public Object getHPRGUIDCH() {
//                return HPRGUIDCH;
//            }
//
//            public void setHPRGUIDCH(Object HPRGUIDCH) {
//                this.HPRGUIDCH = HPRGUIDCH;
//            }
//
//            public int getMOVEFMONDEY() {
//                return MOVEFMONDEY;
//            }
//
//            public void setMOVEFMONDEY(int MOVEFMONDEY) {
//                this.MOVEFMONDEY = MOVEFMONDEY;
//            }
//
//            public String getCINVSTD() {
//                return CINVSTD;
//            }
//
//            public void setCINVSTD(String CINVSTD) {
//                this.CINVSTD = CINVSTD;
//            }
//
//            public String getCINVNAME() {
//                return CINVNAME;
//            }
//
//            public void setCINVNAME(String CINVNAME) {
//                this.CINVNAME = CINVNAME;
//            }
//
//            public int getMOVEIPERTAXRATE() {
//                return MOVEIPERTAXRATE;
//            }
//
//            public void setMOVEIPERTAXRATE(int MOVEIPERTAXRATE) {
//                this.MOVEIPERTAXRATE = MOVEIPERTAXRATE;
//            }
//
//            public Object getHPSNGUID() {
//                return HPSNGUID;
//            }
//
//            public void setHPSNGUID(Object HPSNGUID) {
//                this.HPSNGUID = HPSNGUID;
//            }
//
//            public int getMOVEFTAXRATE() {
//                return MOVEFTAXRATE;
//            }
//
//            public void setMOVEFTAXRATE(int MOVEFTAXRATE) {
//                this.MOVEFTAXRATE = MOVEFTAXRATE;
//            }
//        }
    }
}