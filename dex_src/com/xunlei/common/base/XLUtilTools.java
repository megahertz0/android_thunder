package com.xunlei.common.base;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import anet.channel.util.HttpConstant;
import com.taobao.accs.data.Message;
import com.umeng.a;
import com.umeng.socialize.common.SocializeConstants;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public final class XLUtilTools {
    public static final String getServerDomain(String str) {
        String str2 = a.d;
        if (str == null) {
            return str2;
        }
        int indexOf = str.indexOf(HttpConstant.SCHEME_SPLIT);
        if (indexOf == -1) {
            return str2;
        }
        int i = indexOf + 3;
        indexOf = str.indexOf(":", i);
        if (indexOf == -1) {
            indexOf = str.indexOf("/", i);
        }
        return indexOf >= 0 ? str.substring(i, indexOf) : str2;
    }

    public static final String parseJSONPString(String str) {
        int indexOf = str.indexOf(SocializeConstants.OP_OPEN_PAREN);
        if (indexOf != -1) {
            str = str.substring(indexOf + 1);
        }
        indexOf = str.lastIndexOf(SocializeConstants.OP_CLOSE_PAREN);
        return indexOf != -1 ? str.substring(0, indexOf) : str;
    }

    public static String transformGBKString(String str) {
        String str2 = a.d;
        try {
            return new String(str.getBytes("ISO-8859-1"), "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            XLLog.e("XLUtilTools", new StringBuilder("transformGBKString error = ").append(e.getMessage()).toString());
            return str2;
        }
    }

    public static String transformUTF8String(String str) {
        String str2 = a.d;
        try {
            return new String(str.getBytes("ISO-8859-1"), "UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            XLLog.e("XLUtilTools", new StringBuilder("transformUTF8String error = ").append(e.getMessage()).toString());
            return str2;
        }
    }

    public static String getHostAddress(String str) {
        String str2 = a.d;
        try {
            return InetAddress.getByName(str).getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return str2;
        } catch (Exception e2) {
            e2.printStackTrace();
            return str2;
        }
    }

    public static boolean isApplicationInstalled(Context context, String str) {
        boolean z = false;
        if (str == null || a.d.equals(str)) {
            return false;
        }
        try {
            context.getPackageManager().getApplicationInfo(str, Message.FLAG_REQ_BIT2);
            z = true;
            return true;
        } catch (NameNotFoundException e) {
            return z;
        }
    }
}
