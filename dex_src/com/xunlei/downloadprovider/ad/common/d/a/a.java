package com.xunlei.downloadprovider.ad.common.d.a;

import android.text.TextUtils;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xunlei.thundersniffer.sniff.sniffer.internal.server.SvrProtocol.SHubBatchQueryKeys;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: ParseUtil.java
public final class a {

    // compiled from: ParseUtil.java
    private static abstract class a {
        protected abstract f a(JSONObject jSONObject);

        private a() {
        }

        final f a(String str) {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            try {
                JSONObject jSONObject = new JSONObject(str);
            } catch (JSONException e) {
                jSONObject = null;
            }
            if (jSONObject == null) {
                return null;
            }
            int optInt = jSONObject.optInt(XiaomiOAuthConstants.EXTRA_CODE_2, 0);
            f fVar = new f(optInt);
            if (optInt != 0) {
                return fVar;
            }
            JSONArray optJSONArray = jSONObject.optJSONArray(SocializeProtocolConstants.PROTOCOL_KEY_DATA);
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    if (optJSONObject != null) {
                        JSONArray optJSONArray2 = optJSONObject.optJSONArray("materials");
                        if (optJSONArray2 != null && optJSONArray2.length() > 0) {
                            JSONObject optJSONObject2 = optJSONArray2.optJSONObject(0);
                            if (optJSONObject2 != null) {
                                f a = a(optJSONObject2);
                                if (a != null) {
                                    boolean z;
                                    if (optJSONObject2.optInt("open_type") == 2) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    a.e = z;
                                    a.l = optJSONObject2.optString("open_url");
                                    a.g = optJSONObject.optInt("orderId");
                                    a.h = optJSONObject.optInt("positionId");
                                    if (optJSONObject.optInt("default_ad", 0) == 1) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    a.i = z;
                                    fVar.b.add(a);
                                }
                            }
                        }
                    }
                }
            }
            return fVar;
        }
    }

    // compiled from: ParseUtil.java
    private static class b extends a {
        private b() {
            super();
        }

        protected final f a(JSONObject jSONObject) {
            f fVar = new f();
            if (jSONObject != null) {
                fVar.m = jSONObject.optInt("material_id");
                JSONArray optJSONArray = jSONObject.optJSONArray(SocializeProtocolConstants.PROTOCOL_KEY_DATA);
                if (optJSONArray != null && optJSONArray.length() >= 5) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(MqttConnectOptions.MQTT_VERSION_3_1_1);
                    if (optJSONObject != null) {
                        fVar.d = optJSONObject.optString(SHubBatchQueryKeys.url);
                    }
                    optJSONObject = optJSONArray.optJSONObject(1);
                    if (optJSONObject != null) {
                        fVar.b = optJSONObject.optString("value");
                    }
                    optJSONObject = optJSONArray.optJSONObject(SimpleLog.LOG_LEVEL_DEBUG);
                    if (optJSONObject != null) {
                        fVar.j = optJSONObject.optString(SHubBatchQueryKeys.url);
                    }
                    fVar.k = optJSONArray.optJSONObject(MqttConnectOptions.MQTT_VERSION_3_1).optString("value");
                    return fVar;
                }
            }
            return null;
        }
    }

    // compiled from: ParseUtil.java
    private static class c extends a {
        private c() {
            super();
        }

        protected final f a(JSONObject jSONObject) {
            f fVar = new f();
            if (jSONObject != null) {
                fVar.m = jSONObject.optInt("material_id");
                JSONArray optJSONArray = jSONObject.optJSONArray(SocializeProtocolConstants.PROTOCOL_KEY_DATA);
                if (optJSONArray != null && optJSONArray.length() >= 7) {
                    fVar.b = optJSONArray.optJSONObject(0).optString("value");
                    fVar.d = optJSONArray.optJSONObject(SimpleLog.LOG_LEVEL_FATAL).optString(SHubBatchQueryKeys.url);
                    JSONObject optJSONObject = optJSONArray.optJSONObject(SimpleLog.LOG_LEVEL_DEBUG);
                    if (optJSONObject != null) {
                        fVar.o = optJSONObject.optString(SHubBatchQueryKeys.url);
                    }
                    optJSONObject = optJSONArray.optJSONObject(MqttConnectOptions.MQTT_VERSION_3_1);
                    if (optJSONObject != null) {
                        fVar.n = optJSONObject.optString("value");
                    }
                    optJSONObject = optJSONArray.optJSONObject(MqttConnectOptions.MQTT_VERSION_3_1_1);
                    if (optJSONObject != null) {
                        fVar.j = optJSONObject.optString(SHubBatchQueryKeys.url);
                    }
                    JSONObject optJSONObject2 = optJSONArray.optJSONObject(SimpleLog.LOG_LEVEL_ERROR);
                    if (optJSONObject2 == null) {
                        return fVar;
                    }
                    fVar.k = optJSONObject2.optString("value");
                    return fVar;
                }
            }
            return null;
        }
    }

    // compiled from: ParseUtil.java
    private static class d extends a {
        private d() {
            super();
        }

        protected final f a(JSONObject jSONObject) {
            f fVar = new f();
            if (jSONObject != null) {
                fVar.m = jSONObject.optInt("material_id");
                JSONArray optJSONArray = jSONObject.optJSONArray(SocializeProtocolConstants.PROTOCOL_KEY_DATA);
                if (optJSONArray != null && optJSONArray.length() >= 4) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(1);
                    if (optJSONObject != null) {
                        fVar.d = optJSONObject.optString(SHubBatchQueryKeys.url);
                    }
                    optJSONObject = optJSONArray.optJSONObject(0);
                    if (optJSONObject != null) {
                        fVar.b = optJSONObject.optString("value");
                    }
                    optJSONObject = optJSONArray.optJSONObject(SimpleLog.LOG_LEVEL_DEBUG);
                    if (optJSONObject != null) {
                        fVar.j = optJSONObject.optString(SHubBatchQueryKeys.url);
                    }
                    fVar.k = optJSONArray.optJSONObject(MqttConnectOptions.MQTT_VERSION_3_1).optString("value");
                    return fVar;
                }
            }
            return null;
        }
    }

    // compiled from: ParseUtil.java
    private static class e extends a {
        private e() {
            super();
        }

        protected final f a(JSONObject jSONObject) {
            f fVar = new f();
            if (jSONObject != null) {
                fVar.m = jSONObject.optInt("material_id");
                JSONArray optJSONArray = jSONObject.optJSONArray(SocializeProtocolConstants.PROTOCOL_KEY_DATA);
                if (optJSONArray != null && optJSONArray.length() >= 5) {
                    fVar.d = optJSONArray.optJSONObject(0).optString(SHubBatchQueryKeys.url);
                    fVar.b = optJSONArray.optJSONObject(1).optString("value");
                    JSONObject optJSONObject = optJSONArray.optJSONObject(SimpleLog.LOG_LEVEL_DEBUG);
                    if (optJSONObject != null) {
                        fVar.f = (float) optJSONObject.optDouble("value");
                    }
                    optJSONObject = optJSONArray.optJSONObject(MqttConnectOptions.MQTT_VERSION_3_1);
                    if (optJSONObject != null) {
                        fVar.j = optJSONObject.optString(SHubBatchQueryKeys.url);
                    }
                    JSONObject optJSONObject2 = optJSONArray.optJSONObject(MqttConnectOptions.MQTT_VERSION_3_1_1);
                    if (optJSONObject2 == null) {
                        return fVar;
                    }
                    fVar.k = optJSONObject2.optString("value");
                    return fVar;
                }
            }
            return null;
        }
    }

    // compiled from: ParseUtil.java
    static class f {
        public final int a;
        final List<com.xunlei.downloadprovider.ad.common.a> b;

        f(int i) {
            this.a = i;
            this.b = new ArrayList();
        }
    }

    // compiled from: ParseUtil.java
    public static class g extends a {
        public g() {
            super();
        }

        protected final f a(JSONObject jSONObject) {
            if (jSONObject != null) {
                f fVar = new f();
                fVar.m = jSONObject.optInt("material_id");
                JSONArray optJSONArray = jSONObject.optJSONArray(SocializeProtocolConstants.PROTOCOL_KEY_DATA);
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(0);
                    if (optJSONObject != null) {
                        fVar.c = optJSONObject.optString(SHubBatchQueryKeys.url);
                    }
                    optJSONObject = optJSONArray.optJSONObject(1);
                    if (optJSONObject != null) {
                        fVar.a = optJSONObject.optString("value");
                    }
                    optJSONObject = optJSONArray.optJSONObject(SimpleLog.LOG_LEVEL_DEBUG);
                    if (optJSONObject != null) {
                        fVar.b = optJSONObject.optString("value");
                    }
                    optJSONObject = optJSONArray.optJSONObject(MqttConnectOptions.MQTT_VERSION_3_1);
                    if (optJSONObject != null) {
                        fVar.f = (float) optJSONObject.optDouble("value");
                    }
                    optJSONObject = optJSONArray.optJSONObject(MqttConnectOptions.MQTT_VERSION_3_1_1);
                    if (optJSONObject != null) {
                        fVar.j = optJSONObject.optString(SHubBatchQueryKeys.url);
                    }
                    JSONObject optJSONObject2 = optJSONArray.optJSONObject(SimpleLog.LOG_LEVEL_ERROR);
                    if (optJSONObject2 == null) {
                        return fVar;
                    }
                    fVar.k = optJSONObject2.optString("value");
                    return fVar;
                }
            }
            return null;
        }
    }
}
