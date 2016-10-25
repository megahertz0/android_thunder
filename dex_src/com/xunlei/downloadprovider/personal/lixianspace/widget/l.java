package com.xunlei.downloadprovider.personal.lixianspace.widget;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.common.lixian.XLLX_TASKDETAIL;
import com.xunlei.common.lixian.XLLixianTask;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.personal.lixianspace.LixianSpaceFragment$a;

// compiled from: LixianSpaceListWidget.java
final class l implements OnClickListener {
    final /* synthetic */ XLLixianTask a;
    final /* synthetic */ LixianSpaceFragment$a b;
    final /* synthetic */ XLLX_TASKDETAIL c;
    final /* synthetic */ int d;
    final /* synthetic */ a e;

    l(a aVar, XLLixianTask xLLixianTask, LixianSpaceFragment$a lixianSpaceFragment$a, XLLX_TASKDETAIL xllx_taskdetail, int i) {
        this.e = aVar;
        this.a = xLLixianTask;
        this.b = lixianSpaceFragment$a;
        this.c = xllx_taskdetail;
        this.d = i;
    }

    public final void onClick(View view) {
        switch (view.getId()) {
            case R.id.cloud_list_item_layout:
                if (this.e.a.e) {
                    if (LixianSpaceListWidget.A(this.e.a).contains(Long.valueOf(this.a.getTaskId()))) {
                        this.b.h.setImageResource(R.drawable.big_unselected);
                        LixianSpaceListWidget.A(this.e.a).remove(Long.valueOf(this.a.getTaskId()));
                    } else {
                        this.b.h.setImageResource(R.drawable.big_selected);
                        LixianSpaceListWidget.A(this.e.a).add(Long.valueOf(this.a.getTaskId()));
                    }
                    if (LixianSpaceListWidget.o(this.e.a) != null) {
                        LixianSpaceListWidget.o(this.e.a).b();
                        return;
                    }
                    return;
                }
                if (this.a.isBtTask()) {
                    LixianSpaceListWidget.a(this.e.a, this.a, this.c);
                    StatReporter.reportLixianListPlayOrOpenBT();
                } else if (this.c.filetype.getClassType() == 100) {
                    LixianSpaceListWidget.b(this.e.a, this.a, this.c);
                    StatReporter.reportOverallPlay("cloud_lixian", "tcloud");
                    StatReporter.reportLixianListPlayOrOpenBT();
                } else {
                    LixianSpaceListWidget.c(this.e.a, this.a, this.c);
                }
                LixianSpaceListWidget.l(this.e.a);
            case R.id.cloud_list_item_btn_right:
                if (!this.e.a.e) {
                    if (this.a.isBtTask()) {
                        LixianSpaceListWidget.a(this.e.a, this.a, this.c);
                        StatReporter.reportLixianListPlayOrOpenBT();
                    } else if (this.c.filetype.getClassType() == 100) {
                        LixianSpaceListWidget.b(this.e.a, this.a, this.c);
                        StatReporter.reportLixianListPlayOrOpenBT();
                        StatReporter.reportOverallPlay("cloud_lixian", "tcloud");
                    } else {
                        LixianSpaceListWidget.c(this.e.a, this.a, this.c);
                    }
                }
            default:
                break;
        }
    }
}
