package com.xunlei.downloadprovider.download.tasklist.list.a;

import android.view.View;
import android.view.View.OnClickListener;
import com.qq.e.ads.nativ.NativeADDataRef;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.downloadprovider.ad.common.d.c;
import com.xunlei.downloadprovider.ad.common.d.d;
import com.xunlei.downloadprovider.download.tasklist.list.a.a.h;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.a;
import com.xunlei.xiazaibao.BuildConfig;
import com.xunlei.xllib.R;

// compiled from: TaskDownloadingAdCardViewHolder.java
final class ak implements OnClickListener {
    final /* synthetic */ NativeADDataRef a;
    final /* synthetic */ af b;

    ak(af afVar, NativeADDataRef nativeADDataRef) {
        this.b = afVar;
        this.a = nativeADDataRef;
    }

    public final void onClick(View view) {
        this.a.onClicked(af.d(this.b));
        a.b(h.a(af.a(this.b).a()), SocializeProtocolConstants.PROTOCOL_KEY_TENCENT, SocializeProtocolConstants.PROTOCOL_KEY_TENCENT, this.b.d(), BuildConfig.VERSION_NAME);
        com.xunlei.downloadprovider.ad.common.d.a.a aVar = new com.xunlei.downloadprovider.ad.common.d.a.a();
        n.a();
        aVar.b = d.a(n.a(af.a(this.b).a()), R.styleable.AppCompatTheme_buttonStyle);
        n.a();
        aVar.c = n.a(af.a(this.b).a());
        aVar.a = 2;
        com.xunlei.downloadprovider.j.a.a().e().a(new c(aVar, new al(this), new am(this)));
    }
}
