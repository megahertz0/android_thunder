package com.xunlei.common.member.b;

import android.text.TextUtils;
import com.tencent.connect.common.Constants;
import com.tencent.open.SocialConstants;
import com.umeng.a;
import com.xunlei.common.base.XLLog;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: XLQQSSOResult.java
public final class h {
    public String a;
    public String b;
    public String c;
    private String d;

    public h(JSONObject jSONObject) {
        this.d = g.class.getSimpleName();
        this.a = a.d;
        this.b = a.d;
        this.c = a.d;
        if (jSONObject != null) {
            try {
                this.b = jSONObject.getString(Constants.PARAM_ACCESS_TOKEN);
                this.c = jSONObject.getString(Constants.PARAM_EXPIRES_IN);
                this.a = jSONObject.getString(SocialConstants.PARAM_OPEN_ID);
            } catch (JSONException e) {
                e.printStackTrace();
                XLLog.v(this.d, "extract json object error");
            }
        }
    }

    public final boolean a() {
        return (TextUtils.isEmpty(this.b) || TextUtils.isEmpty(this.a) || TextUtils.isEmpty(this.c)) ? false : true;
    }

    public h() {
        this.d = g.class.getSimpleName();
        this.a = a.d;
        this.b = a.d;
        this.c = a.d;
    }
}
