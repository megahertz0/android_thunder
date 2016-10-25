package com.xunlei.downloadprovider.web.record;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.model.b;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.xllib.b.g;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

// compiled from: FavorAndHistroyActivity.java
final class h implements OnClickListener {
    final /* synthetic */ FavorAndHistroyActivity a;

    h(FavorAndHistroyActivity favorAndHistroyActivity) {
        this.a = favorAndHistroyActivity;
    }

    public final void onClick(View view) {
        if (this.a.y != null) {
            this.a.y.dismiss();
        }
        if (this.a.f != null) {
            this.a.w.clearAnimation();
            this.a.w.startAnimation(FavorAndHistroyActivity.t(this.a));
            aa aaVar = new aa();
            ArrayList arrayList = (ArrayList) this.a.f.getLocal();
            aaVar.a = this.a.I;
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("userId", LoginHelper.a().j);
                jSONObject.put("collection", 1);
                jSONObject.put(JsInterface.FUNPLAY_AD_TRPE, "localtoyun");
                JSONArray jSONArray = new JSONArray();
                for (int i = 0; i < arrayList.size(); i++) {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("urlName", ((b) ((t) arrayList.get(i)).b).b);
                    jSONObject2.put("urlAddress", ((b) ((t) arrayList.get(i)).b).c);
                    jSONArray.put(jSONObject2);
                }
                jSONObject.put("urlAddressList", jSONArray);
                aaVar.c = 3;
                String a = g.a((jSONObject.toString() + "&C63xmnzM+").getBytes());
                aa.a(aa.a("http://quan.m.xunlei.com/cgi-bin/favorites", a), jSONObject.toString(), aaVar.f);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        StatReporter.reportFavorClickSyn(16005, "favortosever");
    }
}
