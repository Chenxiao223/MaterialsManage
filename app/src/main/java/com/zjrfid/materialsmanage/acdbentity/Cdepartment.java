package com.zjrfid.materialsmanage.acdbentity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouyu on 2017/4/29.
 */
public class Cdepartment {

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
        return "Cdepartment{" +
                "statusCode='" + statusCode + '\'' +
                ", message='" + message + '\'' +
                ", navTabId='" + navTabId + '\'' +
                ", forwordUrl='" + forwordUrl + '\'' +
                ", callbackType='" + callbackType + '\'' +
                ", jsonData=" + jsonData +
                '}';
    }

    public static class JsonDataBean {
        private String currentPage;
        private String numPerPage;
        private String pageNumShown;
        private String totalCount;
        private ArrayList<Bean> list;

        public String getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(String currentPage) {
            this.currentPage = currentPage;
        }

        @Override
        public String toString() {
            return "JsonDataBean{" +
                    "currentPage='" + currentPage + '\'' +
                    ", numPerPage='" + numPerPage + '\'' +
                    ", pageNumShown='" + pageNumShown + '\'' +
                    ", totalCount='" + totalCount + '\'' +
                    ", list=" + list +
                    '}';
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

        public ArrayList<Bean> getList() {
            return list;
        }

        public void setList(ArrayList<Bean> list) {
            this.list = list;
        }
    }

    public static class Bean{
        private String deptid;
        private String orgname;

        public Bean(String deptid, String orgname) {
            this.deptid = deptid;
            this.orgname = orgname;
        }


        @Override
        public String toString() {
            return "Bean{" +
                    "deptid='" + deptid + '\'' +
                    ", orgname='" + orgname + '\'' +
                    '}';
        }

        public String getDeptid() {
            return deptid;
        }

        public void setDeptid(String deptid) {
            this.deptid = deptid;
        }

        public String getOrgname() {
            return orgname;
        }

        public void setOrgname(String orgname) {
            this.orgname = orgname;
        }
    }

}
