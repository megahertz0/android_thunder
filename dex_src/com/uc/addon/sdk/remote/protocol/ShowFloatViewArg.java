package com.uc.addon.sdk.remote.protocol;

import android.os.Bundle;

public class ShowFloatViewArg implements BaseArg {
    public RemoteFloatView remoteView;

    public boolean checkArgs() {
        return this.remoteView != null;
    }

    public void fromBundle(Bundle bundle) {
        bundle.setClassLoader(RemoteFloatView.class.getClassLoader());
        try {
            this.remoteView = (RemoteFloatView) bundle.getParcelable("key_builder");
        } catch (Exception e) {
            this.remoteView = null;
        }
    }

    public void toBundle(Bundle bundle) {
        bundle.putParcelable("key_builder", this.remoteView);
    }
}
