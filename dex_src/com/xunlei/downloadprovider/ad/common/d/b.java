package com.xunlei.downloadprovider.ad.common.d;

import android.text.TextUtils;
import com.taobao.accs.common.Constants;
import com.tencent.open.SocialConstants;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.download.Downloads.Impl.RequestHeaders;
import com.xunlei.downloadprovider.ad.a.a;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Deprecated
// compiled from: SSPParser.java
public class b {
    private static final String a;

    static {
        a = b.class.getSimpleName();
    }

    public static void a(String str, com.xunlei.downloadprovider.ad.recommend.a.b.b<a> bVar) {
        List arrayList = new ArrayList();
        if (TextUtils.isEmpty(str)) {
            bVar.a(com.xunlei.downloadprovider.ad.recommend.a.b.a.a);
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
        } catch (JSONException e) {
            new StringBuilder("responseJsonObj error: ").append(e.getLocalizedMessage());
            jSONObject = null;
        }
        if (jSONObject == null) {
            bVar.a(com.xunlei.downloadprovider.ad.recommend.a.b.a.c);
            return;
        }
        int optInt = jSONObject.optInt(Constants.KEY_HTTP_CODE, 0);
        if (optInt != 0) {
            bVar.a(new com.xunlei.downloadprovider.ad.recommend.a.b.a(optInt, jSONObject.optString(SocializeConstants.JSON_DATA)));
            return;
        }
        JSONArray optJSONArray = jSONObject.optJSONArray(SocializeConstants.JSON_DATA);
        if (optJSONArray != null) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    a aVar = new a();
                    aVar.b = optJSONObject.optInt("orderId");
                    aVar.a = optJSONObject.optInt("positionId");
                    JSONArray optJSONArray2 = optJSONObject.optJSONArray("materials");
                    if (optJSONArray2 != null && optJSONArray2.length() > 0) {
                        optJSONObject = optJSONArray2.optJSONObject(0);
                        if (optJSONObject != null) {
                            aVar.c = optJSONObject.optInt("open_type");
                            aVar.d = optJSONObject.optString("open_url");
                            int optInt2 = optJSONObject.optInt(JsInterface.FUNPLAY_AD_TRPE);
                            aVar.j = optInt2;
                            aVar.k = optJSONObject.optInt("material_id");
                            optJSONArray2 = optJSONObject.optJSONArray(SocializeConstants.JSON_DATA);
                            if (optJSONArray2 != null && optJSONArray2.length() > 0) {
                                JSONObject optJSONObject2;
                                if (optInt2 == 1) {
                                    optJSONObject2 = optJSONArray2.optJSONObject(0);
                                    if (optJSONObject2 != null) {
                                        aVar.i = optJSONObject2.optString(SocialConstants.PARAM_URL);
                                    }
                                    optJSONObject2 = optJSONArray2.optJSONObject(1);
                                    if (optJSONObject2 != null) {
                                        aVar.m = optJSONObject2.optString(SocialConstants.PARAM_URL);
                                    }
                                    optJSONObject = optJSONArray2.optJSONObject(XZBDevice.DOWNLOAD_LIST_RECYCLE);
                                    if (optJSONObject != null) {
                                        aVar.l = optJSONObject.optString(RequestHeaders.COLUMN_VALUE);
                                    }
                                    arrayList.add(aVar);
                                } else if (optInt2 == 2) {
                                    optJSONObject2 = optJSONArray2.optJSONObject(0);
                                    if (optJSONObject2 != null) {
                                        aVar.i = optJSONObject2.optString(SocialConstants.PARAM_URL);
                                    }
                                    optJSONObject2 = optJSONArray2.optJSONObject(1);
                                    if (optJSONObject2 != null) {
                                        aVar.f = optJSONObject2.optString(RequestHeaders.COLUMN_VALUE);
                                    }
                                    optJSONObject2 = optJSONArray2.optJSONObject(XZBDevice.DOWNLOAD_LIST_RECYCLE);
                                    if (optJSONObject2 != null) {
                                        aVar.h = (float) optJSONObject2.optDouble(RequestHeaders.COLUMN_VALUE, 4.5d);
                                    }
                                    optJSONObject2 = optJSONArray2.optJSONObject(XZBDevice.DOWNLOAD_LIST_FAILED);
                                    if (optJSONObject2 != null) {
                                        aVar.m = optJSONObject2.optString(SocialConstants.PARAM_URL);
                                    }
                                    optJSONObject = optJSONArray2.optJSONObject(XZBDevice.DOWNLOAD_LIST_ALL);
                                    if (optJSONObject != null) {
                                        aVar.l = optJSONObject.optString(RequestHeaders.COLUMN_VALUE);
                                    }
                                    arrayList.add(aVar);
                                }
                            }
                        }
                    }
                }
            }
        }
        bVar.a(arrayList);
    }
}
