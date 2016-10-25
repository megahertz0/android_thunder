package com.xunlei.common.accelerator.bean;

public class KeepResultBean extends BaseResultBean {
    String client_type;
    String peerid;
    String timestamp;
    String upgrade_type;
    String userid;

    public String getClient_type() {
        return this.client_type;
    }

    public void setClient_type(String str) {
        this.client_type = str;
    }

    public String getPeerid() {
        return this.peerid;
    }

    public void setPeerid(String str) {
        this.peerid = str;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(String str) {
        this.timestamp = str;
    }

    public String getUpgrade_type() {
        return this.upgrade_type;
    }

    public void setUpgrade_type(String str) {
        this.upgrade_type = str;
    }

    public String getUserid() {
        return this.userid;
    }

    public void setUserid(String str) {
        this.userid = str;
    }
}
