package com.tencent.open.b;

import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import anet.channel.util.HttpConstant;
import com.alipay.sdk.packet.d;
import com.taobao.agoo.a.a.b;
import com.tencent.connect.common.Constants;
import com.tencent.open.SocialConstants;
import com.tencent.open.a.f;
import com.tencent.open.utils.Global;
import com.tencent.open.utils.HttpUtils;
import com.tencent.open.utils.OpenConfig;
import com.tencent.open.utils.ServerSetting;
import com.tencent.open.utils.ThreadManager;
import com.tencent.open.utils.Util;
import com.umeng.a;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.sdk.IHost;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;
import java.util.concurrent.Executor;
import org.android.agoo.message.MessageService;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.json.JSONArray;
import org.json.JSONObject;

// compiled from: ProGuard
public class g {
    protected static g a;
    protected Random b;
    protected List<Serializable> c;
    protected List<Serializable> d;
    protected HandlerThread e;
    protected Handler f;
    protected Executor g;
    protected Executor h;

    // compiled from: ProGuard
    class AnonymousClass_1 extends Handler {
        AnonymousClass_1(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case IHost.HOST_NOFITY_REFRESH_LIST:
                    g.this.b();
                    break;
                case IHost.HOST_NOFITY_PAGE_SELECTED:
                    g.this.e();
                    break;
            }
            super.handleMessage(message);
        }
    }

    // compiled from: ProGuard
    class AnonymousClass_2 implements Runnable {
        final /* synthetic */ Bundle a;
        final /* synthetic */ boolean b;

        AnonymousClass_2(Bundle bundle, boolean z) {
            this.a = bundle;
            this.b = z;
        }

        public void run() {
            try {
                Bundle bundle = new Bundle();
                bundle.putString("uin", Constants.DEFAULT_UIN);
                bundle.putString(com.taobao.accs.common.Constants.KEY_IMEI, c.b(Global.getContext()));
                bundle.putString(com.taobao.accs.common.Constants.KEY_IMSI, c.c(Global.getContext()));
                bundle.putString("android_id", c.d(Global.getContext()));
                bundle.putString("mac", c.a());
                bundle.putString(Constants.PARAM_PLATFORM, MessageService.MSG_DB_NOTIFY_REACHED);
                bundle.putString("os_ver", VERSION.RELEASE);
                bundle.putString("position", Util.getLocation(Global.getContext()));
                bundle.putString("network", a.a(Global.getContext()));
                bundle.putString("language", c.b());
                bundle.putString("resolution", c.a(Global.getContext()));
                bundle.putString("apn", a.b(Global.getContext()));
                bundle.putString("model_name", Build.MODEL);
                bundle.putString("timezone", TimeZone.getDefault().getID());
                bundle.putString("sdk_ver", Constants.SDK_VERSION);
                bundle.putString("qz_ver", Util.getAppVersionName(Global.getContext(), Constants.PACKAGE_QZONE));
                bundle.putString("qq_ver", Util.getVersionName(Global.getContext(), Constants.PACKAGE_QQ));
                bundle.putString("qua", Util.getQUA3(Global.getContext(), Global.getPackageName()));
                bundle.putString(LogBuilder.KEY_PACKAGE_NAME, Global.getPackageName());
                bundle.putString("app_ver", Util.getAppVersionName(Global.getContext(), Global.getPackageName()));
                if (this.a != null) {
                    bundle.putAll(this.a);
                }
                g.this.d.add(new b(bundle));
                int size = g.this.d.size();
                int i = OpenConfig.getInstance(Global.getContext(), null).getInt("Agent_ReportTimeInterval");
                if (i == 0) {
                    i = 10000;
                }
                if (g.this.a("report_via", size) || this.b) {
                    g.this.e();
                    g.this.f.removeMessages(IHost.HOST_NOFITY_PAGE_SELECTED);
                } else if (!g.this.f.hasMessages(IHost.HOST_NOFITY_PAGE_SELECTED)) {
                    Message obtain = Message.obtain();
                    obtain.what = 1001;
                    g.this.f.sendMessageDelayed(obtain, (long) i);
                }
            } catch (Throwable e) {
                f.b("openSDK_LOG.ReportManager", "--> reporVia, exception in sub thread.", e);
            }
        }
    }

    // compiled from: ProGuard
    class AnonymousClass_3 implements Runnable {
        final /* synthetic */ long a;
        final /* synthetic */ String b;
        final /* synthetic */ String c;
        final /* synthetic */ int d;
        final /* synthetic */ long e;
        final /* synthetic */ long f;
        final /* synthetic */ boolean g;

        AnonymousClass_3(long j, String str, String str2, int i, long j2, long j3, boolean z) {
            this.a = j;
            this.b = str;
            this.c = str2;
            this.d = i;
            this.e = j2;
            this.f = j3;
            this.g = z;
        }

        public void run() {
            int i = 1;
            try {
                long elapsedRealtime = SystemClock.elapsedRealtime() - this.a;
                Bundle bundle = new Bundle();
                String a = a.a(Global.getContext());
                bundle.putString("apn", a);
                bundle.putString(SocialConstants.PARAM_APP_ID, "1000067");
                bundle.putString("commandid", this.b);
                bundle.putString(JsInterface.PAGE_DETAIL, this.c);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("network=").append(a).append('&');
                stringBuilder.append("sdcard=").append(Environment.getExternalStorageState().equals("mounted") ? 1 : 0).append('&');
                stringBuilder.append("wifi=").append(a.e(Global.getContext()));
                bundle.putString("deviceInfo", stringBuilder.toString());
                int a2 = 100 / g.this.a(this.d);
                if (a2 > 0) {
                    if (a2 > 100) {
                        i = 100;
                    } else {
                        i = a2;
                    }
                }
                bundle.putString("frequency", String.valueOf(i));
                bundle.putString("reqSize", this.e);
                bundle.putString(b.JSON_ERRORCODE, this.d);
                bundle.putString("rspSize", this.f);
                bundle.putString("timeCost", String.valueOf(elapsedRealtime));
                bundle.putString("uin", Constants.DEFAULT_UIN);
                g.this.c.add(new b(bundle));
                int size = g.this.c.size();
                i = OpenConfig.getInstance(Global.getContext(), null).getInt("Agent_ReportTimeInterval");
                if (i == 0) {
                    i = 10000;
                }
                if (g.this.a("report_cgi", size) || this.g) {
                    g.this.b();
                    g.this.f.removeMessages(IHost.HOST_NOFITY_REFRESH_LIST);
                } else if (!g.this.f.hasMessages(IHost.HOST_NOFITY_REFRESH_LIST)) {
                    Message obtain = Message.obtain();
                    obtain.what = 1000;
                    g.this.f.sendMessageDelayed(obtain, (long) i);
                }
            } catch (Throwable e) {
                f.b("openSDK_LOG.ReportManager", "--> reportCGI, exception in sub thread.", e);
            }
        }
    }

    // compiled from: ProGuard
    class AnonymousClass_6 implements Runnable {
        final /* synthetic */ Bundle a;
        final /* synthetic */ String b;
        final /* synthetic */ boolean c;
        final /* synthetic */ String d;

        AnonymousClass_6(Bundle bundle, String str, boolean z, String str2) {
            this.a = bundle;
            this.b = str;
            this.c = z;
            this.d = str2;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.open.b.g.AnonymousClass_6.run():void");
            /*
            this = this;
            r2 = 1;
            r0 = 0;
            r1 = r10.a;	 Catch:{ Exception -> 0x00bc }
            if (r1 != 0) goto L_0x0010;
        L_0x0006:
            r0 = "openSDK_LOG.ReportManager";
            r1 = "-->httpRequest, params is null!";
            com.tencent.open.a.f.e(r0, r1);	 Catch:{ Exception -> 0x00bc }
        L_0x000f:
            return;
        L_0x0010:
            r1 = com.tencent.open.b.e.a();	 Catch:{ Exception -> 0x00bc }
            if (r1 != 0) goto L_0x00c8;
        L_0x0016:
            r1 = 3;
            r4 = r1;
        L_0x0018:
            r1 = "openSDK_LOG.ReportManager";
            r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00bc }
            r5 = "-->httpRequest, retryCount: ";
            r3.<init>(r5);	 Catch:{ Exception -> 0x00bc }
            r3 = r3.append(r4);	 Catch:{ Exception -> 0x00bc }
            r3 = r3.toString();	 Catch:{ Exception -> 0x00bc }
            com.tencent.open.a.f.b(r1, r3);	 Catch:{ Exception -> 0x00bc }
            r1 = com.tencent.open.utils.Global.getContext();	 Catch:{ Exception -> 0x00bc }
            r3 = 0;
            r5 = r10.b;	 Catch:{ Exception -> 0x00bc }
            r5 = com.tencent.open.utils.HttpUtils.getHttpClient(r1, r3, r5);	 Catch:{ Exception -> 0x00bc }
            r1 = r10.a;	 Catch:{ Exception -> 0x00bc }
            r1 = com.tencent.open.utils.HttpUtils.encodeUrl(r1);	 Catch:{ Exception -> 0x00bc }
            r3 = r10.c;	 Catch:{ Exception -> 0x00bc }
            if (r3 == 0) goto L_0x013d;
        L_0x0043:
            r1 = java.net.URLEncoder.encode(r1);	 Catch:{ Exception -> 0x00bc }
            r3 = r1;
        L_0x0048:
            r1 = r10.d;	 Catch:{ Exception -> 0x00bc }
            r1 = r1.toUpperCase();	 Catch:{ Exception -> 0x00bc }
            r6 = "GET";
            r1 = r1.equals(r6);	 Catch:{ Exception -> 0x00bc }
            if (r1 == 0) goto L_0x00cb;
        L_0x0057:
            r6 = new java.lang.StringBuffer;	 Catch:{ Exception -> 0x00bc }
            r1 = r10.b;	 Catch:{ Exception -> 0x00bc }
            r6.<init>(r1);	 Catch:{ Exception -> 0x00bc }
            r6.append(r3);	 Catch:{ Exception -> 0x00bc }
            r1 = new org.apache.http.client.methods.HttpGet;	 Catch:{ Exception -> 0x00bc }
            r3 = r6.toString();	 Catch:{ Exception -> 0x00bc }
            r1.<init>(r3);	 Catch:{ Exception -> 0x00bc }
            r3 = r1;
        L_0x006b:
            r1 = "Accept-Encoding";
            r6 = "gzip";
            r3.addHeader(r1, r6);	 Catch:{ Exception -> 0x00bc }
            r1 = "Content-Type";
            r6 = "application/x-www-form-urlencoded";
            r3.addHeader(r1, r6);	 Catch:{ Exception -> 0x00bc }
            r1 = r0;
        L_0x007e:
            r1 = r1 + 1;
            r6 = r5.execute(r3);	 Catch:{ ConnectTimeoutException -> 0x0106, SocketTimeoutException -> 0x0113, Exception -> 0x011e }
            r6 = r6.getStatusLine();	 Catch:{ ConnectTimeoutException -> 0x0106, SocketTimeoutException -> 0x0113, Exception -> 0x011e }
            r6 = r6.getStatusCode();	 Catch:{ ConnectTimeoutException -> 0x0106, SocketTimeoutException -> 0x0113, Exception -> 0x011e }
            r7 = "openSDK_LOG.ReportManager";
            r8 = new java.lang.StringBuilder;	 Catch:{ ConnectTimeoutException -> 0x0106, SocketTimeoutException -> 0x0113, Exception -> 0x011e }
            r9 = "-->httpRequest, statusCode: ";
            r8.<init>(r9);	 Catch:{ ConnectTimeoutException -> 0x0106, SocketTimeoutException -> 0x0113, Exception -> 0x011e }
            r8 = r8.append(r6);	 Catch:{ ConnectTimeoutException -> 0x0106, SocketTimeoutException -> 0x0113, Exception -> 0x011e }
            r8 = r8.toString();	 Catch:{ ConnectTimeoutException -> 0x0106, SocketTimeoutException -> 0x0113, Exception -> 0x011e }
            com.tencent.open.a.f.b(r7, r8);	 Catch:{ ConnectTimeoutException -> 0x0106, SocketTimeoutException -> 0x0113, Exception -> 0x011e }
            r7 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
            if (r6 == r7) goto L_0x00fb;
        L_0x00a6:
            r6 = "openSDK_LOG.ReportManager";
            r7 = "-->ReportCenter httpRequest : HttpStatuscode != 200";
            com.tencent.open.a.f.b(r6, r7);	 Catch:{ ConnectTimeoutException -> 0x0106, SocketTimeoutException -> 0x0113, Exception -> 0x011e }
        L_0x00af:
            if (r0 != r2) goto L_0x0129;
        L_0x00b1:
            r0 = "openSDK_LOG.ReportManager";
            r1 = "-->ReportCenter httpRequest Thread request success";
            com.tencent.open.a.f.b(r0, r1);	 Catch:{ Exception -> 0x00bc }
            goto L_0x000f;
        L_0x00bc:
            r0 = move-exception;
            r0 = "openSDK_LOG.ReportManager";
            r1 = "-->httpRequest, exception in serial executor.";
            com.tencent.open.a.f.b(r0, r1);
            goto L_0x000f;
        L_0x00c8:
            r4 = r1;
            goto L_0x0018;
        L_0x00cb:
            r1 = r10.d;	 Catch:{ Exception -> 0x00bc }
            r1 = r1.toUpperCase();	 Catch:{ Exception -> 0x00bc }
            r6 = "POST";
            r1 = r1.equals(r6);	 Catch:{ Exception -> 0x00bc }
            if (r1 == 0) goto L_0x00f0;
        L_0x00da:
            r1 = new org.apache.http.client.methods.HttpPost;	 Catch:{ Exception -> 0x00bc }
            r6 = r10.b;	 Catch:{ Exception -> 0x00bc }
            r1.<init>(r6);	 Catch:{ Exception -> 0x00bc }
            r3 = com.tencent.open.utils.Util.getBytesUTF8(r3);	 Catch:{ Exception -> 0x00bc }
            r6 = new org.apache.http.entity.ByteArrayEntity;	 Catch:{ Exception -> 0x00bc }
            r6.<init>(r3);	 Catch:{ Exception -> 0x00bc }
            r1.setEntity(r6);	 Catch:{ Exception -> 0x00bc }
            r3 = r1;
            goto L_0x006b;
        L_0x00f0:
            r0 = "openSDK_LOG.ReportManager";
            r1 = "-->httpRequest unkonw request method return.";
            com.tencent.open.a.f.e(r0, r1);	 Catch:{ Exception -> 0x00bc }
            goto L_0x000f;
        L_0x00fb:
            r0 = "openSDK_LOG.ReportManager";
            r6 = "-->ReportCenter httpRequest Thread success";
            com.tencent.open.a.f.b(r0, r6);	 Catch:{ ConnectTimeoutException -> 0x013a, SocketTimeoutException -> 0x0137, Exception -> 0x0134 }
            r0 = r2;
            goto L_0x00af;
        L_0x0106:
            r6 = move-exception;
        L_0x0107:
            r6 = "openSDK_LOG.ReportManager";
            r7 = "-->ReportCenter httpRequest ConnectTimeoutException";
            com.tencent.open.a.f.b(r6, r7);	 Catch:{ Exception -> 0x00bc }
        L_0x0110:
            if (r1 < r4) goto L_0x007e;
        L_0x0112:
            goto L_0x00af;
        L_0x0113:
            r6 = move-exception;
        L_0x0114:
            r6 = "openSDK_LOG.ReportManager";
            r7 = "-->ReportCenter httpRequest SocketTimeoutException";
            com.tencent.open.a.f.b(r6, r7);	 Catch:{ Exception -> 0x00bc }
            goto L_0x0110;
        L_0x011e:
            r1 = move-exception;
        L_0x011f:
            r1 = "openSDK_LOG.ReportManager";
            r3 = "-->ReportCenter httpRequest Exception";
            com.tencent.open.a.f.b(r1, r3);	 Catch:{ Exception -> 0x00bc }
            goto L_0x00af;
        L_0x0129:
            r0 = "openSDK_LOG.ReportManager";
            r1 = "-->ReportCenter httpRequest Thread request failed";
            com.tencent.open.a.f.b(r0, r1);	 Catch:{ Exception -> 0x00bc }
            goto L_0x000f;
        L_0x0134:
            r0 = move-exception;
            r0 = r2;
            goto L_0x011f;
        L_0x0137:
            r0 = move-exception;
            r0 = r2;
            goto L_0x0114;
        L_0x013a:
            r0 = move-exception;
            r0 = r2;
            goto L_0x0107;
        L_0x013d:
            r3 = r1;
            goto L_0x0048;
            */
        }
    }

    public static synchronized g a() {
        g gVar;
        synchronized (g.class) {
            if (a == null) {
                a = new g();
            }
            gVar = a;
        }
        return gVar;
    }

    private g() {
        this.e = null;
        this.b = new Random();
        this.d = Collections.synchronizedList(new ArrayList());
        this.c = Collections.synchronizedList(new ArrayList());
        this.g = ThreadManager.newSerialExecutor();
        this.h = ThreadManager.newSerialExecutor();
        if (this.e == null) {
            this.e = new HandlerThread("opensdk.report.handlerthread", 10);
            this.e.start();
        }
        if (this.e.isAlive() && this.e.getLooper() != null) {
            this.f = new AnonymousClass_1(this.e.getLooper());
        }
    }

    public void a(Bundle bundle, String str, boolean z) {
        if (bundle != null) {
            f.a("openSDK_LOG.ReportManager", new StringBuilder("-->reportVia, bundle: ").append(bundle.toString()).toString());
            if (a("report_via", str) || z) {
                this.g.execute(new AnonymousClass_2(bundle, z));
            }
        }
    }

    public void a(String str, long j, long j2, long j3, int i) {
        a(str, j, j2, j3, i, a.d, false);
    }

    public void a(String str, long j, long j2, long j3, int i, String str2, boolean z) {
        f.a("openSDK_LOG.ReportManager", new StringBuilder("-->reportCgi, command: ").append(str).append(" | startTime: ").append(j).append(" | reqSize:").append(j2).append(" | rspSize: ").append(j3).append(" | responseCode: ").append(i).append(" | detail: ").append(str2).toString());
        if (a("report_cgi", String.valueOf(i)) || z) {
            this.h.execute(new AnonymousClass_3(j, str, str2, i, j2, j3, z));
        }
    }

    protected void b() {
        this.h.execute(new Runnable() {
            public void run() {
                Object obj = null;
                try {
                    Bundle c = g.this.c();
                    if (c != null) {
                        int i = OpenConfig.getInstance(Global.getContext(), null).getInt("Common_HttpRetryCount");
                        int i2 = i == 0 ? 3 : i;
                        f.b("openSDK_LOG.ReportManager", new StringBuilder("-->doReportCgi, retryCount: ").append(i2).toString());
                        i = 0;
                        do {
                            i++;
                            try {
                                HttpClient httpClient = HttpUtils.getHttpClient(Global.getContext(), null, ServerSetting.DEFAULT_URL_REPORT);
                                HttpUriRequest httpPost = new HttpPost(ServerSetting.DEFAULT_URL_REPORT);
                                httpPost.addHeader(HttpConstant.ACCEPT_ENCODING, HttpConstant.GZIP);
                                httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
                                httpPost.setEntity(new ByteArrayEntity(Util.getBytesUTF8(HttpUtils.encodeUrl(c))));
                                int statusCode = httpClient.execute(httpPost).getStatusLine().getStatusCode();
                                f.b("openSDK_LOG.ReportManager", new StringBuilder("-->doReportCgi, statusCode: ").append(statusCode).toString());
                                if (statusCode == 200) {
                                    f.a().b("report_cgi");
                                    obj = 1;
                                }
                            } catch (Throwable e) {
                                f.b("openSDK_LOG.ReportManager", "-->doReportCgi, doupload exception", e);
                                continue;
                            } catch (Throwable e2) {
                                f.b("openSDK_LOG.ReportManager", "-->doReportCgi, doupload exception", e2);
                                continue;
                            } catch (Throwable e3) {
                                f.b("openSDK_LOG.ReportManager", "-->doReportCgi, doupload exception", e3);
                            }
                            if (obj == null) {
                                f.a().a("report_cgi", g.this.c);
                            }
                            g.this.c.clear();
                        } while (i < i2);
                        if (obj == null) {
                            f.a().a("report_cgi", g.this.c);
                        }
                        g.this.c.clear();
                    }
                } catch (Throwable e32) {
                    f.b("openSDK_LOG.ReportManager", "-->doReportCgi, doupload exception out.", e32);
                }
            }
        });
    }

    protected boolean a(String str, String str2) {
        f.b("openSDK_LOG.ReportManager", new StringBuilder("-->availableFrequency, report: ").append(str).append(" | ext: ").append(str2).toString());
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        int a;
        boolean z;
        if (str.equals("report_cgi")) {
            try {
                a = a(Integer.parseInt(str2));
                if (this.b.nextInt(R.styleable.AppCompatTheme_buttonStyle) < a) {
                    z = true;
                } else {
                    z = false;
                }
            } catch (Exception e) {
                return false;
            }
        } else if (str.equals("report_via")) {
            a = e.a(str2);
            if (this.b.nextInt(R.styleable.AppCompatTheme_buttonStyle) < a) {
                z = true;
            } else {
                z = false;
            }
        } else {
            a = 100;
            z = false;
        }
        f.b("openSDK_LOG.ReportManager", new StringBuilder("-->availableFrequency, result: ").append(z).append(" | frequency: ").append(a).toString());
        return z;
    }

    protected boolean a(String str, int i) {
        int i2 = XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED;
        int i3;
        if (str.equals("report_cgi")) {
            i3 = OpenConfig.getInstance(Global.getContext(), null).getInt("Common_CGIReportMaxcount");
            if (i3 != 0) {
                i2 = i3;
            }
        } else if (str.equals("report_via")) {
            i3 = OpenConfig.getInstance(Global.getContext(), null).getInt("Agent_ReportBatchCount");
            if (i3 != 0) {
                i2 = i3;
            }
        } else {
            i2 = 0;
        }
        f.b("openSDK_LOG.ReportManager", new StringBuilder("-->availableCount, report: ").append(str).append(" | dataSize: ").append(i).append(" | maxcount: ").append(i2).toString());
        return i >= i2;
    }

    protected int a(int i) {
        int i2;
        if (i == 0) {
            i2 = OpenConfig.getInstance(Global.getContext(), null).getInt("Common_CGIReportFrequencySuccess");
            return i2 == 0 ? XZBDevice.Stop : i2;
        } else {
            i2 = OpenConfig.getInstance(Global.getContext(), null).getInt("Common_CGIReportFrequencyFailed");
            return i2 == 0 ? R.styleable.AppCompatTheme_buttonStyle : i2;
        }
    }

    protected Bundle c() {
        if (this.c.size() == 0) {
            return null;
        }
        b bVar = (b) this.c.get(0);
        if (bVar == null) {
            f.b("openSDK_LOG.ReportManager", "-->prepareCgiData, the 0th cgireportitem is null.");
            return null;
        }
        String str = (String) bVar.a.get(SocialConstants.PARAM_APP_ID);
        Collection a = f.a().a("report_cgi");
        if (a != null) {
            this.c.addAll(a);
        }
        f.b("openSDK_LOG.ReportManager", new StringBuilder("-->prepareCgiData, mCgiList size: ").append(this.c.size()).toString());
        if (this.c.size() == 0) {
            return null;
        }
        Bundle bundle = new Bundle();
        try {
            bundle.putString(SocialConstants.PARAM_APP_ID, str);
            bundle.putString("releaseversion", Constants.SDK_VERSION_REPORT);
            bundle.putString(d.n, Build.DEVICE);
            bundle.putString("qua", Constants.SDK_QUA);
            bundle.putString("key", "apn,frequency,commandid,resultcode,tmcost,reqsize,rspsize,detail,touin,deviceinfo");
            for (int i = 0; i < this.c.size(); i++) {
                bVar = (b) this.c.get(i);
                bundle.putString(i + "_1", (String) bVar.a.get("apn"));
                bundle.putString(i + "_2", (String) bVar.a.get("frequency"));
                bundle.putString(i + "_3", (String) bVar.a.get("commandid"));
                bundle.putString(i + "_4", (String) bVar.a.get(b.JSON_ERRORCODE));
                bundle.putString(i + "_5", (String) bVar.a.get("timeCost"));
                bundle.putString(i + "_6", (String) bVar.a.get("reqSize"));
                bundle.putString(i + "_7", (String) bVar.a.get("rspSize"));
                bundle.putString(i + "_8", (String) bVar.a.get(JsInterface.PAGE_DETAIL));
                bundle.putString(i + "_9", (String) bVar.a.get("uin"));
                bundle.putString(i + "_10", c.e(Global.getContext()) + com.alipay.sdk.sys.a.b + ((String) bVar.a.get("deviceInfo")));
            }
            f.a("openSDK_LOG.ReportManager", new StringBuilder("-->prepareCgiData, end. params: ").append(bundle.toString()).toString());
            return bundle;
        } catch (Throwable e) {
            f.b("openSDK_LOG.ReportManager", "-->prepareCgiData, exception.", e);
            return null;
        }
    }

    protected Bundle d() {
        Collection a = f.a().a("report_via");
        if (a != null) {
            this.d.addAll(a);
        }
        f.b("openSDK_LOG.ReportManager", new StringBuilder("-->prepareViaData, mViaList size: ").append(this.d.size()).toString());
        if (this.d.size() == 0) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        for (Serializable serializable : this.d) {
            JSONObject jSONObject = new JSONObject();
            b bVar = (b) serializable;
            for (String str : bVar.a.keySet()) {
                try {
                    Object obj;
                    String str2 = (String) bVar.a.get(str);
                    if (str2 == null) {
                        obj = a.d;
                    }
                    jSONObject.put(str, obj);
                } catch (Throwable e) {
                    f.b("openSDK_LOG.ReportManager", "-->prepareViaData, put bundle to json array exception", e);
                }
            }
            jSONArray.put(jSONObject);
        }
        f.a("openSDK_LOG.ReportManager", new StringBuilder("-->prepareViaData, JSONArray array: ").append(jSONArray.toString()).toString());
        Bundle bundle = new Bundle();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put(SocializeConstants.JSON_DATA, jSONArray);
            bundle.putString(SocializeConstants.JSON_DATA, jSONObject2.toString());
            return bundle;
        } catch (Throwable e2) {
            f.b("openSDK_LOG.ReportManager", "-->prepareViaData, put bundle to json array exception", e2);
            return null;
        }
    }

    protected void e() {
        this.g.execute(new Runnable() {
            public void run() {
                throw new UnsupportedOperationException("Method not decompiled: com.tencent.open.b.g.AnonymousClass_5.run():void");
                /* JADX: method processing error */
/*
                Error: jadx.core.utils.exceptions.JadxRuntimeException: Try/catch wrap count limit reached in com.tencent.open.b.g.AnonymousClass_5.run():void
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:54)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:40)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:16)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
	at jadx.core.ProcessClass.process(ProcessClass.java:22)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:209)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:133)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(Unknown Source)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(Unknown Source)
	at java.lang.Thread.run(Unknown Source)
*/
                /*
                this = this;
                r0 = r18;
                r2 = com.tencent.open.b.g.this;	 Catch:{ Exception -> 0x00a4 }
                r14 = r2.d();	 Catch:{ Exception -> 0x00a4 }
                if (r14 != 0) goto L_0x000b;
            L_0x000a:
                return;
            L_0x000b:
                r2 = "openSDK_LOG.ReportManager";
                r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00a4 }
                r4 = "-->doReportVia, params: ";
                r3.<init>(r4);	 Catch:{ Exception -> 0x00a4 }
                r4 = r14.toString();	 Catch:{ Exception -> 0x00a4 }
                r3 = r3.append(r4);	 Catch:{ Exception -> 0x00a4 }
                r3 = r3.toString();	 Catch:{ Exception -> 0x00a4 }
                com.tencent.open.a.f.a(r2, r3);	 Catch:{ Exception -> 0x00a4 }
                r11 = com.tencent.open.b.e.a();	 Catch:{ Exception -> 0x00a4 }
                r10 = 0;
                r3 = 0;
                r8 = android.os.SystemClock.elapsedRealtime();	 Catch:{ Exception -> 0x00a4 }
                r6 = 0;
                r4 = 0;
                r2 = 0;
            L_0x0034:
                r10 = r10 + 1;
                r12 = com.tencent.open.utils.Global.getContext();	 Catch:{ ConnectTimeoutException -> 0x00b3, SocketTimeoutException -> 0x00c3, JSONException -> 0x00ce, NetworkUnavailableException -> 0x00d5, HttpStatusException -> 0x00ea, IOException -> 0x0115, Exception -> 0x0120 }
                r13 = "http://appsupport.qq.com/cgi-bin/appstage/mstats_batch_report";
                r15 = "POST";
                r15 = com.tencent.open.utils.HttpUtils.openUrl2(r12, r13, r15, r14);	 Catch:{ ConnectTimeoutException -> 0x00b3, SocketTimeoutException -> 0x00c3, JSONException -> 0x00ce, NetworkUnavailableException -> 0x00d5, HttpStatusException -> 0x00ea, IOException -> 0x0115, Exception -> 0x0120 }
                r12 = r15.response;	 Catch:{ ConnectTimeoutException -> 0x00b3, SocketTimeoutException -> 0x00c3, JSONException -> 0x00ce, NetworkUnavailableException -> 0x00d5, HttpStatusException -> 0x00ea, IOException -> 0x0115, Exception -> 0x0120 }
                r12 = com.tencent.open.utils.Util.parseJson(r12);	 Catch:{ ConnectTimeoutException -> 0x00b3, SocketTimeoutException -> 0x00c3, JSONException -> 0x00ce, NetworkUnavailableException -> 0x00d5, HttpStatusException -> 0x00ea, IOException -> 0x0115, Exception -> 0x0120 }
                r13 = "ret";
                r12 = r12.getInt(r13);	 Catch:{ JSONException -> 0x00b0, ConnectTimeoutException -> 0x00b3, SocketTimeoutException -> 0x00c3, NetworkUnavailableException -> 0x00d5, HttpStatusException -> 0x00ea, IOException -> 0x0115, Exception -> 0x0120 }
            L_0x0051:
                if (r12 == 0) goto L_0x005b;
            L_0x0053:
                r12 = r15.response;	 Catch:{ ConnectTimeoutException -> 0x00b3, SocketTimeoutException -> 0x00c3, JSONException -> 0x00ce, NetworkUnavailableException -> 0x00d5, HttpStatusException -> 0x00ea, IOException -> 0x0115, Exception -> 0x0120 }
                r12 = android.text.TextUtils.isEmpty(r12);	 Catch:{ ConnectTimeoutException -> 0x00b3, SocketTimeoutException -> 0x00c3, JSONException -> 0x00ce, NetworkUnavailableException -> 0x00d5, HttpStatusException -> 0x00ea, IOException -> 0x0115, Exception -> 0x0120 }
                if (r12 != 0) goto L_0x005d;
            L_0x005b:
                r3 = 1;
                r10 = r11;
            L_0x005d:
                r12 = r15.reqSize;	 Catch:{ ConnectTimeoutException -> 0x00b3, SocketTimeoutException -> 0x00c3, JSONException -> 0x00ce, NetworkUnavailableException -> 0x00d5, HttpStatusException -> 0x00ea, IOException -> 0x0115, Exception -> 0x0120 }
                r4 = r15.rspSize;	 Catch:{ ConnectTimeoutException -> 0x00b3, SocketTimeoutException -> 0x00c3, JSONException -> 0x00ce, NetworkUnavailableException -> 0x00d5, HttpStatusException -> 0x013b, IOException -> 0x0115, Exception -> 0x0120 }
                r6 = r12;
            L_0x0062:
                if (r10 < r11) goto L_0x0034;
            L_0x0064:
                r10 = r2;
                r13 = r3;
                r16 = r8;
                r8 = r4;
                r4 = r16;
            L_0x006b:
                r0 = r18;
                r2 = com.tencent.open.b.g.this;	 Catch:{ Exception -> 0x00a4 }
                r3 = "mapp_apptrace_sdk";
                r11 = 0;
                r12 = 0;
                r2.a(r3, r4, r6, r8, r10, r11, r12);	 Catch:{ Exception -> 0x00a4 }
                if (r13 == 0) goto L_0x0129;
            L_0x0079:
                r2 = com.tencent.open.b.f.a();	 Catch:{ Exception -> 0x00a4 }
                r3 = "report_via";
                r2.b(r3);	 Catch:{ Exception -> 0x00a4 }
            L_0x0083:
                r0 = r18;
                r2 = com.tencent.open.b.g.this;	 Catch:{ Exception -> 0x00a4 }
                r2 = r2.d;	 Catch:{ Exception -> 0x00a4 }
                r2.clear();	 Catch:{ Exception -> 0x00a4 }
                r2 = "openSDK_LOG.ReportManager";
                r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00a4 }
                r4 = "-->doReportVia, uploadSuccess: ";
                r3.<init>(r4);	 Catch:{ Exception -> 0x00a4 }
                r3 = r3.append(r13);	 Catch:{ Exception -> 0x00a4 }
                r3 = r3.toString();	 Catch:{ Exception -> 0x00a4 }
                com.tencent.open.a.f.b(r2, r3);	 Catch:{ Exception -> 0x00a4 }
                goto L_0x000a;
            L_0x00a4:
                r2 = move-exception;
                r3 = "openSDK_LOG.ReportManager";
                r4 = "-->doReportVia, exception in serial executor.";
                com.tencent.open.a.f.b(r3, r4, r2);
                goto L_0x000a;
            L_0x00b0:
                r12 = move-exception;
                r12 = -4;
                goto L_0x0051;
            L_0x00b3:
                r2 = move-exception;
                r2 = r10;
                r8 = android.os.SystemClock.elapsedRealtime();	 Catch:{ Exception -> 0x00a4 }
                r12 = 0;
                r6 = 0;
                r4 = -7;
                r10 = r2;
                r2 = r4;
                r4 = r6;
                r6 = r12;
                goto L_0x0062;
            L_0x00c3:
                r2 = move-exception;
                r8 = android.os.SystemClock.elapsedRealtime();	 Catch:{ Exception -> 0x00a4 }
                r6 = 0;
                r4 = 0;
                r2 = -8;
                goto L_0x0062;
            L_0x00ce:
                r2 = move-exception;
                r6 = 0;
                r4 = 0;
                r2 = -4;
                goto L_0x0062;
            L_0x00d5:
                r2 = move-exception;
                r0 = r18;
                r2 = com.tencent.open.b.g.this;	 Catch:{ Exception -> 0x00a4 }
                r2 = r2.d;	 Catch:{ Exception -> 0x00a4 }
                r2.clear();	 Catch:{ Exception -> 0x00a4 }
                r2 = "openSDK_LOG.ReportManager";
                r3 = "doReportVia, NetworkUnavailableException.";
                com.tencent.open.a.f.b(r2, r3);	 Catch:{ Exception -> 0x00a4 }
                goto L_0x000a;
            L_0x00ea:
                r10 = move-exception;
                r16 = r10;
                r10 = r3;
                r3 = r16;
            L_0x00f0:
                r3 = r3.getMessage();	 Catch:{ Exception -> 0x010b }
                r11 = "http status code error:";
                r12 = "";
                r3 = r3.replace(r11, r12);	 Catch:{ Exception -> 0x010b }
                r2 = java.lang.Integer.parseInt(r3);	 Catch:{ Exception -> 0x010b }
                r13 = r10;
                r10 = r2;
                r16 = r8;
                r8 = r4;
                r4 = r16;
                goto L_0x006b;
            L_0x010b:
                r3 = move-exception;
                r13 = r10;
                r10 = r2;
                r16 = r8;
                r8 = r4;
                r4 = r16;
                goto L_0x006b;
            L_0x0115:
                r2 = move-exception;
                r6 = 0;
                r4 = 0;
                r2 = com.tencent.open.utils.HttpUtils.getErrorCodeFromException(r2);	 Catch:{ Exception -> 0x00a4 }
                goto L_0x0062;
            L_0x0120:
                r2 = move-exception;
                r6 = 0;
                r4 = 0;
                r2 = -6;
                r10 = r11;
                goto L_0x0062;
            L_0x0129:
                r2 = com.tencent.open.b.f.a();	 Catch:{ Exception -> 0x00a4 }
                r3 = "report_via";
                r0 = r18;
                r4 = com.tencent.open.b.g.this;	 Catch:{ Exception -> 0x00a4 }
                r4 = r4.d;	 Catch:{ Exception -> 0x00a4 }
                r2.a(r3, r4);	 Catch:{ Exception -> 0x00a4 }
                goto L_0x0083;
            L_0x013b:
                r6 = move-exception;
                r10 = r3;
                r3 = r6;
                r6 = r12;
                goto L_0x00f0;
                */
            }
        });
    }

    public void a(String str, String str2, Bundle bundle, boolean z) {
        ThreadManager.executeOnSubThread(new AnonymousClass_6(bundle, str, z, str2));
    }
}
