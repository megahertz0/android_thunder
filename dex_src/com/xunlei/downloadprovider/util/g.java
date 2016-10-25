package com.xunlei.downloadprovider.util;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.xunlei.download.proguard.c;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.xiazaibao.BuildConfig;
import com.xunlei.xllib.R;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Hashtable;
import org.eclipse.paho.client.mqttv3.MqttTopic;

// compiled from: FileHandler.java
public final class g {
    private static g a;
    private Hashtable<String, String> b;

    static {
        a = null;
    }

    public static g a() {
        if (a == null) {
            a = new g();
        }
        return a;
    }

    public static boolean a(String str) {
        if (str != null) {
            return str.endsWith(".apk") || str.endsWith(".APK");
        } else {
            return false;
        }
    }

    @SuppressLint({"DefaultLocale"})
    public static boolean b(String str) {
        return str != null ? str.toLowerCase().endsWith(".torrent") : false;
    }

    @SuppressLint({"DefaultLocale"})
    public final void a(String str, Context context) {
        if (str == null) {
            throw new IllegalArgumentException("\u6587\u4ef6\u8def\u5f84\u4e3a\u7a7a");
        }
        File file = new File(str);
        if (file.exists() && file.isFile()) {
            int lastIndexOf = str.lastIndexOf(R.styleable.AppCompatTheme_dropdownListPreferredItemHeight);
            if (-1 == lastIndexOf) {
                throw new IllegalArgumentException("\u627e\u4e0d\u5230\u6587\u4ef6\u540e\u7f00\u540d");
            }
            String str2 = (String) this.b.get(str.substring(lastIndexOf).toLowerCase());
            if (str2 == null) {
                throw new ActivityNotFoundException("\u627e\u4e0d\u5230\u7a0b\u5e8f\u6253\u5f00\u8be5\u6587\u4ef6");
            }
            Intent intent = new Intent("android.intent.action.VIEW");
            if (!str.startsWith("http://")) {
                str = new StringBuilder("file://").append(str).toString();
            }
            try {
                int lastIndexOf2 = str.lastIndexOf(R.styleable.AppCompatTheme_spinnerDropDownItemStyle);
                String substring = str.substring(0, lastIndexOf2 + 1);
                String str3 = substring + URLEncoder.encode(str.substring(lastIndexOf2 + 1, str.length()), "utf-8").replace(MqttTopic.SINGLE_LEVEL_WILDCARD, "%20");
                if (context.getPackageManager().resolveActivity(intent, 0) != null) {
                    intent.setDataAndType(Uri.parse(str3), str2);
                    intent.setFlags(335544320);
                    context.startActivity(intent);
                    return;
                }
                XLToast.a(context, XLToastType.XLTOAST_TYPE_ALARM, "\u627e\u4e0d\u5230\u7a0b\u5e8f\u6253\u5f00\u8be5\u6587\u4ef6");
                return;
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("\u6587\u4ef6\u540d\u7f16\u7801\u9519\u8bef");
            }
        }
        throw new IllegalArgumentException("\u627e\u4e0d\u5230\u6587\u4ef6");
    }

