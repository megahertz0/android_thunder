package com.uc.addon.sdk.remote.protocol;

import android.os.Bundle;
import com.umeng.socialize.common.SocializeConstants;

public class TabRemoteEventArg implements BaseArg {
    public int id;

    public boolean checkArgs() {
        return this.id >= 0;
    }

    public void fromBundle(Bundle bundle) {
        this.id = bundle.getInt(SocializeConstants.WEIBO_ID);
    }

    public void toBundle(Bundle bundle) {
        bundle.putInt(SocializeConstants.WEIBO_ID, this.id);
    }
}
