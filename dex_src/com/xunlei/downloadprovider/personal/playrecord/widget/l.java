package com.xunlei.downloadprovider.personal.playrecord.widget;

import android.text.TextUtils;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.util.b.f;
import com.xunlei.downloadprovider.vod.VodUtil;
import com.xunlei.downloadprovider.vod.ao;
import com.xunlei.downloadprovider.vod.ap;
import com.xunlei.downloadprovider.web.BrowserUtil.StartFromType;
import com.xunlei.downloadprovider.web.core.g;
import com.xunlei.xllib.a.b;
import com.xunlei.xllib.b.d;
import java.util.List;

// compiled from: PlayRecordListWidget.java
final class l extends f {
    final /* synthetic */ PlayRecordListWidget a;

    l(PlayRecordListWidget playRecordListWidget) {
        this.a = playRecordListWidget;
    }

    public final void a(int i, String str, String str2, String str3, String str4, String str5) {
        super.a(i, str, str2, str3, str4, str5);
        if (i == 0) {
            ap a = ao.a(str5);
            VodUtil.a();
            VodUtil.a(this.a.getContext(), a);
        }
        PlayRecordListWidget.g();
    }

    public final void a(int i, String str) {
        if (i != 0) {
            XLToast.a(this.a.getContext(), XLToastType.XLTOAST_TYPE_ALARM, "\u6682\u4e0d\u80fd\u4e0b\u8f7d");
        } else if (!TextUtils.isEmpty(str)) {
            List b = g.b(str);
            if (!(d.a(b) || PlayRecordListWidget.p(this.a) == null)) {
                PlayRecordListWidget.p(this.a).a(b, StartFromType.unknow);
            }
        }
        if (!b.a(this.a.getContext())) {
            XLToast.a(this.a.getContext(), XLToastType.XLTOAST_TYPE_ALARM, this.a.getContext().getString(R.string.qrcode_no_net));
        }
    }
}
