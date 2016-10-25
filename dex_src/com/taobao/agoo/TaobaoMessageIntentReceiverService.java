package com.taobao.agoo;

import android.content.Context;
import com.taobao.accs.client.a;
import com.taobao.accs.utl.ALog;
import org.android.agoo.message.MessageReceiverService;

public class TaobaoMessageIntentReceiverService extends MessageReceiverService {
    public String getIntentServiceClassName(Context context) {
        ALog.w("Taobao", new StringBuilder("getPackage Name=").append(context.getPackageName()).toString(), new Object[0]);
        return a.b(context.getPackageName());
    }
}
