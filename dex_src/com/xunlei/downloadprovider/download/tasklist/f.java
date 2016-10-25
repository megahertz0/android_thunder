package com.xunlei.downloadprovider.download.tasklist;

// compiled from: TaskListPageFragment.java
final class f implements Runnable {
    final /* synthetic */ e a;

    f(e eVar) {
        this.a = eVar;
    }

    public final void run() {
        if (this.a.a.isVisible()) {
            TaskListPageFragment.g(this.a.a);
        }
    }
}
