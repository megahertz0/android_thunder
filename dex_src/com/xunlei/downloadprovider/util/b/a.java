package com.xunlei.downloadprovider.util.b;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.webkit.JavascriptInterface;

// compiled from: CrackJSInterface.java
public final class a {
    public static final String NameSpace = "share";
    private Handler a;
    private f b;
    public boolean mCancel;
    public String mID;
    public Object mKeyData;
    public int mOperateType;
    public String mRefURL;
    public String mTAG;
    public String mTitle;

    public a(Handler handler) {
        this.a = null;
        this.b = null;
        this.mCancel = false;
        this.mOperateType = 0;
        this.mKeyData = null;
        this.mTitle = null;
        this.mRefURL = null;
        this.mTAG = null;
        this.mID = null;
        this.a = handler;
        this.mCancel = false;
    }

    @JavascriptInterface
    public final void addTasks(String str) {
        if (!this.mCancel && this.a != null) {
            this.a.removeMessages(9901, this.mKeyData);
            Message obtainMessage = this.a.obtainMessage();
            obtainMessage.what = 9902;
            Bundle bundle = new Bundle();
            bundle.putString("crack_key_json_data", str);
            obtainMessage.setData(bundle);
            obtainMessage.obj = this.mKeyData;
            obtainMessage.sendToTarget();
        }
    }

    @JavascriptInterface
    public final void multiPlay(String str) {
        if (!this.mCancel && this.a != null) {
            this.a.removeMessages(9901, this.mKeyData);
            Message obtainMessage = this.a.obtainMessage();
            obtainMessage.what = 9903;
            Bundle bundle = new Bundle();
            bundle.putString("crack_key_json_data", str);
            obtainMessage.setData(bundle);
            obtainMessage.obj = this.mKeyData;
            obtainMessage.sendToTarget();
        }
    }

    public final void cancelRequest() {
        this.mCancel = true;
    }

    public final void setOnCrackCallback(f fVar) {
        this.b = fVar;
    }

    public final f getOnCrackCallback() {
        return this.b;
    }
}
