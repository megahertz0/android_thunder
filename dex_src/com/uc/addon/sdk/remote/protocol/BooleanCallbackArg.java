package com.uc.addon.sdk.remote.protocol;

import android.os.Bundle;

public class BooleanCallbackArg implements BaseArg {
    public boolean result;

    public BooleanCallbackArg() {
        this.result = false;
    }

    public boolean checkArgs() {
        return true;
    }

    public void fromBundle(Bundle bundle) {
        this.result = bundle.getBoolean("result");
    }

    public void toBundle(Bundle bundle) {
        bundle.putBoolean("result", this.result);
    }
}
