package com.xunlei.downloadprovider.service.downloads.task.info;

import com.umeng.a;
import com.xunlei.download.DownloadManager.TaskType;
import java.io.Serializable;

public class TaskBasicInfo implements Serializable {
    public static final int ETT_BT = 1;
    public static final int ETT_CID = 2;
    public static final int ETT_EMULE = 3;
    public static final int ETT_MAGNET = 4;
    public static final int ETT_URL = 0;
    public static final int STATUS_FAILED = 16;
    public static final int STATUS_PAUSED = 4;
    public static final int STATUS_PENDING = 1;
    public static final int STATUS_RUNNING = 2;
    public static final int STATUS_SUCCESSFUL = 8;
    private static final long serialVersionUID = 1;
    public String mAppName;
    public String mAppVersion;
    public String mCID;
    public String mCookie;
    public long mCreateTime;
    public String mDescription;
    public String mDisplayName;
    public long mDownloadDurationTime;
    public long mDownloadSpeed;
    public long mDownloadedSize;
    public String mErrorMsg;
    public long mFailureReason;
    public String mFileName;
    public String mFilePath;
    public long mFileSize;
    public String mGCID;
    public boolean mHasLixianSpeedup;
    public boolean mHasVipChannelSpeedup;
    public String mInfoHash;
    public boolean mIsXunleiSpdy;
    public long mLastModifiedTime;
    public long mLixianProgress;
    public long mLixianReceivedSize;
    public long mLixianSpeed;
    public int mLixianStatus;
    public int mLixianStatusCode;
    public String mLocalFileName;
    public long mOriginReceivedSize;
    public long mOriginSpeed;
    public int mOriginalStatusCode;
    public long mP2pReceivedSize;
    public long mP2pSpeed;
    public long mP2sReceivedSize;
    public long mP2sSpeed;
    public String mRefUrl;
    public long mResLinkTotal;
    public long mResLinkUsed;
    public long mTaskId;
    public int mTaskOldType;
    public int mTaskStatus;
    public TaskType mTaskType;
    public String mTitle;
    public String mUrl;
    public long mVipChannelReceivedSize;
    public long mVipChannelSpeed;
    public int mVipChannelStatus;
    public int mVipChannelStatusCode;

    public TaskBasicInfo() {
        this.mUrl = a.d;
        this.mRefUrl = a.d;
        this.mCookie = a.d;
        this.mTitle = a.d;
        this.mDisplayName = null;
        this.mLocalFileName = a.d;
        this.mFileSize = 0;
        this.mDownloadSpeed = 0;
        this.mOriginSpeed = 0;
        this.mP2sSpeed = 0;
        this.mP2pSpeed = 0;
        this.mLixianSpeed = 0;
        this.mVipChannelSpeed = 0;
        this.mDownloadedSize = 0;
        this.mOriginReceivedSize = 0;
        this.mP2pReceivedSize = 0;
        this.mP2sReceivedSize = 0;
        this.mVipChannelReceivedSize = 0;
        this.mLixianReceivedSize = 0;
        this.mResLinkTotal = 0;
        this.mResLinkUsed = 0;
        this.mCreateTime = 0;
        this.mLastModifiedTime = 0;
        this.mDownloadDurationTime = 0;
        this.mAppVersion = a.d;
        this.mAppName = a.d;
        this.mDescription = a.d;
        this.mCID = a.d;
        this.mGCID = a.d;
        this.mInfoHash = a.d;
        this.mHasLixianSpeedup = false;
        this.mLixianStatus = 0;
        this.mLixianStatusCode = 0;
        this.mLixianProgress = 0;
        this.mHasVipChannelSpeedup = false;
        this.mVipChannelStatus = 0;
        this.mVipChannelStatusCode = 0;
        this.mIsXunleiSpdy = false;
        this.mTaskType = TaskType.HTTP;
        this.mTaskOldType = 0;
        this.mTaskId = -1;
        this.mTaskStatus = 0;
        this.mOriginalStatusCode = 0;
        this.mFailureReason = 0;
        this.mErrorMsg = a.d;
    }

