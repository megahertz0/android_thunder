package com.xunlei.downloadprovider.xiazaibao.remotedownload;

import com.xunlei.xiazaibao.sdk.entities.DownloadTaskInfo;
import java.io.Serializable;

// compiled from: XZBDownloadTaskInfo.java
public final class al extends DownloadTaskInfo implements Serializable {
    boolean a;

    public al(DownloadTaskInfo downloadTaskInfo) {
        setId(downloadTaskInfo.getId());
        setName(downloadTaskInfo.getName());
        setState(downloadTaskInfo.getState());
        setSize(downloadTaskInfo.getSize());
        setSpeed(downloadTaskInfo.getSpeed());
        setPath(downloadTaskInfo.getPath());
        setUrl(downloadTaskInfo.getUrl());
        setType(downloadTaskInfo.getType());
        setSubList(downloadTaskInfo.getSubList());
        setRemainTime(downloadTaskInfo.getRemainTime());
        setCompleteTime(downloadTaskInfo.getCompleteTime());
        setProgress(downloadTaskInfo.getProgress());
        setFailCode(downloadTaskInfo.getFailCode());
        setCreateTime(downloadTaskInfo.getCreateTime());
        setDownTime(downloadTaskInfo.getDownTime());
        setLixianChannel(downloadTaskInfo.getLixianChannel());
        setVipChannel(downloadTaskInfo.getVipChannel());
    }
}
