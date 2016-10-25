package com.xunlei.downloadprovider.member.payment.ui;

import android.os.Message;
import com.xunlei.downloadprovider.a.h.a;
import com.xunlei.downloadprovider.member.payment.external.PayUtil;
import com.xunlei.downloadprovider.personal.settings.HelpActivity;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.tdlive.R;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: PaymentH5Activity.java
final class an implements a {
    final /* synthetic */ PaymentH5Activity a;

    an(PaymentH5Activity paymentH5Activity) {
        this.a = paymentH5Activity;
    }

    public final void a(Message message) {
        JSONObject jSONObject;
        switch (message.what) {
            case JsInterface.MSG_JS_SHOW_PAYMENT_TYPE_WINDOW:
                if (message.obj instanceof String) {
                    PaymentH5Activity.b(this.a, (String) message.obj);
                    com.xunlei.downloadprovider.member.payment.a.a(this.a.j, this.a.d.c());
                }
            case JsInterface.MSG_JS_GET_PRICE_CONFIG:
                if (message.obj instanceof String) {
                    PaymentH5Activity.a(this.a, (String) message.obj);
                }
            case JsInterface.MSG_GOTO_PAYMENT_PROTOCOL_ACTIVITY:
                HelpActivity.a(this.a, "http://m.sjzhushou.com/ios_page/publish/vip_help/index.html", this.a.getResources().getString(2131231715));
            case JsInterface.MSG_GET_PAYMENT_USER_PARAMS:
                if (message.obj != null && (message.obj instanceof String)) {
                    String str = (String) message.obj;
                    Map hashMap = new HashMap();
                    String f = this.a.d.f();
                    hashMap.put("OperType", String.valueOf(PaymentH5Activity.a(this.a.i)));
                    hashMap.put("ExpireDate", f);
                    hashMap.put("From", String.valueOf(this.a.c.a.isFromKuaiNiao() ? R.styleable.Toolbar_contentInsetEnd : 0));
                    jSONObject = new JSONObject(hashMap);
                    this.a.e.a(PayUtil.a(str, jSONObject.toString()));
                }
            case JsInterface.MSG_REPORT_H5_PAGE_SHOW:
                if (message.obj != null && (message.obj instanceof String)) {
                    try {
                        jSONObject = new JSONObject((String) message.obj);
                        com.xunlei.downloadprovider.member.payment.a.a(this.a.j, this.a.d.c(), jSONObject.getInt("loadTime"), jSONObject.getString(anet.channel.strategy.dispatch.a.NET_TYPE), jSONObject.getString("pageType"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            default:
                break;
        }
    }
}
