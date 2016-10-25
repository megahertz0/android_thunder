package com.alipay.sdk.authjs;

import android.widget.Toast;
import com.alipay.sdk.authjs.a.a;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import java.util.Timer;
import org.json.JSONException;
import org.json.JSONObject;

final class d implements Runnable {
    final /* synthetic */ a a;
    final /* synthetic */ c b;

    d(c cVar, a aVar) {
        this.b = cVar;
        this.a = aVar;
    }

    public final void run() {
        int i;
        c cVar = this.b;
        a aVar = this.a;
        if (aVar != null && "toast".equals(aVar.k)) {
            JSONObject jSONObject = aVar.m;
            CharSequence optString = jSONObject.optString(ParamKey.CONTENT);
            int optInt = jSONObject.optInt("duration");
            i = 1;
            if (optInt < 2500) {
                i = 0;
            }
            Toast.makeText(cVar.b, optString, i).show();
            new Timer().schedule(new e(cVar, aVar), (long) i);
        }
        i = a.a;
        if (i != a.a) {
            try {
                this.b.a(this.a.i, i);
            } catch (JSONException e) {
            }
        }
    }
}
