package com.android.providers.downloads;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.UriMatcher;
import android.content.pm.ApplicationInfo;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Binder;
import android.os.Handler;
import android.os.ParcelFileDescriptor;
import android.os.Process;
import android.text.TextUtils;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import com.umeng.message.MsgConstant;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.download.DownloadManager;
import com.xunlei.download.DownloadManager.TaskType;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.download.Downloads.Impl.RequestHeaders;
import com.xunlei.download.proguard.ab;
import com.xunlei.download.proguard.am;
import com.xunlei.download.proguard.d;
import com.xunlei.download.proguard.f;
import com.xunlei.download.proguard.l;
import com.xunlei.download.proguard.m;
import com.xunlei.download.proguard.q;
import com.xunlei.download.proguard.s;
import com.xunlei.download.proguard.u;
import com.xunlei.download.proguard.y;
import com.xunlei.download.proguard.z;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.WebBrowserActivity;
import com.xunlei.util.StatHelper;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

public class DownloadProvider extends ContentProvider {
    private static final String[] A;
    private static final HashSet<String> B;
    private static final HashMap<String, String> C;
    private static final List<String> D;
    public static final String a = "_key";
    public static final String b = "_value";
    public static Uri c = null;
    public static Uri d = null;
    public static String e = null;
    private static final String g = "xl_downloads.db";
    private static final int h = 116;
    private static final String i = "xl_downloads";
    private static final String j = "vnd.android.cursor.dir/download";
    private static final String k = "vnd.android.cursor.item/download";
    private static final UriMatcher l;
    private static final int m = 1;
    private static final int n = 2;
    private static final int o = 3;
    private static final int p = 4;
    private static final int q = 5;
    private static final int r = 6;
    private static final int s = 9;
    private static final String t = "xl_config";
    private static final int u = 100;
    private static final String v = "xl_bt_sub_task";
    private static final int w = 200;
    private static final int x = 201;
    private static final int y = 202;
    private static Uri[] z;
    private Handler E;
    private SQLiteOpenHelper F;
    private int G;
    private int H;
    private File I;
    u f;

    final class a extends SQLiteOpenHelper {
        public a(Context context) {
            super(context, g, null, 116);
        }

        public final void onCreate(SQLiteDatabase sQLiteDatabase) {
            onUpgrade(sQLiteDatabase, 0, h);
        }

        public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            if (i == 3 || i == 4) {
                i = 100;
            } else if (i == 31) {
                i = 100;
            } else if (i < 100) {
                am.a(DownloadManager.LOG_TAG, new StringBuilder("Upgrading downloads database from version ").append(i).append(" to version ").append(i2).append(", which will destroy all old data").toString());
                i = 99;
            } else if (i > i2) {
                am.a(DownloadManager.LOG_TAG, new StringBuilder("Downgrading downloads database from version ").append(i).append(" (current version is ").append(i2).append("), destroying all old data").toString());
                i = 99;
            }
            for (int i3 = i + 1; i3 <= i2; i3++) {
                a(sQLiteDatabase, i3);
            }
        }

