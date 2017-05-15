package com.zjrfid.materialsmanage.acdbentity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouyu on 2017/4/29.
 */
public class CgoodsAllocation {

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

    @Override
    public String toString() {
        return "CgoodsAllocation{" +
                "statusCode='" + statusCode + '\'' +
                ", message='" + message + '\'' +
                ", navTabId='" + navTabId + '\'' +
                ", forwordUrl='" + forwordUrl + '\'' +
                ", callbackType='" + callbackType + '\'' +
                ", jsonData=" + jsonData +
                '}';
    }

    public static class JsonDataBean {

        private int currentPage;
        private List<Bean> list;
        private int numPerPage;
        private int pageNumShown;
        private int totalCount;


        public List<Bean> getList() {
            return list;
        }

        public void setList(List<Bean> list) {
            this.list = list;
        }

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

        @Override
        public String toString() {
            return "JsonDataBean{" +
                    "currentPage=" + currentPage +
                    ", list=" + list +
                    ", numPerPage=" + numPerPage +
                    ", pageNumShown=" + pageNumShown +
                    ", totalCount=" + totalCount +
                    '}';
        }
    }

    public static class Bean{

        private String alias;
        private String cbarcode;
        private String cdemo;
        private String cparentid;
        private String cposcode;
        private String createBy;
        private String createDt;
        private int delFlag;
        private String hppGuid;
        private String hpwGuid;
        private int imaxcubage;
        private int imaxweight;
        private String parentid;
        private String updateBy;
        private String updateDt;
        private String userDefined1;
        private String userDefined10;
        private String userDefined2;
        private String userDefined3;
        private String userDefined4;
        private String userDefined5;
        private String userDefined6;
        private String userDefined7;
        private String userDefined8;
        private String userDefined9;


        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public String getCparentid() {
            return cparentid;
        }

        public void setCparentid(String cparentid) {
            this.cparentid = cparentid;
        }

        public String getCposcode() {
            return cposcode;
        }

        public void setCposcode(String cposcode) {
            this.cposcode = cposcode;
        }

        public String getHppGuid() {
            return hppGuid;
        }

        public void setHppGuid(String hppGuid) {
            this.hppGuid = hppGuid;
        }

        public String getParentid() {
            return parentid;
        }

        public void setParentid(String parentid) {
            this.parentid = parentid;
        }

        public String getCbarcode() {
            return cbarcode;
        }

        public void setCbarcode(String cbarcode) {
            this.cbarcode = cbarcode;
        }

        public String getCdemo() {
            return cdemo;
        }

        public void setCdemo(String cdemo) {
            this.cdemo = cdemo;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public String getCreateDt() {
            return createDt;
        }

        public void setCreateDt(String createDt) {
            this.createDt = createDt;
        }

        public int getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(int delFlag) {
            this.delFlag = delFlag;
        }

        public String getHpwGuid() {
            return hpwGuid;
        }

        public void setHpwGuid(String hpwGuid) {
            this.hpwGuid = hpwGuid;
        }

        public int getImaxcubage() {
            return imaxcubage;
        }

        public void setImaxcubage(int imaxcubage) {
            this.imaxcubage = imaxcubage;
        }

        public int getImaxweight() {
            return imaxweight;
        }

        public void setImaxweight(int imaxweight) {
            this.imaxweight = imaxweight;
        }

        public String getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(String updateBy) {
            this.updateBy = updateBy;
        }

        public String getUpdateDt() {
            return updateDt;
        }

        public void setUpdateDt(String updateDt) {
            this.updateDt = updateDt;
        }

        public String getUserDefined1() {
            return userDefined1;
        }

        public void setUserDefined1(String userDefined1) {
            this.userDefined1 = userDefined1;
        }

        public String getUserDefined10() {
            return userDefined10;
        }

        public void setUserDefined10(String userDefined10) {
            this.userDefined10 = userDefined10;
        }

        public String getUserDefined2() {
            return userDefined2;
        }

        public void setUserDefined2(String userDefined2) {
            this.userDefined2 = userDefined2;
        }

        public String getUserDefined3() {
            return userDefined3;
        }

        public void setUserDefined3(String userDefined3) {
            this.userDefined3 = userDefined3;
        }

        public String getUserDefined4() {
            return userDefined4;
        }

        public void setUserDefined4(String userDefined4) {
            this.userDefined4 = userDefined4;
        }

        public String getUserDefined5() {
            return userDefined5;
        }

        public void setUserDefined5(String userDefined5) {
            this.userDefined5 = userDefined5;
        }

        public String getUserDefined6() {
            return userDefined6;
        }

        public void setUserDefined6(String userDefined6) {
            this.userDefined6 = userDefined6;
        }

        public String getUserDefined7() {
            return userDefined7;
        }

        public void setUserDefined7(String userDefined7) {
            this.userDefined7 = userDefined7;
        }

        public String getUserDefined8() {
            return userDefined8;
        }

        public void setUserDefined8(String userDefined8) {
            this.userDefined8 = userDefined8;
        }

        public String getUserDefined9() {
            return userDefined9;
        }

        public void setUserDefined9(String userDefined9) {
            this.userDefined9 = userDefined9;
        }

        @Override
        public String toString() {
            return "Bean{" +
                    "alias='" + alias + '\'' +
                    ", cbarcode='" + cbarcode + '\'' +
                    ", cdemo='" + cdemo + '\'' +
                    ", cparentid='" + cparentid + '\'' +
                    ", cposcode='" + cposcode + '\'' +
                    ", createBy='" + createBy + '\'' +
                    ", createDt='" + createDt + '\'' +
                    ", delFlag=" + delFlag +
                    ", hppGuid='" + hppGuid + '\'' +
                    ", hpwGuid='" + hpwGuid + '\'' +
                    ", imaxcubage=" + imaxcubage +
                    ", imaxweight=" + imaxweight +
                    ", parentid='" + parentid + '\'' +
                    ", updateBy='" + updateBy + '\'' +
                    ", updateDt='" + updateDt + '\'' +
                    ", userDefined1='" + userDefined1 + '\'' +
                    ", userDefined10='" + userDefined10 + '\'' +
                    ", userDefined2='" + userDefined2 + '\'' +
                    ", userDefined3='" + userDefined3 + '\'' +
                    ", userDefined4='" + userDefined4 + '\'' +
                    ", userDefined5='" + userDefined5 + '\'' +
                    ", userDefined6='" + userDefined6 + '\'' +
                    ", userDefined7='" + userDefined7 + '\'' +
                    ", userDefined8='" + userDefined8 + '\'' +
                    ", userDefined9='" + userDefined9 + '\'' +
                    '}';
        }
    }

}
