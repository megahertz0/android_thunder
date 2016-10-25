package com.xunlei.downloadprovider.service.downloads;

import com.xunlei.downloadprovider.service.downloads.task.info.TaskRunningInfo;
import java.io.Serializable;

public class TaskInfo extends TaskRunningInfo implements Serializable {
    public static final int STATE_DELETED = 17;
    public static final int STATE_FAILED = 16;
    public static final int STATE_PAUSED = 4;
    public static final int STATE_RUNNING = 2;
    public static final int STATE_SUCCESS = 8;
    public static final int STATE_WAITING = 1;
    private static final long serialVersionUID = 1;

    public TaskInfo(TaskInfo taskInfo) {
        deepCopyFrom(taskInfo);
    }

    public boolean equals(Object obj) {
        boolean equals = super.equals(obj);
        return (equals || (obj instanceof TaskInfo)) ? equals : obj.equals(this);
    }

    public int hashCode() {
        return (int) (this.mTaskId ^ (this.mTaskId >>> 32));
    }
}
