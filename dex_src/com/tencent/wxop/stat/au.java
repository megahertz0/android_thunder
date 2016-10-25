package com.tencent.wxop.stat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.media.session.PlaybackStateCompat;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.tencent.open.GameAppOperation;
import com.tencent.wxop.stat.common.StatLogger;
import com.tencent.wxop.stat.common.a;
import com.tencent.wxop.stat.common.e;
import com.tencent.wxop.stat.common.k;
import com.tencent.wxop.stat.common.q;
import com.umeng.socialize.common.SocializeConstants;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import org.android.agoo.message.MessageService;
import org.json.JSONObject;

public class au {
    private static StatLogger h;
    private static Context i;
    private static au j;
    volatile int a;
    a b;
    private bc c;
    private bc d;
    private e e;
    private String f;
    private String g;
    private int k;
    private ConcurrentHashMap<com.tencent.wxop.stat.a.e, String> l;
    private boolean m;
    private HashMap<String, String> n;

    static {
        h = k.b();
        i = null;
        j = null;
    }

    private au(Context context) {
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = com.umeng.a.d;
        this.g = com.umeng.a.d;
        this.a = 0;
        this.b = null;
        this.k = 0;
        this.l = null;
        this.m = false;
        this.n = new HashMap();
        try {
            this.e = new e();
            i = context.getApplicationContext();
            this.l = new ConcurrentHashMap();
            this.f = k.r(context);
            this.g = new StringBuilder("pri_").append(k.r(context)).toString();
            this.c = new bc(i, this.f);
            this.d = new bc(i, this.g);
            a(true);
            a(false);
            f();
            b(i);
            d();
            j();
        } catch (Throwable th) {
            h.e(th);
        }
    }

    public static au a(Context context) {
        if (j == null) {
            synchronized (au.class) {
                if (j == null) {
                    j = new au(context);
                }
            }
        }
        return j;
    }

    private String a(List<bd> list) {
        StringBuilder stringBuilder = new StringBuilder(list.size() * 3);
        stringBuilder.append("event_id in (");
        int size = list.size();
        int i = 0;
        for (bd bdVar : list) {
            stringBuilder.append(bdVar.a);
            if (i != size - 1) {
                stringBuilder.append(MiPushClient.ACCEPT_TIME_SEPARATOR);
            }
            i++;
        }
        stringBuilder.append(SocializeConstants.OP_CLOSE_PAREN);
        return stringBuilder.toString();
    }

    private synchronized void a(int i, boolean z) {
        try {
            if (this.a > 0 && i > 0 && !StatServiceImpl.a()) {
                if (StatConfig.isDebugEnable()) {
                    h.i(new StringBuilder("Load ").append(this.a).append(" unsent events").toString());
                }
                List arrayList = new ArrayList(i);
                b(arrayList, i, z);
                if (arrayList.size() > 0) {
                    if (StatConfig.isDebugEnable()) {
                        h.i(new StringBuilder("Peek ").append(arrayList.size()).append(" unsent events.").toString());
                    }
                    a(arrayList, (int) XZBDevice.DOWNLOAD_LIST_RECYCLE, z);
                    i.b(i).b(arrayList, new ba(this, arrayList, z));
                }
            }
        } catch (Throwable th) {
            h.e(th);
        }
    }

