package com.xunlei.downloadprovider.download.tasklist.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView.t;
import android.view.View;
import android.view.ViewGroup;
import com.android.volley.toolbox.i;
import com.xunlei.downloadprovider.ad.recommend.a.k;
import com.xunlei.downloadprovider.download.tasklist.TaskListPageFragment;
import com.xunlei.downloadprovider.download.tasklist.TaskListPageFragment.LOAD_TAG;
import com.xunlei.downloadprovider.download.tasklist.list.a.a.h;
import com.xunlei.downloadprovider.download.tasklist.list.a.a.m;
import com.xunlei.downloadprovider.download.tasklist.list.a.af;
import com.xunlei.downloadprovider.download.tasklist.list.a.at;
import com.xunlei.downloadprovider.download.tasklist.list.b.e;
import com.xunlei.downloadprovider.download.tasklist.list.b.f;
import com.xunlei.downloadprovider.download.tasklist.list.c.g;
import com.xunlei.downloadprovider.download.tasklist.list.xzb.c;
import com.xunlei.downloadprovider.download.tasklist.list.xzb.report.XZBReporter;
import com.xunlei.downloadprovider.homepage.choiceness.ui.a.b;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.util.r;
import com.xunlei.downloadprovider.util.r.j;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import com.xunlei.xiazaibao.shoulei.XZBShouleiUtil;
import com.xunlei.xllib.R;
import com.xunlei.xllib.b.d;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: TaskListAdapter.java
public class a extends android.support.v7.widget.RecyclerView.a<f> {
    private static final String n;
    protected final ArrayList<e> a;
    protected final i b;
    protected final int c;
    protected Context d;
    public boolean e;
    public boolean f;
    public a g;
    public TaskListPageFragment h;
    public XZBDevice i;
    public e j;
    public e k;
    public int l;
    public com.xunlei.downloadprovider.download.a.a m;
    private ArrayList<e> o;
    private e p;
    private e q;

    // compiled from: TaskListAdapter.java
    public static interface a {
        void a();
    }

    public /* synthetic */ void onBindViewHolder(t tVar, int i) {
        f fVar = (f) tVar;
        new StringBuilder("onBindViewHolder.position: ").append(i).append(" mpageIndex: ").append(this.c);
        if (fVar.h() == null) {
            fVar.a(this.m);
        }
        if (fVar instanceof at) {
            ((at) fVar).a = this.l;
        }
        e eVar = (e) this.a.get(i);
        fVar.b(this.e);
        fVar.a(eVar);
    }

    static {
        n = a.class.getSimpleName();
    }

    public a(Context context, int i, TaskListPageFragment taskListPageFragment) {
        this.a = new ArrayList(1);
        this.o = new ArrayList();
        this.f = false;
        this.i = new XZBDevice();
        this.q = new e();
        this.d = context;
        this.c = i;
        this.b = new i(com.xunlei.downloadprovider.j.a.b(), new b());
        this.h = taskListPageFragment;
    }

    public final int a() {
        return this.c;
    }

    public final void b() {
        List k = k();
        TaskListPageFragment taskListPageFragment = this.h;
        if (taskListPageFragment.e != null) {
            taskListPageFragment.e.a(k);
        }
    }

    public int getItemViewType(int i) {
        e eVar = (e) this.a.get(i);
        return eVar == null ? -1 : eVar.a;
    }

    public long getItemId(int i) {
        e eVar = (e) this.a.get(i);
        if (eVar == null) {
            return -1;
        }
        long j = eVar.b;
        return (j == -1 || j > 2147483647L) ? -1 : (((long) eVar.a) << 32) | (j & 2147483647L);
    }

    public int getItemCount() {
        return this.a.size();
    }

    public final void a(e eVar) {
        this.a.remove(eVar);
        notifyDataSetChanged();
    }

    public final void a(Collection<e> collection) {
        this.a.removeAll(collection);
        notifyDataSetChanged();
    }

    public final void c() {
        if (this.p != null && this.a.contains(this.p)) {
            a(this.p);
            this.p = null;
        }
    }

