package com.zjrfid.materialsmanage.acdbentity;

import java.util.List;

/**
 * Created by Administrator on 2017/3/29 0029.
 * 供应商分类实体类
 */
public class SupplierClassification {
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
            private String alias;
            private String ccode;
            private String cdemo;
            private String cname;
            private String createBy;
            private String createDt;
            private String delFlag;
            private String delhp;
            private String hpsGuid;
            private String smid;
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
                        ", ccode='" + ccode + '\'' +
                        ", cdemo='" + cdemo + '\'' +
                        ", cname='" + cname + '\'' +
                        ", createBy='" + createBy + '\'' +
                        ", createDt='" + createDt + '\'' +
                        ", delFlag='" + delFlag + '\'' +
                        ", delhp='" + delhp + '\'' +
                        ", hpsGuid='" + hpsGuid + '\'' +
                        ", smid='" + smid + '\'' +
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

            public String getCcode() {
                return ccode;
            }

            public void setCcode(String ccode) {
                this.ccode = ccode;
            }

            public String getCdemo() {
                return cdemo;
            }

            public void setCdemo(String cdemo) {
                this.cdemo = cdemo;
            }

            public String getCname() {
                return cname;
            }

            public void setCname(String cname) {
                this.cname = cname;
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

            public String getHpsGuid() {
                return hpsGuid;
            }

            public void setHpsGuid(String hpsGuid) {
                this.hpsGuid = hpsGuid;
            }

            public String getSmid() {
                return smid;
            }

            public void setSmid(String smid) {
                this.smid = smid;
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