    private void a(com.tencent.wxop.stat.a.e eVar, h hVar, boolean z) {
        long insert;
        long j;
        SQLiteDatabase sQLiteDatabase = null;
        try {
            sQLiteDatabase = c(z);
            sQLiteDatabase.beginTransaction();
            if (!z && this.a > StatConfig.getMaxStoreEventCount()) {
                h.warn("Too many events stored in db.");
                this.a -= this.c.getWritableDatabase().delete("events", "event_id in (select event_id from events where timestamp in (select min(timestamp) from events) limit 1)", null);
            }
            ContentValues contentValues = new ContentValues();
            String g = eVar.g();
            if (StatConfig.isDebugEnable()) {
                h.i(new StringBuilder("insert 1 event, content:").append(g).toString());
            }
            contentValues.put(ParamKey.CONTENT, q.b(g));
            contentValues.put("send_count", MessageService.MSG_DB_READY_REPORT);
            contentValues.put(Impl.COLUMN_STATUS, Integer.toString(1));
            contentValues.put("timestamp", Long.valueOf(eVar.c()));
            insert = sQLiteDatabase.insert("events", null, contentValues);
            sQLiteDatabase.setTransactionSuccessful();
            if (sQLiteDatabase != null) {
                try {
                    sQLiteDatabase.endTransaction();
                    j = insert;
                } catch (Throwable th) {
                    h.e(th);
                    j = insert;
                }
                if (j <= 0) {
                    this.a++;
                    if (StatConfig.isDebugEnable()) {
                        h.d(new StringBuilder("directStoreEvent insert event to db, event:").append(eVar.g()).toString());
                    }
                    if (hVar != null) {
                        hVar.a();
                    }
                }
                h.error(new StringBuilder("Failed to store event:").append(eVar.g()).toString());
                return;
            }
        } catch (Throwable th2) {
            insert = -1;
            try {
                h.e(th2);
                if (sQLiteDatabase != null) {
                    try {
                        sQLiteDatabase.endTransaction();
                        j = -1;
                    } catch (Throwable th22) {
                        h.e(th22);
                        j = -1;
                    }
                }
            } catch (Throwable th3) {
                if (sQLiteDatabase != null) {
                    try {
                        sQLiteDatabase.endTransaction();
                    } catch (Throwable th4) {
                        h.e(th4);
                    }
                }
            }
            j = insert;
        }
        j = insert;
        if (j <= 0) {
            h.error(new StringBuilder("Failed to store event:").append(eVar.g()).toString());
            return;
        }
        this.a++;
        if (StatConfig.isDebugEnable()) {
            h.d(new StringBuilder("directStoreEvent insert event to db, event:").append(eVar.g()).toString());
        }
        if (hVar != null) {
            hVar.a();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void a(java.util.List<com.tencent.wxop.stat.bd> r7, int r8, boolean r9) {
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.wxop.stat.au.a(java.util.List, int, boolean):void");
        /*
        this = this;
        r2 = 0;
        monitor-enter(r6);
        r0 = r7.size();	 Catch:{ all -> 0x0082 }
        if (r0 != 0) goto L_0x000a;
    L_0x0008:
        monitor-exit(r6);
        return;
    L_0x000a:
        r3 = r6.b(r9);	 Catch:{ all -> 0x0082 }
        r1 = r6.c(r9);	 Catch:{ Throwable -> 0x00e8, all -> 0x00d7 }
        r0 = 2;
        if (r8 != r0) goto L_0x0085;
    L_0x0015:
        r0 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00c2 }
        r3 = "update events set status=";
        r0.<init>(r3);	 Catch:{ Throwable -> 0x00c2 }
        r0 = r0.append(r8);	 Catch:{ Throwable -> 0x00c2 }
        r3 = ", send_count=send_count+1  where ";
        r0 = r0.append(r3);	 Catch:{ Throwable -> 0x00c2 }
        r3 = r6.a(r7);	 Catch:{ Throwable -> 0x00c2 }
        r0 = r0.append(r3);	 Catch:{ Throwable -> 0x00c2 }
        r0 = r0.toString();	 Catch:{ Throwable -> 0x00c2 }
    L_0x0034:
        r3 = com.tencent.wxop.stat.StatConfig.isDebugEnable();	 Catch:{ Throwable -> 0x00c2 }
        if (r3 == 0) goto L_0x004f;
    L_0x003a:
        r3 = h;	 Catch:{ Throwable -> 0x00c2 }
        r4 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00c2 }
        r5 = "update sql:";
        r4.<init>(r5);	 Catch:{ Throwable -> 0x00c2 }
        r4 = r4.append(r0);	 Catch:{ Throwable -> 0x00c2 }
        r4 = r4.toString();	 Catch:{ Throwable -> 0x00c2 }
        r3.i(r4);	 Catch:{ Throwable -> 0x00c2 }
    L_0x004f:
        r1.beginTransaction();	 Catch:{ Throwable -> 0x00c2 }
        r1.execSQL(r0);	 Catch:{ Throwable -> 0x00c2 }
        if (r2 == 0) goto L_0x0072;
    L_0x0057:
        r0 = h;	 Catch:{ Throwable -> 0x00c2 }
        r3 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00c2 }
        r4 = "update for delete sql:";
        r3.<init>(r4);	 Catch:{ Throwable -> 0x00c2 }
        r3 = r3.append(r2);	 Catch:{ Throwable -> 0x00c2 }
        r3 = r3.toString();	 Catch:{ Throwable -> 0x00c2 }
        r0.i(r3);	 Catch:{ Throwable -> 0x00c2 }
        r1.execSQL(r2);	 Catch:{ Throwable -> 0x00c2 }
        r6.f();	 Catch:{ Throwable -> 0x00c2 }
    L_0x0072:
        r1.setTransactionSuccessful();	 Catch:{ Throwable -> 0x00c2 }
        if (r1 == 0) goto L_0x0008;
    L_0x0077:
        r1.endTransaction();	 Catch:{ Throwable -> 0x007b }
        goto L_0x0008;
    L_0x007b:
        r0 = move-exception;
        r1 = h;	 Catch:{ all -> 0x0082 }
        r1.e(r0);	 Catch:{ all -> 0x0082 }
        goto L_0x0008;
    L_0x0082:
        r0 = move-exception;
        monitor-exit(r6);
        throw r0;
    L_0x0085:
        r0 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00c2 }
        r4 = "update events set status=";
        r0.<init>(r4);	 Catch:{ Throwable -> 0x00c2 }
        r0 = r0.append(r8);	 Catch:{ Throwable -> 0x00c2 }
        r4 = " where ";
        r0 = r0.append(r4);	 Catch:{ Throwable -> 0x00c2 }
        r4 = r6.a(r7);	 Catch:{ Throwable -> 0x00c2 }
        r0 = r0.append(r4);	 Catch:{ Throwable -> 0x00c2 }
        r0 = r0.toString();	 Catch:{ Throwable -> 0x00c2 }
        r4 = r6.k;	 Catch:{ Throwable -> 0x00c2 }
        r4 = r4 % 3;
        if (r4 != 0) goto L_0x00ba;
    L_0x00aa:
        r2 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00c2 }
        r4 = "delete from events where send_count>";
        r2.<init>(r4);	 Catch:{ Throwable -> 0x00c2 }
        r2 = r2.append(r3);	 Catch:{ Throwable -> 0x00c2 }
        r2 = r2.toString();	 Catch:{ Throwable -> 0x00c2 }
    L_0x00ba:
        r3 = r6.k;	 Catch:{ Throwable -> 0x00c2 }
        r3 = r3 + 1;
        r6.k = r3;	 Catch:{ Throwable -> 0x00c2 }
        goto L_0x0034;
    L_0x00c2:
        r0 = move-exception;
    L_0x00c3:
        r2 = h;	 Catch:{ all -> 0x00e6 }
        r2.e(r0);	 Catch:{ all -> 0x00e6 }
        if (r1 == 0) goto L_0x0008;
    L_0x00ca:
        r1.endTransaction();	 Catch:{ Throwable -> 0x00cf }
        goto L_0x0008;
    L_0x00cf:
        r0 = move-exception;
        r1 = h;	 Catch:{ all -> 0x0082 }
        r1.e(r0);	 Catch:{ all -> 0x0082 }
        goto L_0x0008;
    L_0x00d7:
        r0 = move-exception;
        r1 = r2;
    L_0x00d9:
        if (r1 == 0) goto L_0x00de;
    L_0x00db:
        r1.endTransaction();	 Catch:{ Throwable -> 0x00df }
    L_0x00de:
        throw r0;	 Catch:{ all -> 0x0082 }
    L_0x00df:
        r1 = move-exception;
        r2 = h;	 Catch:{ all -> 0x0082 }
        r2.e(r1);	 Catch:{ all -> 0x0082 }
        goto L_0x00de;
    L_0x00e6:
        r0 = move-exception;
        goto L_0x00d9;
    L_0x00e8:
        r0 = move-exception;
        r1 = r2;
        goto L_0x00c3;
        */
    }

    private synchronized void a(List<bd> list, boolean z) {
        SQLiteDatabase sQLiteDatabase = null;
        synchronized (this) {
            try {
                if (list.size() != 0) {
                    if (StatConfig.isDebugEnable()) {
                        h.i(new StringBuilder("Delete ").append(list.size()).append(" events, important:").append(z).toString());
                    }
                    StringBuilder stringBuilder = new StringBuilder(list.size() * 3);
                    stringBuilder.append("event_id in (");
                    int size = list.size();
                    int i = 0;
                    for (bd bdVar : list) {
                        stringBuilder.append(bdVar.a);
                        if (i != size - 1) {
                            stringBuilder.append(MiPushClient.ACCEPT_TIME_SEPARATOR);
                        }
                        i++;
                    }
                    stringBuilder.append(SocializeConstants.OP_CLOSE_PAREN);
                    try {
                        sQLiteDatabase = c(z);
                        sQLiteDatabase.beginTransaction();
                        int delete = sQLiteDatabase.delete("events", stringBuilder.toString(), null);
                        if (StatConfig.isDebugEnable()) {
                            h.i(new StringBuilder("delete ").append(size).append(" event ").append(stringBuilder.toString()).append(", success delete:").append(delete).toString());
                        }
                        this.a -= delete;
                        sQLiteDatabase.setTransactionSuccessful();
                        f();
                        if (sQLiteDatabase != null) {
                            try {
                                sQLiteDatabase.endTransaction();
                            } catch (Throwable th) {
                                h.e(th);
                            }
                        }
                    } catch (Throwable th2) {
                        h.e(th2);
                        if (sQLiteDatabase != null) {
                            try {
                                sQLiteDatabase.endTransaction();
                            } catch (Throwable th22) {
                                h.e(th22);
                            }
                        }
                    }
                }
            } catch (Throwable th3) {
            }
        }
    }

    private void a(boolean z) {
        SQLiteDatabase sQLiteDatabase = null;
        try {
            sQLiteDatabase = c(z);
            sQLiteDatabase.beginTransaction();
            ContentValues contentValues = new ContentValues();
            contentValues.put(Impl.COLUMN_STATUS, Integer.valueOf(1));
            int update = sQLiteDatabase.update("events", contentValues, "status=?", new String[]{Long.toString(PlaybackStateCompat.ACTION_PAUSE)});
            if (StatConfig.isDebugEnable()) {
                h.i(new StringBuilder("update ").append(update).append(" unsent events.").toString());
            }
            sQLiteDatabase.setTransactionSuccessful();
            if (sQLiteDatabase != null) {
                try {
                    sQLiteDatabase.endTransaction();
                } catch (Throwable th) {
                    h.e(th);
                }
            }
        } catch (Throwable th2) {
            try {
                h.e(th2);
                if (sQLiteDatabase != null) {
                    try {
                        sQLiteDatabase.endTransaction();
                    } catch (Throwable th22) {
                        h.e(th22);
                    }
                }
            } catch (Throwable th3) {
                if (sQLiteDatabase != null) {
                    try {
                        sQLiteDatabase.endTransaction();
                    } catch (Throwable th4) {
                        h.e(th4);
                    }
                }
            }
        }
    }

    private int b(boolean z) {
        return !z ? StatConfig.getMaxSendRetryCount() : StatConfig.getMaxImportantDataSendRetryCount();
    }

    public static au b() {
        return j;
    }

    private void b(int i, boolean z) {
        int g = i == -1 ? !z ? g() : h() : i;
        if (g > 0) {
            int sendPeriodMinutes = (StatConfig.getSendPeriodMinutes() * 60) * StatConfig.getNumEventsCommitPerSec();
            if (g > sendPeriodMinutes && sendPeriodMinutes > 0) {
                g = sendPeriodMinutes;
            }
            int a = StatConfig.a();
            int i2 = g / a;
            int i3 = g % a;
            if (StatConfig.isDebugEnable()) {
                h.i(new StringBuilder("sentStoreEventsByDb sendNumbers=").append(g).append(",important=").append(z).append(",maxSendNumPerFor1Period=").append(sendPeriodMinutes).append(",maxCount=").append(i2).append(",restNumbers=").append(i3).toString());
            }
            for (g = 0; g < i2; g++) {
                a(a, z);
            }
            if (i3 > 0) {
                a(i3, z);
            }
        }
    }

    private synchronized void b(com.tencent.wxop.stat.a.e eVar, h hVar, boolean z, boolean z2) {
        if (StatConfig.getMaxStoreEventCount() > 0) {
            if (StatConfig.m <= 0 || z || z2) {
                a(eVar, hVar, z);
            } else if (StatConfig.m > 0) {
                if (StatConfig.isDebugEnable()) {
                    h.i(new StringBuilder("cacheEventsInMemory.size():").append(this.l.size()).append(",numEventsCachedInMemory:").append(StatConfig.m).append(",numStoredEvents:").append(this.a).toString());
                    h.i(new StringBuilder("cache event:").append(eVar.g()).toString());
                }
                this.l.put(eVar, com.umeng.a.d);
                if (this.l.size() >= StatConfig.m) {
                    i();
                }
                if (hVar != null) {
                    if (this.l.size() > 0) {
                        i();
                    }
                    hVar.a();
                }
            }
        }
    }

    private synchronized void b(f fVar) {
        Throwable th;
        try {
            long update;
            String a = fVar.a();
            String a2 = k.a(a);
            ContentValues contentValues = new ContentValues();
            contentValues.put(ParamKey.CONTENT, fVar.b.toString());
            contentValues.put("md5sum", a2);
            fVar.c = a2;
            contentValues.put(GameAppOperation.QQFAV_DATALINE_VERSION, Integer.valueOf(fVar.d));
            Cursor query = this.c.getReadableDatabase().query("config", null, null, null, null, null, null);
            do {
                try {
                    if (!query.moveToNext()) {
                        Object obj = null;
                        break;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } while (query.getInt(0) != fVar.a);
            int i = 1;
            this.c.getWritableDatabase().beginTransaction();
            if (1 == obj) {
                update = (long) this.c.getWritableDatabase().update("config", contentValues, "type=?", new String[]{Integer.toString(fVar.a)});
            } else {
                contentValues.put(JsInterface.FUNPLAY_AD_TRPE, Integer.valueOf(fVar.a));
                update = this.c.getWritableDatabase().insert("config", null, contentValues);
            }
            if (update == -1) {
                h.e(new StringBuilder("Failed to store cfg:").append(a).toString());
            } else {
                h.d(new StringBuilder("Sucessed to store cfg:").append(a).toString());
            }
            this.c.getWritableDatabase().setTransactionSuccessful();
            if (query != null) {
                query.close();
            }
            try {
                this.c.getWritableDatabase().endTransaction();
            } catch (Exception e) {
            }
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            this.c.getWritableDatabase().endTransaction();
            throw th;
        }
    }

    private void b(List<bd> list, int i, boolean z) {
        Throwable th;
        Cursor cursor;
        try {
            Cursor query = d(z).query("events", null, "status=?", new String[]{Integer.toString(1)}, null, null, null, Integer.toString(i));
            while (query.moveToNext()) {
                try {
                    long j = query.getLong(0);
                    String string = query.getString(1);
                    if (!StatConfig.g) {
                        string = q.a(string);
                    }
                    int i2 = query.getInt(XZBDevice.DOWNLOAD_LIST_RECYCLE);
                    int i3 = query.getInt(XZBDevice.DOWNLOAD_LIST_FAILED);
                    bd bdVar = new bd(j, string, i2, i3);
                    if (StatConfig.isDebugEnable()) {
                        h.i(new StringBuilder("peek event, id=").append(j).append(",send_count=").append(i3).append(",timestamp=").append(query.getLong(XZBDevice.DOWNLOAD_LIST_ALL)).toString());
                    }
                    list.add(bdVar);
                } catch (Throwable th2) {
                    th = th2;
                }
            }
            if (query != null) {
                query.close();
            }
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    private SQLiteDatabase c(boolean z) {
        return !z ? this.c.getWritableDatabase() : this.d.getWritableDatabase();
    }

    private SQLiteDatabase d(boolean z) {
        return !z ? this.c.getReadableDatabase() : this.d.getReadableDatabase();
    }

    private void f() {
        this.a = g() + h();
    }

    private int g() {
        return (int) DatabaseUtils.queryNumEntries(this.c.getReadableDatabase(), "events");
    }

    private int h() {
        return (int) DatabaseUtils.queryNumEntries(this.d.getReadableDatabase(), "events");
    }

    private void i() {
        SQLiteDatabase sQLiteDatabase = null;
        if (!this.m) {
            synchronized (this.l) {
                try {
                    if (this.l.size() == 0) {
                        return;
                    }
                    this.m = true;
                    if (StatConfig.isDebugEnable()) {
                        h.i(new StringBuilder("insert ").append(this.l.size()).append(" events ,numEventsCachedInMemory:").append(StatConfig.m).append(",numStoredEvents:").append(this.a).toString());
                    }
                    try {
                        sQLiteDatabase = this.c.getWritableDatabase();
                        sQLiteDatabase.beginTransaction();
                        Iterator it = this.l.entrySet().iterator();
                        while (it.hasNext()) {
                            com.tencent.wxop.stat.a.e eVar = (com.tencent.wxop.stat.a.e) ((Entry) it.next()).getKey();
                            ContentValues contentValues = new ContentValues();
                            String g = eVar.g();
                            if (StatConfig.isDebugEnable()) {
                                h.i(new StringBuilder("insert content:").append(g).toString());
                            }
                            contentValues.put(ParamKey.CONTENT, q.b(g));
                            contentValues.put("send_count", MessageService.MSG_DB_READY_REPORT);
                            contentValues.put(Impl.COLUMN_STATUS, Integer.toString(1));
                            contentValues.put("timestamp", Long.valueOf(eVar.c()));
                            sQLiteDatabase.insert("events", null, contentValues);
                            it.remove();
                        }
                        sQLiteDatabase.setTransactionSuccessful();
                        if (sQLiteDatabase != null) {
                            try {
                                sQLiteDatabase.endTransaction();
                                f();
                            } catch (Throwable th) {
                                h.e(th);
                            }
                        }
                    } catch (Throwable th2) {
                        h.e(th2);
                        if (sQLiteDatabase != null) {
                            try {
                                sQLiteDatabase.endTransaction();
                                f();
                            } catch (Throwable th22) {
                                h.e(th22);
                            }
                        }
                    }
                    this.m = false;
                    if (StatConfig.isDebugEnable()) {
                        h.i(new StringBuilder("after insert, cacheEventsInMemory.size():").append(this.l.size()).append(",numEventsCachedInMemory:").append(StatConfig.m).append(",numStoredEvents:").append(this.a).toString());
                    }
                } catch (Throwable th3) {
                }
            }
        }
    }

    private void j() {
        try {
            Cursor query = this.c.getReadableDatabase().query("keyvalues", null, null, null, null, null, null);
            while (query.moveToNext()) {
                try {
                    this.n.put(query.getString(0), query.getString(1));
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
        return this.a;
    }

    void a(int i) {
        this.e.a(new bb(this, i));
    }

    void a(com.tencent.wxop.stat.a.e eVar, h hVar, boolean z, boolean z2) {
        if (this.e != null) {
            this.e.a(new ay(this, eVar, hVar, z, z2));
        }
    }

    void a(f fVar) {
        if (fVar != null) {
            this.e.a(new az(this, fVar));
        }
    }

    void a(List<bd> list, int i, boolean z, boolean z2) {
        if (this.e != null) {
            this.e.a(new av(this, list, i, z, z2));
        }
    }

    void a(List<bd> list, boolean z, boolean z2) {
        if (this.e != null) {
            this.e.a(new aw(this, list, z, z2));
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized com.tencent.wxop.stat.common.a b(android.content.Context r20) {
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.wxop.stat.au.b(android.content.Context):com.tencent.wxop.stat.common.a");
        /*
        this = this;
        monitor-enter(r19);
        r0 = r19;
        r2 = r0.b;	 Catch:{ all -> 0x0207 }
        if (r2 == 0) goto L_0x000d;
    L_0x0007:
        r0 = r19;
        r2 = r0.b;	 Catch:{ all -> 0x0207 }
    L_0x000b:
        monitor-exit(r19);
        return r2;
    L_0x000d:
        r11 = 0;
        r0 = r19;
        r2 = r0.c;	 Catch:{ Throwable -> 0x020a, all -> 0x0229 }
        r2 = r2.getWritableDatabase();	 Catch:{ Throwable -> 0x020a, all -> 0x0229 }
        r2.beginTransaction();	 Catch:{ Throwable -> 0x020a, all -> 0x0229 }
        r2 = com.tencent.wxop.stat.StatConfig.isDebugEnable();	 Catch:{ Throwable -> 0x020a, all -> 0x0229 }
        if (r2 == 0) goto L_0x0027;
    L_0x001f:
        r2 = h;	 Catch:{ Throwable -> 0x020a, all -> 0x0229 }
        r3 = "try to load user info from db.";
        r2.i(r3);	 Catch:{ Throwable -> 0x020a, all -> 0x0229 }
    L_0x0027:
        r0 = r19;
        r2 = r0.c;	 Catch:{ Throwable -> 0x020a, all -> 0x0229 }
        r2 = r2.getReadableDatabase();	 Catch:{ Throwable -> 0x020a, all -> 0x0229 }
        r3 = "user";
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r10 = 0;
        r5 = r2.query(r3, r4, r5, r6, r7, r8, r9, r10);	 Catch:{ Throwable -> 0x020a, all -> 0x0229 }
        r2 = 0;
        r3 = r5.moveToNext();	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        if (r3 == 0) goto L_0x0138;
    L_0x0044:
        r2 = 0;
        r10 = r5.getString(r2);	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        r7 = com.tencent.wxop.stat.common.q.a(r10);	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        r2 = 1;
        r9 = r5.getInt(r2);	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        r2 = 2;
        r3 = r5.getString(r2);	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        r2 = 3;
        r12 = r5.getLong(r2);	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        r6 = 1;
        r14 = java.lang.System.currentTimeMillis();	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        r16 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r14 = r14 / r16;
        r2 = 1;
        if (r9 == r2) goto L_0x025b;
    L_0x0068:
        r16 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r12 = r12 * r16;
        r2 = com.tencent.wxop.stat.common.k.a(r12);	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        r12 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r12 = r12 * r14;
        r4 = com.tencent.wxop.stat.common.k.a(r12);	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        r2 = r2.equals(r4);	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        if (r2 != 0) goto L_0x025b;
    L_0x007d:
        r2 = 1;
    L_0x007e:
        r4 = com.tencent.wxop.stat.common.k.n(r20);	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        r3 = r3.equals(r4);	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        if (r3 != 0) goto L_0x0258;
    L_0x0088:
        r2 = r2 | 2;
        r8 = r2;
    L_0x008b:
        r2 = ",";
        r11 = r7.split(r2);	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        r2 = 0;
        if (r11 == 0) goto L_0x01d1;
    L_0x0095:
        r3 = r11.length;	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        if (r3 <= 0) goto L_0x01d1;
    L_0x0098:
        r3 = 0;
        r4 = r11[r3];	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        if (r4 == 0) goto L_0x00a5;
    L_0x009d:
        r3 = r4.length();	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        r12 = 11;
        if (r3 >= r12) goto L_0x0251;
    L_0x00a5:
        r3 = com.tencent.wxop.stat.common.q.a(r20);	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        if (r3 == 0) goto L_0x024e;
    L_0x00ab:
        r12 = r3.length();	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        r13 = 10;
        if (r12 <= r13) goto L_0x024e;
    L_0x00b3:
        r2 = 1;
    L_0x00b4:
        r4 = r7;
        r7 = r3;
    L_0x00b6:
        if (r11 == 0) goto L_0x01d9;
    L_0x00b8:
        r3 = r11.length;	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        r12 = 2;
        if (r3 < r12) goto L_0x01d9;
    L_0x00bc:
        r3 = 1;
        r3 = r11[r3];	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        r4 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        r4.<init>();	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        r4 = r4.append(r7);	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        r11 = ",";
        r4 = r4.append(r11);	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        r4 = r4.append(r3);	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        r4 = r4.toString();	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
    L_0x00d7:
        r11 = new com.tencent.wxop.stat.common.a;	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        r11.<init>(r7, r3, r8);	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        r0 = r19;
        r0.b = r11;	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        r3 = new android.content.ContentValues;	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        r3.<init>();	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        r4 = com.tencent.wxop.stat.common.q.b(r4);	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        r7 = "uid";
        r3.put(r7, r4);	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        r4 = "user_type";
        r7 = java.lang.Integer.valueOf(r8);	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        r3.put(r4, r7);	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        r4 = "app_ver";
        r7 = com.tencent.wxop.stat.common.k.n(r20);	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        r3.put(r4, r7);	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        r4 = "ts";
        r7 = java.lang.Long.valueOf(r14);	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        r3.put(r4, r7);	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        if (r2 == 0) goto L_0x0126;
    L_0x010f:
        r0 = r19;
        r2 = r0.c;	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        r2 = r2.getWritableDatabase();	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        r4 = "user";
        r7 = "uid=?";
        r11 = 1;
        r11 = new java.lang.String[r11];	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        r12 = 0;
        r11[r12] = r10;	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        r2.update(r4, r3, r7, r11);	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
    L_0x0126:
        if (r8 == r9) goto L_0x0137;
    L_0x0128:
        r0 = r19;
        r2 = r0.c;	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        r2 = r2.getWritableDatabase();	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        r4 = "user";
        r7 = 0;
        r2.replace(r4, r7, r3);	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
    L_0x0137:
        r2 = r6;
    L_0x0138:
        if (r2 != 0) goto L_0x01b0;
    L_0x013a:
        r3 = com.tencent.wxop.stat.common.k.b(r20);	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        r4 = com.tencent.wxop.stat.common.k.c(r20);	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        if (r4 == 0) goto L_0x024b;
    L_0x0144:
        r2 = r4.length();	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        if (r2 <= 0) goto L_0x024b;
    L_0x014a:
        r2 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        r2.<init>();	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        r2 = r2.append(r3);	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        r6 = ",";
        r2 = r2.append(r6);	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        r2 = r2.append(r4);	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        r2 = r2.toString();	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
    L_0x0162:
        r6 = java.lang.System.currentTimeMillis();	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        r8 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r6 = r6 / r8;
        r8 = com.tencent.wxop.stat.common.k.n(r20);	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        r9 = new android.content.ContentValues;	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        r9.<init>();	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        r2 = com.tencent.wxop.stat.common.q.b(r2);	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        r10 = "uid";
        r9.put(r10, r2);	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        r2 = "user_type";
        r10 = 0;
        r10 = java.lang.Integer.valueOf(r10);	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        r9.put(r2, r10);	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        r2 = "app_ver";
        r9.put(r2, r8);	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        r2 = "ts";
        r6 = java.lang.Long.valueOf(r6);	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        r9.put(r2, r6);	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        r0 = r19;
        r2 = r0.c;	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        r2 = r2.getWritableDatabase();	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        r6 = "user";
        r7 = 0;
        r2.insert(r6, r7, r9);	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        r2 = new com.tencent.wxop.stat.common.a;	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        r6 = 0;
        r2.<init>(r3, r4, r6);	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        r0 = r19;
        r0.b = r2;	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
    L_0x01b0:
        r0 = r19;
        r2 = r0.c;	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        r2 = r2.getWritableDatabase();	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        r2.setTransactionSuccessful();	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        if (r5 == 0) goto L_0x01c0;
    L_0x01bd:
        r5.close();	 Catch:{ Throwable -> 0x0200 }
    L_0x01c0:
        r0 = r19;
        r2 = r0.c;	 Catch:{ Throwable -> 0x0200 }
        r2 = r2.getWritableDatabase();	 Catch:{ Throwable -> 0x0200 }
        r2.endTransaction();	 Catch:{ Throwable -> 0x0200 }
    L_0x01cb:
        r0 = r19;
        r2 = r0.b;	 Catch:{ all -> 0x0207 }
        goto L_0x000b;
    L_0x01d1:
        r4 = com.tencent.wxop.stat.common.k.b(r20);	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        r2 = 1;
        r7 = r4;
        goto L_0x00b6;
    L_0x01d9:
        r3 = com.tencent.wxop.stat.common.k.c(r20);	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        if (r3 == 0) goto L_0x00d7;
    L_0x01df:
        r11 = r3.length();	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        if (r11 <= 0) goto L_0x00d7;
    L_0x01e5:
        r2 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        r2.<init>();	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        r2 = r2.append(r7);	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        r4 = ",";
        r2 = r2.append(r4);	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        r2 = r2.append(r3);	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        r4 = r2.toString();	 Catch:{ Throwable -> 0x0248, all -> 0x0243 }
        r2 = 1;
        goto L_0x00d7;
    L_0x0200:
        r2 = move-exception;
        r3 = h;	 Catch:{ all -> 0x0207 }
        r3.e(r2);	 Catch:{ all -> 0x0207 }
        goto L_0x01cb;
    L_0x0207:
        r2 = move-exception;
        monitor-exit(r19);
        throw r2;
    L_0x020a:
        r2 = move-exception;
        r3 = r11;
    L_0x020c:
        r4 = h;	 Catch:{ all -> 0x0245 }
        r4.e(r2);	 Catch:{ all -> 0x0245 }
        if (r3 == 0) goto L_0x0216;
    L_0x0213:
        r3.close();	 Catch:{ Throwable -> 0x0222 }
    L_0x0216:
        r0 = r19;
        r2 = r0.c;	 Catch:{ Throwable -> 0x0222 }
        r2 = r2.getWritableDatabase();	 Catch:{ Throwable -> 0x0222 }
        r2.endTransaction();	 Catch:{ Throwable -> 0x0222 }
        goto L_0x01cb;
    L_0x0222:
        r2 = move-exception;
        r3 = h;	 Catch:{ all -> 0x0207 }
        r3.e(r2);	 Catch:{ all -> 0x0207 }
        goto L_0x01cb;
    L_0x0229:
        r2 = move-exception;
        r5 = r11;
    L_0x022b:
        if (r5 == 0) goto L_0x0230;
    L_0x022d:
        r5.close();	 Catch:{ Throwable -> 0x023c }
    L_0x0230:
        r0 = r19;
        r3 = r0.c;	 Catch:{ Throwable -> 0x023c }
        r3 = r3.getWritableDatabase();	 Catch:{ Throwable -> 0x023c }
        r3.endTransaction();	 Catch:{ Throwable -> 0x023c }
    L_0x023b:
        throw r2;	 Catch:{ all -> 0x0207 }
    L_0x023c:
        r3 = move-exception;
        r4 = h;	 Catch:{ all -> 0x0207 }
        r4.e(r3);	 Catch:{ all -> 0x0207 }
        goto L_0x023b;
    L_0x0243:
        r2 = move-exception;
        goto L_0x022b;
    L_0x0245:
        r2 = move-exception;
        r5 = r3;
        goto L_0x022b;
    L_0x0248:
        r2 = move-exception;
        r3 = r5;
        goto L_0x020c;
    L_0x024b:
        r2 = r3;
        goto L_0x0162;
    L_0x024e:
        r3 = r4;
        goto L_0x00b4;
    L_0x0251:
        r18 = r4;
        r4 = r7;
        r7 = r18;
        goto L_0x00b6;
    L_0x0258:
        r8 = r2;
        goto L_0x008b;
    L_0x025b:
        r2 = r9;
        goto L_0x007e;
        */
    }

    void c() {
        if (StatConfig.isEnableStatService()) {
            try {
                this.e.a(new ax(this));
            } catch (Throwable th) {
                h.e(th);
            }
        }
    }

    void d() {
        try {
            Cursor query = this.c.getReadableDatabase().query("config", null, null, null, null, null, null);
            while (query.moveToNext()) {
                try {
                    int i = query.getInt(0);
                    String string = query.getString(1);
                    String string2 = query.getString(XZBDevice.DOWNLOAD_LIST_RECYCLE);
                    int i2 = query.getInt(XZBDevice.DOWNLOAD_LIST_FAILED);
                    f fVar = new f(i);
                    fVar.a = i;
                    fVar.b = new JSONObject(string);
                    fVar.c = string2;
                    fVar.d = i2;
                    StatConfig.a(i, fVar);
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
}
