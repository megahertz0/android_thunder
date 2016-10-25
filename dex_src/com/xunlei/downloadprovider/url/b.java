package com.xunlei.downloadprovider.url;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import com.xunlei.downloadprovider.util.g;
import com.xunlei.tdlive.R;

// compiled from: UrlUtil.java
public abstract class b {
    @SuppressLint({"DefaultLocale"})
    public static boolean a(String str) {
        if (str == null) {
            return false;
        }
        String toLowerCase = str.toLowerCase();
        if (toLowerCase.endsWith(".rmvb") || toLowerCase.endsWith(".mkv") || toLowerCase.endsWith(".rm") || toLowerCase.endsWith(".avi") || toLowerCase.endsWith(".mp4") || toLowerCase.endsWith(".3gp") || toLowerCase.endsWith(".flv") || toLowerCase.endsWith(".wmv") || toLowerCase.endsWith(".mpg") || toLowerCase.endsWith(".swf") || toLowerCase.endsWith(".xv")) {
            return true;
        }
        int indexOf = toLowerCase.indexOf("?");
        if (indexOf == -1 || indexOf == 0) {
            return false;
        }
        toLowerCase = toLowerCase.substring(0, indexOf);
        return toLowerCase.endsWith(".rmvb") || toLowerCase.endsWith(".mkv") || toLowerCase.endsWith(".rm") || toLowerCase.endsWith(".avi") || toLowerCase.endsWith(".mp4") || toLowerCase.endsWith(".3gp") || toLowerCase.endsWith(".flv") || toLowerCase.endsWith(".wmv") || toLowerCase.endsWith(".mpg") || toLowerCase.endsWith(".swf") || toLowerCase.endsWith(".xv");
    }

    @SuppressLint({"DefaultLocale"})
    public static boolean b(String str) {
        if (str == null) {
            return false;
        }
        String toLowerCase = str.toLowerCase();
        if (toLowerCase.endsWith(".mp3") || toLowerCase.endsWith(".wma")) {
            return true;
        }
        int indexOf = toLowerCase.indexOf("?");
        if (indexOf == -1 || indexOf == 0) {
            return false;
        }
        toLowerCase = toLowerCase.substring(0, indexOf);
        return toLowerCase.endsWith(".mp3") || toLowerCase.endsWith(".wma");
    }

    public static String c(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (str.startsWith("magnet")) {
            return str;
        }
        return str.startsWith("http://magnet") ? str.substring(R.styleable.Toolbar_contentInsetLeft) : null;
    }

    public static boolean d(String str) {
        if (str == null) {
            return false;
        }
        String toLowerCase = str.toLowerCase();
        if (toLowerCase.endsWith(".exe") || toLowerCase.endsWith(".rar")) {
            return true;
        }
        int indexOf = toLowerCase.indexOf("?");
        if (indexOf == -1 || indexOf == 0) {
            return false;
        }
        toLowerCase = toLowerCase.substring(0, indexOf);
        return toLowerCase.endsWith(".exe") || toLowerCase.endsWith(".rar");
    }

    public static boolean e(String str) {
        if (str == null) {
            return false;
        }
        String toLowerCase = str.toLowerCase();
        if (g.a(toLowerCase)) {
            return true;
        }
        int indexOf = toLowerCase.indexOf("?");
        return (indexOf == -1 || indexOf == 0 || !toLowerCase.substring(0, indexOf).endsWith(".apk")) ? false : true;
    }

    public static boolean f(String str) {
        if (str == null) {
            return false;
        }
        String toLowerCase = str.toLowerCase();
        if (toLowerCase.endsWith(".7z") || toLowerCase.endsWith(".rar") || toLowerCase.endsWith(".zip") || toLowerCase.endsWith(".gz") || toLowerCase.endsWith(".cab") || toLowerCase.endsWith(".tar") || toLowerCase.endsWith(".gzip")) {
            return true;
        }
        int indexOf = toLowerCase.indexOf("?");
        if (indexOf == -1 || indexOf == 0) {
            return false;
        }
        toLowerCase = toLowerCase.substring(0, indexOf);
        return toLowerCase.endsWith(".7z") || toLowerCase.endsWith(".rar") || toLowerCase.endsWith(".zip") || toLowerCase.endsWith(".gz") || toLowerCase.endsWith(".cab") || toLowerCase.endsWith(".tar") || toLowerCase.endsWith(".gzip");
    }

    public static boolean g(String str) {
        return str != null && g.b(str);
    }
}
