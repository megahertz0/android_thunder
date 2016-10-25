package com.xunlei.tdlive;

import anet.channel.strategy.dispatch.a;
import com.taobao.accs.common.Constants;
import com.tencent.open.SocialConstants;
import com.xunlei.tdlive.modal.JsonWrapper;
import com.xunlei.tdlive.util.ac;

// compiled from: WebBrowserActivity.java
class ft extends a {
    final /* synthetic */ WebBrowserActivity a;

    ft(WebBrowserActivity webBrowserActivity) {
        this.a = webBrowserActivity;
        super();
    }

    public String a(String str, String str2) {
        JsonWrapper jsonWrapper = new JsonWrapper("{}");
        jsonWrapper.putString(SocialConstants.PARAM_APP_ID, ac.j() ? "1001" : "1003");
        jsonWrapper.putString("appver", ac.d());
        jsonWrapper.putString("appcode", (ac.j() ? ac.e() : R.styleable.AppCompatTheme_dividerVertical));
        jsonWrapper.putString(Constants.KEY_OS_VERSION, a.ANDROID);
        jsonWrapper.putString("osver", ac.c());
        jsonWrapper.putString(Constants.KEY_MODEL, ac.a(false));
        jsonWrapper.putString("deviceid", ac.g());
        jsonWrapper.putString(LogBuilder.KEY_CHANNEL, ac.j() ? ac.d("UMENG_CHANNEL") : "ThunderSDK");
        this.a.callJS(str2, jsonWrapper);
        return null;
    }
}
