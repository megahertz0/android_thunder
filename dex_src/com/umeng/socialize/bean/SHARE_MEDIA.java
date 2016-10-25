package com.umeng.socialize.bean;

import android.text.TextUtils;
import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.constants.ConstantsAPI.Token;
import com.umeng.socialize.PlatformConfig.Alipay;
import com.umeng.socialize.PlatformConfig.Renren;
import com.umeng.socialize.PlatformConfig.TencentWeibo;
import com.umeng.socialize.shareboard.SnsPlatform;

public enum SHARE_MEDIA {
    GOOGLEPLUS,
    GENERIC,
    SMS,
    EMAIL,
    SINA,
    QZONE,
    QQ,
    RENREN,
    WEIXIN,
    WEIXIN_CIRCLE,
    WEIXIN_FAVORITE,
    TENCENT,
    DOUBAN,
    FACEBOOK,
    TWITTER,
    LAIWANG,
    LAIWANG_DYNAMIC,
    YIXIN,
    YIXIN_CIRCLE,
    INSTAGRAM,
    PINTEREST,
    EVERNOTE,
    POCKET,
    LINKEDIN,
    FOURSQUARE,
    YNOTE,
    WHATSAPP,
    LINE,
    FLICKR,
    TUMBLR,
    ALIPAY,
    KAKAO;
    private String a;

    public static SHARE_MEDIA convertToEmun(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (str.equals("wxtimeline")) {
            return WEIXIN_CIRCLE;
        }
        if (str.equals("wxsession")) {
            return WEIXIN;
        }
        SHARE_MEDIA[] values = values();
        int length = values.length;
        for (int i = 0; i < length; i++) {
            SHARE_MEDIA share_media = values[i];
            if (share_media.toString().trim().equals(str)) {
                return share_media;
            }
        }
        return null;
    }

    private SHARE_MEDIA(String str) {
        this.a = str;
    }

    public static SnsPlatform createSnsPlatform(String str, String str2, String str3, String str4, int i) {
        SnsPlatform snsPlatform = new SnsPlatform();
        snsPlatform.mShowWord = str;
        snsPlatform.mIcon = str3;
        snsPlatform.mGrayIcon = str4;
        snsPlatform.mIndex = i;
        snsPlatform.mKeyword = str2;
        return snsPlatform;
    }

