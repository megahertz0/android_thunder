package com.xunlei.downloadprovider.download.tasklist.list.a;

import com.taobao.accs.common.Constants;
import com.tencent.open.SocialConstants;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.download.Downloads.Impl.RequestHeaders;
import com.xunlei.downloadprovider.ad.common.d.a.e;
import com.xunlei.downloadprovider.download.tasklist.list.a.b.b;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.ArrayList;
import java.util.List;
import org.android.agoo.message.MessageService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Deprecated
// compiled from: SSPParse.java
public final class q {
    public static a a(String str) {
        a aVar = new a();
        try {
            JSONObject jSONObject = new JSONObject(str);
        } catch (JSONException e) {
            jSONObject = null;
        }
        if (jSONObject == null) {
            return null;
        }
        int optInt = jSONObject.optInt(Constants.KEY_HTTP_CODE, 0);
        if (optInt != 0) {
            aVar.a = optInt;
        }
        List arrayList = new ArrayList();
        JSONArray optJSONArray = jSONObject.optJSONArray(SocializeConstants.JSON_DATA);
        if (optJSONArray != null) {
            for (optInt = 0; optInt < optJSONArray.length(); optInt++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(optInt);
                if (optJSONObject != null) {
                    boolean z;
                    e eVar = new e();
                    eVar.b = optJSONObject.optInt("orderId");
                    eVar.a = optJSONObject.optInt("positionId");
                    if (optJSONObject.optInt("default_ad", 0) == 1) {
                        z = true;
                    } else {
                        z = false;
                    }
                    eVar.e = z;
                    JSONArray optJSONArray2 = optJSONObject.optJSONArray("materials");
                    if (optJSONArray2 != null && optJSONArray2.length() > 0) {
                        jSONObject = optJSONArray2.optJSONObject(0);
                        if (jSONObject != null) {
                            eVar.c = jSONObject.optInt("open_type");
                            eVar.d = jSONObject.optString("open_url");
                            b bVar = new b();
                            bVar.c = eVar;
                            bVar.d = eVar.b;
                            if (eVar.c == 2) {
                                bVar.k = MessageService.MSG_DB_NOTIFY_DISMISS;
                                bVar.i = eVar.d;
                            } else {
                                bVar.k = MessageService.MSG_DB_NOTIFY_REACHED;
                                bVar.g = eVar.d;
                            }
                            bVar.r = eVar.e;
                            if (eVar.e) {
                                bVar.q = 1;
                            } else {
                                bVar.q = 2;
                            }
                            bVar.m = jSONObject.optInt("material_id");
                            optJSONArray2 = jSONObject.optJSONArray(SocializeConstants.JSON_DATA);
                            if (optJSONArray2 != null && optJSONArray2.length() >= 6) {
                                bVar.j = optJSONArray2.optJSONObject(0).optString(SocialConstants.PARAM_URL);
                                bVar.f = optJSONArray2.optJSONObject(1).optString(RequestHeaders.COLUMN_VALUE);
                                bVar.l = optJSONArray2.optJSONObject(XZBDevice.DOWNLOAD_LIST_RECYCLE).optString(RequestHeaders.COLUMN_VALUE);
                                bVar.p = optJSONArray2.optJSONObject(XZBDevice.DOWNLOAD_LIST_FAILED).optString(RequestHeaders.COLUMN_VALUE);
                                bVar.o = optJSONArray2.optJSONObject(XZBDevice.DOWNLOAD_LIST_ALL).optString(SocialConstants.PARAM_URL);
                                bVar.n = optJSONArray2.optJSONObject(XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED).optString(RequestHeaders.COLUMN_VALUE);
                                arrayList.add(bVar);
                            }
                        }
                    }
                }
            }
        }
        aVar.b = arrayList;
        return aVar;
    }
}
