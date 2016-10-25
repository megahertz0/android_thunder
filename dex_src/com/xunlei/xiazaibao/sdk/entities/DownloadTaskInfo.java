package com.xunlei.xiazaibao.sdk.entities;

import com.xunlei.xiazaibao.sdk.base.NoObfuscationClassBase;
import java.io.Serializable;
import java.util.List;

public class DownloadTaskInfo extends NoObfuscationClassBase implements Serializable {
    private static final long serialVersionUID = -7060210544600464481L;
    private long completeTime;
    private long createTime;
    private long downTime;
    private int failCode;
    private String id;
    private DownloadLixianChannel lixianChannel;
    private String name;
    private String path;
    private int progress;
    private long remainTime;
    private long size;
    private long speed;
    private int state;
    private List<DownloadSubBtInfo> subList;
    private int type;
    private String url;
    private DownloadVipChannel vipChannel;

    public void setProgress(int i) {
        this.progress = i;
    }

    public void setCreateTime(long j) {
        this.createTime = j;
    }

    public void setSpeed(long j) {
        this.speed = j;
    }

    public void setState(int i) {
        this.state = i;
    }

    public void setType(int i) {
        this.type = i;
    }

    public void setVipChannel(DownloadVipChannel downloadVipChannel) {
        this.vipChannel = downloadVipChannel;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public void setSize(long j) {
        this.size = j;
    }

    public void setDownTime(long j) {
        this.downTime = j;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setRemainTime(long j) {
        this.remainTime = j;
    }

    public void setFailCode(int i) {
        this.failCode = i;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setPath(String str) {
        this.path = str;
    }

    public void setLixianChannel(DownloadLixianChannel downloadLixianChannel) {
        this.lixianChannel = downloadLixianChannel;
    }

    public void setSubList(List<DownloadSubBtInfo> list) {
        this.subList = list;
    }

    public void setCompleteTime(long j) {
        this.completeTime = j;
    }

    public int getProgress() {
        return this.progress;
    }

    public long getCreateTime() {
        return this.createTime;
    }

    public long getSpeed() {
        return this.speed;
    }

    public int getState() {
        return this.state;
    }

    public int getType() {
        return this.type;
    }

    public DownloadVipChannel getVipChannel() {
        return this.vipChannel;
    }

    public String getUrl() {
        return this.url;
    }

    public long getSize() {
        return this.size;
    }

    public long getDownTime() {
        return this.downTime;
    }

    public String getId() {
        return this.id;
    }

    public long getRemainTime() {
        return this.remainTime;
    }

    public int getFailCode() {
        return this.failCode;
    }

    public String getName() {
        return this.name;
    }

    public String getPath() {
        return this.path;
    }

    public DownloadLixianChannel getLixianChannel() {
        return this.lixianChannel;
    }

    public List<DownloadSubBtInfo> getSubList() {
        return this.subList;
    }

    public long getCompleteTime() {
        return this.completeTime;
    }
}
