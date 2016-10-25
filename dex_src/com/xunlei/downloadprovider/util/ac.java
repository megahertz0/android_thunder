package com.xunlei.downloadprovider.util;

import android.text.TextUtils;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.downloadprovider.member.login.a.a;
import com.xunlei.downloadprovider.model.b;
import com.xunlei.downloadprovider.model.i;
import com.xunlei.thundersniffer.sniff.sniffer.internal.server.SvrProtocol.SHubBatchQueryKeys;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: WebsiteHelper.java
public final class ac {
    private static boolean a;
    private static boolean b;

    static {
        a = false;
        b = true;
    }

    private static b d(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            b bVar = new b();
            try {
                JSONObject jSONObject = new JSONObject(str);
                bVar.b = jSONObject.optString(SocializeProtocolConstants.PROTOCOL_KEY_FRIENDS_NAME, BuildConfig.VERSION_NAME);
                bVar.a = jSONObject.optString(SocializeProtocolConstants.PROTOCOL_KEY_USER_ICON2, BuildConfig.VERSION_NAME);
                bVar.c = jSONObject.optString(SHubBatchQueryKeys.url, BuildConfig.VERSION_NAME);
                return bVar;
            } catch (JSONException e) {
                JSONException e2 = e;
            }
        } catch (JSONException e3) {
            JSONException jSONException = e3;
            bVar = null;
            e2 = jSONException;
            e2.printStackTrace();
            return bVar;
        }
    }

    private static List<b> e(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        List<b> arrayList = new ArrayList();
        try {
            JSONArray optJSONArray = new JSONObject(str).optJSONArray("list");
            if (optJSONArray == null) {
                return arrayList;
            }
            int length = optJSONArray.length();
            for (int i = 0; i < length; i++) {
                b bVar = new b();
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                bVar.b = optJSONObject.optString(SocializeProtocolConstants.PROTOCOL_KEY_FRIENDS_NAME, BuildConfig.VERSION_NAME);
                bVar.a = optJSONObject.optString(SocializeProtocolConstants.PROTOCOL_KEY_USER_ICON2, BuildConfig.VERSION_NAME);
                bVar.c = optJSONObject.optString(SHubBatchQueryKeys.url, BuildConfig.VERSION_NAME);
                arrayList.add(bVar);
            }
            return arrayList;
        } catch (JSONException e) {
            e.printStackTrace();
            return arrayList;
        }
    }

    public static void a(String str) {
        a = true;
        b d = d(str);
        if (d != null) {
            a.a().a(d);
            i.a().a(1, d.c);
        }
    }

    public static void b(String str) {
        if (b) {
            b = false;
            List e = e(str);
            if (e != null) {
                i.a().a(e);
            }
        }
    }

    public static void c(String str) {
        a = true;
        if (!TextUtils.isEmpty(str)) {
            a.a().a(str);
            i.a().a(0, str);
        }
    }
}
