package com.tencent.stat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v4.media.session.PlaybackStateCompat;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.tencent.open.GameAppOperation;
import com.tencent.stat.a.e;
import com.tencent.stat.common.StatLogger;
import com.tencent.stat.common.k;
import com.umeng.message.MsgConstant;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.android.agoo.message.MessageService;

public class n {
    private static StatLogger e;
    private static n f;
    Handler a;
    volatile int b;
    DeviceInfo c;
    private w d;
    private HashMap<String, String> g;

    static {
        e = k.b();
        f = null;
    }

    private n(Context context) {
        this.a = null;
        this.b = 0;
        this.c = null;
        this.g = new HashMap();
        try {
            HandlerThread handlerThread = new HandlerThread("StatStore");
            handlerThread.start();
            e.w(new StringBuilder("Launch store thread:").append(handlerThread).toString());
            this.a = new Handler(handlerThread.getLooper());
            Context applicationContext = context.getApplicationContext();
            this.d = new w(applicationContext);
            this.d.getWritableDatabase();
            this.d.getReadableDatabase();
            b(applicationContext);
            c();
            f();
            this.a.post(new o(this));
        } catch (Object th) {
            e.e(th);
        }
    }

    public static synchronized n a(Context context) {
        n nVar;
        synchronized (n.class) {
            if (f == null) {
                f = new n(context);
            }
            nVar = f;
        }
        return nVar;
    }

    public static n b() {
        return f;
    }

    private synchronized void b(int i) {
        try {
            if (this.b > 0 && i > 0) {
                e.i(new StringBuilder("Load ").append(Integer.toString(this.b)).append(" unsent events").toString());
                List arrayList = new ArrayList();
                List<x> arrayList2 = new ArrayList();
                if (i == -1 || i > StatConfig.a()) {
                    i = StatConfig.a();
                }
                this.b -= i;
                c(arrayList2, i);
                e.i(new StringBuilder("Peek ").append(Integer.toString(arrayList2.size())).append(" unsent events.").toString());
                if (!arrayList2.isEmpty()) {
                    b((List) arrayList2, (int) XZBDevice.DOWNLOAD_LIST_RECYCLE);
                    for (x xVar : arrayList2) {
                        arrayList.add(xVar.b);
                    }
                    d.b().b(arrayList, new u(this, arrayList2, i));
                }
            }
        } catch (Object th) {
            e.e(th);
        }
    }

    private synchronized void b(e eVar, c cVar) {
        try {
            if (StatConfig.getMaxStoreEventCount() > 0) {
                try {
                    this.d.getWritableDatabase().beginTransaction();
                    if (this.b > StatConfig.getMaxStoreEventCount()) {
                        e.warn("Too many events stored in db.");
                        this.b -= this.d.getWritableDatabase().delete("events", "event_id in (select event_id from events where timestamp in (select min(timestamp) from events) limit 1)", null);
                    }
                    ContentValues contentValues = new ContentValues();
                    String c = k.c(eVar.d());
                    contentValues.put(ParamKey.CONTENT, c);
                    contentValues.put("send_count", MessageService.MSG_DB_READY_REPORT);
                    contentValues.put(Impl.COLUMN_STATUS, Integer.toString(1));
                    contentValues.put("timestamp", Long.valueOf(eVar.b()));
                    if (this.d.getWritableDatabase().insert("events", null, contentValues) == -1) {
                        e.error(new StringBuilder("Failed to store event:").append(c).toString());
                    } else {
                        this.b++;
                        this.d.getWritableDatabase().setTransactionSuccessful();
                        if (cVar != null) {
                            cVar.a();
                        }
                    }
                    try {
                        this.d.getWritableDatabase().endTransaction();
                    } catch (Throwable th) {
                    }
                } catch (Object th2) {
                    e.e(th2);
                    try {
                        this.d.getWritableDatabase().endTransaction();
                    } catch (Throwable th3) {
                    }
                }
            }
        } catch (Throwable th4) {
        }
    }

