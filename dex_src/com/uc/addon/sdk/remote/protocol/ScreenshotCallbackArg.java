package com.uc.addon.sdk.remote.protocol;

import android.os.Bundle;

public class ScreenshotCallbackArg implements BaseArg {
    public String path;

    public boolean checkArgs() {
        return this.path != null;
    }

    public void fromBundle(Bundle bundle) {
        this.path = bundle.getString("key_path");
    }

    public void toBundle(Bundle bundle) {
        bundle.putString("key_path", this.path);
    }
}