    private g() {
        this.b = null;
        this.b = new Hashtable();
        this.b.put(".xv", "video/*");
        this.b.put(".3gp", "video/3gpp");
        this.b.put(".amr", "audio/amr");
        this.b.put(".apk", "application/vnd.android.package-archive");
        this.b.put(".asf", "video/x-ms-asf");
        this.b.put(".avi", "video/x-msvideo");
        this.b.put(".bmp", "image/bmp");
        this.b.put(".c", "text/plain");
        this.b.put(".class", "application/octet-stream");
        this.b.put(".conf", "text/plain");
        this.b.put(".cpp", "text/plain");
        this.b.put(".doc", "application/msword");
        this.b.put(".docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        this.b.put(".gif", "image/gif");
        this.b.put(".gtar", "application/x-gtar");
        this.b.put(".gz", "application/x-gzip");
        this.b.put(".h", "text/plain");
        this.b.put(".htm", "text/html");
        this.b.put(c.m, "text/html");
        this.b.put(".jar", "application/java-archive");
        this.b.put(".java", "text/plain");
        this.b.put(".jpeg", "image/jpeg");
        this.b.put(".jpg", "image/jpeg");
        this.b.put(".js", "application/x-javascript");
        this.b.put(".log", "text/plain");
        this.b.put(".m3u", "audio/x-mpegurl");
        this.b.put(".m4a", "audio/mp4a-latm");
        this.b.put(".m4b", "audio/mp4a-latm");
        this.b.put(".m4p", "audio/mp4a-latm");
        this.b.put(".m4u", "video/vnd.mpegurl");
        this.b.put(".m4v", "video/x-m4v");
        this.b.put(".mov", "video/quicktime");
        this.b.put(".mp2", "audio/x-mpeg");
        this.b.put(".mp3", "audio/x-mpeg");
        this.b.put(".mp4", "video/mp4");
        this.b.put(".mpc", "application/vnd.mpohun.certificate");
        this.b.put(".mpe", "video/mpeg");
        this.b.put(".mpeg", "video/mpeg");
        this.b.put(".mpg", "video/mpeg");
        this.b.put(".mpg4", "video/mp4");
        this.b.put(".mpga", "audio/mpeg");
        this.b.put(".msg", "application/vnd.ms-outlook");
        this.b.put(".ogg", "audio/ogg");
        this.b.put(".pdf", "application/pdf");
        this.b.put(".png", "image/png");
        this.b.put(".pps", "application/vnd.ms-powerpoint");
        this.b.put(".ppt", "application/vnd.ms-powerpoint");
        this.b.put(".pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation");
        this.b.put(".prop", "text/plain");
        this.b.put(".rar", "application/x-rar-compressed");
        this.b.put(".rc", "text/plain");
        this.b.put(".rmvb", "video/x-pn-realaudio");
        this.b.put(".rtf", "application/rtf");
        this.b.put(".sh", "text/plain");
        this.b.put(".tar", "application/x-tar");
        this.b.put(".tgz", "application/x-compressed");
        this.b.put(c.n, "text/plain");
        this.b.put(".wav", "audio/x-wav");
        this.b.put(".wma", "audio/x-ms-wma");
        this.b.put(".wmv", "video/x-ms-wmv");
        this.b.put(".wps", "application/vnd.ms-works");
        this.b.put(".xml", "text/plain");
        this.b.put(".xls", "application/vnd.ms-excel");
        this.b.put(".xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        this.b.put(".z", "application/x-compress");
        this.b.put(".zip", "application/zip");
        this.b.put(".torrent", "application/x-bittorrent");
        this.b.put(".flv", "flv-application/octet-stream");
        this.b.put(".rm", "video/x-pn-realaudio");
        this.b.put(BuildConfig.VERSION_NAME, "*/*");
        this.b.put(".icon", "image/icon");
        this.b.put(".jpe", "image/jpe");
        this.b.put(".asx", "video/*");
        this.b.put(".dat", "video/*");
        this.b.put(".mkv", "video/*");
        this.b.put(".f4v", "video/*");
        this.b.put(".vob", "video/*");
        this.b.put(".ts", "video/*");
        this.b.put(".midi", "audio/*");
        this.b.put(".cda", "audio/*");
        this.b.put(".mp3pro", "audio/*");
        this.b.put(".sacd", "audio/*");
        this.b.put(".vqf", "audio/*");
        this.b.put(".ra", "audio/*");
        this.b.put(".rmx", "audio/*");
        this.b.put(".voc", "audio/*");
        this.b.put(".au", "audio/*");
        this.b.put(".aif", "audio/*");
        this.b.put(".snd", "audio/*");
        this.b.put(".aac", "audio/*");
    }
}
