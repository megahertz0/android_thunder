package com.xunlei.downloadprovider.vod.b;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import com.sina.weibo.sdk.component.GameManager;
import com.sina.weibo.sdk.register.mobile.SelectCountryActivity;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.tdlive.R;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

// compiled from: PlayRecordDatabase.java
public final class a extends SQLiteOpenHelper {
    static final String[][] a;
    private static String c;
    private static String d;
    private static a e;
    private final String b;

    static {
        c = "playrecord_db";
        d = "playrecord_table";
        r0 = new String[19][];
        r0[0] = new String[]{SocializeConstants.WEIBO_ID, "integer primary key autoincrement"};
        r0[1] = new String[]{SelectCountryActivity.EXTRA_COUNTRY_NAME, "text"};
        r0[2] = new String[]{"last_play_time", "text"};
        r0[3] = new String[]{JsInterface.FUNPLAY_AD_TRPE, "integer"};
        r0[4] = new String[]{"vod_type", "integer"};
        r0[5] = new String[]{"play_url", "text"};
        r0[6] = new String[]{"download_url", "text"};
        r0[7] = new String[]{Impl.COLUMN_CID, "text"};
        r0[8] = new String[]{Impl.COLUMN_GCID, "text"};
        r0[9] = new String[]{"size", "long"};
        r0[10] = new String[]{"played_time", "integer"};
        r0[11] = new String[]{"total_time", "integer"};
        r0[12] = new String[]{"file_type", "text"};
        r0[13] = new String[]{"ref_url", "text"};
        r0[14] = new String[]{"ref_no", "integer"};
        r0[15] = new String[]{"ref_id", "text"};
        r0[16] = new String[]{"tag", "text"};
        r0[17] = new String[]{"tv_key", "text"};
        r0[18] = new String[]{"tv_no", "integer"};
        a = r0;
    }

    public static synchronized a a() {
        a aVar;
        synchronized (a.class) {
            if (e == null) {
                e = new a(BrothersApplication.a);
            }
            aVar = e;
        }
        return aVar;
    }

