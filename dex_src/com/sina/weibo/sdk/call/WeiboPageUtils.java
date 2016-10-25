package com.sina.weibo.sdk.call;

import android.content.Context;
import android.text.TextUtils;
import com.sina.weibo.sdk.component.GameManager;
import com.sina.weibo.sdk.constant.WBPageConstants.ExceptionMsg;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.sina.weibo.sdk.constant.WBPageConstants.Scheme;
import com.tencent.open.SocialConstants;
import com.umeng.a;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.tdlive.WebBrowserActivity;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import org.android.agoo.message.MessageService;

public final class WeiboPageUtils {
    private WeiboPageUtils() {
    }

    public static void postNewWeibo(Context context, String str, String str2, String str3, Position position, String str4, String str5) throws WeiboNotInstalledException {
        if (context == null) {
            throw new WeiboIllegalParameterException(ExceptionMsg.CONTEXT_ERROR);
        }
        StringBuilder stringBuilder = new StringBuilder(Scheme.SENDWEIBO);
        HashMap hashMap = new HashMap();
        try {
            hashMap.put(ParamKey.CONTENT, URLEncoder.encode(str, GameManager.DEFAULT_CHARSET).replaceAll("\\+", "%20"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        hashMap.put(ParamKey.POIID, str2);
        hashMap.put(ParamKey.POINAME, str3);
        if (position != null) {
            hashMap.put(ParamKey.LONGITUDE, position.getStrLongitude());
            hashMap.put(ParamKey.LATITUDE, position.getStrLatitude());
        }
        hashMap.put(ParamKey.PAGEID, str4);
        hashMap.put(ParamKey.EXTPARAM, str5);
        stringBuilder.append(CommonUtils.buildUriQuery(hashMap));
        CommonUtils.openWeiboActivity(context, "android.intent.action.VIEW", stringBuilder.toString());
    }

    public static void viewNearbyPeople(Context context, Position position, String str) throws WeiboNotInstalledException {
        if (context == null) {
            throw new WeiboIllegalParameterException(ExceptionMsg.CONTEXT_ERROR);
        }
        StringBuilder stringBuilder = new StringBuilder(Scheme.NEARBYPEOPLE);
        HashMap hashMap = new HashMap();
        if (position != null) {
            hashMap.put(ParamKey.LONGITUDE, position.getStrLongitude());
            hashMap.put(ParamKey.LATITUDE, position.getStrLatitude());
            hashMap.put(ParamKey.OFFSET, position.getStrOffset());
        }
        hashMap.put(ParamKey.EXTPARAM, str);
        stringBuilder.append(CommonUtils.buildUriQuery(hashMap));
        CommonUtils.openWeiboActivity(context, "android.intent.action.VIEW", stringBuilder.toString());
    }

    public static void viewNearbyWeibo(Context context, Position position, String str) throws WeiboNotInstalledException {
        if (context == null) {
            throw new WeiboIllegalParameterException(ExceptionMsg.CONTEXT_ERROR);
        }
        StringBuilder stringBuilder = new StringBuilder(Scheme.NEARBYWEIBO);
        HashMap hashMap = new HashMap();
        if (position != null) {
            hashMap.put(ParamKey.LONGITUDE, position.getStrLongitude());
            hashMap.put(ParamKey.LATITUDE, position.getStrLatitude());
            hashMap.put(ParamKey.OFFSET, position.getStrOffset());
        }
        hashMap.put(ParamKey.EXTPARAM, str);
        stringBuilder.append(CommonUtils.buildUriQuery(hashMap));
        CommonUtils.openWeiboActivity(context, "android.intent.action.VIEW", stringBuilder.toString());
    }

    public static void viewUserInfo(Context context, String str, String str2, String str3) throws WeiboNotInstalledException {
        if (context == null) {
            throw new WeiboIllegalParameterException(ExceptionMsg.CONTEXT_ERROR);
        } else if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
            throw new WeiboIllegalParameterException(ExceptionMsg.UID_NICK_ERROR);
        } else {
            StringBuilder stringBuilder = new StringBuilder(Scheme.USERINFO);
            HashMap hashMap = new HashMap();
            hashMap.put(ParamKey.UID, str);
            hashMap.put(ParamKey.NICK, str2);
            hashMap.put(ParamKey.EXTPARAM, str3);
            stringBuilder.append(CommonUtils.buildUriQuery(hashMap));
            CommonUtils.openWeiboActivity(context, "android.intent.action.VIEW", stringBuilder.toString());
        }
    }

    public static void viewUsertrends(Context context, String str, String str2) throws WeiboNotInstalledException {
        if (context == null) {
            throw new WeiboIllegalParameterException(ExceptionMsg.CONTEXT_ERROR);
        } else if (TextUtils.isEmpty(str)) {
            throw new WeiboIllegalParameterException(ExceptionMsg.UID_NICK_ERROR);
        } else {
            StringBuilder stringBuilder = new StringBuilder(Scheme.USERTRENDS);
            HashMap hashMap = new HashMap();
            hashMap.put(ParamKey.UID, str);
            hashMap.put(ParamKey.EXTPARAM, str2);
            stringBuilder.append(CommonUtils.buildUriQuery(hashMap));
            CommonUtils.openWeiboActivity(context, "android.intent.action.VIEW", stringBuilder.toString());
        }
    }

    public static void viewPageInfo(Context context, String str, String str2, String str3) throws WeiboNotInstalledException {
        if (context == null) {
            throw new WeiboIllegalParameterException(ExceptionMsg.CONTEXT_ERROR);
        } else if (TextUtils.isEmpty(str)) {
            throw new WeiboIllegalParameterException(ExceptionMsg.PAGEID_ERROR);
        } else {
            StringBuilder stringBuilder = new StringBuilder(Scheme.PAGEINFO);
            HashMap hashMap = new HashMap();
            hashMap.put(ParamKey.PAGEID, str);
            hashMap.put(WebBrowserActivity.EXTRA_TITLE, str2);
            hashMap.put(ParamKey.EXTPARAM, str3);
            stringBuilder.append(CommonUtils.buildUriQuery(hashMap));
            CommonUtils.openWeiboActivity(context, "android.intent.action.VIEW", stringBuilder.toString());
        }
    }

    public static void viewPageProductList(Context context, String str, String str2, String str3, Integer num, String str4) throws WeiboNotInstalledException {
        if (context == null) {
            throw new WeiboIllegalParameterException(ExceptionMsg.CONTEXT_ERROR);
        } else if (TextUtils.isEmpty(str)) {
            throw new WeiboIllegalParameterException(ExceptionMsg.PAGEID_ERROR);
        } else if (TextUtils.isEmpty(str2)) {
            throw new WeiboIllegalParameterException(ExceptionMsg.CARDID_ERROR);
        } else if (num == null || num.intValue() >= 0) {
            StringBuilder stringBuilder = new StringBuilder(Scheme.PAGEPRODUCTLIST);
            HashMap hashMap = new HashMap();
            hashMap.put(ParamKey.PAGEID, str);
            hashMap.put(ParamKey.CARDID, str2);
            hashMap.put(WebBrowserActivity.EXTRA_TITLE, str3);
            hashMap.put(JsInterface.KEY_PAGE, MessageService.MSG_DB_NOTIFY_REACHED);
            hashMap.put(ParamKey.COUNT, String.valueOf(num));
            hashMap.put(ParamKey.EXTPARAM, str4);
            stringBuilder.append(CommonUtils.buildUriQuery(hashMap));
            CommonUtils.openWeiboActivity(context, "android.intent.action.VIEW", stringBuilder.toString());
        } else {
            throw new WeiboIllegalParameterException(ExceptionMsg.COUNT_ERROR);
        }
    }

    public static void viewPageUserList(Context context, String str, String str2, String str3, Integer num, String str4) throws WeiboNotInstalledException {
        if (context == null) {
            throw new WeiboIllegalParameterException(ExceptionMsg.CONTEXT_ERROR);
        } else if (TextUtils.isEmpty(str)) {
            throw new WeiboIllegalParameterException(ExceptionMsg.PAGEID_ERROR);
        } else if (TextUtils.isEmpty(str2)) {
            throw new WeiboIllegalParameterException(ExceptionMsg.CARDID_ERROR);
        } else if (num == null || num.intValue() >= 0) {
            StringBuilder stringBuilder = new StringBuilder(Scheme.PAGEUSERLIST);
            HashMap hashMap = new HashMap();
            hashMap.put(ParamKey.PAGEID, str);
            hashMap.put(ParamKey.CARDID, str2);
            hashMap.put(WebBrowserActivity.EXTRA_TITLE, str3);
            hashMap.put(JsInterface.KEY_PAGE, MessageService.MSG_DB_NOTIFY_REACHED);
            hashMap.put(ParamKey.COUNT, String.valueOf(num));
            hashMap.put(ParamKey.EXTPARAM, str4);
            stringBuilder.append(CommonUtils.buildUriQuery(hashMap));
            CommonUtils.openWeiboActivity(context, "android.intent.action.VIEW", stringBuilder.toString());
        } else {
            throw new WeiboIllegalParameterException(ExceptionMsg.COUNT_ERROR);
        }
    }

    public static void viewPageWeiboList(Context context, String str, String str2, String str3, Integer num, String str4) throws WeiboNotInstalledException {
        if (context == null) {
            throw new WeiboIllegalParameterException(ExceptionMsg.CONTEXT_ERROR);
        } else if (TextUtils.isEmpty(str)) {
            throw new WeiboIllegalParameterException(ExceptionMsg.PAGEID_ERROR);
        } else if (TextUtils.isEmpty(str2)) {
            throw new WeiboIllegalParameterException(ExceptionMsg.CARDID_ERROR);
        } else if (num == null || num.intValue() >= 0) {
            StringBuilder stringBuilder = new StringBuilder(Scheme.PAGEWEIBOLIST);
            HashMap hashMap = new HashMap();
            hashMap.put(ParamKey.PAGEID, str);
            hashMap.put(ParamKey.CARDID, str2);
            hashMap.put(WebBrowserActivity.EXTRA_TITLE, str3);
            hashMap.put(JsInterface.KEY_PAGE, MessageService.MSG_DB_NOTIFY_REACHED);
            hashMap.put(ParamKey.COUNT, String.valueOf(num));
            hashMap.put(ParamKey.EXTPARAM, str4);
            stringBuilder.append(CommonUtils.buildUriQuery(hashMap));
            CommonUtils.openWeiboActivity(context, "android.intent.action.VIEW", stringBuilder.toString());
        } else {
            throw new WeiboIllegalParameterException(ExceptionMsg.COUNT_ERROR);
        }
    }

    public static void viewPagePhotoList(Context context, String str, String str2, String str3, Integer num, String str4) throws WeiboNotInstalledException {
        if (context == null) {
            throw new WeiboIllegalParameterException(ExceptionMsg.CONTEXT_ERROR);
        } else if (TextUtils.isEmpty(str)) {
            throw new WeiboIllegalParameterException(ExceptionMsg.PAGEID_ERROR);
        } else if (TextUtils.isEmpty(str2)) {
            throw new WeiboIllegalParameterException(ExceptionMsg.CARDID_ERROR);
        } else if (num == null || num.intValue() >= 0) {
            StringBuilder stringBuilder = new StringBuilder(Scheme.PAGEPHOTOLIST);
            HashMap hashMap = new HashMap();
            hashMap.put(ParamKey.PAGEID, str);
            hashMap.put(ParamKey.CARDID, str2);
            hashMap.put(WebBrowserActivity.EXTRA_TITLE, str3);
            hashMap.put(JsInterface.KEY_PAGE, MessageService.MSG_DB_NOTIFY_REACHED);
            hashMap.put(ParamKey.COUNT, String.valueOf(num));
            hashMap.put(ParamKey.EXTPARAM, str4);
            stringBuilder.append(CommonUtils.buildUriQuery(hashMap));
            CommonUtils.openWeiboActivity(context, "android.intent.action.VIEW", stringBuilder.toString());
        } else {
            throw new WeiboIllegalParameterException(ExceptionMsg.COUNT_ERROR);
        }
    }

    public static void viewPageDetailInfo(Context context, String str, String str2, String str3, String str4) throws WeiboNotInstalledException {
        if (context == null) {
            throw new WeiboIllegalParameterException(ExceptionMsg.CONTEXT_ERROR);
        } else if (TextUtils.isEmpty(str)) {
            throw new WeiboIllegalParameterException(ExceptionMsg.PAGEID_ERROR);
        } else if (TextUtils.isEmpty(str2)) {
            throw new WeiboIllegalParameterException(ExceptionMsg.CARDID_ERROR);
        } else {
            StringBuilder stringBuilder = new StringBuilder(Scheme.PAGEDETAILINFO);
            HashMap hashMap = new HashMap();
            hashMap.put(ParamKey.PAGEID, str);
            hashMap.put(ParamKey.CARDID, str2);
            hashMap.put(WebBrowserActivity.EXTRA_TITLE, str3);
            hashMap.put(ParamKey.EXTPARAM, str4);
            stringBuilder.append(CommonUtils.buildUriQuery(hashMap));
            CommonUtils.openWeiboActivity(context, "android.intent.action.VIEW", stringBuilder.toString());
        }
    }

    public static void openInWeiboBrowser(Context context, String str, String str2, String str3) throws WeiboNotInstalledException {
        if (context == null) {
            throw new WeiboIllegalParameterException(ExceptionMsg.CONTEXT_ERROR);
        } else if (TextUtils.isEmpty(str)) {
            throw new WeiboIllegalParameterException(ExceptionMsg.URL_ERROR);
        } else if (TextUtils.isEmpty(str2) || "topnav".equals(str2) || "default".equals(str2) || "fullscreen".equals(str2)) {
            StringBuilder stringBuilder = new StringBuilder(Scheme.BROWSER);
            HashMap hashMap = new HashMap();
            hashMap.put(SocialConstants.PARAM_URL, str);
            hashMap.put(ParamKey.SINAINTERNALBROWSER, str2);
            hashMap.put(ParamKey.EXTPARAM, str3);
            stringBuilder.append(CommonUtils.buildUriQuery(hashMap));
            CommonUtils.openWeiboActivity(context, "android.intent.action.VIEW", stringBuilder.toString());
        } else {
            throw new WeiboIllegalParameterException(ExceptionMsg.SINAINTERNALBROWSER);
        }
    }

    public static void displayInWeiboMap(Context context, Position position, String str) throws WeiboNotInstalledException {
        if (context == null) {
            throw new WeiboIllegalParameterException(ExceptionMsg.CONTEXT_ERROR);
        }
        String str2 = "http://weibo.cn/dpool/ttt/maps.php?xy=%s,%s&amp;size=320x320&amp;offset=%s";
        String str3 = a.d;
        String str4 = a.d;
        String str5 = a.d;
        if (position != null) {
            str3 = position.getStrLongitude();
            str4 = position.getStrLatitude();
            str5 = position.getStrOffset();
        }
        openInWeiboBrowser(context, String.format(str2, new Object[]{str3, str4, str5}), "default", str);
    }

    public static void openQrcodeScanner(Context context, String str) throws WeiboNotInstalledException {
        if (context == null) {
            throw new WeiboIllegalParameterException(ExceptionMsg.CONTEXT_ERROR);
        }
        StringBuilder stringBuilder = new StringBuilder(Scheme.QRCODE);
        HashMap hashMap = new HashMap();
        hashMap.put(ParamKey.EXTPARAM, str);
        stringBuilder.append(CommonUtils.buildUriQuery(hashMap));
        CommonUtils.openWeiboActivity(context, "android.intent.action.VIEW", stringBuilder.toString());
    }

    public static void viewNearPhotoList(Context context, String str, String str2, Integer num, String str3) throws WeiboNotInstalledException {
        viewPagePhotoList(context, new StringBuilder("100101").append(str).append("_").append(str2).toString(), "nearphoto", "\u5468\u8fb9\u70ed\u56fe", num, str3);
    }

    public static void viewPoiPhotoList(Context context, String str, Integer num, String str2) throws WeiboNotInstalledException {
        viewPagePhotoList(context, new StringBuilder("100101").append(str).toString(), "nearphoto", "\u5468\u8fb9\u70ed\u56fe", num, str2);
    }

    public static void viewPoiPage(Context context, String str, String str2, String str3, String str4) throws WeiboNotInstalledException {
        viewPageInfo(context, new StringBuilder("100101").append(str).append("_").append(str2).toString(), str3, str4);
    }

    public static void weiboDetail(Context context, String str, String str2) throws WeiboNotInstalledException {
        if (context == null) {
            throw new WeiboIllegalParameterException(ExceptionMsg.CONTEXT_ERROR);
        } else if (TextUtils.isEmpty(str)) {
            throw new WeiboIllegalParameterException(ExceptionMsg.PAGEID_ERROR);
        } else {
            StringBuilder stringBuilder = new StringBuilder(Scheme.MBLOGDETAIL);
            HashMap hashMap = new HashMap();
            hashMap.put(ParamKey.MBLOGID, str);
            hashMap.put(ParamKey.EXTPARAM, str2);
            stringBuilder.append(CommonUtils.buildUriQuery(hashMap));
            CommonUtils.openWeiboActivity(context, "android.intent.action.VIEW", stringBuilder.toString());
        }
    }
}
