package com.xunlei.downloadprovider.model;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import com.xunlei.download.DownloadManager;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.model.a.a;
import com.xunlei.xllib.b.d;
import java.util.ArrayList;
import java.util.List;

// compiled from: ThunderDatabaseProvider.java
public final class p extends SQLiteOpenHelper {
    private static p b;
    private SQLiteDatabase a;

    private p(Context context) {
        super(context, "brothers.db", null, 23);
    }

    public final synchronized void onCreate(SQLiteDatabase sQLiteDatabase) {
        this.a = sQLiteDatabase;
        sQLiteDatabase.execSQL("create table if not exists bookmark (_id integer primary key autoincrement,userid text,markname text,candelete integer,urladdr text,iconurl text,iconlocalpath text,addtype text,ismobilesite integer)");
        sQLiteDatabase.execSQL("create table if not exists searchengine (_id integer primary key autoincrement,title text,urladdr text,iconweburl text,iconlocalpath text,encode text,isdefault integer)");
        sQLiteDatabase.execSQL("create table if not exists sitehistory (_id integer primary key autoincrement,sitename text,accesstime bigint,downloadcount integer default 0,urladdr text)");
        sQLiteDatabase.execSQL("create table if not exists websuggest (_id integer primary key autoincrement,has_add integer,name text,url text,classfication integer,small_icon text,big_icon text)");
        sQLiteDatabase.execSQL("create table if not exists taskinfo (taskid integer not null primary key , tmode integer default 0, org_size integer default 0, maxspeed integer default 0, filesize integer default 0, totaltime integer default 0, isHighSpeedDone integer default 0, highSpeedDownloadedSize integer default 0, taskReportType integer default 0, xlTwoDimensionCodeFrom text, cid text, gcid text, posterurl text, seen integer default 0, vodflag integer default 256, refurl text,  isLiXianSpeedDone integer default 0);");
        sQLiteDatabase.execSQL("create table if not exists fileconsume (taskid integer not null primary key)");
        sQLiteDatabase.execSQL("create table if not exists addwebsiteInfo(id integer not null primary key autoincrement,name text,info text,siteUrl text,logoUrl text,downloadTimes integer,isTranscoded integer,category integer not null)");
    }

    public static p a() {
        if (b == null) {
            b = new p(BrothersApplication.a.getApplicationContext());
        }
        return b;
    }

