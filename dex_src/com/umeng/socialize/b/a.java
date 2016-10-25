package com.umeng.socialize.b;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Pair;
import com.umeng.message.ALIAS_TYPE;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.PlatformConfig.Platform;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.a.c;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.handler.UMSSOHandler;
import com.umeng.socialize.net.RestAPI;
import com.umeng.socialize.net.UrlRequest;
import com.umeng.socialize.net.UrlResponse;
import com.umeng.socialize.utils.Log;
import com.umeng.socialize.view.UMFriendListener;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// compiled from: SocialRouter.java
public final class a {
    private int a;
    private final Map<SHARE_MEDIA, UMSSOHandler> b;
    private final List<Pair<SHARE_MEDIA, String>> c;
    private a d;
    private Context e;

    // compiled from: SocialRouter.java
    static class a {
        private Map<SHARE_MEDIA, UMSSOHandler> a;

        public a(Map<SHARE_MEDIA, UMSSOHandler> map) {
            this.a = map;
        }

        public boolean a(Context context, SHARE_MEDIA share_media) {
            if (!a(context)) {
                return false;
            }
            if (!a(share_media)) {
                return false;
            }
            if (((UMSSOHandler) this.a.get(share_media)).isSupportAuth()) {
                return true;
            }
            Log.w(share_media.toString() + "\u5e73\u53f0\u4e0d\u652f\u6301\u6388\u6743,\u65e0\u6cd5\u5b8c\u6210\u64cd\u4f5c");
            return false;
        }

        public boolean a(Activity activity, ShareAction shareAction) {
            if (!a((Context) activity)) {
                return false;
            }
            SHARE_MEDIA platform = shareAction.getPlatform();
            return platform != null && a(platform);
        }

        private boolean a(Context context) {
            if (context != null) {
                return true;
            }
            Log.e("Context is null");
            return false;
        }

        private boolean a(SHARE_MEDIA share_media) {
            Platform platform = (Platform) PlatformConfig.configs.get(share_media);
            if (platform != null && !platform.isConfigured()) {
                Log.e(share_media + ": \u6ca1\u6709\u914d\u7f6e\u76f8\u5173\u7684Appkey\u3001Secret");
                return false;
            } else if (((UMSSOHandler) this.a.get(share_media)) != null) {
                return true;
            } else {
                Log.e(new StringBuilder("\u6ca1\u6709\u914d\u7f6e ").append(share_media).append(" \u7684jar\u5305").toString());
                return false;
            }
        }
    }

    public a(Context context) {
        this.a = 0;
        this.b = new HashMap();
        this.c = new ArrayList();
        List list = this.c;
        list.add(new Pair(SHARE_MEDIA.LAIWANG, "com.umeng.socialize.handler.UMLWHandler"));
        list.add(new Pair(SHARE_MEDIA.LAIWANG_DYNAMIC, "com.umeng.socialize.handler.UMLWHandler"));
        list.add(new Pair(SHARE_MEDIA.SINA, "com.umeng.socialize.handler.SinaSsoHandler"));
        list.add(new Pair(SHARE_MEDIA.PINTEREST, "com.umeng.socialize.handler.UMPinterestHandler"));
        list.add(new Pair(SHARE_MEDIA.QZONE, "com.umeng.socialize.handler.QZoneSsoHandler"));
        list.add(new Pair(SHARE_MEDIA.QQ, "com.umeng.socialize.handler.UMQQSsoHandler"));
        list.add(new Pair(SHARE_MEDIA.RENREN, "com.umeng.socialize.handler.RenrenSsoHandler"));
        list.add(new Pair(SHARE_MEDIA.TENCENT, "com.umeng.socialize.handler.QQwbHandler"));
        list.add(new Pair(SHARE_MEDIA.WEIXIN, "com.umeng.socialize.handler.UMWXHandler"));
        list.add(new Pair(SHARE_MEDIA.WEIXIN_CIRCLE, "com.umeng.socialize.handler.UMWXHandler"));
        list.add(new Pair(SHARE_MEDIA.WEIXIN_FAVORITE, "com.umeng.socialize.handler.UMWXHandler"));
        list.add(new Pair(SHARE_MEDIA.YIXIN, "com.umeng.socialize.handler.UMYXHandler"));
        list.add(new Pair(SHARE_MEDIA.YIXIN_CIRCLE, "com.umeng.socialize.handler.UMYXHandler"));
        list.add(new Pair(SHARE_MEDIA.EMAIL, "com.umeng.socialize.handler.EmailHandler"));
        list.add(new Pair(SHARE_MEDIA.EVERNOTE, "com.umeng.socialize.handler.UMEvernoteHandler"));
        list.add(new Pair(SHARE_MEDIA.FACEBOOK, "com.umeng.socialize.handler.UMFacebookHandler"));
        list.add(new Pair(SHARE_MEDIA.FLICKR, "com.umeng.socialize.handler.UMFlickrHandler"));
        list.add(new Pair(SHARE_MEDIA.FOURSQUARE, "com.umeng.socialize.handler.UMFourSquareHandler"));
        list.add(new Pair(SHARE_MEDIA.GOOGLEPLUS, "com.umeng.socialize.handler.UMGooglePlusHandler"));
        list.add(new Pair(SHARE_MEDIA.INSTAGRAM, "com.umeng.socialize.handler.UMInstagramHandler"));
        list.add(new Pair(SHARE_MEDIA.KAKAO, "com.umeng.socialize.handler.UMKakaoHandler"));
        list.add(new Pair(SHARE_MEDIA.LINE, "com.umeng.socialize.handler.UMLineHandler"));
        list.add(new Pair(SHARE_MEDIA.LINKEDIN, "com.umeng.socialize.handler.UMLinkedInHandler"));
        list.add(new Pair(SHARE_MEDIA.POCKET, "com.umeng.socialize.handler.UMPocketHandler"));
        list.add(new Pair(SHARE_MEDIA.WHATSAPP, "com.umeng.socialize.handler.UMWhatsAppHandler"));
        list.add(new Pair(SHARE_MEDIA.YNOTE, "com.umeng.socialize.handler.UMYNoteHandler"));
        list.add(new Pair(SHARE_MEDIA.SMS, "com.umeng.socialize.handler.SmsHandler"));
        list.add(new Pair(SHARE_MEDIA.DOUBAN, "com.umeng.socialize.handler.DoubanHandler"));
        list.add(new Pair(SHARE_MEDIA.TUMBLR, "com.umeng.socialize.handler.UMTumblrHandler"));
        list.add(new Pair(SHARE_MEDIA.TWITTER, "com.umeng.socialize.handler.TwitterHandler"));
        list.add(new Pair(SHARE_MEDIA.ALIPAY, "com.umeng.socialize.handler.AlipayHandler"));
        this.d = new a(this.b);
        this.e = null;
        this.e = context;
        a();
    }

