package com.xunlei.tdlive;

import android.os.Handler;
import android.os.Message;
import com.xunlei.tdlive.sdk.XLLiveSDK;
import com.xunlei.tdlive.util.q;

// compiled from: SDKLiveListFragment.java
class ec extends Handler {
    final /* synthetic */ ea a;

    ec(ea eaVar) {
        this.a = eaVar;
    }

    public void handleMessage(Message message) {
        int i = 1;
        if (message.what == 1000) {
            if (ea.b(this.a) != null) {
                ea.a(this.a, "host_refresh");
                ea.b(this.a).setRefreshing(true);
            }
        } else if (message.what == 1001) {
            ea.a(this.a, true);
            this.a.a(ea.e(this.a));
            ea.b(this.a, false);
            try {
                if (!XLLiveSDK.getInstance(this.a.getActivity()).hasRedFlagOnXLLiveTab()) {
                    i = 0;
                }
                q.e("home_page_show").a("isred", i).b(new String[0]);
                q.e("zb_content_read").c("viewid");
                XLLiveSDK.getInstance(this.a.getActivity()).setHasRedFlagOnXLLiveTab(false);
            } catch (Throwable th) {
            }
        } else if (message.what == 1002) {
            ea.a(this.a, false);
            this.a.a();
        }
    }
}
