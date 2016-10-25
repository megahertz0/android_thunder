package com.xunlei.tdlive.base;

import com.tencent.open.yyb.AppbarJsBridge;
import com.uc.addon.sdk.remote.Tabs;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.base.BaseActivity.OnCheckUpdateStateChangeListener;
import com.xunlei.tdlive.c.a.a;
import com.xunlei.tdlive.c.a.b;
import com.xunlei.thundersniffer.sniff.SniffingResourceGroup;
import org.android.spdy.SpdyAgent;

// compiled from: BaseActivity.java
class g extends a {
    final /* synthetic */ OnCheckUpdateStateChangeListener a;
    final /* synthetic */ BaseActivity b;

    g(BaseActivity baseActivity, OnCheckUpdateStateChangeListener onCheckUpdateStateChangeListener) {
        this.b = baseActivity;
        this.a = onCheckUpdateStateChangeListener;
    }

    public void a(int i, b bVar) {
        switch (i) {
            case AppbarJsBridge.Code_Java_Exception:
                if (this.a != null) {
                    this.a.a(i, this.b.getString(R.string.timeout));
                }
                break;
            case Tabs.TAB_CREATE_REACH_MAX_COUNT:
                if (this.a != null) {
                    this.a.a(i, this.b.getString(R.string.none_wifi_for_update));
                }
                break;
            case SniffingResourceGroup.PAGETYPE_NONE:
                if (this.a != null) {
                    this.a.a(i, this.b.getString(R.string.none_update));
                }
                break;
            case SpdyAgent.ACCS_TEST_SERVER:
                if (this.a != null) {
                    this.a.a(i, this.b.getString(R.string.discover_update_version));
                }
                com.xunlei.tdlive.c.a.a(this.b, bVar);
                break;
        }
        com.xunlei.tdlive.c.a.a(true);
    }
}
