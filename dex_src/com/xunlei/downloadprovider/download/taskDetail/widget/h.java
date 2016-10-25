package com.xunlei.downloadprovider.download.taskDetail.widget;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.download.report.a;
import com.xunlei.downloadprovider.download.util.n;

// compiled from: TaskDetailSpeedInfoView.java
final class h implements OnClickListener {
    final /* synthetic */ TaskDetailSpeedInfoView a;

    h(TaskDetailSpeedInfoView taskDetailSpeedInfoView) {
        this.a = taskDetailSpeedInfoView;
    }

    public final void onClick(View view) {
        if (this.a.b != null) {
            this.a.c.d(this.a.b);
            a.a("dl_download", n.e(this.a.b), n.c(this.a.b) ? 1 : 0);
        }
    }
}