    public final synchronized List<o> a(String str) {
        List<o> arrayList;
        Exception e;
        arrayList = new ArrayList();
        SQLiteDatabase readableDatabase = getReadableDatabase();
        try {
            Cursor query = readableDatabase.query("sitehistory", null, null, null, null, null, "_id desc", str);
            if (query != null) {
                try {
                    if (query.getCount() > 0 && query.moveToFirst()) {
                        int columnIndex = query.getColumnIndex(DownloadManager.COLUMN_ID);
                        int columnIndex2 = query.getColumnIndex("sitename");
                        int columnIndex3 = query.getColumnIndex("urladdr");
                        int columnIndex4 = query.getColumnIndex("accesstime");
                        int columnIndex5 = query.getColumnIndex("downloadcount");
                        do {
                            o oVar = new o(query.getInt(columnIndex), query.getString(columnIndex2), query.getString(columnIndex3));
                            oVar.d = query.getLong(columnIndex4);
                            oVar.c = query.getInt(columnIndex5);
                            arrayList.add(oVar);
                        } while (query.moveToNext());
                    }
                } catch (Exception e2) {
                    e = e2;
                    e.printStackTrace();
                    if (query != null) {
                        query.close();
                    }
                    if (readableDatabase.isOpen()) {
                        readableDatabase.close();
                    }
                    return arrayList;
                }
            }
            if (query != null) {
                query.close();
            }
            if (readableDatabase.isOpen()) {
                readableDatabase.close();
            }
        } catch (Exception e3) {
            e = e3;
            query = null;
            try {
                e.printStackTrace();
                if (query != null) {
                    query.close();
                }
                if (readableDatabase.isOpen()) {
                    readableDatabase.close();
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                if (query != null) {
                    query.close();
                }
                if (readableDatabase.isOpen()) {
                    readableDatabase.close();
                }
                throw th2;
            }
            return arrayList;
        } catch (Throwable th3) {
            th2 = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            if (readableDatabase.isOpen()) {
                readableDatabase.close();
            }
            throw th2;
        }
        return arrayList;
    }

    public final synchronized List<o> b() {
        List<o> arrayList;
        Exception e;
        arrayList = new ArrayList();
        SQLiteDatabase readableDatabase = getReadableDatabase();
        try {
            Cursor query = readableDatabase.query("sitehistory", null, null, null, null, null, "_id desc");
            if (query != null) {
                try {
                    if (query.getCount() > 0 && query.moveToFirst()) {
                        int columnIndex = query.getColumnIndex(DownloadManager.COLUMN_ID);
                        int columnIndex2 = query.getColumnIndex("sitename");
                        int columnIndex3 = query.getColumnIndex("urladdr");
                        int columnIndex4 = query.getColumnIndex("accesstime");
                        int columnIndex5 = query.getColumnIndex("downloadcount");
                        do {
                            o oVar = new o(query.getInt(columnIndex), query.getString(columnIndex2), query.getString(columnIndex3));
                            oVar.d = query.getLong(columnIndex4);
                            oVar.c = query.getInt(columnIndex5);
                            arrayList.add(oVar);
                        } while (query.moveToNext());
                    }
                } catch (Exception e2) {
                    e = e2;
                    e.printStackTrace();
                    if (query != null) {
                        query.close();
                    }
                    if (readableDatabase.isOpen()) {
                        readableDatabase.close();
                    }
                    return arrayList;
                }
            }
            if (query != null) {
                query.close();
            }
            if (readableDatabase.isOpen()) {
                readableDatabase.close();
            }
        } catch (Exception e3) {
            e = e3;
            query = null;
            try {
                e.printStackTrace();
                if (query != null) {
                    query.close();
                }
                if (readableDatabase.isOpen()) {
                    readableDatabase.close();
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                if (query != null) {
                    query.close();
                }
                if (readableDatabase.isOpen()) {
                    readableDatabase.close();
                }
                throw th2;
            }
            return arrayList;
        } catch (Throwable th3) {
            th2 = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            if (readableDatabase.isOpen()) {
                readableDatabase.close();
            }
            throw th2;
        }
        return arrayList;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized long a(android.content.ContentValues r10) {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.downloadprovider.model.p.a(android.content.ContentValues):long");
        /*
        this = this;
        r1 = 0;
        monitor-enter(r9);
        r4 = 0;
        r6 = r9.getWritableDatabase();	 Catch:{ all -> 0x0093 }
        r0 = "sitename";
        r10.get(r0);	 Catch:{ all -> 0x0093 }
        r0 = "urladdr";
        r0 = r10.get(r0);	 Catch:{ all -> 0x0093 }
        r0 = (java.lang.String) r0;	 Catch:{ all -> 0x0093 }
        r6.beginTransaction();	 Catch:{ Exception -> 0x007a, all -> 0x0096 }
        r3 = r9.c(r0);	 Catch:{ Exception -> 0x007a, all -> 0x0096 }
        if (r3 == 0) goto L_0x005c;
    L_0x0020:
        r1 = r3.getCount();	 Catch:{ Exception -> 0x00ac }
        if (r1 <= 0) goto L_0x005c;
    L_0x0026:
        r1 = r3.moveToNext();	 Catch:{ Exception -> 0x00ac }
        if (r1 == 0) goto L_0x005c;
    L_0x002c:
        r1 = "downloadcount";
        r1 = r3.getColumnIndex(r1);	 Catch:{ Exception -> 0x00ac }
        r1 = r3.getInt(r1);	 Catch:{ Exception -> 0x00ac }
        r2 = "downloadcount";
        r2 = r10.getAsInteger(r2);	 Catch:{ Exception -> 0x00ac }
        r2 = r2.intValue();	 Catch:{ Exception -> 0x00ac }
        r1 = r1 + r2;
        r2 = "downloadcount";
        r1 = java.lang.Integer.valueOf(r1);	 Catch:{ Exception -> 0x00ac }
        r10.put(r2, r1);	 Catch:{ Exception -> 0x00ac }
        r1 = "sitehistory";
        r2 = " urladdr = ? ";
        r7 = 1;
        r7 = new java.lang.String[r7];	 Catch:{ Exception -> 0x00ac }
        r8 = 0;
        r7[r8] = r0;	 Catch:{ Exception -> 0x00ac }
        r6.delete(r1, r2, r7);	 Catch:{ Exception -> 0x00ac }
    L_0x005c:
        r0 = "sitehistory";
        r1 = 0;
        r0 = r6.insert(r0, r1, r10);	 Catch:{ Exception -> 0x00ac }
        r6.setTransactionSuccessful();	 Catch:{ Exception -> 0x00b0 }
        r6.endTransaction();	 Catch:{ all -> 0x0093 }
        if (r3 == 0) goto L_0x006f;
    L_0x006c:
        r3.close();	 Catch:{ all -> 0x0093 }
    L_0x006f:
        r2 = r6.isOpen();	 Catch:{ all -> 0x0093 }
        if (r2 == 0) goto L_0x0078;
    L_0x0075:
        r6.close();	 Catch:{ all -> 0x0093 }
    L_0x0078:
        monitor-exit(r9);
        return r0;
    L_0x007a:
        r0 = move-exception;
        r2 = r0;
        r3 = r1;
        r0 = r4;
    L_0x007e:
        r2.printStackTrace();	 Catch:{ all -> 0x00aa }
        r6.endTransaction();	 Catch:{ all -> 0x0093 }
        if (r3 == 0) goto L_0x0089;
    L_0x0086:
        r3.close();	 Catch:{ all -> 0x0093 }
    L_0x0089:
        r2 = r6.isOpen();	 Catch:{ all -> 0x0093 }
        if (r2 == 0) goto L_0x0078;
    L_0x008f:
        r6.close();	 Catch:{ all -> 0x0093 }
        goto L_0x0078;
    L_0x0093:
        r0 = move-exception;
        monitor-exit(r9);
        throw r0;
    L_0x0096:
        r0 = move-exception;
        r3 = r1;
    L_0x0098:
        r6.endTransaction();	 Catch:{ all -> 0x0093 }
        if (r3 == 0) goto L_0x00a0;
    L_0x009d:
        r3.close();	 Catch:{ all -> 0x0093 }
    L_0x00a0:
        r1 = r6.isOpen();	 Catch:{ all -> 0x0093 }
        if (r1 == 0) goto L_0x00a9;
    L_0x00a6:
        r6.close();	 Catch:{ all -> 0x0093 }
    L_0x00a9:
        throw r0;	 Catch:{ all -> 0x0093 }
    L_0x00aa:
        r0 = move-exception;
        goto L_0x0098;
    L_0x00ac:
        r0 = move-exception;
        r2 = r0;
        r0 = r4;
        goto L_0x007e;
    L_0x00b0:
        r2 = move-exception;
        goto L_0x007e;
        */
    }

    public final synchronized void b(String str) {
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            try {
                writableDatabase.beginTransaction();
                writableDatabase.delete("sitehistory", " sitename = ? ", new String[]{str});
                writableDatabase.setTransactionSuccessful();
                writableDatabase.endTransaction();
                writableDatabase.close();
            } catch (Exception e) {
                e.printStackTrace();
                writableDatabase.endTransaction();
                writableDatabase.close();
            }
        } catch (Throwable th) {
        }
    }

    public final synchronized void a(List<String> list) {
        try {
            SQLiteDatabase writableDatabase;
            if (!d.a(list)) {
                writableDatabase = getWritableDatabase();
                try {
                    writableDatabase.beginTransaction();
                    for (String str : list) {
                        if (!TextUtils.isEmpty(str)) {
                            writableDatabase.delete("sitehistory", " urladdr = ? ", new String[]{(String) r2.next()});
                        }
                    }
                    writableDatabase.setTransactionSuccessful();
                    try {
                        writableDatabase.endTransaction();
                    } catch (Exception e) {
                    }
                    try {
                        writableDatabase.close();
                    } catch (Exception e2) {
                    }
                } catch (Exception e3) {
                    e3.printStackTrace();
                    try {
                        writableDatabase.endTransaction();
                    } catch (Exception e4) {
                    }
                    try {
                        writableDatabase.close();
                    } catch (Exception e5) {
                    }
                }
            }
        } catch (Throwable th) {
        }
    }

    private synchronized Cursor c(String str) {
        return getReadableDatabase().query("sitehistory", null, " urladdr = ? ", new String[]{str}, null, null, "_id desc");
    }

    @Deprecated
    public final synchronized a a(long j) {
        Cursor query;
        a aVar;
        Cursor cursor;
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            try {
                query = writableDatabase.query("taskinfo", new String[]{"taskid", "tmode", "org_size", "maxspeed", "filesize", "totaltime", "highSpeedDownloadedSize", "isHighSpeedDone", "taskReportType", "xlTwoDimensionCodeFrom", Impl.COLUMN_CID, Impl.COLUMN_GCID, "posterurl", "seen", "vodflag", "refurl", "isLiXianSpeedDone"}, " taskid=? ", new String[]{String.valueOf(j)}, null, null, null);
                a aVar2 = null;
                if (query != null) {
                    if (!query.isClosed()) {
                        query.close();
                    }
                }
                if (writableDatabase == null) {
                }
                aVar = aVar2;
            } catch (SQLiteException e) {
                e = e;
                cursor = null;
                r5 = writableDatabase;
                aVar = null;
                e.printStackTrace();
                if (cursor != null) {
                    if (!cursor.isClosed()) {
                        cursor.close();
                    }
                }
                r5.close();
                return aVar;
            } catch (Throwable th) {
                query = null;
                r5 = writableDatabase;
                Throwable th2 = th;
                if (query != null) {
                    if (!query.isClosed()) {
                        query.close();
                    }
                }
                r5.close();
                throw th2;
            }
            if (query != null) {
                try {
                } catch (SQLiteException e2) {
                    e = e2;
                    cursor = query;
                    r5 = writableDatabase;
                    aVar = null;
                    e.printStackTrace();
                    if (cursor != null) {
                        if (cursor.isClosed()) {
                            cursor.close();
                        }
                    }
                    r5.close();
                    return aVar;
                } catch (Throwable th3) {
                    r5 = writableDatabase;
                    th2 = th3;
                    if (query != null) {
                        if (query.isClosed()) {
                            query.close();
                        }
                    }
                    r5.close();
                    throw th2;
                }
                if (query.moveToFirst()) {
                    int columnIndexOrThrow = query.getColumnIndexOrThrow("taskid");
                    int columnIndexOrThrow2 = query.getColumnIndexOrThrow("tmode");
                    int columnIndexOrThrow3 = query.getColumnIndexOrThrow("org_size");
                    int columnIndexOrThrow4 = query.getColumnIndexOrThrow("maxspeed");
                    int columnIndexOrThrow5 = query.getColumnIndexOrThrow("filesize");
                    int columnIndexOrThrow6 = query.getColumnIndexOrThrow("totaltime");
                    int columnIndexOrThrow7 = query.getColumnIndexOrThrow("highSpeedDownloadedSize");
                    int columnIndexOrThrow8 = query.getColumnIndexOrThrow("isHighSpeedDone");
                    int columnIndexOrThrow9 = query.getColumnIndexOrThrow("taskReportType");
                    int columnIndexOrThrow10 = query.getColumnIndexOrThrow("xlTwoDimensionCodeFrom");
                    int columnIndexOrThrow11 = query.getColumnIndexOrThrow(Impl.COLUMN_CID);
                    int columnIndexOrThrow12 = query.getColumnIndexOrThrow(Impl.COLUMN_GCID);
                    int columnIndex = query.getColumnIndex("posterurl");
                    int columnIndex2 = query.getColumnIndex("seen");
                    int columnIndex3 = query.getColumnIndex("vodflag");
                    int columnIndex4 = query.getColumnIndex("refurl");
                    int columnIndex5 = query.getColumnIndex("isLiXianSpeedDone");
                    aVar2 = new a(query.getLong(columnIndexOrThrow), query.getInt(columnIndexOrThrow2), query.getLong(columnIndexOrThrow3), query.getInt(columnIndexOrThrow4), query.getLong(columnIndexOrThrow5), query.getLong(columnIndexOrThrow6), query.getInt(columnIndexOrThrow9), query.getString(columnIndexOrThrow10));
                    try {
                        long j2 = query.getLong(columnIndexOrThrow7);
                        columnIndexOrThrow2 = query.getInt(columnIndexOrThrow8);
                        aVar2.g = j2;
                        aVar2.h = columnIndexOrThrow2;
                        aVar2.l = query.getString(columnIndexOrThrow11);
                        aVar2.k = query.getString(columnIndexOrThrow12);
                        aVar2.m = query.getString(columnIndex);
                        aVar2.n = query.getInt(columnIndex2);
                        aVar2.o = query.getInt(columnIndex3);
                        aVar2.p = query.getString(columnIndex4);
                        aVar2.q = query.getInt(columnIndex5);
                    } catch (SQLiteException e3) {
                        r5 = writableDatabase;
                        aVar = aVar2;
                        e = e3;
                        cursor = query;
                        e.printStackTrace();
                        if (cursor != null) {
                            if (cursor.isClosed()) {
                                cursor.close();
                            }
                        }
                        if (r5 != null && r5.isOpen()) {
                            r5.close();
                        }
                        return aVar;
                    } catch (Throwable th32) {
                        r5 = writableDatabase;
                        th2 = th32;
                        if (query != null) {
                            if (query.isClosed()) {
                                query.close();
                            }
                        }
                        r5.close();
                        throw th2;
                    }
                    if (query != null) {
                        if (query.isClosed()) {
                            query.close();
                        }
                    }
                    if (writableDatabase == null && writableDatabase.isOpen()) {
                        writableDatabase.close();
                        aVar = aVar2;
                    } else {
                        aVar = aVar2;
                    }
                }
            }
        } catch (SQLiteException e4) {
            cursor = null;
            r5 = null;
            e = e4;
            aVar = null;
            try {
                SQLiteException e5;
                SQLiteDatabase sQLiteDatabase;
                e5.printStackTrace();
                if (cursor != null) {
                    if (cursor.isClosed()) {
                        cursor.close();
                    }
                }
                sQLiteDatabase.close();
            } catch (Throwable th4) {
                th2 = th4;
                query = cursor;
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
            return aVar;
        } catch (Throwable th5) {
            th2 = th5;
            query = null;
            sQLiteDatabase = null;
            if (query != null) {
                if (query.isClosed()) {
                    query.close();
                }
            }
            sQLiteDatabase.close();
            throw th2;
        }
        return aVar;
    }

    public final synchronized void c() {
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            try {
                writableDatabase.beginTransaction();
                writableDatabase.delete("taskinfo", null, null);
                writableDatabase.setTransactionSuccessful();
                writableDatabase.endTransaction();
                if (writableDatabase.isOpen()) {
                    writableDatabase.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                writableDatabase.endTransaction();
                if (writableDatabase.isOpen()) {
                    writableDatabase.close();
                }
            }
        } catch (Throwable th) {
        }
    }

    private static void a(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("alter table taskinfo add column cid text;");
            sQLiteDatabase.execSQL("alter table taskinfo add column gcid text;");
        } catch (SQLException e) {
        }
    }

    private static void b(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("alter table taskinfo add column posterurl text;");
        } catch (SQLException e) {
        }
    }

