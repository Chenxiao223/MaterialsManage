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
            private String CPERSONCODE;
            private String CCVCODE;
            private String HPCVGUID;
            private String CMAKER;
            private String DCVDATE;
            private String CPERSONNAME;
            private String CWHCODE;
            private String DVERIDAATE;
            private String CDEPCODE;
            private String CREATEDT;
            private String CWHNAME;
            private String HPWGUID;
            private String CHANDLER;
            private String CORDCODE;
            private String CIRDCODE;
            private String ORGNAME;

            @Override
            public String toString() {
                return "ListBean{" +
                        "CDEMO='" + CDEMO + '\'' +
                        ", CPERSONCODE='" + CPERSONCODE + '\'' +
                        ", CCVCODE='" + CCVCODE + '\'' +
                        ", HPCVGUID='" + HPCVGUID + '\'' +
                        ", CMAKER='" + CMAKER + '\'' +
                        ", DCVDATE='" + DCVDATE + '\'' +
                        ", CPERSONNAME='" + CPERSONNAME + '\'' +
                        ", CWHCODE='" + CWHCODE + '\'' +
                        ", DVERIDAATE='" + DVERIDAATE + '\'' +
                        ", CDEPCODE='" + CDEPCODE + '\'' +
                        ", CREATEDT='" + CREATEDT + '\'' +
                        ", CWHNAME='" + CWHNAME + '\'' +
                        ", HPWGUID='" + HPWGUID + '\'' +
                        ", CHANDLER='" + CHANDLER + '\'' +
                        ", CORDCODE='" + CORDCODE + '\'' +
                        ", CIRDCODE='" + CIRDCODE + '\'' +
                        ", ORGNAME='" + ORGNAME + '\'' +
                        '}';
            }

            public String getCDEMO() {
                return CDEMO;
            }

            public void setCDEMO(String CDEMO) {
                this.CDEMO = CDEMO;
            }

            public String getCPERSONCODE() {
                return CPERSONCODE;
            }

            public void setCPERSONCODE(String CPERSONCODE) {
                this.CPERSONCODE = CPERSONCODE;
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

            public String getCWHCODE() {
                return CWHCODE;
            }

            public void setCWHCODE(String CWHCODE) {
                this.CWHCODE = CWHCODE;
            }

            public String getDVERIDAATE() {
                return DVERIDAATE;
            }

            public void setDVERIDAATE(String DVERIDAATE) {
                this.DVERIDAATE = DVERIDAATE;
            }

            public String getCDEPCODE() {
                return CDEPCODE;
            }

            public void setCDEPCODE(String CDEPCODE) {
                this.CDEPCODE = CDEPCODE;
            }

            public String getCREATEDT() {
                return CREATEDT;
            }

            public void setCREATEDT(String CREATEDT) {
                this.CREATEDT = CREATEDT;
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

            public String getORGNAME() {
                return ORGNAME;
            }

            public void setORGNAME(String ORGNAME) {
                this.ORGNAME = ORGNAME;
            }
        }
    }
}
