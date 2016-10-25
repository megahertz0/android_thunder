package com.xunlei.downloadprovider.download.tasklist;

// compiled from: TaskListPageFragment.java
final class c implements Runnable {
    final /* synthetic */ b a;

    c(b bVar) {
        this.a = bVar;
    }

    public final void run() {
        if (this.a.b.d != null && this.a.a > 0) {
            this.a.a = 0;
            this.a.b.d.notifyDataSetChanged();
            new StringBuilder("notifyDataSetChanged - PageIndex = ").append(this.a.b.b);
        }
    }
}
