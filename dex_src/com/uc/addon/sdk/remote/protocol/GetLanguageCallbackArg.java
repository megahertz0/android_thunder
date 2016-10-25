package com.uc.addon.sdk.remote.protocol;

import android.os.Bundle;

public class GetLanguageCallbackArg implements BaseArg {
    private String a;
    public String language;

    public GetLanguageCallbackArg() {
        this.language = null;
        this.a = "language";
    }

    public boolean checkArgs() {
        return (this.language == null || this.language.length() == 0) ? false : true;
    }

    public void fromBundle(Bundle bundle) {
        this.language = bundle.getString(this.a);
    }

    public void toBundle(Bundle bundle) {
        bundle.putString(this.a, this.language);
    }
}
