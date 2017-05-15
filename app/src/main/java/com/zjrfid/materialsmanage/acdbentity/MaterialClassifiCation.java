package com.zjrfid.materialsmanage.acdbentity;

import java.util.List;

/**
 * Created by Administrator on 2017/3/27 0027.物资分类实体类
 */
public class MaterialClassifiCation {
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
            private String alias;
            private String bdeprtype;
            private String ccomunitname;
            private String cdemo;
            private String cinvccode;
            private String cinvcname;
            private String createBy;
            private String createDt;
            private String delFlag;
            private String delhp;
            private String deptid;
            private String fbvrate;
            private String hpdGuid;
            private String hpiaGuid;
            private String hprGuid;
            private String ilife;
            private String isassets;
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

            @Override
            public String toString() {
                return "ListBean{" +
                        "alias='" + alias + '\'' +
                        ", bdeprtype='" + bdeprtype + '\'' +
                        ", ccomunitname='" + ccomunitname + '\'' +
                        ", cdemo='" + cdemo + '\'' +
                        ", cinvccode='" + cinvccode + '\'' +
                        ", cinvcname='" + cinvcname + '\'' +
                        ", createBy='" + createBy + '\'' +
                        ", createDt='" + createDt + '\'' +
                        ", delFlag='" + delFlag + '\'' +
                        ", delhp='" + delhp + '\'' +
                        ", deptid='" + deptid + '\'' +
                        ", fbvrate='" + fbvrate + '\'' +
                        ", hpdGuid='" + hpdGuid + '\'' +
                        ", hpiaGuid='" + hpiaGuid + '\'' +
                        ", hprGuid='" + hprGuid + '\'' +
                        ", ilife='" + ilife + '\'' +
                        ", isassets='" + isassets + '\'' +
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

            public String getAlias() {
                return alias;
            }

            public void setAlias(String alias) {
                this.alias = alias;
            }

            public String getBdeprtype() {
                return bdeprtype;
            }

            public void setBdeprtype(String bdeprtype) {
                this.bdeprtype = bdeprtype;
            }

            public String getCcomunitname() {
                return ccomunitname;
            }

            public void setCcomunitname(String ccomunitname) {
                this.ccomunitname = ccomunitname;
            }

            public String getCdemo() {
                return cdemo;
            }

            public void setCdemo(String cdemo) {
                this.cdemo = cdemo;
            }

            public String getCinvccode() {
                return cinvccode;
            }

            public void setCinvccode(String cinvccode) {
                this.cinvccode = cinvccode;
            }

            public String getCinvcname() {
                return cinvcname;
            }

            public void setCinvcname(String cinvcname) {
                this.cinvcname = cinvcname;
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

            public String getDelFlag() {
                return delFlag;
            }

            public void setDelFlag(String delFlag) {
                this.delFlag = delFlag;
            }

            public String getDelhp() {
                return delhp;
            }

            public void setDelhp(String delhp) {
                this.delhp = delhp;
            }

            public String getDeptid() {
                return deptid;
            }

            public void setDeptid(String deptid) {
                this.deptid = deptid;
            }

            public String getFbvrate() {
                return fbvrate;
            }

            public void setFbvrate(String fbvrate) {
                this.fbvrate = fbvrate;
            }

            public String getHpdGuid() {
                return hpdGuid;
            }

            public void setHpdGuid(String hpdGuid) {
                this.hpdGuid = hpdGuid;
            }

            public String getHpiaGuid() {
                return hpiaGuid;
            }

            public void setHpiaGuid(String hpiaGuid) {
                this.hpiaGuid = hpiaGuid;
            }

            public String getHprGuid() {
                return hprGuid;
            }

            public void setHprGuid(String hprGuid) {
                this.hprGuid = hprGuid;
            }

            public String getIlife() {
                return ilife;
            }

            public void setIlife(String ilife) {
                this.ilife = ilife;
            }

            public String getIsassets() {
                return isassets;
            }

            public void setIsassets(String isassets) {
                this.isassets = isassets;
            }

            public String getParentid() {
                return parentid;
            }

            public void setParentid(String parentid) {
                this.parentid = parentid;
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
        }
    }
}
