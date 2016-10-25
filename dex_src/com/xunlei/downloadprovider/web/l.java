package com.xunlei.downloadprovider.web;

import android.os.Message;
import com.xunlei.downloadprovider.a.h.a;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.service.downloads.TaskInfo;
import com.xunlei.tdlive.R;

// compiled from: DetailPageBrowserActivity.java
final class l implements a {
    final /* synthetic */ DetailPageBrowserActivity a;

    l(DetailPageBrowserActivity detailPageBrowserActivity) {
        this.a = detailPageBrowserActivity;
    }

    public final void a(Message message) {
        switch (message.what) {
            case R.styleable.AppCompatTheme_buttonStyle:
                DetailPageBrowserActivity.c;
                new StringBuilder("handle ADD_TASK_SUCCESS : time = ").append(System.currentTimeMillis());
                if (message.obj instanceof TaskInfo) {
                    TaskInfo taskInfo = (TaskInfo) message.obj;
                    if (!this.a.c()) {
                        this.a.h.a(taskInfo.mFileName, taskInfo.mTaskId);
                    }
                    if (this.a.isBatch()) {
                        this.a.updateBatchDialog(true, message.arg1, taskInfo.mTaskId, taskInfo.mFileName);
                    }
                    this.a.t = taskInfo;
                    DetailPageBrowserActivity.b(this.a, taskInfo.mTaskId);
                }
            case R.styleable.AppCompatTheme_buttonStyleSmall:
                TaskInfo taskInfo2 = (TaskInfo) message.obj;
                if (this.a.isBatch()) {
                    this.a.updateBatchDialog(false, message.arg1, taskInfo2.mTaskId, taskInfo2.mFileName);
                } else {
                    this.a.handleTaskOperator(message.what, message.arg1, taskInfo2.mTaskId, taskInfo2);
                }
            case 116:
                XLToast.a(this.a.getApplicationContext(), XLToastType.XLTOAST_TYPE_NORMAL, this.a.getString(2131232758));
            case 117:
                XLToast.a(this.a.getApplicationContext(), XLToastType.XLTOAST_TYPE_NORMAL, this.a.getString(2131232751));
            case 3104:
                this.a.d.b();
            default:
                break;
        }
    }
}
