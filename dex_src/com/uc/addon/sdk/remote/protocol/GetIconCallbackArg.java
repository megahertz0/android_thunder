package com.uc.addon.sdk.remote.protocol;

import android.graphics.Bitmap;
import android.os.Bundle;

public class GetIconCallbackArg implements BaseArg {
    private String a;
    public Bitmap mIcon;

    public GetIconCallbackArg() {
        this.mIcon = null;
        this.a = "icon";
    }

    public boolean checkArgs() {
        return this.mIcon != null;
    }

    public void fromBundle(Bundle bundle) {
        try {
            this.mIcon = (Bitmap) bundle.getParcelable(this.a);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void toBundle(Bundle bundle) {
        try {
            if (this.mIcon != null) {
                bundle.putParcelable(this.a, this.mIcon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
