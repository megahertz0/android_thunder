package com.xunlei.xiazaibao.sdk.entities;

import com.xunlei.xiazaibao.sdk.base.NoObfuscationClassBase;

public class DownloadSubBtInfo extends NoObfuscationClassBase {
    private int failCode;
    private int id;
    private String name;
    private int progress;
    private int selected;
    private long size;
    private int status;

    public void setProgress(int i) {
        this.progress = i;
    }

    public void setId(int i) {
        this.id = i;
    }

    public void setSelected(int i) {
        this.selected = i;
    }

    public void setFailCode(int i) {
        this.failCode = i;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setSize(long j) {
        this.size = j;
    }

    public int getProgress() {
        return this.progress;
    }

    public int getId() {
        return this.id;
    }

    public int getSelected() {
        return this.selected;
    }

    public int getFailCode() {
        return this.failCode;
    }

    public int getStatus() {
        return this.status;
    }

    public String getName() {
        return this.name;
    }

    public long getSize() {
        return this.size;
    }
}
