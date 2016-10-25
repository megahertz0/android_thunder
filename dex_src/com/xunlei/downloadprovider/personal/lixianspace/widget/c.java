package com.xunlei.downloadprovider.personal.lixianspace.widget;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.common.lixian.XLLX_TASKDETAIL;

// compiled from: LixianSpaceListWidget.java
final class c implements OnClickListener {
    final /* synthetic */ XLLX_TASKDETAIL a;
    final /* synthetic */ LixianSpaceListWidget b;

    c(LixianSpaceListWidget lixianSpaceListWidget, XLLX_TASKDETAIL xllx_taskdetail) {
        this.b = lixianSpaceListWidget;
        this.a = xllx_taskdetail;
    }

    public final void onClick(View view) {
        LixianSpaceListWidget.A(this.b).add(Long.valueOf(this.a.taskid));
        if (LixianSpaceListWidget.o(this.b) != null) {
            LixianSpaceListWidget.o(this.b).a();
        }
        if (LixianSpaceListWidget.B(this.b) != null) {
            LixianSpaceListWidget.B(this.b).dismiss();
        }
    }
}
