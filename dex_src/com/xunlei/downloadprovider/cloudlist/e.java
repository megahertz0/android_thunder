package com.xunlei.downloadprovider.cloudlist;

import android.os.Message;
import com.xunlei.downloadprovider.a.h.a;
import com.xunlei.downloadprovider.service.DownloadService;
import com.xunlei.downloadprovider.service.downloads.TaskInfo;
import com.xunlei.tdlive.R;

// compiled from: CloudListBTFileActivity.java
final class e implements a {
    final /* synthetic */ CloudListBTFileActivity a;

    e(CloudListBTFileActivity cloudListBTFileActivity) {
        this.a = cloudListBTFileActivity;
    }

    public final void a(Message message) {
        if (message.obj instanceof TaskInfo) {
            TaskInfo taskInfo = (TaskInfo) message.obj;
            switch (message.what) {
                case R.styleable.AppCompatTheme_buttonStyle:
                    if (this.a.e != null && this.a.e.c() == 101) {
                        DownloadService.a();
                        DownloadService.i();
                    }
                case R.styleable.AppCompatTheme_buttonStyleSmall:
                    if (taskInfo != null) {
                        this.a.handleTaskOperator(message.what, message.arg1, taskInfo.mTaskId, taskInfo);
                    }
                default:
                    break;
            }
        }
    }
}
