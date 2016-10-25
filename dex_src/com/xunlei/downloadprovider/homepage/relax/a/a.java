package com.xunlei.downloadprovider.homepage.relax.a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.model.protocol.b.d;
import com.xunlei.thundersniffer.sniff.sniffer.SnifferProtocol.SetKey;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.ArrayList;
import java.util.List;
import org.android.agoo.common.AgooConstants;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: RelaxDb.java
public final class a extends SQLiteOpenHelper {
    private static a a;

    static {
        a = null;
    }

    private a() {
        this(BrothersApplication.a(), "xl_relax.db");
    }

    private a(Context context, String str) {
        super(context, str, null, 7);
    }

    public static synchronized a a() {
        a aVar;
        synchronized (a.class) {
            if (a == null) {
                a = new a();
            }
            aVar = a;
        }
        return aVar;
    }

    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(String.format("create table if not exists %s (id text not null primary key,Resid long,title text,detailUrl text,thumbnailUrl text,resourceUrl text,updateTime text,catrgory integer,subCategory text,blockId long,savaTime long,playenable integer,playtag text,playtype integer,iscloudplay integer,vediowidth integer,vedioHeight integer,content text);", new Object[]{"relax_image"}));
        sQLiteDatabase.execSQL(String.format("create table if not exists %s (id text not null primary key,Resid long,title text,detailUrl text,thumbnailUrl text,resourceUrl text,updateTime text,catrgory integer,subCategory text,blockId long,savaTime long,playenable integer,playtag text,playtype integer,iscloudplay integer,vediowidth integer,vedioHeight integer,content text);", new Object[]{"relax_joke"}));
        sQLiteDatabase.execSQL(String.format("create table if not exists %s (id text not null primary key,Resid long,title text,detailUrl text,thumbnailUrl text,resourceUrl text,updateTime text,catrgory integer,subCategory text,blockId long,savaTime long,playenable integer,playtag text,playtype integer,iscloudplay integer,vediowidth integer,vedioHeight integer,content text);", new Object[]{"relax_vedio"}));
        sQLiteDatabase.execSQL(String.format("create table if not exists %s (id text not null primary key,Resid long,title text,detailUrl text,thumbnailUrl text,resourceUrl text,updateTime text,catrgory integer,subCategory text,blockId long,savaTime long,playenable integer,playtag text,playtype integer,iscloudplay integer,vediowidth integer,vedioHeight integer,content text);", new Object[]{"relax_save"}));
        sQLiteDatabase.execSQL("create table if not exists relax_comment (commentId text not null primary key, commentResid long,userName text,userID long,supportNum integer,commentNum integer,iconUrl text,content text,publicTime long);");
    }

    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL(String.format("drop table if exists %s", new Object[]{"relax_image"}));
        sQLiteDatabase.execSQL(String.format("drop table if exists %s", new Object[]{"relax_joke"}));
        sQLiteDatabase.execSQL(String.format("drop table if exists %s", new Object[]{"relax_vedio"}));
        sQLiteDatabase.execSQL(String.format("drop table if exists %s", new Object[]{"relax_save"}));
        sQLiteDatabase.execSQL(String.format("drop table if exists %s", new Object[]{"relax_comment"}));
        onCreate(sQLiteDatabase);
    }

    public final void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL(String.format("create table if not exists %s (id text not null primary key,Resid long,title text,detailUrl text,thumbnailUrl text,resourceUrl text,updateTime text,catrgory integer,subCategory text,blockId long,savaTime long,playenable integer,playtag text,playtype integer,iscloudplay integer,vediowidth integer,vedioHeight integer,content text);", new Object[]{"relax_image"}));
        sQLiteDatabase.execSQL(String.format("create table if not exists %s (id text not null primary key,Resid long,title text,detailUrl text,thumbnailUrl text,resourceUrl text,updateTime text,catrgory integer,subCategory text,blockId long,savaTime long,playenable integer,playtag text,playtype integer,iscloudplay integer,vediowidth integer,vedioHeight integer,content text);", new Object[]{"relax_joke"}));
        sQLiteDatabase.execSQL(String.format("create table if not exists %s (id text not null primary key,Resid long,title text,detailUrl text,thumbnailUrl text,resourceUrl text,updateTime text,catrgory integer,subCategory text,blockId long,savaTime long,playenable integer,playtag text,playtype integer,iscloudplay integer,vediowidth integer,vedioHeight integer,content text);", new Object[]{"relax_vedio"}));
        sQLiteDatabase.execSQL(String.format("create table if not exists %s (id text not null primary key,Resid long,title text,detailUrl text,thumbnailUrl text,resourceUrl text,updateTime text,catrgory integer,subCategory text,blockId long,savaTime long,playenable integer,playtag text,playtype integer,iscloudplay integer,vediowidth integer,vedioHeight integer,content text);", new Object[]{"relax_save"}));
        sQLiteDatabase.execSQL("create table if not exists relax_comment (commentId text not null primary key, commentResid long,userName text,userID long,supportNum integer,commentNum integer,iconUrl text,content text,publicTime long);");
    }

    public final synchronized void a(List<d> list, int i) {
        SQLiteDatabase writableDatabase;
        SQLiteException e;
        Cursor cursor;
        SQLiteDatabase sQLiteDatabase;
        Throwable th;
        Cursor cursor2;
        SQLiteDatabase sQLiteDatabase2 = null;
        synchronized (this) {
            if (list != null) {
                int size;
                String a;
                ContentValues contentValues;
                try {
                    size = list.size();
                    a = a(i);
                    try {
                        writableDatabase = getWritableDatabase();
                    } catch (SQLiteException e2) {
                        e = e2;
                        cursor = null;
                        sQLiteDatabase = null;
                        try {
                            e.printStackTrace();
                            if (sQLiteDatabase != null) {
                                sQLiteDatabase.endTransaction();
                            }
                            cursor.close();
                            sQLiteDatabase.close();
                        } catch (Throwable th2) {
                            th = th2;
                            sQLiteDatabase2 = sQLiteDatabase;
                            cursor2 = cursor;
                            if (sQLiteDatabase2 != null) {
                                sQLiteDatabase2.endTransaction();
                            }
                            if (!(cursor2 == null || cursor2.isClosed())) {
                                cursor2.close();
                            }
                            if (sQLiteDatabase2 != null && sQLiteDatabase2.isOpen()) {
                                sQLiteDatabase2.close();
                            }
                            throw th;
                        }
                        a = a(i);
                        sQLiteDatabase2 = getWritableDatabase();
                        sQLiteDatabase2.beginTransaction();
                        contentValues = new ContentValues();
                        for (d dVar : list) {
                            contentValues.put(AgooConstants.MESSAGE_ID, d.a(dVar.g).concat(String.valueOf(dVar.a)));
                            contentValues.put("Resid", Long.valueOf(dVar.a));
                            contentValues.put(SetKey.TITLE, dVar.b);
                            contentValues.put("detailUrl", dVar.c);
                            contentValues.put("thumbnailUrl", dVar.d);
                            contentValues.put("resourceUrl", dVar.e);
                            contentValues.put("updateTime", dVar.f);
                            contentValues.put("catrgory", Integer.valueOf(dVar.g));
                            contentValues.put("subCategory", dVar.h);
                            contentValues.put("content", dVar.i);
                            contentValues.put("blockId", Long.valueOf(dVar.j));
                            contentValues.put("savaTime", Long.valueOf(dVar.n));
                            contentValues.put("playenable", Integer.valueOf(dVar.m));
                            contentValues.put("playtag", dVar.p);
                            contentValues.put("playtype", Integer.valueOf(dVar.q));
                            contentValues.put("iscloudplay", Integer.valueOf(dVar.o));
                            contentValues.put("vediowidth", Integer.valueOf(dVar.k));
                            contentValues.put("vedioHeight", Integer.valueOf(dVar.l));
                            sQLiteDatabase2.replace(a, null, contentValues);
                            contentValues.clear();
                        }
                        sQLiteDatabase2.setTransactionSuccessful();
                        if (sQLiteDatabase2 != null) {
                            if (sQLiteDatabase2.isOpen()) {
                                sQLiteDatabase2.endTransaction();
                                sQLiteDatabase2.close();
                            }
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        cursor2 = null;
                        if (sQLiteDatabase2 != null) {
                            sQLiteDatabase2.endTransaction();
                        }
                        cursor2.close();
                        sQLiteDatabase2.close();
                        throw th;
                    }
                } catch (Throwable th4) {
                }
                try {
                    writableDatabase.beginTransaction();
                    cursor2 = writableDatabase.query(a, new String[]{AgooConstants.MESSAGE_ID, "Resid"}, null, null, null, null, "Resid asc");
                } catch (SQLiteException e3) {
                    sQLiteDatabase = writableDatabase;
                    e = e3;
                    cursor = null;
                    e.printStackTrace();
                    if (sQLiteDatabase != null) {
                        sQLiteDatabase.endTransaction();
                    }
                    if (!(cursor == null || cursor.isClosed())) {
                        cursor.close();
                    }
                    if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                        sQLiteDatabase.close();
                    }
                    a = a(i);
                    sQLiteDatabase2 = getWritableDatabase();
                    sQLiteDatabase2.beginTransaction();
                    contentValues = new ContentValues();
                    for (d dVar2 : list) {
                        contentValues.put(AgooConstants.MESSAGE_ID, d.a(dVar2.g).concat(String.valueOf(dVar2.a)));
                        contentValues.put("Resid", Long.valueOf(dVar2.a));
                        contentValues.put(SetKey.TITLE, dVar2.b);
                        contentValues.put("detailUrl", dVar2.c);
                        contentValues.put("thumbnailUrl", dVar2.d);
                        contentValues.put("resourceUrl", dVar2.e);
                        contentValues.put("updateTime", dVar2.f);
                        contentValues.put("catrgory", Integer.valueOf(dVar2.g));
                        contentValues.put("subCategory", dVar2.h);
                        contentValues.put("content", dVar2.i);
                        contentValues.put("blockId", Long.valueOf(dVar2.j));
                        contentValues.put("savaTime", Long.valueOf(dVar2.n));
                        contentValues.put("playenable", Integer.valueOf(dVar2.m));
                        contentValues.put("playtag", dVar2.p);
                        contentValues.put("playtype", Integer.valueOf(dVar2.q));
                        contentValues.put("iscloudplay", Integer.valueOf(dVar2.o));
                        contentValues.put("vediowidth", Integer.valueOf(dVar2.k));
                        contentValues.put("vedioHeight", Integer.valueOf(dVar2.l));
                        sQLiteDatabase2.replace(a, null, contentValues);
                        contentValues.clear();
                    }
                    sQLiteDatabase2.setTransactionSuccessful();
                    if (sQLiteDatabase2 != null) {
                        if (sQLiteDatabase2.isOpen()) {
                            sQLiteDatabase2.endTransaction();
                            sQLiteDatabase2.close();
                        }
                    }
                } catch (Throwable th5) {
                    cursor2 = null;
                    sQLiteDatabase2 = writableDatabase;
                    th = th5;
                    if (sQLiteDatabase2 != null) {
                        sQLiteDatabase2.endTransaction();
                    }
                    cursor2.close();
                    sQLiteDatabase2.close();
                    throw th;
                }
                if (cursor2 == null) {
                    if (writableDatabase != null) {
                        writableDatabase.endTransaction();
                    }
                    if (!(cursor2 == null || cursor2.isClosed())) {
                        cursor2.close();
                    }
                    if (writableDatabase != null && writableDatabase.isOpen()) {
                        writableDatabase.close();
                    }
                    a = a(i);
                    try {
                        sQLiteDatabase2 = getWritableDatabase();
                        sQLiteDatabase2.beginTransaction();
                        contentValues = new ContentValues();
                        for (d dVar22 : list) {
                            contentValues.put(AgooConstants.MESSAGE_ID, d.a(dVar22.g).concat(String.valueOf(dVar22.a)));
                            contentValues.put("Resid", Long.valueOf(dVar22.a));
                            contentValues.put(SetKey.TITLE, dVar22.b);
                            contentValues.put("detailUrl", dVar22.c);
                            contentValues.put("thumbnailUrl", dVar22.d);
                            contentValues.put("resourceUrl", dVar22.e);
                            contentValues.put("updateTime", dVar22.f);
                            contentValues.put("catrgory", Integer.valueOf(dVar22.g));
                            contentValues.put("subCategory", dVar22.h);
                            contentValues.put("content", dVar22.i);
                            contentValues.put("blockId", Long.valueOf(dVar22.j));
                            contentValues.put("savaTime", Long.valueOf(dVar22.n));
                            contentValues.put("playenable", Integer.valueOf(dVar22.m));
                            contentValues.put("playtag", dVar22.p);
                            contentValues.put("playtype", Integer.valueOf(dVar22.q));
                            contentValues.put("iscloudplay", Integer.valueOf(dVar22.o));
                            contentValues.put("vediowidth", Integer.valueOf(dVar22.k));
                            contentValues.put("vedioHeight", Integer.valueOf(dVar22.l));
                            sQLiteDatabase2.replace(a, null, contentValues);
                            contentValues.clear();
                        }
                        sQLiteDatabase2.setTransactionSuccessful();
                        if (sQLiteDatabase2 != null) {
                            if (sQLiteDatabase2.isOpen()) {
                                sQLiteDatabase2.endTransaction();
                                sQLiteDatabase2.close();
                            }
                        }
                    } catch (SQLiteException e4) {
                        e4.printStackTrace();
                        if (sQLiteDatabase2 != null) {
                            if (sQLiteDatabase2.isOpen()) {
                                sQLiteDatabase2.endTransaction();
                                sQLiteDatabase2.close();
                            }
                        }
                    }
                } else {
                    int columnCount;
                    try {
                        columnCount = 800 - cursor2.getColumnCount();
                    } catch (SQLiteException e32) {
                        SQLiteException sQLiteException = e32;
                        cursor = cursor2;
                        sQLiteDatabase = writableDatabase;
                        e4 = sQLiteException;
                        e4.printStackTrace();
                        if (sQLiteDatabase != null) {
                            sQLiteDatabase.endTransaction();
                        }
                        cursor.close();
                        sQLiteDatabase.close();
                        a = a(i);
                        sQLiteDatabase2 = getWritableDatabase();
                        sQLiteDatabase2.beginTransaction();
                        contentValues = new ContentValues();
                        for (d dVar222 : list) {
                            contentValues.put(AgooConstants.MESSAGE_ID, d.a(dVar222.g).concat(String.valueOf(dVar222.a)));
                            contentValues.put("Resid", Long.valueOf(dVar222.a));
                            contentValues.put(SetKey.TITLE, dVar222.b);
                            contentValues.put("detailUrl", dVar222.c);
                            contentValues.put("thumbnailUrl", dVar222.d);
                            contentValues.put("resourceUrl", dVar222.e);
                            contentValues.put("updateTime", dVar222.f);
                            contentValues.put("catrgory", Integer.valueOf(dVar222.g));
                            contentValues.put("subCategory", dVar222.h);
                            contentValues.put("content", dVar222.i);
                            contentValues.put("blockId", Long.valueOf(dVar222.j));
                            contentValues.put("savaTime", Long.valueOf(dVar222.n));
                            contentValues.put("playenable", Integer.valueOf(dVar222.m));
                            contentValues.put("playtag", dVar222.p);
                            contentValues.put("playtype", Integer.valueOf(dVar222.q));
                            contentValues.put("iscloudplay", Integer.valueOf(dVar222.o));
                            contentValues.put("vediowidth", Integer.valueOf(dVar222.k));
                            contentValues.put("vedioHeight", Integer.valueOf(dVar222.l));
                            sQLiteDatabase2.replace(a, null, contentValues);
                            contentValues.clear();
                        }
                        sQLiteDatabase2.setTransactionSuccessful();
                        if (sQLiteDatabase2 != null) {
                            if (sQLiteDatabase2.isOpen()) {
                                sQLiteDatabase2.endTransaction();
                                sQLiteDatabase2.close();
                            }
                        }
                    } catch (Throwable th52) {
                        sQLiteDatabase2 = writableDatabase;
                        th = th52;
                        if (sQLiteDatabase2 != null) {
                            sQLiteDatabase2.endTransaction();
                        }
                        cursor2.close();
                        sQLiteDatabase2.close();
                        throw th;
                    }
                    if (size <= columnCount) {
                        if (writableDatabase != null) {
                            writableDatabase.endTransaction();
                        }
                        if (!(cursor2 == null || cursor2.isClosed())) {
                            cursor2.close();
                        }
                        if (writableDatabase != null && writableDatabase.isOpen()) {
                            writableDatabase.close();
                        }
                        a = a(i);
                        sQLiteDatabase2 = getWritableDatabase();
                        sQLiteDatabase2.beginTransaction();
                        contentValues = new ContentValues();
                        for (d dVar2222 : list) {
                            contentValues.put(AgooConstants.MESSAGE_ID, d.a(dVar2222.g).concat(String.valueOf(dVar2222.a)));
                            contentValues.put("Resid", Long.valueOf(dVar2222.a));
                            contentValues.put(SetKey.TITLE, dVar2222.b);
                            contentValues.put("detailUrl", dVar2222.c);
                            contentValues.put("thumbnailUrl", dVar2222.d);
                            contentValues.put("resourceUrl", dVar2222.e);
                            contentValues.put("updateTime", dVar2222.f);
                            contentValues.put("catrgory", Integer.valueOf(dVar2222.g));
                            contentValues.put("subCategory", dVar2222.h);
                            contentValues.put("content", dVar2222.i);
                            contentValues.put("blockId", Long.valueOf(dVar2222.j));
                            contentValues.put("savaTime", Long.valueOf(dVar2222.n));
                            contentValues.put("playenable", Integer.valueOf(dVar2222.m));
                            contentValues.put("playtag", dVar2222.p);
                            contentValues.put("playtype", Integer.valueOf(dVar2222.q));
                            contentValues.put("iscloudplay", Integer.valueOf(dVar2222.o));
                            contentValues.put("vediowidth", Integer.valueOf(dVar2222.k));
                            contentValues.put("vedioHeight", Integer.valueOf(dVar2222.l));
                            sQLiteDatabase2.replace(a, null, contentValues);
                            contentValues.clear();
                        }
                        sQLiteDatabase2.setTransactionSuccessful();
                        if (sQLiteDatabase2 != null) {
                            if (sQLiteDatabase2.isOpen()) {
                                sQLiteDatabase2.endTransaction();
                                sQLiteDatabase2.close();
                            }
                        }
                    } else {
                        if (cursor2.moveToPosition(size - columnCount)) {
                            long j = cursor2.getLong(cursor2.getColumnIndexOrThrow("Resid"));
                            writableDatabase.delete(a, "Resid <= ?", new String[]{String.valueOf(j)});
                        }
                        writableDatabase.setTransactionSuccessful();
                        if (writableDatabase != null) {
                            writableDatabase.endTransaction();
                        }
                        if (!(cursor2 == null || cursor2.isClosed())) {
                            cursor2.close();
                        }
                        if (writableDatabase != null && writableDatabase.isOpen()) {
                            writableDatabase.close();
                        }
                        a = a(i);
                        sQLiteDatabase2 = getWritableDatabase();
                        sQLiteDatabase2.beginTransaction();
                        contentValues = new ContentValues();
                        for (d dVar22222 : list) {
                            contentValues.put(AgooConstants.MESSAGE_ID, d.a(dVar22222.g).concat(String.valueOf(dVar22222.a)));
                            contentValues.put("Resid", Long.valueOf(dVar22222.a));
                            contentValues.put(SetKey.TITLE, dVar22222.b);
                            contentValues.put("detailUrl", dVar22222.c);
                            contentValues.put("thumbnailUrl", dVar22222.d);
                            contentValues.put("resourceUrl", dVar22222.e);
                            contentValues.put("updateTime", dVar22222.f);
                            contentValues.put("catrgory", Integer.valueOf(dVar22222.g));
                            contentValues.put("subCategory", dVar22222.h);
                            contentValues.put("content", dVar22222.i);
                            contentValues.put("blockId", Long.valueOf(dVar22222.j));
                            contentValues.put("savaTime", Long.valueOf(dVar22222.n));
                            contentValues.put("playenable", Integer.valueOf(dVar22222.m));
                            contentValues.put("playtag", dVar22222.p);
                            contentValues.put("playtype", Integer.valueOf(dVar22222.q));
                            contentValues.put("iscloudplay", Integer.valueOf(dVar22222.o));
                            contentValues.put("vediowidth", Integer.valueOf(dVar22222.k));
                            contentValues.put("vedioHeight", Integer.valueOf(dVar22222.l));
                            sQLiteDatabase2.replace(a, null, contentValues);
                            contentValues.clear();
                        }
                        sQLiteDatabase2.setTransactionSuccessful();
                        if (sQLiteDatabase2 != null) {
                            if (sQLiteDatabase2.isOpen()) {
                                sQLiteDatabase2.endTransaction();
                                sQLiteDatabase2.close();
                            }
                        }
                    }
                }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized java.util.List<com.xunlei.downloadprovider.model.protocol.b.d> a(int r14, int r15, long r16, int r18) {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.downloadprovider.homepage.relax.a.a.a(int, int, long, int):java.util.List<com.xunlei.downloadprovider.model.protocol.b.d>");
        /*
        this = this;
        monitor-enter(r13);
        r10 = new java.util.ArrayList;	 Catch:{ all -> 0x01b2 }
        r10.<init>();	 Catch:{ all -> 0x01b2 }
        r2 = 0;
        r9 = 0;
        r1 = a(r14);	 Catch:{ all -> 0x01b2 }
        r0 = r13.getReadableDatabase();	 Catch:{ SQLiteException -> 0x01f7, all -> 0x01cd }
        r2 = 4;
        if (r14 != r2) goto L_0x0154;
    L_0x0013:
        r2 = "savaTime";
        r5 = r2;
    L_0x0017:
        r2 = 0;
        r2 = (r16 > r2 ? 1 : (r16 == r2 ? 0 : -1));
        if (r2 > 0) goto L_0x015a;
    L_0x001d:
        r2 = 1;
    L_0x001e:
        if (r2 == 0) goto L_0x015d;
    L_0x0020:
        r3 = 0;
        r4 = 0;
        r2 = new java.lang.StringBuilder;	 Catch:{ SQLiteException -> 0x0194, all -> 0x01e7 }
        r2.<init>();	 Catch:{ SQLiteException -> 0x0194, all -> 0x01e7 }
        r2 = r2.append(r5);	 Catch:{ SQLiteException -> 0x0194, all -> 0x01e7 }
        r5 = " desc";
        r2 = r2.append(r5);	 Catch:{ SQLiteException -> 0x0194, all -> 0x01e7 }
        r7 = r2.toString();	 Catch:{ SQLiteException -> 0x0194, all -> 0x01e7 }
    L_0x0036:
        r2 = 0;
        r5 = 0;
        r6 = 0;
        r8 = java.lang.String.valueOf(r18);	 Catch:{ SQLiteException -> 0x0194, all -> 0x01e7 }
        r2 = r0.query(r1, r2, r3, r4, r5, r6, r7, r8);	 Catch:{ SQLiteException -> 0x0194, all -> 0x01e7 }
        if (r2 == 0) goto L_0x013c;
    L_0x0043:
        r1 = r2.moveToFirst();	 Catch:{ SQLiteException -> 0x01fa, all -> 0x01ed }
        if (r1 == 0) goto L_0x013c;
    L_0x0049:
        r1 = new com.xunlei.downloadprovider.model.protocol.b.d;	 Catch:{ SQLiteException -> 0x01fa, all -> 0x01ed }
        r1.<init>();	 Catch:{ SQLiteException -> 0x01fa, all -> 0x01ed }
        r3 = "Resid";
        r3 = r2.getColumnIndexOrThrow(r3);	 Catch:{ SQLiteException -> 0x01fa, all -> 0x01ed }
        r4 = r2.getLong(r3);	 Catch:{ SQLiteException -> 0x01fa, all -> 0x01ed }
        r1.a = r4;	 Catch:{ SQLiteException -> 0x01fa, all -> 0x01ed }
        r3 = "title";
        r3 = r2.getColumnIndexOrThrow(r3);	 Catch:{ SQLiteException -> 0x01fa, all -> 0x01ed }
        r3 = r2.getString(r3);	 Catch:{ SQLiteException -> 0x01fa, all -> 0x01ed }
        r1.b = r3;	 Catch:{ SQLiteException -> 0x01fa, all -> 0x01ed }
        r3 = "detailUrl";
        r3 = r2.getColumnIndexOrThrow(r3);	 Catch:{ SQLiteException -> 0x01fa, all -> 0x01ed }
        r3 = r2.getString(r3);	 Catch:{ SQLiteException -> 0x01fa, all -> 0x01ed }
        r1.c = r3;	 Catch:{ SQLiteException -> 0x01fa, all -> 0x01ed }
        r3 = "thumbnailUrl";
        r3 = r2.getColumnIndexOrThrow(r3);	 Catch:{ SQLiteException -> 0x01fa, all -> 0x01ed }
        r3 = r2.getString(r3);	 Catch:{ SQLiteException -> 0x01fa, all -> 0x01ed }
        r1.d = r3;	 Catch:{ SQLiteException -> 0x01fa, all -> 0x01ed }
        r3 = "resourceUrl";
        r3 = r2.getColumnIndexOrThrow(r3);	 Catch:{ SQLiteException -> 0x01fa, all -> 0x01ed }
        r3 = r2.getString(r3);	 Catch:{ SQLiteException -> 0x01fa, all -> 0x01ed }
        r1.e = r3;	 Catch:{ SQLiteException -> 0x01fa, all -> 0x01ed }
        r3 = "updateTime";
        r3 = r2.getColumnIndexOrThrow(r3);	 Catch:{ SQLiteException -> 0x01fa, all -> 0x01ed }
        r3 = r2.getString(r3);	 Catch:{ SQLiteException -> 0x01fa, all -> 0x01ed }
        r1.f = r3;	 Catch:{ SQLiteException -> 0x01fa, all -> 0x01ed }
        r3 = "catrgory";
        r3 = r2.getColumnIndexOrThrow(r3);	 Catch:{ SQLiteException -> 0x01fa, all -> 0x01ed }
        r3 = r2.getInt(r3);	 Catch:{ SQLiteException -> 0x01fa, all -> 0x01ed }
        r1.g = r3;	 Catch:{ SQLiteException -> 0x01fa, all -> 0x01ed }
        r3 = "subCategory";
        r3 = r2.getColumnIndexOrThrow(r3);	 Catch:{ SQLiteException -> 0x01fa, all -> 0x01ed }
        r3 = r2.getString(r3);	 Catch:{ SQLiteException -> 0x01fa, all -> 0x01ed }
        r1.h = r3;	 Catch:{ SQLiteException -> 0x01fa, all -> 0x01ed }
        r3 = "content";
        r3 = r2.getColumnIndexOrThrow(r3);	 Catch:{ SQLiteException -> 0x01fa, all -> 0x01ed }
        r3 = r2.getString(r3);	 Catch:{ SQLiteException -> 0x01fa, all -> 0x01ed }
        r1.i = r3;	 Catch:{ SQLiteException -> 0x01fa, all -> 0x01ed }
        r3 = "blockId";
        r3 = r2.getColumnIndexOrThrow(r3);	 Catch:{ SQLiteException -> 0x01fa, all -> 0x01ed }
        r4 = r2.getLong(r3);	 Catch:{ SQLiteException -> 0x01fa, all -> 0x01ed }
        r1.j = r4;	 Catch:{ SQLiteException -> 0x01fa, all -> 0x01ed }
        r3 = "savaTime";
        r3 = r2.getColumnIndexOrThrow(r3);	 Catch:{ SQLiteException -> 0x01fa, all -> 0x01ed }
        r4 = r2.getLong(r3);	 Catch:{ SQLiteException -> 0x01fa, all -> 0x01ed }
        r1.n = r4;	 Catch:{ SQLiteException -> 0x01fa, all -> 0x01ed }
        r3 = "playenable";
        r3 = r2.getColumnIndexOrThrow(r3);	 Catch:{ SQLiteException -> 0x01fa, all -> 0x01ed }
        r3 = r2.getInt(r3);	 Catch:{ SQLiteException -> 0x01fa, all -> 0x01ed }
        r1.m = r3;	 Catch:{ SQLiteException -> 0x01fa, all -> 0x01ed }
        r3 = "playtag";
        r3 = r2.getColumnIndexOrThrow(r3);	 Catch:{ SQLiteException -> 0x01fa, all -> 0x01ed }
        r3 = r2.getString(r3);	 Catch:{ SQLiteException -> 0x01fa, all -> 0x01ed }
        r1.p = r3;	 Catch:{ SQLiteException -> 0x01fa, all -> 0x01ed }
        r3 = "playtype";
        r3 = r2.getColumnIndexOrThrow(r3);	 Catch:{ SQLiteException -> 0x01fa, all -> 0x01ed }
        r3 = r2.getInt(r3);	 Catch:{ SQLiteException -> 0x01fa, all -> 0x01ed }
        r1.q = r3;	 Catch:{ SQLiteException -> 0x01fa, all -> 0x01ed }
        r3 = "iscloudplay";
        r3 = r2.getColumnIndexOrThrow(r3);	 Catch:{ SQLiteException -> 0x01fa, all -> 0x01ed }
        r3 = r2.getInt(r3);	 Catch:{ SQLiteException -> 0x01fa, all -> 0x01ed }
        r1.o = r3;	 Catch:{ SQLiteException -> 0x01fa, all -> 0x01ed }
        r3 = "vediowidth";
        r3 = r2.getColumnIndexOrThrow(r3);	 Catch:{ SQLiteException -> 0x01fa, all -> 0x01ed }
        r3 = r2.getInt(r3);	 Catch:{ SQLiteException -> 0x01fa, all -> 0x01ed }
        r1.k = r3;	 Catch:{ SQLiteException -> 0x01fa, all -> 0x01ed }
        r3 = "vedioHeight";
        r3 = r2.getColumnIndexOrThrow(r3);	 Catch:{ SQLiteException -> 0x01fa, all -> 0x01ed }
        r3 = r2.getInt(r3);	 Catch:{ SQLiteException -> 0x01fa, all -> 0x01ed }
        r1.l = r3;	 Catch:{ SQLiteException -> 0x01fa, all -> 0x01ed }
        r4 = r1.a;	 Catch:{ SQLiteException -> 0x01fa, all -> 0x01ed }
        r3 = r13.a(r4);	 Catch:{ SQLiteException -> 0x01fa, all -> 0x01ed }
        r1.r = r3;	 Catch:{ SQLiteException -> 0x01fa, all -> 0x01ed }
        r10.add(r1);	 Catch:{ SQLiteException -> 0x01fa, all -> 0x01ed }
        r1 = r2.moveToNext();	 Catch:{ SQLiteException -> 0x01fa, all -> 0x01ed }
        if (r1 != 0) goto L_0x0049;
    L_0x013c:
        if (r2 == 0) goto L_0x0147;
    L_0x013e:
        r1 = r2.isClosed();	 Catch:{ all -> 0x01b2 }
        if (r1 != 0) goto L_0x0147;
    L_0x0144:
        r2.close();	 Catch:{ all -> 0x01b2 }
    L_0x0147:
        if (r0 == 0) goto L_0x0152;
    L_0x0149:
        r1 = r0.isOpen();	 Catch:{ all -> 0x01b2 }
        if (r1 == 0) goto L_0x0152;
    L_0x014f:
        r0.close();	 Catch:{ all -> 0x01b2 }
    L_0x0152:
        monitor-exit(r13);
        return r10;
    L_0x0154:
        r2 = "Resid";
        r5 = r2;
        goto L_0x0017;
    L_0x015a:
        r2 = 0;
        goto L_0x001e;
    L_0x015d:
        r2 = 1;
        if (r15 != r2) goto L_0x01b5;
    L_0x0160:
        r2 = new java.lang.StringBuilder;	 Catch:{ SQLiteException -> 0x0194, all -> 0x01e7 }
        r2.<init>();	 Catch:{ SQLiteException -> 0x0194, all -> 0x01e7 }
        r2 = r2.append(r5);	 Catch:{ SQLiteException -> 0x0194, all -> 0x01e7 }
        r3 = " asc";
        r2 = r2.append(r3);	 Catch:{ SQLiteException -> 0x0194, all -> 0x01e7 }
        r7 = r2.toString();	 Catch:{ SQLiteException -> 0x0194, all -> 0x01e7 }
        r2 = " > ? ";
    L_0x0177:
        r3 = new java.lang.StringBuilder;	 Catch:{ SQLiteException -> 0x0194, all -> 0x01e7 }
        r3.<init>();	 Catch:{ SQLiteException -> 0x0194, all -> 0x01e7 }
        r3 = r3.append(r5);	 Catch:{ SQLiteException -> 0x0194, all -> 0x01e7 }
        r2 = r3.append(r2);	 Catch:{ SQLiteException -> 0x0194, all -> 0x01e7 }
        r3 = r2.toString();	 Catch:{ SQLiteException -> 0x0194, all -> 0x01e7 }
        r2 = 1;
        r4 = new java.lang.String[r2];	 Catch:{ SQLiteException -> 0x0194, all -> 0x01e7 }
        r2 = 0;
        r5 = java.lang.String.valueOf(r16);	 Catch:{ SQLiteException -> 0x0194, all -> 0x01e7 }
        r4[r2] = r5;	 Catch:{ SQLiteException -> 0x0194, all -> 0x01e7 }
        goto L_0x0036;
    L_0x0194:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
        r1 = r9;
    L_0x0198:
        r0.printStackTrace();	 Catch:{ all -> 0x01f2 }
        if (r1 == 0) goto L_0x01a6;
    L_0x019d:
        r0 = r1.isClosed();	 Catch:{ all -> 0x01b2 }
        if (r0 != 0) goto L_0x01a6;
    L_0x01a3:
        r1.close();	 Catch:{ all -> 0x01b2 }
    L_0x01a6:
        if (r2 == 0) goto L_0x0152;
    L_0x01a8:
        r0 = r2.isOpen();	 Catch:{ all -> 0x01b2 }
        if (r0 == 0) goto L_0x0152;
    L_0x01ae:
        r2.close();	 Catch:{ all -> 0x01b2 }
        goto L_0x0152;
    L_0x01b2:
        r0 = move-exception;
        monitor-exit(r13);
        throw r0;
    L_0x01b5:
        r2 = new java.lang.StringBuilder;	 Catch:{ SQLiteException -> 0x0194, all -> 0x01e7 }
        r2.<init>();	 Catch:{ SQLiteException -> 0x0194, all -> 0x01e7 }
        r2 = r2.append(r5);	 Catch:{ SQLiteException -> 0x0194, all -> 0x01e7 }
        r3 = " desc";
        r2 = r2.append(r3);	 Catch:{ SQLiteException -> 0x0194, all -> 0x01e7 }
        r7 = r2.toString();	 Catch:{ SQLiteException -> 0x0194, all -> 0x01e7 }
        r2 = " < ? ";
        goto L_0x0177;
    L_0x01cd:
        r0 = move-exception;
        r1 = r2;
        r2 = r9;
    L_0x01d0:
        if (r2 == 0) goto L_0x01db;
    L_0x01d2:
        r3 = r2.isClosed();	 Catch:{ all -> 0x01b2 }
        if (r3 != 0) goto L_0x01db;
    L_0x01d8:
        r2.close();	 Catch:{ all -> 0x01b2 }
    L_0x01db:
        if (r1 == 0) goto L_0x01e6;
    L_0x01dd:
        r2 = r1.isOpen();	 Catch:{ all -> 0x01b2 }
        if (r2 == 0) goto L_0x01e6;
    L_0x01e3:
        r1.close();	 Catch:{ all -> 0x01b2 }
    L_0x01e6:
        throw r0;	 Catch:{ all -> 0x01b2 }
    L_0x01e7:
        r1 = move-exception;
        r2 = r9;
        r11 = r1;
        r1 = r0;
        r0 = r11;
        goto L_0x01d0;
    L_0x01ed:
        r1 = move-exception;
        r11 = r1;
        r1 = r0;
        r0 = r11;
        goto L_0x01d0;
    L_0x01f2:
        r0 = move-exception;
        r11 = r1;
        r1 = r2;
        r2 = r11;
        goto L_0x01d0;
    L_0x01f7:
        r0 = move-exception;
        r1 = r9;
        goto L_0x0198;
    L_0x01fa:
        r1 = move-exception;
        r11 = r1;
        r1 = r2;
        r2 = r0;
        r0 = r11;
        goto L_0x0198;
        */
    }

    private synchronized com.xunlei.downloadprovider.model.protocol.c.a a(long j) {
        com.xunlei.downloadprovider.model.protocol.c.a aVar;
        Cursor query;
        Cursor cursor;
        SQLiteException sQLiteException;
        SQLiteDatabase sQLiteDatabase = null;
        synchronized (this) {
            aVar = new com.xunlei.downloadprovider.model.protocol.c.a();
            List arrayList = new ArrayList();
            try {
                SQLiteDatabase readableDatabase = getReadableDatabase();
                try {
                    query = readableDatabase.query("relax_comment", null, "commentResid= ?", new String[]{String.valueOf(j)}, null, null, null);
                    if (query != null) {
                        if (!query.isClosed()) {
                            query.close();
                        }
                    }
                    if (readableDatabase != null && readableDatabase.isOpen()) {
                        readableDatabase.close();
                    }
                } catch (SQLiteException e) {
                    SQLiteException sQLiteException2 = e;
                    cursor = null;
                    sQLiteDatabase = readableDatabase;
                    sQLiteException = sQLiteException2;
                    sQLiteException.printStackTrace();
                    if (cursor != null) {
                        if (!cursor.isClosed()) {
                            cursor.close();
                        }
                    }
                    sQLiteDatabase.close();
                    return aVar;
                } catch (Throwable th) {
                    query = null;
                    sQLiteDatabase = readableDatabase;
                    Throwable th2 = th;
                    if (query != null) {
                        if (!query.isClosed()) {
                            query.close();
                        }
                    }
                    sQLiteDatabase.close();
                    throw th2;
                }
                if (query != null) {
                    try {
                        if (query.moveToFirst()) {
                            aVar.a = query.getLong(query.getColumnIndexOrThrow("commentResid"));
                            aVar.b = query.getInt(query.getColumnIndexOrThrow("commentNum"));
                            aVar.d = query.getInt(query.getColumnIndexOrThrow("supportNum"));
                            do {
                                com.xunlei.downloadprovider.model.protocol.c.a.a.a aVar2 = new com.xunlei.downloadprovider.model.protocol.c.a.a.a();
                                aVar2.f = query.getString(query.getColumnIndexOrThrow("commentId"));
                                aVar2.a = query.getString(query.getColumnIndexOrThrow("userName"));
                                aVar2.b = query.getLong(query.getColumnIndexOrThrow("userID"));
                                aVar2.e = query.getString(query.getColumnIndexOrThrow("iconUrl"));
                                aVar2.d = query.getString(query.getColumnIndexOrThrow("content"));
                                aVar2.c = query.getLong(query.getColumnIndexOrThrow("publicTime"));
                                arrayList.add(aVar2);
                            } while (query.moveToNext());
                            aVar.g = arrayList;
                        }
                    } catch (SQLiteException e2) {
                        sQLiteDatabase = readableDatabase;
                        sQLiteException = e2;
                        cursor = query;
                        sQLiteException.printStackTrace();
                        if (cursor != null) {
                            if (cursor.isClosed()) {
                                cursor.close();
                            }
                        }
                        sQLiteDatabase.close();
                        return aVar;
                    } catch (Throwable th3) {
                        sQLiteDatabase = readableDatabase;
                        th2 = th3;
                        if (query != null) {
                            if (query.isClosed()) {
                                query.close();
                            }
                        }
                        sQLiteDatabase.close();
                        throw th2;
                    }
                }
            } catch (SQLiteException e3) {
                sQLiteException = e3;
                cursor = null;
                try {
                    sQLiteException.printStackTrace();
                    if (cursor != null) {
                        if (cursor.isClosed()) {
                            cursor.close();
                        }
                    }
                    if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                        sQLiteDatabase.close();
                    }
                } catch (Throwable th4) {
                    th2 = th4;
                    query = cursor;
                    if (query != null) {
                        if (query.isClosed()) {
                            query.close();
                        }
                    }
                    sQLiteDatabase.close();
                    throw th2;
                }
                return aVar;
            } catch (Throwable th5) {
                th2 = th5;
                query = null;
                if (query != null) {
                    if (query.isClosed()) {
                        query.close();
                    }
                }
                if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                    sQLiteDatabase.close();
                }
                throw th2;
            }
        }
        return aVar;
    }

    private static String a(int i) {
        String str = BuildConfig.VERSION_NAME;
        switch (i) {
            case SimpleLog.LOG_LEVEL_TRACE:
                return "relax_image";
            case SimpleLog.LOG_LEVEL_DEBUG:
                return "relax_joke";
            case MqttConnectOptions.MQTT_VERSION_3_1:
                return "relax_vedio";
            case MqttConnectOptions.MQTT_VERSION_3_1_1:
                return "relax_save";
            default:
                return str;
        }
    }
}
