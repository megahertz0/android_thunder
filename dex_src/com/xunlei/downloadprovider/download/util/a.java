package com.xunlei.downloadprovider.download.util;

import android.content.ClipData;
import android.content.ClipData.Item;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.alipay.sdk.util.h;
import com.sina.weibo.sdk.component.GameManager;
import com.xunlei.download.DownloadManager.TaskType;
import com.xunlei.downloadprovider.businessutil.XLFileTypeUtil;
import com.xunlei.downloadprovider.businessutil.XLFileTypeUtil.EFileCategoryType;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.service.downloads.TaskInfo;
import com.xunlei.downloadprovider.service.downloads.b.c;
import com.xunlei.downloadprovider.service.downloads.task.info.TaskRunningInfo;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import com.xunlei.xllib.b.e;
import com.xunlei.xllib.b.g;
import com.xunlei.xllib.b.k;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

// compiled from: DownloadCenterTaskUtil.java
public class a {
    private static final String a;

    static {
        a = a.class.getSimpleName();
    }

    public static void a(TaskRunningInfo taskRunningInfo, Context context) {
        if (taskRunningInfo != null) {
            if (VERSION.SDK_INT >= 11) {
                ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService("clipboard");
                clipboardManager.addPrimaryClipChangedListener(new b(clipboardManager, context));
                Object taskDownloadUrl = taskRunningInfo.getTaskDownloadUrl();
                ClipData newPlainText = ClipData.newPlainText("thunder", taskDownloadUrl);
                if (n.a(taskRunningInfo)) {
                    try {
                        StringBuilder stringBuilder = new StringBuilder();
                        String str = taskRunningInfo.mFileName;
                        if (!str.contains(".")) {
                            str = str + ".mp4";
                        }
                        stringBuilder.append("thundertask://|file|").append(k.a(str, GameManager.DEFAULT_CHARSET));
                        stringBuilder.append("|size|").append(taskRunningInfo.mFileSize);
                        stringBuilder.append("|url|").append(k.a(taskDownloadUrl, GameManager.DEFAULT_CHARSET));
                        newPlainText.addItem(new Item(stringBuilder.toString()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                clipboardManager.setPrimaryClip(newPlainText);
                return;
            }
            ((android.text.ClipboardManager) context.getSystemService("clipboard")).setText(taskRunningInfo.getTaskDownloadUrl());
            XLToast.a(context, XLToastType.XLTOAST_TYPE_SUC, context.getString(2131231121));
        }
    }

    public static List<String> a(Context context) {
        Object arrayList = new ArrayList();
        CharSequence charSequence;
        if (VERSION.SDK_INT >= 11) {
            try {
                ClipData primaryClip = ((ClipboardManager) context.getSystemService("clipboard")).getPrimaryClip();
                if (primaryClip != null && primaryClip.getItemCount() > 0) {
                    for (int i = 0; i < primaryClip.getItemCount(); i++) {
                        charSequence = null;
                        Item itemAt = primaryClip.getItemAt(i);
                        if (itemAt.getText() != null) {
                            charSequence = itemAt.getText().toString();
                        } else if (itemAt.getUri() != null) {
                            charSequence = itemAt.getUri().toString();
                        }
                        if (!TextUtils.isEmpty(charSequence)) {
                            arrayList.add(charSequence);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                android.text.ClipboardManager clipboardManager = (android.text.ClipboardManager) context.getSystemService("clipboard");
                if (clipboardManager != null && clipboardManager.hasText()) {
                    charSequence = clipboardManager.getText();
                    if (charSequence != null) {
                        arrayList.add(charSequence.toString());
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return arrayList;
    }

    public static String b(Context context) {
        String str;
        Exception exception;
        String str2 = null;
        if (VERSION.SDK_INT >= 11) {
            try {
                ClipData primaryClip = ((ClipboardManager) context.getSystemService("clipboard")).getPrimaryClip();
                if (primaryClip == null || primaryClip.getItemCount() <= 0) {
                    str = null;
                } else {
                    String toString = primaryClip.toString();
                    Item itemAt = primaryClip.getItemAt(0);
                    if (itemAt.getText() != null) {
                        str = itemAt.getText().toString();
                    } else if (itemAt.getUri() != null) {
                        str = itemAt.getUri().toString();
                    } else {
                        str = null;
                    }
                    try {
                        Object obj;
                        new StringBuilder("copy_original_url_str --> ").append(toString).append(", isFromSniffer --> ").append(toString.contains("sniff_downlaod"));
                        if (!TextUtils.isEmpty(toString)) {
                            obj = "text/plain \"";
                            int indexOf = toString.indexOf(obj);
                            int lastIndexOf = toString.lastIndexOf(h.f);
                            if (indexOf > 0 && lastIndexOf > indexOf) {
                                obj = toString.substring(obj.length() + indexOf, lastIndexOf);
                            }
                            if (!TextUtils.isEmpty(obj) && obj.equals("sniff_downlaod")) {
                                obj = 1;
                                if (!(TextUtils.isEmpty(str) || r1 == null)) {
                                    str = str.replace("&amp;", com.alipay.sdk.sys.a.b).trim().replaceAll(" ", "%20");
                                    new StringBuilder("isFromSniffer --> true, copy_original_url_str --> ").append(str).append(", after_handled_copy_original_url_str --> ").append(str);
                                }
                            }
                        }
                        obj = null;
                        str = str.replace("&amp;", com.alipay.sdk.sys.a.b).trim().replaceAll(" ", "%20");
                        new StringBuilder("isFromSniffer --> true, copy_original_url_str --> ").append(str).append(", after_handled_copy_original_url_str --> ").append(str);
                    } catch (Exception e) {
                        Exception exception2 = e;
                        str2 = str;
                        exception = exception2;
                        exception.printStackTrace();
                        return str2;
                    }
                }
                return str;
            } catch (Exception e2) {
                exception = e2;
                exception.printStackTrace();
                return str2;
            }
        }
        try {
            android.text.ClipboardManager clipboardManager = (android.text.ClipboardManager) context.getSystemService("clipboard");
            if (clipboardManager == null || !clipboardManager.hasText()) {
                return null;
            }
            CharSequence text = clipboardManager.getText();
            return text != null ? text.toString() : null;
        } catch (Exception exception3) {
            exception3.printStackTrace();
            return null;
        }
    }

    public static String a(long j) {
        return e.a(j, e.b, false);
    }

    public static String b(long j) {
        return e.a(j, e.a);
    }

    public static String a(TaskRunningInfo taskRunningInfo) {
        String str;
        String str2 = taskRunningInfo.mUrl;
        String str3 = taskRunningInfo.mTitle;
        Object obj = null;
        if (!((!TextUtils.isEmpty(taskRunningInfo.mUrl) && !taskRunningInfo.mUrl.startsWith("cid://")) || TextUtils.isEmpty(taskRunningInfo.mCID) || TextUtils.isEmpty(taskRunningInfo.mGCID))) {
            obj = 1;
        }
        if (obj != null) {
            String str4 = taskRunningInfo.mCID;
            str = taskRunningInfo.mGCID;
            long j = taskRunningInfo.mFileSize;
            StringBuilder stringBuilder = new StringBuilder("http://gcidtask.xunlei.com/");
            stringBuilder.append(str3 + "?");
            str2 = com.umeng.a.d;
            str3 = String.valueOf(j);
            if (str4 != null || com.umeng.a.d.equals(str4)) {
                if (str == null) {
                    str = com.umeng.a.d;
                }
                str = str4.concat("|").concat(str3).concat("|").concat(str);
            } else {
                str = str2;
            }
            stringBuilder.append(new StringBuilder("fid=").append(str).append(com.alipay.sdk.sys.a.b).toString());
            stringBuilder.append(new StringBuilder("tid=").append(g.a(str.getBytes())).append(com.alipay.sdk.sys.a.b).toString());
            stringBuilder.append("srcid=1&");
            stringBuilder.append("verno=1");
            return k.a(stringBuilder.toString());
        }
        if (TaskType.BT == taskRunningInfo.mTaskType) {
            str = c.b(taskRunningInfo.mInfoHash);
        } else if (TaskType.MAGNET == taskRunningInfo.mTaskType) {
            str = taskRunningInfo.mUrl;
        } else {
            str = k.a(str2);
        }
        try {
            return (str.startsWith("http://") || str.startsWith("https://") || str.startsWith("ftp://")) ? URLEncoder.encode(str, GameManager.DEFAULT_CHARSET) : str;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return str;
        }
    }

    public static boolean a(com.xunlei.downloadprovider.download.tasklist.a.a aVar) {
        return aVar.mTaskStatus == 8;
    }

    public static String b(TaskRunningInfo taskRunningInfo) {
        return taskRunningInfo != null ? taskRunningInfo.mLocalFileName : null;
    }

    public static String a(com.xunlei.downloadprovider.download.tasklist.a.a aVar, Context context) {
        if (!TextUtils.isEmpty(aVar.mDisplayName)) {
            return aVar.mDisplayName;
        }
        String str = aVar.mTitle;
        if (XLFileTypeUtil.a(aVar.mLocalFileName) != EFileCategoryType.E_SOFTWARE_CATEGORY || 8 != aVar.mTaskStatus) {
            return str;
        }
        if (!TextUtils.isEmpty(aVar.mAppName)) {
            return aVar.mAppName;
        }
        com.xunlei.downloadprovider.a.c.a a = com.xunlei.downloadprovider.a.c.a(context, aVar.mLocalFileName);
        if (a == null) {
            return str;
        }
        CharSequence a2 = a.a();
        if (a2 == null) {
            return str;
        }
        aVar.mAppName = a2.toString() + ".apk";
        str = aVar.mAppName;
        aVar.mDisplayName = str;
        return str;
    }

    public static String b(com.xunlei.downloadprovider.download.tasklist.a.a aVar, Context context) {
        return !TextUtils.isEmpty(aVar.b) ? aVar.b : a(a(aVar, context));
    }

    private static String a(String str) {
        String replaceAll;
        String substring;
        Exception e;
        try {
            Pattern compile = Pattern.compile("(?:(?i)www|(?i)http).*?(?i)com", XZBDevice.DOWNLOAD_LIST_RECYCLE);
            str = compile.matcher(str).replaceAll(com.umeng.a.d).replaceAll("\\(.*\\)", com.umeng.a.d);
            replaceAll = str.replaceAll("\\[.*\\]", com.umeng.a.d);
        } catch (PatternSyntaxException e2) {
            PatternSyntaxException patternSyntaxException = e2;
            replaceAll = str;
            patternSyntaxException.printStackTrace();
        }
        int lastIndexOf = replaceAll.lastIndexOf(".");
        if (lastIndexOf != -1) {
            try {
                String substring2 = replaceAll.substring(lastIndexOf + 1);
                substring = replaceAll.substring(0, lastIndexOf);
                try {
                    substring = substring.replaceAll(substring2, com.umeng.a.d);
                } catch (Exception e3) {
                    e = e3;
                    e.printStackTrace();
                    substring = substring.replaceAll("(?i)720p|(?i)1080p|(?i)1080i|(?i)1280p|1280", com.umeng.a.d).replaceAll("(?i)BD|(?i)HD|(?i)TC|(?i)TS|(?i)TV", com.umeng.a.d).replaceAll(" ", com.umeng.a.d).replaceAll("\u5929\u7a7a\u6811|\u5b57\u5e55|\u7ec4|\u4e2d\u65e5|\u4e2d\u6587|\u82f1\u6587|\u82f1\u8bed|\u8d85\u6e05|\u9ad8\u6e05|\u6e05\u6670|\u6807\u6e05|\u56fd\u8bed|\u4e2d\u5b57|\u56fd\u7ca4\u8bed|\u56fd\u7ca4\u53cc\u8bed|\u53cc\u8bed|\u4e2d\u82f1|\u53cc\u5b57|\u9633\u5149|\u514d\u8d39\u4e0b\u8f7d|\u9996\u53d1|\u7535\u5f71\u5929\u5802|\u7535\u5f71|\u5b8c\u6574|\u526a\u8f91", com.umeng.a.d).replaceAll("(?i)Skytree|(?i)dvd|(?i)gb_jp|(?i)quot|(?i)x264|(?i)h264|(?i)ac3|(?i)rarbt|(?i)bt|(?i)czw", com.umeng.a.d);
                    return substring.replaceAll("[_`~!@#\\$%\\^&\\*\\(\\)\\+=\\|\\{\\}':;,\\-\\[\\]\\.<>/\\?\uff01\uffe5\u2026\uff08\uff09\u2014\u3010\u3011\u2018\uff1b\uff1a\u201d\u201c\u2019\u3002\uff0c\u3001\uff1f]", com.umeng.a.d);
                }
            } catch (Exception e4) {
                Exception exception = e4;
                substring = replaceAll;
                e = exception;
                e.printStackTrace();
                try {
                    substring = substring.replaceAll("(?i)720p|(?i)1080p|(?i)1080i|(?i)1280p|1280", com.umeng.a.d).replaceAll("(?i)BD|(?i)HD|(?i)TC|(?i)TS|(?i)TV", com.umeng.a.d).replaceAll(" ", com.umeng.a.d).replaceAll("\u5929\u7a7a\u6811|\u5b57\u5e55|\u7ec4|\u4e2d\u65e5|\u4e2d\u6587|\u82f1\u6587|\u82f1\u8bed|\u8d85\u6e05|\u9ad8\u6e05|\u6e05\u6670|\u6807\u6e05|\u56fd\u8bed|\u4e2d\u5b57|\u56fd\u7ca4\u8bed|\u56fd\u7ca4\u53cc\u8bed|\u53cc\u8bed|\u4e2d\u82f1|\u53cc\u5b57|\u9633\u5149|\u514d\u8d39\u4e0b\u8f7d|\u9996\u53d1|\u7535\u5f71\u5929\u5802|\u7535\u5f71|\u5b8c\u6574|\u526a\u8f91", com.umeng.a.d).replaceAll("(?i)Skytree|(?i)dvd|(?i)gb_jp|(?i)quot|(?i)x264|(?i)h264|(?i)ac3|(?i)rarbt|(?i)bt|(?i)czw", com.umeng.a.d);
                    return substring.replaceAll("[_`~!@#\\$%\\^&\\*\\(\\)\\+=\\|\\{\\}':;,\\-\\[\\]\\.<>/\\?\uff01\uffe5\u2026\uff08\uff09\u2014\u3010\u3011\u2018\uff1b\uff1a\u201d\u201c\u2019\u3002\uff0c\u3001\uff1f]", com.umeng.a.d);
                } catch (Exception e5) {
                    exception = e5;
                    replaceAll = substring;
                    exception.printStackTrace();
                    return replaceAll;
                }
            }
        }
        substring = replaceAll;
        substring = substring.replaceAll("(?i)720p|(?i)1080p|(?i)1080i|(?i)1280p|1280", com.umeng.a.d).replaceAll("(?i)BD|(?i)HD|(?i)TC|(?i)TS|(?i)TV", com.umeng.a.d).replaceAll(" ", com.umeng.a.d).replaceAll("\u5929\u7a7a\u6811|\u5b57\u5e55|\u7ec4|\u4e2d\u65e5|\u4e2d\u6587|\u82f1\u6587|\u82f1\u8bed|\u8d85\u6e05|\u9ad8\u6e05|\u6e05\u6670|\u6807\u6e05|\u56fd\u8bed|\u4e2d\u5b57|\u56fd\u7ca4\u8bed|\u56fd\u7ca4\u53cc\u8bed|\u53cc\u8bed|\u4e2d\u82f1|\u53cc\u5b57|\u9633\u5149|\u514d\u8d39\u4e0b\u8f7d|\u9996\u53d1|\u7535\u5f71\u5929\u5802|\u7535\u5f71|\u5b8c\u6574|\u526a\u8f91", com.umeng.a.d).replaceAll("(?i)Skytree|(?i)dvd|(?i)gb_jp|(?i)quot|(?i)x264|(?i)h264|(?i)ac3|(?i)rarbt|(?i)bt|(?i)czw", com.umeng.a.d);
        return substring.replaceAll("[_`~!@#\\$%\\^&\\*\\(\\)\\+=\\|\\{\\}':;,\\-\\[\\]\\.<>/\\?\uff01\uffe5\u2026\uff08\uff09\u2014\u3010\u3011\u2018\uff1b\uff1a\u201d\u201c\u2019\u3002\uff0c\u3001\uff1f]", com.umeng.a.d);
    }

    public static String a(TaskInfo taskInfo, Context context) {
        if (!TextUtils.isEmpty(taskInfo.mDisplayName)) {
            return taskInfo.mDisplayName;
        }
        String str = taskInfo.mTitle;
        if (XLFileTypeUtil.a(taskInfo.mLocalFileName) != EFileCategoryType.E_SOFTWARE_CATEGORY || 8 != taskInfo.mTaskStatus) {
            return str;
        }
        if (!TextUtils.isEmpty(taskInfo.mAppName)) {
            return taskInfo.mAppName;
        }
        com.xunlei.downloadprovider.a.c.a a = com.xunlei.downloadprovider.a.c.a(context, taskInfo.mLocalFileName);
        if (a == null) {
            return str;
        }
        CharSequence a2 = a.a();
        if (a2 == null) {
            return str;
        }
        taskInfo.mAppName = a2.toString() + ".apk";
        str = taskInfo.mAppName;
        taskInfo.mDisplayName = str;
        return str;
    }

    public static String a(Context context, long j) {
        if (j > 86400) {
            return context.getResources().getString(2131231230);
        }
        String str;
        if (j < 60) {
            str = String.valueOf(j) + "\u79d2";
        } else if (j < 3600) {
            str = String.valueOf(j / 60) + "\u5206\u949f";
        } else {
            str = String.valueOf(j / 3600) + "\u5c0f\u65f6";
        }
        return context.getResources().getString(2131231229, new Object[]{str});
    }
}
