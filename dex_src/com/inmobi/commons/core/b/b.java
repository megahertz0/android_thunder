package com.inmobi.commons.core.b;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import com.alipay.sdk.util.h;
import com.inmobi.commons.a.a;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.ArrayList;
import java.util.List;

// compiled from: DbStore.java
public final class b {
    private static final String a;
    private static volatile b b;
    private static final Object c;
    private static final Object d;
    private static int e;
    private SQLiteDatabase f;

    static {
        a = b.class.getSimpleName();
        c = new Object();
        d = new Object();
        e = 0;
    }

    private b() {
        try {
            this.f = new a(a.b()).getWritableDatabase();
            b = this;
        } catch (Throwable e) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Problem while getting writable database connection.", e);
        }
    }

    public static synchronized b a() {
        b bVar;
        synchronized (b.class) {
            try {
                synchronized (d) {
                    e++;
                }
                bVar = b;
                if (bVar == null) {
                    synchronized (c) {
                        bVar = b;
                        if (bVar == null) {
                            bVar = new b();
                            b = bVar;
                        }
                    }
                }
            } catch (Throwable th) {
            }
        }
        return bVar;
    }

    public final synchronized void a(String str, ContentValues contentValues, String str2, String[] strArr) {
        if (!a(str, contentValues)) {
            b(str, contentValues, str2, strArr);
        }
    }

    public final synchronized boolean a(String str, ContentValues contentValues) {
        return this.f.insertWithOnConflict(str, null, contentValues, XZBDevice.DOWNLOAD_LIST_ALL) != -1;
    }

    public final synchronized int a(String str, String str2, String[] strArr) {
        return this.f.delete(str, str2, strArr);
    }

    public final synchronized int b(String str, ContentValues contentValues, String str2, String[] strArr) {
        return this.f.updateWithOnConflict(str, contentValues, str2, strArr, XZBDevice.DOWNLOAD_LIST_ALL);
    }

    public final synchronized List<ContentValues> a(String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6) {
        List<ContentValues> arrayList;
        Cursor query = this.f.query(str, strArr, str2, strArr2, str3, str4, str5, str6);
        arrayList = new ArrayList();
        if (query.moveToFirst()) {
            do {
                ContentValues contentValues = new ContentValues();
                DatabaseUtils.cursorRowToContentValues(query, contentValues);
                arrayList.add(contentValues);
            } while (query.moveToNext());
        }
        query.close();
        return arrayList;
    }

    public final synchronized int a(String str) {
        int i;
        Cursor rawQuery = this.f.rawQuery(new StringBuilder("SELECT COUNT(*) FROM ").append(str).append(" ; ").toString(), null);
        rawQuery.moveToFirst();
        i = rawQuery.getInt(0);
        rawQuery.close();
        return i;
    }

    public final synchronized int b(String str, String str2, String[] strArr) {
        int i;
        Cursor rawQuery = this.f.rawQuery(new StringBuilder("SELECT COUNT(*) FROM ").append(str).append(" WHERE ").append(str2).append(" ; ").toString(), strArr);
        rawQuery.moveToFirst();
        i = rawQuery.getInt(0);
        rawQuery.close();
        return i;
    }

    public final synchronized void a(String str, String str2) {
        this.f.execSQL(new StringBuilder("CREATE TABLE IF NOT EXISTS ").append(str).append(str2).append(h.b).toString());
    }

    public final synchronized void b() {
        try {
            synchronized (d) {
                int i = e - 1;
                e = i;
                if (i == 0) {
                    this.f.close();
                    b = null;
                }
            }
        } catch (Throwable th) {
        }
    }
}
