package com.xunlei.downloadprovider.web.base;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.taobao.accs.utl.UtilityImpl;
import com.umeng.a;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.e;
import com.xunlei.downloadprovider.web.base.ShortMovieDetailActivity.From;

// compiled from: KandanListActivity.java
final class k implements OnItemClickListener {
    final /* synthetic */ KandanListActivity a;

    k(KandanListActivity kandanListActivity) {
        this.a = kandanListActivity;
    }

    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        Object item = adapterView.getAdapter().getItem(i);
        if (item instanceof s) {
            s sVar = (s) item;
            String str = UtilityImpl.NET_TYPE_UNKNOWN;
            if (r.a(sVar) == 0) {
                ShortMovieDetailActivity.a(this.a, sVar.d, sVar.e, From.KANDAN, sVar.g, sVar.h, sVar.i, a.d, sVar.o, sVar.k, sVar.p, -1);
                str = "video";
            } else if (r.a(sVar) == 1) {
                LongVideoDetailActivity.a(this.a, "kandan", String.format("http://m.sjzhushou.com/h5/page/video_long/index.html?from=kandan&id=%s&poster=%s&title=%s&_time_=%d", new Object[]{sVar.g, sVar.k, sVar.i, Long.valueOf(System.currentTimeMillis())}), sVar.i);
                str = "yingshi";
            }
            e.a(this.a.D, str, sVar.g, ParamKey.CONTENT);
        }
    }
}
