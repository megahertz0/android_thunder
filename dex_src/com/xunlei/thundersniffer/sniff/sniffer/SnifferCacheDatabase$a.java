package com.xunlei.thundersniffer.sniff.sniffer;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.xiazaibao.BuildConfig;

class SnifferCacheDatabase$a extends SQLiteOpenHelper {
    private long a;

    public SnifferCacheDatabase$a(Context context) {
        this(context, "ThunderSniffer");
    }

    private SnifferCacheDatabase$a(Context context, String str) {
        super(context, str, null, 2);
        this.a = 0;
    }

    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(String.format("CREATE TABLE IF NOT EXISTS %s(wd TEXT PRIMARY KEY, ts INTEGER, ts_expiration INTEGER, flags INTEGER, data TEXT)", new Object[]{"cache_detail_page_urls"}));
    }

    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i <= 1) {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS sniffer_cache");
        }
        sQLiteDatabase.execSQL(String.format("DROP TABLE IF EXISTS %s", new Object[]{"cache_detail_page_urls"}));
        onCreate(sQLiteDatabase);
    }

    public final synchronized void a() {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        if (writableDatabase != null) {
            writableDatabase.execSQL(String.format("DROP TABLE IF EXISTS %s", new Object[]{"cache_detail_page_urls"}));
            onCreate(writableDatabase);
            writableDatabase.close();
        }
    }

    public final synchronized boolean a(String str, String str2, long j, long j2) {
        boolean z = false;
        synchronized (this) {
            if (!TextUtils.isEmpty(str)) {
                if (str2 == null) {
                    str2 = BuildConfig.VERSION_NAME;
                }
                if (j2 < 0 || j2 > 7200) {
                    j2 = 7200;
                }
                long currentTimeMillis = System.currentTimeMillis() / 1000;
                SQLiteDatabase writableDatabase = getWritableDatabase();
                if (writableDatabase != null) {
                    writableDatabase.execSQL(String.format("REPLACE INTO %s(wd,ts,ts_expiration,flags,data) VALUES(?,?,?,?,?)", new Object[]{"cache_detail_page_urls"}), new Object[]{str, Long.valueOf(currentTimeMillis), Long.valueOf(currentTimeMillis + j2), Long.valueOf(j), str2});
                    writableDatabase.close();
                }
                z = true;
            }
        }
        return z;
    }

    public final synchronized boolean a(String str, SnifferCacheDatabase$ISnifferCacheCallback snifferCacheDatabase$ISnifferCacheCallback) {
        boolean z;
        String str2;
        String string;
        Exception e;
        int i;
        long j;
        if (TextUtils.isEmpty(str) || snifferCacheDatabase$ISnifferCacheCallback == null) {
            z = false;
        } else {
            SQLiteDatabase readableDatabase = getReadableDatabase();
            Object obj = -1;
            if (readableDatabase != null) {
                Cursor query;
                long currentTimeMillis;
                Object obj2;
                try {
                    String str3 = "cache_detail_page_urls";
                    query = readableDatabase.query(str3, null, "wd=?", new String[]{str}, null, null, null);
                    currentTimeMillis = System.currentTimeMillis() / 1000;
                    try {
                        query.close();
                        str2 = string;
                    } catch (Exception e2) {
                        e = e2;
                        e.printStackTrace();
                        str2 = string;
                        readableDatabase.close();
                        snifferCacheDatabase$ISnifferCacheCallback.onSnifferCacheCallback("cache_detail_page_urls", i, str, j, str2);
                        z = true;
                        return z;
                    }
                } catch (Exception e3) {
                    e = e3;
                    i = -1;
                    obj2 = null;
                    j = 0;
                    e.printStackTrace();
                    str2 = string;
                    readableDatabase.close();
                    snifferCacheDatabase$ISnifferCacheCallback.onSnifferCacheCallback("cache_detail_page_urls", i, str, j, str2);
                    z = true;
                    return z;
                }
                if (query.moveToNext()) {
                    j = query.getLong(query.getColumnIndex("ts_expiration"));
                    long j2 = query.getLong(query.getColumnIndex("ts"));
                    if (j2 > currentTimeMillis || currentTimeMillis >= j || j2 >= j) {
                        i = 1;
                        obj2 = null;
                        j = 0;
                    } else {
                        currentTimeMillis = query.getLong(query.getColumnIndex("flags"));
                        try {
                            obj = null;
                            j = currentTimeMillis;
                            string = query.getString(query.getColumnIndex(SocializeProtocolConstants.PROTOCOL_KEY_DATA));
                            i = 0;
                        } catch (Exception e4) {
                            e = e4;
                            j = currentTimeMillis;
                            obj2 = null;
                            i = r8;
                            e.printStackTrace();
                            str2 = string;
                            readableDatabase.close();
                            snifferCacheDatabase$ISnifferCacheCallback.onSnifferCacheCallback("cache_detail_page_urls", i, str, j, str2);
                            z = true;
                            return z;
                        }
                    }
                }
                i = -1;
                obj2 = null;
                j = 0;
                readableDatabase.close();
            } else {
                i = -1;
                str2 = null;
                j = 0;
            }
            snifferCacheDatabase$ISnifferCacheCallback.onSnifferCacheCallback("cache_detail_page_urls", i, str, j, str2);
            z = true;
        }
        return z;
    }

    private synchronized void c() {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        if (writableDatabase != null) {
            try {
                String str = "cache_detail_page_urls";
                writableDatabase.delete(str, "ts_expiration < ?", new String[]{Long.valueOf(System.currentTimeMillis() / 1000).toString()});
            } catch (Exception e) {
                e.printStackTrace();
            }
            writableDatabase.close();
        }
    }

    public final synchronized void b() {
        if (this.a == 0) {
            this.a = System.nanoTime();
            c();
        } else if ((System.nanoTime() - this.a) / 1000000000 >= 600) {
            this.a = System.nanoTime();
            c();
        }
    }
}
