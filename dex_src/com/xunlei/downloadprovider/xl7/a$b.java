package com.xunlei.downloadprovider.xl7;

import com.xunlei.downloadprovider.b.c.f;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: ReportMyPhoneToQRHelper.java
private class a$b extends f {
    final /* synthetic */ a a;

    private a$b(a aVar) {
        this.a = aVar;
    }

    public final Object parseJson(JSONObject jSONObject) throws JSONException {
        Object com_xunlei_downloadprovider_xl7_a_a = new a$a(this.a);
        if (jSONObject == null) {
            return null;
        }
        try {
            com_xunlei_downloadprovider_xl7_a_a.a = jSONObject.getInt("rtn");
            return com_xunlei_downloadprovider_xl7_a_a;
        } catch (Exception e) {
            return com_xunlei_downloadprovider_xl7_a_a;
        }
    }
}
