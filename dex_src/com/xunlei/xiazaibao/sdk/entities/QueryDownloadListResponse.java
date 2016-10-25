package com.xunlei.xiazaibao.sdk.entities;

import com.xunlei.xiazaibao.sdk.base.NoObfuscationClassBase;
import java.util.List;

public class QueryDownloadListResponse extends NoObfuscationClassBase {
    private int completeNum;
    private int dlNum;
    public long lixian_vip_speedCount;
    public String msg;
    private int recycleNum;
    private int rtn;
    private int serverFailNum;
    public long speedCount;
    private int sync;
    private List<DownloadTaskInfo> tasks;

    public void setRtn(int i) {
        this.rtn = i;
    }

    public void setSync(int i) {
        this.sync = i;
    }

    public void setTasks(List<DownloadTaskInfo> list) {
        this.tasks = list;
    }

    public void setDlNum(int i) {
        this.dlNum = i;
    }

    public void setRecycleNum(int i) {
        this.recycleNum = i;
    }

    public void setCompleteNum(int i) {
        this.completeNum = i;
    }

    public void setServerFailNum(int i) {
        this.serverFailNum = i;
    }

    public int getRtn() {
        return this.rtn;
    }

    public int getSync() {
        return this.sync;
    }

    public List<DownloadTaskInfo> getTasks() {
        return this.tasks;
    }

    public int getDlNum() {
        return this.dlNum;
    }

    public int getRecycleNum() {
        return this.recycleNum;
    }

    public int getCompleteNum() {
        return this.completeNum;
    }

    public int getServerFailNum() {
        return this.serverFailNum;
    }
}
