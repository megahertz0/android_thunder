package com.xunlei.thundersniffer.sniff.sniffer.internal.server;

import com.xunlei.thundersniffer.sniff.sniffer.internal.server.SvrCacheDatabase.CacheSaveHandler;
import com.xunlei.thundersniffer.sniff.sniffer.internal.server.SvrProtocol.SHubBatchQueryKeys;
import org.json.JSONObject;

final class c extends CacheSaveHandler<JSONObject> {
    final /* synthetic */ SHubBatchQuery a;

    c(SHubBatchQuery sHubBatchQuery) {
        this.a = sHubBatchQuery;
    }

    public final /* synthetic */ String getCacheDataForItem(Object obj, String str, String str2) {
        return ((JSONObject) obj).toString();
    }

    public final /* synthetic */ String getPrimaryValueForItem(Object obj) {
        return ((JSONObject) obj).optString(SHubBatchQueryKeys.url);
    }
}
