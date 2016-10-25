package com.xunlei.downloadprovider.personal.playrecord;

import android.os.Message;
import com.xunlei.downloadprovider.a.h.a;
import com.xunlei.downloadprovider.service.DownloadService;
import com.xunlei.downloadprovider.service.downloads.TaskInfo;
import com.xunlei.downloadprovider.task.ThunderTask;
import com.xunlei.xllib.R;

// compiled from: PlayRecordFragment.java
final class l implements a {
    final /* synthetic */ PlayRecordFragment a;

    l(PlayRecordFragment playRecordFragment) {
        this.a = playRecordFragment;
    }

    public final void a(Message message) {
        if (message.obj instanceof TaskInfo) {
            TaskInfo taskInfo = (TaskInfo) message.obj;
            switch (message.what) {
                case R.styleable.AppCompatTheme_buttonStyle:
                    ThunderTask thunderTask = null;
                    if (this.a.getActivity() instanceof ThunderTask) {
                        thunderTask = (ThunderTask) this.a.getActivity();
                    }
                    if (thunderTask != null && thunderTask.isBatch()) {
                        thunderTask.updateBatchDialog(true, message.arg1, taskInfo.mTaskId, taskInfo.mFileName);
                    }
                    if (PlayRecordFragment.i(this.a).remove(taskInfo.mUrl)) {
                        DownloadService.a();
                        DownloadService.i();
                    }
                case R.styleable.AppCompatTheme_buttonStyleSmall:
                    if ((this.a.getActivity() instanceof ThunderTask) && taskInfo != null) {
                        ((ThunderTask) this.a.getActivity()).handleTaskOperator(message.what, message.arg1, taskInfo.mTaskId, taskInfo);
                    }
                default:
                    break;
            }
        }
    }
}
