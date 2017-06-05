package com.zjrfid.materialsmanage.acdbentity;

import java.util.List;

/**
 * Created by Administrator on 2017/5/31 0031.
 */
public class CAllGoodsAllocationData {
    private String statusCode;
    private String message;
    private String navTabId;
    private String forwordUrl;
    private String callbackType;
    private List<JsonDataBean> jsonData;

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

    public List<JsonDataBean> getJsonData() {
        return jsonData;
    }

    public void setJsonData(List<JsonDataBean> jsonData) {
        this.jsonData = jsonData;
    }

    public static class JsonDataBean {
        /**
         * alias : 01 莲花停保场仓库
         * cparentid : 莲花停保场仓库
         * cposcode : 01
         * hppGuid : 0
         * icon : /goods/styles/img/whouse.png
         * parentid : 0
         */

        private String alias;
        private String cparentid;
        private String cposcode;
        private String hppGuid;
        private String icon;
        private String parentid;

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

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getParentid() {
            return parentid;
        }

        public void setParentid(String parentid) {
            this.parentid = parentid;
        }
    }
}
