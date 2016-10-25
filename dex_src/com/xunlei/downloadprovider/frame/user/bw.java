package com.xunlei.downloadprovider.frame.user;

import android.os.Message;
import com.umeng.fb.model.Reply;
import com.umeng.fb.net.a;
import java.util.UUID;

// compiled from: UserFeedBackUmActivity.java
final class bw implements Runnable {
    final /* synthetic */ bv a;

    bw(bv bvVar) {
        this.a = bvVar;
    }

    public final void run() {
        try {
            Thread.sleep(2000);
            Reply reply = new Reply("\u857e\u59b9\u5728\u8d76\u6765\u7684\u8def\u4e0a~\u66f4\u591a\u5410\u69fd\u548c\u53cd\u9988\u53ef\u4ee5\u6dfb\u52a0QQ\u7fa4: 482925431", new StringBuilder("R").append(UUID.randomUUID().toString()).toString(), a.g, System.currentTimeMillis());
            reply.status = Reply.STATUS_SENT;
            UserFeedBackUmActivity.a(this.a.b).add(reply);
            UserFeedBackUmActivity.b(this.a.b).addReply(reply);
            UserFeedBackUmActivity.g(this.a.b).sendMessage(new Message());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
