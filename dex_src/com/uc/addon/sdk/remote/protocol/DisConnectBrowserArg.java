package com.uc.addon.sdk.remote.protocol;

import android.os.Bundle;
import com.umeng.socialize.common.SocializeConstants;

public class DisConnectBrowserArg implements BaseArg {
    public String id;

    public boolean checkArgs() {
        return (this.id == null || this.id.length() == 0) ? false : true;
    }

    public void fromBundle(Bundle bundle) {
        this.id = bundle.getString(SocializeConstants.WEIBO_ID);
    }

    public void toBundle(Bundle bundle) {
        bundle.putString(SocializeConstants.WEIBO_ID, this.id);
    }
}
