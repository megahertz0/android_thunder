package com.xunlei.downloadprovider.service.a;

import android.content.Context;
import android.content.Intent;
import com.sina.weibo.sdk.register.mobile.SelectCountryActivity;
import com.tencent.open.SocialConstants;
import com.xunlei.download.Downloads.Impl;

// compiled from: ThirdPartCallDownload.java
public final class a {
    public static void a(Context context, String str, String str2, boolean z) {
        if (str != null && context != null) {
            Intent intent = new Intent("com.xunlei.downloadprovider.ACTION_DOWNLOAD_STATUS");
            intent.putExtra(Impl.COLUMN_STATUS, z ? "CREATE_SUCCESS" : "CREATE_FAIL");
            intent.putExtra(SocialConstants.PARAM_URL, str);
            intent.putExtra(SelectCountryActivity.EXTRA_COUNTRY_NAME, str2);
            intent.putExtra("return", 0);
            context.sendBroadcast(intent);
        }
    }
}
