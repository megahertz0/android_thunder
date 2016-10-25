package com.xunlei.downloadprovider.bho;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.bho.ThunderTaskBHOActivity.b;
import org.android.spdy.TnetStatusCode;

// compiled from: ThunderTaskBHOActivity.java
final class m implements OnClickListener {
    final /* synthetic */ b a;

    m(b bVar) {
        this.a = bVar;
    }

    public final void onClick(View view) {
        if (b.a(this.a) != null) {
            b.a(this.a).onClick(this.a, TnetStatusCode.EASY_REQ_STAGE_SEND_FAIL);
        }
    }
}
