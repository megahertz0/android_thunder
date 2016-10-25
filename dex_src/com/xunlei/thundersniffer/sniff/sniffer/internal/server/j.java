package com.xunlei.thundersniffer.sniff.sniffer.internal.server;

import android.text.TextUtils;
import com.android.volley.r.b;
import com.xunlei.thundersniffer.sniff.SniffingResource;
import com.xunlei.thundersniffer.sniff.sniffer.internal.server.SvrProtocol.SVodBatchQueryKeys;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

final class j implements b<JSONObject> {
    final /* synthetic */ ArrayList a;
    final /* synthetic */ SVodBatchQuery b;

    j(SVodBatchQuery sVodBatchQuery, ArrayList arrayList) {
        this.b = sVodBatchQuery;
        this.a = arrayList;
    }

    public final /* synthetic */ void onResponse(Object obj) {
        JSONObject jSONObject = (JSONObject) obj;
        if (jSONObject != null) {
            new StringBuilder("svod response: ").append(jSONObject.toString());
            JSONArray optJSONArray = jSONObject.optJSONArray(SVodBatchQueryKeys.trans_list);
            if (!(optJSONArray == null || this.a == null)) {
                int i = 0;
                while (i < optJSONArray.length()) {
                    try {
                        SniffingResource sniffingResource = (SniffingResource) this.a.get(i);
                        int optInt = optJSONArray.optInt(i, -1);
                        if (sniffingResource == null || TextUtils.isEmpty(sniffingResource.downloadUrl) || optInt == -1) {
                            i++;
                        } else {
                            sniffingResource.vodplay = optInt;
                            this.b.b.put(sniffingResource.downloadUrl, Integer.valueOf(optInt));
                            new StringBuilder("svr vod status: ").append(String.format("%d(%d)", new Object[]{Integer.valueOf(sniffingResource.vodplay), Integer.valueOf(optInt)})).append(" url=").append(sniffingResource.downloadUrl);
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
