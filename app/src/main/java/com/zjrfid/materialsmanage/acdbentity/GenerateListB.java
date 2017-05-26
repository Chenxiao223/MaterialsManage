package com.zjrfid.materialsmanage.acdbentity;

import java.util.List;

/**
 * Created by chenxiao on 2017/5/23.
 * 生单（体）
 */
public class GenerateListB {
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
    public static class JsonDataBean{
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
        public static class ListBean{
            private String CDEMO;
            private String HPPPDID;
            private String CPARENTID;
            private String HPALGUID;
            private String IPERTAXRATE;
            private String TAXAMOUNT;
            private String FMONDEY;
            private String HPIGUID;
            private String HPALPID;
            private String CINVSTD;
            private String CINVNAME;
            private String FUNITPRICE;
            private String FTAXPRICE;
            private String FTAXRATE;
            private String ORDERNUM;
            private String HPWGUID;
            private String CWHNAME;
            private String IBATCH;
            private String CCOMUNITNAME;
            private String FQUANTITY;
            private String CINVCODE;
            private String CPOSCODE;

            @Override
            public String toString() {
                return "ListBean{" +
                        "CDEMO='" + CDEMO + '\'' +
                        ", HPPPDID='" + HPPPDID + '\'' +
                        ", CPARENTID='" + CPARENTID + '\'' +
                        ", HPALGUID='" + HPALGUID + '\'' +
                        ", IPERTAXRATE='" + IPERTAXRATE + '\'' +
                        ", TAXAMOUNT='" + TAXAMOUNT + '\'' +
                        ", FMONDEY='" + FMONDEY + '\'' +
                        ", HPIGUID='" + HPIGUID + '\'' +
                        ", HPALPID='" + HPALPID + '\'' +
                        ", CINVSTD='" + CINVSTD + '\'' +
                        ", CINVNAME='" + CINVNAME + '\'' +
                        ", FUNITPRICE='" + FUNITPRICE + '\'' +
                        ", FTAXPRICE='" + FTAXPRICE + '\'' +
                        ", FTAXRATE='" + FTAXRATE + '\'' +
                        ", ORDERNUM='" + ORDERNUM + '\'' +
                        ", HPWGUID='" + HPWGUID + '\'' +
                        ", CWHNAME='" + CWHNAME + '\'' +
                        ", IBATCH='" + IBATCH + '\'' +
                        ", CCOMUNITNAME='" + CCOMUNITNAME + '\'' +
                        ", FQUANTITY='" + FQUANTITY + '\'' +
                        ", CINVCODE='" + CINVCODE + '\'' +
                        ", CPOSCODE='" + CPOSCODE + '\'' +
                        '}';
            }

            public String getCDEMO() {
                return CDEMO;
            }

            public void setCDEMO(String CDEMO) {
                this.CDEMO = CDEMO;
            }

            public String getHPPPDID() {
                return HPPPDID;
            }

            public void setHPPPDID(String HPPPDID) {
                this.HPPPDID = HPPPDID;
            }

            public String getCPARENTID() {
                return CPARENTID;
            }

            public void setCPARENTID(String CPARENTID) {
                this.CPARENTID = CPARENTID;
            }

            public String getHPALGUID() {
                return HPALGUID;
            }

            public void setHPALGUID(String HPALGUID) {
                this.HPALGUID = HPALGUID;
            }

            public String getIPERTAXRATE() {
                return IPERTAXRATE;
            }

            public void setIPERTAXRATE(String IPERTAXRATE) {
                this.IPERTAXRATE = IPERTAXRATE;
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

            public String getHPALPID() {
                return HPALPID;
            }

            public void setHPALPID(String HPALPID) {
                this.HPALPID = HPALPID;
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

            public String getFTAXRATE() {
                return FTAXRATE;
            }

            public void setFTAXRATE(String FTAXRATE) {
                this.FTAXRATE = FTAXRATE;
            }

            public String getORDERNUM() {
                return ORDERNUM;
            }

            public void setORDERNUM(String ORDERNUM) {
                this.ORDERNUM = ORDERNUM;
            }

            public String getHPWGUID() {
                return HPWGUID;
            }

            public void setHPWGUID(String HPWGUID) {
                this.HPWGUID = HPWGUID;
            }

            public String getCWHNAME() {
                return CWHNAME;
            }

            public void setCWHNAME(String CWHNAME) {
                this.CWHNAME = CWHNAME;
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
