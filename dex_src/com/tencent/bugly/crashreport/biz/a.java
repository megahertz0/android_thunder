package com.tencent.bugly.crashreport.biz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Parcel;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.proguard.as;
import com.tencent.bugly.proguard.j;
import com.tencent.bugly.proguard.o;
import com.tencent.bugly.proguard.s;
import com.tencent.bugly.proguard.t;
import com.tencent.bugly.proguard.v;
import com.tencent.bugly.proguard.w;
import com.xunlei.download.DownloadManager;
import com.xunlei.tdlive.sdk.IHost;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// compiled from: BUGLY
public final class a {
    private Context a;
    private long b;
    private int c;

    // compiled from: BUGLY
    final class AnonymousClass_1 implements s {
        private /* synthetic */ List a;

        AnonymousClass_1(List list) {
            this.a = list;
        }

        public final void a(boolean z) {
            if (z) {
                w.c("up success do final", new Object[0]);
                long currentTimeMillis = System.currentTimeMillis();
                for (UserInfoBean userInfoBean : this.a) {
                    userInfoBean.f = currentTimeMillis;
                    a.a(a.this, userInfoBean);
                }
            }
        }
    }

    // compiled from: BUGLY
    class a implements Runnable {
        private boolean a;
        private UserInfoBean b;

        public a(UserInfoBean userInfoBean, boolean z) {
            this.b = userInfoBean;
            this.a = z;
        }

        public final void run() {
            try {
                if (this.b != null) {
                    UserInfoBean userInfoBean = this.b;
                    if (userInfoBean != null) {
                        com.tencent.bugly.crashreport.common.info.a a = com.tencent.bugly.crashreport.common.info.a.a();
                        if (a != null) {
                            userInfoBean.j = a.d();
                        }
                    }
                    w.c("record userinfo", new Object[0]);
                    a.a(a.this, this.b);
                }
                if (this.a) {
                    a.this.b();
                }
            } catch (Throwable th) {
                if (!w.a(th)) {
                    th.printStackTrace();
                }
            }
        }
    }

    // compiled from: BUGLY
    class b implements Runnable {
        b() {
        }

