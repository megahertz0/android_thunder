package com.xunlei.common.member.a;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.text.TextUtils;
import com.inmobi.ads.InMobiStrandPositioning.InMobiClientPositioning;
import com.umeng.a;
import com.xunlei.common.base.XLLog;
import com.xunlei.common.encrypt.MD5;
import com.xunlei.common.httpclient.AsyncHttpClient;
import com.xunlei.common.httpclient.BaseHttpClient;
import com.xunlei.common.member.XLOnUserListener;
import com.xunlei.common.member.XLQRCodeAuthHandler;
import com.xunlei.common.member.XLUserInfo;
import com.xunlei.common.member.XLUserInfo.USERINFOKEY;
import com.xunlei.common.member.c.c;
import com.xunlei.common.member.c.d;
import com.xunlei.common.member.c.d.b;
import com.xunlei.common.member.c.e;
import com.xunlei.common.member.c.f;
import com.xunlei.common.member.c.g;
import com.xunlei.common.member.c.h;
import com.xunlei.common.member.c.i;
import com.xunlei.common.member.c.j;
import com.xunlei.common.member.c.k;
import com.xunlei.common.member.c.l;
import com.xunlei.common.member.c.n;
import com.xunlei.common.member.c.o;
import com.xunlei.common.member.c.p;
import com.xunlei.common.member.c.q;
import com.xunlei.common.member.c.r;
import com.xunlei.common.member.c.s;
import com.xunlei.common.member.c.t;
import com.xunlei.common.member.c.u;
import com.xunlei.common.member.c.v;
import com.xunlei.common.stat.XLStatUtil;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

@SuppressLint({"UseSparseArrays"})
// compiled from: XLUserUtilProxy.java
public final class m implements h {
    private static int a = 1;
    private static int b = 2;
    private static int c = 3;
    private static int d = 4;
    private static int e = 5;
    private static int f = 6;
    private static int g = 7;
    private static int h = 8;
    private static int i = 9;
    private static int j = 10;
    private static int k = 11;
    private static int l = 12;
    private static final m p;
    private String A;
    private int B;
    private String C;
    private Map<Integer, p> D;
    private Map<Integer, g> E;
    private int F;
    private Context G;
    private String H;
    private String I;
    private String J;
    private int K;
    private String L;
    private int M;
    private i N;
    private XLStatUtil O;
    private k P;
    private l Q;
    private com.xunlei.common.member.c.m R;
    private BroadcastReceiver S;
    private final int m;
    private final int n;
    private final String o;
    private String q;
    private l r;
    private List<XLOnUserListener> s;
    private i t;
    private a u;
    private PendingIntent v;
    private Handler w;
    private BaseHttpClient x;
    private boolean y;
    private boolean z;

    // compiled from: XLUserUtilProxy.java
    final class AnonymousClass_3 implements Runnable {
        private /* synthetic */ p a;
        private /* synthetic */ Bundle b;

        AnonymousClass_3(p pVar, Bundle bundle) {
            this.a = pVar;
            this.b = bundle;
        }

        public final void run() {
            this.a.a(this.b);
            if (this.a.e()) {
                XLLog.v("XLUserUtil", new StringBuilder("notify to global listener task = ").append(this.a.j()).toString());
                m.this.a(m.this.a((int) XZBDevice.Success, new Object{this.a, this.b}));
            }
        }
    }

    static {
        p = new m();
    }

