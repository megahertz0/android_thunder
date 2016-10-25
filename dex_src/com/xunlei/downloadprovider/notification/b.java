package com.xunlei.downloadprovider.notification;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.xunlei.common.yunbo.XLYunboMassage;
import com.xunlei.downloadprovider.service.downloads.TaskInfo;
import com.xunlei.xllib.R;
import java.util.List;

// compiled from: DownloadNotification.java
final class b extends Handler {
    final /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    public final void handleMessage(Message message) {
        try {
            switch (message.what) {
                case R.styleable.Toolbar_collapseContentDescription:
                    if (message.obj instanceof TaskInfo) {
                        this.a.a((TaskInfo) message.obj);
                    }
                case R.styleable.AppCompatTheme_buttonStyle:
                    new StringBuilder("ADD_TASK_SUCCESS mNewTasksNum=").append(a.f(this.a));
                    if (a.f(this.a) > 0) {
                        a.g(this.a);
                    }
                    if (message.obj instanceof TaskInfo) {
                        TaskInfo taskInfo = (TaskInfo) message.obj;
                        if (a.f(this.a) >= 0) {
                            a.h(this.a).add(taskInfo);
                            new StringBuilder("add task size=").append(a.h(this.a).size());
                        }
                        if (a.f(this.a) == 0) {
                            a.b(this.a, a.h(this.a));
                        } else if (a.f(this.a) == -1) {
                            a.a(this.a, taskInfo);
                        }
                    }
                case R.styleable.AppCompatTheme_buttonStyleSmall:
                    new StringBuilder("ADD_TASK_FAILED mNewTasksNum=").append(a.f(this.a));
                    if (a.f(this.a) > 0) {
                        a.g(this.a);
                    }
                case R.styleable.AppCompatTheme_ratingBarStyleSmall:
                    if (message.obj instanceof TaskInfo) {
                        this.a.a((TaskInfo) message.obj, false);
                        if (!this.a.g) {
                            new StringBuilder("msg.what = TaskInfo.TASK_STATE_CHANGED_NOTIFY refresh taskListSize=").append(a.a(this.a).b().size());
                            a.a(this.a, a.a(this.a).b());
                        }
                        this.a.g = false;
                        Bundle data = message.getData();
                        if (data != null && data.getBoolean("hasBeforeState")) {
                            a.a(this.a, (TaskInfo) message.obj, message.arg1);
                        }
                    }
                case XLYunboMassage.MSG_TASKBEGINEXEC:
                    a.b(this.a);
                    if (message.obj != null) {
                        try {
                            List list = (List) message.obj;
                            if (!this.a.g) {
                                new StringBuilder("msg.what = TaskInfo.UPDATE_ALL_RUNNING_TASK refresh taskListSize=").append(list.size());
                                a.a(this.a, list);
                            }
                            this.a.g = false;
                            return;
                        } catch (ClassCastException e) {
                            new StringBuilder("UPDATE_ALL_RUNNING_TASK error=").append(e.getMessage());
                        }
                    }
                    a.c(this.a);
                    a.d(this.a);
                    a.e(this.a);
                case XLYunboMassage.MSG_TASKCANCELED:
                    this.a.a((List) message.obj);
                case 1048561:
                    a.b(this.a, (TaskInfo) message.obj);
                default:
                    break;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
