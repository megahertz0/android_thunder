package com.xunlei.downloadprovider.c;

import android.text.TextUtils;
import com.android.volley.r.b;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xunlei.downloadprovider.c.a.f;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: CommentManager.java
final class i implements b<JSONObject> {
    final /* synthetic */ a a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ a d;

    i(a aVar, a aVar2, String str, String str2) {
        this.d = aVar;
        this.a = aVar2;
        this.b = str;
        this.c = str2;
    }

    public final /* synthetic */ void onResponse(Object obj) {
        a.b bVar;
        JSONObject jSONObject = (JSONObject) obj;
        a.a;
        new StringBuilder("comment list response=>").append(jSONObject.toString());
        try {
            f a = f.a(jSONObject);
            if (this.a != null) {
                this.a.a(a);
            }
            if (TextUtils.equals(this.b, "hot")) {
                this.d.g = a;
            } else if (!TextUtils.equals(this.b, "new")) {
            } else {
                if (this.d.e == null || TextUtils.equals(this.c, "refresh")) {
                    this.d.e = a;
                } else if (TextUtils.equals(this.c, "loadmore")) {
                    f a2 = this.d.e;
                    if (a == null) {
                        return;
                    }
                    if (TextUtils.isEmpty(a2.a) || TextUtils.equals(a.a, a2.a)) {
                        a2.a = a.a;
                        if (a.e != null && !a.e.isEmpty()) {
                            if (a2.e == null) {
                                a2.e = new ArrayList();
                                a2.c = a.c;
                                a2.b = a.b;
                            }
                            a2.e.addAll(a.e);
                            return;
                        }
                        return;
                    }
                    throw new IllegalStateException("appendList: commentResId is different!");
                }
            }
        } catch (JSONException e) {
            a.a;
            e.getMessage();
            bVar = new a.b();
            bVar.a = jSONObject.optInt("result", XiaomiOAuthConstants.ERROR_NEED_AUTHORIZE);
            bVar.b = e.getMessage();
            if (this.a != null) {
                this.a.a(bVar);
            }
            e.printStackTrace();
        }
    }
}
