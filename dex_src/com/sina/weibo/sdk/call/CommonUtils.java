package com.sina.weibo.sdk.call;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.alipay.sdk.sys.a;
import com.sina.weibo.sdk.constant.WBPageConstants.ExceptionMsg;
import java.util.HashMap;

class CommonUtils {
    CommonUtils() {
    }

    public static String buildUriQuery(HashMap<String, String> hashMap) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : hashMap.keySet()) {
            String str2 = (String) hashMap.get(str);
            if (str2 != null) {
                stringBuilder.append(a.b).append(str).append("=").append(str2);
            }
        }
        return stringBuilder.toString().replaceFirst(a.b, "?");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void openWeiboActivity(android.content.Context r2, java.lang.String r3, java.lang.String r4, java.lang.String r5) throws com.sina.weibo.sdk.call.WeiboNotInstalledException {
        throw new UnsupportedOperationException("Method not decompiled: com.sina.weibo.sdk.call.CommonUtils.openWeiboActivity(android.content.Context, java.lang.String, java.lang.String, java.lang.String):void");
        /*
        if (r5 == 0) goto L_0x0018;
    L_0x0002:
        r0 = new android.content.Intent;	 Catch:{ ActivityNotFoundException -> 0x002b }
        r0.<init>();	 Catch:{ ActivityNotFoundException -> 0x002b }
        r0.setAction(r3);	 Catch:{ ActivityNotFoundException -> 0x002b }
        r1 = android.net.Uri.parse(r4);	 Catch:{ ActivityNotFoundException -> 0x002b }
        r0.setData(r1);	 Catch:{ ActivityNotFoundException -> 0x002b }
        r0.setPackage(r5);	 Catch:{ ActivityNotFoundException -> 0x002b }
        r2.startActivity(r0);	 Catch:{ ActivityNotFoundException -> 0x002b }
    L_0x0017:
        return;
    L_0x0018:
        r0 = new android.content.Intent;	 Catch:{ ActivityNotFoundException -> 0x002b }
        r0.<init>();	 Catch:{ ActivityNotFoundException -> 0x002b }
        r0.setAction(r3);	 Catch:{ ActivityNotFoundException -> 0x002b }
        r1 = android.net.Uri.parse(r4);	 Catch:{ ActivityNotFoundException -> 0x002b }
        r0.setData(r1);	 Catch:{ ActivityNotFoundException -> 0x002b }
        r2.startActivity(r0);	 Catch:{ ActivityNotFoundException -> 0x002b }
        goto L_0x0017;
    L_0x002b:
        r0 = move-exception;
        if (r5 == 0) goto L_0x004b;
    L_0x002e:
        r0 = new android.content.Intent;	 Catch:{ ActivityNotFoundException -> 0x0041 }
        r0.<init>();	 Catch:{ ActivityNotFoundException -> 0x0041 }
        r0.setAction(r3);	 Catch:{ ActivityNotFoundException -> 0x0041 }
        r1 = android.net.Uri.parse(r4);	 Catch:{ ActivityNotFoundException -> 0x0041 }
        r0.setData(r1);	 Catch:{ ActivityNotFoundException -> 0x0041 }
        r2.startActivity(r0);	 Catch:{ ActivityNotFoundException -> 0x0041 }
        goto L_0x0017;
    L_0x0041:
        r0 = move-exception;
        r0 = new com.sina.weibo.sdk.call.WeiboNotInstalledException;
        r1 = "\u65e0\u6cd5\u627e\u5230\u5fae\u535a\u5b98\u65b9\u5ba2\u6237\u7aef";
        r0.<init>(r1);
        throw r0;
    L_0x004b:
        r0 = new com.sina.weibo.sdk.call.WeiboNotInstalledException;
        r1 = "\u65e0\u6cd5\u627e\u5230\u5fae\u535a\u5b98\u65b9\u5ba2\u6237\u7aef";
        r0.<init>(r1);
        throw r0;
        */
    }

    public static void openWeiboActivity(Context context, String str, String str2) throws WeiboNotInstalledException {
        try {
            Intent intent = new Intent();
            intent.setAction(str);
            intent.setData(Uri.parse(str2));
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            throw new WeiboNotInstalledException(ExceptionMsg.WEIBO_NOT_INSTALLED);
        }
    }
}
