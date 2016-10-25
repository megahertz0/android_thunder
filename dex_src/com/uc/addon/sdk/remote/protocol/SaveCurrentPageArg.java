package com.uc.addon.sdk.remote.protocol;

import android.os.Bundle;

public class SaveCurrentPageArg implements BaseArg {
    public static final int SAVE_TYPE_HTML_ALL = 2;
    public static final int SAVE_TYPE_HTML_ONLY = 0;
    public static final int SAVE_TYPE_MTHML = 3;
    public static final int SAVE_TYPE_TXT = 1;
    public String savePath;
    public int saveType;

    public boolean checkArgs() {
        return this.saveType >= 0 && this.saveType <= 3;
    }

    public void fromBundle(Bundle bundle) {
        this.savePath = bundle.getString("save_path");
        this.saveType = bundle.getInt("save_type");
    }

    public void toBundle(Bundle bundle) {
        bundle.putString("save_path", this.savePath);
        bundle.putInt("save_type", this.saveType);
    }
}
