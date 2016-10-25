package com.uc.addon.sdk.remote.protocol;

import android.content.IntentFilter;
import android.os.Bundle;

public class RegisterEventReceiverArg implements BaseArg {
    public String addonId;
    public String extensionName;
    public IntentFilter filter;

    public boolean checkArgs() {
        return (this.extensionName == null || this.filter == null || this.addonId == null) ? false : true;
    }

    public void fromBundle(Bundle bundle) {
        this.addonId = bundle.getString("addon_id");
        this.extensionName = bundle.getString("extension_name");
        this.filter = (IntentFilter) bundle.getParcelable("filter");
    }

    public void toBundle(Bundle bundle) {
        bundle.putString("addon_id", this.addonId);
        bundle.putString("extension_name", this.extensionName);
        bundle.putParcelable("filter", this.filter);
    }
}
