package com.zjrfid.materialsmanage.acdbentity;

import java.util.List;

/**
 * Created by Administrator on 2017/3/29 0029.
 * 供应商信息
 */
public class SupplierInfo {
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
            private String address;
            private String arrAddress;
            private String arrWarehouse;
            private String arrWay;
            private String belong;
            private String cdemo;
            private String cfax;
            private String clevel;
            private String contact1;
            private String contact2;
            private String createBy;
            private String createDt;
            private String cvencode;
            private String cvenname;
            private String cwhcode;
            private String delFlag;
            private String deptid;
            private String developmentdate;
            private String disabledate;
            private String email;
            private String guidsname;
            private String hpsGuid;
            private String hpsnGuid;
            private String orgname;
            private String postalcode;
            private String telephone1;
            private String telephone2;
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
            private String userid;

            @Override
            public String toString() {
                return "ListBean{" +
                        "address='" + address + '\'' +
                        ", arrAddress='" + arrAddress + '\'' +
                        ", arrWarehouse='" + arrWarehouse + '\'' +
                        ", arrWay='" + arrWay + '\'' +
                        ", belong='" + belong + '\'' +
                        ", cdemo='" + cdemo + '\'' +
                        ", cfax='" + cfax + '\'' +
                        ", clevel='" + clevel + '\'' +
                        ", contact1='" + contact1 + '\'' +
                        ", contact2='" + contact2 + '\'' +
                        ", createBy='" + createBy + '\'' +
                        ", createDt='" + createDt + '\'' +
                        ", cvencode='" + cvencode + '\'' +
                        ", cvenname='" + cvenname + '\'' +
                        ", cwhcode='" + cwhcode + '\'' +
                        ", delFlag='" + delFlag + '\'' +
                        ", deptid='" + deptid + '\'' +
                        ", developmentdate='" + developmentdate + '\'' +
                        ", disabledate='" + disabledate + '\'' +
                        ", email='" + email + '\'' +
                        ", guidsname='" + guidsname + '\'' +
                        ", hpsGuid='" + hpsGuid + '\'' +
                        ", hpsnGuid='" + hpsnGuid + '\'' +
                        ", orgname='" + orgname + '\'' +
                        ", postalcode='" + postalcode + '\'' +
                        ", telephone1='" + telephone1 + '\'' +
                        ", telephone2='" + telephone2 + '\'' +
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
                        ", userid='" + userid + '\'' +
                        '}';
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getArrAddress() {
                return arrAddress;
            }

            public void setArrAddress(String arrAddress) {
                this.arrAddress = arrAddress;
            }

            public String getArrWarehouse() {
                return arrWarehouse;
            }

            public void setArrWarehouse(String arrWarehouse) {
                this.arrWarehouse = arrWarehouse;
            }

            public String getArrWay() {
                return arrWay;
            }

            public void setArrWay(String arrWay) {
                this.arrWay = arrWay;
            }

            public String getBelong() {
                return belong;
            }

            public void setBelong(String belong) {
                this.belong = belong;
            }

            public String getCdemo() {
                return cdemo;
            }

            public void setCdemo(String cdemo) {
                this.cdemo = cdemo;
            }

            public String getCfax() {
                return cfax;
            }

            public void setCfax(String cfax) {
                this.cfax = cfax;
            }

            public String getClevel() {
                return clevel;
            }

            public void setClevel(String clevel) {
                this.clevel = clevel;
            }

            public String getContact1() {
                return contact1;
            }

            public void setContact1(String contact1) {
                this.contact1 = contact1;
            }

            public String getContact2() {
                return contact2;
            }

            public void setContact2(String contact2) {
                this.contact2 = contact2;
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

            public String getCvencode() {
                return cvencode;
            }

            public void setCvencode(String cvencode) {
                this.cvencode = cvencode;
            }

            public String getCvenname() {
                return cvenname;
            }

            public void setCvenname(String cvenname) {
                this.cvenname = cvenname;
            }

            public String getCwhcode() {
                return cwhcode;
            }

            public void setCwhcode(String cwhcode) {
                this.cwhcode = cwhcode;
            }

            public String getDelFlag() {
                return delFlag;
            }

            public void setDelFlag(String delFlag) {
                this.delFlag = delFlag;
            }

            public String getDeptid() {
                return deptid;
            }

            public void setDeptid(String deptid) {
                this.deptid = deptid;
            }

            public String getDevelopmentdate() {
                return developmentdate;
            }

            public void setDevelopmentdate(String developmentdate) {
                this.developmentdate = developmentdate;
            }

            public String getDisabledate() {
                return disabledate;
            }

            public void setDisabledate(String disabledate) {
                this.disabledate = disabledate;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getGuidsname() {
                return guidsname;
            }

            public void setGuidsname(String guidsname) {
                this.guidsname = guidsname;
            }

            public String getHpsGuid() {
                return hpsGuid;
            }

            public void setHpsGuid(String hpsGuid) {
                this.hpsGuid = hpsGuid;
            }

            public String getHpsnGuid() {
                return hpsnGuid;
            }

            public void setHpsnGuid(String hpsnGuid) {
                this.hpsnGuid = hpsnGuid;
            }

            public String getOrgname() {
                return orgname;
            }

            public void setOrgname(String orgname) {
                this.orgname = orgname;
            }

            public String getPostalcode() {
                return postalcode;
            }

            public void setPostalcode(String postalcode) {
                this.postalcode = postalcode;
            }

            public String getTelephone1() {
                return telephone1;
            }

            public void setTelephone1(String telephone1) {
                this.telephone1 = telephone1;
            }

            public String getTelephone2() {
                return telephone2;
            }

            public void setTelephone2(String telephone2) {
                this.telephone2 = telephone2;
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

            public String getUserid() {
                return userid;
            }

            public void setUserid(String userid) {
                this.userid = userid;
            }
        }
    }
}
