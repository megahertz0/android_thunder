package com.uc.addon.sdk.remote.protocol;

import android.os.Bundle;

public class FileManagerDirectoryOpenArg implements BaseArg {
    public String path;

    public boolean checkArgs() {
        if (!StringUtil.isEmpty(this.path)) {
            return true;
        }
        DebugUtil.error("FileManagerDirectoryOpen path can't not be null");
        return false;
    }

    public void fromBundle(Bundle bundle) {
        this.path = bundle.getString("path");
    }

    public void toBundle(Bundle bundle) {
        bundle.putString("path", this.path);
    }
}
