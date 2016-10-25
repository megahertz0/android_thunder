package com.xunlei.downloadprovider.service.downloads.task.info;

import android.os.SystemClock;
import com.xunlei.download.DownloadManager.TaskType;
import com.xunlei.downloadprovider.service.downloads.b.c;

public class TaskRunningInfo extends TaskBasicInfo {
    public long mAcceleratedChannelDownloadedSize;
    public long mAcceleratedChannelSpeed;
    public boolean mConsumed;
    public String mCreateOrigin;
    public long mDownloadRemainTime;
    public c mExtraInfo;
    public long mFreeTrialLMT;
    public long mFreeTrialRemainTime;
    public int mFreeTrialTimes;
    public boolean mHasOriginSpeed;
    public boolean mHasSpeed;
    public String mIconUrl;
    public boolean mIsEnteredHighSpeedTrial;
    public boolean mIsManualStart;
    public boolean mIsToastForTask;
    public String mPosterUrl;
    public transient int mRevision;
    public final transient a mRunningInfo;
    public int mSeen;
    public boolean mShouldAutoSpeedup;
    public String mSniffKeyword;
    public int mTaskReportType;
    public String mUrlEigenvalue;
    public long mVipAcceleratedChannelDownloadedSize;
    public long mVipAcceleratedChannelSpeed;
    public String mWebsiteName;

    public static class a {
        public boolean a;
        public int b;
        public int c;
        private long d;

        public a() {
            this.a = true;
            this.b = -1;
            this.c = -1;
        }

        public final boolean a() {
            return this.c != -1 && SystemClock.elapsedRealtime() - this.d > 5000;
        }

        public final void a(int i) {
            this.c = i;
            this.d = i == -1 ? 0 : SystemClock.elapsedRealtime();
        }
    }

    public TaskRunningInfo() {
        this.mTaskReportType = 0;
        this.mCreateOrigin = com.umeng.a.d;
        this.mPosterUrl = com.umeng.a.d;
        this.mIsManualStart = false;
        this.mSeen = 0;
        this.mConsumed = false;
        this.mSniffKeyword = com.umeng.a.d;
        this.mWebsiteName = com.umeng.a.d;
        this.mIconUrl = com.umeng.a.d;
        this.mHasSpeed = false;
        this.mHasOriginSpeed = false;
        this.mShouldAutoSpeedup = false;
        this.mDownloadRemainTime = -1;
        this.mRevision = 0;
        this.mUrlEigenvalue = com.umeng.a.d;
        this.mFreeTrialLMT = 0;
        this.mFreeTrialRemainTime = -1;
        this.mRunningInfo = new a();
        this.mExtraInfo = null;
    }

    public long getTaskId() {
        return this.mTaskId;
    }

    public String getTaskDownloadUrl() {
        return this.mTaskType == TaskType.BT ? c.b(this.mInfoHash) : this.mUrl;
    }

    public void syncExtraInfo() {
        if (this.mExtraInfo == null) {
            this.mExtraInfo = new c();
        }
        this.mExtraInfo.a = this.mTaskId;
        this.mExtraInfo.b = this.mUrl;
        this.mExtraInfo.c = this.mRefUrl;
        this.mExtraInfo.d = this.mCID;
        this.mExtraInfo.e = this.mGCID;
        this.mExtraInfo.f = this.mInfoHash;
        this.mExtraInfo.h = this.mCreateOrigin;
        this.mExtraInfo.g = this.mTaskReportType;
        this.mExtraInfo.i = this.mSeen;
        this.mExtraInfo.l = this.mSniffKeyword;
        this.mExtraInfo.m = this.mWebsiteName;
        this.mExtraInfo.n = this.mIconUrl;
        this.mExtraInfo.o = this.mDisplayName;
    }

    public void deepCopyFrom(Object obj) {
        super.deepCopyFrom(obj);
        if (obj != null && (obj instanceof TaskRunningInfo)) {
            TaskRunningInfo taskRunningInfo = (TaskRunningInfo) obj;
            this.mTaskReportType = taskRunningInfo.mTaskReportType;
            this.mCreateOrigin = taskRunningInfo.mCreateOrigin;
            this.mPosterUrl = taskRunningInfo.mPosterUrl;
            this.mIsManualStart = taskRunningInfo.mIsManualStart;
            this.mSeen = taskRunningInfo.mSeen;
            this.mAcceleratedChannelSpeed = taskRunningInfo.mAcceleratedChannelSpeed;
            this.mAcceleratedChannelDownloadedSize = taskRunningInfo.mAcceleratedChannelDownloadedSize;
            this.mVipAcceleratedChannelSpeed = taskRunningInfo.mVipAcceleratedChannelSpeed;
            this.mVipAcceleratedChannelDownloadedSize = taskRunningInfo.mVipAcceleratedChannelDownloadedSize;
            this.mHasSpeed = taskRunningInfo.mHasSpeed;
            this.mHasOriginSpeed = taskRunningInfo.mHasOriginSpeed;
            this.mShouldAutoSpeedup = taskRunningInfo.mShouldAutoSpeedup;
            this.mDownloadRemainTime = taskRunningInfo.mDownloadRemainTime;
            this.mUrlEigenvalue = taskRunningInfo.mUrlEigenvalue;
            this.mSniffKeyword = taskRunningInfo.mSniffKeyword;
            this.mConsumed = taskRunningInfo.mConsumed;
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

    public String toString() {
        return new StringBuilder("TaskRunningInfo{mTaskId='").append(this.mTaskId).append('\'').append("mTaskStatus='").append(this.mTaskStatus).append('\'').append('}').toString();
    }
}
