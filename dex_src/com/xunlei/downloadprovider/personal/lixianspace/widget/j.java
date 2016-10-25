package com.xunlei.downloadprovider.personal.lixianspace.widget;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.common.lixian.XLLX_TASKDETAIL;
import com.xunlei.common.lixian.XLLixianTask;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;

// compiled from: LixianSpaceListWidget.java
final class j implements OnClickListener {
    final /* synthetic */ XLLixianTask a;
    final /* synthetic */ XLLX_TASKDETAIL b;
    final /* synthetic */ LixianSpaceListWidget c;

    j(LixianSpaceListWidget lixianSpaceListWidget, XLLixianTask xLLixianTask, XLLX_TASKDETAIL xllx_taskdetail) {
        this.c = lixianSpaceListWidget;
        this.a = xLLixianTask;
        this.b = xllx_taskdetail;
    }

    public final void onClick(View view) {
        if (this.a.isBtTask()) {
            LixianSpaceListWidget.a(this.c, this.a, this.b);
        } else if (this.b.filetype.getClassType() == 100) {
            LixianSpaceListWidget.b(this.c, this.a, this.b);
            StatReporter.reportOverallPlay("cloud_lixian", "tcloud");
        }
        if (LixianSpaceListWidget.B(this.c) != null) {
            LixianSpaceListWidget.B(this.c).dismiss();
        }
    }
}
