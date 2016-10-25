package com.xunlei.downloadprovider.pushmessage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.tencent.open.SocialConstants;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.downloadprovider.pushmessage.a.a;
import com.xunlei.downloadprovider.web.DetailPageBrowserActivity;
import com.xunlei.downloadprovider.web.base.ShortMovieDetailActivity;
import com.xunlei.downloadprovider.web.base.ShortMovieDetailActivity.From;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.sdk.XLLiveSDK;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

// compiled from: PushResultHandler.java
public final class f {
    public static Intent a(Context context, a aVar) {
        if (aVar == null) {
            return null;
        }
        String str;
        String str2;
        Intent intent;
        switch (aVar.s) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                str = aVar.p;
                str2 = aVar.q;
                intent = new Intent(context, ShortMovieDetailActivity.class);
                intent.addFlags(268435456);
                intent.putExtra("from", From.PUSH.getText());
                intent.putExtra(SocialConstants.PARAM_URL, aVar.c);
                intent.putExtra("movie_id", str);
                intent.putExtra(Impl.COLUMN_GCID, str2);
                new StringBuilder("createNewIntent movieId=").append(str).append(",gcId=").append(str2);
                return intent;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                str = aVar.p;
                str2 = aVar.q;
                intent = new Intent(context, ShortMovieDetailActivity.class);
                intent.addFlags(268435456);
                intent.putExtra("from", From.PUSH.getText());
                intent.putExtra(SocialConstants.PARAM_URL, aVar.c);
                intent.putExtra("movie_id", str);
                intent.putExtra(Impl.COLUMN_GCID, str2);
                new StringBuilder("createNewIntent movieId=").append(str).append(",gcId=").append(str2);
                return intent;
            case XZBDevice.DOWNLOAD_LIST_ALL:
                intent = new Intent(context, DetailPageBrowserActivity.class);
                intent.addFlags(268435456);
                Bundle bundle = new Bundle();
                bundle.putInt("from_entry", R.styleable.Toolbar_navigationContentDescription);
                bundle.putString("key_url", aVar.c);
                bundle.putString("key_title", aVar.h);
                bundle.putBoolean("key_is_from_notification", true);
                bundle.putString("key_is_notification_tag", "UrlDetailPage");
                bundle.putInt("from_entry", R.styleable.Toolbar_navigationContentDescription);
                intent.putExtras(bundle);
                return intent;
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                if (!XLLiveSDK.getInstance(context).canPlay()) {
                    return null;
                }
                XLLiveSDK.getInstance(context).playFromPush(context, aVar.t);
                return null;
            default:
                return null;
        }
    }
}