    private m() {
        this.q = a.d;
        this.r = new l();
        this.s = new LinkedList();
        this.t = new i(this);
        this.u = a.b();
        this.v = null;
        this.w = null;
        this.x = null;
        this.y = false;
        this.z = false;
        this.B = 300000;
        this.D = new HashMap();
        this.E = new HashMap();
        this.F = 0;
        this.G = null;
        this.H = "1.0.0";
        this.I = "ABCDEFGHIJK";
        this.J = "1.6.6.177628";
        this.K = 0;
        this.L = "MEA";
        this.M = 0;
        this.N = null;
        this.O = null;
        this.P = null;
        this.Q = null;
        this.R = null;
        this.S = new BroadcastReceiver() {
            public final void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                XLLog.v("KEEP_LIVE_ACTION", new StringBuilder("recive broadcast action = ").append(action).toString());
                if (!action.equals(m.this.q)) {
                    return;
                }
                if (m.this.q()) {
                    m.this.r();
                    return;
                }
                XLLog.v("KEEP_LIVE_ACTION", "user is not logined, kill timer and return.");
                m.this.a(false, 0);
            }
        };
    }

    public static m a() {
        return p;
    }

    public final boolean a(Context context, int i, String str, String str2, String str3) {
        if (this.y || Looper.myLooper() == null) {
            return false;
        }
        XLLog.i("UserUtil", "init");
        this.y = true;
        this.F = i;
        this.H = str;
        this.I = str2;
        this.G = context;
        this.w = new Handler() {
            public final void handleMessage(Message message) {
                m.this.b(message);
            }
        };
        v.a = str3;
        v.a();
        this.u.a(context, i, str);
        IntentFilter intentFilter = new IntentFilter();
        this.q = new StringBuilder("com.xunlei.acc.sdk.vip.keepLiveTimerAlarm.").append(String.valueOf(i)).append(".").append(System.currentTimeMillis()).toString();
        XLLog.v("XLUserUtil", new StringBuilder("mKeepAliveAction = ").append(this.q).toString());
        this.v = PendingIntent.getBroadcast(this.G, 0, new Intent(this.q), 134217728);
        XLLog.i("TEST", new StringBuilder("Init threadid = ").append(String.valueOf(Thread.currentThread().getId())).append(" pendingKeepLiveIntent:").append(String.valueOf(this.v.toString())).toString());
        intentFilter.addAction(this.q);
        intentFilter.setPriority(InMobiClientPositioning.NO_REPEAT);
        this.G.registerReceiver(this.S, intentFilter);
        this.O = XLStatUtil.getInstance();
        this.O.init(context, this.F, this.H, this.J, this.I);
        this.P = new k(this.O);
        a(this.P);
        this.N = new i(this, this.G);
        this.N.a();
        this.K = z();
        this.r.b(this.G);
        return true;
    }

    public final boolean b() {
        if (this.z) {
            return false;
        }
        b(this.P);
        XLLog.i("UserUtil", "uinit");
        this.z = true;
        a.a();
        this.G.unregisterReceiver(this.S);
        this.v.cancel();
        a(false, 0);
        this.O.uninit();
        this.N.b();
        return true;
    }

    public final String c() {
        return this.J;
    }

    public final int d() {
        return this.F;
    }

    public final String e() {
        return this.H;
    }

    public final int f() {
        return this.K;
    }

    public final String g() {
        return this.I;
    }

    public final Context h() {
        return this.G;
    }

    public final XLUserInfo i() {
        return this.r;
    }

    public final a j() {
        return this.u;
    }

    public final void a(BaseHttpClient baseHttpClient) {
        this.x = baseHttpClient;
    }

    public final BaseHttpClient k() {
        if (this.x == null) {
            this.x = new AsyncHttpClient(this.J);
        }
        return this.x;
    }

    public final String l() {
        return MD5.encrypt(new StringBuilder("device.").append(v.a()).toString());
    }

    public final String m() {
        return this.G.getApplicationInfo().packageName;
    }

    public final p a(int i) {
        return (p) this.D.get(Integer.valueOf(i));
    }

    public final Handler n() {
        return this.w;
    }

    public final synchronized void a(XLOnUserListener xLOnUserListener) {
        this.s.add(xLOnUserListener);
    }

    public final synchronized void b(XLOnUserListener xLOnUserListener) {
        this.s.remove(xLOnUserListener);
    }

    public final void a(p pVar) {
        b(pVar);
    }

    public final void a(boolean z, int i) {
        AlarmManager alarmManager = (AlarmManager) this.G.getSystemService(NotificationCompatApi21.CATEGORY_ALARM);
        alarmManager.cancel(this.v);
        if (!z) {
            XLLog.v("XLUserUtil", new StringBuilder("stop keepalive timer period = ").append(i).append(" process = ").append(Process.myPid()).toString());
        } else if (q()) {
            XLLog.v("XLUserUtil", new StringBuilder("start keepalive timer period = ").append(i).append(" process = ").append(Process.myPid()).toString());
            if (i > 0) {
                this.B = i;
            }
            alarmManager.setRepeating(0, System.currentTimeMillis() + ((long) this.B), (long) this.B, this.v);
            r();
        } else {
            XLLog.v("XLUserUtil", "user is not login, do not start timer and return");
        }
    }

    public final int a(String str, String str2, String str3, String str4, XLOnUserListener xLOnUserListener, Object obj) {
        p gVar = new g(this);
        gVar.a();
        gVar.a(str, false);
        gVar.a(str2, null);
        gVar.b(obj);
        gVar.b(str3, str4);
        gVar.a(xLOnUserListener);
        this.O.registerStatReq(gVar.j(), 103000);
        return b(gVar);
    }

    public final int a(String str, String str2, XLOnUserListener xLOnUserListener, Object obj) {
        if (q()) {
            XLLog.v("userAutoLogin", "user is online, execute ping command!");
            r();
            return 0;
        }
        XLLog.v("userAutoLogin", "user is not online, execute auto login command!");
        d a = d.a(this.G);
        if (a == null) {
            return 0;
        }
        p fVar;
        int i;
        if (!TextUtils.isEmpty(a.d)) {
            fVar = new f(this);
            fVar.a();
            fVar.a(xLOnUserListener);
            fVar.b(obj);
            fVar.a(String.valueOf(a.a), a.d, a.d);
            i = 102000;
        } else if (TextUtils.isEmpty(a.b)) {
            return 0;
        } else {
            fVar = new g(this);
            fVar.a();
            fVar.a(xLOnUserListener);
            fVar.b(obj);
            fVar.a(String.valueOf(a.a), true);
            fVar.a(a.b, a.c);
            fVar.b(str, str2);
            i = 101000;
        }
        this.O.registerStatReq(fVar.j(), i);
        return b(fVar);
    }

    public final int a(int i, Object obj, XLOnUserListener xLOnUserListener, Object obj2) {
        int i2 = 0;
        p oVar;
        if (i == 2) {
            oVar = new o(this);
            oVar.a();
            oVar.a(obj);
            oVar.b(obj2);
            oVar.a(xLOnUserListener);
            i2 = b(oVar);
            this.D.put(Integer.valueOf(i2), oVar);
            this.O.registerStatReq(i2);
            return i2;
        } else if (i == 1) {
            oVar = new u(this);
            oVar.a();
            oVar.a(obj);
            oVar.b(obj2);
            oVar.a(xLOnUserListener);
            i2 = b(oVar);
            this.D.put(Integer.valueOf(i2), oVar);
            this.O.registerStatReq(i2);
            return i2;
        } else if (i == 3) {
            oVar = new t(this);
            oVar.a();
            oVar.a(obj);
            oVar.b(obj2);
            oVar.a(xLOnUserListener);
            i2 = b(oVar);
            this.D.put(Integer.valueOf(i2), oVar);
            this.O.registerStatReq(i2);
            return i2;
        } else {
            if (i == 4) {
                oVar = new com.xunlei.common.member.c.a(this);
                oVar.a();
                oVar.a(obj);
                oVar.b(obj2);
                oVar.a(xLOnUserListener);
                i2 = b(oVar);
                this.D.put(Integer.valueOf(i2), oVar);
                this.O.registerStatReq(i2);
            }
            if (i != 5) {
                return i2;
            }
            oVar = new k(this);
            oVar.a();
            oVar.a(obj);
            oVar.b(obj2);
            oVar.a(xLOnUserListener);
            i2 = b(oVar);
            this.D.put(Integer.valueOf(i2), oVar);
            this.O.registerStatReq(i2);
            return i2;
        }
    }

    public final int a(int i, String str, int i2, int i3, XLOnUserListener xLOnUserListener, Object obj) {
        return a(i, str, i2, i3, xLOnUserListener, obj, false);
    }

    public final int a(int i, String str, int i2, int i3, XLOnUserListener xLOnUserListener, Object obj, boolean z) {
        p nVar = new n(this);
        nVar.a();
        nVar.a(i, str, i2, i3);
        nVar.b(obj);
        nVar.a(xLOnUserListener);
        if (z) {
            nVar.b(false);
        } else {
            this.O.registerStatReq(nVar.j());
        }
        return b(nVar);
    }

    public final int b(String str, String str2, String str3, String str4, XLOnUserListener xLOnUserListener, Object obj) {
        return a(str, str2, str3, str4, xLOnUserListener, obj, false);
    }

    public final int a(String str, String str2, String str3, String str4, XLOnUserListener xLOnUserListener, Object obj, boolean z) {
        p qVar = new q(this);
        qVar.a();
        qVar.a(str, str2, str3, str4);
        qVar.b(obj);
        qVar.a(xLOnUserListener);
        if (z) {
            qVar.b(false);
        } else {
            this.O.registerStatReq(qVar.j());
        }
        return b(qVar);
    }

    public final int b(String str, String str2, XLOnUserListener xLOnUserListener, Object obj) {
        p jVar = new j(this);
        jVar.a();
        jVar.a(str, str2);
        jVar.b(obj);
        jVar.a(xLOnUserListener);
        this.O.registerStatReq(jVar.j());
        return b(jVar);
    }

    public final int c(String str, String str2, XLOnUserListener xLOnUserListener, Object obj) {
        p sVar = new s(this);
        sVar.a();
        sVar.a(str, str2);
        sVar.b(obj);
        sVar.a(xLOnUserListener);
        this.O.registerStatReq(sVar.j());
        return b(sVar);
    }

    public static boolean o() {
        return false;
    }

    public final int a(List<USERINFOKEY> list, XLOnUserListener xLOnUserListener, int i, Object obj) {
        return a((List) list, xLOnUserListener, i, false, obj);
    }

    public final int a(List<USERINFOKEY> list, XLOnUserListener xLOnUserListener, int i, boolean z, Object obj) {
        p eVar = new e(this);
        eVar.a();
        eVar.a((List) list);
        eVar.b(obj);
        eVar.b(i);
        eVar.a(xLOnUserListener);
        if (z) {
            eVar.b(false);
        } else {
            this.O.registerStatReq(eVar.j());
        }
        this.O.registerStatReq(eVar.j());
        return b(eVar);
    }

    public final int a(JSONObject jSONObject, String str, String str2, XLOnUserListener xLOnUserListener, Object obj) {
        p bVar = new b(this);
        bVar.a();
        bVar.b(obj);
        bVar.a(xLOnUserListener);
        bVar.a(jSONObject, str, str2);
        return b(bVar);
    }

    public final int a(XLOnUserListener xLOnUserListener, Object obj) {
        p aVar = new com.xunlei.common.member.c.d.a(this);
        aVar.a();
        aVar.b(obj);
        aVar.a(xLOnUserListener);
        return b(aVar);
    }

    public final int b(XLOnUserListener xLOnUserListener, Object obj) {
        p cVar = new c(this);
        cVar.a();
        cVar.b(obj);
        cVar.a(xLOnUserListener);
        return b(cVar);
    }

    public final int c(XLOnUserListener xLOnUserListener, Object obj) {
        p dVar = new d(this);
        dVar.a();
        dVar.b(obj);
        dVar.a(xLOnUserListener);
        return b(dVar);
    }

    public final int d(XLOnUserListener xLOnUserListener, Object obj) {
        p hVar = new h(this);
        hVar.a();
        hVar.b(obj);
        hVar.a(xLOnUserListener);
        this.O.registerStatReq(hVar.j());
        return b(hVar);
    }

    public final int e(XLOnUserListener xLOnUserListener, Object obj) {
        p rVar = new r(this);
        rVar.a();
        rVar.b(obj);
        rVar.a(xLOnUserListener);
        this.O.registerStatReq(rVar.j());
        return b(rVar);
    }

    public final int f(XLOnUserListener xLOnUserListener, Object obj) {
        p aVar = new com.xunlei.common.member.c.b.a(this);
        aVar.a();
        aVar.b(obj);
        aVar.a(xLOnUserListener);
        this.O.registerStatReq(aVar.j());
        return b(aVar);
    }

    public final int a(int i, XLOnUserListener xLOnUserListener, Object obj) {
        p bVar = new com.xunlei.common.member.c.b.b(this);
        bVar.a();
        bVar.b(obj);
        bVar.b(i);
        bVar.a(xLOnUserListener);
        this.O.registerStatReq(bVar.j());
        return b(bVar);
    }

    public final int a(Bitmap bitmap, XLOnUserListener xLOnUserListener, Object obj) {
        p cVar = new com.xunlei.common.member.c.b.c(this);
        cVar.a();
        cVar.b(obj);
        cVar.a(bitmap);
        cVar.a(xLOnUserListener);
        this.O.registerStatReq(cVar.j());
        return b(cVar);
    }

    public final boolean p() {
        return this.r.getLongValue(USERINFOKEY.UserID) != 0;
    }

    public final boolean q() {
        return (TextUtils.isEmpty(this.r.getStringValue(USERINFOKEY.SessionID)) || this.r.getLongValue(USERINFOKEY.UserID) == 0) ? false : true;
    }

    public static boolean a(String str) {
        return (TextUtils.isEmpty(str) || str.indexOf("xunlei.com/remote_login") == -1) ? false : true;
    }

    public final int a(String str, XLQRCodeAuthHandler xLQRCodeAuthHandler, XLOnUserListener xLOnUserListener, Object obj) {
        if (xLQRCodeAuthHandler == null) {
            return 0;
        }
        if (this.Q != null) {
            this.Q.c();
        }
        p lVar = new l(this);
        lVar.a();
        lVar.b(obj);
        lVar.a(xLQRCodeAuthHandler, str);
        lVar.a(xLOnUserListener);
        this.D.put(Integer.valueOf(lVar.j()), lVar);
        this.Q = lVar;
        return b(lVar);
    }

    public final int b(int i, XLOnUserListener xLOnUserListener, Object obj) {
        p bVar = new com.xunlei.common.member.c.b(this);
        bVar.a();
        bVar.b(obj);
        bVar.b(i);
        bVar.a(xLOnUserListener);
        return b(bVar);
    }

    public final int a(String str, int i, XLOnUserListener xLOnUserListener, Object obj) {
        if (this.R != null) {
            this.R.c();
        }
        p mVar = new com.xunlei.common.member.c.m(this);
        mVar.a();
        mVar.b(obj);
        mVar.a(str, i);
        mVar.a(xLOnUserListener);
        this.R = mVar;
        return b(mVar);
    }

    public final int g(XLOnUserListener xLOnUserListener, Object obj) {
        p bVar = new com.xunlei.common.member.c.c.b(this);
        bVar.a();
        bVar.b(obj);
        bVar.a(xLOnUserListener);
        return b(bVar);
    }

    public final int a(int i, String str, String str2, XLOnUserListener xLOnUserListener, Object obj) {
        p aVar = new com.xunlei.common.member.c.c.a(this);
        aVar.a();
        aVar.b(obj);
        aVar.a(xLOnUserListener);
        aVar.a(i, str, str2);
        this.D.put(Integer.valueOf(aVar.j()), aVar);
        return b(aVar);
    }

    public final int a(int i, String str, XLOnUserListener xLOnUserListener, Object obj) {
        p cVar = new com.xunlei.common.member.c.c.c(this);
        cVar.a();
        cVar.b(obj);
        cVar.a(xLOnUserListener);
        cVar.b(i, str);
        this.D.put(Integer.valueOf(cVar.j()), cVar);
        return b(cVar);
    }

    public final int c(int i, XLOnUserListener xLOnUserListener, Object obj) {
        p dVar = new com.xunlei.common.member.c.c.d(this);
        dVar.a();
        dVar.b(obj);
        dVar.a(xLOnUserListener);
        dVar.b(i);
        return b(dVar);
    }

    public final int a(String str, XLOnUserListener xLOnUserListener, Object obj) {
        p bVar = new com.xunlei.common.member.c.a.b(this);
        bVar.a();
        bVar.b(obj);
        bVar.a(xLOnUserListener);
        bVar.a(str);
        return b(bVar);
    }

    public final int d(String str, String str2, XLOnUserListener xLOnUserListener, Object obj) {
        p aVar = new com.xunlei.common.member.c.a.a(this);
        aVar.a();
        aVar.b(obj);
        aVar.a(xLOnUserListener);
        aVar.a(str, str2);
        return b(aVar);
    }

    private int b(p pVar) {
        if (Looper.myLooper() == null) {
            a(a((int) XZBDevice.Stop, (Object) pVar));
        } else {
            pVar.b();
        }
        return pVar.j();
    }

    private void b(p pVar, Bundle bundle) {
        a(a((int) XZBDevice.Success, new Object{pVar, bundle}));
    }

    public final void r() {
        this.t.b(1);
        this.t.b();
    }

    private static String y() {
        return v.a();
    }

    public static String s() {
        return v.b();
    }

    private int z() {
        int lastIndexOf = this.J.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return 0;
        }
        try {
            return Integer.valueOf(this.J.substring(lastIndexOf + 1)).intValue();
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private Message a(int i, Object obj) {
        if (this.w != null) {
            return this.w.obtainMessage(i, obj);
        }
        Message obtain = Message.obtain();
        obtain.what = i;
        obtain.obj = obj;
        return obtain;
    }

    private void a(Message message) {
        if (this.w != null) {
            this.w.sendMessage(message);
        } else {
            b(message);
        }
    }

    private void b(Message message) {
        switch (message.what) {
            case XZBDevice.Stop:
                ((p) message.obj).b();
            case XZBDevice.Success:
                Object[] objArr = (Object[]) message.obj;
                for (int i = 0; i < this.s.size(); i++) {
                    ((p) objArr[0]).a((XLOnUserListener) this.s.get(i), (Bundle) objArr[1]);
                }
            default:
                break;
        }
    }

    public final void b(int i) {
        this.D.remove(Integer.valueOf(i));
    }

    public final void b(String str) {
    }

    public final void a(int i, g gVar) {
        this.E.put(Integer.valueOf(i), gVar);
    }

    private void e(int i) {
        this.E.remove(Integer.valueOf(i));
    }

    public final g c(int i) {
        return (g) this.E.get(Integer.valueOf(i));
    }

    public final void c(XLOnUserListener xLOnUserListener) {
        this.t.a(xLOnUserListener);
    }

    public final String t() {
        return this.L;
    }

    public final void c(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.L = str;
        }
    }

    public final void d(int i) {
        this.M = 1;
    }

    public final int u() {
        return this.M;
    }

    public final void v() {
        this.u.d();
    }

    public final void w() {
        this.Q = null;
    }

    public final void x() {
        this.R = null;
    }

    public final void a(boolean z) {
        XLLog.v("XLUserUtil", new StringBuilder("onNetWorkChange state = ").append(z).toString());
        if (!q()) {
            XLLog.v("XLUserUtil", "onNetWorkChange user is not online, go back!");
        } else if (z) {
            this.w.postDelayed(new Runnable() {
                public final void run() {
                    if (m.this.q()) {
                        m.this.r();
                    }
                }
            }, 10);
        } else {
            r();
        }
    }

    public final void b(boolean z) {
        d.a(this.G, d.a.c);
        if (z) {
            p.r.clearUserData();
        }
    }

    public final void a(p pVar, Bundle bundle) {
        this.w.post(new AnonymousClass_3(pVar, bundle));
    }

    public static void a(int i, String str, int i2) {
        p a = p.a(i2);
        if (a != null) {
            a.a(i, str);
        }
    }
}
