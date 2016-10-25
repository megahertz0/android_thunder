package com.xunlei.downloadprovider.web.core;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import com.xunlei.downloadprovider.a.h.a;
import com.xunlei.downloadprovider.web.BrowserUtil;
import com.xunlei.downloadprovider.web.BrowserUtil.StartFromType;
import com.xunlei.tdlive.R;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: BottomTipController.java
final class b implements a {
    final /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    public final void a(Message message) {
        int i = 0;
        String string;
        switch (message.what) {
            case JsInterface.MSG_JS_OPEN_BROWSER_PAGE:
                string = message.getData().getString(JsInterface.URL_KEY);
                BrowserUtil.a();
                BrowserUtil.a(this.a.e, 0, string, true, null, true);
            case JsInterface.MSG_JS_OPEN_DETAIL_PAGE:
                Bundle data = message.getData();
                string = data.getString(JsInterface.URL_KEY);
                Object string2 = data.getString(JsInterface.MODULE_KEY);
                if (string2 == null) {
                    string2 = com.umeng.a.d;
                }
                BrowserUtil.a();
                Context a = this.a.e;
                if ("btdigg".equals(string2)) {
                    i = R.styleable.AppCompatTheme_actionModeCutDrawable;
                }
                BrowserUtil.a(a, string, null, i, null);
            case JsInterface.MSG_JS_DISABLE_BOTTOM_TIP:
                this.a.a = true;
            case JsInterface.MSG_JS_GET_DISPLAY_SNIFF_PAGE_URL:
                try {
                    JSONObject jSONObject = new JSONObject((String) message.obj);
                    BrowserUtil.a();
                    BrowserUtil.a(this.a.e, 0, jSONObject.optString("display_baidu_url"), false, StartFromType.outside_showed, true);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            default:
                break;
        }
    }
}
