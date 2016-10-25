package com.xunlei.downloadprovider.ad.home.ui;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import com.umeng.socialize.media.WeiXinShareContent;
import com.xunlei.downloadprovider.ad.common.CommonConst.AD_TYPE;
import com.xunlei.downloadprovider.ad.common.a;
import com.xunlei.downloadprovider.ad.common.b;
import com.xunlei.downloadprovider.model.g;
import com.xunlei.downloadprovider.task.ThunderTask;

// compiled from: ADPlayVodItem.java
final class l implements OnClickListener {
    final /* synthetic */ a a;
    final /* synthetic */ i b;

    l(i iVar, a aVar) {
        this.b = iVar;
        this.a = aVar;
    }

    public final void onClick(View view) {
        if (this.a != null) {
            com.xunlei.downloadprovider.ad.home.a.a(b.a(this.a), this.a.o().getSourceName(), WeiXinShareContent.TYPE_VIDEO, "button", this.a.j());
            this.a.onClick(this.b);
            if (!this.a.e()) {
                return;
            }
            if (this.a.o() == AD_TYPE.SOURCE_SSP_DEFAULT_FLAG || this.a.o() == AD_TYPE.SOURCE_SSP_FLAG) {
                String h = this.a.h();
                if (!TextUtils.isEmpty(h)) {
                    i.b(this.b).b = false;
                    com.xunlei.downloadprovider.service.downloads.task.b bVar = new com.xunlei.downloadprovider.service.downloads.task.b();
                    bVar.c = this.a.n();
                    bVar.e = this.a.m();
                    bVar.d = true;
                    g gVar = new g(3, h, null);
                    gVar.d = com.xunlei.downloadprovider.service.a.e + this.a.s();
                    ((ThunderTask) this.b.getContext()).createLocalTaskWithAdditionInfo(h, this.a.m(), 0, null, null, null, 0, gVar, null, false, bVar);
                }
            }
        }
    }
}
