package com.uc.addon.sdk.remote;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.webkit.ValueCallback;
import com.uc.addon.sdk.remote.protocol.BaseArg;
import com.uc.addon.sdk.remote.protocol.CommandConstant;
import com.uc.addon.sdk.remote.protocol.DownloadSimpleArg;
import com.uc.addon.sdk.remote.protocol.DownloadTaskStatusChangeListener;
import com.uc.addon.sdk.remote.protocol.IApp;
import com.uc.addon.sdk.remote.protocol.IValueCallback.Stub;

public class DownloadImpl extends RequestSender implements Download {
    private Handler a;

    class AnonymousClass_1 extends Stub {
        final /* synthetic */ ValueCallback a;

        class AnonymousClass_1 implements Runnable {
            private /* synthetic */ DownloadTaskArg a;

            AnonymousClass_1(DownloadTaskArg downloadTaskArg) {
                this.a = downloadTaskArg;
            }

            public void run() {
                AnonymousClass_1.this.a.onReceiveValue(this.a.checkArgs() ? this.a.downloadTask : null);
            }
        }

        AnonymousClass_1(ValueCallback valueCallback) {
            this.a = valueCallback;
        }

        public void onReceiveValue(Bundle bundle) throws RemoteException {
            DownloadTaskArg downloadTaskArg = new DownloadTaskArg();
            downloadTaskArg.fromBundle(bundle);
            DownloadImpl.this.a.post(new AnonymousClass_1(downloadTaskArg));
        }
    }

    public DownloadImpl(IApp iApp) {
        super(iApp);
        this.a = new Handler(Looper.getMainLooper());
    }

    public void addTask(DownloadTaskRequest downloadTaskRequest) {
        if (downloadTaskRequest != null && downloadTaskRequest.url != null) {
            DownloadTask downloadTask = new DownloadTask();
            downloadTask.url = downloadTaskRequest.url;
            downloadTask.filePath = downloadTaskRequest.filePath;
            downloadTask.ask = downloadTaskRequest.ask;
            downloadTask.fileName = downloadTaskRequest.fileName;
            downloadTask.title = downloadTaskRequest.title;
            downloadTask.type = downloadTaskRequest.type;
            downloadTask.a = downloadTaskRequest.a;
            downloadTask.b = downloadTaskRequest.b;
            BaseArg downloadTaskArg = new DownloadTaskArg();
            downloadTaskArg.downloadTask = downloadTask;
            a(CommandConstant.COMMAND_ADD_DOWNLOAD_TASK, downloadTaskArg, null);
        }
    }

    public void cancleTask(int i) {
        BaseArg downloadSimpleArg = new DownloadSimpleArg();
        downloadSimpleArg.taskID = i;
        a(CommandConstant.COMMAND_CANCLE_DOWNLOAD_TASK, downloadSimpleArg, null);
    }

    public void pauseTask(int i) {
        BaseArg downloadSimpleArg = new DownloadSimpleArg();
        downloadSimpleArg.taskID = i;
        a(CommandConstant.COMMAND_PAUSE_DOWNLOAD_TASK, downloadSimpleArg, null);
    }

    public void queryTask(int i, ValueCallback valueCallback) {
        if (valueCallback != null) {
            BaseArg downloadSimpleArg = new DownloadSimpleArg();
            downloadSimpleArg.taskID = i;
            a(CommandConstant.COMMAND_QUERY_DOWNLOAD_TASK, downloadSimpleArg, new AnonymousClass_1(valueCallback));
        }
    }

    public void registerStatusChangeListener(int i, DownloadTaskStatusChangeListener downloadTaskStatusChangeListener) {
        if (downloadTaskStatusChangeListener != null) {
            DownloadTask downloadTask = new DownloadTask();
            downloadTask.taskID = i;
            downloadTask.url = "null";
            downloadTask.setDownloadTaskStatusChangeListener(downloadTaskStatusChangeListener);
            BaseArg downloadTaskArg = new DownloadTaskArg();
            downloadTaskArg.downloadTask = downloadTask;
            a(CommandConstant.COMMAND_REGIST_DOWNLOAD_STATUS_LISTENER, downloadTaskArg, null);
        }
    }

    public void restartTask(int i) {
        BaseArg downloadSimpleArg = new DownloadSimpleArg();
        downloadSimpleArg.taskID = i;
        a(CommandConstant.COMMAND_RESTART_DOWNLOAD_TASK, downloadSimpleArg, null);
    }

    public void startTask(int i) {
        BaseArg downloadSimpleArg = new DownloadSimpleArg();
        downloadSimpleArg.taskID = i;
        a(CommandConstant.COMMAND_START_DOWNLOAD_TASK, downloadSimpleArg, null);
    }
}