        public final void run() {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis < a.this.b) {
                v.a().a(new b(), (a.this.b - currentTimeMillis) + 5000);
                return;
            }
            a.b(a.this);
            a.this.a(XZBDevice.DOWNLOAD_LIST_FAILED, false, 0);
            a.this.a();
        }
    }

    // compiled from: BUGLY
    class c implements Runnable {
        private long a;

        public c(long j) {
            this.a = 21600000;
            this.a = j;
        }

        public final void run() {
            a.this.b();
            a aVar = a.this;
            long j = this.a;
            v.a().a(new c(j), j);
        }
    }

    static /* synthetic */ void a(a aVar, UserInfoBean userInfoBean) {
        if (userInfoBean != null) {
            long a = o.a().a("t_ui", a(userInfoBean), null, true);
            if (a >= 0) {
                w.c("insert %s success! %d", "t_ui", Long.valueOf(a));
                userInfoBean.a = a;
            }
        }
    }

    static /* synthetic */ int b(a aVar) {
        int i = aVar.c;
        aVar.c = i + 1;
        return i;
    }

    public a(Context context) {
        this.a = context;
    }

    public final void a(int i, boolean z, long j) {
        int i2 = 1;
        StrategyBean c = com.tencent.bugly.crashreport.common.strategy.a.a().c();
        if (c == null || c.e || i == 1 || i == 3) {
            if (i == 1) {
                this.c++;
            }
            Context context = this.a;
            if (i != 1) {
                i2 = 0;
            }
            com.tencent.bugly.crashreport.common.info.a a = com.tencent.bugly.crashreport.common.info.a.a(context);
            UserInfoBean userInfoBean = new UserInfoBean();
            userInfoBean.b = i;
            userInfoBean.c = a.d;
            userInfoBean.d = a.f();
            userInfoBean.e = System.currentTimeMillis();
            userInfoBean.f = -1;
            userInfoBean.n = a.i;
            userInfoBean.o = i2;
            userInfoBean.l = a.n;
            userInfoBean.m = a.o;
            userInfoBean.g = a.p;
            userInfoBean.h = a.q;
            userInfoBean.i = a.r;
            userInfoBean.k = a.s;
            userInfoBean.r = a.y();
            userInfoBean.s = a.C();
            userInfoBean.p = a.D();
            userInfoBean.q = a.E();
            v.a().a(new a(userInfoBean, z), 0);
            return;
        }
        w.e("UserInfo is disable", new Object[0]);
    }

    public final void a() {
        this.b = com.tencent.bugly.proguard.a.c() + 86400000;
        v.a().a(new b(), (this.b - System.currentTimeMillis()) + 5000);
    }

    public final synchronized void b() {
        int i = 1;
        synchronized (this) {
            int i2;
            List list;
            String str = com.tencent.bugly.crashreport.common.info.a.a(this.a).d;
            List arrayList = new ArrayList();
            List a = a(str);
            if (a != null) {
                UserInfoBean userInfoBean;
                int i3;
                int size = a.size() - 10;
                if (size > 0) {
                    for (int i4 = 0; i4 < a.size() - 1; i4++) {
                        for (i2 = i4 + 1; i2 < a.size(); i2++) {
                            if (((UserInfoBean) a.get(i4)).e > ((UserInfoBean) a.get(i2)).e) {
                                userInfoBean = (UserInfoBean) a.get(i4);
                                a.set(i4, a.get(i2));
                                a.set(i2, userInfoBean);
                            }
                        }
                    }
                    for (i3 = 0; i3 < size; i3++) {
                        arrayList.add(a.get(i3));
                    }
                }
                Iterator it = a.iterator();
                i2 = 0;
                while (it.hasNext()) {
                    userInfoBean = (UserInfoBean) it.next();
                    if (userInfoBean.f != -1) {
                        it.remove();
                        if (userInfoBean.e < com.tencent.bugly.proguard.a.c()) {
                            arrayList.add(userInfoBean);
                        }
                    }
                    if (userInfoBean.e <= System.currentTimeMillis() - 600000 || !(userInfoBean.b == 1 || userInfoBean.b == 4)) {
                        i3 = i2;
                    } else {
                        i3 = i2 + 1;
                    }
                    i2 = i3;
                }
                if (i2 > 15) {
                    w.d("[userinfo] userinfo too many times in 10 min: %d", Integer.valueOf(i2));
                    Object obj = null;
                } else {
                    i3 = 1;
                }
                i2 = i3;
                list = a;
            } else {
                list = new ArrayList();
                i2 = 1;
            }
            if (arrayList.size() > 0) {
                a(arrayList);
            }
            if (!(i2 == 0 || list == null || list.size() == 0)) {
                w.c("[userinfo] do userinfo, size: %d", Integer.valueOf(list.size()));
                if (this.c != 1) {
                    i = XZBDevice.DOWNLOAD_LIST_RECYCLE;
                }
                j a2 = com.tencent.bugly.proguard.a.a(list, i);
                if (a2 == null) {
                    w.d("[biz] create uPkg fail!", new Object[0]);
                } else {
                    byte[] a3 = com.tencent.bugly.proguard.a.a(a2);
                    if (a3 == null) {
                        w.d("[biz] send encode fail!", new Object[0]);
                    } else {
                        as a4 = com.tencent.bugly.proguard.a.a(this.a, 640, a3);
                        if (a4 == null) {
                            w.d("request package is null.", new Object[0]);
                        } else {
                            t.a().a((int) IHost.HOST_NOFITY_PAGE_SELECTED, a4, null, new AnonymousClass_1(list), false);
                        }
                    }
                }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<com.tencent.bugly.crashreport.biz.UserInfoBean> a(java.lang.String r10) {
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.biz.a.a(java.lang.String):java.util.List<com.tencent.bugly.crashreport.biz.UserInfoBean>");
        /*
        this = this;
        r0 = 0;
        r1 = 1;
        r7 = 0;
        if (r10 == 0) goto L_0x0030;
    L_0x0005:
        r2 = r10.trim();	 Catch:{ Throwable -> 0x00f1, all -> 0x00eb }
        r2 = r2.length();	 Catch:{ Throwable -> 0x00f1, all -> 0x00eb }
        if (r2 <= 0) goto L_0x0030;
    L_0x000f:
        if (r0 == 0) goto L_0x0032;
    L_0x0011:
        r3 = r7;
    L_0x0012:
        r0 = com.tencent.bugly.proguard.o.a();	 Catch:{ Throwable -> 0x00f1, all -> 0x00eb }
        r1 = "t_ui";
        r2 = 0;
        r4 = 0;
        r5 = 0;
        r6 = 1;
        r8 = r0.a(r1, r2, r3, r4, r5, r6);	 Catch:{ Throwable -> 0x00f1, all -> 0x00eb }
        if (r8 != 0) goto L_0x004a;
    L_0x0023:
        if (r8 == 0) goto L_0x002e;
    L_0x0025:
        r0 = r8.isClosed();
        if (r0 != 0) goto L_0x002e;
    L_0x002b:
        r8.close();
    L_0x002e:
        r0 = r7;
    L_0x002f:
        return r0;
    L_0x0030:
        r0 = r1;
        goto L_0x000f;
    L_0x0032:
        r0 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00f1, all -> 0x00eb }
        r1 = "_pc = '";
        r0.<init>(r1);	 Catch:{ Throwable -> 0x00f1, all -> 0x00eb }
        r0 = r0.append(r10);	 Catch:{ Throwable -> 0x00f1, all -> 0x00eb }
        r1 = "'";
        r0 = r0.append(r1);	 Catch:{ Throwable -> 0x00f1, all -> 0x00eb }
        r3 = r0.toString();	 Catch:{ Throwable -> 0x00f1, all -> 0x00eb }
        goto L_0x0012;
    L_0x004a:
        r0 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0064, all -> 0x009d }
        r0.<init>();	 Catch:{ Throwable -> 0x0064, all -> 0x009d }
        r6 = new java.util.ArrayList;	 Catch:{ Throwable -> 0x0064, all -> 0x009d }
        r6.<init>();	 Catch:{ Throwable -> 0x0064, all -> 0x009d }
    L_0x0054:
        r1 = r8.moveToNext();	 Catch:{ Throwable -> 0x0064, all -> 0x009d }
        if (r1 == 0) goto L_0x00aa;
    L_0x005a:
        r1 = a(r8);	 Catch:{ Throwable -> 0x0064, all -> 0x009d }
        if (r1 == 0) goto L_0x007c;
    L_0x0060:
        r6.add(r1);	 Catch:{ Throwable -> 0x0064, all -> 0x009d }
        goto L_0x0054;
    L_0x0064:
        r0 = move-exception;
        r1 = r8;
    L_0x0066:
        r2 = com.tencent.bugly.proguard.w.a(r0);	 Catch:{ all -> 0x00ee }
        if (r2 != 0) goto L_0x006f;
    L_0x006c:
        r0.printStackTrace();	 Catch:{ all -> 0x00ee }
    L_0x006f:
        if (r1 == 0) goto L_0x007a;
    L_0x0071:
        r0 = r1.isClosed();
        if (r0 != 0) goto L_0x007a;
    L_0x0077:
        r1.close();
    L_0x007a:
        r0 = r7;
        goto L_0x002f;
    L_0x007c:
        r1 = "_id";
        r1 = r8.getColumnIndex(r1);	 Catch:{ Throwable -> 0x0092, all -> 0x009d }
        r2 = r8.getLong(r1);	 Catch:{ Throwable -> 0x0092, all -> 0x009d }
        r1 = " or _id = ";
        r1 = r0.append(r1);	 Catch:{ Throwable -> 0x0092, all -> 0x009d }
        r1.append(r2);	 Catch:{ Throwable -> 0x0092, all -> 0x009d }
        goto L_0x0054;
    L_0x0092:
        r1 = move-exception;
        r1 = "unknown id!";
        r2 = 0;
        r2 = new java.lang.Object[r2];	 Catch:{ Throwable -> 0x0064, all -> 0x009d }
        com.tencent.bugly.proguard.w.d(r1, r2);	 Catch:{ Throwable -> 0x0064, all -> 0x009d }
        goto L_0x0054;
    L_0x009d:
        r0 = move-exception;
    L_0x009e:
        if (r8 == 0) goto L_0x00a9;
    L_0x00a0:
        r1 = r8.isClosed();
        if (r1 != 0) goto L_0x00a9;
    L_0x00a6:
        r8.close();
    L_0x00a9:
        throw r0;
    L_0x00aa:
        r0 = r0.toString();	 Catch:{ Throwable -> 0x0064, all -> 0x009d }
        r1 = r0.length();	 Catch:{ Throwable -> 0x0064, all -> 0x009d }
        if (r1 <= 0) goto L_0x00dd;
    L_0x00b4:
        r1 = 4;
        r2 = r0.substring(r1);	 Catch:{ Throwable -> 0x0064, all -> 0x009d }
        r0 = com.tencent.bugly.proguard.o.a();	 Catch:{ Throwable -> 0x0064, all -> 0x009d }
        r1 = "t_ui";
        r3 = 0;
        r4 = 0;
        r5 = 1;
        r0 = r0.a(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x0064, all -> 0x009d }
        r1 = "[session] deleted %s error data %d";
        r2 = 2;
        r2 = new java.lang.Object[r2];	 Catch:{ Throwable -> 0x0064, all -> 0x009d }
        r3 = 0;
        r4 = "t_ui";
        r2[r3] = r4;	 Catch:{ Throwable -> 0x0064, all -> 0x009d }
        r3 = 1;
        r0 = java.lang.Integer.valueOf(r0);	 Catch:{ Throwable -> 0x0064, all -> 0x009d }
        r2[r3] = r0;	 Catch:{ Throwable -> 0x0064, all -> 0x009d }
        com.tencent.bugly.proguard.w.d(r1, r2);	 Catch:{ Throwable -> 0x0064, all -> 0x009d }
    L_0x00dd:
        if (r8 == 0) goto L_0x00e8;
    L_0x00df:
        r0 = r8.isClosed();
        if (r0 != 0) goto L_0x00e8;
    L_0x00e5:
        r8.close();
    L_0x00e8:
        r0 = r6;
        goto L_0x002f;
    L_0x00eb:
        r0 = move-exception;
        r8 = r7;
        goto L_0x009e;
    L_0x00ee:
        r0 = move-exception;
        r8 = r1;
        goto L_0x009e;
    L_0x00f1:
        r0 = move-exception;
        r1 = r7;
        goto L_0x0066;
        */
    }

    private static void a(List<UserInfoBean> list) {
        if (list != null && list.size() != 0) {
            StringBuilder stringBuilder = new StringBuilder();
            for (UserInfoBean userInfoBean : list) {
                stringBuilder.append(" or _id = ").append(userInfoBean.a);
            }
            String toString = stringBuilder.toString();
            if (toString.length() > 0) {
                toString = toString.substring(XZBDevice.DOWNLOAD_LIST_ALL);
            }
            stringBuilder.setLength(0);
            try {
                int a = o.a().a("t_ui", toString, null, null, true);
                w.c("deleted %s data %d", "t_ui", Integer.valueOf(a));
            } catch (Throwable th) {
                if (!w.a(th)) {
                    th.printStackTrace();
                }
            }
        }
    }

    private static ContentValues a(UserInfoBean userInfoBean) {
        if (userInfoBean == null) {
            return null;
        }
        try {
            ContentValues contentValues = new ContentValues();
            if (userInfoBean.a > 0) {
                contentValues.put(DownloadManager.COLUMN_ID, Long.valueOf(userInfoBean.a));
            }
            contentValues.put("_tm", Long.valueOf(userInfoBean.e));
            contentValues.put("_ut", Long.valueOf(userInfoBean.f));
            contentValues.put("_tp", Integer.valueOf(userInfoBean.b));
            contentValues.put("_pc", userInfoBean.c);
            Parcel obtain = Parcel.obtain();
            userInfoBean.writeToParcel(obtain, 0);
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

    private static UserInfoBean a(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            byte[] blob = cursor.getBlob(cursor.getColumnIndex("_dt"));
            if (blob == null) {
                return null;
            }
            long j = cursor.getLong(cursor.getColumnIndex(DownloadManager.COLUMN_ID));
            UserInfoBean userInfoBean = (UserInfoBean) com.tencent.bugly.proguard.a.a(blob, UserInfoBean.CREATOR);
            if (userInfoBean == null) {
                return userInfoBean;
            }
            userInfoBean.a = j;
            return userInfoBean;
        } catch (Throwable th) {
            if (!w.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }
}
