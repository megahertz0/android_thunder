package com.xunlei.downloadprovider.filemanager.model;

import com.xunlei.downloadprovider.businessutil.XLFileTypeUtil.EFileCategoryType;

// compiled from: FileManagerUtil.java
/* synthetic */ class b$1 {
    static final /* synthetic */ int[] a;

    static {
        a = new int[EFileCategoryType.values().length];
        try {
            a[EFileCategoryType.E_VIDEO_CATEGORY.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            a[EFileCategoryType.E_MUSIC_CATEGORY.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            a[EFileCategoryType.E_PICTURE_CATEGORY.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
    }
}
