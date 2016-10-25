package com.xunlei.downloadprovider.ad.home.ui;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.ad.common.a;
import com.xunlei.downloadprovider.model.g;
import com.xunlei.downloadprovider.service.downloads.task.b;
import com.xunlei.downloadprovider.task.ThunderTask;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.HashMap;
import java.util.Map;
import org.android.agoo.common.AgooConstants;

// compiled from: VideoInfoViewHolder.java
final class u implements OnClickListener {
    final /* synthetic */ a a;
    final /* synthetic */ s b;

    u(s sVar, a aVar) {
        this.b = sVar;
        this.a = aVar;
    }

    public final void onClick(View view) {
        String s = this.a.s();
        Object j = this.a.j();
        Map hashMap = new HashMap();
        hashMap.put(AgooConstants.MESSAGE_ID, s);
        hashMap.put("ad_type", "shangwu");
        s = "material";
        if (j == null) {
            j = BuildConfig.VERSION_NAME;
        }
        hashMap.put(s, j);
        com.xunlei.downloadprovider.ad.home.a.a("adv_homeflow_bigvideo_detail_click", hashMap);
        this.a.onClick(s.e(this.b));
        if (this.a.e()) {
            String h = this.a.h();
            if (!TextUtils.isEmpty(h)) {
                b bVar = new b();
                bVar.c = this.a.n();
                bVar.e = this.a.m();
                bVar.d = true;
                g gVar = new g(2, h, null);
                gVar.d = com.xunlei.downloadprovider.service.a.e + this.a.s();
                ((ThunderTask) s.e(this.b).getContext()).createLocalTaskWithAdditionInfo(h, this.a.m(), 0, null, null, null, 0, gVar, null, false, bVar);
            }
        }
    }
}
