package com.uc.addon.sdk.remote.protocol;

import android.os.Bundle;

public class GetSelectionTextCallbackArg implements BaseArg {
    public String selectionText;

    public boolean checkArgs() {
        return true;
    }

    public void fromBundle(Bundle bundle) {
        this.selectionText = bundle.getString("key_selection_text");
    }

    public void toBundle(Bundle bundle) {
        bundle.putString("key_selection_text", this.selectionText);
    }
}
