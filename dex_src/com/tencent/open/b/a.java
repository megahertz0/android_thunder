package com.tencent.open.b;

import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import com.alipay.sdk.app.statistic.c;
import com.baidu.mobads.interfaces.utils.IXAdSystemUtils;
import com.taobao.accs.data.Message;
import com.taobao.accs.utl.UtilityImpl;
import com.tencent.open.a.f;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: ProGuard
public class a {
    protected static final Uri a;

    static {
        a = Uri.parse("content://telephony/carriers/preferapn");
    }

    public static String a(Context context) {
        int d = d(context);
        if (d == 2) {
            return UtilityImpl.NET_TYPE_WIFI;
        }
        if (d == 1) {
            return "cmwap";
        }
        if (d == 4) {
            return "cmnet";
        }
        if (d == 16) {
            return "uniwap";
        }
        if (d == 8) {
            return "uninet";
        }
        if (d == 64) {
            return "wap";
        }
        if (d == 32) {
            return c.a;
        }
        if (d == 512) {
            return "ctwap";
        }
        if (d == 256) {
            return "ctnet";
        }
        if (d == 2048) {
            return "3gnet";
        }
        if (d == 1024) {
            return "3gwap";
        }
        String b = b(context);
        return (b == null || b.length() == 0) ? IXAdSystemUtils.NT_NONE : b;
    }

    public static String b(Context context) {
        try {
            Cursor query = context.getContentResolver().query(a, null, null, null, null);
            if (query == null) {
                return null;
            }
            query.moveToFirst();
            if (query.isAfterLast()) {
                if (query != null) {
                    query.close();
                }
                return null;
            }
            String string = query.getString(query.getColumnIndex("apn"));
            if (query == null) {
                return string;
            }
            query.close();
            return string;
        } catch (SecurityException e) {
            f.e("openSDK_LOG.APNUtil", new StringBuilder("getApn has exception: ").append(e.getMessage()).toString());
            return com.umeng.a.d;
        } catch (Exception e2) {
            f.e("openSDK_LOG.APNUtil", new StringBuilder("getApn has exception: ").append(e2.getMessage()).toString());
            return com.umeng.a.d;
        }
    }

    public static String c(Context context) {
        try {
            Cursor query = context.getContentResolver().query(a, null, null, null, null);
            if (query == null) {
                return null;
            }
            query.moveToFirst();
            if (query.isAfterLast()) {
                if (query != null) {
                    query.close();
                }
                return null;
            }
            String string = query.getString(query.getColumnIndex("proxy"));
            if (query == null) {
                return string;
            }
            query.close();
            return string;
        } catch (SecurityException e) {
            f.e("openSDK_LOG.APNUtil", new StringBuilder("getApnProxy has exception: ").append(e.getMessage()).toString());
            return com.umeng.a.d;
        }
    }

    public static int d(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return 128;
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return 128;
            }
            if (activeNetworkInfo.getTypeName().toUpperCase().equals("WIFI")) {
                return XZBDevice.DOWNLOAD_LIST_RECYCLE;
            }
            String toLowerCase = activeNetworkInfo.getExtraInfo().toLowerCase();
            if (toLowerCase.startsWith("cmwap")) {
                return 1;
            }
            if (toLowerCase.startsWith("cmnet") || toLowerCase.startsWith("epc.tmobile.com")) {
                return XZBDevice.DOWNLOAD_LIST_ALL;
            }
            if (toLowerCase.startsWith("uniwap")) {
                return R.styleable.Toolbar_titleMarginBottom;
            }
            if (toLowerCase.startsWith("uninet")) {
                return XZBDevice.Wait;
            }
            if (toLowerCase.startsWith("wap")) {
                return R.styleable.AppCompatTheme_imageButtonStyle;
            }
            if (toLowerCase.startsWith(c.a)) {
                return R.styleable.AppCompatTheme_actionModeCutDrawable;
            }
            if (toLowerCase.startsWith("ctwap")) {
                return 512;
            }
            if (toLowerCase.startsWith("ctnet")) {
                return 256;
            }
            if (toLowerCase.startsWith("3gwap")) {
                return JsInterface.MSG_JS_COLLECT_WEBSITE;
            }
            if (toLowerCase.startsWith("3gnet")) {
                return Message.FLAG_RET;
            }
            if (toLowerCase.startsWith("#777")) {
                toLowerCase = c(context);
                return (toLowerCase == null || toLowerCase.length() <= 0) ? 256 : 512;
            }
            return 128;
        } catch (Exception e) {
            f.e("openSDK_LOG.APNUtil", new StringBuilder("getMProxyType has exception: ").append(e.getMessage()).toString());
        }
    }

    public static String e(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            return "MOBILE";
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null ? activeNetworkInfo.getTypeName() : "MOBILE";
    }
}
