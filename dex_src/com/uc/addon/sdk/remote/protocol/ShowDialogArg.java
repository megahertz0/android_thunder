package com.uc.addon.sdk.remote.protocol;

import android.os.Bundle;

public class ShowDialogArg implements BaseArg {
    public DialogBuilder dialogBuilder;

    public boolean checkArgs() {
        return this.dialogBuilder != null;
    }

    public void fromBundle(Bundle bundle) {
        bundle.setClassLoader(DialogBuilder.class.getClassLoader());
        this.dialogBuilder = (DialogBuilder) bundle.getParcelable("key_builder");
    }

    public void toBundle(Bundle bundle) {
        bundle.putParcelable("key_builder", this.dialogBuilder);
    }
}
