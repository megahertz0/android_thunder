package com.umeng.message;

import android.content.Intent;
import com.umeng.common.UmLog;
import org.android.agoo.common.CallBack;

class PushAgent$4 implements CallBack {
    final /* synthetic */ PushAgent a;

    PushAgent$4(PushAgent pushAgent) {
        this.a = pushAgent;
    }

    public void onSuccess() {
        UmLog.i(PushAgent.a(), "bindAgoo-->onSuccess");
        Intent intent = new Intent();
        intent.setPackage(PushAgent.a(this.a).getPackageName());
        intent.setAction("com.umeng.message.enablecallback.action");
        intent.putExtra("status", true);
        PushAgent.a(this.a).startService(intent);
    }

    public void onFailure(String str, String str2) {
        UmLog.i(PushAgent.a(), new StringBuilder("bindAgoo-->onFailure-->s:").append(str).append(",s1:").append(str2).toString());
        Intent intent = new Intent();
        intent.setPackage(PushAgent.a(this.a).getPackageName());
        intent.setAction("com.umeng.message.enablecallback.action");
        intent.putExtra("status", false);
        intent.putExtra("s", str);
        intent.putExtra("s1", str2);
        PushAgent.a(this.a).startService(intent);
    }
}
