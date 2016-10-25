package com.xunlei.downloadprovider.service.downloads.task.info;

import com.xunlei.xiazaibao.BuildConfig;
import java.io.Serializable;

public class BTSubTaskInfo implements Serializable {
    private static final long serialVersionUID = 1;
    public int mBTSubIndex;
    public String mCID;
    public long mDownloadSpeed;
    public long mDownloadedSize;
    public long mFileSize;
    public String mGCID;
    public String mLocalFileName;
    public int mOriginalStatusCode;
    public long mParentTaskId;
    public long mTaskId;
    public int mTaskStatus;
    public String mTitle;

    public BTSubTaskInfo() {
        this.mTitle = BuildConfig.VERSION_NAME;
        this.mLocalFileName = BuildConfig.VERSION_NAME;
        this.mFileSize = 0;
        this.mTaskId = -1;
        this.mParentTaskId = -1;
        this.mBTSubIndex = -1;
        this.mDownloadedSize = 0;
        this.mDownloadSpeed = 0;
        this.mTaskStatus = 0;
        this.mOriginalStatusCode = 0;
        this.mCID = BuildConfig.VERSION_NAME;
        this.mGCID = BuildConfig.VERSION_NAME;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BTSubTaskInfo)) {
            return false;
        }
        return this.mTaskId == ((BTSubTaskInfo) obj).mTaskId;
    }

    public int hashCode() {
        return (int) (this.mTaskId ^ (this.mTaskId >>> 32));
    }
}
