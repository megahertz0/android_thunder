package com.umeng.socialize.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Images.Media;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.view.Display;
import android.view.WindowManager;
import com.alipay.sdk.sys.a;
import com.umeng.socialize.common.ResContainer;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import org.android.spdy.SpdyAgent;
import org.json.JSONObject;

public class SocializeUtils {
    protected static final String TAG = "SocializeUtils";
    public static Set<Uri> deleteUris;
    private static Pattern mDoubleByte_Pattern;
    private static int smDip;

    static {
        deleteUris = new HashSet();
        mDoubleByte_Pattern = null;
        smDip = 0;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getAppkey(android.content.Context r4) {
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.socialize.utils.SocializeUtils.getAppkey(android.content.Context):java.lang.String");
        /*
        r0 = com.umeng.socialize.common.SocializeConstants.APPKEY;
        r1 = android.text.TextUtils.isEmpty(r0);
        if (r1 == 0) goto L_0x0027;
    L_0x0008:
        r1 = r4.getPackageManager();	 Catch:{ Exception -> 0x0032 }
        r2 = r4.getPackageName();	 Catch:{ Exception -> 0x0032 }
        r3 = 128; // 0x80 float:1.794E-43 double:6.32E-322;
        r1 = r1.getApplicationInfo(r2, r3);	 Catch:{ Exception -> 0x0032 }
        if (r1 == 0) goto L_0x0027;
    L_0x0018:
        r1 = r1.metaData;	 Catch:{ Exception -> 0x0032 }
        r2 = "UMENG_APPKEY";
        r1 = r1.get(r2);	 Catch:{ Exception -> 0x0032 }
        if (r1 == 0) goto L_0x0028;
    L_0x0023:
        r0 = r1.toString();	 Catch:{ Exception -> 0x0032 }
    L_0x0027:
        return r0;
    L_0x0028:
        r1 = "com.umeng.socialize";
        r2 = "Could not read UMENG_APPKEY meta-data from AndroidManifest.xml.";
        com.umeng.socialize.utils.Log.i(r1, r2);	 Catch:{ Exception -> 0x0032 }
        goto L_0x0027;
    L_0x0032:
        r1 = move-exception;
        r2 = "com.umeng.socialize";
        r3 = "Could not read UMENG_APPKEY meta-data from AndroidManifest.xml.";
        com.umeng.socialize.utils.Log.i(r2, r3, r1);
        goto L_0x0027;
        */
    }

    public static void safeCloseDialog(Dialog dialog) {
        if (dialog != null) {
            try {
                if (dialog.isShowing()) {
                    Activity ownerActivity = dialog.getOwnerActivity();
                    if (ownerActivity != null && !ownerActivity.isFinishing()) {
                        dialog.dismiss();
                    }
                }
            } catch (Exception e) {
                Log.e(TAG, "dialog dismiss error", e);
            }
        }
    }

    public static void safeShowDialog(Dialog dialog) {
        if (dialog != null) {
            try {
                if (!dialog.isShowing()) {
                    Activity ownerActivity = dialog.getOwnerActivity();
                    if (ownerActivity != null && !ownerActivity.isFinishing()) {
                        dialog.show();
                    }
                }
            } catch (Exception e) {
                Log.e(TAG, "dialog show error", e);
            }
        }
    }

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

    public static Bundle decodeUrl(String str) {
        Bundle bundle = new Bundle();
        if (str != null) {
            String[] split = str.split(a.b);
            int length = split.length;
            for (int i = 0; i < length; i++) {
                String[] split2 = split[i].split("=");
                bundle.putString(URLDecoder.decode(split2[0]), URLDecoder.decode(split2[1]));
            }
        }
        return bundle;
    }

    public static int countContentLength(String str) {
        Object trim = str.trim();
        int i = 0;
        while (getDoubleBytePattern().matcher(trim).find()) {
            i++;
        }
        int length = trim.length() - i;
        return length % 2 != 0 ? i + ((length + 1) / 2) : i + (length / 2);
    }

    private static Pattern getDoubleBytePattern() {
        if (mDoubleByte_Pattern == null) {
            mDoubleByte_Pattern = Pattern.compile("[^\\x00-\\xff]");
        }
        return mDoubleByte_Pattern;
    }

    public static Object[] readSIMCard(Context context) {
        try {
            Object[] objArr = new Object[3];
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            objArr[0] = Boolean.valueOf(false);
            switch (telephonyManager.getSimState()) {
                case SpdyAgent.ACCS_TEST_SERVER:
                    objArr[0] = Boolean.valueOf(true);
                    objArr[1] = "\u672a\u77e5\u72b6\u6001";
                    break;
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    objArr[1] = "\u65e0\u5361";
                    break;
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    objArr[1] = "\u9700\u8981PIN\u89e3\u9501";
                    break;
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    objArr[1] = "\u9700\u8981PUK\u89e3\u9501";
                    break;
                case XZBDevice.DOWNLOAD_LIST_ALL:
                    objArr[1] = "\u9700\u8981NetworkPIN\u89e3\u9501";
                    break;
                case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                    objArr[0] = Boolean.valueOf(true);
                    objArr[1] = "\u826f\u597d";
                    break;
            }
            return objArr;
        } catch (Exception e) {
            Log.e(TAG, new StringBuilder("cannot read SIM card. [").append(e.toString()).append("]").toString());
            return null;
        }
    }

    public static boolean isGoogleMapExist() {
        try {
            Class.forName("com.google.android.maps.MapActivity");
            return true;
        } catch (Exception e) {
            Log.w(TAG, "The device has no google map lib!");
            return false;
        }
    }

    public static int[] getFloatWindowSize(Context context) {
        ResContainer resContainer = ResContainer.get(context);
        Resources resources = context.getResources();
        return new int[]{(int) resources.getDimension(resContainer.dimen("umeng_socialize_pad_window_width")), (int) resources.getDimension(resContainer.dimen("umeng_socialize_pad_window_height"))};
    }

    public static boolean isFloatWindowStyle(Context context) {
        if (SocializeConstants.SUPPORT_PAD) {
            if (smDip == 0) {
                WindowManager windowManager = (WindowManager) context.getSystemService("window");
                Display defaultDisplay = windowManager.getDefaultDisplay();
                int width = defaultDisplay.getWidth();
                int height = defaultDisplay.getHeight();
                if (width <= height) {
                    height = width;
                }
                DisplayMetrics displayMetrics = new DisplayMetrics();
                windowManager.getDefaultDisplay().getMetrics(displayMetrics);
                smDip = (int) ((((float) height) / displayMetrics.density) + 0.5f);
            }
            if ((context.getResources().getConfiguration().screenLayout & 15) >= 3 && smDip >= 550) {
                return true;
            }
        }
        return false;
    }

    public static Uri insertImage(Context context, String str) {
        if (TextUtils.isEmpty(str) || !new File(str).exists()) {
            return null;
        }
        try {
            Object insertImage = Media.insertImage(context.getContentResolver(), str, "umeng_social_shareimg", null);
            return !TextUtils.isEmpty(insertImage) ? Uri.parse(insertImage) : null;
        } catch (Exception e) {
            Log.e(SocializeConstants.COMMON_TAG, com.umeng.a.d, e);
            return null;
        } catch (Exception e2) {
            Log.e(SocializeConstants.COMMON_TAG, com.umeng.a.d, e2);
            return null;
        }
    }

    public static void deleteUriImage(Context context, Set<Uri> set) {
        Set<String> set2 = (Set) getObject(BitmapUtils.PATH + SocializeConstants.FILE_URI_NAME);
        if (set2 != null && set2.size() > 0) {
            for (String str : set2) {
                set.add(Uri.parse(str));
            }
        }
        if (set != null && set.size() > 0) {
            for (Uri uri : set) {
                context.getContentResolver().delete(uri, null, null);
            }
            set.clear();
        } else if (set == null) {
            HashSet hashSet = new HashSet();
        }
    }

    public static void saveObject(Object obj, String str) {
        try {
            File file = new File(str);
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            objectOutputStream.writeObject(obj);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static <T> T getObject(String str) {
        ObjectInputStream objectInputStream;
        Exception e;
        try {
            File file = new File(str);
            if (!file.exists()) {
                return null;
            }
            objectInputStream = new ObjectInputStream(new FileInputStream(file));
            try {
                T readObject = objectInputStream.readObject();
                if (readObject != null) {
                    try {
                        objectInputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                    return readObject;
                }
                try {
                    objectInputStream.close();
                    return null;
                } catch (IOException e3) {
                    e3.printStackTrace();
                    return null;
                }
            } catch (Exception e4) {
                e = e4;
                e.printStackTrace();
                if (objectInputStream != null) {
                    return null;
                }
                objectInputStream.close();
                return null;
            }
        } catch (Exception e5) {
            e = e5;
            objectInputStream = null;
            try {
                e.printStackTrace();
                if (objectInputStream != null) {
                    return null;
                }
                try {
                    objectInputStream.close();
                    return null;
                } catch (IOException e32) {
                    e32.printStackTrace();
                    return null;
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                throw th2;
            }
        } catch (Throwable th3) {
            objectInputStream = null;
            th2 = th3;
            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                } catch (IOException e322) {
                    e322.printStackTrace();
                }
            }
            throw th2;
        }
    }

    public static int dip2Px(Context context, float f) {
        return (int) ((context.getResources().getDisplayMetrics().density * f) + 0.5f);
    }

    public static String reverse(String str) {
        if (TextUtils.isEmpty(str)) {
            return com.umeng.a.d;
        }
        StringBuilder stringBuilder = new StringBuilder();
        char[] toCharArray = str.toCharArray();
        for (int length = toCharArray.length - 1; length >= 0; length--) {
            stringBuilder.append(toCharArray[length]);
        }
        return stringBuilder.toString();
    }

    public static Pair<String, String> resolveActivity(Context context, String str, String str2) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("image/*");
        List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 0);
        if (!queryIntentActivities.isEmpty()) {
            for (ResolveInfo resolveInfo : queryIntentActivities) {
                boolean contains = resolveInfo.activityInfo.packageName.toLowerCase().contains(str);
                boolean contains2 = resolveInfo.activityInfo.name.toLowerCase().contains(str2);
                if (!contains) {
                    if (contains2) {
                    }
                }
                return new Pair(resolveInfo.activityInfo.parentActivityName, resolveInfo.activityInfo.name);
            }
        }
        return null;
    }

    public static Map<String, String> jsonToMap(String str) {
        Map<String, String> hashMap = new HashMap();
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str2 = (String) keys.next();
                hashMap.put(str2, jSONObject.get(str2));
            }
        } catch (Exception e) {
            Log.e(NotificationCompatApi21.CATEGORY_SOCIAL, new StringBuilder("jsontomap fail=").append(e).toString());
        }
        return hashMap;
    }
}
