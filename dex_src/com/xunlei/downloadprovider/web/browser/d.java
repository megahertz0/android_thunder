package com.xunlei.downloadprovider.web.browser;

import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;
import com.xunlei.downloadprovider.a.h.a;
import com.xunlei.downloadprovider.service.downloads.TaskInfo;
import com.xunlei.tdlive.R;

// compiled from: BrowserActivity.java
final class d implements a {
    final /* synthetic */ BrowserActivity a;

    d(BrowserActivity browserActivity) {
        this.a = browserActivity;
    }

    public final void a(Message message) {
        Toast toast;
        switch (message.what) {
            case R.styleable.AppCompatTheme_buttonStyle:
                TaskInfo taskInfo = (TaskInfo) message.obj;
                String str = BrowserActivity.a;
                new StringBuilder("taskId = ").append(taskInfo.mTaskId);
                if (this.a.isBatch()) {
                    this.a.updateBatchDialog(true, message.arg1, taskInfo.mTaskId, taskInfo.mFileName);
                }
                this.a.s.a();
                toast = new Toast(this.a);
                toast.setView(LayoutInflater.from(this.a).inflate(2130968744, null));
                toast.setGravity(R.styleable.Toolbar_maxButtonHeight, 0, 0);
                toast.setDuration(0);
                toast.show();
                this.a.e();
            case R.styleable.AppCompatTheme_buttonStyleSmall:
                TaskInfo taskInfo2 = (TaskInfo) message.obj;
                String str2 = BrowserActivity.a;
                new StringBuilder("handle ADD_TASK_FAILED , url = ").append(taskInfo2.mUrl);
                if (this.a.isBatch()) {
                    this.a.updateBatchDialog(false, message.arg1, taskInfo2.mTaskId, taskInfo2.mFileName);
                } else if (message.arg1 == 102409) {
                    this.a.handleTaskOperator(message.what, message.arg1, taskInfo2.mTaskId, taskInfo2);
                } else {
                    toast = new Toast(this.a);
                    View inflate = LayoutInflater.from(this.a).inflate(2130968742, null);
                    toast.setGravity(R.styleable.Toolbar_maxButtonHeight, 0, 0);
                    toast.setView(inflate);
                    toast.setDuration(0);
                    toast.show();
                }
                this.a.s.a();
            default:
                break;
        }
    }
}
