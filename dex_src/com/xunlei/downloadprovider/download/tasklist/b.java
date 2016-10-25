package com.xunlei.downloadprovider.download.tasklist;

import com.xunlei.downloadprovider.download.tasklist.TaskListPageFragment.LOAD_TAG;
import com.xunlei.downloadprovider.download.tasklist.a.b.c;
import com.xunlei.downloadprovider.download.tasklist.list.b.e;
import java.util.Collection;
import java.util.List;

// compiled from: TaskListPageFragment.java
final class b extends c {
    int a;
    final /* synthetic */ TaskListPageFragment b;
    private long c;

    b(TaskListPageFragment taskListPageFragment) {
        this.b = taskListPageFragment;
        this.c = 0;
        this.a = 0;
    }

    public final void a() {
        if (this.b.isVisible() && this.b.isResumed()) {
            this.b.f.remove(LOAD_TAG.LOAD_TASK);
            if (this.b.d != null) {
                this.b.d.notifyDataSetChanged();
                new StringBuilder("notifyDownloadTaskLoaded - PageIndex = ").append(this.b.b);
                this.b.d.n();
            }
        }
    }

    public final void b() {
        if (this.b.isVisible() && this.b.isResumed()) {
            this.b.f.remove(LOAD_TAG.LOAD_TASK);
            long nanoTime = System.nanoTime();
            long j = (nanoTime - this.c) / 1000000;
            if (this.c == 0 || j > 3000) {
                this.c = nanoTime;
                this.a = 0;
                if (this.b.d != null) {
                    this.b.d.notifyDataSetChanged();
                    new StringBuilder("notifyDataSetChanged - PageIndex = ").append(this.b.b);
                }
            } else {
                this.c = nanoTime;
                this.a++;
                if (this.a <= 1) {
                    this.b.w.postDelayed(new c(this), com.xunlei.download.proguard.c.x);
                }
            }
            if (this.b.e != null) {
                this.b.e.a(this.b.b);
            }
            if (this.b.x > 0) {
                this.b.w.post(new d(this));
                return;
            }
            return;
        }
        TaskListPageFragment.c(this.b);
    }

    public final void a(List<e> list) {
        if (!this.b.isVisible() || !this.b.isResumed()) {
            TaskListPageFragment.c(this.b);
            this.b.k = true;
        } else if (this.b.d != null && list != null) {
            this.b.d.b((List) list);
        }
    }

    public final void a(Collection<e> collection) {
        if (!this.b.isVisible() || !this.b.isResumed()) {
            TaskListPageFragment.c(this.b);
            this.b.k = true;
        } else if (this.b.d != null && collection != null) {
            this.b.k = true;
            this.b.d.a((Collection) collection);
        }
    }
}
