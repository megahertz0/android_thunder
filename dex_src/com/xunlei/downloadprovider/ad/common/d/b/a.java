package com.xunlei.downloadprovider.ad.common.d.b;

import android.text.TextUtils;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xunlei.downloadprovider.ad.common.CommonConst.AD_TYPE;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: CfgParser.java
public final class a {

    // compiled from: CfgParser.java
    public static class a {
        public final int a;
        public final Map<String, Map<AD_TYPE, Integer>> b;

        a(int i) {
            this.a = i;
            this.b = new HashMap();
        }
    }

    public static a a(String str) {
        if (TextUtils.isEmpty(str)) {
            return new a(-1110);
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
        } catch (JSONException e) {
            jSONObject = null;
        }
        if (jSONObject == null) {
            return new a(-1110);
        }
        int optInt = jSONObject.optInt(XiaomiOAuthConstants.EXTRA_CODE_2, 0);
        a aVar = new a(optInt);
        if (optInt != 0) {
            return aVar;
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("positions");
        if (optJSONArray != null) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    Map hashMap = new HashMap();
                    CharSequence charSequence = (optJSONObject.optInt("positionId") == 0 ? BuildConfig.VERSION_NAME : Integer.valueOf(optJSONObject.optInt("positionId")));
                    if (!TextUtils.isEmpty(charSequence)) {
                        aVar.b.put(charSequence, hashMap);
                        JSONArray optJSONArray2 = optJSONObject.optJSONArray("services");
                        if (optJSONArray2 != null) {
                            int i2;
                            optInt = 0;
                            for (i2 = 0; i2 < optJSONArray2.length(); i2++) {
                                JSONObject optJSONObject2 = optJSONArray2.optJSONObject(i2);
                                if (optJSONObject2 != null) {
                                    int i3;
                                    String optString = optJSONObject2.optString("serviceId");
                                    Object obj = -1;
                                    switch (optString.hashCode()) {
                                        case 48625:
                                            if (optString.equals("100")) {
                                                i3 = 0;
                                            }
                                            break;
                                        case 48626:
                                            if (optString.equals("101")) {
                                                obj = SimpleLog.LOG_LEVEL_DEBUG;
                                            }
                                            break;
                                        case 48627:
                                            if (optString.equals("102")) {
                                                obj = MqttConnectOptions.MQTT_VERSION_3_1;
                                            }
                                            break;
                                        case 48629:
                                            if (optString.equals("104")) {
                                                obj = 1;
                                            }
                                            break;
                                        case 48630:
                                            if (optString.equals("105")) {
                                                obj = MqttConnectOptions.MQTT_VERSION_3_1_1;
                                            }
                                            break;
                                    }
                                    switch (i3) {
                                        case MqttConnectOptions.MQTT_VERSION_DEFAULT:
                                            obj = AD_TYPE.SOURCE_GDT_FLAG;
                                            break;
                                        case SimpleLog.LOG_LEVEL_TRACE:
                                            obj = AD_TYPE.SOURCE_SSP_FLAG;
                                            break;
                                        case SimpleLog.LOG_LEVEL_DEBUG:
                                            obj = AD_TYPE.SOURCE_BAIDU_FLAG;
                                            break;
                                        case MqttConnectOptions.MQTT_VERSION_3_1:
                                            obj = AD_TYPE.SOURCE_INMOBI_NATIVE_FLAG;
                                            break;
                                        case MqttConnectOptions.MQTT_VERSION_3_1_1:
                                            obj = AD_TYPE.SOURCE_QIHU_FLAG;
                                            break;
                                        default:
                                            obj = null;
                                            break;
                                    }
                                    if (obj != null) {
                                        hashMap.put(obj, Integer.valueOf(optJSONObject2.optInt("trafficRatio")));
                                    } else {
                                        optInt += optJSONObject2.optInt("trafficRatio");
                                    }
                                }
                            }
                            Integer num = (Integer) hashMap.get(AD_TYPE.SOURCE_GDT_FLAG);
                            AD_TYPE ad_type = AD_TYPE.SOURCE_GDT_FLAG;
                            if (num == null) {
                                i2 = 0;
                            } else {
                                i2 = num.intValue();
                            }
                            hashMap.put(ad_type, Integer.valueOf(i2 + optInt));
                        }
                    }
                }
            }
        }
        return aVar;
    }
}
