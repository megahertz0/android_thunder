package com.xunlei.downloadprovider.download.tasklist.a;

import com.xunlei.downloadprovider.service.downloads.task.info.TaskBasicInfo;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// compiled from: TaskListManager.java
class h$b {
    final LinkedList<c> b;
    final a<c> c;
    ExecutorService d;
    boolean e;

    // compiled from: TaskListManager.java
    static class a<T> {
        final LinkedList<T> a;
        int b;

        public a() {
            this.a = new LinkedList();
            this.b = 5;
            this.b = 5;
        }

        public final T a() {
            T t;
            synchronized (this.a) {
                if (this.a.isEmpty()) {
                    t = null;
                } else {
                    t = this.a.removeFirst();
                }
            }
            return t;
        }
    }

    // compiled from: TaskListManager.java
    static class b<K, V> {
        final HashMap<K, V> a;
        int b;

        public b() {
            this.a = new HashMap();
            this.b = 5;
            this.b = 20;
        }

        public final V a(K k) {
            V remove;
            synchronized (this.a) {
                if (this.a.containsKey(k)) {
                    remove = this.a.remove(k);
                } else {
                    remove = null;
                }
            }
            return remove;
        }
    }

    // compiled from: TaskListManager.java
    static class c {
        public final com.xunlei.downloadprovider.download.tasklist.a.h$b.b<Long, TaskBasicInfo> a;
        public boolean b;
        public List<TaskBasicInfo> c;

        public c(List<TaskBasicInfo> list, boolean z) {
            this.a = new b();
            this.b = false;
            this.b = z;
            this.c = list;
        }

        public final void a() {
            synchronized (this.a) {
                try {
                    if (!(this.c == null || this.c.isEmpty())) {
                        for (TaskBasicInfo taskBasicInfo : this.c) {
                            b bVar = this.a;
                            Long valueOf = Long.valueOf(taskBasicInfo.mTaskId);
                            if (taskBasicInfo != null) {
                                synchronized (bVar.a) {
                                    if (bVar.a.size() >= bVar.b) {
                                    } else {
                                        bVar.a.put(valueOf, taskBasicInfo);
                                    }
                                }
                            }
                        }
                        this.c.clear();
                    }
                } catch (Throwable th) {
                }
            }
        }
    }

    h$b() {
        this.b = new LinkedList();
        this.c = new a();
        this.d = Executors.newSingleThreadExecutor();
        this.e = true;
    }

    public void a(List<TaskBasicInfo> list, boolean z) {
    }
}
