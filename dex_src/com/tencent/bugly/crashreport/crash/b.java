package com.tencent.bugly.crashreport.crash;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Parcel;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.crashreport.common.info.PlugInBean;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.common.strategy.a;
import com.tencent.bugly.proguard.an;
import com.tencent.bugly.proguard.ap;
import com.tencent.bugly.proguard.aq;
import com.tencent.bugly.proguard.ar;
import com.tencent.bugly.proguard.as;
import com.tencent.bugly.proguard.j;
import com.tencent.bugly.proguard.n;
import com.tencent.bugly.proguard.o;
import com.tencent.bugly.proguard.q;
import com.tencent.bugly.proguard.s;
import com.tencent.bugly.proguard.t;
import com.tencent.bugly.proguard.w;
import com.uc.addon.sdk.remote.TabsImpl;
import com.xunlei.download.DownloadManager;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.android.agoo.message.MessageService;
import org.android.spdy.SpdyAgent;

// compiled from: BUGLY
public final class b {
    private static int a;
    private Context b;
    private o c;
    private a d;
    private n e;
    private BuglyStrategy.a f;

    // compiled from: BUGLY
    final class AnonymousClass_1 implements s {
        private /* synthetic */ List a;

        AnonymousClass_1(List list) {
            this.a = list;
        }

        public final void a(boolean z) {
            b.a(z, this.a);
        }
    }

    static {
        a = 0;
    }

    public b(int i, Context context, t tVar, o oVar, a aVar, BuglyStrategy.a aVar2, n nVar) {
        a = i;
        this.b = context;
        this.c = oVar;
        this.d = aVar;
        this.f = aVar2;
        this.e = nVar;
    }

    private static List<a> a(List<a> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        long c = com.tencent.bugly.proguard.a.c();
        List<a> arrayList = new ArrayList();
        for (a aVar : list) {
            if (aVar.d && aVar.b < c - 86400000) {
                arrayList.add(aVar);
            }
        }
        return arrayList;
    }

    private CrashDetailBean a(List<a> list, CrashDetailBean crashDetailBean) {
        if (list == null || list.size() == 0) {
            return crashDetailBean;
        }
        CrashDetailBean crashDetailBean2;
        CrashDetailBean crashDetailBean3 = null;
        List arrayList = new ArrayList(10);
        for (a aVar : list) {
            if (aVar.e) {
                arrayList.add(aVar);
            }
        }
        if (arrayList.size() > 0) {
            List b = b(arrayList);
            if (b != null && b.size() > 0) {
                Collections.sort(b);
                int i = 0;
                while (i < b.size()) {
                    crashDetailBean2 = (CrashDetailBean) b.get(i);
                    if (i != 0) {
                        if (crashDetailBean2.s != null) {
                            String[] split = crashDetailBean2.s.split("\n");
                            if (split != null) {
                                int length = split.length;
                                for (int i2 = 0; i2 < length; i2++) {
                                    Object obj = split[i2];
                                    if (!crashDetailBean3.s.contains(obj)) {
                                        crashDetailBean3.t++;
                                        crashDetailBean3.s += obj + "\n";
                                    }
                                }
                            }
                        }
                        crashDetailBean2 = crashDetailBean3;
                    }
                    i++;
                    crashDetailBean3 = crashDetailBean2;
                }
                crashDetailBean2 = crashDetailBean3;
                if (crashDetailBean2 != null) {
                    crashDetailBean.j = true;
                    crashDetailBean.t = 0;
                    crashDetailBean.s = com.umeng.a.d;
                    crashDetailBean3 = crashDetailBean;
                } else {
                    crashDetailBean3 = crashDetailBean2;
                }
                for (a aVar2 : list) {
                    if (aVar2.e && !aVar2.d && !crashDetailBean3.s.contains(aVar2.b)) {
                        crashDetailBean3.t++;
                        crashDetailBean3.s += aVar2.b + "\n";
                    }
                }
                if (crashDetailBean3.r == crashDetailBean.r && !crashDetailBean3.s.contains(crashDetailBean.r)) {
                    crashDetailBean3.t++;
                    crashDetailBean3.s += crashDetailBean.r + "\n";
                    return crashDetailBean3;
                }
            }
        }
        crashDetailBean2 = null;
        if (crashDetailBean2 != null) {
            crashDetailBean3 = crashDetailBean2;
        } else {
            crashDetailBean.j = true;
            crashDetailBean.t = 0;
            crashDetailBean.s = com.umeng.a.d;
            crashDetailBean3 = crashDetailBean;
        }
        for (a aVar22 : list) {
            if (aVar22.e) {
            }
        }
        return crashDetailBean3.r == crashDetailBean.r ? crashDetailBean3 : crashDetailBean3;
    }

    public final boolean a(CrashDetailBean crashDetailBean) {
        return a(crashDetailBean, -123456789);
    }

