package com.inmobi.rendering;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DownloadManager;
import android.app.DownloadManager.Query;
import android.app.DownloadManager.Request;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.ParseException;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore.Images.Media;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.webkit.JavascriptInterface;
import android.webkit.URLUtil;
import android.widget.FrameLayout;
import anet.channel.util.HttpConstant;
import com.alipay.sdk.util.h;
import com.inmobi.commons.core.network.NetworkRequest;
import com.inmobi.commons.core.network.NetworkRequest.RequestType;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.info.DisplayInfo;
import com.inmobi.commons.core.utilities.info.DisplayInfo.ORIENTATION_VALUES;
import com.inmobi.rendering.InMobiAdActivity.c;
import com.inmobi.rendering.RenderView.RenderViewState;
import com.inmobi.rendering.RenderingProperties.PlacementType;
import com.inmobi.rendering.mraid.MraidMediaProcessor.MediaContentType;
import com.inmobi.rendering.mraid.b;
import com.inmobi.rendering.mraid.d;
import com.inmobi.rendering.mraid.e;
import com.inmobi.rendering.mraid.f;
import com.inmobi.rendering.mraid.l;
import com.inmobi.rendering.mraid.m;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import com.tencent.bugly.Bugly;
import com.tencent.open.SocialConstants;
import com.umeng.message.MsgConstant;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.tdlive.R;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import org.android.agoo.message.MessageService;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: JavaScriptBridge.java
public class a {
    static final String[] a;
    private static final String b;
    private RenderView c;
    private RenderingProperties d;
    private l e;
    private Context f;
    private DownloadManager g;
    private BroadcastReceiver h;

    // compiled from: JavaScriptBridge.java
    class AnonymousClass_10 implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        AnonymousClass_10(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        public void run() {
            a.this.b(this.a, this.b);
        }
    }

    // compiled from: JavaScriptBridge.java
    class AnonymousClass_11 implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        AnonymousClass_11(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        public void run() {
            a.this.g(this.a, this.b);
        }
    }

    // compiled from: JavaScriptBridge.java
    class AnonymousClass_12 implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        AnonymousClass_12(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        public void run() {
            a.this.c("openEmbedded", this.a, this.b);
        }
    }

    // compiled from: JavaScriptBridge.java
    class AnonymousClass_13 implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        AnonymousClass_13(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        public void run() {
            a.this.h(this.a, this.b);
        }
    }

    // compiled from: JavaScriptBridge.java
    class AnonymousClass_14 implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        AnonymousClass_14(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        public void run() {
            a.this.i(this.a, this.b);
        }
    }

    // compiled from: JavaScriptBridge.java
    class AnonymousClass_15 implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        AnonymousClass_15(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        public void run() {
            a.this.c(this.a, this.b);
        }
    }

    // compiled from: JavaScriptBridge.java
    class AnonymousClass_16 implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        AnonymousClass_16(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        public void run() {
            a.this.d(this.a, this.b);
        }
    }

    // compiled from: JavaScriptBridge.java
    class AnonymousClass_17 implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ String b;
        final /* synthetic */ int c;

        AnonymousClass_17(String str, String str2, int i) {
            this.a = str;
            this.b = str2;
            this.c = i;
        }

