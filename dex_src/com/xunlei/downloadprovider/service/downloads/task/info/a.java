package com.xunlei.downloadprovider.service.downloads.task.info;

import java.io.Serializable;

// compiled from: DownloadInfo.java
public final class a implements Serializable {
    public String a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public long g;
    public String h;
    public String i;
    public a j;

    // compiled from: DownloadInfo.java
    public static class a implements Serializable {
        public boolean a;
        public int b;
        public int c;
        public String d;
        public String e;
        public boolean f;
        public String g;
        public String h;

        public a() {
            this.a = false;
            this.b = 0;
            this.c = 0;
        }

        public final String toString() {
            return new StringBuilder("Extra{mIsManualStart=").append(this.a).append(", mReportValue=").append(this.b).append(", mNeedNotificationFlag=").append(this.c).append(", mDisplayName='").append(this.d).append('\'').append('}').toString();
        }
    }

    public final a a() {
        if (this.j == null) {
            this.j = new a();
        }
        return this.j;
    }

    public a(String str, String str2, long j, String str3, String str4) {
        this.e = str;
        this.f = str2;
        this.g = j;
        this.a = str3;
        this.h = str4;
    }

    public a(String str, String str2, String str3, String str4, String str5) {
        this.a = str2;
        this.b = str;
        this.c = str3;
        this.d = str4;
        this.h = str5;
    }

    public final String toString() {
        return new StringBuilder("DownloadInfo{mFileName='").append(this.a).append('\'').append(", mDownloadUrl='").append(this.b).append('\'').append(", mRefUrl='").append(this.c).append('\'').append(", mCookie='").append(this.d).append('\'').append(", mCID='").append(this.e).append('\'').append(", mGCID='").append(this.f).append('\'').append(", mFileSize=").append(this.g).append(", mCreateOrigin='").append(this.h).append('\'').append(", mDownloadPath='").append(this.i).append('\'').append(", mExtra=").append(this.j).append('}').toString();
    }
}
