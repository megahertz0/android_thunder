package com.umeng.socialize.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.TextUtils;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class OauthHelper {
    public static final String APP_ID = "appid";
    public static final String APP_KEY = "appkey";
    private static final String QQ_KEY = "qq_sso";
    private static final String TAG = "OauthHelper";

    public static boolean isAuthenticated(Context context, SHARE_MEDIA share_media) {
        if (context == null) {
            Log.w(TAG, "context\u5bf9\u8c61\u4e3a\u7a7a\uff0c\u8bf7\u4f20\u9012\u4e00\u4e2a\u975e\u7a7aContext\u5bf9\u8c61");
            return false;
        } else if (share_media != null && share_media != SHARE_MEDIA.GENERIC) {
            return !TextUtils.isEmpty(getUsid(context, share_media));
        } else {
            Log.w(TAG, "\u4f20\u9012\u7684\u68c0\u6d4b\u6388\u6743\u5e73\u53f0\u65e0\u6548");
            return false;
        }
    }

    public static boolean isAuthenticatedAndTokenNotExpired(Context context, SHARE_MEDIA share_media) {
        if (!isAuthenticated(context, share_media)) {
            return false;
        }
        return System.currentTimeMillis() / 1000 < getTokenExpiresIn(context, share_media);
    }

    public static Map<SHARE_MEDIA, String> getAuthenticatedPlatform(Context context) {
        Map<SHARE_MEDIA, String> hashMap = new HashMap();
        try {
            for (SHARE_MEDIA share_media : SHARE_MEDIA.getDefaultPlatform()) {
                if (isAuthenticated(context, share_media)) {
                    String usid = getUsid(context, share_media);
                    hashMap.put(share_media, usid);
                    Log.i(TAG, new StringBuilder("found platform ").append(share_media.toString()).append(" usid=").append(usid).toString());
                } else {
                    Log.i(TAG, new StringBuilder("No found oauthed platform ").append(share_media.toString()).toString());
                }
            }
            Log.i(TAG, new StringBuilder("found platform count ").append(hashMap.size()).toString());
            return hashMap;
        } catch (NullPointerException e) {
            Log.i(TAG, "no authenticated platform.......");
            return null;
        }
    }

    public static void saveTokenExpiresIn(Context context, SHARE_MEDIA share_media, String str) {
        if (context == null) {
            Log.w(TAG, "context is null when save expires in");
            return;
        }
        String toString = share_media.toString();
        if (TextUtils.isEmpty(toString)) {
            Log.w(TAG, "platform is null when save expires in ");
        } else if (!TextUtils.isEmpty(str)) {
            long longValue;
            try {
                longValue = Long.valueOf(str).longValue();
            } catch (Exception e) {
                longValue = 0;
            }
            if (longValue <= 0) {
                Log.w(TAG, toString + " expires in is " + longValue);
                return;
            }
            longValue += System.currentTimeMillis() / 1000;
            Editor edit = context.getSharedPreferences("umeng_socialize_token_expire_in", 0).edit();
            edit.putLong(toString, longValue);
            edit.commit();
        }
    }

    public static long getTokenExpiresIn(Context context, SHARE_MEDIA share_media) {
        if (context == null) {
            Log.w(TAG, "context is null when obtain expires in");
            return 0;
        }
        Object toString = share_media.toString();
        if (!TextUtils.isEmpty(toString)) {
            return context.getSharedPreferences("umeng_socialize_token_expire_in", 0).getLong(toString, 0);
        }
        Log.w(TAG, "platform is null when save expires in ");
        return 0;
    }

    public static void removeTokenExpiresIn(Context context, SHARE_MEDIA share_media) {
        if (context == null) {
            Log.w(TAG, "context is null when removing expires in");
            return;
        }
        Object toString = share_media.toString();
        if (TextUtils.isEmpty(toString)) {
            Log.w(TAG, "platform is null when save expires in ");
            return;
        }
        Editor edit = context.getSharedPreferences("umeng_socialize_token_expire_in", 0).edit();
        edit.remove(toString);
        edit.commit();
    }

    public static String getUsid(Context context, SHARE_MEDIA share_media) {
        if (share_media == null) {
            return BuildConfig.VERSION_NAME;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("umeng_socialize", 0);
        Object toString = share_media.toString();
        return !TextUtils.isEmpty(toString) ? sharedPreferences.getString(toString, BuildConfig.VERSION_NAME) : BuildConfig.VERSION_NAME;
    }

    public static void setUsid(Context context, SHARE_MEDIA share_media, String str) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("umeng_socialize", 0);
        synchronized (sharedPreferences) {
            Editor edit = sharedPreferences.edit();
            String toString = share_media.toString();
            if (!TextUtils.isEmpty(toString)) {
                edit.putString(toString, str);
                edit.commit();
                Log.d(TAG, new StringBuilder("Save platform ").append(toString).append("   ").append(str).toString());
            }
        }
    }

    public static void saveAccessToken(Context context, SHARE_MEDIA share_media, String str, String str2) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("umeng_socialize", 0);
        synchronized (sharedPreferences) {
            Editor edit = sharedPreferences.edit();
            String toString = share_media.toString();
            if (!TextUtils.isEmpty(toString)) {
                edit.putString(toString + "_ak", str);
                edit.putString(toString + "_as", str2);
                edit.commit();
            }
        }
    }

    public static String[] getAccessToken(Context context, SHARE_MEDIA share_media) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("umeng_socialize", 0);
        String toString = share_media.toString();
        String str = toString + "_ak";
        toString = toString + "_as";
        if (!sharedPreferences.contains(str) || !sharedPreferences.contains(toString)) {
            return new String[0];
        }
        str = sharedPreferences.getString(str, BuildConfig.VERSION_NAME);
        toString = sharedPreferences.getString(toString, BuildConfig.VERSION_NAME);
        return new String[]{str, toString};
    }

    public static void saveQQAccessToken(Context context, JSONObject jSONObject) {
        saveQQAccessToken(context, jSONObject.optString(XiaomiOAuthConstants.EXTRA_ACCESS_TOKEN_2, BuildConfig.VERSION_NAME), jSONObject.optString(SocializeProtocolConstants.PROTOCOL_KEY_OPENID, BuildConfig.VERSION_NAME), jSONObject.optString(XiaomiOAuthConstants.EXTRA_EXPIRES_IN_2, BuildConfig.VERSION_NAME));
    }

    public static void saveQQAccessToken(Context context, Bundle bundle) {
        saveQQAccessToken(context, bundle.getString(XiaomiOAuthConstants.EXTRA_ACCESS_TOKEN_2), bundle.getString(SocializeProtocolConstants.PROTOCOL_KEY_OPENID), bundle.getString(XiaomiOAuthConstants.EXTRA_EXPIRES_IN_2));
    }

    private static void saveQQAccessToken(Context context, String str, String str2, String str3) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("umeng_socialize_qq", 0);
        synchronized (sharedPreferences) {
            Editor edit = sharedPreferences.edit();
            if (!(TextUtils.isEmpty(str) && TextUtils.isEmpty(str2))) {
                edit.putString("qq_sso_token", str);
                edit.putString("qq_sso_openid", str2);
                edit.putString("qq_sso_expires_in", calExpireTime(str3));
                edit.commit();
                Log.d(TAG, "### Saved QQ Token.");
            }
        }
    }

    public static String[] getAccessTokenForQQ(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("umeng_socialize_qq", 0);
        String str = "qq_sso_token";
        String str2 = "qq_sso_openid";
        String str3 = "qq_sso_expires_in";
        if (!sharedPreferences.contains(str) || !sharedPreferences.contains(str2)) {
            return null;
        }
        str = sharedPreferences.getString(str, BuildConfig.VERSION_NAME);
        str2 = sharedPreferences.getString(str2, BuildConfig.VERSION_NAME);
        String string = sharedPreferences.getString(str3, BuildConfig.VERSION_NAME);
        Log.d(TAG, new StringBuilder("get QQ Token.").append(string).toString());
        if (isQQAuthExpired(string)) {
            return null;
        }
        return new String[]{str, str2, string};
    }

    private static String calExpireTime(String str) {
        return String.valueOf((System.currentTimeMillis() / 1000) + Long.parseLong(str));
    }

    public static boolean isQQAuthExpired(String str) {
        return System.currentTimeMillis() / 1000 >= Long.parseLong(str);
    }

    public static void remove(Context context, SHARE_MEDIA share_media) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("umeng_socialize", 0);
        synchronized (sharedPreferences) {
            Editor edit = sharedPreferences.edit();
            edit.remove(share_media.toString());
            edit.remove(share_media.toString() + "_ak");
            edit.remove(share_media.toString() + "_as");
            edit.commit();
            Log.d(TAG, new StringBuilder("Remove platform ").append(share_media.toString()).toString());
        }
        deleteRefreshToken(context, share_media);
    }

    public static void saveAppidAndAppkey(Context context, String str, String str2) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("umeng_socialize_qq", 0);
        synchronized (sharedPreferences) {
            Editor edit = sharedPreferences.edit();
            edit.putString(APP_ID, str);
            edit.putString(str2, str2);
            edit.commit();
        }
    }

    public static Map<String, String> getAppIdAndAppkey(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("umeng_socialize_qq", 0);
        String string = sharedPreferences.getString(APP_ID, BuildConfig.VERSION_NAME);
        String string2 = sharedPreferences.getString(APP_KEY, BuildConfig.VERSION_NAME);
        Map<String, String> hashMap = new HashMap();
        hashMap.put(APP_ID, string);
        hashMap.put(APP_KEY, string2);
        return hashMap;
    }

    public static void saveRefreshToken(Context context, SHARE_MEDIA share_media, String str) {
        Editor edit = context.getSharedPreferences("umeng_socialize_refresh_token", 0).edit();
        edit.putString(share_media.toString(), str);
        edit.commit();
    }

    public static void deleteRefreshToken(Context context, SHARE_MEDIA share_media) {
        Editor edit = context.getSharedPreferences("umeng_socialize_refresh_token", 0).edit();
        edit.remove(share_media.toString());
        edit.commit();
        edit = context.getSharedPreferences("umeng_socialize_refresh_token_expires", 0).edit();
        edit.remove(share_media.toString());
        edit.commit();
    }

    public static String getRefreshToken(Context context, SHARE_MEDIA share_media) {
        return context.getSharedPreferences("umeng_socialize_refresh_token", 0).getString(share_media.toString(), BuildConfig.VERSION_NAME);
    }

    public static void saveRefreshTokenExpires(Context context, SHARE_MEDIA share_media, long j) {
        long currentTimeMillis = (System.currentTimeMillis() / 1000) + j;
        Editor edit = context.getSharedPreferences("umeng_socialize_refresh_token_expires", 0).edit();
        edit.putLong(share_media.toString(), currentTimeMillis);
        edit.commit();
    }

    public static boolean isRefreshTokenNotExpired(Context context, SHARE_MEDIA share_media) {
        return context.getSharedPreferences("umeng_socialize_refresh_token_expires", 0).getLong(share_media.toString(), 0) > System.currentTimeMillis() / 1000;
    }
}
