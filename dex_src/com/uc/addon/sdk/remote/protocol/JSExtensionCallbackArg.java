package com.uc.addon.sdk.remote.protocol;

import android.os.Bundle;
import com.alipay.sdk.packet.d;

public class JSExtensionCallbackArg implements BaseArg {
    public String argsJson;
    public String method;
    public int tabID;

    public boolean checkArgs() {
        return !StringUtil.isEmpty(this.method) && this.tabID >= 0;
    }

    public void fromBundle(Bundle bundle) {
        this.tabID = bundle.getInt("tabId");
        this.method = bundle.getString(d.q);
        this.argsJson = bundle.getString("args");
    }

    public void toBundle(Bundle bundle) {
        bundle.putInt("tabId", this.tabID);
        bundle.putString(d.q, this.method);
        bundle.putString("args", this.argsJson);
    }
}
