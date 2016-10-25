package com.xunlei.thundersniffer.sniff.sniffer.internal.server;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.common.encrypt.CharsetConvert;
import com.xunlei.thundersniffer.sniff.sniffer.internal.server.SvrCacheDatabase.CacheQueryHandler;
import com.xunlei.thundersniffer.sniff.sniffer.internal.server.SvrCacheDatabase.CacheSaveHandler;
import com.xunlei.thundersniffer.sniff.sniffer.internal.server.SvrCacheDatabase.QuerySVodInfoDelegate;
import com.xunlei.xiazaibao.BuildConfig;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.android.agoo.common.AgooConstants;

final class SvrCacheDatabase {
    private static SvrCacheDatabase c;
    a a;
    private Context b;

    public static abstract class CacheQueryHandler<T> {
        public abstract String getPrimaryValueForItem(T t);

        public abstract void onCacheHitForItem(boolean z, T t, String str, int i, String str2);
    }

    public static abstract class CacheSaveHandler<T> {
        public abstract String getCacheDataForItem(T t, String str, String str2);

        public abstract String getPrimaryValueForItem(T t);
    }

    public static abstract class QuerySVodInfoDelegate<T> {
        public abstract String getUrl(T t);

        public abstract void onSVodInfoGet(T t, String str, int i);
    }

    static class a extends SQLiteOpenHelper {
        private long a;

        public a(Context context) {
            this(context, "ThunderSnifferVod");
        }

        private a(Context context, String str) {
            super(context, str, null, 4);
            this.a = 0;
        }

        public final void onCreate(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL(String.format("CREATE TABLE IF NOT EXISTS %s(cacheHash TEXT PRIMARY KEY, ts INTEGER, primaryValue TEXT, svod INTEGER, data TEXT)", new Object[]{"svod_cache"}));
            sQLiteDatabase.execSQL(String.format("CREATE TABLE IF NOT EXISTS %s(cacheHash TEXT PRIMARY KEY, ts INTEGER, primaryValue TEXT, flag INTEGER, data TEXT)", new Object[]{"shub_cache"}));
        }

        public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            if (i <= 1) {
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS vod_cache");
            }
            if (i >= 2) {
                sQLiteDatabase.execSQL(String.format("DROP TABLE IF EXISTS %s", new Object[]{"svod_cache"}));
            }
            if (i >= 4) {
                sQLiteDatabase.execSQL(String.format("DROP TABLE IF EXISTS %s", new Object[]{"shub_cache"}));
            }
            onCreate(sQLiteDatabase);
        }

