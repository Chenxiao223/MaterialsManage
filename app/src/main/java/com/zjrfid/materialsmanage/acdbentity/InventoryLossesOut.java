package com.zjrfid.materialsmanage.acdbentity;

import java.util.List;

/**
 * Created by Administrator on 2017/4/10. 盘亏出库实体类
 */
public class InventoryLossesOut {

    /**
     * statusCode : 200
     * message : 操作成功
     * navTabId :
     * forwordUrl :
     * callbackType :
     * jsonData : {"currentPage":1,"list":[{"CDEMO":null,"IWHPOS":"1","TAXAMOUNT":1007,"FMONDEY":860.6829,"HPIGUID":"00000000000HPINVENTORY00000000002107","CBATCH":"C0520806011500000001","CMAKER":"610214|黄晓承","FUNITPRICE":45.2991,"FTAXPRICE":53,"DKEEPDATE":"2017-04-07","HPRGUID":"000000000000HPRDRECORD17040721329205","CWHCODE":"01","CHANDLER":null,"CCODE":"PD201704070004","CCOMUNITNAME":"KG","IBATCH":1,"FQUANTITY":19,"CINVCODE":"1000002107","CPOSCODE":"010104400001","CPARENTID":"W01","HPRGUIDCH":"0000000000HPRDRECORDCH17040721329207","CINVNAME":"油漆","CINVSTD":"G2-003白","CPERSONNAME":"黄晓承","CWHNAME":"莲花停保场仓库","ORGNAME":"计划财务部"},{"CDEMO":null,"IWHPOS":"1","TAXAMOUNT":225,"FMONDEY":192.3075,"HPIGUID":"00000000000HPINVENTORY00000000002107","CBATCH":"C0300806011500000001","CMAKER":"610214|黄晓承","FUNITPRICE":38.4615,"FTAXPRICE":45,"DKEEPDATE":"2017-04-07","HPRGUID":"000000000000HPRDRECORD17040721329205","CWHCODE":"01","CHANDLER":null,"CCODE":"PD201704070004","CCOMUNITNAME":"KG","IBATCH":1,"FQUANTITY":5,"CINVCODE":"1000002107","CPOSCODE":"010104400001","CPARENTID":"W01","HPRGUIDCH":"0000000000HPRDRECORDCH17040721329206","CINVNAME":"油漆","CINVSTD":"G2-003白","CPERSONNAME":"黄晓承","CWHNAME":"莲花停保场仓库","ORGNAME":"计划财务部"},{"CDEMO":null,"IWHPOS":"1","TAXAMOUNT":112.5,"FMONDEY":96.1538,"HPIGUID":"00000000000HPINVENTORY00000000002107","CBATCH":"C0300806011500000001","CMAKER":"610214|黄晓承","FUNITPRICE":38.4615,"FTAXPRICE":45,"DKEEPDATE":"2017-04-01","HPRGUID":"000000000000HPRDRECORD17040521329187","CWHCODE":"01","CHANDLER":"610214|黄晓承","CCODE":"PD201704010011","CCOMUNITNAME":"KG","IBATCH":1,"FQUANTITY":2.5,"CINVCODE":"1000002107","CPOSCODE":"010104400001","CPARENTID":"W01","HPRGUIDCH":"0000000000HPRDRECORDCH17040521329188","CINVNAME":"油漆","CINVSTD":"G2-003白","CPERSONNAME":"黄晓承","CWHNAME":"莲花停保场仓库","ORGNAME":"计划财务部"},{"CDEMO":null,"IWHPOS":"1","TAXAMOUNT":112.5,"FMONDEY":96.1538,"HPIGUID":"00000000000HPINVENTORY00000000002107","CBATCH":"C0300806011500000001","CMAKER":"610214|黄晓承","FUNITPRICE":38.4615,"FTAXPRICE":45,"DKEEPDATE":"2017-04-01","HPRGUID":"000000000000HPRDRECORD17040121329145","CWHCODE":"01","CHANDLER":"610214|黄晓承","CCODE":"PD201704010011","CCOMUNITNAME":"KG","IBATCH":1,"FQUANTITY":2.5,"CINVCODE":"1000002107","CPOSCODE":"010104400001","CPARENTID":"W01","HPRGUIDCH":"0000000000HPRDRECORDCH17040121329146","CINVNAME":"油漆","CINVSTD":"G2-003白","CPERSONNAME":"黄晓承","CWHNAME":"莲花停保场仓库","ORGNAME":"计划财务部"},{"CDEMO":null,"IWHPOS":"1","TAXAMOUNT":22.5,"FMONDEY":19.2308,"HPIGUID":"00000000000HPINVENTORY00000000002107","CBATCH":"C0300806011500000001","CMAKER":"610214|黄晓承","FUNITPRICE":38.4615,"FTAXPRICE":45,"DKEEPDATE":"2017-03-31","HPRGUID":"000000000000HPRDRECORD17033121329040","CWHCODE":"01","CHANDLER":null,"CCODE":"PD201703310002","CCOMUNITNAME":"KG","IBATCH":1,"FQUANTITY":0.5,"CINVCODE":"1000002107","CPOSCODE":"010104400001","CPARENTID":"W01","HPRGUIDCH":"0000000000HPRDRECORDCH17033121329041","CINVNAME":"油漆","CINVSTD":"G2-003白","CPERSONNAME":"黄晓承","CWHNAME":"莲花停保场仓库","ORGNAME":"计划财务部"}],"numPerPage":10,"pageNumShown":10,"totalCount":5}
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
         * list : [{"CDEMO":null,"IWHPOS":"1","TAXAMOUNT":1007,"FMONDEY":860.6829,"HPIGUID":"00000000000HPINVENTORY00000000002107","CBATCH":"C0520806011500000001","CMAKER":"610214|黄晓承","FUNITPRICE":45.2991,"FTAXPRICE":53,"DKEEPDATE":"2017-04-07","HPRGUID":"000000000000HPRDRECORD17040721329205","CWHCODE":"01","CHANDLER":null,"CCODE":"PD201704070004","CCOMUNITNAME":"KG","IBATCH":1,"FQUANTITY":19,"CINVCODE":"1000002107","CPOSCODE":"010104400001","CPARENTID":"W01","HPRGUIDCH":"0000000000HPRDRECORDCH17040721329207","CINVNAME":"油漆","CINVSTD":"G2-003白","CPERSONNAME":"黄晓承","CWHNAME":"莲花停保场仓库","ORGNAME":"计划财务部"},{"CDEMO":null,"IWHPOS":"1","TAXAMOUNT":225,"FMONDEY":192.3075,"HPIGUID":"00000000000HPINVENTORY00000000002107","CBATCH":"C0300806011500000001","CMAKER":"610214|黄晓承","FUNITPRICE":38.4615,"FTAXPRICE":45,"DKEEPDATE":"2017-04-07","HPRGUID":"000000000000HPRDRECORD17040721329205","CWHCODE":"01","CHANDLER":null,"CCODE":"PD201704070004","CCOMUNITNAME":"KG","IBATCH":1,"FQUANTITY":5,"CINVCODE":"1000002107","CPOSCODE":"010104400001","CPARENTID":"W01","HPRGUIDCH":"0000000000HPRDRECORDCH17040721329206","CINVNAME":"油漆","CINVSTD":"G2-003白","CPERSONNAME":"黄晓承","CWHNAME":"莲花停保场仓库","ORGNAME":"计划财务部"},{"CDEMO":null,"IWHPOS":"1","TAXAMOUNT":112.5,"FMONDEY":96.1538,"HPIGUID":"00000000000HPINVENTORY00000000002107","CBATCH":"C0300806011500000001","CMAKER":"610214|黄晓承","FUNITPRICE":38.4615,"FTAXPRICE":45,"DKEEPDATE":"2017-04-01","HPRGUID":"000000000000HPRDRECORD17040521329187","CWHCODE":"01","CHANDLER":"610214|黄晓承","CCODE":"PD201704010011","CCOMUNITNAME":"KG","IBATCH":1,"FQUANTITY":2.5,"CINVCODE":"1000002107","CPOSCODE":"010104400001","CPARENTID":"W01","HPRGUIDCH":"0000000000HPRDRECORDCH17040521329188","CINVNAME":"油漆","CINVSTD":"G2-003白","CPERSONNAME":"黄晓承","CWHNAME":"莲花停保场仓库","ORGNAME":"计划财务部"},{"CDEMO":null,"IWHPOS":"1","TAXAMOUNT":112.5,"FMONDEY":96.1538,"HPIGUID":"00000000000HPINVENTORY00000000002107","CBATCH":"C0300806011500000001","CMAKER":"610214|黄晓承","FUNITPRICE":38.4615,"FTAXPRICE":45,"DKEEPDATE":"2017-04-01","HPRGUID":"000000000000HPRDRECORD17040121329145","CWHCODE":"01","CHANDLER":"610214|黄晓承","CCODE":"PD201704010011","CCOMUNITNAME":"KG","IBATCH":1,"FQUANTITY":2.5,"CINVCODE":"1000002107","CPOSCODE":"010104400001","CPARENTID":"W01","HPRGUIDCH":"0000000000HPRDRECORDCH17040121329146","CINVNAME":"油漆","CINVSTD":"G2-003白","CPERSONNAME":"黄晓承","CWHNAME":"莲花停保场仓库","ORGNAME":"计划财务部"},{"CDEMO":null,"IWHPOS":"1","TAXAMOUNT":22.5,"FMONDEY":19.2308,"HPIGUID":"00000000000HPINVENTORY00000000002107","CBATCH":"C0300806011500000001","CMAKER":"610214|黄晓承","FUNITPRICE":38.4615,"FTAXPRICE":45,"DKEEPDATE":"2017-03-31","HPRGUID":"000000000000HPRDRECORD17033121329040","CWHCODE":"01","CHANDLER":null,"CCODE":"PD201703310002","CCOMUNITNAME":"KG","IBATCH":1,"FQUANTITY":0.5,"CINVCODE":"1000002107","CPOSCODE":"010104400001","CPARENTID":"W01","HPRGUIDCH":"0000000000HPRDRECORDCH17033121329041","CINVNAME":"油漆","CINVSTD":"G2-003白","CPERSONNAME":"黄晓承","CWHNAME":"莲花停保场仓库","ORGNAME":"计划财务部"}]
         * numPerPage : 10
         * pageNumShown : 10
         * totalCount : 5
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
             * CDEMO : null
             * IWHPOS : 1
             * TAXAMOUNT : 1007
             * FMONDEY : 860.6829
             * HPIGUID : 00000000000HPINVENTORY00000000002107
             * CBATCH : C0520806011500000001
             * CMAKER : 610214|黄晓承
             * FUNITPRICE : 45.2991
             * FTAXPRICE : 53
             * DKEEPDATE : 2017-04-07
             * HPRGUID : 000000000000HPRDRECORD17040721329205
             * CWHCODE : 01
             * CHANDLER : null
             * CCODE : PD201704070004
             * CCOMUNITNAME : KG
             * IBATCH : 1
             * FQUANTITY : 19
             * CINVCODE : 1000002107
             * CPOSCODE : 010104400001
             * CPARENTID : W01
             * HPRGUIDCH : 0000000000HPRDRECORDCH17040721329207
             * CINVNAME : 油漆
             * CINVSTD : G2-003白
             * CPERSONNAME : 黄晓承
             * CWHNAME : 莲花停保场仓库
             * ORGNAME : 计划财务部
             */

