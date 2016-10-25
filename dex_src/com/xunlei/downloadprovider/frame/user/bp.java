package com.xunlei.downloadprovider.frame.user;

// compiled from: UserCenterTaskManager.java
final class bp implements bo$a {
    final /* synthetic */ ag a;
    final /* synthetic */ bo b;

    bp(bo boVar, ag agVar) {
        this.b = boVar;
        this.a = agVar;
    }

    public final void a(String str, String str2) {
        String str3 = this.a.c;
        if (str2.equals("successed_report")) {
            bo.b(str3);
        } else if (str2.equals("failed_report")) {
            bo.c();
            new StringBuilder("\u725b\u53c9\u4efb\u52a1\u4e0a\u62a5\u5931\u8d25\uff0c\u5931\u8d25\u7684\u539f\u56e0 --> ").append(str).append(", \u5931\u8d25\u7684\u6570\u636eNXTaskInfo --> ").append(this.a.toString());
            bo.a(str3, str2);
        }
    }
}
