package com.zjrfid.materialsmanage.analyticalData;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/1 0001.
 */
public class AnalyticalData {
    private String json;
    private String statusCode;
    private String message;
    private String navTabId;
    private String forwordUrl;
    private String callbackType;
    private List<JsonData> jsonDatas=new ArrayList<>();
    private List<TotalInfo> totalInfos=new ArrayList<>();

    class JsonData {
        private String currentPage;
        private List<InBoundData> inBoundDatas=new ArrayList<>();

        public class InBoundData {
            private String FMONDEY;
            private String CRDCODE;
            private String CBATCH;
            private String CBUSTYPE;
            private String FUNITPRICE;
            private String FTAXPRICE;
            private String BRDFLAG;
            private String CDEPCODE;
            private String CICODE1;
            private String CICODE2;
            private String CVENCODE;
            private String CCODE;
            private String IBATCH;
            private String CVOUCHTYPE;
            private String CPARENTID;
            private String CVENNAME;
            private String CINVNAME;
            private String ALID;
            private String CDEMO;
            private String CINVCNAME;
            private String IWHPOS;
            private String HPALGUID;
            private String TAXAMOUNT;
            private String HPIGUID;
            private String CMAKER;
            private String DKEEPDATE;
            private String HPRGUID;
            private String CWHCODE;
            private String DVERIDAATE;
            private String FTAXRATE;
            private String CHANDLER;
            private String CPTNAME2;
            private String CCOMUNITNAME;
            private String CRDNAME;
            private String FQUANTITY;
            private String HPRRGUID;
            private String CINVCODE;
            private String OLDCORD;
            private String CPOSCODE;
            private String CPERSONCODE;
            private String IPERTAXRATE;
            private String CPTNAME1;
            private String CSOURCE;
            private String HPRGUIDCH;
            private String CINVSTD;
            private String CPERSONNAME;
            private String CREATEDT;
            private String HPSNGUID;
            private String HPWGUID;
            private String CMEMO;
            private String CWHNAME;
            private String CVOUCHTYPE1;
            private String ORGNAME;
            private String CVOUCHTYPE2;

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

            public String getBRDFLAG() {
                return BRDFLAG;
            }

            public void setBRDFLAG(String BRDFLAG) {
                this.BRDFLAG = BRDFLAG;
            }

            public String getCDEPCODE() {
                return CDEPCODE;
            }

            public void setCDEPCODE(String CDEPCODE) {
                this.CDEPCODE = CDEPCODE;
            }

            public String getCICODE1() {
                return CICODE1;
            }

            public void setCICODE1(String CICODE1) {
                this.CICODE1 = CICODE1;
            }

            public String getCICODE2() {
                return CICODE2;
            }

