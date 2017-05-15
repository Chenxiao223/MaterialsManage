package com.zjrfid.materialsmanage.acdbentity;

import java.util.List;

/**
 * Created by Administrator on 2017/3/10 0010.出库单列表
 */
public class StockRemovalList {
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
            private String TAXAMOUNT;
            private String FMONDEY;
            private String CRDCODE;
            private String CBATCH;
            private String CBUSTYPE;
            private String CMAKER;
            private String FUNITPRICE;
            private String FTAXPRICE;
            private String DKEEPDATE;
            private String HPRGUID;
            private String BRDFLAG;
            private String CWHCODE;
            private String CICODE1;
            private String FTAXRATE;
            private String CDEPCODE;
            private String DVERIDAATE;
            private String CVENCODE;
            private String CCODE;
            private String CHANDLER;
            private String CCOMUNITNAME;
            private String CRDNAME;
            private String FQUANTITY;
            private String CINVCODE;
            private String LINE;
            private String BUSNO;
            private String CVOUCHTYPE;
            private String CPOSCODE;
            private String DDATE;
            private String OLDCORD;
            private String CPARENTID;
            private String CPERSONCODE;
            private String IPERTAXRATE;
            private String CVENNAME;
            private String CSOURCE;
            private String CINVSTD;
            private String CINVNAME;
            private String CPERSONNAME;
            private String CMEMO;
            private String CWHNAME;
            private String DEL_FLAG;
            private String HPIA_GUID;
            private String ALID;
            private String ORGNAME;
            private String HPI_GUID;

