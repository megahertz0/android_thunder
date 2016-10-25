package com.taobao.accs.utl;

import com.ut.mini.IUTApplication;
import com.ut.mini.core.sign.IUTRequestAuthentication;
import com.ut.mini.core.sign.UTSecuritySDKRequestAuthentication;
import com.ut.mini.crashhandler.IUTCrashCaughtListner;

// compiled from: Taobao
class i implements IUTApplication {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ UTMini c;

    i(UTMini uTMini, String str, String str2) {
        this.c = uTMini;
        this.a = str;
        this.b = str2;
    }

    public String getUTAppVersion() {
        return null;
    }

    public String getUTChannel() {
        return this.a;
    }

    public IUTRequestAuthentication getUTRequestAuthInstance() {
        return new UTSecuritySDKRequestAuthentication(this.b);
    }

    public boolean isUTLogEnable() {
        return false;
    }

    public boolean isAliyunOsSystem() {
        return false;
    }

    public IUTCrashCaughtListner getUTCrashCraughtListener() {
        return null;
    }

    public boolean isUTCrashHandlerDisable() {
        return false;
    }
}
