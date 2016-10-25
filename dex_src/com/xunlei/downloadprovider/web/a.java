package com.xunlei.downloadprovider.web;

import android.os.Message;
import com.xunlei.downloadprovider.service.downloads.TaskInfo;
import com.xunlei.downloadprovider.vod.VodUtil;
import com.xunlei.downloadprovider.vod.ap;
import com.xunlei.downloadprovider.web.BrowserUtil.StartFromType;
import com.xunlei.tdlive.R;
import java.util.List;

// compiled from: CopyrightIntermediatePageActivity.java
final class a implements com.xunlei.downloadprovider.a.h.a {
    final /* synthetic */ CopyrightIntermediatePageActivity a;

    a(CopyrightIntermediatePageActivity copyrightIntermediatePageActivity) {
        this.a = copyrightIntermediatePageActivity;
    }

    public final void a(Message message) {
        switch (message.what) {
            case R.styleable.AppCompatTheme_buttonStyle:
                TaskInfo taskInfo = (TaskInfo) message.obj;
                this.a.k.a(taskInfo.mFileName, taskInfo.mTaskId);
                if (this.a.isBatch()) {
                    this.a.updateBatchDialog(true, message.arg1, taskInfo.mTaskId, taskInfo.mFileName);
                }
            case R.styleable.AppCompatTheme_buttonStyleSmall:
                TaskInfo taskInfo2 = (TaskInfo) message.obj;
                if (this.a.isBatch()) {
                    this.a.updateBatchDialog(false, message.arg1, taskInfo2.mTaskId, taskInfo2.mFileName);
                } else {
                    this.a.handleTaskOperator(message.what, message.arg1, taskInfo2.mTaskId, taskInfo2);
                }
            case 901:
                if (message.obj instanceof ap) {
                    ap apVar = (ap) message.obj;
                    if (apVar.f.equals("player") || !apVar.f.equals("browser")) {
                        VodUtil.a();
                        VodUtil.b(this.a, apVar);
                    }
                } else {
                    this.a.a();
                }
                this.a.g.b();
            case 902:
                if (message.obj instanceof List) {
                    this.a.createTasks((List) message.obj, this.a.r, R.styleable.AppCompatTheme_actionModeStyle, StartFromType.unknow);
                } else {
                    this.a.a();
                }
                this.a.g.b();
            default:
                break;
        }
    }
}
