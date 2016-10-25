package com.tencent.open;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.widget.Toast;
import com.alipay.sdk.util.h;
import com.qq.e.comm.constants.Constants.KEYS;
import com.sina.weibo.sdk.component.GameManager;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.Constants;
import com.tencent.connect.common.UIListenerManager;
import com.tencent.connect.share.QQShare;
import com.tencent.open.a.f;
import com.tencent.open.b.c;
import com.tencent.open.b.d;
import com.tencent.open.utils.Global;
import com.tencent.open.utils.HttpUtils;
import com.tencent.open.utils.ServerSetting;
import com.tencent.open.utils.SystemUtils;
import com.tencent.open.utils.ThreadManager;
import com.tencent.open.utils.Util;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import com.umeng.a;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xunlei.tdlive.R;
import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import org.android.agoo.message.MessageService;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: ProGuard
public class GameAppOperation extends BaseApi {
    public static final String GAME_FRIEND_ADD_MESSAGE = "add_msg";
    public static final String GAME_FRIEND_LABEL = "friend_label";
    public static final String GAME_FRIEND_OPENID = "fopen_id";
    public static final String GAME_SIGNATURE = "signature";
    public static final String GAME_UNION_ID = "unionid";
    public static final String GAME_UNION_NAME = "union_name";
    public static final String GAME_ZONE_ID = "zoneid";
    public static final char PIC_SYMBOLE = '\u0014';
    public static final String QQFAV_DATALINE_APPNAME = "app_name";
    public static final String QQFAV_DATALINE_AUDIOURL = "audioUrl";
    public static final String QQFAV_DATALINE_DESCRIPTION = "description";
    public static final String QQFAV_DATALINE_FILEDATA = "file_data";
    public static final String QQFAV_DATALINE_IMAGEURL = "image_url";
    public static final String QQFAV_DATALINE_OPENID = "open_id";
    public static final String QQFAV_DATALINE_REQTYPE = "req_type";
    public static final String QQFAV_DATALINE_SHAREID = "share_id";
    public static final String QQFAV_DATALINE_SRCTYPE = "src_type";
    public static final String QQFAV_DATALINE_TITLE = "title";
    public static final int QQFAV_DATALINE_TYPE_AUDIO = 2;
    public static final int QQFAV_DATALINE_TYPE_DEFAULT = 1;
    public static final int QQFAV_DATALINE_TYPE_IMAGE_TEXT = 5;
    public static final int QQFAV_DATALINE_TYPE_TEXT = 6;
    public static final String QQFAV_DATALINE_URL = "url";
    public static final String QQFAV_DATALINE_VERSION = "version";
    public static final String SHARE_PRIZE_ACTIVITY_ID = "activityid";
    public static final String SHARE_PRIZE_IMAGE_URL = "imageUrl";
    public static final String SHARE_PRIZE_SHARE_ID = "shareid";
    public static final String SHARE_PRIZE_SHARE_ID_LIST = "shareid_list";
    public static final String SHARE_PRIZE_SUMMARY = "summary";
    public static final int SHARE_PRIZE_SUMMARY_MAX_LENGTH = 60;
    public static final String SHARE_PRIZE_TARGET_URL = "targetUrl";
    public static final String SHARE_PRIZE_TITLE = "title";
    public static final int SHARE_PRIZE_TITLE_MAX_LENGTH = 45;
    public static final String TROOPBAR_ID = "troopbar_id";

    // compiled from: ProGuard
    class AnonymousClass_1 implements Runnable {
        final /* synthetic */ IUiListener a;
        final /* synthetic */ String b;
        final /* synthetic */ Activity c;
        final /* synthetic */ Bundle d;

        AnonymousClass_1(IUiListener iUiListener, String str, Activity activity, Bundle bundle) {
            this.a = iUiListener;
            this.b = str;
            this.c = activity;
            this.d = bundle;
        }

        public void run() {
            Bundle a = GameAppOperation.this.a();
            if (a == null) {
                String str = "accesstoken or openid or appid is null, please login first!";
                f.e("openSDK_LOG.GameAppOperation", str);
                this.a.onError(new UiError(-5, Constants.MSG_PARAM_ERROR, str));
                return;
            }
            a.putString(SHARE_PRIZE_ACTIVITY_ID, this.b);
            try {
                JSONObject request = HttpUtils.request(GameAppOperation.this.mToken, this.c.getApplicationContext(), ServerSetting.URL_PRIZE_MAKE_SHARE_URL, a, Constants.HTTP_GET);
                try {
                    int i = request.getInt(KEYS.RET);
                    int i2 = request.getInt("subCode");
                    if (i == 0 && i2 == 0) {
                        this.d.putString(SHARE_PRIZE_TARGET_URL, request.getString("share_url"));
                        new QQShare(this.c.getApplicationContext(), GameAppOperation.this.mToken).shareToQQ(this.c, this.d, this.a);
                        return;
                    }
                    str = request.getString(SocialConstants.PARAM_SEND_MSG);
                    this.a.onError(new UiError(i, "make_share_url error.", str));
                    f.c("openSDK_LOG.GameAppOperation", new StringBuilder("code = ").append(i).append(", subcode = errormsg = ").append(str).toString());
                } catch (JSONException e) {
                    f.e("openSDK_LOG.GameAppOperation", new StringBuilder("JSONException occur in make_share_url, errorMsg: ").append(e.getMessage()).toString());
                    this.a.onError(new UiError(-4, Constants.MSG_JSON_ERROR, a.d));
                }
            } catch (Throwable e2) {
                f.b("openSDK_LOG.GameAppOperation", "Exception occur in make_share_url", e2);
                this.a.onError(new UiError(-2, Constants.MSG_IO_ERROR, e2.getMessage()));
            }
        }
    }

    // compiled from: ProGuard
    class AnonymousClass_2 implements Runnable {
        final /* synthetic */ IUiListener a;
        final /* synthetic */ Bundle b;
        final /* synthetic */ Context c;

        AnonymousClass_2(IUiListener iUiListener, Bundle bundle, Context context) {
            this.a = iUiListener;
            this.b = bundle;
            this.c = context;
        }

        public void run() {
            Bundle a = GameAppOperation.this.a();
            if (a == null) {
                String str = "accesstoken or openid or appid is null, please login first!";
                f.e("openSDK_LOG.GameAppOperation", str);
                this.a.onError(new UiError(-5, Constants.MSG_PARAM_ERROR, str));
                return;
            }
            a.putAll(this.b);
            try {
                this.a.onComplete(HttpUtils.request(GameAppOperation.this.mToken, this.c, ServerSetting.URL_PRIZE_QUERY_UNEXCHANGE, a, Constants.HTTP_GET));
            } catch (Throwable e) {
                f.b("openSDK_LOG.GameAppOperation", "Exception occur in queryUnexchangePrize", e);
                this.a.onError(new UiError(-2, Constants.MSG_IO_ERROR, e.getMessage()));
            }
        }
    }

    // compiled from: ProGuard
    class AnonymousClass_3 implements Runnable {
        final /* synthetic */ IUiListener a;
        final /* synthetic */ StringBuffer b;
        final /* synthetic */ Context c;

