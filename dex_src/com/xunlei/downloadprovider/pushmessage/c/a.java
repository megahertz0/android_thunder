package com.xunlei.downloadprovider.pushmessage.c;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.xunlei.downloadprovider.app.BrothersApplication;

// compiled from: NewPushMsgDatabase.java
public final class a extends SQLiteOpenHelper {
    private static a a;

    static {
        a = null;
    }

    private a(Context context) {
        super(context, "NewPushMsgDB", null, 1);
    }

    public static synchronized a a() {
        a aVar;
        synchronized (a.class) {
            if (a == null) {
                a = new a(BrothersApplication.a);
            }
            aVar = a;
        }
        return aVar;
    }

    public final synchronized void a(String str, String str2) {
        SQLiteDatabase sQLiteDatabase = null;
        synchronized (this) {
            try {
                ContentValues contentValues = new ContentValues();
                contentValues.put("msgid", str);
                contentValues.put(ParamKey.CONTENT, str2);
                try {
                    sQLiteDatabase = getWritableDatabase();
                    sQLiteDatabase.beginTransaction();
                    sQLiteDatabase.insert("newpushmsg", null, contentValues);
                    sQLiteDatabase.setTransactionSuccessful();
                    if (sQLiteDatabase != null) {
                        sQLiteDatabase.endTransaction();
                        sQLiteDatabase.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (sQLiteDatabase != null) {
                        sQLiteDatabase.endTransaction();
                        sQLiteDatabase.close();
                    }
                }
            } catch (Throwable th) {
            }
        }
    }

    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("create table if not exists newpushmsg ( id integer primary key autoincrement, msgid text UNIQUE, content text )");
        } catch (SQLException e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        try {
            sQLiteDatabase.execSQL("drop table if exists newpushmsg");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        onCreate(sQLiteDatabase);
    }

    public final void close() {
        super.close();
    }

    public final boolean a(String str) {
        Cursor cursor;
        SQLiteDatabase sQLiteDatabase;
        Cursor cursor2 = null;
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            try {
                Cursor query = writableDatabase.query("newpushmsg", new String[]{"*"}, "msgid=?", new String[]{str}, null, null, "id desc");
                if (query != null) {
                    query.close();
                }
                if (writableDatabase != null) {
                    writableDatabase.close();
                }
                return false;
            } catch (Exception e) {
                Exception exception = e;
                cursor = null;
                r10 = writableDatabase;
                r0 = exception;
                r0.printStackTrace();
                if (cursor != null) {
                    cursor.close();
                }
                if (r10 != null) {
                    r10.close();
                }
                return false;
            } catch (Throwable th) {
                Throwable th2 = th;
                sQLiteDatabase = writableDatabase;
                Throwable th3 = th2;
                if (cursor2 != null) {
                    cursor2.close();
                }
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.close();
                }
                throw th3;
            }
            if (query != null) {
                try {
                    if (query.getCount() > 0) {
                        if (query != null) {
                            query.close();
                        }
                        if (writableDatabase != null) {
                            writableDatabase.close();
                        }
                        return true;
                    }
                } catch (Exception e2) {
                    r10 = writableDatabase;
                    r0 = e2;
                    cursor = query;
                    r0.printStackTrace();
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (r10 != null) {
                        r10.close();
                    }
                    return false;
                } catch (Throwable th4) {
                    cursor2 = query;
                    th2 = th4;
                    sQLiteDatabase = writableDatabase;
                    th3 = th2;
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    if (sQLiteDatabase != null) {
                        sQLiteDatabase.close();
                    }
                    throw th3;
                }
            }
        } catch (Exception e3) {
            r0 = e3;
            cursor = null;
            try {
                Exception exception2;
                exception2.printStackTrace();
                if (cursor != null) {
                    cursor.close();
                }
                SQLiteDatabase sQLiteDatabase2;
                if (sQLiteDatabase2 != null) {
                    sQLiteDatabase2.close();
                }
                return false;
            } catch (Throwable th5) {
                th3 = th5;
                Cursor cursor3 = cursor;
                sQLiteDatabase = sQLiteDatabase2;
                cursor2 = cursor3;
                if (cursor2 != null) {
                    cursor2.close();
                }
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.close();
                }
                throw th3;
            }
        } catch (Throwable th6) {
            th3 = th6;
            sQLiteDatabase = null;
            if (cursor2 != null) {
                cursor2.close();
            }
            if (sQLiteDatabase != null) {
                sQLiteDatabase.close();
            }
            throw th3;
        }
    }
}
