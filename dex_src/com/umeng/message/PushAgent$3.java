package com.umeng.message;

import com.taobao.agoo.IRegister;
import com.umeng.common.UmLog;

class PushAgent$3 extends IRegister {
    final /* synthetic */ PushAgent a;

    PushAgent$3(PushAgent pushAgent) {
        this.a = pushAgent;
    }

    public void onSuccess(String str) {
        UmLog.i(PushAgent.a(), new StringBuilder("register-->onSuccess:").append(str).toString());
        PushAgent.a(this.a, str);
    }

    public void onFailure(String str, String str2) {
        UmLog.i(PushAgent.a(), new StringBuilder("register-->onFailure-->s:").append(str).append(",s1:").append(str2).toString());
        PushAgent.a(this.a, str, str2);
    }
}
