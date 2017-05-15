package com.zjrfid.materialsmanage.acdbentity;

/**
 * Created by Administrator on 2017/2/20 0020.
 */
public class LoginInfo {
    private String jobnumber;
    private String username;
    private String joindate;
    private String orgname;//部门
    private String userid;
    private String cardnumber;
    private String fullname;
    private String mobile;
    public LoginInfo(){}
    public LoginInfo(String jobnumber, String username, String joindate, String orgname, String userid, String cardnumber, String fullname, String mobile) {
        this.jobnumber = jobnumber;
        this.username = username;
        this.joindate = joindate;
        this.orgname = orgname;
        this.userid = userid;
        this.cardnumber = cardnumber;
        this.fullname = fullname;
        this.mobile = mobile;
    }

    public String getJobnumber() {
        return jobnumber;
    }

    public void setJobnumber(String jobnumber) {
        this.jobnumber = jobnumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getJoindate() {
        return joindate;
    }

    public void setJoindate(String joindate) {
        this.joindate = joindate;
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

    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