    public void deepCopyFrom(Object obj) {
        if (this != obj && obj != null && (obj instanceof TaskBasicInfo)) {
            TaskBasicInfo taskBasicInfo = (TaskBasicInfo) obj;
            this.mUrl = taskBasicInfo.mUrl;
            this.mRefUrl = taskBasicInfo.mRefUrl;
            this.mCookie = taskBasicInfo.mCookie;
            this.mTitle = taskBasicInfo.mTitle;
            this.mDisplayName = taskBasicInfo.mDisplayName;
            this.mFileName = taskBasicInfo.mFileName;
            this.mFilePath = taskBasicInfo.mFilePath;
            this.mLocalFileName = taskBasicInfo.mLocalFileName;
            this.mFileSize = taskBasicInfo.mFileSize;
            this.mDownloadSpeed = taskBasicInfo.mDownloadSpeed;
            this.mOriginSpeed = taskBasicInfo.mOriginSpeed;
            this.mP2sSpeed = taskBasicInfo.mP2sSpeed;
            this.mP2pSpeed = taskBasicInfo.mP2pSpeed;
            this.mLixianSpeed = taskBasicInfo.mLixianSpeed;
            this.mVipChannelSpeed = taskBasicInfo.mVipChannelSpeed;
            this.mDownloadedSize = taskBasicInfo.mDownloadedSize;
            this.mOriginReceivedSize = taskBasicInfo.mOriginReceivedSize;
            this.mP2pReceivedSize = taskBasicInfo.mP2pReceivedSize;
            this.mP2sReceivedSize = taskBasicInfo.mP2sReceivedSize;
            this.mVipChannelReceivedSize = taskBasicInfo.mVipChannelReceivedSize;
            this.mLixianReceivedSize = taskBasicInfo.mLixianReceivedSize;
            this.mResLinkTotal = taskBasicInfo.mResLinkTotal;
            this.mResLinkUsed = taskBasicInfo.mResLinkUsed;
            this.mCreateTime = taskBasicInfo.mCreateTime;
            this.mLastModifiedTime = taskBasicInfo.mLastModifiedTime;
            this.mDownloadDurationTime = taskBasicInfo.mDownloadDurationTime;
            this.mAppVersion = taskBasicInfo.mAppVersion;
            this.mAppName = taskBasicInfo.mAppName;
            this.mDescription = taskBasicInfo.mDescription;
            this.mCID = taskBasicInfo.mCID;
            this.mGCID = taskBasicInfo.mGCID;
            this.mInfoHash = taskBasicInfo.mInfoHash;
            this.mHasLixianSpeedup = taskBasicInfo.mHasLixianSpeedup;
            this.mLixianStatus = taskBasicInfo.mLixianStatus;
            this.mLixianStatusCode = taskBasicInfo.mLixianStatusCode;
            this.mLixianProgress = taskBasicInfo.mLixianProgress;
            this.mHasVipChannelSpeedup = taskBasicInfo.mHasVipChannelSpeedup;
            this.mVipChannelStatus = taskBasicInfo.mVipChannelStatus;
            this.mVipChannelStatusCode = taskBasicInfo.mVipChannelStatusCode;
            this.mIsXunleiSpdy = taskBasicInfo.mIsXunleiSpdy;
            this.mTaskType = taskBasicInfo.mTaskType;
            this.mTaskOldType = taskBasicInfo.mTaskOldType;
            this.mTaskId = taskBasicInfo.mTaskId;
            this.mTaskStatus = taskBasicInfo.mTaskStatus;
            this.mOriginalStatusCode = taskBasicInfo.mOriginalStatusCode;
            this.mFailureReason = taskBasicInfo.mFailureReason;
            this.mErrorMsg = taskBasicInfo.mErrorMsg;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TaskBasicInfo)) {
            return false;
        }
        return this.mTaskId == ((TaskBasicInfo) obj).mTaskId;
    }

    public int hashCode() {
        return (int) (this.mTaskId ^ (this.mTaskId >>> 32));
    }
}
