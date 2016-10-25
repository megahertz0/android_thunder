package com.xunlei.downloadprovider.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.widget.Toast;
import com.sina.weibo.sdk.register.mobile.SelectCountryActivity;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.member.login.a.a;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.xllib.b.d;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.android.agoo.message.MessageService;

// compiled from: RelativeRecSqlHelper.java
public final class i extends SQLiteOpenHelper {
    private static i a;

    public i() {
        this(BrothersApplication.a.getApplicationContext(), "xlrelativerec.db");
    }

    private i(Context context, String str) {
        super(context, str, null, 67);
        File databasePath = context.getDatabasePath(str);
        if (!databasePath.exists() || !databasePath.isFile()) {
            a.a(context);
        }
    }

    public static synchronized i a() {
        i iVar;
        synchronized (i.class) {
            if (a == null) {
                a = new i();
            }
            iVar = a;
        }
        return iVar;
    }

    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table if not exists collected_website (_id integer primary key autoincrement, imgurl text, name text, type integer default 1, weburl text not null);");
        sQLiteDatabase.execSQL("create table if not exists booked_sitcom_uc (_id integer primary key autoincrement, id text not null, imgurl text, url text not null, name text, current integer default 0, end integer default 0, progress text, detail text);");
        sQLiteDatabase.execSQL("create table if not exists thundertab_res (_id integer primary key autoincrement, url text, recom_time INTEGER default 0, priority INTEGER default 0, img text, name text, title text, newcontent integer default 0, type INTEGER default 0, pkg text, ver text, ontop integer default 0, refreshtime INTEGER default 0, refreshmode INTEGER default 0, descri text);");
        sQLiteDatabase.execSQL("create table if not exists thundertab_site (siteid text not null primary key, url text, img text, title text, bigtitle text, name text, recom_time INTEGER default 0, priority INTEGER default 0, newcontent integer default 0, ontop integer default 0, refreshtime INTEGER default 0, refreshmode INTEGER default 0, status INTEGER default 1);");
        sQLiteDatabase.execSQL("create table if not exists thundertab_site_del (id text not null, type integer default 0, op_time INTEGER default 0, recom_time INTEGER default 0);");
        sQLiteDatabase.execSQL("create table if not exists thundertab_pushch_del (id text not null primary key, type integer default 0, extra text not null);");
    }

    public final void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i == 66) {
            a.a(BrothersApplication.a.getApplicationContext());
            onCreate(sQLiteDatabase);
            return;
        }
        a.a(BrothersApplication.a.getApplicationContext());
        sQLiteDatabase.execSQL("drop table if exists thundertab_res");
        sQLiteDatabase.execSQL("drop table if exists thundertab_site");
        sQLiteDatabase.execSQL("drop table if exists collected_website");
        sQLiteDatabase.execSQL("drop table if exists thundertab_site_del");
        sQLiteDatabase.execSQL("drop table if exists thundertab_pushch_del");
        sQLiteDatabase.execSQL("drop table if exists booked_sitcom_uc");
        onCreate(sQLiteDatabase);
    }

    public final synchronized List<b> b() {
        List arrayList;
        arrayList = new ArrayList();
        SQLiteDatabase readableDatabase = getReadableDatabase();
        Cursor query = readableDatabase.query("collected_website", null, null, null, null, null, "_id desc");
        if (query != null) {
            if (query.moveToFirst()) {
                int columnIndex = query.getColumnIndex("imgurl");
                int columnIndex2 = query.getColumnIndex(SelectCountryActivity.EXTRA_COUNTRY_NAME);
                int columnIndex3 = query.getColumnIndex("weburl");
                int columnIndex4 = query.getColumnIndex(JsInterface.FUNPLAY_AD_TRPE);
                do {
                    b bVar = new b();
                    bVar.a = query.getString(columnIndex);
                    bVar.b = query.getString(columnIndex2);
                    bVar.c = query.getString(columnIndex3);
                    bVar.d = query.getInt(columnIndex4);
                    arrayList.add(bVar);
                } while (query.moveToNext());
            }
            query.close();
        }
        readableDatabase.close();
        return arrayList;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized com.xunlei.downloadprovider.model.b a(java.lang.String r12) {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.downloadprovider.model.i.a(java.lang.String):com.xunlei.downloadprovider.model.b");
        /*
        this = this;
        r9 = 0;
        monitor-enter(r11);
        if (r12 == 0) goto L_0x008a;
    L_0x0004:
        r0 = r12.length();	 Catch:{ Exception -> 0x0075 }
        if (r0 <= 0) goto L_0x008a;
    L_0x000a:
        r0 = r11.getReadableDatabase();	 Catch:{ Exception -> 0x0075 }
        r1 = "collected_website";
        r2 = 0;
        r3 = "weburl=?";
        r4 = 1;
        r4 = new java.lang.String[r4];	 Catch:{ Exception -> 0x0075 }
        r5 = 0;
        r4[r5] = r12;	 Catch:{ Exception -> 0x0075 }
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = "1";
        r8 = java.lang.String.valueOf(r8);	 Catch:{ Exception -> 0x0075 }
        r2 = r0.query(r1, r2, r3, r4, r5, r6, r7, r8);	 Catch:{ Exception -> 0x0075 }
        if (r2 == 0) goto L_0x0088;
    L_0x002b:
        r1 = r2.moveToFirst();	 Catch:{ Exception -> 0x0075 }
        if (r1 == 0) goto L_0x006b;
    L_0x0031:
        r1 = "imgurl";
        r3 = r2.getColumnIndex(r1);	 Catch:{ Exception -> 0x0075 }
        r1 = "name";
        r4 = r2.getColumnIndex(r1);	 Catch:{ Exception -> 0x0075 }
        r1 = "weburl";
        r5 = r2.getColumnIndex(r1);	 Catch:{ Exception -> 0x0075 }
        r1 = "type";
        r6 = r2.getColumnIndex(r1);	 Catch:{ Exception -> 0x0075 }
        r1 = new com.xunlei.downloadprovider.model.b;	 Catch:{ Exception -> 0x0075 }
        r1.<init>();	 Catch:{ Exception -> 0x0075 }
        r3 = r2.getString(r3);	 Catch:{ Exception -> 0x007f }
        r1.a = r3;	 Catch:{ Exception -> 0x007f }
        r3 = r2.getString(r4);	 Catch:{ Exception -> 0x007f }
        r1.b = r3;	 Catch:{ Exception -> 0x007f }
        r3 = r2.getString(r5);	 Catch:{ Exception -> 0x007f }
        r1.c = r3;	 Catch:{ Exception -> 0x007f }
        r3 = r2.getInt(r6);	 Catch:{ Exception -> 0x007f }
        r1.d = r3;	 Catch:{ Exception -> 0x007f }
        r9 = r1;
    L_0x006b:
        r2.close();	 Catch:{ Exception -> 0x0084 }
        r1 = r9;
    L_0x006f:
        r0.close();	 Catch:{ Exception -> 0x007f }
        r0 = r1;
    L_0x0073:
        monitor-exit(r11);
        return r0;
    L_0x0075:
        r0 = move-exception;
        r1 = r0;
        r0 = r9;
    L_0x0078:
        r1.printStackTrace();	 Catch:{ all -> 0x007c }
        goto L_0x0073;
    L_0x007c:
        r0 = move-exception;
        monitor-exit(r11);
        throw r0;
    L_0x007f:
        r0 = move-exception;
        r10 = r0;
        r0 = r1;
        r1 = r10;
        goto L_0x0078;
    L_0x0084:
        r0 = move-exception;
        r1 = r0;
        r0 = r9;
        goto L_0x0078;
    L_0x0088:
        r1 = r9;
        goto L_0x006f;
    L_0x008a:
        r0 = r9;
        goto L_0x0073;
        */
    }

    public final synchronized boolean a(b bVar, SQLiteDatabase sQLiteDatabase) {
        boolean z;
        SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
        Cursor query = sQLiteDatabase2.query("collected_website", null, "weburl=?", new String[]{bVar.c}, null, null, null, null);
        if (query != null) {
            if (query.moveToFirst()) {
                z = true;
            } else {
                z = false;
            }
            query.close();
        } else {
            z = false;
        }
        return z;
    }

    public final synchronized boolean a(b bVar) {
        Object obj = 1;
        boolean z = false;
        synchronized (this) {
            if (bVar != null) {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put("imgurl", bVar.a);
                contentValues.put(SelectCountryActivity.EXTRA_COUNTRY_NAME, bVar.b);
                contentValues.put("weburl", bVar.c);
                contentValues.put(JsInterface.FUNPLAY_AD_TRPE, Integer.valueOf(bVar.d));
                writableDatabase.delete("collected_website", "weburl=?", new String[]{bVar.c});
                Cursor rawQuery = writableDatabase.rawQuery("SELECT COUNT(*) FROM collected_website", null);
                rawQuery.moveToNext();
                int i = rawQuery.getInt(0);
                rawQuery.close();
                if (i >= 100) {
                    Toast.makeText(BrothersApplication.a().getApplicationContext(), "\u6536\u85cf\u5931\u8d25\uff0c\u6570\u91cf\u5df2\u8fbe\u4e0a\u9650", 0).show();
                } else {
                    boolean z2;
                    if (writableDatabase.insert("collected_website", null, contentValues) == -1) {
                        z2 = false;
                    }
                    z = z2;
                }
                writableDatabase.close();
            }
        }
        return z;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(java.util.ArrayList<com.xunlei.downloadprovider.model.b> r12) {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.downloadprovider.model.i.a(java.util.ArrayList):void");
        /*
        this = this;
        r1 = 0;
        r0 = r12.isEmpty();
        if (r0 != 0) goto L_0x00e4;
    L_0x0007:
        r0 = r11.getWritableDatabase();	 Catch:{ Exception -> 0x00ea }
        r0.beginTransaction();	 Catch:{ Exception -> 0x00ea }
        r2 = r1;
    L_0x000f:
        r1 = r12.size();	 Catch:{ Exception -> 0x00ea }
        if (r2 >= r1) goto L_0x0071;
    L_0x0015:
        r3 = new android.content.ContentValues;	 Catch:{ Exception -> 0x00ea }
        r3.<init>();	 Catch:{ Exception -> 0x00ea }
        r4 = "imgurl";
        r1 = r12.get(r2);	 Catch:{ Exception -> 0x00ea }
        r1 = (com.xunlei.downloadprovider.model.b) r1;	 Catch:{ Exception -> 0x00ea }
        r1 = r1.a;	 Catch:{ Exception -> 0x00ea }
        r3.put(r4, r1);	 Catch:{ Exception -> 0x00ea }
        r4 = "name";
        r1 = r12.get(r2);	 Catch:{ Exception -> 0x00ea }
        r1 = (com.xunlei.downloadprovider.model.b) r1;	 Catch:{ Exception -> 0x00ea }
        r1 = r1.b;	 Catch:{ Exception -> 0x00ea }
        r3.put(r4, r1);	 Catch:{ Exception -> 0x00ea }
        r4 = "weburl";
        r1 = r12.get(r2);	 Catch:{ Exception -> 0x00ea }
        r1 = (com.xunlei.downloadprovider.model.b) r1;	 Catch:{ Exception -> 0x00ea }
        r1 = r1.c;	 Catch:{ Exception -> 0x00ea }
        r3.put(r4, r1);	 Catch:{ Exception -> 0x00ea }
        r1 = "type";
        r4 = 0;
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ Exception -> 0x00ea }
        r3.put(r1, r4);	 Catch:{ Exception -> 0x00ea }
        r4 = "collected_website";
        r5 = "weburl=?";
        r1 = 1;
        r6 = new java.lang.String[r1];	 Catch:{ Exception -> 0x00ea }
        r7 = 0;
        r1 = r12.get(r2);	 Catch:{ Exception -> 0x00ea }
        r1 = (com.xunlei.downloadprovider.model.b) r1;	 Catch:{ Exception -> 0x00ea }
        r1 = r1.c;	 Catch:{ Exception -> 0x00ea }
        r6[r7] = r1;	 Catch:{ Exception -> 0x00ea }
        r0.delete(r4, r5, r6);	 Catch:{ Exception -> 0x00ea }
        r1 = "collected_website";
        r4 = 0;
        r0.insert(r1, r4, r3);	 Catch:{ Exception -> 0x00ea }
        r1 = r2 + 1;
        r2 = r1;
        goto L_0x000f;
    L_0x0071:
        r0.setTransactionSuccessful();	 Catch:{ Exception -> 0x00ea }
        r0.endTransaction();	 Catch:{ Exception -> 0x00ea }
        r1 = "SELECT COUNT(*) FROM collected_website";
        r2 = 0;
        r1 = r0.rawQuery(r1, r2);	 Catch:{ Exception -> 0x00e5 }
        r1.moveToNext();	 Catch:{ Exception -> 0x00e5 }
        r2 = 0;
        r2 = r1.getInt(r2);	 Catch:{ Exception -> 0x00e5 }
        r1.close();	 Catch:{ Exception -> 0x00e5 }
        r1 = 100;
        if (r2 <= r1) goto L_0x00e1;
    L_0x008e:
        r8 = r2 + -100;
        r1 = "collected_website";
        r2 = 0;
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r9 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00e5 }
        r10 = "0,";
        r9.<init>(r10);	 Catch:{ Exception -> 0x00e5 }
        r8 = r9.append(r8);	 Catch:{ Exception -> 0x00e5 }
        r8 = r8.toString();	 Catch:{ Exception -> 0x00e5 }
        r1 = r0.query(r1, r2, r3, r4, r5, r6, r7, r8);	 Catch:{ Exception -> 0x00e5 }
        if (r1 == 0) goto L_0x00e1;
    L_0x00af:
        r2 = r1.moveToFirst();	 Catch:{ Exception -> 0x00e5 }
        if (r2 == 0) goto L_0x00de;
    L_0x00b5:
        r2 = new com.xunlei.downloadprovider.model.b;	 Catch:{ Exception -> 0x00e5 }
        r2.<init>();	 Catch:{ Exception -> 0x00e5 }
        r3 = "weburl";
        r3 = r1.getColumnIndex(r3);	 Catch:{ Exception -> 0x00e5 }
        r3 = r1.getString(r3);	 Catch:{ Exception -> 0x00e5 }
        r2.c = r3;	 Catch:{ Exception -> 0x00e5 }
        r3 = "collected_website";
        r4 = "weburl=?";
        r5 = 1;
        r5 = new java.lang.String[r5];	 Catch:{ Exception -> 0x00e5 }
        r6 = 0;
        r2 = r2.c;	 Catch:{ Exception -> 0x00e5 }
        r5[r6] = r2;	 Catch:{ Exception -> 0x00e5 }
        r0.delete(r3, r4, r5);	 Catch:{ Exception -> 0x00e5 }
        r2 = r1.moveToNext();	 Catch:{ Exception -> 0x00e5 }
        if (r2 != 0) goto L_0x00b5;
    L_0x00de:
        r1.close();	 Catch:{ Exception -> 0x00e5 }
    L_0x00e1:
        r0.close();	 Catch:{ Exception -> 0x00ea }
    L_0x00e4:
        return;
    L_0x00e5:
        r1 = move-exception;
        r1.printStackTrace();	 Catch:{ Exception -> 0x00ea }
        goto L_0x00e1;
    L_0x00ea:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x00e4;
        */
    }

    public final synchronized boolean a(List<b> list) {
        a.a().c.getSharedPreferences(a.a, 0).edit().putBoolean(a.b, false).commit();
        if (!(list == null || list.isEmpty())) {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            writableDatabase.delete("collected_website", "type=?", new String[]{MessageService.MSG_DB_NOTIFY_REACHED});
            int size = list.size();
            ContentValues contentValues = new ContentValues();
            for (int i = size - 1; i >= 0; i--) {
                boolean z;
                b bVar = (b) list.get(i);
                if (a(bVar, writableDatabase)) {
                    z = false;
                } else {
                    int i2 = 1;
                }
                if (z) {
                    contentValues.put("imgurl", bVar.a);
                    contentValues.put(SelectCountryActivity.EXTRA_COUNTRY_NAME, bVar.b);
                    contentValues.put("weburl", bVar.c);
                    contentValues.put(JsInterface.FUNPLAY_AD_TRPE, Integer.valueOf(bVar.d));
                    writableDatabase.insert("collected_website", null, contentValues);
                    contentValues.clear();
                }
            }
            writableDatabase.close();
        }
        return false;
    }

    public final synchronized boolean b(String str) {
        boolean z = false;
        synchronized (this) {
            if (str != null) {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                if (writableDatabase.delete("collected_website", "weburl=?", new String[]{str}) != 0) {
                    int i = 1;
                }
                writableDatabase.close();
            }
        }
        return z;
    }

    public final synchronized void b(List<String> list) {
        SQLiteDatabase sQLiteDatabase;
        try {
            if (!d.a(list)) {
                sQLiteDatabase = null;
                try {
                    sQLiteDatabase = getWritableDatabase();
                    sQLiteDatabase.beginTransaction();
                    for (String str : list) {
                        if (!TextUtils.isEmpty(str)) {
                            sQLiteDatabase.delete("collected_website", "weburl=?", new String[]{(String) r2.next()});
                        }
                    }
                    sQLiteDatabase.setTransactionSuccessful();
                    if (sQLiteDatabase != null) {
                        if (sQLiteDatabase.isOpen()) {
                            sQLiteDatabase.endTransaction();
                            sQLiteDatabase.close();
                        }
                    }
                } catch (SQLiteException e) {
                    e.printStackTrace();
                    if (sQLiteDatabase != null) {
                        if (sQLiteDatabase.isOpen()) {
                            sQLiteDatabase.endTransaction();
                            sQLiteDatabase.close();
                        }
                    }
                }
            }
        } catch (Throwable th) {
        }
    }

    public final synchronized void c() {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.delete("collected_website", null, null);
        writableDatabase.close();
    }

    public final synchronized void a(int i, String str) {
        SQLiteDatabase sQLiteDatabase;
        if (str != null) {
            try {
                if (!com.umeng.a.d.equals(str)) {
                    sQLiteDatabase = null;
                    try {
                        sQLiteDatabase = getWritableDatabase();
                        ContentValues contentValues = new ContentValues();
                        contentValues.put(Impl.COLUMN_STATUS, Integer.valueOf(i));
                        sQLiteDatabase.update("thundertab_site", contentValues, " url=? ", new String[]{str});
                        if (sQLiteDatabase != null) {
                            if (sQLiteDatabase.isOpen()) {
                                sQLiteDatabase.close();
                            }
                        }
                    } catch (SQLiteException e) {
                        e.printStackTrace();
                        if (sQLiteDatabase != null) {
                            if (sQLiteDatabase.isOpen()) {
                                sQLiteDatabase.close();
                            }
                        }
                    }
                }
            } catch (Throwable th) {
            }
        }
    }

    public final int d() {
        int i = 0;
        try {
            Cursor rawQuery = getWritableDatabase().rawQuery("SELECT COUNT(*) FROM collected_website", null);
            rawQuery.moveToNext();
            i = rawQuery.getInt(0);
            rawQuery.close();
            return i;
        } catch (Exception e) {
            return i;
        }
    }
}
