package com.tencent.map.b;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.telephony.TelephonyManager;
import anet.channel.util.ErrorConstant;
import com.alipay.sdk.util.h;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.sina.weibo.sdk.register.mobile.SelectCountryActivity;
import com.tencent.map.a.a.d;
import com.umeng.socialize.editorpage.ShareActivity;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;
import org.android.spdy.SpdyAgent;
import org.json.JSONArray;
import org.json.JSONObject;

public final class f implements com.tencent.map.b.b.a, com.tencent.map.b.d.c, com.tencent.map.b.e.c, com.tencent.map.b.g.c {
    private static boolean t;
    private static f u;
    private d A;
    private int B;
    private int C;
    private int D;
    private String E;
    private String F;
    private String G;
    private String H;
    private String I;
    private String J;
    private boolean K;
    private boolean L;
    private long M;
    private Handler N;
    private Runnable O;
    private final BroadcastReceiver P;
    private long a;
    private Context b;
    private e c;
    private d d;
    private g e;
    private int f;
    private int g;
    private c h;
    private b i;
    private com.tencent.map.a.a.b j;
    private int k;
    private int l;
    private int m;
    private byte[] n;
    private byte[] o;
    private boolean p;
    private c q;
    private b r;
    private a s;
    private long v;
    private com.tencent.map.b.e.b w;
    private com.tencent.map.b.d.b x;
    private com.tencent.map.b.g.b y;
    private d z;

    class a extends Thread {
        private com.tencent.map.b.e.b a;
        private com.tencent.map.b.d.b b;
        private com.tencent.map.b.g.b c;

        a(com.tencent.map.b.e.b bVar, com.tencent.map.b.d.b bVar2, com.tencent.map.b.g.b bVar3) {
            this.a = null;
            this.b = null;
            this.c = null;
            if (bVar != null) {
                this.a = (com.tencent.map.b.e.b) bVar.clone();
            }
            if (bVar2 != null) {
                this.b = (com.tencent.map.b.d.b) bVar2.clone();
            }
            if (bVar3 != null) {
                this.c = (com.tencent.map.b.g.b) bVar3.clone();
            }
        }

        public final void run() {
            if (!t) {
                try {
                    TelephonyManager telephonyManager = (TelephonyManager) f.this.b.getSystemService("phone");
                    f.this.E = telephonyManager.getDeviceId();
                    f.this.F = telephonyManager.getSubscriberId();
                    f.this.G = telephonyManager.getLine1Number();
                    Pattern compile = Pattern.compile("[0-9a-zA-Z+-]*");
                    f.this.E = f.this.E == null ? com.umeng.a.d : f.this.E;
                    if (compile.matcher(f.this.E).matches()) {
                        f.this.E = f.this.E == null ? com.umeng.a.d : f.this.E;
                    } else {
                        f.this.E = com.umeng.a.d;
                    }
                    f.this.F = f.this.F == null ? com.umeng.a.d : f.this.F;
                    if (compile.matcher(f.this.F).matches()) {
                        f.this.F = f.this.F == null ? com.umeng.a.d : f.this.F;
                    } else {
                        f.this.F = com.umeng.a.d;
                    }
                    f.this.G = f.this.G == null ? com.umeng.a.d : f.this.G;
                    if (compile.matcher(f.this.G).matches()) {
                        f.this.G = f.this.G == null ? com.umeng.a.d : f.this.G;
                    } else {
                        f.this.G = com.umeng.a.d;
                    }
                } catch (Exception e) {
                }
                t = true;
                f.this.E = f.this.E == null ? com.umeng.a.d : f.this.E;
                f.this.F = f.this.F == null ? com.umeng.a.d : f.this.F;
                f.this.G = f.this.G == null ? com.umeng.a.d : f.this.G;
                f.this.I = j.a(f.this.E == null ? "0123456789ABCDEF" : f.this.E);
            }
            String a = f.this.g == 4 ? i.a(this.c) : "[]";
            String a2 = i.a(this.b, f.this.b());
            String a3 = i.a(f.this.E, f.this.F, f.this.G, f.this.H, f.this.K);
            String a4 = (this.a == null || !this.a.a()) ? "{}" : i.a(this.a);
            f.this.q.sendMessage(f.this.q.obtainMessage(R.styleable.Toolbar_titleMarginBottom, (new StringBuilder("{\"version\":\"1.1.8\",\"address\":").append(f.this.l).toString() + ",\"source\":203,\"access_token\":\"" + f.this.I + "\",\"app_name\":\"" + f.this.J + "\",\"bearing\":1") + ",\"attribute\":" + a3 + ",\"location\":" + a4 + ",\"cells\":" + a2 + ",\"wifis\":" + a + h.d));
        }
    }

