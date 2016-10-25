package com.xunlei.downloadprovider.web.base.core;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import org.apache.commons.logging.impl.SimpleLog;
import org.json.JSONException;
import org.json.JSONObject;

private class BaseJsInterface$a extends Handler {
    final /* synthetic */ BaseJsInterface a;

    public BaseJsInterface$a(BaseJsInterface baseJsInterface, Looper looper) {
        this.a = baseJsInterface;
        super(looper);
    }

    public final void handleMessage(Message message) {
        switch (message.what) {
            case SimpleLog.LOG_LEVEL_TRACE:
                Bundle peekData = message.peekData();
                if (peekData != null) {
                    String string = peekData.getString("MethodName");
                    String string2 = peekData.getString("Params");
                    String string3 = peekData.getString("Callback");
                    MethodName method = MethodName.getMethod(string);
                    if (method == null) {
                        new StringBuilder("methodName : ").append(string).append(" should declared in enum MethodName");
                        if (!TextUtils.isEmpty(string3)) {
                            this.a.callbackError(string3, 1, new StringBuilder("method ").append(string).append(" is not declared").toString());
                            return;
                        }
                        return;
                    }
                    try {
                        if (t.a(string2)) {
                            this.a.handleMessage(method, null, string3);
                            return;
                        }
                        this.a.handleMessage(method, new JSONObject(string2), string3);
                    } catch (JSONException e) {
                        if (!t.a(string3)) {
                            this.a.callbackError(string3, 1, "Params is Error");
                        }
                    }
                }
            default:
                break;
        }
    }
}
