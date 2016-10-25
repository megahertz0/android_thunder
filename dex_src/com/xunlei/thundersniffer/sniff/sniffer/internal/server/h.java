package com.xunlei.thundersniffer.sniff.sniffer.internal.server;

import com.xunlei.thundersniffer.sniff.SniffingResource;
import com.xunlei.thundersniffer.sniff.sniffer.internal.server.SvrCacheDatabase.QuerySVodInfoDelegate;

final class h extends QuerySVodInfoDelegate<SniffingResource> {
    final /* synthetic */ SVodBatchQuery a;

    h(SVodBatchQuery sVodBatchQuery) {
        this.a = sVodBatchQuery;
    }

    public final /* synthetic */ String getUrl(Object obj) {
        return ((SniffingResource) obj).downloadUrl;
    }

    public final /* synthetic */ void onSVodInfoGet(Object obj, String str, int i) {
        SniffingResource sniffingResource = (SniffingResource) obj;
        if (i != -1 && sniffingResource.vodplay == -1) {
            sniffingResource.vodplay = i;
            new StringBuilder("db vod status:").append(i).append(" url=").append(str);
        }
    }
}
