package com.xunlei.downloadprovider.app;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import com.xunlei.downloadprovider.a.k;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.download.create.DownloadBtFileExplorerActivity;
import com.xunlei.downloadprovider.service.downloads.TaskInfo;
import com.xunlei.downloadprovider.util.XLAlarmDialogActivity;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.tdlive.R;

// compiled from: TaskHandler.java
public final class w extends Handler implements o {
    public o a;

    public w() {
        this.a = null;
    }

    public final void handleMessage(Message message) {
        TaskInfo taskInfo = (TaskInfo) message.obj;
        long j = taskInfo == null ? -1 : taskInfo.mTaskId;
        if (this.a == null || !this.a.handleTaskOperator(message.what, message.arg1, j, taskInfo)) {
            handleTaskOperator(message.what, message.arg1, j, taskInfo);
        }
    }

    public final boolean handleTaskOperator(int i, int i2, long j, TaskInfo taskInfo) {
        switch (i) {
            case R.styleable.AppCompatTheme_buttonStyle:
                if (taskInfo.mIsToastForTask) {
                    XLToast.b(BrothersApplication.a(), XLToastType.XLTOAST_TYPE_SUC, "\u521b\u5efa\u4e0b\u8f7d\u6210\u529f");
                }
                break;
            case R.styleable.AppCompatTheme_buttonStyleSmall:
                if (i2 == 102409) {
                    Intent intent = new Intent(BrothersApplication.a(), XLAlarmDialogActivity.class);
                    intent.putExtra(JsInterface.FUNPLAY_AD_TRPE, 100100);
                    intent.putExtra(DownloadBtFileExplorerActivity.EXTRA_KEY_NAME_TASK_ID, taskInfo.mTaskId);
                    intent.setFlags(268435456);
                    BrothersApplication.a().startActivity(intent);
                    return true;
                }
                String str;
                if (i2 == 13) {
                    if (k.d()) {
                        str = "\u521b\u5efa\u4efb\u52a1\u5931\u8d25,\u62d2\u7edd\u8bbf\u95ee!";
                    } else {
                        XLToast.a(BrothersApplication.a, XLToastType.XLTOAST_TYPE_ALARM, "USB\u5b58\u50a8\u6a21\u5f0f\u4e0b\uff0cSD\u5361\u6682\u4e0d\u53ef\u5199,\u8bf7\u5c1d\u8bd5\u91cd\u65b0\u63d2\u5165SD\u5361");
                        return true;
                    }
                } else if (i2 == 3173) {
                    return true;
                } else {
                    if (i2 == 102439 || i2 == 102448) {
                        XLToast.a(BrothersApplication.a, XLToastType.XLTOAST_TYPE_ALARM, "\u94fe\u63a5\u8f93\u5165\u4e0d\u5b8c\u6574\u6216\u4e0d\u51c6\u786e");
                        return true;
                    } else if (i2 == 102416) {
                        str = "\u521b\u5efa\u4efb\u52a1\u5931\u8d25,\u6587\u4ef6\u5df2\u7ecf\u5b58\u5728!";
                    } else {
                        str = "\u521b\u5efa\u4efb\u52a1\u5931\u8d25,\u4e0d\u53ef\u7528\u7684url!";
                    }
                }
                XLToast.a(BrothersApplication.a, XLToastType.XLTOAST_TYPE_ALARM, str);
                break;
            default:
                return false;
        }
        return true;
    }
}
