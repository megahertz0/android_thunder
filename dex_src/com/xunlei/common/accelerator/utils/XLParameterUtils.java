package com.xunlei.common.accelerator.utils;

import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.xunlei.common.accelerator.bean.XLAccelUser;
import com.xunlei.xiazaibao.BuildConfig;

public class XLParameterUtils {
    private static int XLA_SEQ_NUM;
    private String mClientVersion;
    private String mPeerId;
    private String mVersion;

    static {
        XLA_SEQ_NUM = 268435456;
    }

    public XLParameterUtils(String str, String str2) {
        this.mPeerId = "ABCDEF0123456789";
        this.mClientVersion = "1.0.0.0";
        this.mVersion = "2.0";
        this.mClientVersion = str;
        this.mPeerId = str2;
    }

    public String getmPeerId() {
        return this.mPeerId;
    }

    private String getClientVersion() {
        return new StringBuilder("androidshoulei-").append(this.mClientVersion).toString();
    }

    public String getmVersion() {
        return this.mVersion;
    }

    private int getValidSeqNum() {
        int i = XLA_SEQ_NUM;
        XLA_SEQ_NUM = i + 1;
        return i;
    }

    private String getClientType() {
        return new StringBuilder("android-shouleiknplugin-").append(this.mVersion).toString();
    }

    public String getOSModel() {
        Object obj = Build.MODEL;
        if (TextUtils.isEmpty(obj)) {
            return new StringBuilder("android-").append(getAndroidVersion()).toString();
        }
        return new StringBuilder("android-").append(getAndroidVersion()).append(obj.replace(" ", BuildConfig.VERSION_NAME)).toString();
    }

    private String getAndroidVersion() {
        return VERSION.RELEASE + "." + VERSION.SDK_INT;
    }

    public String parameterToHttpAddress(String str, String str2, XLAccelUser xLAccelUser) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str).append(str2).append("?");
        stringBuilder.append("sequence=").append(getValidSeqNum()).append("&");
        stringBuilder.append("peerid=").append(getmPeerId()).append("&");
        stringBuilder.append("userid=").append(xLAccelUser.mUserID).append("&");
        stringBuilder.append("client_type=").append(getClientType()).append("&");
        stringBuilder.append("client_version=").append(getClientVersion()).append("&");
        stringBuilder.append("os=").append(getOSModel());
        return stringBuilder.toString();
    }

    public String getTryAccelInfoAddress(String str, String str2, XLAccelUser xLAccelUser) {
        return parameterToHttpAddress(str, str2, xLAccelUser) + "&sessionid=" + xLAccelUser.mSessionID;
    }

    public String getStartOrStopOrAliveAddress(String str, String str2, XLAccelUser xLAccelUser, String str3) {
        return getTryAccelInfoAddress(str, str2, xLAccelUser) + "&user_type=" + xLAccelUser.mUserType + "&dial_account=" + str3;
    }
}
