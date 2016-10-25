package com.inmobi.rendering.a;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.PowerManager;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.inmobi.commons.core.network.NetworkError.ErrorCode;
import com.inmobi.commons.core.network.NetworkRequest;
import com.inmobi.commons.core.network.NetworkRequest.RequestType;
import com.inmobi.commons.core.network.e;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.taobao.accs.common.Constants;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import org.android.spdy.SpdyAgent;

// compiled from: ClickManager.java
public class c implements com.inmobi.commons.core.configs.b.b {
    public static com.inmobi.ads.b.b a;
    private static final String b;
    private static c c;
    private static final Object d;
    private static ExecutorService e;
    private static a f;
    private static HandlerThread g;
    private static List<a> h;
    private static b i;
    private static AtomicBoolean j;
    private static final Object k;
    private boolean l;
    private PowerManager m;
    private final d n;

    // compiled from: ClickManager.java
    class AnonymousClass_1 implements Runnable {
        final /* synthetic */ a a;

        AnonymousClass_1(a aVar) {
            this.a = aVar;
        }

        public void run() {
            if (this.a.h) {
                new b(c.this.n).a(this.a);
            } else {
                new c(c.this.n).a(this.a);
            }
        }
    }

    // compiled from: ClickManager.java
    static interface d {
        void a(a aVar);

        void a(a aVar, ErrorCode errorCode);
    }

