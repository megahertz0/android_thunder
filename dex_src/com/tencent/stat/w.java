package com.tencent.stat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.tencent.stat.common.k;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.ArrayList;
import java.util.List;

class w extends SQLiteOpenHelper {
    public w(Context context) {
        super(context, k.v(context), null, 3);
    }

    private void a(SQLiteDatabase sQLiteDatabase) {
        Object th;
        String str = null;
        try {
            Cursor query = sQLiteDatabase.query("user", null, null, null, null, null, null);
            try {
                ContentValues contentValues = new ContentValues();
                if (query.moveToNext()) {
                    str = query.getString(0);
                    query.getInt(1);
                    query.getString(XZBDevice.DOWNLOAD_LIST_RECYCLE);
                    query.getLong(XZBDevice.DOWNLOAD_LIST_FAILED);
                    contentValues.put(ParamKey.UID, k.c(str));
                }
                if (str != null) {
                    sQLiteDatabase.update("user", contentValues, "uid=?", new String[]{str});
                }
                if (query != null) {
                    query.close();
                }
            } catch (Throwable th2) {
                th = th2;
                n.d().e(th);
                if (query != null) {
                    query.close();
                }
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

    private void b(SQLiteDatabase sQLiteDatabase) {
        Cursor cursor;
        try {
            Cursor query = sQLiteDatabase.query("events", null, null, null, null, null, null);
            try {
                List<x> arrayList = new ArrayList();
                while (query.moveToNext()) {
                    arrayList.add(new x(query.getLong(0), query.getString(1), query.getInt(XZBDevice.DOWNLOAD_LIST_RECYCLE), query.getInt(XZBDevice.DOWNLOAD_LIST_FAILED)));
                }
                ContentValues contentValues = new ContentValues();
                for (x xVar : arrayList) {
                    contentValues.put(ParamKey.CONTENT, k.c(xVar.b));
                    sQLiteDatabase.update("events", contentValues, "event_id=?", new String[]{Long.toString(xVar.a)});
                }
                if (query != null) {
                    query.close();
                }
            } catch (Throwable th) {
                th = th;
                if (query != null) {
                    query.close();
                }
                throw th;
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

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table if not exists events(event_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, content TEXT, status INTEGER, send_count INTEGER, timestamp LONG)");
        sQLiteDatabase.execSQL("create table if not exists user(uid TEXT PRIMARY KEY, user_type INTEGER, app_ver TEXT, ts INTEGER)");
        sQLiteDatabase.execSQL("create table if not exists config(type INTEGER PRIMARY KEY NOT NULL, content TEXT, md5sum TEXT, version INTEGER)");
        sQLiteDatabase.execSQL("create table if not exists keyvalues(key TEXT PRIMARY KEY NOT NULL, value TEXT)");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        n.d().debug(new StringBuilder("upgrade DB from oldVersion ").append(i).append(" to newVersion ").append(i2).toString());
        if (i == 1) {
            sQLiteDatabase.execSQL("create table if not exists keyvalues(key TEXT PRIMARY KEY NOT NULL, value TEXT)");
            a(sQLiteDatabase);
            b(sQLiteDatabase);
        }
        if (i == 2) {
            a(sQLiteDatabase);
            b(sQLiteDatabase);
        }
    }
}
