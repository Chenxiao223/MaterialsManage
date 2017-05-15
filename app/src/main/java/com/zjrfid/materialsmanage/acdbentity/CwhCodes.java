package com.zjrfid.materialsmanage.acdbentity;

import java.util.List;

/**
 * Created by chenxiao on 2017/3/31.
 */
public class CwhCodes {
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
    public static class WareReadhouseBean{
        private String cwhcode;

        @Override
        public String toString() {
            return "WareReadhouseBean{" +
                    "cwhcode='" + cwhcode + '\'' +
                    '}';
        }

        public String getCwhcode() {
            return cwhcode;
        }

        public void setCwhcode(String cwhcode) {
            this.cwhcode = cwhcode;
        }
    }
    public static class WareWritehouseBean{
        private String cwhcode;

        @Override
        public String toString() {
            return "WareWritehouseBean{" +
                    "cwhcode='" + cwhcode + '\'' +
                    '}';
        }

        public String getCwhcode() {
            return cwhcode;
        }

        public void setCwhcode(String cwhcode) {
            this.cwhcode = cwhcode;
        }
    }
}
