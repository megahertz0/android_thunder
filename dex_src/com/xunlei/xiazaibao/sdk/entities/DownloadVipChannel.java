package com.xunlei.xiazaibao.sdk.entities;

import com.xunlei.xiazaibao.sdk.base.NoObfuscationClassBase;
import java.io.Serializable;

public class DownloadVipChannel extends NoObfuscationClassBase implements Serializable {
    private static final long serialVersionUID = -7060210544600464482L;
    private int available;
    private long dlBytes;
    private int failCode;
    private int opened;
    private long speed;
    private int type;

    public void setSpeed(int i) {
        this.speed = (long) i;
    }

    public void setFailCode(int i) {
        this.failCode = i;
    }

    public void setDlBytes(int i) {
        this.dlBytes = (long) i;
    }

    public void setAvailable(int i) {
        this.available = i;
    }

    public void setType(int i) {
        this.type = i;
    }

    public void setOpened(int i) {
        this.opened = i;
    }

    public long getSpeed() {
        return this.speed;
    }

    public int getFailCode() {
        return this.failCode;
    }

    public long getDlBytes() {
        return this.dlBytes;
    }

    public int getAvailable() {
        return this.available;
    }

    public int getType() {
        return this.type;
    }

    public int getOpened() {
        return this.opened;
    }
}
