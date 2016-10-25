package com.xunlei.downloadprovider.download.tasklist.a;

import com.xunlei.download.DownloadManager.TaskType;

// compiled from: TaskListManager.java
/* synthetic */ class h$1 {
    static final /* synthetic */ int[] a;

    static {
        a = new int[TaskType.values().length];
        try {
            a[TaskType.MAGNET.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            a[TaskType.BT.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            a[TaskType.ED2K.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            a[TaskType.CID.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
        try {
            a[TaskType.UNKOWN.ordinal()] = 5;
        } catch (NoSuchFieldError e5) {
        }
        try {
            a[TaskType.HTTP.ordinal()] = 6;
        } catch (NoSuchFieldError e6) {
        }
        try {
            a[TaskType.FTP.ordinal()] = 7;
        } catch (NoSuchFieldError e7) {
        }
    }
}
