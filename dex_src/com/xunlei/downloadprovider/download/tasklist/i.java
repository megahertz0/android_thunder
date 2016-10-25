package com.xunlei.downloadprovider.download.tasklist;

// compiled from: TaskListPageFragment.java
final class i implements Runnable {
    final /* synthetic */ TaskListPageFragment a;

    i(TaskListPageFragment taskListPageFragment) {
        this.a = taskListPageFragment;
    }

    public final void run() {
        if (this.a.isVisible()) {
            TaskListPageFragment.g(this.a);
        }
    }
}
