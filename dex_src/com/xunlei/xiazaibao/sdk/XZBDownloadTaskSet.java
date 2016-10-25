package com.xunlei.xiazaibao.sdk;

import com.xunlei.xiazaibao.sdk.entities.DownloadTaskInfo;
import com.xunlei.xiazaibao.sdk.entities.QueryDownloadListResponse;
import java.util.List;

public class XZBDownloadTaskSet {
    private QueryDownloadListResponse queryDownloadListResponse;

    public XZBDownloadTaskSet(QueryDownloadListResponse queryDownloadListResponse) {
        this.queryDownloadListResponse = queryDownloadListResponse;
    }

    public int getRtn() {
        return this.queryDownloadListResponse.getRtn();
    }

    public int getSync() {
        return this.queryDownloadListResponse.getSync();
    }

    public List<DownloadTaskInfo> getTasks() {
        return this.queryDownloadListResponse.getTasks();
    }

    public int getDowloadingNum() {
        return this.queryDownloadListResponse.getDlNum();
    }

    public int getRecycleNum() {
        return this.queryDownloadListResponse.getRecycleNum();
    }

    public int getCompleteNum() {
        return this.queryDownloadListResponse.getCompleteNum();
    }

    public int getServerFailNum() {
        return this.queryDownloadListResponse.getServerFailNum();
    }

    public long getLatestCreateTime() {
        List tasks = getTasks();
        return (tasks == null || tasks.isEmpty()) ? 0 : ((DownloadTaskInfo) tasks.get(0)).getCreateTime();
    }

    public long getSpeedCount() {
        return this.queryDownloadListResponse.speedCount;
    }

    public long getSpeedupCount() {
        return this.queryDownloadListResponse.lixian_vip_speedCount;
    }
}
