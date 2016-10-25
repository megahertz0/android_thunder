package com.xunlei.downloadprovider.model.protocol.e;

import android.os.Handler;
import com.xunlei.downloadprovider.c.a;
import com.xunlei.downloadprovider.c.a.e;
import java.util.List;
import org.json.JSONObject;

// compiled from: ShortTimeVideoManager.java
final class b implements com.android.volley.r.b<JSONObject> {
    final /* synthetic */ Handler a;
    final /* synthetic */ int b;
    final /* synthetic */ a c;

    b(a aVar, Handler handler, int i) {
        this.c = aVar;
        this.a = handler;
        this.b = i;
    }

    public final /* synthetic */ void onResponse(Object obj) {
        JSONObject jSONObject = (JSONObject) obj;
        if (this.a != null) {
            a$b a = this.c.a(jSONObject);
            a.b = this.b;
            a aVar = this.c;
            List list = a.a;
            a$a cVar = new c(this, a);
            a aVar2 = new a();
            for (int i = 0; i < list.size(); i++) {
                a$c com_xunlei_downloadprovider_model_protocol_e_a_c = (a$c) list.get(i);
                aVar2.a(new e(com_xunlei_downloadprovider_model_protocol_e_a_c.b, com_xunlei_downloadprovider_model_protocol_e_a_c.a));
                aVar2.a(new e(aVar, com_xunlei_downloadprovider_model_protocol_e_a_c, i, list, cVar));
            }
        }
    }
}
