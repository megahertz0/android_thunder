package com.uc.addon.sdk.remote.protocol;

import android.os.Bundle;

public class UpdateTabArg implements BaseArg {
    public int tabId;
    public TabUpdateProperties tabProperties;

    public boolean checkArgs() {
        return this.tabId >= 0 && this.tabProperties != null;
    }

    public void fromBundle(Bundle bundle) {
        bundle.setClassLoader(TabUpdateProperties.class.getClassLoader());
        this.tabId = bundle.getInt("tab_id");
        this.tabProperties = (TabUpdateProperties) bundle.getParcelable("tab_properties");
    }

    public void toBundle(Bundle bundle) {
        bundle.putInt("tab_id", this.tabId);
        bundle.putParcelable("tab_properties", this.tabProperties);
    }
}
