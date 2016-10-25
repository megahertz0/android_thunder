package com.uc.addon.sdk.remote.protocol;

import android.os.Bundle;
import android.text.TextUtils;

public class DownloadEventArg implements BaseArg {
    public String filename;
    public String path;
    public String url;

    public boolean checkArgs() {
        return (TextUtils.isEmpty(this.filename) && TextUtils.isEmpty(this.url) && TextUtils.isEmpty(this.path)) ? false : true;
    }

    public void fromBundle(Bundle bundle) {
        this.filename = bundle.getString("key_filename");
        this.url = bundle.getString("key_url");
        this.path = bundle.getString("key_path");
    }

    public void toBundle(Bundle bundle) {
        bundle.putString("key_filename", this.filename);
        bundle.putString("key_url", this.url);
        bundle.putString("key_path", this.path);
    }
}