        public final synchronized <T> boolean a(List<T> list, CacheSaveHandler<T> cacheSaveHandler) {
            long currentTimeMillis = System.currentTimeMillis() / 1000;
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (writableDatabase != null) {
                if (!(list == null || list.isEmpty())) {
                    try {
                        Iterator it = list.iterator();
                        while (it.hasNext()) {
                            Object next = it.next();
                            String primaryValueForItem = cacheSaveHandler.getPrimaryValueForItem(next);
                            if (!TextUtils.isEmpty(primaryValueForItem)) {
                                String cacheDataForItem = cacheSaveHandler.getCacheDataForItem(next, SvrCacheDatabase.a(primaryValueForItem), primaryValueForItem);
                                if (cacheDataForItem == null) {
                                    cacheDataForItem = BuildConfig.VERSION_NAME;
                                }
                                writableDatabase.execSQL(String.format("REPLACE INTO %s(cacheHash,ts,flag,primaryValue,data) VALUES(?,?,?,?,?)", new Object[]{"shub_cache"}), new Object[]{r6, Long.valueOf(currentTimeMillis), Integer.valueOf(0), primaryValueForItem, cacheDataForItem});
                            }
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                writableDatabase.close();
            }
            return true;
        }

        public final synchronized <T> boolean a(List<T> list, CacheQueryHandler<T> cacheQueryHandler) {
            long currentTimeMillis = System.currentTimeMillis() / 1000;
            long j = 0;
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (writableDatabase != null) {
                try {
                    String str = "cacheHash=?";
                    if (!(list == null || list.isEmpty())) {
                        Iterator it = list.iterator();
                        while (it.hasNext()) {
                            long j2;
                            Object next = it.next();
                            String primaryValueForItem = cacheQueryHandler.getPrimaryValueForItem(next);
                            if (TextUtils.isEmpty(primaryValueForItem)) {
                                j2 = j;
                            } else {
                                long j3;
                                String string;
                                boolean z;
                                Cursor query = writableDatabase.query("shub_cache", null, str, new String[]{SvrCacheDatabase.a(primaryValueForItem)}, null, null, null);
                                if (query.moveToNext()) {
                                    j3 = query.getLong(query.getColumnIndex(AgooConstants.MESSAGE_FLAG));
                                    long j4 = query.getLong(query.getColumnIndex("ts"));
                                    string = query.getString(query.getColumnIndex(SocializeProtocolConstants.PROTOCOL_KEY_DATA));
                                    z = true;
                                    if (7200 + j4 < currentTimeMillis) {
                                        z = false;
                                    } else if (TextUtils.isEmpty(string) && j4 + 30 < currentTimeMillis) {
                                        z = false;
                                    }
                                } else {
                                    z = false;
                                    j3 = j;
                                    string = null;
                                }
                                query.close();
                                cacheQueryHandler.onCacheHitForItem(z, next, primaryValueForItem, (int) j3, string);
                                j2 = j3;
                            }
                            j = j2;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return true;
        }

        public final synchronized boolean a(Map<String, Integer> map) {
            long currentTimeMillis = System.currentTimeMillis() / 1000;
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (writableDatabase != null) {
                if (!(map == null || map.isEmpty())) {
                    Set<Entry> entrySet = map.entrySet();
                    if (!entrySet.isEmpty()) {
                        try {
                            for (Entry entry : entrySet) {
                                String str = (String) entry.getKey();
                                int intValue = ((Integer) entry.getValue()).intValue();
                                writableDatabase.execSQL(String.format("REPLACE INTO %s(cacheHash,ts,svod,primaryValue) VALUES(?,?,?,?)", new Object[]{"svod_cache"}), new Object[]{SvrCacheDatabase.a(str), Long.valueOf(currentTimeMillis), Integer.valueOf(intValue), str});
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
                writableDatabase.close();
            }
            return true;
        }

        private static long a(SQLiteDatabase sQLiteDatabase, String str, long j) {
            long j2;
            try {
                Cursor query = sQLiteDatabase.query("svod_cache", null, "cacheHash=?", new String[]{str}, null, null, null);
                if (query.moveToNext()) {
                    long j3 = query.getLong(query.getColumnIndex("svod"));
                    try {
                        long j4 = query.getLong(query.getColumnIndex("ts"));
                        j2 = j3;
                    } catch (Exception e) {
                        Exception exception = e;
                        j2 = j3;
                        Exception exception2 = exception;
                        exception2.printStackTrace();
                        return j2;
                    }
                }
                j4 = 0;
                j2 = -1;
                try {
                    Object obj;
                    query.close();
                    int i = (int) j2;
                    if (i == -1 || i == 3 || i == 5) {
                        obj = 1;
                    } else {
                        obj = null;
                    }
                    return obj == null ? 7200 + j4 < j ? -1 : j2 : 30 + j4 >= j ? 3 : j2;
                } catch (Exception e2) {
                    exception2 = e2;
                    exception2.printStackTrace();
                    return j2;
                }
            } catch (Exception e3) {
                exception2 = e3;
                j2 = -1;
                exception2.printStackTrace();
                return j2;
            }
        }

        public final synchronized <T> boolean a(Set<T> set, QuerySVodInfoDelegate<T> querySVodInfoDelegate) {
            boolean z;
            if (!(querySVodInfoDelegate == null || set == null)) {
                if (!set.isEmpty()) {
                    SQLiteDatabase readableDatabase;
                    try {
                        readableDatabase = getReadableDatabase();
                    } catch (Exception e) {
                        e.printStackTrace();
                        readableDatabase = null;
                    }
                    if (readableDatabase != null) {
                        Iterator it = set.iterator();
                        while (it.hasNext()) {
                            Object next = it.next();
                            if (next != null) {
                                long a;
                                String url = querySVodInfoDelegate.getUrl(next);
                                if (!TextUtils.isEmpty(url)) {
                                    try {
                                        a = a(readableDatabase, SvrCacheDatabase.a(url), System.currentTimeMillis() / 1000);
                                    } catch (Exception e2) {
                                        e2.printStackTrace();
                                    }
                                    querySVodInfoDelegate.onSVodInfoGet(next, url, (int) a);
                                }
                                a = -1;
                                querySVodInfoDelegate.onSVodInfoGet(next, url, (int) a);
                            }
                        }
                    }
                    z = true;
                }
            }
            z = false;
            return z;
        }

        private synchronized void b() {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (writableDatabase != null) {
                try {
                    String str = "ts < ?";
                    String[] strArr = new String[]{Long.valueOf((System.currentTimeMillis() / 1000) - 7200).toString()};
                    writableDatabase.delete("svod_cache", str, strArr);
                    writableDatabase.delete("shub_cache", str, strArr);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                writableDatabase.close();
            }
        }

        public final synchronized void a() {
            if (this.a == 0) {
                this.a = System.nanoTime();
                b();
            } else if ((System.nanoTime() - this.a) / 1000000000 >= 600) {
                this.a = System.nanoTime();
                b();
            }
        }
    }

    public static synchronized SvrCacheDatabase a() {
        SvrCacheDatabase svrCacheDatabase;
        synchronized (SvrCacheDatabase.class) {
            if (c == null) {
                c = new SvrCacheDatabase();
            }
            svrCacheDatabase = c;
        }
        return svrCacheDatabase;
    }

    public final synchronized void a(Context context) {
        if (context != null) {
            if (this.b == null) {
                this.b = context.getApplicationContext();
            }
        }
        if (this.a == null && this.b != null) {
            this.a = new a(this.b);
        }
    }

    public final synchronized boolean b() {
        return this.a != null;
    }

    public final synchronized <T> boolean a(List<T> list, CacheSaveHandler<T> cacheSaveHandler) {
        boolean z;
        if (this.a != null) {
            this.a.a((List) list, (CacheSaveHandler) cacheSaveHandler);
            z = true;
        } else {
            z = false;
        }
        return z;
    }

    public final synchronized <T> boolean a(List<T> list, CacheQueryHandler<T> cacheQueryHandler) {
        boolean z;
        if (this.a != null) {
            this.a.a((List) list, (CacheQueryHandler) cacheQueryHandler);
            z = true;
        } else {
            z = false;
        }
        return z;
    }

    public final synchronized boolean a(Map<String, Integer> map) {
        boolean z;
        if (this.a != null) {
            this.a.a(map);
            z = true;
        } else {
            z = false;
        }
        return z;
    }

    public final synchronized <T> boolean a(Set<T> set, QuerySVodInfoDelegate<T> querySVodInfoDelegate) {
        boolean z;
        z = this.a != null && this.a.a((Set) set, (QuerySVodInfoDelegate) querySVodInfoDelegate);
        return z;
    }

    public final synchronized void c() {
        if (this.a != null) {
            this.a.a();
        }
    }

    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "00000000000000000000000000000000";
        }
        try {
            str = com.xunlei.c.b.a.a(str.getBytes(CharsetConvert.UTF_8));
        } catch (UnsupportedEncodingException e) {
            try {
                str = com.xunlei.c.b.a.a(str.getBytes(CharsetConvert.ISO_8859_1));
            } catch (UnsupportedEncodingException e2) {
                try {
                    str = com.xunlei.c.b.a.a(str.getBytes());
                } catch (Exception e3) {
                    e3.printStackTrace();
                    if (null != null) {
                        str = null;
                    }
                }
            }
        }
        return TextUtils.isEmpty(str) ? "00000000000000000000000000000000" : str;
    }

    static {
        c = null;
    }

    private SvrCacheDatabase() {
    }
}