    class b extends Thread {
        private String a;
        private String b;
        private String c;

        public b(String str) {
            this.a = null;
            this.b = null;
            this.c = null;
            this.a = str;
            this.b = (f.this.D == 0 ? "http://lstest.map.soso.com/loc?c=1" : "http://lbs.map.qq.com/loc?c=1") + "&mars=" + f.this.m;
        }

        private String a(byte[] bArr, String str) {
            f.this.M = System.currentTimeMillis();
            StringBuffer stringBuffer = new StringBuffer();
            try {
                stringBuffer.append(new String(bArr, str));
                return stringBuffer.toString();
            } catch (Exception e) {
                return null;
            }
        }

        public final void run() {
            Message message = new Message();
            message.what = 8;
            try {
                byte[] a = j.a(this.a.getBytes());
                f.this.p = true;
                n a2 = b.a(this.b, "SOSO MAP LBS SDK", a);
                f.this.p = false;
                this.c = a(j.b(a2.a), a2.b);
                if (this.c != null) {
                    message.arg1 = 0;
                    message.obj = this.c;
                } else {
                    message.arg1 = 1;
                }
            } catch (Exception e) {
                r0 = 0;
                while (true) {
                    int i;
                    i++;
                    if (i > 3) {
                        break;
                    }
                    try {
                        sleep(1000);
                        byte[] a3 = j.a(this.a.getBytes());
                        f.this.p = true;
                        n a4 = b.a(this.b, "SOSO MAP LBS SDK", a3);
                        f.this.p = false;
                        this.c = a(j.b(a4.a), a4.b);
                        if (this.c != null) {
                            message.arg1 = 0;
                            message.obj = this.c;
                        } else {
                            message.arg1 = 1;
                        }
                    } catch (Exception e2) {
                    }
                }
                f.this.p = false;
                message.arg1 = 1;
            }
            f.j(f.this);
            f.this.q.sendMessage(message);
        }
    }

    class c extends Handler {
        public c() {
            super(Looper.getMainLooper());
        }

