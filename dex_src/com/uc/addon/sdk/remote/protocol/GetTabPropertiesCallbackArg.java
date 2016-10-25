package com.uc.addon.sdk.remote.protocol;

import android.os.Bundle;

public class GetTabPropertiesCallbackArg implements BaseArg {
    public TabProperties tabProperties;

    public boolean checkArgs() {
        return this.tabProperties != null;
    }

    public void fromBundle(Bundle bundle) {
        bundle.setClassLoader(TabProperties.class.getClassLoader());
        this.tabProperties = (TabProperties) bundle.getParcelable("tab_properties");
    }

    public void toBundle(Bundle bundle) {
        bundle.putParcelable("tab_properties", this.tabProperties);
    }
}
