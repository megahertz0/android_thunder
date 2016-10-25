package com.xunlei.common.member.act;

import com.xunlei.common.member.act.XLXmLoginActivity.AnonymousClass_1;

class XLXmLoginActivity$1$1 implements Runnable {
    final /* synthetic */ AnonymousClass_1 this$1;
    final /* synthetic */ String val$vBindUrl;

    XLXmLoginActivity$1$1(AnonymousClass_1 anonymousClass_1, String str) {
        this.this$1 = anonymousClass_1;
        this.val$vBindUrl = str;
    }

    public void run() {
        this.this$1.this$0.startBindXunleiAccount(this.val$vBindUrl);
    }
}
