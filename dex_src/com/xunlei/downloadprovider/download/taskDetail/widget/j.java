package com.xunlei.downloadprovider.download.taskDetail.widget;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: TaskDetailSpeedInfoView.java
final class j implements OnClickListener {
    final /* synthetic */ TaskDetailSpeedInfoView a;

    j(TaskDetailSpeedInfoView taskDetailSpeedInfoView) {
        this.a = taskDetailSpeedInfoView;
    }

    public final void onClick(View view) {
        if (this.a.e) {
            TaskDetailSpeedInfoView.d(this.a);
        } else {
            TaskDetailSpeedInfoView.e(this.a);
        }
    }
}
