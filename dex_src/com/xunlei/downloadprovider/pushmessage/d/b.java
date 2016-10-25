package com.xunlei.downloadprovider.pushmessage.d;

import com.android.volley.Request;
import com.android.volley.f;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.umeng.message.MsgConstant;
import com.umeng.message.PushAgent;
import com.umeng.socialize.common.SocializeConstants;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.member.payment.a.e;
import com.xunlei.downloadprovider.pushmessage.c;
import com.xunlei.downloadprovider.util.ai;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.downloadprovidercommon.b.a.a;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: PushNetworkHelper.java
public final class b extends e {
    static /* synthetic */ void a(JSONObject jSONObject) {
        int i = 0;
        if (jSONObject != null) {
            JSONObject optJSONObject = jSONObject.optJSONObject(SocializeConstants.JSON_DATA);
            if (optJSONObject != null) {
                JSONArray optJSONArray = optJSONObject.optJSONArray(MsgConstant.KEY_TAGS);
                if (optJSONArray != null && optJSONArray.length() != 0) {
                    String[] strArr = new String[optJSONArray.length()];
                    for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                        strArr[i2] = (String) optJSONArray.opt(i2);
                    }
                    if (strArr.length == 0) {
                        return;
                    }
                    if (ai.a()) {
                        int length = strArr.length;
                        while (i < length) {
                            MiPushClient.subscribe(BrothersApplication.a, strArr[i], null);
                            i++;
                        }
                        new StringBuilder("updatePushTag MiPushClient tags=").append(MiPushClient.getAllTopic(BrothersApplication.a));
                        return;
                    }
                    PushAgent.getInstance(BrothersApplication.a).getTagManager().update(new c(), strArr);
                }
            }
        }
    }

    public final void a(String str, String str2, String str3, String str4, String str5, String str6, List<String> list, String str7) {
        JSONObject a = a(str2, str3, str4, str5, str6, list, str7);
        new StringBuilder("url=").append(str).append("  ,peerId =").append(str2).append(",uid=").append(str3).append(",registerId=").append(str4).append(",status=").append(str5).append(",details=").append(str6);
        Request aVar = new a(str, a, new c(this), new d(this));
        aVar.setShouldCache(false);
        aVar.setRetryPolicy(new f(5000, 1, 1.0f));
        a(aVar);
    }

    private static JSONObject a(String str, String str2, String str3, String str4, String str5, List<String> list, String str6) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("peerid", str);
            jSONObject2.put(ParamKey.UID, str2);
            jSONObject2.put("services_id", str3);
            jSONObject2.put(Impl.COLUMN_STATUS, str4);
            jSONObject2.put(JsInterface.PAGE_DETAIL, str5);
            JSONArray jSONArray = new JSONArray();
            for (String str7 : list) {
                jSONArray.put(str7);
            }
            jSONObject2.put(MsgConstant.KEY_TAGS, jSONArray);
            if (str6 != null) {
                jSONObject2.put("pusher", str6);
            }
            jSONObject2.put("time", System.currentTimeMillis());
            jSONObject.put(SocializeConstants.JSON_DATA, jSONObject2);
        } catch (JSONException e) {
        }
        return jSONObject;
    }
}
