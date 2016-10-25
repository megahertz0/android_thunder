package com.uc.addon.sdk.remote.protocol;

import android.os.Bundle;
import com.tencent.open.SocialConstants;

public class PageStartEventArg implements BaseArg {
    public int id;
    public String url;

    public boolean checkArgs() {
        return this.id >= 0;
    }

    public void fromBundle(Bundle bundle) {
        this.id = bundle.getInt("tab_id");
        this.url = bundle.getString(SocialConstants.PARAM_URL);
    }

    public void toBundle(Bundle bundle) {
        bundle.putInt("tab_id", this.id);
        bundle.putString(SocialConstants.PARAM_URL, this.url);
    }
}
