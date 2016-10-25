package com.xunlei.thundersniffer.sniff.sniffer;

import android.text.TextUtils;
import com.xunlei.thundersniffer.sniff.sniffer.SnifferProtocol.GetKey;
import com.xunlei.thundersniffer.sniff.sniffer.SniffingDetailPageTask.LocalCacheOperation;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

final class u implements SnifferCacheDatabase$ISnifferCacheCallback {
    final /* synthetic */ String a;
    final /* synthetic */ LocalCacheOperation b;

    u(LocalCacheOperation localCacheOperation, String str) {
        this.b = localCacheOperation;
        this.a = str;
    }

    public final void onSnifferCacheCallback(String str, int i, String str2, long j, String str3) {
        ArrayList arrayList;
        Exception exception;
        String str4 = null;
        String str5 = null;
        try {
            if (TextUtils.isEmpty(str3)) {
                arrayList = null;
            } else {
                JSONObject jSONObject = new JSONObject(str3);
                String optString = jSONObject.optString("realUrl");
                try {
                    str4 = jSONObject.optString("pageTitle");
                    try {
                        JSONArray jSONArray = jSONObject.getJSONArray("res_link_list");
                        if (jSONArray == null || jSONArray.length() <= 0) {
                            str5 = str4;
                            str4 = optString;
                            arrayList = null;
                        } else {
                            ArrayList arrayList2 = new ArrayList();
                            int i2 = 0;
                            while (i2 < jSONArray.length()) {
                                try {
                                    Object obj;
                                    JSONObject jSONObject2 = (JSONObject) jSONArray.opt(i2);
                                    if (jSONObject2 != null) {
                                        z zVar = new z(jSONObject2.optString("download_url"));
                                        zVar.e = jSONObject2.optString(GetKey.ORIGINAL_DOWNLOAD_URL);
                                        z zVar2 = zVar;
                                    } else {
                                        obj = null;
                                    }
                                    if (obj != null) {
                                        arrayList2.add(obj);
                                    }
                                    i2++;
                                } catch (Exception e) {
                                    Exception exception2 = e;
                                    str5 = str4;
                                    str4 = optString;
                                    arrayList = arrayList2;
                                    exception = exception2;
                                }
                            }
                            str5 = str4;
                            str4 = optString;
                            arrayList = arrayList2;
                        }
                    } catch (Exception e2) {
                        exception = e2;
                        str5 = str4;
                        str4 = optString;
                        arrayList = null;
                        exception.printStackTrace();
                        this.b.mResult.pageUrl = this.a;
                        this.b.mResult.realUrl = str4;
                        this.b.mResult.isValid = i != 0;
                        this.b.mResult.content = arrayList;
                        this.b.mResult.flags = j;
                        this.b.mResult.pageTitle = str5;
                        this.b.a();
                    }
                } catch (Exception e3) {
                    exception = e3;
                    str4 = optString;
                    arrayList = null;
                    exception.printStackTrace();
                    this.b.mResult.pageUrl = this.a;
                    this.b.mResult.realUrl = str4;
                    if (i != 0) {
                    }
                    this.b.mResult.isValid = i != 0;
                    this.b.mResult.content = arrayList;
                    this.b.mResult.flags = j;
                    this.b.mResult.pageTitle = str5;
                    this.b.a();
                }
            }
        } catch (Exception e4) {
            exception = e4;
            arrayList = null;
            exception.printStackTrace();
            this.b.mResult.pageUrl = this.a;
            this.b.mResult.realUrl = str4;
            if (i != 0) {
            }
            this.b.mResult.isValid = i != 0;
            this.b.mResult.content = arrayList;
            this.b.mResult.flags = j;
            this.b.mResult.pageTitle = str5;
            this.b.a();
        }
        this.b.mResult.pageUrl = this.a;
        this.b.mResult.realUrl = str4;
        if (i != 0) {
        }
        this.b.mResult.isValid = i != 0;
        this.b.mResult.content = arrayList;
        this.b.mResult.flags = j;
        this.b.mResult.pageTitle = str5;
        this.b.a();
    }
}
