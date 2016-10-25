package com.uc.addon.sdk.remote.protocol;

import android.os.Bundle;

public class DownloadSimpleArg implements BaseArg {
    public int result;
    public int taskID;

    public DownloadSimpleArg() {
        this.taskID = -1;
        this.result = -1;
    }

    public boolean checkArgs() {
        return this.result >= 0 || this.taskID >= 0;
    }

    public void fromBundle(Bundle bundle) {
        this.taskID = bundle.getInt("key_task_id");
        this.result = bundle.getInt("key_task_result");
    }

    public void toBundle(Bundle bundle) {
        bundle.putInt("key_task_id", this.taskID);
        bundle.putInt("key_task_result", this.result);
    }
}
