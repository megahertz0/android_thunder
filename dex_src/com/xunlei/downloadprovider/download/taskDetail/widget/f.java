package com.xunlei.downloadprovider.download.taskDetail.widget;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.download.report.a;
import com.xunlei.downloadprovider.download.util.n;

// compiled from: TaskDetailSpeedInfoView.java
final class f implements OnClickListener {
    final /* synthetic */ TaskDetailSpeedInfoView a;

    f(TaskDetailSpeedInfoView taskDetailSpeedInfoView) {
        this.a = taskDetailSpeedInfoView;
    }

    public final void onClick(View view) {
        if (this.a.b != null) {
            a.a("dl_pause", n.e(this.a.b), n.c(this.a.b) ? 1 : 0);
            this.a.c;
            com.xunlei.downloadprovider.download.a.a.b(this.a.b);
        }
    }
}
