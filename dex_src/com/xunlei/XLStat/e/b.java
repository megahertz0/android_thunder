package com.xunlei.XLStat.e;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.umeng.message.proguard.j;
import com.xunlei.XLStat.XLStatLog.XLStatLog;
import com.xunlei.XLStat.e;
import com.xunlei.XLStat.f;
import com.xunlei.XLStat.g;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class b extends SQLiteOpenHelper {
    private static String b;
    private static String c;
    private static String d;
    private static String e;
    private String a;
    private String f;
    private final Object g;

    static {
        b = "XLSTAT_EVENT_2";
        c = "XLSTAT_CONTEXT_2";
        d = "XLSTAT_HEARTBEAT_2";
        e = "XLSTAT_SessionData_2";
    }

    public b(Context context, String str, String str2) {
        super(context, str, null, 2);
        this.a = "DatebaseHelper";
        this.f = BuildConfig.VERSION_NAME;
        this.g = new Object();
        this.f = str2;
        if (str2 != null && !BuildConfig.VERSION_NAME.equals(str2)) {
            b = new StringBuilder("XLSTAT_EVENT_").append(str2).append("_2").toString();
            c = new StringBuilder("XLSTAT_CONTEXT_").append(str2).append("_2").toString();
            d = new StringBuilder("XLSTAT_HEARTBEAT_").append(str2).append("_2").toString();
        }
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
            String str = e.substring(0, e.lastIndexOf("_")) + "_1";
            String str2 = c.substring(0, c.lastIndexOf("_")) + "_1";
            String str3 = b.substring(0, b.lastIndexOf("_")) + "_1";
            String str4 = d.substring(0, d.lastIndexOf("_")) + "_1";
            if (i == 1 && i2 == 2) {
                sQLiteDatabase.execSQL(new StringBuilder(" DROP TABLE IF EXISTS ").append(str).toString());
                sQLiteDatabase.execSQL(new StringBuilder(" DROP TABLE IF EXISTS ").append(str2).toString());
                sQLiteDatabase.execSQL(new StringBuilder(" DROP TABLE IF EXISTS ").append(str3).toString());
                sQLiteDatabase.execSQL(new StringBuilder(" DROP TABLE IF EXISTS ").append(str4).toString());
            }
            sQLiteDatabase.execSQL(" DROP TABLE IF EXISTS mytable");
            onCreate(sQLiteDatabase);
        }
    }

    public void a(String str) {
        XLStatLog.d(this.a, "createEventTable", new StringBuilder("tableName: ").append(str).toString());
        String toString = new StringBuilder(j.o).append(str).append(" (EVENT_ORDER INTEGER PRIMARY KEY AUTOINCREMENT, EVENT_ID INTEGER, PROCESS_ID INTEGER, ATTRIBUTE1 TEXT, ATTRIBUTE2 TEXT, EVENT_COST1 INTEGER, EVENT_COST2 INTEGER, EVENT_COST3 INTEGER, EVENT_COST4 INTEGER, EVENT_EXT TEXT, EVENT_TIME LONG, EVENT_REPORTPOLICY INTEGER)").toString();
        SQLiteDatabase sQLiteDatabase = null;
        synchronized (this.g) {
            try {
                sQLiteDatabase = getWritableDatabase();
                sQLiteDatabase.execSQL(toString);
                a(sQLiteDatabase);
            } catch (Exception e) {
                XLStatLog.d(this.a, "createEventTable", new StringBuilder("create ").append(str).append(" failed ... ").toString());
                e.printStackTrace();
                a(sQLiteDatabase);
            }
            XLStatLog.d(this.a, "createEventTable", new StringBuilder("create ").append(str).append(" successfully").toString());
        }
    }

    public void b(String str) {
        XLStatLog.d(this.a, "createContextTable", new StringBuilder("tableName: ").append(str).toString());
        String toString = new StringBuilder(j.o).append(str).append(" (CONTEXT_ORDER INTEGER PRIMARY KEY AUTOINCREMENT, PROCESS_ID INTEGER, SRC_CONTEXT_ID INTEGER, CONTEXT_ID INTEGER, SESSION_ID INTEGER, EXT_DATA TEXT, CONTEXT_TIME LONG, CONTEXT_REPORTPOLICY INTEGER)").toString();
        SQLiteDatabase sQLiteDatabase = null;
        synchronized (this.g) {
            try {
                sQLiteDatabase = getWritableDatabase();
                sQLiteDatabase.execSQL(toString);
                a(sQLiteDatabase);
            } catch (Exception e) {
                XLStatLog.d(this.a, "createContextTable", new StringBuilder("create ").append(str).append(" failed ... ").toString());
                e.printStackTrace();
                a(sQLiteDatabase);
            }
            XLStatLog.d(this.a, "createContextTable", new StringBuilder("create ").append(str).append(" successfully").toString());
        }
    }

    public void c(String str) {
        XLStatLog.d(this.a, "createHeartbeatTable", new StringBuilder("tableName: ").append(str).toString());
        String toString = new StringBuilder(j.o).append(str).append(" (HEARTBEAT_ORDER INTEGER PRIMARY KEY AUTOINCREMENT, PROCESS_ID INTEGER, SEQ_ID INTEGER, HEARTBEAT_ID INTEGER, STATUS INTEGER, EXT_DATA TEXT, TIME LONG, REPORT_POLICY INTEGER)").toString();
        SQLiteDatabase sQLiteDatabase = null;
        synchronized (this.g) {
            try {
                sQLiteDatabase = getWritableDatabase();
                sQLiteDatabase.execSQL(toString);
                a(sQLiteDatabase);
            } catch (Exception e) {
                XLStatLog.d(this.a, "createHeartbeatTable", new StringBuilder("create ").append(str).append(" failed ... ").toString());
                e.printStackTrace();
                a(sQLiteDatabase);
            }
            XLStatLog.d(this.a, "createHeartbeatTable", new StringBuilder("create ").append(str).append(" successfully").toString());
        }
    }

    public void d(String str) {
        String toString = new StringBuilder(j.o).append(str).append(" (SessionDataId INTEGER PRIMARY KEY AUTOINCREMENT, sessionId INTEGER, sessionStartTime LONG, activityName TEXT, activityDuration SHORT, activityEndTime LONG, sended INTEGER ,extData TEXT,time LONG)").toString();
        SQLiteDatabase sQLiteDatabase = null;
        synchronized (this.g) {
            try {
                sQLiteDatabase = getWritableDatabase();
                sQLiteDatabase.execSQL(toString);
                a(sQLiteDatabase);
            } catch (Throwable th) {
            }
        }
    }

    public void a(String str, Object obj) {
        synchronized (this.g) {
            ArrayList arrayList;
            if (b.equals(str)) {
                if (obj instanceof ArrayList) {
                    arrayList = (ArrayList) obj;
                    a(str, arrayList);
                    XLStatLog.d(this.a, "insert", new StringBuilder("insert ").append(arrayList.size()).append(" evnts into ").append(str).toString());
                } else {
                    XLStatLog.d(this.a, "insert", "evnet objList type error ... ");
                }
            } else if (c.equals(str)) {
                if (obj instanceof ArrayList) {
                    arrayList = (ArrayList) obj;
                    b(str, arrayList);
                    XLStatLog.d(this.a, "insert", new StringBuilder("insert ").append(arrayList.size()).append(" contexts into ").append(str).toString());
                } else {
                    XLStatLog.d(this.a, "insert", "context objList type error ... ");
                }
            } else if (d.equals(str)) {
                if (obj instanceof ArrayList) {
                    arrayList = (ArrayList) obj;
                    c(str, arrayList);
                    XLStatLog.d(this.a, "insert", new StringBuilder("insert ").append(arrayList.size()).append(" heartbeats into ").append(str).toString());
                } else {
                    XLStatLog.d(this.a, "insert", "heartbeat objList type error ... ");
                }
            } else if (e.equals(str)) {
                if (obj instanceof ArrayList) {
                    arrayList = (ArrayList) obj;
                    d(str, arrayList);
                    XLStatLog.d(this.a, "insert", new StringBuilder("insert ").append(arrayList.size()).append(" heartbeats into ").append(str).toString());
                } else {
                    XLStatLog.d(this.a, "insert", "session objList type error ... ");
                }
                XLStatLog.d(this.a, "insert", "insert table name is invalide ... ");
            } else {
                XLStatLog.d(this.a, "insert", "insert table name is invalide ... ");
            }
        }
    }

    public void a(String str, ArrayList<e> arrayList) {
        XLStatLog.d(this.a, "insertEvents", new StringBuilder("table name: ").append(str).append("  event size: ").append(arrayList.size()).toString());
        SQLiteDatabase sQLiteDatabase = null;
        try {
            sQLiteDatabase = getWritableDatabase();
            sQLiteDatabase.beginTransaction();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                e eVar = (e) it.next();
                sQLiteDatabase.execSQL(new StringBuilder("INSERT INTO ").append(str).append(" (EVENT_ID, PROCESS_ID, ATTRIBUTE1, ATTRIBUTE2, EVENT_COST1, EVENT_COST2, EVENT_COST3, EVENT_COST4, EVENT_EXT, EVENT_TIME, EVENT_REPORTPOLICY) VALUES ('").append(eVar.b).append("','").append(eVar.a).append("','").append(eVar.c).append("','").append(eVar.d).append("','").append(eVar.e).append("','").append(eVar.f).append("','").append(eVar.g).append("','").append(eVar.h).append("','").append(eVar.i).append("','").append(eVar.j).append("','").append(eVar.l).append("')").toString());
            }
            sQLiteDatabase.setTransactionSuccessful();
            sQLiteDatabase.endTransaction();
            a(sQLiteDatabase);
        } catch (Exception e) {
            e.printStackTrace();
            sQLiteDatabase.endTransaction();
            a(sQLiteDatabase);
        }
        XLStatLog.d(this.a, "insertEvents", new StringBuilder("insert ").append(arrayList.size()).append("  events successfully").toString());
    }

    public void b(String str, ArrayList<com.xunlei.XLStat.f.b> arrayList) {
        XLStatLog.d(this.a, "insertContexts", new StringBuilder("table name: ").append(str).append("  event size: ").append(arrayList.size()).toString());
        SQLiteDatabase sQLiteDatabase = null;
        try {
            sQLiteDatabase = getWritableDatabase();
            sQLiteDatabase.beginTransaction();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                com.xunlei.XLStat.f.b bVar = (com.xunlei.XLStat.f.b) it.next();
                sQLiteDatabase.execSQL(new StringBuilder("INSERT INTO ").append(str).append(" (PROCESS_ID, SRC_CONTEXT_ID, CONTEXT_ID, SESSION_ID, EXT_DATA, CONTEXT_TIME, CONTEXT_REPORTPOLICY) VALUES ('").append(bVar.a).append("','").append(bVar.b).append("','").append(bVar.c).append("','").append(bVar.d).append("','").append(bVar.e).append("','").append(bVar.f).append("','").append(bVar.h).append("')").toString());
            }
            sQLiteDatabase.setTransactionSuccessful();
            sQLiteDatabase.endTransaction();
            a(sQLiteDatabase);
        } catch (Exception e) {
            e.printStackTrace();
            sQLiteDatabase.endTransaction();
            a(sQLiteDatabase);
        }
        XLStatLog.d(this.a, "insertContexts", new StringBuilder("insert ").append(arrayList.size()).append("  contexts successfully").toString());
    }

    public void c(String str, ArrayList<f> arrayList) {
        XLStatLog.d(this.a, "insertHeartbeats", new StringBuilder("table name: ").append(str).append(" heartbeats size: ").append(arrayList.size()).toString());
        SQLiteDatabase sQLiteDatabase = null;
        try {
            sQLiteDatabase = getWritableDatabase();
            sQLiteDatabase.beginTransaction();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                f fVar = (f) it.next();
                sQLiteDatabase.execSQL(new StringBuilder("INSERT INTO ").append(str).append(" (PROCESS_ID, SEQ_ID, HEARTBEAT_ID, STATUS, EXT_DATA, TIME, REPORT_POLICY) VALUES ('").append(fVar.a).append("','").append(fVar.b).append("','").append(fVar.c).append("','").append(fVar.d).append("','").append(fVar.e).append("','").append(fVar.f).append("','").append(fVar.h).append("')").toString());
            }
            sQLiteDatabase.setTransactionSuccessful();
            sQLiteDatabase.endTransaction();
            a(sQLiteDatabase);
        } catch (Exception e) {
            e.printStackTrace();
            sQLiteDatabase.endTransaction();
            a(sQLiteDatabase);
        }
        XLStatLog.d(this.a, "insertHeartbeats", new StringBuilder("insert ").append(arrayList.size()).append(" heartbeats successfully").toString());
    }

    public void d(String str, ArrayList<g> arrayList) {
        XLStatLog.d("wang.log.hubble", "insertSession", new StringBuilder("table name:\n ").append(str).append(" session size: \n").append(arrayList.size()).toString());
        SQLiteDatabase sQLiteDatabase = null;
        try {
            sQLiteDatabase = getWritableDatabase();
            sQLiteDatabase.beginTransaction();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                g gVar = (g) it.next();
                String toString = new StringBuilder("INSERT INTO ").append(str).append(" ( sessionId ,sessionStartTime , activityName, activityDuration, activityEndTime, sended ,extData) VALUES ('").append(gVar.b).append("','").append(gVar.c).append("','").append(gVar.d).append("','").append(gVar.e).append("','").append(gVar.f).append("','").append(gVar.h).append("','").append(gVar.g).append("')").toString();
                XLStatLog.d("wang.log.hubble", "insertSession", new StringBuilder("table name:\n ").append(str).append(" session size: \n").append(arrayList.size()).append("\nsessionId :").append(gVar.b).append("\nsessionStartTime :").append(gVar.c).append("\n activityName: ").append(gVar.d).append("\n activityDuration :").append(gVar.e).append("\n activityEndTime :").append(gVar.f).toString());
                sQLiteDatabase.execSQL(toString);
            }
            sQLiteDatabase.setTransactionSuccessful();
            sQLiteDatabase.endTransaction();
            a(sQLiteDatabase);
        } catch (Exception e) {
            e.printStackTrace();
            sQLiteDatabase.endTransaction();
            a(sQLiteDatabase);
        }
        XLStatLog.d(this.a, "insertSessions", new StringBuilder("insert ").append(arrayList.size()).append(" heartbeats successfully").toString());
    }

    public Object a(String str, int i, String str2) {
        XLStatLog.d(this.a, "query", new StringBuilder("tableName: ").append(str).append(", strategy: ").append(i).toString());
        if (b.equals(str)) {
            ArrayList b = b(str, i, str2);
            XLStatLog.d(this.a, "query", new StringBuilder("events size: ").append(b.size()).toString());
            return b;
        } else if (c.equals(str)) {
            c = c(str, i, str2);
            XLStatLog.d(this.a, "query", new StringBuilder("contexts size: ").append(((ArrayList) c).size()).toString());
            return c;
        } else if (d.equals(str)) {
            c = d(str, i, str2);
            XLStatLog.d(this.a, "query", new StringBuilder("heartbeats size: ").append(((ArrayList) c).size()).toString());
            return c;
        } else {
            XLStatLog.d(this.a, "query", new StringBuilder(" there is not the ").append(str).append(" table ... ").toString());
            return null;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.ArrayList<com.xunlei.XLStat.e> b(java.lang.String r14, int r15, java.lang.String r16) {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.XLStat.e.b.b(java.lang.String, int, java.lang.String):java.util.ArrayList<com.xunlei.XLStat.e>");
        /*
        this = this;
        r0 = r13.a;
        r1 = "queryEvent";
        r2 = new java.lang.StringBuilder;
        r3 = "table name: ";
        r2.<init>(r3);
        r2 = r2.append(r14);
        r3 = "  strategy: ";
        r2 = r2.append(r3);
        r2 = r2.append(r15);
        r2 = r2.toString();
        com.xunlei.XLStat.XLStatLog.XLStatLog.d(r0, r1, r2);
        r1 = 0;
        r9 = 0;
        r10 = new java.util.ArrayList;
        r10.<init>();
        r11 = r13.g;
        monitor-enter(r11);
        r0 = r13.getWritableDatabase();	 Catch:{ Exception -> 0x0161, all -> 0x0153 }
        r1 = -1;
        if (r15 != r1) goto L_0x0105;
    L_0x0034:
        r2 = 0;
        r3 = " EVENT_REPORTPOLICY>=? ";
        r1 = 1;
        r4 = new java.lang.String[r1];	 Catch:{ Exception -> 0x0166, all -> 0x0156 }
        r1 = 0;
        r5 = "-1";
        r4[r1] = r5;	 Catch:{ Exception -> 0x0166, all -> 0x0156 }
        r5 = 0;
        r6 = 0;
        r7 = "EVENT_TIME DESC ";
        r1 = r14;
        r8 = r16;
        r2 = r0.query(r1, r2, r3, r4, r5, r6, r7, r8);	 Catch:{ Exception -> 0x0166, all -> 0x0156 }
        r1 = "wang.log.query";
        r3 = "queryEvent";
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00d9, all -> 0x013e }
        r5 = "report_save ";
        r4.<init>(r5);	 Catch:{ Exception -> 0x00d9, all -> 0x013e }
        r5 = r2.getCount();	 Catch:{ Exception -> 0x00d9, all -> 0x013e }
        r4 = r4.append(r5);	 Catch:{ Exception -> 0x00d9, all -> 0x013e }
        r4 = r4.toString();	 Catch:{ Exception -> 0x00d9, all -> 0x013e }
        com.xunlei.XLStat.XLStatLog.XLStatLog.d(r1, r3, r4);	 Catch:{ Exception -> 0x00d9, all -> 0x013e }
    L_0x006a:
        if (r2 == 0) goto L_0x014c;
    L_0x006c:
        r1 = r2.getCount();	 Catch:{ Exception -> 0x00d9, all -> 0x013e }
        if (r1 <= 0) goto L_0x014c;
    L_0x0072:
        r1 = r2.moveToNext();	 Catch:{ Exception -> 0x00d9, all -> 0x013e }
        if (r1 == 0) goto L_0x014c;
    L_0x0078:
        r1 = new com.xunlei.XLStat.e;	 Catch:{ Exception -> 0x00d9, all -> 0x013e }
        r1.<init>();	 Catch:{ Exception -> 0x00d9, all -> 0x013e }
        r3 = 0;
        r3 = r2.getInt(r3);	 Catch:{ Exception -> 0x00d9, all -> 0x013e }
        r1.k = r3;	 Catch:{ Exception -> 0x00d9, all -> 0x013e }
        r3 = 1;
        r3 = r2.getInt(r3);	 Catch:{ Exception -> 0x00d9, all -> 0x013e }
        r1.b = r3;	 Catch:{ Exception -> 0x00d9, all -> 0x013e }
        r3 = 2;
        r3 = r2.getInt(r3);	 Catch:{ Exception -> 0x00d9, all -> 0x013e }
        r1.a = r3;	 Catch:{ Exception -> 0x00d9, all -> 0x013e }
        r3 = 3;
        r3 = r2.getString(r3);	 Catch:{ Exception -> 0x00d9, all -> 0x013e }
        r1.c = r3;	 Catch:{ Exception -> 0x00d9, all -> 0x013e }
        r3 = 4;
        r3 = r2.getString(r3);	 Catch:{ Exception -> 0x00d9, all -> 0x013e }
        r1.d = r3;	 Catch:{ Exception -> 0x00d9, all -> 0x013e }
        r3 = 5;
        r3 = r2.getInt(r3);	 Catch:{ Exception -> 0x00d9, all -> 0x013e }
        r1.e = r3;	 Catch:{ Exception -> 0x00d9, all -> 0x013e }
        r3 = 6;
        r3 = r2.getInt(r3);	 Catch:{ Exception -> 0x00d9, all -> 0x013e }
        r1.f = r3;	 Catch:{ Exception -> 0x00d9, all -> 0x013e }
        r3 = 7;
        r3 = r2.getInt(r3);	 Catch:{ Exception -> 0x00d9, all -> 0x013e }
        r1.g = r3;	 Catch:{ Exception -> 0x00d9, all -> 0x013e }
        r3 = 8;
        r3 = r2.getInt(r3);	 Catch:{ Exception -> 0x00d9, all -> 0x013e }
        r1.h = r3;	 Catch:{ Exception -> 0x00d9, all -> 0x013e }
        r3 = 9;
        r3 = r2.getString(r3);	 Catch:{ Exception -> 0x00d9, all -> 0x013e }
        r1.i = r3;	 Catch:{ Exception -> 0x00d9, all -> 0x013e }
        r3 = 10;
        r4 = r2.getLong(r3);	 Catch:{ Exception -> 0x00d9, all -> 0x013e }
        r1.j = r4;	 Catch:{ Exception -> 0x00d9, all -> 0x013e }
        r3 = 11;
        r3 = r2.getInt(r3);	 Catch:{ Exception -> 0x00d9, all -> 0x013e }
        r1.l = r3;	 Catch:{ Exception -> 0x00d9, all -> 0x013e }
        r10.add(r1);	 Catch:{ Exception -> 0x00d9, all -> 0x013e }
        goto L_0x0072;
    L_0x00d9:
        r1 = move-exception;
        r12 = r1;
        r1 = r2;
        r2 = r0;
        r0 = r12;
    L_0x00de:
        r0.printStackTrace();	 Catch:{ all -> 0x015c }
        r13.a(r1);	 Catch:{ all -> 0x0149 }
        r13.a(r2);	 Catch:{ all -> 0x0149 }
    L_0x00e7:
        monitor-exit(r11);	 Catch:{ all -> 0x0149 }
        r0 = r13.a;
        r1 = "queryEvent";
        r2 = new java.lang.StringBuilder;
        r3 = "events list size: ";
        r2.<init>(r3);
        r3 = r10.size();
        r2 = r2.append(r3);
        r2 = r2.toString();
        com.xunlei.XLStat.XLStatLog.XLStatLog.d(r0, r1, r2);
        return r10;
    L_0x0105:
        r2 = 0;
        r3 = "EVENT_REPORTPOLICY=?";
        r1 = 1;
        r4 = new java.lang.String[r1];	 Catch:{ Exception -> 0x0166, all -> 0x0156 }
        r1 = 0;
        r5 = java.lang.String.valueOf(r15);	 Catch:{ Exception -> 0x0166, all -> 0x0156 }
        r4[r1] = r5;	 Catch:{ Exception -> 0x0166, all -> 0x0156 }
        r5 = 0;
        r6 = 0;
        r7 = "EVENT_TIME DESC ";
        r1 = r14;
        r8 = r16;
        r2 = r0.query(r1, r2, r3, r4, r5, r6, r7, r8);	 Catch:{ Exception -> 0x0166, all -> 0x0156 }
        r1 = "wang.log.query";
        r3 = "queryEvent";
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00d9, all -> 0x013e }
        r5 = "report_other ";
        r4.<init>(r5);	 Catch:{ Exception -> 0x00d9, all -> 0x013e }
        r5 = r2.getCount();	 Catch:{ Exception -> 0x00d9, all -> 0x013e }
        r4 = r4.append(r5);	 Catch:{ Exception -> 0x00d9, all -> 0x013e }
        r4 = r4.toString();	 Catch:{ Exception -> 0x00d9, all -> 0x013e }
        com.xunlei.XLStat.XLStatLog.XLStatLog.d(r1, r3, r4);	 Catch:{ Exception -> 0x00d9, all -> 0x013e }
        goto L_0x006a;
    L_0x013e:
        r1 = move-exception;
        r12 = r1;
        r1 = r0;
        r0 = r12;
    L_0x0142:
        r13.a(r2);	 Catch:{ all -> 0x0149 }
        r13.a(r1);	 Catch:{ all -> 0x0149 }
        throw r0;	 Catch:{ all -> 0x0149 }
    L_0x0149:
        r0 = move-exception;
        monitor-exit(r11);	 Catch:{ all -> 0x0149 }
        throw r0;
    L_0x014c:
        r13.a(r2);	 Catch:{ all -> 0x0149 }
        r13.a(r0);	 Catch:{ all -> 0x0149 }
        goto L_0x00e7;
    L_0x0153:
        r0 = move-exception;
        r2 = r9;
        goto L_0x0142;
    L_0x0156:
        r1 = move-exception;
        r2 = r9;
        r12 = r1;
        r1 = r0;
        r0 = r12;
        goto L_0x0142;
    L_0x015c:
        r0 = move-exception;
        r12 = r1;
        r1 = r2;
        r2 = r12;
        goto L_0x0142;
    L_0x0161:
        r0 = move-exception;
        r2 = r1;
        r1 = r9;
        goto L_0x00de;
    L_0x0166:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
        r1 = r9;
        goto L_0x00de;
        */
    }

    private ArrayList<com.xunlei.XLStat.f.b> c(String str, int i, String str2) {
        XLStatLog.d(this.a, "queryContext", new StringBuilder("table name: ").append(str).append("  strategy: ").append(i).toString());
        SQLiteDatabase sQLiteDatabase = null;
        ArrayList<com.xunlei.XLStat.f.b> arrayList = new ArrayList();
        synchronized (this.g) {
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                try {
                    String str3 = str;
                    Cursor query = writableDatabase.query(str3, null, "CONTEXT_REPORTPOLICY=?", new String[]{String.valueOf(i)}, null, null, null, str2);
                    a(query);
                    a(writableDatabase);
                } catch (Exception e) {
                    SQLiteDatabase sQLiteDatabase2 = writableDatabase;
                    Exception exception = e;
                    Cursor cursor = null;
                    exception.printStackTrace();
                    a(cursor);
                    a(sQLiteDatabase2);
                    XLStatLog.d(this.a, "queryContext", new StringBuilder("contexts list size: ").append(arrayList.size()).toString());
                    return arrayList;
                } catch (Throwable th) {
                    query = null;
                    Throwable th2 = th;
                    sQLiteDatabase = writableDatabase;
                    Throwable th3 = th2;
                    a(query);
                    a(sQLiteDatabase);
                    throw th3;
                }
                if (query != null) {
                    try {
                        if (query.getCount() > 0) {
                            while (query.moveToNext()) {
                                com.xunlei.XLStat.f.b bVar = new com.xunlei.XLStat.f.b();
                                bVar.g = query.getInt(0);
                                bVar.a = query.getInt(1);
                                bVar.b = query.getInt(SimpleLog.LOG_LEVEL_DEBUG);
                                bVar.c = query.getInt(MqttConnectOptions.MQTT_VERSION_3_1);
                                bVar.d = query.getInt(MqttConnectOptions.MQTT_VERSION_3_1_1);
                                bVar.e = query.getString(SimpleLog.LOG_LEVEL_ERROR);
                                bVar.f = query.getLong(SimpleLog.LOG_LEVEL_FATAL);
                                bVar.h = query.getInt(SimpleLog.LOG_LEVEL_OFF);
                                arrayList.add(bVar);
                            }
                        }
                    } catch (Exception e2) {
                        Exception exception2 = e2;
                        cursor = query;
                        sQLiteDatabase2 = writableDatabase;
                        exception = exception2;
                        try {
                            exception.printStackTrace();
                            a(cursor);
                            a(sQLiteDatabase2);
                        } catch (Throwable th4) {
                            th3 = th4;
                            Cursor cursor2 = cursor;
                            sQLiteDatabase = sQLiteDatabase2;
                            query = cursor2;
                            a(query);
                            a(sQLiteDatabase);
                            throw th3;
                        }
                        XLStatLog.d(this.a, "queryContext", new StringBuilder("contexts list size: ").append(arrayList.size()).toString());
                        return arrayList;
                    } catch (Throwable th5) {
                        th2 = th5;
                        sQLiteDatabase = writableDatabase;
                        th3 = th2;
                        a(query);
                        a(sQLiteDatabase);
                        throw th3;
                    }
                }
            } catch (Exception e3) {
                exception = e3;
                sQLiteDatabase2 = null;
                cursor = null;
                exception.printStackTrace();
                a(cursor);
                a(sQLiteDatabase2);
                XLStatLog.d(this.a, "queryContext", new StringBuilder("contexts list size: ").append(arrayList.size()).toString());
                return arrayList;
            } catch (Throwable th6) {
                th3 = th6;
                query = null;
                a(query);
                a(sQLiteDatabase);
                throw th3;
            }
        }
        XLStatLog.d(this.a, "queryContext", new StringBuilder("contexts list size: ").append(arrayList.size()).toString());
        return arrayList;
    }

    private ArrayList<f> d(String str, int i, String str2) {
        XLStatLog.d(this.a, "queryHeartbeat", new StringBuilder("table name: ").append(str).append(" strategy: ").append(i).toString());
        SQLiteDatabase sQLiteDatabase = null;
        ArrayList<f> arrayList = new ArrayList();
        synchronized (this.g) {
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                try {
                    String str3 = str;
                    Cursor query = writableDatabase.query(str3, null, "REPORT_POLICY=?", new String[]{String.valueOf(i)}, null, null, null, str2);
                    a(query);
                    a(writableDatabase);
                } catch (Exception e) {
                    SQLiteDatabase sQLiteDatabase2 = writableDatabase;
                    Exception exception = e;
                    Cursor cursor = null;
                    exception.printStackTrace();
                    a(cursor);
                    a(sQLiteDatabase2);
                    XLStatLog.d(this.a, "queryHeartbeat", new StringBuilder("heartbeats list size: ").append(arrayList.size()).toString());
                    return arrayList;
                } catch (Throwable th) {
                    query = null;
                    Throwable th2 = th;
                    sQLiteDatabase = writableDatabase;
                    Throwable th3 = th2;
                    a(query);
                    a(sQLiteDatabase);
                    throw th3;
                }
                if (query != null) {
                    try {
                        if (query.getCount() > 0) {
                            while (query.moveToNext()) {
                                f fVar = new f();
                                fVar.g = query.getInt(0);
                                fVar.a = query.getInt(1);
                                fVar.b = query.getInt(SimpleLog.LOG_LEVEL_DEBUG);
                                fVar.c = query.getInt(MqttConnectOptions.MQTT_VERSION_3_1);
                                fVar.d = query.getInt(MqttConnectOptions.MQTT_VERSION_3_1_1);
                                fVar.e = query.getString(SimpleLog.LOG_LEVEL_ERROR);
                                fVar.f = query.getLong(SimpleLog.LOG_LEVEL_FATAL);
                                fVar.h = query.getInt(SimpleLog.LOG_LEVEL_OFF);
                                arrayList.add(fVar);
                            }
                        }
                    } catch (Exception e2) {
                        Exception exception2 = e2;
                        cursor = query;
                        sQLiteDatabase2 = writableDatabase;
                        exception = exception2;
                        try {
                            exception.printStackTrace();
                            a(cursor);
                            a(sQLiteDatabase2);
                        } catch (Throwable th4) {
                            th3 = th4;
                            Cursor cursor2 = cursor;
                            sQLiteDatabase = sQLiteDatabase2;
                            query = cursor2;
                            a(query);
                            a(sQLiteDatabase);
                            throw th3;
                        }
                        XLStatLog.d(this.a, "queryHeartbeat", new StringBuilder("heartbeats list size: ").append(arrayList.size()).toString());
                        return arrayList;
                    } catch (Throwable th5) {
                        th2 = th5;
                        sQLiteDatabase = writableDatabase;
                        th3 = th2;
                        a(query);
                        a(sQLiteDatabase);
                        throw th3;
                    }
                }
            } catch (Exception e3) {
                exception = e3;
                sQLiteDatabase2 = null;
                cursor = null;
                exception.printStackTrace();
                a(cursor);
                a(sQLiteDatabase2);
                XLStatLog.d(this.a, "queryHeartbeat", new StringBuilder("heartbeats list size: ").append(arrayList.size()).toString());
                return arrayList;
            } catch (Throwable th6) {
                th3 = th6;
                query = null;
                a(query);
                a(sQLiteDatabase);
                throw th3;
            }
        }
        XLStatLog.d(this.a, "queryHeartbeat", new StringBuilder("heartbeats list size: ").append(arrayList.size()).toString());
        return arrayList;
    }

    public ArrayList<g> e(String str) {
        SQLiteDatabase sQLiteDatabase;
        Cursor cursor;
        Cursor cursor2 = null;
        ArrayList<g> arrayList = new ArrayList();
        Object obj = this.g;
        synchronized (obj) {
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                try {
                    Cursor query = writableDatabase.query(e, new String[]{"SessionDataId", "sessionId", "sessionStartTime", "activityName", "activityDuration", "activityEndTime", "sended", "extData"}, "sended=0", null, null, null, "time DESC", str);
                    while (query.moveToNext()) {
                        try {
                            g gVar = new g();
                            gVar.a = query.getInt(0);
                            gVar.b = query.getInt(1);
                            gVar.c = query.getLong(SimpleLog.LOG_LEVEL_DEBUG);
                            gVar.d = query.getString(MqttConnectOptions.MQTT_VERSION_3_1);
                            gVar.e = query.getShort(MqttConnectOptions.MQTT_VERSION_3_1_1);
                            gVar.f = query.getLong(SimpleLog.LOG_LEVEL_ERROR);
                            gVar.h = query.getInt(SimpleLog.LOG_LEVEL_FATAL);
                            gVar.g = query.getString(SimpleLog.LOG_LEVEL_OFF);
                            arrayList.add(gVar);
                        } catch (Exception e) {
                            sQLiteDatabase = writableDatabase;
                            r0 = e;
                            cursor = query;
                        } catch (Throwable th) {
                            cursor2 = query;
                            Throwable th2 = th;
                            r1 = writableDatabase;
                            r0 = th2;
                        }
                    }
                    if (query != null) {
                        query.close();
                    }
                    a(writableDatabase);
                } catch (Exception e2) {
                    Exception exception = e2;
                    cursor = null;
                    sQLiteDatabase = writableDatabase;
                    r0 = exception;
                    r0.printStackTrace();
                    if (cursor != null) {
                        cursor.close();
                    }
                    a(sQLiteDatabase);
                    return arrayList;
                } catch (Throwable th3) {
                    th2 = th3;
                    r1 = writableDatabase;
                    r0 = th2;
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    a(r1);
                    throw r0;
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
                    a(sQLiteDatabase);
                    return arrayList;
                } catch (Throwable th4) {
                    SQLiteDatabase sQLiteDatabase2;
                    Throwable th5;
                    th5 = th4;
                    Cursor cursor3 = cursor;
                    sQLiteDatabase2 = sQLiteDatabase;
                    cursor2 = cursor3;
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    a(sQLiteDatabase2);
                    throw th5;
                }
            } catch (Throwable th6) {
                th5 = th6;
                sQLiteDatabase2 = null;
                if (cursor2 != null) {
                    cursor2.close();
                }
                a(sQLiteDatabase2);
                throw th5;
            }
        }
        return arrayList;
    }

    public int a(String str, String str2, String[] strArr) {
        int delete;
        XLStatLog.d(this.a, "delete", new StringBuilder("table name: ").append(str).toString());
        SQLiteDatabase sQLiteDatabase = null;
        synchronized (this.g) {
            try {
                sQLiteDatabase = getWritableDatabase();
                delete = sQLiteDatabase.delete(str, str2, strArr);
                a(sQLiteDatabase);
            } catch (Exception e) {
                e.printStackTrace();
                a(sQLiteDatabase);
                return -1;
            }
        }
        return delete;
    }

    private void a(SQLiteDatabase sQLiteDatabase) {
        if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
            sQLiteDatabase.close();
        }
        XLStatLog.d(this.a, "closeDatabase", "close db sucess");
    }

    private void a(Cursor cursor) {
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
    }

    public static String a() {
        return "xlstat.db";
    }

    public static String b() {
        return b;
    }

    public static String c() {
        return c;
    }

    public static String d() {
        return d;
    }

    public static String e() {
        return e;
    }
}
