package com.uc.addon.sdk.remote.protocol;

import android.os.Bundle;
import com.tencent.open.SocialConstants;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.tdlive.WebBrowserActivity;

public class HistoryDeleteArg implements BaseArg {
    public String title;
    public int type;
    public String url;

    public HistoryDeleteArg() {
        this.type = 0;
        this.url = null;
        this.title = null;
    }

    public boolean checkArgs() {
        if (this.type != 0 && 1 != this.type) {
            DebugUtil.error("HistoryItem type invalid");
            return false;
        } else if (!StringUtil.isEmpty(this.url)) {
            return true;
        } else {
            DebugUtil.error("HistoryItem url can not be empty");
            return false;
        }
    }

    public void fromBundle(Bundle bundle) {
        this.type = bundle.getInt(JsInterface.FUNPLAY_AD_TRPE);
        this.url = bundle.getString(SocialConstants.PARAM_URL);
        this.title = bundle.getString(WebBrowserActivity.EXTRA_TITLE);
    }

    public void toBundle(Bundle bundle) {
        bundle.putInt(JsInterface.FUNPLAY_AD_TRPE, this.type);
        bundle.putString(SocialConstants.PARAM_URL, this.url);
        bundle.putString(WebBrowserActivity.EXTRA_TITLE, this.title);
    }
}
