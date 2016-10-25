package com.xunlei.downloadprovider.filemanager.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.businessutil.XLFileTypeUtil;
import com.xunlei.downloadprovider.businessutil.XLFileTypeUtil.EFileCategoryType;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.paho.client.mqttv3.MqttTopic;

// compiled from: FileDB.java
public final class a extends SQLiteOpenHelper {
    public static a a;
    private static Object e;
    private final String b;
    private final String c;
    private String d;

    static {
        e = new Object();
    }

    public static a a() {
        if (a == null) {
            synchronized (e) {
                if (a == null) {
                    a = new a(BrothersApplication.a());
                }
            }
        }
        return a;
    }

    private a(Context context) {
        super(context, "filescanner2.db", null, 2);
        this.b = "FILE_CACHE";
        this.c = "CREATE TABLE IF NOT EXISTS FILE_CACHE(_ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT, parent TEXT,FILESIZE INTEGER, FILETYPE INTEGER, LASTMODIFY INTEGER);";
        this.d = getClass().getSimpleName();
    }

    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS FILE_CACHE");
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS FILE_CACHE(_ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT, parent TEXT,FILESIZE INTEGER, FILETYPE INTEGER, LASTMODIFY INTEGER);");
    }

    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        onCreate(sQLiteDatabase);
    }

    private synchronized boolean a(i iVar) {
        boolean z = false;
        synchronized (this) {
            SQLiteDatabase sQLiteDatabase = null;
            try {
                int delete;
                sQLiteDatabase = getWritableDatabase();
                if (iVar.f == -1) {
                    delete = sQLiteDatabase.delete("FILE_CACHE", "parent=? and NAME=?", new String[]{String.valueOf(iVar.b()), String.valueOf(iVar.a())});
                } else {
                    delete = sQLiteDatabase.delete("FILE_CACHE", "_ID=?", new String[]{String.valueOf(iVar.f)});
                }
                if (delete >= 0) {
                    z = true;
                }
                if (sQLiteDatabase != null) {
                    if (sQLiteDatabase.isOpen()) {
                        sQLiteDatabase.close();
                    }
                }
            } catch (Throwable th) {
            }
        }
        return z;
    }

    public final synchronized boolean a(List<i> list) {
        boolean z;
        SQLiteDatabase sQLiteDatabase;
        SQLiteDatabase sQLiteDatabase2 = null;
        try {
            sQLiteDatabase2 = getWritableDatabase();
            try {
                sQLiteDatabase2.beginTransaction();
                for (int i = 0; i < list.size(); i++) {
                    i iVar = (i) list.get(i);
                    if (iVar.d() != EFileCategoryType.E_OTHER_CATEGORY) {
                        if (iVar.f == -1) {
                            sQLiteDatabase2.delete("FILE_CACHE", "parent=? and NAME=?", new String[]{String.valueOf(iVar.b()), String.valueOf(iVar.a())});
                        } else {
                            sQLiteDatabase2.delete("FILE_CACHE", "_ID=?", new String[]{String.valueOf(iVar.f)});
                        }
                    }
                }
                sQLiteDatabase2.setTransactionSuccessful();
                z = true;
            } catch (Exception e) {
                e = e;
                sQLiteDatabase = sQLiteDatabase2;
                try {
                    Exception e2;
                    e2.printStackTrace();
                    z = false;
                } catch (Throwable th) {
                    Throwable th2 = th;
                    sQLiteDatabase2 = sQLiteDatabase;
                    sQLiteDatabase2.endTransaction();
                    sQLiteDatabase2.close();
                    throw th2;
                }
                if (sQLiteDatabase != null) {
                    if (sQLiteDatabase.isOpen()) {
                        sQLiteDatabase.endTransaction();
                        sQLiteDatabase.close();
                        z = false;
                        return z;
                    }
                }
                return z;
            } catch (Throwable th3) {
                th2 = th3;
                sQLiteDatabase2.endTransaction();
                sQLiteDatabase2.close();
                throw th2;
            }
            if (sQLiteDatabase2 != null) {
                if (sQLiteDatabase2.isOpen()) {
                    sQLiteDatabase2.endTransaction();
                    sQLiteDatabase2.close();
                    z = true;
                }
            }
        } catch (Exception e3) {
            e2 = e3;
            sQLiteDatabase = null;
            e2.printStackTrace();
            if (sQLiteDatabase != null) {
                if (sQLiteDatabase.isOpen()) {
                    sQLiteDatabase.endTransaction();
                    sQLiteDatabase.close();
                    z = false;
                    return z;
                }
            }
            z = false;
            return z;
        } catch (Throwable th32) {
            th2 = th32;
            sQLiteDatabase2.endTransaction();
            sQLiteDatabase2.close();
            throw th2;
        }
        return z;
    }

    public final synchronized boolean a(String str, String str2) {
        boolean z = false;
        synchronized (this) {
            SQLiteDatabase sQLiteDatabase = null;
            try {
                if (XLFileTypeUtil.a(str2) == EFileCategoryType.E_OTHER_CATEGORY) {
                    i iVar = new i();
                    iVar.a(str2);
                    i iVar2 = new i();
                    iVar2.a(str);
                    a(iVar2);
                    z = a(iVar);
                } else {
                    i iVar3 = new i();
                    iVar3.a(str);
                    i iVar4 = new i();
                    iVar4.a(str2);
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("NAME", iVar4.a());
                    contentValues.put("parent", iVar4.b());
                    contentValues.put("FILETYPE", Integer.valueOf(iVar4.d().ordinal()));
                    try {
                        sQLiteDatabase = getWritableDatabase();
                        String valueOf = String.valueOf(iVar3.b());
                        String valueOf2 = String.valueOf(iVar3.a());
                        if (sQLiteDatabase.update("FILE_CACHE", contentValues, "parent=? and NAME=?", new String[]{valueOf, valueOf2}) >= 0) {
                            z = true;
                        }
                        if (sQLiteDatabase != null) {
                            if (sQLiteDatabase.isOpen()) {
                                sQLiteDatabase.close();
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        if (sQLiteDatabase != null) {
                            if (sQLiteDatabase.isOpen()) {
                                sQLiteDatabase.close();
                            }
                        }
                    }
                }
            } catch (Throwable th) {
            }
        }
        return z;
    }

    public final synchronized void a(List<String> list, List<String> list2) {
        Exception e;
        Throwable th;
        SQLiteDatabase sQLiteDatabase = null;
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            try {
                writableDatabase.beginTransaction();
                for (int i = 0; i < list.size(); i++) {
                    String str = (String) list.get(i);
                    String str2 = (String) list2.get(i);
                    i iVar = new i();
                    iVar.a(str);
                    i iVar2 = new i();
                    iVar2.a(str2);
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("NAME", iVar2.a());
                    contentValues.put("parent", iVar2.b());
                    str = String.valueOf(iVar.b());
                    String valueOf = String.valueOf(iVar.a());
                    writableDatabase.update("FILE_CACHE", contentValues, "parent=? and NAME=?", new String[]{str, valueOf});
                }
                writableDatabase.setTransactionSuccessful();
                if (writableDatabase != null) {
                    if (writableDatabase.isOpen()) {
                        writableDatabase.endTransaction();
                        writableDatabase.close();
                    }
                }
            } catch (Exception e2) {
                e = e2;
                sQLiteDatabase = writableDatabase;
                e.printStackTrace();
                if (sQLiteDatabase != null) {
                    if (sQLiteDatabase.isOpen()) {
                        sQLiteDatabase.endTransaction();
                        sQLiteDatabase.close();
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (writableDatabase != null) {
                    if (writableDatabase.isOpen()) {
                        writableDatabase.endTransaction();
                        writableDatabase.close();
                    }
                }
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
            try {
                e.printStackTrace();
                if (sQLiteDatabase != null) {
                    if (sQLiteDatabase.isOpen()) {
                        sQLiteDatabase.endTransaction();
                        sQLiteDatabase.close();
                    }
                }
            } catch (Throwable th3) {
                th = th3;
                writableDatabase = sQLiteDatabase;
                if (writableDatabase != null) {
                    if (writableDatabase.isOpen()) {
                        writableDatabase.endTransaction();
                        writableDatabase.close();
                    }
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            writableDatabase = null;
            if (writableDatabase != null) {
                if (writableDatabase.isOpen()) {
                    writableDatabase.endTransaction();
                    writableDatabase.close();
                }
            }
            throw th;
        }
    }

    public final synchronized boolean b(List<i> list) {
        boolean z;
        SQLiteDatabase sQLiteDatabase = null;
        synchronized (this) {
            try {
                sQLiteDatabase = getWritableDatabase();
                sQLiteDatabase.beginTransaction();
                int size = list.size();
                ContentValues contentValues = new ContentValues();
                for (int i = 0; i < size; i++) {
                    i iVar = (i) list.get(i);
                    contentValues.clear();
                    contentValues.put("NAME", iVar.a());
                    contentValues.put("parent", iVar.b());
                    contentValues.put("FILESIZE", Long.valueOf(iVar.i));
                    contentValues.put("FILETYPE", Integer.valueOf(iVar.e()));
                    contentValues.put("LASTMODIFY", Long.valueOf(iVar.h));
                    sQLiteDatabase.insert("FILE_CACHE", null, contentValues);
                }
                sQLiteDatabase.setTransactionSuccessful();
                z = true;
                if (sQLiteDatabase != null) {
                    if (sQLiteDatabase.isOpen()) {
                        sQLiteDatabase.endTransaction();
                        sQLiteDatabase.close();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                if (sQLiteDatabase != null) {
                    if (sQLiteDatabase.isOpen()) {
                        sQLiteDatabase.endTransaction();
                        sQLiteDatabase.close();
                        z = false;
                    }
                }
                z = false;
            }
        }
        return z;
    }

    public final synchronized List<i> a(int i, String str) {
        List<i> arrayList;
        Cursor cursor = null;
        synchronized (this) {
            if (!str.endsWith(MqttTopic.TOPIC_LEVEL_SEPARATOR)) {
                str = str + MqttTopic.TOPIC_LEVEL_SEPARATOR;
            }
            arrayList = new ArrayList();
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                try {
                    new StringBuilder("type:").append(i).append(" parent").append(str);
                    cursor = writableDatabase.rawQuery(new StringBuilder("select * from FILE_CACHE where FILETYPE=").append(i).append(" and parent='").append(str).append("'").toString(), null);
                    if (cursor != null) {
                        new StringBuilder("type:").append(i).append(" parent").append(str).append(" rows:").append(cursor.getCount());
                        int columnIndex = cursor.getColumnIndex("_ID");
                        int columnIndex2 = cursor.getColumnIndex("NAME");
                        int columnIndex3 = cursor.getColumnIndex("parent");
                        int columnIndex4 = cursor.getColumnIndex("FILESIZE");
                        int columnIndex5 = cursor.getColumnIndex("FILETYPE");
                        int columnIndex6 = cursor.getColumnIndex("LASTMODIFY");
                        if (cursor.moveToFirst()) {
                            do {
                                i iVar = new i();
                                iVar.f = (long) cursor.getInt(columnIndex);
                                iVar.h = cursor.getLong(columnIndex6);
                                iVar.i = cursor.getLong(columnIndex4);
                                int i2 = cursor.getInt(columnIndex5);
                                EFileCategoryType[] values = EFileCategoryType.values();
                                if (i2 < 0 || i2 >= values.length) {
                                    iVar.j = EFileCategoryType.E_OTHER_CATEGORY;
                                } else {
                                    iVar.j = values[i2];
                                }
                                iVar.a(cursor.getString(columnIndex3), cursor.getString(columnIndex2));
                                arrayList.add(iVar);
                            } while (cursor.moveToNext());
                        }
                    }
                    if (cursor != null) {
                        if (!cursor.isClosed()) {
                            cursor.close();
                        }
                    }
                    if (writableDatabase != null && writableDatabase.isOpen()) {
                        writableDatabase.close();
                    }
                } catch (Exception e) {
                    e = e;
                }
            } catch (Exception e2) {
                e = e2;
                writableDatabase = null;
                try {
                    Exception e3;
                    e3.printStackTrace();
                    if (cursor != null) {
                        if (!cursor.isClosed()) {
                            cursor.close();
                        }
                    }
                    if (writableDatabase != null && writableDatabase.isOpen()) {
                        writableDatabase.close();
                    }
                } catch (Throwable th) {
                    Throwable th2 = th;
                    if (cursor != null) {
                        if (!cursor.isClosed()) {
                            cursor.close();
                        }
                    }
                    writableDatabase.close();
                    throw th2;
                }
                return arrayList;
            } catch (Throwable th3) {
                th2 = th3;
                writableDatabase = null;
                if (cursor != null) {
                    if (cursor.isClosed()) {
                        cursor.close();
                    }
                }
                if (writableDatabase != null && writableDatabase.isOpen()) {
                    writableDatabase.close();
                }
                throw th2;
            }
        }
        return arrayList;
    }
}
