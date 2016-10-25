package com.uc.addon.sdk.remote.protocol;

import android.os.Bundle;
import com.tencent.open.SocialConstants;

public class GetIconArg implements BaseArg {
    private String a;
    public String mUrl;

    public GetIconArg() {
        this.mUrl = null;
        this.a = SocialConstants.PARAM_URL;
    }

    public boolean checkArgs() {
        return this.mUrl != null && this.mUrl.trim().length() > 0;
    }

    public void fromBundle(Bundle bundle) {
        this.mUrl = bundle.getString(this.a);
    }

    public void toBundle(Bundle bundle) {
        bundle.putString(this.a, this.mUrl);
    }
}
