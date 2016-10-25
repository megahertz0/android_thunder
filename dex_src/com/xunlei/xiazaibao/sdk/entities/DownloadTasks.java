package com.xunlei.xiazaibao.sdk.entities;

import java.util.List;

public class DownloadTasks {
    public String msg;
    private int rtn;
    private List<DownloadTaskResult> tasks;

    public void setRtn(int i) {
        this.rtn = i;
    }

    public void setTasks(List<DownloadTaskResult> list) {
        this.tasks = list;
    }

    public int getRtn() {
        return this.rtn;
    }

    public List<DownloadTaskResult> getTasks() {
        return this.tasks;
    }
}
