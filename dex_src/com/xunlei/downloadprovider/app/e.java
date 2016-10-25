package com.xunlei.downloadprovider.app;

import android.os.Message;
import com.xunlei.downloadprovider.a.h.a;
import com.xunlei.downloadprovider.service.downloads.task.d;
import com.xunlei.tdlive.R;
import com.xunlei.thundersniffer.sniff.SniffingResourceGroup;
import org.android.spdy.SpdyAgent;

// compiled from: BrothersApplication.java
final class e implements a {
    e() {
    }

    public final void a(Message message) {
        switch (message.what) {
            case SniffingResourceGroup.PAGETYPE_NONE:
                d.a();
                d.a(BrothersApplication.j, false);
            case SpdyAgent.ACCS_TEST_SERVER:
                if (BrothersApplication.f != null) {
                    BrothersApplication.f = null;
                }
                d.a();
                d.g();
            case R.styleable.AppCompatTheme_buttonBarNeutralButtonStyle:
                com.xunlei.downloadprovider.service.downloads.task.a aVar = (com.xunlei.downloadprovider.service.downloads.task.a) message.obj;
                if (aVar.a != null && aVar.a.size() > 0) {
                    BrothersApplication.f = aVar.a;
                }
            default:
                break;
        }
    }
}
