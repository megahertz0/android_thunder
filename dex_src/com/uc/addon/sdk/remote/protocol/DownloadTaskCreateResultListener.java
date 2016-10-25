package com.uc.addon.sdk.remote.protocol;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import com.uc.addon.sdk.remote.protocol.IDownloadTaskCreateResultListener.Stub;

public abstract class DownloadTaskCreateResultListener extends Stub {
    public static final int CREATE_TASK_FAIL_NO_STORAGE = 1;
    public static final int CREATE_TASK_FAIL_PARAMS_ERROR = 3;
    public static final int CREATE_TASK_FAIL_UNKNOWN_ERROR = 0;
    public static final int CREATE_TASK_FAIL_USER_CANCEL = 2;
    public static final int CREATE_TASK_SUCCESS = 4;
    private Handler a;

    class AnonymousClass_1 implements Runnable {
        private /* synthetic */ DownloadSimpleArg a;

        AnonymousClass_1(DownloadSimpleArg downloadSimpleArg) {
            this.a = downloadSimpleArg;
        }

        public void run() {
            DownloadTaskCreateResultListener.this.onCreateTaskCallback(this.a.result, this.a.taskID);
        }
    }

    public DownloadTaskCreateResultListener() {
        this.a = new Handler(Looper.getMainLooper());
    }

    public abstract void onCreateTaskCallback(int i, int i2);

    public void onCreateTaskCallbackRemote(Bundle bundle) throws RemoteException {
        DownloadSimpleArg downloadSimpleArg = new DownloadSimpleArg();
        downloadSimpleArg.fromBundle(bundle);
        this.a.post(new AnonymousClass_1(downloadSimpleArg));
    }
}
