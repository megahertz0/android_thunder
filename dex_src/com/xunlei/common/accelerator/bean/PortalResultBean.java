package com.xunlei.common.accelerator.bean;

public class PortalResultBean extends BaseResultBean {
    private String address;
    private String ip;
    private String port;

    public String getIp() {
        return this.ip;
    }

    public void setIp(String str) {
        this.ip = str;
    }

    public String getPort() {
        return this.port;
    }

    public void setPort(String str) {
        this.port = str;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String str) {
        this.address = str;
    }
}
