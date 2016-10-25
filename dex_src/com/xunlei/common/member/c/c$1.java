package com.xunlei.common.member.c;

import android.os.Bundle;
import com.xunlei.common.base.XLLog;
import com.xunlei.common.httpclient.BaseHttpClientListener;
import com.xunlei.common.member.XLHspeedCapacity;
import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: UserGetHighSpeedCapacityTask.java
final class c$1 implements BaseHttpClientListener {
    private /* synthetic */ c a;

    c$1(c cVar) {
        this.a = cVar;
    }

    public final void onSuccess(int i, Header[] headerArr, byte[] bArr) {
        String str = new String(bArr);
        XLLog.v("UserGetHighSpeedCapacity result", str);
        try {
            JSONObject jSONObject = new JSONObject(str.toString());
            c.a(this.a, new XLHspeedCapacity());
            c.a(this.a).total_capacity = jSONObject.optLong("total_capacity");
            c.a(this.a).used_capacity = jSONObject.optLong("used_capacity");
            c.a(this.a).solidify_capacity = jSONObject.optLong("solidify_capacity");
            c.a(this.a).fluxcard_total_capacity = jSONObject.optLong("fluxcard_total_capacity");
            c.a(this.a).fluxcard_invalid_capacity = jSONObject.optLong("fluxcard_invalid_capacity");
            c.a(this.a).fluxcard_pause_capacity = jSONObject.optLong("fluxcard_pause_capacity");
            Bundle bundle = new Bundle();
            bundle.putInt("errorCode", 0);
            bundle.putString("action", "user_get_high_speed_capacity");
            this.a.g().a(this.a, bundle);
        } catch (JSONException e) {
            e.printStackTrace();
            bundle = new Bundle();
            bundle.putInt("errorCode", 16777214);
            bundle.putString("action", "user_get_high_speed_capacity");
            this.a.g().a(this.a, bundle);
        }
    }

    public final void onFailure(Throwable th, byte[] bArr) {
        XLLog.e("UserGetHighSpeedCapacity error", th.getMessage());
        Bundle bundle = new Bundle();
        bundle.putString("action", "user_get_high_speed_capacity");
        bundle.putInt("errorCode", 16781312);
        this.a.g().a(this.a, bundle);
    }
}
