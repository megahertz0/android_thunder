package com.xunlei.downloadprovider.download.tasklist.list.a;

import com.xunlei.downloadprovider.ad.recommend.c.b;
import com.xunlei.downloadprovider.ad.recommend.c.c;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.android.spdy.SpdyAgent;

// compiled from: DownloadADReportHelper.java
public final class n {
    public static n d;
    public HashMap<Integer, Set<Integer>> a;
    public HashMap<Integer, Set<Integer>> b;
    public Set<Integer> c;
    public int e;
    private ArrayList<o> f;
    private boolean g;

    private n() {
        this.f = new ArrayList();
        this.c = new HashSet();
        this.a = new HashMap();
        this.a.put(Integer.valueOf(0), new HashSet());
        this.a.put(Integer.valueOf(1), new HashSet());
        this.a.put(Integer.valueOf(XZBDevice.DOWNLOAD_LIST_RECYCLE), new HashSet());
        this.b = new HashMap();
        this.b.put(Integer.valueOf(0), new HashSet());
        this.b.put(Integer.valueOf(1), new HashSet());
        this.b.put(Integer.valueOf(XZBDevice.DOWNLOAD_LIST_RECYCLE), new HashSet());
    }

    public static n a() {
        if (d == null) {
            d = new n();
        }
        return d;
    }

    public final void b() {
        Collection arrayList = new ArrayList();
        Iterator it = this.f.iterator();
        while (it.hasNext()) {
            o oVar = (o) it.next();
            if (oVar.a() == this.e) {
                Object obj = null;
                int i;
                if (!oVar.b()) {
                    i = 1;
                } else if (this.g) {
                    i = 1;
                }
                if (obj != null) {
                    oVar.c();
                    arrayList.add(oVar);
                }
            }
        }
        if (arrayList.size() != 0) {
            this.f.removeAll(arrayList);
        }
    }

    public final void a(o oVar) {
        Set set;
        if (oVar instanceof b) {
            b bVar = (b) oVar;
            set = (Set) this.a.get(Integer.valueOf(bVar.a));
            if (set == null) {
                throw new NullPointerException(new StringBuilder("you should set config for this pageIndex,pageIndex: ").append(bVar.a).toString());
            } else if (!set.contains(Integer.valueOf(bVar.b))) {
                set.add(Integer.valueOf(bVar.b));
            } else {
                return;
            }
        } else if (oVar instanceof c) {
            c cVar = (c) oVar;
            set = (Set) this.b.get(Integer.valueOf(cVar.a));
            if (set == null) {
                throw new NullPointerException(new StringBuilder("you should set config for this pageIndex,pageIndex: ").append(cVar.a).toString());
            } else if (!set.contains(Integer.valueOf(cVar.c))) {
                set.add(Integer.valueOf(cVar.c));
            } else {
                return;
            }
        }
        this.f.add(oVar);
        if (oVar.a() == this.e) {
            Object obj = null;
            int i;
            if (!oVar.b()) {
                i = 1;
            } else if (this.g) {
                i = 1;
            }
            if (obj != null) {
                oVar.c();
                if (this.f != null) {
                    this.f.remove(oVar);
                }
            }
        }
    }

    public final void a(boolean z) {
        this.g = z;
        if (z) {
            b();
        }
    }

    public static int a(int i) {
        switch (i) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                return 1176;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                return 1177;
            default:
                return 1175;
        }
    }
}
