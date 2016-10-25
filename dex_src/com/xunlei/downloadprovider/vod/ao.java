package com.xunlei.downloadprovider.vod;

import android.text.TextUtils;
import com.sina.weibo.sdk.register.mobile.SelectCountryActivity;
import com.tencent.open.SocialConstants;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.downloadprovider.vod.VodUtil.SharpnessValue;
import com.xunlei.downloadprovider.vod.a.c;
import com.xunlei.downloadprovider.vod.protocol.VodSourceType;
import com.xunlei.downloadprovider.vod.protocol.VodVideoFormat;
import com.xunlei.downloadprovider.vod.protocol.h;
import com.xunlei.downloadprovider.vod.protocol.h.a;
import com.xunlei.downloadprovider.vod.protocol.h.b;
import com.xunlei.downloadprovider.vod.protocol.i;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.tdlive.WebBrowserActivity;
import java.io.File;
import java.util.ArrayList;
import org.android.agoo.message.MessageService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: VodPlayerParamFactory.java
public final class ao {
    private static final String a;

    static {
        a = ao.class.getName();
    }

    public static ap a(String str) {
        return b(str, VodSourceType.webpage);
    }

    private static ap b(String str, VodSourceType vodSourceType) {
        if (!TextUtils.isEmpty(str)) {
            ap apVar = new ap();
            apVar.b = vodSourceType;
            apVar.a = 0;
            try {
                JSONObject jSONObject = new JSONObject(str);
                apVar.m = jSONObject.optString("popupUrl");
                apVar.c = jSONObject.optString("sourceUrl");
                apVar.f = jSONObject.optString("playOn");
                apVar.h = jSONObject.optInt("operateType");
                CharSequence optString = jSONObject.optString("realUrl");
                if (!(TextUtils.isEmpty(optString) || apVar.e == null)) {
                    apVar.e.add(optString);
                }
                JSONArray optJSONArray = jSONObject.optJSONArray("epList");
                if (optJSONArray instanceof JSONArray) {
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                        if (optJSONObject instanceof JSONObject) {
                            int i2;
                            h hVar = new h();
                            hVar.a = optJSONObject.optString(WebBrowserActivity.EXTRA_TITLE);
                            hVar.k = optJSONObject.optString(SocializeConstants.WEIBO_ID);
                            hVar.l = optJSONObject.optString("tag");
                            hVar.o = optJSONObject.optInt("vodType");
                            hVar.j = optJSONObject.optBoolean("sliced");
                            hVar.h = optJSONObject.optBoolean("havenext");
                            hVar.i = optJSONObject.optBoolean("haveprev");
                            hVar.g = optJSONObject.optString("videoType");
                            hVar.x = VodVideoFormat.flv;
                            JSONArray optJSONArray2 = optJSONObject.optJSONArray("dataList");
                            if (optJSONArray2 == null) {
                                hVar.c = optJSONObject.optString("refUrl");
                            } else if (hVar.j || optJSONArray2.length() != 1) {
                                hVar.c = optJSONArray2.getJSONObject(0).optString(SocialConstants.PARAM_URL);
                                hVar.t = new ArrayList();
                                for (i2 = 0; i2 < optJSONArray2.length(); i2++) {
                                    JSONObject jSONObject2 = optJSONArray2.getJSONObject(i2);
                                    if (jSONObject2 instanceof JSONObject) {
                                        b bVar = new b();
                                        bVar.a = jSONObject2.optString(SocialConstants.PARAM_URL);
                                        bVar.b = jSONObject2.optInt("duration");
                                        hVar.t.add(bVar);
                                    }
                                }
                            } else {
                                hVar.c = optJSONArray2.getJSONObject(0).optString(SocialConstants.PARAM_URL);
                            }
                            JSONObject optJSONObject2 = optJSONObject.optJSONObject("resourceData");
                            if (optJSONObject2 != null) {
                                String optString2 = optJSONObject2.optString("finished");
                                hVar.b = optJSONObject2.optString(WebBrowserActivity.EXTRA_TITLE);
                                if (optString2 == null || !optString2.equals(MessageService.MSG_DB_READY_REPORT)) {
                                    hVar.u = true;
                                } else {
                                    hVar.u = false;
                                }
                                JSONArray optJSONArray3 = optJSONObject2.optJSONArray("list");
                                if (optJSONArray3 != null) {
                                    for (i2 = 0; i2 < optJSONArray3.length(); i2++) {
                                        JSONObject jSONObject3 = optJSONArray3.getJSONObject(i2);
                                        if (jSONObject3 != null) {
                                            a aVar = new a();
                                            aVar.b = jSONObject3.optString(SelectCountryActivity.EXTRA_COUNTRY_NAME);
                                            aVar.c = jSONObject3.optString("fileName");
                                            aVar.a = jSONObject3.optString("down");
                                            aVar.d = String.valueOf(jSONObject3.optInt("no"));
                                            if (hVar.w == null) {
                                                hVar.w = new ArrayList();
                                            }
                                            hVar.w.add(aVar);
                                        }
                                    }
                                }
                            }
                            apVar.a(hVar);
                        }
                    }
                }
                a(apVar, jSONObject);
                return apVar;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private static void a(ap apVar, JSONObject jSONObject) {
        Object optString = jSONObject.optString("sharpness", null);
        if (!TextUtils.isEmpty(optString)) {
            try {
                apVar.i = SharpnessValue.valueOf(optString);
            } catch (Exception e) {
                e.printStackTrace();
                apVar.i = SharpnessValue.flv;
            }
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("formatRange");
        if (optJSONArray != null) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                apVar.j.add(SharpnessValue.valueOf(optJSONArray.optString(i)));
            }
        }
    }

    public static ap a(i iVar) {
        if (iVar == null) {
            return null;
        }
        ap apVar = new ap();
        h hVar = new h();
        if (!(TextUtils.isEmpty(iVar.b) || TextUtils.isEmpty(iVar.c) || iVar.d <= 0)) {
            hVar.d = iVar.b;
            hVar.e = iVar.c;
            hVar.f = iVar.d;
        }
        hVar.q = null;
        hVar.c = iVar.e;
        hVar.a = iVar.a;
        hVar.o = iVar.f;
        hVar.x = iVar.g;
        apVar.b = iVar.h;
        apVar.a(hVar);
        apVar.l = iVar.i;
        return apVar;
    }

    public static ap b(String str) {
        String optString;
        String optString2;
        String optString3;
        String optString4;
        long optLong;
        String optString5;
        try {
            JSONObject jSONObject = new JSONObject(str);
            optString = jSONObject.optString(WebBrowserActivity.EXTRA_TITLE);
            optString2 = jSONObject.optString(Impl.COLUMN_CID);
            optString3 = jSONObject.optString(Impl.COLUMN_GCID);
            optString4 = jSONObject.optString(SocialConstants.PARAM_URL);
            optLong = jSONObject.optLong("file_size");
            optString5 = jSONObject.optString("cooperation_id", com.umeng.a.d);
            String optString6 = jSONObject.optString("torrentUrl", com.umeng.a.d);
            String optString7 = jSONObject.optString(JsInterface.KEY_APK_NAME, com.umeng.a.d);
            String str2 = optString5;
            optString5 = optString;
            optString = optString2;
            optString2 = optString3;
            optString3 = optString4;
            optString4 = str2;
        } catch (JSONException e) {
            optString7 = null;
            optString6 = null;
            optLong = 0;
            optString3 = null;
            optString2 = null;
            optString = null;
            optString4 = null;
            optString5 = null;
        }
        ap apVar = new ap();
        h hVar = new h();
        hVar.d = optString;
        hVar.q = null;
        hVar.f = optLong;
        hVar.e = optString2;
        hVar.c = optString3;
        hVar.a = optString5;
        hVar.o = 1;
        hVar.x = VodVideoFormat.flv;
        apVar.b = VodSourceType.normal;
        apVar.a(hVar);
        int a = c.a(optString4);
        if (com.xunlei.downloadprovider.vod.a.b.a(a)) {
            apVar.l = new com.xunlei.downloadprovider.vod.a.a(optString6, optString5, a, 0, optString7);
        }
        return apVar;
    }

    public static ap a(String str, VodSourceType vodSourceType) {
        ap apVar = new ap();
        h hVar = new h();
        hVar.f = new File(str).length();
        apVar.b = vodSourceType;
        if (TextUtils.isEmpty(str) || !str.toLowerCase().trim().endsWith(".mp4")) {
            hVar.x = VodVideoFormat.flv;
        } else {
            hVar.x = VodVideoFormat.mp4;
        }
        hVar.c = str;
        if (str.contains("/")) {
            hVar.a = str.substring(str.lastIndexOf("/") + 1);
        } else {
            hVar.a = str;
        }
        apVar.a(hVar);
        return apVar;
    }
}
