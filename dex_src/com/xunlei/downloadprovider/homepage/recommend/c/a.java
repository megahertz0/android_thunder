package com.xunlei.downloadprovider.homepage.recommend.c;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.umeng.message.proguard.j;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.downloadprovider.app.BrothersApplication;
import java.util.ArrayList;
import java.util.List;
import org.android.agoo.common.AgooConstants;

// compiled from: ClickNiceDatabase.java
public final class a extends SQLiteOpenHelper {
    private static String b;
    private static String c;
    private static String d;
    private static String e;
    private static String f;
    private static String g;
    private static a h;
    private final String a;

    static {
        b = "clickNiceRecord_table";
        c = AgooConstants.MESSAGE_ID;
        d = SocializeProtocolConstants.PROTOCOL_KEY_FRIENDS_NAME;
        e = "play_url";
        f = "movie_id";
        g = "gc_id";
    }

    public static synchronized a a() {
        a aVar;
        synchronized (a.class) {
            if (h == null) {
                h = new a(BrothersApplication.a);
            }
            aVar = h;
        }
        return aVar;
    }

    private a(Context context) {
        super(context, "clickNiceRecord_db", null, 111);
        this.a = "ClickNiceDatabase";
    }

    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        a(sQLiteDatabase);
    }

    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        new StringBuilder("onUpgrade oldVersion=").append(i).append(",newVersion=").append(i2);
        sQLiteDatabase.execSQL(new StringBuilder("drop table if exists ").append(b).toString());
        a(sQLiteDatabase);
    }

    private static void a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(new StringBuilder("create table if not exists ").append(b).append(j.s).append(c).append(" integer primary key autoincrement,").append(d).append(" text,").append(f).append(" long,").append(e).append(" text,").append(g).append(" text)").toString());
    }

    public final synchronized void a(b bVar) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(d, bVar.b);
        contentValues.put(e, bVar.c);
        contentValues.put(f, bVar.d);
        contentValues.put(g, bVar.e);
        writableDatabase.insert(b, null, contentValues);
        writableDatabase.close();
    }

    public final synchronized b a(String str) {
        b bVar;
        Cursor cursor = null;
        synchronized (this) {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            try {
                b bVar2;
                Cursor query = writableDatabase.query(b, null, f + "=" + str, null, null, null, null, null);
                if (query != null) {
                    List a;
                    try {
                        a = a(query);
                    } catch (Exception e) {
                        e = e;
                        e.printStackTrace();
                        if (query != null) {
                            query.close();
                        }
                        if (writableDatabase == null) {
                            writableDatabase.close();
                            bVar = null;
                        } else {
                            bVar = null;
                        }
                        return bVar;
                    }
                    if (a != null && a.size() > 0) {
                        bVar2 = (b) a.get(0);
                        if (query != null) {
                            query.close();
                        }
                        if (writableDatabase == null) {
                            writableDatabase.close();
                            bVar = bVar2;
                        } else {
                            bVar = bVar2;
                        }
                    }
                }
                bVar2 = null;
                if (query != null) {
                    query.close();
                }
                if (writableDatabase == null) {
                    bVar = bVar2;
                } else {
                    writableDatabase.close();
                    bVar = bVar2;
                }
            } catch (Exception e2) {
                e = e2;
                query = null;
                try {
                    Exception e3;
                    e3.printStackTrace();
                    if (query != null) {
                        query.close();
                    }
                    if (writableDatabase == null) {
                        bVar = null;
                    } else {
                        writableDatabase.close();
                        bVar = null;
                    }
                } catch (Throwable th) {
                    Throwable th2 = th;
                    cursor = query;
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (writableDatabase != null) {
                        writableDatabase.close();
                    }
                    throw th2;
                }
                return bVar;
            } catch (Throwable th3) {
                th2 = th3;
                if (cursor != null) {
                    cursor.close();
                }
                if (writableDatabase != null) {
                    writableDatabase.close();
                }
                throw th2;
            }
        }
        return bVar;
    }

    public final synchronized List<b> b() {
        return a(getWritableDatabase().query(b, new String[]{c, d, e, f, g}, null, null, null, null, null, null));
    }

    public final synchronized void b(String str) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.delete(b, f + "=" + str, null);
        writableDatabase.close();
    }

    private static List<b> a(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        List<b> arrayList = new ArrayList();
        try {
            int columnIndex = cursor.getColumnIndex(c);
            int columnIndex2 = cursor.getColumnIndex(d);
            int columnIndex3 = cursor.getColumnIndex(f);
            int columnIndex4 = cursor.getColumnIndex(e);
            int columnIndex5 = cursor.getColumnIndex(g);
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    b bVar = new b();
                    if (columnIndex >= 0) {
                        bVar.a = cursor.getInt(columnIndex);
                    }
                    if (columnIndex2 >= 0) {
                        bVar.b = cursor.getString(columnIndex2);
                    }
                    if (columnIndex3 >= 0) {
                        bVar.d = cursor.getString(columnIndex3);
                    }
                    if (columnIndex4 >= 0) {
                        try {
                            bVar.c = com.xunlei.downloadprovider.util.c.a.e(cursor.getString(columnIndex4));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    if (columnIndex5 > 0) {
                        bVar.e = cursor.getString(columnIndex5);
                    }
                    arrayList.add(bVar);
                    cursor.moveToNext();
                }
            }
            if (cursor == null) {
                return arrayList;
            }
            cursor.close();
            return arrayList;
        } catch (Exception e2) {
            try {
                e2.printStackTrace();
                if (cursor == null) {
                    return arrayList;
                }
                cursor.close();
                return arrayList;
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
    }
}
