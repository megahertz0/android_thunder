package com.xunlei.downloadprovider.search.a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.search.bean.d;
import com.xunlei.downloadprovider.util.sniff.SniffConfigure;
import java.util.ArrayList;
import java.util.List;

// compiled from: SearchRecordDbHelper.java
public class a extends SQLiteOpenHelper {
    private static a a;

    private a(Context context) {
        super(context, "xlsearch.db", null, 1);
    }

    public static a a() {
        if (a == null) {
            synchronized (a.class) {
                if (a == null) {
                    a = new a(BrothersApplication.a());
                }
            }
        }
        return a;
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table if not exists Search_Record_Tab (_id integer primary key autoincrement,searchContent text,searchTimestamp long ,searchKeywordTab int );");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS Search_Record_Tab");
        onCreate(sQLiteDatabase);
    }

    public final List<d> b() {
        List<d> arrayList = new ArrayList();
        SQLiteDatabase readableDatabase = getReadableDatabase();
        try {
            Cursor query = readableDatabase.query("Search_Record_Tab", new String[]{"searchContent", "searchTimestamp", "searchKeywordTab"}, null, null, null, null, "searchTimestamp DESC ");
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        int columnIndex = query.getColumnIndex("searchContent");
                        int columnIndex2 = query.getColumnIndex("searchTimestamp");
                        int columnIndex3 = query.getColumnIndex("searchKeywordTab");
                        do {
                            d dVar = new d();
                            String string = query.getString(columnIndex);
                            if (!TextUtils.isEmpty(string)) {
                                String str;
                                String[] split = string.split(" ");
                                int length = split.length;
                                if (length > 0) {
                                    str = split[length - 1];
                                } else {
                                    str = null;
                                }
                                ArrayList arrayList2 = SniffConfigure.a().b().a;
                                if (arrayList2 == null || arrayList2.isEmpty() || !arrayList2.contains(str)) {
                                    dVar.a = string;
                                } else {
                                    dVar.a = string.substring(0, string.lastIndexOf(str)).trim();
                                    dVar.d = str;
                                }
                            }
                            long j = query.getLong(columnIndex2);
                            int i = query.getInt(columnIndex3);
                            dVar.b = j;
                            dVar.c = i;
                            arrayList.add(dVar);
                        } while (query.moveToNext());
                    }
                } catch (Exception e) {
                    e = e;
                }
            }
            a(readableDatabase, query);
        } catch (Exception e2) {
            e = e2;
            query = null;
            try {
                Exception e3;
                e3.printStackTrace();
                a(readableDatabase, query);
            } catch (Throwable th) {
                Throwable th2 = th;
                a(readableDatabase, query);
                throw th2;
            }
            return arrayList;
        } catch (Throwable th3) {
            th2 = th3;
            query = null;
            a(readableDatabase, query);
            throw th2;
        }
        return arrayList;
    }

    private static void a(SQLiteDatabase sQLiteDatabase, Cursor cursor) {
        a(sQLiteDatabase);
        if (cursor != null) {
            try {
                cursor.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void a(SQLiteDatabase sQLiteDatabase) {
        if (sQLiteDatabase != null) {
            try {
                sQLiteDatabase.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public final synchronized void a(String str) {
        a(new d(str, System.currentTimeMillis()));
    }

    private synchronized void a(d dVar) {
        SQLiteDatabase sQLiteDatabase = null;
        synchronized (this) {
            try {
                sQLiteDatabase = getWritableDatabase();
                sQLiteDatabase.delete("Search_Record_Tab", "searchContent=?", new String[]{dVar.a});
                ContentValues contentValues = new ContentValues();
                contentValues.put("searchContent", dVar.a);
                contentValues.put("searchTimestamp", Long.valueOf(dVar.b));
                contentValues.put("searchKeywordTab", Integer.valueOf(dVar.c));
                sQLiteDatabase.insert("Search_Record_Tab", null, contentValues);
                a(sQLiteDatabase);
            } catch (Throwable th) {
            }
        }
    }

    public final synchronized void c() {
        SQLiteDatabase sQLiteDatabase = null;
        synchronized (this) {
            try {
                sQLiteDatabase = getWritableDatabase();
                sQLiteDatabase.delete("Search_Record_Tab", null, null);
                a(sQLiteDatabase);
            } catch (Throwable th) {
            }
        }
    }
}
