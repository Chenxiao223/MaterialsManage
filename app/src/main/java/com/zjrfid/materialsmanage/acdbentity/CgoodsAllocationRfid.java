package com.zjrfid.materialsmanage.acdbentity;

import java.util.List;

/**
 * Created by zhouyu on 2017/4/29.
 */
public class CgoodsAllocationRfid {

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

    public static class JsonDataBean {

        private String RFID;
        private String CPARENTID;
        private String IPERTAXRATE;
        private String TAXAMOUNT;
        private String FMONDEY;
        private String HPIGUID;
        private String CBATCH;
        private String CINVNAME;
        private String CINVSTD;
        private String FUNITPRICE;
        private String FTAXPRICE;
        private String CWHCODE;
        private String FTAXRATE;
        private String CWHNAME;
        private String CCOMUNITNAME;
        private String FQUANTITY;
        private String CINVCODE;
        private String CPOSCODE;
        private String OLDCORD;
        private String HPICGUID;
        private String OLDUNITNAME;
        private String CUNITNAME;
        private String CINVCNAME;

        @Override
        public String toString() {
            return "JsonDataBean{" +
                    "RFID='" + RFID + '\'' +
                    ", CPARENTID='" + CPARENTID + '\'' +
                    ", IPERTAXRATE='" + IPERTAXRATE + '\'' +
                    ", TAXAMOUNT='" + TAXAMOUNT + '\'' +
                    ", FMONDEY='" + FMONDEY + '\'' +
                    ", HPIGUID='" + HPIGUID + '\'' +
                    ", CBATCH='" + CBATCH + '\'' +
                    ", CINVNAME='" + CINVNAME + '\'' +
                    ", CINVSTD='" + CINVSTD + '\'' +
                    ", FUNITPRICE='" + FUNITPRICE + '\'' +
                    ", FTAXPRICE='" + FTAXPRICE + '\'' +
                    ", CWHCODE='" + CWHCODE + '\'' +
                    ", FTAXRATE='" + FTAXRATE + '\'' +
                    ", CWHNAME='" + CWHNAME + '\'' +
                    ", CCOMUNITNAME='" + CCOMUNITNAME + '\'' +
                    ", FQUANTITY='" + FQUANTITY + '\'' +
                    ", CINVCODE='" + CINVCODE + '\'' +
                    ", CPOSCODE='" + CPOSCODE + '\'' +
                    ", OLDCORD='" + OLDCORD + '\'' +
                    ", HPICGUID='" + HPICGUID + '\'' +
                    ", OLDUNITNAME='" + OLDUNITNAME + '\'' +
                    ", CUNITNAME='" + CUNITNAME + '\'' +
                    ", CINVCNAME='" + CINVCNAME + '\'' +
                    '}';
        }

        public String getCINVCNAME() {
            return CINVCNAME;
        }

        public void setCINVCNAME(String CINVCNAME) {
            this.CINVCNAME = CINVCNAME;
        }

        public String getRFID() {
            return RFID;
        }

        public void setRFID(String RFID) {
            this.RFID = RFID;
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

        public String getCWHNAME() {
            return CWHNAME;
        }

        public void setCWHNAME(String CWHNAME) {
            this.CWHNAME = CWHNAME;
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

        public String getHPICGUID() {
            return HPICGUID;
        }

        public void setHPICGUID(String HPICGUID) {
            this.HPICGUID = HPICGUID;
        }

        public String getOLDUNITNAME() {
            return OLDUNITNAME;
        }

        public void setOLDUNITNAME(String OLDUNITNAME) {
            this.OLDUNITNAME = OLDUNITNAME;
        }

        public String getCUNITNAME() {
            return CUNITNAME;
        }

        public void setCUNITNAME(String CUNITNAME) {
            this.CUNITNAME = CUNITNAME;
        }
    }

}
