package com.xunlei.downloadprovider.service;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.download.create.StorageTipActivity;
import com.xunlei.downloadprovider.service.downloads.TaskInfo;
import com.xunlei.downloadprovider.service.downloads.task.a.g;
import com.xunlei.downloadprovider.service.downloads.task.a.n;
import com.xunlei.downloadprovider.service.downloads.task.c;
import com.xunlei.downloadprovider.service.downloads.task.d;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.sdk.IHost;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.android.spdy.SpdyAgent;

@SuppressLint({"HandlerLeak", "UseSparseArrays"})
public class DownloadEngine extends g {
    private static long q;
    private static long r;
    public List<Handler> a;
    public Handler b;
    boolean c;
    public long d;
    public long e;
    private boolean s;
    private List<Handler> t;
    private List<Handler> u;
    private long v;
    private Runnable w;

    public class a implements Runnable {
        public final void run() {
            try {
                DownloadEngine.g(DownloadEngine.this);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (DownloadEngine.this.w != null) {
                DownloadEngine.this.j.removeCallbacks(DownloadEngine.this.w);
                DownloadEngine.this.j.postDelayed(this, 1500);
            }
        }
    }

    private class b extends com.xunlei.downloadprovider.service.downloads.task.a.g.a {
        public b() {
            super();
        }

        public final void a(Message message) {
            switch (message.what) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    DownloadEngine.this.a(null, false);
                case 123:
                    DownloadEngine.f(DownloadEngine.this);
                case 137:
                    DownloadEngine.this.a((c) message.obj);
                case 138:
                    g gVar = DownloadEngine.this;
                    n nVar = (n) message.obj;
                    if (nVar != null) {
                        Collection collection;
                        switch (DownloadEngine.this) {
                            case SpdyAgent.ACCS_ONLINE_SERVER:
                                collection = nVar.b;
                                boolean z = nVar.e;
                                Handler handler = nVar.c;
                                if (collection != null && collection.size() > 0) {
                                    gVar.f.a(z, collection);
                                }
                                if (handler != null) {
                                    handler.obtainMessage(R.styleable.AppCompatTheme_buttonBarNegativeButtonStyle).sendToTarget();
                                }
                            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                                boolean z2 = nVar.e;
                                if (DownloadEngine.this != null && !DownloadEngine.this.isEmpty()) {
                                    Collection arrayList = new ArrayList();
                                    for (TaskInfo taskInfo : DownloadEngine.this.values()) {
                                        if (4 == taskInfo.mTaskStatus || 16 == taskInfo.mTaskStatus) {
                                            arrayList.add(Long.valueOf(taskInfo.mTaskId));
                                        }
                                    }
                                    if (arrayList.size() > 0) {
                                        gVar.f.a(z2, arrayList);
                                    }
                                }
                            case XZBDevice.DOWNLOAD_LIST_FAILED:
                                gVar.a(nVar.b, nVar.e, nVar.c);
                            case XZBDevice.DOWNLOAD_LIST_ALL:
                                collection = nVar.b;
                                Handler handler2 = nVar.c;
                                boolean z3 = nVar.f;
                                if (collection != null && !collection.isEmpty()) {
                                    gVar.f.a(collection, z3);
                                    if (handler2 != null) {
                                        handler2.obtainMessage(R.styleable.AppCompatTheme_buttonBarNeutralButtonStyle).sendToTarget();
                                    }
                                }
                            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                                gVar.b(nVar.c, nVar.f);
                            case R.styleable.Toolbar_contentInsetEnd:
                                gVar.a(nVar.b, nVar.c, nVar.d);
                            case R.styleable.Toolbar_contentInsetLeft:
                                gVar.a(nVar.g, nVar.c, nVar.d);
                            default:
                                break;
                        }
                    }
                case 139:
                    DownloadEngine.e(DownloadEngine.this);
                case IHost.HOST_NOFITY_REFRESH_LIST:
                    DownloadEngine.a(DownloadEngine.this, (Handler) message.obj, message.arg1);
                case IHost.HOST_NOFITY_PAGE_SELECTED:
                    DownloadEngine.d(DownloadEngine.this);
                    if (message.obj != null) {
                        ((Handler) message.obj).obtainMessage(message.arg1).sendToTarget();
                    }
                default:
                    break;
            }
        }
    }

