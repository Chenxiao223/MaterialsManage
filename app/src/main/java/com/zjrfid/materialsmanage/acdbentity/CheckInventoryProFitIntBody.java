package com.zjrfid.materialsmanage.acdbentity;

import java.util.List;

/**
 * Created by Administrator on 2017/4/11. 盘点入库单查询（详细）表体
 */
public class CheckInventoryProFitIntBody {

    /**
     * statusCode : 200
     * message : 操作成功
     * navTabId :
     * forwordUrl :
     * callbackType :
     * jsonData : {"currentPage":1,"list":[{"CPARENTID":"一楼A04货架第一层第一格","TAXAMOUNT":32.8,"FMONDEY":28.0342,"HPRGUIDCH":"0000000000HPRDRECORDCH17010521327263","CBATCH":"C1100105017500000005","HPIGUID":"00000000000HPINVENTORY00000000000130","CINVSTD":"M7600-1306004B 75度_23-25XX车用","FUNITPRICE":14.0171,"CINVNAME":"节温器","FTAXPRICE":16.4,"HPRGUID":"000000000000HPRDRECORD17010521327262","IBATCH":1,"CCOMUNITNAME":"PCS","FQUANTITY":2,"CINVCODE":"1000000130","CPOSCODE":"010101100019"}],"numPerPage":10,"pageNumShown":10,"totalCount":1}
     */

    public String statusCode;
    public String message;
    public String navTabId;
    public String forwordUrl;
    public String callbackType;
    public JsonDataBean jsonData;

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
         * list : [{"CPARENTID":"一楼A04货架第一层第一格","TAXAMOUNT":32.8,"FMONDEY":28.0342,"HPRGUIDCH":"0000000000HPRDRECORDCH17010521327263","CBATCH":"C1100105017500000005","HPIGUID":"00000000000HPINVENTORY00000000000130","CINVSTD":"M7600-1306004B 75度_23-25XX车用","FUNITPRICE":14.0171,"CINVNAME":"节温器","FTAXPRICE":16.4,"HPRGUID":"000000000000HPRDRECORD17010521327262","IBATCH":1,"CCOMUNITNAME":"PCS","FQUANTITY":2,"CINVCODE":"1000000130","CPOSCODE":"010101100019"}]
         * numPerPage : 10
         * pageNumShown : 10
         * totalCount : 1
         */

        public String currentPage;
        public String numPerPage;
        public String pageNumShown;
        public String totalCount;
        public List<ListBean> list;

        public String getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(String currentPage) {
            this.currentPage = currentPage;
        }

        public String getNumPerPage() {
            return numPerPage;
        }

        public void setNumPerPage(String numPerPage) {
            this.numPerPage = numPerPage;
        }

        public String getPageNumShown() {
            return pageNumShown;
        }

        public void setPageNumShown(String pageNumShown) {
            this.pageNumShown = pageNumShown;
        }

        public String getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(String totalCount) {
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
             * CPARENTID : 一楼A04货架第一层第一格
             * TAXAMOUNT : 32.8
             * FMONDEY : 28.0342
             * HPRGUIDCH : 0000000000HPRDRECORDCH17010521327263
             * CBATCH : C1100105017500000005
             * HPIGUID : 00000000000HPINVENTORY00000000000130
             * CINVSTD : M7600-1306004B 75度_23-25XX车用
             * FUNITPRICE : 14.0171
             * CINVNAME : 节温器
             * FTAXPRICE : 16.4
             * HPRGUID : 000000000000HPRDRECORD17010521327262
             * IBATCH : 1
             * CCOMUNITNAME : PCS
             * FQUANTITY : 2
             * CINVCODE : 1000000130
             * CPOSCODE : 010101100019
             */

            public String CPARENTID;
            public String TAXAMOUNT;
            public String FMONDEY;
            public String HPRGUIDCH;
            public String CBATCH;
            public String HPIGUID;
            public String CINVSTD;
            public String FUNITPRICE;
            public String CINVNAME;
            public String FTAXPRICE;
            public String HPRGUID;
            public String IBATCH;
            public String CCOMUNITNAME;
            public String FQUANTITY;
            public String CINVCODE;
            public String CPOSCODE;

            public String getCPARENTID() {
                return CPARENTID;
            }

            public void setCPARENTID(String CPARENTID) {
                this.CPARENTID = CPARENTID;
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

            public String getHPRGUIDCH() {
                return HPRGUIDCH;
            }

            public void setHPRGUIDCH(String HPRGUIDCH) {
                this.HPRGUIDCH = HPRGUIDCH;
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

            public String getCINVSTD() {
                return CINVSTD;
            }

            public void setCINVSTD(String CINVSTD) {
                this.CINVSTD = CINVSTD;
            }

            public String getFUNITPRICE() {
                return FUNITPRICE;
            }

            public void setFUNITPRICE(String FUNITPRICE) {
                this.FUNITPRICE = FUNITPRICE;
            }

            public String getCINVNAME() {
                return CINVNAME;
            }

            public void setCINVNAME(String CINVNAME) {
                this.CINVNAME = CINVNAME;
            }

            public String getFTAXPRICE() {
                return FTAXPRICE;
            }

            public void setFTAXPRICE(String FTAXPRICE) {
                this.FTAXPRICE = FTAXPRICE;
            }

            public String getHPRGUID() {
                return HPRGUID;
            }

            public void setHPRGUID(String HPRGUID) {
                this.HPRGUID = HPRGUID;
            }

            public String getIBATCH() {
                return IBATCH;
            }

            public void setIBATCH(String IBATCH) {
                this.IBATCH = IBATCH;
            }

            public String getCCOMUNITNAME() {
                return CCOMUNITNAME;
            }

            public void setCCOMUNITNAME(String CCOMUNITNAME) {
                this.CCOMUNITNAME = CCOMUNITNAME;
            }

            public String getFQUANTITY() {
                return FQUANTITY;
            }

            public void setFQUANTITY(String FQUANTITY) {
                this.FQUANTITY = FQUANTITY;
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
        }
    }
}
