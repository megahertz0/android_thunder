package com.umeng.message;

import android.content.Intent;
import com.umeng.common.UmLog;
import org.android.agoo.common.CallBack;

class PushAgent$5 implements CallBack {
    final /* synthetic */ PushAgent a;

    PushAgent$5(PushAgent pushAgent) {
        this.a = pushAgent;
    }

    public void onSuccess() {
        UmLog.i(PushAgent.a(), "unBindAgoo-->onSuccess");
        Intent intent = new Intent();
        intent.setPackage(PushAgent.a(this.a).getPackageName());
        intent.setAction("com.umeng.message.disablecallback.action");
        intent.putExtra("status", true);
        PushAgent.a(this.a).startService(intent);
    }

    public void onFailure(String str, String str2) {
        UmLog.i(PushAgent.a(), new StringBuilder("onFailure-->s:").append(str).append(",s1:").append(str2).toString());
        Intent intent = new Intent();
        intent.setPackage(PushAgent.a(this.a).getPackageName());
        intent.setAction("com.umeng.message.disablecallback.action");
        intent.putExtra("status", false);
        intent.putExtra("s", str);
        intent.putExtra("s1", str2);
        PushAgent.a(this.a).startService(intent);
    }
}
