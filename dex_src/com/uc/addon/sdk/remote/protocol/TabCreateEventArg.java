package com.uc.addon.sdk.remote.protocol;

import android.os.Bundle;

public class TabCreateEventArg implements BaseArg {
    public int id;
    public boolean isActive;

    public boolean checkArgs() {
        return this.id >= 0;
    }

    public void fromBundle(Bundle bundle) {
        this.id = bundle.getInt("tab_id");
        this.isActive = bundle.getBoolean("tab_active");
    }

    public void toBundle(Bundle bundle) {
        bundle.putInt("tab_id", this.id);
        bundle.putBoolean("tab_active", this.isActive);
    }
}
