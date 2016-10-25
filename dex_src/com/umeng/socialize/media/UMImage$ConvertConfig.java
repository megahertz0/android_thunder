package com.umeng.socialize.media;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import com.umeng.socialize.utils.BitmapUtils;
import com.umeng.socialize.utils.DeviceConfig;
import com.xunlei.xiazaibao.BuildConfig;
import java.io.File;
import java.io.IOException;

class UMImage$ConvertConfig {
    private static final String a = "/umeng_cache/";
    private String b;
    public float mImageSizeLimit;

    public UMImage$ConvertConfig(Context context) {
        this.mImageSizeLimit = 2048.0f;
        this.b = BuildConfig.VERSION_NAME;
        try {
            this.b = context.getCacheDir().getCanonicalPath();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public UMImage$ConvertConfig() {
        this.mImageSizeLimit = 2048.0f;
        this.b = BuildConfig.VERSION_NAME;
    }

    public File getCache() throws IOException {
        String canonicalPath;
        if (DeviceConfig.isSdCardWrittenable()) {
            canonicalPath = Environment.getExternalStorageDirectory().getCanonicalPath();
        } else if (TextUtils.isEmpty(this.b)) {
            throw new IOException("dirpath is unknow");
        } else {
            canonicalPath = this.b;
        }
        File file = new File(canonicalPath + a);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public File generateCacheFile(String str) throws IOException {
        BitmapUtils.cleanCache();
        File file = new File(getCache(), str);
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        return file;
    }
}
