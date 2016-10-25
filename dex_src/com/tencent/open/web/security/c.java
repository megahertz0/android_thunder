package com.tencent.open.web.security;

import android.webkit.WebView;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.tencent.mm.sdk.modelbase.BaseResp.ErrCode;
import com.tencent.open.a.a;
import com.tencent.open.a.f;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.downloadprovider.web.base.core.BaseJsInterface;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: ProGuard
public class c extends a {
    private String d;

    public c(WebView webView, long j, String str, String str2) {
        super(webView, j, str);
        this.d = str2;
    }

    public void a(Object obj) {
        f.a("openSDK_LOG.SecureJsListener", new StringBuilder("-->onComplete, result: ").append(obj).toString());
    }

    public void a() {
        f.b("openSDK_LOG.SecureJsListener", "-->onNoMatchMethod...");
    }

    public void a(String str) {
        f.a("openSDK_LOG.SecureJsListener", new StringBuilder("-->onCustomCallback, js: ").append(str).toString());
        JSONObject jSONObject = new JSONObject();
        int i = 0;
        if (!com.tencent.open.c.c.a) {
            i = ErrCode.ERR_AUTH_DENIED;
        }
        try {
            jSONObject.put("result", i);
            jSONObject.put(IXAdRequestInfo.SN, this.b);
            jSONObject.put(SocializeConstants.JSON_DATA, str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        b(jSONObject.toString());
    }

    private void b(String str) {
        WebView webView = (WebView) this.a.get();
        if (webView != null) {
            StringBuffer stringBuffer = new StringBuffer(BaseJsInterface.JS_PREFIX);
            stringBuffer.append("if(!!").append(this.d).append("){");
            stringBuffer.append(this.d);
            stringBuffer.append(SocializeConstants.OP_OPEN_PAREN);
            stringBuffer.append(str);
            stringBuffer.append(")}");
            String toString = stringBuffer.toString();
            f.a("openSDK_LOG.SecureJsListener", new StringBuilder("-->callback, callback: ").append(toString).toString());
            webView.loadUrl(toString);
        }
    }
}
