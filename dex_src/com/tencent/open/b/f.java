package com.tencent.open.b;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import com.tencent.open.utils.Global;

// compiled from: ProGuard
public class f extends SQLiteOpenHelper {
    protected static final String[] a;
    protected static f b;

    static {
        a = new String[]{"key"};
    }

    public static synchronized f a() {
        f fVar;
        synchronized (f.class) {
            if (b == null) {
                b = new f(Global.getContext());
            }
            fVar = b;
        }
        return fVar;
    }

    public f(Context context) {
        super(context, "sdk_report.db", null, 2);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS via_cgi_report( _id INTEGER PRIMARY KEY,key TEXT,type TEXT,blob BLOB);");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS via_cgi_report");
        onCreate(sQLiteDatabase);
    }

    public synchronized java.util.List<java.io.Serializable> a(java.lang.String r11) {
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.open.b.f.a(java.lang.String):java.util.List<java.io.Serializable>");
        /* JADX: method processing error */
/*
        Error: jadx.core.utils.exceptions.JadxRuntimeException: Try/catch wrap count limit reached in com.tencent.open.b.f.a(java.lang.String):java.util.List<java.io.Serializable>
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:54)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:40)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:16)
	at jadx.core.ProcessClass.process(ProcessClass.java:22)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:209)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:133)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(Unknown Source)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(Unknown Source)
	at java.lang.Thread.run(Unknown Source)
*/
        /*
        this = this;
        r9 = 0;
        monitor-enter(r10);
        r0 = new java.util.ArrayList;	 Catch:{ all -> 0x00a3 }
        r0.<init>();	 Catch:{ all -> 0x00a3 }
        r8 = java.util.Collections.synchronizedList(r0);	 Catch:{ all -> 0x00a3 }
        r0 = android.text.TextUtils.isEmpty(r11);	 Catch:{ all -> 0x00a3 }
        if (r0 == 0) goto L_0x0014;
    L_0x0011:
        r0 = r8;
    L_0x0012:
        monitor-exit(r10);
        return r0;
    L_0x0014:
        r0 = r10.getReadableDatabase();	 Catch:{ all -> 0x00a3 }
        if (r0 != 0) goto L_0x001c;
    L_0x001a:
        r0 = r8;
        goto L_0x0012;
    L_0x001c:
        r1 = "via_cgi_report";
        r2 = 0;
        r3 = "type = ?";
        r4 = 1;
        r4 = new java.lang.String[r4];	 Catch:{ Exception -> 0x00c2, all -> 0x00a6 }
        r5 = 0;
        r4[r5] = r11;	 Catch:{ Exception -> 0x00c2, all -> 0x00a6 }
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r3 = r0.query(r1, r2, r3, r4, r5, r6, r7);	 Catch:{ Exception -> 0x00c2, all -> 0x00a6 }
        if (r3 == 0) goto L_0x0067;
    L_0x0032:
        r1 = r3.getCount();	 Catch:{ Exception -> 0x008d, all -> 0x00bd }
        if (r1 <= 0) goto L_0x0067;
    L_0x0038:
        r3.moveToFirst();	 Catch:{ Exception -> 0x008d, all -> 0x00bd }
    L_0x003b:
        r1 = "blob";
        r1 = r3.getColumnIndex(r1);	 Catch:{ Exception -> 0x008d, all -> 0x00bd }
        r1 = r3.getBlob(r1);	 Catch:{ Exception -> 0x008d, all -> 0x00bd }
        r4 = new java.io.ByteArrayInputStream;	 Catch:{ Exception -> 0x008d, all -> 0x00bd }
        r4.<init>(r1);	 Catch:{ Exception -> 0x008d, all -> 0x00bd }
        r2 = new java.io.ObjectInputStream;	 Catch:{ Exception -> 0x0073, all -> 0x0082 }
        r2.<init>(r4);	 Catch:{ Exception -> 0x0073, all -> 0x0082 }
        r1 = r2.readObject();	 Catch:{ Exception -> 0x00c6, all -> 0x00c4 }
        r1 = (java.io.Serializable) r1;	 Catch:{ Exception -> 0x00c6, all -> 0x00c4 }
        r2.close();	 Catch:{ IOException -> 0x00b3 }
    L_0x0059:
        r4.close();	 Catch:{ IOException -> 0x00b5 }
    L_0x005c:
        if (r1 == 0) goto L_0x0061;
    L_0x005e:
        r8.add(r1);	 Catch:{ Exception -> 0x008d, all -> 0x00bd }
    L_0x0061:
        r1 = r3.moveToNext();	 Catch:{ Exception -> 0x008d, all -> 0x00bd }
        if (r1 != 0) goto L_0x003b;
    L_0x0067:
        if (r3 == 0) goto L_0x006c;
    L_0x0069:
        r3.close();	 Catch:{ all -> 0x00a3 }
    L_0x006c:
        if (r0 == 0) goto L_0x0071;
    L_0x006e:
        r0.close();	 Catch:{ all -> 0x00a3 }
    L_0x0071:
        r0 = r8;
        goto L_0x0012;
    L_0x0073:
        r1 = move-exception;
        r1 = r9;
    L_0x0075:
        if (r1 == 0) goto L_0x007a;
    L_0x0077:
        r1.close();	 Catch:{ IOException -> 0x00b7 }
    L_0x007a:
        r4.close();	 Catch:{ IOException -> 0x007f }
        r1 = r9;
        goto L_0x005c;
    L_0x007f:
        r1 = move-exception;
        r1 = r9;
        goto L_0x005c;
    L_0x0082:
        r1 = move-exception;
        r2 = r9;
    L_0x0084:
        if (r2 == 0) goto L_0x0089;
    L_0x0086:
        r2.close();	 Catch:{ IOException -> 0x00b9 }
    L_0x0089:
        r4.close();	 Catch:{ IOException -> 0x00bb }
    L_0x008c:
        throw r1;	 Catch:{ Exception -> 0x008d, all -> 0x00bd }
    L_0x008d:
        r1 = move-exception;
        r9 = r3;
    L_0x008f:
        r2 = "openSDK_LOG.ReportDatabaseHelper";
        r3 = "getReportItemFromDB has exception.";
        com.tencent.open.a.f.b(r2, r3, r1);	 Catch:{ all -> 0x00bf }
        if (r9 == 0) goto L_0x009d;
    L_0x009a:
        r9.close();	 Catch:{ all -> 0x00a3 }
    L_0x009d:
        if (r0 == 0) goto L_0x0071;
    L_0x009f:
        r0.close();	 Catch:{ all -> 0x00a3 }
        goto L_0x0071;
    L_0x00a3:
        r0 = move-exception;
        monitor-exit(r10);
        throw r0;
    L_0x00a6:
        r1 = move-exception;
        r3 = r9;
    L_0x00a8:
        if (r3 == 0) goto L_0x00ad;
    L_0x00aa:
        r3.close();	 Catch:{ all -> 0x00a3 }
    L_0x00ad:
        if (r0 == 0) goto L_0x00b2;
    L_0x00af:
        r0.close();	 Catch:{ all -> 0x00a3 }
    L_0x00b2:
        throw r1;	 Catch:{ all -> 0x00a3 }
    L_0x00b3:
        r2 = move-exception;
        goto L_0x0059;
    L_0x00b5:
        r2 = move-exception;
        goto L_0x005c;
    L_0x00b7:
        r1 = move-exception;
        goto L_0x007a;
    L_0x00b9:
        r2 = move-exception;
        goto L_0x0089;
    L_0x00bb:
        r2 = move-exception;
        goto L_0x008c;
    L_0x00bd:
        r1 = move-exception;
        goto L_0x00a8;
    L_0x00bf:
        r1 = move-exception;
        r3 = r9;
        goto L_0x00a8;
    L_0x00c2:
        r1 = move-exception;
        goto L_0x008f;
    L_0x00c4:
        r1 = move-exception;
        goto L_0x0084;
    L_0x00c6:
        r1 = move-exception;
        r1 = r2;
        goto L_0x0075;
        */
    }

    public synchronized void a(java.lang.String r9, java.util.List<java.io.Serializable> r10) {
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.open.b.f.a(java.lang.String, java.util.List):void");
        /* JADX: method processing error */
/*
        Error: jadx.core.utils.exceptions.JadxRuntimeException: Try/catch wrap count limit reached in com.tencent.open.b.f.a(java.lang.String, java.util.List):void
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:54)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:40)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:16)
	at jadx.core.ProcessClass.process(ProcessClass.java:22)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:209)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:133)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(Unknown Source)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(Unknown Source)
	at java.lang.Thread.run(Unknown Source)
*/
        /*
        this = this;
        r2 = 0;
        r1 = 20;
        monitor-enter(r8);
        r0 = r10.size();	 Catch:{ all -> 0x0092 }
        if (r0 != 0) goto L_0x000c;
    L_0x000a:
        monitor-exit(r8);
        return;
    L_0x000c:
        if (r0 > r1) goto L_0x0065;
    L_0x000e:
        r4 = r0;
    L_0x000f:
        r0 = android.text.TextUtils.isEmpty(r9);	 Catch:{ all -> 0x0092 }
        if (r0 != 0) goto L_0x000a;
    L_0x0015:
        r8.b(r9);	 Catch:{ all -> 0x0092 }
        r5 = r8.getWritableDatabase();	 Catch:{ all -> 0x0092 }
        if (r5 == 0) goto L_0x000a;
    L_0x001e:
        r5.beginTransaction();	 Catch:{ all -> 0x0092 }
        r6 = new android.content.ContentValues;	 Catch:{ Exception -> 0x007e }
        r6.<init>();	 Catch:{ Exception -> 0x007e }
        r0 = 0;
        r3 = r0;
    L_0x0028:
        if (r3 >= r4) goto L_0x0095;
    L_0x002a:
        r0 = r10.get(r3);	 Catch:{ Exception -> 0x007e }
        r0 = (java.io.Serializable) r0;	 Catch:{ Exception -> 0x007e }
        if (r0 == 0) goto L_0x005e;
    L_0x0032:
        r1 = "type";
        r6.put(r1, r9);	 Catch:{ Exception -> 0x007e }
        r7 = new java.io.ByteArrayOutputStream;	 Catch:{ Exception -> 0x007e }
        r1 = 512; // 0x200 float:7.175E-43 double:2.53E-321;
        r7.<init>(r1);	 Catch:{ Exception -> 0x007e }
        r1 = new java.io.ObjectOutputStream;	 Catch:{ IOException -> 0x0067, all -> 0x0074 }
        r1.<init>(r7);	 Catch:{ IOException -> 0x0067, all -> 0x0074 }
        r1.writeObject(r0);	 Catch:{ IOException -> 0x00b9, all -> 0x00b6 }
        r1.close();	 Catch:{ IOException -> 0x00ac }
    L_0x004a:
        r7.close();	 Catch:{ IOException -> 0x00ae }
    L_0x004d:
        r0 = "blob";
        r1 = r7.toByteArray();	 Catch:{ Exception -> 0x007e }
        r6.put(r0, r1);	 Catch:{ Exception -> 0x007e }
        r0 = "via_cgi_report";
        r1 = 0;
        r5.insert(r0, r1, r6);	 Catch:{ Exception -> 0x007e }
    L_0x005e:
        r6.clear();	 Catch:{ Exception -> 0x007e }
        r0 = r3 + 1;
        r3 = r0;
        goto L_0x0028;
    L_0x0065:
        r4 = r1;
        goto L_0x000f;
    L_0x0067:
        r0 = move-exception;
        r0 = r2;
    L_0x0069:
        if (r0 == 0) goto L_0x006e;
    L_0x006b:
        r0.close();	 Catch:{ IOException -> 0x00b0 }
    L_0x006e:
        r7.close();	 Catch:{ IOException -> 0x0072 }
        goto L_0x004d;
    L_0x0072:
        r0 = move-exception;
        goto L_0x004d;
    L_0x0074:
        r0 = move-exception;
    L_0x0075:
        if (r2 == 0) goto L_0x007a;
    L_0x0077:
        r2.close();	 Catch:{ IOException -> 0x00b2 }
    L_0x007a:
        r7.close();	 Catch:{ IOException -> 0x00b4 }
    L_0x007d:
        throw r0;	 Catch:{ Exception -> 0x007e }
    L_0x007e:
        r0 = move-exception;
        r0 = "openSDK_LOG.ReportDatabaseHelper";
        r1 = "saveReportItemToDB has exception.";
        com.tencent.open.a.f.e(r0, r1);	 Catch:{ all -> 0x00a2 }
        r5.endTransaction();	 Catch:{ all -> 0x0092 }
        if (r5 == 0) goto L_0x000a;
    L_0x008d:
        r5.close();	 Catch:{ all -> 0x0092 }
        goto L_0x000a;
    L_0x0092:
        r0 = move-exception;
        monitor-exit(r8);
        throw r0;
    L_0x0095:
        r5.setTransactionSuccessful();	 Catch:{ Exception -> 0x007e }
        r5.endTransaction();	 Catch:{ all -> 0x0092 }
        if (r5 == 0) goto L_0x000a;
    L_0x009d:
        r5.close();	 Catch:{ all -> 0x0092 }
        goto L_0x000a;
    L_0x00a2:
        r0 = move-exception;
        r5.endTransaction();	 Catch:{ all -> 0x0092 }
        if (r5 == 0) goto L_0x00ab;
    L_0x00a8:
        r5.close();	 Catch:{ all -> 0x0092 }
    L_0x00ab:
        throw r0;	 Catch:{ all -> 0x0092 }
    L_0x00ac:
        r0 = move-exception;
        goto L_0x004a;
    L_0x00ae:
        r0 = move-exception;
        goto L_0x004d;
    L_0x00b0:
        r0 = move-exception;
        goto L_0x006e;
    L_0x00b2:
        r1 = move-exception;
        goto L_0x007a;
    L_0x00b4:
        r1 = move-exception;
        goto L_0x007d;
    L_0x00b6:
        r0 = move-exception;
        r2 = r1;
        goto L_0x0075;
    L_0x00b9:
        r0 = move-exception;
        r0 = r1;
        goto L_0x0069;
        */
    }

    public synchronized void b(String str) {
        SQLiteDatabase writableDatabase;
        try {
            if (!TextUtils.isEmpty(str)) {
                writableDatabase = getWritableDatabase();
                if (writableDatabase != null) {
                    try {
                        writableDatabase.delete("via_cgi_report", "type = ?", new String[]{str});
                        if (writableDatabase != null) {
                            writableDatabase.close();
                        }
                    } catch (Throwable e) {
                        com.tencent.open.a.f.b("openSDK_LOG.ReportDatabaseHelper", "clearReportItem has exception.", e);
                        if (writableDatabase != null) {
                            writableDatabase.close();
                        }
                    }
                }
            }
        } catch (Throwable th) {
        }
    }
}
