package com.xunlei.downloadprovider.download.tasklist.a;

import android.database.Observable;
import android.os.Handler;
import android.os.Looper;
import com.xunlei.downloadprovider.download.tasklist.a.b.c;
import com.xunlei.downloadprovider.download.tasklist.list.b.e;
import com.xunlei.downloadprovider.service.downloads.task.info.TaskRunningInfo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

// compiled from: TaskDataSource.java
public final class b {
    public final int a;
    protected final ConcurrentHashMap<Long, e> b;
    protected final List<e> c;
    protected b d;
    public boolean e;
    boolean f;
    private boolean g;

    // compiled from: TaskDataSource.java
    static class b extends Observable<c> {
        b() {
        }

        public void a() {
            for (int size = this.mObservers.size() - 1; size >= 0; size--) {
                ((c) this.mObservers.get(size)).a();
            }
        }

        public void b() {
            for (int size = this.mObservers.size() - 1; size >= 0; size--) {
                ((c) this.mObservers.get(size)).b();
            }
        }

        public void a(List<e> list) {
            for (int size = this.mObservers.size() - 1; size >= 0; size--) {
                ((c) this.mObservers.get(size)).a((List) list);
            }
        }

        public void a(Collection<e> collection) {
            for (int size = this.mObservers.size() - 1; size >= 0; size--) {
                ((c) this.mObservers.get(size)).a((Collection) collection);
            }
        }
    }

    // compiled from: TaskDataSource.java
    static class a extends b {
        protected Handler a;

        public a(Handler handler) {
            this.a = handler;
        }

        public final void a() {
            if (this.a == null) {
                super.a();
            } else {
                this.a.post(new d(this));
            }
        }

        public final void b() {
            if (this.a == null) {
                super.b();
            } else {
                this.a.post(new e(this));
            }
        }

        public final void a(List<e> list) {
            if (this.a == null) {
                super.a((List) list);
            } else {
                this.a.post(new f(this, list));
            }
        }

        public final void a(Collection<e> collection) {
            if (this.a == null) {
                super.a((Collection) collection);
            } else {
                this.a.post(new g(this, collection));
            }
        }
    }

    // compiled from: TaskDataSource.java
    public static abstract class c {
        public void a() {
        }

        public void b() {
        }

        public void a(List<e> list) {
        }

        public void a(Collection<e> collection) {
        }
    }

    public b(int i) {
        this.b = new ConcurrentHashMap();
        this.c = new ArrayList();
        this.d = new a(new Handler(Looper.getMainLooper()));
        this.g = true;
        this.e = false;
        this.a = i;
    }

    private void c() {
        if (this.b.isEmpty()) {
            synchronized (this.c) {
                this.c.clear();
                this.g = false;
            }
            return;
        }
        List arrayList = new ArrayList(this.b.values());
        b(arrayList);
        synchronized (this.c) {
            this.c.clear();
            this.c.addAll(arrayList);
            this.g = false;
        }
    }

    private static void b(List<e> list) {
        if (list.size() > 1) {
            Collections.sort(list, new c());
        }
    }

    public final void b() {
        if (this.a != 0) {
            Collection<e> arrayList = new ArrayList();
            Collection<e> values = this.b.values();
            HashSet hashSet = new HashSet();
            if (!values.isEmpty()) {
                for (e eVar : values) {
                    if (eVar.a == 0) {
                        a b = eVar.b();
                        if (b != null) {
                            if (this.a == 1) {
                                if (b.mTaskStatus == 8) {
                                    arrayList.add(eVar);
                                }
                            } else if (this.a == 2 && b.mTaskStatus != 8) {
                                arrayList.add(eVar);
                            }
                        }
                    }
                }
                if (!arrayList.isEmpty()) {
                    for (e eVar2 : arrayList) {
                        if (eVar2.b() != null) {
                            this.b.remove(Long.valueOf(eVar2.b().mTaskId));
                        }
                    }
                    this.g = true;
                    this.d.a((Collection) arrayList);
                    if (this.f) {
                        this.d.b();
                    }
                }
            }
        }
    }

    public final boolean a(List<a> list, boolean z) {
        boolean z2;
        List arrayList = new ArrayList();
        Collection hashSet = new HashSet();
        int i;
        if (list != null && !list.isEmpty()) {
            i = 0;
            for (a aVar : list) {
                Object eVar;
                long j = aVar.mTaskId;
                e eVar2 = (e) this.b.get(Long.valueOf(j));
                if (eVar2 == null) {
                    eVar = new e(0, aVar, j);
                    arrayList.add(eVar);
                    this.b.put(Long.valueOf(j), eVar);
                    this.g = true;
                } else {
                    eVar2.c = aVar;
                }
                if (this.a == 1) {
                    if (aVar.mTaskStatus == 8) {
                        z2 = true;
                    }
                    i = 0;
                } else {
                    if (this.a == 2 && aVar.mTaskStatus != 8) {
                        z2 = true;
                    }
                    i = 0;
                }
                if (z2) {
                    this.b.remove(Long.valueOf(j));
                    this.g = true;
                    arrayList.remove(eVar);
                    hashSet.add(eVar);
                }
                z2 = true;
            }
        } else if (list == null || !list.isEmpty()) {
            i = 0;
        } else {
            synchronized (this.b) {
                if (!this.b.isEmpty()) {
                    for (e eVar3 : this.b.values()) {
                        hashSet.add(eVar3);
                    }
                    this.b.clear();
                    this.g = true;
                }
            }
            i = 0;
        }
        if (z && !this.b.isEmpty()) {
            b();
        }
        if (this.g) {
            c();
        }
        if (!hashSet.isEmpty()) {
            this.d.a(hashSet);
            z2 = true;
        }
        if (!arrayList.isEmpty()) {
            b(arrayList);
            this.d.a(arrayList);
            z2 = true;
        }
        if (z2 && this.f) {
            this.d.b();
        }
        if (!this.e) {
            this.e = true;
            this.d.a();
        }
        return z2;
    }

    public final List<e> a(List<TaskRunningInfo> list) {
        List<e> emptyList = Collections.emptyList();
        if (list == null || list.isEmpty() || this.b == null) {
            return Collections.emptyList();
        }
        Collection arrayList = new ArrayList();
        for (TaskRunningInfo taskRunningInfo : list) {
            e eVar = (e) this.b.remove(Long.valueOf(taskRunningInfo.mTaskId));
            this.g = true;
            if (eVar != null) {
                arrayList.add(eVar);
            }
        }
        if (arrayList.isEmpty()) {
            return emptyList;
        }
        this.d.a(arrayList);
        if (this.f) {
            this.d.b();
        }
        return arrayList;
    }

    public final List<e> a(Collection<Long> collection) {
        List<e> emptyList = Collections.emptyList();
        if (collection == null || collection.isEmpty() || this.b == null) {
            return Collections.emptyList();
        }
        Collection arrayList = new ArrayList();
        for (Long l : collection) {
            e eVar = (e) this.b.remove(l);
            this.g = true;
            if (eVar != null) {
                arrayList.add(eVar);
            }
        }
        if (arrayList.isEmpty()) {
            return emptyList;
        }
        this.d.a(arrayList);
        if (this.f) {
            this.d.b();
        }
        return arrayList;
    }

    public final void a(c cVar) {
        this.d.registerObserver(cVar);
    }

    public final void b(c cVar) {
        this.d.unregisterObserver(cVar);
    }

    public final List<e> a() {
        if (this.g) {
            c();
        }
        return new ArrayList(this.c);
    }
}
