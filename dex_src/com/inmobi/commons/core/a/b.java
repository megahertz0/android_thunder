package com.inmobi.commons.core.a;

import android.util.Log;
import com.inmobi.commons.core.c.e;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.sina.weibo.sdk.register.mobile.SelectCountryActivity;
import com.taobao.accs.common.Constants;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: CrashEvent.java
public class b extends e {
    private static final String a;

    static {
        a = b.class.getSimpleName();
    }

    public b(Thread thread, Throwable th) {
        super("crashReporting", "CrashEvent");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(SelectCountryActivity.EXTRA_COUNTRY_NAME, th.getClass().getSimpleName());
            jSONObject.put(Constants.SHARED_MESSAGE_ID_FILE, th.getMessage());
            jSONObject.put("stack", Log.getStackTraceString(th));
            jSONObject.put("thread", thread.getName());
            a(jSONObject.toString());
        } catch (JSONException e) {
            Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("JSONException: ").append(e).toString());
        }
    }
}