    public final SnsPlatform toSnsPlatform() {
        SnsPlatform snsPlatform = new SnsPlatform();
        if (toString().equals(Constants.SOURCE_QQ)) {
            snsPlatform.mShowWord = "umeng_socialize_text_qq_key";
            snsPlatform.mIcon = "umeng_socialize_qq_on";
            snsPlatform.mGrayIcon = "umeng_socialize_qq_off";
            snsPlatform.mIndex = 0;
            snsPlatform.mKeyword = "qq";
        } else if (toString().equals("SMS")) {
            snsPlatform.mShowWord = "umeng_socialize_sms";
            snsPlatform.mIcon = "umeng_socialize_sms_on";
            snsPlatform.mGrayIcon = "umeng_socialize_sms_off";
            snsPlatform.mIndex = 1;
            snsPlatform.mKeyword = "sms";
        } else if (toString().equals("GOOGLEPLUS")) {
            snsPlatform.mShowWord = "umeng_socialize_text_googleplus_key";
            snsPlatform.mIcon = "umeng_socialize_google";
            snsPlatform.mGrayIcon = "umeng_socialize_google";
            snsPlatform.mIndex = 0;
            snsPlatform.mKeyword = "gooleplus";
        } else if (!toString().equals("GENERIC")) {
            if (toString().equals("EMAIL")) {
                snsPlatform.mShowWord = "umeng_socialize_mail";
                snsPlatform.mIcon = "umeng_socialize_gmail_on";
                snsPlatform.mGrayIcon = "umeng_socialize_gmail_off";
                snsPlatform.mIndex = 2;
                snsPlatform.mKeyword = NotificationCompatApi21.CATEGORY_EMAIL;
            } else if (toString().equals("SINA")) {
                snsPlatform.mShowWord = "umeng_socialize_sina";
                snsPlatform.mIcon = "umeng_socialize_sina_on";
                snsPlatform.mGrayIcon = "umeng_socialize_sina_off";
                snsPlatform.mIndex = 0;
                snsPlatform.mKeyword = "sina";
            } else if (toString().equals("QZONE")) {
                snsPlatform.mShowWord = "umeng_socialize_text_qq_zone_key";
                snsPlatform.mIcon = "umeng_socialize_qzone_on";
                snsPlatform.mGrayIcon = "umeng_socialize_qzone_off";
                snsPlatform.mIndex = 0;
                snsPlatform.mKeyword = Constants.SOURCE_QZONE;
            } else if (toString().equals("RENREN")) {
                snsPlatform.mShowWord = "umeng_socialize_text_renren_key";
                snsPlatform.mIcon = "umeng_socialize_renren_on";
                snsPlatform.mGrayIcon = "umeng_socialize_renren_off";
                snsPlatform.mIndex = 0;
                snsPlatform.mKeyword = Renren.Name;
            } else if (toString().equals("WEIXIN")) {
                snsPlatform.mShowWord = "umeng_socialize_text_weixin_key";
                snsPlatform.mIcon = "umeng_socialize_wechat";
                snsPlatform.mGrayIcon = "umeng_socialize_weichat_gray";
                snsPlatform.mIndex = 0;
                snsPlatform.mKeyword = Token.WX_TOKEN_PLATFORMID_VALUE;
            } else if (toString().equals("WEIXIN_CIRCLE")) {
                snsPlatform.mShowWord = "umeng_socialize_text_weixin_circle_key";
                snsPlatform.mIcon = "umeng_socialize_wxcircle";
                snsPlatform.mGrayIcon = "umeng_socialize_wxcircle_gray";
                snsPlatform.mIndex = 0;
                snsPlatform.mKeyword = "wxcircle";
            } else if (toString().equals("WEIXIN_FAVORITE")) {
                snsPlatform.mShowWord = "umeng_socialize_text_weixin_fav_key";
                snsPlatform.mIcon = "wechat_fav";
                snsPlatform.mGrayIcon = "wechat_fav";
                snsPlatform.mIndex = 0;
                snsPlatform.mKeyword = "wechatfavorite";
            } else if (toString().equals("TENCENT")) {
                snsPlatform.mShowWord = "umeng_socialize_text_tencent_key";
                snsPlatform.mIcon = "umeng_socialize_tx_on";
                snsPlatform.mGrayIcon = "umeng_socialize_tx_off";
                snsPlatform.mIndex = 0;
                snsPlatform.mKeyword = TencentWeibo.Name;
            } else if (toString().equals("FACEBOOK")) {
                snsPlatform.mShowWord = "umeng_socialize_text_facebook_key";
                snsPlatform.mIcon = "umeng_socialize_facebook";
                snsPlatform.mGrayIcon = "umeng_socialize_facebook";
                snsPlatform.mIndex = 0;
                snsPlatform.mKeyword = "facebook";
            } else if (toString().equals("YIXIN")) {
                snsPlatform.mShowWord = "umeng_socialize_text_yixin_key";
                snsPlatform.mIcon = "umeng_socialize_yixin";
                snsPlatform.mGrayIcon = "umeng_socialize_yixin_gray";
                snsPlatform.mIndex = 0;
                snsPlatform.mKeyword = "yinxin";
            } else if (toString().equals("TWITTER")) {
                snsPlatform.mShowWord = "umeng_socialize_text_twitter_key";
                snsPlatform.mIcon = "umeng_socialize_twitter";
                snsPlatform.mGrayIcon = "umeng_socialize_twitter";
                snsPlatform.mIndex = 0;
                snsPlatform.mKeyword = "twitter";
            } else if (toString().equals("LAIWANG")) {
                snsPlatform.mShowWord = "umeng_socialize_text_laiwang_key";
                snsPlatform.mIcon = "umeng_socialize_laiwang";
                snsPlatform.mGrayIcon = "umeng_socialize_laiwang_gray";
                snsPlatform.mIndex = 0;
                snsPlatform.mKeyword = "laiwang";
            } else if (toString().equals("LAIWANG_DYNAMIC")) {
                snsPlatform.mShowWord = "umeng_socialize_text_laiwangdynamic_key";
                snsPlatform.mIcon = "umeng_socialize_laiwang_dynamic";
                snsPlatform.mGrayIcon = "umeng_socialize_laiwang_dynamic_gray";
                snsPlatform.mIndex = 0;
                snsPlatform.mKeyword = "laiwang_dynamic";
            } else if (toString().equals("INSTAGRAM")) {
                snsPlatform.mShowWord = "umeng_socialize_text_instagram_key";
                snsPlatform.mIcon = "umeng_socialize_instagram_on";
                snsPlatform.mGrayIcon = "umeng_socialize_instagram_off";
                snsPlatform.mIndex = 0;
                snsPlatform.mKeyword = "qq";
            } else if (toString().equals("YIXIN_CIRCLE")) {
                snsPlatform.mShowWord = "umeng_socialize_text_yixincircle_key";
                snsPlatform.mIcon = "umeng_socialize_yixin_circle";
                snsPlatform.mGrayIcon = "umeng_socialize_yixin_circle_gray";
                snsPlatform.mIndex = 0;
                snsPlatform.mKeyword = "yinxincircle";
            } else if (toString().equals("PINTEREST")) {
                snsPlatform.mShowWord = "umeng_socialize_text_pinterest_key";
                snsPlatform.mIcon = "umeng_socialize_pinterest";
                snsPlatform.mGrayIcon = "umeng_socialize_pinterest_gray";
                snsPlatform.mIndex = 0;
                snsPlatform.mKeyword = "pinterest";
            } else if (toString().equals("EVERNOTE")) {
                snsPlatform.mShowWord = "umeng_socialize_text_evernote_key";
                snsPlatform.mIcon = "umeng_socialize_evernote";
                snsPlatform.mGrayIcon = "umeng_socialize_evernote_gray";
                snsPlatform.mIndex = 0;
                snsPlatform.mKeyword = "evernote";
            } else if (toString().equals("POCKET")) {
                snsPlatform.mShowWord = "umeng_socialize_text_pocket_key";
                snsPlatform.mIcon = "umeng_socialize_pocket";
                snsPlatform.mGrayIcon = "umeng_socialize_pocket_gray";
                snsPlatform.mIndex = 0;
                snsPlatform.mKeyword = "pocket";
            } else if (toString().equals("LINKEDIN")) {
                snsPlatform.mShowWord = "umeng_socialize_text_linkedin_key";
                snsPlatform.mIcon = "umeng_socialize_linkedin";
                snsPlatform.mGrayIcon = "umeng_socialize_linkedin_gray";
                snsPlatform.mIndex = 0;
                snsPlatform.mKeyword = "linkedin";
            } else if (toString().equals("FOURSQUARE")) {
                snsPlatform.mShowWord = "umeng_socialize_text_foursquare_key";
                snsPlatform.mIcon = "umeng_socialize_foursquare";
                snsPlatform.mGrayIcon = "umeng_socialize_foursquare_gray";
                snsPlatform.mIndex = 0;
                snsPlatform.mKeyword = "foursquare";
            } else if (toString().equals("YNOTE")) {
                snsPlatform.mShowWord = "umeng_socialize_text_ydnote_key";
                snsPlatform.mIcon = "umeng_socialize_ynote";
                snsPlatform.mGrayIcon = "umeng_socialize_ynote_gray";
                snsPlatform.mIndex = 0;
                snsPlatform.mKeyword = "ynote";
            } else if (toString().equals("WHATSAPP")) {
                snsPlatform.mShowWord = "umeng_socialize_text_whatsapp_key";
                snsPlatform.mIcon = "umeng_socialize_whatsapp";
                snsPlatform.mGrayIcon = "umeng_socialize_whatsapp_gray";
                snsPlatform.mIndex = 0;
                snsPlatform.mKeyword = "whatsapp";
            } else if (toString().equals("LINE")) {
                snsPlatform.mShowWord = "umeng_socialize_text_line_key";
                snsPlatform.mIcon = "umeng_socialize_line";
                snsPlatform.mGrayIcon = "umeng_socialize_line_gray";
                snsPlatform.mIndex = 0;
                snsPlatform.mKeyword = "line";
            } else if (toString().equals("FLICKR")) {
                snsPlatform.mShowWord = "umeng_socialize_text_flickr_key";
                snsPlatform.mIcon = "umeng_socialize_flickr";
                snsPlatform.mGrayIcon = "umeng_socialize_flickr_gray";
                snsPlatform.mIndex = 0;
                snsPlatform.mKeyword = "flickr";
            } else if (toString().equals("TUMBLR")) {
                snsPlatform.mShowWord = "umeng_socialize_text_tumblr_key";
                snsPlatform.mIcon = "umeng_socialize_tumblr";
                snsPlatform.mGrayIcon = "umeng_socialize_tumblr_gray";
                snsPlatform.mIndex = 0;
                snsPlatform.mKeyword = "tumblr";
            } else if (toString().equals("KAKAO")) {
                snsPlatform.mShowWord = "umeng_socialize_text_kakao_key";
                snsPlatform.mIcon = "umeng_socialize_kakao";
                snsPlatform.mGrayIcon = "umeng_socialize_kakao_gray";
                snsPlatform.mIndex = 0;
                snsPlatform.mKeyword = "kakao";
            } else if (toString().equals("DOUBAN")) {
                snsPlatform.mShowWord = "umeng_socialize_text_douban_key";
                snsPlatform.mIcon = "umeng_socialize_douban_on";
                snsPlatform.mGrayIcon = "umeng_socialize_douban_off";
                snsPlatform.mIndex = 0;
                snsPlatform.mKeyword = "douban";
            } else if (toString().equals("ALIPAY")) {
                snsPlatform.mShowWord = "umeng_socialize_text_alipay_key";
                snsPlatform.mIcon = "umeng_socialize_alipay";
                snsPlatform.mGrayIcon = "umeng_socialize_alipay";
                snsPlatform.mIndex = 0;
                snsPlatform.mKeyword = Alipay.Name;
            }
        }
        snsPlatform.mPlatform = this;
        return snsPlatform;
    }

    public final String toString() {
        return super.toString();
    }

    public static SHARE_MEDIA[] getDefaultPlatform() {
        return new SHARE_MEDIA[]{SINA, DOUBAN, QZONE, TENCENT, RENREN, SMS, EMAIL, WEIXIN};
    }
}
