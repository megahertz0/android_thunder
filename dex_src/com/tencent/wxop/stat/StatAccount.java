package com.tencent.wxop.stat;

import com.tencent.wxop.stat.common.k;
import com.tencent.wxop.stat.common.q;
import com.umeng.a;
import org.json.JSONException;
import org.json.JSONObject;

public class StatAccount {
    public static final int CUSTOM_TYPE = 7;
    public static final int DEFAULT_TYPE = 0;
    public static final int EMAIL_TYPE = 6;
    public static final int PHONE_NUM_TYPE = 5;
    public static final int QQ_NUM_TYPE = 1;
    public static final int QQ_OPENID_TYPE = 3;
    public static final int WECHAT_ID_TYPE = 2;
    public static final int WECHAT_OPENID_TYPE = 4;
    private String a;
    private int b;
    private String c;
    private String d;

    public StatAccount(String str) {
        this.a = a.d;
        this.b = 0;
        this.c = a.d;
        this.d = a.d;
        this.a = str;
    }

    public StatAccount(String str, int i) {
        this.a = a.d;
        this.b = 0;
        this.c = a.d;
        this.d = a.d;
        this.a = str;
        this.b = i;
    }

    public String getAccount() {
        return this.a;
    }

    public int getAccountType() {
        return this.b;
    }

    public String getExt() {
        return this.c;
    }

    public String getExt1() {
        return this.d;
    }

    public void setAccount(String str) {
        this.a = str;
    }

    public void setAccountType(int i) {
        this.b = i;
    }

    public void setExt(String str) {
        this.c = str;
    }

    public void setExt1(String str) {
        this.d = str;
    }

    public String toJsonString() {
        JSONObject jSONObject = new JSONObject();
        if (k.c(this.a)) {
            try {
                q.a(jSONObject, "a", this.a);
                jSONObject.put(anet.channel.strategy.dispatch.a.TIMESTAMP, this.b);
                q.a(jSONObject, "e", this.c);
                q.a(jSONObject, "e1", this.d);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jSONObject.toString();
    }

    public String toString() {
        return new StringBuilder("StatAccount [account=").append(this.a).append(", accountType=").append(this.b).append(", ext=").append(this.c).append(", ext1=").append(this.d).append("]").toString();
    }
}
