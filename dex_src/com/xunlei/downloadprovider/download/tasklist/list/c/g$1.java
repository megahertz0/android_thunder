package com.xunlei.downloadprovider.download.tasklist.list.c;

import com.xunlei.downloadprovider.businessutil.XLFileTypeUtil.EFileCategoryType;
import com.xunlei.downloadprovider.download.util.DownloadError.FailureCode;

// compiled from: TaskDownloadCardViewHolder.java
/* synthetic */ class g$1 {
    static final /* synthetic */ int[] a;
    static final /* synthetic */ int[] b;
    static final /* synthetic */ int[] c;
    static final /* synthetic */ int[] d;

    static {
        d = new int[g$a.a().length];
        try {
            d[g$a.a - 1] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            d[g$a.b - 1] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            d[g$a.c - 1] = 3;
        } catch (NoSuchFieldError e3) {
        }
        c = new int[g$b.a().length];
        try {
            c[g$b.a - 1] = 1;
        } catch (NoSuchFieldError e4) {
        }
        try {
            c[g$b.b - 1] = 2;
        } catch (NoSuchFieldError e5) {
        }
        try {
            c[g$b.c - 1] = 3;
        } catch (NoSuchFieldError e6) {
        }
        b = new int[EFileCategoryType.values().length];
        try {
            b[EFileCategoryType.E_VIDEO_CATEGORY.ordinal()] = 1;
        } catch (NoSuchFieldError e7) {
        }
        try {
            b[EFileCategoryType.E_SOFTWARE_CATEGORY.ordinal()] = 2;
        } catch (NoSuchFieldError e8) {
        }
        try {
            b[EFileCategoryType.E_MUSIC_CATEGORY.ordinal()] = 3;
        } catch (NoSuchFieldError e9) {
        }
        try {
            b[EFileCategoryType.E_BOOK_CATEGORY.ordinal()] = 4;
        } catch (NoSuchFieldError e10) {
        }
        try {
            b[EFileCategoryType.E_PICTURE_CATEGORY.ordinal()] = 5;
        } catch (NoSuchFieldError e11) {
        }
        try {
            b[EFileCategoryType.E_ZIP_CATEGORY.ordinal()] = 6;
        } catch (NoSuchFieldError e12) {
        }
        try {
            b[EFileCategoryType.E_TORRENT_CATEGORY.ordinal()] = 7;
        } catch (NoSuchFieldError e13) {
        }
        try {
            b[EFileCategoryType.E_OTHER_CATEGORY.ordinal()] = 8;
        } catch (NoSuchFieldError e14) {
        }
        a = new int[FailureCode.values().length];
        try {
            a[FailureCode.RESOURCE_SERVER_CONNECTION_INTERRUPTION.ordinal()] = 1;
        } catch (NoSuchFieldError e15) {
        }
        try {
            a[FailureCode.RESOURCE_SERVER_CONNECTION_FAILURE.ordinal()] = 2;
        } catch (NoSuchFieldError e16) {
        }
        try {
            a[FailureCode.EMULE_LINK_PARSE_FAILURE.ordinal()] = 3;
        } catch (NoSuchFieldError e17) {
        }
        try {
            a[FailureCode.TASK_LINK_FAILURE.ordinal()] = 4;
        } catch (NoSuchFieldError e18) {
        }
        try {
            a[FailureCode.DOWNLOAD_INFORMATION_UPDATE_FAILURE.ordinal()] = 5;
        } catch (NoSuchFieldError e19) {
        }
        try {
            a[FailureCode.MAGNET_LINK_PARSE_FAILURE.ordinal()] = 6;
        } catch (NoSuchFieldError e20) {
        }
        try {
            a[FailureCode.PATH_CANNOT_BE_WRITTEN.ordinal()] = 7;
        } catch (NoSuchFieldError e21) {
        }
        try {
            a[FailureCode.CONTINUINGLY_TASK_FAILURE.ordinal()] = 8;
        } catch (NoSuchFieldError e22) {
        }
        try {
            a[FailureCode.SENSITIVE_RESOURCE_DOWNLOAD_LIMITED.ordinal()] = 9;
        } catch (NoSuchFieldError e23) {
        }
        try {
            a[FailureCode.INSUFFICIENT_SPACE.ordinal()] = 10;
        } catch (NoSuchFieldError e24) {
        }
        try {
            a[FailureCode.TORRENT_NOT_EXIST.ordinal()] = 11;
        } catch (NoSuchFieldError e25) {
        }
        try {
            a[FailureCode.TORRENT_INVALID.ordinal()] = 12;
        } catch (NoSuchFieldError e26) {
        }
        try {
            a[FailureCode.BT_PART_SUBTASK_DOWNLOAD_FAILURE.ordinal()] = 13;
        } catch (NoSuchFieldError e27) {
        }
        try {
            a[FailureCode.BT_ALL_SUBTASK_DOWNLOAD_FAILURE.ordinal()] = 14;
        } catch (NoSuchFieldError e28) {
        }
        try {
            a[FailureCode.BT_SUBFILE_DOWNLOAD_FAILURE.ordinal()] = 15;
        } catch (NoSuchFieldError e29) {
        }
        try {
            a[FailureCode.BT_TASK_DOWNLOAD_FAILURE.ordinal()] = 16;
        } catch (NoSuchFieldError e30) {
        }
        try {
            a[FailureCode.BT_FILE_PARSE_FAILURE.ordinal()] = 17;
        } catch (NoSuchFieldError e31) {
        }
        try {
            a[FailureCode.FILE_ERROR.ordinal()] = 18;
        } catch (NoSuchFieldError e32) {
        }
        try {
            a[FailureCode.FILE_NOT_EXIST.ordinal()] = 19;
        } catch (NoSuchFieldError e33) {
        }
        try {
            a[FailureCode.GET_RESOURCE_NAME_FAILURE.ordinal()] = 20;
        } catch (NoSuchFieldError e34) {
        }
        try {
            a[FailureCode.TASK_PARAMETER_ERROR.ordinal()] = 21;
        } catch (NoSuchFieldError e35) {
        }
        try {
            a[FailureCode.TASK_COUNT_MORE_THAN_UPPER_LIMIT.ordinal()] = 22;
        } catch (NoSuchFieldError e36) {
        }
        try {
            a[FailureCode.TASK_ALREADY_EXISTS.ordinal()] = 23;
        } catch (NoSuchFieldError e37) {
        }
        try {
            a[FailureCode.TASK_DELETED.ordinal()] = 24;
        } catch (NoSuchFieldError e38) {
        }
    }
}
