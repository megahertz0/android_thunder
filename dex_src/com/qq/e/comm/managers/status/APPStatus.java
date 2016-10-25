package com.qq.e.comm.managers.status;

import android.content.Context;
import com.qq.e.comm.util.StringUtil;

public class APPStatus {
    private String a;
    private Context b;

    public APPStatus(String str, Context context) {
        this.a = str;
        this.b = context;
    }

    public String getAPPID() {
        return this.a;
    }

    public String getAPPName() {
        return this.b.getPackageName();
    }

    public String getAPPVersion() {
        String aPPName = getAPPName();
        if (StringUtil.isEmpty(aPPName)) {
            return null;
        }
        try {
            return this.b.getPackageManager().getPackageInfo(aPPName, 0).versionName;
        } catch (Exception e) {
            return null;
        }
    }
}
