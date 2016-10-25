package com.xunlei.downloadprovider.web.base.core;

import android.os.Message;
import com.xunlei.downloadprovider.a.h.a;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.service.downloads.TaskInfo;
import com.xunlei.downloadprovider.task.ThunderTask;
import com.xunlei.xllib.R;

// compiled from: DefaultJsInterface.java
final class g implements a {
    final /* synthetic */ DefaultJsInterface a;

    g(DefaultJsInterface defaultJsInterface) {
        this.a = defaultJsInterface;
    }

    public final void a(Message message) {
        if (message.obj instanceof TaskInfo) {
            TaskInfo taskInfo = (TaskInfo) message.obj;
            switch (message.what) {
                case R.styleable.AppCompatTheme_buttonStyle:
                    XLToast.b(this.a.getContext(), XLToastType.XLTOAST_TYPE_SUC, "\u521b\u5efa\u4e0b\u8f7d\u6210\u529f");
                case R.styleable.AppCompatTheme_buttonStyleSmall:
                    if (this.a.getContext() instanceof ThunderTask) {
                        ((ThunderTask) this.a.getContext()).handleTaskOperator(message.what, message.arg1, taskInfo.mTaskId, taskInfo);
                    }
                default:
                    break;
            }
        }
    }
}
