package com.xunlei.downloadprovider.search.ui.hotsite;

import android.os.Message;
import com.xunlei.downloadprovider.a.h.b;
import org.json.JSONObject;

// compiled from: SearchTabHotSiteView.java
final class c extends b {
    final /* synthetic */ SearchTabHotSiteView b;
    private JSONObject c;

    c(SearchTabHotSiteView searchTabHotSiteView) {
        this.b = searchTabHotSiteView;
    }

    public final void handleMessage(Message message) {
        new StringBuilder().append(getClass()).append("---handleMessage---").append(message);
        switch (message.what) {
            case 3001:
                new StringBuilder().append(getClass()).append("---handleMessage---HotSiteHelper.MSG_GET_HOTSITE_SUCCESS---");
                try {
                    this.c = (JSONObject) message.obj;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                new d(this).start();
            case 3002:
                this.b.d.setAdapter(SearchTabHotSiteView.h);
            case 3003:
                try {
                    if (SearchTabHotSiteView.h != null) {
                        SearchTabHotSiteView.h.notifyDataSetChanged();
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            default:
                break;
        }
    }
}
