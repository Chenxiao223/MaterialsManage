package com.zjrfid.materialsmanage.acdbentity;

import java.util.List;

/**
 * Created by chenxiao on 2017/4/10.
 * 盘点单查询（详细）表体
 */
public class InventoryTB {
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
            private String UTAXPRICE;
            private String CPARENTID;
            private String IPERTAXRATE;
            private String TAXAMOUNT;
            private String FMONDEY;
            private String YTAXPRICE;
            private String HPCVGUID;
            private String UPERTAXRATE;
            private String HPIGUID;
            private String CBATCH;
            private String FUNITPRICE;
            private String CINVNAME;
            private String CINVSTD;
            private String FTAXPRICE;
            private String FTAXRATE;
            private String PQUANTITY;
            private String CCOMUNITNAME;
            private String FQUANTITY;
            private String CINVCODE;
            private String HPCVGUIDCH;
            private String YQUANTITY;
            private String CPOSCODE;
            private String CDEMO;

            @Override
            public String toString() {
                return "ListBean{" +
                        "UTAXPRICE='" + UTAXPRICE + '\'' +
                        ", CPARENTID='" + CPARENTID + '\'' +
                        ", IPERTAXRATE='" + IPERTAXRATE + '\'' +
                        ", TAXAMOUNT='" + TAXAMOUNT + '\'' +
                        ", FMONDEY='" + FMONDEY + '\'' +
                        ", YTAXPRICE='" + YTAXPRICE + '\'' +
                        ", HPCVGUID='" + HPCVGUID + '\'' +
                        ", UPERTAXRATE='" + UPERTAXRATE + '\'' +
                        ", HPIGUID='" + HPIGUID + '\'' +
                        ", CBATCH='" + CBATCH + '\'' +
                        ", FUNITPRICE='" + FUNITPRICE + '\'' +
                        ", CINVNAME='" + CINVNAME + '\'' +
                        ", CINVSTD='" + CINVSTD + '\'' +
                        ", FTAXPRICE='" + FTAXPRICE + '\'' +
                        ", FTAXRATE='" + FTAXRATE + '\'' +
                        ", PQUANTITY='" + PQUANTITY + '\'' +
                        ", CCOMUNITNAME='" + CCOMUNITNAME + '\'' +
                        ", FQUANTITY='" + FQUANTITY + '\'' +
                        ", CINVCODE='" + CINVCODE + '\'' +
                        ", HPCVGUIDCH='" + HPCVGUIDCH + '\'' +
                        ", YQUANTITY='" + YQUANTITY + '\'' +
                        ", CPOSCODE='" + CPOSCODE + '\'' +
                        ", CDEMO='" + CDEMO + '\'' +
                        '}';
            }


            public String getUTAXPRICE() {
                return UTAXPRICE;
            }

            public void setUTAXPRICE(String UTAXPRICE) {
                this.UTAXPRICE = UTAXPRICE;
            }

            public String getCPARENTID() {
                return CPARENTID;
            }

            public void setCPARENTID(String CPARENTID) {
                this.CPARENTID = CPARENTID;
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

            public String getYTAXPRICE() {
                return YTAXPRICE;
            }

            public void setYTAXPRICE(String YTAXPRICE) {
                this.YTAXPRICE = YTAXPRICE;
            }

            public String getHPCVGUID() {
                return HPCVGUID;
            }

            public void setHPCVGUID(String HPCVGUID) {
                this.HPCVGUID = HPCVGUID;
            }

            public String getUPERTAXRATE() {
                return UPERTAXRATE;
            }

            public void setUPERTAXRATE(String UPERTAXRATE) {
                this.UPERTAXRATE = UPERTAXRATE;
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

            public String getCINVSTD() {
                return CINVSTD;
            }

            public void setCINVSTD(String CINVSTD) {
                this.CINVSTD = CINVSTD;
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

            public String getPQUANTITY() {
                return PQUANTITY;
            }

            public void setPQUANTITY(String PQUANTITY) {
                this.PQUANTITY = PQUANTITY;
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

            public String getHPCVGUIDCH() {
                return HPCVGUIDCH;
            }

            public void setHPCVGUIDCH(String HPCVGUIDCH) {
                this.HPCVGUIDCH = HPCVGUIDCH;
            }

            public String getYQUANTITY() {
                return YQUANTITY;
            }

            public void setYQUANTITY(String YQUANTITY) {
                this.YQUANTITY = YQUANTITY;
            }

            public String getCPOSCODE() {
                return CPOSCODE;
            }

            public void setCPOSCODE(String CPOSCODE) {
                this.CPOSCODE = CPOSCODE;
            }

            public String getCDEMO() {
                return CDEMO;
            }

            public void setCDEMO(String CDEMO) {
                this.CDEMO = CDEMO;
            }
        }
    }

}
