package com.baidu.mobads.interfaces.utils;

import android.content.Context;
import java.io.File;
import java.io.InputStream;

public interface IXAdIOUtils {
    public static final int BUFFER_SIZE = 1024;
    public static final String DEFAULT_CACHE_PATH = "/bddownload/";
    public static final String DEFAULT_SD_CARD_PATH = "/mnt/sdcard";

    void copyFileFromAssetsTo(Context context, String str, String str2);

    void copyFileInputStream(InputStream inputStream, String str);

    File deleteFileRecursive(File file);

    File deleteFileRecursive(String str);

    File getExternalFilesDir(Context context);

    String getStoreagePath(Context context);

    String getStoreagePath(Context context, String str, String str2);

    boolean renameFile(String str, String str2);
}
