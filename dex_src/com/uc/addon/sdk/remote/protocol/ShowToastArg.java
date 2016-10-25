package com.uc.addon.sdk.remote.protocol;

import android.os.Bundle;

public class ShowToastArg implements BaseArg {
    public ToastBuilder toastBuilder;

    public boolean checkArgs() {
        return this.toastBuilder != null;
    }

    public void fromBundle(Bundle bundle) {
        bundle.setClassLoader(ToastBuilder.class.getClassLoader());
        this.toastBuilder = (ToastBuilder) bundle.getParcelable("key_toast_builder");
    }

    public void toBundle(Bundle bundle) {
        bundle.putParcelable("key_toast_builder", this.toastBuilder);
    }
}
