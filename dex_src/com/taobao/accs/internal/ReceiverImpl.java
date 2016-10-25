package com.taobao.accs.internal;

import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.text.TextUtils;
import com.taobao.accs.base.IBaseReceiver;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.UtilityImpl;
import com.taobao.accs.utl.a;

// compiled from: Taobao
public class ReceiverImpl implements IBaseReceiver {
    public void onReceive(Context context, Intent intent) {
        ALog.d("ReceiverImpl", "ReceiverImpl onReceive begin......", new Object[0]);
        if (intent == null || TextUtils.isEmpty(intent.getAction())) {
            intent = new Intent();
        }
        try {
            if (UtilityImpl.getServiceEnabled(context)) {
                intent.setClassName(context.getPackageName(), a.channelService);
                context.startService(intent);
            } else {
                Process.killProcess(Process.myPid());
            }
            if (UtilityImpl.getAgooServiceEnabled(context)) {
                intent.setClassName(context, com.taobao.accs.client.a.b(context.getPackageName()));
                context.startService(intent);
            }
        } catch (Throwable th) {
            ALog.e("ReceiverImpl", new StringBuilder("ReceiverImpl onReceive,exception,e=").append(th.getMessage()).toString(), new Object[0]);
        }
    }
}
