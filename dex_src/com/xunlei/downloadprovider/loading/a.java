package com.xunlei.downloadprovider.loading;

import com.umeng.fb.SyncListener;
import com.umeng.fb.model.Reply;
import java.util.List;

// compiled from: LoadingActivity.java
final class a implements SyncListener {
    final /* synthetic */ LoadingActivity a;

    a(LoadingActivity loadingActivity) {
        this.a = loadingActivity;
    }

    public final void onSendUserReply(List<Reply> list) {
    }

    public final void onReceiveDevReply(List<Reply> list) {
        LoadingActivity.a(this.a, list);
    }
}