            private String CDEMO;
            private String IWHPOS;
            private String TAXAMOUNT;
            private String FMONDEY;
            private String HPIGUID;
            private String CBATCH;
            private String CMAKER;
            private String FUNITPRICE;
            private String FTAXPRICE;
            private String DKEEPDATE;
            private String HPRGUID;
            private String CWHCODE;
            private String CHANDLER;
            private String CCODE;
            private String CCOMUNITNAME;
            private String IBATCH;
            private String FQUANTITY;
            private String CINVCODE;
            private String CPOSCODE;
            private String CPARENTID;
            private String HPRGUIDCH;
            private String CINVNAME;
            private String CINVSTD;
            private String CPERSONNAME;
            private String CWHNAME;
            private String ORGNAME;

            public String getCDEMO() {
                return CDEMO;
            }

            public void setCDEMO(String CDEMO) {
                this.CDEMO = CDEMO;
            }

            public String getIWHPOS() {
                return IWHPOS;
            }

            public void setIWHPOS(String IWHPOS) {
                this.IWHPOS = IWHPOS;
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

            public String getHPIGUID() {
                return HPIGUID;
            }

            public void setHPIGUID(String HPIGUID) {
                this.HPIGUID = HPIGUID;
            }

            public String getCBATCH() {
                return CBATCH;
            }

            public void setCBATCH(String CBATCH) {
                this.CBATCH = CBATCH;
            }

            public String getCMAKER() {
                return CMAKER;
            }

            public void setCMAKER(String CMAKER) {
                this.CMAKER = CMAKER;
            }

            public String getFUNITPRICE() {
                return FUNITPRICE;
            }

            public void setFUNITPRICE(String FUNITPRICE) {
                this.FUNITPRICE = FUNITPRICE;
            }

            public String getFTAXPRICE() {
                return FTAXPRICE;
            }

            public void setFTAXPRICE(String FTAXPRICE) {
                this.FTAXPRICE = FTAXPRICE;
            }

            public String getDKEEPDATE() {
                return DKEEPDATE;
            }

            public void setDKEEPDATE(String DKEEPDATE) {
                this.DKEEPDATE = DKEEPDATE;
            }

            public String getHPRGUID() {
                return HPRGUID;
            }

            public void setHPRGUID(String HPRGUID) {
                this.HPRGUID = HPRGUID;
            }

            public String getCWHCODE() {
                return CWHCODE;
            }

            public void setCWHCODE(String CWHCODE) {
                this.CWHCODE = CWHCODE;
            }

            public String getCHANDLER() {
                return CHANDLER;
            }

            public void setCHANDLER(String CHANDLER) {
                this.CHANDLER = CHANDLER;
            }

            public String getCCODE() {
                return CCODE;
            }

            public void setCCODE(String CCODE) {
                this.CCODE = CCODE;
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

            public String getCPARENTID() {
                return CPARENTID;
            }

            public void setCPARENTID(String CPARENTID) {
                this.CPARENTID = CPARENTID;
            }

            public String getHPRGUIDCH() {
                return HPRGUIDCH;
            }

            public void setHPRGUIDCH(String HPRGUIDCH) {
                this.HPRGUIDCH = HPRGUIDCH;
            }

            public String getCINVNAME() {
                return CINVNAME;
            }

            public void setCINVNAME(String CINVNAME) {
                this.CINVNAME = CINVNAME;
            }

            public String getCINVSTD() {
                return CINVSTD;
            }

            public void setCINVSTD(String CINVSTD) {
                this.CINVSTD = CINVSTD;
            }

            public String getCPERSONNAME() {
                return CPERSONNAME;
            }

            public void setCPERSONNAME(String CPERSONNAME) {
                this.CPERSONNAME = CPERSONNAME;
            }

            public String getCWHNAME() {
                return CWHNAME;
            }

            public void setCWHNAME(String CWHNAME) {
                this.CWHNAME = CWHNAME;
            }

            public String getORGNAME() {
                return ORGNAME;
            }

            public void setORGNAME(String ORGNAME) {
                this.ORGNAME = ORGNAME;
            }
        }
    }
}