    // compiled from: ClickManager.java
    final class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        public final void handleMessage(Message message) {
            int i = XZBDevice.DOWNLOAD_LIST_RECYCLE;
            a aVar;
            Message obtain;
            switch (message.what) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    h = i.a(a.e(), a.b());
                    if (!h.isEmpty()) {
                        aVar = (a) h.get(0);
                        Message obtain2 = Message.obtain();
                        if (aVar.h) {
                            i = XZBDevice.DOWNLOAD_LIST_FAILED;
                        }
                        obtain2.what = i;
                        obtain2.obj = aVar;
                        long currentTimeMillis = System.currentTimeMillis() - aVar.d;
                        if (currentTimeMillis < ((long) (a.b() * 1000))) {
                            sendMessageDelayed(obtain2, ((long) (a.b() * 1000)) - currentTimeMillis);
                        } else {
                            sendMessage(obtain2);
                        }
                    } else if (i.a()) {
                        j.set(false);
                    } else {
                        obtain = Message.obtain();
                        obtain.what = 1;
                        sendMessageDelayed(obtain, (long) (a.b() * 1000));
                    }
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    if (com.inmobi.commons.core.utilities.c.a()) {
                        aVar = (a) message.obj;
                        if (aVar.f == 0) {
                            a(aVar, 1);
                            return;
                        } else if (aVar.a(a.f())) {
                            a(aVar, (int) XZBDevice.DOWNLOAD_LIST_RECYCLE);
                            return;
                        } else {
                            i = (a.a() - aVar.f) + 1;
                            if (i == 0) {
                                Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("Pinging click (").append(c.this).append(") over HTTP").toString());
                            } else {
                                Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("Retry attempt #").append(i).append(" for click (").append(c.this).append(") over HTTP").toString());
                            }
                            new c(new d() {
                                public void a(a aVar) {
                                    a.this.b(aVar);
                                }

                                public void a(a aVar, ErrorCode errorCode) {
                                    Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("Pinging click (").append(a.this).append(") via HTTP failed ...").toString());
                                    a.this.b(aVar);
                                    a.this.c(aVar);
                                }
                            }).a(aVar);
                            return;
                        }
                    }
                    j.set(false);
                    c.this.c();
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    if (com.inmobi.commons.core.utilities.c.a()) {
                        aVar = (a) message.obj;
                        if (aVar.f == 0) {
                            a(aVar, 1);
                            return;
                        } else if (aVar.a(a.f())) {
                            a(aVar, (int) XZBDevice.DOWNLOAD_LIST_RECYCLE);
                            return;
                        } else {
                            i = (a.a() - aVar.f) + 1;
                            if (i == 0) {
                                Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("Pinging click (").append(c.this).append(") in WebView").toString());
                            } else {
                                Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("Retry attempt #").append(i).append(" for click (").append(c.this).append(") using WebView").toString());
                            }
                            new b(new d() {
                                public void a(a aVar) {
                                    a.this.b(aVar);
                                }

                                public void a(a aVar, ErrorCode errorCode) {
                                    Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("Pinging click (").append(a.this).append(") via WebView failed ...").toString());
                                    a.this.b(aVar);
                                    a.this.c(aVar);
                                }
                            }).a(aVar);
                            return;
                        }
                    }
                    j.set(false);
                    c.this.c();
                case XZBDevice.DOWNLOAD_LIST_ALL:
                    aVar = (a) message.obj;
                    Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("Processing click (").append(c.this).append(") completed").toString());
                    i.b(aVar);
                    h.remove(aVar);
                    if (h.isEmpty()) {
                        a((a) h.get(0));
                    } else if (i.a()) {
                        obtain = Message.obtain();
                        obtain.what = 1;
                        sendMessage(obtain);
                    } else {
                        Logger.a(InternalLogLevel.INTERNAL, b, "Done processing all clicks!");
                        j.set(false);
                    }
                case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                    aVar = (a) message.obj;
                    Map hashMap = new HashMap();
                    hashMap.put("pingUrl", aVar.b);
                    switch (message.arg1) {
                        case SpdyAgent.ACCS_ONLINE_SERVER:
                            hashMap.put(Constants.KEY_ERROR_CODE, "MaxRetryCountReached");
                            break;
                        case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                            hashMap.put(Constants.KEY_ERROR_CODE, "ExpiredClick");
                            break;
                    }
                    com.inmobi.commons.core.c.a.a().a("ads", "PingDiscarded", hashMap);
                    aVar = (a) message.obj;
                    Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("Processing click (").append(c.this).append(") completed").toString());
                    i.b(aVar);
                    h.remove(aVar);
                    if (h.isEmpty()) {
                        a((a) h.get(0));
                    } else if (i.a()) {
                        obtain = Message.obtain();
                        obtain.what = 1;
                        sendMessage(obtain);
                    } else {
                        Logger.a(InternalLogLevel.INTERNAL, b, "Done processing all clicks!");
                        j.set(false);
                    }
                default:
                    break;
            }
        }

        private void a(a aVar) {
            Message obtain = Message.obtain();
            obtain.what = aVar.h ? XZBDevice.DOWNLOAD_LIST_FAILED : XZBDevice.DOWNLOAD_LIST_RECYCLE;
            obtain.obj = aVar;
            sendMessage(obtain);
        }

        private void a(a aVar, int i) {
            Message obtain = Message.obtain();
            obtain.what = 5;
            obtain.obj = aVar;
            obtain.arg1 = i;
            sendMessage(obtain);
        }

        private void b(a aVar) {
            Message obtain = Message.obtain();
            obtain.what = 4;
            obtain.obj = aVar;
            sendMessage(obtain);
        }

        private void c(a aVar) {
            int indexOf = h.indexOf(aVar);
            if (-1 != indexOf) {
                a aVar2 = (a) h.get(indexOf == h.size() + -1 ? 0 : indexOf + 1);
                Message obtain = Message.obtain();
                obtain.what = aVar2.h ? XZBDevice.DOWNLOAD_LIST_FAILED : XZBDevice.DOWNLOAD_LIST_RECYCLE;
                obtain.obj = aVar2;
                if (System.currentTimeMillis() - aVar2.d < ((long) (a.b() * 1000))) {
                    sendMessageDelayed(obtain, (long) (a.b() * 1000));
                } else {
                    sendMessage(obtain);
                }
            }
        }
    }

    // compiled from: ClickManager.java
    static final class b {
        private d a;

        // compiled from: ClickManager.java
        class AnonymousClass_1 implements Runnable {
            final /* synthetic */ a a;
            final /* synthetic */ Handler b;

            AnonymousClass_1(a aVar, Handler handler) {
                this.a = aVar;
                this.b = handler;
            }

            public void run() {
                new e(new NetworkRequest(RequestType.GET, this.a.b, false, null), new WebViewClient() {
                    AtomicBoolean a;
                    boolean b;

                    // compiled from: ClickManager.java
                    class AnonymousClass_1 implements Runnable {
                        final /* synthetic */ WebView a;

                        AnonymousClass_1(WebView webView) {
                            this.a = webView;
                        }

                        public void run() {
                            try {
                                Thread.sleep((long) (a.c() * 1000));
                            } catch (InterruptedException e) {
                            }
                            if (!AnonymousClass_1.this.a.get()) {
                                Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("Pinging click (").append(AnonymousClass_1.this.a.a).append(") via WebView timed out!").toString());
                                AnonymousClass_1.this.a.g.set(true);
                                AnonymousClass_1.this.post(new Runnable() {
                                    public void run() {
                                        AnonymousClass_1.this.stopLoading();
                                    }
                                });
                                AnonymousClass_1.this.a.a(AnonymousClass_1.this.a, null);
                            }
                        }
                    }

                    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                        this.a = new AtomicBoolean(false);
                        this.b = false;
                        new Thread(new AnonymousClass_1(webView)).start();
                    }

                    public void onPageFinished(WebView webView, String str) {
                        this.a.set(true);
                        if (!this.b && !AnonymousClass_1.this.a.g.get()) {
                            AnonymousClass_1.this.a.a(AnonymousClass_1.this.a);
                        }
                    }

                    @TargetApi(22)
                    public void onReceivedError(WebView webView, int i, String str, String str2) {
                        this.b = true;
                        AnonymousClass_1.this.a.a(AnonymousClass_1.this.a, null);
                    }

                    @TargetApi(23)
                    public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
                        this.b = true;
                        AnonymousClass_1.this.a.a(AnonymousClass_1.this.a, null);
                    }

                    @TargetApi(23)
                    public void onReceivedHttpError(WebView webView, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
                        this.b = true;
                        AnonymousClass_1.this.a.a(AnonymousClass_1.this.a, null);
                    }

                    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                        return (AnonymousClass_1.this.a.i || str.equals(AnonymousClass_1.this.a.b)) ? false : true;
                    }
                }).a();
            }
        }

        public b(d dVar) {
            this.a = dVar;
        }

        public final void a(a aVar) {
            aVar.g.set(false);
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new AnonymousClass_1(aVar, handler));
        }
    }

    // compiled from: ClickManager.java
    static final class c {
        private d a;

        public c(d dVar) {
            this.a = dVar;
        }

        public final void a(a aVar) {
            NetworkRequest networkRequest = new NetworkRequest(RequestType.GET, aVar.b, false, null);
            networkRequest.a(false);
            networkRequest.c(aVar.c);
            networkRequest.b(aVar.i);
            networkRequest.b(a.c() * 1000);
            networkRequest.c(a.c() * 1000);
            com.inmobi.commons.core.network.c a = new com.inmobi.commons.core.network.d(networkRequest).a();
            if (a.a()) {
                ErrorCode a2 = a.c().a();
                if (aVar.i || !(ErrorCode.HTTP_SEE_OTHER == a2 || ErrorCode.HTTP_MOVED_TEMP == a2)) {
                    this.a.a(aVar, a.c().a());
                    return;
                } else {
                    this.a.a(aVar);
                    return;
                }
            }
            this.a.a(aVar);
        }
    }

    static {
        b = c.class.getSimpleName();
        d = new Object();
        h = new ArrayList();
        j = new AtomicBoolean(false);
        k = new Object();
    }

    public static c a() {
        c cVar = c;
        if (cVar == null) {
            synchronized (d) {
                cVar = c;
                if (cVar == null) {
                    cVar = new c();
                    c = cVar;
                }
            }
        }
        return cVar;
    }

    public void a(com.inmobi.commons.core.configs.a aVar) {
        a = ((com.inmobi.ads.b) aVar).i();
    }

    public void b() {
        if (com.inmobi.commons.core.utilities.c.a()) {
            synchronized (k) {
                if (j.compareAndSet(false, true)) {
                    Logger.a(InternalLogLevel.INTERNAL, b, "Resume processing clicks ...");
                    if (g == null) {
                        HandlerThread handlerThread = new HandlerThread("pingHandlerThread");
                        g = handlerThread;
                        handlerThread.start();
                    }
                    if (f == null) {
                        f = new a(g.getLooper());
                    }
                    if (i.a()) {
                        Logger.a(InternalLogLevel.INTERNAL, b, "Done processing all clicks!");
                        j.set(false);
                        c();
                    } else {
                        Message obtain = Message.obtain();
                        obtain.what = 1;
                        f.sendMessage(obtain);
                    }
                }
            }
        }
    }

    public void a(String str, boolean z) {
        a aVar = new a(str, z, false, a.a() + 1);
        Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("Received click (").append(aVar.a).append(") for pinging over HTTP").toString());
        a(aVar);
    }

    public void a(String str, Map<String, String> map, boolean z) {
        a aVar = new a(str, map, z, false, a.a() + 1);
        Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("Received click (").append(aVar.a).append(") for pinging over HTTP").toString());
        a(aVar);
    }

    public void b(String str, boolean z) {
        a aVar = new a(str, z, true, a.a() + 1);
        Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("Received click (").append(aVar.a).append(") for pinging in WebView").toString());
        a(aVar);
    }

    public void c() {
        j.set(false);
        synchronized (k) {
            if (!j.get()) {
                if (g != null) {
                    g.getLooper().quit();
                    g.interrupt();
                    g = null;
                    f = null;
                }
                h.clear();
            }
        }
    }

    private void b(a aVar) {
        if (aVar.f > 0) {
            aVar.f--;
            aVar.d = System.currentTimeMillis();
            i.a(aVar);
        }
    }

    protected void a(a aVar) {
        i.a(aVar, a.d());
        if (com.inmobi.commons.core.utilities.c.a()) {
            e.submit(new AnonymousClass_1(aVar));
            return;
        }
        Logger.a(InternalLogLevel.INTERNAL, b, "No network available. Saving click for later processing ...");
        j.set(false);
        c();
    }

    public c() {
        this.l = false;
        this.n = new d() {
            public void a(a aVar) {
                if (aVar != null) {
                    Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("Processing click (").append(c.this).append(") completed").toString());
                    i.b(aVar);
                }
            }

            public void a(a aVar, ErrorCode errorCode) {
                if (aVar != null) {
                    Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("Pinging click (").append(c.this).append(") failed! Updating retry counts and timestamps ...").toString());
                    c.this.b(aVar);
                    c.this.b();
                }
            }
        };
        Logger.a(InternalLogLevel.INTERNAL, b, "Creating a new instance ...");
        d();
    }

    public void d() {
        e = Executors.newFixedThreadPool(XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
        HandlerThread handlerThread = new HandlerThread("pingHandlerThread");
        g = handlerThread;
        handlerThread.start();
        f = new a(g.getLooper());
        com.inmobi.commons.core.configs.a bVar = new com.inmobi.ads.b();
        com.inmobi.commons.core.configs.b.a().a(bVar, (com.inmobi.commons.core.configs.b.b) this);
        a = bVar.i();
        i = new b();
        this.m = (PowerManager) com.inmobi.commons.a.a.b().getSystemService("power");
        i();
    }

    @TargetApi(23)
    private void i() {
        com.inmobi.commons.core.utilities.e.a().a("android.net.conn.CONNECTIVITY_CHANGE", new com.inmobi.commons.core.utilities.e.b() {
            public void b(boolean z) {
                if (z) {
                    c.this.b();
                }
            }
        });
        if (VERSION.SDK_INT >= 23) {
            com.inmobi.commons.core.utilities.e.a().a("android.os.action.DEVICE_IDLE_MODE_CHANGED", new com.inmobi.commons.core.utilities.e.b() {
                public void b(boolean z) {
                    if (!z) {
                        c.this.b();
                    }
                }
            });
        }
    }
}
