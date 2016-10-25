package com.umeng.socialize.common;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.view.KeyEvent;
import com.umeng.socialize.Config;
import com.umeng.socialize.utils.SocializeUtils;

public abstract class QueuedWork$DialogThread<T> extends QueuedWork$UMAsyncTask {
    Dialog dialog;

    public QueuedWork$DialogThread(Context context) {
        this.dialog = null;
        if ((context instanceof Activity) && Config.dialogSwitch) {
            if (Config.dialog != null) {
                this.dialog = Config.dialog;
            } else {
                this.dialog = new ProgressDialog(context);
            }
            this.dialog.setOwnerActivity((Activity) context);
            this.dialog.setOnKeyListener(new OnKeyListener() {
                public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                    if (i == 4 && keyEvent.getRepeatCount() == 0) {
                        QueuedWork.removeInBack(QueuedWork$DialogThread.this.thread);
                    }
                    return false;
                }
            });
        }
    }

    protected void onPostExecute(Object obj) {
        super.onPostExecute(obj);
        SocializeUtils.safeCloseDialog(this.dialog);
    }

    protected void onPreExecute() {
        super.onPreExecute();
        SocializeUtils.safeShowDialog(this.dialog);
    }
}
