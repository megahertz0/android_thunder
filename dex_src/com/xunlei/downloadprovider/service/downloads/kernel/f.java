package com.xunlei.downloadprovider.service.downloads.kernel;

import android.net.Uri;
import android.text.TextUtils;
import com.xunlei.common.encrypt.CharsetConvert;
import com.xunlei.xiazaibao.BuildConfig;
import com.xunlei.xllib.b.k;
import java.io.File;

// compiled from: DownloadLocalFileServer.java
public final class f {
    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return BuildConfig.VERSION_NAME;
        }
        try {
            String path = Uri.parse(str).getPath();
            return !TextUtils.isEmpty(path) ? new File(k.b(path, CharsetConvert.UTF_8)).getPath() : path;
        } catch (Exception e) {
            e.printStackTrace();
            return BuildConfig.VERSION_NAME;
        }
    }
}
