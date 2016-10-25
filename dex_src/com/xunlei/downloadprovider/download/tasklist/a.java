package com.xunlei.downloadprovider.download.tasklist;

import android.os.Handler;
import com.xunlei.downloadprovider.download.util.k;

// compiled from: TaskListPageFragment.java
final class a extends k {
    final /* synthetic */ TaskListPageFragment a;

    a(TaskListPageFragment taskListPageFragment, Handler handler) {
        this.a = taskListPageFragment;
        super(handler);
    }

    public final void a() {
        if (this.a.isVisible()) {
            TaskListPageFragment.a(this.a);
        }
    }
}
