package com.xunlei.downloadprovider.search.ui.hotsite;

import android.os.Message;
import com.xunlei.downloadprovider.model.b;
import com.xunlei.downloadprovider.model.i;
import com.xunlei.downloadprovider.search.ui.hotsite.SearchTabHotSiteView.a;
import com.xunlei.thundersniffer.sniff.sniffer.SnifferProtocol.SetKey;
import com.xunlei.thundersniffer.sniff.sniffer.internal.server.SvrProtocol.SHubBatchQueryKeys;
import org.json.JSONArray;
import org.json.JSONObject;

// compiled from: SearchTabHotSiteView.java
final class d extends Thread {
    final /* synthetic */ c a;

    d(c cVar) {
        this.a = cVar;
    }

    public final void run() {
        try {
            new StringBuilder().append(getClass()).append("---handleMessage---j---");
            new StringBuilder().append(getClass()).append("---handleMessage---j---").append(c.a(this.a).toString());
            JSONArray jSONArray = c.a(this.a).getJSONArray("list");
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                a aVar = new a(this.a.b);
                aVar.a = jSONObject.getString("order");
                aVar.b = jSONObject.getString(SetKey.TITLE);
                aVar.c = jSONObject.getString("count");
                aVar.d = jSONObject.getString(SHubBatchQueryKeys.url);
                new StringBuilder().append(getClass()).append("---handleMessage---mViewHolder.num---").append(aVar.a);
                new StringBuilder().append(getClass()).append("---handleMessage---mViewHolder.name---").append(aVar.b);
                new StringBuilder().append(getClass()).append("---handleMessage---mViewHolder.people---").append(aVar.c);
                new StringBuilder().append(getClass()).append("---handleMessage---mViewHolder.address---").append(aVar.d);
                b bVar = new b();
                bVar.b = aVar.b;
                bVar.c = aVar.d;
                bVar.d = 1;
                if (i.a().a(bVar, this.a.b.b)) {
                    aVar.e = 1;
                }
                SearchTabHotSiteView.a(this.a.b).add(aVar);
            }
            Message message = new Message();
            message.what = 3002;
            SearchTabHotSiteView.b(this.a.b).sendMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
