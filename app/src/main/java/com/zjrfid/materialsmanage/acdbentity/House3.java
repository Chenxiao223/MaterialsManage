package com.zjrfid.materialsmanage.acdbentity;

/**
 * Created by Administrator on 2016/12/28.
 */
public class House3 {
    private int wzshuliang,wzdanjia,rkshuliang;
    private String bianma,name,huowei,pici,is,cwhname;
    private  int num;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public House3(int wzshuliang, int wzdanjia, int rkshuliang, String bianma, String name, String huowei, String pici, String is,String cwhname) {
        super();
        this.wzshuliang = wzshuliang;
        this.wzdanjia = wzdanjia;
        this.rkshuliang = rkshuliang;
        this.bianma = bianma;
        this.name = name;
        this.huowei = huowei;
        this.pici = pici;
        this.is=is;
        this.cwhname=cwhname;
    }

    public int getWzshuliang() {
        return wzshuliang;
    }

    public void setWzshuliang(int wzshuliang) {
        this.wzshuliang = wzshuliang;
    }

    public int getWzdanjia() {
        return wzdanjia;
    }

    public void setWzdanjia(int wzdanjia) {
        this.wzdanjia = wzdanjia;
    }

    public int getRkshuliang() {
        return rkshuliang;
    }

    public void setRkshuliang(int rkshuliang) {
        this.rkshuliang = rkshuliang;
    }

    public String getBianma() {
        return bianma;
    }

    public void setBianma(String bianma) {
        this.bianma = bianma;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHuowei() {
        return huowei;
    }

    public void setHuowei(String huowei) {
        this.huowei = huowei;
    }

    public String getPici() {
        return pici;
    }

    public void setPici(String pici) {
        this.pici = pici;
    }

    public String getIs() {
        return is;
    }

    public void setIs(String is) {
        this.is = is;
    }

    public String getCwhname() {
        return cwhname;
    }

    public void setCwhname(String cwhname) {
        this.cwhname = cwhname;
    }
}
