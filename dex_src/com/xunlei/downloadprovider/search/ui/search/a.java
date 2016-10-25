package com.xunlei.downloadprovider.search.ui.search;

import android.os.Handler;
import android.os.Message;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

// compiled from: SearchActivity.java
final class a extends Handler {
    final /* synthetic */ SearchActivity a;

    a(SearchActivity searchActivity) {
        this.a = searchActivity;
    }

    public final void handleMessage(Message message) {
        switch (message.what) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                SearchActivity.a(this.a);
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                if (this.a.getWindow() != null) {
                    this.a.getWindow().setSoftInputMode(R.styleable.AppCompatTheme_actionModeCutDrawable);
                }
            default:
                break;
        }
    }
}
