package com.alipay.sdk.authjs;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.Toast;
import com.alipay.sdk.authjs.a.a;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.umeng.socialize.common.SocializeConstants;
import java.util.Timer;
import org.json.JSONException;
import org.json.JSONObject;

public final class c {
    b a;
    Context b;

    private static /* synthetic */ int a(c cVar, a aVar) {
        if (aVar != null && "toast".equals(aVar.k)) {
            JSONObject jSONObject = aVar.m;
            CharSequence optString = jSONObject.optString(ParamKey.CONTENT);
            int optInt = jSONObject.optInt("duration");
            int i = 1;
            if (optInt < 2500) {
                i = 0;
            }
            Toast.makeText(cVar.b, optString, i).show();
            new Timer().schedule(new e(cVar, aVar), (long) i);
        }
        return a.a;
    }

    public c(Context context, b bVar) {
        this.b = context;
        this.a = bVar;
    }

    private void a(String str) {
        Object obj;
        try {
            JSONObject jSONObject = new JSONObject(str);
            Object string = jSONObject.getString(a.e);
            if (!TextUtils.isEmpty(string)) {
                JSONObject jSONObject2 = jSONObject.getJSONObject(SocializeConstants.OP_KEY);
                if (jSONObject2 instanceof JSONObject) {
                    jSONObject2 = jSONObject2;
                } else {
                    jSONObject2 = null;
                }
                String string2 = jSONObject.getString(a.g);
                String string3 = jSONObject.getString(a.d);
                a aVar = new a(a.b);
                aVar.j = string3;
                aVar.k = string2;
                aVar.m = jSONObject2;
                aVar.i = string;
                a(aVar);
            }
        } catch (Exception e) {
            obj = string;
            if (!TextUtils.isEmpty(r0)) {
                try {
                    a(r0, a.d);
                } catch (JSONException e2) {
                }
            }
        }
    }

    public final void a(a aVar) throws JSONException {
        if (TextUtils.isEmpty(aVar.k)) {
            a(aVar.i, a.c);
            return;
        }
        Runnable dVar = new d(this, aVar);
        if ((Looper.getMainLooper() == Looper.myLooper() ? 1 : null) != null) {
            dVar.run();
        } else {
            new Handler(Looper.getMainLooper()).post(dVar);
        }
    }

    public final void a(String str, int i) throws JSONException {
        if (!TextUtils.isEmpty(str)) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("error", i - 1);
            a aVar = new a(a.c);
            aVar.m = jSONObject;
            aVar.i = str;
            this.a.a(aVar);
        }
    }

    private static void a(Runnable runnable) {
        if ((Looper.getMainLooper() == Looper.myLooper() ? 1 : null) != null) {
            runnable.run();
        } else {
            new Handler(Looper.getMainLooper()).post(runnable);
        }
    }

    private int b(a aVar) {
        if (aVar != null && "toast".equals(aVar.k)) {
            JSONObject jSONObject = aVar.m;
            CharSequence optString = jSONObject.optString(ParamKey.CONTENT);
            int optInt = jSONObject.optInt("duration");
            int i = 1;
            if (optInt < 2500) {
                i = 0;
            }
            Toast.makeText(this.b, optString, i).show();
            new Timer().schedule(new e(this, aVar), (long) i);
        }
        return a.a;
    }

    private void c(a aVar) {
        JSONObject jSONObject = aVar.m;
        CharSequence optString = jSONObject.optString(ParamKey.CONTENT);
        int optInt = jSONObject.optInt("duration");
        int i = 1;
        if (optInt < 2500) {
            i = 0;
        }
        Toast.makeText(this.b, optString, i).show();
        new Timer().schedule(new e(this, aVar), (long) i);
    }
}