    private void a() {
        for (Pair pair : this.c) {
            Object a;
            UMSSOHandler uMSSOHandler;
            if (pair.first == SHARE_MEDIA.WEIXIN_CIRCLE || pair.first == SHARE_MEDIA.WEIXIN_FAVORITE) {
                uMSSOHandler = (UMSSOHandler) this.b.get(SHARE_MEDIA.WEIXIN);
            } else if (pair.first == SHARE_MEDIA.YIXIN_CIRCLE) {
                uMSSOHandler = (UMSSOHandler) this.b.get(SHARE_MEDIA.YIXIN);
            } else if (pair.first == SHARE_MEDIA.LAIWANG_DYNAMIC) {
                uMSSOHandler = (UMSSOHandler) this.b.get(SHARE_MEDIA.LAIWANG);
            } else if (pair.first != SHARE_MEDIA.TENCENT) {
                a = a((String) pair.second);
            } else if (Config.WBBYQQ) {
                a = a((String) pair.second);
            } else {
                a = a("com.umeng.socialize.handler.TencentWBSsoHandler");
            }
            this.b.put(pair.first, a);
        }
    }

    private UMSSOHandler a(String str) {
        try {
            return (UMSSOHandler) Class.forName(str).newInstance();
        } catch (Exception e) {
            Log.v("xxxx", new StringBuilder("ignore=").append(e).toString());
            return null;
        }
    }

    public final UMSSOHandler a(SHARE_MEDIA share_media) {
        UMSSOHandler uMSSOHandler = (UMSSOHandler) this.b.get(share_media);
        if (uMSSOHandler != null) {
            uMSSOHandler.onCreate(this.e, PlatformConfig.getPlatform(share_media));
        }
        return uMSSOHandler;
    }

    public final void a(int i, int i2, Intent intent) {
        UMSSOHandler a = a(i);
        if (a != null) {
            a.onActivityResult(i, i2, intent);
        }
    }

    private UMSSOHandler a(int i) {
        Object obj = 10103;
        if (!(i == 10103 || i == 11101)) {
            int i2 = i;
        }
        if (i == 64207 || i == 64206) {
            i2 = 64206;
        }
        int i3;
        if (i == 32973 || i == 765) {
            i3 = 5659;
        } else {
            i3 = i2;
        }
        for (UMSSOHandler uMSSOHandler : this.b.values()) {
            if (uMSSOHandler == null || r1 == uMSSOHandler.getRequestCode()) {
                return uMSSOHandler;
            }
        }
        return null;
    }

    public final void a(SHARE_MEDIA share_media, UMSSOHandler uMSSOHandler) {
        if (share_media == null || uMSSOHandler == null) {
            Log.e("SHARE_MEDIA or UMSSOHandler is null");
        } else {
            this.b.put(share_media, uMSSOHandler);
        }
    }

    public final void a(Activity activity, SHARE_MEDIA share_media, UMAuthListener uMAuthListener) {
        if (this.d.a((Context) activity, share_media)) {
            if (uMAuthListener == null) {
                uMAuthListener = new b(this);
            }
            ((UMSSOHandler) this.b.get(share_media)).onCreate(activity, PlatformConfig.getPlatform(share_media));
            ((UMSSOHandler) this.b.get(share_media)).deleteAuth(activity, uMAuthListener);
        }
    }

