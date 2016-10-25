package com.xunlei.downloadprovider.pushmessage;

import android.text.TextUtils;
import com.taobao.accs.common.Constants;
import com.tencent.open.SocialConstants;
import com.umeng.message.entity.UMessage;
import com.xunlei.downloadprovider.pushmessage.a.a;
import com.xunlei.tdlive.WebBrowserActivity;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: PushResultParser.java
public final class h {
    public static a a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            a aVar = new a();
            try {
                aVar.l = jSONObject.optString("messageId", com.umeng.a.d);
                aVar.b = jSONObject.optString(Constants.KEY_TARGET);
                aVar.d = jSONObject.optString("tab");
                aVar.c = jSONObject.optString(SocialConstants.PARAM_URL);
                aVar.g = jSONObject.optString("searchKey");
                aVar.e = jSONObject.optString("groupId");
                aVar.f = jSONObject.optString("groupResourceId");
                aVar.h = jSONObject.optString(WebBrowserActivity.EXTRA_TITLE);
                aVar.i = jSONObject.optString("icon");
                aVar.j = jSONObject.optString(SocialConstants.PARAM_APP_DESC);
                aVar.k = jSONObject.optString("resName");
                aVar.m = jSONObject.optString("big_pic");
                aVar.n = jSONObject.optString("big_pic_new");
                aVar.o = jSONObject.optInt("is_short", -1);
                aVar.r = jSONObject.optString("res_type");
                aVar.s = jSONObject.optInt("display_type", -1);
                aVar.p = jSONObject.optString("video_id");
                aVar.q = jSONObject.optString("gcId");
                aVar.t = jSONObject.optString("room_info");
                return aVar;
            } catch (JSONException e) {
                return aVar;
            }
        } catch (JSONException e2) {
            return null;
        }
    }

    public static a b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            Object obj = new UMessage(new JSONObject(str)).custom;
            if (TextUtils.isEmpty(obj)) {
                return null;
            }
            try {
                JSONObject jSONObject = new JSONObject(obj);
                a aVar = new a();
                try {
                    aVar.l = jSONObject.optString("messageId", com.umeng.a.d);
                    aVar.b = jSONObject.optString(Constants.KEY_TARGET);
                    aVar.d = jSONObject.optString("tab");
                    aVar.c = jSONObject.optString(SocialConstants.PARAM_URL);
                    aVar.g = jSONObject.optString("searchKey");
                    aVar.e = jSONObject.optString("groupId");
                    aVar.f = jSONObject.optString("groupResourceId");
                    aVar.h = jSONObject.optString(WebBrowserActivity.EXTRA_TITLE);
                    aVar.i = jSONObject.optString("icon");
                    aVar.j = jSONObject.optString(SocialConstants.PARAM_APP_DESC);
                    aVar.k = jSONObject.optString("resName");
                    aVar.m = jSONObject.optString("big_pic");
                    aVar.n = jSONObject.optString("big_pic_new");
                    aVar.o = jSONObject.optInt("is_short", -1);
                    aVar.a = str;
                    aVar.r = jSONObject.optString("res_type");
                    aVar.s = jSONObject.optInt("display_type", -1);
                    aVar.p = jSONObject.optString("video_id");
                    aVar.q = jSONObject.optString("gcId");
                    aVar.t = jSONObject.optString("room_info");
                    return aVar;
                } catch (JSONException e) {
                    return aVar;
                }
            } catch (JSONException e2) {
                return null;
            }
        } catch (JSONException e3) {
            return null;
        }
    }
}