    static /* synthetic */ void g(DownloadEngine downloadEngine) {
        int b = com.xunlei.xllib.a.b.b(downloadEngine.g);
        BrothersApplication a = BrothersApplication.a();
        boolean e = BrothersApplication.e();
        if (b == 0 && !e && downloadEngine.a() > 0) {
            BrothersApplication.a(true);
            a.f();
        }
        if (b == 1) {
            BrothersApplication.a(false);
        }
    }

    static {
        q = 0;
        r = 0;
    }

    DownloadEngine(DownloadService downloadService) {
        super(downloadService);
        this.s = false;
        this.v = 0;
        this.c = false;
        this.d = 0;
        this.e = -1;
        this.t = new ArrayList();
        this.u = new ArrayList();
        this.n = new c(this);
        this.l = new b();
        this.l.start();
        this.f.k.registerObserver(d.a());
    }

    protected final void a(List<TaskInfo> list) {
        int i;
        int i2 = 0;
        if (list == null) {
            list = new ArrayList();
            List j = this.f.j();
            for (i = 0; i < j.size(); i++) {
                list.add(j.get(i));
            }
        }
        Message obtainMessage = this.j.obtainMessage();
        obtainMessage.obj = list;
        obtainMessage.what = 10001;
        while (i2 < this.t.size()) {
            ((Handler) this.t.get(i2)).obtainMessage(obtainMessage.what, obtainMessage.arg1, obtainMessage.arg2, obtainMessage.obj).sendToTarget();
            i2++;
        }
        if (this.b != null) {
            i = obtainMessage.what;
            i2 = obtainMessage.arg1;
            int i3 = obtainMessage.arg2;
            Object obj = obtainMessage.obj;
            int size = list.size();
            if (i == 10001) {
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - this.v >= 3800 || size == 0) {
                    this.b.obtainMessage(i, i2, i3, obj).sendToTarget();
                    this.v = currentTimeMillis;
                    return;
                }
                return;
            }
            this.b.obtainMessage(i, i2, i3, obj).sendToTarget();
        }
    }

    public final void a(Handler handler) {
        if (!this.u.contains(handler)) {
            this.u.add(handler);
        }
    }

    public final void b(Handler handler) {
        if (this.u.contains(handler)) {
            this.u.remove(handler);
        }
    }

    protected final void a(int i, TaskInfo taskInfo, int i2) {
        for (int i3 = 0; i3 < this.u.size(); i3++) {
            ((Handler) this.u.get(i3)).obtainMessage(R.styleable.AppCompatTheme_ratingBarStyleSmall, i2, i, taskInfo).sendToTarget();
            Message obtainMessage = ((Handler) this.u.get(i3)).obtainMessage(R.styleable.AppCompatTheme_ratingBarStyleSmall, i2, i, taskInfo);
            Bundle bundle = new Bundle();
            bundle.putBoolean("hasBeforeState", true);
            obtainMessage.setData(bundle);
            obtainMessage.sendToTarget();
        }
        a(null);
    }

    public final boolean a(Handler handler, boolean z) {
        n nVar = new n(5);
        nVar.c = handler;
        nVar.f = z;
        return a(nVar);
    }

    public final boolean a(c cVar, Handler handler) {
        new StringBuilder("commitDownloadTask  ").append(cVar);
        c();
        cVar.c = handler;
        Message obtainMessage = this.j.obtainMessage(137, cVar);
        long currentTimeMillis = System.currentTimeMillis();
        long j = currentTimeMillis - q;
        q = currentTimeMillis;
        if (j < 1000) {
            r += 800;
        } else {
            r = 0;
        }
        this.j.sendMessageDelayed(obtainMessage, r);
        return true;
    }

    public final void c() {
        this.d++;
    }

    public final int d() {
        com.xunlei.downloadprovider.service.downloads.task.a.a aVar = this.f;
        if (aVar.c.isEmpty()) {
            return 0;
        }
        Iterator it = aVar.c.iterator();
        int i = 0;
        while (it.hasNext()) {
            int i2;
            if (((TaskInfo) it.next()).mSeen == 0) {
                i2 = i + 1;
            } else {
                i2 = i;
            }
            i = i2;
        }
        return i;
    }

    public final boolean a(n nVar) {
        if (nVar == null) {
            return false;
        }
        this.j.obtainMessage(138, nVar).sendToTarget();
        return true;
    }

    public final int a() {
        return this.f.i();
    }

    public final List<TaskInfo> b() {
        return this.f.j();
    }

    static /* synthetic */ void b(DownloadEngine downloadEngine) {
        ArrayList arrayList = new ArrayList();
        Message obtainMessage = downloadEngine.j.obtainMessage();
        obtainMessage.obj = arrayList;
        obtainMessage.what = 10000;
        for (int i = 0; i < downloadEngine.t.size(); i++) {
            ((Handler) downloadEngine.t.get(i)).obtainMessage(obtainMessage.what, obtainMessage.arg1, obtainMessage.arg2, obtainMessage.obj).sendToTarget();
        }
    }

    static /* synthetic */ void a(DownloadEngine downloadEngine, Message message) {
        TaskInfo taskInfo = (TaskInfo) message.obj;
        if (100 == message.what) {
            downloadEngine.f.a(taskInfo.mTaskId, taskInfo);
            downloadEngine.f.a(taskInfo);
            if (taskInfo.mTaskStatus == 0 || 2 == taskInfo.mTaskStatus || 8 == taskInfo.mTaskStatus) {
                downloadEngine.f.a(taskInfo, taskInfo.mTaskStatus, -1);
            }
        }
        if (downloadEngine.u != null) {
            for (Handler handler : downloadEngine.u) {
                handler.obtainMessage(message.what, message.arg1, message.arg2, taskInfo).sendToTarget();
            }
        }
        if (100 == message.what && com.xunlei.downloadprovider.businessutil.b.a().a.getSharedPreferences("settingstate", 0).getBoolean("name_first_create_task", true) && !downloadEngine.s) {
            com.xunlei.downloadprovider.businessutil.b.a().g();
            downloadEngine.m.sendEmptyMessageDelayed(IHost.HOST_NOFITY_PAGE_DESELECTED, 50);
        }
    }

    static /* synthetic */ void c(DownloadEngine downloadEngine) {
        Intent intent = new Intent(downloadEngine.g, StorageTipActivity.class);
        intent.setFlags(268435456);
        downloadEngine.g.startActivity(intent);
    }

    static /* synthetic */ void a(DownloadEngine downloadEngine, Handler handler, int i) {
        downloadEngine.f.e();
        downloadEngine.c = true;
        if (downloadEngine.w == null) {
            downloadEngine.w = new a();
            if (downloadEngine.j != null) {
                downloadEngine.j.post(downloadEngine.w);
            }
        }
        downloadEngine.b(null, false);
        downloadEngine.m.obtainMessage(IHost.HOST_NOFITY_REFRESH_LIST).sendToTarget();
        handler.obtainMessage(i).sendToTarget();
    }

    static /* synthetic */ void d(DownloadEngine downloadEngine) {
        downloadEngine.c = false;
        if (!(downloadEngine.j == null || downloadEngine.w == null)) {
            downloadEngine.j.removeCallbacks(downloadEngine.w);
        }
        downloadEngine.w = null;
    }

    static /* synthetic */ void e(DownloadEngine downloadEngine) {
        for (TaskInfo taskInfo : downloadEngine.f.a.values()) {
            taskInfo.mShouldAutoSpeedup = false;
        }
    }

    static /* synthetic */ void f(DownloadEngine downloadEngine) {
        if (downloadEngine.f.a != null && !downloadEngine.f.a.isEmpty()) {
            Collection arrayList = new ArrayList();
            for (TaskInfo taskInfo : downloadEngine.f.a.values()) {
                if (8 != taskInfo.mTaskStatus) {
                    arrayList.add(Long.valueOf(taskInfo.mTaskId));
                }
            }
            long[] a = com.xunlei.downloadprovider.service.downloads.b.c.a(arrayList);
            if (a.length > 0) {
                com.xunlei.downloadprovider.service.downloads.kernel.c.a().b(a);
                com.xunlei.downloadprovider.service.downloads.kernel.c.a().c(a);
            }
        }
    }
}
