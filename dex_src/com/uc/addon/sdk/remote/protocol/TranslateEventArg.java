package com.uc.addon.sdk.remote.protocol;

import android.os.Bundle;
import android.text.TextUtils;

public class TranslateEventArg implements BaseArg {
    public String text;

    public boolean checkArgs() {
        return !TextUtils.isEmpty(this.text);
    }

    public void fromBundle(Bundle bundle) {
        this.text = bundle.getString("key_text");
    }

    public void toBundle(Bundle bundle) {
        bundle.putString("key_text", this.text);
    }
}
