package com.taobao.accs.c;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import com.taobao.accs.common.Constants;
import com.taobao.accs.internal.b;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.UtilityImpl;
import com.xunlei.download.DownloadManager;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.android.agoo.message.MessageService;

// compiled from: Taobao
public class a extends SQLiteOpenHelper {
    private static volatile a c;
    private static final Lock e;
    public int a;
    LinkedList<a> b;
    private Context d;

    // compiled from: Taobao
    private class a {
        String a;
        Object[] b;

        private a(String str, Object[] objArr) {
            this.a = str;
            this.b = objArr;
        }
    }

    static {
        c = null;
        e = new ReentrantLock();
    }

    public SQLiteDatabase getWritableDatabase() {
        return !com.taobao.accs.utl.a.a(super.getWritableDatabase().getPath(), 102400) ? null : super.getWritableDatabase();
    }

    public static a a(Context context) {
        if (c == null) {
            synchronized (a.class) {
                if (c == null) {
                    c = new a(context, Constants.DB_NAME, null, 3);
                }
            }
        }
        return c;
    }

    private a(Context context, String str, CursorFactory cursorFactory, int i) {
        super(context, str, cursorFactory, i);
        this.a = 0;
        this.b = new LinkedList();
        this.d = context;
    }

    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        if (e.tryLock()) {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS traffic(_id INTEGER PRIMARY KEY AUTOINCREMENT, date TEXT, host TEXT,serviceid TEXT, bid TEXT, isbackground TEXT, size TEXT)");
        }
        e.unlock();
    }

    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i < i2) {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS service");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS network");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS ping");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS msg");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS ack");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS election");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS bindApp");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS bindUser");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS traffic");
            onCreate(sQLiteDatabase);
        }
    }

    public void a(String str, String str2, String str3, boolean z, long j, String str4) {
        if (a(str, str3, z, str4)) {
            a("UPDATE traffic SET size=? WHERE date=? AND host=? AND bid=? AND isbackground=?", new Object[]{Long.valueOf(j), str4, str, str3, String.valueOf(z)}, true);
            return;
        }
        a("INSERT INTO traffic VALUES(null,?,?,?,?,?,?)", new Object[]{str4, str, str2, str3, String.valueOf(z), Long.valueOf(j)}, true);
    }

    private synchronized boolean a(String str, String str2, boolean z, String str3) {
        boolean z2;
        Exception e;
        Cursor cursor = null;
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (writableDatabase == null) {
                z2 = false;
            } else {
                query = writableDatabase.query("traffic", new String[]{DownloadManager.COLUMN_ID, "date", b.ELECTION_KEY_HOST, "serviceid", "bid", "isbackground", "size"}, "date=? AND host=? AND bid=? AND isbackground=?", new String[]{str3, str, str2, String.valueOf(z)}, null, null, null, MessageService.MSG_DB_COMPLETE);
                if (query != null) {
                    try {
                        if (query.getCount() > 0) {
                            if (query != null) {
                                query.close();
                            }
                            z2 = true;
                        }
                    } catch (Exception e2) {
                        e = e2;
                        ALog.w("DBHelper", e.toString(), new Object[0]);
                        if (query != null) {
                            query.close();
                        }
                        z2 = false;
                        return z2;
                    }
                }
                if (query != null) {
                    query.close();
                }
                z2 = false;
            }
        } catch (Exception e3) {
            e = e3;
            query = null;
            try {
                ALog.w("DBHelper", e.toString(), new Object[0]);
                Cursor query;
                if (query != null) {
                    query.close();
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                cursor = query;
                if (cursor != null) {
                    cursor.close();
                }
                throw th2;
            }
            z2 = false;
            return z2;
        } catch (Throwable th3) {
            th2 = th3;
            if (cursor != null) {
                cursor.close();
            }
            throw th2;
        }
        return z2;
    }

    public void a() {
        a("DELETE FROM traffic", null, true);
    }

    public List<com.taobao.accs.ut.monitor.TrafficsMonitor.a> a(boolean z) {
        Cursor query;
        Exception e;
        Cursor cursor = null;
        synchronized (this) {
            List<com.taobao.accs.ut.monitor.TrafficsMonitor.a> arrayList = new ArrayList();
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                if (writableDatabase == null) {
                    return null;
                }
                if (z) {
                    query = writableDatabase.query("traffic", new String[]{DownloadManager.COLUMN_ID, "date", b.ELECTION_KEY_HOST, "serviceid", "bid", "isbackground", "size"}, "date=?", new String[]{UtilityImpl.formatDay(System.currentTimeMillis())}, null, null, null, MessageService.MSG_DB_COMPLETE);
                } else {
                    query = writableDatabase.query("traffic", new String[]{DownloadManager.COLUMN_ID, "date", b.ELECTION_KEY_HOST, "serviceid", "bid", "isbackground", "size"}, null, null, null, null, null, MessageService.MSG_DB_COMPLETE);
                }
                if (query == null) {
                    if (query != null) {
                        query.close();
                    }
                    return null;
                }
                try {
                    if (query.moveToFirst()) {
                        do {
                            String string = query.getString(1);
                            String string2 = query.getString(XZBDevice.DOWNLOAD_LIST_RECYCLE);
                            String string3 = query.getString(XZBDevice.DOWNLOAD_LIST_FAILED);
                            String string4 = query.getString(XZBDevice.DOWNLOAD_LIST_ALL);
                            boolean booleanValue = Boolean.valueOf(query.getString(XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED)).booleanValue();
                            long j = query.getLong(R.styleable.Toolbar_contentInsetEnd);
                            if (string4 != null && j > 0) {
                                arrayList.add(new com.taobao.accs.ut.monitor.TrafficsMonitor.a(string, string4, string3, booleanValue, string2, j));
                                ALog.d("DBHelper", new StringBuilder("resotre traffics from db bid:").append(string4).append(" serviceid:").append(string3).append(" host:").append(string2).append(" size:").append(j).toString(), new Object[0]);
                            }
                        } while (query.moveToNext());
                    }
                    if (query != null) {
                        query.close();
                    }
                } catch (Exception e2) {
                    e = e2;
                    cursor = query;
                    ALog.w("DBHelper", e.toString(), new Object[0]);
                    if (cursor != null) {
                        cursor.close();
                    }
                    return arrayList;
                } catch (Throwable th) {
                    th = th;
                    cursor = query;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
                return arrayList;
            } catch (Exception e3) {
                e = e3;
                try {
                    ALog.w("DBHelper", e.toString(), new Object[0]);
                    if (cursor != null) {
                        cursor.close();
                    }
                } catch (Throwable th2) {
                    Throwable th3;
                    th3 = th2;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th3;
                }
                return arrayList;
            }
        }
    }

    private synchronized void a(String str, Object[] objArr, boolean z) {
        try {
            this.b.add(new a(str, objArr, null));
            if (this.b.size() > 5 || z) {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                if (writableDatabase != null) {
                    while (this.b.size() > 0) {
                        a aVar = (a) this.b.removeFirst();
                        if (aVar.b != null) {
                            writableDatabase.execSQL(aVar.a, aVar.b);
                        } else {
                            writableDatabase.execSQL(aVar.a);
                        }
                        if (aVar.a.contains("INSERT")) {
                            this.a++;
                            if (this.a > 4000) {
                                ALog.d("DBHelper", "db is full!", new Object[0]);
                                onUpgrade(writableDatabase, 0, 1);
                                this.a = 0;
                                break;
                            }
                        }
                    }
                    writableDatabase.close();
                }
            }
        } catch (Exception e) {
            ALog.d("DBHelper", e.toString(), new Object[0]);
        }
    }
}
