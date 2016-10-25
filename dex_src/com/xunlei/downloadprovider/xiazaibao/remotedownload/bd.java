package com.xunlei.downloadprovider.xiazaibao.remotedownload;

import com.xunlei.downloadprovider.download.taskDetail.TaskDetailDragView$a;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: XZBTaskInfoDetailFragment.java
final class bd implements TaskDetailDragView$a {
    final /* synthetic */ XZBTaskInfoDetailFragment a;

    bd(XZBTaskInfoDetailFragment xZBTaskInfoDetailFragment) {
        this.a = xZBTaskInfoDetailFragment;
    }

    public final void a(float f) {
        if (f == -1.0f) {
            XZBTaskInfoDetailFragment.a(this.a, 1);
        } else if (XZBTaskInfoDetailFragment.f(this.a) != 2) {
            XZBTaskInfoDetailFragment.a(this.a, SimpleLog.LOG_LEVEL_DEBUG);
        }
    }

    public final void a(int i) {
        if (i == 10) {
            this.a.b();
        }
    }
}
