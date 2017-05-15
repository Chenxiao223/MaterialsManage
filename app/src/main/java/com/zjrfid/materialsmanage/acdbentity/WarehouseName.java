package com.zjrfid.materialsmanage.acdbentity;

import java.util.List;

/**
 * Created by Administrator on 2017/3/10 0010.
 */
public class WarehouseName {
    private String statusCode;
    private String message;
    private String navTabId;
    private String forwordUrl;
    private String callbackType;
    private JsonData jsonData;

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

    public JsonData getJsonData() {
        return jsonData;
    }

    public void setJsonData(JsonData jsonData) {
        this.jsonData = jsonData;
    }

    public static class JsonData{
        private String currentPage;
        private String numPerPage;
        private String pageNumShown;
        private String totalCount;
        private List<Info> list;

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

        public List<Info> getList() {
            return list;
        }

        public void setList(List<Info> list) {
            this.list = list;
        }

        public static class Info{
            private String alias;
            private String cdemo;
            private String cwhcode;
            private String cwhname;
            private String cwhperson;
            private String hpwGuid;
            private String iassets;
            private String iwhpos;
            private String parentid;

            @Override
            public String toString() {
                return "Info{" +
                        "alias='" + alias + '\'' +
                        ", cdemo='" + cdemo + '\'' +
                        ", cwhcode='" + cwhcode + '\'' +
                        ", cwhname='" + cwhname + '\'' +
                        ", cwhperson='" + cwhperson + '\'' +
                        ", hpwGuid='" + hpwGuid + '\'' +
                        ", iassets='" + iassets + '\'' +
                        ", iwhpos='" + iwhpos + '\'' +
                        ", parentid='" + parentid + '\'' +
                        '}';
            }

            public String getAlias() {
                return alias;
            }

            public void setAlias(String alias) {
                this.alias = alias;
            }

            public String getCdemo() {
                return cdemo;
            }

            public void setCdemo(String cdemo) {
                this.cdemo = cdemo;
            }

            public String getCwhcode() {
                return cwhcode;
            }

            public void setCwhcode(String cwhcode) {
                this.cwhcode = cwhcode;
            }

            public String getCwhname() {
                return cwhname;
            }

            public void setCwhname(String cwhname) {
                this.cwhname = cwhname;
            }

            public String getCwhperson() {
                return cwhperson;
            }

            public void setCwhperson(String cwhperson) {
                this.cwhperson = cwhperson;
            }

            public String getHpwGuid() {
                return hpwGuid;
            }

            public void setHpwGuid(String hpwGuid) {
                this.hpwGuid = hpwGuid;
            }

            public String getIassets() {
                return iassets;
            }

            public void setIassets(String iassets) {
                this.iassets = iassets;
            }

            public String getIwhpos() {
                return iwhpos;
            }

            public void setIwhpos(String iwhpos) {
                this.iwhpos = iwhpos;
            }

            public String getParentid() {
                return parentid;
            }

            public void setParentid(String parentid) {
                this.parentid = parentid;
            }
        }
    }
}
