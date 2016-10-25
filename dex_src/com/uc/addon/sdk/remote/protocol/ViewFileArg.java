package com.uc.addon.sdk.remote.protocol;

import android.content.Intent;
import android.os.Bundle;

public class ViewFileArg implements BaseArg {
    public Intent intent;

    public boolean checkArgs() {
        return this.intent != null && this.intent.getAction().equals("android.intent.action.VIEW");
    }

    public void fromBundle(Bundle bundle) {
        this.intent = (Intent) bundle.getParcelable("intent");
    }

    public void toBundle(Bundle bundle) {
        bundle.putParcelable("intent", this.intent);
    }
}
