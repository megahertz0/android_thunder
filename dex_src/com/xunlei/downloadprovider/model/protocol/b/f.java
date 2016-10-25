package com.xunlei.downloadprovider.model.protocol.b;

import com.umeng.socialize.media.WeiXinShareContent;
import com.xunlei.downloadprovider.a.b;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.businessutil.a;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovidershare.R;
import com.xunlei.thundersniffer.sniff.sniffer.SnifferProtocol.SetKey;
import com.xunlei.thundersniffer.sniff.sniffer.internal.server.SvrProtocol.SHubBatchQueryKeys;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.android.agoo.common.AgooConstants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: FunResourceUtil.java
public class f {
    public static final String a;

    static {
        a = f.class.getSimpleName();
    }

    protected static j a(JSONObject jSONObject) throws JSONException, IOException {
        j jVar = new j();
        jVar.f = jSONObject;
        jVar.d = jSONObject.getLong("block");
        if (!(jVar.d == 0 || jVar.d == -404)) {
            jVar.b = jSONObject.getString("category");
            List arrayList = new ArrayList();
            JSONArray jSONArray = jSONObject.getJSONArray("infoList");
            if (jSONArray != null && jSONArray.length() > 0) {
                int length = jSONArray.length();
                for (int i = 0; i < length; i++) {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                    String str = jVar.b;
                    long j = jVar.d;
                    d dVar = new d();
                    dVar.j = j;
                    dVar.a = jSONObject2.getLong(AgooConstants.MESSAGE_ID);
                    dVar.b = jSONObject2.getString(SetKey.TITLE);
                    dVar.c = jSONObject2.getString("detailUrl");
                    dVar.d = jSONObject2.getString("pic");
                    dVar.e = jSONObject2.getString(SHubBatchQueryKeys.url);
                    dVar.f = jSONObject2.getString("updateTime");
                    dVar.h = jSONObject2.getString("sub_category");
                    dVar.i = jSONObject2.getString("content");
                    dVar.m = jSONObject2.optInt("playEnable", -1);
                    dVar.q = jSONObject2.optInt("playType", -1);
                    dVar.p = jSONObject2.optString("playTag", null);
                    dVar.o = jSONObject2.optInt("isCloudPlay", -1);
                    dVar.k = jSONObject2.optInt("width");
                    dVar.l = jSONObject2.optInt("height");
                    if (str.equals("pic")) {
                        dVar.g = 0;
                    } else {
                        dVar.g = -1;
                    }
                    arrayList.add(dVar);
                }
            }
            jVar.e = arrayList;
        }
        return jVar;
    }

    public static j a(String str, String str2) throws JSONException, IOException {
        k kVar = new k(BrothersApplication.a().getApplicationContext());
        return a(new JSONObject(k.a(c(str, str2))));
    }

    public static String a() {
        return BrothersApplication.a().getString(R.string.product_id);
    }

    public static String b() {
        return BrothersApplication.a().getString(R.string.version);
    }

    public static String c() {
        return b.d();
    }

    public static String d() {
        return b.f();
    }

    public static String e() {
        return String.valueOf(LoginHelper.a().j);
    }

    public static String f() {
        return String.valueOf(b.x());
    }

    private static String c(String str, String str2) {
        String str3 = null;
        if (str.equals("joke")) {
            str3 = a.e() + "joke/";
        } else if (str.equals("pic")) {
            str3 = a.e() + "pic/";
        } else if (str.equals(WeiXinShareContent.TYPE_VIDEO)) {
            str3 = a.e() + "video/";
        }
        return str3 + str2;
    }

    public static boolean b(String str, String str2) throws IOException {
        BrothersApplication.a().getApplicationContext();
        return new File(c(str, str2)).exists();
    }
}
