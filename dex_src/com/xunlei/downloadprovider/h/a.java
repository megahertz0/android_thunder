package com.xunlei.downloadprovider.h;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import com.xunlei.download.proguard.c;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.xiazaibao.BuildConfig;
import com.xunlei.xllib.R;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import org.eclipse.paho.client.mqttv3.MqttTopic;

// compiled from: LocalAppManager.java
public final class a {
    private static a c;
    private Context a;
    private Hashtable<String, String> b;
    private final String d;

    private a(Context context) {
        this.b = null;
        this.d = "ThunderDownload/";
        this.a = context;
        this.b = new Hashtable();
        this.b.put(".apk", "application/vnd.android.package-archive");
        this.b.put(".bmp", "image/bmp");
        this.b.put(".gif", "image/gif");
        this.b.put(".jpeg", "image/jpeg");
        this.b.put(".jpg", "image/jpeg");
        this.b.put(".png", "image/png");
        this.b.put(".icon", "image/icon");
        this.b.put(".jpe", "image/jpe");
        this.b.put(".amr", "audio/amr");
        this.b.put(".mp2", "audio/x-mpeg");
        this.b.put(".mp3", "audio/x-mpeg");
        this.b.put(".wav", "audio/x-wav");
        this.b.put(".wma", "audio/x-ms-wma");
        this.b.put(".wmv", "video/x-ms-wmv");
        this.b.put(".m3u", "audio/x-mpegurl");
        this.b.put(".m4a", "audio/mp4a-latm");
        this.b.put(".m4b", "audio/mp4a-latm");
        this.b.put(".m4p", "audio/mp4a-latm");
        this.b.put(".ogg", "audio/ogg");
        this.b.put(".midi", "audio/midi");
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
        this.b.put(".aac", "audio/aac");
        this.b.put(".flac", "audio/flac");
        this.b.put(".xmf", "audio/midi");
        this.b.put(".3gp", "video/3gpp");
        this.b.put(".xv", "video/*");
        this.b.put(".asf", "video/x-ms-asf");
        this.b.put(".avi", "video/x-msvideo");
        this.b.put(".m4u", "video/vnd.mpegurl");
        this.b.put(".m4v", "video/x-m4v");
        this.b.put(".mov", "video/quicktime");
        this.b.put(".mp4", "video/mp4");
        this.b.put(".mpc", "application/vnd.mpohun.certificate");
        this.b.put(".mpe", "video/mpeg");
        this.b.put(".mpeg", "video/mpeg");
        this.b.put(".mpg", "video/mpeg");
        this.b.put(".mpg4", "video/mp4");
        this.b.put(".mpga", "audio/mpeg");
        this.b.put(".msg", "application/vnd.ms-outlook");
        this.b.put(".flv", "video/x-flv");
        this.b.put(".rm", "video/x-pn-realaudio");
        this.b.put(".asx", "video/*");
        this.b.put(".dat", "video/*");
        this.b.put(".mkv", "video/*");
        this.b.put(".f4v", "video/*");
        this.b.put(".vob", "video/*");
        this.b.put(".ts", "video/*");
        this.b.put(".rmvb", "video/x-pn-realaudio");
        this.b.put(".prop", "text/plain");
        this.b.put(".rc", "text/plain");
        this.b.put(".log", "text/plain");
        this.b.put(".xhtm", "text/plain");
        this.b.put(".epub", "text/plain");
        this.b.put(".umd", "text/plain");
        this.b.put(".xml", "text/plain");
        this.b.put(c.n, "text/plain");
        this.b.put(".sh", "text/plain");
        this.b.put(".java", "text/plain");
        this.b.put(".xtm", "text/html");
        this.b.put(".xthm", "text/html");
        this.b.put(".chm", "text/plain");
        this.b.put(".cbz", "text/plain");
        this.b.put(".h", "text/plain");
        this.b.put(".htm", "text/html");
        this.b.put(c.m, "text/html");
        this.b.put(".conf", "text/plain");
        this.b.put(".cpp", "text/plain");
        this.b.put(".c", "text/plain");
        this.b.put(".rar", "application/x-rar-compressed");
        this.b.put(".tar", "application/x-tar");
        this.b.put(".zip", "application/zip");
        this.b.put(".cab", "application/zip");
        this.b.put(".lzma", "application/zip");
        this.b.put(".arj", "application/zip");
        this.b.put(".iso", "application/zip");
        this.b.put(".xar", "application/zip");
        this.b.put(".gzip", "application/zip");
        this.b.put(".bzip2", "application/zip");
        this.b.put(".gtar", "application/x-gtar");
        this.b.put(".gz", "application/x-gzip");
        this.b.put(".jar", "application/java-archive");
        this.b.put(".class", "application/octet-stream");
        this.b.put(".doc", "application/msword");
        this.b.put(".docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        this.b.put(".pdf", "application/pdf");
        this.b.put(".pps", "application/vnd.ms-powerpoint");
        this.b.put(".ppt", "application/vnd.ms-powerpoint");
        this.b.put(".pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation");
        this.b.put(".rtf", "application/rtf");
        this.b.put(".tgz", "application/x-compressed");
        this.b.put(".wps", "application/vnd.ms-works");
        this.b.put(".xls", "application/vnd.ms-excel");
        this.b.put(".xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        this.b.put(".z", "application/x-compress");
        this.b.put(".torrent", "application/x-bittorrent");
        this.b.put(".js", "application/x-javascript");
        this.b.put(BuildConfig.VERSION_NAME, "*/*");
    }

    public static a a(Context context) {
        if (c == null) {
            c = new a(context);
        }
        return c;
    }

    public final Intent a(String str, ResolveInfo resolveInfo) {
        int lastIndexOf = str.lastIndexOf(R.styleable.AppCompatTheme_dropdownListPreferredItemHeight);
        if (-1 == lastIndexOf) {
            return null;
        }
        String toLowerCase = str.substring(lastIndexOf).toLowerCase(Locale.getDefault());
        Intent intent = new Intent("android.intent.action.VIEW");
        if (!str.startsWith("http://")) {
            str = new StringBuilder("file://").append(str).toString();
        }
        try {
            String substring;
            String str2;
            lastIndexOf = str.lastIndexOf("ThunderDownload/");
            int lastIndexOf2 = str.lastIndexOf(R.styleable.AppCompatTheme_spinnerDropDownItemStyle);
            if (lastIndexOf <= 0 || lastIndexOf2 <= 0 || lastIndexOf2 <= lastIndexOf + 16) {
                substring = str.substring(0, lastIndexOf2 + 1);
                str2 = substring + URLEncoder.encode(str.substring(lastIndexOf2 + 1, str.length()), "utf-8").replace(MqttTopic.SINGLE_LEVEL_WILDCARD, "%20");
            } else {
                String str3 = str.substring(0, lastIndexOf) + "ThunderDownload/";
                substring = URLEncoder.encode(str.substring(lastIndexOf + 16, lastIndexOf2), "utf-8").replace(MqttTopic.SINGLE_LEVEL_WILDCARD, "%20");
                str2 = str3 + substring + MqttTopic.TOPIC_LEVEL_SEPARATOR + URLEncoder.encode(str.substring(lastIndexOf2 + 1, str.length()), "utf-8").replace(MqttTopic.SINGLE_LEVEL_WILDCARD, "%20");
            }
            substring = (String) this.b.get(toLowerCase);
            if (substring == null) {
                return null;
            }
            intent.setDataAndType(Uri.parse(str2), substring);
            if (resolveInfo != null) {
                intent.setComponent(new ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name));
            }
            return intent;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("\u6587\u4ef6\u540d\u7f16\u7801\u9519\u8bef");
        }
    }

    public final List<ResolveInfo> a(String str) {
        if (str == null) {
            return null;
        }
        File file = new File(str);
        if (file.exists() && file.isFile()) {
            int lastIndexOf = str.lastIndexOf(R.styleable.AppCompatTheme_dropdownListPreferredItemHeight);
            if (-1 == lastIndexOf) {
                return null;
            }
            String toLowerCase = str.substring(lastIndexOf).toLowerCase(Locale.getDefault());
            Intent intent = new Intent("android.intent.action.VIEW");
            if (!str.startsWith("http://")) {
                str = new StringBuilder("file://").append(str).toString();
            }
            try {
                int lastIndexOf2 = str.lastIndexOf(R.styleable.AppCompatTheme_spinnerDropDownItemStyle);
                String str2 = str.substring(0, lastIndexOf2 + 1) + URLEncoder.encode(str.substring(lastIndexOf2 + 1, str.length()), "utf-8").replace(MqttTopic.SINGLE_LEVEL_WILDCARD, "%20");
                toLowerCase = (String) this.b.get(toLowerCase);
                if (toLowerCase == null) {
                    return null;
                }
                Context applicationContext = BrothersApplication.a.getApplicationContext();
                if (applicationContext.getPackageManager().resolveActivity(intent, 0) == null) {
                    return null;
                }
                intent.setDataAndType(Uri.parse(str2), toLowerCase);
                intent.setFlags(67108864);
                PackageManager packageManager = applicationContext.getPackageManager();
                List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, R.styleable.AppCompatTheme_imageButtonStyle);
                toLowerCase = "null";
                if (str2 != null) {
                    int lastIndexOf3 = str2.lastIndexOf(".");
                    if (lastIndexOf3 != -1) {
                        toLowerCase = str2.substring(lastIndexOf3 + 1, str2.length());
                    }
                }
                toLowerCase = toLowerCase.toLowerCase(Locale.getDefault()).trim();
                List<ResolveInfo> arrayList = new ArrayList();
                str2 = this.a.getSharedPreferences("default_app_setting", 0).getString(toLowerCase, "null");
                for (ResolveInfo resolveInfo : queryIntentActivities) {
                    if (resolveInfo.activityInfo == null || (resolveInfo.activityInfo.exported && resolveInfo.activityInfo.enabled)) {
                        String toString = resolveInfo.loadLabel(packageManager).toString();
                        if (str2.equals("null") || !toString.equals(str2)) {
                            arrayList.add(resolveInfo);
                        } else {
                            arrayList.clear();
                            arrayList.add(resolveInfo);
                            return arrayList;
                        }
                    }
                }
                return arrayList.size() == 0 ? null : arrayList;
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("\u6587\u4ef6\u540d\u7f16\u7801\u9519\u8bef");
            }
        }
        throw new IllegalArgumentException("\u627e\u4e0d\u5230\u6587\u4ef6");
    }
}
