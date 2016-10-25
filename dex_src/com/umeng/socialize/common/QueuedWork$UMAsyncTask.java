package com.umeng.socialize.common;

import com.umeng.socialize.common.QueuedWork.UMAsyncTask;

public abstract class QueuedWork$UMAsyncTask<Result> {
    protected Runnable thread;

    public abstract Result doInBackground();

    protected void onPreExecute() {
    }

    protected void onPostExecute(Result result) {
    }

    public final UMAsyncTask<Result> execute() {
        this.thread = new Runnable() {

            class AnonymousClass_1 implements Runnable {
                final /* synthetic */ Object val$result;

                AnonymousClass_1(Object obj) {
                    this.val$result = obj;
                }

                public void run() {
                    QueuedWork$UMAsyncTask.this.onPostExecute(this.val$result);
                }
            }

            public void run() {
                QueuedWork.runInMain(new AnonymousClass_1(QueuedWork$UMAsyncTask.this.doInBackground()));
            }
        };
        QueuedWork.runInMain(new Runnable() {
            public void run() {
                QueuedWork$UMAsyncTask.this.onPreExecute();
            }
        });
        QueuedWork.runInBack(this.thread);
        return this;
    }

    public static void waitAsyncTask() {
    }
}