            @Override
            public String toString() {
                return "ListBean{" +
                        "CDEMO='" + CDEMO + '\'' +
                        ", CINVCNAME='" + CINVCNAME + '\'' +
                        ", TAXAMOUNT='" + TAXAMOUNT + '\'' +
                        ", FMONDEY='" + FMONDEY + '\'' +
                        ", CRDCODE='" + CRDCODE + '\'' +
                        ", CBATCH='" + CBATCH + '\'' +
                        ", CBUSTYPE='" + CBUSTYPE + '\'' +
                        ", CMAKER='" + CMAKER + '\'' +
                        ", FUNITPRICE='" + FUNITPRICE + '\'' +
                        ", FTAXPRICE='" + FTAXPRICE + '\'' +
                        ", DKEEPDATE='" + DKEEPDATE + '\'' +
                        ", HPRGUID='" + HPRGUID + '\'' +
                        ", BRDFLAG='" + BRDFLAG + '\'' +
                        ", CWHCODE='" + CWHCODE + '\'' +
                        ", CICODE1='" + CICODE1 + '\'' +
                        ", FTAXRATE='" + FTAXRATE + '\'' +
                        ", CDEPCODE='" + CDEPCODE + '\'' +
                        ", DVERIDAATE='" + DVERIDAATE + '\'' +
                        ", CVENCODE='" + CVENCODE + '\'' +
                        ", CCODE='" + CCODE + '\'' +
                        ", CHANDLER='" + CHANDLER + '\'' +
                        ", CCOMUNITNAME='" + CCOMUNITNAME + '\'' +
                        ", CRDNAME='" + CRDNAME + '\'' +
                        ", FQUANTITY='" + FQUANTITY + '\'' +
                        ", CINVCODE='" + CINVCODE + '\'' +
                        ", LINE='" + LINE + '\'' +
                        ", BUSNO='" + BUSNO + '\'' +
                        ", CVOUCHTYPE='" + CVOUCHTYPE + '\'' +
                        ", CPOSCODE='" + CPOSCODE + '\'' +
                        ", DDATE='" + DDATE + '\'' +
                        ", OLDCORD='" + OLDCORD + '\'' +
                        ", CPARENTID='" + CPARENTID + '\'' +
                        ", CPERSONCODE='" + CPERSONCODE + '\'' +
                        ", IPERTAXRATE='" + IPERTAXRATE + '\'' +
                        ", CVENNAME='" + CVENNAME + '\'' +
                        ", CSOURCE='" + CSOURCE + '\'' +
                        ", CINVSTD='" + CINVSTD + '\'' +
                        ", CINVNAME='" + CINVNAME + '\'' +
                        ", CPERSONNAME='" + CPERSONNAME + '\'' +
                        ", CMEMO='" + CMEMO + '\'' +
                        ", CWHNAME='" + CWHNAME + '\'' +
                        ", DEL_FLAG='" + DEL_FLAG + '\'' +
                        ", HPIA_GUID='" + HPIA_GUID + '\'' +
                        ", ALID='" + ALID + '\'' +
                        ", ORGNAME='" + ORGNAME + '\'' +
                        ", HPI_GUID='" + HPI_GUID + '\'' +
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

            public String getCRDCODE() {
                return CRDCODE;
            }

            public void setCRDCODE(String CRDCODE) {
                this.CRDCODE = CRDCODE;
            }

            public String getCBATCH() {
                return CBATCH;
            }

            public void setCBATCH(String CBATCH) {
                this.CBATCH = CBATCH;
            }

            public String getCBUSTYPE() {
                return CBUSTYPE;
            }

            public void setCBUSTYPE(String CBUSTYPE) {
                this.CBUSTYPE = CBUSTYPE;
            }

            public String getCMAKER() {
                return CMAKER;
            }

            public void setCMAKER(String CMAKER) {
                this.CMAKER = CMAKER;
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

            public String getDKEEPDATE() {
                return DKEEPDATE;
            }

            public void setDKEEPDATE(String DKEEPDATE) {
                this.DKEEPDATE = DKEEPDATE;
            }

            public String getHPRGUID() {
                return HPRGUID;
            }

            public void setHPRGUID(String HPRGUID) {
                this.HPRGUID = HPRGUID;
            }

            public String getBRDFLAG() {
                return BRDFLAG;
            }

            public void setBRDFLAG(String BRDFLAG) {
                this.BRDFLAG = BRDFLAG;
            }

            public String getCWHCODE() {
                return CWHCODE;
            }

            public void setCWHCODE(String CWHCODE) {
                this.CWHCODE = CWHCODE;
            }

            public String getCICODE1() {
                return CICODE1;
            }

            public void setCICODE1(String CICODE1) {
                this.CICODE1 = CICODE1;
            }

            public String getFTAXRATE() {
                return FTAXRATE;
            }

            public void setFTAXRATE(String FTAXRATE) {
                this.FTAXRATE = FTAXRATE;
            }

            public String getCDEPCODE() {
                return CDEPCODE;
            }

            public void setCDEPCODE(String CDEPCODE) {
                this.CDEPCODE = CDEPCODE;
            }

            public String getDVERIDAATE() {
                return DVERIDAATE;
            }

            public void setDVERIDAATE(String DVERIDAATE) {
                this.DVERIDAATE = DVERIDAATE;
            }

            public String getCVENCODE() {
                return CVENCODE;
            }

            public void setCVENCODE(String CVENCODE) {
                this.CVENCODE = CVENCODE;
            }

            public String getCCODE() {
                return CCODE;
            }

            public void setCCODE(String CCODE) {
                this.CCODE = CCODE;
            }

            public String getCHANDLER() {
                return CHANDLER;
            }

            public void setCHANDLER(String CHANDLER) {
                this.CHANDLER = CHANDLER;
            }

            public String getCCOMUNITNAME() {
                return CCOMUNITNAME;
            }

            public void setCCOMUNITNAME(String CCOMUNITNAME) {
                this.CCOMUNITNAME = CCOMUNITNAME;
            }

            public String getCRDNAME() {
                return CRDNAME;
            }

            public void setCRDNAME(String CRDNAME) {
                this.CRDNAME = CRDNAME;
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

            public String getLINE() {
                return LINE;
            }

            public void setLINE(String LINE) {
                this.LINE = LINE;
            }

            public String getBUSNO() {
                return BUSNO;
            }

            public void setBUSNO(String BUSNO) {
                this.BUSNO = BUSNO;
            }

            public String getCVOUCHTYPE() {
                return CVOUCHTYPE;
            }

            public void setCVOUCHTYPE(String CVOUCHTYPE) {
                this.CVOUCHTYPE = CVOUCHTYPE;
            }

            public String getCPOSCODE() {
                return CPOSCODE;
            }

            public void setCPOSCODE(String CPOSCODE) {
                this.CPOSCODE = CPOSCODE;
            }

            public String getDDATE() {
                return DDATE;
            }

            public void setDDATE(String DDATE) {
                this.DDATE = DDATE;
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

            public String getCPERSONCODE() {
                return CPERSONCODE;
            }

            public void setCPERSONCODE(String CPERSONCODE) {
                this.CPERSONCODE = CPERSONCODE;
            }

            public String getIPERTAXRATE() {
                return IPERTAXRATE;
            }

            public void setIPERTAXRATE(String IPERTAXRATE) {
                this.IPERTAXRATE = IPERTAXRATE;
            }

            public String getCVENNAME() {
                return CVENNAME;
            }

            public void setCVENNAME(String CVENNAME) {
                this.CVENNAME = CVENNAME;
            }

            public String getCSOURCE() {
                return CSOURCE;
            }

            public void setCSOURCE(String CSOURCE) {
                this.CSOURCE = CSOURCE;
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

            public String getCPERSONNAME() {
                return CPERSONNAME;
            }

            public void setCPERSONNAME(String CPERSONNAME) {
                this.CPERSONNAME = CPERSONNAME;
            }

            public String getCMEMO() {
                return CMEMO;
            }

            public void setCMEMO(String CMEMO) {
                this.CMEMO = CMEMO;
            }

            public String getCWHNAME() {
                return CWHNAME;
            }

            public void setCWHNAME(String CWHNAME) {
                this.CWHNAME = CWHNAME;
            }

            public String getDEL_FLAG() {
                return DEL_FLAG;
            }

            public void setDEL_FLAG(String DEL_FLAG) {
                this.DEL_FLAG = DEL_FLAG;
            }

            public String getHPIA_GUID() {
                return HPIA_GUID;
            }

            public void setHPIA_GUID(String HPIA_GUID) {
                this.HPIA_GUID = HPIA_GUID;
            }

            public String getALID() {
                return ALID;
            }

            public void setALID(String ALID) {
                this.ALID = ALID;
            }

            public String getORGNAME() {
                return ORGNAME;
            }

            public void setORGNAME(String ORGNAME) {
                this.ORGNAME = ORGNAME;
            }

            public String getHPI_GUID() {
                return HPI_GUID;
            }

            public void setHPI_GUID(String HPI_GUID) {
                this.HPI_GUID = HPI_GUID;
            }
        }
    }

    public static class TotalInfoBean{
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