        public void run() {
            a.this.c.b(this.a, this.b, this.c);
        }
    }

    // compiled from: JavaScriptBridge.java
    class AnonymousClass_18 implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ String b;
        final /* synthetic */ String c;
        final /* synthetic */ boolean d;
        final /* synthetic */ boolean e;
        final /* synthetic */ String f;
        final /* synthetic */ String g;
        final /* synthetic */ boolean h;

        AnonymousClass_18(String str, String str2, String str3, boolean z, boolean z2, String str4, String str5, boolean z3) {
            this.a = str;
            this.b = str2;
            this.c = str3;
            this.d = z;
            this.e = z2;
            this.f = str4;
            this.g = str5;
            this.h = z3;
        }

        public void run() {
            if ((this.a == null || this.a.trim().length() == 0) && (this.b == null || this.b.trim().length() == 0 || !this.b.startsWith(HttpConstant.HTTP))) {
                a.this.c.a(this.c, "Null or empty or invalid media playback URL supplied", "playVideo");
                return;
            }
            Logger.a(InternalLogLevel.INTERNAL, b, "Media player properties");
            Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("shouldAutoPlay: ").append(this.d).append("; shouldLoopPlayback: ").append(this.e).append("; startStyle: ").append(this.f).append("; stopStyle: ").append(this.g).toString());
            b bVar = new b();
            bVar.a = 0;
            bVar.b = 0;
            bVar.c = 24;
            bVar.d = 24;
            f fVar = new f();
            if (this.a == null || this.a.length() != 0) {
                fVar.a = this.a;
            }
            if (!a.this.c.getMediaProcessor().a()) {
                fVar.d = this.d;
                fVar.f = this.e;
                fVar.b = this.f;
                fVar.c = this.g;
                fVar.e = this.h;
            }
            a.this.c.getMediaProcessor().a(bVar);
            a.this.c.getMediaProcessor().a(fVar);
            a.this.c.a(this.c, this.b, MediaContentType.MEDIA_CONTENT_TYPE_AUDIO);
        }
    }

    // compiled from: JavaScriptBridge.java
    class AnonymousClass_19 implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ String b;
        final /* synthetic */ int c;

        AnonymousClass_19(String str, String str2, int i) {
            this.a = str;
            this.b = str2;
            this.c = i;
        }

        public void run() {
            a.this.c.a(this.a, this.b, this.c);
        }
    }

    // compiled from: JavaScriptBridge.java
    class AnonymousClass_1 implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        AnonymousClass_1(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        public void run() {
            a.this.c("open", this.a, this.b);
        }
    }

    // compiled from: JavaScriptBridge.java
    class AnonymousClass_20 implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        AnonymousClass_20(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        public void run() {
            a.this.b(this.a, this.b);
        }
    }

    // compiled from: JavaScriptBridge.java
    class AnonymousClass_21 implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        AnonymousClass_21(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        public void run() {
            a.this.c(this.a, this.b);
        }
    }

    // compiled from: JavaScriptBridge.java
    class AnonymousClass_22 implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        AnonymousClass_22(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        public void run() {
            a.this.d(this.a, this.b);
        }
    }

    // compiled from: JavaScriptBridge.java
    class AnonymousClass_24 implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ String b;
        final /* synthetic */ int c;

        AnonymousClass_24(String str, String str2, int i) {
            this.a = str;
            this.b = str2;
            this.c = i;
        }

        public void run() {
            a.this.c.b(this.a, this.b, this.c);
        }
    }

    // compiled from: JavaScriptBridge.java
    class AnonymousClass_25 implements Runnable {
        final /* synthetic */ boolean a;

        AnonymousClass_25(boolean z) {
            this.a = z;
        }

        public void run() {
            a.this.c.b(this.a);
        }
    }

    // compiled from: JavaScriptBridge.java
    class AnonymousClass_26 implements Runnable {
        final /* synthetic */ String a;

        AnonymousClass_26(String str) {
            this.a = str;
        }

        public void run() {
            a.this.c.d(this.a);
        }
    }

    // compiled from: JavaScriptBridge.java
    class AnonymousClass_27 implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ String b;
        final /* synthetic */ int c;

        AnonymousClass_27(String str, String str2, int i) {
            this.a = str;
            this.b = str2;
            this.c = i;
        }

        public void run() {
            a.this.c.c(this.a, this.b, this.c);
        }
    }

    // compiled from: JavaScriptBridge.java
    class AnonymousClass_29 implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        AnonymousClass_29(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        public void run() {
            a.this.j(this.a, this.b);
        }
    }

    // compiled from: JavaScriptBridge.java
    class AnonymousClass_2 implements c {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        AnonymousClass_2(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        public void a(int i, String[] strArr, int[] iArr) {
            if (iArr.length == 1 && iArr[0] == 0) {
                a.this.a(this.a, this.b);
            } else {
                a.this.a(this.a, "Permission denied by user.", "storePicture");
            }
        }
    }

    // compiled from: JavaScriptBridge.java
    class AnonymousClass_31 implements Runnable {
        final /* synthetic */ boolean a;

        AnonymousClass_31(boolean z) {
            this.a = z;
        }

        public void run() {
            a.this.c.c(this.a);
        }
    }

    // compiled from: JavaScriptBridge.java
    class AnonymousClass_32 implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        AnonymousClass_32(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        public void run() {
            a.this.a(this.a, this.b, MediaContentType.MEDIA_CONTENT_TYPE_AUDIO_VIDEO);
        }
    }

    // compiled from: JavaScriptBridge.java
    class AnonymousClass_3 extends BroadcastReceiver {
        final /* synthetic */ String a;

        AnonymousClass_3(String str) {
            this.a = str;
        }

        public void onReceive(Context context, Intent intent) {
            if (intent != null && com.xunlei.download.DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(intent.getAction())) {
                long longExtra = intent.getLongExtra(com.xunlei.download.DownloadManager.EXTRA_DOWNLOAD_ID, 0);
                Query query = new Query();
                query.setFilterById(new long[]{longExtra});
                Cursor query2 = a.this.g.query(query);
                if (query2.moveToFirst()) {
                    int columnIndex = query2.getColumnIndex(Impl.COLUMN_STATUS);
                    if (16 == query2.getInt(columnIndex)) {
                        a.this.c.a(this.a, "File failed to download", "storePicture");
                    } else if (8 == query2.getInt(columnIndex)) {
                        Logger.a(InternalLogLevel.INTERNAL, b, "Download completed");
                    } else if (1 == query2.getInt(columnIndex)) {
                        Logger.a(InternalLogLevel.INTERNAL, b, "Download queued");
                    } else if (2 == query2.getInt(columnIndex)) {
                        Logger.a(InternalLogLevel.INTERNAL, b, "Download ongoing");
                    }
                }
                query2.close();
            }
        }
    }

    // compiled from: JavaScriptBridge.java
    class AnonymousClass_4 implements c {
        final /* synthetic */ String a;
        final /* synthetic */ String b;
        final /* synthetic */ String c;
        final /* synthetic */ String d;
        final /* synthetic */ String e;
        final /* synthetic */ String f;
        final /* synthetic */ String g;
        final /* synthetic */ String h;
        final /* synthetic */ String i;
        final /* synthetic */ String j;
        final /* synthetic */ String k;

        AnonymousClass_4(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11) {
            this.a = str;
            this.b = str2;
            this.c = str3;
            this.d = str4;
            this.e = str5;
            this.f = str6;
            this.g = str7;
            this.h = str8;
            this.i = str9;
            this.j = str10;
            this.k = str11;
        }

        public void a(int i, String[] strArr, int[] iArr) {
            if (iArr.length == 2 && iArr[0] == 0 && iArr[1] == 0) {
                a.this.c.a(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k);
            } else {
                a.this.c.a(this.a, "Permission denied by user.", "createCalendarEvent");
            }
        }
    }

    // compiled from: JavaScriptBridge.java
    class AnonymousClass_5 implements c {
        final /* synthetic */ String a;

        AnonymousClass_5(String str) {
            this.a = str;
        }

        public void a(int i, String[] strArr, int[] iArr) {
            if (iArr.length == 1 && iArr[0] == 0) {
                a.this.b(this.a);
            } else {
                a.this.c.a(this.a, "Permission denied by user.", "processGetGalleryImage");
            }
        }
    }

    // compiled from: JavaScriptBridge.java
    class AnonymousClass_6 implements com.inmobi.rendering.InMobiAdActivity.a {
        final /* synthetic */ String a;

        AnonymousClass_6(String str) {
            this.a = str;
        }

        public void a(int i, Intent intent) {
            if (i == -1) {
                Bitmap a = e.a(e.a(intent.getData(), a.this.f), a.this.f, a.this.c.getRenderingConfig().a(), a.this.c.getRenderingConfig().b());
                int width = a.getWidth();
                a.this.c.a(this.a, new StringBuilder("fireGalleryImageSelectedEvent('").append(e.a(a, a.this.f, a.this.c.getRenderingConfig().c())).append("','").append(width).append("','").append(a.getHeight()).append("')").toString());
                return;
            }
            a.this.c.a(this.a, "User did not select an image from gallery", "getGalleryImage");
        }
    }

    // compiled from: JavaScriptBridge.java
    class AnonymousClass_8 implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ String b;
        final /* synthetic */ String c;
        final /* synthetic */ String d;
        final /* synthetic */ String e;
        final /* synthetic */ boolean f;
        final /* synthetic */ boolean g;
        final /* synthetic */ String h;
        final /* synthetic */ String i;
        final /* synthetic */ String j;
        final /* synthetic */ String k;
        final /* synthetic */ boolean l;
        final /* synthetic */ boolean m;

        AnonymousClass_8(String str, String str2, String str3, String str4, String str5, boolean z, boolean z2, String str6, String str7, String str8, String str9, boolean z3, boolean z4) {
            this.a = str;
            this.b = str2;
            this.c = str3;
            this.d = str4;
            this.e = str5;
            this.f = z;
            this.g = z2;
            this.h = str6;
            this.i = str7;
            this.j = str8;
            this.k = str9;
            this.l = z3;
            this.m = z4;
        }

        public void run() {
            int i = 0;
            Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("JavaScript called: playVideo (").append(this.a).append(", ").append(this.b).append(SocializeConstants.OP_CLOSE_PAREN).toString());
            if ((this.b == null || this.b.trim().length() == 0) && (this.a == null || this.a.trim().length() == 0 || !this.a.startsWith(HttpConstant.HTTP))) {
                a.this.c.a(this.c, "Null or empty or invalid media playback URL supplied", "playVideo");
                return;
            }
            Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("Video dimensions: (").append(this.d).append(", ").append(this.e).append(SocializeConstants.OP_CLOSE_PAREN).toString());
            Logger.a(InternalLogLevel.INTERNAL, b, "Media player properties");
            Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("shouldAutoPlay: ").append(this.f).append("; shouldLoopPlayback: ").append(this.g).append("; startStyle: ").append(this.h).append("; stopStyle: ").append(this.i).toString());
            b bVar = new b();
            f fVar = new f();
            if (this.b == null || this.b.length() != 0) {
                fVar.a = this.b;
            }
            int a = a.c(this.d);
            int a2 = a.c(this.e);
            int a3 = a.c(this.j);
            int a4 = a.c(this.k);
            if (!(-99999 == a && -99999 == a2) && a > 0 && a2 > 0) {
                float c = DisplayInfo.a().c();
                if (a3 == -99999) {
                    a3 = 0;
                }
                if (a4 != -99999) {
                    i = a4;
                }
                bVar.a = (int) ((((float) a3) * c) + 0.5f);
                bVar.b = (int) ((((float) i) * c) + 0.5f);
                bVar.c = (int) ((((float) a) * c) + 0.5f);
                bVar.d = (int) ((((float) a2) * c) + 0.5f);
                fVar.b = this.h;
            } else {
                fVar.b = "fullscreen";
            }
            if (!a.this.c.getMediaProcessor().a()) {
                fVar.g = this.l;
                fVar.d = this.f;
                fVar.f = this.g;
                fVar.c = this.i;
                fVar.e = this.m;
            }
            a.this.c.getMediaProcessor().a(bVar);
            a.this.c.getMediaProcessor().a(fVar);
            a.this.c.a(this.c, this.a, MediaContentType.MEDIA_CONTENT_TYPE_AUDIO_VIDEO);
        }
    }

    // compiled from: JavaScriptBridge.java
    class AnonymousClass_9 implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ String b;
        final /* synthetic */ int c;

        AnonymousClass_9(String str, String str2, int i) {
            this.a = str;
            this.b = str2;
            this.c = i;
        }

        public void run() {
            a.this.c.a(this.a, this.b, this.c);
        }
    }

    @TargetApi(16)
    // compiled from: JavaScriptBridge.java
    private static class a implements OnGlobalLayoutListener {
        private int a;
        private int b;
        private View c;
        private final Boolean d;

        a(View view) {
            this.d = Boolean.valueOf(false);
            this.c = view;
        }

        public void onGlobalLayout() {
            this.a = DisplayInfo.b(this.c.getWidth());
            this.b = DisplayInfo.b(this.c.getHeight());
            if (VERSION.SDK_INT >= 16) {
                this.c.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            } else {
                this.c.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
            synchronized (this.d) {
                this.d.notify();
            }
        }
    }

    static {
        b = a.class.getSimpleName();
        a = new String[]{"tel", "sms", "calendar", "storePicture", "inlineVideo"};
    }

    public a(RenderView renderView, RenderingProperties renderingProperties) {
        this.f = renderView.getRenderViewContext();
        this.c = renderView;
        this.d = renderingProperties;
    }

    @JavascriptInterface
    public void open(String str, String str2) {
        if (this.c == null) {
            Logger.a(InternalLogLevel.INTERNAL, b, "Found a null instance of render view!");
        } else {
            new Handler(this.c.getRenderViewContext().getMainLooper()).post(new AnonymousClass_1(str, str2));
        }
    }

    @JavascriptInterface
    public void openEmbedded(String str, String str2) {
        if (this.c == null) {
            Logger.a(InternalLogLevel.INTERNAL, b, "Found a null instance of render view!");
        } else {
            new Handler(this.c.getRenderViewContext().getMainLooper()).post(new AnonymousClass_12(str, str2));
        }
    }

    @JavascriptInterface
    public void ping(String str, String str2, boolean z) {
        if (this.c == null) {
            Logger.a(InternalLogLevel.INTERNAL, b, "Found a null instance of render view!");
        } else if (str2 == null || str2.trim().length() == 0 || !URLUtil.isValidUrl(str2)) {
            this.c.a(str, new StringBuilder("Invalid URL:").append(str2).toString(), "ping");
        } else {
            Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("JavaScript called ping() URL: >>> ").append(str2).append(" <<<").toString());
            com.inmobi.rendering.a.c.a().a(str2, z);
        }
    }

    @JavascriptInterface
    public void pingInWebView(String str, String str2, boolean z) {
        if (this.c == null) {
            Logger.a(InternalLogLevel.INTERNAL, b, "Found a null instance of render view!");
        } else if (str2 == null || str2.trim().length() == 0 || !URLUtil.isValidUrl(str2)) {
            this.c.a(str, new StringBuilder("Invalid URL:").append(str2).toString(), "pingInWebView");
        } else {
            Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("JavaScript called pingInWebView() URL: >>> ").append(str2).append(" <<<").toString());
            com.inmobi.rendering.a.c.a().b(str2, z);
        }
    }

    @JavascriptInterface
    public void log(String str, String str2) {
        Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("Log called. Message:").append(str2).toString());
    }

    @JavascriptInterface
    public String getPlatformVersion(String str) {
        String toString = Integer.toString(VERSION.SDK_INT);
        Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("getPlatformVersion. Version:").append(toString).toString());
        return toString;
    }

    @JavascriptInterface
    public String getPlatform(String str) {
        Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("getPlatform. Platform:").append(com.inmobi.commons.a.b.e()).toString());
        return com.inmobi.commons.a.b.e();
    }

    @JavascriptInterface
    public void fireAdReady(String str) {
        Logger.a(InternalLogLevel.INTERNAL, b, "fireAdReady called.");
        this.c.getListener().a(this.c);
    }

    @JavascriptInterface
    public void fireAdFailed(String str) {
        Logger.a(InternalLogLevel.INTERNAL, b, "fireAdFailed called.");
        this.c.getListener().b(this.c);
    }

    @JavascriptInterface
    public String getDefaultPosition(String str) {
        if (this.c == null) {
            Logger.a(InternalLogLevel.INTERNAL, b, "Found a null instance of render view!");
            return new JSONObject().toString();
        }
        synchronized (this.c.getDefaultPositionMonitor()) {
            this.c.setDefaultPositionLock();
            new Handler(this.c.getRenderViewContext().getMainLooper()).post(new Runnable() {
                public void run() {
                    a.this.c.setDefaultPosition();
                }
            });
            while (this.c.b()) {
                try {
                    this.c.getDefaultPositionMonitor().wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return this.c.getDefaultPosition();
    }

    @JavascriptInterface
    public String getCurrentPosition(String str) {
        if (this.c == null) {
            Logger.a(InternalLogLevel.INTERNAL, b, "Found a null instance of render view!");
            return com.umeng.a.d;
        }
        synchronized (this.c.getCurrentPositionMonitor()) {
            this.c.setCurrentPositionLock();
            new Handler(this.c.getRenderViewContext().getMainLooper()).post(new Runnable() {
                public void run() {
                    a.this.c.setCurrentPosition();
                }
            });
            while (this.c.c()) {
                try {
                    this.c.getCurrentPositionMonitor().wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return this.c.getCurrentPosition();
    }

    @JavascriptInterface
    public void setExpandProperties(String str, String str2) {
        Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("setExpandProperties called. Params:").append(str2).toString());
        if (this.c == null) {
            Logger.a(InternalLogLevel.INTERNAL, b, "Found a null instance of render view!");
        } else if (this.c.getState() == RenderViewState.EXPANDED) {
            Logger.a(InternalLogLevel.INTERNAL, b, "setExpandProperties can't be called on an already expanded ad.");
        } else {
            this.c.setExpandProperties(d.a(str2, this.c.getExpandProperties(), this.c.getOrientationProperties()));
        }
    }

    @JavascriptInterface
    public String getExpandProperties(String str) {
        if (this.c != null) {
            return this.c.getExpandProperties().c();
        }
        Logger.a(InternalLogLevel.INTERNAL, b, "Found a null instance of render view!");
        return com.umeng.a.d;
    }

    @JavascriptInterface
    public void expand(String str, String str2) {
        Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("expand called. Url:").append(str2).toString());
        if (this.d.a() == PlacementType.FULL_SCREEN) {
            return;
        }
        if (this.c == null) {
            Logger.a(InternalLogLevel.INTERNAL, b, "Found a null instance of render view!");
        } else if (!this.c.a()) {
            this.c.a(str, "Creative is not visible. Ignoring request.", "expand");
        } else if (str2 == null || str2.length() == 0 || str2.startsWith(HttpConstant.HTTP)) {
            new Handler(this.c.getRenderViewContext().getMainLooper()).post(new AnonymousClass_29(str, str2));
        } else {
            this.c.a(str, "Invalid URL", "expand");
        }
    }

    @JavascriptInterface
    public String getVersion(String str) {
        Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("getVersion called. Version:").append(com.inmobi.commons.a.b.d()).toString());
        return com.inmobi.commons.a.b.d();
    }

    @JavascriptInterface
    public void setResizeProperties(String str, String str2) {
        if (this.c == null) {
            Logger.a(InternalLogLevel.INTERNAL, b, "Found a null instance of render view!");
            return;
        }
        Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("setResizeProperties called. Properties:").append(str2).toString());
        m a = m.a(str2, this.c.getResizeProperties());
        if (a == null) {
            this.c.a(str, "setResizeProperties", "All mandatory fields are not present");
        }
        this.c.setResizeProperties(a);
    }

    @JavascriptInterface
    public String getResizeProperties(String str) {
        if (this.c == null) {
            Logger.a(InternalLogLevel.INTERNAL, b, "Found a null instance of render view!");
            return com.umeng.a.d;
        }
        m resizeProperties = this.c.getResizeProperties();
        return resizeProperties == null ? com.umeng.a.d : resizeProperties.a();
    }

    @JavascriptInterface
    public void resize(String str) {
        Logger.a(InternalLogLevel.INTERNAL, b, "resize called");
        if (this.d.a() == PlacementType.FULL_SCREEN) {
            return;
        }
        if (this.c == null) {
            Logger.a(InternalLogLevel.INTERNAL, b, "Found a null instance of render view!");
        } else if (this.c.a()) {
            new Handler(this.c.getRenderViewContext().getMainLooper()).post(new Runnable() {
                public void run() {
                    a.this.c.j();
                }
            });
        } else {
            this.c.a(str, "Creative is not visible. Ignoring request.", "resize");
        }
    }

    @JavascriptInterface
    public void setOrientationProperties(String str, String str2) {
        Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("setOrientationProperties called: ").append(str2).toString());
        this.e = l.a(str2, this.c.getOrientationProperties());
        this.c.setOrientationProperties(this.e);
    }

    @JavascriptInterface
    public String getOrientationProperties(String str) {
        String a = this.e.a();
        Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("getOrientationProperties called: ").append(a).toString());
        return a;
    }

    @JavascriptInterface
    public void onOrientationChange(String str) {
        Logger.a(InternalLogLevel.INTERNAL, b, ">>> onOrientationChange() >>> This API is deprecated!");
    }

    @JavascriptInterface
    public boolean isViewable(String str) {
        if (this.c != null) {
            return this.c.a();
        }
        Logger.a(InternalLogLevel.INTERNAL, b, "Found a null instance of render view!");
        return false;
    }

    @JavascriptInterface
    public void useCustomClose(String str, boolean z) {
        Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("useCustomClose called:").append(z).toString());
        new Handler(this.c.getRenderViewContext().getMainLooper()).post(new AnonymousClass_31(z));
    }

    @JavascriptInterface
    public void playVideo(String str, String str2) {
        if (this.c == null) {
            Logger.a(InternalLogLevel.INTERNAL, b, "Found a null instance of render view!");
        } else if (str2 == null || str2.trim().length() == 0 || !str2.startsWith(HttpConstant.HTTP)) {
            this.c.a(str, "Null or empty or invalid media playback URL supplied", "playVideo");
        } else {
            Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("JavaScript called: playVideo (").append(str2).append(SocializeConstants.OP_CLOSE_PAREN).toString());
            new Handler(this.c.getRenderViewContext().getMainLooper()).post(new AnonymousClass_32(str, str2));
        }
    }

    @JavascriptInterface
    public String getState(String str) {
        String toLowerCase = this.c.getState().toString().toLowerCase(Locale.ENGLISH);
        Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("getState called:").append(toLowerCase).toString());
        return toLowerCase;
    }

    @JavascriptInterface
    public String getScreenSize(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("width", DisplayInfo.a().b());
            jSONObject.put("height", DisplayInfo.a().a());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String toString = jSONObject.toString();
        Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("getScreenSize called:").append(toString).toString());
        return toString;
    }

    @JavascriptInterface
    public String getMaxSize(String str) {
        int i;
        Logger.a(InternalLogLevel.INTERNAL, b, "getMaxSize called");
        Activity fullScreenActivity = this.c.getFullScreenActivity();
        if (fullScreenActivity == null) {
            if (!(this.c.getRenderViewContext() instanceof Activity)) {
                return getScreenSize(str);
            }
            fullScreenActivity = (Activity) this.c.getRenderViewContext();
        }
        FrameLayout frameLayout = (FrameLayout) fullScreenActivity.findViewById(16908290);
        int b = DisplayInfo.b(frameLayout.getWidth());
        int b2 = DisplayInfo.b(frameLayout.getHeight());
        if (this.c.getFullScreenActivity() == null || !(b == 0 || b2 == 0)) {
            i = b2;
            b2 = b;
        } else {
            a aVar = new a(frameLayout);
            frameLayout.getViewTreeObserver().addOnGlobalLayoutListener(aVar);
            synchronized (a.a(aVar)) {
                try {
                    a.a(aVar).wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                b2 = a.b(aVar);
                i = a.c(aVar);
            }
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("width", b2);
            jSONObject.put("height", i);
        } catch (Throwable e2) {
            Logger.a(InternalLogLevel.INTERNAL, b, "Error while creating max size Json.", e2);
        }
        Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("getMaxSize called:").append(jSONObject.toString()).toString());
        return jSONObject.toString();
    }

    @JavascriptInterface
    public void close(String str) {
        Logger.a(InternalLogLevel.INTERNAL, b, "close called");
        new Handler(this.c.getRenderViewContext().getMainLooper()).post(new Runnable() {
            public void run() {
                a.this.c.k();
            }
        });
    }

    @JavascriptInterface
    public String getPlacementType(String str) {
        Logger.a(InternalLogLevel.INTERNAL, b, "getPlacementType called");
        return this.d.a() == PlacementType.FULL_SCREEN ? "interstitial" : "inline";
    }

    @JavascriptInterface
    @SuppressLint({"NewApi"})
    public void storePicture(String str, String str2) {
        Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("storePicture called with URL: ").append(str2).toString());
        if (!this.c.f("storePicture")) {
            Logger.a(InternalLogLevel.INTERNAL, b, "storePicture called despite the fact that it is not supported");
        } else if (str2 == null || str2.length() == 0) {
            this.c.a(str, "Null or empty URL supplied", "storePicture");
        } else if (!str2.startsWith(HttpConstant.HTTP) && !str2.startsWith("HTTP")) {
            this.c.a(str, "Invalid URL scheme - only HTTP(S) is supported", "storePicture");
        } else if (VERSION.SDK_INT < 23 || com.inmobi.commons.a.a.b().checkSelfPermission(MsgConstant.PERMISSION_WRITE_EXTERNAL_STORAGE) == 0) {
            a(str, str2);
        } else {
            InMobiAdActivity.a(new String[]{MsgConstant.PERMISSION_WRITE_EXTERNAL_STORAGE}, new AnonymousClass_2(str, str2));
        }
    }

    private void a(String str, String str2) {
        if (this.g == null) {
            this.g = (DownloadManager) com.inmobi.commons.a.a.b().getSystemService("download");
        }
        try {
            Uri parse = Uri.parse(str2);
            Request request = new Request(parse);
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, parse.getLastPathSegment());
            registerBroadcastListener(str);
            Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("Download enqueued with ID: ").append(this.g.enqueue(request)).toString());
        } catch (ParseException e) {
            Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("Invalid URL provided to storePicture ").append(e.getMessage()).toString());
            this.c.a(str, "Invalid URL", "storePicture");
        }
    }

    @SuppressLint({"NewApi"})
    public void registerBroadcastListener(String str) {
        if (this.h == null) {
            this.h = new AnonymousClass_3(str);
            com.inmobi.commons.a.a.b().registerReceiver(this.h, new IntentFilter(com.xunlei.download.DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        }
    }

    @SuppressLint({"NewApi"})
    public void unRegisterBroadcastListener() {
        if (this.h != null) {
            com.inmobi.commons.a.a.b().unregisterReceiver(this.h);
            this.h = null;
        }
    }

    @JavascriptInterface
    @TargetApi(23)
    public void createCalendarEvent(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11) {
        if (this.c == null) {
            Logger.a(InternalLogLevel.INTERNAL, b, "Found a null instance of render view!");
        } else if (!this.c.f("calendar")) {
            Logger.a(InternalLogLevel.INTERNAL, b, "createCalendarEvent called even when it is not supported");
        } else if (str3 == null || str3.trim().length() == 0 || str4 == null || str4.trim().length() == 0) {
            this.c.a(str, "Mandatory parameter(s) start and/or end date not supplied", "createCalendarEvent");
        } else {
            Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("createCalendarEvent called with parameters: \nevent ID: ").append(str2).append("; startDate: ").append(str3).append("; endDate: ").append(str4).append("; location: ").append(str5).append("; description: ").append(str6).append("; summary: ").append(str7).append("; status: ").append(str8).append("; transparency: ").append(str9).append("; recurrence: ").append(str10).append("; reminder: ").append(str11).toString());
            if (VERSION.SDK_INT < 23 || (com.inmobi.commons.a.a.b().checkSelfPermission("android.permission.WRITE_CALENDAR") == 0 && com.inmobi.commons.a.a.b().checkSelfPermission("android.permission.READ_CALENDAR") == 0)) {
                this.c.a(str, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11);
                return;
            }
            InMobiAdActivity.a(new String[]{"android.permission.WRITE_CALENDAR", "android.permission.READ_CALENDAR"}, new AnonymousClass_4(str, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11));
        }
    }

    @JavascriptInterface
    public void makeCall(String str, String str2) {
        Logger.a(InternalLogLevel.INTERNAL, b, "makeCall called even when it is not supported");
    }

    @JavascriptInterface
    public void sendMail(String str, String str2, String str3, String str4) {
        Logger.a(InternalLogLevel.INTERNAL, b, "sendMail called even when it is not supported");
    }

    @JavascriptInterface
    public void sendSMS(String str, String str2, String str3) {
        Logger.a(InternalLogLevel.INTERNAL, b, "sendSMS called even when it is not supported");
    }

    @JavascriptInterface
    @TargetApi(23)
    public void takeCameraPicture(String str) {
        Logger.a(InternalLogLevel.INTERNAL, b, "takeCameraPicture called even when it is not supported");
    }

    @JavascriptInterface
    @TargetApi(23)
    public void getGalleryImage(String str) {
        if (this.c.f("getGalleryImage")) {
            Logger.a(InternalLogLevel.INTERNAL, b, "getGalleryImage called ");
            if (VERSION.SDK_INT < 23 || com.inmobi.commons.a.a.b().checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") == 0) {
                b(str);
                return;
            }
            InMobiAdActivity.a(new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, new AnonymousClass_5(str));
            return;
        }
        Logger.a(InternalLogLevel.INTERNAL, b, "getGalleryImage called even when it is not supported");
    }

    private void b(String str) {
        int a = InMobiAdActivity.a(new Intent("android.intent.action.PICK", Media.EXTERNAL_CONTENT_URI), new AnonymousClass_6(str));
        Intent intent = new Intent(this.f, InMobiAdActivity.class);
        intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_ACTIVITY_TYPE", R.styleable.AppCompatTheme_checkedTextViewStyle);
        intent.putExtra(SocializeConstants.WEIBO_ID, a);
        com.inmobi.commons.a.a.a(this.f, intent);
        this.c.getListener().g(this.c);
    }

    @JavascriptInterface
    public void postToSocial(String str, int i, String str2, String str3, String str4) {
        if (this.c == null) {
            Logger.a(InternalLogLevel.INTERNAL, b, "Found a null instance of render view!");
            return;
        }
        Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("postToSocial called with parameters: socialType: ").append(i).append("; text: ").append(str2).append("; link: ").append(str3).append("; image URL: ").append(str4).toString());
        this.c.a(str, i, str2, str3, str4);
    }

    @JavascriptInterface
    public String getSdkVersion(String str) {
        Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("getSdkVersion called. Version:").append(com.inmobi.commons.a.b.c()).toString());
        return com.inmobi.commons.a.b.c();
    }

    @JavascriptInterface
    public String supports(String str, String str2) {
        Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("Checking support for: ").append(str2).toString());
        return (Arrays.asList(a).contains(str2) || this.c.f(str2)) ? String.valueOf(this.c.f(str2)) : Bugly.SDK_IS_DEV;
    }

    @JavascriptInterface
    public void openExternal(String str, String str2) {
        Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("openExternal called with url: ").append(str2).toString());
        if (this.c == null) {
            Logger.a(InternalLogLevel.INTERNAL, b, "Found a null instance of render view!");
        } else if (!str2.startsWith(HttpConstant.HTTP) || URLUtil.isValidUrl(str2)) {
            this.c.b("openExternal", str, str2);
        } else {
            Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("openExternal called with invalid url (").append(str2).append(SocializeConstants.OP_CLOSE_PAREN).toString());
            this.c.a(str, "Invalid URL", "openExternal");
        }
    }

    @JavascriptInterface
    public void asyncPing(String str, String str2) {
        Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("asyncPing called: ").append(str2).toString());
        if (URLUtil.isValidUrl(str2)) {
            NetworkRequest networkRequest = new NetworkRequest(RequestType.GET, str2, false, null);
            networkRequest.a(false);
            new com.inmobi.commons.core.network.a(networkRequest, new com.inmobi.commons.core.network.a.a() {
                public void a(com.inmobi.commons.core.network.c cVar) {
                    Logger.a(InternalLogLevel.INTERNAL, b, "asyncPing Successful");
                }

                public void b(com.inmobi.commons.core.network.c cVar) {
                    Logger.a(InternalLogLevel.INTERNAL, b, "asyncPing Failed");
                }
            }).a();
            return;
        }
        this.c.a(str, "Invalid url", "asyncPing");
    }

    @JavascriptInterface
    public void showAlert(String str, String str2) {
        Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("showAlert: ").append(str2).toString());
    }

    @JavascriptInterface
    public void playVideo(String str, String str2, boolean z, boolean z2, boolean z3, boolean z4, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
        if (this.c == null) {
            Logger.a(InternalLogLevel.INTERNAL, b, "Found a null instance of render view!");
            return;
        }
        new Handler(this.c.getRenderViewContext().getMainLooper()).post(new AnonymousClass_8(str2, str9, str, str5, str6, z2, z4, str7, str8, str4, str3, z, z3));
    }

    @JavascriptInterface
    public void seekVideo(String str, String str2, int i) {
        Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("JavaScript called: seekVideo (").append(str2).append(SocializeConstants.OP_CLOSE_PAREN).toString());
        if (this.c == null) {
            Logger.a(InternalLogLevel.INTERNAL, b, "Found a null instance of render view!");
        } else {
            new Handler(this.c.getRenderViewContext().getMainLooper()).post(new AnonymousClass_9(str, str2, i));
        }
    }

    @JavascriptInterface
    public void pauseVideo(String str, String str2) {
        Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("JavaScript called: pauseVideo (").append(str2).append(SocializeConstants.OP_CLOSE_PAREN).toString());
        if (this.c == null) {
            Logger.a(InternalLogLevel.INTERNAL, b, "Found a null instance of render view!");
        } else {
            new Handler(this.c.getRenderViewContext().getMainLooper()).post(new AnonymousClass_10(str, str2));
        }
    }

    @JavascriptInterface
    public void closeVideo(String str, String str2) {
        Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("JavaScript called: closeVideo (").append(str2).append(SocializeConstants.OP_CLOSE_PAREN).toString());
        if (this.c == null) {
            Logger.a(InternalLogLevel.INTERNAL, b, "Found a null instance of render view!");
        } else {
            new Handler(this.c.getRenderViewContext().getMainLooper()).post(new AnonymousClass_11(str, str2));
        }
    }

    @JavascriptInterface
    public void hideVideo(String str, String str2) {
        Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("JavaScript called: hideVideo (").append(str2).append(SocializeConstants.OP_CLOSE_PAREN).toString());
        if (this.c == null) {
            Logger.a(InternalLogLevel.INTERNAL, b, "Found a null instance of render view!");
        } else {
            new Handler(this.c.getRenderViewContext().getMainLooper()).post(new AnonymousClass_13(str, str2));
        }
    }

    @JavascriptInterface
    public void showVideo(String str, String str2) {
        Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("JavaScript called: showVideo (").append(str2).append(SocializeConstants.OP_CLOSE_PAREN).toString());
        if (this.c == null) {
            Logger.a(InternalLogLevel.INTERNAL, b, "Found a null instance of render view!");
        } else {
            new Handler(this.c.getRenderViewContext().getMainLooper()).post(new AnonymousClass_14(str, str2));
        }
    }

    @JavascriptInterface
    public void muteVideo(String str, String str2) {
        Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("JavaScript called: muteVideo (").append(str2).append(SocializeConstants.OP_CLOSE_PAREN).toString());
        if (this.c == null) {
            Logger.a(InternalLogLevel.INTERNAL, b, "Found a null instance of render view!");
        } else {
            new Handler(this.c.getRenderViewContext().getMainLooper()).post(new AnonymousClass_15(str, str2));
        }
    }

    @JavascriptInterface
    public void unMuteVideo(String str, String str2) {
        Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("JavaScript called: unMuteVideo (").append(str2).append(SocializeConstants.OP_CLOSE_PAREN).toString());
        if (this.c == null) {
            Logger.a(InternalLogLevel.INTERNAL, b, "Found a null instance of render view!");
        } else {
            new Handler(this.c.getRenderViewContext().getMainLooper()).post(new AnonymousClass_16(str, str2));
        }
    }

    @JavascriptInterface
    public boolean isVideoMuted(String str, String str2) {
        Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("JavaScript called: isVideoMuted (").append(str2).append(SocializeConstants.OP_CLOSE_PAREN).toString());
        if (this.c != null) {
            return this.c.f(str, str2);
        }
        Logger.a(InternalLogLevel.INTERNAL, b, "Found a null instance of render view!");
        return false;
    }

    @JavascriptInterface
    public void setVideoVolume(String str, String str2, int i) {
        Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("JavaScript called: setVideoVolume (").append(str2).append(", ").append(i).append(SocializeConstants.OP_CLOSE_PAREN).toString());
        if (this.c == null) {
            Logger.a(InternalLogLevel.INTERNAL, b, "Found a null instance of render view!");
        } else {
            new Handler(this.c.getRenderViewContext().getMainLooper()).post(new AnonymousClass_17(str, str2, i));
        }
    }

    @JavascriptInterface
    public int getVideoVolume(String str, String str2) {
        Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("JavaScript called: getVideoVolume (").append(str2).append(SocializeConstants.OP_CLOSE_PAREN).toString());
        if (this.c != null) {
            return this.c.e(str, str2);
        }
        Logger.a(InternalLogLevel.INTERNAL, b, "Found a null instance of render view!");
        return 0;
    }

    @JavascriptInterface
    public void playAudio(String str, String str2, boolean z, boolean z2, boolean z3, String str3, String str4, String str5) {
        Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("JavaScript called: playAudio (").append(str2).append(", ").append(str5).append(SocializeConstants.OP_CLOSE_PAREN).toString());
        if (this.c == null) {
            Logger.a(InternalLogLevel.INTERNAL, b, "Found a null instance of render view!");
        } else {
            new Handler(this.c.getRenderViewContext().getMainLooper()).post(new AnonymousClass_18(str5, str2, str, z, z3, str3, str4, z2));
        }
    }

    @JavascriptInterface
    public void seekAudio(String str, String str2, int i) {
        Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("JavaScript called: seekAudio (").append(str2).append(SocializeConstants.OP_CLOSE_PAREN).toString());
        if (this.c == null) {
            Logger.a(InternalLogLevel.INTERNAL, b, "Found a null instance of render view!");
        } else {
            new Handler(this.c.getRenderViewContext().getMainLooper()).post(new AnonymousClass_19(str, str2, i));
        }
    }

    @JavascriptInterface
    public void pauseAudio(String str, String str2) {
        Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("JavaScript called: pauseAudio (").append(str2).append(SocializeConstants.OP_CLOSE_PAREN).toString());
        if (this.c == null) {
            Logger.a(InternalLogLevel.INTERNAL, b, "Found a null instance of render view!");
        } else {
            new Handler(this.c.getRenderViewContext().getMainLooper()).post(new AnonymousClass_20(str, str2));
        }
    }

    @JavascriptInterface
    public void muteAudio(String str, String str2) {
        Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("JavaScript called: muteAudio (").append(str2).append(SocializeConstants.OP_CLOSE_PAREN).toString());
        if (this.c == null) {
            Logger.a(InternalLogLevel.INTERNAL, b, "Found a null instance of render view!");
        } else {
            new Handler(this.c.getRenderViewContext().getMainLooper()).post(new AnonymousClass_21(str, str2));
        }
    }

    @JavascriptInterface
    public void unMuteAudio(String str, String str2) {
        Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("JavaScript called: unMuteAudio (").append(str2).append(SocializeConstants.OP_CLOSE_PAREN).toString());
        if (this.c == null) {
            Logger.a(InternalLogLevel.INTERNAL, b, "Found a null instance of render view!");
        } else {
            new Handler(this.c.getRenderViewContext().getMainLooper()).post(new AnonymousClass_22(str, str2));
        }
    }

    @JavascriptInterface
    public boolean isAudioMuted(String str, String str2) {
        Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("JavaScript called: isAudioMuted (").append(str2).append(SocializeConstants.OP_CLOSE_PAREN).toString());
        if (this.c != null) {
            return this.c.f(str, str2);
        }
        Logger.a(InternalLogLevel.INTERNAL, b, "Found a null instance of render view!");
        return false;
    }

    @JavascriptInterface
    public void setAudioVolume(String str, String str2, int i) {
        Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("JavaScript called: setAudioVolume (").append(str2).append(", ").append(i).append(SocializeConstants.OP_CLOSE_PAREN).toString());
        if (this.c == null) {
            Logger.a(InternalLogLevel.INTERNAL, b, "Found a null instance of render view!");
        } else {
            new Handler(this.c.getRenderViewContext().getMainLooper()).post(new AnonymousClass_24(str, str2, i));
        }
    }

    @JavascriptInterface
    public int getAudioVolume(String str, String str2) {
        Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("JavaScript called: getAudioVolume (").append(str2).append(SocializeConstants.OP_CLOSE_PAREN).toString());
        if (this.c != null) {
            return this.c.e(str, str2);
        }
        Logger.a(InternalLogLevel.INTERNAL, b, "Found a null instance of render view!");
        return 0;
    }

    @JavascriptInterface
    public double getMicIntensity(String str) {
        Logger.a(InternalLogLevel.INTERNAL, b, "getMicIntensity called even when it is not supported");
        return -2.147483648E9d;
    }

    @JavascriptInterface
    public void disableCloseRegion(String str, boolean z) {
        if (this.c == null) {
            Logger.a(InternalLogLevel.INTERNAL, b, "Found a null instance of render view!");
        } else {
            new Handler(this.c.getRenderViewContext().getMainLooper()).post(new AnonymousClass_25(z));
        }
    }

    @JavascriptInterface
    public void onUserInteraction(String str, String str2) {
        Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("onUserInteraction called. Params:").append(str2).toString());
        if (str2 == null) {
            this.c.getListener().b(this.c, null);
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str2);
            HashMap hashMap = new HashMap();
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str3 = (String) keys.next();
                hashMap.put(str3, jSONObject.get(str3));
            }
            this.c.getListener().b(this.c, hashMap);
        } catch (JSONException e) {
            e.printStackTrace();
            this.c.getListener().b(this.c, null);
        }
    }

    @JavascriptInterface
    public void incentCompleted(String str, String str2) {
        Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("incentCompleted called. IncentData:").append(str2).toString());
        if (str2 == null) {
            this.c.getListener().a(this.c, null);
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str2);
            HashMap hashMap = new HashMap();
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str3 = (String) keys.next();
                hashMap.put(str3, jSONObject.get(str3));
            }
            this.c.getListener().a(this.c, hashMap);
        } catch (JSONException e) {
            e.printStackTrace();
            this.c.getListener().a(this.c, null);
        }
    }

    @JavascriptInterface
    public void vibrate(String str) {
        if (this.c == null) {
            Logger.a(InternalLogLevel.INTERNAL, b, "Found a null instance of render view!");
            return;
        }
        Logger.a(InternalLogLevel.INTERNAL, b, "Vibrate called");
        new Handler(this.c.getRenderViewContext().getMainLooper()).post(new AnonymousClass_26(str));
    }

    @JavascriptInterface
    public void vibrate(String str, String str2, int i) {
        if (this.c == null) {
            Logger.a(InternalLogLevel.INTERNAL, b, "Found a null instance of render view!");
            return;
        }
        Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("Vibrate called with pattern ").append(str2).toString());
        new Handler(this.c.getRenderViewContext().getMainLooper()).post(new AnonymousClass_27(str, str2, i));
    }

    @JavascriptInterface
    public String getOrientation(String str) {
        Logger.a(InternalLogLevel.INTERNAL, b, "getOrientation called");
        int b = DisplayInfo.b();
        if (b == ORIENTATION_VALUES.PORTRAIT.getValue()) {
            return MessageService.MSG_DB_READY_REPORT;
        }
        if (b == ORIENTATION_VALUES.LANDSCAPE.getValue()) {
            return "90";
        }
        if (b == ORIENTATION_VALUES.REVERSE_PORTRAIT.getValue()) {
            return "180";
        }
        return b == ORIENTATION_VALUES.REVERSE_LANDSCAPE.getValue() ? "270" : WeiboAuthException.DEFAULT_AUTH_ERROR_CODE;
    }

    @JavascriptInterface
    public void saveContent(String str, String str2, String str3) {
        if (str2 == null || str2.length() == 0 || str3 == null || str3.length() == 0) {
            String str4;
            Logger.a(InternalLogLevel.INTERNAL, b, "saveContent called with invalid parameters");
            JSONObject jSONObject = new JSONObject();
            try {
                str4 = SocialConstants.PARAM_URL;
                if (str3 == null) {
                    str3 = com.umeng.a.d;
                }
                jSONObject.put(str4, str3);
                jSONObject.put(com.xunlei.download.DownloadManager.COLUMN_REASON, 1);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            str4 = jSONObject.toString().replace(h.f, "\\\"");
            StringBuilder stringBuilder = new StringBuilder("sendSaveContentResult(\"saveContent_");
            if (str2 == null) {
                str2 = com.umeng.a.d;
            }
            this.c.a(str, stringBuilder.append(str2).append("\", 'failed', \"").append(str4).append("\");").toString());
            return;
        }
        this.c.d(str, str2, str3);
    }

    @JavascriptInterface
    public void cancelSaveContent(String str, String str2) {
        Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("cancelSaveContent called. mediaId:").append(str2).toString());
        this.c.e(str2);
    }

    @JavascriptInterface
    @TargetApi(23)
    public void registerMicListener(String str) {
        Logger.a(InternalLogLevel.INTERNAL, b, "registerMicListener called even when it is not supported");
    }

    @JavascriptInterface
    @TargetApi(23)
    public void unregisterMicListener(String str) {
        Logger.a(InternalLogLevel.INTERNAL, b, "unRegisterMicListener called even when it is not supported");
    }

    @JavascriptInterface
    public String isDeviceMuted(String str) {
        if (this.c == null) {
            Logger.a(InternalLogLevel.INTERNAL, b, "Found a null instance of render view!");
            return Bugly.SDK_IS_DEV;
        }
        Logger.a(InternalLogLevel.INTERNAL, b, "JavaScript called: isDeviceMuted()");
        return String.valueOf(this.c.getMediaProcessor().d());
    }

    @JavascriptInterface
    public String isHeadphonePlugged(String str) {
        if (this.c == null) {
            Logger.a(InternalLogLevel.INTERNAL, b, "Found a null instance of render view!");
            return Bugly.SDK_IS_DEV;
        }
        Logger.a(InternalLogLevel.INTERNAL, b, "JavaScript called: isHeadphonePlugged()");
        return String.valueOf(this.c.getMediaProcessor().g());
    }

    @JavascriptInterface
    public void registerDeviceMuteEventListener(String str) {
        if (this.c == null) {
            Logger.a(InternalLogLevel.INTERNAL, b, "Found a null instance of render view!");
        } else {
            this.c.getMediaProcessor().a(str);
        }
    }

    @JavascriptInterface
    public void unregisterDeviceMuteEventListener(String str) {
        if (this.c == null) {
            Logger.a(InternalLogLevel.INTERNAL, b, "Found a null instance of render view!");
            return;
        }
        Logger.a(InternalLogLevel.INTERNAL, b, "Unregister device mute event listener ...");
        this.c.getMediaProcessor().e();
    }

    @JavascriptInterface
    public void registerDeviceVolumeChangeEventListener(String str) {
        if (this.c == null) {
            Logger.a(InternalLogLevel.INTERNAL, b, "Found a null instance of render view!");
        } else {
            this.c.getMediaProcessor().b(str);
        }
    }

    @JavascriptInterface
    public void unregisterDeviceVolumeChangeEventListener(String str) {
        if (this.c == null) {
            Logger.a(InternalLogLevel.INTERNAL, b, "Found a null instance of render view!");
            return;
        }
        Logger.a(InternalLogLevel.INTERNAL, b, "Unregister device volume change listener ...");
        this.c.getMediaProcessor().f();
    }

    @JavascriptInterface
    public void registerHeadphonePluggedEventListener(String str) {
        if (this.c == null) {
            Logger.a(InternalLogLevel.INTERNAL, b, "Found a null instance of render view!");
        } else {
            this.c.getMediaProcessor().c(str);
        }
    }

    @JavascriptInterface
    public void unregisterHeadphonePluggedEventListener(String str) {
        if (this.c == null) {
            Logger.a(InternalLogLevel.INTERNAL, b, "Found a null instance of render view!");
            return;
        }
        Logger.a(InternalLogLevel.INTERNAL, b, "Unregister headphone plugged event listener ...");
        this.c.getMediaProcessor().h();
    }

    @JavascriptInterface
    public void disableBackButton(String str, boolean z) {
        if (this.c == null) {
            Logger.a(InternalLogLevel.INTERNAL, b, "Found a null instance of render view!");
        } else {
            this.c.setDisableBackButton(z);
        }
    }

    @JavascriptInterface
    public boolean isBackButtonDisabled(String str) {
        if (this.c != null) {
            return this.c.g();
        }
        Logger.a(InternalLogLevel.INTERNAL, b, "Found a null instance of render view!");
        return false;
    }

    @JavascriptInterface
    public void registerBackButtonPressedEventListener(String str) {
        if (this.c == null) {
            Logger.a(InternalLogLevel.INTERNAL, b, "Found a null instance of render view!");
        } else {
            this.c.c(str);
        }
    }

    @JavascriptInterface
    public void unregisterBackButtonPressedEventListener(String str) {
        if (this.c == null) {
            Logger.a(InternalLogLevel.INTERNAL, b, "Found a null instance of render view!");
        } else {
            this.c.h();
        }
    }

    private static int c(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return -99999;
        }
    }
}
