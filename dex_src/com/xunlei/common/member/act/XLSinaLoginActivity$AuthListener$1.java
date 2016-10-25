package com.xunlei.common.member.act;

import com.xunlei.common.member.act.XLSinaLoginActivity.AuthListener;

class XLSinaLoginActivity$AuthListener$1 implements Runnable {
    final /* synthetic */ AuthListener this$1;

    XLSinaLoginActivity$AuthListener$1(AuthListener authListener) {
        this.this$1 = authListener;
    }

    public void run() {
        XLSinaLoginActivity.access$100(this.this$1.this$0).authorizeWeb(new AuthListener(this.this$1.this$0, this.this$1.this$0));
    }
}
