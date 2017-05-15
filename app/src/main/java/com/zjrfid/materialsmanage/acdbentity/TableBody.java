package com.zjrfid.materialsmanage.acdbentity;

import java.util.List;

/**
 * Created by Administrator on 2017/3/13 0013.表体类
 */
public class TableBody {
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
        public static class ListBean{
            private String CDEMO;
            private String CPARENTID;
            private String HPALGUID;
            private String IPERTAXRATE;
            private String TAXAMOUNT;
            private String FMONDEY;
            private String HPRGUIDCH;
            private String CBATCH;
            private String HPIGUID;
            private String CINVSTD;
            private String CINVNAME;
            private String FUNITPRICE;
            private String FTAXPRICE;
            private String HPRGUID;
            private String FTAXRATE;
            private String IBATCH;
            private String CCOMUNITNAME;
            private String FQUANTITY;
            private String CINVCODE;
            private String HPRRGUID;
            private String CPOSCODE;

            @Override
            public String
            toString() {
                return "ListBean{" +
                        "CDEMO='" + CDEMO + '\'' +
                        ", CPARENTID='" + CPARENTID + '\'' +
                        ", HPALGUID='" + HPALGUID + '\'' +
                        ", IPERTAXRATE='" + IPERTAXRATE + '\'' +
                        ", TAXAMOUNT='" + TAXAMOUNT + '\'' +
                        ", FMONDEY='" + FMONDEY + '\'' +
                        ", HPRGUIDCH='" + HPRGUIDCH + '\'' +
                        ", CBATCH='" + CBATCH + '\'' +
                        ", HPIGUID='" + HPIGUID + '\'' +
                        ", CINVSTD='" + CINVSTD + '\'' +
                        ", CINVNAME='" + CINVNAME + '\'' +
                        ", FUNITPRICE='" + FUNITPRICE + '\'' +
                        ", FTAXPRICE='" + FTAXPRICE + '\'' +
                        ", HPRGUID='" + HPRGUID + '\'' +
                        ", FTAXRATE='" + FTAXRATE + '\'' +
                        ", IBATCH='" + IBATCH + '\'' +
                        ", CCOMUNITNAME='" + CCOMUNITNAME + '\'' +
                        ", FQUANTITY='" + FQUANTITY + '\'' +
                        ", CINVCODE='" + CINVCODE + '\'' +
                        ", HPRRGUID='" + HPRRGUID + '\'' +
                        ", CPOSCODE='" + CPOSCODE + '\'' +
                        '}';
            }

            public String getCDEMO() {
                return CDEMO;
            }

            public void setCDEMO(String CDEMO) {
                this.CDEMO = CDEMO;
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

            public String getHPRGUID() {
                return HPRGUID;
            }

            public void setHPRGUID(String HPRGUID) {
                this.HPRGUID = HPRGUID;
            }

            public String getFTAXRATE() {
                return FTAXRATE;
            }

            public void setFTAXRATE(String FTAXRATE) {
                this.FTAXRATE = FTAXRATE;
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

            public String getHPRRGUID() {
                return HPRRGUID;
            }

            public void setHPRRGUID(String HPRRGUID) {
                this.HPRRGUID = HPRRGUID;
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
