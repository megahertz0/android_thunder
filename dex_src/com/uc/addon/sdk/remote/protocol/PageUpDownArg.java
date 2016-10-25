package com.uc.addon.sdk.remote.protocol;

import android.os.Bundle;
import com.umeng.socialize.common.SocializeConstants;

public class PageUpDownArg implements BaseArg {
    public int id;
    public boolean topBottom;

    public boolean checkArgs() {
        return this.id >= 0;
    }

    public void fromBundle(Bundle bundle) {
        this.id = bundle.getInt(SocializeConstants.WEIBO_ID);
        this.topBottom = bundle.getBoolean("top_bottom");
    }

    public void toBundle(Bundle bundle) {
        bundle.putInt(SocializeConstants.WEIBO_ID, this.id);
        bundle.putBoolean("top_bottom", this.topBottom);
    }
}