    private synchronized void b(b bVar) {
        try {
            long update;
            String a = bVar.a();
            String a2 = k.a(a);
            ContentValues contentValues = new ContentValues();
            contentValues.put(ParamKey.CONTENT, bVar.b.toString());
            contentValues.put("md5sum", a2);
            bVar.c = a2;
            contentValues.put(GameAppOperation.QQFAV_DATALINE_VERSION, Integer.valueOf(bVar.d));
            Cursor query = this.d.getReadableDatabase().query("config", null, null, null, null, null, null);
            do {
                try {
                    if (!query.moveToNext()) {
                        r0 = null;
                        break;
                    }
                } catch (Throwable th) {
                    r0 = th;
                }
            } while (query.getInt(0) != bVar.a);
            int i = 1;
            if (1 == r0) {
                update = (long) this.d.getWritableDatabase().update("config", contentValues, "type=?", new String[]{Integer.toString(bVar.a)});
            } else {
                contentValues.put(JsInterface.FUNPLAY_AD_TRPE, Integer.valueOf(bVar.a));
                update = this.d.getWritableDatabase().insert("config", null, contentValues);
            }
            if (update == -1) {
                e.e(new StringBuilder("Failed to store cfg:").append(a).toString());
            } else {
                e.d(new StringBuilder("Sucessed to store cfg:").append(a).toString());
            }
            if (query != null) {
                query.close();
            }
        } catch (Throwable th2) {
            th = th2;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    private synchronized void b(List<x> list) {
        try {
            e.i(new StringBuilder("Delete ").append(list.size()).append(" sent events in thread:").append(Thread.currentThread()).toString());
            try {
                this.d.getWritableDatabase().beginTransaction();
                for (x xVar : list) {
                    this.b -= this.d.getWritableDatabase().delete("events", "event_id = ?", new String[]{Long.toString(xVar.a)});
                }
                this.d.getWritableDatabase().setTransactionSuccessful();
                this.b = (int) DatabaseUtils.queryNumEntries(this.d.getReadableDatabase(), "events");
                try {
                    this.d.getWritableDatabase().endTransaction();
                } catch (Exception e) {
                    e.e(e);
                }
            } catch (Object th) {
                e.e(th);
                try {
                    this.d.getWritableDatabase().endTransaction();
                } catch (Exception e2) {
                    e.e(e2);
                }
            }
        } catch (Throwable th2) {
        }
    }

    private synchronized void b(List<x> list, int i) {
        try {
            e.i(new StringBuilder("Update ").append(list.size()).append(" sending events to status:").append(i).append(" in thread:").append(Thread.currentThread()).toString());
            try {
                ContentValues contentValues = new ContentValues();
                contentValues.put(Impl.COLUMN_STATUS, Integer.toString(i));
                this.d.getWritableDatabase().beginTransaction();
                for (x xVar : list) {
                    if (xVar.d + 1 > StatConfig.getMaxSendRetryCount()) {
                        this.b -= this.d.getWritableDatabase().delete("events", "event_id=?", new String[]{Long.toString(xVar.a)});
                    } else {
                        contentValues.put("send_count", Integer.valueOf(xVar.d + 1));
                        e.i(new StringBuilder("Update event:").append(xVar.a).append(" for content:").append(contentValues).toString());
                        int update = this.d.getWritableDatabase().update("events", contentValues, "event_id=?", new String[]{Long.toString(xVar.a)});
                        if (update <= 0) {
                            e.e(new StringBuilder("Failed to update db, error code:").append(Integer.toString(update)).toString());
                        }
                    }
                }
                this.d.getWritableDatabase().setTransactionSuccessful();
                this.b = (int) DatabaseUtils.queryNumEntries(this.d.getReadableDatabase(), "events");
                try {
                    this.d.getWritableDatabase().endTransaction();
                } catch (Exception e) {
                    e.e(e);
                }
            } catch (Object th) {
                e.e(th);
                try {
                    this.d.getWritableDatabase().endTransaction();
                } catch (Exception e2) {
                    e.e(e2);
                }
            }
        } catch (Throwable th2) {
        }
    }

    private void c(List<x> list, int i) {
        Throwable th;
        Cursor cursor = null;
        try {
            Cursor query = this.d.getReadableDatabase().query("events", null, "status=?", new String[]{Integer.toString(1)}, null, null, "event_id", Integer.toString(i));
            while (query.moveToNext()) {
                try {
                    list.add(new x(query.getLong(0), k.d(query.getString(1)), query.getInt(XZBDevice.DOWNLOAD_LIST_RECYCLE), query.getInt(XZBDevice.DOWNLOAD_LIST_FAILED)));
                } catch (Throwable th2) {
                    th = th2;
                    cursor = query;
                }
            }
            if (query != null) {
                query.close();
            }
        } catch (Throwable th3) {
            th = th3;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    private void e() {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(Impl.COLUMN_STATUS, Integer.valueOf(1));
            this.d.getWritableDatabase().update("events", contentValues, "status=?", new String[]{Long.toString(PlaybackStateCompat.ACTION_PAUSE)});
            this.b = (int) DatabaseUtils.queryNumEntries(this.d.getReadableDatabase(), "events");
            e.i(new StringBuilder("Total ").append(this.b).append(" unsent events.").toString());
        } catch (Object th) {
            e.e(th);
        }
    }

    private void f() {
        try {
            Cursor query = this.d.getReadableDatabase().query("keyvalues", null, null, null, null, null, null);
            while (query.moveToNext()) {
                try {
                    this.g.put(query.getString(0), query.getString(1));
                } catch (Throwable th) {
                    th = th;
                }
            }
            if (query != null) {
                query.close();
            }
        } catch (Throwable th2) {
            th = th2;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    public int a() {
        return this.b;
    }

    void a(int i) {
        this.a.post(new v(this, i));
    }

    void a(e eVar, c cVar) {
        if (StatConfig.isEnableStatService()) {
            try {
                if (Thread.currentThread().getId() == this.a.getLooper().getThread().getId()) {
                    b(eVar, cVar);
                } else {
                    this.a.post(new r(this, eVar, cVar));
                }
            } catch (Object th) {
                e.e(th);
            }
        }
    }

    void a(b bVar) {
        if (bVar != null) {
            this.a.post(new s(this, bVar));
        }
    }

    void a(List<x> list) {
        try {
            if (Thread.currentThread().getId() == this.a.getLooper().getThread().getId()) {
                b((List) list);
            } else {
                this.a.post(new q(this, list));
            }
        } catch (Exception e) {
            e.e(e);
        }
    }

    void a(List<x> list, int i) {
        try {
            if (Thread.currentThread().getId() == this.a.getLooper().getThread().getId()) {
                b((List) list, i);
            } else {
                this.a.post(new p(this, list, i));
            }
        } catch (Object th) {
            e.e(th);
        }
    }

    public synchronized DeviceInfo b(Context context) {
        DeviceInfo deviceInfo;
        Cursor query;
        Cursor cursor;
        Throwable th;
        if (this.c != null) {
            deviceInfo = this.c;
        } else {
            Object obj;
            try {
                query = this.d.getReadableDatabase().query("user", null, null, null, null, null, null, null);
                obj = null;
                try {
                    String string;
                    String b;
                    if (query.moveToNext()) {
                        String d = k.d(query.getString(0));
                        int i = query.getInt(1);
                        string = query.getString(XZBDevice.DOWNLOAD_LIST_RECYCLE);
                        long currentTimeMillis = System.currentTimeMillis() / 1000;
                        int i2 = (i == 1 || k.a(query.getLong(XZBDevice.DOWNLOAD_LIST_FAILED) * 1000).equals(k.a(1000 * currentTimeMillis))) ? i : 1;
                        int i3 = !string.equals(k.r(context)) ? i2 | 2 : i2;
                        String[] split = d.split(MiPushClient.ACCEPT_TIME_SEPARATOR);
                        obj = null;
                        if (split == null || split.length <= 0) {
                            b = k.b(context);
                            obj = 1;
                            d = b;
                        } else {
                            b = split[0];
                            if (b == null || b.length() < 11) {
                                string = k.l(context);
                                if (string == null || string.length() <= 10) {
                                    string = b;
                                } else {
                                    obj = 1;
                                }
                                b = d;
                                d = string;
                            } else {
                                String str = b;
                                b = d;
                                d = str;
                            }
                        }
                        if (split == null || split.length < 2) {
                            string = k.c(context);
                            if (string != null && string.length() > 0) {
                                b = d + MiPushClient.ACCEPT_TIME_SEPARATOR + string;
                                obj = 1;
                            }
                        } else {
                            string = split[1];
                            b = d + MiPushClient.ACCEPT_TIME_SEPARATOR + string;
                        }
                        this.c = new DeviceInfo(d, string, i3);
                        ContentValues contentValues = new ContentValues();
                        contentValues.put(ParamKey.UID, k.c(b));
                        contentValues.put("user_type", Integer.valueOf(i3));
                        contentValues.put("app_ver", k.r(context));
                        contentValues.put(MsgConstant.KEY_TS, Long.valueOf(currentTimeMillis));
                        if (obj != null) {
                            this.d.getWritableDatabase().update("user", contentValues, "uid=?", new String[]{r10});
                        }
                        if (i3 != i) {
                            this.d.getWritableDatabase().replace("user", null, contentValues);
                        }
                        i2 = 1;
                    }
                    if (obj == null) {
                        string = k.b(context);
                        b = k.c(context);
                        String str2 = (b == null || b.length() <= 0) ? string : string + MiPushClient.ACCEPT_TIME_SEPARATOR + b;
                        long currentTimeMillis2 = System.currentTimeMillis() / 1000;
                        String r = k.r(context);
                        ContentValues contentValues2 = new ContentValues();
                        contentValues2.put(ParamKey.UID, k.c(str2));
                        contentValues2.put("user_type", Integer.valueOf(0));
                        contentValues2.put("app_ver", r);
                        contentValues2.put(MsgConstant.KEY_TS, Long.valueOf(currentTimeMillis2));
                        this.d.getWritableDatabase().insert("user", null, contentValues2);
                        this.c = new DeviceInfo(string, b, 0);
                    }
                    if (query != null) {
                        query.close();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (query != null) {
                        query.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                query = null;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
            deviceInfo = this.c;
        }
        return deviceInfo;
    }

    void c() {
        this.a.post(new t(this));
    }
}
