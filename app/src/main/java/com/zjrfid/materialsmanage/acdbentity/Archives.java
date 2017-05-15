package com.zjrfid.materialsmanage.acdbentity;

import java.util.List;

/**
 * Created by Administrator on 2017/3/28 0028.物资档案详细信息接口
 */
public class Archives {
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
            private String CINVCNAME;
            private String RFID;
            private String CSAFESTOCK11;
            private String CGETPRICETYPE;
            private String BUSINFO;
            private String CONVRATE;
            private String HPIGUID;
            private String DEDATE;
            private String CSAFESTOCK33;
            private String IASSET;
            private String FTAXRATE;
            private String GUARANTEEPERIOD;
            private String ISALE;
            private String CSAFESTOCK1;
            private String IBATCH;
            private String CCOMUNITNAME;
            private String ITURNOVER;
            private String CINVCODE;
            private String CSAFESTOCK2;
            private String CUNIT;
            private String CPOSCODE;
            private String OLDCORD;
            private String CSAFESTOCK3;
            private String CSAFESTOCK22;
            private String CPARENTID;
            private String CINVSTD;
            private String CINVNAME;
            private String IMIN;
            private String IMAX;
            private String HPWGUID;
            private String DSDATE;
            private String CWHNAME;
            private String SHORTNAME;
            private String OLDUNITNAME;
            private String CUNITNAME;
            private String CINVADDCODE;
            private String HPICGUID;

            @Override
            public String toString() {
                return "ListBean{" +
                        "CDEMO='" + CDEMO + '\'' +
                        ", CINVCNAME='" + CINVCNAME + '\'' +
                        ", RFID='" + RFID + '\'' +
                        ", CSAFESTOCK11='" + CSAFESTOCK11 + '\'' +
                        ", CGETPRICETYPE='" + CGETPRICETYPE + '\'' +
                        ", BUSINFO='" + BUSINFO + '\'' +
                        ", CONVRATE='" + CONVRATE + '\'' +
                        ", HPIGUID='" + HPIGUID + '\'' +
                        ", DEDATE='" + DEDATE + '\'' +
                        ", CSAFESTOCK33='" + CSAFESTOCK33 + '\'' +
                        ", IASSET='" + IASSET + '\'' +
                        ", FTAXRATE='" + FTAXRATE + '\'' +
                        ", GUARANTEEPERIOD='" + GUARANTEEPERIOD + '\'' +
                        ", ISALE='" + ISALE + '\'' +
                        ", CSAFESTOCK1='" + CSAFESTOCK1 + '\'' +
                        ", IBATCH='" + IBATCH + '\'' +
                        ", CCOMUNITNAME='" + CCOMUNITNAME + '\'' +
                        ", ITURNOVER='" + ITURNOVER + '\'' +
                        ", CINVCODE='" + CINVCODE + '\'' +
                        ", CSAFESTOCK2='" + CSAFESTOCK2 + '\'' +
                        ", CUNIT='" + CUNIT + '\'' +
                        ", CPOSCODE='" + CPOSCODE + '\'' +
                        ", OLDCORD='" + OLDCORD + '\'' +
                        ", CSAFESTOCK3='" + CSAFESTOCK3 + '\'' +
                        ", CSAFESTOCK22='" + CSAFESTOCK22 + '\'' +
                        ", CPARENTID='" + CPARENTID + '\'' +
                        ", CINVSTD='" + CINVSTD + '\'' +
                        ", CINVNAME='" + CINVNAME + '\'' +
                        ", IMIN='" + IMIN + '\'' +
                        ", IMAX='" + IMAX + '\'' +
                        ", HPWGUID='" + HPWGUID + '\'' +
                        ", DSDATE='" + DSDATE + '\'' +
                        ", CWHNAME='" + CWHNAME + '\'' +
                        ", SHORTNAME='" + SHORTNAME + '\'' +
                        ", OLDUNITNAME='" + OLDUNITNAME + '\'' +
                        ", CUNITNAME='" + CUNITNAME + '\'' +
                        ", CINVADDCODE='" + CINVADDCODE + '\'' +
                        ", HPICGUID='" + HPICGUID + '\'' +
                        '}';
            }

            public String getCDEMO() {
                return CDEMO;
            }

