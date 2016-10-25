package com.xunlei.downloadprovider.vod.protocol;

import java.io.Serializable;

// compiled from: DownloadVodInfo.java
public final class a implements Serializable {
    public String a;
    public String b;
    public long c;
    public long d;
    public String e;
    public String f;
    public long g;

    public a() {
        this.c = -1;
        this.d = -1;
        this.g = 0;
    }

    public final String toString() {
        return new StringBuilder("DownloadVodInfo{mSourceUrl='").append(this.a).append('\'').append(", mPlayUrl='").append(this.b).append('\'').append(", mTaskId=").append(this.c).append(", mBtSubIndex=").append(this.d).append(", mCID='").append(this.e).append('\'').append(", mGCID='").append(this.f).append('\'').append(", mFileSize=").append(this.g).append('}').toString();
    }
}
