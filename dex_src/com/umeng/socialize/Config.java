package com.umeng.socialize;

import android.app.Dialog;
import com.umeng.a;

public class Config {
    public static String Descriptor = null;
    public static String EntityKey = null;
    public static String EntityName = null;
    public static boolean IsToastTip = false;
    public static int LinkedInProfileScope = 0;
    public static int LinkedInShareCode = 0;
    public static boolean OpenEditor = false;
    public static String QQAPPNAME = null;
    public static int QQWITHQZONE = 0;
    public static String REDIRECT_URL = null;
    public static String SessionId = null;
    public static boolean ShareLocation = false;
    public static String UID = null;
    public static int UseCocos = 0;
    public static boolean WBBYQQ = false;
    private static String a = null;
    public static String appName = null;
    private static String b = null;
    public static int connectionTimeOut = 0;
    public static Dialog dialog = null;
    public static boolean dialogSwitch = false;
    public static float imageSize = 0.0f;
    public static boolean isIntentShareFB = false;
    public static boolean isLoadImgByCompress = false;
    public static boolean isloadUrl = false;
    public static final boolean mEncrypt = true;
    public static int readSocketTimeOut;
    public static boolean showShareBoardOnTop;

    static {
        OpenEditor = true;
        ShareLocation = true;
        UID = null;
        EntityKey = null;
        EntityName = null;
        WBBYQQ = true;
        Descriptor = "com.umeng.share";
        SessionId = null;
        QQWITHQZONE = 0;
        QQAPPNAME = a.d;
        dialog = null;
        UseCocos = 0;
        isloadUrl = false;
        imageSize = 3072.0f;
        a = a.d;
        b = a.d;
        REDIRECT_URL = "http://sns.whalecloud.com";
        IsToastTip = true;
        LinkedInProfileScope = 0;
        LinkedInShareCode = 0;
        dialogSwitch = true;
        isIntentShareFB = false;
        connectionTimeOut = 30000;
        readSocketTimeOut = 30000;
        showShareBoardOnTop = false;
        isLoadImgByCompress = true;
    }

    public static String getAdapterSDKVersion() {
        return b;
    }

    public static String getAdapterSDK() {
        return a;
    }

    public static void setAdapterSDKInfo(String str, String str2) {
        a = str;
        b = str2;
    }
}
