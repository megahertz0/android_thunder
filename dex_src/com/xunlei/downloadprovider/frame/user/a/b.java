package com.xunlei.downloadprovider.frame.user.a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import com.tencent.open.SocialConstants;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.downloadprovider.frame.user.ag;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.Calendar;
import org.android.agoo.message.MessageService;

// compiled from: UserCenterTaskDbHelper.java
public final class b extends SQLiteOpenHelper {
    private static b v;
    private final String a;
    private final String b;
    private final String c;
    private final String d;
    private final String e;
    private final String f;
    private final String g;
    private final String h;
    private final String i;
    private final String j;
    private final String k;
    private final String l;
    private final String m;
    private final String n;
    private final String o;
    private final String p;
    private final String q;
    private final String r;
    private final String s;
    private final String t;
    private final String u;

    // compiled from: UserCenterTaskDbHelper.java
    public static class a {
        public String a;
        public boolean b;
        public int c;
        public int d;
        public long e;
        public int f;

        public a() {
            this.a = com.umeng.a.d;
            this.c = 0;
            this.d = 0;
            this.e = -1;
            this.f = 0;
        }
    }

    private b(Context context) {
        super(context, "UserCenterTask.db", null, 4);
        this.a = "User_Center_Task_List";
        this.b = SocialConstants.PARAM_APPNAME;
        this.c = "downloadUrl";
        this.d = JsInterface.KEY_APK_NAME;
        this.e = "extId";
        this.f = "actState";
        this.g = "fileWholePath";
        this.h = "reportState";
        this.i = SocializeConstants.WEIBO_ID;
        this.j = "User_Vip_Continue_Tip_Table";
        this.k = "userId";
        this.l = "viplastTipTime";
        this.m = "tasklastTipTime";
        this.n = "extTip1";
        this.o = "extTip2";
        this.p = "User_Sign_Score_Table";
        this.q = "userId";
        this.r = "sign_day";
        this.s = "signed_day";
        this.t = "user_score";
        this.u = "unfinishNum";
    }

