package com.xunlei.common.accelerator.bean;

public class BaseResultBean {
    private int error;
    private String message;
    private String richmessage;
    private int seq;

    public BaseResultBean() {
        this.seq = 0;
    }

    public int getSeq() {
        return this.seq;
    }

    public void setSeq(int i) {
        this.seq = i;
    }

    public int getError() {
        return this.error;
    }

    public void setError(int i) {
        this.error = i;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public String getRichmessage() {
        return this.richmessage;
    }

    public void setRichmessage(String str) {
        this.richmessage = str;
    }
}