    public final boolean a(CrashDetailBean crashDetailBean, int i) {
        int i2 = crashDetailBean.b;
        String str = crashDetailBean.n;
        str = crashDetailBean.p;
        str = crashDetailBean.q;
        long j = crashDetailBean.r;
        str = crashDetailBean.m;
        str = crashDetailBean.e;
        str = crashDetailBean.c;
        if (this.e != null && !this.e.c()) {
            return true;
        }
        if (crashDetailBean.b != 2) {
            q qVar = new q();
            qVar.b = 1;
            qVar.c = crashDetailBean.z;
            qVar.d = crashDetailBean.A;
            qVar.e = crashDetailBean.r;
            o.b(1);
            this.c.a(qVar);
            w.b("[crash] a crash occur, handling...", new Object[0]);
        } else {
            w.b("[crash] a caught exception occur, handling...", new Object[0]);
        }
        List<a> b = b();
        List list = null;
        if (b != null && b.size() > 0) {
            List arrayList = new ArrayList(10);
            List arrayList2 = new ArrayList(10);
            arrayList.addAll(a((List) b));
            b.removeAll(arrayList);
            if (!com.tencent.bugly.b.b && c.c) {
                int i3 = 0;
                for (a aVar : b) {
                    boolean z;
                    if (crashDetailBean.u.equals(aVar.c)) {
                        if (aVar.e) {
                            z = true;
                        }
                        arrayList2.add(aVar);
                    }
                    z = z;
                }
                if (i3 != 0 || arrayList2.size() + 1 >= 5) {
                    w.a("same crash occur too much do merged!", new Object[0]);
                    CrashDetailBean a = a(arrayList2, crashDetailBean);
                    a.a = -1;
                    c(a);
                    arrayList.addAll(arrayList2);
                    c(arrayList);
                    w.b("[crash] save crash success. this device crash many times, won't upload crashes immediately", new Object[0]);
                    return true;
                }
            }
            list = arrayList;
        }
        c(crashDetailBean);
        c(list);
        w.b("[crash] save crash success", new Object[0]);
        return false;
    }

