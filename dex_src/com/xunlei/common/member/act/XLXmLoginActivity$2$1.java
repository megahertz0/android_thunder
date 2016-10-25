package com.xunlei.common.member.act;

import com.xunlei.common.member.act.XLXmLoginActivity.AnonymousClass_2;

class XLXmLoginActivity$2$1 implements Runnable {
    final /* synthetic */ AnonymousClass_2 this$1;

    XLXmLoginActivity$2$1(AnonymousClass_2 anonymousClass_2) {
        this.this$1 = anonymousClass_2;
    }

    public void run() {
        this.this$1.this$0.checkXiaoMiAccountBind(XLXmLoginActivity.access$400(this.this$1.this$0).getCode());
    }
}
