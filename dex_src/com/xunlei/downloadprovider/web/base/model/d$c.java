package com.xunlei.downloadprovider.web.base.model;

import android.content.ContentValues;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import com.xunlei.downloadprovider.web.base.model.a.a;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: ShortMovieDetailDataLoader.java
private class d$c implements Runnable {
    final /* synthetic */ d a;

    private d$c(d dVar) {
        this.a = dVar;
    }

    public final void run() {
        SQLiteException e;
        Throwable th;
        SQLiteDatabase sQLiteDatabase = null;
        if (this.a.b != null && this.a.i != null && this.a.i.size() > 0) {
            a aVar = this.a.b;
            ArrayList arrayList = this.a.i;
            if (!(arrayList == null || arrayList.size() == 0)) {
                new StringBuilder("insert data size =>").append(arrayList.size());
                SQLiteDatabase writableDatabase;
                try {
                    writableDatabase = aVar.getWritableDatabase();
                    try {
                        writableDatabase.beginTransaction();
                        ContentValues contentValues = new ContentValues();
                        Iterator it = arrayList.iterator();
                        while (it.hasNext()) {
                            int i;
                            int i2;
                            a aVar2 = (a) it.next();
                            contentValues.clear();
                            contentValues.put("res_id", aVar2.e);
                            contentValues.put("src_id", aVar2.f);
                            contentValues.put("usr_id", Long.valueOf(aVar2.g));
                            contentValues.put("comment_id", Long.valueOf(aVar2.a));
                            contentValues.put("has_zan", Integer.valueOf(aVar2.b ? 1 : 0));
                            String str = "has_login";
                            if (aVar2.c) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            contentValues.put(str, Integer.valueOf(i));
                            String str2 = "has_commit";
                            if (aVar2.d) {
                                i2 = 1;
                            } else {
                                i2 = 0;
                            }
                            contentValues.put(str2, Integer.valueOf(i2));
                            writableDatabase.insertWithOnConflict("comment_zan_info", null, contentValues, SimpleLog.LOG_LEVEL_ERROR);
                        }
                        writableDatabase.setTransactionSuccessful();
                        if (writableDatabase != null && writableDatabase.isOpen()) {
                            writableDatabase.endTransaction();
                            writableDatabase.close();
                        }
                    } catch (SQLiteConstraintException e2) {
                        sQLiteDatabase = writableDatabase;
                        sQLiteDatabase.endTransaction();
                        sQLiteDatabase.close();
                        this.a.i.clear();
                    } catch (SQLiteException e3) {
                        e = e3;
                        try {
                            e.printStackTrace();
                            writableDatabase.endTransaction();
                            writableDatabase.close();
                        } catch (Throwable th2) {
                            th = th2;
                            if (writableDatabase != null && writableDatabase.isOpen()) {
                                writableDatabase.endTransaction();
                                writableDatabase.close();
                            }
                            throw th;
                        }
                        this.a.i.clear();
                    }
                } catch (SQLiteConstraintException e4) {
                    if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                        sQLiteDatabase.endTransaction();
                        sQLiteDatabase.close();
                    }
                    this.a.i.clear();
                } catch (SQLiteException e5) {
                    SQLiteException sQLiteException = e5;
                    writableDatabase = null;
                    e = sQLiteException;
                    e.printStackTrace();
                    if (writableDatabase != null && writableDatabase.isOpen()) {
                        writableDatabase.endTransaction();
                        writableDatabase.close();
                    }
                    this.a.i.clear();
                } catch (Throwable th3) {
                    Throwable th4 = th3;
                    writableDatabase = null;
                    th = th4;
                    writableDatabase.endTransaction();
                    writableDatabase.close();
                    throw th;
                }
            }
            this.a.i.clear();
        }
    }
}
