package com.xunlei.downloadprovider.web.base.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.umeng.message.proguard.j;
import java.util.ArrayList;

// compiled from: CommentDbHelpler.java
public class a extends SQLiteOpenHelper {
    private static final String a;

    // compiled from: CommentDbHelpler.java
    public static class a {
        public long a;
        public boolean b;
        public boolean c;
        public boolean d;
        public String e;
        public String f;
        public long g;
    }

    static {
        a = a.class.getSimpleName();
    }

    public a(Context context) {
        super(context, "comment.db", null, 3);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        a(sQLiteDatabase);
    }

    private static void a(SQLiteDatabase sQLiteDatabase) {
        StringBuilder stringBuilder = new StringBuilder(j.o);
        stringBuilder.append("comment_zan_info( _id INTEGER PRIMARY KEY AUTOINCREMENT, res_id TEXT, src_id TEXT, usr_id LONG, comment_id LONG UNIQUE, has_zan SHORT , has_login SHORT , has_commit SHORT )");
        sQLiteDatabase.execSQL(stringBuilder.toString());
    }

    private static void b(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(new StringBuilder("DROP TABLE IF EXISTS comment_zan_info").toString());
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        b(sQLiteDatabase);
        a(sQLiteDatabase);
    }

    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        b(sQLiteDatabase);
        a(sQLiteDatabase);
    }

    public final ArrayList<a> a(String str) {
        ArrayList<a> arrayList = new ArrayList();
        try {
            SQLiteDatabase readableDatabase = getReadableDatabase();
            try {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("SELECT * FROM comment_zan_info WHERE res_id='").append(str).append("'");
                Cursor rawQuery = readableDatabase.rawQuery(stringBuilder.toString(), null);
            } catch (Exception e) {
                e = e;
            }
            if (rawQuery != null) {
                while (rawQuery.moveToNext()) {
                    boolean z;
                    a aVar = new a();
                    aVar.e = rawQuery.getString(rawQuery.getColumnIndex("res_id"));
                    aVar.f = rawQuery.getString(rawQuery.getColumnIndex("src_id"));
                    aVar.g = rawQuery.getLong(rawQuery.getColumnIndex("usr_id"));
                    aVar.a = rawQuery.getLong(rawQuery.getColumnIndex("comment_id"));
                    aVar.b = rawQuery.getShort(rawQuery.getColumnIndex("has_zan")) == (short) 1;
                    if (rawQuery.getShort(rawQuery.getColumnIndex("has_login")) == (short) 1) {
                        z = true;
                    } else {
                        z = false;
                    }
                    aVar.c = z;
                    if (rawQuery.getShort(rawQuery.getColumnIndex("has_commit")) == (short) 1) {
                        z = true;
                    } else {
                        z = false;
                    }
                    aVar.d = z;
                    arrayList.add(aVar);
                }
                if (readableDatabase != null) {
                    readableDatabase.close();
                }
                new StringBuilder("query data size=>").append(arrayList.size());
                return arrayList;
            } else if (readableDatabase == null) {
                return null;
            } else {
                readableDatabase.close();
                return null;
            }
        } catch (Exception e2) {
            Exception exception = e2;
            readableDatabase = null;
            e = exception;
            try {
                Exception e3;
                e3.printStackTrace();
                if (readableDatabase != null) {
                    readableDatabase.close();
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                if (readableDatabase != null) {
                    readableDatabase.close();
                }
                throw th2;
            }
            new StringBuilder("query data size=>").append(arrayList.size());
            return arrayList;
        } catch (Throwable th3) {
            readableDatabase = null;
            th2 = th3;
            if (readableDatabase != null) {
                readableDatabase.close();
            }
            throw th2;
        }
    }

    public final ArrayList<a> a(long j) {
        ArrayList<a> arrayList = new ArrayList();
        try {
            SQLiteDatabase readableDatabase = getReadableDatabase();
            try {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("SELECT * FROM comment_zan_info WHERE usr_id='").append(j).append("'");
                Cursor rawQuery = readableDatabase.rawQuery(stringBuilder.toString(), null);
            } catch (Exception e) {
                e = e;
            }
            if (rawQuery != null) {
                while (rawQuery.moveToNext()) {
                    boolean z;
                    a aVar = new a();
                    aVar.e = rawQuery.getString(rawQuery.getColumnIndex("res_id"));
                    aVar.f = rawQuery.getString(rawQuery.getColumnIndex("src_id"));
                    aVar.g = rawQuery.getLong(rawQuery.getColumnIndex("usr_id"));
                    aVar.a = rawQuery.getLong(rawQuery.getColumnIndex("comment_id"));
                    aVar.b = rawQuery.getShort(rawQuery.getColumnIndex("has_zan")) == (short) 1;
                    if (rawQuery.getShort(rawQuery.getColumnIndex("has_login")) == (short) 1) {
                        z = true;
                    } else {
                        z = false;
                    }
                    aVar.c = z;
                    if (rawQuery.getShort(rawQuery.getColumnIndex("has_commit")) == (short) 1) {
                        z = true;
                    } else {
                        z = false;
                    }
                    aVar.d = z;
                    arrayList.add(aVar);
                }
                if (readableDatabase != null) {
                    readableDatabase.close();
                }
                new StringBuilder("query data size=>").append(arrayList.size());
                return arrayList;
            } else if (readableDatabase == null) {
                return null;
            } else {
                readableDatabase.close();
                return null;
            }
        } catch (Exception e2) {
            Exception exception = e2;
            readableDatabase = null;
            e = exception;
            try {
                Exception e3;
                e3.printStackTrace();
                if (readableDatabase != null) {
                    readableDatabase.close();
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                if (readableDatabase != null) {
                    readableDatabase.close();
                }
                throw th2;
            }
            new StringBuilder("query data size=>").append(arrayList.size());
            return arrayList;
        } catch (Throwable th3) {
            readableDatabase = null;
            th2 = th3;
            if (readableDatabase != null) {
                readableDatabase.close();
            }
            throw th2;
        }
    }

    public final ArrayList<a> a() {
        ArrayList<a> arrayList = new ArrayList();
        try {
            SQLiteDatabase readableDatabase = getReadableDatabase();
            try {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("SELECT * FROM comment_zan_info WHERE has_commit=0");
                Cursor rawQuery = readableDatabase.rawQuery(stringBuilder.toString(), null);
            } catch (Exception e) {
                e = e;
            }
            if (rawQuery != null) {
                while (rawQuery.moveToNext()) {
                    boolean z;
                    a aVar = new a();
                    aVar.e = rawQuery.getString(rawQuery.getColumnIndex("res_id"));
                    aVar.f = rawQuery.getString(rawQuery.getColumnIndex("src_id"));
                    aVar.g = rawQuery.getLong(rawQuery.getColumnIndex("usr_id"));
                    aVar.a = rawQuery.getLong(rawQuery.getColumnIndex("comment_id"));
                    aVar.b = rawQuery.getShort(rawQuery.getColumnIndex("has_zan")) == (short) 1;
                    if (rawQuery.getShort(rawQuery.getColumnIndex("has_login")) == (short) 1) {
                        z = true;
                    } else {
                        z = false;
                    }
                    aVar.c = z;
                    if (rawQuery.getShort(rawQuery.getColumnIndex("has_commit")) == (short) 1) {
                        z = true;
                    } else {
                        z = false;
                    }
                    aVar.d = z;
                    arrayList.add(aVar);
                }
                if (readableDatabase != null) {
                    readableDatabase.close();
                }
                new StringBuilder("query data size=>").append(arrayList.size());
                return arrayList;
            } else if (readableDatabase == null) {
                return null;
            } else {
                readableDatabase.close();
                return null;
            }
        } catch (Exception e2) {
            Exception exception = e2;
            readableDatabase = null;
            e = exception;
            try {
                Exception e3;
                e3.printStackTrace();
                if (readableDatabase != null) {
                    readableDatabase.close();
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                if (readableDatabase != null) {
                    readableDatabase.close();
                }
                throw th2;
            }
            new StringBuilder("query data size=>").append(arrayList.size());
            return arrayList;
        } catch (Throwable th3) {
            readableDatabase = null;
            th2 = th3;
            if (readableDatabase != null) {
                readableDatabase.close();
            }
            throw th2;
        }
    }
}
