package com.zjrfid.materialsmanage.acdbentity;

import java.util.List;

/**
 * Created by chenxiao on 2017/3/30.
 * 出库详细信息（表体）实体类
 */
public class OutboundInfoTB {
    private String statusCode;
    private String message;
    private String navTabId;
    private String forwordUrl;
    private String callbackType;
    private List<JsonDataBean> jsonData;

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

    public List<JsonDataBean> getJsonData() {
        return jsonData;
    }

    public void setJsonData(List<JsonDataBean> jsonData) {
        this.jsonData = jsonData;
    }
    public static class JsonDataBean{
        private String CDEMO;
        private String CPARENTID;
        private String HPROGUID;
        private String IPERTAXRATE;
        private String TAXAMOUNT;
        private String FMONDEY;
        private String HPIAGUID;
        private String HPRGUIDCH;
        private String HPIGUID;
        private String CBATCH;
        private String CINVSTD;
        private String CINVNAME;
        private String FUNITPRICE;
        private String FTAXPRICE;
        private String FTAXRATE;
        private String CCOMUNITNAME;
        private String IBATCH;
        private String DEL_FLAG;
        private String FQUANTITY;
        private String HPWDGUID;
        private String CINVCODE;
        private String CPOSCODE;

        @Override
        public String toString() {
            return "JsonDataBean{" +
                    "CDEMO='" + CDEMO + '\'' +
                    ", CPARENTID='" + CPARENTID + '\'' +
                    ", HPROGUID='" + HPROGUID + '\'' +
                    ", IPERTAXRATE='" + IPERTAXRATE + '\'' +
                    ", TAXAMOUNT='" + TAXAMOUNT + '\'' +
                    ", FMONDEY='" + FMONDEY + '\'' +
                    ", HPIAGUID='" + HPIAGUID + '\'' +
                    ", HPRGUIDCH='" + HPRGUIDCH + '\'' +
                    ", HPIGUID='" + HPIGUID + '\'' +
                    ", CBATCH='" + CBATCH + '\'' +
                    ", CINVSTD='" + CINVSTD + '\'' +
                    ", CINVNAME='" + CINVNAME + '\'' +
                    ", FUNITPRICE='" + FUNITPRICE + '\'' +
                    ", FTAXPRICE='" + FTAXPRICE + '\'' +
                    ", FTAXRATE='" + FTAXRATE + '\'' +
                    ", CCOMUNITNAME='" + CCOMUNITNAME + '\'' +
                    ", IBATCH='" + IBATCH + '\'' +
                    ", DEL_FLAG='" + DEL_FLAG + '\'' +
                    ", FQUANTITY='" + FQUANTITY + '\'' +
                    ", HPWDGUID='" + HPWDGUID + '\'' +
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

        public String getCPARENTID() {
            return CPARENTID;
        }

        public void setCPARENTID(String CPARENTID) {
            this.CPARENTID = CPARENTID;
        }

        public String getHPROGUID() {
            return HPROGUID;
        }

        public void setHPROGUID(String HPROGUID) {
            this.HPROGUID = HPROGUID;
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

        public String getHPIAGUID() {
            return HPIAGUID;
        }

        public void setHPIAGUID(String HPIAGUID) {
            this.HPIAGUID = HPIAGUID;
        }

        public String getHPRGUIDCH() {
            return HPRGUIDCH;
        }

        public void setHPRGUIDCH(String HPRGUIDCH) {
            this.HPRGUIDCH = HPRGUIDCH;
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

        public String getDEL_FLAG() {
            return DEL_FLAG;
        }

        public void setDEL_FLAG(String DEL_FLAG) {
            this.DEL_FLAG = DEL_FLAG;
        }

        public String getFQUANTITY() {
            return FQUANTITY;
        }

        public void setFQUANTITY(String FQUANTITY) {
            this.FQUANTITY = FQUANTITY;
        }

        public String getHPWDGUID() {
            return HPWDGUID;
        }

        public void setHPWDGUID(String HPWDGUID) {
            this.HPWDGUID = HPWDGUID;
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
