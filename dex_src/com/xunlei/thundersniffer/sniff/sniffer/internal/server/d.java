package com.xunlei.thundersniffer.sniff.sniffer.internal.server;

import com.xunlei.thundersniffer.sniff.SniffingResource;
import com.xunlei.thundersniffer.sniff.sniffer.internal.server.SvrCacheDatabase.CacheQueryHandler;
import com.xunlei.thundersniffer.sniff.sniffer.internal.server.SvrProtocol.SHubBatchQueryKeys;
import java.util.HashSet;
import org.json.JSONException;
import org.json.JSONObject;

final class d extends CacheQueryHandler<SniffingResource> {
    final /* synthetic */ HashSet a;
    final /* synthetic */ SHubBatchQuery b;

    d(SHubBatchQuery sHubBatchQuery, HashSet hashSet) {
        this.b = sHubBatchQuery;
        this.a = hashSet;
    }

    public final /* synthetic */ String getPrimaryValueForItem(Object obj) {
        return ((SniffingResource) obj).downloadUrl;
    }

    public final /* synthetic */ void onCacheHitForItem(boolean z, Object obj, String str, int i, String str2) {
        JSONObject jSONObject;
        SniffingResource sniffingResource = (SniffingResource) obj;
        JSONObject jSONObject2 = null;
        if (z) {
            try {
                jSONObject = new JSONObject(str2);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            jSONObject = null;
        }
        jSONObject2 = jSONObject;
        if (z && sniffingResource.fileSize <= 0 && jSONObject2 != null && jSONObject2.has(SHubBatchQueryKeys.filesize)) {
            new StringBuilder("db shub: ").append(jSONObject2.toString());
            this.b.a(sniffingResource, jSONObject2);
            this.a.add(sniffingResource.downloadUrl);
        } else if (z) {
            this.a.add(sniffingResource.downloadUrl);
        }
    }
}
