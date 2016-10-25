package com.umeng.fb;

import com.umeng.fb.model.Reply;
import java.util.List;

public interface SyncListener {
    void onReceiveDevReply(List<Reply> list);

    void onSendUserReply(List<Reply> list);
}
