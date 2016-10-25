package com.xunlei.downloadprovider.service.downloads.task.b;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.sina.weibo.sdk.register.mobile.SelectCountryActivity;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.xiazaibao.sdk.XZBDevice;

@Deprecated
// compiled from: CounterDatabase.java
public final class a extends SQLiteOpenHelper {
    private static a a;
    private SQLiteDatabase b;

    public static a a() {
        if (a == null) {
            a = new a(BrothersApplication.a.getApplicationContext(), "couter.db");
        }
        return a;
    }

    private a(Context context, String str) {
        super(context, str, null, 3);
        this.b = getWritableDatabase();
    }

    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table if not exists task_speed(_id integer primary key autoincrement, speed integer, size integer, name text, state integer, task_id integer, finished_time integer)");
    }

    public final synchronized c a(int i) {
        c cVar;
        Exception exception;
        try {
            Cursor query = this.b.query("task_speed", new String[]{"speed", "size", SelectCountryActivity.EXTRA_COUNTRY_NAME, "task_id", "finished_time", "state"}, "task_id = ?", new String[]{String.valueOf(i)}, null, null, "finished_time desc");
            if (query != null) {
                if (query.moveToFirst()) {
                    c cVar2 = new c();
                    try {
                        cVar2.a = query.getLong(0);
                        cVar2.c = query.getLong(1);
                        cVar2.b = query.getString(XZBDevice.DOWNLOAD_LIST_RECYCLE);
                        cVar2.g = (long) query.getInt(XZBDevice.DOWNLOAD_LIST_FAILED);
                        cVar2.f = query.getLong(XZBDevice.DOWNLOAD_LIST_ALL);
                        cVar2.d = query.getInt(XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
                        cVar = cVar2;
                    } catch (Exception e) {
                        Exception exception2 = e;
                        cVar = cVar2;
                        exception = exception2;
                        exception.printStackTrace();
                        return cVar;
                    }
                }
                Object obj = null;
                try {
                    query.close();
                } catch (Exception e2) {
                    exception = e2;
                    exception.printStackTrace();
                    return cVar;
                }
            }
            cVar = null;
        } catch (Exception e3) {
            exception = e3;
            cVar = null;
            exception.printStackTrace();
            return cVar;
        }
        return cVar;
    }

    public final synchronized void b() {
        try {
            getWritableDatabase().delete("task_speed", null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS task_speed");
        onCreate(sQLiteDatabase);
    }

    public final void close() {
        if (this.b != null) {
            this.b.close();
        }
        super.close();
    }
}
