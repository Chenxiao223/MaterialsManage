package com.zjrfid.materialsmanage.acdbentity;

import java.util.List;

/**
 * Created by Administrator on 2017/4/12.  仓库权限实体类
 */
public class WarehouseAuthority {


    /**
     * deptid : abe58536-c081-4d99-ab87-9eed12667470
     * deptname : 计划财务部
     * jobnumber : 610214
     * userid : B1DB9C97-9DE9-4209-BF46-59C46B0893AA
     * username : 黄晓承
     * wareReadhouse : [{"cwhcode":"01"},{"cwhcode":"05"},{"cwhcode":"0401"},{"cwhcode":"0402"},{"cwhcode":"0405"},{"cwhcode":"0406"},{"cwhcode":"03"},{"cwhcode":"0404"},{"cwhcode":"02"},{"cwhcode":"0403"}]
     * wareWritehouse : [{"cwhcode":"01"},{"cwhcode":"05"},{"cwhcode":"0401"},{"cwhcode":"0402"},{"cwhcode":"0405"},{"cwhcode":"0406"},{"cwhcode":"03"},{"cwhcode":"0404"},{"cwhcode":"02"},{"cwhcode":"0403"}]
     */

    private String deptid;
    private String deptname;
    private String jobnumber;
    private String userid;
    private String username;
    private List<WareReadhouseBean> wareReadhouse;
    private List<WareWritehouseBean> wareWritehouse;

    public String getDeptid() {
        return deptid;
    }

    public void setDeptid(String deptid) {
        this.deptid = deptid;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public String getJobnumber() {
        return jobnumber;
    }

    public void setJobnumber(String jobnumber) {
        this.jobnumber = jobnumber;
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

    public List<WareReadhouseBean> getWareReadhouse() {
        return wareReadhouse;
    }

    public void setWareReadhouse(List<WareReadhouseBean> wareReadhouse) {
        this.wareReadhouse = wareReadhouse;
    }

    public List<WareWritehouseBean> getWareWritehouse() {
        return wareWritehouse;
    }

    public void setWareWritehouse(List<WareWritehouseBean> wareWritehouse) {
        this.wareWritehouse = wareWritehouse;
    }

    public static class WareReadhouseBean {
        /**
         * cwhcode : 01
         */

        private String cwhcode;

        public String getCwhcode() {
            return cwhcode;
        }

        public void setCwhcode(String cwhcode) {
            this.cwhcode = cwhcode;
        }
    }

    public static class WareWritehouseBean {
        /**
         * cwhcode : 01
         */

        private String cwhcode;

        public String getCwhcode() {
            return cwhcode;
        }

        public void setCwhcode(String cwhcode) {
            this.cwhcode = cwhcode;
        }
    }
}
