package com.uc.addon.sdk.remote.protocol;

import android.os.Bundle;

public class CreateTabCallbackArg implements BaseArg {
    public int tabId;

    public CreateTabCallbackArg() {
        this.tabId = -1;
    }

    public boolean checkArgs() {
        return this.tabId >= -2;
    }

    public void fromBundle(Bundle bundle) {
        this.tabId = bundle.getInt("key_tab_id");
    }

    public void toBundle(Bundle bundle) {
        bundle.putInt("key_tab_id", this.tabId);
    }
}