    public final List<CrashDetailBean> a() {
        StrategyBean c = a.a().c();
        if (c == null) {
            w.d("have not synced remote!", new Object[0]);
            return null;
        } else if (c.d) {
            long currentTimeMillis = System.currentTimeMillis();
            long c2 = com.tencent.bugly.proguard.a.c();
            List b = b();
            if (b == null || b.size() <= 0) {
                return null;
            }
            List arrayList = new ArrayList();
            Iterator it = b.iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                if (aVar.b < c2 - c.f) {
                    it.remove();
                    arrayList.add(aVar);
                } else if (aVar.d) {
                    if (aVar.b >= currentTimeMillis - 86400000) {
                        it.remove();
                    } else if (!aVar.e) {
                        it.remove();
                        arrayList.add(aVar);
                    }
                } else if (((long) aVar.f) >= 3 && aVar.b < currentTimeMillis - 86400000) {
                    it.remove();
                    arrayList.add(aVar);
                }
            }
            if (arrayList.size() > 0) {
                c(arrayList);
            }
            List arrayList2 = new ArrayList();
            List<CrashDetailBean> b2 = b(b);
            if (b2 != null && b2.size() > 0) {
                String str = com.tencent.bugly.crashreport.common.info.a.a().i;
                Iterator it2 = b2.iterator();
                while (it2.hasNext()) {
                    CrashDetailBean crashDetailBean = (CrashDetailBean) it2.next();
                    if (!str.equals(crashDetailBean.f)) {
                        it2.remove();
                        arrayList2.add(crashDetailBean);
                    }
                }
            }
            if (arrayList2.size() > 0) {
                d(arrayList2);
            }
            return b2;
        } else {
            w.d("Crashreport remote closed, please check your APPID correct and Version available, then uninstall and reinstall your app.", new Object[0]);
            w.b("[init] WARNING! Crashreport closed by server, please check your APPID correct and Version available, then uninstall and reinstall your app.", new Object[0]);
            return null;
        }
    }

    public final void a(CrashDetailBean crashDetailBean, long j, boolean z) {
        w.a("try to upload right now", new Object[0]);
        List arrayList = new ArrayList();
        arrayList.add(crashDetailBean);
        if (crashDetailBean.b == 7) {
            a(arrayList, TabsImpl.SYNC_TIME_OUT, z, true);
        } else {
            a(arrayList, TabsImpl.SYNC_TIME_OUT, z, false);
        }
        int i = crashDetailBean.b;
    }

    public final void a(List<CrashDetailBean> list, long j, boolean z, boolean z2) {
        if (!this.d.c().d) {
            w.d("remote report is disable!", new Object[0]);
            w.b("[crash] server closed bugly in this app. please check your appid if is correct, and re-install it", new Object[0]);
        } else if (list != null && list.size() != 0) {
            try {
                j jVar;
                Context context = this.b;
                com.tencent.bugly.crashreport.common.info.a a = com.tencent.bugly.crashreport.common.info.a.a();
                if (context == null || list == null || list.size() == 0 || a == null) {
                    w.d("enEXPPkg args == null!", new Object[0]);
                    jVar = null;
                } else {
                    a.s();
                    j arVar = new ar();
                    arVar.a = new ArrayList();
                    for (CrashDetailBean crashDetailBean : list) {
                        arVar.a.add(a(context, crashDetailBean, a));
                    }
                    jVar = arVar;
                }
                if (jVar == null) {
                    w.d("create eupPkg fail!", new Object[0]);
                    return;
                }
                byte[] a2 = com.tencent.bugly.proguard.a.a(jVar);
                if (a2 == null) {
                    w.d("send encode fail!", new Object[0]);
                    return;
                }
                as a3 = com.tencent.bugly.proguard.a.a(this.b, 630, a2);
                if (a3 == null) {
                    w.d("request package is null.", new Object[0]);
                    return;
                }
                s anonymousClass_1 = new AnonymousClass_1(list);
                if (z) {
                    if (z2) {
                        t.a().a(a, a3, null, anonymousClass_1, true, j, true);
                    } else {
                        t.a().a(a, a3, null, anonymousClass_1, true, j, false);
                    }
                    w.a("wake up!", new Object[0]);
                } else if (z2) {
                    t.a().a(a, a3, null, anonymousClass_1, true);
                } else {
                    t.a().a(a, a3, null, anonymousClass_1, false);
                }
            } catch (Throwable th) {
                w.e("req cr error %s", th.toString());
                if (!w.b(th)) {
                    th.printStackTrace();
                }
            }
        }
    }

    public static void a(boolean z, List<CrashDetailBean> list) {
        if (list != null && list.size() > 0) {
            w.c("up finish update state %b", Boolean.valueOf(z));
            for (CrashDetailBean crashDetailBean : list) {
                w.c("pre uid:%s uc:%d re:%b me:%b", crashDetailBean.c, Integer.valueOf(crashDetailBean.l), Boolean.valueOf(crashDetailBean.d), Boolean.valueOf(crashDetailBean.j));
                crashDetailBean.l++;
                crashDetailBean.d = z;
                w.c("set uid:%s uc:%d re:%b me:%b", crashDetailBean.c, Integer.valueOf(crashDetailBean.l), Boolean.valueOf(crashDetailBean.d), Boolean.valueOf(crashDetailBean.j));
            }
            for (CrashDetailBean crashDetailBean2 : list) {
                c.a().a(crashDetailBean2);
            }
            w.c("update state size %d", Integer.valueOf(list.size()));
        }
        if (!z) {
            w.b("[crash] upload fail.", new Object[0]);
        }
    }

    public final void b(CrashDetailBean crashDetailBean) {
        if (crashDetailBean != null) {
            if (this.f != null || this.e != null) {
                try {
                    int i;
                    String b;
                    w.a("[crash callback] start user's callback:onCrashHandleStart()", new Object[0]);
                    switch (crashDetailBean.b) {
                        case SpdyAgent.ACCS_TEST_SERVER:
                            i = 0;
                            break;
                        case SpdyAgent.ACCS_ONLINE_SERVER:
                            i = 2;
                            break;
                        case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                            i = 1;
                            break;
                        case XZBDevice.DOWNLOAD_LIST_FAILED:
                            i = 4;
                            break;
                        case XZBDevice.DOWNLOAD_LIST_ALL:
                            i = 3;
                            break;
                        case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                            i = 5;
                            break;
                        case R.styleable.Toolbar_contentInsetEnd:
                            i = 6;
                            break;
                        case R.styleable.Toolbar_contentInsetLeft:
                            i = 7;
                            break;
                        default:
                            return;
                    }
                    int i2 = crashDetailBean.b;
                    String str = crashDetailBean.n;
                    str = crashDetailBean.p;
                    str = crashDetailBean.q;
                    long j = crashDetailBean.r;
                    Map map = null;
                    if (this.e != null) {
                        b = this.e.b();
                        if (b != null) {
                            map = new HashMap(1);
                            map.put("userData", b);
                        }
                    } else if (this.f != null) {
                        map = this.f.onCrashHandleStart(i, crashDetailBean.n, crashDetailBean.o, crashDetailBean.q);
                    }
                    if (map != null && map.size() > 0) {
                        crashDetailBean.N = new LinkedHashMap(map.size());
                        for (Entry entry : map.entrySet()) {
                            b = (String) entry.getKey();
                            int i3 = (b == null || b.trim().length() <= 0) ? 1 : 0;
                            if (i3 == 0) {
                                b = (String) entry.getKey();
                                if (b.length() > 100) {
                                    b = b.substring(0, R.styleable.AppCompatTheme_buttonStyle);
                                    w.d("setted key length is over limit %d substring to %s", Integer.valueOf(R.styleable.AppCompatTheme_buttonStyle), b);
                                }
                                String str2 = b;
                                b = (String) entry.getValue();
                                i3 = (b == null || b.trim().length() <= 0) ? 1 : 0;
                                if (i3 != 0 || ((String) entry.getValue()).length() <= 30000) {
                                    str = ((String) entry.getValue());
                                } else {
                                    str = ((String) entry.getValue()).substring(((String) entry.getValue()).length() - 30000);
                                    w.d("setted %s value length is over limit %d substring", str2, Integer.valueOf(BuglyStrategy.a.MAX_USERDATA_VALUE_LENGTH));
                                }
                                crashDetailBean.N.put(str2, str);
                                w.a("add setted key %s value size:%d", str2, Integer.valueOf(str.length()));
                            }
                        }
                    }
                    w.a("[crash callback] start user's callback:onCrashHandleStart2GetExtraDatas()", new Object[0]);
                    byte[] bArr = null;
                    if (this.e != null) {
                        bArr = this.e.a();
                    } else if (this.f != null) {
                        bArr = this.f.onCrashHandleStart2GetExtraDatas(i, crashDetailBean.n, crashDetailBean.o, crashDetailBean.q);
                    }
                    crashDetailBean.S = bArr;
                    if (crashDetailBean.S != null) {
                        if (crashDetailBean.S.length > 30000) {
                            w.d("extra bytes size %d is over limit %d will drop over part", Integer.valueOf(crashDetailBean.S.length), Integer.valueOf(BuglyStrategy.a.MAX_USERDATA_VALUE_LENGTH));
                        }
                        w.a("add extra bytes %d ", Integer.valueOf(crashDetailBean.S.length));
                    }
                } catch (Throwable th) {
                    w.d("crash handle callback somthing wrong! %s", th.getClass().getName());
                    if (!w.a(th)) {
                        th.printStackTrace();
                    }
                }
            }
        }
    }

    private static ContentValues d(CrashDetailBean crashDetailBean) {
        int i = 1;
        if (crashDetailBean == null) {
            return null;
        }
        try {
            int i2;
            ContentValues contentValues = new ContentValues();
            if (crashDetailBean.a > 0) {
                contentValues.put(DownloadManager.COLUMN_ID, Long.valueOf(crashDetailBean.a));
            }
            contentValues.put("_tm", Long.valueOf(crashDetailBean.r));
            contentValues.put("_s1", crashDetailBean.u);
            String str = "_up";
            if (crashDetailBean.d) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            contentValues.put(str, Integer.valueOf(i2));
            String str2 = "_me";
            if (!crashDetailBean.j) {
                i = 0;
            }
            contentValues.put(str2, Integer.valueOf(i));
            contentValues.put("_uc", Integer.valueOf(crashDetailBean.l));
            Parcel obtain = Parcel.obtain();
            crashDetailBean.writeToParcel(obtain, 0);
            byte[] marshall = obtain.marshall();
            obtain.recycle();
            contentValues.put("_dt", marshall);
            return contentValues;
        } catch (Throwable th) {
            if (w.a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    private static CrashDetailBean a(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            byte[] blob = cursor.getBlob(cursor.getColumnIndex("_dt"));
            if (blob == null) {
                return null;
            }
            long j = cursor.getLong(cursor.getColumnIndex(DownloadManager.COLUMN_ID));
            CrashDetailBean crashDetailBean = (CrashDetailBean) com.tencent.bugly.proguard.a.a(blob, CrashDetailBean.CREATOR);
            if (crashDetailBean == null) {
                return crashDetailBean;
            }
            crashDetailBean.a = j;
            return crashDetailBean;
        } catch (Throwable th) {
            if (!w.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public final void c(CrashDetailBean crashDetailBean) {
        if (crashDetailBean != null) {
            ContentValues d = d(crashDetailBean);
            if (d != null) {
                long a = o.a().a("t_cr", d, null, true);
                if (a >= 0) {
                    w.c("insert %s success!", "t_cr");
                    crashDetailBean.a = a;
                }
            }
        }
    }

    private List<CrashDetailBean> b(List<a> list) {
        Throwable th;
        if (list == null || list.size() == 0) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (a aVar : list) {
            stringBuilder.append(" or _id = ").append(aVar.a);
        }
        String toString = stringBuilder.toString();
        if (toString.length() > 0) {
            toString = toString.substring(XZBDevice.DOWNLOAD_LIST_ALL);
        }
        stringBuilder.setLength(0);
        try {
            Cursor a = o.a().a("t_cr", null, toString, null, null, true);
            if (a == null) {
                if (!(a == null || a.isClosed())) {
                    a.close();
                }
                return null;
            }
            try {
                List<CrashDetailBean> arrayList = new ArrayList();
                while (a.moveToNext()) {
                    CrashDetailBean a2 = a(a);
                    if (a2 != null) {
                        arrayList.add(a2);
                    } else {
                        try {
                            stringBuilder.append(" or _id = ").append(a.getLong(a.getColumnIndex(DownloadManager.COLUMN_ID)));
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    }
                }
                String toString2 = stringBuilder.toString();
                if (toString2.length() > 0) {
                    int a3 = o.a().a("t_cr", toString2.substring(XZBDevice.DOWNLOAD_LIST_ALL), null, null, true);
                    w.d("deleted %s illegle data %d", "t_cr", Integer.valueOf(a3));
                }
                if (!(a == null || a.isClosed())) {
                    a.close();
                }
                return arrayList;
            } catch (Throwable th22) {
                th = th22;
            }
        } catch (Throwable th3) {
            th = th3;
            a = null;
            a.close();
            throw th;
        }
    }

    private static a b(Cursor cursor) {
        boolean z = true;
        if (cursor == null) {
            return null;
        }
        try {
            a aVar = new a();
            aVar.a = cursor.getLong(cursor.getColumnIndex(DownloadManager.COLUMN_ID));
            aVar.b = cursor.getLong(cursor.getColumnIndex("_tm"));
            aVar.c = cursor.getString(cursor.getColumnIndex("_s1"));
            aVar.d = cursor.getInt(cursor.getColumnIndex("_up")) == 1;
            if (cursor.getInt(cursor.getColumnIndex("_me")) != 1) {
                z = false;
            }
            aVar.e = z;
            aVar.f = cursor.getInt(cursor.getColumnIndex("_uc"));
            return aVar;
        } catch (Throwable th) {
            if (w.a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    private List<a> b() {
        Cursor cursor = null;
        List<a> arrayList = new ArrayList();
        try {
            Cursor a = o.a().a("t_cr", new String[]{DownloadManager.COLUMN_ID, "_tm", "_s1", "_up", "_me", "_uc"}, null, null, null, true);
            if (a == null) {
                if (!(a == null || a.isClosed())) {
                    a.close();
                }
                return null;
            }
            try {
                StringBuilder stringBuilder = new StringBuilder();
                while (a.moveToNext()) {
                    a b = b(a);
                    if (b != null) {
                        arrayList.add(b);
                    } else {
                        try {
                            stringBuilder.append(" or _id = ").append(a.getLong(a.getColumnIndex(DownloadManager.COLUMN_ID)));
                        } catch (Throwable th) {
                            th = th;
                        }
                    }
                }
                String toString = stringBuilder.toString();
                if (toString.length() > 0) {
                    int a2 = o.a().a("t_cr", toString.substring(XZBDevice.DOWNLOAD_LIST_ALL), null, null, true);
                    w.d("deleted %s illegle data %d", "t_cr", Integer.valueOf(a2));
                }
                if (!(a == null || a.isClosed())) {
                    a.close();
                }
                return arrayList;
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
            a = null;
            a.close();
            throw th;
        }
    }

    private static void c(List<a> list) {
        if (list != null && list.size() != 0) {
            StringBuilder stringBuilder = new StringBuilder();
            for (a aVar : list) {
                stringBuilder.append(" or _id = ").append(aVar.a);
            }
            String toString = stringBuilder.toString();
            if (toString.length() > 0) {
                toString = toString.substring(XZBDevice.DOWNLOAD_LIST_ALL);
            }
            stringBuilder.setLength(0);
            try {
                int a = o.a().a("t_cr", toString, null, null, true);
                w.c("deleted %s data %d", "t_cr", Integer.valueOf(a));
            } catch (Throwable th) {
                if (!w.a(th)) {
                    th.printStackTrace();
                }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void d(java.util.List<com.tencent.bugly.crashreport.crash.CrashDetailBean> r6) {
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.b.d(java.util.List):void");
        /*
        if (r6 == 0) goto L_0x0008;
    L_0x0002:
        r0 = r6.size();	 Catch:{ Throwable -> 0x002b }
        if (r0 != 0) goto L_0x0009;
    L_0x0008:
        return;
    L_0x0009:
        r1 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x002b }
        r1.<init>();	 Catch:{ Throwable -> 0x002b }
        r2 = r6.iterator();	 Catch:{ Throwable -> 0x002b }
    L_0x0012:
        r0 = r2.hasNext();	 Catch:{ Throwable -> 0x002b }
        if (r0 == 0) goto L_0x0036;
    L_0x0018:
        r0 = r2.next();	 Catch:{ Throwable -> 0x002b }
        r0 = (com.tencent.bugly.crashreport.crash.CrashDetailBean) r0;	 Catch:{ Throwable -> 0x002b }
        r3 = " or _id = ";
        r3 = r1.append(r3);	 Catch:{ Throwable -> 0x002b }
        r4 = r0.a;	 Catch:{ Throwable -> 0x002b }
        r3.append(r4);	 Catch:{ Throwable -> 0x002b }
        goto L_0x0012;
    L_0x002b:
        r0 = move-exception;
        r1 = com.tencent.bugly.proguard.w.a(r0);
        if (r1 != 0) goto L_0x0008;
    L_0x0032:
        r0.printStackTrace();
        goto L_0x0008;
    L_0x0036:
        r2 = r1.toString();	 Catch:{ Throwable -> 0x002b }
        r0 = r2.length();	 Catch:{ Throwable -> 0x002b }
        if (r0 <= 0) goto L_0x0045;
    L_0x0040:
        r0 = 4;
        r2 = r2.substring(r0);	 Catch:{ Throwable -> 0x002b }
    L_0x0045:
        r0 = 0;
        r1.setLength(r0);	 Catch:{ Throwable -> 0x002b }
        r0 = com.tencent.bugly.proguard.o.a();	 Catch:{ Throwable -> 0x002b }
        r1 = "t_cr";
        r3 = 0;
        r4 = 0;
        r5 = 1;
        r0 = r0.a(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x002b }
        r1 = "deleted %s data %d";
        r2 = 2;
        r2 = new java.lang.Object[r2];	 Catch:{ Throwable -> 0x002b }
        r3 = 0;
        r4 = "t_cr";
        r2[r3] = r4;	 Catch:{ Throwable -> 0x002b }
        r3 = 1;
        r0 = java.lang.Integer.valueOf(r0);	 Catch:{ Throwable -> 0x002b }
        r2[r3] = r0;	 Catch:{ Throwable -> 0x002b }
        com.tencent.bugly.proguard.w.c(r1, r2);	 Catch:{ Throwable -> 0x002b }
        goto L_0x0008;
        */
    }

    private static aq a(Context context, CrashDetailBean crashDetailBean, com.tencent.bugly.crashreport.common.info.a aVar) {
        boolean z = true;
        if (context == null || crashDetailBean == null || aVar == null) {
            w.d("enExp args == null", new Object[0]);
            return null;
        }
        ap a;
        aq aqVar = new aq();
        switch (crashDetailBean.b) {
            case SpdyAgent.ACCS_TEST_SERVER:
                aqVar.a = crashDetailBean.j ? "200" : MessageService.MSG_DB_COMPLETE;
                break;
            case SpdyAgent.ACCS_ONLINE_SERVER:
                aqVar.a = crashDetailBean.j ? "201" : "101";
                break;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                aqVar.a = crashDetailBean.j ? "202" : "102";
                break;
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                aqVar.a = crashDetailBean.j ? "203" : "103";
                break;
            case XZBDevice.DOWNLOAD_LIST_ALL:
                aqVar.a = crashDetailBean.j ? "204" : "104";
                break;
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                aqVar.a = crashDetailBean.j ? "207" : "107";
                break;
            case R.styleable.Toolbar_contentInsetEnd:
                aqVar.a = crashDetailBean.j ? "206" : "106";
                break;
            case R.styleable.Toolbar_contentInsetLeft:
                aqVar.a = crashDetailBean.j ? "208" : "108";
                break;
            default:
                w.e("crash type error! %d", Integer.valueOf(crashDetailBean.b));
                break;
        }
        aqVar.b = crashDetailBean.r;
        aqVar.c = crashDetailBean.n;
        aqVar.d = crashDetailBean.o;
        aqVar.e = crashDetailBean.p;
        aqVar.g = crashDetailBean.q;
        aqVar.h = crashDetailBean.y;
        aqVar.i = crashDetailBean.c;
        aqVar.j = null;
        aqVar.l = crashDetailBean.m;
        aqVar.m = crashDetailBean.e;
        aqVar.f = crashDetailBean.A;
        aqVar.t = com.tencent.bugly.crashreport.common.info.a.a().h();
        aqVar.n = null;
        if (crashDetailBean.i != null && crashDetailBean.i.size() > 0) {
            aqVar.o = new ArrayList();
            for (Entry entry : crashDetailBean.i.entrySet()) {
                an anVar = new an();
                anVar.a = ((PlugInBean) entry.getValue()).a;
                anVar.c = ((PlugInBean) entry.getValue()).c;
                anVar.d = ((PlugInBean) entry.getValue()).b;
                anVar.b = aVar.q();
                aqVar.o.add(anVar);
            }
        }
        if (crashDetailBean.h != null && crashDetailBean.h.size() > 0) {
            aqVar.p = new ArrayList();
            for (Entry entry2 : crashDetailBean.h.entrySet()) {
                anVar = new an();
                anVar.a = ((PlugInBean) entry2.getValue()).a;
                anVar.c = ((PlugInBean) entry2.getValue()).c;
                anVar.d = ((PlugInBean) entry2.getValue()).b;
                aqVar.p.add(anVar);
            }
        }
        if (crashDetailBean.j) {
            int size;
            aqVar.k = crashDetailBean.t;
            if (crashDetailBean.s != null && crashDetailBean.s.length() > 0) {
                if (aqVar.q == null) {
                    aqVar.q = new ArrayList();
                }
                try {
                    aqVar.q.add(new ap((byte) 1, "alltimes.txt", crashDetailBean.s.getBytes("utf-8")));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    aqVar.q = null;
                }
            }
            String str = "crashcount:%d sz:%d";
            Object[] objArr = new Object[2];
            objArr[0] = Integer.valueOf(aqVar.k);
            if (aqVar.q != null) {
                size = aqVar.q.size();
            } else {
                size = 0;
            }
            objArr[1] = Integer.valueOf(size);
            w.c(str, objArr);
        }
        if (crashDetailBean.w != null) {
            if (aqVar.q == null) {
                aqVar.q = new ArrayList();
            }
            try {
                aqVar.q.add(new ap((byte) 1, "log.txt", crashDetailBean.w.getBytes("utf-8")));
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
                aqVar.q = null;
            }
        }
        if (crashDetailBean.T != null || crashDetailBean.T.length() > 0) {
            Object apVar;
            if (aqVar.q == null) {
                aqVar.q = new ArrayList();
            }
            try {
                apVar = new ap((byte) 1, "crashInfos.txt", crashDetailBean.T.getBytes("utf-8"));
            } catch (UnsupportedEncodingException e22) {
                e22.printStackTrace();
                apVar = null;
            }
            if (apVar != null) {
                w.c("attach crash infos", new Object[0]);
                aqVar.q.add(apVar);
            }
        }
        if (crashDetailBean.U != null) {
            if (aqVar.q == null) {
                aqVar.q = new ArrayList();
            }
            a = a("backupRecord.zip", context, crashDetailBean.U);
            if (a != null) {
                w.c("attach backup record", new Object[0]);
                aqVar.q.add(a);
            }
        }
        if (crashDetailBean.x != null && crashDetailBean.x.length > 0) {
            a = new ap((byte) 2, "buglylog.zip", crashDetailBean.x);
            w.c("attach user log", new Object[0]);
            if (aqVar.q == null) {
                aqVar.q = new ArrayList();
            }
            aqVar.q.add(a);
        }
        if (crashDetailBean.b == 3) {
            if (aqVar.q == null) {
                aqVar.q = new ArrayList();
            }
            if (crashDetailBean.N != null && crashDetailBean.N.containsKey("BUGLY_CR_01")) {
                try {
                    aqVar.q.add(new ap((byte) 1, "anrMessage.txt", ((String) crashDetailBean.N.get("BUGLY_CR_01")).getBytes("utf-8")));
                    w.c("attach anr message", new Object[0]);
                } catch (UnsupportedEncodingException e222) {
                    e222.printStackTrace();
                    aqVar.q = null;
                }
                crashDetailBean.N.remove("BUGLY_CR_01");
            }
            if (crashDetailBean.v != null) {
                a = a("trace.zip", context, crashDetailBean.v);
                if (a != null) {
                    w.c("attach traces", new Object[0]);
                    aqVar.q.add(a);
                }
            }
        }
        if (crashDetailBean.b == 1) {
            if (aqVar.q == null) {
                aqVar.q = new ArrayList();
            }
            if (crashDetailBean.v != null) {
                a = a("tomb.zip", context, crashDetailBean.v);
                if (a != null) {
                    w.c("attach tombs", new Object[0]);
                    aqVar.q.add(a);
                }
            }
        }
        if (crashDetailBean.S != null && crashDetailBean.S.length > 0) {
            if (aqVar.q == null) {
                aqVar.q = new ArrayList();
            }
            aqVar.q.add(new ap((byte) 1, "userExtraByteData", crashDetailBean.S));
            w.c("attach extraData", new Object[0]);
        }
        aqVar.r = new HashMap();
        aqVar.r.put("A9", crashDetailBean.B);
        aqVar.r.put("A11", crashDetailBean.C);
        aqVar.r.put("A10", crashDetailBean.D);
        aqVar.r.put("A23", crashDetailBean.f);
        aqVar.r.put("A7", aVar.e);
        aqVar.r.put("A6", aVar.r());
        aqVar.r.put("A5", aVar.q());
        aqVar.r.put("A22", aVar.g());
        aqVar.r.put("A2", crashDetailBean.F);
        aqVar.r.put("A1", crashDetailBean.E);
        aqVar.r.put("A24", aVar.g);
        aqVar.r.put("A17", crashDetailBean.G);
        aqVar.r.put("A3", aVar.j());
        aqVar.r.put("A16", aVar.l());
        aqVar.r.put("A25", aVar.m());
        aqVar.r.put("A14", aVar.k());
        aqVar.r.put("A15", aVar.v());
        aqVar.r.put("A13", aVar.w());
        aqVar.r.put("A34", crashDetailBean.z);
        if (aVar.w != null) {
            aqVar.r.put("productIdentify", aVar.w);
        }
        try {
            aqVar.r.put("A26", URLEncoder.encode(crashDetailBean.H, "utf-8"));
        } catch (UnsupportedEncodingException e2222) {
            e2222.printStackTrace();
        }
        if (crashDetailBean.b == 1) {
            aqVar.r.put("A27", crashDetailBean.J);
            aqVar.r.put("A28", crashDetailBean.I);
            aqVar.r.put("A29", crashDetailBean.k);
        }
        aqVar.r.put("A30", crashDetailBean.K);
        aqVar.r.put("A18", crashDetailBean.L);
        aqVar.r.put("A36", (!crashDetailBean.M));
        aqVar.r.put("F02", aVar.p);
        aqVar.r.put("F03", aVar.q);
        aqVar.r.put("F04", aVar.d());
        aqVar.r.put("F05", aVar.r);
        aqVar.r.put("F06", aVar.o);
        aqVar.r.put("F08", aVar.u);
        aqVar.r.put("F09", aVar.v);
        aqVar.r.put("F10", aVar.s);
        if (crashDetailBean.O >= 0) {
            aqVar.r.put("C01", crashDetailBean.O);
        }
        if (crashDetailBean.P >= 0) {
            aqVar.r.put("C02", crashDetailBean.P);
        }
        if (crashDetailBean.Q != null && crashDetailBean.Q.size() > 0) {
            for (Entry entry22 : crashDetailBean.Q.entrySet()) {
                aqVar.r.put(new StringBuilder("C03_").append((String) entry22.getKey()).toString(), entry22.getValue());
            }
        }
        if (crashDetailBean.R != null && crashDetailBean.R.size() > 0) {
            for (Entry entry222 : crashDetailBean.R.entrySet()) {
                aqVar.r.put(new StringBuilder("C04_").append((String) entry222.getKey()).toString(), entry222.getValue());
            }
        }
        aqVar.s = null;
        if (crashDetailBean.N != null && crashDetailBean.N.size() > 0) {
            aqVar.s = crashDetailBean.N;
            w.a("setted message size %d", Integer.valueOf(aqVar.s.size()));
        }
        String str2 = "%s rid:%s sess:%s ls:%ds isR:%b isF:%b isM:%b isN:%b mc:%d ,%s ,isUp:%b ,vm:%d";
        Object[] objArr2 = new Object[12];
        objArr2[0] = crashDetailBean.n;
        objArr2[1] = crashDetailBean.c;
        objArr2[2] = aVar.d();
        objArr2[3] = Long.valueOf((crashDetailBean.r - crashDetailBean.L) / 1000);
        objArr2[4] = Boolean.valueOf(crashDetailBean.k);
        objArr2[5] = Boolean.valueOf(crashDetailBean.M);
        objArr2[6] = Boolean.valueOf(crashDetailBean.j);
        if (crashDetailBean.b != 1) {
            z = false;
        }
        objArr2[7] = Boolean.valueOf(z);
        objArr2[8] = Integer.valueOf(crashDetailBean.t);
        objArr2[9] = crashDetailBean.s;
        objArr2[10] = Boolean.valueOf(crashDetailBean.d);
        objArr2[11] = Integer.valueOf(aqVar.r.size());
        w.c(str2, objArr2);
        return aqVar;
    }

    private static ap a(String str, Context context, String str2) {
        FileInputStream fileInputStream;
        Throwable e;
        Throwable th;
        if (str2 == null || context == null) {
            w.d("rqdp{  createZipAttachment sourcePath == null || context == null ,pls check}", new Object[0]);
            return null;
        }
        w.c("zip %s", str2);
        File file = new File(str2);
        File file2 = new File(context.getCacheDir(), str);
        if (com.tencent.bugly.proguard.a.a(file, file2, 5000)) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                fileInputStream = new FileInputStream(file2);
                try {
                    byte[] bArr = new byte[1000];
                    while (true) {
                        int read = fileInputStream.read(bArr);
                        if (read <= 0) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr, 0, read);
                        byteArrayOutputStream.flush();
                    }
                    w.c("read bytes :%d", Integer.valueOf(byteArrayOutputStream.toByteArray().length));
                    ap apVar = new ap((byte) 2, file2.getName(), bArr);
                    try {
                        fileInputStream.close();
                    } catch (Throwable e2) {
                        if (!w.a(e2)) {
                            e2.printStackTrace();
                        }
                    }
                    if (file2.exists()) {
                        w.c("del tmp", new Object[0]);
                        file2.delete();
                    }
                    return apVar;
                } catch (Throwable th2) {
                    th = th2;
                    try {
                        if (!w.a(th)) {
                            th.printStackTrace();
                        }
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (Throwable th3) {
                                if (!w.a(th3)) {
                                    th3.printStackTrace();
                                }
                            }
                        }
                        if (file2.exists()) {
                            return null;
                        }
                        w.c("del tmp", new Object[0]);
                        file2.delete();
                        return null;
                    } catch (Throwable th4) {
                        e2 = th4;
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (Throwable th32) {
                                if (!w.a(th32)) {
                                    th32.printStackTrace();
                                }
                            }
                        }
                        if (file2.exists()) {
                            w.c("del tmp", new Object[0]);
                            file2.delete();
                        }
                        throw e2;
                    }
                }
            } catch (Throwable th322) {
                fileInputStream = null;
                e2 = th322;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (file2.exists()) {
                    w.c("del tmp", new Object[0]);
                    file2.delete();
                }
                throw e2;
            }
        }
        w.d("zip fail!", new Object[0]);
        return null;
    }

    public static void a(String str, String str2, String str3, Thread thread, String str4, CrashDetailBean crashDetailBean) {
        com.tencent.bugly.crashreport.common.info.a a = com.tencent.bugly.crashreport.common.info.a.a();
        if (a != null) {
            int i;
            w.e("#++++++++++Record By Bugly++++++++++#", new Object[0]);
            w.e("# You can use Bugly(http:\\\\bugly.qq.com) to get more Crash Detail!", new Object[0]);
            w.e("# PKG NAME: %s", a.c);
            w.e("# APP VER: %s", a.i);
            w.e("# LAUNCH TIME: %s", com.tencent.bugly.proguard.a.a(new Date(com.tencent.bugly.crashreport.common.info.a.a().a)));
            w.e("# CRASH TYPE: %s", str);
            w.e("# CRASH TIME: %s", str2);
            w.e("# CRASH PROCESS: %s", str3);
            if (thread != null) {
                w.e("# CRASH THREAD: %s", thread.getName());
            }
            if (crashDetailBean != null) {
                w.e("# REPORT ID: %s", crashDetailBean.c);
                String str5 = "# CRASH DEVICE: %s %s";
                Object[] objArr = new Object[2];
                objArr[0] = a.f;
                objArr[1] = a.w().booleanValue() ? "ROOTED" : "UNROOT";
                w.e(str5, objArr);
                w.e("# RUNTIME AVAIL RAM:%d ROM:%d SD:%d", Long.valueOf(crashDetailBean.B), Long.valueOf(crashDetailBean.C), Long.valueOf(crashDetailBean.D));
                w.e("# RUNTIME TOTAL RAM:%d ROM:%d SD:%d", Long.valueOf(crashDetailBean.E), Long.valueOf(crashDetailBean.F), Long.valueOf(crashDetailBean.G));
                String str6 = crashDetailBean.J;
                if (str6 == null || str6.trim().length() <= 0) {
                    i = 1;
                } else {
                    i = 0;
                }
                if (i == 0) {
                    w.e("# EXCEPTION FIRED BY %s %s", crashDetailBean.J, crashDetailBean.I);
                } else if (crashDetailBean.b == 3) {
                    str5 = "# EXCEPTION ANR MESSAGE:\n %s";
                    objArr = new Object[1];
                    objArr[0] = crashDetailBean.N == null ? "null" : ((String) crashDetailBean.N.get("BUGLY_CR_01"));
                    w.e(str5, objArr);
                }
            }
            if (str4 == null || str4.trim().length() <= 0) {
                i = 1;
            } else {
                i = 0;
            }
            if (i == 0) {
                w.e("# CRASH STACK: ", new Object[0]);
                w.e(str4, new Object[0]);
            }
            w.e("#++++++++++++++++++++++++++++++++++++++++++#", new Object[0]);
        }
    }
}
