package com.xunlei.downloadprovider.loading.a;

import android.util.Base64;
import com.xunlei.downloadprovider.util.r;
import com.xunlei.downloadprovider.util.r.a;
import com.xunlei.thundersniffer.sniff.sniffer.SnifferProtocol.SetKey;
import com.xunlei.thundersniffer.sniff.sniffer.internal.server.SvrProtocol.SHubBatchQueryKeys;
import java.util.ArrayList;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: Parser.java
public final class d {
    public static b a(String str) {
        int i = MqttConnectOptions.MQTT_VERSION_3_1;
        b bVar = new b();
        try {
            JSONObject jSONObject = new JSONObject(str);
        } catch (JSONException e) {
            jSONObject = null;
        }
        if (jSONObject == null) {
            return null;
        }
        String optString = jSONObject.optString("ret", "-1");
        if (optString.equals("0")) {
            bVar.i = true;
            optString = jSONObject.optString("resp_list");
            if (optString != null) {
                try {
                    jSONObject = new JSONObject(optString);
                } catch (JSONException e2) {
                    jSONObject = null;
                }
                if (jSONObject != null) {
                    JSONArray optJSONArray = jSONObject.optJSONArray("ads");
                    if (optJSONArray == null || optJSONArray.length() == 0) {
                        return null;
                    }
                    JSONObject optJSONObject = optJSONArray.optJSONObject(0);
                    String optString2 = optJSONObject.optString("pubContent");
                    if (optString2 != null) {
                        try {
                            jSONObject = new JSONObject(new String(Base64.decode(optString2, 0)));
                            optString = jSONObject.optString(SetKey.TITLE);
                            String optString3 = jSONObject.optString("landingURL");
                            String optString4 = jSONObject.optString("cta");
                            bVar.b = optString3;
                            bVar.c = optString4;
                            bVar.a = optString;
                            jSONObject = jSONObject.optJSONObject("screenshots");
                            if (jSONObject != null) {
                                bVar.d = jSONObject.optString(SHubBatchQueryKeys.url);
                            }
                        } catch (JSONException e3) {
                        }
                    }
                    optJSONObject = optJSONObject.optJSONObject("eventTracking");
                    if (optJSONObject != null) {
                        jSONObject = optJSONObject.optJSONObject("120");
                        if (jSONObject != null) {
                            bVar.e = a(jSONObject);
                        }
                        jSONObject = optJSONObject.optJSONObject("18");
                        if (jSONObject != null) {
                            bVar.f = a(jSONObject);
                        }
                        optJSONObject = optJSONObject.optJSONObject("8");
                        if (optJSONObject != null) {
                            bVar.g = a(optJSONObject);
                        }
                    }
                }
            }
            bVar.h = 3;
            a aVar = r.c().e;
            if (aVar != null) {
                a.a a = aVar.a();
                if (a.a >= 3) {
                    i = a.a;
                }
                bVar.h = i;
            }
            return bVar;
        }
        bVar.j = Integer.valueOf(optString).intValue();
        bVar.i = false;
        return bVar;
    }

    private static ArrayList<String> a(JSONObject jSONObject) {
        ArrayList<String> arrayList = new ArrayList();
        if (jSONObject != null) {
            JSONArray optJSONArray = jSONObject.optJSONArray("urls");
            if (!(optJSONArray == null || optJSONArray.length() == 0)) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    arrayList.add(optJSONArray.optString(i));
                }
                return arrayList;
            }
        }
        return null;
    }
}
