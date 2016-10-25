package com.xunlei.tdlive;

import com.sina.weibo.sdk.constant.WBConstants;
import com.sina.weibo.sdk.register.mobile.SelectCountryActivity;
import com.tencent.open.SocialConstants;
import com.umeng.a;
import com.xunlei.tdlive.modal.JsonWrapper;

// compiled from: WebBrowserActivity.java
class ey extends a {
    final /* synthetic */ WebBrowserActivity a;

    ey(WebBrowserActivity webBrowserActivity) {
        this.a = webBrowserActivity;
        super();
    }

    public String a(String str, String str2) {
        JsonWrapper jsonWrapper = new JsonWrapper(str);
        this.a.mWebChromeClient.a(jsonWrapper.getString(SocialConstants.PARAM_URL, a.d), jsonWrapper.getString(SelectCountryActivity.EXTRA_COUNTRY_NAME, WBConstants.GAME_PARAMS_GAME_IMAGE_URL), str2);
        return null;
    }
}