            public void setCDEMO(String CDEMO) {
                this.CDEMO = CDEMO;
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

            public String getCSAFESTOCK11() {
                return CSAFESTOCK11;
            }

            public void setCSAFESTOCK11(String CSAFESTOCK11) {
                this.CSAFESTOCK11 = CSAFESTOCK11;
            }

            public String getCGETPRICETYPE() {
                return CGETPRICETYPE;
            }

            public void setCGETPRICETYPE(String CGETPRICETYPE) {
                this.CGETPRICETYPE = CGETPRICETYPE;
            }

            public String getBUSINFO() {
                return BUSINFO;
            }

            public void setBUSINFO(String BUSINFO) {
                this.BUSINFO = BUSINFO;
            }

            public String getCONVRATE() {
                return CONVRATE;
            }

            public void setCONVRATE(String CONVRATE) {
                this.CONVRATE = CONVRATE;
            }

            public String getHPIGUID() {
                return HPIGUID;
            }

            public void setHPIGUID(String HPIGUID) {
                this.HPIGUID = HPIGUID;
            }

            public String getDEDATE() {
                return DEDATE;
            }

            public void setDEDATE(String DEDATE) {
                this.DEDATE = DEDATE;
            }

            public String getCSAFESTOCK33() {
                return CSAFESTOCK33;
            }

            public void setCSAFESTOCK33(String CSAFESTOCK33) {
                this.CSAFESTOCK33 = CSAFESTOCK33;
            }

            public String getIASSET() {
                return IASSET;
            }

            public void setIASSET(String IASSET) {
                this.IASSET = IASSET;
            }

            public String getFTAXRATE() {
                return FTAXRATE;
            }

            public void setFTAXRATE(String FTAXRATE) {
                this.FTAXRATE = FTAXRATE;
            }

            public String getGUARANTEEPERIOD() {
                return GUARANTEEPERIOD;
            }

            public void setGUARANTEEPERIOD(String GUARANTEEPERIOD) {
                this.GUARANTEEPERIOD = GUARANTEEPERIOD;
            }

            public String getISALE() {
                return ISALE;
            }

            public void setISALE(String ISALE) {
                this.ISALE = ISALE;
            }

            public String getCSAFESTOCK1() {
                return CSAFESTOCK1;
            }

            public void setCSAFESTOCK1(String CSAFESTOCK1) {
                this.CSAFESTOCK1 = CSAFESTOCK1;
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

            public String getITURNOVER() {
                return ITURNOVER;
            }

            public void setITURNOVER(String ITURNOVER) {
                this.ITURNOVER = ITURNOVER;
            }

            public String getCINVCODE() {
                return CINVCODE;
            }

            public void setCINVCODE(String CINVCODE) {
                this.CINVCODE = CINVCODE;
            }

            public String getCSAFESTOCK2() {
                return CSAFESTOCK2;
            }

            public void setCSAFESTOCK2(String CSAFESTOCK2) {
                this.CSAFESTOCK2 = CSAFESTOCK2;
            }

            public String getCUNIT() {
                return CUNIT;
            }

            public void setCUNIT(String CUNIT) {
                this.CUNIT = CUNIT;
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

            public String getCSAFESTOCK3() {
                return CSAFESTOCK3;
            }

            public void setCSAFESTOCK3(String CSAFESTOCK3) {
                this.CSAFESTOCK3 = CSAFESTOCK3;
            }

            public String getCSAFESTOCK22() {
                return CSAFESTOCK22;
            }

            public void setCSAFESTOCK22(String CSAFESTOCK22) {
                this.CSAFESTOCK22 = CSAFESTOCK22;
            }

            public String getCPARENTID() {
                return CPARENTID;
            }

            public void setCPARENTID(String CPARENTID) {
                this.CPARENTID = CPARENTID;
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

            public String getIMIN() {
                return IMIN;
            }

            public void setIMIN(String IMIN) {
                this.IMIN = IMIN;
            }

            public String getIMAX() {
                return IMAX;
            }

            public void setIMAX(String IMAX) {
                this.IMAX = IMAX;
            }

            public String getHPWGUID() {
                return HPWGUID;
            }

            public void setHPWGUID(String HPWGUID) {
                this.HPWGUID = HPWGUID;
            }

            public String getDSDATE() {
                return DSDATE;
            }

            public void setDSDATE(String DSDATE) {
                this.DSDATE = DSDATE;
            }

            public String getCWHNAME() {
                return CWHNAME;
            }

            public void setCWHNAME(String CWHNAME) {
                this.CWHNAME = CWHNAME;
            }

            public String getSHORTNAME() {
                return SHORTNAME;
            }

            public void setSHORTNAME(String SHORTNAME) {
                this.SHORTNAME = SHORTNAME;
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

            public String getCINVADDCODE() {
                return CINVADDCODE;
            }

            public void setCINVADDCODE(String CINVADDCODE) {
                this.CINVADDCODE = CINVADDCODE;
            }

            public String getHPICGUID() {
                return HPICGUID;
            }

            public void setHPICGUID(String HPICGUID) {
                this.HPICGUID = HPICGUID;
            }
        }
    }
}
