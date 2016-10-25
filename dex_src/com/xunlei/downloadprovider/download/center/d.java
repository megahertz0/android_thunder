package com.xunlei.downloadprovider.download.center;

import android.os.Message;
import com.xunlei.downloadprovider.a.h.a;
import com.xunlei.downloadprovider.service.downloads.TaskInfo;
import com.xunlei.tdlive.R;

// compiled from: DownloadCenterActivity.java
final class d implements a {
    final /* synthetic */ DownloadCenterActivity a;

    d(DownloadCenterActivity downloadCenterActivity) {
        this.a = downloadCenterActivity;
    }

    public final void a(Message message) {
        TaskInfo taskInfo;
        switch (message.what) {
            case R.styleable.AppCompatTheme_buttonStyle:
                taskInfo = (TaskInfo) message.obj;
                DownloadCenterActivity.d(this.a);
                if (taskInfo != null) {
                    com.xunlei.downloadprovider.service.a.a.a(this.a, taskInfo.getTaskDownloadUrl(), taskInfo.mTitle, true);
                    if (this.a.isBatch()) {
                        this.a.updateBatchDialog(true, message.arg1, taskInfo.mTaskId, taskInfo.mFileName);
                    } else {
                        DownloadCenterActivity.e(this.a);
                    }
                }
            case R.styleable.AppCompatTheme_buttonStyleSmall:
                DownloadCenterActivity.d(this.a);
                int i = message.arg1;
                taskInfo = (TaskInfo) message.obj;
                if (taskInfo != null) {
                    if (this.a.isBatch()) {
                        this.a.updateBatchDialog(false, message.arg1, taskInfo.mTaskId, taskInfo.mFileName);
                    } else {
                        this.a.handleTaskOperator(message.what, message.arg1, taskInfo.mTaskId, taskInfo);
                    }
                    com.xunlei.downloadprovider.service.a.a.a(this.a, taskInfo.getTaskDownloadUrl(), taskInfo.mTitle, false);
                }
            default:
                break;
        }
    }
}
