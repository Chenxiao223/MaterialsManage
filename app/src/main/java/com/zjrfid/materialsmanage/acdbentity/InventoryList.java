package com.zjrfid.materialsmanage.acdbentity;

import java.util.List;

/**
 * Created by chenxiao on 2017/4/10.
 * 盘点单查询（列表）实体类
 */
public class InventoryList {
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
            private String CPARENTID;
            private String CCVCODE;
            private String HPCVGUID;
            private String CMAKER;
            private String DCVDATE;
            private String CPERSONNAME;
            private String CREATEDT;
            private String PQUANTITY;
            private String CWHNAME;
            private String HPWGUID;
            private String CHANDLER;
            private String FQUANTITY;
            private String ORGNAME;
            private String CORDCODE;
            private String CIRDCODE;

            @Override
            public String toString() {
                return "ListBean{" +
                        "CDEMO='" + CDEMO + '\'' +
                        ", CPARENTID='" + CPARENTID + '\'' +
                        ", CCVCODE='" + CCVCODE + '\'' +
                        ", HPCVGUID='" + HPCVGUID + '\'' +
                        ", CMAKER='" + CMAKER + '\'' +
                        ", DCVDATE='" + DCVDATE + '\'' +
                        ", CPERSONNAME='" + CPERSONNAME + '\'' +
                        ", CREATEDT='" + CREATEDT + '\'' +
                        ", PQUANTITY='" + PQUANTITY + '\'' +
                        ", CWHNAME='" + CWHNAME + '\'' +
                        ", HPWGUID='" + HPWGUID + '\'' +
                        ", CHANDLER='" + CHANDLER + '\'' +
                        ", FQUANTITY='" + FQUANTITY + '\'' +
                        ", ORGNAME='" + ORGNAME + '\'' +
                        ", CORDCODE='" + CORDCODE + '\'' +
                        ", CIRDCODE='" + CIRDCODE + '\'' +
                        '}';
            }

            public String getCORDCODE() {
                return CORDCODE;
            }

            public void setCORDCODE(String CORDCODE) {
                this.CORDCODE = CORDCODE;
            }

            public String getCIRDCODE() {
                return CIRDCODE;
            }

            public void setCIRDCODE(String CIRDCODE) {
                this.CIRDCODE = CIRDCODE;
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

            public String getCCVCODE() {
                return CCVCODE;
            }

            public void setCCVCODE(String CCVCODE) {
                this.CCVCODE = CCVCODE;
            }

            public String getHPCVGUID() {
                return HPCVGUID;
            }

            public void setHPCVGUID(String HPCVGUID) {
                this.HPCVGUID = HPCVGUID;
            }

            public String getCMAKER() {
                return CMAKER;
            }

            public void setCMAKER(String CMAKER) {
                this.CMAKER = CMAKER;
            }

            public String getDCVDATE() {
                return DCVDATE;
            }

            public void setDCVDATE(String DCVDATE) {
                this.DCVDATE = DCVDATE;
            }

            public String getCPERSONNAME() {
                return CPERSONNAME;
            }

            public void setCPERSONNAME(String CPERSONNAME) {
                this.CPERSONNAME = CPERSONNAME;
            }

            public String getCREATEDT() {
                return CREATEDT;
            }

            public void setCREATEDT(String CREATEDT) {
                this.CREATEDT = CREATEDT;
            }

            public String getPQUANTITY() {
                return PQUANTITY;
            }

            public void setPQUANTITY(String PQUANTITY) {
                this.PQUANTITY = PQUANTITY;
            }

            public String getCWHNAME() {
                return CWHNAME;
            }

            public void setCWHNAME(String CWHNAME) {
                this.CWHNAME = CWHNAME;
            }

            public String getHPWGUID() {
                return HPWGUID;
            }

            public void setHPWGUID(String HPWGUID) {
                this.HPWGUID = HPWGUID;
            }

            public String getCHANDLER() {
                return CHANDLER;
            }

            public void setCHANDLER(String CHANDLER) {
                this.CHANDLER = CHANDLER;
            }

            public String getFQUANTITY() {
                return FQUANTITY;
            }

            public void setFQUANTITY(String FQUANTITY) {
                this.FQUANTITY = FQUANTITY;
            }

            public String getORGNAME() {
                return ORGNAME;
            }

            public void setORGNAME(String ORGNAME) {
                this.ORGNAME = ORGNAME;
            }
        }
    }

    public static class TotalInfoBean{
        private String TOTALFQUANTITY;
        private String TOTALFMONDEY;
        private String TOTALTAXAMOUNT;
        private String TOTALPQUANTITY;

        @Override
        public String toString() {
            return "TotalInfoBean{" +
                    "TOTALFQUANTITY='" + TOTALFQUANTITY + '\'' +
                    ", TOTALFMONDEY='" + TOTALFMONDEY + '\'' +
                    ", TOTALTAXAMOUNT='" + TOTALTAXAMOUNT + '\'' +
                    ", TOTALPQUANTITY='" + TOTALPQUANTITY + '\'' +
                    '}';
        }

        public String getTOTALFQUANTITY() {
            return TOTALFQUANTITY;
        }

        public void setTOTALFQUANTITY(String TOTALFQUANTITY) {
            this.TOTALFQUANTITY = TOTALFQUANTITY;
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

        public String getTOTALPQUANTITY() {
            return TOTALPQUANTITY;
        }

        public void setTOTALPQUANTITY(String TOTALPQUANTITY) {
            this.TOTALPQUANTITY = TOTALPQUANTITY;
        }
    }
}
