package com.zjrfid.materialsmanage.acdbentity;

import java.util.List;

/**
 * Created by Administrator on 2017/6/1 0001.
 */
public class InventoryQuery {
    private String statusCode;
    private String message;
    private String navTabId;
    private String forwordUrl;
    private String callbackType;
    private JsonDataBean jsonData;
    private TotalInfoBean totalInfo;

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

    public TotalInfoBean getTotalInfo() {
        return totalInfo;
    }

    public void setTotalInfo(TotalInfoBean totalInfo) {
        this.totalInfo = totalInfo;
    }

    public static class JsonDataBean {
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
            private String CINVCNAME;
            private String TAXAMOUNT;
            private String FMONDEY;
            private String MOVEFUNITPRICE;
            private String MOVEFTAXPRICE;
            private String CBATCH;
            private String HPIGUID;
            private String FUNITPRICE;
            private String MOVETAXAMOUNT;
            private String FTAXPRICE;
            private String CWHCODE;
            private String FTAXRATE;
            private String HPR_GUID_CH;
            private String CCOMUNITNAME;
            private String FQUANTITY;
            private String CINVCODE;
            private String CPOSCODE;
            private String OLDCORD;
            private String CPARENTID;
            private String IPERTAXRATE;
            private String HPSCPID;
            private String CPOSCODES;
            private String MOVEFMONDEY;
            private String CINVNAME;
            private String CINVSTD;
            private String MOVEIPERTAXRATE;
            private String CWHNAME;
            private String MOVEFTAXRATE;

            @Override
            public String toString() {
                return "ListBean{" +
                        "CINVCNAME='" + CINVCNAME + '\'' +
                        ", TAXAMOUNT='" + TAXAMOUNT + '\'' +
                        ", FMONDEY='" + FMONDEY + '\'' +
                        ", MOVEFUNITPRICE='" + MOVEFUNITPRICE + '\'' +
                        ", MOVEFTAXPRICE='" + MOVEFTAXPRICE + '\'' +
                        ", CBATCH='" + CBATCH + '\'' +
                        ", HPIGUID='" + HPIGUID + '\'' +
                        ", FUNITPRICE='" + FUNITPRICE + '\'' +
                        ", MOVETAXAMOUNT='" + MOVETAXAMOUNT + '\'' +
                        ", FTAXPRICE='" + FTAXPRICE + '\'' +
                        ", CWHCODE='" + CWHCODE + '\'' +
                        ", FTAXRATE='" + FTAXRATE + '\'' +
                        ", HPR_GUID_CH='" + HPR_GUID_CH + '\'' +
                        ", CCOMUNITNAME='" + CCOMUNITNAME + '\'' +
                        ", FQUANTITY='" + FQUANTITY + '\'' +
                        ", CINVCODE='" + CINVCODE + '\'' +
                        ", CPOSCODE='" + CPOSCODE + '\'' +
                        ", OLDCORD='" + OLDCORD + '\'' +
                        ", CPARENTID='" + CPARENTID + '\'' +
                        ", IPERTAXRATE='" + IPERTAXRATE + '\'' +
                        ", HPSCPID='" + HPSCPID + '\'' +
                        ", CPOSCODES='" + CPOSCODES + '\'' +
                        ", MOVEFMONDEY='" + MOVEFMONDEY + '\'' +
                        ", CINVNAME='" + CINVNAME + '\'' +
                        ", CINVSTD='" + CINVSTD + '\'' +
                        ", MOVEIPERTAXRATE='" + MOVEIPERTAXRATE + '\'' +
                        ", CWHNAME='" + CWHNAME + '\'' +
                        ", MOVEFTAXRATE='" + MOVEFTAXRATE + '\'' +
                        '}';
            }

            public String getCINVCNAME() {
                return CINVCNAME;
            }

            public void setCINVCNAME(String CINVCNAME) {
                this.CINVCNAME = CINVCNAME;
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

            public String getMOVEFTAXPRICE() {
                return MOVEFTAXPRICE;
            }

            public void setMOVEFTAXPRICE(String MOVEFTAXPRICE) {
                this.MOVEFTAXPRICE = MOVEFTAXPRICE;
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

            public String getHPR_GUID_CH() {
                return HPR_GUID_CH;
            }

            public void setHPR_GUID_CH(String HPR_GUID_CH) {
                this.HPR_GUID_CH = HPR_GUID_CH;
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

            public String getOLDCORD() {
                return OLDCORD;
            }

            public void setOLDCORD(String OLDCORD) {
                this.OLDCORD = OLDCORD;
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

            public String getHPSCPID() {
                return HPSCPID;
            }

            public void setHPSCPID(String HPSCPID) {
                this.HPSCPID = HPSCPID;
            }

            public String getCPOSCODES() {
                return CPOSCODES;
            }

            public void setCPOSCODES(String CPOSCODES) {
                this.CPOSCODES = CPOSCODES;
            }

            public String getMOVEFMONDEY() {
                return MOVEFMONDEY;
            }

            public void setMOVEFMONDEY(String MOVEFMONDEY) {
                this.MOVEFMONDEY = MOVEFMONDEY;
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

            public String getMOVEIPERTAXRATE() {
                return MOVEIPERTAXRATE;
            }

            public void setMOVEIPERTAXRATE(String MOVEIPERTAXRATE) {
                this.MOVEIPERTAXRATE = MOVEIPERTAXRATE;
            }

            public String getCWHNAME() {
                return CWHNAME;
            }

            public void setCWHNAME(String CWHNAME) {
                this.CWHNAME = CWHNAME;
            }

            public String getMOVEFTAXRATE() {
                return MOVEFTAXRATE;
            }

            public void setMOVEFTAXRATE(String MOVEFTAXRATE) {
                this.MOVEFTAXRATE = MOVEFTAXRATE;
            }
        }
    }

    public static class TotalInfoBean {
        private String TOTALFQUANTITY;
        private String TOTALIPERTAXRATE;
        private String TOTALFMONDEY;
        private String TOTALTAXAMOUNT;

        @Override
        public String toString() {
            return "TotalInfoBean{" +
                    "TOTALFQUANTITY='" + TOTALFQUANTITY + '\'' +
                    ", TOTALIPERTAXRATE='" + TOTALIPERTAXRATE + '\'' +
                    ", TOTALFMONDEY='" + TOTALFMONDEY + '\'' +
                    ", TOTALTAXAMOUNT='" + TOTALTAXAMOUNT + '\'' +
                    '}';
        }

        public String getTOTALFQUANTITY() {
            return TOTALFQUANTITY;
        }

        public void setTOTALFQUANTITY(String TOTALFQUANTITY) {
            this.TOTALFQUANTITY = TOTALFQUANTITY;
        }

        public String getTOTALIPERTAXRATE() {
            return TOTALIPERTAXRATE;
        }

        public void setTOTALIPERTAXRATE(String TOTALIPERTAXRATE) {
            this.TOTALIPERTAXRATE = TOTALIPERTAXRATE;
        }

        public String getTOTALFMONDEY() {
            return TOTALFMONDEY;
        }

        public void setTOTALFMONDEY(String TOTALFMONDEY) {
            this.TOTALFMONDEY = TOTALFMONDEY;
        }

        public String getTOTALTAXAMOUNT() {
            return TOTALTAXAMOUNT;
        }

        public void setTOTALTAXAMOUNT(String TOTALTAXAMOUNT) {
            this.TOTALTAXAMOUNT = TOTALTAXAMOUNT;
        }
    }
}
