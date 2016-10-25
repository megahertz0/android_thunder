package com.xunlei.downloadprovider.util;

import android.content.Context;
import android.os.Handler;
import android.text.ClipboardManager;
import android.text.TextUtils;
import anet.channel.util.HttpConstant;
import com.alipay.sdk.cons.b;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.app.o;
import com.xunlei.downloadprovider.commonview.dialog.XLAlarmDialog;
import com.xunlei.downloadprovider.frame.MainTabActivity;
import com.xunlei.downloadprovider.model.g;
import com.xunlei.downloadprovider.service.DownloadService;
import com.xunlei.downloadprovider.task.ThunderTask;

// compiled from: CreateTaskFromClipBoardHandler.java
public class d {
    private static final String c;
    public Context a;
    public XLAlarmDialog b;
    private a d;

    // compiled from: CreateTaskFromClipBoardHandler.java
    public static interface a {
    }

    static /* synthetic */ void a(Context context, String str) {
        if (!TextUtils.isEmpty(str)) {
            String toString;
            int indexOf = str.indexOf(":");
            if (-1 != indexOf) {
                String substring = str.substring(0, indexOf);
                if (substring.equalsIgnoreCase(HttpConstant.HTTP)) {
                    toString = new StringBuilder(HttpConstant.HTTP).append(str.substring(indexOf)).toString();
                } else if (substring.equalsIgnoreCase(b.a)) {
                    toString = new StringBuilder(b.a).append(str.substring(indexOf)).toString();
                } else if (substring.equalsIgnoreCase("ed2k")) {
                    toString = new StringBuilder("ed2k").append(str.substring(indexOf)).toString();
                } else if (substring.equalsIgnoreCase("thunder")) {
                    toString = new StringBuilder("thunder").append(str.substring(indexOf)).toString();
                } else if (substring.equalsIgnoreCase("ftp")) {
                    toString = new StringBuilder("ftp").append(str.substring(indexOf)).toString();
                } else if (substring.equalsIgnoreCase("magnet")) {
                    toString = new StringBuilder("magnet").append(str.substring(indexOf)).toString();
                } else {
                    toString = new StringBuilder("http://").append(str).toString();
                }
            } else {
                toString = new StringBuilder("http://").append(str).toString();
            }
            if (DownloadService.a() != null) {
                g gVar = new g(3, toString, null);
                gVar.d = "manual/paste_download";
                if (context instanceof ThunderTask) {
                    ThunderTask thunderTask = (ThunderTask) context;
                    Handler handler = BrothersApplication.a().e;
                    handler.a = (o) context;
                    thunderTask.createLocalTask(toString, null, 0, null, null, null, 0, gVar, handler, false);
                }
            }
        }
    }

    static {
        c = d.class.getSimpleName();
    }

    public d(MainTabActivity mainTabActivity, a aVar) {
        this.b = null;
        this.a = mainTabActivity;
        this.d = aVar;
    }

    public static boolean a(String str) {
        return BrothersApplication.a().getSharedPreferences("shared_save_url_from_clip_board", 0).getString("last_copy_url", com.umeng.a.d).equals(str);
    }

    public static void b(String str) {
        BrothersApplication.a().getSharedPreferences("shared_save_url_from_clip_board", 0).edit().putString("last_copy_url", str).commit();
    }

    public static boolean a() {
        return BrothersApplication.a().getSharedPreferences("shared_save_url_from_clip_board", 0).getBoolean("can_show_tip_dialog", true);
    }

    public static void a(boolean z) {
        BrothersApplication.a().getSharedPreferences("shared_save_url_from_clip_board", 0).edit().putBoolean("can_show_tip_dialog", z).commit();
    }

    public final String b() {
        int i = 0;
        String str = com.umeng.a.d;
        try {
            ClipboardManager clipboardManager = (ClipboardManager) this.a.getSystemService("clipboard");
            if (clipboardManager != null && clipboardManager.hasText()) {
                CharSequence text = clipboardManager.getText();
                if (text != null) {
                    String toString = text.toString();
                    String[] strArr = new String[]{"http://", "https://", "ftp://", "ed2k://", "magnet:?", "thunder://"};
                    while (i < 6) {
                        if (!toString.startsWith(strArr[i])) {
                            i++;
                        } else if (com.xunlei.downloadprovider.util.c.a.b(toString) == 1) {
                            return toString;
                        }
                    }
                }
            }
            return str;
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }
}
