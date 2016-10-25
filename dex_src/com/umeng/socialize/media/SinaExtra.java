package com.umeng.socialize.media;

import android.content.Context;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.AsyncWeiboRunner;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.net.WeiboParameters;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.PlatformConfig.SinaWeibo;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import java.util.HashMap;
import java.util.Map;
import org.android.spdy.SpdyRequest;
import org.apache.commons.logging.impl.SimpleLog;

public class SinaExtra {

    final class AnonymousClass_1 implements RequestListener {
        final /* synthetic */ UMAuthListener a;

        AnonymousClass_1(UMAuthListener uMAuthListener) {
            this.a = uMAuthListener;
        }

        public final void onComplete(String str) {
            Map hashMap = new HashMap();
            hashMap.put("result", str);
            this.a.onComplete(SHARE_MEDIA.SINA, SimpleLog.LOG_LEVEL_DEBUG, hashMap);
        }

        public final void onWeiboException(WeiboException weiboException) {
            this.a.onError(SHARE_MEDIA.SINA, SimpleLog.LOG_LEVEL_DEBUG, new Throwable(weiboException));
        }
    }

    public static void JudgeAccessToken(Context context, String str, UMAuthListener uMAuthListener) {
        WeiboParameters weiboParameters = new WeiboParameters(((SinaWeibo) PlatformConfig.getPlatform(SHARE_MEDIA.SINA)).appKey);
        weiboParameters.put(XiaomiOAuthConstants.EXTRA_ACCESS_TOKEN_2, str);
        new AsyncWeiboRunner(context).requestAsync("https://api.weibo.com/oauth2/get_token_info", weiboParameters, SpdyRequest.POST_METHOD, new AnonymousClass_1(uMAuthListener));
    }
}
