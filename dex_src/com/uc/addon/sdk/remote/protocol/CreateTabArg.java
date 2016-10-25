package com.uc.addon.sdk.remote.protocol;

import android.os.Bundle;

public class CreateTabArg implements BaseArg {
    public boolean isActive;
    public String url;

    public boolean checkArgs() {
        return this.url != null;
    }

    public void fromBundle(Bundle bundle) {
        this.url = bundle.getString("key_url");
        this.isActive = bundle.getBoolean("key_is_active");
    }

    public void toBundle(Bundle bundle) {
        bundle.putString("key_url", this.url);
        bundle.putBoolean("key_is_active", this.isActive);
    }
}
