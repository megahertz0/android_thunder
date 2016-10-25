package com.uc.addon.sdk.remote.protocol;

import android.content.Intent;
import android.os.Bundle;

public class ShareArg implements BaseArg {
    private static String a;
    public Intent intent;

    static {
        a = "key_intent";
    }

    public boolean checkArgs() {
        return this.intent != null;
    }

    public void fromBundle(Bundle bundle) {
        this.intent = (Intent) bundle.getParcelable(a);
    }

    public void toBundle(Bundle bundle) {
        bundle.putParcelable(a, this.intent);
    }
}
