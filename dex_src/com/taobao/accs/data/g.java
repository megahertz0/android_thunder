package com.taobao.accs.data;

import android.content.Context;
import android.content.Intent;
import com.taobao.accs.common.Constants;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.BaseMonitor;
import com.taobao.accs.utl.b;
import com.taobao.accs.utl.h;
import com.umeng.a;

// compiled from: Taobao
class g implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ Context b;
    final /* synthetic */ Intent c;
    final /* synthetic */ e d;

    g(e eVar, String str, Context context, Intent intent) {
        this.d = eVar;
        this.a = str;
        this.b = context;
        this.c = intent;
    }

    public void run() {
        if (e.d() != null && e.d().contains(this.a)) {
            ALog.e("MsgDistribute", "routing msg time out, try election", Constants.KEY_DATA_ID, this.a);
            e.d().remove(this.a);
            e.a(this.d, this.b);
            b.a(h.NAMESPACE, BaseMonitor.ALARM_MSG_ROUTING_RATE, a.d, com.alipay.sdk.data.a.f, new StringBuilder("pkg:").append(this.c.getPackage()).toString());
        }
    }
}
