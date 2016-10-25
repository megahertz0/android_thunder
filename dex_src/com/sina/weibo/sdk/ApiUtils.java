package com.sina.weibo.sdk;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.text.TextUtils;
import com.sina.weibo.sdk.constant.WBConstants;
import com.sina.weibo.sdk.utils.LogUtil;
import com.sina.weibo.sdk.utils.MD5;
import com.xunlei.tdlive.R;

public class ApiUtils {
    public static final int BUILD_INT = 10350;
    public static final int BUILD_INT_440 = 10355;
    public static final int BUILD_INT_VER_2_2 = 10351;
    public static final int BUILD_INT_VER_2_3 = 10352;
    public static final int BUILD_INT_VER_2_5 = 10353;
    private static final String TAG;

    static {
        TAG = ApiUtils.class.getName();
    }

    public static boolean validateWeiboSign(Context context, String str) {
        boolean z = false;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            z = containSign(context.getPackageManager().getPackageInfo(str, R.styleable.AppCompatTheme_imageButtonStyle).signatures, WBConstants.WEIBO_SIGN);
            return z;
        } catch (NameNotFoundException e) {
            return z;
        }
    }

    private static boolean containSign(Signature[] signatureArr, String str) {
        if (signatureArr == null || str == null) {
            return false;
        }
        int length = signatureArr.length;
        for (int i = 0; i < length; i++) {
            if (str.equals(MD5.hexdigest(signatureArr[i].toByteArray()))) {
                LogUtil.d(TAG, "check pass");
                return true;
            }
        }
        return false;
    }
}