        public final void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            am.b(DownloadManager.LOG_TAG, new StringBuilder("onDowngrade() ").append(i).append(" -> ").append(i2).toString());
            a(sQLiteDatabase);
            onUpgrade(sQLiteDatabase, 0, h);
        }

        private void a(SQLiteDatabase sQLiteDatabase, int i) {
            switch (i) {
                case u:
                    d(sQLiteDatabase);
                case R.styleable.AppCompatTheme_buttonStyleSmall:
                    e(sQLiteDatabase);
                case R.styleable.AppCompatTheme_checkboxStyle:
                    a(sQLiteDatabase, i, Impl.COLUMN_IS_PUBLIC_API, "INTEGER NOT NULL DEFAULT 0");
                    a(sQLiteDatabase, i, Impl.COLUMN_ALLOW_ROAMING, "INTEGER NOT NULL DEFAULT 0");
                    a(sQLiteDatabase, i, Impl.COLUMN_ALLOWED_NETWORK_TYPES, "INTEGER NOT NULL DEFAULT 0");
                case R.styleable.AppCompatTheme_checkedTextViewStyle:
                    a(sQLiteDatabase, i, Impl.COLUMN_IS_VISIBLE_IN_DOWNLOADS_UI, "INTEGER NOT NULL DEFAULT 1");
                    c(sQLiteDatabase);
                case R.styleable.AppCompatTheme_editTextStyle:
                    a(sQLiteDatabase, i, Impl.COLUMN_BYPASS_RECOMMENDED_SIZE_LIMIT, "INTEGER NOT NULL DEFAULT 0");
                case R.styleable.AppCompatTheme_radioButtonStyle:
                    b(sQLiteDatabase);
                case R.styleable.AppCompatTheme_ratingBarStyle:
                    a(sQLiteDatabase, i, Impl.COLUMN_MEDIAPROVIDER_URI, "TEXT");
                    a(sQLiteDatabase, i, Impl.COLUMN_DELETED, "BOOLEAN NOT NULL DEFAULT 0");
                case R.styleable.AppCompatTheme_ratingBarStyleIndicator:
                    a(sQLiteDatabase, i, Impl.COLUMN_ERROR_MSG, "TEXT");
                case R.styleable.AppCompatTheme_ratingBarStyleSmall:
                    a(sQLiteDatabase, i, Impl.COLUMN_ALLOW_METERED, "INTEGER NOT NULL DEFAULT 1");
                case R.styleable.AppCompatTheme_seekBarStyle:
                    a(sQLiteDatabase, i, Impl.COLUMN_ALLOW_WRITE, "BOOLEAN NOT NULL DEFAULT 0");
                case R.styleable.AppCompatTheme_spinnerStyle:
                    a(sQLiteDatabase, i, y.j, "INTEGER DEFAULT 0");
                    a(sQLiteDatabase, i, y.i, "INTEGER DEFAULT 0");
                    a(sQLiteDatabase, i, y.e, "INTEGER DEFAULT 0");
                    a(sQLiteDatabase, i, y.h, "INTEGER NOT NULL DEFAULT -1");
                    a(sQLiteDatabase, i, y.g, "TEXT");
                    f(sQLiteDatabase);
                case R.styleable.AppCompatTheme_switchStyle:
                    a(sQLiteDatabase, i, Impl.COLUMN_ADDITION_VIP_SPEED, "INTEGER DEFAULT 0");
                    a(sQLiteDatabase, i, Impl.COLUMN_CID, "TEXT");
                    a(sQLiteDatabase, i, Impl.COLUMN_GCID, "TEXT");
                    a(sQLiteDatabase, i, Impl.COLUMN_BT_SELECT_SET, "TEXT");
                    a(sQLiteDatabase, i, Impl.COLUMN_IS_VIP_SPEEDUP, "INTEGER DEFAULT 0");
                    a(sQLiteDatabase, i, Impl.COLUMN_IS_LX_SPEEDUP, "INTEGER DEFAULT 0");
                    a(sQLiteDatabase, i, Impl.COLUMN_CREATE_TIME, "INTEGER DEFAULT 0");
                    a(sQLiteDatabase, i, Impl.COLUMN_DOWNLOAD_DURATION, "INTEGER DEFAULT 0");
                    g(sQLiteDatabase);
                case 112:
                    a(sQLiteDatabase, i, Impl.COLUMN_ALLOW_AUTO_RESUME, "INTEGER DEFAULT 0");
                    a(sQLiteDatabase, i, Impl.COLUMN_APK_PACKAGE, "TEXT");
                    a(sQLiteDatabase, i, Impl.COLUMN_APK_VERSION, "INTEGER DEFAULT 0");
                case 113:
                    a(sQLiteDatabase, i, Impl.COLUMN_VIP_RECEIVE_SIZE, "INTEGER DEFAULT 0");
                    a(sQLiteDatabase, i, Impl.COLUMN_VIP_STATUS, "INTEGER DEFAULT 190");
                    a(sQLiteDatabase, i, Impl.COLUMN_ADDITION_LX_SPEED, "INTEGER DEFAULT 0");
                    a(sQLiteDatabase, i, Impl.COLUMN_LX_RECEIVE_SIZE, "INTEGER DEFAULT 0");
                    a(sQLiteDatabase, i, Impl.COLUMN_LX_STATUS, "INTEGER DEFAULT 190");
                    a(sQLiteDatabase, i, Impl.COLUMN_LX_PROGRESS, "REAL DEFAULT 0");
                    a(sQLiteDatabase, i, Impl.COLUMN_P2P_SPEED, "INTEGER DEFAULT 0");
                    a(sQLiteDatabase, i, Impl.COLUMN_P2P_RECEIVE_SIZE, "INTEGER DEFAULT 0");
                    a(sQLiteDatabase, i, Impl.COLUMN_P2S_RECEIVE_SIZE, "INTEGER DEFAULT 0");
                    a(sQLiteDatabase, i, Impl.COLUMN_ORIGIN_RECEIVE_SIZE, "INTEGER DEFAULT 0");
                    a(sQLiteDatabase, i, Impl.COLUMN_TASK_TYPE, "INTEGER DEFAULT 0");
                    a(sQLiteDatabase, v, Impl.COLUMN_VIP_RECEIVE_SIZE, "INTEGER DEFAULT 0");
                    a(sQLiteDatabase, v, Impl.COLUMN_VIP_STATUS, "INTEGER DEFAULT 190");
                    a(sQLiteDatabase, v, Impl.COLUMN_ADDITION_LX_SPEED, "INTEGER DEFAULT 0");
                    a(sQLiteDatabase, v, Impl.COLUMN_LX_RECEIVE_SIZE, "INTEGER DEFAULT 0");
                    a(sQLiteDatabase, v, Impl.COLUMN_LX_STATUS, "INTEGER DEFAULT 190");
                    a(sQLiteDatabase, v, Impl.COLUMN_LX_PROGRESS, "REAL DEFAULT 0");
                    a(sQLiteDatabase, v, Impl.COLUMN_P2P_SPEED, "INTEGER DEFAULT 0");
                    a(sQLiteDatabase, v, Impl.COLUMN_P2P_RECEIVE_SIZE, "INTEGER DEFAULT 0");
                    a(sQLiteDatabase, v, Impl.COLUMN_P2S_RECEIVE_SIZE, "INTEGER DEFAULT 0");
                    a(sQLiteDatabase, v, Impl.COLUMN_ORIGIN_RECEIVE_SIZE, "INTEGER DEFAULT 0");
                case 114:
                    a(sQLiteDatabase, i, Impl.COLUMN_GROUP_ID, "INTEGER DEFAULT 0");
                case 115:
                    a(sQLiteDatabase, i, Impl.COLUMN_RES_TOTAL, "INTEGER DEFAULT 0");
                    a(sQLiteDatabase, i, Impl.COLUMN_RES_USED_TOTAL, "INTEGER DEFAULT 0");
                case h:
                    a(sQLiteDatabase, i, Impl.COLUMN_XL_ORIGIN, "TEXT");
                default:
                    throw new IllegalStateException(new StringBuilder("Don't know how to upgrade to ").append(i).toString());
            }
        }

        private void b(SQLiteDatabase sQLiteDatabase) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(Impl.COLUMN_CURRENT_BYTES, Integer.valueOf(0));
            a(sQLiteDatabase, contentValues);
            contentValues.put(d.b, Integer.valueOf(-1));
            a(sQLiteDatabase, contentValues);
            contentValues.put(WebBrowserActivity.EXTRA_TITLE, com.umeng.a.d);
            a(sQLiteDatabase, contentValues);
            contentValues.put(Impl.COLUMN_DESCRIPTION, com.umeng.a.d);
            a(sQLiteDatabase, contentValues);
        }

        private void a(SQLiteDatabase sQLiteDatabase, ContentValues contentValues) {
            sQLiteDatabase.update(i, contentValues, ((String) ((Entry) contentValues.valueSet().iterator().next()).getKey()) + " is null", null);
            contentValues.clear();
        }

        private void c(SQLiteDatabase sQLiteDatabase) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(Impl.COLUMN_IS_VISIBLE_IN_DOWNLOADS_UI, Boolean.valueOf(false));
            sQLiteDatabase.update(i, contentValues, "destination != 0", null);
        }

        private void a(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3) {
            sQLiteDatabase.execSQL(new StringBuilder("ALTER TABLE ").append(str).append(" ADD COLUMN ").append(str2).append(" ").append(str3).toString());
        }

        private void d(SQLiteDatabase sQLiteDatabase) {
            try {
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS xl_downloads");
                sQLiteDatabase.execSQL("CREATE TABLE xl_downloads(_id INTEGER PRIMARY KEY AUTOINCREMENT,uri TEXT, method INTEGER, entity TEXT, no_integrity BOOLEAN, hint TEXT, otaupdate BOOLEAN, _data TEXT, mimetype TEXT, destination INTEGER, no_system BOOLEAN, visibility INTEGER, control INTEGER, status INTEGER, numfailed INTEGER, lastmod BIGINT, notificationpackage TEXT, notificationclass TEXT, notificationextras TEXT, cookiedata TEXT, useragent TEXT, referer TEXT, total_bytes INTEGER, current_bytes INTEGER, etag TEXT, uid INTEGER, otheruid INTEGER, title TEXT, description TEXT, scanned BOOLEAN);");
            } catch (SQLException e) {
                am.d(DownloadManager.LOG_TAG, "couldn't create table in downloads database");
                throw e;
            }
        }

        private void e(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS request_headers");
            sQLiteDatabase.execSQL("CREATE TABLE request_headers(id INTEGER PRIMARY KEY AUTOINCREMENT,download_id INTEGER NOT NULL,header TEXT NOT NULL,value TEXT NOT NULL);");
        }

        private void f(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS xl_config");
            sQLiteDatabase.execSQL("CREATE TABLE xl_config(id INTEGER PRIMARY KEY AUTOINCREMENT,_key TEXT NOT NULL,_value TEXT NOT NULL);");
            ContentValues contentValues = new ContentValues();
            contentValues.put(a, com.xunlei.download.proguard.y.a.a);
            contentValues.put(b, WeiboAuthException.DEFAULT_AUTH_ERROR_CODE);
            sQLiteDatabase.insert(t, null, contentValues);
            contentValues = new ContentValues();
            contentValues.put(a, com.xunlei.download.proguard.y.a.b);
            contentValues.put(b, Long.valueOf(ab.a(DownloadProvider.this.getContext(), DownloadManager.KEY_RECOMMENDED_MAX_BYTES_OVER_MOBILE, (long) DownloadManager.RECOMMENDED_MAX_BYTES_OVERMOBILE)));
            sQLiteDatabase.insert(t, null, contentValues);
        }

        private void g(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS xl_bt_sub_task");
            sQLiteDatabase.execSQL("CREATE TABLE xl_bt_sub_task(_id INTEGER PRIMARY KEY AUTOINCREMENT,bt_parent_id INTEGER NOT NULL,bt_sub_index INTEGER NOT NULL,title TEXT, _data TEXT, mimetype TEXT, total_bytes INTEGER, current_bytes INTEGER, download_speed INTEGER DEFAULT 0, p2s_speed INTEGER DEFAULT 0, origin_speed INTEGER DEFAULT 0, addition_vip_speed INTEGER DEFAULT 0, cid TEXT, gcid TEXT, status INTEGER, errorMsg TEXT, bt_sub_is_selected INTEGER );");
        }

        public final void a(SQLiteDatabase sQLiteDatabase) {
            try {
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS xl_downloads");
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS request_headers");
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS xl_config");
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS xl_bt_sub_task");
            } catch (Throwable e) {
                DownloadProvider.this.d();
                am.a(DownloadManager.LOG_TAG, "couldn't delete table in downloads database.", e);
            }
        }
    }

    static class b {
        public static final String c = " AND ";
        public static final String d = " OR ";
        public StringBuilder a;
        public List<String> b;

        private b() {
            this.a = new StringBuilder();
            this.b = new ArrayList();
        }

        public <T> void a(String str, T... tArr) {
            a(str, c, tArr);
        }

        public <T> void a(String str, String str2, T... tArr) {
            if (str != null && str.length() != 0) {
                if (this.a.length() != 0) {
                    this.a.append(str2);
                }
                this.a.append(SocializeConstants.OP_OPEN_PAREN);
                this.a.append(str);
                this.a.append(SocializeConstants.OP_CLOSE_PAREN);
                if (tArr != null) {
                    for (Object obj : tArr) {
                        this.b.add(obj.toString());
                    }
                }
            }
        }

        public String a() {
            return this.a.toString();
        }

        public String[] b() {
            return (String[]) this.b.toArray(new String[this.b.size()]);
        }
    }

    static final class c {
        static final String a = "StatTracker";
        static final String b = "null";
        static final String[] c;
        int d;
        ArrayList<a> e;

        static final class a {
            long a;
            int b;
            long c;
            int d;
            String e;
            String f;

            a() {
                this.e = b;
                this.f = b;
            }
        }

        static {
            c = new String[]{DownloadManager.COLUMN_ID, Impl.COLUMN_STATUS, Impl.COLUMN_URI, d.b, Impl.COLUMN_TASK_TYPE, Impl.COLUMN_ERROR_MSG, Impl.COLUMN_XL_ORIGIN};
        }

        c(long j, int i, int i2, String str, ContentValues contentValues) {
            this.e = new ArrayList();
            this.d = i;
            a aVar = new a();
            aVar.b = i2;
            aVar.a = ((long) XlTaskHelper.a(j, str)) & 4294967295L;
            if (contentValues != null) {
                try {
                    aVar.c = contentValues.getAsLong(d.b).longValue();
                    aVar.d = contentValues.getAsInteger(Impl.COLUMN_TASK_TYPE).intValue();
                    aVar.e = contentValues.getAsString(Impl.COLUMN_ERROR_MSG);
                    aVar.f = contentValues.getAsString(Impl.COLUMN_XL_ORIGIN);
                } catch (Throwable e) {
                    e.printStackTrace();
                    am.a(e);
                }
            }
            this.e.add(aVar);
        }

        c(int i, SQLiteDatabase sQLiteDatabase, b bVar, ContentValues contentValues) {
            Throwable e;
            this.e = new ArrayList();
            if (bVar != null) {
                this.d = i;
                Cursor query;
                try {
                    query = sQLiteDatabase.query(i, c, bVar.a(), bVar.b(), null, null, null);
                    if (query != null) {
                        try {
                            if (query.moveToFirst()) {
                                while (!query.isAfterLast()) {
                                    if (i != query.getInt(query.getColumnIndex(Impl.COLUMN_STATUS))) {
                                        a aVar = new a();
                                        long j = query.getLong(query.getColumnIndex(DownloadManager.COLUMN_ID));
                                        aVar.a = ((long) XlTaskHelper.a(j, query.getString(query.getColumnIndex(Impl.COLUMN_URI)))) & 4294967295L;
                                        am.b(a, new StringBuilder("trackStatusChange id= ").append(j).append(", appseqid=").append(aVar.a).toString());
                                        aVar.b = query.getInt(query.getColumnIndex(Impl.COLUMN_STATUS));
                                        aVar.c = query.getLong(query.getColumnIndex(d.b));
                                        aVar.d = query.getInt(query.getColumnIndex(Impl.COLUMN_TASK_TYPE));
                                        aVar.e = query.getString(query.getColumnIndex(Impl.COLUMN_ERROR_MSG));
                                        if (contentValues != null) {
                                            Long asLong = contentValues.getAsLong(d.b);
                                            if (asLong != null) {
                                                aVar.c = asLong.longValue();
                                            }
                                            String asString = contentValues.getAsString(Impl.COLUMN_ERROR_MSG);
                                            if (asString != null) {
                                                aVar.e = asString;
                                            }
                                        }
                                        aVar.f = query.getString(query.getColumnIndex(Impl.COLUMN_XL_ORIGIN));
                                        this.e.add(aVar);
                                    }
                                    query.moveToNext();
                                }
                            }
                        } catch (Exception e2) {
                            e = e2;
                            try {
                                e.printStackTrace();
                                am.a(e);
                                if (query == null) {
                                    return;
                                }
                            } catch (Throwable th) {
                                e = th;
                                if (query != null) {
                                    query.close();
                                }
                                throw e;
                            }
                            query.close();
                        }
                    }
                    if (query != null) {
                        query.close();
                    }
                } catch (Exception e3) {
                    e = e3;
                    query = null;
                    e.printStackTrace();
                    am.a(e);
                    if (query == null) {
                        return;
                    }
                    query.close();
                } catch (Throwable th2) {
                    e = th2;
                    query = null;
                    if (query != null) {
                        query.close();
                    }
                    throw e;
                }
            }
        }

        final void a(Context context) {
            Iterator it = this.e.iterator();
            while (it.hasNext()) {
                StatHelper.instance(context).trackStatusChange(a((a) it.next()));
            }
        }

        private String a(a aVar) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(new StringBuilder("AppSeqId=").append(aVar.a).toString());
            stringBuilder.append(new StringBuilder(",Status=").append(this.d).toString());
            stringBuilder.append(new StringBuilder(",OldStatus=").append(aVar.b).toString());
            if (TextUtils.isEmpty(aVar.e)) {
                aVar.e = b;
            }
            stringBuilder.append(new StringBuilder(",ErrMsg=").append(aVar.e).toString());
            if (aVar.c < 0) {
                aVar.c = 0;
            }
            stringBuilder.append(new StringBuilder(",FileSize=").append(aVar.c).toString());
            stringBuilder.append(new StringBuilder(",TaskType=").append(aVar.d).toString());
            if (TextUtils.isEmpty(aVar.f)) {
                aVar.f = b;
            }
            stringBuilder.append(new StringBuilder(",TaskOrigin=").append(aVar.f).toString());
            return stringBuilder.toString();
        }
    }

    public DownloadProvider() {
        this.F = null;
        this.G = -1;
        this.H = -1;
    }

    static {
        int i = 0;
        l = new UriMatcher(-1);
        c = Uri.parse("content://xldownloads/xl_bt_sub_task");
        d = Uri.parse("content://xldownloads/xl_config");
        e = "xldownloads";
        z = new Uri[]{Impl.CONTENT_URI, Impl.ALL_DOWNLOADS_CONTENT_URI};
        A = new String[]{DownloadManager.COLUMN_ID, Impl.COLUMN_APP_DATA, Impl._DATA, Impl.COLUMN_MIME_TYPE, Impl.COLUMN_VISIBILITY, Impl.COLUMN_DESTINATION, Impl.COLUMN_CONTROL, Impl.COLUMN_STATUS, Impl.COLUMN_LAST_MODIFICATION, Impl.COLUMN_NOTIFICATION_PACKAGE, Impl.COLUMN_NOTIFICATION_CLASS, d.b, Impl.COLUMN_CURRENT_BYTES, WebBrowserActivity.EXTRA_TITLE, Impl.COLUMN_DESCRIPTION, Impl.COLUMN_URI, Impl.COLUMN_IS_VISIBLE_IN_DOWNLOADS_UI, Impl.COLUMN_FILE_NAME_HINT, Impl.COLUMN_MEDIAPROVIDER_URI, Impl.COLUMN_DELETED, Impl.COLUMN_ERROR_MSG, "_display_name", "_size", y.j, y.e, y.i, y.h, y.g, Impl.COLUMN_BT_SELECT_SET, Impl.COLUMN_ADDITION_VIP_SPEED, Impl.COLUMN_CID, Impl.COLUMN_GCID, Impl.COLUMN_IS_VIP_SPEEDUP, Impl.COLUMN_IS_LX_SPEEDUP, Impl.COLUMN_CREATE_TIME, Impl.COLUMN_DOWNLOAD_DURATION, Impl.COLUMN_ALLOW_AUTO_RESUME, Impl.COLUMN_APK_PACKAGE, Impl.COLUMN_APK_VERSION, Impl.COLUMN_BT_INFO_HASH, Impl.COLUMN_GROUP_ID, Impl.COLUMN_TASK_TYPE};
        B = new HashSet();
        for (int i2 = 0; i2 < A.length; i2++) {
            B.add(A[i2]);
        }
        HashMap hashMap = new HashMap();
        C = hashMap;
        hashMap.put("_display_name", "title AS _display_name");
        C.put("_size", "total_bytes AS _size");
        D = new ArrayList();
        String[] strArr = DownloadManager.UNDERLYING_COLUMNS;
        int length = strArr.length;
        while (i < length) {
            D.add(strArr[i]);
            i++;
        }
    }

    public void attachInfo(Context context, ProviderInfo providerInfo) {
        super.attachInfo(context, providerInfo);
        a(providerInfo.authority);
    }

    public static void a(String str) {
        e = str;
        d = Uri.parse(new StringBuilder("content://").append(e).append("/xl_config").toString());
        c = Uri.parse(new StringBuilder("content://").append(e).append("/xl_bt_sub_task").toString());
        z = new Uri[]{Uri.parse(new StringBuilder("content://").append(e).append("/my_downloads").toString()), Uri.parse(new StringBuilder("content://").append(e).append("/all_downloads").toString())};
        l.addURI(e, "my_downloads", m);
        l.addURI(e, "my_downloads/#", n);
        l.addURI(e, "all_downloads", o);
        l.addURI(e, "all_downloads/#", p);
        l.addURI(e, "my_downloads/#/headers", q);
        l.addURI(e, "all_downloads/#/headers", q);
        l.addURI(e, "xl_task_group/#/#/headers", q);
        l.addURI(e, "download", m);
        l.addURI(e, "download/#", n);
        l.addURI(e, "download/#/headers", q);
        l.addURI(e, "public_downloads/#", r);
        l.addURI(e, t, s);
        l.addURI(e, v, u);
        l.addURI(e, "xl_task_group/#", w);
        l.addURI(e, "xl_task_group/#/#", x);
    }

    private synchronized SQLiteOpenHelper c() {
        if (this.F == null) {
            File taskDBFile = DownloadManager.getInstanceFor(getContext()).getTaskDBFile(getContext());
            am.b(DownloadManager.LOG_TAG, new StringBuilder("getTaskDBFile file : ").append(taskDBFile).toString());
            if (taskDBFile == null || taskDBFile.isDirectory()) {
                this.F = new a(getContext());
            } else {
                this.F = new a(new f(this, getContext(), taskDBFile));
            }
        }
        return this.F;
    }

    private synchronized void d() {
        if (this.F != null) {
            this.F.close();
            this.F = null;
            am.b(DownloadManager.LOG_TAG, "closeDatabaseHelper");
        }
    }

    public boolean onCreate() {
        if (this.f == null) {
            this.f = new q(getContext());
        }
        this.E = new Handler();
        this.G = 1000;
        try {
            ApplicationInfo applicationInfo = getContext().getPackageManager().getApplicationInfo("com.android.defcontainer", 0);
        } catch (Throwable e) {
            am.b(DownloadManager.LOG_TAG, "Could not get ApplicationInfo for com.android.defconatiner", e);
            applicationInfo = null;
        }
        if (applicationInfo != null) {
            this.H = applicationInfo.uid;
        }
        Context context = getContext();
        try {
            context.startService(new Intent(context, DownloadService.class));
        } catch (Throwable e2) {
            e2.printStackTrace();
            am.a(e2);
        }
        this.I = s.a(getContext());
        return true;
    }

    public String getType(Uri uri) {
        int match = l.match(uri);
        switch (match) {
            case m:
            case o:
            case w:
                return j;
            case n:
            case p:
            case r:
            case x:
                String a = a(uri, match);
                a = DatabaseUtils.stringForQuery(c().getReadableDatabase(), "SELECT mimetype FROM xl_downloads WHERE _id = ?", new String[]{a});
                return TextUtils.isEmpty(a) ? k : a;
            default:
                am.e(DownloadManager.LOG_TAG, new StringBuilder("calling getType on an unknown URI: ").append(uri).toString());
                throw new IllegalArgumentException(new StringBuilder("Unknown URI: ").append(uri).toString());
        }
    }

    private TaskType b(String str) {
        String b = z.b(getContext(), str);
        if (b.startsWith("http://") || b.startsWith("https://")) {
            return TaskType.HTTP;
        }
        if (b.startsWith("magnet:?")) {
            return TaskType.MAGNET;
        }
        if (b.startsWith("file:///")) {
            return TaskType.BT;
        }
        if (b.startsWith("ftp://")) {
            return TaskType.FTP;
        }
        if (b.startsWith("ed2k://")) {
            return TaskType.ED2K;
        }
        if (b.startsWith("cid://")) {
            return TaskType.CID;
        }
        return b.startsWith(DownloadManager.TASK_GROUP_URI_PREFIX) ? TaskType.GROUP : TaskType.UNKOWN;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.net.Uri insert(android.net.Uri r19, android.content.ContentValues r20) {
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.downloads.DownloadProvider.insert(android.net.Uri, android.content.ContentValues):android.net.Uri");
        /*
        this = this;
        r4 = l;	 Catch:{ Exception -> 0x00cb }
        r0 = r19;
        r12 = r4.match(r0);	 Catch:{ Exception -> 0x00cb }
        r4 = 100;
        if (r12 != r4) goto L_0x0063;
    L_0x000c:
        r4 = r18.c();	 Catch:{ Exception -> 0x00cb }
        r4 = r4.getWritableDatabase();	 Catch:{ Exception -> 0x00cb }
        r5 = "xl_bt_sub_task";
        r6 = 0;
        r0 = r20;
        r4 = r4.insert(r5, r6, r0);	 Catch:{ Exception -> 0x00cb }
        r6 = -1;
        r6 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r6 != 0) goto L_0x002f;
    L_0x0024:
        r4 = "DownloadManager";
        r5 = "couldn't insert into bt sub task database";
        com.xunlei.download.proguard.am.b(r4, r5);	 Catch:{ Exception -> 0x00cb }
        r4 = 0;
    L_0x002e:
        return r4;
    L_0x002f:
        r6 = "DownloadManager";
        r7 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00cb }
        r8 = "insert into bt sub task index=";
        r7.<init>(r8);	 Catch:{ Exception -> 0x00cb }
        r8 = "bt_sub_index";
        r0 = r20;
        r8 = r0.getAsInteger(r8);	 Catch:{ Exception -> 0x00cb }
        r7 = r7.append(r8);	 Catch:{ Exception -> 0x00cb }
        r7 = r7.toString();	 Catch:{ Exception -> 0x00cb }
        com.xunlei.download.proguard.am.b(r6, r7);	 Catch:{ Exception -> 0x00cb }
        r6 = r18.getContext();	 Catch:{ Exception -> 0x00cb }
        r6 = r6.getContentResolver();	 Catch:{ Exception -> 0x00cb }
        r7 = 0;
        r0 = r19;
        r6.notifyChange(r0, r7);	 Catch:{ Exception -> 0x00cb }
        r6 = c;	 Catch:{ Exception -> 0x00cb }
        r4 = android.content.ContentUris.withAppendedId(r6, r4);	 Catch:{ Exception -> 0x00cb }
        goto L_0x002e;
    L_0x0063:
        r4 = 9;
        if (r12 != r4) goto L_0x0091;
    L_0x0067:
        r4 = r18.c();	 Catch:{ Exception -> 0x00cb }
        r4 = r4.getWritableDatabase();	 Catch:{ Exception -> 0x00cb }
        r5 = "xl_config";
        r6 = 0;
        r0 = r20;
        r4 = r4.insert(r5, r6, r0);	 Catch:{ Exception -> 0x00cb }
        r6 = -1;
        r6 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r6 != 0) goto L_0x008a;
    L_0x007f:
        r4 = "DownloadManager";
        r5 = "couldn't insert into xl_config database";
        com.xunlei.download.proguard.am.b(r4, r5);	 Catch:{ Exception -> 0x00cb }
        r4 = 0;
        goto L_0x002e;
    L_0x008a:
        r0 = r19;
        r4 = android.content.ContentUris.withAppendedId(r0, r4);	 Catch:{ Exception -> 0x00cb }
        goto L_0x002e;
    L_0x0091:
        r0 = r18;
        r1 = r20;
        r0.c(r1);	 Catch:{ Exception -> 0x00cb }
        r4 = 1;
        if (r12 == r4) goto L_0x00d8;
    L_0x009b:
        r4 = "DownloadManager";
        r5 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00cb }
        r6 = "calling insert on an unknown/invalid URI: ";
        r5.<init>(r6);	 Catch:{ Exception -> 0x00cb }
        r0 = r19;
        r5 = r5.append(r0);	 Catch:{ Exception -> 0x00cb }
        r5 = r5.toString();	 Catch:{ Exception -> 0x00cb }
        com.xunlei.download.proguard.am.b(r4, r5);	 Catch:{ Exception -> 0x00cb }
        r4 = new java.lang.IllegalArgumentException;	 Catch:{ Exception -> 0x00cb }
        r5 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00cb }
        r6 = "Unknown/Invalid URI ";
        r5.<init>(r6);	 Catch:{ Exception -> 0x00cb }
        r0 = r19;
        r5 = r5.append(r0);	 Catch:{ Exception -> 0x00cb }
        r5 = r5.toString();	 Catch:{ Exception -> 0x00cb }
        r4.<init>(r5);	 Catch:{ Exception -> 0x00cb }
        throw r4;	 Catch:{ Exception -> 0x00cb }
    L_0x00cb:
        r4 = move-exception;
        r18.d();
        r4.printStackTrace();
        com.xunlei.download.proguard.am.a(r4);
        r4 = 0;
        goto L_0x002e;
    L_0x00d8:
        r4 = "DownloadManager";
        r5 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00cb }
        r6 = "insert() uri = ";
        r5.<init>(r6);	 Catch:{ Exception -> 0x00cb }
        r0 = r19;
        r5 = r5.append(r0);	 Catch:{ Exception -> 0x00cb }
        r6 = ", values: ";
        r5 = r5.append(r6);	 Catch:{ Exception -> 0x00cb }
        r0 = r20;
        r5 = r5.append(r0);	 Catch:{ Exception -> 0x00cb }
        r5 = r5.toString();	 Catch:{ Exception -> 0x00cb }
        com.xunlei.download.proguard.am.b(r4, r5);	 Catch:{ Exception -> 0x00cb }
        r4 = "uri";
        r0 = r20;
        r4 = r0.getAsString(r4);	 Catch:{ Exception -> 0x00cb }
        r10 = r4.trim();	 Catch:{ Exception -> 0x00cb }
        r0 = r18;
        r7 = r0.b(r10);	 Catch:{ Exception -> 0x00cb }
        r4 = "uri";
        r0 = r20;
        r0.put(r4, r10);	 Catch:{ Exception -> 0x00cb }
        r4 = com.xunlei.download.DownloadManager.TaskType.BT;	 Catch:{ Exception -> 0x00cb }
        if (r7 != r4) goto L_0x016b;
    L_0x011c:
        r4 = "etag";
        r0 = r20;
        r4 = r0.getAsString(r4);	 Catch:{ Exception -> 0x00cb }
        r4 = android.text.TextUtils.isEmpty(r4);	 Catch:{ Exception -> 0x00cb }
        if (r4 == 0) goto L_0x016b;
    L_0x012b:
        r4 = "etag";
        r0 = r20;
        r4 = r0.getAsString(r4);	 Catch:{ Exception -> 0x00cb }
        r5 = android.text.TextUtils.isEmpty(r4);	 Catch:{ Exception -> 0x00cb }
        if (r5 == 0) goto L_0x0146;
    L_0x013a:
        r4 = "DownloadManager";
        r5 = "Insert BT Task Without InfoHash";
        com.xunlei.download.proguard.am.d(r4, r5);	 Catch:{ Exception -> 0x00cb }
        r4 = 0;
        goto L_0x002e;
    L_0x0146:
        r5 = r4.length();	 Catch:{ Exception -> 0x00cb }
        r6 = 40;
        if (r5 == r6) goto L_0x016b;
    L_0x014e:
        r5 = "DownloadManager";
        r6 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00cb }
        r7 = "Insert BT Task With bad InfoHash length =";
        r6.<init>(r7);	 Catch:{ Exception -> 0x00cb }
        r4 = r4.length();	 Catch:{ Exception -> 0x00cb }
        r4 = r6.append(r4);	 Catch:{ Exception -> 0x00cb }
        r4 = r4.toString();	 Catch:{ Exception -> 0x00cb }
        com.xunlei.download.proguard.am.d(r5, r4);	 Catch:{ Exception -> 0x00cb }
        r4 = 0;
        goto L_0x002e;
    L_0x016b:
        r0 = r18;
        r1 = r19;
        r2 = r20;
        r4 = r0.a(r1, r2, r7);	 Catch:{ Exception -> 0x00cb }
        r6 = "DownloadManager";
        r8 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00cb }
        r9 = "in insert url=";
        r8.<init>(r9);	 Catch:{ Exception -> 0x00cb }
        r9 = r19.toString();	 Catch:{ Exception -> 0x00cb }
        r8 = r8.append(r9);	 Catch:{ Exception -> 0x00cb }
        r9 = ", existsId=";
        r8 = r8.append(r9);	 Catch:{ Exception -> 0x00cb }
        r8 = r8.append(r4);	 Catch:{ Exception -> 0x00cb }
        r8 = r8.toString();	 Catch:{ Exception -> 0x00cb }
        com.xunlei.download.proguard.am.b(r6, r8);	 Catch:{ Exception -> 0x00cb }
        r8 = 0;
        r6 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1));
        if (r6 <= 0) goto L_0x01ae;
    L_0x01a0:
        r6 = r18.getContext();	 Catch:{ Exception -> 0x00cb }
        r6 = com.xunlei.download.DownloadManager.getInstanceFor(r6);	 Catch:{ Exception -> 0x00cb }
        r4 = r6.getDownloadUri(r4);	 Catch:{ Exception -> 0x00cb }
        goto L_0x002e;
    L_0x01ae:
        r11 = new android.content.ContentValues;	 Catch:{ Exception -> 0x00cb }
        r11.<init>();	 Catch:{ Exception -> 0x00cb }
        r4 = "uri";
        r0 = r20;
        c(r4, r0, r11);	 Catch:{ Exception -> 0x00cb }
        r4 = "entity";
        r0 = r20;
        c(r4, r0, r11);	 Catch:{ Exception -> 0x00cb }
        r4 = "no_integrity";
        r0 = r20;
        b(r4, r0, r11);	 Catch:{ Exception -> 0x00cb }
        r4 = "hint";
        r0 = r20;
        c(r4, r0, r11);	 Catch:{ Exception -> 0x00cb }
        r4 = "mimetype";
        r0 = r20;
        c(r4, r0, r11);	 Catch:{ Exception -> 0x00cb }
        r4 = "is_public_api";
        r0 = r20;
        b(r4, r0, r11);	 Catch:{ Exception -> 0x00cb }
        r4 = "bt_select_set";
        r0 = r20;
        c(r4, r0, r11);	 Catch:{ Exception -> 0x00cb }
        r4 = "is_lx_speedup";
        r0 = r20;
        b(r4, r0, r11);	 Catch:{ Exception -> 0x00cb }
        r4 = "is_vip_speedup";
        r0 = r20;
        b(r4, r0, r11);	 Catch:{ Exception -> 0x00cb }
        r4 = "allow_auto_resume";
        r0 = r20;
        b(r4, r0, r11);	 Catch:{ Exception -> 0x00cb }
        r4 = "xunlei_spdy";
        r0 = r20;
        b(r4, r0, r11);	 Catch:{ Exception -> 0x00cb }
        r4 = "group_id";
        r0 = r20;
        a(r4, r0, r11);	 Catch:{ Exception -> 0x00cb }
        r4 = "xl_origin";
        r0 = r20;
        c(r4, r0, r11);	 Catch:{ Exception -> 0x00cb }
        r4 = com.xunlei.download.DownloadManager.TaskType.BT;	 Catch:{ Exception -> 0x00cb }
        if (r7 != r4) goto L_0x0227;
    L_0x021f:
        r4 = "etag";
        r0 = r20;
        c(r4, r0, r11);	 Catch:{ Exception -> 0x00cb }
    L_0x0227:
        r4 = "is_public_api";
        r0 = r20;
        r4 = r0.getAsBoolean(r4);	 Catch:{ Exception -> 0x00cb }
        r5 = java.lang.Boolean.TRUE;	 Catch:{ Exception -> 0x00cb }
        if (r4 != r5) goto L_0x0280;
    L_0x0234:
        r4 = 1;
        r6 = r4;
    L_0x0236:
        r4 = "destination";
        r0 = r20;
        r4 = r0.getAsInteger(r4);	 Catch:{ Exception -> 0x00cb }
        if (r4 == 0) goto L_0x02ca;
    L_0x0241:
        r5 = r18.getContext();	 Catch:{ Exception -> 0x00cb }
        r8 = "android.permission.ACCESS_DOWNLOAD_MANAGER_ADVANCED";
        r5 = r5.checkCallingPermission(r8);	 Catch:{ Exception -> 0x00cb }
        if (r5 == 0) goto L_0x0283;
    L_0x024e:
        r5 = r4.intValue();	 Catch:{ Exception -> 0x00cb }
        r8 = 1;
        if (r5 == r8) goto L_0x0263;
    L_0x0255:
        r5 = r4.intValue();	 Catch:{ Exception -> 0x00cb }
        r8 = 3;
        if (r5 == r8) goto L_0x0263;
    L_0x025c:
        r5 = r4.intValue();	 Catch:{ Exception -> 0x00cb }
        r8 = 5;
        if (r5 != r8) goto L_0x0283;
    L_0x0263:
        r5 = new java.lang.SecurityException;	 Catch:{ Exception -> 0x00cb }
        r6 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00cb }
        r7 = "setting destination to : ";
        r6.<init>(r7);	 Catch:{ Exception -> 0x00cb }
        r4 = r6.append(r4);	 Catch:{ Exception -> 0x00cb }
        r6 = " not allowed, unless PERMISSION_ACCESS_ADVANCED is granted";
        r4 = r4.append(r6);	 Catch:{ Exception -> 0x00cb }
        r4 = r4.toString();	 Catch:{ Exception -> 0x00cb }
        r5.<init>(r4);	 Catch:{ Exception -> 0x00cb }
        throw r5;	 Catch:{ Exception -> 0x00cb }
    L_0x0280:
        r4 = 0;
        r6 = r4;
        goto L_0x0236;
    L_0x0283:
        r5 = r18.getContext();	 Catch:{ Exception -> 0x00cb }
        r8 = "android.permission.DOWNLOAD_CACHE_NON_PURGEABLE";
        r5 = r5.checkCallingPermission(r8);	 Catch:{ Exception -> 0x00cb }
        if (r5 != 0) goto L_0x04ef;
    L_0x0290:
        r5 = 1;
    L_0x0291:
        if (r6 == 0) goto L_0x02a1;
    L_0x0293:
        r8 = r4.intValue();	 Catch:{ Exception -> 0x00cb }
        r9 = 2;
        if (r8 != r9) goto L_0x02a1;
    L_0x029a:
        if (r5 == 0) goto L_0x02a1;
    L_0x029c:
        r4 = 1;
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ Exception -> 0x00cb }
    L_0x02a1:
        r5 = r4.intValue();	 Catch:{ Exception -> 0x00cb }
        r8 = 4;
        if (r5 != r8) goto L_0x04f2;
    L_0x02a8:
        r5 = r18.getContext();	 Catch:{ Exception -> 0x00cb }
        r8 = "android.permission.WRITE_EXTERNAL_STORAGE";
        r9 = android.os.Binder.getCallingPid();	 Catch:{ Exception -> 0x00cb }
        r13 = android.os.Binder.getCallingUid();	 Catch:{ Exception -> 0x00cb }
        r14 = "need WRITE_EXTERNAL_STORAGE permission to use DESTINATION_FILE_URI";
        r5.enforcePermission(r8, r9, r13, r14);	 Catch:{ Exception -> 0x00cb }
        r0 = r18;
        r1 = r20;
        r0.b(r1);	 Catch:{ Exception -> 0x00cb }
    L_0x02c4:
        r5 = "destination";
        r11.put(r5, r4);	 Catch:{ Exception -> 0x00cb }
    L_0x02ca:
        r5 = "visibility";
        r0 = r20;
        r5 = r0.getAsInteger(r5);	 Catch:{ Exception -> 0x00cb }
        if (r5 != 0) goto L_0x051d;
    L_0x02d5:
        r5 = r4.intValue();	 Catch:{ Exception -> 0x00cb }
        if (r5 != 0) goto L_0x0510;
    L_0x02db:
        r5 = "visibility";
        r8 = 1;
        r8 = java.lang.Integer.valueOf(r8);	 Catch:{ Exception -> 0x00cb }
        r11.put(r5, r8);	 Catch:{ Exception -> 0x00cb }
    L_0x02e6:
        r5 = "control";
        r0 = r20;
        a(r5, r0, r11);	 Catch:{ Exception -> 0x00cb }
        r5 = "scanned";
        r0 = r20;
        a(r5, r0, r11);	 Catch:{ Exception -> 0x00cb }
        r5 = "destination";
        r0 = r20;
        r5 = r0.getAsInteger(r5);	 Catch:{ Exception -> 0x00cb }
        r5 = r5.intValue();	 Catch:{ Exception -> 0x00cb }
        r8 = 6;
        if (r5 != r8) goto L_0x0525;
    L_0x0306:
        r5 = "status";
        r8 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        r8 = java.lang.Integer.valueOf(r8);	 Catch:{ Exception -> 0x00cb }
        r11.put(r5, r8);	 Catch:{ Exception -> 0x00cb }
        r5 = "total_bytes";
        r8 = "total_bytes";
        r0 = r20;
        r8 = r0.getAsLong(r8);	 Catch:{ Exception -> 0x00cb }
        r11.put(r5, r8);	 Catch:{ Exception -> 0x00cb }
        r5 = "current_bytes";
        r8 = 0;
        r8 = java.lang.Integer.valueOf(r8);	 Catch:{ Exception -> 0x00cb }
        r11.put(r5, r8);	 Catch:{ Exception -> 0x00cb }
        r5 = "notificationpackage";
        r8 = r18.getContext();	 Catch:{ Exception -> 0x00cb }
        r8 = r8.getApplicationContext();	 Catch:{ Exception -> 0x00cb }
        r8 = r8.getPackageName();	 Catch:{ Exception -> 0x00cb }
        r11.put(r5, r8);	 Catch:{ Exception -> 0x00cb }
        r5 = com.xunlei.download.DownloadManager.TaskType.UNKOWN;	 Catch:{ Exception -> 0x00cb }
        if (r7 == r5) goto L_0x03b0;
    L_0x0342:
        r5 = new java.io.File;	 Catch:{ Exception -> 0x00cb }
        r8 = "_data";
        r0 = r20;
        r8 = r0.getAsString(r8);	 Catch:{ Exception -> 0x00cb }
        r5.<init>(r8);	 Catch:{ Exception -> 0x00cb }
        r5 = android.net.Uri.fromFile(r5);	 Catch:{ Exception -> 0x00cb }
        r5 = r5.toString();	 Catch:{ Exception -> 0x00cb }
        r8 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00cb }
        r8.<init>();	 Catch:{ Exception -> 0x00cb }
        r9 = 0;
        r13 = java.io.File.separatorChar;	 Catch:{ Exception -> 0x00cb }
        r13 = r5.lastIndexOf(r13);	 Catch:{ Exception -> 0x00cb }
        r13 = r13 + 1;
        r5 = r5.substring(r9, r13);	 Catch:{ Exception -> 0x00cb }
        r5 = r8.append(r5);	 Catch:{ Exception -> 0x00cb }
        r8 = "{filename}";
        r5 = r5.append(r8);	 Catch:{ Exception -> 0x00cb }
        r5 = r5.toString();	 Catch:{ Exception -> 0x00cb }
        r8 = "hint";
        r11.put(r8, r5);	 Catch:{ Exception -> 0x00cb }
        r5 = "destination";
        r8 = 4;
        r8 = java.lang.Integer.valueOf(r8);	 Catch:{ Exception -> 0x00cb }
        r11.put(r5, r8);	 Catch:{ Exception -> 0x00cb }
        r5 = "task_type";
        r8 = r7.ordinal();	 Catch:{ Exception -> 0x00cb }
        r8 = java.lang.Integer.valueOf(r8);	 Catch:{ Exception -> 0x00cb }
        r11.put(r5, r8);	 Catch:{ Exception -> 0x00cb }
        r5 = com.xunlei.download.DownloadManager.TaskType.HTTP;	 Catch:{ Exception -> 0x00cb }
        if (r7 != r5) goto L_0x03a5;
    L_0x039c:
        r5 = "thunder://";
        r5 = r10.startsWith(r5);	 Catch:{ Exception -> 0x00cb }
        if (r5 == 0) goto L_0x03b0;
    L_0x03a5:
        r5 = "xunlei_spdy";
        r8 = 1;
        r8 = java.lang.Boolean.valueOf(r8);	 Catch:{ Exception -> 0x00cb }
        r11.put(r5, r8);	 Catch:{ Exception -> 0x00cb }
    L_0x03b0:
        r5 = "_data";
        r0 = r20;
        c(r5, r0, r11);	 Catch:{ Exception -> 0x00cb }
        r5 = "allow_write";
        r0 = r20;
        b(r5, r0, r11);	 Catch:{ Exception -> 0x00cb }
    L_0x03c0:
        r5 = "create_time";
        r8 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x00cb }
        r8 = java.lang.Long.valueOf(r8);	 Catch:{ Exception -> 0x00cb }
        r11.put(r5, r8);	 Catch:{ Exception -> 0x00cb }
        r0 = r18;
        r5 = r0.f;	 Catch:{ Exception -> 0x00cb }
        r8 = r5.a();	 Catch:{ Exception -> 0x00cb }
        r5 = "lastmod";
        r8 = java.lang.Long.valueOf(r8);	 Catch:{ Exception -> 0x00cb }
        r11.put(r5, r8);	 Catch:{ Exception -> 0x00cb }
        r5 = "notificationpackage";
        r0 = r20;
        r5 = r0.getAsString(r5);	 Catch:{ Exception -> 0x00cb }
        r8 = "notificationclass";
        r0 = r20;
        r8 = r0.getAsString(r8);	 Catch:{ Exception -> 0x00cb }
        if (r5 == 0) goto L_0x0416;
    L_0x03f4:
        if (r8 != 0) goto L_0x03f8;
    L_0x03f6:
        if (r6 == 0) goto L_0x0416;
    L_0x03f8:
        r9 = android.os.Binder.getCallingUid();	 Catch:{ Exception -> 0x00cb }
        if (r9 == 0) goto L_0x0408;
    L_0x03fe:
        r0 = r18;
        r13 = r0.f;	 Catch:{ NameNotFoundException -> 0x0665 }
        r9 = r13.a(r9, r5);	 Catch:{ NameNotFoundException -> 0x0665 }
        if (r9 == 0) goto L_0x0416;
    L_0x0408:
        r9 = "notificationpackage";
        r11.put(r9, r5);	 Catch:{ NameNotFoundException -> 0x0665 }
        if (r8 == 0) goto L_0x0416;
    L_0x0410:
        r5 = "notificationclass";
        r11.put(r5, r8);	 Catch:{ NameNotFoundException -> 0x0665 }
    L_0x0416:
        r5 = "notificationextras";
        r0 = r20;
        c(r5, r0, r11);	 Catch:{ Exception -> 0x00cb }
        r5 = "cookiedata";
        r0 = r20;
        c(r5, r0, r11);	 Catch:{ Exception -> 0x00cb }
        r5 = "useragent";
        r0 = r20;
        c(r5, r0, r11);	 Catch:{ Exception -> 0x00cb }
        r5 = "referer";
        r0 = r20;
        c(r5, r0, r11);	 Catch:{ Exception -> 0x00cb }
        r5 = r18.getContext();	 Catch:{ Exception -> 0x00cb }
        r8 = "android.permission.ACCESS_DOWNLOAD_MANAGER_ADVANCED";
        r5 = r5.checkCallingPermission(r8);	 Catch:{ Exception -> 0x00cb }
        if (r5 != 0) goto L_0x044b;
    L_0x0443:
        r5 = "otheruid";
        r0 = r20;
        a(r5, r0, r11);	 Catch:{ Exception -> 0x00cb }
    L_0x044b:
        r5 = "uid";
        r8 = android.os.Binder.getCallingUid();	 Catch:{ Exception -> 0x00cb }
        r8 = java.lang.Integer.valueOf(r8);	 Catch:{ Exception -> 0x00cb }
        r11.put(r5, r8);	 Catch:{ Exception -> 0x00cb }
        r5 = android.os.Binder.getCallingUid();	 Catch:{ Exception -> 0x00cb }
        if (r5 != 0) goto L_0x0467;
    L_0x045f:
        r5 = "uid";
        r0 = r20;
        a(r5, r0, r11);	 Catch:{ Exception -> 0x00cb }
    L_0x0467:
        r5 = "title";
        r8 = "";
        r0 = r20;
        a(r5, r0, r11, r8);	 Catch:{ Exception -> 0x00cb }
        r5 = "description";
        r8 = "";
        r0 = r20;
        a(r5, r0, r11, r8);	 Catch:{ Exception -> 0x00cb }
        r5 = "is_visible_in_downloads_ui";
        r0 = r20;
        r5 = r0.containsKey(r5);	 Catch:{ Exception -> 0x00cb }
        if (r5 == 0) goto L_0x05ed;
    L_0x0488:
        r4 = "is_visible_in_downloads_ui";
        r0 = r20;
        b(r4, r0, r11);	 Catch:{ Exception -> 0x00cb }
    L_0x0490:
        if (r6 == 0) goto L_0x04b2;
    L_0x0492:
        r4 = "allowed_network_types";
        r0 = r20;
        a(r4, r0, r11);	 Catch:{ Exception -> 0x00cb }
        r4 = "allow_roaming";
        r0 = r20;
        b(r4, r0, r11);	 Catch:{ Exception -> 0x00cb }
        r4 = "allow_metered";
        r0 = r20;
        b(r4, r0, r11);	 Catch:{ Exception -> 0x00cb }
        r4 = "bypass_recommended_size_limit";
        r0 = r20;
        b(r4, r0, r11);	 Catch:{ Exception -> 0x00cb }
    L_0x04b2:
        r4 = 0;
        r5 = com.xunlei.download.DownloadManager.TaskType.BT;	 Catch:{ Exception -> 0x00cb }
        if (r7 != r5) goto L_0x04cd;
    L_0x04b7:
        r4 = r18.getContext();	 Catch:{ Exception -> 0x00cb }
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r4 = com.xunlei.download.proguard.l.a(r4, r5, r6, r7, r8);	 Catch:{ Exception -> 0x00cb }
        if (r4 == 0) goto L_0x04cd;
    L_0x04c5:
        r5 = r18.getContext();	 Catch:{ Exception -> 0x00cb }
        r11 = r4.a(r5, r11);	 Catch:{ Exception -> 0x00cb }
    L_0x04cd:
        r5 = r18.c();	 Catch:{ Exception -> 0x00cb }
        r13 = r5.getWritableDatabase();	 Catch:{ Exception -> 0x00cb }
        r5 = "xl_downloads";
        r6 = 0;
        r6 = r13.insert(r5, r6, r11);	 Catch:{ Exception -> 0x00cb }
        r8 = -1;
        r5 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
        if (r5 != 0) goto L_0x0604;
    L_0x04e3:
        r4 = "DownloadManager";
        r5 = "couldn't insert into downloads database";
        com.xunlei.download.proguard.am.b(r4, r5);	 Catch:{ Exception -> 0x00cb }
        r4 = 0;
        goto L_0x002e;
    L_0x04ef:
        r5 = 0;
        goto L_0x0291;
    L_0x04f2:
        r5 = r4.intValue();	 Catch:{ Exception -> 0x00cb }
        r8 = 5;
        if (r5 != r8) goto L_0x02c4;
    L_0x04f9:
        r5 = r18.getContext();	 Catch:{ Exception -> 0x00cb }
        r8 = "android.permission.ACCESS_CACHE_FILESYSTEM";
        r9 = android.os.Binder.getCallingPid();	 Catch:{ Exception -> 0x00cb }
        r13 = android.os.Binder.getCallingUid();	 Catch:{ Exception -> 0x00cb }
        r14 = "need ACCESS_CACHE_FILESYSTEM permission to use system cache";
        r5.enforcePermission(r8, r9, r13, r14);	 Catch:{ Exception -> 0x00cb }
        goto L_0x02c4;
    L_0x0510:
        r5 = "visibility";
        r8 = 2;
        r8 = java.lang.Integer.valueOf(r8);	 Catch:{ Exception -> 0x00cb }
        r11.put(r5, r8);	 Catch:{ Exception -> 0x00cb }
        goto L_0x02e6;
    L_0x051d:
        r8 = "visibility";
        r11.put(r8, r5);	 Catch:{ Exception -> 0x00cb }
        goto L_0x02e6;
    L_0x0525:
        r5 = "status";
        r8 = 190; // 0xbe float:2.66E-43 double:9.4E-322;
        r8 = java.lang.Integer.valueOf(r8);	 Catch:{ Exception -> 0x00cb }
        r11.put(r5, r8);	 Catch:{ Exception -> 0x00cb }
        r5 = "_data";
        r0 = r20;
        r5 = r0.getAsString(r5);	 Catch:{ Exception -> 0x00cb }
        if (r5 != 0) goto L_0x0578;
    L_0x053c:
        r5 = "total_bytes";
        r8 = -1;
        r8 = java.lang.Integer.valueOf(r8);	 Catch:{ Exception -> 0x00cb }
        r11.put(r5, r8);	 Catch:{ Exception -> 0x00cb }
        r5 = "current_bytes";
        r8 = 0;
        r8 = java.lang.Integer.valueOf(r8);	 Catch:{ Exception -> 0x00cb }
        r11.put(r5, r8);	 Catch:{ Exception -> 0x00cb }
    L_0x0552:
        r5 = "DownloadManager";
        r8 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00cb }
        r9 = "insert contentvalues = ";
        r8.<init>(r9);	 Catch:{ Exception -> 0x00cb }
        r8 = r8.append(r11);	 Catch:{ Exception -> 0x00cb }
        r8 = r8.toString();	 Catch:{ Exception -> 0x00cb }
        com.xunlei.download.proguard.am.b(r5, r8);	 Catch:{ Exception -> 0x00cb }
        r5 = com.xunlei.download.DownloadManager.TaskType.UNKOWN;	 Catch:{ Exception -> 0x00cb }
        if (r7 != r5) goto L_0x05c5;
    L_0x056c:
        r4 = "DownloadManager";
        r5 = "UNKOWN PROTOCAL";
        com.xunlei.download.proguard.am.b(r4, r5);	 Catch:{ Exception -> 0x00cb }
        r4 = 0;
        goto L_0x002e;
    L_0x0578:
        r5 = "total_bytes";
        r0 = r20;
        r5 = r0.getAsLong(r5);	 Catch:{ Exception -> 0x00cb }
        r8 = r5.longValue();	 Catch:{ Exception -> 0x00cb }
        r5 = "current_bytes";
        r0 = r20;
        r5 = r0.getAsLong(r5);	 Catch:{ Exception -> 0x00cb }
        r14 = r5.longValue();	 Catch:{ Exception -> 0x00cb }
        r5 = (r14 > r8 ? 1 : (r14 == r8 ? 0 : -1));
        if (r5 != 0) goto L_0x05a8;
    L_0x0596:
        r16 = 0;
        r5 = (r8 > r16 ? 1 : (r8 == r16 ? 0 : -1));
        if (r5 <= 0) goto L_0x05a8;
    L_0x059c:
        r5 = "status";
        r13 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        r13 = java.lang.Integer.valueOf(r13);	 Catch:{ Exception -> 0x00cb }
        r11.put(r5, r13);	 Catch:{ Exception -> 0x00cb }
    L_0x05a8:
        r5 = "total_bytes";
        r8 = java.lang.Long.valueOf(r8);	 Catch:{ Exception -> 0x00cb }
        r11.put(r5, r8);	 Catch:{ Exception -> 0x00cb }
        r5 = "current_bytes";
        r8 = java.lang.Long.valueOf(r14);	 Catch:{ Exception -> 0x00cb }
        r11.put(r5, r8);	 Catch:{ Exception -> 0x00cb }
        r5 = "_data";
        r0 = r20;
        c(r5, r0, r11);	 Catch:{ Exception -> 0x00cb }
        goto L_0x0552;
    L_0x05c5:
        r5 = com.xunlei.download.DownloadManager.TaskType.HTTP;	 Catch:{ Exception -> 0x00cb }
        if (r7 != r5) goto L_0x05d2;
    L_0x05c9:
        r5 = "thunder://";
        r5 = r10.startsWith(r5);	 Catch:{ Exception -> 0x00cb }
        if (r5 == 0) goto L_0x05dd;
    L_0x05d2:
        r5 = "xunlei_spdy";
        r8 = 1;
        r8 = java.lang.Boolean.valueOf(r8);	 Catch:{ Exception -> 0x00cb }
        r11.put(r5, r8);	 Catch:{ Exception -> 0x00cb }
    L_0x05dd:
        r5 = "task_type";
        r8 = r7.ordinal();	 Catch:{ Exception -> 0x00cb }
        r8 = java.lang.Integer.valueOf(r8);	 Catch:{ Exception -> 0x00cb }
        r11.put(r5, r8);	 Catch:{ Exception -> 0x00cb }
        goto L_0x03c0;
    L_0x05ed:
        if (r4 == 0) goto L_0x05f5;
    L_0x05ef:
        r4 = r4.intValue();	 Catch:{ Exception -> 0x00cb }
        if (r4 != 0) goto L_0x0602;
    L_0x05f5:
        r4 = 1;
    L_0x05f6:
        r5 = "is_visible_in_downloads_ui";
        r4 = java.lang.Boolean.valueOf(r4);	 Catch:{ Exception -> 0x00cb }
        r11.put(r5, r4);	 Catch:{ Exception -> 0x00cb }
        goto L_0x0490;
    L_0x0602:
        r4 = 0;
        goto L_0x05f6;
    L_0x0604:
        r5 = new com.android.providers.downloads.DownloadProvider$c;	 Catch:{ Exception -> 0x00cb }
        r8 = "status";
        r8 = r11.getAsInteger(r8);	 Catch:{ Exception -> 0x00cb }
        r8 = r8.intValue();	 Catch:{ Exception -> 0x00cb }
        r9 = 0;
        r5.<init>(r6, r8, r9, r10, r11);	 Catch:{ Exception -> 0x00cb }
        r8 = r18.getContext();	 Catch:{ Exception -> 0x00cb }
        r5.a(r8);	 Catch:{ Exception -> 0x00cb }
        if (r4 == 0) goto L_0x0633;
    L_0x061e:
        r5 = r18.getContext();	 Catch:{ Exception -> 0x00cb }
        r8 = "bt_select_set";
        r8 = r11.getAsString(r8);	 Catch:{ Exception -> 0x00cb }
        r9 = "_data";
        r9 = r11.getAsString(r9);	 Catch:{ Exception -> 0x00cb }
        r4.a(r5, r6, r8, r9);	 Catch:{ Exception -> 0x00cb }
    L_0x0633:
        r0 = r18;
        r1 = r20;
        r0.a(r13, r6, r1);	 Catch:{ Exception -> 0x00cb }
        r0 = r18;
        r1 = r19;
        r0.b(r1, r12);	 Catch:{ Exception -> 0x00cb }
        r5 = r18.getContext();	 Catch:{ Exception -> 0x00cb }
        r4 = new android.content.Intent;	 Catch:{ Exception -> 0x065d }
        r8 = com.android.providers.downloads.DownloadService.class;
        r4.<init>(r5, r8);	 Catch:{ Exception -> 0x065d }
        r5.startService(r4);	 Catch:{ Exception -> 0x065d }
    L_0x064f:
        r4 = com.xunlei.download.DownloadManager.getInstanceFor(r5);	 Catch:{ Exception -> 0x00cb }
        r4 = r4.getDownloadUri();	 Catch:{ Exception -> 0x00cb }
        r4 = android.content.ContentUris.withAppendedId(r4, r6);	 Catch:{ Exception -> 0x00cb }
        goto L_0x002e;
    L_0x065d:
        r4 = move-exception;
        r4.printStackTrace();	 Catch:{ Exception -> 0x00cb }
        com.xunlei.download.proguard.am.a(r4);	 Catch:{ Exception -> 0x00cb }
        goto L_0x064f;
    L_0x0665:
        r5 = move-exception;
        goto L_0x0416;
        */
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private long a(android.net.Uri r16, android.content.ContentValues r17, com.xunlei.download.DownloadManager.TaskType r18) {
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.downloads.DownloadProvider.a(android.net.Uri, android.content.ContentValues, com.xunlei.download.DownloadManager$TaskType):long");
        /*
        this = this;
        r2 = "uri";
        r0 = r17;
        r3 = r0.getAsString(r2);
        r2 = "hint";
        r0 = r17;
        r2 = r0.getAsString(r2);
        r4 = "destination";
        r0 = r17;
        r4 = r0.getAsInteger(r4);
        r4 = r4.intValue();
        r5 = com.xunlei.download.DownloadManager.TaskType.UNKOWN;
        r0 = r18;
        if (r0 == r5) goto L_0x0028;
    L_0x0025:
        r5 = 6;
        if (r4 != r5) goto L_0x002b;
    L_0x0028:
        r2 = -1;
    L_0x002a:
        return r2;
    L_0x002b:
        r8 = -1;
        r4 = "'";
        r5 = "''";
        r3 = r3.replaceAll(r4, r5);
        r4 = android.text.TextUtils.isEmpty(r2);
        if (r4 != 0) goto L_0x0047;
    L_0x003d:
        r4 = "'";
        r5 = "''";
        r2 = r2.replaceAll(r4, r5);
    L_0x0047:
        r4 = "etag";
        r0 = r17;
        r4 = r0.getAsString(r4);
        r5 = com.xunlei.download.DownloadManager.TaskType.BT;
        r0 = r18;
        if (r0 != r5) goto L_0x011a;
    L_0x0056:
        r5 = android.text.TextUtils.isEmpty(r4);
        if (r5 != 0) goto L_0x011a;
    L_0x005c:
        r3 = new java.lang.StringBuilder;
        r5 = " (etag='";
        r3.<init>(r5);
        r3 = r3.append(r4);
        r4 = "' ) AND (hint='";
        r3 = r3.append(r4);
        r2 = r3.append(r2);
        r3 = "' ) AND (deleted != '1')";
        r2 = r2.append(r3);
        r5 = r2.toString();
    L_0x007e:
        r10 = 0;
        r4 = 0;
        r6 = 0;
        r7 = 0;
        r2 = r15;
        r3 = r16;
        r6 = r2.query(r3, r4, r5, r6, r7);	 Catch:{ Exception -> 0x01c4, all -> 0x01b7 }
        if (r6 == 0) goto L_0x01cf;
    L_0x008b:
        r2 = r6.moveToFirst();	 Catch:{ Exception -> 0x01b2, all -> 0x01bf }
        if (r2 == 0) goto L_0x01cf;
    L_0x0091:
        r2 = "status";
        r2 = r6.getColumnIndexOrThrow(r2);	 Catch:{ Exception -> 0x01b2, all -> 0x01bf }
        r3 = "_data";
        r6.getColumnIndexOrThrow(r3);	 Catch:{ Exception -> 0x01b2, all -> 0x01bf }
        r3 = "_id";
        r3 = r6.getColumnIndexOrThrow(r3);	 Catch:{ Exception -> 0x01b2, all -> 0x01bf }
        r4 = "deleted";
        r4 = r6.getColumnIndexOrThrow(r4);	 Catch:{ Exception -> 0x01b2, all -> 0x01bf }
        r10 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x01b2, all -> 0x01bf }
    L_0x00b0:
        r5 = r6.isAfterLast();	 Catch:{ Exception -> 0x01b2, all -> 0x01bf }
        if (r5 != 0) goto L_0x01cf;
    L_0x00b6:
        r5 = r6.getInt(r2);	 Catch:{ Exception -> 0x01b2, all -> 0x01bf }
        r7 = new android.content.ContentValues;	 Catch:{ Exception -> 0x01b2, all -> 0x01bf }
        r7.<init>();	 Catch:{ Exception -> 0x01b2, all -> 0x01bf }
        r12 = "create_time";
        r13 = java.lang.Long.valueOf(r10);	 Catch:{ Exception -> 0x01b2, all -> 0x01bf }
        r7.put(r12, r13);	 Catch:{ Exception -> 0x01b2, all -> 0x01bf }
        r12 = "lastmod";
        r13 = java.lang.Long.valueOf(r10);	 Catch:{ Exception -> 0x01b2, all -> 0x01bf }
        r7.put(r12, r13);	 Catch:{ Exception -> 0x01b2, all -> 0x01bf }
        r12 = "bypass_recommended_size_limit";
        r0 = r17;
        r12 = r0.getAsInteger(r12);	 Catch:{ Exception -> 0x01b2, all -> 0x01bf }
        if (r12 == 0) goto L_0x00e4;
    L_0x00de:
        r13 = "bypass_recommended_size_limit";
        r7.put(r13, r12);	 Catch:{ Exception -> 0x01b2, all -> 0x01bf }
    L_0x00e4:
        r12 = "allowed_network_types";
        r0 = r17;
        r12 = r0.getAsInteger(r12);	 Catch:{ Exception -> 0x01b2, all -> 0x01bf }
        if (r12 == 0) goto L_0x00f5;
    L_0x00ef:
        r13 = "allowed_network_types";
        r7.put(r13, r12);	 Catch:{ Exception -> 0x01b2, all -> 0x01bf }
    L_0x00f5:
        r12 = r6.getInt(r4);	 Catch:{ Exception -> 0x01b2, all -> 0x01bf }
        r13 = 1;
        if (r12 == r13) goto L_0x01cf;
    L_0x00fc:
        r12 = com.xunlei.download.Downloads.Impl.isStatusInformational(r5);	 Catch:{ Exception -> 0x01b2, all -> 0x01bf }
        if (r12 == 0) goto L_0x0171;
    L_0x0102:
        r2 = r6.getInt(r3);	 Catch:{ Exception -> 0x01b2, all -> 0x01bf }
        r2 = (long) r2;
        r4 = 190; // 0xbe float:2.66E-43 double:9.4E-322;
        if (r5 == r4) goto L_0x0151;
    L_0x010b:
        r4 = 192; // 0xc0 float:2.69E-43 double:9.5E-322;
        if (r5 == r4) goto L_0x0151;
    L_0x010f:
        r4 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r5 != r4) goto L_0x013e;
    L_0x0113:
        if (r6 == 0) goto L_0x002a;
    L_0x0115:
        r6.close();
        goto L_0x002a;
    L_0x011a:
        r4 = new java.lang.StringBuilder;
        r5 = " (uri='";
        r4.<init>(r5);
        r3 = r4.append(r3);
        r4 = "' ) AND (hint='";
        r3 = r3.append(r4);
        r2 = r3.append(r2);
        r3 = "' ) AND (deleted != '1')";
        r2 = r2.append(r3);
        r5 = r2.toString();
        goto L_0x007e;
    L_0x013e:
        a(r7);	 Catch:{ Exception -> 0x015f, all -> 0x01bf }
        r4 = a();	 Catch:{ Exception -> 0x015f, all -> 0x01bf }
        r5 = a(r2);	 Catch:{ Exception -> 0x015f, all -> 0x01bf }
        r0 = r16;
        r15.update(r0, r7, r4, r5);	 Catch:{ Exception -> 0x015f, all -> 0x01bf }
    L_0x014e:
        if (r6 == 0) goto L_0x002a;
    L_0x0150:
        goto L_0x0115;
    L_0x0151:
        r4 = a();	 Catch:{ Exception -> 0x015f, all -> 0x01bf }
        r5 = a(r2);	 Catch:{ Exception -> 0x015f, all -> 0x01bf }
        r0 = r16;
        r15.update(r0, r7, r4, r5);	 Catch:{ Exception -> 0x015f, all -> 0x01bf }
        goto L_0x014e;
    L_0x015f:
        r4 = move-exception;
        r5 = r6;
    L_0x0161:
        r15.d();	 Catch:{ all -> 0x01c1 }
        r4.printStackTrace();	 Catch:{ all -> 0x01c1 }
        com.xunlei.download.proguard.am.a(r4);	 Catch:{ all -> 0x01c1 }
        if (r5 == 0) goto L_0x002a;
    L_0x016c:
        r5.close();
        goto L_0x002a;
    L_0x0171:
        r5 = com.xunlei.download.Downloads.Impl.isStatusError(r5);	 Catch:{ Exception -> 0x01b2, all -> 0x01bf }
        if (r5 == 0) goto L_0x01ad;
    L_0x0177:
        r2 = r6.getInt(r3);	 Catch:{ Exception -> 0x01b2, all -> 0x01bf }
        r4 = (long) r2;
        r2 = "control";
        r3 = 0;
        r3 = java.lang.Integer.valueOf(r3);	 Catch:{ Exception -> 0x01c9, all -> 0x01bf }
        r7.put(r2, r3);	 Catch:{ Exception -> 0x01c9, all -> 0x01bf }
        r2 = "status";
        r3 = 190; // 0xbe float:2.66E-43 double:9.4E-322;
        r3 = java.lang.Integer.valueOf(r3);	 Catch:{ Exception -> 0x01c9, all -> 0x01bf }
        r7.put(r2, r3);	 Catch:{ Exception -> 0x01c9, all -> 0x01bf }
        r2 = "numfailed";
        r3 = 0;
        r3 = java.lang.Integer.valueOf(r3);	 Catch:{ Exception -> 0x01c9, all -> 0x01bf }
        r7.put(r2, r3);	 Catch:{ Exception -> 0x01c9, all -> 0x01bf }
        r2 = a();	 Catch:{ Exception -> 0x01c9, all -> 0x01bf }
        r3 = a(r4);	 Catch:{ Exception -> 0x01c9, all -> 0x01bf }
        r0 = r16;
        r15.update(r0, r7, r2, r3);	 Catch:{ Exception -> 0x01c9, all -> 0x01bf }
        r2 = r4;
        goto L_0x014e;
    L_0x01ad:
        r6.moveToNext();	 Catch:{ Exception -> 0x01b2, all -> 0x01bf }
        goto L_0x00b0;
    L_0x01b2:
        r2 = move-exception;
        r4 = r2;
        r5 = r6;
        r2 = r8;
        goto L_0x0161;
    L_0x01b7:
        r2 = move-exception;
        r6 = r10;
    L_0x01b9:
        if (r6 == 0) goto L_0x01be;
    L_0x01bb:
        r6.close();
    L_0x01be:
        throw r2;
    L_0x01bf:
        r2 = move-exception;
        goto L_0x01b9;
    L_0x01c1:
        r2 = move-exception;
        r6 = r5;
        goto L_0x01b9;
    L_0x01c4:
        r2 = move-exception;
        r4 = r2;
        r5 = r10;
        r2 = r8;
        goto L_0x0161;
    L_0x01c9:
        r2 = move-exception;
        r14 = r2;
        r2 = r4;
        r4 = r14;
        r5 = r6;
        goto L_0x0161;
    L_0x01cf:
        r2 = r8;
        goto L_0x014e;
        */
    }

    private void b(ContentValues contentValues) {
        String asString = contentValues.getAsString(Impl.COLUMN_FILE_NAME_HINT);
        if (asString == null) {
            throw new IllegalArgumentException("DESTINATION_FILE_URI must include a file URI under COLUMN_FILE_NAME_HINT");
        }
        Uri parse = Uri.parse(asString);
        asString = parse.getScheme();
        if (asString == null || !asString.equals("file")) {
            throw new IllegalArgumentException(new StringBuilder("Not a file URI: ").append(parse).toString());
        }
        String path = parse.getPath();
        if (path == null) {
            throw new IllegalArgumentException(new StringBuilder("Invalid file URI: ").append(parse).toString());
        }
        File file = new File(path);
        do {
            file = file.getParentFile();
            if (file == null) {
                break;
            }
        } while (!file.exists());
        if (file == null) {
            throw new SecurityException(new StringBuilder("Destination must be on external storage: ").append(parse).toString());
        }
    }

    private void c(ContentValues contentValues) {
        if (getContext().checkCallingOrSelfPermission(Impl.PERMISSION_ACCESS) != 0) {
            getContext().enforceCallingOrSelfPermission(MsgConstant.PERMISSION_INTERNET, "INTERNET permission is required to use the download manager");
            ContentValues contentValues2 = new ContentValues(contentValues);
            a(contentValues2, Impl.COLUMN_IS_PUBLIC_API, Boolean.TRUE);
            if (contentValues2.getAsInteger(Impl.COLUMN_DESTINATION).intValue() == 6) {
                contentValues2.remove(Impl.COLUMN_STATUS);
            }
            contentValues2.remove(Impl._DATA);
            contentValues2.remove(d.b);
            contentValues2.remove(Impl.COLUMN_CURRENT_BYTES);
            contentValues2.remove(Impl.COLUMN_BT_INFO_HASH);
            a(contentValues2, Impl.COLUMN_DESTINATION, Integer.valueOf(n), Integer.valueOf(p), Integer.valueOf(r));
            if (getContext().checkCallingOrSelfPermission(Impl.PERMISSION_NO_NOTIFICATION) == 0) {
                a(contentValues2, Impl.COLUMN_VISIBILITY, Integer.valueOf(n), Integer.valueOf(0), Integer.valueOf(m), Integer.valueOf(o));
            } else {
                a(contentValues2, Impl.COLUMN_VISIBILITY, Integer.valueOf(0), Integer.valueOf(m), Integer.valueOf(o));
            }
            contentValues2.remove(Impl.COLUMN_URI);
            contentValues2.remove(WebBrowserActivity.EXTRA_TITLE);
            contentValues2.remove(Impl.COLUMN_DESCRIPTION);
            contentValues2.remove(Impl.COLUMN_MIME_TYPE);
            contentValues2.remove(Impl.COLUMN_FILE_NAME_HINT);
            contentValues2.remove(Impl.COLUMN_NOTIFICATION_PACKAGE);
            contentValues2.remove(Impl.COLUMN_ALLOWED_NETWORK_TYPES);
            contentValues2.remove(Impl.COLUMN_ALLOW_ROAMING);
            contentValues2.remove(Impl.COLUMN_ALLOW_METERED);
            contentValues2.remove(Impl.COLUMN_IS_VISIBLE_IN_DOWNLOADS_UI);
            contentValues2.remove(Impl.COLUMN_MEDIA_SCANNED);
            contentValues2.remove(Impl.COLUMN_ALLOW_WRITE);
            contentValues2.remove(y.h);
            contentValues2.remove(Impl.COLUMN_BT_SELECT_SET);
            contentValues2.remove(Impl.COLUMN_IS_VIP_SPEEDUP);
            contentValues2.remove(Impl.COLUMN_IS_LX_SPEEDUP);
            contentValues2.remove(Impl.COLUMN_ALLOW_AUTO_RESUME);
            contentValues2.remove(Impl.COLUMN_GROUP_ID);
            contentValues2.remove(Impl.COLUMN_XL_ORIGIN);
            Iterator it = contentValues2.valueSet().iterator();
            while (it.hasNext()) {
                if (((String) ((Entry) it.next()).getKey()).startsWith(RequestHeaders.INSERT_KEY_PREFIX)) {
                    it.remove();
                }
            }
            if (contentValues2.size() > 0) {
                StringBuilder stringBuilder = new StringBuilder("Invalid columns in request: ");
                for (Entry entry : contentValues2.valueSet()) {
                    stringBuilder.append((String) entry.getKey());
                }
                throw new SecurityException(stringBuilder.toString());
            }
        }
    }

    private void a(ContentValues contentValues, String str, Object... objArr) {
        Object obj = contentValues.get(str);
        contentValues.remove(str);
        int length = objArr.length;
        int i = 0;
        while (i < length) {
            Object obj2 = objArr[i];
            if (obj != null || obj2 != null) {
                if (obj != null && obj.equals(obj2)) {
                    return;
                }
                i++;
            } else {
                return;
            }
        }
        throw new SecurityException(new StringBuilder("Invalid value for ").append(str).append(": ").append(obj).toString());
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        int i = 0;
        try {
            SQLiteDatabase readableDatabase = c().getReadableDatabase();
            int match = l.match(uri);
            if (match == 9) {
                return readableDatabase.query(t, strArr, str, strArr2, null, null, str2);
            }
            Cursor query;
            if (match == 100) {
                query = readableDatabase.query(v, strArr, str, strArr2, null, null, str2);
                if (query == null) {
                    return query;
                }
                query.setNotificationUri(getContext().getContentResolver(), uri);
                return query;
            }
            m.a(str, B);
            if (match == -1) {
                am.e(DownloadManager.LOG_TAG, new StringBuilder("querying unknown URI: ").append(uri).toString());
                throw new IllegalArgumentException(new StringBuilder("Unknown URI: ").append(uri).toString());
            } else if (match != 5) {
                b a = a(uri, str, strArr2, match);
                if (e()) {
                    if (strArr == null) {
                        strArr = (String[]) A.clone();
                    } else {
                        match = 0;
                        while (match < strArr.length) {
                            if (!B.contains(strArr[match]) && !D.contains(strArr[match])) {
                                throw new IllegalArgumentException(new StringBuilder("column ").append(strArr[match]).append(" is not allowed in queries").toString());
                            }
                            match++;
                        }
                    }
                    while (i < strArr.length) {
                        String str3 = (String) C.get(strArr[i]);
                        if (str3 != null) {
                            strArr[i] = str3;
                        }
                        i++;
                    }
                }
                query = readableDatabase.query(i, strArr, a.a(), a.b(), null, null, str2);
                if (query != null) {
                    query.setNotificationUri(getContext().getContentResolver(), uri);
                    return query;
                }
                am.e(DownloadManager.LOG_TAG, "query failed in downloads database");
                return query;
            } else if (strArr == null && str == null && str2 == null) {
                return a(readableDatabase, uri);
            } else {
                throw new UnsupportedOperationException("Request header queries do not support projections, selections or sorting");
            }
        } catch (Throwable e) {
            d();
            e.printStackTrace();
            am.a(e);
            return null;
        }
    }

    private void a(String[] strArr, String str, String[] strArr2, String str2, SQLiteDatabase sQLiteDatabase) {
        int i = 0;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("starting query, database is ");
        if (sQLiteDatabase != null) {
            stringBuilder.append("not ");
        }
        stringBuilder.append("null; ");
        if (strArr == null) {
            stringBuilder.append("projection is null; ");
        } else if (strArr.length == 0) {
            stringBuilder.append("projection is empty; ");
        } else {
            for (int i2 = 0; i2 < strArr.length; i2++) {
                stringBuilder.append("projection[");
                stringBuilder.append(i2);
                stringBuilder.append("] is ");
                stringBuilder.append(strArr[i2]);
                stringBuilder.append("; ");
            }
        }
        stringBuilder.append("selection is ");
        stringBuilder.append(str);
        stringBuilder.append("; ");
        if (strArr2 == null) {
            stringBuilder.append("selectionArgs is null; ");
        } else if (strArr2.length == 0) {
            stringBuilder.append("selectionArgs is empty; ");
        } else {
            while (i < strArr2.length) {
                stringBuilder.append("selectionArgs[");
                stringBuilder.append(i);
                stringBuilder.append("] is ");
                stringBuilder.append(strArr2[i]);
                stringBuilder.append("; ");
                i++;
            }
        }
        stringBuilder.append("sort is ");
        stringBuilder.append(str2);
        stringBuilder.append(".");
        am.e(DownloadManager.LOG_TAG, stringBuilder.toString());
    }

    private String a(Uri uri, int i) {
        return (i == 2 || i == 4 || i == 6 || i == 201 || i == 200) ? uri.getLastPathSegment() : (String) uri.getPathSegments().get(m);
    }

    private void a(SQLiteDatabase sQLiteDatabase, long j, ContentValues contentValues) {
        ContentValues contentValues2 = new ContentValues();
        contentValues2.put(RequestHeaders.COLUMN_DOWNLOAD_ID, Long.valueOf(j));
        for (Entry entry : contentValues.valueSet()) {
            if (((String) entry.getKey()).startsWith(RequestHeaders.INSERT_KEY_PREFIX)) {
                String toString = entry.getValue().toString();
                if (toString.contains(":")) {
                    String[] split = toString.split(":", n);
                    contentValues2.put(RequestHeaders.COLUMN_HEADER, split[0].trim());
                    contentValues2.put(RequestHeaders.COLUMN_VALUE, split[1].trim());
                    sQLiteDatabase.insert(RequestHeaders.HEADERS_DB_TABLE, null, contentValues2);
                } else {
                    throw new IllegalArgumentException(new StringBuilder("Invalid HTTP header line: ").append(toString).toString());
                }
            }
        }
    }

    private Cursor a(SQLiteDatabase sQLiteDatabase, Uri uri) {
        String toString = new StringBuilder("download_id=").append(a(uri, (int) q)).toString();
        return sQLiteDatabase.query(RequestHeaders.HEADERS_DB_TABLE, new String[]{RequestHeaders.COLUMN_HEADER, RequestHeaders.COLUMN_VALUE}, toString, null, null, null, null);
    }

    private void a(SQLiteDatabase sQLiteDatabase, String str, String[] strArr) {
        SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
        Cursor query = sQLiteDatabase2.query(i, new String[]{DownloadManager.COLUMN_ID}, str, strArr, null, null, null, null);
        query.moveToFirst();
        while (!query.isAfterLast()) {
            sQLiteDatabase.delete(RequestHeaders.HEADERS_DB_TABLE, new StringBuilder("download_id=").append(query.getLong(0)).toString(), null);
            query.moveToNext();
        }
        query.close();
    }

    private boolean e() {
        int callingUid = Binder.getCallingUid();
        return (Binder.getCallingPid() == Process.myPid() || callingUid == this.G || callingUid == this.H) ? false : true;
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        Cursor query;
        Throwable th;
        int i = 0;
        try {
            int update;
            SQLiteDatabase writableDatabase = c().getWritableDatabase();
            int match = l.match(uri);
            if (match == 9) {
                update = writableDatabase.update(t, contentValues, str, strArr);
                getContext().getContentResolver().notifyChange(uri, null);
                return update;
            } else if (match == 100) {
                update = writableDatabase.update(v, contentValues, str, strArr);
                getContext().getContentResolver().notifyChange(uri, null);
                return update;
            } else {
                ContentValues contentValues2;
                Object obj;
                String string;
                String string2;
                b a;
                c cVar;
                Context context;
                if (e()) {
                    m.a(str, B);
                }
                am.b(DownloadManager.LOG_TAG, new StringBuilder("update() uri = ").append(uri).append(", values = ").append(contentValues).append(", where = ").append(str).append(", whereArgs = ").append(Arrays.toString(strArr)).toString());
                Object obj2 = null;
                if (contentValues.containsKey(Impl.COLUMN_DELETED)) {
                    if (contentValues.getAsInteger(Impl.COLUMN_DELETED).intValue() == 1) {
                        obj2 = m;
                    }
                }
                Integer asInteger = contentValues.getAsInteger(Impl.COLUMN_CONTROL);
                if (asInteger != null) {
                    obj2 = m;
                }
                if (Binder.getCallingPid() != Process.myPid()) {
                    ContentValues contentValues3 = new ContentValues();
                    c(Impl.COLUMN_APP_DATA, contentValues, contentValues3);
                    a(Impl.COLUMN_VISIBILITY, contentValues, contentValues3);
                    if (asInteger != null) {
                        contentValues3.put(Impl.COLUMN_CONTROL, asInteger);
                    }
                    a(Impl.COLUMN_CONTROL, contentValues, contentValues3);
                    a(Impl.COLUMN_STATUS, contentValues, contentValues3);
                    c(WebBrowserActivity.EXTRA_TITLE, contentValues, contentValues3);
                    c(Impl.COLUMN_MEDIAPROVIDER_URI, contentValues, contentValues3);
                    c(Impl.COLUMN_DESCRIPTION, contentValues, contentValues3);
                    a(Impl.COLUMN_DELETED, contentValues, contentValues3);
                    a(Impl.COLUMN_IS_VIP_SPEEDUP, contentValues, contentValues3);
                    a(Impl.COLUMN_IS_LX_SPEEDUP, contentValues, contentValues3);
                    contentValues2 = contentValues3;
                    obj = obj2;
                } else {
                    String asString = contentValues.getAsString(Impl._DATA);
                    if (asString != null) {
                        try {
                            query = query(uri, new String[]{WebBrowserActivity.EXTRA_TITLE}, null, null, null);
                            try {
                                if (query.moveToFirst()) {
                                    string = query.getString(0);
                                    if (string.length() == 0 || string.endsWith(" ")) {
                                        contentValues.put(WebBrowserActivity.EXTRA_TITLE, new File(asString).getName());
                                    }
                                } else {
                                    contentValues.put(WebBrowserActivity.EXTRA_TITLE, new File(asString).getName());
                                }
                                z.a(query);
                            } catch (Throwable th2) {
                                th = th2;
                                z.a(query);
                                throw th;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            query = null;
                            z.a(query);
                            throw th;
                        }
                    }
                    Integer asInteger2 = contentValues.getAsInteger(Impl.COLUMN_STATUS);
                    Object obj3 = (asInteger2 == null || asInteger2.intValue() != 190) ? null : m;
                    boolean containsKey = contentValues.containsKey(Impl.COLUMN_BYPASS_RECOMMENDED_SIZE_LIMIT);
                    if (obj3 != null || containsKey) {
                        contentValues2 = contentValues;
                        int i2 = 1;
                    } else {
                        contentValues2 = contentValues;
                        obj = obj2;
                    }
                }
                String asString2 = contentValues.getAsString(Impl.COLUMN_BT_SELECT_SET);
                Object obj4 = null;
                String str2 = null;
                if ((asString2 != null && match == 2) || match == 4) {
                    Cursor query2 = query(uri, new String[]{Impl.COLUMN_BT_SELECT_SET, Impl.COLUMN_STATUS, Impl._DATA, Impl.COLUMN_URI, Impl.COLUMN_BT_INFO_HASH}, new StringBuilder("task_type='").append(TaskType.BT.ordinal()).append("'").toString(), null, null);
                    if (query2 != null) {
                        String string3;
                        Object obj5;
                        if (query2.moveToFirst()) {
                            string = query2.getString(query2.getColumnIndexOrThrow(Impl.COLUMN_BT_SELECT_SET));
                            string3 = query2.getString(query2.getColumnIndexOrThrow(Impl._DATA));
                            int i3 = query2.getInt(query2.getColumnIndexOrThrow(Impl.COLUMN_STATUS));
                            String string4 = query2.getString(query2.getColumnIndexOrThrow(Impl.COLUMN_URI));
                            string2 = query2.getString(query2.getColumnIndexOrThrow(Impl.COLUMN_BT_INFO_HASH));
                            if (asString2.equals(string) || i3 == 192) {
                                string = null;
                                obj5 = null;
                            } else {
                                string = Uri.parse(string4).getPath();
                                if (!new File(string).exists()) {
                                    string = z.a(string3, string2);
                                }
                                obj5 = m;
                            }
                        } else {
                            string = null;
                            string3 = null;
                            obj5 = null;
                        }
                        query2.close();
                        str2 = string;
                        string2 = string3;
                        obj4 = obj5;
                        switch (match) {
                            case m:
                            case n:
                            case o:
                            case p:
                            case w:
                            case x:
                                a = a(uri, str, strArr, match);
                                if (contentValues2.size() <= 0) {
                                    cVar = null;
                                    if (contentValues2.containsKey(Impl.COLUMN_STATUS)) {
                                        cVar = new c(contentValues2.getAsInteger(Impl.COLUMN_STATUS).intValue(), writableDatabase, a, contentValues2);
                                    }
                                    i = writableDatabase.update(i, contentValues2, a.a(), a.b());
                                    if (i > 0 && cVar != null) {
                                        cVar.a(getContext());
                                    }
                                } else {
                                    i = 0;
                                }
                                b(uri, match);
                                if (obj != null) {
                                    context = getContext();
                                    try {
                                        context.startService(new Intent(context, DownloadService.class));
                                    } catch (Throwable th4) {
                                        th4.printStackTrace();
                                        am.a(th4);
                                    }
                                }
                                if (obj4 != null && i > 0) {
                                    l.a(getContext(), null, null, null, null).a(getContext(), Long.parseLong(uri.getLastPathSegment()), asString2, string2, str2);
                                }
                                return i;
                            default:
                                am.b(DownloadManager.LOG_TAG, new StringBuilder("updating unknown/invalid URI: ").append(uri).toString());
                                throw new UnsupportedOperationException(new StringBuilder("Cannot update URI: ").append(uri).toString());
                        }
                    }
                }
                string2 = null;
                switch (match) {
                    case m:
                    case n:
                    case o:
                    case p:
                    case w:
                    case x:
                        a = a(uri, str, strArr, match);
                        if (contentValues2.size() <= 0) {
                            i = 0;
                        } else {
                            cVar = null;
                            if (contentValues2.containsKey(Impl.COLUMN_STATUS)) {
                                cVar = new c(contentValues2.getAsInteger(Impl.COLUMN_STATUS).intValue(), writableDatabase, a, contentValues2);
                            }
                            i = writableDatabase.update(i, contentValues2, a.a(), a.b());
                            cVar.a(getContext());
                        }
                        b(uri, match);
                        if (obj != null) {
                            context = getContext();
                            context.startService(new Intent(context, DownloadService.class));
                        }
                        l.a(getContext(), null, null, null, null).a(getContext(), Long.parseLong(uri.getLastPathSegment()), asString2, string2, str2);
                        return i;
                    default:
                        am.b(DownloadManager.LOG_TAG, new StringBuilder("updating unknown/invalid URI: ").append(uri).toString());
                        throw new UnsupportedOperationException(new StringBuilder("Cannot update URI: ").append(uri).toString());
                }
            }
        } catch (Throwable th42) {
            Throwable th5 = th42;
            update = i;
            d();
            th5.printStackTrace();
            am.a(th5);
            return update;
        }
    }

    private void b(Uri uri, int i) {
        if (i == 200 || i == 201) {
            getContext().getContentResolver().notifyChange(uri, null);
            return;
        }
        Long valueOf;
        if (i == 2 || i == 4) {
            valueOf = Long.valueOf(Long.parseLong(a(uri, i)));
        } else {
            valueOf = null;
        }
        Uri[] uriArr = z;
        int length = uriArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            Uri uri2 = uriArr[i2];
            if (valueOf != null) {
                uri2 = ContentUris.withAppendedId(uri2, valueOf.longValue());
            }
            getContext().getContentResolver().notifyChange(uri2, null);
        }
    }

    private b a(Uri uri, String str, String[] strArr, int i) {
        b bVar = new b();
        bVar.a(str, strArr);
        if (i == 202) {
            bVar.a("group_id =? ", Integer.valueOf(0));
        } else if (i == 200) {
            bVar.a("group_id =? ", a(uri, (int) w));
        }
        if (i == 2 || i == 4 || i == 6 || i == 201) {
            bVar.a("_id = ?", a(uri, i));
        }
        return bVar;
    }

    public int delete(Uri uri, String str, String[] strArr) {
        try {
            if (e()) {
                m.a(str, B);
            }
            SQLiteDatabase writableDatabase = c().getWritableDatabase();
            int match = l.match(uri);
            int delete;
            switch (match) {
                case m:
                case n:
                case o:
                case p:
                case w:
                case x:
                    b a = a(uri, str, strArr, match);
                    a(writableDatabase, a.a(), a.b());
                    am.b(DownloadManager.LOG_TAG, new StringBuilder("deleting ").append(uri).append(", whereArgs=").append(Arrays.toString(strArr)).toString());
                    writableDatabase.execSQL(new StringBuilder(" DELETE FROM xl_bt_sub_task WHERE bt_parent_id IN (SELECT _id FROM xl_downloads WHERE ").append(a.a()).append(" ) ").toString(), a.b());
                    c cVar = new c(1, writableDatabase, a, null);
                    delete = writableDatabase.delete(i, a.a(), a.b());
                    if (delete > 0) {
                        cVar.a(getContext());
                    }
                    b(uri, match);
                    return delete;
                case s:
                    delete = writableDatabase.delete(t, str, strArr);
                    getContext().getContentResolver().notifyChange(uri, null);
                    return delete;
                case u:
                    delete = writableDatabase.delete(v, str, strArr);
                    getContext().getContentResolver().notifyChange(uri, null);
                    return delete;
                default:
                    am.b(DownloadManager.LOG_TAG, new StringBuilder("deleting unknown/invalid URI: ").append(uri).toString());
                    throw new UnsupportedOperationException(new StringBuilder("Cannot delete URI: ").append(uri).toString());
            }
        } catch (Throwable e) {
            d();
            e.printStackTrace();
            am.a(e);
            return 0;
        }
    }

    private int a(SQLiteDatabase sQLiteDatabase, b bVar) {
        String[] strArr = new String[]{DownloadManager.COLUMN_ID, Impl.COLUMN_TASK_TYPE};
        ArrayList arrayList = new ArrayList();
        Cursor query = sQLiteDatabase.query(i, strArr, bVar.a(), bVar.b(), null, null, null);
        int columnIndexOrThrow = query.getColumnIndexOrThrow(DownloadManager.COLUMN_ID);
        int columnIndexOrThrow2 = query.getColumnIndexOrThrow(Impl.COLUMN_TASK_TYPE);
        while (query.moveToNext()) {
            long j = query.getLong(columnIndexOrThrow);
            if (TaskType.GROUP.ordinal() == query.getInt(columnIndexOrThrow2)) {
                am.b(DownloadManager.LOG_TAG, new StringBuilder("delete group: ").append(j).toString());
                arrayList.add(Long.valueOf(j));
            }
        }
        query.close();
        if (arrayList.size() <= 0) {
            return 0;
        }
        b bVar2 = new b();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            long longValue = ((Long) it.next()).longValue();
            bVar2.a("group_id=?", b.d, String.valueOf(longValue));
        }
        return sQLiteDatabase.delete(i, bVar2.a(), bVar2.b());
    }

    private int c(String str) {
        if ("r".equals(str)) {
            return 268435456;
        }
        if (IXAdRequestInfo.WIDTH.equals(str) || "wt".equals(str)) {
            return 738197504;
        }
        if ("wa".equals(str)) {
            return 704643072;
        }
        if ("rw".equals(str)) {
            return 939524096;
        }
        if ("rwt".equals(str)) {
            return 1006632960;
        }
        throw new IllegalArgumentException(new StringBuilder("Bad mode '").append(str).append("'").toString());
    }

    public ParcelFileDescriptor openFile(Uri uri, String str) throws FileNotFoundException {
        Cursor query = query(uri, new String[]{Impl._DATA}, null, null, null);
        int count = query != null ? query.getCount() : 0;
        if (count == 1) {
            query.moveToFirst();
            String string = query.getString(0);
            z.a(query);
            if (string == null) {
                throw new FileNotFoundException("No filename found.");
            } else if (m.a(string, this.I)) {
                return ParcelFileDescriptor.open(new File(string), c(str));
            } else {
                throw new FileNotFoundException(new StringBuilder("Invalid filename: ").append(string).toString());
            }
        } else if (count == 0) {
            throw new FileNotFoundException(new StringBuilder("No entry for ").append(uri).toString());
        } else {
            throw new FileNotFoundException(new StringBuilder("Multiple items at ").append(uri).toString());
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void dump(java.io.FileDescriptor r11, java.io.PrintWriter r12, java.lang.String[] r13) {
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.downloads.DownloadProvider.dump(java.io.FileDescriptor, java.io.PrintWriter, java.lang.String[]):void");
        /*
        this = this;
        r8 = 0;
        r9 = new com.xunlei.download.proguard.o;
        r0 = "  ";
        r1 = 120; // 0x78 float:1.68E-43 double:5.93E-322;
        r9.<init>(r12, r0, r1);
        r0 = "Downloads updated in last hour:";
        r9.println(r0);
        r9.a();
        r0 = r10.c();	 Catch:{ Exception -> 0x00b9, all -> 0x00ac }
        r0 = r0.getReadableDatabase();	 Catch:{ Exception -> 0x00b9, all -> 0x00ac }
        r1 = r10.f;	 Catch:{ Exception -> 0x00b9, all -> 0x00ac }
        r2 = r1.a();	 Catch:{ Exception -> 0x00b9, all -> 0x00ac }
        r4 = 3600000; // 0x36ee80 float:5.044674E-39 double:1.7786363E-317;
        r4 = r2 - r4;
        r1 = "xl_downloads";
        r2 = 0;
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00b9, all -> 0x00ac }
        r6 = "lastmod>";
        r3.<init>(r6);	 Catch:{ Exception -> 0x00b9, all -> 0x00ac }
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x00b9, all -> 0x00ac }
        r3 = r3.toString();	 Catch:{ Exception -> 0x00b9, all -> 0x00ac }
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = "_id ASC";
        r1 = r0.query(r1, r2, r3, r4, r5, r6, r7);	 Catch:{ Exception -> 0x00b9, all -> 0x00ac }
        r2 = r1.getColumnNames();	 Catch:{ Exception -> 0x0099 }
        r0 = "_id";
        r3 = r1.getColumnIndex(r0);	 Catch:{ Exception -> 0x0099 }
    L_0x0050:
        r0 = r1.moveToNext();	 Catch:{ Exception -> 0x0099 }
        if (r0 == 0) goto L_0x00b4;
    L_0x0056:
        r0 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0099 }
        r4 = "Download #";
        r0.<init>(r4);	 Catch:{ Exception -> 0x0099 }
        r4 = r1.getInt(r3);	 Catch:{ Exception -> 0x0099 }
        r0 = r0.append(r4);	 Catch:{ Exception -> 0x0099 }
        r4 = ":";
        r0 = r0.append(r4);	 Catch:{ Exception -> 0x0099 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x0099 }
        r9.println(r0);	 Catch:{ Exception -> 0x0099 }
        r9.a();	 Catch:{ Exception -> 0x0099 }
        r0 = 0;
    L_0x0078:
        r4 = r2.length;	 Catch:{ Exception -> 0x0099 }
        if (r0 >= r4) goto L_0x0092;
    L_0x007b:
        r4 = "cookiedata";
        r5 = r2[r0];	 Catch:{ Exception -> 0x0099 }
        r4 = r4.equals(r5);	 Catch:{ Exception -> 0x0099 }
        if (r4 != 0) goto L_0x008f;
    L_0x0086:
        r4 = r2[r0];	 Catch:{ Exception -> 0x0099 }
        r5 = r1.getString(r0);	 Catch:{ Exception -> 0x0099 }
        r9.a(r4, r5);	 Catch:{ Exception -> 0x0099 }
    L_0x008f:
        r0 = r0 + 1;
        goto L_0x0078;
    L_0x0092:
        r9.println();	 Catch:{ Exception -> 0x0099 }
        r9.b();	 Catch:{ Exception -> 0x0099 }
        goto L_0x0050;
    L_0x0099:
        r0 = move-exception;
    L_0x009a:
        r10.d();	 Catch:{ all -> 0x00b7 }
        r0.printStackTrace();	 Catch:{ all -> 0x00b7 }
        com.xunlei.download.proguard.am.a(r0);	 Catch:{ all -> 0x00b7 }
        if (r1 == 0) goto L_0x00a8;
    L_0x00a5:
        r1.close();
    L_0x00a8:
        r9.b();
        return;
    L_0x00ac:
        r0 = move-exception;
        r1 = r8;
    L_0x00ae:
        if (r1 == 0) goto L_0x00b3;
    L_0x00b0:
        r1.close();
    L_0x00b3:
        throw r0;
    L_0x00b4:
        if (r1 == 0) goto L_0x00a8;
    L_0x00b6:
        goto L_0x00a5;
    L_0x00b7:
        r0 = move-exception;
        goto L_0x00ae;
    L_0x00b9:
        r0 = move-exception;
        r1 = r8;
        goto L_0x009a;
        */
    }

    private void a(Uri uri, String str) {
        am.e(DownloadManager.LOG_TAG, new StringBuilder("openFile uri: ").append(uri).append(", mode: ").append(str).append(", uid: ").append(Binder.getCallingUid()).toString());
        Cursor query = query(DownloadManager.getInstanceFor(getContext()).getDownloadUri(), new String[]{DownloadManager.COLUMN_ID}, null, null, DownloadManager.COLUMN_ID);
        if (query == null) {
            am.e(DownloadManager.LOG_TAG, "null cursor in openFile");
        } else {
            if (query.moveToFirst()) {
                do {
                    am.e(DownloadManager.LOG_TAG, new StringBuilder("row ").append(query.getInt(0)).append(" available").toString());
                } while (query.moveToNext());
            } else {
                am.e(DownloadManager.LOG_TAG, "empty cursor in openFile");
            }
            query.close();
        }
        query = query(uri, new String[]{Impl._DATA}, null, null, null);
        if (query == null) {
            am.e(DownloadManager.LOG_TAG, "null cursor in openFile");
            return;
        }
        if (query.moveToFirst()) {
            String string = query.getString(0);
            am.e(DownloadManager.LOG_TAG, new StringBuilder("filename in openFile: ").append(string).toString());
            if (new File(string).isFile()) {
                am.e(DownloadManager.LOG_TAG, "file exists in openFile");
            }
        } else {
            am.e(DownloadManager.LOG_TAG, "empty cursor in openFile");
        }
        query.close();
    }

    private static final void a(String str, ContentValues contentValues, ContentValues contentValues2) {
        Integer asInteger = contentValues.getAsInteger(str);
        if (asInteger != null) {
            contentValues2.put(str, asInteger);
        }
    }

    private static final void b(String str, ContentValues contentValues, ContentValues contentValues2) {
        Boolean asBoolean = contentValues.getAsBoolean(str);
        if (asBoolean != null) {
            contentValues2.put(str, asBoolean);
        }
    }

    private static final void c(String str, ContentValues contentValues, ContentValues contentValues2) {
        String asString = contentValues.getAsString(str);
        if (asString != null) {
            contentValues2.put(str, asString);
        }
    }

    private static final void a(String str, ContentValues contentValues, ContentValues contentValues2, String str2) {
        c(str, contentValues, contentValues2);
        if (!contentValues2.containsKey(str)) {
            contentValues2.put(str, str2);
        }
    }

    static String a() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("(_id = ? )");
        return stringBuilder.toString();
    }

    static String[] a(long j) {
        return new String[]{Long.toString(j)};
    }

    private static String a(String[] strArr, String[] strArr2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SocializeConstants.OP_OPEN_PAREN);
        for (int i = 0; i < strArr.length; i++) {
            if (i > 0) {
                stringBuilder.append(strArr2[i - 1] + " ");
            }
            stringBuilder.append(Impl.COLUMN_STATUS);
            stringBuilder.append(new StringBuilder(" ").append(strArr[i]).append(" ? ").toString());
        }
        stringBuilder.append(SocializeConstants.OP_CLOSE_PAREN);
        return stringBuilder.toString();
    }

    private static String[] a(int[] iArr) {
        String[] strArr = new String[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            strArr[i] = Integer.toString(iArr[i]);
        }
        return strArr;
    }

    private static <T> T[] a(T[] tArr, T[] tArr2, Class<T> cls) {
        Object[] objArr = (Object[]) Array.newInstance(cls, tArr.length + tArr2.length);
        System.arraycopy(tArr, 0, objArr, 0, tArr.length);
        System.arraycopy(tArr2, 0, objArr, tArr.length, tArr2.length);
        return objArr;
    }

    static void a(ContentValues contentValues) {
        if (contentValues != null) {
            contentValues.put(Impl.COLUMN_STATUS, Integer.valueOf(Impl.STATUS_PENDING));
            contentValues.put(Impl.COLUMN_CONTROL, Integer.valueOf(0));
        }
    }

    @Deprecated
    public static void a(Context context, Class<?> cls) {
        DownloadManager.getInstanceFor(context);
    }

    @Deprecated
    public static Uri b() {
        return Uri.parse(new StringBuilder("content://").append(e).append("/my_downloads").toString());
    }
}
