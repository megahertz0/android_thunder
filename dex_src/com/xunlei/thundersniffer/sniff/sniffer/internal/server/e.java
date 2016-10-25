package com.xunlei.thundersniffer.sniff.sniffer.internal.server;

import android.text.TextUtils;
import com.android.volley.r.b;
import com.xunlei.thundersniffer.sniff.SniffingResource;
import com.xunlei.thundersniffer.sniff.sniffer.internal.server.SvrProtocol.SHubBatchQueryKeys;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

final class e implements b<JSONObject> {
    final /* synthetic */ HashMap a;
    final /* synthetic */ SHubBatchQuery b;

    e(SHubBatchQuery sHubBatchQuery, HashMap hashMap) {
        this.b = sHubBatchQuery;
        this.a = hashMap;
    }

    public final /* synthetic */ void onResponse(Object obj) {
        JSONObject jSONObject = (JSONObject) obj;
        if (jSONObject != null) {
            new StringBuilder("shub response: ").append(jSONObject.toString());
            JSONArray optJSONArray = jSONObject.optJSONArray(SHubBatchQueryKeys.res);
            if (optJSONArray != null) {
                int i = 0;
                while (i < optJSONArray.length()) {
                    try {
                        JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                        if (optJSONObject == null || !optJSONObject.has(SHubBatchQueryKeys.hash)) {
                            i++;
                        } else {
                            CharSequence optString = optJSONObject.optString(SHubBatchQueryKeys.hash);
                            SniffingResource sniffingResource = null;
                            if (!TextUtils.isEmpty(optString)) {
                                sniffingResource = (SniffingResource) this.a.get(optString);
                            }
                            if (sniffingResource != null) {
                                this.b.b.put(sniffingResource.downloadUrl, optJSONObject);
                                this.b.a(sniffingResource, optJSONObject);
                            }
                            i++;
                        }
                    } catch (Exception e) {
                    }
                }
            }
        }
        this.b.checkNext();
    }
}