    public static b a(Context context) {
        if (v == null) {
            v = new b(context);
        }
        return v;
    }

    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        onCreate(sQLiteDatabase);
    }

    public final synchronized void a(ag agVar) {
        SQLiteException sQLiteException;
        Throwable th;
        SQLiteDatabase sQLiteDatabase = null;
        synchronized (this) {
            try {
                sQLiteDatabase = getWritableDatabase();
                try {
                    sQLiteDatabase.beginTransaction();
                    ContentValues contentValues = new ContentValues();
                    contentValues.clear();
                    contentValues.put(SocialConstants.PARAM_APPNAME, agVar.e);
                    contentValues.put("downloadUrl", agVar.b);
                    contentValues.put(JsInterface.KEY_APK_NAME, agVar.c);
                    contentValues.put("extId", agVar.a);
                    contentValues.put("actState", Integer.valueOf(agVar.f));
                    contentValues.put("fileWholePath", agVar.d);
                    contentValues.put("reportState", agVar.g);
                    sQLiteDatabase.insert("User_Center_Task_List", null, contentValues);
                    sQLiteDatabase.setTransactionSuccessful();
                    if (sQLiteDatabase != null) {
                        if (sQLiteDatabase.isOpen()) {
                            sQLiteDatabase.endTransaction();
                            sQLiteDatabase.close();
                        }
                    }
                } catch (SQLiteConstraintException e) {
                    if (sQLiteDatabase != null) {
                        if (sQLiteDatabase.isOpen()) {
                            sQLiteDatabase.endTransaction();
                            sQLiteDatabase.close();
                        }
                    }
                } catch (SQLiteException e2) {
                    SQLiteException sQLiteException2 = e2;
                    r1 = sQLiteDatabase;
                    sQLiteException = sQLiteException2;
                    sQLiteException.printStackTrace();
                    if (r1 != null) {
                        if (r1.isOpen()) {
                            r1.endTransaction();
                            r1.close();
                        }
                    }
                } catch (Throwable th2) {
                    Throwable th3 = th2;
                    r1 = sQLiteDatabase;
                    th = th3;
                    r1.endTransaction();
                    r1.close();
                    throw th;
                }
            } catch (SQLiteConstraintException e3) {
                if (sQLiteDatabase != null) {
                    if (sQLiteDatabase.isOpen()) {
                        sQLiteDatabase.endTransaction();
                        sQLiteDatabase.close();
                    }
                }
            } catch (SQLiteException e22) {
                sQLiteException2 = e22;
                r1 = null;
                sQLiteException = sQLiteException2;
                try {
                    SQLiteDatabase sQLiteDatabase2;
                    sQLiteException.printStackTrace();
                    if (sQLiteDatabase2 != null) {
                        if (sQLiteDatabase2.isOpen()) {
                            sQLiteDatabase2.endTransaction();
                            sQLiteDatabase2.close();
                        }
                    }
                } catch (Throwable th4) {
                    th = th4;
                    sQLiteDatabase2.endTransaction();
                    sQLiteDatabase2.close();
                    throw th;
                }
            } catch (Throwable th22) {
                th3 = th22;
                sQLiteDatabase2 = null;
                th = th3;
                if (sQLiteDatabase2 != null && sQLiteDatabase2.isOpen()) {
                    sQLiteDatabase2.endTransaction();
                    sQLiteDatabase2.close();
                }
                throw th;
            }
        }
    }

    public final synchronized void a(String str) {
        SQLiteDatabase sQLiteDatabase = null;
        try {
            sQLiteDatabase = getWritableDatabase();
            sQLiteDatabase.beginTransaction();
            sQLiteDatabase.delete("User_Center_Task_List", "packageName = ?", new String[]{str});
            sQLiteDatabase.setTransactionSuccessful();
            if (sQLiteDatabase != null) {
                if (sQLiteDatabase.isOpen()) {
                    sQLiteDatabase.endTransaction();
                    sQLiteDatabase.close();
                }
            }
        } catch (Throwable th) {
        }
    }

    public final synchronized void a(String str, String str2) {
        SQLiteDatabase sQLiteDatabase = null;
        try {
            sQLiteDatabase = getWritableDatabase();
            sQLiteDatabase.beginTransaction();
            ContentValues contentValues = new ContentValues();
            contentValues.put("reportState", str2);
            sQLiteDatabase.update("User_Center_Task_List", contentValues, "packageName = ?", new String[]{str});
            sQLiteDatabase.setTransactionSuccessful();
            if (sQLiteDatabase != null) {
                if (sQLiteDatabase.isOpen()) {
                    sQLiteDatabase.endTransaction();
                    sQLiteDatabase.close();
                }
            }
        } catch (Throwable th) {
        }
    }

    public final synchronized void b(String str, String str2) {
        SQLiteDatabase sQLiteDatabase = null;
        try {
            sQLiteDatabase = getWritableDatabase();
            sQLiteDatabase.beginTransaction();
            ContentValues contentValues = new ContentValues();
            contentValues.put("fileWholePath", str2);
            sQLiteDatabase.update("User_Center_Task_List", contentValues, "appName = ?", new String[]{str});
            sQLiteDatabase.setTransactionSuccessful();
            if (sQLiteDatabase != null) {
                if (sQLiteDatabase.isOpen()) {
                    sQLiteDatabase.endTransaction();
                    sQLiteDatabase.close();
                }
            }
        } catch (Throwable th) {
        }
    }

    public final synchronized ag b(String str) {
        Cursor query;
        ag agVar;
        SQLiteException e;
        SQLiteDatabase sQLiteDatabase;
        ag agVar2;
        Cursor cursor = null;
        synchronized (this) {
            try {
                SQLiteDatabase readableDatabase = getReadableDatabase();
                try {
                    ag agVar3;
                    readableDatabase.beginTransaction();
                    query = readableDatabase.query("User_Center_Task_List", null, "packageName = ?", new String[]{str}, null, null, null);
                    Cursor cursor2 = null;
                    readableDatabase.setTransactionSuccessful();
                    if (query != null) {
                        if (!query.isClosed()) {
                            query.close();
                        }
                    }
                    if (readableDatabase == null) {
                    }
                    agVar = agVar3;
                } catch (SQLiteException e2) {
                    e = e2;
                    sQLiteDatabase = readableDatabase;
                    agVar = null;
                    e.printStackTrace();
                    if (cursor != null) {
                        if (!cursor.isClosed()) {
                            cursor.close();
                        }
                    }
                    sQLiteDatabase.endTransaction();
                    sQLiteDatabase.close();
                    return agVar;
                } catch (Throwable th) {
                    query = null;
                    sQLiteDatabase = readableDatabase;
                    Throwable th2 = th;
                    if (query != null) {
                        if (!query.isClosed()) {
                            query.close();
                        }
                    }
                    sQLiteDatabase.endTransaction();
                    sQLiteDatabase.close();
                    throw th2;
                }
                if (query != null) {
                    try {
                    } catch (SQLiteException e3) {
                        e = e3;
                        sQLiteDatabase = readableDatabase;
                        agVar = null;
                        cursor = query;
                        e.printStackTrace();
                        if (cursor != null) {
                            if (cursor.isClosed()) {
                                cursor.close();
                            }
                        }
                        sQLiteDatabase.endTransaction();
                        sQLiteDatabase.close();
                        return agVar;
                    } catch (Throwable th3) {
                        sQLiteDatabase = readableDatabase;
                        th2 = th3;
                        if (query != null) {
                            if (query.isClosed()) {
                                query.close();
                            }
                        }
                        sQLiteDatabase.endTransaction();
                        sQLiteDatabase.close();
                        throw th2;
                    }
                    if (query.moveToFirst()) {
                        ag agVar4 = new ag();
                        try {
                            agVar4.e = query.getString(query.getColumnIndex(SocialConstants.PARAM_APPNAME));
                            agVar4.b = query.getString(query.getColumnIndex("downloadUrl"));
                            agVar4.c = query.getString(query.getColumnIndex(JsInterface.KEY_APK_NAME));
                            agVar4.a = query.getString(query.getColumnIndex("extId"));
                            agVar4.f = query.getInt(query.getColumnIndex("actState"));
                            agVar4.d = query.getString(query.getColumnIndex("fileWholePath"));
                            agVar4.g = query.getString(query.getColumnIndex("reportState"));
                            agVar3 = agVar4;
                        } catch (SQLiteException e4) {
                            e = e4;
                            cursor = query;
                            agVar2 = agVar4;
                            sQLiteDatabase = readableDatabase;
                            agVar = agVar2;
                            e.printStackTrace();
                            if (cursor != null) {
                                if (cursor.isClosed()) {
                                    cursor.close();
                                }
                            }
                            if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                                sQLiteDatabase.endTransaction();
                                sQLiteDatabase.close();
                            }
                            return agVar;
                        } catch (Throwable th32) {
                            sQLiteDatabase = readableDatabase;
                            th2 = th32;
                            if (query != null) {
                                if (query.isClosed()) {
                                    query.close();
                                }
                            }
                            sQLiteDatabase.endTransaction();
                            sQLiteDatabase.close();
                            throw th2;
                        }
                        try {
                            readableDatabase.setTransactionSuccessful();
                            if (query != null) {
                                if (query.isClosed()) {
                                    query.close();
                                }
                            }
                            if (readableDatabase == null && readableDatabase.isOpen()) {
                                readableDatabase.endTransaction();
                                readableDatabase.close();
                                agVar = agVar3;
                            } else {
                                agVar = agVar3;
                            }
                        } catch (SQLiteException e5) {
                            cursor = query;
                            agVar2 = agVar3;
                            e = e5;
                            sQLiteDatabase = readableDatabase;
                            agVar = agVar2;
                            e.printStackTrace();
                            if (cursor != null) {
                                if (cursor.isClosed()) {
                                    cursor.close();
                                }
                            }
                            sQLiteDatabase.endTransaction();
                            sQLiteDatabase.close();
                            return agVar;
                        } catch (Throwable th322) {
                            sQLiteDatabase = readableDatabase;
                            th2 = th322;
                            if (query != null) {
                                if (query.isClosed()) {
                                    query.close();
                                }
                            }
                            sQLiteDatabase.endTransaction();
                            sQLiteDatabase.close();
                            throw th2;
                        }
                    }
                }
            } catch (SQLiteException e6) {
                e = e6;
                sQLiteDatabase = null;
                agVar = null;
                try {
                    e.printStackTrace();
                    if (cursor != null) {
                        if (cursor.isClosed()) {
                            cursor.close();
                        }
                    }
                    sQLiteDatabase.endTransaction();
                    sQLiteDatabase.close();
                } catch (Throwable th4) {
                    th2 = th4;
                    query = cursor;
                    if (query != null) {
                        if (query.isClosed()) {
                            query.close();
                        }
                    }
                    sQLiteDatabase.endTransaction();
                    sQLiteDatabase.close();
                    throw th2;
                }
                return agVar;
            } catch (Throwable th5) {
                th2 = th5;
                query = null;
                sQLiteDatabase = null;
                if (query != null) {
                    if (query.isClosed()) {
                        query.close();
                    }
                }
                sQLiteDatabase.endTransaction();
                sQLiteDatabase.close();
                throw th2;
            }
        }
        return agVar;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized java.util.List<com.xunlei.downloadprovider.frame.user.ag> a() {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.downloadprovider.frame.user.a.b.a():java.util.List<com.xunlei.downloadprovider.frame.user.ag>");
        /*
        this = this;
        r8 = 0;
        monitor-enter(r11);
        r9 = new java.util.ArrayList;	 Catch:{ all -> 0x00c6 }
        r9.<init>();	 Catch:{ all -> 0x00c6 }
        r0 = r11.getReadableDatabase();	 Catch:{ SQLiteException -> 0x0100, all -> 0x00d3 }
        r0.beginTransaction();	 Catch:{ SQLiteException -> 0x0103, all -> 0x00f4 }
        r1 = "User_Center_Task_List";
        r2 = 0;
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r2 = r0.query(r1, r2, r3, r4, r5, r6, r7);	 Catch:{ SQLiteException -> 0x0103, all -> 0x00f4 }
        if (r2 == 0) goto L_0x00a9;
    L_0x001d:
        r1 = r2.moveToNext();	 Catch:{ SQLiteException -> 0x0087, all -> 0x00f9 }
        if (r1 == 0) goto L_0x00a9;
    L_0x0023:
        r1 = new com.xunlei.downloadprovider.frame.user.ag;	 Catch:{ SQLiteException -> 0x0087, all -> 0x00f9 }
        r1.<init>();	 Catch:{ SQLiteException -> 0x0087, all -> 0x00f9 }
        r3 = "appName";
        r3 = r2.getColumnIndex(r3);	 Catch:{ SQLiteException -> 0x0087, all -> 0x00f9 }
        r3 = r2.getString(r3);	 Catch:{ SQLiteException -> 0x0087, all -> 0x00f9 }
        r1.e = r3;	 Catch:{ SQLiteException -> 0x0087, all -> 0x00f9 }
        r3 = "downloadUrl";
        r3 = r2.getColumnIndex(r3);	 Catch:{ SQLiteException -> 0x0087, all -> 0x00f9 }
        r3 = r2.getString(r3);	 Catch:{ SQLiteException -> 0x0087, all -> 0x00f9 }
        r1.b = r3;	 Catch:{ SQLiteException -> 0x0087, all -> 0x00f9 }
        r3 = "packageName";
        r3 = r2.getColumnIndex(r3);	 Catch:{ SQLiteException -> 0x0087, all -> 0x00f9 }
        r3 = r2.getString(r3);	 Catch:{ SQLiteException -> 0x0087, all -> 0x00f9 }
        r1.c = r3;	 Catch:{ SQLiteException -> 0x0087, all -> 0x00f9 }
        r3 = "extId";
        r3 = r2.getColumnIndex(r3);	 Catch:{ SQLiteException -> 0x0087, all -> 0x00f9 }
        r3 = r2.getString(r3);	 Catch:{ SQLiteException -> 0x0087, all -> 0x00f9 }
        r1.a = r3;	 Catch:{ SQLiteException -> 0x0087, all -> 0x00f9 }
        r3 = "actState";
        r3 = r2.getColumnIndex(r3);	 Catch:{ SQLiteException -> 0x0087, all -> 0x00f9 }
        r3 = r2.getInt(r3);	 Catch:{ SQLiteException -> 0x0087, all -> 0x00f9 }
        r1.f = r3;	 Catch:{ SQLiteException -> 0x0087, all -> 0x00f9 }
        r3 = "fileWholePath";
        r3 = r2.getColumnIndex(r3);	 Catch:{ SQLiteException -> 0x0087, all -> 0x00f9 }
        r3 = r2.getString(r3);	 Catch:{ SQLiteException -> 0x0087, all -> 0x00f9 }
        r1.d = r3;	 Catch:{ SQLiteException -> 0x0087, all -> 0x00f9 }
        r3 = "reportState";
        r3 = r2.getColumnIndex(r3);	 Catch:{ SQLiteException -> 0x0087, all -> 0x00f9 }
        r3 = r2.getString(r3);	 Catch:{ SQLiteException -> 0x0087, all -> 0x00f9 }
        r1.g = r3;	 Catch:{ SQLiteException -> 0x0087, all -> 0x00f9 }
        r9.add(r1);	 Catch:{ SQLiteException -> 0x0087, all -> 0x00f9 }
        goto L_0x001d;
    L_0x0087:
        r1 = move-exception;
        r8 = r0;
        r0 = r1;
        r1 = r2;
    L_0x008b:
        r0.printStackTrace();	 Catch:{ all -> 0x00fd }
        if (r1 == 0) goto L_0x0099;
    L_0x0090:
        r0 = r1.isClosed();	 Catch:{ all -> 0x00c6 }
        if (r0 != 0) goto L_0x0099;
    L_0x0096:
        r1.close();	 Catch:{ all -> 0x00c6 }
    L_0x0099:
        if (r8 == 0) goto L_0x00a7;
    L_0x009b:
        r0 = r8.isOpen();	 Catch:{ all -> 0x00c6 }
        if (r0 == 0) goto L_0x00a7;
    L_0x00a1:
        r8.endTransaction();	 Catch:{ Exception -> 0x00ce }
    L_0x00a4:
        r8.close();	 Catch:{ all -> 0x00c6 }
    L_0x00a7:
        monitor-exit(r11);
        return r9;
    L_0x00a9:
        r0.setTransactionSuccessful();	 Catch:{ SQLiteException -> 0x0087, all -> 0x00f9 }
        if (r2 == 0) goto L_0x00b7;
    L_0x00ae:
        r1 = r2.isClosed();	 Catch:{ all -> 0x00c6 }
        if (r1 != 0) goto L_0x00b7;
    L_0x00b4:
        r2.close();	 Catch:{ all -> 0x00c6 }
    L_0x00b7:
        if (r0 == 0) goto L_0x00a7;
    L_0x00b9:
        r1 = r0.isOpen();	 Catch:{ all -> 0x00c6 }
        if (r1 == 0) goto L_0x00a7;
    L_0x00bf:
        r0.endTransaction();	 Catch:{ Exception -> 0x00c9 }
    L_0x00c2:
        r0.close();	 Catch:{ all -> 0x00c6 }
        goto L_0x00a7;
    L_0x00c6:
        r0 = move-exception;
        monitor-exit(r11);
        throw r0;
    L_0x00c9:
        r1 = move-exception;
        r1.printStackTrace();	 Catch:{ all -> 0x00c6 }
        goto L_0x00c2;
    L_0x00ce:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x00c6 }
        goto L_0x00a4;
    L_0x00d3:
        r0 = move-exception;
        r2 = r8;
    L_0x00d5:
        if (r2 == 0) goto L_0x00e0;
    L_0x00d7:
        r1 = r2.isClosed();	 Catch:{ all -> 0x00c6 }
        if (r1 != 0) goto L_0x00e0;
    L_0x00dd:
        r2.close();	 Catch:{ all -> 0x00c6 }
    L_0x00e0:
        if (r8 == 0) goto L_0x00ee;
    L_0x00e2:
        r1 = r8.isOpen();	 Catch:{ all -> 0x00c6 }
        if (r1 == 0) goto L_0x00ee;
    L_0x00e8:
        r8.endTransaction();	 Catch:{ Exception -> 0x00ef }
    L_0x00eb:
        r8.close();	 Catch:{ all -> 0x00c6 }
    L_0x00ee:
        throw r0;	 Catch:{ all -> 0x00c6 }
    L_0x00ef:
        r1 = move-exception;
        r1.printStackTrace();	 Catch:{ all -> 0x00c6 }
        goto L_0x00eb;
    L_0x00f4:
        r1 = move-exception;
        r2 = r8;
        r8 = r0;
        r0 = r1;
        goto L_0x00d5;
    L_0x00f9:
        r1 = move-exception;
        r8 = r0;
        r0 = r1;
        goto L_0x00d5;
    L_0x00fd:
        r0 = move-exception;
        r2 = r1;
        goto L_0x00d5;
    L_0x0100:
        r0 = move-exception;
        r1 = r8;
        goto L_0x008b;
    L_0x0103:
        r1 = move-exception;
        r10 = r1;
        r1 = r8;
        r8 = r0;
        r0 = r10;
        goto L_0x008b;
        */
    }

    public final int c(String str) {
        SQLiteException e;
        Cursor cursor;
        SQLiteDatabase sQLiteDatabase;
        int i;
        SQLiteDatabase sQLiteDatabase2;
        Cursor cursor2 = null;
        try {
            int i2;
            SQLiteDatabase readableDatabase = getReadableDatabase();
            try {
                readableDatabase.beginTransaction();
                Cursor query = readableDatabase.query("User_Center_Task_List", null, "packageName = ?", new String[]{str}, null, null, null);
                Object obj = null;
                readableDatabase.setTransactionSuccessful();
                query.close();
                if (readableDatabase != null) {
                }
                return i2;
            } catch (SQLiteException e2) {
                e = e2;
                cursor = null;
                sQLiteDatabase = readableDatabase;
                i = 0;
                e.printStackTrace();
                cursor.close();
                return sQLiteDatabase == null ? i : i;
            } catch (Throwable th) {
                Throwable th2 = th;
                sQLiteDatabase2 = readableDatabase;
                Throwable th3 = th2;
                cursor2.close();
                sQLiteDatabase2.endTransaction();
                sQLiteDatabase2.close();
                throw th3;
            }
            if (query != null) {
                try {
                } catch (SQLiteException e3) {
                    e = e3;
                    cursor = query;
                    sQLiteDatabase = readableDatabase;
                    i = 0;
                    e.printStackTrace();
                    cursor.close();
                    if (sQLiteDatabase == null) {
                    }
                } catch (Throwable th4) {
                    cursor2 = query;
                    th2 = th4;
                    sQLiteDatabase2 = readableDatabase;
                    th3 = th2;
                    cursor2.close();
                    sQLiteDatabase2.endTransaction();
                    sQLiteDatabase2.close();
                    throw th3;
                }
                if (query.moveToFirst()) {
                    i2 = query.getInt(query.getColumnIndex("actState"));
                    try {
                        readableDatabase.setTransactionSuccessful();
                        if (!(query == null || query.isClosed())) {
                            query.close();
                        }
                        if (readableDatabase != null || !readableDatabase.isOpen()) {
                            return i2;
                        }
                        readableDatabase.endTransaction();
                        readableDatabase.close();
                        return i2;
                    } catch (SQLiteException e4) {
                        sQLiteDatabase = readableDatabase;
                        i = i2;
                        e = e4;
                        cursor = query;
                        e.printStackTrace();
                        cursor.close();
                        if (sQLiteDatabase == null) {
                        }
                    } catch (Throwable th42) {
                        cursor2 = query;
                        th2 = th42;
                        sQLiteDatabase2 = readableDatabase;
                        th3 = th2;
                        cursor2.close();
                        sQLiteDatabase2.endTransaction();
                        sQLiteDatabase2.close();
                        throw th3;
                    }
                }
            }
        } catch (SQLiteException e5) {
            e = e5;
            cursor = null;
            i = 0;
            try {
                e.printStackTrace();
                if (!(cursor == null || cursor.isClosed())) {
                    cursor.close();
                }
                if (sQLiteDatabase == null && sQLiteDatabase.isOpen()) {
                    sQLiteDatabase.endTransaction();
                    sQLiteDatabase.close();
                    return i;
                }
            } catch (Throwable th5) {
                th3 = th5;
                sQLiteDatabase2 = sQLiteDatabase;
                cursor2 = cursor;
                cursor2.close();
                sQLiteDatabase2.endTransaction();
                sQLiteDatabase2.close();
                throw th3;
            }
        } catch (Throwable th6) {
            th3 = th6;
            sQLiteDatabase2 = null;
            if (!(cursor2 == null || cursor2.isClosed())) {
                cursor2.close();
            }
            if (sQLiteDatabase2 != null && sQLiteDatabase2.isOpen()) {
                sQLiteDatabase2.endTransaction();
                sQLiteDatabase2.close();
            }
            throw th3;
        }
    }

    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS User_Center_Task_List");
        StringBuilder stringBuilder = new StringBuilder("CREATE TABLE IF NOT EXISTS ");
        stringBuilder.append("User_Center_Task_List( id INTEGER PRIMARY KEY AUTOINCREMENT, appName,downloadUrl,packageName,extId,actState, fileWholePath, reportState )");
        sQLiteDatabase.execSQL(stringBuilder.toString());
        stringBuilder = new StringBuilder("CREATE TABLE IF NOT EXISTS ");
        stringBuilder.append("User_Vip_Continue_Tip_Table(id INTEGER PRIMARY KEY AUTOINCREMENT, userId TEXT , viplastTipTime LONG , tasklastTipTime LONG , extTip1 TEXT , extTip2 TEXT )");
        sQLiteDatabase.execSQL(stringBuilder.toString());
        a(sQLiteDatabase);
    }

    private static void a(SQLiteDatabase sQLiteDatabase) {
        StringBuilder stringBuilder = new StringBuilder("CREATE TABLE IF NOT EXISTS ");
        stringBuilder.append("User_Sign_Score_Table(_ID INTEGER PRIMARY KEY AUTOINCREMENT, userId TEXT , sign_day LONG , signed_day INTEGER , user_score INTEGER , unfinishNum INTEGER )");
        sQLiteDatabase.execSQL(stringBuilder.toString());
    }

    public final boolean d(String str) {
        SQLiteException e;
        Cursor cursor;
        SQLiteDatabase sQLiteDatabase;
        boolean z;
        Exception e2;
        SQLiteDatabase sQLiteDatabase2;
        Cursor cursor2 = null;
        try {
            boolean a;
            SQLiteDatabase readableDatabase = getReadableDatabase();
            try {
                readableDatabase.beginTransaction();
                Cursor query = readableDatabase.query("User_Vip_Continue_Tip_Table", null, "userId=?", new String[]{str}, null, null, null);
                Object obj = null;
                readableDatabase.setTransactionSuccessful();
                query.close();
                if (readableDatabase != null) {
                }
                return a;
            } catch (SQLiteException e3) {
                e = e3;
                cursor = null;
                sQLiteDatabase = readableDatabase;
                z = false;
                e.printStackTrace();
                cursor.close();
                return sQLiteDatabase == null ? z : z;
            } catch (Exception e4) {
                e2 = e4;
                sQLiteDatabase2 = readableDatabase;
                z = false;
                e2.printStackTrace();
                cursor2.close();
                return sQLiteDatabase2 == null ? z : z;
            } catch (Throwable th) {
                sQLiteDatabase2 = readableDatabase;
                Throwable th2 = th;
                cursor2.close();
                sQLiteDatabase2.endTransaction();
                sQLiteDatabase2.close();
                throw th2;
            }
            if (query != null) {
                try {
                } catch (SQLiteException e5) {
                    e = e5;
                    cursor = query;
                    sQLiteDatabase = readableDatabase;
                    z = false;
                    e.printStackTrace();
                    cursor.close();
                    if (sQLiteDatabase == null) {
                    }
                } catch (Exception e6) {
                    e2 = e6;
                    cursor2 = query;
                    sQLiteDatabase2 = readableDatabase;
                    z = false;
                    e2.printStackTrace();
                    cursor2.close();
                    if (sQLiteDatabase2 == null) {
                    }
                } catch (Throwable th3) {
                    cursor2 = query;
                    sQLiteDatabase2 = readableDatabase;
                    th2 = th3;
                    cursor2.close();
                    sQLiteDatabase2.endTransaction();
                    sQLiteDatabase2.close();
                    throw th2;
                }
                if (query.moveToNext()) {
                    a = com.xunlei.downloadprovider.d.b.a(System.currentTimeMillis(), query.getLong(XZBDevice.DOWNLOAD_LIST_RECYCLE));
                    try {
                        readableDatabase.setTransactionSuccessful();
                        if (!(query == null || query.isClosed())) {
                            query.close();
                        }
                        if (readableDatabase != null || !readableDatabase.isOpen()) {
                            return a;
                        }
                        readableDatabase.endTransaction();
                        readableDatabase.close();
                        return a;
                    } catch (SQLiteException e7) {
                        sQLiteDatabase = readableDatabase;
                        z = a;
                        e = e7;
                        cursor = query;
                        e.printStackTrace();
                        cursor.close();
                        if (sQLiteDatabase == null) {
                        }
                    } catch (Exception e8) {
                        cursor2 = query;
                        boolean z2 = a;
                        e2 = e8;
                        sQLiteDatabase2 = readableDatabase;
                        z = z2;
                        e2.printStackTrace();
                        cursor2.close();
                        if (sQLiteDatabase2 == null) {
                        }
                    } catch (Throwable th32) {
                        cursor2 = query;
                        sQLiteDatabase2 = readableDatabase;
                        th2 = th32;
                        cursor2.close();
                        sQLiteDatabase2.endTransaction();
                        sQLiteDatabase2.close();
                        throw th2;
                    }
                }
            }
        } catch (SQLiteException e9) {
            e = e9;
            cursor = null;
            z = false;
            try {
                e.printStackTrace();
                if (!(cursor == null || cursor.isClosed())) {
                    cursor.close();
                }
                if (sQLiteDatabase == null && sQLiteDatabase.isOpen()) {
                    sQLiteDatabase.endTransaction();
                    sQLiteDatabase.close();
                    return z;
                }
            } catch (Throwable th4) {
                th2 = th4;
                Cursor cursor3 = cursor;
                sQLiteDatabase2 = sQLiteDatabase;
                cursor2 = cursor3;
                cursor2.close();
                sQLiteDatabase2.endTransaction();
                sQLiteDatabase2.close();
                throw th2;
            }
        } catch (Exception e10) {
            e2 = e10;
            sQLiteDatabase2 = null;
            z = false;
            try {
                e2.printStackTrace();
                if (!(cursor2 == null || cursor2.isClosed())) {
                    cursor2.close();
                }
                if (sQLiteDatabase2 == null && sQLiteDatabase2.isOpen()) {
                    sQLiteDatabase2.endTransaction();
                    sQLiteDatabase2.close();
                    return z;
                }
            } catch (Throwable th5) {
                th2 = th5;
                cursor2.close();
                sQLiteDatabase2.endTransaction();
                sQLiteDatabase2.close();
                throw th2;
            }
        } catch (Throwable th6) {
            th2 = th6;
            sQLiteDatabase2 = null;
            if (!(cursor2 == null || cursor2.isClosed())) {
                cursor2.close();
            }
            if (sQLiteDatabase2 != null && sQLiteDatabase2.isOpen()) {
                sQLiteDatabase2.endTransaction();
                sQLiteDatabase2.close();
            }
            throw th2;
        }
    }

    public final boolean e(String str) {
        boolean a;
        SQLiteException e;
        Cursor cursor;
        boolean z;
        Exception e2;
        SQLiteDatabase sQLiteDatabase = null;
        try {
            Cursor query;
            SQLiteDatabase readableDatabase = getReadableDatabase();
            try {
                readableDatabase.beginTransaction();
                query = readableDatabase.query("User_Vip_Continue_Tip_Table", null, "userId=?", new String[]{str}, null, null, null);
                Object obj = null;
                readableDatabase.setTransactionSuccessful();
                query.close();
                if (readableDatabase != null) {
                }
                return a;
            } catch (SQLiteException e3) {
                e = e3;
                cursor = null;
                sQLiteDatabase = readableDatabase;
                z = false;
                e.printStackTrace();
                cursor.close();
                return sQLiteDatabase == null ? z : z;
            } catch (Exception e4) {
                e2 = e4;
                query = null;
                sQLiteDatabase = readableDatabase;
                z = false;
                e2.printStackTrace();
                query.close();
                return sQLiteDatabase == null ? z : z;
            } catch (Throwable th) {
                query = null;
                sQLiteDatabase = readableDatabase;
                Throwable th2 = th;
                query.close();
                sQLiteDatabase.endTransaction();
                sQLiteDatabase.close();
                throw th2;
            }
            if (query != null) {
                try {
                } catch (SQLiteException e5) {
                    e = e5;
                    cursor = query;
                    sQLiteDatabase = readableDatabase;
                    z = false;
                    e.printStackTrace();
                    if (!(cursor == null || cursor.isClosed())) {
                        cursor.close();
                    }
                    if (sQLiteDatabase == null && sQLiteDatabase.isOpen()) {
                        sQLiteDatabase.endTransaction();
                        sQLiteDatabase.close();
                        return z;
                    }
                } catch (Exception e6) {
                    e2 = e6;
                    sQLiteDatabase = readableDatabase;
                    z = false;
                    e2.printStackTrace();
                    if (!(query == null || query.isClosed())) {
                        query.close();
                    }
                    if (sQLiteDatabase == null && sQLiteDatabase.isOpen()) {
                        sQLiteDatabase.endTransaction();
                        sQLiteDatabase.close();
                        return z;
                    }
                } catch (Throwable th3) {
                    sQLiteDatabase = readableDatabase;
                    th2 = th3;
                    query.close();
                    sQLiteDatabase.endTransaction();
                    sQLiteDatabase.close();
                    throw th2;
                }
                if (query.moveToNext()) {
                    a = com.xunlei.downloadprovider.d.b.a(System.currentTimeMillis(), Long.valueOf(query.getString(XZBDevice.DOWNLOAD_LIST_ALL)).longValue());
                    try {
                        readableDatabase.setTransactionSuccessful();
                        if (!(query == null || query.isClosed())) {
                            query.close();
                        }
                        if (readableDatabase != null || !readableDatabase.isOpen()) {
                            return a;
                        }
                        readableDatabase.endTransaction();
                        readableDatabase.close();
                        return a;
                    } catch (SQLiteException e7) {
                        sQLiteDatabase = readableDatabase;
                        z = a;
                        e = e7;
                        cursor = query;
                        e.printStackTrace();
                        cursor.close();
                        if (sQLiteDatabase == null) {
                        }
                    } catch (Exception e8) {
                        sQLiteDatabase = readableDatabase;
                        z = a;
                        e2 = e8;
                        e2.printStackTrace();
                        query.close();
                        if (sQLiteDatabase == null) {
                        }
                    } catch (Throwable th32) {
                        sQLiteDatabase = readableDatabase;
                        th2 = th32;
                        query.close();
                        sQLiteDatabase.endTransaction();
                        sQLiteDatabase.close();
                        throw th2;
                    }
                }
            }
        } catch (SQLiteException e9) {
            e = e9;
            cursor = null;
            z = false;
            try {
                e.printStackTrace();
                cursor.close();
                if (sQLiteDatabase == null) {
                }
            } catch (Throwable th4) {
                th2 = th4;
                query = cursor;
                query.close();
                sQLiteDatabase.endTransaction();
                sQLiteDatabase.close();
                throw th2;
            }
        } catch (Exception e10) {
            e2 = e10;
            query = null;
            z = false;
            try {
                e2.printStackTrace();
                query.close();
                if (sQLiteDatabase == null) {
                }
            } catch (Throwable th5) {
                th2 = th5;
                if (!(query == null || query.isClosed())) {
                    query.close();
                }
                if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                    sQLiteDatabase.endTransaction();
                    sQLiteDatabase.close();
                }
                throw th2;
            }
        } catch (Throwable th6) {
            th2 = th6;
            query = null;
            query.close();
            sQLiteDatabase.endTransaction();
            sQLiteDatabase.close();
            throw th2;
        }
    }

    public final synchronized a f(String str) {
        a aVar;
        Cursor query;
        Cursor cursor;
        Exception exception;
        SQLiteDatabase sQLiteDatabase = null;
        synchronized (this) {
            aVar = new a();
            try {
                SQLiteDatabase readableDatabase = getReadableDatabase();
                try {
                    query = readableDatabase.query("User_Sign_Score_Table", null, "userId=?", new String[]{str}, null, null, null);
                    if (query != null) {
                        if (!query.isClosed()) {
                            query.close();
                        }
                    }
                    if (readableDatabase != null && readableDatabase.isOpen()) {
                        readableDatabase.close();
                    }
                } catch (SQLiteException e) {
                    SQLiteException sQLiteException = e;
                    cursor = null;
                    sQLiteDatabase = readableDatabase;
                    r0 = sQLiteException;
                    r0.printStackTrace();
                    if (cursor != null) {
                        if (!cursor.isClosed()) {
                            cursor.close();
                        }
                    }
                    if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                        sQLiteDatabase.close();
                    }
                    return aVar;
                } catch (Exception e2) {
                    query = null;
                    sQLiteDatabase = readableDatabase;
                    exception = e2;
                    exception.printStackTrace();
                    if (query != null) {
                        if (!query.isClosed()) {
                            query.close();
                        }
                    }
                    if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                        sQLiteDatabase.close();
                    }
                    return aVar;
                } catch (Throwable th) {
                    query = null;
                    sQLiteDatabase = readableDatabase;
                    Throwable th2 = th;
                    if (!(query == null || query.isClosed())) {
                        query.close();
                    }
                    if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                        sQLiteDatabase.close();
                    }
                    throw th2;
                }
                if (query != null) {
                    try {
                        if (query.moveToNext()) {
                            aVar.a = query.getString(1);
                            long j = query.getLong(XZBDevice.DOWNLOAD_LIST_RECYCLE);
                            aVar.e = j;
                            aVar.d = query.getInt(XZBDevice.DOWNLOAD_LIST_FAILED);
                            aVar.c = query.getInt(XZBDevice.DOWNLOAD_LIST_ALL);
                            aVar.b = com.xunlei.downloadprovider.d.b.a(System.currentTimeMillis(), j);
                            aVar.f = query.getInt(XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
                        }
                    } catch (SQLiteException e3) {
                        sQLiteDatabase = readableDatabase;
                        r0 = e3;
                        cursor = query;
                        r0.printStackTrace();
                        if (cursor != null) {
                            if (cursor.isClosed()) {
                                cursor.close();
                            }
                        }
                        sQLiteDatabase.close();
                        return aVar;
                    } catch (Exception e22) {
                        sQLiteDatabase = readableDatabase;
                        exception = e22;
                        exception.printStackTrace();
                        if (query != null) {
                            if (query.isClosed()) {
                                query.close();
                            }
                        }
                        sQLiteDatabase.close();
                        return aVar;
                    } catch (Throwable th3) {
                        sQLiteDatabase = readableDatabase;
                        th2 = th3;
                        query.close();
                        sQLiteDatabase.close();
                        throw th2;
                    }
                }
            } catch (SQLiteException e4) {
                r0 = e4;
                cursor = null;
                try {
                    SQLiteException sQLiteException2;
                    sQLiteException2.printStackTrace();
                    if (cursor != null) {
                        if (cursor.isClosed()) {
                            cursor.close();
                        }
                    }
                    sQLiteDatabase.close();
                } catch (Throwable th4) {
                    th2 = th4;
                    query = cursor;
                    query.close();
                    sQLiteDatabase.close();
                    throw th2;
                }
                return aVar;
            } catch (Exception e5) {
                exception = e5;
                query = null;
                try {
                    exception.printStackTrace();
                    if (query != null) {
                        if (query.isClosed()) {
                            query.close();
                        }
                    }
                    sQLiteDatabase.close();
                } catch (Throwable th5) {
                    th2 = th5;
                    query.close();
                    sQLiteDatabase.close();
                    throw th2;
                }
                return aVar;
            } catch (Throwable th6) {
                th2 = th6;
                query = null;
                query.close();
                sQLiteDatabase.close();
                throw th2;
            }
        }
        return aVar;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void a(java.lang.String r12, int r13, long r14) {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.downloadprovider.frame.user.a.b.a(java.lang.String, int, long):void");
        /*
        this = this;
        monitor-enter(r11);
        r0 = android.text.TextUtils.isEmpty(r12);	 Catch:{ all -> 0x00a6 }
        if (r0 == 0) goto L_0x0009;
    L_0x0007:
        monitor-exit(r11);
        return;
    L_0x0009:
        r8 = 0;
        r1 = 0;
        r0 = r11.getWritableDatabase();	 Catch:{ Exception -> 0x0170, all -> 0x0168 }
        r1 = "User_Sign_Score_Table";
        r2 = 0;
        r3 = "userId = ?";
        r4 = 1;
        r4 = new java.lang.String[r4];	 Catch:{ Exception -> 0x0176 }
        r5 = 0;
        r4[r5] = r12;	 Catch:{ Exception -> 0x0176 }
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r2 = r0.query(r1, r2, r3, r4, r5, r6, r7);	 Catch:{ Exception -> 0x0176 }
        r1 = 0;
        if (r2 == 0) goto L_0x002d;
    L_0x0026:
        r3 = r2.moveToFirst();	 Catch:{ Exception -> 0x00b1, all -> 0x016a }
        if (r3 == 0) goto L_0x002d;
    L_0x002c:
        r1 = 1;
    L_0x002d:
        r3 = new android.content.ContentValues;	 Catch:{ Exception -> 0x00b1, all -> 0x016a }
        r3.<init>();	 Catch:{ Exception -> 0x00b1, all -> 0x016a }
        r3.clear();	 Catch:{ Exception -> 0x00b1, all -> 0x016a }
        r4 = "userId";
        r3.put(r4, r12);	 Catch:{ Exception -> 0x00b1, all -> 0x016a }
        r4 = "sign_day";
        r5 = java.lang.Long.valueOf(r14);	 Catch:{ Exception -> 0x00b1, all -> 0x016a }
        r3.put(r4, r5);	 Catch:{ Exception -> 0x00b1, all -> 0x016a }
        r4 = "signed_day";
        r5 = java.lang.Integer.valueOf(r13);	 Catch:{ Exception -> 0x00b1, all -> 0x016a }
        r3.put(r4, r5);	 Catch:{ Exception -> 0x00b1, all -> 0x016a }
        if (r1 == 0) goto L_0x00a9;
    L_0x0051:
        r1 = "user_score";
        r4 = 4;
        r4 = r2.getInt(r4);	 Catch:{ Exception -> 0x00b1, all -> 0x016a }
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ Exception -> 0x00b1, all -> 0x016a }
        r3.put(r1, r4);	 Catch:{ Exception -> 0x00b1, all -> 0x016a }
        r1 = "unfinishNum";
        r4 = 5;
        r4 = r2.getInt(r4);	 Catch:{ Exception -> 0x00b1, all -> 0x016a }
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ Exception -> 0x00b1, all -> 0x016a }
        r3.put(r1, r4);	 Catch:{ Exception -> 0x00b1, all -> 0x016a }
        r1 = "User_Sign_Score_Table";
        r4 = "userId = ?";
        r5 = 1;
        r5 = new java.lang.String[r5];	 Catch:{ Exception -> 0x00b1, all -> 0x016a }
        r6 = 0;
        r5[r6] = r12;	 Catch:{ Exception -> 0x00b1, all -> 0x016a }
        r0.update(r1, r3, r4, r5);	 Catch:{ Exception -> 0x00b1, all -> 0x016a }
    L_0x007e:
        r1 = com.xunlei.downloadprovider.app.BrothersApplication.a();	 Catch:{ Exception -> 0x00b1, all -> 0x016a }
        r1 = r1.getApplicationContext();	 Catch:{ Exception -> 0x00b1, all -> 0x016a }
        r3 = b();	 Catch:{ Exception -> 0x00b1, all -> 0x016a }
        r4 = 1;
        com.xunlei.downloadprovider.util.aa.a(r1, r3, r4);	 Catch:{ Exception -> 0x00b1, all -> 0x016a }
        if (r2 == 0) goto L_0x0099;
    L_0x0090:
        r1 = r2.isClosed();	 Catch:{ all -> 0x00a6 }
        if (r1 != 0) goto L_0x0099;
    L_0x0096:
        r2.close();	 Catch:{ all -> 0x00a6 }
    L_0x0099:
        if (r0 == 0) goto L_0x0007;
    L_0x009b:
        r1 = r0.isOpen();	 Catch:{ all -> 0x00a6 }
        if (r1 == 0) goto L_0x0007;
    L_0x00a1:
        r0.close();	 Catch:{ all -> 0x00a6 }
        goto L_0x0007;
    L_0x00a6:
        r0 = move-exception;
        monitor-exit(r11);
        throw r0;
    L_0x00a9:
        r1 = "User_Sign_Score_Table";
        r4 = 0;
        r0.insert(r1, r4, r3);	 Catch:{ Exception -> 0x00b1, all -> 0x016a }
        goto L_0x007e;
    L_0x00b1:
        r1 = move-exception;
        r8 = r2;
    L_0x00b3:
        r1.printStackTrace();	 Catch:{ all -> 0x014d }
        a(r0);	 Catch:{ all -> 0x014d }
        r1 = "User_Sign_Score_Table";
        r2 = 0;
        r3 = "userId = ?";
        r4 = 1;
        r4 = new java.lang.String[r4];	 Catch:{ Exception -> 0x0148 }
        r5 = 0;
        r4[r5] = r12;	 Catch:{ Exception -> 0x0148 }
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = r0.query(r1, r2, r3, r4, r5, r6, r7);	 Catch:{ Exception -> 0x0148 }
        r1 = 0;
        if (r8 == 0) goto L_0x00d7;
    L_0x00d0:
        r2 = r8.moveToFirst();	 Catch:{ Exception -> 0x0148 }
        if (r2 == 0) goto L_0x00d7;
    L_0x00d6:
        r1 = 1;
    L_0x00d7:
        r2 = new android.content.ContentValues;	 Catch:{ Exception -> 0x0148 }
        r2.<init>();	 Catch:{ Exception -> 0x0148 }
        r2.clear();	 Catch:{ Exception -> 0x0148 }
        r3 = "userId";
        r2.put(r3, r12);	 Catch:{ Exception -> 0x0148 }
        r3 = "sign_day";
        r4 = java.lang.Long.valueOf(r14);	 Catch:{ Exception -> 0x0148 }
        r2.put(r3, r4);	 Catch:{ Exception -> 0x0148 }
        r3 = "signed_day";
        r4 = java.lang.Integer.valueOf(r13);	 Catch:{ Exception -> 0x0148 }
        r2.put(r3, r4);	 Catch:{ Exception -> 0x0148 }
        if (r1 == 0) goto L_0x0140;
    L_0x00fb:
        r1 = "user_score";
        r3 = 4;
        r3 = r8.getInt(r3);	 Catch:{ Exception -> 0x0148 }
        r3 = java.lang.Integer.valueOf(r3);	 Catch:{ Exception -> 0x0148 }
        r2.put(r1, r3);	 Catch:{ Exception -> 0x0148 }
        r1 = "unfinishNum";
        r3 = 5;
        r3 = r8.getInt(r3);	 Catch:{ Exception -> 0x0148 }
        r3 = java.lang.Integer.valueOf(r3);	 Catch:{ Exception -> 0x0148 }
        r2.put(r1, r3);	 Catch:{ Exception -> 0x0148 }
        r1 = "User_Sign_Score_Table";
        r3 = "userId = ?";
        r4 = 1;
        r4 = new java.lang.String[r4];	 Catch:{ Exception -> 0x0148 }
        r5 = 0;
        r4[r5] = r12;	 Catch:{ Exception -> 0x0148 }
        r0.update(r1, r2, r3, r4);	 Catch:{ Exception -> 0x0148 }
    L_0x0128:
        if (r8 == 0) goto L_0x0133;
    L_0x012a:
        r1 = r8.isClosed();	 Catch:{ all -> 0x00a6 }
        if (r1 != 0) goto L_0x0133;
    L_0x0130:
        r8.close();	 Catch:{ all -> 0x00a6 }
    L_0x0133:
        if (r0 == 0) goto L_0x0007;
    L_0x0135:
        r1 = r0.isOpen();	 Catch:{ all -> 0x00a6 }
        if (r1 == 0) goto L_0x0007;
    L_0x013b:
        r0.close();	 Catch:{ all -> 0x00a6 }
        goto L_0x0007;
    L_0x0140:
        r1 = "User_Sign_Score_Table";
        r3 = 0;
        r0.insert(r1, r3, r2);	 Catch:{ Exception -> 0x0148 }
        goto L_0x0128;
    L_0x0148:
        r1 = move-exception;
        r1.printStackTrace();	 Catch:{ all -> 0x014d }
        goto L_0x0128;
    L_0x014d:
        r1 = move-exception;
        r9 = r1;
        r1 = r0;
        r0 = r9;
    L_0x0151:
        if (r8 == 0) goto L_0x015c;
    L_0x0153:
        r2 = r8.isClosed();	 Catch:{ all -> 0x00a6 }
        if (r2 != 0) goto L_0x015c;
    L_0x0159:
        r8.close();	 Catch:{ all -> 0x00a6 }
    L_0x015c:
        if (r1 == 0) goto L_0x0167;
    L_0x015e:
        r2 = r1.isOpen();	 Catch:{ all -> 0x00a6 }
        if (r2 == 0) goto L_0x0167;
    L_0x0164:
        r1.close();	 Catch:{ all -> 0x00a6 }
    L_0x0167:
        throw r0;	 Catch:{ all -> 0x00a6 }
    L_0x0168:
        r0 = move-exception;
        goto L_0x0151;
    L_0x016a:
        r1 = move-exception;
        r8 = r2;
        r9 = r0;
        r0 = r1;
        r1 = r9;
        goto L_0x0151;
    L_0x0170:
        r0 = move-exception;
        r9 = r0;
        r0 = r1;
        r1 = r9;
        goto L_0x00b3;
    L_0x0176:
        r1 = move-exception;
        goto L_0x00b3;
        */
    }

    public static String b() {
        String toString;
        int i = Calendar.getInstance().get(XZBDevice.DOWNLOAD_LIST_RECYCLE) + 1;
        if (i < 10) {
            toString = new StringBuilder(MessageService.MSG_DB_READY_REPORT).append(i).toString();
        } else {
            toString = String.valueOf(i);
        }
        return LoginHelper.a().j + (Calendar.getInstance().get(1) + toString + Calendar.getInstance().get(XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void a(java.lang.String r13, int r14) {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.downloadprovider.frame.user.a.b.a(java.lang.String, int):void");
        /*
        this = this;
        r9 = 1;
        r10 = 0;
        r8 = 0;
        monitor-enter(r12);
        r0 = android.text.TextUtils.isEmpty(r13);	 Catch:{ all -> 0x0097 }
        if (r0 == 0) goto L_0x000c;
    L_0x000a:
        monitor-exit(r12);
        return;
    L_0x000c:
        r0 = r12.getWritableDatabase();	 Catch:{ Exception -> 0x0162, all -> 0x0159 }
        r1 = "User_Sign_Score_Table";
        r2 = 0;
        r3 = "userId = ?";
        r4 = 1;
        r4 = new java.lang.String[r4];	 Catch:{ Exception -> 0x0167 }
        r5 = 0;
        r4[r5] = r13;	 Catch:{ Exception -> 0x0167 }
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r2 = r0.query(r1, r2, r3, r4, r5, r6, r7);	 Catch:{ Exception -> 0x0167 }
        if (r2 == 0) goto L_0x016d;
    L_0x0026:
        r1 = r2.moveToFirst();	 Catch:{ Exception -> 0x00a2, all -> 0x015c }
        if (r1 == 0) goto L_0x016d;
    L_0x002c:
        r1 = r9;
    L_0x002d:
        r3 = new android.content.ContentValues;	 Catch:{ Exception -> 0x00a2, all -> 0x015c }
        r3.<init>();	 Catch:{ Exception -> 0x00a2, all -> 0x015c }
        r3.clear();	 Catch:{ Exception -> 0x00a2, all -> 0x015c }
        r4 = "userId";
        r3.put(r4, r13);	 Catch:{ Exception -> 0x00a2, all -> 0x015c }
        r4 = "user_score";
        r5 = java.lang.Integer.valueOf(r14);	 Catch:{ Exception -> 0x00a2, all -> 0x015c }
        r3.put(r4, r5);	 Catch:{ Exception -> 0x00a2, all -> 0x015c }
        if (r1 == 0) goto L_0x009a;
    L_0x0047:
        r1 = "sign_day";
        r4 = 2;
        r4 = r2.getLong(r4);	 Catch:{ Exception -> 0x00a2, all -> 0x015c }
        r4 = java.lang.Long.valueOf(r4);	 Catch:{ Exception -> 0x00a2, all -> 0x015c }
        r3.put(r1, r4);	 Catch:{ Exception -> 0x00a2, all -> 0x015c }
        r1 = "signed_day";
        r4 = 3;
        r4 = r2.getString(r4);	 Catch:{ Exception -> 0x00a2, all -> 0x015c }
        r3.put(r1, r4);	 Catch:{ Exception -> 0x00a2, all -> 0x015c }
        r1 = "unfinishNum";
        r4 = 5;
        r4 = r2.getInt(r4);	 Catch:{ Exception -> 0x00a2, all -> 0x015c }
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ Exception -> 0x00a2, all -> 0x015c }
        r3.put(r1, r4);	 Catch:{ Exception -> 0x00a2, all -> 0x015c }
        r1 = "User_Sign_Score_Table";
        r4 = "userId=?";
        r5 = 1;
        r5 = new java.lang.String[r5];	 Catch:{ Exception -> 0x00a2, all -> 0x015c }
        r6 = 0;
        r5[r6] = r13;	 Catch:{ Exception -> 0x00a2, all -> 0x015c }
        r0.update(r1, r3, r4, r5);	 Catch:{ Exception -> 0x00a2, all -> 0x015c }
    L_0x007f:
        if (r2 == 0) goto L_0x008a;
    L_0x0081:
        r1 = r2.isClosed();	 Catch:{ all -> 0x0097 }
        if (r1 != 0) goto L_0x008a;
    L_0x0087:
        r2.close();	 Catch:{ all -> 0x0097 }
    L_0x008a:
        if (r0 == 0) goto L_0x000a;
    L_0x008c:
        r1 = r0.isOpen();	 Catch:{ all -> 0x0097 }
        if (r1 == 0) goto L_0x000a;
    L_0x0092:
        r0.close();	 Catch:{ all -> 0x0097 }
        goto L_0x000a;
    L_0x0097:
        r0 = move-exception;
        monitor-exit(r12);
        throw r0;
    L_0x009a:
        r1 = "User_Sign_Score_Table";
        r4 = 0;
        r0.insert(r1, r4, r3);	 Catch:{ Exception -> 0x00a2, all -> 0x015c }
        goto L_0x007f;
    L_0x00a2:
        r1 = move-exception;
        r8 = r2;
    L_0x00a4:
        r1.printStackTrace();	 Catch:{ all -> 0x013e }
        a(r0);	 Catch:{ all -> 0x013e }
        r1 = "User_Sign_Score_Table";
        r2 = 0;
        r3 = "userId = ?";
        r4 = 1;
        r4 = new java.lang.String[r4];	 Catch:{ Exception -> 0x0139 }
        r5 = 0;
        r4[r5] = r13;	 Catch:{ Exception -> 0x0139 }
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = r0.query(r1, r2, r3, r4, r5, r6, r7);	 Catch:{ Exception -> 0x0139 }
        if (r8 == 0) goto L_0x016a;
    L_0x00c0:
        r1 = r8.moveToFirst();	 Catch:{ Exception -> 0x0139 }
        if (r1 == 0) goto L_0x016a;
    L_0x00c6:
        r1 = r9;
    L_0x00c7:
        r2 = new android.content.ContentValues;	 Catch:{ Exception -> 0x0139 }
        r2.<init>();	 Catch:{ Exception -> 0x0139 }
        r2.clear();	 Catch:{ Exception -> 0x0139 }
        r3 = "userId";
        r2.put(r3, r13);	 Catch:{ Exception -> 0x0139 }
        r3 = "user_score";
        r4 = java.lang.Integer.valueOf(r14);	 Catch:{ Exception -> 0x0139 }
        r2.put(r3, r4);	 Catch:{ Exception -> 0x0139 }
        if (r1 == 0) goto L_0x0131;
    L_0x00e1:
        r1 = "sign_day";
        r3 = 2;
        r4 = r8.getLong(r3);	 Catch:{ Exception -> 0x0139 }
        r3 = java.lang.Long.valueOf(r4);	 Catch:{ Exception -> 0x0139 }
        r2.put(r1, r3);	 Catch:{ Exception -> 0x0139 }
        r1 = "signed_day";
        r3 = 3;
        r3 = r8.getString(r3);	 Catch:{ Exception -> 0x0139 }
        r2.put(r1, r3);	 Catch:{ Exception -> 0x0139 }
        r1 = "unfinishNum";
        r3 = 5;
        r3 = r8.getInt(r3);	 Catch:{ Exception -> 0x0139 }
        r3 = java.lang.Integer.valueOf(r3);	 Catch:{ Exception -> 0x0139 }
        r2.put(r1, r3);	 Catch:{ Exception -> 0x0139 }
        r1 = "User_Sign_Score_Table";
        r3 = "userId=?";
        r4 = 1;
        r4 = new java.lang.String[r4];	 Catch:{ Exception -> 0x0139 }
        r5 = 0;
        r4[r5] = r13;	 Catch:{ Exception -> 0x0139 }
        r0.update(r1, r2, r3, r4);	 Catch:{ Exception -> 0x0139 }
    L_0x0119:
        if (r8 == 0) goto L_0x0124;
    L_0x011b:
        r1 = r8.isClosed();	 Catch:{ all -> 0x0097 }
        if (r1 != 0) goto L_0x0124;
    L_0x0121:
        r8.close();	 Catch:{ all -> 0x0097 }
    L_0x0124:
        if (r0 == 0) goto L_0x000a;
    L_0x0126:
        r1 = r0.isOpen();	 Catch:{ all -> 0x0097 }
        if (r1 == 0) goto L_0x000a;
    L_0x012c:
        r0.close();	 Catch:{ all -> 0x0097 }
        goto L_0x000a;
    L_0x0131:
        r1 = "User_Sign_Score_Table";
        r3 = 0;
        r0.insert(r1, r3, r2);	 Catch:{ Exception -> 0x0139 }
        goto L_0x0119;
    L_0x0139:
        r1 = move-exception;
        r1.printStackTrace();	 Catch:{ all -> 0x013e }
        goto L_0x0119;
    L_0x013e:
        r1 = move-exception;
        r11 = r1;
        r1 = r0;
        r0 = r11;
    L_0x0142:
        if (r8 == 0) goto L_0x014d;
    L_0x0144:
        r2 = r8.isClosed();	 Catch:{ all -> 0x0097 }
        if (r2 != 0) goto L_0x014d;
    L_0x014a:
        r8.close();	 Catch:{ all -> 0x0097 }
    L_0x014d:
        if (r1 == 0) goto L_0x0158;
    L_0x014f:
        r2 = r1.isOpen();	 Catch:{ all -> 0x0097 }
        if (r2 == 0) goto L_0x0158;
    L_0x0155:
        r1.close();	 Catch:{ all -> 0x0097 }
    L_0x0158:
        throw r0;	 Catch:{ all -> 0x0097 }
    L_0x0159:
        r0 = move-exception;
        r1 = r8;
        goto L_0x0142;
    L_0x015c:
        r1 = move-exception;
        r8 = r2;
        r11 = r1;
        r1 = r0;
        r0 = r11;
        goto L_0x0142;
    L_0x0162:
        r0 = move-exception;
        r1 = r0;
        r0 = r8;
        goto L_0x00a4;
    L_0x0167:
        r1 = move-exception;
        goto L_0x00a4;
    L_0x016a:
        r1 = r10;
        goto L_0x00c7;
    L_0x016d:
        r1 = r10;
        goto L_0x002d;
        */
    }
}
