package com.xunlei.downloadprovider.download.taskDetail.widget;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.download.report.a;
import com.xunlei.downloadprovider.download.util.n;

// compiled from: TaskDetailSpeedInfoView.java
final class g implements OnClickListener {
    final /* synthetic */ TaskDetailSpeedInfoView a;

    g(TaskDetailSpeedInfoView taskDetailSpeedInfoView) {
        this.a = taskDetailSpeedInfoView;
    }

    public final void onClick(View view) {
        if (this.a.b != null) {
            this.a.c.a(this.a.b.getTaskId(), "v_an_shoulei_hytq_msion_detail");
            a.a("dl_acce", n.e(this.a.b), n.c(this.a.b) ? 1 : 0);
        }
    }
}
