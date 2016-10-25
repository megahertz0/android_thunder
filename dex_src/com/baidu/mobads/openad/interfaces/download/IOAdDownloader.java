package com.baidu.mobads.openad.interfaces.download;

import java.util.Observer;

public interface IOAdDownloader {

    public enum DownloadStatus {
        NONE(-1, "\u672a\u5f00\u59cb"),
        INITING(0, "\u4e0b\u8f7d\u51c6\u5907\u4e2d"),
        DOWNLOADING(1, "\u6b63\u5728\u4e0b\u8f7d"),
        CANCELLED(2, "\u5df2\u53d6\u6d88\u4e0b\u8f7d"),
        COMPLETED(3, "\u4e0b\u8f7d\u5b8c\u6210"),
        ERROR(4, "\u4e0b\u8f7d\u5931\u8d25"),
        COMPLETE_BUT_FILE_REMOVED(5, "\u4e0b\u8f7d\u5b8c\u4f46\u6587\u4ef6\u5f02\u5e38"),
        PAUSED(6, "\u5df2\u6682\u505c\u4e0b\u8f7d");
        private int a;
        private String b;

        static {
            String str = "\u672a\u5f00\u59cb";
            NONE = new com.baidu.mobads.openad.interfaces.download.IOAdDownloader.DownloadStatus("NONE", 0, -1, "\u672a\u5f00\u59cb");
            String str2 = "\u4e0b\u8f7d\u51c6\u5907\u4e2d";
            INITING = new com.baidu.mobads.openad.interfaces.download.IOAdDownloader.DownloadStatus("INITING", 1, 0, "\u4e0b\u8f7d\u51c6\u5907\u4e2d");
            str2 = "\u6b63\u5728\u4e0b\u8f7d";
            DOWNLOADING = new com.baidu.mobads.openad.interfaces.download.IOAdDownloader.DownloadStatus("DOWNLOADING", 2, 1, "\u6b63\u5728\u4e0b\u8f7d");
            str2 = "\u5df2\u53d6\u6d88\u4e0b\u8f7d";
            CANCELLED = new com.baidu.mobads.openad.interfaces.download.IOAdDownloader.DownloadStatus("CANCELLED", 3, 2, "\u5df2\u53d6\u6d88\u4e0b\u8f7d");
            str2 = "\u4e0b\u8f7d\u5b8c\u6210";
            COMPLETED = new com.baidu.mobads.openad.interfaces.download.IOAdDownloader.DownloadStatus("COMPLETED", 4, 3, "\u4e0b\u8f7d\u5b8c\u6210");
            str = "\u4e0b\u8f7d\u5931\u8d25";
            ERROR = new com.baidu.mobads.openad.interfaces.download.IOAdDownloader.DownloadStatus("ERROR", 5, 4, "\u4e0b\u8f7d\u5931\u8d25");
            String str3 = "\u4e0b\u8f7d\u5b8c\u4f46\u6587\u4ef6\u5f02\u5e38";
            COMPLETE_BUT_FILE_REMOVED = new com.baidu.mobads.openad.interfaces.download.IOAdDownloader.DownloadStatus("COMPLETE_BUT_FILE_REMOVED", 6, 5, "\u4e0b\u8f7d\u5b8c\u4f46\u6587\u4ef6\u5f02\u5e38");
            str3 = "\u5df2\u6682\u505c\u4e0b\u8f7d";
            PAUSED = new com.baidu.mobads.openad.interfaces.download.IOAdDownloader.DownloadStatus("PAUSED", 7, 6, "\u5df2\u6682\u505c\u4e0b\u8f7d");
            c = new com.baidu.mobads.openad.interfaces.download.IOAdDownloader.DownloadStatus[]{NONE, INITING, DOWNLOADING, CANCELLED, COMPLETED, ERROR, COMPLETE_BUT_FILE_REMOVED, PAUSED};
        }

        private DownloadStatus(int i, String str) {
            this.a = i;
            this.b = str;
        }

        public final int getCode() {
            return this.a;
        }

        public final String getMessage() {
            return this.b;
        }
    }

    void addObserver(Observer observer);

    void cancel();

    int getFileSize();

    String getOutputPath();

    String getPackageName();

    float getProgress();

    DownloadStatus getState();

    String getTargetURL();

    String getTitle();

    String getURL();

    void pause();

    void removeObservers();

    void resume();

    void start();
}
