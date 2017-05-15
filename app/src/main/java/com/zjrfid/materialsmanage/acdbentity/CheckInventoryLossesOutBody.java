package com.zjrfid.materialsmanage.acdbentity;

import java.util.List;

/**
 * Created by Administrator on 2017/4/11.盘点出库单查询（详细）表体
 */
public class CheckInventoryLossesOutBody {


    /**
     * statusCode : 200
     * message : 操作成功
     * navTabId :
     * forwordUrl :
     * callbackType :
     * jsonData : {"currentPage":1,"list":[{"CPARENTID":"W01","TAXAMOUNT":112.5,"FMONDEY":96.1538,"HPRGUIDCH":"0000000000HPRDRECORDCH17040121329146","CBATCH":"C0300806011500000001","HPIGUID":"00000000000HPINVENTORY00000000002107","CINVSTD":"G2-003白","FUNITPRICE":38.4615,"CINVNAME":"油漆","FTAXPRICE":45,"HPRGUID":"000000000000HPRDRECORD17040121329145","IBATCH":1,"CCOMUNITNAME":"KG","FQUANTITY":2.5,"CINVCODE":"1000002107","CPOSCODE":"010104400001"}],"numPerPage":10,"pageNumShown":10,"totalCount":1}
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
         * list : [{"CPARENTID":"W01","TAXAMOUNT":112.5,"FMONDEY":96.1538,"HPRGUIDCH":"0000000000HPRDRECORDCH17040121329146","CBATCH":"C0300806011500000001","HPIGUID":"00000000000HPINVENTORY00000000002107","CINVSTD":"G2-003白","FUNITPRICE":38.4615,"CINVNAME":"油漆","FTAXPRICE":45,"HPRGUID":"000000000000HPRDRECORD17040121329145","IBATCH":1,"CCOMUNITNAME":"KG","FQUANTITY":2.5,"CINVCODE":"1000002107","CPOSCODE":"010104400001"}]
         * numPerPage : 10
         * pageNumShown : 10
         * totalCount : 1
         */

        private String currentPage;
        private String numPerPage;
        private String pageNumShown;
        private String totalCount;
        private List<ListBean> list;

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
             * CPARENTID : W01
             * TAXAMOUNT : 112.5
             * FMONDEY : 96.1538
             * HPRGUIDCH : 0000000000HPRDRECORDCH17040121329146
             * CBATCH : C0300806011500000001
             * HPIGUID : 00000000000HPINVENTORY00000000002107
             * CINVSTD : G2-003白
             * FUNITPRICE : 38.4615
             * CINVNAME : 油漆
             * FTAXPRICE : 45
             * HPRGUID : 000000000000HPRDRECORD17040121329145
             * IBATCH : 1
             * CCOMUNITNAME : KG
             * FQUANTITY : 2.5
             * CINVCODE : 1000002107
             * CPOSCODE : 010104400001
             */

            private String CPARENTID;
            private String TAXAMOUNT;
            private String FMONDEY;
            private String HPRGUIDCH;
            private String CBATCH;
            private String HPIGUID;
            private String CINVSTD;
            private String FUNITPRICE;
            private String CINVNAME;
            private String FTAXPRICE;
            private String HPRGUID;
            private String IBATCH;
            private String CCOMUNITNAME;
            private String FQUANTITY;
            private String CINVCODE;
            private String CPOSCODE;

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