    public final void b(Activity activity, SHARE_MEDIA share_media, UMAuthListener uMAuthListener) {
        if (this.d.a((Context) activity, share_media)) {
            if (uMAuthListener == null) {
                uMAuthListener = new c(this);
            }
            ((UMSSOHandler) this.b.get(share_media)).onCreate(activity, PlatformConfig.getPlatform(share_media));
            ((UMSSOHandler) this.b.get(share_media)).getPlatformInfo(activity, uMAuthListener);
        }
    }

    public final void a(Activity activity, SHARE_MEDIA share_media, UMFriendListener uMFriendListener) {
        if (this.d.a((Context) activity, share_media)) {
            if (uMFriendListener == null) {
                uMFriendListener = new d(this);
            }
            ((UMSSOHandler) this.b.get(share_media)).onCreate(activity, PlatformConfig.getPlatform(share_media));
            ((UMSSOHandler) this.b.get(share_media)).getfriend(activity, uMFriendListener);
        }
    }

    public final boolean a(Activity activity, SHARE_MEDIA share_media) {
        if (!this.d.a((Context) activity, share_media)) {
            return false;
        }
        ((UMSSOHandler) this.b.get(share_media)).onCreate(activity, PlatformConfig.getPlatform(share_media));
        return ((UMSSOHandler) this.b.get(share_media)).isInstall(activity);
    }

    public final boolean b(Activity activity, SHARE_MEDIA share_media) {
        if (!this.d.a((Context) activity, share_media)) {
            return false;
        }
        ((UMSSOHandler) this.b.get(share_media)).onCreate(activity, PlatformConfig.getPlatform(share_media));
        return ((UMSSOHandler) this.b.get(share_media)).isAuthorize(activity);
    }

    public final void c(Activity activity, SHARE_MEDIA share_media, UMAuthListener uMAuthListener) {
        if (this.d.a((Context) activity, share_media)) {
            UMSSOHandler uMSSOHandler = (UMSSOHandler) this.b.get(share_media);
            uMSSOHandler.onCreate(activity, PlatformConfig.getPlatform(share_media));
            uMSSOHandler.authorize(activity, uMAuthListener);
        }
    }

    public final void a(Activity activity, ShareAction shareAction, UMShareListener uMShareListener) {
        if (this.d.a(activity, shareAction)) {
            if (uMShareListener == null) {
                uMShareListener = new e(this);
            }
            SHARE_MEDIA platform = shareAction.getPlatform();
            UMSSOHandler uMSSOHandler = (UMSSOHandler) this.b.get(platform);
            uMSSOHandler.setCaller(shareAction.getFrom());
            uMSSOHandler.onCreate(activity, PlatformConfig.getPlatform(platform));
            if (!(platform.toString().equals("TENCENT") || platform.toString().equals(ALIAS_TYPE.RENREN) || platform.toString().equals("DOUBAN"))) {
                if (platform.toString().equals(ALIAS_TYPE.WEIXIN)) {
                    c.a(activity, "wxsession", shareAction.getShareContent().mText, shareAction.getShareContent().mMedia);
                } else if (platform.toString().equals("WEIXIN_CIRCLE")) {
                    c.a(activity, "wxtimeline", shareAction.getShareContent().mText, shareAction.getShareContent().mMedia);
                } else if (platform.toString().equals("WEIXIN_FAVORITE")) {
                    c.a(activity, "wxfavorite", shareAction.getShareContent().mText, shareAction.getShareContent().mMedia);
                } else {
                    c.a(activity, platform.toString().toLowerCase(), shareAction.getShareContent().mText, shareAction.getShareContent().mMedia);
                }
            }
            if (platform.toString().equals("TENCENT") && Config.WBBYQQ) {
                c.a(activity, platform.toString().toLowerCase(), shareAction.getShareContent().mText, shareAction.getShareContent().mMedia);
            }
            if (Config.isloadUrl) {
                a(activity, shareAction);
            }
            uMSSOHandler.share(activity, shareAction.getShareContent(), uMShareListener);
        }
    }

    private void a(Activity activity, ShareAction shareAction) {
        Object obj = shareAction.getShareContent().mTargetUrl;
        if (!TextUtils.isEmpty(obj)) {
            String str;
            if (shareAction.getPlatform().toString().equals(ALIAS_TYPE.WEIXIN)) {
                str = "wxsession";
            } else if (shareAction.getPlatform().toString().equals(BuildConfig.VERSION_NAME)) {
                str = "wxtimeline";
            } else {
                str = shareAction.getPlatform().toString().toLowerCase();
            }
            UrlResponse uploadUrl = RestAPI.uploadUrl(new UrlRequest(activity, str, obj));
            Log.e(new StringBuilder("xxxxxx resp").append(uploadUrl).toString());
            if (uploadUrl == null || uploadUrl.mStCode != 200) {
                Log.e("upload url fail ");
            } else {
                shareAction.withTargetUrl(uploadUrl.result);
            }
        }
    }
}
