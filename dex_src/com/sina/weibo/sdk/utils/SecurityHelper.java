package com.sina.weibo.sdk.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import com.sina.weibo.sdk.ApiUtils;
import com.sina.weibo.sdk.WeiboAppManager.WeiboInfo;
import com.sina.weibo.sdk.constant.WBConstants;
import com.sina.weibo.sdk.constant.WBConstants.Base;
import com.xunlei.tdlive.R;

public class SecurityHelper {
    public static boolean validateAppSignatureForIntent(Context context, Intent intent) {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            return false;
        }
        ResolveInfo resolveActivity = packageManager.resolveActivity(intent, 0);
        if (resolveActivity == null) {
            return false;
        }
        try {
            return containSign(packageManager.getPackageInfo(resolveActivity.activityInfo.packageName, R.styleable.AppCompatTheme_imageButtonStyle).signatures, WBConstants.WEIBO_SIGN);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static boolean checkResponseAppLegal(Context context, WeiboInfo weiboInfo, Intent intent) {
        if ((weiboInfo != null && weiboInfo.getSupportApi() <= 10352) || weiboInfo == null) {
            return true;
        }
        String stringExtra = intent != null ? intent.getStringExtra(Base.APP_PKG) : null;
        return (stringExtra == null || intent.getStringExtra(WBConstants.TRAN) == null || !ApiUtils.validateWeiboSign(context, stringExtra)) ? false : true;
    }

    public static boolean containSign(Signature[] signatureArr, String str) {
        if (signatureArr == null || str == null) {
            return false;
        }
        int length = signatureArr.length;
        for (int i = 0; i < length; i++) {
            if (str.equals(MD5.hexdigest(signatureArr[i].toByteArray()))) {
                return true;
            }
        }
        return false;
    }
}
