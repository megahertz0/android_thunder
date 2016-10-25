package com.xunlei.downloadprovider.personal.redenvelope.redenvelopelist.ui;

import android.view.View;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.personal.redenvelope.redenvelopedetail.RedEnvelopesDetailActivity;
import com.xunlei.downloadprovider.personal.redenvelope.redenvelopelist.b.d;
import com.xunlei.downloadprovider.personal.redenvelope.redenvelopelist.ui.a.a;

// compiled from: RedEnvelopesActivity.java
final class c implements a {
    final /* synthetic */ RedEnvelopesActivity a;

    c(RedEnvelopesActivity redEnvelopesActivity) {
        this.a = redEnvelopesActivity;
    }

    public final void a(View view, int i) {
        XLToast.b(this.a.getApplicationContext(), XLToastType.XLTOAST_TYPE_SUC, new StringBuilder("position").append(i).toString());
        RedEnvelopesDetailActivity.a(this.a, ((d) view.getTag()).a);
    }
}
