package com.zjrfid.materialsmanage.acdbentity;

import java.util.List;

/**
 * Created by chenxiao on 2017/5/23.
 * 生单（头）
 */
public class GenerateListH {
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
            private String CVERIFIER;
            private String CVENNAME;
            private String SCURRENC;
            private String EXCHANGERATE;
            private String HPALPID;
            private String CMAKER;
            private String HPPPID;
            private String HPSNGUID;
            private String SDEPMENT;
            private String CCONTRACTTYPE;
            private String SSALESMAN;
            private String ALID;
            private String NUM;
            private String TRANSPORTATION;
            private String CPTNAME;
            private String DREQUIRDATE;
            private String CCLOSER;
            private String CVENCODE;

            @Override
            public String toString() {
                return "ListBean{" +
                        "CDEMO='" + CDEMO + '\'' +
                        ", CVERIFIER='" + CVERIFIER + '\'' +
                        ", CVENNAME='" + CVENNAME + '\'' +
                        ", SCURRENC='" + SCURRENC + '\'' +
                        ", EXCHANGERATE='" + EXCHANGERATE + '\'' +
                        ", HPALPID='" + HPALPID + '\'' +
                        ", CMAKER='" + CMAKER + '\'' +
                        ", HPPPID='" + HPPPID + '\'' +
                        ", HPSNGUID='" + HPSNGUID + '\'' +
                        ", SDEPMENT='" + SDEPMENT + '\'' +
                        ", CCONTRACTTYPE='" + CCONTRACTTYPE + '\'' +
                        ", SSALESMAN='" + SSALESMAN + '\'' +
                        ", ALID='" + ALID + '\'' +
                        ", NUM='" + NUM + '\'' +
                        ", TRANSPORTATION='" + TRANSPORTATION + '\'' +
                        ", CPTNAME='" + CPTNAME + '\'' +
                        ", DREQUIRDATE='" + DREQUIRDATE + '\'' +
                        ", CCLOSER='" + CCLOSER + '\'' +
                        ", CVENCODE='" + CVENCODE + '\'' +
                        '}';
            }

            public String getCVENCODE() {
                return CVENCODE;
            }

            public void setCVENCODE(String CVENCODE) {
                this.CVENCODE = CVENCODE;
            }

            public String getCDEMO() {
                return CDEMO;
            }

            public void setCDEMO(String CDEMO) {
                this.CDEMO = CDEMO;
            }

            public String getCVERIFIER() {
                return CVERIFIER;
            }

            public void setCVERIFIER(String CVERIFIER) {
                this.CVERIFIER = CVERIFIER;
            }

            public String getCVENNAME() {
                return CVENNAME;
            }

            public void setCVENNAME(String CVENNAME) {
                this.CVENNAME = CVENNAME;
            }

            public String getSCURRENC() {
                return SCURRENC;
            }

            public void setSCURRENC(String SCURRENC) {
                this.SCURRENC = SCURRENC;
            }

            public String getEXCHANGERATE() {
                return EXCHANGERATE;
            }

            public void setEXCHANGERATE(String EXCHANGERATE) {
                this.EXCHANGERATE = EXCHANGERATE;
            }

            public String getHPALPID() {
                return HPALPID;
            }

            public void setHPALPID(String HPALPID) {
                this.HPALPID = HPALPID;
            }

            public String getCMAKER() {
                return CMAKER;
            }

            public void setCMAKER(String CMAKER) {
                this.CMAKER = CMAKER;
            }

            public String getHPPPID() {
                return HPPPID;
            }

            public void setHPPPID(String HPPPID) {
                this.HPPPID = HPPPID;
            }

            public String getHPSNGUID() {
                return HPSNGUID;
            }

            public void setHPSNGUID(String HPSNGUID) {
                this.HPSNGUID = HPSNGUID;
            }

            public String getSDEPMENT() {
                return SDEPMENT;
            }

            public void setSDEPMENT(String SDEPMENT) {
                this.SDEPMENT = SDEPMENT;
            }

            public String getCCONTRACTTYPE() {
                return CCONTRACTTYPE;
            }

            public void setCCONTRACTTYPE(String CCONTRACTTYPE) {
                this.CCONTRACTTYPE = CCONTRACTTYPE;
            }

            public String getSSALESMAN() {
                return SSALESMAN;
            }

            public void setSSALESMAN(String SSALESMAN) {
                this.SSALESMAN = SSALESMAN;
            }

            public String getALID() {
                return ALID;
            }

            public void setALID(String ALID) {
                this.ALID = ALID;
            }

            public String getNUM() {
                return NUM;
            }

            public void setNUM(String NUM) {
                this.NUM = NUM;
            }

            public String getTRANSPORTATION() {
                return TRANSPORTATION;
            }

            public void setTRANSPORTATION(String TRANSPORTATION) {
                this.TRANSPORTATION = TRANSPORTATION;
            }

            public String getCPTNAME() {
                return CPTNAME;
            }

            public void setCPTNAME(String CPTNAME) {
                this.CPTNAME = CPTNAME;
            }

            public String getDREQUIRDATE() {
                return DREQUIRDATE;
            }

            public void setDREQUIRDATE(String DREQUIRDATE) {
                this.DREQUIRDATE = DREQUIRDATE;
            }

            public String getCCLOSER() {
                return CCLOSER;
            }

            public void setCCLOSER(String CCLOSER) {
                this.CCLOSER = CCLOSER;
            }
        }
    }
}
