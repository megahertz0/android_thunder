package com.xunlei.downloadprovider.service.downloads;

public class TorrentSeedInfo {
    public int mFileIndex;
    public String mFileName;
    public int mFileNameLength;
    public String mFilePath;
    public int mFilePathLength;
    public long mFileSize;
    public String mTitleName;

    public TorrentSeedInfo(String str, String str2, String str3, long j, int i, int i2, int i3) {
        this.mTitleName = str;
        this.mFileName = str2;
        this.mFilePath = str3;
        this.mFileSize = j;
        this.mFileIndex = i;
        this.mFileNameLength = i2;
        this.mFilePathLength = i3;
    }
}
