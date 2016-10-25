package com.xunlei.downloadprovider.personal.lixianspace.widget;

import android.view.View;
import android.view.View.OnLongClickListener;
import com.xunlei.common.lixian.XLLX_TASKDETAIL;
import com.xunlei.common.lixian.XLLixianTask;
import com.xunlei.downloadprovider.R;

// compiled from: LixianSpaceListWidget.java
final class m implements OnLongClickListener {
    final /* synthetic */ XLLixianTask a;
    final /* synthetic */ XLLX_TASKDETAIL b;
    final /* synthetic */ a c;

    m(a aVar, XLLixianTask xLLixianTask, XLLX_TASKDETAIL xllx_taskdetail) {
        this.c = aVar;
        this.a = xLLixianTask;
        this.b = xllx_taskdetail;
    }

    public final boolean onLongClick(View view) {
        switch (view.getId()) {
            case R.id.cloud_list_item_layout:
                LixianSpaceListWidget.a(this.c.a, this.a, this.b.taskname, this.b.filesize);
                break;
        }
        return true;
    }
}