    private a(Context context) {
        super(context, c, null, 112);
        this.b = "pr-PlayRecordDatabase";
    }

    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        a(sQLiteDatabase);
    }

    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL(new StringBuilder("DROP TABLE IF EXISTS ").append(d).toString());
        a(sQLiteDatabase);
        a(sQLiteDatabase, "shortVideo");
    }

    public final void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL(new StringBuilder("DROP TABLE IF EXISTS ").append(d).toString());
        a(sQLiteDatabase);
    }

    private static void a(SQLiteDatabase sQLiteDatabase) {
        String str = d;
        String[][] strArr = a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CREATE TABLE IF NOT EXISTS `").append(str).append("`(");
        for (int i = 0; i < strArr.length; i++) {
            if (i > 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("`").append(strArr[i][0]).append("` ").append(strArr[i][1]);
        }
        stringBuilder.append(SocializeConstants.OP_CLOSE_PAREN);
        sQLiteDatabase.execSQL(stringBuilder.toString());
    }

    public final synchronized void a(com.xunlei.downloadprovider.vod.b.b.a aVar) {
        com.xunlei.downloadprovider.vod.b.b.a b;
        new StringBuilder("addRecord record name=").append(aVar.b);
        SQLiteDatabase writableDatabase = getWritableDatabase();
        switch (aVar.m) {
            case R.styleable.AppCompatTheme_buttonStyle:
                b = b(aVar.n);
                break;
            default:
                if ("shortVideo".equals(aVar.q)) {
                    b = d(aVar.p);
                } else {
                    b = a(aVar.f);
                }
                break;
        }
        new StringBuilder("addRecord oldRecord = ").append(b);
        if (b == null) {
            ContentValues b2 = b(aVar);
            if (b2 != null) {
                writableDatabase.insert(d, null, b2);
            }
        } else {
            a(b.a, aVar);
        }
    }

    public final synchronized com.xunlei.downloadprovider.vod.b.b.a a(String str) {
        com.xunlei.downloadprovider.vod.b.b.a aVar;
        SQLiteDatabase writableDatabase = getWritableDatabase();
        try {
            Cursor query = writableDatabase.query(d, null, new StringBuilder("play_url='").append(URLEncoder.encode(str, GameManager.DEFAULT_CHARSET).replace(SocializeConstants.OP_DIVIDER_PLUS, "%20")).append("'").toString(), null, null, null, null);
            if (query != null) {
                List a = a(query);
                if (a == null || a.size() <= 0) {
                    aVar = null;
                } else {
                    aVar = (com.xunlei.downloadprovider.vod.b.b.a) a.get(0);
                }
                try {
                    query.close();
                } catch (Exception e) {
                }
            } else {
                aVar = null;
            }
        } catch (Exception e2) {
            aVar = null;
        }
        return aVar;
    }

    public final synchronized com.xunlei.downloadprovider.vod.b.b.a b(String str) {
        com.xunlei.downloadprovider.vod.b.b.a aVar;
        SQLiteDatabase writableDatabase = getWritableDatabase();
        try {
            Cursor query = writableDatabase.query(d, null, new StringBuilder("ref_url='").append(URLEncoder.encode(str, GameManager.DEFAULT_CHARSET).replace(SocializeConstants.OP_DIVIDER_PLUS, "%20")).append("'").toString(), null, null, null, null);
            if (query != null) {
                List a = a(query);
                if (a == null || a.size() <= 0) {
                    aVar = null;
                } else {
                    aVar = (com.xunlei.downloadprovider.vod.b.b.a) a.get(0);
                }
                try {
                    query.close();
                } catch (Exception e) {
                }
            } else {
                aVar = null;
            }
        } catch (Exception e2) {
            aVar = null;
        }
        return aVar;
    }

    private synchronized com.xunlei.downloadprovider.vod.b.b.a d(String str) {
        com.xunlei.downloadprovider.vod.b.b.a aVar;
        try {
            Cursor query = getWritableDatabase().query(d, null, new StringBuilder("ref_id='").append(str).append("'").toString(), null, null, null, null);
            if (query != null) {
                List a = a(query);
                if (a == null || a.size() <= 0) {
                    aVar = null;
                } else {
                    aVar = (com.xunlei.downloadprovider.vod.b.b.a) a.get(0);
                }
                try {
                    query.close();
                } catch (Exception e) {
                }
            } else {
                aVar = null;
            }
        } catch (Exception e2) {
            aVar = null;
        }
        return aVar;
    }

    public final synchronized void a(Set<String> set) {
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            try {
                writableDatabase.beginTransaction();
                for (String str : set) {
                    writableDatabase.delete(d, new StringBuilder("id=").append(str).toString(), null);
                }
                writableDatabase.setTransactionSuccessful();
                if (writableDatabase != null) {
                    if (writableDatabase.isOpen()) {
                        writableDatabase.endTransaction();
                    }
                }
            } catch (SQLiteException e) {
                e.printStackTrace();
                if (writableDatabase != null) {
                    if (writableDatabase.isOpen()) {
                        writableDatabase.endTransaction();
                    }
                }
            }
        } catch (Throwable th) {
        }
    }

    public final synchronized void c(String str) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        try {
            writableDatabase.delete(d, "ref_id=?", new String[]{str});
            writableDatabase.close();
        } catch (Exception e) {
        }
    }

    private synchronized void a(SQLiteDatabase sQLiteDatabase, String str) {
        sQLiteDatabase.delete(d, new StringBuilder("tag='").append(str).append("'").toString(), null);
    }

    private static ContentValues b(com.xunlei.downloadprovider.vod.b.b.a aVar) {
        if (aVar == null) {
            return null;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(SelectCountryActivity.EXTRA_COUNTRY_NAME, aVar.b);
        contentValues.put("last_play_time", Long.valueOf(aVar.c));
        contentValues.put(JsInterface.FUNPLAY_AD_TRPE, Integer.valueOf(aVar.d));
        contentValues.put("vod_type", Integer.valueOf(aVar.e));
        try {
            contentValues.put("play_url", URLEncoder.encode(aVar.f, "utf-8").replace(SocializeConstants.OP_DIVIDER_PLUS, "%20"));
        } catch (Exception e) {
        }
        try {
            contentValues.put("download_url", URLEncoder.encode(aVar.g, "utf-8").replace(SocializeConstants.OP_DIVIDER_PLUS, "%20"));
        } catch (Exception e2) {
        }
        contentValues.put(Impl.COLUMN_CID, aVar.h);
        contentValues.put(Impl.COLUMN_GCID, aVar.i);
        contentValues.put("size", Long.valueOf(aVar.j));
        contentValues.put("played_time", Integer.valueOf(aVar.k));
        contentValues.put("total_time", Integer.valueOf(aVar.l));
        contentValues.put("file_type", Integer.valueOf(aVar.m));
        try {
            contentValues.put("ref_url", URLEncoder.encode(aVar.n, GameManager.DEFAULT_CHARSET).replace(SocializeConstants.OP_DIVIDER_PLUS, "%20"));
        } catch (Exception e3) {
        }
        contentValues.put("ref_no", Integer.valueOf(aVar.o));
        contentValues.put("ref_id", aVar.p);
        contentValues.put("tag", aVar.q);
        contentValues.put("tv_key", aVar.r);
        contentValues.put("tv_no", Integer.valueOf(aVar.s));
        return contentValues;
    }

    private synchronized void a(int i, com.xunlei.downloadprovider.vod.b.b.a aVar) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues b = b(aVar);
        if (b != null) {
            writableDatabase.update(d, b, new StringBuilder("id=").append(i).toString(), null);
        }
    }

    private static List<com.xunlei.downloadprovider.vod.b.b.a> a(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        List<com.xunlei.downloadprovider.vod.b.b.a> arrayList = new ArrayList();
        int columnIndex = cursor.getColumnIndex(SocializeConstants.WEIBO_ID);
        int columnIndex2 = cursor.getColumnIndex(SelectCountryActivity.EXTRA_COUNTRY_NAME);
        int columnIndex3 = cursor.getColumnIndex("last_play_time");
        int columnIndex4 = cursor.getColumnIndex(JsInterface.FUNPLAY_AD_TRPE);
        int columnIndex5 = cursor.getColumnIndex("vod_type");
        int columnIndex6 = cursor.getColumnIndex("play_url");
        int columnIndex7 = cursor.getColumnIndex("download_url");
        int columnIndex8 = cursor.getColumnIndex(Impl.COLUMN_CID);
        int columnIndex9 = cursor.getColumnIndex(Impl.COLUMN_GCID);
        int columnIndex10 = cursor.getColumnIndex("size");
        int columnIndexOrThrow = cursor.getColumnIndexOrThrow("played_time");
        int columnIndexOrThrow2 = cursor.getColumnIndexOrThrow("total_time");
        int columnIndexOrThrow3 = cursor.getColumnIndexOrThrow("file_type");
        int columnIndexOrThrow4 = cursor.getColumnIndexOrThrow("ref_url");
        int columnIndexOrThrow5 = cursor.getColumnIndexOrThrow("ref_no");
        int columnIndexOrThrow6 = cursor.getColumnIndexOrThrow("ref_id");
        int columnIndexOrThrow7 = cursor.getColumnIndexOrThrow("tag");
        int columnIndex11 = cursor.getColumnIndex("tv_key");
        int columnIndex12 = cursor.getColumnIndex("tv_no");
        if (cursor.moveToFirst()) {
            while (cursor != null && !cursor.isAfterLast()) {
                com.xunlei.downloadprovider.vod.b.b.a aVar = new com.xunlei.downloadprovider.vod.b.b.a();
                if (columnIndex >= 0) {
                    aVar.a = cursor.getInt(columnIndex);
                }
                if (columnIndex2 >= 0) {
                    aVar.b = cursor.getString(columnIndex2);
                }
                if (columnIndex4 >= 0) {
                    aVar.d = cursor.getInt(columnIndex4);
                }
                if (columnIndex5 >= 0) {
                    aVar.e = cursor.getInt(columnIndex5);
                }
                if (columnIndex6 >= 0) {
                    try {
                        aVar.f = com.xunlei.downloadprovider.util.c.a.e(cursor.getString(columnIndex6));
                    } catch (Exception e) {
                    }
                }
                if (columnIndex7 >= 0) {
                    try {
                        aVar.g = com.xunlei.downloadprovider.util.c.a.e(cursor.getString(columnIndex7));
                    } catch (Exception e2) {
                    }
                }
                if (columnIndex3 >= 0) {
                    aVar.c = cursor.getLong(columnIndex3);
                }
                if (columnIndex8 >= 0) {
                    aVar.h = cursor.getString(columnIndex8);
                }
                if (columnIndex9 >= 0) {
                    aVar.i = cursor.getString(columnIndex9);
                }
                if (columnIndex10 >= 0) {
                    aVar.j = cursor.getLong(columnIndex10);
                }
                if (columnIndexOrThrow > 0) {
                    aVar.k = cursor.getInt(columnIndexOrThrow);
                }
                if (columnIndexOrThrow2 > 0) {
                    aVar.l = cursor.getInt(columnIndexOrThrow2);
                }
                if (columnIndexOrThrow3 >= 0) {
                    aVar.m = cursor.getInt(columnIndexOrThrow3);
                }
                if (columnIndexOrThrow4 >= 0) {
                    try {
                        aVar.n = com.xunlei.downloadprovider.util.c.a.e(cursor.getString(columnIndexOrThrow4));
                    } catch (Exception e3) {
                    }
                }
                if (columnIndexOrThrow5 >= 0) {
                    aVar.o = cursor.getInt(columnIndexOrThrow5);
                }
                if (columnIndexOrThrow6 > 0) {
                    aVar.p = cursor.getString(columnIndexOrThrow6);
                }
                if (columnIndexOrThrow7 >= 0) {
                    aVar.q = cursor.getString(columnIndexOrThrow7);
                }
                if (columnIndex11 >= 0) {
                    aVar.r = cursor.getString(columnIndex11);
                }
                if (columnIndex12 >= 0) {
                    aVar.s = cursor.getInt(columnIndex12);
                }
                arrayList.add(aVar);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return arrayList;
    }

    public final synchronized List<com.xunlei.downloadprovider.vod.b.b.a> a(long j, int i) {
        List<com.xunlei.downloadprovider.vod.b.b.a> a;
        String str = null;
        synchronized (this) {
            String str2;
            String[] strArr;
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (j > 0) {
                str2 = "last_play_time<?";
                strArr = new String[]{String.valueOf(j)};
            } else {
                strArr = null;
                str2 = null;
            }
            if (i > 0) {
                str = String.valueOf(i);
            }
            a = a(writableDatabase.query(d, null, str2, strArr, null, null, "last_play_time desc", str));
        }
        return a;
    }
}
