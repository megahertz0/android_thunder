package com.umeng.message;

import anet.channel.AccsSessionManager.Callback;

class PushAgent$2 implements Callback {
    final /* synthetic */ PushAgent a;

    PushAgent$2(PushAgent pushAgent) {
        this.a = pushAgent;
    }

    public int getSessionCount() {
        return 1;
    }

    public String getSessionKey(int i) {
        return "https://umengacs.m.taobao.com";
    }
}
