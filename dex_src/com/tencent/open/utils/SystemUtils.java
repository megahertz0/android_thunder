package com.tencent.open.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.os.Build.VERSION;
import android.view.MotionEvent;
import anet.channel.security.ISecurity;
import com.taobao.accs.data.Message;
import com.tencent.connect.common.Constants;
import com.tencent.open.GameAppOperation;
import com.tencent.open.a.f;
import com.umeng.a;
import com.xunlei.tdlive.R;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.security.MessageDigest;

// compiled from: ProGuard
public class SystemUtils {
    public static final String ACTION_LOGIN = "action_login";
    public static final String ACTION_REQUEST_API = "action_request";
    public static final String ACTION_SHARE = "action_share";
    public static final String H5_SHARE_DATA = "h5_share_data";
    public static final String IS_LOGIN = "is_login";
    public static final String IS_QQ_MOBILE_SHARE = "is_qq_mobile_share";
    public static final String QQDATALINE_CALLBACK_ACTION = "sendToMyComputer";
    public static final String QQFAVORITES_CALLBACK_ACTION = "addToQQFavorites";
    public static final String QQ_SHARE_CALLBACK_ACTION = "shareToQQ";
    public static final String QQ_VERSION_NAME_4_2_0 = "4.2.0";
    public static final String QQ_VERSION_NAME_4_3_0 = "4.3.0";
    public static final String QQ_VERSION_NAME_4_5_0 = "4.5.0";
    public static final String QQ_VERSION_NAME_4_6_0 = "4.6.0";
    public static final String QQ_VERSION_NAME_5_0_0 = "5.0.0";
    public static final String QQ_VERSION_NAME_5_1_0 = "5.1.0";
    public static final String QQ_VERSION_NAME_5_2_0 = "5.2.0";
    public static final String QQ_VERSION_NAME_5_3_0 = "5.3.0";
    public static final String QQ_VERSION_NAME_5_9_5 = "5.9.5";
    public static final String QZONE_SHARE_CALLBACK_ACTION = "shareToQzone";
    public static final String TROOPBAR_CALLBACK_ACTION = "shareToTroopBar";

    public static String getAppVersionName(Context context, String str) {
        try {
            return context.getPackageManager().getPackageInfo(str, 0).versionName;
        } catch (NameNotFoundException e) {
            return null;
        }
    }

    public static int compareVersion(String str, String str2) {
        if (str == null && str2 == null) {
            return 0;
        }
        if (str != null && str2 == null) {
            return 1;
        }
        if (str == null && str2 != null) {
            return -1;
        }
        String[] split = str.split("\\.");
        String[] split2 = str2.split("\\.");
        int i = 0;
        while (i < split.length && i < split2.length) {
            try {
                int parseInt = Integer.parseInt(split[i]);
                int parseInt2 = Integer.parseInt(split2[i]);
                if (parseInt < parseInt2) {
                    return -1;
                }
                if (parseInt > parseInt2) {
                    return 1;
                }
                i++;
            } catch (NumberFormatException e) {
                return str.compareTo(str2);
            }
        }
        if (split.length > i) {
            return 1;
        }
        return split2.length > i ? -1 : 0;
    }

