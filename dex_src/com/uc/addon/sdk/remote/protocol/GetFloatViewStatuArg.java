package com.uc.addon.sdk.remote.protocol;

import android.os.Bundle;

public class GetFloatViewStatuArg implements BaseArg {
    private String a;
    public boolean isShowing;

    public GetFloatViewStatuArg() {
        this.a = "isShowing";
        this.isShowing = false;
    }

    public boolean checkArgs() {
        return true;
    }

    public void fromBundle(Bundle bundle) {
        this.isShowing = bundle.getBoolean(this.a);
    }

    public void toBundle(Bundle bundle) {
        bundle.putBoolean(this.a, this.isShowing);
    }
}
