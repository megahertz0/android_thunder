package com.android.providers.downloads;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.IBinder;
import android.text.TextUtils;
import com.xunlei.download.DownloadManager;
import com.xunlei.download.DownloadManager.TaskType;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.download.proguard.ab;
import com.xunlei.download.proguard.am;
import com.xunlei.download.proguard.d;
import com.xunlei.download.proguard.e;
import com.xunlei.download.proguard.g;
import com.xunlei.download.proguard.h;
import com.xunlei.download.proguard.i;
import com.xunlei.download.proguard.o;
import com.xunlei.download.proguard.q;
import com.xunlei.download.proguard.s;
import com.xunlei.download.proguard.u;
import com.xunlei.download.proguard.z;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.File;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DownloadService extends Service {
    private static final boolean b = true;
    private static boolean g = false;
    private static final int o = 1;
    private static final int p = 2;
    u a;
    private AlarmManager c;
    private s d;
    private a e;
    private e f;
    @SuppressLint({"UseSparseArrays"})
    private final Map<Long, d> h;
    private ExecutorService i;
    private g j;
    private HandlerThread k;
    private Handler l;
    private boolean m;
    private volatile int n;
    private Callback q;

    public class a extends ContentObserver {
        public a() {
            super(new Handler());
        }

        public void onChange(boolean z) {
            DownloadService.this.a();
        }
    }

    public DownloadService() {
        this.h = new HashMap();
        this.m = false;
        this.q = new i(this);
    }

    static {
        g = false;
    }

    private static ExecutorService a(Context context) {
        int a = ab.a(context, DownloadManager.KEY_MAX_CONCURRENT_DOWNLOADS, (int) XZBDevice.Stop);
        return new ThreadPoolExecutor(a, a, 10, TimeUnit.SECONDS, new LinkedBlockingQueue());
    }

    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Cannot bind to Download Manager Service");
    }

    public void onCreate() {
        super.onCreate();
        if (g) {
            am.e(DownloadManager.LOG_TAG, "Service Has Created");
            return;
        }
        g = true;
        am.e(DownloadManager.LOG_TAG, "Service onCreate");
        if (this.a == null) {
            this.a = new q(this);
        }
        this.i = a((Context) this);
        this.c = (AlarmManager) getSystemService(NotificationCompatApi21.CATEGORY_ALARM);
        this.d = new s(this);
        this.k = new HandlerThread("DownloadManager-UpdateThread");
        this.k.start();
        this.l = new Handler(this.k.getLooper(), this.q);
        this.j = new g(this);
        this.f = new e(this);
        this.f.a();
        this.e = new a();
        try {
            getContentResolver().registerContentObserver(DownloadManager.getInstanceFor(this).getDownloadUri(), b, this.e);
        } catch (Throwable e) {
            e.printStackTrace();
            am.a(e);
        }
        XlTaskHelper.a().a((Context) this);
        new h(this).start();
        this.m = ab.a((Context) this, "com.xunlei.download.SERVICE_IDLE_EXIT", this.m);
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        int onStartCommand = super.onStartCommand(intent, i, i2);
        if (this.l == null) {
            return p;
        }
        am.e(DownloadManager.LOG_TAG, new StringBuilder("Service onStart startId = ").append(i2).toString());
        this.n = i2;
        a();
        return ab.a((Context) this, "com.xunlei.download.SERVICE_START_COMMAND", onStartCommand);
    }

    public void onDestroy() {
        g = false;
        if (this.l == null) {
            super.onDestroy();
            return;
        }
        try {
            getContentResolver().unregisterContentObserver(this.e);
        } catch (Throwable e) {
            e.printStackTrace();
            am.a(e);
        }
        this.j.b();
        this.k.quit();
        am.e(DownloadManager.LOG_TAG, "Service onDestroy");
        XlTaskHelper.a().d(this);
        XlTaskHelper.a().b((Context) this);
        super.onDestroy();
    }

    private void a() {
        this.l.removeMessages(o);
        this.l.obtainMessage(o, this.n, -1).sendToTarget();
    }

    private void b() {
        this.l.removeMessages(p);
        this.l.sendMessageDelayed(this.l.obtainMessage(p, this.n, -1), 300000);
    }

    private boolean c() {
        Throwable e;
        long a = this.a.a();
        Set<Long> hashSet = new HashSet(this.h.keySet());
        ContentResolver contentResolver = getContentResolver();
        try {
            Cursor query = contentResolver.query(Uri.parse(new StringBuilder("content://").append(DownloadProvider.e).append("/all_downloads").toString()), null, null, null, null);
            try {
                d.a();
                d.d dVar = new d.d(contentResolver, query);
                int columnIndexOrThrow = query.getColumnIndexOrThrow(DownloadManager.COLUMN_ID);
                int i = 0;
                long j = Long.MAX_VALUE;
                while (query.moveToNext()) {
                    d dVar2;
                    long j2 = query.getLong(columnIndexOrThrow);
                    hashSet.remove(Long.valueOf(j2));
                    d dVar3 = (d) this.h.get(Long.valueOf(j2));
                    if (dVar3 != null) {
                        a(dVar, dVar3, a);
                        dVar2 = dVar3;
                    } else {
                        dVar2 = a(dVar, a);
                    }
                    if (!dVar2.A) {
                        boolean a2;
                        if (dVar2.ab == 0) {
                            a2 = dVar2.a(this.i);
                        } else {
                            dVar3 = (d) this.h.get(Long.valueOf(dVar2.ab));
                            if (dVar3 != null) {
                                dVar2.D = dVar3.D;
                                a2 = (dVar3.d() && dVar3.b(dVar2.c) && dVar2.a(this.i)) ? b : false;
                            } else {
                                a2 = false;
                            }
                        }
                        boolean a3 = dVar2.a(this.j);
                        if (a2 || a3) {
                            am.e(DownloadManager.LOG_TAG, new StringBuilder("Download ").append(dVar2.c).append(": activeDownload=").append(a2).append(", activeScan=").append(a3).toString());
                        }
                        i = (a2 | r4) | a3;
                    } else if (dVar2.f()) {
                        i = o;
                        a();
                    } else {
                        if (!TextUtils.isEmpty(dVar2.B)) {
                            contentResolver.delete(Uri.parse(dVar2.B), null, null);
                        }
                        if (dVar2.U != TaskType.GROUP && dVar2.U != TaskType.BT) {
                            a(dVar2.g);
                        } else if (!TextUtils.isEmpty(dVar2.g)) {
                            ArrayList a4 = a(dVar2.U.ordinal(), j2);
                            if (a4 != null) {
                                Iterator it = a4.iterator();
                                while (it.hasNext()) {
                                    a((String) it.next());
                                }
                            }
                            if (dVar2.U == TaskType.BT && !TextUtils.isEmpty(dVar2.x)) {
                                new File(dVar2.g, new StringBuilder(".").append(dVar2.x).toString()).delete();
                                new File(z.a(dVar2.g, dVar2.x)).delete();
                            }
                            a(new File(dVar2.g));
                        }
                        am.b(DownloadManager.LOG_TAG, new StringBuilder("delete id: ").append(j2).toString());
                        contentResolver.delete(dVar2.j(), null, null);
                    }
                    j = Math.min(dVar2.d(a), j);
                }
                if (query != null) {
                    query.close();
                }
                for (Long l : hashSet) {
                    a(l.longValue());
                }
                this.f.a(this.h.values());
                if (j > 0 && j < Long.MAX_VALUE) {
                    am.e(DownloadManager.LOG_TAG, new StringBuilder("scheduling start in ").append(j).append("ms").toString());
                    Intent intent = new Intent("android.intent.action.DOWNLOAD_WAKEUP");
                    intent.setClass(this, DownloadReceiver.class);
                    this.c.set(0, a + j, PendingIntent.getBroadcast(this, 0, intent, 1073741824));
                }
                return r4;
            } catch (Exception e2) {
                e = e2;
                r3 = query;
            } catch (Throwable th) {
                e = th;
            }
        } catch (Exception e3) {
            e = e3;
            r3 = null;
            try {
                e.printStackTrace();
                am.a(e);
                Cursor cursor;
                if (cursor != null) {
                    cursor.close();
                }
                return b;
            } catch (Throwable th2) {
                e = th2;
                query = cursor;
                if (query != null) {
                    query.close();
                }
                throw e;
            }
        } catch (Throwable th3) {
            e = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw e;
        }
    }

    private d a(d.d dVar, long j) {
        d a = dVar.a(this, this.a, this.d, this.f);
        this.h.put(Long.valueOf(a.c), a);
        return a;
    }

    private void a(d.d dVar, d dVar2, long j) {
        dVar.a(dVar2);
    }

    private void a(long j) {
        d dVar = (d) this.h.get(Long.valueOf(j));
        if (dVar.l == 192) {
            dVar.l = 490;
        }
        this.h.remove(Long.valueOf(dVar.c));
    }

    private void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            am.b(DownloadManager.LOG_TAG, new StringBuilder("deleteFileIfExists() deleting ").append(str).toString());
            File file = new File(str);
            if (file.exists() && !file.delete()) {
                am.c(DownloadManager.LOG_TAG, new StringBuilder("file: '").append(str).append("' couldn't be deleted").toString());
            }
            file = new File(str + ".cfg");
            if (file.exists()) {
                file.delete();
            }
        }
    }

    private void a(File file) {
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    a(file2);
                }
            }
            file.delete();
        }
    }

    private ArrayList<String> a(int i, long j) {
        Throwable e;
        Cursor cursor = null;
        String[] strArr = new String[]{Impl._DATA};
        try {
            Cursor query;
            if (i == TaskType.GROUP.ordinal()) {
                query = getContentResolver().query(DownloadManager.getInstanceFor(this).getDownloadUri(), strArr, "group_id = ?", new String[]{String.valueOf(j)}, null);
            } else if (i == TaskType.BT.ordinal()) {
                query = getContentResolver().query(DownloadProvider.c, strArr, "bt_parent_id = ?", new String[]{String.valueOf(j)}, null);
            } else {
                if (cursor != null) {
                    cursor.close();
                }
                return cursor;
            }
            try {
                ArrayList<String> arrayList = new ArrayList();
                query.moveToFirst();
                while (!query.isAfterLast()) {
                    CharSequence string = query.getString(0);
                    if (!TextUtils.isEmpty(string)) {
                        arrayList.add(string);
                    }
                    query.moveToNext();
                }
                if (query == null) {
                    return arrayList;
                }
                query.close();
                return arrayList;
            } catch (Exception e2) {
                e = e2;
                try {
                    e.printStackTrace();
                    am.a(e);
                    if (query != null) {
                        query.close();
                    }
                    return cursor;
                } catch (Throwable th) {
                    e = th;
                    cursor = query;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw e;
                }
            }
        } catch (Exception e3) {
            e = e3;
            query = cursor;
            e.printStackTrace();
            am.a(e);
            if (query != null) {
                query.close();
            }
            return cursor;
        } catch (Throwable th2) {
            e = th2;
            if (cursor != null) {
                cursor.close();
            }
            throw e;
        }
    }

    protected void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        o oVar = new o(printWriter, "  ");
        synchronized (this.h) {
            List<Long> arrayList = new ArrayList(this.h.keySet());
            Collections.sort(arrayList);
            for (Long l : arrayList) {
                ((d) this.h.get(l)).a(oVar);
            }
        }
    }
}
