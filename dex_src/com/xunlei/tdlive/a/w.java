package com.xunlei.tdlive.a;

import android.view.View;
import com.xunlei.tdlive.modal.JsonWrapper;
import com.xunlei.tdlive.protocol.XLLiveRequest.JsonCallBack;

// compiled from: RankListAdapter.java
class w implements JsonCallBack {
    final /* synthetic */ v a;

    w(v vVar) {
        this.a = vVar;
    }

    public void onResponse(int i, String str, JsonWrapper jsonWrapper) {
        boolean z = true;
        this.a.a.setEnabled(true);
        if (i == 0) {
            View view = this.a.a;
            if (this.a.a.isSelected()) {
                z = false;
            }
            view.setSelected(z);
        }
    }
}
