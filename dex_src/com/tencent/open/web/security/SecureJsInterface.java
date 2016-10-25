package com.tencent.open.web.security;

import com.tencent.open.a.b;
import com.tencent.open.a.f;

// compiled from: ProGuard
public class SecureJsInterface extends b {
    public static boolean isPWDEdit;
    private String a;

    static {
        isPWDEdit = false;
    }

    public boolean customCallback() {
        return true;
    }

    public void curPosFromJS(String str) {
        f.b("openSDK_LOG.SecureJsInterface", new StringBuilder("-->curPosFromJS: ").append(str).toString());
        int i = -1;
        try {
            i = Integer.parseInt(str);
        } catch (Throwable e) {
            f.b("openSDK_LOG.SecureJsInterface", "-->curPosFromJS number format exception.", e);
        }
        if (i < 0) {
            throw new RuntimeException("position is illegal.");
        }
        boolean z = a.c;
        if (!a.b) {
            this.a = a.a;
            JniInterface.insetTextToArray(i, this.a, this.a.length());
            f.a("openSDK_LOG.SecureJsInterface", new StringBuilder("curPosFromJS mKey: ").append(this.a).toString());
        } else if (Boolean.valueOf(JniInterface.BackSpaceChar(a.b, i)).booleanValue()) {
            a.b = false;
        }
    }

    public void isPasswordEdit(String str) {
        f.c("openSDK_LOG.SecureJsInterface", new StringBuilder("-->is pswd edit, flag: ").append(str).toString());
        int i = -1;
        try {
            i = Integer.parseInt(str);
        } catch (Exception e) {
            f.e("openSDK_LOG.SecureJsInterface", new StringBuilder("-->is pswd edit exception: ").append(e.getMessage()).toString());
        }
        if (i != 0 && i != 1) {
            throw new RuntimeException("is pswd edit flag is illegal.");
        } else if (i == 0) {
            isPWDEdit = false;
        } else if (i == 1) {
            isPWDEdit = true;
        }
    }

    public void clearAllEdit() {
        f.c("openSDK_LOG.SecureJsInterface", "-->clear all edit.");
        try {
            JniInterface.clearAllPWD();
        } catch (Throwable e) {
            f.e("openSDK_LOG.SecureJsInterface", new StringBuilder("-->clear all edit exception: ").append(e.getMessage()).toString());
            throw new RuntimeException(e);
        }
    }

    public String getMD5FromNative() {
        f.c("openSDK_LOG.SecureJsInterface", "-->get md5 form native");
        try {
            String pWDKeyToMD5 = JniInterface.getPWDKeyToMD5(null);
            f.a("openSDK_LOG.SecureJsInterface", new StringBuilder("-->getMD5FromNative, MD5= ").append(pWDKeyToMD5).toString());
            return pWDKeyToMD5;
        } catch (Throwable e) {
            f.e("openSDK_LOG.SecureJsInterface", new StringBuilder("-->get md5 form native exception: ").append(e.getMessage()).toString());
            throw new RuntimeException(e);
        }
    }
}
