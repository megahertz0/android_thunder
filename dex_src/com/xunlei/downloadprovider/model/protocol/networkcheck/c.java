package com.xunlei.downloadprovider.model.protocol.networkcheck;

import android.os.Handler;
import android.os.Message;
import com.xunlei.downloadprovider.a.a;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.tdlive.sdk.IHost;
import org.android.agoo.message.MessageService;

// compiled from: IPAddressErrorActivity.java
final class c extends Handler {
    c() {
    }

    public final void handleMessage(Message message) {
        switch (message.what) {
            case IHost.HOST_NOFITY_PAGE_SELECTED:
                if (((String) message.obj).equals(MessageService.MSG_DB_READY_REPORT)) {
                    IPAddressErrorActivity.c = true;
                    IPAddressErrorActivity.b();
                    return;
                }
                IPAddressErrorActivity.c = false;
            case IHost.HOST_NOFITY_PAGE_DESELECTED:
                IPAddressErrorActivity.c = false;
                IPAddressErrorActivity.a;
            case JsInterface.MSG_JS_OPENWINDOW_WITH_DOWNLOADLISTEX:
                if (((String) message.obj).equals(MessageService.MSG_DB_READY_REPORT)) {
                    IPAddressErrorActivity.c = true;
                    IPAddressErrorActivity.b();
                } else {
                    a.a(IPAddressErrorActivity.b, IPAddressErrorActivity.class);
                }
                IPAddressErrorActivity.c = false;
                IPAddressErrorActivity.a;
            default:
                break;
        }
    }
}
