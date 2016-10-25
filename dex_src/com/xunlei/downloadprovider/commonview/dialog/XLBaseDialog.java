package com.xunlei.downloadprovider.commonview.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;

public class XLBaseDialog extends Dialog {
    protected Context mCtx;
    protected Object mTag;

    public XLBaseDialog(Context context, boolean z, OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
        this.mCtx = context;
    }

    public XLBaseDialog(Context context, int i) {
        super(context, i);
        this.mCtx = context;
    }

    public XLBaseDialog(Context context) {
        super(context);
        this.mCtx = context;
    }

    public void dismiss() {
        try {
            if (this.mCtx instanceof Activity) {
                if (!((Activity) this.mCtx).isFinishing()) {
                    super.dismiss();
                }
            } else if (isShowing()) {
                super.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void show() {
        try {
            Activity ownerActivity = getOwnerActivity();
            if (this.mCtx instanceof Activity) {
                ownerActivity = (Activity) this.mCtx;
            }
            if (ownerActivity == null) {
                super.show();
            } else if (!ownerActivity.isFinishing() && ownerActivity.getWindow() != null) {
                super.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setTag(Object obj) {
        this.mTag = obj;
    }

    public Object getTag() {
        return this.mTag;
    }
}
