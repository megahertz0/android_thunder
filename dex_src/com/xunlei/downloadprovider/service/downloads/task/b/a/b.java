package com.xunlei.downloadprovider.service.downloads.task.b.a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import com.tencent.open.SocialConstants;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.service.downloads.task.info.c;

// compiled from: ThunderTaskDatabase.java
public final class b extends SQLiteOpenHelper {
    private static b b;
    public final b a;
    private boolean c;
    private final a d;

    private b(Context context, String str) {
        super(context, str, null, 6);
        this.c = false;
        this.a = new b();
        this.d = new a();
    }

    public static b a() {
        if (b == null) {
            b = new b(BrothersApplication.a.getApplicationContext(), "thunder_tasks.db");
        }
        return b;
    }

    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        this.c = true;
        b.a(sQLiteDatabase);
        a.a(sQLiteDatabase);
    }

    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i <= 0) {
            b.a(sQLiteDatabase);
        } else if (i >= 6) {
            b.a(sQLiteDatabase);
        } else {
            if (i < 2) {
                try {
                    sQLiteDatabase.execSQL(new StringBuilder("ALTER TABLE `").append(b.d).append("` ADD COLUMN `max_speed` INTEGER default 0").toString());
                } catch (SQLException e) {
                }
            }
            if (i < 4) {
                try {
                    sQLiteDatabase.execSQL("ALTER TABLE `task_extra` ADD COLUMN `sniff_key_word` TEXT , ` website_name` + TEXT ");
                } catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
            if (i < 5) {
                try {
                    sQLiteDatabase.execSQL("ALTER TABLE `task_extra` ADD COLUMN `icon_url` TEXT ");
                } catch (SQLException e22) {
                    e22.printStackTrace();
                }
            }
            if (i < 6) {
                try {
                    sQLiteDatabase.execSQL("ALTER TABLE `task_extra` ADD COLUMN `display_name` TEXT ");
                } catch (SQLException e222) {
                    e222.printStackTrace();
                }
            }
        }
        if (i < 4) {
            a.a(sQLiteDatabase);
        } else if (i >= 4) {
            a.a(sQLiteDatabase);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized com.xunlei.downloadprovider.service.downloads.task.info.c a(long r8) {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.downloadprovider.service.downloads.task.b.a.b.a(long):com.xunlei.downloadprovider.service.downloads.task.info.c");
        /*
        this = this;
        r0 = 0;
        monitor-enter(r7);
        r2 = 0;
        r1 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1));
        if (r1 >= 0) goto L_0x000a;
    L_0x0008:
        monitor-exit(r7);
        return r0;
    L_0x000a:
        monitor-enter(r7);	 Catch:{ all -> 0x00f4 }
        r1 = r7.getReadableDatabase();	 Catch:{ Exception -> 0x00f7 }
    L_0x000f:
        if (r1 == 0) goto L_0x00ee;
    L_0x0011:
        r2 = "SELECT url,referer,cid,gcid,info_hash,create_origin,task_report_value,seen,max_speed,sniff_key_word,website_name,icon_url,display_name FROM `task_extra` WHERE task_id=?";
        r3 = 1;
        r3 = new java.lang.String[r3];	 Catch:{ Exception -> 0x00fe, all -> 0x0113 }
        r4 = 0;
        r5 = java.lang.String.valueOf(r8);	 Catch:{ Exception -> 0x00fe, all -> 0x0113 }
        r3[r4] = r5;	 Catch:{ Exception -> 0x00fe, all -> 0x0113 }
        r2 = r1.rawQuery(r2, r3);	 Catch:{ Exception -> 0x00fe, all -> 0x0113 }
        r1 = r2.moveToNext();	 Catch:{ Exception -> 0x0127, all -> 0x0122 }
        if (r1 == 0) goto L_0x0138;
    L_0x0028:
        r1 = new com.xunlei.downloadprovider.service.downloads.task.info.c;	 Catch:{ Exception -> 0x0127, all -> 0x0122 }
        r1.<init>();	 Catch:{ Exception -> 0x0127, all -> 0x0122 }
        r1.a = r8;	 Catch:{ Exception -> 0x012d, all -> 0x0122 }
        r3 = "url";
        r3 = r2.getColumnIndex(r3);	 Catch:{ Exception -> 0x012d, all -> 0x0122 }
        r3 = r2.getString(r3);	 Catch:{ Exception -> 0x012d, all -> 0x0122 }
        r1.b = r3;	 Catch:{ Exception -> 0x012d, all -> 0x0122 }
        r3 = "cid";
        r3 = r2.getColumnIndex(r3);	 Catch:{ Exception -> 0x012d, all -> 0x0122 }
        r3 = r2.getString(r3);	 Catch:{ Exception -> 0x012d, all -> 0x0122 }
        r1.d = r3;	 Catch:{ Exception -> 0x012d, all -> 0x0122 }
        r3 = "gcid";
        r3 = r2.getColumnIndex(r3);	 Catch:{ Exception -> 0x012d, all -> 0x0122 }
        r3 = r2.getString(r3);	 Catch:{ Exception -> 0x012d, all -> 0x0122 }
        r1.e = r3;	 Catch:{ Exception -> 0x012d, all -> 0x0122 }
        r3 = "info_hash";
        r3 = r2.getColumnIndex(r3);	 Catch:{ Exception -> 0x012d, all -> 0x0122 }
        r3 = r2.getString(r3);	 Catch:{ Exception -> 0x012d, all -> 0x0122 }
        r1.f = r3;	 Catch:{ Exception -> 0x012d, all -> 0x0122 }
        r3 = "referer";
        r3 = r2.getColumnIndex(r3);	 Catch:{ Exception -> 0x012d, all -> 0x0122 }
        r3 = r2.getString(r3);	 Catch:{ Exception -> 0x012d, all -> 0x0122 }
        r1.c = r3;	 Catch:{ Exception -> 0x012d, all -> 0x0122 }
        r3 = "create_origin";
        r3 = r2.getColumnIndex(r3);	 Catch:{ Exception -> 0x012d, all -> 0x0122 }
        r3 = r2.getString(r3);	 Catch:{ Exception -> 0x012d, all -> 0x0122 }
        r1.h = r3;	 Catch:{ Exception -> 0x012d, all -> 0x0122 }
        r3 = "task_report_value";
        r3 = r2.getColumnIndex(r3);	 Catch:{ Exception -> 0x012d, all -> 0x0122 }
        r3 = r2.getInt(r3);	 Catch:{ Exception -> 0x012d, all -> 0x0122 }
        r1.g = r3;	 Catch:{ Exception -> 0x012d, all -> 0x0122 }
        r3 = "seen";
        r3 = r2.getColumnIndex(r3);	 Catch:{ Exception -> 0x012d, all -> 0x0122 }
        r3 = r2.getInt(r3);	 Catch:{ Exception -> 0x012d, all -> 0x0122 }
        r1.i = r3;	 Catch:{ Exception -> 0x012d, all -> 0x0122 }
        r3 = "max_speed";
        r3 = r2.getColumnIndex(r3);	 Catch:{ Exception -> 0x012d, all -> 0x0122 }
        r3 = r2.getInt(r3);	 Catch:{ Exception -> 0x012d, all -> 0x0122 }
        r4 = (long) r3;	 Catch:{ Exception -> 0x012d, all -> 0x0122 }
        r1.k = r4;	 Catch:{ Exception -> 0x012d, all -> 0x0122 }
        r3 = "sniff_key_word";
        r3 = r2.getColumnIndex(r3);	 Catch:{ Exception -> 0x012d, all -> 0x0122 }
        r3 = r2.getString(r3);	 Catch:{ Exception -> 0x012d, all -> 0x0122 }
        r1.l = r3;	 Catch:{ Exception -> 0x012d, all -> 0x0122 }
        r3 = "website_name";
        r3 = r2.getColumnIndex(r3);	 Catch:{ Exception -> 0x012d, all -> 0x0122 }
        r3 = r2.getString(r3);	 Catch:{ Exception -> 0x012d, all -> 0x0122 }
        r1.m = r3;	 Catch:{ Exception -> 0x012d, all -> 0x0122 }
        r3 = "icon_url";
        r3 = r2.getColumnIndex(r3);	 Catch:{ Exception -> 0x012d, all -> 0x0122 }
        r3 = r2.getString(r3);	 Catch:{ Exception -> 0x012d, all -> 0x0122 }
        r1.n = r3;	 Catch:{ Exception -> 0x012d, all -> 0x0122 }
        r3 = "display_name";
        r3 = r2.getColumnIndex(r3);	 Catch:{ Exception -> 0x012d, all -> 0x0122 }
        r3 = r2.getString(r3);	 Catch:{ Exception -> 0x012d, all -> 0x0122 }
        r1.o = r3;	 Catch:{ Exception -> 0x012d, all -> 0x0122 }
    L_0x00d9:
        r3 = r2.isClosed();	 Catch:{ Exception -> 0x012d, all -> 0x0122 }
        if (r3 != 0) goto L_0x0136;
    L_0x00df:
        r2.close();	 Catch:{ Exception -> 0x012d, all -> 0x0122 }
    L_0x00e2:
        if (r0 == 0) goto L_0x0134;
    L_0x00e4:
        r2 = r0.isClosed();	 Catch:{ all -> 0x00f1 }
        if (r2 != 0) goto L_0x0134;
    L_0x00ea:
        r0.close();	 Catch:{ all -> 0x00f1 }
        r0 = r1;
    L_0x00ee:
        monitor-exit(r7);	 Catch:{ all -> 0x00f1 }
        goto L_0x0008;
    L_0x00f1:
        r0 = move-exception;
        monitor-exit(r7);	 Catch:{ all -> 0x00f1 }
        throw r0;	 Catch:{ all -> 0x00f4 }
    L_0x00f4:
        r0 = move-exception;
        monitor-exit(r7);
        throw r0;
    L_0x00f7:
        r1 = move-exception;
        r1.printStackTrace();	 Catch:{ all -> 0x00f1 }
        r1 = r0;
        goto L_0x000f;
    L_0x00fe:
        r1 = move-exception;
        r2 = r0;
        r6 = r0;
        r0 = r1;
        r1 = r6;
    L_0x0103:
        r0.printStackTrace();	 Catch:{ all -> 0x0124 }
        if (r1 == 0) goto L_0x0132;
    L_0x0108:
        r0 = r1.isClosed();	 Catch:{ all -> 0x00f1 }
        if (r0 != 0) goto L_0x0132;
    L_0x010e:
        r1.close();	 Catch:{ all -> 0x00f1 }
        r0 = r2;
        goto L_0x00ee;
    L_0x0113:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
    L_0x0116:
        if (r2 == 0) goto L_0x0121;
    L_0x0118:
        r1 = r2.isClosed();	 Catch:{ all -> 0x00f1 }
        if (r1 != 0) goto L_0x0121;
    L_0x011e:
        r2.close();	 Catch:{ all -> 0x00f1 }
    L_0x0121:
        throw r0;	 Catch:{ all -> 0x00f1 }
    L_0x0122:
        r0 = move-exception;
        goto L_0x0116;
    L_0x0124:
        r0 = move-exception;
        r2 = r1;
        goto L_0x0116;
    L_0x0127:
        r1 = move-exception;
        r6 = r1;
        r1 = r2;
        r2 = r0;
        r0 = r6;
        goto L_0x0103;
    L_0x012d:
        r0 = move-exception;
        r6 = r2;
        r2 = r1;
        r1 = r6;
        goto L_0x0103;
    L_0x0132:
        r0 = r2;
        goto L_0x00ee;
    L_0x0134:
        r0 = r1;
        goto L_0x00ee;
    L_0x0136:
        r0 = r2;
        goto L_0x00e2;
    L_0x0138:
        r1 = r0;
        goto L_0x00d9;
        */
    }

    public final synchronized long a(c cVar) {
        long j = -1;
        SQLiteDatabase sQLiteDatabase = null;
        synchronized (this) {
            if (cVar != null) {
                try {
                    if (cVar.a >= 0) {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put(SocialConstants.PARAM_URL, cVar.b);
                        contentValues.put(Impl.COLUMN_REFERER, cVar.c);
                        contentValues.put(Impl.COLUMN_CID, cVar.d);
                        contentValues.put(Impl.COLUMN_GCID, cVar.e);
                        contentValues.put("info_hash", cVar.f);
                        contentValues.put("create_origin", cVar.h);
                        contentValues.put("task_report_value", Integer.valueOf(cVar.g));
                        contentValues.put("seen", Integer.valueOf(cVar.i));
                        contentValues.put("sniff_key_word", cVar.l);
                        contentValues.put("website_name", cVar.m);
                        contentValues.put("icon_url", cVar.n);
                        contentValues.put("display_name", cVar.o);
                        synchronized (this) {
                            try {
                                sQLiteDatabase = getWritableDatabase();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            if (sQLiteDatabase != null) {
                                try {
                                    contentValues.put("task_id", Long.valueOf(cVar.a));
                                    j = (long) ((int) sQLiteDatabase.replace("task_extra", null, contentValues));
                                } catch (Exception e2) {
                                    e2.printStackTrace();
                                }
                            }
                        }
                    }
                } catch (Throwable th) {
                }
            }
        }
        return j;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized long a(long r10, long r12, boolean r14) {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.downloadprovider.service.downloads.task.b.a.b.a(long, long, boolean):long");
        /*
        this = this;
        r0 = -1;
        r3 = 0;
        r2 = 0;
        monitor-enter(r9);
        r4 = 0;
        r4 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1));
        if (r4 >= 0) goto L_0x000d;
    L_0x000b:
        monitor-exit(r9);
        return r0;
    L_0x000d:
        r4 = new android.content.ContentValues;	 Catch:{ all -> 0x0059 }
        r4.<init>();	 Catch:{ all -> 0x0059 }
        r5 = "max_speed";
        r6 = java.lang.Long.valueOf(r12);	 Catch:{ all -> 0x0059 }
        r4.put(r5, r6);	 Catch:{ all -> 0x0059 }
        monitor-enter(r9);	 Catch:{ all -> 0x0059 }
        r3 = r9.getWritableDatabase();	 Catch:{ Exception -> 0x005c }
        r5 = "SELECT url FROM `task_extra` WHERE task_id=?";
        r6 = 1;
        r6 = new java.lang.String[r6];	 Catch:{ Exception -> 0x005c }
        r7 = 0;
        r8 = java.lang.String.valueOf(r10);	 Catch:{ Exception -> 0x005c }
        r6[r7] = r8;	 Catch:{ Exception -> 0x005c }
        r5 = r3.rawQuery(r5, r6);	 Catch:{ Exception -> 0x005c }
        r6 = r5.moveToFirst();	 Catch:{ Exception -> 0x005c }
        if (r6 == 0) goto L_0x0039;
    L_0x0038:
        r14 = r2;
    L_0x0039:
        r5.close();	 Catch:{ Exception -> 0x005c }
    L_0x003c:
        if (r3 == 0) goto L_0x0054;
    L_0x003e:
        if (r14 == 0) goto L_0x0061;
    L_0x0040:
        r2 = "task_id";
        r5 = java.lang.Long.valueOf(r10);	 Catch:{ Exception -> 0x0077 }
        r4.put(r2, r5);	 Catch:{ Exception -> 0x0077 }
        r2 = "task_extra";
        r5 = 0;
        r0 = r3.replace(r2, r5, r4);	 Catch:{ Exception -> 0x0077 }
        r0 = (int) r0;
        r0 = (long) r0;
    L_0x0054:
        monitor-exit(r9);	 Catch:{ all -> 0x0056 }
        goto L_0x000b;
    L_0x0056:
        r0 = move-exception;
        monitor-exit(r9);	 Catch:{ all -> 0x0056 }
        throw r0;	 Catch:{ all -> 0x0059 }
    L_0x0059:
        r0 = move-exception;
        monitor-exit(r9);
        throw r0;
    L_0x005c:
        r2 = move-exception;
        r2.printStackTrace();	 Catch:{ all -> 0x0056 }
        goto L_0x003c;
    L_0x0061:
        r2 = "task_extra";
        r5 = " task_id=? ";
        r6 = 1;
        r6 = new java.lang.String[r6];	 Catch:{ Exception -> 0x0077 }
        r7 = 0;
        r8 = java.lang.String.valueOf(r10);	 Catch:{ Exception -> 0x0077 }
        r6[r7] = r8;	 Catch:{ Exception -> 0x0077 }
        r0 = r3.update(r2, r4, r5, r6);	 Catch:{ Exception -> 0x0077 }
        r0 = (long) r0;
        goto L_0x0054;
    L_0x0077:
        r2 = move-exception;
        r2.printStackTrace();	 Catch:{ all -> 0x0056 }
        goto L_0x0054;
        */
    }

    public final synchronized int a(long... jArr) {
        int i = 0;
        synchronized (this) {
            try {
                synchronized (this) {
                    try {
                        SQLiteDatabase writableDatabase = getWritableDatabase();
                    } catch (Exception e) {
                        e.printStackTrace();
                        writableDatabase = null;
                    }
                    if (writableDatabase != null) {
                        writableDatabase.beginTransaction();
                        for (int i2 = 0; i2 <= 0; i2++) {
                            if (jArr[i2] > 0) {
                                try {
                                    if (writableDatabase.delete("task_extra", "task_id=?", new String[]{String.valueOf(jArr[i2])}) > 0) {
                                        i++;
                                    }
                                } catch (Exception e2) {
                                    e2.printStackTrace();
                                }
                            }
                        }
                        writableDatabase.setTransactionSuccessful();
                        writableDatabase.endTransaction();
                    }
                }
            } catch (Throwable th) {
            }
        }
        return i;
    }

    public final synchronized long b(long j) {
        long insert;
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            try {
                writableDatabase.beginTransaction();
                contentValues.put("task_id", Long.valueOf(j));
                if (c(j)) {
                    writableDatabase.delete("task_consume", " task_id=? ", new String[]{String.valueOf(j)});
                }
                insert = writableDatabase.insert("task_consume", null, contentValues);
                try {
                    writableDatabase.setTransactionSuccessful();
                    writableDatabase.endTransaction();
                    if (writableDatabase != null && writableDatabase.isOpen()) {
                        writableDatabase.close();
                    }
                } catch (SQLiteException e) {
                    SQLiteException e2 = e;
                    e2.printStackTrace();
                    writableDatabase.endTransaction();
                    writableDatabase.close();
                    return insert;
                }
            } catch (SQLiteException e3) {
                SQLiteException sQLiteException = e3;
                insert = -1;
                e2 = sQLiteException;
                e2.printStackTrace();
                writableDatabase.endTransaction();
                if (writableDatabase != null && writableDatabase.isOpen()) {
                    writableDatabase.close();
                }
                return insert;
            }
        } catch (Throwable th) {
        }
        return insert;
    }

    public final synchronized boolean c(long j) {
        Exception e;
        boolean z;
        Cursor cursor = null;
        synchronized (this) {
            try {
                Cursor query = getReadableDatabase().query("task_consume", null, new StringBuilder(" task_id=").append(j).toString(), null, null, null, null);
                if (query != null) {
                    try {
                    } catch (Exception e2) {
                        e = e2;
                        e.printStackTrace();
                        if (query == null) {
                            query.close();
                            z = false;
                        } else {
                            z = false;
                        }
                        return z;
                    }
                    if (query.moveToFirst()) {
                        z = true;
                        if (query != null) {
                            query.close();
                        }
                    }
                }
                z = false;
                if (query != null) {
                    query.close();
                }
            } catch (Exception e3) {
                e = e3;
                query = null;
                try {
                    e.printStackTrace();
                    if (query == null) {
                        z = false;
                    } else {
                        query.close();
                        z = false;
                    }
                } catch (Throwable th) {
                    Throwable th2 = th;
                    cursor = query;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th2;
                }
                return z;
            } catch (Throwable th3) {
                th2 = th3;
                if (cursor != null) {
                    cursor.close();
                }
                throw th2;
            }
        }
        return z;
    }

    public final synchronized int b(long... jArr) {
        int i = 0;
        synchronized (this) {
            try {
                synchronized (this) {
                    try {
                        SQLiteDatabase writableDatabase = getWritableDatabase();
                    } catch (Exception e) {
                        e.printStackTrace();
                        writableDatabase = null;
                    }
                    if (writableDatabase != null) {
                        writableDatabase.beginTransaction();
                        for (int i2 = 0; i2 <= 0; i2++) {
                            if (jArr[i2] > 0) {
                                try {
                                    if (writableDatabase.delete("task_consume", "task_id=?", new String[]{String.valueOf(jArr[i2])}) > 0) {
                                        i++;
                                    }
                                } catch (Exception e2) {
                                    e2.printStackTrace();
                                }
                            }
                        }
                        writableDatabase.setTransactionSuccessful();
                        writableDatabase.endTransaction();
                    }
                }
            } catch (Throwable th) {
            }
        }
        return i;
    }
}
