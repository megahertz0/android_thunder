package com.uc.addon.sdk.remote.protocol;

import android.os.Bundle;

public class LoadJavaScriptArg implements BaseArg {
    public String addonId;
    public boolean bResgister;
    public String extensionName;
    public JSParam jsParam;
    public int tabID;

    public boolean checkArgs() {
        return !StringUtil.isEmpty(this.addonId) && this.tabID >= 0;
    }

    public void fromBundle(Bundle bundle) {
        bundle.setClassLoader(LoadJavaScriptArg.class.getClassLoader());
        this.addonId = bundle.getString("addon_id");
        this.extensionName = bundle.getString("extension_name");
        this.jsParam = (JSParam) bundle.getParcelable("js_param");
        this.tabID = bundle.getInt("tabID");
        this.bResgister = bundle.getBoolean("register_extension");
    }

    public void toBundle(Bundle bundle) {
        bundle.putString("addon_id", this.addonId);
        bundle.putString("extension_name", this.extensionName);
        bundle.putParcelable("js_param", this.jsParam);
        bundle.putInt("tabID", this.tabID);
        bundle.putBoolean("register_extension", this.bResgister);
    }
}
