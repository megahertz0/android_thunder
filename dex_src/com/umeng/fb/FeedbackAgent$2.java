package com.umeng.fb;

import com.umeng.fb.model.Reply;
import java.util.List;

class FeedbackAgent$2 implements SyncListener {
    final /* synthetic */ FeedbackAgent a;

    FeedbackAgent$2(FeedbackAgent feedbackAgent) {
        this.a = feedbackAgent;
    }

    public void onSendUserReply(List<Reply> list) {
    }

    public void onReceiveDevReply(List<Reply> list) {
        if (list != null && list.size() > 0) {
            this.a.showReplyNotification(list);
        }
    }
}