            public void setCICODE2(String CICODE2) {
                this.CICODE2 = CICODE2;
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

            public String getIBATCH() {
                return IBATCH;
            }

            public void setIBATCH(String IBATCH) {
                this.IBATCH = IBATCH;
            }

            public String getCVOUCHTYPE() {
                return CVOUCHTYPE;
            }

            public void setCVOUCHTYPE(String CVOUCHTYPE) {
                this.CVOUCHTYPE = CVOUCHTYPE;
            }

            public String getCPARENTID() {
                return CPARENTID;
            }

            public void setCPARENTID(String CPARENTID) {
                this.CPARENTID = CPARENTID;
            }

            public String getCVENNAME() {
                return CVENNAME;
            }

            public void setCVENNAME(String CVENNAME) {
                this.CVENNAME = CVENNAME;
            }

            public String getCINVNAME() {
                return CINVNAME;
            }

            public void setCINVNAME(String CINVNAME) {
                this.CINVNAME = CINVNAME;
            }

            public String getALID() {
                return ALID;
            }

            public void setALID(String ALID) {
                this.ALID = ALID;
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

            public String getIWHPOS() {
                return IWHPOS;
            }

            public void setIWHPOS(String IWHPOS) {
                this.IWHPOS = IWHPOS;
            }

            public String getHPALGUID() {
                return HPALGUID;
            }

            public void setHPALGUID(String HPALGUID) {
                this.HPALGUID = HPALGUID;
            }

            public String getTAXAMOUNT() {
                return TAXAMOUNT;
            }

            public void setTAXAMOUNT(String TAXAMOUNT) {
                this.TAXAMOUNT = TAXAMOUNT;
            }

            public String getHPIGUID() {
                return HPIGUID;
            }

            public void setHPIGUID(String HPIGUID) {
                this.HPIGUID = HPIGUID;
            }

            public String getCMAKER() {
                return CMAKER;
            }

            public void setCMAKER(String CMAKER) {
                this.CMAKER = CMAKER;
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

            public String getFTAXRATE() {
                return FTAXRATE;
            }

            public void setFTAXRATE(String FTAXRATE) {
                this.FTAXRATE = FTAXRATE;
            }

            public String getCHANDLER() {
                return CHANDLER;
            }

            public void setCHANDLER(String CHANDLER) {
                this.CHANDLER = CHANDLER;
            }

            public String getCPTNAME2() {
                return CPTNAME2;
            }

            public void setCPTNAME2(String CPTNAME2) {
                this.CPTNAME2 = CPTNAME2;
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

            public String getHPRRGUID() {
                return HPRRGUID;
            }

            public void setHPRRGUID(String HPRRGUID) {
                this.HPRRGUID = HPRRGUID;
            }

            public String getCINVCODE() {
                return CINVCODE;
            }

            public void setCINVCODE(String CINVCODE) {
                this.CINVCODE = CINVCODE;
            }

            public String getOLDCORD() {
                return OLDCORD;
            }

            public void setOLDCORD(String OLDCORD) {
                this.OLDCORD = OLDCORD;
            }

            public String getCPOSCODE() {
                return CPOSCODE;
            }

            public void setCPOSCODE(String CPOSCODE) {
                this.CPOSCODE = CPOSCODE;
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

            public String getCPTNAME1() {
                return CPTNAME1;
            }

            public void setCPTNAME1(String CPTNAME1) {
                this.CPTNAME1 = CPTNAME1;
            }

            public String getCSOURCE() {
                return CSOURCE;
            }

            public void setCSOURCE(String CSOURCE) {
                this.CSOURCE = CSOURCE;
            }

            public String getHPRGUIDCH() {
                return HPRGUIDCH;
            }

            public void setHPRGUIDCH(String HPRGUIDCH) {
                this.HPRGUIDCH = HPRGUIDCH;
            }

            public String getCINVSTD() {
                return CINVSTD;
            }

            public void setCINVSTD(String CINVSTD) {
                this.CINVSTD = CINVSTD;
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

            public String getHPSNGUID() {
                return HPSNGUID;
            }

            public void setHPSNGUID(String HPSNGUID) {
                this.HPSNGUID = HPSNGUID;
            }

            public String getHPWGUID() {
                return HPWGUID;
            }

            public void setHPWGUID(String HPWGUID) {
                this.HPWGUID = HPWGUID;
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

            public String getCVOUCHTYPE1() {
                return CVOUCHTYPE1;
            }

            public void setCVOUCHTYPE1(String CVOUCHTYPE1) {
                this.CVOUCHTYPE1 = CVOUCHTYPE1;
            }

            public String getORGNAME() {
                return ORGNAME;
            }

            public void setORGNAME(String ORGNAME) {
                this.ORGNAME = ORGNAME;
            }

            public String getCVOUCHTYPE2() {
                return CVOUCHTYPE2;
            }

            public void setCVOUCHTYPE2(String CVOUCHTYPE2) {
                this.CVOUCHTYPE2 = CVOUCHTYPE2;
            }
        }

        public String getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(String currentPage) {
            this.currentPage = currentPage;
        }

        public List<InBoundData> getInBoundDatas() {
            return inBoundDatas;
        }

        public void setInBoundDatas(List<InBoundData> inBoundDatas) {
            this.inBoundDatas = inBoundDatas;
        }
    }

    class TotalInfo {
        private String TOTALFQUANTITY;
        private String TOTALIPERTAXRATE;
        private String TOTALFMONDEY;
        private String TOTALTAXAMOUNT;

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

