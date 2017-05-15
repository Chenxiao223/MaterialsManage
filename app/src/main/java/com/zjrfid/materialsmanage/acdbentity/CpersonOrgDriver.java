package com.zjrfid.materialsmanage.acdbentity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouyu on 2017/4/29.
 */
public class CpersonOrgDriver {
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
        private List<Bean> list;

        public String getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(String currentPage) {
            this.currentPage = currentPage;
        }

        public String getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(String totalCount) {
            this.totalCount = totalCount;
        }

        public String getPageNumShown() {
            return pageNumShown;
        }

        public void setPageNumShown(String pageNumShown) {
            this.pageNumShown = pageNumShown;
        }

        public String getNumPerPage() {
            return numPerPage;
        }

        public void setNumPerPage(String numPerPage) {
            this.numPerPage = numPerPage;
        }

        public List<Bean> getList() {
            return list;
        }

        public void setList(List<Bean> list) {
            this.list = list;
        }
        public static class Bean{
            private String cptname;
            private String deptid;
            private String jobnumber;
            private String orgname;
            private String userid;
            private String username;

            @Override
            public String toString() {
                return "Bean{" +
                        "cptname='" + cptname + '\'' +
                        ", deptid='" + deptid + '\'' +
                        ", jobnumber='" + jobnumber + '\'' +
                        ", orgname='" + orgname + '\'' +
                        ", userid='" + userid + '\'' +
                        ", username='" + username + '\'' +
                        '}';
            }

            public String getCptname() {
                return cptname;
            }

            public void setCptname(String cptname) {
                this.cptname = cptname;
            }

            public String getDeptid() {
                return deptid;
            }

            public void setDeptid(String deptid) {
                this.deptid = deptid;
            }

            public String getJobnumber() {
                return jobnumber;
            }

            public void setJobnumber(String jobnumber) {
                this.jobnumber = jobnumber;
            }

            public String getOrgname() {
                return orgname;
            }

            public void setOrgname(String orgname) {
                this.orgname = orgname;
            }

            public String getUserid() {
                return userid;
            }

            public void setUserid(String userid) {
                this.userid = userid;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }
        }
    }
}
