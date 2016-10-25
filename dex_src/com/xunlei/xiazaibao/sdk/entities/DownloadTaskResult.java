package com.xunlei.xiazaibao.sdk.entities;

public class DownloadTaskResult {
    private String id;
    private String msg;
    private int result;

    public void setId(String str) {
        this.id = str;
    }

    public void setResult(int i) {
        this.result = i;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public String getId() {
        return this.id;
    }

    public int getResult() {
        return this.result;
    }

    public String getMsg() {
        return this.msg;
    }
}