    private static void c(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("alter table taskinfo add column seen integer default 1;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void d(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("alter table taskinfo add column vodflag integer default 256;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void e(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("alter table taskinfo add column refurl text;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void f(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("alter table taskinfo add column isLiXianSpeedDone integer default 0;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i == 4 && i2 == 5) {
            sQLiteDatabase.execSQL("create table if not exists taskinfo (taskid integer not null primary key , tmode integer default 0, org_size integer default 0, maxspeed integer default 0, filesize integer default 0, totaltime integer default 0, isHighSpeedDone integer default 0, highSpeedDownloadedSize integer default 0, taskReportType integer default 0, xlTwoDimensionCodeFrom text, cid text, gcid text, posterurl text, seen integer default 0, vodflag integer default 256, refurl text,  isLiXianSpeedDone integer default 0);");
        } else if (i == 13) {
            sQLiteDatabase.execSQL("create table if not exists addwebsiteInfo(id integer not null primary key autoincrement,name text,info text,siteUrl text,logoUrl text,downloadTimes integer,isTranscoded integer,category integer not null)");
            a(sQLiteDatabase);
            b(sQLiteDatabase);
            c(sQLiteDatabase);
            d(sQLiteDatabase);
            e(sQLiteDatabase);
            f(sQLiteDatabase);
        } else if (i == 16) {
            a(sQLiteDatabase);
            b(sQLiteDatabase);
            c(sQLiteDatabase);
            d(sQLiteDatabase);
            e(sQLiteDatabase);
            f(sQLiteDatabase);
        } else if (i == 17) {
            a(sQLiteDatabase);
            b(sQLiteDatabase);
            c(sQLiteDatabase);
            d(sQLiteDatabase);
            e(sQLiteDatabase);
            f(sQLiteDatabase);
        } else if (i == 18) {
            b(sQLiteDatabase);
            c(sQLiteDatabase);
            d(sQLiteDatabase);
            e(sQLiteDatabase);
            f(sQLiteDatabase);
        } else if (i == 19) {
            c(sQLiteDatabase);
            d(sQLiteDatabase);
            e(sQLiteDatabase);
            f(sQLiteDatabase);
        } else if (i == 20) {
            d(sQLiteDatabase);
            e(sQLiteDatabase);
            f(sQLiteDatabase);
        } else if (i == 21) {
            e(sQLiteDatabase);
            f(sQLiteDatabase);
        } else if (i == 22) {
            f(sQLiteDatabase);
        } else {
            sQLiteDatabase.execSQL("drop table if exists bookmark");
            sQLiteDatabase.execSQL("drop table if exists sitehistory");
            sQLiteDatabase.execSQL("drop table if exists successtasknotread");
            sQLiteDatabase.execSQL("drop table if exists searchengine");
            sQLiteDatabase.execSQL("drop table if exists websuggest");
            sQLiteDatabase.execSQL("drop table if exists fileconsume");
            sQLiteDatabase.execSQL("drop table if exists taskinfo");
            onCreate(sQLiteDatabase);
        }
    }

    public final void close() {
        if (this.a != null) {
            this.a.close();
            this.a = null;
        }
    }
}
