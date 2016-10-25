package com.umeng.message.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.text.TextUtils;
import com.umeng.common.UmLog;
import com.umeng.message.MessageSharedPrefs;
import com.umeng.message.proguard.k;

public class MessageProvider extends ContentProvider {
    private static final String a;
    private static final UriMatcher b;
    private static final int e = 1;
    private static final int f = 2;
    private static final int g = 3;
    private static final int h = 4;
    private static final int i = 5;
    private static final int j = 6;
    private static final int k = 7;
    private static final int l = 8;
    private static final int m = 9;
    private static Context n;
    private a c;
    private b d;

    private class a extends SQLiteOpenHelper {
        public a(Context context) {
            super(context, "MessageStore.db", null, 2);
        }

        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            UmLog.d(a, "MessageStoreHelper-->onCreate-->start");
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS MessageStore(_id Integer  PRIMARY KEY  AUTOINCREMENT  , MsdId Varchar  , Json Varchar  , SdkVersion Varchar  , ArrivalTime Long  , ActionType Integer )");
            sQLiteDatabase.execSQL("create table if not exists MsgTemp(tempkey varchar default NULL, tempvalue varchar default NULL,PRIMARY KEY(tempkey))");
            sQLiteDatabase.execSQL("create table if not exists MsgAlias(time long,type varchar default NULL,alias varchar default NULL,exclusive int,error int,message varchar,PRIMARY KEY(time))");
            UmLog.d(a, "MessageStoreHelper-->onCreate-->end");
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            onCreate(sQLiteDatabase);
            UmLog.d(a, "MessageStoreHelper-->onUpgrade");
        }
    }

    private class b extends SQLiteOpenHelper {
        public b(Context context) {
            super(context, k.a, null, 4);
        }

        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL("create table if not exists MsgLogStore (MsgId varchar, ActionType Integer, Time long, PRIMARY KEY(MsgId, ActionType))");
            sQLiteDatabase.execSQL("create table if not exists MsgLogIdTypeStore (MsgId varchar, MsgType varchar, PRIMARY KEY(MsgId))");
            sQLiteDatabase.execSQL("create table if not exists MsgLogStoreForAgoo (MsgId varchar, TaskId varchar, MsgStatus varchar, Time long, PRIMARY KEY(MsgId, MsgStatus))");
            sQLiteDatabase.execSQL("create table if not exists MsgLogIdTypeStoreForAgoo (MsgId varchar, TaskId varchar, MsgStatus varchar, PRIMARY KEY(MsgId))");
            boolean a = a(sQLiteDatabase, k.g);
            sQLiteDatabase.execSQL("create table if not exists MsgConfigInfo (SerialNo integer default 1, AppLaunchAt long default 0, UpdateResponse varchar default NULL)");
            if (a) {
                sQLiteDatabase.execSQL("alter table MsgConfigInfo add column UpdateResponse varchar");
            } else {
                int serialNo = MessageSharedPrefs.getInstance(MessageProvider.this.getContext()).getSerialNo();
                long appLaunchLogSentAt = MessageSharedPrefs.getInstance(MessageProvider.this.getContext()).getAppLaunchLogSentAt();
                sQLiteDatabase.execSQL("insert into MsgConfigInfo(SerialNo, AppLaunchAt) values (?, ?)", new Object[]{Integer.valueOf(serialNo), Long.valueOf(appLaunchLogSentAt)});
            }
            UmLog.d(a, "MsgLogStoreHelper-->onCreate");
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            onCreate(sQLiteDatabase);
            UmLog.d(a, "MsgLogStoreHelper-->onUpgrade");
        }

        private boolean a(SQLiteDatabase sQLiteDatabase, String str) {
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            try {
                Cursor rawQuery = sQLiteDatabase.rawQuery(new StringBuilder("select count(*) as c from sqlite_master where type = 'table' and name = '").append(str.trim()).append("'").toString(), null);
                return rawQuery.moveToNext() && rawQuery.getInt(0) > 0;
            } catch (Exception e) {
                return false;
            }
        }
    }

    static {
        a = MessageProvider.class.getSimpleName();
        b = new UriMatcher(-1);
    }

    public boolean onCreate() {
        this.c = new a(getContext());
        this.d = new b(getContext());
        n = getContext();
        UriMatcher uriMatcher = b;
        a.a(n);
        uriMatcher.addURI(a.a, "MessageStores", e);
        uriMatcher = b;
        a.a(n);
        uriMatcher.addURI(a.a, "MsgTemps", f);
        uriMatcher = b;
        a.a(n);
        uriMatcher.addURI(a.a, "MsgAlias", g);
        uriMatcher = b;
        a.a(n);
        uriMatcher.addURI(a.a, "MsgAliasDeleteAll", h);
        uriMatcher = b;
        a.a(n);
        uriMatcher.addURI(a.a, "MsgLogStores", i);
        uriMatcher = b;
        a.a(n);
        uriMatcher.addURI(a.a, "MsgLogIdTypeStores", j);
        uriMatcher = b;
        a.a(n);
        uriMatcher.addURI(a.a, "MsgLogStoreForAgoos", k);
        uriMatcher = b;
        a.a(n);
        uriMatcher.addURI(a.a, "MsgLogIdTypeStoreForAgoos", l);
        uriMatcher = b;
        a.a(n);
        uriMatcher.addURI(a.a, "MsgConfigInfos", m);
        return true;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        Cursor cursor = null;
        switch (b.match(uri)) {
            case e:
                cursor.setNotificationUri(getContext().getContentResolver(), uri);
                return cursor;
            case f:
                cursor = this.c.getReadableDatabase().query("MsgTemp", strArr, str, strArr2, null, null, str2);
                cursor.setNotificationUri(getContext().getContentResolver(), uri);
                return cursor;
            case g:
                cursor = this.c.getReadableDatabase().query("MsgAlias", strArr, str, strArr2, null, null, str2);
                cursor.setNotificationUri(getContext().getContentResolver(), uri);
                return cursor;
            case i:
                cursor = this.d.getReadableDatabase().query(k.c, strArr, str, strArr2, null, null, str2);
                cursor.setNotificationUri(getContext().getContentResolver(), uri);
                return cursor;
            case k:
                cursor = this.d.getReadableDatabase().query(k.e, strArr, str, strArr2, null, null, str2);
                cursor.setNotificationUri(getContext().getContentResolver(), uri);
                return cursor;
            case l:
                cursor = this.d.getReadableDatabase().query(k.f, strArr, str, strArr2, null, null, str2);
                cursor.setNotificationUri(getContext().getContentResolver(), uri);
                return cursor;
            case m:
                cursor = this.d.getReadableDatabase().query(k.g, strArr, str, strArr2, null, null, str2);
                cursor.setNotificationUri(getContext().getContentResolver(), uri);
                return cursor;
            default:
                throw new IllegalArgumentException(new StringBuilder("Unknown URI ").append(uri).toString());
        }
    }

    public String getType(Uri uri) {
        switch (b.match(uri)) {
            case e:
            case f:
            case g:
            case i:
            case k:
            case l:
            case m:
                return com.umeng.message.provider.a.a.j;
            default:
                throw new IllegalArgumentException(new StringBuilder("Unknown URI ").append(uri).toString());
        }
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        long insert;
        Uri withAppendedId;
        switch (b.match(uri)) {
            case e:
                insert = this.c.getWritableDatabase().insert("MessageStore", null, contentValues);
                if (insert > 0) {
                    a.a(n);
                    withAppendedId = ContentUris.withAppendedId(a.b, insert);
                    getContext().getContentResolver().notifyChange(withAppendedId, null);
                    return withAppendedId;
                }
            case f:
                insert = this.c.getWritableDatabase().insert("MsgTemp", null, contentValues);
                if (insert > 0) {
                    a.a(n);
                    withAppendedId = ContentUris.withAppendedId(a.b, insert);
                    getContext().getContentResolver().notifyChange(withAppendedId, null);
                    return withAppendedId;
                }
            case g:
                insert = this.c.getWritableDatabase().insertWithOnConflict("MsgAlias", null, contentValues, i);
                if (insert > 0) {
                    a.a(n);
                    withAppendedId = ContentUris.withAppendedId(a.d, insert);
                    getContext().getContentResolver().notifyChange(withAppendedId, null);
                    return withAppendedId;
                }
            case i:
                insert = this.d.getWritableDatabase().insertWithOnConflict(k.c, null, contentValues, i);
                if (insert > 0) {
                    a.a(n);
                    withAppendedId = ContentUris.withAppendedId(a.f, insert);
                    getContext().getContentResolver().notifyChange(withAppendedId, null);
                    return withAppendedId;
                }
            case j:
                insert = this.d.getWritableDatabase().insertWithOnConflict(k.d, null, contentValues, i);
                if (insert > 0) {
                    a.a(n);
                    return ContentUris.withAppendedId(a.g, insert);
                }
            case k:
                insert = this.d.getWritableDatabase().insertWithOnConflict(k.e, null, contentValues, i);
                if (insert > 0) {
                    a.a(n);
                    return ContentUris.withAppendedId(a.h, insert);
                }
            case l:
                insert = this.d.getWritableDatabase().insertWithOnConflict(k.f, null, contentValues, i);
                if (insert > 0) {
                    a.a(n);
                    return ContentUris.withAppendedId(a.i, insert);
                }
        }
        return null;
    }

    public int delete(Uri uri, String str, String[] strArr) {
        int i = 0;
        switch (b.match(uri)) {
            case f:
                i = this.c.getWritableDatabase().delete("MsgTemp", str, strArr);
                break;
            case g:
                i = this.c.getWritableDatabase().delete("MsgAlias", str, strArr);
                break;
            case h:
                i = this.c.getWritableDatabase().delete("MsgAlias", null, null);
                break;
            case i:
                i = this.d.getWritableDatabase().delete(k.c, str, strArr);
                break;
            case j:
                i = this.d.getWritableDatabase().delete(k.d, str, strArr);
                break;
            case k:
                i = this.d.getWritableDatabase().delete(k.e, str, strArr);
                break;
            case l:
                i = this.d.getWritableDatabase().delete(k.f, str, strArr);
                break;
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return i;
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        int updateWithOnConflict;
        switch (b.match(uri)) {
            case e:
                updateWithOnConflict = this.c.getWritableDatabase().updateWithOnConflict("MessageStore", contentValues, str, strArr, i);
                break;
            case f:
                updateWithOnConflict = this.c.getWritableDatabase().update("MsgTemp", contentValues, str, strArr);
                break;
            case g:
                this.c.getWritableDatabase().updateWithOnConflict("MsgAlias", contentValues, str, strArr, i);
                updateWithOnConflict = 0;
                break;
            case m:
                updateWithOnConflict = this.d.getWritableDatabase().updateWithOnConflict(k.g, contentValues, str, strArr, i);
                break;
            default:
                updateWithOnConflict = 0;
                break;
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return updateWithOnConflict;
    }
}
