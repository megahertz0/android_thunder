package com.uc.addon.sdk.remote;

import android.os.Bundle;
import com.uc.addon.sdk.remote.protocol.BaseArg;

public class DownloadTaskv1 implements BaseArg {
    private static String a;
    private static String b;
    private static String c;
    private static String d;
    public boolean ask;
    public String fileName;
    public String filePath;
    public String url;

    static {
        a = "key_url";
        b = "key_filename";
        c = "key_ASK";
        d = "key_filepath";
    }

    public DownloadTaskv1() {
        this.ask = false;
    }

    public boolean checkArgs() {
        return this.url != null;
    }

    public void fromBundle(Bundle bundle) {
        this.url = bundle.getString(a);
        this.fileName = bundle.getString(b);
        this.ask = bundle.getBoolean(c);
        this.filePath = bundle.getString(d);
    }

    public void toBundle(Bundle bundle) {
        bundle.putString(a, this.url);
        bundle.putString(b, this.fileName);
        bundle.putBoolean(c, this.ask);
        bundle.putString(d, this.filePath);
    }
}
