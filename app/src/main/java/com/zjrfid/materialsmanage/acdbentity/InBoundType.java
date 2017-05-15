package com.zjrfid.materialsmanage.acdbentity;

import java.util.List;

/**
 * Created by Administrator on 2017/3/10 0010.入库类别
 */
public class InBoundType {
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
            private String brdflag;
            private String cdemo;
            private String cparentcode;
            private String crdcode;
            private String crdname;
            private String createBy;
            private String createDt;
            private String delFlag;
            private String hppiGuid;
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
                        ", brdflag='" + brdflag + '\'' +
                        ", cdemo='" + cdemo + '\'' +
                        ", cparentcode='" + cparentcode + '\'' +
                        ", crdcode='" + crdcode + '\'' +
                        ", crdname='" + crdname + '\'' +
                        ", createBy='" + createBy + '\'' +
                        ", createDt='" + createDt + '\'' +
                        ", delFlag='" + delFlag + '\'' +
                        ", hppiGuid='" + hppiGuid + '\'' +
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

            public String getBrdflag() {
                return brdflag;
            }

            public void setBrdflag(String brdflag) {
                this.brdflag = brdflag;
            }

            public String getCdemo() {
                return cdemo;
            }

            public void setCdemo(String cdemo) {
                this.cdemo = cdemo;
            }

            public String getCparentcode() {
                return cparentcode;
            }

            public void setCparentcode(String cparentcode) {
                this.cparentcode = cparentcode;
            }

            public String getCrdcode() {
                return crdcode;
            }

            public void setCrdcode(String crdcode) {
                this.crdcode = crdcode;
            }

            public String getCrdname() {
                return crdname;
            }

            public void setCrdname(String crdname) {
                this.crdname = crdname;
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

            public String getHppiGuid() {
                return hppiGuid;
            }

            public void setHppiGuid(String hppiGuid) {
                this.hppiGuid = hppiGuid;
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
