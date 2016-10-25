package com.xunlei.downloadprovider.personal.lixianspace.widget;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.common.lixian.XLLX_TASKDETAIL;
import com.xunlei.common.lixian.XLLixianTask;

// compiled from: LixianSpaceListWidget.java
final class k implements OnClickListener {
    final /* synthetic */ XLLixianTask a;
    final /* synthetic */ XLLX_TASKDETAIL b;
    final /* synthetic */ LixianSpaceListWidget c;

    k(LixianSpaceListWidget lixianSpaceListWidget, XLLixianTask xLLixianTask, XLLX_TASKDETAIL xllx_taskdetail) {
        this.c = lixianSpaceListWidget;
        this.a = xLLixianTask;
        this.b = xllx_taskdetail;
    }

    public final void onClick(View view) {
        LixianSpaceListWidget.c(this.c, this.a, this.b);
        if (LixianSpaceListWidget.B(this.c) != null) {
            LixianSpaceListWidget.B(this.c).dismiss();
        }
    }
}
