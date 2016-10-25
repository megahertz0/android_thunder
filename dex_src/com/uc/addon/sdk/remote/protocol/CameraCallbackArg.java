package com.uc.addon.sdk.remote.protocol;

import android.os.Bundle;

public class CameraCallbackArg implements BaseArg {
    public String bitmapPath;

    public boolean checkArgs() {
        return true;
    }

    public void fromBundle(Bundle bundle) {
        this.bitmapPath = bundle.getString("callback_image_key");
    }

    public void toBundle(Bundle bundle) {
        bundle.putString("callback_image_key", this.bitmapPath);
    }
}
