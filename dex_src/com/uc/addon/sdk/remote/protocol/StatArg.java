package com.uc.addon.sdk.remote.protocol;

import android.os.Bundle;
import android.text.TextUtils;

public class StatArg implements BaseArg {
    private static String a;
    private static String b;
    private static String c;
    public String id;
    public boolean isMgrStat;
    public String key;

    static {
        a = "key_id";
        b = "key_key";
        c = "key_mgr";
    }

    public boolean checkArgs() {
        return (TextUtils.isEmpty(this.id) || TextUtils.isEmpty(this.key)) ? false : true;
    }

    public void fromBundle(Bundle bundle) {
        this.id = bundle.getString(a);
        this.key = bundle.getString(b);
        this.isMgrStat = bundle.getInt(c) != 0;
    }

    public void toBundle(Bundle bundle) {
        bundle.putString(a, this.id);
        bundle.putString(b, this.key);
        bundle.putInt(c, this.isMgrStat ? 1 : 0);
    }
}
