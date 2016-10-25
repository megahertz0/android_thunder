package com.uc.addon.sdk.remote.protocol;

import android.os.Bundle;

public class TabSimpleArg implements BaseArg {
    public int tabId;

    public boolean checkArgs() {
        return this.tabId >= 0;
    }

    public void fromBundle(Bundle bundle) {
        this.tabId = bundle.getInt("tab_id");
    }

    public void toBundle(Bundle bundle) {
        bundle.putInt("tab_id", this.tabId);
    }
}
