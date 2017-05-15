package com.zjrfid.materialsmanage.acdbentity;

import java.util.List;

/**
 * Created by Administrator on 2017/4/11.盘点入库单(列表实体类)
 */
public class InventoryProFitInt {


    /**
     * statusCode : 200
     * message : 操作成功
     * navTabId :
     * forwordUrl :
     * callbackType :
     * jsonData : {"currentPage":1,"list":[{"CDEMO":null,"IWHPOS":"1","TAXAMOUNT":4,"FMONDEY":3.4188,"HPIGUID":"00000000000HPINVENTORY00000000002100","CBATCH":"C0830806010200000001","CMAKER":"610214|黄晓承","FUNITPRICE":1.7094,"FTAXPRICE":2,"DKEEPDATE":"2017-04-11","HPRGUID":"000000000000HPRDRECORD17041121329227","CWHCODE":"01","CHANDLER":null,"CCODE":"PD201704110013","CCOMUNITNAME":"PCS","IBATCH":1,"FQUANTITY":2,"CINVCODE":"1000002100","CPOSCODE":"010101100183","CPARENTID":"一楼C05货架第三层第二格","HPRGUIDCH":"0000000000HPRDRECORDCH17041121329228","CINVNAME":"白粉笔","CINVSTD":"50支/盒","CPERSONNAME":"黄晓承","CWHNAME":"莲花停保场仓库","ORGNAME":"计划财务部"},{"CDEMO":null,"IWHPOS":"1","TAXAMOUNT":1,"FMONDEY":0.8547,"HPIGUID":"00000000000HPINVENTORY00000000002107","CBATCH":"123123123","CMAKER":"610214|黄晓承","FUNITPRICE":0.8547,"FTAXPRICE":1,"DKEEPDATE":"2017-04-07","HPRGUID":"000000000000HPRDRECORD17040721329208","CWHCODE":"01","CHANDLER":null,"CCODE":"PD201704070004","CCOMUNITNAME":"KG","IBATCH":1,"FQUANTITY":1,"CINVCODE":"1000002107","CPOSCODE":"010104400001","CPARENTID":"W01","HPRGUIDCH":"0000000000HPRDRECORDCH17040721329209","CINVNAME":"油漆","CINVSTD":"G2-003白","CPERSONNAME":"黄晓承","CWHNAME":"莲花停保场仓库","ORGNAME":"计划财务部"},{"CDEMO":null,"IWHPOS":"1","TAXAMOUNT":45,"FMONDEY":38.4615,"HPIGUID":"00000000000HPINVENTORY00000000002107","CBATCH":"C0300806011500000001","CMAKER":"610214|黄晓承","FUNITPRICE":38.4615,"FTAXPRICE":45,"DKEEPDATE":"2017-04-01","HPRGUID":"000000000000HPRDRECORD17040121329151","CWHCODE":"01","CHANDLER":"610214","CCODE":"PD201704010013","CCOMUNITNAME":"KG","IBATCH":1,"FQUANTITY":1,"CINVCODE":"1000002107","CPOSCODE":"010104400001","CPARENTID":"W01","HPRGUIDCH":"0000000000HPRDRECORDCH17040121329152","CINVNAME":"油漆","CINVSTD":"G2-003白","CPERSONNAME":"黄晓承","CWHNAME":"莲花停保场仓库","ORGNAME":"计划财务部"}],"numPerPage":10,"pageNumShown":10,"totalCount":3}
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
         * list : [{"CDEMO":null,"IWHPOS":"1","TAXAMOUNT":4,"FMONDEY":3.4188,"HPIGUID":"00000000000HPINVENTORY00000000002100","CBATCH":"C0830806010200000001","CMAKER":"610214|黄晓承","FUNITPRICE":1.7094,"FTAXPRICE":2,"DKEEPDATE":"2017-04-11","HPRGUID":"000000000000HPRDRECORD17041121329227","CWHCODE":"01","CHANDLER":null,"CCODE":"PD201704110013","CCOMUNITNAME":"PCS","IBATCH":1,"FQUANTITY":2,"CINVCODE":"1000002100","CPOSCODE":"010101100183","CPARENTID":"一楼C05货架第三层第二格","HPRGUIDCH":"0000000000HPRDRECORDCH17041121329228","CINVNAME":"白粉笔","CINVSTD":"50支/盒","CPERSONNAME":"黄晓承","CWHNAME":"莲花停保场仓库","ORGNAME":"计划财务部"},{"CDEMO":null,"IWHPOS":"1","TAXAMOUNT":1,"FMONDEY":0.8547,"HPIGUID":"00000000000HPINVENTORY00000000002107","CBATCH":"123123123","CMAKER":"610214|黄晓承","FUNITPRICE":0.8547,"FTAXPRICE":1,"DKEEPDATE":"2017-04-07","HPRGUID":"000000000000HPRDRECORD17040721329208","CWHCODE":"01","CHANDLER":null,"CCODE":"PD201704070004","CCOMUNITNAME":"KG","IBATCH":1,"FQUANTITY":1,"CINVCODE":"1000002107","CPOSCODE":"010104400001","CPARENTID":"W01","HPRGUIDCH":"0000000000HPRDRECORDCH17040721329209","CINVNAME":"油漆","CINVSTD":"G2-003白","CPERSONNAME":"黄晓承","CWHNAME":"莲花停保场仓库","ORGNAME":"计划财务部"},{"CDEMO":null,"IWHPOS":"1","TAXAMOUNT":45,"FMONDEY":38.4615,"HPIGUID":"00000000000HPINVENTORY00000000002107","CBATCH":"C0300806011500000001","CMAKER":"610214|黄晓承","FUNITPRICE":38.4615,"FTAXPRICE":45,"DKEEPDATE":"2017-04-01","HPRGUID":"000000000000HPRDRECORD17040121329151","CWHCODE":"01","CHANDLER":"610214","CCODE":"PD201704010013","CCOMUNITNAME":"KG","IBATCH":1,"FQUANTITY":1,"CINVCODE":"1000002107","CPOSCODE":"010104400001","CPARENTID":"W01","HPRGUIDCH":"0000000000HPRDRECORDCH17040121329152","CINVNAME":"油漆","CINVSTD":"G2-003白","CPERSONNAME":"黄晓承","CWHNAME":"莲花停保场仓库","ORGNAME":"计划财务部"}]
         * numPerPage : 10
         * pageNumShown : 10
         * totalCount : 3
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
             * CDEMO : null
             * IWHPOS : 1
             * TAXAMOUNT : 4
             * FMONDEY : 3.4188
             * HPIGUID : 00000000000HPINVENTORY00000000002100
             * CBATCH : C0830806010200000001
             * CMAKER : 610214|黄晓承
             * FUNITPRICE : 1.7094
             * FTAXPRICE : 2
             * DKEEPDATE : 2017-04-11
             * HPRGUID : 000000000000HPRDRECORD17041121329227
             * CWHCODE : 01
             * CHANDLER : null
             * CCODE : PD201704110013
             * CCOMUNITNAME : PCS
             * IBATCH : 1
             * FQUANTITY : 2
             * CINVCODE : 1000002100
             * CPOSCODE : 010101100183
             * CPARENTID : 一楼C05货架第三层第二格
             * HPRGUIDCH : 0000000000HPRDRECORDCH17041121329228
             * CINVNAME : 白粉笔
             * CINVSTD : 50支/盒
             * CPERSONNAME : 黄晓承
             * CWHNAME : 莲花停保场仓库
             * ORGNAME : 计划财务部
             */

            public String CDEMO;
            public String IWHPOS;
            public String TAXAMOUNT;
            public String FMONDEY;
            public String HPIGUID;
            public String CBATCH;
            public String CMAKER;
            public String FUNITPRICE;
            public String FTAXPRICE;
            public String DKEEPDATE;
            public String HPRGUID;
            public String CWHCODE;
            public String CHANDLER;
            public String CCODE;
            public String CCOMUNITNAME;
            public String IBATCH;
            public String FQUANTITY;
            public String CINVCODE;
            public String CPOSCODE;
            public String CPARENTID;
            public String HPRGUIDCH;
            public String CINVNAME;
            public String CINVSTD;
            public String CPERSONNAME;
            public String CWHNAME;
            public String ORGNAME;

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
