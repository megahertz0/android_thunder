package com.uc.addon.sdk.remote.protocol;

import android.os.Bundle;
import com.sina.weibo.sdk.register.mobile.SelectCountryActivity;

public class UnregisterEventReceiverArg implements BaseArg {
    public String addonId;
    public String extensionName;

    public boolean checkArgs() {
        return (this.addonId == null && this.extensionName == null) ? false : true;
    }

    public void fromBundle(Bundle bundle) {
        this.addonId = bundle.getString("addon_id");
        this.extensionName = bundle.getString(SelectCountryActivity.EXTRA_COUNTRY_NAME);
    }

    public void toBundle(Bundle bundle) {
        bundle.putString("addon_id", this.addonId);
        bundle.putString(SelectCountryActivity.EXTRA_COUNTRY_NAME, this.extensionName);
    }
}