    public final boolean d() {
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            e eVar = (e) it.next();
            if (eVar.a == 0 && !eVar.a()) {
                return false;
            }
        }
        return true;
    }

    public final boolean e() {
        if (this.a == null || this.a.size() == 0) {
            return false;
        }
        if (this.a.size() == 1) {
            return ((e) this.a.get(0)).a == 0;
        } else {
            return true;
        }
    }

    public final boolean f() {
        if (this.a == null || this.a.size() == 0) {
            return false;
        }
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            if (((e) it.next()).a == 0) {
                return true;
            }
        }
        return false;
    }

    private int p() {
        if (this.a == null || this.a.size() == 0) {
            return 0;
        }
        Iterator it = this.a.iterator();
        int i = 0;
        while (it.hasNext()) {
            int i2;
            if (((e) it.next()).a == 0) {
                i2 = i + 1;
            } else {
                i2 = i;
            }
            i = i2;
        }
        return i;
    }

    public final int g() {
        if (this.a == null || this.a.size() == 0) {
            return 0;
        }
        Iterator it = this.a.iterator();
        int i = 0;
        while (it.hasNext()) {
            int i2;
            e eVar = (e) it.next();
            if (eVar.a == 0 || eVar.a == 200) {
                i2 = i + 1;
            } else {
                i2 = i;
            }
            i = i2;
        }
        return i;
    }

    public final void h() {
        new StringBuilder("removeListADs page: ").append(this.c);
        this.h.f.remove(LOAD_TAG.LOAD_LIST_AD);
        View view = this.h.getView();
        if (view != null) {
            view.post(new b(this));
        }
    }

    public final void a(List<e> list) {
        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                e eVar = (e) list.get(i);
                if (!this.a.contains(eVar)) {
                    this.a.add(eVar);
                }
            }
            q();
            notifyDataSetChanged();
        }
    }

    private void a(int i, List<e> list) {
        if (!list.isEmpty()) {
            Collection arrayList = new ArrayList();
            for (e eVar : list) {
                if (!this.a.contains(eVar)) {
                    arrayList.add(eVar);
                }
            }
            if (!arrayList.isEmpty()) {
                this.a.addAll(i, arrayList);
            }
            q();
            notifyDataSetChanged();
        }
    }

    public final int a(long j) {
        for (int i = 0; i < this.a.size(); i++) {
            e eVar = (e) this.a.get(i);
            if (eVar.b() != null && eVar.b().getTaskId() == j) {
                return i;
            }
        }
        return -1;
    }

    public final void a(boolean z) {
        if (this.e != z) {
            this.e = z;
            notifyDataSetChanged();
        }
        int i;
        if (this.e) {
            if (this.a.size() != 0) {
                if (this.o.size() > 0) {
                    this.o.clear();
                }
                this.o.addAll(this.a);
                i = 0;
                while (i < this.a.size()) {
                    if (((e) this.a.get(i)).a != 0) {
                        this.a.remove(i);
                        i--;
                    }
                    i++;
                }
            }
            if (!d.a(this.a)) {
                this.a.remove(this.q);
                this.a.add(this.q);
            }
        } else if (this.o.size() != 0) {
            i = 0;
            while (i < this.o.size()) {
                e eVar = (e) this.o.get(i);
                if (eVar.a == 0 && !this.a.contains(eVar)) {
                    this.o.remove(eVar);
                    i--;
                }
                i++;
            }
            this.a.clear();
            this.a.addAll(this.o);
            this.o.clear();
            this.a.remove(this.q);
        }
        if (!z) {
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                ((e) it.next()).a(false);
            }
            notifyDataSetChanged();
        }
    }

    public final void i() {
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            e eVar = (e) it.next();
            if (eVar.a == 0) {
                eVar.a(true);
            }
        }
        notifyDataSetChanged();
    }

    public final void j() {
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            e eVar = (e) it.next();
            if (eVar.a == 0) {
                eVar.a(false);
            }
        }
        notifyDataSetChanged();
    }

    public final List<e> k() {
        List arrayList = new ArrayList();
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            e eVar = (e) it.next();
            if (eVar.a()) {
                arrayList.add(eVar);
            }
        }
        return arrayList;
    }

    public final void b(List<e> list) {
        if (list != null && !list.isEmpty()) {
            for (e eVar : list) {
                com.xunlei.downloadprovider.download.tasklist.a.a b = eVar.b();
                if (!this.a.contains(eVar) && b != null) {
                    int i;
                    int i2 = 0;
                    int i3 = -1;
                    while (i2 < this.a.size()) {
                        if (((e) this.a.get(i2)).a == 0) {
                            com.xunlei.downloadprovider.download.tasklist.a.a b2 = ((e) this.a.get(i2)).b();
                            if (b2 != null && b2.mCreateTime <= b.mCreateTime) {
                                if (i3 == 0 && i2 == 1) {
                                    this.a.add(0, eVar);
                                    i = 1;
                                } else {
                                    this.a.add(i2, eVar);
                                    i = 1;
                                }
                                if (i == 0) {
                                    if (f()) {
                                        this.a.add(0, eVar);
                                    } else {
                                        this.a.add(eVar);
                                    }
                                }
                            }
                        } else if (((e) this.a.get(0)).a == 100 || ((e) this.a.get(0)).a == 101) {
                            i3 = i2;
                        }
                        i2++;
                    }
                    i = 0;
                    if (i == 0) {
                        if (f()) {
                            this.a.add(eVar);
                        } else {
                            this.a.add(0, eVar);
                        }
                    }
                }
            }
            q();
            notifyDataSetChanged();
        }
    }

    public final void a(com.xunlei.downloadprovider.download.tasklist.a.b bVar) {
        int i = 0;
        if (bVar != null) {
            Collection a = bVar.a();
            Collection arrayList = new ArrayList();
            Object obj = null;
            if (this.a.isEmpty()) {
                this.a.addAll(a);
                t();
                notifyDataSetChanged();
                return;
            }
            int i2;
            HashMap hashMap = new HashMap();
            ArrayList arrayList2 = new ArrayList();
            int i3 = 0;
            int i4 = 0;
            while (i3 < this.a.size()) {
                Object obj2;
                e eVar = (e) this.a.get(i3);
                if (eVar.a == 0) {
                    arrayList.add(eVar);
                    i2 = i4;
                    obj2 = obj;
                } else if (eVar.a == 100 || eVar.a == 101) {
                    hashMap.put(Integer.valueOf(i3), eVar);
                    arrayList2.add(Integer.valueOf(i3));
                    i2 = i4;
                    obj2 = obj;
                } else if (eVar.a == 200) {
                    e eVar2 = eVar;
                    i2 = i3;
                } else {
                    i2 = i4;
                    obj2 = obj;
                }
                i3++;
                obj = obj2;
                i4 = i2;
            }
            this.a.removeAll(arrayList);
            this.a.addAll(a);
            q();
            if (obj != null) {
                this.a.remove(obj);
                if (i4 <= this.a.size()) {
                    Iterator it = arrayList2.iterator();
                    i3 = 0;
                    while (it.hasNext()) {
                        if (((Integer) it.next()).intValue() > i4) {
                            i2 = i3 + 1;
                        } else {
                            i2 = i3;
                        }
                        i3 = i2;
                    }
                    this.a.add(i4 + i3, obj);
                } else {
                    this.a.add(obj);
                }
            }
            Iterator it2 = arrayList2.iterator();
            while (it2.hasNext()) {
                this.a.remove(hashMap.get(Integer.valueOf(((Integer) it2.next()).intValue())));
            }
            if (!a.isEmpty()) {
                if (hashMap.get(Integer.valueOf(0)) != null) {
                    HashSet hashSet = new HashSet(a);
                    hashSet.removeAll(arrayList);
                    if (hashSet.isEmpty()) {
                        this.a.add(0, hashMap.get(Integer.valueOf(0)));
                    } else {
                        this.a.add(Math.min(this.a.size(), 1), hashMap.get(Integer.valueOf(0)));
                    }
                    arrayList2.remove(0);
                }
                hashMap.remove(Integer.valueOf(0));
            }
            while (i < arrayList2.size()) {
                this.a.add(((Integer) arrayList2.get(i)).intValue(), hashMap.get(arrayList2.get(i)));
                i++;
            }
            q();
            notifyDataSetChanged();
        }
    }

    public final void b(e eVar) {
        if (this.j != null) {
            this.a.remove(this.j);
        }
        this.j = eVar;
        if (this.j != null) {
            this.a.remove(this.j);
            this.a.add(0, this.j);
        }
        notifyDataSetChanged();
    }

    public final void l() {
        if (this.k != null) {
            this.a.remove(this.k);
            this.k = null;
            this.h.i = false;
            notifyDataSetChanged();
        }
    }

    private void q() {
        if (this.p != null) {
            this.a.remove(this.p);
            this.a.add(this.p);
        }
        if (this.j != null) {
            this.a.remove(this.j);
            if (this.h.l && !this.h.m) {
                this.a.add(0, this.j);
            }
        }
        if (this.k != null) {
            this.a.remove(this.k);
            if (!this.h.g) {
                this.a.add(0, this.k);
            }
        }
        t();
    }

    public final void m() {
        if (this.c == 0) {
            com.xunlei.downloadprovider.download.tasklist.list.xzb.e.a();
            if (com.xunlei.downloadprovider.download.tasklist.list.xzb.e.a(p())) {
                boolean s = s();
                LoginHelper.a();
                if (!LoginHelper.c() || XZBShouleiUtil.getInstance().getDefaultDevice() == null) {
                    if (s) {
                        r();
                    } else {
                        a(this.i);
                    }
                } else if (s) {
                    int i;
                    Iterator it = this.a.iterator();
                    while (it.hasNext()) {
                        e eVar = (e) it.next();
                        if (eVar.a == 200) {
                            int indexOf = this.a.indexOf(eVar);
                            this.a.remove(eVar);
                            notifyDataSetChanged();
                            int i2 = indexOf;
                            e eVar2 = eVar;
                            i = i2;
                            break;
                        }
                    }
                    i = 0;
                    Object obj = null;
                    if (obj != null && i >= 0) {
                        this.a.add(i, obj);
                        q();
                        notifyDataSetChanged();
                    }
                } else {
                    XZBDevice defaultDevice = XZBShouleiUtil.getInstance().getDefaultDevice();
                    if (defaultDevice != null) {
                        List arrayList = new ArrayList();
                        arrayList.add(new e(200, defaultDevice, 0));
                        a(arrayList, defaultDevice);
                    } else if (s()) {
                        r();
                    } else {
                        a(this.i);
                    }
                }
            }
        }
    }

    private void a(List<e> list, XZBDevice xZBDevice) {
        if (!list.isEmpty()) {
            Object obj = null;
            for (e eVar : list) {
                if (!this.a.contains(eVar)) {
                    for (int i = 0; i < this.a.size(); i++) {
                        if (((e) this.a.get(i)).a == 0) {
                            com.xunlei.downloadprovider.download.tasklist.a.a b = ((e) this.a.get(i)).b();
                            if (b != null && b.mCreateTime <= xZBDevice.getLastCreateTime() * 1000) {
                                this.a.add(i, eVar);
                                int i2 = 1;
                                break;
                            }
                        }
                    }
                }
            }
            if (obj != null) {
                XZBReporter.a();
                q();
                notifyDataSetChanged();
            } else if (s()) {
                r();
            } else {
                a(xZBDevice);
            }
        }
    }

    private void a(XZBDevice xZBDevice) {
        while (xZBDevice == null) {
            if (s()) {
                r();
                return;
            }
            xZBDevice = this.i;
        }
        c(new e(200, xZBDevice, 0));
        XZBReporter.a();
    }

    private void r() {
        e eVar;
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            eVar = (e) it.next();
            if (eVar.a == 200) {
                this.a.remove(eVar);
                notifyDataSetChanged();
                break;
            }
        }
        eVar = null;
        c(eVar);
    }

    private void c(e eVar) {
        Object obj = 1;
        int i = -1;
        if (eVar != null) {
            List arrayList = new ArrayList();
            arrayList.add(eVar);
            j jVar = r.c().h;
            if (jVar.a != null) {
                i = jVar.a.optInt("xzb_exhibition_position", -1);
            }
            int i2 = (i < 6 || i > 20) ? 0 : 1;
            if (i2 != 0) {
                com.xunlei.downloadprovider.download.tasklist.list.xzb.e.a();
                com.xunlei.downloadprovider.download.tasklist.list.xzb.e.c(i);
            } else {
                int i3;
                com.xunlei.downloadprovider.download.tasklist.list.xzb.e.a();
                i2 = com.xunlei.downloadprovider.download.tasklist.list.xzb.e.e();
                if (i2 < 6 || i2 > 20) {
                    i3 = 0;
                }
                if (i3 != 0) {
                    com.xunlei.downloadprovider.download.tasklist.list.xzb.e.a();
                    i = com.xunlei.downloadprovider.download.tasklist.list.xzb.e.e();
                } else if (i < 0 || i < 6 || i > 20) {
                    i = 6;
                }
            }
            int a = r.c().h.a();
            i = a < i ? a : i - 1;
            if (p() >= i) {
                if (getItemCount() > 0) {
                    i = a(i);
                    if (i < 0) {
                        this.a.add(eVar);
                        q();
                        notifyDataSetChanged();
                        return;
                    } else if (i == 0) {
                        a(0, arrayList);
                        return;
                    } else {
                        a(i, arrayList);
                        return;
                    }
                }
                a(0, arrayList);
            }
        }
    }

    private int a(int i) {
        if (i == 0 || this.a == null || this.a.size() == 0) {
            return 0;
        }
        Object arrayList = new ArrayList();
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            e eVar = (e) it.next();
            if (eVar.a == 0) {
                arrayList.add(eVar);
            }
        }
        if (d.a(arrayList)) {
            return -1;
        }
        eVar = (e) arrayList.get(i - 1);
        return eVar == null ? -1 : this.a.indexOf(eVar) + 1;
    }

    private boolean s() {
        if (d.a(this.a)) {
            return false;
        }
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            if (((e) it.next()).a == 200) {
                return true;
            }
        }
        return false;
    }

    private void t() {
        if (this.a != null && !this.a.isEmpty()) {
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                Object obj = ((e) it.next()).c;
                if (obj != null && (obj instanceof com.xunlei.downloadprovider.download.tasklist.a.a)) {
                    ((com.xunlei.downloadprovider.download.tasklist.a.a) obj).m = false;
                }
            }
        }
    }

    public final void n() {
        if (!this.f) {
            this.f = true;
            new StringBuilder("onDownloadTaskLoaded - PageIndex = ").append(this.c);
            m();
            if (f()) {
                boolean z;
                m a = m.a();
                int i = this.c;
                if (a.c.get(Integer.valueOf(i)) == null) {
                    z = false;
                } else {
                    z = ((Boolean) a.c.get(Integer.valueOf(i))).booleanValue();
                }
                if (!z) {
                    com.xunlei.downloadprovider.download.tasklist.list.a.a.f a2 = com.xunlei.downloadprovider.download.tasklist.list.a.a.f.a();
                    com.xunlei.downloadprovider.download.tasklist.list.a.a.f.a dVar = new d(this);
                    if (a2.f) {
                        a2.e.add(dVar);
                    } else if (a2.g) {
                        dVar.a();
                    } else {
                        dVar.b();
                    }
                }
            }
            switch (this.c) {
                case MqttConnectOptions.MQTT_VERSION_DEFAULT:
                case SimpleLog.LOG_LEVEL_TRACE:
                case SimpleLog.LOG_LEVEL_DEBUG:
                    if (com.xunlei.downloadprovider.ad.recommend.view.b.i()) {
                        this.h.f.add(LOAD_TAG.LOAD_RECOMMEND_AD);
                        e eVar = new e(150, null, 0);
                        new StringBuilder("addRecommendAdCard: ").append(this.c);
                        if (this.p != null) {
                            this.a.remove(this.p);
                        }
                        this.p = eVar;
                        if (this.p != null) {
                            this.a.remove(this.p);
                            this.a.add(this.p);
                        }
                        notifyDataSetChanged();
                    }
                    break;
            }
            if (this.g != null) {
                this.g.a();
            }
        }
    }

    public final void o() {
        int i = SimpleLog.LOG_LEVEL_FATAL;
        int i2 = MqttConnectOptions.MQTT_VERSION_3_1_1;
        if (!h.a[this.c]) {
            int i3;
            this.h.f.add(LOAD_TAG.LOAD_LIST_AD);
            ArrayList arrayList = new ArrayList();
            if (r.c().e != null) {
                com.xunlei.downloadprovider.util.r.a aVar = r.c().e;
                if (aVar.a != null) {
                    i = aVar.a.optInt("downloadlist_ad_task_threshold", SimpleLog.LOG_LEVEL_FATAL);
                }
                i3 = i;
            } else {
                i3 = 6;
            }
            if (r.c().e != null) {
                com.xunlei.downloadprovider.util.r.a aVar2 = r.c().e;
                if (aVar2.a != null) {
                    i = aVar2.a.optInt("ad_downloadlist_position", MqttConnectOptions.MQTT_VERSION_3_1_1);
                } else {
                    i = 4;
                }
                i2 = i;
            }
            int itemCount = getItemCount();
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                if (((e) it.next()).a == 150) {
                    i = itemCount - 1;
                    break;
                }
            }
            i = itemCount;
            for (itemCount = 0; itemCount < i; itemCount++) {
                int i4 = (i2 - 1) + (itemCount * i3);
                if (i4 <= i - 1) {
                    arrayList.add(Integer.valueOf(i4));
                } else if (arrayList.size() != 0) {
                    if (i - (arrayList.size() * i3) > 0) {
                        arrayList.add(Integer.valueOf(i - 1));
                        break;
                    }
                } else if (arrayList.size() == 0) {
                    if (i != 0) {
                        arrayList.add(Integer.valueOf(i - 1));
                    } else {
                        arrayList.add(Integer.valueOf(0));
                    }
                }
            }
            if (i == 0) {
                arrayList.add(Integer.valueOf(0));
            }
            i3 = arrayList.size() > 5 ? 5 : arrayList.size();
            for (i2 = 0; i2 < i3; i2++) {
                List arrayList2 = new ArrayList();
                i = R.styleable.AppCompatTheme_buttonStyle;
                if (this.c == 1) {
                    i = R.styleable.AppCompatTheme_buttonStyleSmall;
                }
                arrayList2.add(new e(i, Integer.valueOf(i2), (long) i2));
                a(((Integer) arrayList.get(i2)).intValue() + i2, arrayList2);
            }
        }
    }

    public /* synthetic */ t onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 3) {
            return at.a(this.d, viewGroup);
        }
        if (i == 0) {
            return g.a(this.d, this.m, this, this.c);
        }
        if (i == 100) {
            return com.xunlei.downloadprovider.download.tasklist.list.a.r.a(this.d, viewGroup, this.b, this);
        }
        if (i == 101) {
            return af.a(this.d, viewGroup, this.b, this);
        }
        if (i == 150) {
            return com.xunlei.downloadprovider.ad.recommend.view.b.a(this.d, viewGroup, this.m, this, new com.xunlei.downloadprovider.ad.recommend.b.b(k.a()), this.c);
        } else if (i == 201) {
            return com.xunlei.downloadprovider.download.tasklist.list.g.a.a(this.d, viewGroup, this.m, this);
        } else {
            if (i == 200) {
                return c.a(this.d, viewGroup, this, this.c);
            }
            return i == 202 ? com.xunlei.downloadprovider.download.tasklist.list.d.b.a(this.d, viewGroup, this.m, this) : null;
        }
    }
}
