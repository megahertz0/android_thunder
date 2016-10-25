package com.xunlei.xiazaibao.sdk.entities;

import com.xunlei.xiazaibao.sdk.base.NoObfuscationClassBase;
import java.io.Serializable;

public class DownloadLixianChannel extends NoObfuscationClassBase implements Serializable {
    private static final long serialVersionUID = -7060210544600464483L;
    private long dlBytes;
    private int failCode;
    private long serverProgress;
    private long serverSpeed;
    private long speed;
    private int state;

    public void setSpeed(int i) {
        this.speed = (long) i;
    }

    public void setServerSpeed(int i) {
        this.serverSpeed = (long) i;
    }

    public void setFailCode(int i) {
        this.failCode = i;
    }

    public void setDlBytes(int i) {
        this.dlBytes = (long) i;
    }

    public void setState(int i) {
        this.state = i;
    }

    public void setServerProgress(int i) {
        this.serverProgress = (long) i;
    }

    public long getSpeed() {
        return this.speed;
    }

    public long getServerSpeed() {
        return this.serverSpeed;
    }

    public int getFailCode() {
        return this.failCode;
    }

    public long getDlBytes() {
        return this.dlBytes;
    }

    public int getState() {
        return this.state;
    }

    public long getServerProgress() {
        return this.serverProgress;
    }
}