    public static boolean isAppSignatureValid(Context context, String str, String str2) {
        f.a("openSDK_LOG.SystemUtils", "OpenUi, validateAppSignatureForPackage");
        try {
            Signature[] signatureArr = context.getPackageManager().getPackageInfo(str, R.styleable.AppCompatTheme_imageButtonStyle).signatures;
            int length = signatureArr.length;
            for (int i = 0; i < length; i++) {
                if (Util.encrypt(signatureArr[i].toCharsString()).equals(str2)) {
                    return true;
                }
            }
            return false;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    public static String getAppSignatureMD5(Context context, String str) {
        String packageName;
        f.a("openSDK_LOG.SystemUtils", "OpenUi, getSignValidString");
        String str2 = a.d;
        try {
            packageName = context.getPackageName();
            Signature[] signatureArr = context.getPackageManager().getPackageInfo(packageName, R.styleable.AppCompatTheme_imageButtonStyle).signatures;
            MessageDigest instance = MessageDigest.getInstance(ISecurity.SIGN_ALGORITHM_MD5);
            instance.update(signatureArr[0].toByteArray());
            String toHexString = Util.toHexString(instance.digest());
            instance.reset();
            f.a("openSDK_LOG.SystemUtils", new StringBuilder("-->sign: ").append(toHexString).toString());
            instance.update(Util.getBytesUTF8(packageName + "_" + toHexString + "_" + str));
            packageName = Util.toHexString(instance.digest());
            try {
                instance.reset();
                f.a("openSDK_LOG.SystemUtils", new StringBuilder("-->signEncryped: ").append(packageName).toString());
            } catch (Exception e) {
                Throwable e2 = e;
                e2.printStackTrace();
                f.b("openSDK_LOG.SystemUtils", "OpenUi, getSignValidString error", e2);
                return packageName;
            }
        } catch (Throwable e3) {
            Throwable th = e3;
            packageName = str2;
            e2 = th;
            e2.printStackTrace();
            f.b("openSDK_LOG.SystemUtils", "OpenUi, getSignValidString error", e2);
            return packageName;
        }
        return packageName;
    }

    public static boolean isActivityExist(Context context, Intent intent) {
        return (context == null || intent == null || context.getPackageManager().queryIntentActivities(intent, 0).size() == 0) ? false : true;
    }

    public static String getAppName(Context context) {
        return context.getApplicationInfo().loadLabel(context.getPackageManager()).toString();
    }

    public static int compareQQVersion(Context context, String str) {
        return compareVersion(getAppVersionName(context, Constants.PACKAGE_QQ), str);
    }

    public static boolean checkMobileQQ(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(Constants.PACKAGE_QQ, 0);
        } catch (Throwable e) {
            f.b("openSDK_LOG.SystemUtils", "checkMobileQQ NameNotFoundException", e);
            e.printStackTrace();
            packageInfo = null;
        }
        if (packageInfo == null) {
            return false;
        }
        String str = packageInfo.versionName;
        try {
            f.b("MobileQQ verson", str);
            String[] split = str.split("\\.");
            int parseInt = Integer.parseInt(split[0]);
            return parseInt > 4 || (parseInt == 4 && Integer.parseInt(split[1]) > 0);
        } catch (Throwable e2) {
            f.b("openSDK_LOG.SystemUtils", "checkMobileQQ Exception", e2);
            e2.printStackTrace();
            return false;
        }
    }

    public static int getAndroidSDKVersion() {
        return VERSION.SDK_INT;
    }

    public static boolean isSupportMultiTouch() {
        Method[] declaredMethods = MotionEvent.class.getDeclaredMethods();
        int length = declaredMethods.length;
        Object obj = null;
        Object obj2 = null;
        for (int i = 0; i < length; i++) {
            Method method = declaredMethods[i];
            if (method.getName().equals("getPointerCount")) {
                boolean z = true;
            }
            if (method.getName().equals("getPointerId")) {
                boolean z2 = true;
            }
        }
        if (getAndroidSDKVersion() < 7) {
            return z && z2;
        } else {
            return true;
        }
    }

    @SuppressLint({"SdCardPath"})
    public static boolean extractSecureLib(String str, String str2, int i) {
        OutputStream outputStream = null;
        f.c("openSDK_LOG.SystemUtils", new StringBuilder("-->extractSecureLib, libName: ").append(str).toString());
        Context context = Global.getContext();
        if (context == null) {
            f.c("openSDK_LOG.SystemUtils", "-->extractSecureLib, global context is null. ");
            return false;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("secure_lib", 0);
        File file = new File(context.getFilesDir(), str2);
        if (file.exists()) {
            int i2 = sharedPreferences.getInt(GameAppOperation.QQFAV_DATALINE_VERSION, 0);
            f.c("openSDK_LOG.SystemUtils", new StringBuilder("-->extractSecureLib, libVersion: ").append(i).append(" | oldVersion: ").append(i2).toString());
            if (i == i2) {
                return true;
            }
        }
        File parentFile = file.getParentFile();
        if (parentFile != null && parentFile.mkdirs()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            InputStream open = context.getAssets().open(str);
            try {
                outputStream = context.openFileOutput(str2, 0);
                a(open, outputStream);
                Editor edit = sharedPreferences.edit();
                edit.putInt(GameAppOperation.QQFAV_DATALINE_VERSION, i);
                edit.commit();
                if (open != null) {
                    try {
                        open.close();
                    } catch (IOException e2) {
                    }
                }
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e3) {
                    }
                }
                return true;
            } catch (Exception e4) {
                e = e4;
                f.b("openSDK_LOG.SystemUtils", "-->extractSecureLib, when copy lib execption.", e);
                if (open != null) {
                    open.close();
                }
                if (outputStream != null) {
                    return false;
                }
                outputStream.close();
                return false;
            }
        } catch (Exception e5) {
            e = e5;
            open = null;
            try {
                Throwable e6;
                f.b("openSDK_LOG.SystemUtils", "-->extractSecureLib, when copy lib execption.", e6);
                if (open != null) {
                    try {
                        open.close();
                    } catch (IOException e7) {
                    }
                }
                if (outputStream != null) {
                    return false;
                }
                try {
                    outputStream.close();
                    return false;
                } catch (IOException e8) {
                    return false;
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                if (open != null) {
                    open.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                throw th2;
            }
        } catch (Throwable th3) {
            th2 = th3;
            open = null;
            if (open != null) {
                try {
                    open.close();
                } catch (IOException e9) {
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e10) {
                }
            }
            throw th2;
        }
    }

    @SuppressLint({"SdCardPath"})
    public static boolean isLibExtracted(String str, int i) {
        Context context = Global.getContext();
        if (context == null) {
            f.c("openSDK_LOG.SystemUtils", "-->isSecureLibExtracted, global context is null. ");
            return false;
        }
        File file = new File(context.getFilesDir(), str);
        SharedPreferences sharedPreferences = context.getSharedPreferences("secure_lib", 0);
        if (!file.exists()) {
            return false;
        }
        int i2 = sharedPreferences.getInt(GameAppOperation.QQFAV_DATALINE_VERSION, 0);
        f.c("openSDK_LOG.SystemUtils", new StringBuilder("-->extractSecureLib, libVersion: ").append(i).append(" | oldVersion: ").append(i2).toString());
        if (i == i2) {
            return true;
        }
        Editor edit = sharedPreferences.edit();
        edit.putInt(GameAppOperation.QQFAV_DATALINE_VERSION, i);
        edit.commit();
        return false;
    }

    private static long a(InputStream inputStream, OutputStream outputStream) throws IOException {
        long j = 0;
        byte[] bArr = new byte[8192];
        while (true) {
            int read = inputStream.read(bArr, 0, Message.FLAG_REQ_BIT2);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
                j += (long) read;
            } else {
                f.c("openSDK_LOG.SystemUtils", new StringBuilder("-->copy, copyed size is: ").append(j).toString());
                return j;
            }
        }
    }

    public static int getRequestCodeFromCallback(String str) {
        if (QQ_SHARE_CALLBACK_ACTION.equals(str)) {
            return Constants.REQUEST_QQ_SHARE;
        }
        if (QZONE_SHARE_CALLBACK_ACTION.equals(str)) {
            return Constants.REQUEST_QZONE_SHARE;
        }
        if (QQFAVORITES_CALLBACK_ACTION.equals(str)) {
            return Constants.REQUEST_QQ_FAVORITES;
        }
        if (QQDATALINE_CALLBACK_ACTION.equals(str)) {
            return Constants.REQUEST_SEND_TO_MY_COMPUTER;
        }
        if (TROOPBAR_CALLBACK_ACTION.equals(str)) {
            return Constants.REQUEST_SHARE_TO_TROOP_BAR;
        }
        if (ACTION_LOGIN.equals(str)) {
            return Constants.REQUEST_LOGIN;
        }
        return ACTION_REQUEST_API.equals(str) ? Constants.REQUEST_API : -1;
    }

    public static String getActionFromRequestcode(int i) {
        if (i == 10103) {
            return QQ_SHARE_CALLBACK_ACTION;
        }
        if (i == 10104) {
            return QZONE_SHARE_CALLBACK_ACTION;
        }
        if (i == 10105) {
            return QQFAVORITES_CALLBACK_ACTION;
        }
        if (i == 10106) {
            return QQDATALINE_CALLBACK_ACTION;
        }
        if (i == 10107) {
            return TROOPBAR_CALLBACK_ACTION;
        }
        if (i == 11101) {
            return ACTION_LOGIN;
        }
        return i == 10100 ? ACTION_REQUEST_API : null;
    }
}