        public final void handleMessage(Message message) {
            switch (message.what) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    f.a(f.this, (com.tencent.map.b.e.b) message.obj);
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    f.a(f.this, (com.tencent.map.b.d.b) message.obj);
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    f.a(f.this, (com.tencent.map.b.g.b) message.obj);
                case XZBDevice.DOWNLOAD_LIST_ALL:
                    f.a(f.this, message.arg1);
                case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                    f.b(f.this, message.arg1);
                case R.styleable.Toolbar_contentInsetEnd:
                    f.a(f.this, (Location) message.obj);
                case XZBDevice.Wait:
                    if (message.arg1 == 0) {
                        f.this.a((String) message.obj);
                    } else if (f.this.w == null || !f.this.w.a()) {
                        f.this.e();
                    }
                case R.styleable.Toolbar_titleMarginBottom:
                    if (message.obj != null) {
                        f.a(f.this, (String) message.obj);
                        f.this.s = null;
                    }
                case AccessibilityNodeInfoCompat.ACTION_NEXT_AT_MOVEMENT_GRANULARITY:
                    if (f.this.B == 1) {
                        f.this.d();
                    }
                default:
                    break;
            }
        }
    }

    static {
        t = false;
        u = null;
    }

    private f() {
        this.a = 5000;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = 1024;
        this.g = 4;
        this.h = null;
        this.i = null;
        this.j = null;
        this.n = new byte[0];
        this.o = new byte[0];
        this.p = false;
        this.q = null;
        this.r = null;
        this.s = null;
        this.v = -1;
        this.w = null;
        this.x = null;
        this.y = null;
        this.z = null;
        this.A = null;
        this.B = 0;
        this.C = 0;
        this.D = 1;
        this.E = com.umeng.a.d;
        this.F = com.umeng.a.d;
        this.G = com.umeng.a.d;
        this.H = com.umeng.a.d;
        this.I = com.umeng.a.d;
        this.J = com.umeng.a.d;
        this.K = false;
        this.L = false;
        this.M = 0;
        this.N = null;
        this.O = new Runnable() {
            public final void run() {
                if (System.currentTimeMillis() - f.this.M >= 8000) {
                    if (f.this.e.b() && f.this.e.c()) {
                        f.this.e.a(0);
                    } else {
                        f.this.d();
                    }
                }
            }
        };
        this.P = new BroadcastReceiver() {
            public final void onReceive(Context context, Intent intent) {
                if (!intent.getBooleanExtra("noConnectivity", false) && f.this.q != null) {
                    f.this.q.sendEmptyMessage(AccessibilityNodeInfoCompat.ACTION_NEXT_AT_MOVEMENT_GRANULARITY);
                }
            }
        };
        this.c = new e();
        this.d = new d();
        this.e = new g();
    }

    public static synchronized f a() {
        f fVar;
        synchronized (f.class) {
            if (u == null) {
                u = new f();
            }
            fVar = u;
        }
        return fVar;
    }

    private static ArrayList<com.tencent.map.a.a.c> a(JSONArray jSONArray) throws Exception {
        int length = jSONArray.length();
        ArrayList<com.tencent.map.a.a.c> arrayList = new ArrayList();
        for (int i = 0; i < length; i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            arrayList.add(new com.tencent.map.a.a.c(jSONObject.getString(SelectCountryActivity.EXTRA_COUNTRY_NAME), jSONObject.getString("addr"), jSONObject.getString("catalog"), jSONObject.getDouble("dist"), Double.parseDouble(jSONObject.getString(ParamKey.LATITUDE)), Double.parseDouble(jSONObject.getString(ParamKey.LONGITUDE))));
        }
        return arrayList;
    }

    static /* synthetic */ void a(f fVar, int i) {
        if (i == 0) {
            fVar.w = null;
        }
        fVar.f = i == 0 ? 1 : XZBDevice.DOWNLOAD_LIST_RECYCLE;
        if (fVar.j != null) {
            fVar.j.a(fVar.f);
        }
    }

    static /* synthetic */ void a(f fVar, Location location) {
        if (location == null || location.getLatitude() > 359.0d || location.getLongitude() > 359.0d) {
            if (fVar.w == null || !fVar.w.a()) {
                fVar.e();
            } else {
                fVar.b(true);
            }
        }
        fVar.z = new d();
        fVar.z.z = 0;
        fVar.z.y = 0;
        fVar.z.b = i.a(location.getLatitude(), (int) R.styleable.Toolbar_contentInsetEnd);
        fVar.z.c = i.a(location.getLongitude(), (int) R.styleable.Toolbar_contentInsetEnd);
        if (fVar.w != null && fVar.w.a()) {
            fVar.z.e = i.a((double) fVar.w.b().getAccuracy(), 1);
            fVar.z.d = i.a(fVar.w.b().getAltitude(), 1);
            fVar.z.f = i.a((double) fVar.w.b().getSpeed(), 1);
            fVar.z.g = i.a((double) fVar.w.b().getBearing(), 1);
            fVar.z.a = 0;
        }
        fVar.z.x = true;
        if (!(fVar.l == 0 || fVar.A == null || fVar.B != 0)) {
            if ((fVar.l == 3 || fVar.l == 4) && fVar.l == fVar.A.z) {
                fVar.z.i = fVar.A.i;
                fVar.z.j = fVar.A.j;
                fVar.z.k = fVar.A.k;
                fVar.z.l = fVar.A.l;
                fVar.z.m = fVar.A.m;
                fVar.z.n = fVar.A.n;
                fVar.z.o = fVar.A.o;
                fVar.z.p = fVar.A.p;
                fVar.z.z = 3;
            }
            if (fVar.l == 4 && fVar.l == fVar.A.z && fVar.A.w != null) {
                fVar.z.w = new ArrayList();
                Iterator it = fVar.A.w.iterator();
                while (it.hasNext()) {
                    fVar.z.w.add(new com.tencent.map.a.a.c((com.tencent.map.a.a.c) it.next()));
                }
                fVar.z.z = 4;
            }
            if (fVar.l == 7 && fVar.l == fVar.A.z) {
                fVar.z.z = 7;
                fVar.z.h = fVar.A.h;
                fVar.z.i = fVar.A.i;
                if (fVar.A.h == 0) {
                    fVar.z.j = fVar.A.j;
                    fVar.z.k = fVar.A.k;
                    fVar.z.l = fVar.A.l;
                    fVar.z.m = fVar.A.m;
                    fVar.z.n = fVar.A.n;
                    fVar.z.o = fVar.A.o;
                    fVar.z.p = fVar.A.p;
                } else {
                    fVar.z.q = fVar.A.q;
                    fVar.z.r = fVar.A.r;
                    fVar.z.s = fVar.A.s;
                    fVar.z.t = fVar.A.t;
                    fVar.z.u = fVar.A.u;
                    fVar.z.v = fVar.A.v;
                }
            }
        }
        if (fVar.B != 0 || fVar.A != null) {
            if (fVar.B != 0) {
                fVar.z.y = fVar.B;
            }
            if (System.currentTimeMillis() - fVar.v >= fVar.a && fVar.j != null && fVar.k == 1) {
                fVar.j.a(fVar.z);
                fVar.v = System.currentTimeMillis();
            }
        }
    }

    static /* synthetic */ void a(f fVar, com.tencent.map.b.d.b bVar) {
        fVar.x = bVar;
        if (fVar.e != null && fVar.e.b() && fVar.e.c()) {
            fVar.e.a(0);
            return;
        }
        if (fVar.C > 0 && !i.a(bVar.a, bVar.b, bVar.c, bVar.d, bVar.e)) {
            fVar.C--;
        }
        fVar.d();
    }

    static /* synthetic */ void a(f fVar, com.tencent.map.b.e.b bVar) {
        if (bVar != null) {
            fVar.w = bVar;
            if (fVar.k != 1 || fVar.w == null || !fVar.w.a()) {
                return;
            }
            if (fVar.m == 0) {
                fVar.b(false);
            } else if (fVar.m == 1 && fVar.i != null) {
                fVar.i.a(fVar.w.b().getLatitude(), fVar.w.b().getLongitude(), (com.tencent.map.b.b.a) fVar);
            }
        }
    }

    static /* synthetic */ void a(f fVar, com.tencent.map.b.g.b bVar) {
        if (bVar != null) {
            fVar.y = bVar;
            fVar.d();
        }
    }

    static /* synthetic */ void a(f fVar, String str) {
        if (i.c(str)) {
            if (fVar.k != 0 || fVar.j == null) {
                String b = fVar.h == null ? null : (fVar.x == null || fVar.y == null) ? null : fVar.h.b(fVar.x.b, fVar.x.c, fVar.x.d, fVar.x.e, fVar.y.a());
                if (b != null) {
                    fVar.a(b);
                    return;
                }
                if (!(fVar.h == null || fVar.x == null || fVar.y == null)) {
                    fVar.h.a(fVar.x.b, fVar.x.c, fVar.x.d, fVar.x.e, fVar.y.a());
                }
                if (!fVar.p) {
                    if (fVar.r != null) {
                        fVar.r.interrupt();
                    }
                    fVar.r = null;
                    fVar.r = new b(str);
                    fVar.r.start();
                    return;
                }
                return;
            }
            byte[] bytes;
            try {
                bytes = str.getBytes();
            } catch (Exception e) {
                bytes = null;
            }
            fVar.j.a(bytes, 0);
        } else if (fVar.C > 0) {
            fVar.C--;
        } else if (fVar.k == 0 && fVar.j != null) {
            fVar.j.a(null, -1);
        } else if (fVar.k == 1 && fVar.j != null) {
            fVar.z = new d();
            fVar.B = 3;
            fVar.z.y = 3;
            fVar.z.z = -1;
            fVar.j.a(fVar.z);
        }
    }

    private void a(String str) {
        int i = 0;
        try {
            double d;
            this.z = new d();
            JSONObject jSONObject = new JSONObject(str);
            JSONObject jSONObject2 = jSONObject.getJSONObject(ShareActivity.KEY_LOCATION);
            this.z.a = 1;
            this.z.b = i.a(jSONObject2.getDouble(ParamKey.LATITUDE), (int) R.styleable.Toolbar_contentInsetEnd);
            this.z.c = i.a(jSONObject2.getDouble(ParamKey.LONGITUDE), (int) R.styleable.Toolbar_contentInsetEnd);
            this.z.d = i.a(jSONObject2.getDouble("altitude"), 1);
            this.z.e = i.a(jSONObject2.getDouble("accuracy"), 1);
            this.z.x = this.m == 1;
            String string = jSONObject.getString("bearing");
            int i2 = ErrorConstant.ERROR_UNKNOWN;
            if (string != null && string.split(MiPushClient.ACCEPT_TIME_SEPARATOR).length > 1) {
                i = Integer.parseInt(string.split(MiPushClient.ACCEPT_TIME_SEPARATOR)[1]);
            }
            if (this.x != null) {
                i2 = this.x.f;
            }
            d dVar = this.z;
            double d2 = this.z.e;
            if (i >= 6) {
                d = 40.0d;
            } else if (i == 5) {
                d = 60.0d;
            } else if (i == 4) {
                d = 70.0d;
            } else if (i == 3) {
                d = 90.0d;
            } else if (i == 2) {
                d = 110.0d;
            } else {
                i2 = (i2 < -72 || i != 0) ? d2 <= 100.0d ? ((int) (((d2 - 1.0d) / 10.0d) + 1.0d)) * 10 : (d2 <= 100.0d || d2 > 800.0d) ? ((int) ((0.8d * d2) / 10.0d)) * 10 : ((int) ((0.85d * d2) / 10.0d)) * 10 : ((int) ((0.45d * d2) / 10.0d)) * 10;
                d = (double) i2;
            }
            dVar.e = d;
            this.z.z = 0;
            if ((this.l == 3 || this.l == 4) && this.m == 1) {
                jSONObject2 = jSONObject.getJSONObject("details").getJSONObject("subnation");
                this.z.a(jSONObject2.getString(SelectCountryActivity.EXTRA_COUNTRY_NAME));
                this.z.m = jSONObject2.getString("town");
                this.z.n = jSONObject2.getString("village");
                this.z.o = jSONObject2.getString("street");
                this.z.p = jSONObject2.getString("street_no");
                this.z.z = 3;
                this.z.h = 0;
            }
            if (this.l == 4 && this.m == 1) {
                this.z.w = a(jSONObject.getJSONObject("details").getJSONArray("poilist"));
                this.z.z = 4;
            }
            if (this.l == 7 && this.m == 1) {
                jSONObject2 = jSONObject.getJSONObject("details");
                i = jSONObject2.getInt("stat");
                jSONObject2 = jSONObject2.getJSONObject("subnation");
                if (i == 0) {
                    this.z.a(jSONObject2.getString(SelectCountryActivity.EXTRA_COUNTRY_NAME));
                    this.z.m = jSONObject2.getString("town");
                    this.z.n = jSONObject2.getString("village");
                    this.z.o = jSONObject2.getString("street");
                    this.z.p = jSONObject2.getString("street_no");
                } else if (i == 1) {
                    this.z.i = jSONObject2.getString("nation");
                    this.z.q = jSONObject2.getString("admin_level_1");
                    this.z.r = jSONObject2.getString("admin_level_2");
                    this.z.s = jSONObject2.getString("admin_level_3");
                    this.z.t = jSONObject2.getString("locality");
                    this.z.u = jSONObject2.getString("sublocality");
                    this.z.v = jSONObject2.getString("route");
                }
                this.z.h = i;
                this.z.z = 7;
            }
            this.z.y = 0;
            this.A = new d(this.z);
            this.B = 0;
            if (this.h != null) {
                this.h.a(str);
            }
        } catch (Exception e) {
            this.z = new d();
            this.z.z = -1;
            this.z.y = 2;
            this.B = 2;
        }
        if (this.j != null && this.k == 1) {
            if (this.w == null || !this.w.a()) {
                this.j.a(this.z);
                this.v = System.currentTimeMillis();
            }
        }
    }

    static /* synthetic */ void b(f fVar, int i) {
        int i2 = XZBDevice.DOWNLOAD_LIST_FAILED;
        if (i == 3) {
            i2 = XZBDevice.DOWNLOAD_LIST_ALL;
        }
        fVar.g = i2;
        if (fVar.j != null) {
            fVar.j.a(fVar.g);
        }
    }

    private void b(boolean z) {
        if (this.w != null && this.w.a()) {
            Location b = this.w.b();
            this.z = new d();
            this.z.b = i.a(b.getLatitude(), (int) R.styleable.Toolbar_contentInsetEnd);
            this.z.c = i.a(b.getLongitude(), (int) R.styleable.Toolbar_contentInsetEnd);
            this.z.d = i.a(b.getAltitude(), 1);
            this.z.e = i.a((double) b.getAccuracy(), 1);
            this.z.f = i.a((double) b.getSpeed(), 1);
            this.z.g = i.a((double) b.getBearing(), 1);
            this.z.a = 0;
            this.z.x = false;
            if (z) {
                this.z.y = 1;
            } else {
                this.z.y = 0;
            }
            this.z.z = 0;
            this.A = new d(this.z);
            this.B = 0;
            if (System.currentTimeMillis() - this.v >= this.a && this.j != null && this.k == 1) {
                this.j.a(this.z);
                this.v = System.currentTimeMillis();
            }
        }
    }

    private void d() {
        if (this.s == null) {
            this.s = new a(this.w, this.x, this.y);
            this.s.start();
        }
    }

    private void e() {
        this.z = new d();
        this.B = 1;
        this.z.y = 1;
        this.z.z = -1;
        this.z.a = 1;
        if (this.j != null && this.k == 1) {
            this.j.a(this.z);
        }
    }

    static /* synthetic */ void j(f fVar) {
    }

    public final void a(double d, double d2) {
        synchronized (this.o) {
            Message obtainMessage = this.q.obtainMessage(R.styleable.Toolbar_contentInsetEnd);
            Location location = new Location("Deflect");
            location.setLatitude(d);
            location.setLongitude(d2);
            obtainMessage.obj = location;
            this.q.sendMessage(obtainMessage);
        }
    }

    public final void a(int i) {
        synchronized (this.o) {
            this.q.sendMessage(this.q.obtainMessage(XZBDevice.DOWNLOAD_LIST_ALL, i, 0));
        }
    }

    public final void a(com.tencent.map.b.d.b bVar) {
        synchronized (this.o) {
            this.q.sendMessage(this.q.obtainMessage(XZBDevice.DOWNLOAD_LIST_RECYCLE, bVar));
        }
    }

    public final void a(com.tencent.map.b.e.b bVar) {
        synchronized (this.o) {
            this.q.sendMessage(this.q.obtainMessage(1, bVar));
        }
    }

    public final void a(com.tencent.map.b.g.b bVar) {
        synchronized (this.o) {
            this.q.sendMessage(this.q.obtainMessage(XZBDevice.DOWNLOAD_LIST_FAILED, bVar));
        }
    }

    public final boolean a(Context context, com.tencent.map.a.a.b bVar) {
        synchronized (this.n) {
            if (context == null || bVar == null) {
                return false;
            } else if (i.a(this.J)) {
                this.q = new c();
                this.N = new Handler(Looper.getMainLooper());
                this.b = context;
                this.j = bVar;
                l.a().a(this.b.getApplicationContext());
                try {
                    ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
                    if (!(connectivityManager == null || connectivityManager.getActiveNetworkInfo() == null)) {
                        this.K = connectivityManager.getActiveNetworkInfo().isRoaming();
                    }
                    this.b.registerReceiver(this.P, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
                } catch (Exception e) {
                }
                this.k = this.j.a();
                this.l = this.j.b();
                this.m = this.j.c();
                this.v = -1;
                if (this.l == 7) {
                    this.l = 0;
                }
                this.L = false;
                this.D = 1;
                boolean a = this.c.a((com.tencent.map.b.e.c) this, this.b);
                boolean a2 = this.d.a(this.b, (com.tencent.map.b.d.c) this);
                boolean a3 = this.e.a(this.b, this, 1);
                this.h = c.a();
                this.i = b.a();
                this.w = null;
                this.x = null;
                this.y = null;
                this.z = null;
                this.A = null;
                this.B = 0;
                if (this.h != null) {
                    this.h.b();
                }
                this.C = 1;
                if (a && this.m == 0) {
                    return true;
                } else if (a2 || a3) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    }

    public final boolean a(String str, String str2) {
        boolean z;
        synchronized (this.n) {
            if (a.a().a(str, str2)) {
                this.J = str;
                z = true;
            } else {
                z = false;
            }
        }
        return z;
    }

    public final void b() {
        synchronized (this.n) {
            try {
                if (this.j != null) {
                    this.j = null;
                    this.N.removeCallbacks(this.O);
                    this.b.unregisterReceiver(this.P);
                    this.c.a();
                    this.d.a();
                    this.e.a();
                }
            } catch (Exception e) {
            }
        }
    }

    public final void b(int i) {
        synchronized (this.o) {
            this.q.sendMessage(this.q.obtainMessage(XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED, i, 0));
        }
    }
}