        AnonymousClass_3(IUiListener iUiListener, StringBuffer stringBuffer, Context context) {
            this.a = iUiListener;
            this.b = stringBuffer;
            this.c = context;
        }

        public void run() {
            Bundle a = GameAppOperation.this.a();
            if (a == null) {
                String str = "accesstoken or openid or appid is null, please login first!";
                f.e("openSDK_LOG.GameAppOperation", str);
                this.a.onError(new UiError(-5, Constants.MSG_PARAM_ERROR, str));
                return;
            }
            a.putString(SHARE_PRIZE_SHARE_ID, this.b.toString());
            a.putString(com.taobao.accs.common.Constants.KEY_IMEI, c.b(Global.getContext()));
            try {
                this.a.onComplete(HttpUtils.request(GameAppOperation.this.mToken, this.c, ServerSetting.URL_PRIZE_EXCHANGE, a, Constants.HTTP_GET));
            } catch (Throwable e) {
                f.b("openSDK_LOG.GameAppOperation", "Exception occur in exchangePrize", e);
                this.a.onError(new UiError(-2, Constants.MSG_IO_ERROR, e.getMessage()));
            }
        }
    }

    // compiled from: ProGuard
    class AnonymousClass_4 implements Runnable {
        final /* synthetic */ IUiListener a;
        final /* synthetic */ String b;
        final /* synthetic */ Activity c;

        AnonymousClass_4(IUiListener iUiListener, String str, Activity activity) {
            this.a = iUiListener;
            this.b = str;
            this.c = activity;
        }

        public void run() {
            Bundle a = GameAppOperation.this.a();
            if (a == null) {
                String str = "accesstoken or openid or appid is null, please login first!";
                f.e("openSDK_LOG.GameAppOperation", str);
                this.a.onError(new UiError(-5, Constants.MSG_PARAM_ERROR, str));
                return;
            }
            a.putString(SHARE_PRIZE_ACTIVITY_ID, this.b);
            try {
                this.a.onComplete(HttpUtils.request(GameAppOperation.this.mToken, this.c.getApplicationContext(), ServerSetting.URL_PRIZE_GET_ACTIVITY_STATE, a, Constants.HTTP_GET));
            } catch (Throwable e) {
                f.b("openSDK_LOG.GameAppOperation", "Exception occur in make_share_url", e);
                this.a.onError(new UiError(-6, "Exception occur in make_share_url", e.getMessage()));
            }
        }
    }

    public GameAppOperation(QQToken qQToken) {
        super(qQToken);
    }

