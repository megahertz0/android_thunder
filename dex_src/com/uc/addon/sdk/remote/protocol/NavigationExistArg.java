package com.uc.addon.sdk.remote.protocol;

import android.os.Bundle;
import com.tencent.open.SocialConstants;
import com.umeng.a;
import com.xunlei.tdlive.WebBrowserActivity;

public class NavigationExistArg implements BaseArg {
    public String title;
    public String url;

    public NavigationExistArg() {
        this.title = null;
        this.url = null;
    }

    public boolean checkArgs() {
        return (this.title == null || a.d.equals(this.title.trim()) || this.url == null || a.d.equals(this.url.trim())) ? false : true;
    }

    public void fromBundle(Bundle bundle) {
        this.title = bundle.getString(WebBrowserActivity.EXTRA_TITLE);
        this.url = bundle.getString(SocialConstants.PARAM_URL);
    }

    public void toBundle(Bundle bundle) {
        bundle.putString(WebBrowserActivity.EXTRA_TITLE, this.title);
        bundle.putString(SocialConstants.PARAM_URL, this.url);
    }
}
