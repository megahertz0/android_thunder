package com.xunlei.downloadprovider.bho;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import com.xunlei.downloadprovider.download.center.DownloadCenterActivity;
import com.xunlei.downloadprovider.download.create.DownloadBtFileExplorerActivity;
import com.xunlei.downloadprovider.service.downloads.TaskInfo;
import com.xunlei.downloadprovider.util.XLAlarmDialogActivity;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.tdlive.R;
import com.xunlei.thundersniffer.sniff.SniffingResourceGroup;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

// compiled from: ScanCodeResultActivity.java
final class a implements com.xunlei.downloadprovider.a.h.a {
    final /* synthetic */ ScanCodeResultActivity a;

    a(ScanCodeResultActivity scanCodeResultActivity) {
        this.a = scanCodeResultActivity;
    }

    public final void a(Message message) {
        long currentTimeMillis;
        TaskInfo taskInfo;
        switch (message.what) {
            case SniffingResourceGroup.PAGETYPE_NONE:
                currentTimeMillis = System.currentTimeMillis() - ScanCodeResultActivity.a(this.a);
                if (!ScanCodeResultActivity.b(this.a) || currentTimeMillis >= 1000) {
                    ScanCodeResultActivity.a(this.a, false);
                    ScanCodeResultActivity.c(this.a).removeMessages(XZBDevice.DOWNLOAD_LIST_RECYCLE);
                    ScanCodeResultActivity.a(this.a, 0);
                    this.a.runOnUiThread(new b(this));
                    return;
                }
                ScanCodeResultActivity.c(this.a).sendEmptyMessageDelayed(1, currentTimeMillis);
            case SpdyAgent.ACCS_TEST_SERVER:
                currentTimeMillis = System.currentTimeMillis() - ScanCodeResultActivity.a(this.a);
                if (ScanCodeResultActivity.b(this.a) && currentTimeMillis < 1000) {
                    ScanCodeResultActivity.c(this.a).sendEmptyMessageDelayed(1, currentTimeMillis);
                } else if (message.arg1 != 0) {
                    new StringBuilder("msg arg1 =").append(message.arg1);
                    ScanCodeResultActivity.a(this.a, false);
                    ScanCodeResultActivity.c(this.a).removeMessages(XZBDevice.DOWNLOAD_LIST_RECYCLE);
                    ScanCodeResultActivity.a(this.a, 0);
                    ScanCodeResultActivity.d(this.a);
                }
            case SpdyAgent.ACCS_ONLINE_SERVER:
                currentTimeMillis = System.currentTimeMillis() - ScanCodeResultActivity.a(this.a);
                if (!ScanCodeResultActivity.b(this.a) || currentTimeMillis >= 1000) {
                    ScanCodeResultActivity.c(this.a).removeMessages(XZBDevice.DOWNLOAD_LIST_RECYCLE);
                    ScanCodeResultActivity.a(this.a, false);
                    ScanCodeResultActivity.a(this.a, 0);
                    ScanCodeResultActivity.a(this.a, message.getData().getString("fileurl"));
                    ScanCodeResultActivity.f(this.a);
                    this.a.runOnUiThread(new c(this));
                    return;
                }
                ScanCodeResultActivity.a(this.a, message.getData().getString("fileurl"));
                Bundle bundle = new Bundle();
                bundle.putString("fileurl", ScanCodeResultActivity.e(this.a));
                Message message2 = new Message();
                message2.what = 1;
                message2.setData(bundle);
                ScanCodeResultActivity.c(this.a).sendMessageDelayed(message2, currentTimeMillis);
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                ScanCodeResultActivity.a(this.a, true);
                ScanCodeResultActivity.a(this.a, System.currentTimeMillis());
                this.a.runOnUiThread(new d(this));
            case R.styleable.AppCompatTheme_buttonStyle:
                taskInfo = (TaskInfo) message.obj;
                if (taskInfo != null) {
                    DownloadCenterActivity.a(this.a, taskInfo.mTaskId, com.umeng.a.d);
                }
            case R.styleable.AppCompatTheme_buttonStyleSmall:
                if (message.arg1 == 102409) {
                    taskInfo = (TaskInfo) message.obj;
                    if (taskInfo != null) {
                        Intent intent = new Intent(this.a, XLAlarmDialogActivity.class);
                        intent.putExtra(JsInterface.FUNPLAY_AD_TRPE, 100100);
                        intent.putExtra(DownloadBtFileExplorerActivity.EXTRA_KEY_NAME_TASK_ID, taskInfo.mTaskId);
                        intent.setFlags(268435456);
                        this.a.startActivity(intent);
                        return;
                    }
                    return;
                }
                DownloadCenterActivity.a(this.a, -1, com.umeng.a.d);
            default:
                break;
        }
    }
}