    public void makeFriend(Activity activity, Bundle bundle) {
        f.c("openSDK_LOG.GameAppOperation", "-->makeFriend()  -- start");
        if (bundle == null) {
            f.e("openSDK_LOG.GameAppOperation", "-->makeFriend params is null");
            d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_MAKE_FRIEND, Constants.VIA_REPORT_TYPE_MAKE_FRIEND, Constants.VIA_REPORT_TYPE_BIND_GROUP, MessageService.MSG_DB_NOTIFY_REACHED);
            return;
        }
        String string = bundle.getString(GAME_FRIEND_OPENID);
        if (TextUtils.isEmpty(string)) {
            f.e("openSDK_LOG.GameAppOperation", "-->make friend, fOpenid is empty.");
            d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_MAKE_FRIEND, Constants.VIA_REPORT_TYPE_MAKE_FRIEND, Constants.VIA_REPORT_TYPE_BIND_GROUP, MessageService.MSG_DB_NOTIFY_REACHED);
            return;
        }
        Object string2 = bundle.getString(GAME_FRIEND_LABEL);
        Object string3 = bundle.getString(GAME_FRIEND_ADD_MESSAGE);
        Object applicationLable = Util.getApplicationLable(activity);
        Object openId = this.mToken.getOpenId();
        Object appId = this.mToken.getAppId();
        f.a("openSDK_LOG.GameAppOperation", new StringBuilder("-->make friend, fOpenid: ").append(string).append(" | label: ").append(string2).append(" | message: ").append(string3).append(" | openid: ").append(openId).append(" | appid:").append(appId).toString());
        StringBuffer stringBuffer = new StringBuffer("mqqapi://gamesdk/add_friend?src_type=app&version=1");
        stringBuffer.append(new StringBuilder("&fopen_id=").append(Base64.encodeToString(Util.getBytesUTF8(string), QQFAV_DATALINE_TYPE_AUDIO)).toString());
        if (!TextUtils.isEmpty(openId)) {
            stringBuffer.append(new StringBuilder("&open_id=").append(Base64.encodeToString(Util.getBytesUTF8(openId), QQFAV_DATALINE_TYPE_AUDIO)).toString());
        }
        if (!TextUtils.isEmpty(appId)) {
            stringBuffer.append(new StringBuilder("&app_id=").append(appId).toString());
        }
        if (!TextUtils.isEmpty(string2)) {
            stringBuffer.append(new StringBuilder("&friend_label=").append(Base64.encodeToString(Util.getBytesUTF8(string2), QQFAV_DATALINE_TYPE_AUDIO)).toString());
        }
        if (!TextUtils.isEmpty(string3)) {
            stringBuffer.append(new StringBuilder("&add_msg=").append(Base64.encodeToString(Util.getBytesUTF8(string3), QQFAV_DATALINE_TYPE_AUDIO)).toString());
        }
        if (!TextUtils.isEmpty(applicationLable)) {
            stringBuffer.append(new StringBuilder("&app_name=").append(Base64.encodeToString(Util.getBytesUTF8(applicationLable), QQFAV_DATALINE_TYPE_AUDIO)).toString());
        }
        f.a("openSDK_LOG.GameAppOperation", new StringBuilder("-->make friend, url: ").append(stringBuffer.toString()).toString());
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(stringBuffer.toString()));
        if (!hasActivityForIntent(intent) || Util.isQQVersionBelow(activity, SystemUtils.QQ_VERSION_NAME_5_1_0)) {
            f.d("openSDK_LOG.GameAppOperation", "-->make friend, there is no activity.");
            a(activity);
            d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_MAKE_FRIEND, Constants.VIA_REPORT_TYPE_MAKE_FRIEND, Constants.VIA_REPORT_TYPE_BIND_GROUP, MessageService.MSG_DB_NOTIFY_REACHED);
        } else {
            f.c("openSDK_LOG.GameAppOperation", "-->makeFriend target activity found, qqver greater than 5.1.0");
            try {
                activity.startActivity(intent);
                d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_MAKE_FRIEND, Constants.VIA_REPORT_TYPE_MAKE_FRIEND, Constants.VIA_REPORT_TYPE_BIND_GROUP, MessageService.MSG_DB_READY_REPORT);
            } catch (Throwable e) {
                f.b("openSDK_LOG.GameAppOperation", "-->make friend, start activity exception.", e);
                a(activity);
                d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_MAKE_FRIEND, Constants.VIA_REPORT_TYPE_MAKE_FRIEND, Constants.VIA_REPORT_TYPE_BIND_GROUP, MessageService.MSG_DB_NOTIFY_REACHED);
            }
        }
        f.c("openSDK_LOG.GameAppOperation", "-->makeFriend()  -- end");
    }

    public void bindQQGroup(Activity activity, Bundle bundle) {
        f.c("openSDK_LOG.GameAppOperation", "-->bindQQGroup()  -- start");
        if (activity == null) {
            f.e("openSDK_LOG.GameAppOperation", "-->bindQQGroup, activity is empty.");
            d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_BIND_GROUP, Constants.VIA_REPORT_TYPE_BIND_GROUP, Constants.VIA_REPORT_TYPE_BIND_GROUP, MessageService.MSG_DB_NOTIFY_REACHED);
        } else if (bundle == null) {
            Toast.makeText(activity, "Bundle\u53c2\u6570\u4e3a\u7a7a", 0).show();
            f.e("openSDK_LOG.GameAppOperation", "-->bindQQGroup, params is empty.");
            d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_BIND_GROUP, Constants.VIA_REPORT_TYPE_BIND_GROUP, Constants.VIA_REPORT_TYPE_BIND_GROUP, MessageService.MSG_DB_NOTIFY_REACHED);
        } else {
            Object applicationLable = Util.getApplicationLable(activity);
            StringBuffer stringBuffer = new StringBuffer("mqqapi://gamesdk/bind_group?src_type=app&version=1");
            if (!TextUtils.isEmpty(applicationLable)) {
                stringBuffer.append(new StringBuilder("&app_name=").append(Base64.encodeToString(Util.getBytesUTF8(applicationLable), QQFAV_DATALINE_TYPE_AUDIO)).toString());
            }
            applicationLable = bundle.getString(GAME_UNION_ID);
            if (TextUtils.isEmpty(applicationLable)) {
                Toast.makeText(activity, "\u6e38\u620f\u516c\u4f1aID\u4e3a\u7a7a", 0).show();
                f.e("openSDK_LOG.GameAppOperation", "-->bindQQGroup, game union id is empty.");
                d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_BIND_GROUP, Constants.VIA_REPORT_TYPE_BIND_GROUP, Constants.VIA_REPORT_TYPE_BIND_GROUP, MessageService.MSG_DB_NOTIFY_REACHED);
                return;
            }
            stringBuffer.append(new StringBuilder("&unionid=").append(Base64.encodeToString(Util.getBytesUTF8(applicationLable), QQFAV_DATALINE_TYPE_AUDIO)).toString());
            applicationLable = bundle.getString(GAME_UNION_NAME);
            if (TextUtils.isEmpty(applicationLable)) {
                Toast.makeText(activity, "\u6e38\u620f\u516c\u4f1a\u540d\u79f0\u4e3a\u7a7a", 0).show();
                f.e("openSDK_LOG.GameAppOperation", "-->bindQQGroup, game union name is empty.");
                d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_BIND_GROUP, Constants.VIA_REPORT_TYPE_BIND_GROUP, Constants.VIA_REPORT_TYPE_BIND_GROUP, MessageService.MSG_DB_NOTIFY_REACHED);
                return;
            }
            stringBuffer.append(new StringBuilder("&union_name=").append(Base64.encodeToString(Util.getBytesUTF8(applicationLable), QQFAV_DATALINE_TYPE_AUDIO)).toString());
            applicationLable = bundle.getString(GAME_ZONE_ID);
            if (TextUtils.isEmpty(applicationLable)) {
                Toast.makeText(activity, "\u6e38\u620f\u533a\u57dfID\u4e3a\u7a7a", 0).show();
                f.e("openSDK_LOG.GameAppOperation", "-->bindQQGroup, game zone id  is empty.");
                d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_BIND_GROUP, Constants.VIA_REPORT_TYPE_BIND_GROUP, Constants.VIA_REPORT_TYPE_BIND_GROUP, MessageService.MSG_DB_NOTIFY_REACHED);
                return;
            }
            stringBuffer.append(new StringBuilder("&zoneid=").append(Base64.encodeToString(Util.getBytesUTF8(applicationLable), QQFAV_DATALINE_TYPE_AUDIO)).toString());
            applicationLable = bundle.getString(GAME_SIGNATURE);
            if (TextUtils.isEmpty(applicationLable)) {
                Toast.makeText(activity, "\u6e38\u620f\u7b7e\u540d\u4e3a\u7a7a", 0).show();
                f.e("openSDK_LOG.GameAppOperation", "-->bindQQGroup, game signature is empty.");
                d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_BIND_GROUP, Constants.VIA_REPORT_TYPE_BIND_GROUP, Constants.VIA_REPORT_TYPE_BIND_GROUP, MessageService.MSG_DB_NOTIFY_REACHED);
                return;
            }
            stringBuffer.append(new StringBuilder("&signature=").append(Base64.encodeToString(Util.getBytesUTF8(applicationLable), QQFAV_DATALINE_TYPE_AUDIO)).toString());
            applicationLable = this.mToken.getOpenId();
            if (TextUtils.isEmpty(applicationLable)) {
                Toast.makeText(activity, "Openid\u4e3a\u7a7a", 0).show();
                f.e("openSDK_LOG.GameAppOperation", "-->bindQQGroup, openid is empty.");
                d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_BIND_GROUP, Constants.VIA_REPORT_TYPE_BIND_GROUP, Constants.VIA_REPORT_TYPE_BIND_GROUP, MessageService.MSG_DB_NOTIFY_REACHED);
                return;
            }
            stringBuffer.append(new StringBuilder("&openid=").append(Base64.encodeToString(Util.getBytesUTF8(applicationLable), QQFAV_DATALINE_TYPE_AUDIO)).toString());
            Bundle composeActivityParams = composeActivityParams();
            for (String str : composeActivityParams.keySet()) {
                composeActivityParams.putString(str, Base64.encodeToString(Util.getBytesUTF8(composeActivityParams.getString(str)), QQFAV_DATALINE_TYPE_AUDIO));
            }
            stringBuffer.append(new StringBuilder(com.alipay.sdk.sys.a.b).append(HttpUtils.encodeUrl(composeActivityParams)).toString());
            f.a("openSDK_LOG.GameAppOperation", new StringBuilder("-->bindQQGroup, url: ").append(stringBuffer.toString()).toString());
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(stringBuffer.toString()));
            if (!hasActivityForIntent(intent) || Util.isQQVersionBelow(activity, SystemUtils.QQ_VERSION_NAME_5_1_0)) {
                f.d("openSDK_LOG.GameAppOperation", "-->bind group, there is no activity, show download page.");
                d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_BIND_GROUP, Constants.VIA_REPORT_TYPE_BIND_GROUP, Constants.VIA_REPORT_TYPE_BIND_GROUP, MessageService.MSG_DB_NOTIFY_REACHED);
                a(activity);
            } else {
                f.c("openSDK_LOG.GameAppOperation", "-->bingQQGroup target activity found, qqver > 5.1.0");
                try {
                    activity.startActivity(intent);
                    d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_BIND_GROUP, Constants.VIA_REPORT_TYPE_BIND_GROUP, Constants.VIA_REPORT_TYPE_BIND_GROUP, MessageService.MSG_DB_READY_REPORT);
                } catch (Throwable e) {
                    f.b("openSDK_LOG.GameAppOperation", "-->bind group, start activity exception.", e);
                    d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_BIND_GROUP, Constants.VIA_REPORT_TYPE_BIND_GROUP, Constants.VIA_REPORT_TYPE_BIND_GROUP, MessageService.MSG_DB_NOTIFY_REACHED);
                    a(activity);
                }
            }
            f.c("openSDK_LOG.GameAppOperation", "-->bindQQGroup()  -- end");
        }
    }

    public void addToQQFavorites(Activity activity, Bundle bundle, IUiListener iUiListener) {
        f.c("openSDK_LOG.GameAppOperation", "addToQQFavorites() -- start");
        int i = bundle.getInt(QQFAV_DATALINE_REQTYPE, QQFAV_DATALINE_TYPE_DEFAULT);
        if (a(activity, bundle, iUiListener)) {
            String string;
            StringBuffer stringBuffer = new StringBuffer("mqqapi://share/to_qqfav?src_type=app&version=1&file_type=news");
            Object string2 = bundle.getString(QQFAV_DATALINE_IMAGEURL);
            Object string3 = bundle.getString(SHARE_PRIZE_TITLE);
            Object string4 = bundle.getString(QQFAV_DATALINE_DESCRIPTION);
            Object string5 = bundle.getString(QQFAV_DATALINE_URL);
            Object string6 = bundle.getString(QQFAV_DATALINE_AUDIOURL);
            String applicationLable = Util.getApplicationLable(activity);
            if (applicationLable == null) {
                string = bundle.getString(QQFAV_DATALINE_APPNAME);
            } else {
                string = applicationLable;
            }
            ArrayList stringArrayList = bundle.getStringArrayList(QQFAV_DATALINE_FILEDATA);
            Object appId = this.mToken.getAppId();
            Object openId = this.mToken.getOpenId();
            f.a("openSDK_LOG.GameAppOperation", new StringBuilder("addToQQFavorites openId:").append(openId).toString());
            if (!TextUtils.isEmpty(string2)) {
                stringBuffer.append(new StringBuilder("&image_url=").append(Base64.encodeToString(Util.getBytesUTF8(string2), QQFAV_DATALINE_TYPE_AUDIO)).toString());
            }
            if (stringArrayList != null) {
                StringBuffer stringBuffer2 = new StringBuffer();
                int size = stringArrayList.size();
                for (int i2 = 0; i2 < size; i2++) {
                    try {
                        stringBuffer2.append(URLEncoder.encode(((String) stringArrayList.get(i2)).trim(), GameManager.DEFAULT_CHARSET));
                    } catch (Throwable e) {
                        e.printStackTrace();
                        f.b("openSDK_LOG.GameAppOperation", "UnsupportedEncodingException", e);
                        stringBuffer2.append(URLEncoder.encode(((String) stringArrayList.get(i2)).trim()));
                    }
                    if (i2 != size - 1) {
                        stringBuffer2.append(h.b);
                    }
                }
                stringBuffer.append(new StringBuilder("&file_data=").append(Base64.encodeToString(Util.getBytesUTF8(stringBuffer2.toString()), QQFAV_DATALINE_TYPE_AUDIO)).toString());
            }
            if (!TextUtils.isEmpty(string3)) {
                stringBuffer.append(new StringBuilder("&title=").append(Base64.encodeToString(Util.getBytesUTF8(string3), QQFAV_DATALINE_TYPE_AUDIO)).toString());
            }
            if (!TextUtils.isEmpty(string4)) {
                stringBuffer.append(new StringBuilder("&description=").append(Base64.encodeToString(Util.getBytesUTF8(string4), QQFAV_DATALINE_TYPE_AUDIO)).toString());
            }
            if (!TextUtils.isEmpty(appId)) {
                stringBuffer.append(new StringBuilder("&share_id=").append(appId).toString());
            }
            if (!TextUtils.isEmpty(string5)) {
                stringBuffer.append(new StringBuilder("&url=").append(Base64.encodeToString(Util.getBytesUTF8(string5), QQFAV_DATALINE_TYPE_AUDIO)).toString());
            }
            if (!TextUtils.isEmpty(string)) {
                if (string.length() > 20) {
                    string = string.substring(0, R.styleable.Toolbar_navigationIcon) + "...";
                }
                stringBuffer.append(new StringBuilder("&app_name=").append(Base64.encodeToString(Util.getBytesUTF8(string), QQFAV_DATALINE_TYPE_AUDIO)).toString());
            }
            if (!TextUtils.isEmpty(openId)) {
                stringBuffer.append(new StringBuilder("&open_id=").append(Base64.encodeToString(Util.getBytesUTF8(openId), QQFAV_DATALINE_TYPE_AUDIO)).toString());
            }
            if (!TextUtils.isEmpty(string6)) {
                stringBuffer.append(new StringBuilder("&audioUrl=").append(Base64.encodeToString(Util.getBytesUTF8(string6), QQFAV_DATALINE_TYPE_AUDIO)).toString());
            }
            stringBuffer.append(new StringBuilder("&req_type=").append(Base64.encodeToString(Util.getBytesUTF8(String.valueOf(i)), QQFAV_DATALINE_TYPE_AUDIO)).toString());
            f.a("openSDK_LOG.GameAppOperation", new StringBuilder("addToQQFavorites url: ").append(stringBuffer.toString()).toString());
            com.tencent.connect.a.a.a(Global.getContext(), this.mToken, "requireApi", SystemUtils.QQFAVORITES_CALLBACK_ACTION);
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(stringBuffer.toString()));
            intent.putExtra("pkg_name", activity.getPackageName());
            UIListenerManager.getInstance().setListnerWithAction(SystemUtils.QQFAVORITES_CALLBACK_ACTION, iUiListener);
            if (hasActivityForIntent(intent)) {
                if (!Util.isQQVersionBelow(activity, SystemUtils.QQ_VERSION_NAME_5_2_0)) {
                    try {
                        activity.startActivity(intent);
                        a(Constants.VIA_REPORT_TYPE_QQFAVORITES, i, MessageService.MSG_DB_READY_REPORT);
                    } catch (Throwable e2) {
                        f.b("openSDK_LOG.GameAppOperation", "-->addToQQFavorites, start activity exception.", e2);
                        a(Constants.VIA_REPORT_TYPE_QQFAVORITES, i, MessageService.MSG_DB_NOTIFY_REACHED);
                        a(activity);
                    }
                    f.c("openSDK_LOG.GameAppOperation", "addToQQFavorites() --end");
                    return;
                }
            }
            f.d("openSDK_LOG.GameAppOperation", "-->addToQQFavorites, there is no activity, show download page.");
            a(Constants.VIA_REPORT_TYPE_QQFAVORITES, i, MessageService.MSG_DB_NOTIFY_REACHED);
            a(activity);
            f.c("openSDK_LOG.GameAppOperation", "addToQQFavorites() --end");
            return;
        }
        f.e("openSDK_LOG.GameAppOperation", "-->addToQQFavorites check parames failed");
        a(Constants.VIA_REPORT_TYPE_QQFAVORITES, i, MessageService.MSG_DB_NOTIFY_REACHED);
    }

    public void sendToMyComputer(Activity activity, Bundle bundle, IUiListener iUiListener) {
        f.c("openSDK_LOG.GameAppOperation", "sendToMyComputer() --start");
        int i = bundle.getInt(QQFAV_DATALINE_REQTYPE, QQFAV_DATALINE_TYPE_DEFAULT);
        if (a(activity, bundle, iUiListener)) {
            String string;
            StringBuffer stringBuffer = new StringBuffer("mqqapi://share/to_qqdataline?src_type=app&version=1&file_type=news");
            Object string2 = bundle.getString(QQFAV_DATALINE_IMAGEURL);
            Object string3 = bundle.getString(SHARE_PRIZE_TITLE);
            Object string4 = bundle.getString(QQFAV_DATALINE_DESCRIPTION);
            Object string5 = bundle.getString(QQFAV_DATALINE_URL);
            Object string6 = bundle.getString(QQFAV_DATALINE_AUDIOURL);
            String applicationLable = Util.getApplicationLable(activity);
            if (applicationLable == null) {
                string = bundle.getString(QQFAV_DATALINE_APPNAME);
            } else {
                string = applicationLable;
            }
            ArrayList stringArrayList = bundle.getStringArrayList(QQFAV_DATALINE_FILEDATA);
            Object appId = this.mToken.getAppId();
            Object openId = this.mToken.getOpenId();
            f.a("openSDK_LOG.GameAppOperation", new StringBuilder("openId:").append(openId).toString());
            if (!TextUtils.isEmpty(string2)) {
                stringBuffer.append(new StringBuilder("&image_url=").append(Base64.encodeToString(Util.getBytesUTF8(string2), QQFAV_DATALINE_TYPE_AUDIO)).toString());
            }
            if (stringArrayList != null) {
                StringBuffer stringBuffer2 = new StringBuffer();
                int size = stringArrayList.size();
                for (int i2 = 0; i2 < size; i2++) {
                    try {
                        stringBuffer2.append(URLEncoder.encode(((String) stringArrayList.get(i2)).trim(), GameManager.DEFAULT_CHARSET));
                    } catch (Throwable e) {
                        e.printStackTrace();
                        f.b("openSDK_LOG.GameAppOperation", "UnsupportedEncodingException", e);
                        stringBuffer2.append(URLEncoder.encode(((String) stringArrayList.get(i2)).trim()));
                    }
                    if (i2 != size - 1) {
                        stringBuffer2.append(h.b);
                    }
                }
                stringBuffer.append(new StringBuilder("&file_data=").append(Base64.encodeToString(Util.getBytesUTF8(stringBuffer2.toString()), QQFAV_DATALINE_TYPE_AUDIO)).toString());
            }
            if (!TextUtils.isEmpty(string3)) {
                stringBuffer.append(new StringBuilder("&title=").append(Base64.encodeToString(Util.getBytesUTF8(string3), QQFAV_DATALINE_TYPE_AUDIO)).toString());
            }
            if (!TextUtils.isEmpty(string4)) {
                stringBuffer.append(new StringBuilder("&description=").append(Base64.encodeToString(Util.getBytesUTF8(string4), QQFAV_DATALINE_TYPE_AUDIO)).toString());
            }
            if (!TextUtils.isEmpty(appId)) {
                stringBuffer.append(new StringBuilder("&share_id=").append(appId).toString());
            }
            if (!TextUtils.isEmpty(string5)) {
                stringBuffer.append(new StringBuilder("&url=").append(Base64.encodeToString(Util.getBytesUTF8(string5), QQFAV_DATALINE_TYPE_AUDIO)).toString());
            }
            if (!TextUtils.isEmpty(string)) {
                if (string.length() > 20) {
                    string = string.substring(0, R.styleable.Toolbar_navigationIcon) + "...";
                }
                stringBuffer.append(new StringBuilder("&app_name=").append(Base64.encodeToString(Util.getBytesUTF8(string), QQFAV_DATALINE_TYPE_AUDIO)).toString());
            }
            if (!TextUtils.isEmpty(openId)) {
                stringBuffer.append(new StringBuilder("&open_id=").append(Base64.encodeToString(Util.getBytesUTF8(openId), QQFAV_DATALINE_TYPE_AUDIO)).toString());
            }
            if (!TextUtils.isEmpty(string6)) {
                stringBuffer.append(new StringBuilder("&audioUrl=").append(Base64.encodeToString(Util.getBytesUTF8(string6), QQFAV_DATALINE_TYPE_AUDIO)).toString());
            }
            stringBuffer.append(new StringBuilder("&req_type=").append(Base64.encodeToString(Util.getBytesUTF8(String.valueOf(i)), QQFAV_DATALINE_TYPE_AUDIO)).toString());
            f.a("openSDK_LOG.GameAppOperation", new StringBuilder("sendToMyComputer url: ").append(stringBuffer.toString()).toString());
            com.tencent.connect.a.a.a(Global.getContext(), this.mToken, "requireApi", SystemUtils.QQDATALINE_CALLBACK_ACTION);
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(stringBuffer.toString()));
            intent.putExtra("pkg_name", activity.getPackageName());
            UIListenerManager.getInstance().setListnerWithAction(SystemUtils.QQDATALINE_CALLBACK_ACTION, iUiListener);
            if (hasActivityForIntent(intent)) {
                if (!Util.isQQVersionBelow(activity, SystemUtils.QQ_VERSION_NAME_5_2_0)) {
                    try {
                        startAssistActivity(activity, Constants.REQUEST_SEND_TO_MY_COMPUTER, intent, false);
                        a(Constants.VIA_REPORT_TYPE_DATALINE, i, MessageService.MSG_DB_READY_REPORT);
                    } catch (Throwable e2) {
                        f.b("openSDK_LOG.GameAppOperation", "-->sendToMyComputer, start activity exception.", e2);
                        a(Constants.VIA_REPORT_TYPE_DATALINE, i, MessageService.MSG_DB_NOTIFY_REACHED);
                        a(activity);
                    }
                    f.c("openSDK_LOG.GameAppOperation", "sendToMyComputer() --end");
                    return;
                }
            }
            f.d("openSDK_LOG.GameAppOperation", "-->sendToMyComputer, there is no activity, show download page.");
            a(Constants.VIA_REPORT_TYPE_DATALINE, i, MessageService.MSG_DB_NOTIFY_REACHED);
            a(activity);
            f.c("openSDK_LOG.GameAppOperation", "sendToMyComputer() --end");
            return;
        }
        f.e("openSDK_LOG.GameAppOperation", "-->sendToMyComputer check parames failed");
        a(Constants.VIA_REPORT_TYPE_DATALINE, i, MessageService.MSG_DB_NOTIFY_REACHED);
    }

    public void shareToTroopBar(Activity activity, Bundle bundle, IUiListener iUiListener) {
        f.c("openSDK_LOG.GameAppOperation", "shareToTroopBar() -- start");
        String str = Constants.MSG_PARAM_ERROR;
        if (iUiListener == null) {
            f.e("openSDK_LOG.GameAppOperation", "listener is null!");
        } else if (activity == null || bundle == null) {
            String str2 = "activity or params is null!";
            f.e("openSDK_LOG.GameAppOperation", str2);
            iUiListener.onError(new UiError(-5, str, str2));
        } else {
            Object string = bundle.getString(SHARE_PRIZE_TITLE);
            if (TextUtils.isEmpty(string)) {
                iUiListener.onError(new UiError(-5, "\u4f20\u5165\u53c2\u6570\u4e0d\u53ef\u4ee5\u4e3a\u7a7a: title is null", null));
                f.e("openSDK_LOG.GameAppOperation", "shareToTroopBar() -- title is null");
            } else if (string.length() < 4 || string.length() > 25) {
                iUiListener.onError(new UiError(-5, "\u4f20\u5165\u53c2\u6570\u6709\u8bef!: title size: 4 ~ 25", null));
                f.e("openSDK_LOG.GameAppOperation", "shareToTroopBar() -- title size: 4 ~ 25");
            } else {
                Object string2 = bundle.getString(QQFAV_DATALINE_DESCRIPTION);
                if (TextUtils.isEmpty(string2)) {
                    iUiListener.onError(new UiError(-5, "\u4f20\u5165\u53c2\u6570\u4e0d\u53ef\u4ee5\u4e3a\u7a7a: description is null", null));
                    f.e("openSDK_LOG.GameAppOperation", "shareToTroopBar() -- description is null");
                } else if (string2.length() < 10 || string2.length() > 700) {
                    iUiListener.onError(new UiError(-5, "\u4f20\u5165\u53c2\u6570\u6709\u8bef!: description size: 10 ~ 700", null));
                    f.e("openSDK_LOG.GameAppOperation", "shareToTroopBar() -- description size: 10 ~ 700");
                } else {
                    ArrayList stringArrayList = bundle.getStringArrayList(QQFAV_DATALINE_FILEDATA);
                    Object stringBuffer = new StringBuffer();
                    if (stringArrayList != null && stringArrayList.size() > 0) {
                        int size = stringArrayList.size();
                        if (size > 9) {
                            iUiListener.onError(new UiError(-5, "\u4f20\u5165\u53c2\u6570\u6709\u8bef!: file_data size: 1 ~ 9", null));
                            f.e("openSDK_LOG.GameAppOperation", "shareToTroopBar() -- file_data size: 1 ~ 9");
                            return;
                        }
                        int i = 0;
                        while (i < size) {
                            str = ((String) stringArrayList.get(i)).trim();
                            if (str.startsWith("/")) {
                                if (str.startsWith("/") && !new File(str).exists()) {
                                    iUiListener.onError(new UiError(-6, Constants.MSG_PARAM_ERROR, "\u56fe\u7247\u6587\u4ef6\u4e0d\u5b58\u5728"));
                                    f.e("openSDK_LOG.GameAppOperation", "shareToTroopBar(): \u56fe\u7247\u6587\u4ef6\u4e0d\u5b58\u5728");
                                    return;
                                }
                                i++;
                            } else {
                                iUiListener.onError(new UiError(-6, Constants.MSG_PARAM_ERROR, "file_data\u5e94\u8be5\u4e3a\u672c\u5730\u56fe\u7247"));
                                f.e("openSDK_LOG.GameAppOperation", "shareToTroopBar(): file_data\u5e94\u8be5\u4e3a\u672c\u5730\u56fe\u7247");
                                return;
                            }
                        }
                        for (i = 0; i < size; i++) {
                            try {
                                stringBuffer.append(URLEncoder.encode(((String) stringArrayList.get(i)).trim(), GameManager.DEFAULT_CHARSET));
                            } catch (Throwable e) {
                                e.printStackTrace();
                                f.b("openSDK_LOG.GameAppOperation", "UnsupportedEncodingException: ", e);
                                stringBuffer.append(URLEncoder.encode(((String) stringArrayList.get(i)).trim()));
                            }
                            if (i != size - 1) {
                                stringBuffer.append(h.b);
                            }
                        }
                    }
                    Object string3 = bundle.getString(TROOPBAR_ID);
                    if (TextUtils.isEmpty(string3) || Util.isNumeric(string3)) {
                        StringBuffer stringBuffer2 = new StringBuffer("mqqapi://share/to_troopbar?src_type=app&version=1&file_type=news");
                        Object appId = this.mToken.getAppId();
                        Object openId = this.mToken.getOpenId();
                        f.a("openSDK_LOG.GameAppOperation", new StringBuilder("shareToTroopBar() -- openId: ").append(openId).toString());
                        str = Util.getApplicationLable(activity);
                        if (!TextUtils.isEmpty(appId)) {
                            stringBuffer2.append(new StringBuilder("&share_id=").append(appId).toString());
                        }
                        if (!TextUtils.isEmpty(openId)) {
                            stringBuffer2.append(new StringBuilder("&open_id=").append(Base64.encodeToString(Util.getBytesUTF8(openId), QQFAV_DATALINE_TYPE_AUDIO)).toString());
                        }
                        if (!TextUtils.isEmpty(str)) {
                            if (str.length() > 20) {
                                str = str.substring(0, R.styleable.Toolbar_navigationIcon) + "...";
                            }
                            stringBuffer2.append(new StringBuilder("&app_name=").append(Base64.encodeToString(Util.getBytesUTF8(str), QQFAV_DATALINE_TYPE_AUDIO)).toString());
                        }
                        if (!TextUtils.isEmpty(string)) {
                            stringBuffer2.append(new StringBuilder("&title=").append(Base64.encodeToString(Util.getBytesUTF8(string), QQFAV_DATALINE_TYPE_AUDIO)).toString());
                        }
                        if (!TextUtils.isEmpty(string2)) {
                            stringBuffer2.append(new StringBuilder("&description=").append(Base64.encodeToString(Util.getBytesUTF8(string2), QQFAV_DATALINE_TYPE_AUDIO)).toString());
                        }
                        if (!TextUtils.isEmpty(string3)) {
                            stringBuffer2.append(new StringBuilder("&troopbar_id=").append(Base64.encodeToString(Util.getBytesUTF8(string3), QQFAV_DATALINE_TYPE_AUDIO)).toString());
                        }
                        if (!TextUtils.isEmpty(stringBuffer)) {
                            stringBuffer2.append(new StringBuilder("&file_data=").append(Base64.encodeToString(Util.getBytesUTF8(stringBuffer.toString()), QQFAV_DATALINE_TYPE_AUDIO)).toString());
                        }
                        f.a("openSDK_LOG.GameAppOperation", new StringBuilder("shareToTroopBar, url: ").append(stringBuffer2.toString()).toString());
                        com.tencent.connect.a.a.a(Global.getContext(), this.mToken, "requireApi", SystemUtils.TROOPBAR_CALLBACK_ACTION);
                        Intent intent = new Intent("android.intent.action.VIEW");
                        intent.setData(Uri.parse(stringBuffer2.toString()));
                        string3 = activity.getPackageName();
                        if (!TextUtils.isEmpty(string3)) {
                            intent.putExtra("pkg_name", string3);
                        }
                        UIListenerManager.getInstance().setListnerWithAction(SystemUtils.TROOPBAR_CALLBACK_ACTION, iUiListener);
                        if (!hasActivityForIntent(intent) || Util.isQQVersionBelow(activity, SystemUtils.QQ_VERSION_NAME_5_3_0)) {
                            f.d("openSDK_LOG.GameAppOperation", "-->shareToTroopBar, there is no activity, show download page.");
                            a(activity, SystemUtils.QQ_VERSION_NAME_5_3_0);
                            d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_SHARE_TO_TROOPBAR, Constants.VIA_REPORT_TYPE_SHARE_TO_TROOPBAR, Constants.VIA_REPORT_TYPE_BIND_GROUP, MessageService.MSG_DB_NOTIFY_REACHED);
                        } else {
                            try {
                                startAssistActivity(activity, Constants.REQUEST_SHARE_TO_TROOP_BAR, intent, false);
                                d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_SHARE_TO_TROOPBAR, Constants.VIA_REPORT_TYPE_SHARE_TO_TROOPBAR, Constants.VIA_REPORT_TYPE_BIND_GROUP, MessageService.MSG_DB_READY_REPORT);
                            } catch (Throwable e2) {
                                f.b("openSDK_LOG.GameAppOperation", "-->shareToTroopBar, start activity exception.", e2);
                                d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_SHARE_TO_TROOPBAR, Constants.VIA_REPORT_TYPE_SHARE_TO_TROOPBAR, Constants.VIA_REPORT_TYPE_BIND_GROUP, MessageService.MSG_DB_NOTIFY_REACHED);
                                a(activity, SystemUtils.QQ_VERSION_NAME_5_3_0);
                            }
                        }
                        f.c("openSDK_LOG.GameAppOperation", "shareToTroopBar() -- end");
                        return;
                    }
                    iUiListener.onError(new UiError(-6, "\u4f20\u5165\u53c2\u6570\u6709\u8bef! troopbar_id \u5fc5\u987b\u4e3a\u6570\u5b57", null));
                    f.e("openSDK_LOG.GameAppOperation", "shareToTroopBar(): troopbar_id \u5fc5\u987b\u4e3a\u6570\u5b57");
                }
            }
        }
    }

    public void sharePrizeToQQ(Activity activity, Bundle bundle, IUiListener iUiListener) {
        f.c("openSDK_LOG.GameAppOperation", "sharePrizeToQQ() -- start");
        String str = Constants.MSG_PARAM_ERROR;
        if (iUiListener == null) {
            f.e("openSDK_LOG.GameAppOperation", "listener is null!");
        } else if (activity == null || bundle == null) {
            r1 = "activity or params is null!";
            f.e("openSDK_LOG.GameAppOperation", r1);
            iUiListener.onError(new UiError(-5, str, r1));
        } else {
            Object string = bundle.getString(SHARE_PRIZE_TITLE);
            if (TextUtils.isEmpty(string)) {
                r1 = "sharePrizeToQQ failed, title is empty.";
                f.e("openSDK_LOG.GameAppOperation", r1);
                iUiListener.onError(new UiError(-5, str, r1));
                return;
            }
            Object string2 = bundle.getString(SHARE_PRIZE_SUMMARY);
            if (TextUtils.isEmpty(string2)) {
                r1 = "sharePrizeToQQ failed, sumary is empty.";
                f.e("openSDK_LOG.GameAppOperation", r1);
                iUiListener.onError(new UiError(-5, str, r1));
                return;
            }
            String string3 = bundle.getString(SHARE_PRIZE_IMAGE_URL);
            if (TextUtils.isEmpty(string3) || !(string3.startsWith("http://") || string3.startsWith("https://"))) {
                r1 = "sharePrizeToQQ failed, imageUrl is empty or illegal.";
                f.e("openSDK_LOG.GameAppOperation", r1);
                iUiListener.onError(new UiError(-5, str, r1));
                return;
            }
            Object string4 = bundle.getString(SHARE_PRIZE_ACTIVITY_ID);
            if (TextUtils.isEmpty(string4)) {
                r1 = "sharePrizeToQQ failed, activityId is empty.";
                f.e("openSDK_LOG.GameAppOperation", r1);
                iUiListener.onError(new UiError(-5, str, r1));
                return;
            }
            Bundle bundle2 = new Bundle();
            bundle2.putString(SHARE_PRIZE_TITLE, string);
            bundle2.putString(SHARE_PRIZE_SUMMARY, string2);
            bundle2.putString(SHARE_PRIZE_IMAGE_URL, string3);
            bundle2.putInt(QQFAV_DATALINE_REQTYPE, QQFAV_DATALINE_TYPE_DEFAULT);
            ThreadManager.executeOnSubThread(new AnonymousClass_1(iUiListener, string4, activity, bundle2));
            f.c("openSDK_LOG.GameAppOperation", "sharePrizeToQQ() -- end");
        }
    }

    public void queryUnexchangePrize(Context context, Bundle bundle, IUiListener iUiListener) {
        String str = Constants.MSG_PARAM_ERROR;
        if (iUiListener == null) {
            f.e("openSDK_LOG.GameAppOperation", "listener is null!");
        } else if (bundle == null) {
            r1 = "params is null!";
            f.e("openSDK_LOG.GameAppOperation", r1);
            iUiListener.onError(new UiError(-5, str, r1));
        } else if (this.mToken == null || !this.mToken.isSessionValid()) {
            r1 = "queryUnexchangePrize failed, auth token is illegal.";
            f.e("openSDK_LOG.GameAppOperation", r1);
            iUiListener.onError(new UiError(-5, str, r1));
        } else if (context == null && Global.getContext() == null) {
            r1 = "queryUnexchangePrize failed, context is null.";
            f.e("openSDK_LOG.GameAppOperation", r1);
            iUiListener.onError(new UiError(-5, str, r1));
        } else if (TextUtils.isEmpty(bundle.getString(SHARE_PRIZE_ACTIVITY_ID))) {
            r1 = "queryUnexchangePrize failed, activityId is empty.";
            f.e("openSDK_LOG.GameAppOperation", r1);
            iUiListener.onError(new UiError(-5, str, r1));
        } else {
            if (context == null) {
                context = Global.getContext();
            }
            ThreadManager.executeOnSubThread(new AnonymousClass_2(iUiListener, bundle, context));
        }
    }

    public void exchangePrize(Context context, Bundle bundle, IUiListener iUiListener) {
        String str = Constants.MSG_PARAM_ERROR;
        if (iUiListener == null) {
            f.e("openSDK_LOG.GameAppOperation", "exchangePrize listener is null!");
        } else if (bundle == null) {
            r1 = "exchangePrize params is null!";
            f.e("openSDK_LOG.GameAppOperation", r1);
            iUiListener.onError(new UiError(-5, str, r1));
        } else if (this.mToken == null || !this.mToken.isSessionValid()) {
            r1 = "exchangePrize failed, auth token is illegal.";
            f.e("openSDK_LOG.GameAppOperation", r1);
            iUiListener.onError(new UiError(-5, str, r1));
        } else if (context == null && Global.getContext() == null) {
            r1 = "exchangePrize failed, context is null.";
            f.e("openSDK_LOG.GameAppOperation", r1);
            iUiListener.onError(new UiError(-5, str, r1));
        } else {
            ArrayList stringArrayList = bundle.getStringArrayList(SHARE_PRIZE_SHARE_ID_LIST);
            if (stringArrayList == null) {
                r1 = "exchangePrize failed, shareid_list is empty.";
                f.e("openSDK_LOG.GameAppOperation", r1);
                iUiListener.onError(new UiError(-5, str, r1));
                return;
            }
            StringBuffer stringBuffer = new StringBuffer(a.d);
            int size = stringArrayList.size();
            for (int i = 0; i < size; i++) {
                str = (String) stringArrayList.get(i);
                if (!TextUtils.isEmpty(str)) {
                    stringBuffer.append(str);
                    if (i < size - 1) {
                        stringBuffer.append(MiPushClient.ACCEPT_TIME_SEPARATOR);
                    }
                }
            }
            if (context == null) {
                context = Global.getContext();
            }
            ThreadManager.executeOnSubThread(new AnonymousClass_3(iUiListener, stringBuffer, context));
        }
    }

    private Bundle a() {
        if (this.mToken == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        CharSequence appId = this.mToken.getAppId();
        CharSequence openId = this.mToken.getOpenId();
        CharSequence accessToken = this.mToken.getAccessToken();
        if (TextUtils.isEmpty(appId) || TextUtils.isEmpty(openId) || TextUtils.isEmpty(accessToken)) {
            f.e("openSDK_LOG.GameAppOperation", "composeLoginStateParams fail, accesstoken or openid or appid is null");
            return null;
        }
        bundle.putString(SocialConstants.PARAM_APP_ID, this.mToken.getAppId());
        bundle.putString(SocialConstants.PARAM_OPEN_ID, this.mToken.getOpenId());
        bundle.putString("accesstoken", this.mToken.getAccessToken());
        return bundle;
    }

    public void isActivityAvailable(Activity activity, String str, IUiListener iUiListener) {
        String str2 = Constants.MSG_PARAM_ERROR;
        String str3;
        if (TextUtils.isEmpty(str)) {
            str3 = "isActivityAvailable failed, activityId is null.";
            f.e("openSDK_LOG.GameAppOperation", str3);
            iUiListener.onError(new UiError(-5, str2, str3));
        } else if (this.mToken == null || !this.mToken.isSessionValid()) {
            str3 = "exchangePrize failed, auth token is illegal.";
            f.e("openSDK_LOG.GameAppOperation", str3);
            iUiListener.onError(new UiError(-5, str2, str3));
        } else {
            ThreadManager.executeOnSubThread(new AnonymousClass_4(iUiListener, str, activity));
        }
    }

    private boolean a(Activity activity, Bundle bundle, IUiListener iUiListener) {
        if (activity == null || bundle == null || iUiListener == null) {
            f.e("openSDK_LOG.GameAppOperation", "activity or params or listener is null!");
            return false;
        }
        int i = bundle.getInt(QQFAV_DATALINE_REQTYPE, QQFAV_DATALINE_TYPE_DEFAULT);
        if (TextUtils.isEmpty(bundle.getString(QQFAV_DATALINE_APPNAME))) {
            iUiListener.onError(new UiError(-5, "\u4f20\u5165\u53c2\u6570\u4e0d\u53ef\u4ee5\u4e3a\u7a7a: app_name", null));
            return false;
        }
        CharSequence string = bundle.getString(QQFAV_DATALINE_DESCRIPTION);
        CharSequence string2 = bundle.getString(QQFAV_DATALINE_URL);
        CharSequence string3 = bundle.getString(QQFAV_DATALINE_AUDIOURL);
        CharSequence string4 = bundle.getString(QQFAV_DATALINE_IMAGEURL);
        ArrayList stringArrayList = bundle.getStringArrayList(QQFAV_DATALINE_FILEDATA);
        switch (i) {
            case QQFAV_DATALINE_TYPE_DEFAULT:
                if (TextUtils.isEmpty(string2) || TextUtils.isEmpty(string4)) {
                    iUiListener.onError(new UiError(-5, "\u4f20\u5165\u53c2\u6570\u4e0d\u53ef\u4ee5\u4e3a\u7a7a: image_url or url is null", null));
                    return false;
                }
            case QQFAV_DATALINE_TYPE_AUDIO:
                if (TextUtils.isEmpty(string2) || TextUtils.isEmpty(string4) || TextUtils.isEmpty(string3)) {
                    iUiListener.onError(new UiError(-5, "\u4f20\u5165\u53c2\u6570\u4e0d\u53ef\u4ee5\u4e3a\u7a7a: image_url or url or audioUrl is null", null));
                    return false;
                }
            case QQFAV_DATALINE_TYPE_IMAGE_TEXT:
                if (stringArrayList == null || stringArrayList.size() == 0) {
                    iUiListener.onError(new UiError(-5, "\u4f20\u5165\u53c2\u6570\u4e0d\u53ef\u4ee5\u4e3a\u7a7a: fill_data is null", null));
                    return false;
                }
                int size = stringArrayList.size();
                for (int i2 = 0; i2 < size; i2++) {
                    String trim = ((String) stringArrayList.get(i2)).trim();
                    if (trim.startsWith("/") && !new File(trim).exists()) {
                        iUiListener.onError(new UiError(-6, Constants.MSG_PARAM_IMAGE_URL_FORMAT_ERROR, null));
                        return false;
                    }
                }
                break;
            case QQFAV_DATALINE_TYPE_TEXT:
                if (TextUtils.isEmpty(string)) {
                    iUiListener.onError(new UiError(-5, "\u4f20\u5165\u53c2\u6570\u4e0d\u53ef\u4ee5\u4e3a\u7a7a: description is null", null));
                    return false;
                }
            default:
                iUiListener.onError(new UiError(-5, "\u4f20\u5165\u53c2\u6570\u6709\u8bef!: unknow req_type", null));
                return false;
        }
        return true;
    }

    public void releaseResource() {
        f.c("openSDK_LOG.GameAppOperation", "releaseResource() -- start");
        f.c("openSDK_LOG.GameAppOperation", "releaseResource() -- end");
    }

    private void a(Activity activity) {
        a(activity, a.d);
    }

    private void a(Activity activity, String str) {
        new TDialog(activity, a.d, getCommonDownloadQQUrl(str), null, this.mToken).show();
    }

    private void a(String str, int i, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            f.a("openSDK_LOG.GameAppOperation", "reportForVia() error: reportType or result is null");
            return;
        }
        String str3;
        switch (i) {
            case QQFAV_DATALINE_TYPE_DEFAULT:
                str3 = Constants.VIA_SHARE_TYPE_INFO;
                break;
            case QQFAV_DATALINE_TYPE_AUDIO:
                str3 = MessageService.MSG_DB_NOTIFY_DISMISS;
                break;
            case QQFAV_DATALINE_TYPE_IMAGE_TEXT:
                str3 = MessageService.MSG_DB_NOTIFY_REACHED;
                break;
            case QQFAV_DATALINE_TYPE_TEXT:
                str3 = Constants.VIA_SHARE_TYPE_TEXT;
                break;
            default:
                f.e("openSDK_LOG.GameAppOperation", new StringBuilder("GameAppOperation -- reportForVia() error: unknow type ").append(String.valueOf(i)).toString());
                return;
        }
        d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), MessageService.MSG_DB_NOTIFY_CLICK, str, Constants.VIA_ACT_TYPE_TWENTY_EIGHT, str2, str3, MessageService.MSG_DB_READY_REPORT, a.d, a.d);
    }
}
