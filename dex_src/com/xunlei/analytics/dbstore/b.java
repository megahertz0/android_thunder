package com.xunlei.analytics.dbstore;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Process;
import android.util.Log;
import com.umeng.message.proguard.j;
import com.xunlei.analytics.c.f;
import com.xunlei.analytics.config.a;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class b {
    private static volatile b d;
    private static AtomicBoolean e;
    SQLiteDatabase a;
    private c b;
    private int c;

    static {
        e = new AtomicBoolean(false);
    }

    private b() {
        this.a = null;
        this.b = new c(this, a.d());
        this.a = this.b.getReadableDatabase();
    }

    public static b a() {
        if (d == null) {
            synchronized (b.class) {
                if (d == null) {
                    d = new b();
                }
            }
        }
        return d;
    }

    private void a(Exception exception) {
        f.a(new StringBuilder("Exception: ").append(Log.getStackTraceString(exception)).toString());
        if (e.compareAndSet(false, true)) {
            try {
                if (this.c > 5) {
                    Process.killProcess(Process.myPid());
                }
                if (this.a != null) {
                    try {
                        this.a.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (this.b != null) {
                    try {
                        this.b.close();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                this.b = new c(this, a.d());
                this.a = this.b.getReadableDatabase();
                e.set(false);
            } catch (Exception e3) {
                exception.printStackTrace();
                this.c++;
                e.set(false);
            }
        }
    }

    private void b(String str, String str2, String str3) {
        if (f.a()) {
            f.a(new StringBuilder("insert event Appid=").append(str).append(",InterId=").append(str2).append(",mEventData=").append(str3).toString());
        }
    }

    public int a(long j) {
        try {
            return this.a.delete(AnalyticsConstant.STORAGE_DB_TABLE_NAME, "_eventTime< ?", new String[]{String.valueOf(j)});
        } catch (Exception e) {
            a(e);
            return 0;
        }
    }

    public List<d> a(int i) {
        Exception e;
        Cursor cursor = null;
        try {
            Cursor query = this.a.query(AnalyticsConstant.STORAGE_DB_TABLE_NAME, new String[]{a.a, a.b, a.c, a.d, j.g}, null, null, null, null, "_id desc ", new StringBuilder("0,").append(i).toString());
            if (query != null) {
                try {
                    List<d> arrayList = new ArrayList();
                    while (query.moveToNext()) {
                        d dVar = new d(query.getString(0), query.getString(1), query.getString(SimpleLog.LOG_LEVEL_DEBUG));
                        dVar.d = query.getInt(MqttConnectOptions.MQTT_VERSION_3_1_1);
                        arrayList.add(dVar);
                    }
                    arrayList.trimToSize();
                    if (query == null) {
                        return arrayList;
                    }
                    query.close();
                    return arrayList;
                } catch (Exception e2) {
                    e = e2;
                    try {
                        a(e);
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
                    return null;
                }
            }
            if (query != null) {
                query.close();
            }
            return null;
        } catch (Exception e3) {
            e = e3;
            query = null;
            a(e);
            if (query != null) {
                query.close();
            }
            return null;
        } catch (Throwable th3) {
            th2 = th3;
            if (cursor != null) {
                cursor.close();
            }
            throw th2;
        }
    }

    public boolean a(d dVar) {
        try {
            this.a.execSQL("insert into analytics(_appId,_interId,_eventData,_eventTime)values(?,?,?,?)", new Object[]{dVar.a, dVar.b, dVar.c, Long.valueOf(System.currentTimeMillis())});
            com.xunlei.analytics.a.a.a().a(false);
            return true;
        } catch (Exception e) {
            a(e);
            return false;
        }
    }

    public boolean a(String str, String str2, String str3) {
        try {
            this.a.execSQL("insert into analytics(_appId,_interId,_eventData,_eventTime)values(?,?,?,?)", new Object[]{str, str2, str3, Long.valueOf(System.currentTimeMillis())});
            b(str, str2, str3);
            com.xunlei.analytics.a.a.a().a(false);
            return true;
        } catch (Exception e) {
            a(e);
            return false;
        }
    }

    public boolean a(List<d> list) {
        try {
            this.a.beginTransaction();
            for (d dVar : list) {
                this.a.execSQL("insert into analytics(_appId,_interId,_eventData,_eventTime)values(?,?,?,?)", new Object[]{dVar.a, dVar.b, dVar.c, Long.valueOf(System.currentTimeMillis())});
                com.xunlei.analytics.a.a.a().a(false);
            }
            this.a.setTransactionSuccessful();
            this.a.endTransaction();
            return true;
        } catch (Exception e) {
            a(e);
            return false;
        }
    }

    public boolean a(Integer... numArr) {
        try {
            if (numArr.length > 0) {
                this.a.beginTransaction();
                int length = numArr.length;
                for (int i = 0; i < length; i++) {
                    int intValue = numArr[i].intValue();
                    this.a.execSQL("delete from analytics where _id= ?", new Object[]{Integer.valueOf(intValue)});
                }
                this.a.setTransactionSuccessful();
                this.a.endTransaction();
                return true;
            }
        } catch (Exception e) {
            a(e);
        }
        return false;
    }

    public int b() {
        Cursor cursor = null;
        int i = 0;
        try {
            cursor = this.a.rawQuery("select count(*) from analytics", null);
            if (cursor != null && cursor.moveToFirst()) {
                i = cursor.getInt(0);
            }
            if (cursor != null) {
                cursor.close();
            }
        } catch (Exception e) {
            try {
                a(e);
                if (cursor != null) {
                    cursor.close();
                }
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
        return i;
    }

    public boolean b(List<d> list) {
        try {
            if (list.size() > 0) {
                this.a.beginTransaction();
                for (d dVar : list) {
                    this.a.execSQL("delete from analytics where _id= ?", new Object[]{Integer.valueOf(dVar.d)});
                    f.a(new StringBuilder("delete event ").append(dVar.toString()).toString());
                }
                this.a.setTransactionSuccessful();
                this.a.endTransaction();
                return true;
            }
        } catch (Exception e) {
            a(e);
        }
        return false;
    }
}
