package com.xunlei.downloadprovider.frame.user;

import com.umeng.fb.SyncListener;
import com.umeng.fb.model.Reply;
import java.util.List;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: UserFeedBackUmActivity.java
final class bv implements SyncListener {
    final /* synthetic */ int a;
    final /* synthetic */ UserFeedBackUmActivity b;

    bv(UserFeedBackUmActivity userFeedBackUmActivity, int i) {
        this.b = userFeedBackUmActivity;
        this.a = i;
    }

    public final void onSendUserReply(List<Reply> list) {
        if (UserFeedBackUmActivity.b(this.b) != null && UserFeedBackUmActivity.b(this.b).getReplyList().size() == 1) {
            new Thread(new bw(this)).start();
        }
    }

    public final void onReceiveDevReply(List<Reply> list) {
        int i = 0;
        if (this.a == 2) {
            if (list != null && list.size() > 0) {
                UserFeedBackUmActivity.b(this.b, list.size());
                while (i < list.size()) {
                    UserFeedBackUmActivity.a(this.b).add(list.get(i));
                    i++;
                }
            }
            UserFeedBackUmActivity.g(this.b).sendEmptyMessage(SimpleLog.LOG_LEVEL_DEBUG);
        } else if (list == null || list.size() <= 0) {
            UserFeedBackUmActivity.g(this.b).sendEmptyMessage(this.a);
        } else {
            while (i < list.size()) {
                UserFeedBackUmActivity.a(this.b).add(list.get(i));
                i++;
            }
            UserFeedBackUmActivity.g(this.b).sendEmptyMessage(this.a);
        }
    }
}
