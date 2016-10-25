package com.sina.weibo.sdk.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import com.alipay.sdk.sys.a;
import com.sina.weibo.sdk.constant.WBConstants;
import com.sina.weibo.sdk.constant.WBConstants.Base;
import com.sina.weibo.sdk.utils.AidTask.AidInfo;
import com.taobao.accs.utl.UtilityImpl;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.tdlive.R;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class Utility {
    private static final String DEFAULT_CHARSET = "UTF-8";
    private static final String WEIBO_IDENTITY_ACTION = "com.sina.weibo.action.sdkidentity";

    public static Bundle parseUrl(String str) {
        try {
            URL url = new URL(str);
            Bundle decodeUrl = decodeUrl(url.getQuery());
            decodeUrl.putAll(decodeUrl(url.getRef()));
            return decodeUrl;
        } catch (MalformedURLException e) {
            return new Bundle();
        }
    }

    public static Bundle parseUri(String str) {
        try {
            return decodeUrl(new URI(str).getQuery());
        } catch (Exception e) {
            return new Bundle();
        }
    }

    public static Bundle decodeUrl(String str) {
        Bundle bundle = new Bundle();
        if (str != null) {
            String[] split = str.split(a.b);
            int length = split.length;
            for (int i = 0; i < length; i++) {
                String[] split2 = split[i].split("=");
                try {
                    bundle.putString(URLDecoder.decode(split2[0], DEFAULT_CHARSET), URLDecoder.decode(split2[1], DEFAULT_CHARSET));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        return bundle;
    }

    public static boolean isChineseLocale(Context context) {
        try {
            Locale locale = context.getResources().getConfiguration().locale;
            return Locale.CHINA.equals(locale) || Locale.CHINESE.equals(locale) || Locale.SIMPLIFIED_CHINESE.equals(locale) || Locale.TAIWAN.equals(locale);
        } catch (Exception e) {
            return true;
        }
    }

    public static String generateGUID() {
        return UUID.randomUUID().toString().replace(SocializeConstants.OP_DIVIDER_MINUS, com.umeng.a.d);
    }

    public static String getSign(Context context, String str) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, R.styleable.AppCompatTheme_imageButtonStyle);
            for (int i = 0; i < packageInfo.signatures.length; i++) {
                byte[] toByteArray = packageInfo.signatures[i].toByteArray();
                if (toByteArray != null) {
                    return MD5.hexdigest(toByteArray);
                }
            }
            return null;
        } catch (NameNotFoundException e) {
            return null;
        }
    }

    public static String safeString(String str) {
        return TextUtils.isEmpty(str) ? com.umeng.a.d : str;
    }

    public static String getAid(Context context, String str) {
        AidInfo aidSync = AidTask.getInstance(context).getAidSync(str);
        return aidSync != null ? aidSync.getAid() : com.umeng.a.d;
    }

    public static String generateUA(Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Build.MANUFACTURER).append(SocializeConstants.OP_DIVIDER_MINUS).append(Build.MODEL);
        stringBuilder.append("_");
        stringBuilder.append(VERSION.RELEASE);
        stringBuilder.append("_");
        stringBuilder.append("weibosdk");
        stringBuilder.append("_");
        stringBuilder.append(WBConstants.WEIBO_SDK_VERSION_CODE);
        stringBuilder.append("_android");
        return stringBuilder.toString();
    }

    public static String generateUAAid(Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Build.MANUFACTURER).append(SocializeConstants.OP_DIVIDER_MINUS).append(Build.MODEL);
        stringBuilder.append("__");
        stringBuilder.append("weibosdk");
        stringBuilder.append("__");
        try {
            stringBuilder.append(WBConstants.WEIBO_SDK_VERSION_CODE.replaceAll("\\s+", "_"));
        } catch (Exception e) {
            stringBuilder.append(UtilityImpl.NET_TYPE_UNKNOWN);
        }
        stringBuilder.append("__android__android").append(VERSION.RELEASE);
        return stringBuilder.toString();
    }

    public static void openWeiboActivity(Context context, String str, Bundle bundle) {
        try {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.putExtra(Base.APP_PKG, context.getPackageName());
            intent.setData(Uri.parse(str));
            intent.setFlags(268435456);
            intent.putExtras(bundle);
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
        }
    }

    public static Boolean isWeiBoVersionSupportNewPay(Context context) {
        boolean z = false;
        Intent intent = new Intent(WEIBO_IDENTITY_ACTION);
        intent.addCategory("android.intent.category.DEFAULT");
        List<ResolveInfo> queryIntentServices = context.getPackageManager().queryIntentServices(intent, 0);
        if (queryIntentServices == null || queryIntentServices.isEmpty()) {
            return Boolean.valueOf(false);
        }
        boolean z2 = false;
        for (ResolveInfo resolveInfo : queryIntentServices) {
            if (resolveInfo.serviceInfo != null && resolveInfo.serviceInfo.applicationInfo != null && !TextUtils.isEmpty(resolveInfo.serviceInfo.applicationInfo.packageName)) {
                try {
                    int i = context.getPackageManager().getPackageInfo(resolveInfo.serviceInfo.applicationInfo.packageName, 0).versionCode;
                } catch (NameNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        if (z2 >= true) {
            z = true;
        }
        return Boolean.valueOf(z);
    }
}
