package com.xunlei.downloadprovider.bho;

import android.content.Intent;
import android.os.Message;
import com.xunlei.downloadprovider.a.h.a;
import com.xunlei.downloadprovider.download.center.DownloadCenterActivity;
import com.xunlei.downloadprovider.download.create.DownloadBtFileExplorerActivity;
import com.xunlei.downloadprovider.service.downloads.TaskInfo;
import com.xunlei.downloadprovider.util.XLAlarmDialogActivity;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.tdlive.R;
import org.android.spdy.SpdyAgent;

// compiled from: ThunderTaskBHOActivity.java
final class k implements a {
    final /* synthetic */ ThunderTaskBHOActivity a;

    k(ThunderTaskBHOActivity thunderTaskBHOActivity) {
        this.a = thunderTaskBHOActivity;
    }

    public final void a(Message message) {
        TaskInfo taskInfo;
        switch (message.what) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                this.a.a();
            case R.styleable.AppCompatTheme_buttonStyle:
                taskInfo = (TaskInfo) message.obj;
                ThunderTaskBHOActivity.b(this.a);
                if (taskInfo != null) {
                    com.xunlei.downloadprovider.service.a.a.a(this.a, taskInfo.getTaskDownloadUrl(), taskInfo.mTitle, true);
                    DownloadCenterActivity.a(this.a, taskInfo.mTaskId, com.umeng.a.d);
                }
            case R.styleable.AppCompatTheme_buttonStyleSmall:
                ThunderTaskBHOActivity.b(this.a);
                taskInfo = (TaskInfo) message.obj;
                if (message.arg1 != 102409) {
                    DownloadCenterActivity.a(this.a, "BHO_SDK");
                } else if (taskInfo != null) {
                    Intent intent = new Intent(this.a, XLAlarmDialogActivity.class);
                    intent.putExtra(JsInterface.FUNPLAY_AD_TRPE, 100100);
                    intent.putExtra(DownloadBtFileExplorerActivity.EXTRA_KEY_NAME_TASK_ID, taskInfo.mTaskId);
                    intent.setFlags(268435456);
                    this.a.startActivity(intent);
                }
                if (taskInfo != null) {
                    com.xunlei.downloadprovider.service.a.a.a(this.a, taskInfo.getTaskDownloadUrl(), taskInfo.mTitle, false);
                }
            default:
                break;
        }
    }
}
