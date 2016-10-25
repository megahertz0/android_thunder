package com.xunlei.thundersniffer.sniff.sniffer.internal.server;

import com.xunlei.thundersniffer.sniff.SniffingResource;
import com.xunlei.thundersniffer.sniff.sniffer.internal.server.SvrCacheDatabase.QuerySVodInfoDelegate;

final class i extends QuerySVodInfoDelegate<SniffingResource> {
    final /* synthetic */ SVodBatchQuery a;

    i(SVodBatchQuery sVodBatchQuery) {
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
        } else if (sniffingResource.vodplay != -1) {
            this.a.b.put(sniffingResource.downloadUrl, Integer.valueOf(sniffingResource.vodplay));
            new StringBuilder("svr-get vod status:").append(sniffingResource.vodplay).append(" url=").append(sniffingResource.downloadUrl);
        }
    }
}
