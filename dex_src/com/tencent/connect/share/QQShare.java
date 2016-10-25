package com.tencent.connect.share;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Base64;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.Constants;
import com.tencent.connect.common.UIListenerManager;
import com.tencent.open.TDialog;
import com.tencent.open.a.f;
import com.tencent.open.b.d;
import com.tencent.open.utils.AsynLoadImg;
import com.tencent.open.utils.AsynLoadImgBack;
import com.tencent.open.utils.Global;
import com.tencent.open.utils.ServerSetting;
import com.tencent.open.utils.SystemUtils;
import com.tencent.open.utils.Util;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import com.umeng.a;
import com.xunlei.tdlive.R;
import java.io.File;
import java.util.ArrayList;
import org.android.agoo.message.MessageService;

// compiled from: ProGuard
public class QQShare extends BaseApi {
    public static final int QQ_SHARE_SUMMARY_MAX_LENGTH = 60;
    public static final int QQ_SHARE_TITLE_MAX_LENGTH = 45;
    public static final String SHARE_TO_QQ_APP_NAME = "appName";
    public static final String SHARE_TO_QQ_AUDIO_URL = "audio_url";
    public static final String SHARE_TO_QQ_EXT_INT = "cflag";
    public static final String SHARE_TO_QQ_EXT_STR = "share_qq_ext_str";
    public static final int SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN = 1;
    public static final int SHARE_TO_QQ_FLAG_QZONE_ITEM_HIDE = 2;
    public static final String SHARE_TO_QQ_IMAGE_LOCAL_URL = "imageLocalUrl";
    public static final String SHARE_TO_QQ_IMAGE_URL = "imageUrl";
    public static final String SHARE_TO_QQ_KEY_TYPE = "req_type";
    public static final String SHARE_TO_QQ_SITE = "site";
    public static final String SHARE_TO_QQ_SUMMARY = "summary";
    public static final String SHARE_TO_QQ_TARGET_URL = "targetUrl";
    public static final String SHARE_TO_QQ_TITLE = "title";
    public static final int SHARE_TO_QQ_TYPE_APP = 6;
    public static final int SHARE_TO_QQ_TYPE_AUDIO = 2;
    public static final int SHARE_TO_QQ_TYPE_DEFAULT = 1;
    public static final int SHARE_TO_QQ_TYPE_IMAGE = 5;
    public String mViaShareQQType;

    // compiled from: ProGuard
    class AnonymousClass_1 implements AsynLoadImgBack {
        final /* synthetic */ Bundle a;
        final /* synthetic */ String b;
        final /* synthetic */ String c;
        final /* synthetic */ IUiListener d;
        final /* synthetic */ Activity e;

        AnonymousClass_1(Bundle bundle, String str, String str2, IUiListener iUiListener, Activity activity) {
            this.a = bundle;
            this.b = str;
            this.c = str2;
            this.d = iUiListener;
            this.e = activity;
        }

        public void saved(int i, String str) {
            if (i == 0) {
                this.a.putString(SHARE_TO_QQ_IMAGE_LOCAL_URL, str);
            } else if (TextUtils.isEmpty(this.b) && TextUtils.isEmpty(this.c)) {
                if (this.d != null) {
                    this.d.onError(new UiError(-6, Constants.MSG_SHARE_GETIMG_ERROR, null));
                    f.e("openSDK_LOG.QQShare", "shareToMobileQQ -- error: \u83b7\u53d6\u5206\u4eab\u56fe\u7247\u5931\u8d25!");
                }
                d.a().a(SHARE_TO_QQ_TYPE_DEFAULT, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, QQShare.this.mToken.getAppId(), MessageService.MSG_DB_READY_REPORT, Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, Constants.MSG_SHARE_GETIMG_ERROR);
                return;
            }
            QQShare.this.b(this.e, this.a, this.d);
        }

        public void batchSaved(int i, ArrayList<String> arrayList) {
        }
    }

    // compiled from: ProGuard
    class AnonymousClass_2 implements AsynLoadImgBack {
        final /* synthetic */ Bundle a;
        final /* synthetic */ String b;
        final /* synthetic */ String c;
        final /* synthetic */ IUiListener d;
        final /* synthetic */ Activity e;

        AnonymousClass_2(Bundle bundle, String str, String str2, IUiListener iUiListener, Activity activity) {
            this.a = bundle;
            this.b = str;
            this.c = str2;
            this.d = iUiListener;
            this.e = activity;
        }

        public void saved(int i, String str) {
            if (i == 0) {
                this.a.putString(SHARE_TO_QQ_IMAGE_LOCAL_URL, str);
            } else if (TextUtils.isEmpty(this.b) && TextUtils.isEmpty(this.c)) {
                if (this.d != null) {
                    this.d.onError(new UiError(-6, Constants.MSG_SHARE_GETIMG_ERROR, null));
                    f.e("openSDK_LOG.QQShare", "shareToMobileQQ -- error: \u83b7\u53d6\u5206\u4eab\u56fe\u7247\u5931\u8d25!");
                }
                d.a().a(SHARE_TO_QQ_TYPE_DEFAULT, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, QQShare.this.mToken.getAppId(), MessageService.MSG_DB_READY_REPORT, Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, Constants.MSG_SHARE_GETIMG_ERROR);
                return;
            }
            QQShare.this.b(this.e, this.a, this.d);
        }

        public void batchSaved(int i, ArrayList<String> arrayList) {
        }
    }

    public QQShare(Context context, QQToken qQToken) {
        super(qQToken);
        this.mViaShareQQType = a.d;
    }

    public void shareToQQ(Activity activity, Bundle bundle, IUiListener iUiListener) {
        f.c("openSDK_LOG.QQShare", "shareToQQ() -- start.");
        String string = bundle.getString(SHARE_TO_QQ_IMAGE_URL);
        String string2 = bundle.getString(SHARE_TO_QQ_TITLE);
        String string3 = bundle.getString(SHARE_TO_QQ_SUMMARY);
        String string4 = bundle.getString(SHARE_TO_QQ_TARGET_URL);
        String string5 = bundle.getString(SHARE_TO_QQ_IMAGE_LOCAL_URL);
        int i = bundle.getInt(SHARE_TO_QQ_KEY_TYPE, SHARE_TO_QQ_TYPE_DEFAULT);
        f.c("openSDK_LOG.QQShare", new StringBuilder("shareToQQ -- type: ").append(i).toString());
        switch (i) {
            case SHARE_TO_QQ_TYPE_DEFAULT:
                this.mViaShareQQType = MessageService.MSG_DB_NOTIFY_REACHED;
                break;
            case SHARE_TO_QQ_TYPE_AUDIO:
                this.mViaShareQQType = MessageService.MSG_DB_NOTIFY_DISMISS;
                break;
            case SHARE_TO_QQ_TYPE_IMAGE:
                this.mViaShareQQType = MessageService.MSG_DB_NOTIFY_CLICK;
                break;
            case SHARE_TO_QQ_TYPE_APP:
                this.mViaShareQQType = MessageService.MSG_ACCS_READY_REPORT;
                break;
        }
        if (i == 6) {
            if (Util.isQQVersionBelow(activity, SystemUtils.QQ_VERSION_NAME_5_0_0)) {
                iUiListener.onError(new UiError(-15, Constants.MSG_PARAM_APPSHARE_TOO_LOW, null));
                f.e("openSDK_LOG.QQShare", "shareToQQ, app share is not support below qq5.0.");
                d.a().a(SHARE_TO_QQ_TYPE_DEFAULT, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.mToken.getAppId(), MessageService.MSG_DB_READY_REPORT, Long.valueOf(SystemClock.elapsedRealtime()), 0, SHARE_TO_QQ_TYPE_DEFAULT, "shareToQQ, app share is not support below qq5.0.");
                return;
            }
            string4 = String.format(ServerSetting.APP_DETAIL_PAGE, new Object[]{this.mToken.getAppId(), "mqq"});
            bundle.putString(SHARE_TO_QQ_TARGET_URL, string4);
        }
        if (Util.hasSDCard() || !Util.isQQVersionBelow(activity, SystemUtils.QQ_VERSION_NAME_4_5_0)) {
            if (i == 5) {
                if (Util.isQQVersionBelow(activity, SystemUtils.QQ_VERSION_NAME_4_3_0)) {
                    iUiListener.onError(new UiError(-6, Constants.MSG_PARAM_QQ_VERSION_ERROR, null));
                    f.e("openSDK_LOG.QQShare", "shareToQQ, version below 4.3 is not support.");
                    d.a().a(SHARE_TO_QQ_TYPE_DEFAULT, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.mToken.getAppId(), MessageService.MSG_DB_READY_REPORT, Long.valueOf(SystemClock.elapsedRealtime()), 0, SHARE_TO_QQ_TYPE_DEFAULT, "shareToQQ, version below 4.3 is not support.");
                    return;
                } else if (!Util.fileExists(string5)) {
                    iUiListener.onError(new UiError(-6, Constants.MSG_PARAM_IMAGE_URL_FORMAT_ERROR, null));
                    f.e("openSDK_LOG.QQShare", "shareToQQ -- error: \u975e\u6cd5\u7684\u56fe\u7247\u5730\u5740!");
                    d.a().a(SHARE_TO_QQ_TYPE_DEFAULT, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.mToken.getAppId(), MessageService.MSG_DB_READY_REPORT, Long.valueOf(SystemClock.elapsedRealtime()), 0, SHARE_TO_QQ_TYPE_DEFAULT, Constants.MSG_PARAM_IMAGE_URL_FORMAT_ERROR);
                    return;
                }
            }
            if (i == 5 || (TextUtils.isEmpty(r0) || !(r0.startsWith("http://") || r0.startsWith("https://")))) {
                iUiListener.onError(new UiError(-6, Constants.MSG_PARAM_ERROR, null));
                f.e("openSDK_LOG.QQShare", "shareToQQ, targetUrl is empty or illegal..");
                d.a().a(SHARE_TO_QQ_TYPE_DEFAULT, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.mToken.getAppId(), MessageService.MSG_DB_READY_REPORT, Long.valueOf(SystemClock.elapsedRealtime()), 0, SHARE_TO_QQ_TYPE_DEFAULT, "shareToQQ, targetUrl is empty or illegal..");
                return;
            }
            if (TextUtils.isEmpty(string2)) {
                iUiListener.onError(new UiError(-6, Constants.MSG_PARAM_TITLE_NULL_ERROR, null));
                f.e("openSDK_LOG.QQShare", "shareToQQ, title is empty.");
                d.a().a(SHARE_TO_QQ_TYPE_DEFAULT, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.mToken.getAppId(), MessageService.MSG_DB_READY_REPORT, Long.valueOf(SystemClock.elapsedRealtime()), 0, SHARE_TO_QQ_TYPE_DEFAULT, "shareToQQ, title is empty.");
                return;
            }
            if (TextUtils.isEmpty(string) || string.startsWith("http://") || string.startsWith("https://") || new File(string).exists()) {
                if (!TextUtils.isEmpty(string2) && string2.length() > 45) {
                    bundle.putString(SHARE_TO_QQ_TITLE, Util.subString(string2, QQ_SHARE_TITLE_MAX_LENGTH, null, null));
                }
                if (!TextUtils.isEmpty(string3) && string3.length() > 60) {
                    bundle.putString(SHARE_TO_QQ_SUMMARY, Util.subString(string3, QQ_SHARE_SUMMARY_MAX_LENGTH, null, null));
                }
                if (Util.isMobileQQSupportShare(activity)) {
                    f.c("openSDK_LOG.QQShare", "shareToQQ, support share");
                    a(activity, bundle, iUiListener);
                } else {
                    try {
                        f.d("openSDK_LOG.QQShare", "shareToQQ, don't support share, will show download dialog");
                        new TDialog(activity, a.d, getCommonDownloadQQUrl(a.d), null, this.mToken).show();
                    } catch (Throwable e) {
                        f.b("openSDK_LOG.QQShare", " shareToQQ, TDialog.show not in main thread", e);
                        e.printStackTrace();
                        iUiListener.onError(new UiError(-6, Constants.MSG_NOT_CALL_ON_MAIN_THREAD, null));
                    }
                }
                f.c("openSDK_LOG.QQShare", "shareToQQ() -- end.");
                return;
            }
            iUiListener.onError(new UiError(-6, Constants.MSG_PARAM_IMAGE_URL_FORMAT_ERROR, null));
            f.e("openSDK_LOG.QQShare", "shareToQQ, image url is emprty or illegal.");
            d.a().a(SHARE_TO_QQ_TYPE_DEFAULT, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.mToken.getAppId(), MessageService.MSG_DB_READY_REPORT, Long.valueOf(SystemClock.elapsedRealtime()), 0, SHARE_TO_QQ_TYPE_DEFAULT, "shareToQQ, image url is emprty or illegal.");
            return;
        }
        iUiListener.onError(new UiError(-6, Constants.MSG_SHARE_NOSD_ERROR, null));
        f.e("openSDK_LOG.QQShare", "shareToQQ sdcard is null--end");
        d.a().a(SHARE_TO_QQ_TYPE_DEFAULT, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.mToken.getAppId(), MessageService.MSG_DB_READY_REPORT, Long.valueOf(SystemClock.elapsedRealtime()), 0, SHARE_TO_QQ_TYPE_DEFAULT, "shareToQQ sdcard is null");
    }

    private void a(Activity activity, Bundle bundle, IUiListener iUiListener) {
        f.c("openSDK_LOG.QQShare", "shareToMobileQQ() -- start.");
        String string = bundle.getString(SHARE_TO_QQ_IMAGE_URL);
        String string2 = bundle.getString(SHARE_TO_QQ_TITLE);
        String string3 = bundle.getString(SHARE_TO_QQ_SUMMARY);
        f.a("openSDK_LOG.QQShare", new StringBuilder("shareToMobileQQ -- imageUrl: ").append(string).toString());
        if (!TextUtils.isEmpty(string)) {
            if (!Util.isValidUrl(string)) {
                bundle.putString(SHARE_TO_QQ_IMAGE_URL, null);
                if (Util.isQQVersionBelow(activity, SystemUtils.QQ_VERSION_NAME_4_3_0)) {
                    f.b("openSDK_LOG.QQShare", "shareToMobileQQ -- QQ Version is < 4.3.0 ");
                } else {
                    f.b("openSDK_LOG.QQShare", "shareToMobileQQ -- QQ Version is > 4.3.0 ");
                    a.a((Context) activity, string, new AnonymousClass_2(bundle, string2, string3, iUiListener, activity));
                    f.c("openSDK_LOG.QQShare", "shareToMobileQQ() -- end");
                }
            } else if (TextUtils.isEmpty(string2) && TextUtils.isEmpty(string3)) {
                if (iUiListener != null) {
                    iUiListener.onError(new UiError(-6, Constants.MSG_SHARE_NOSD_ERROR, null));
                    f.e("openSDK_LOG.QQShare", Constants.MSG_SHARE_NOSD_ERROR);
                }
                d.a().a(SHARE_TO_QQ_TYPE_DEFAULT, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.mToken.getAppId(), MessageService.MSG_DB_READY_REPORT, Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, Constants.MSG_SHARE_NOSD_ERROR);
                return;
            } else if (Util.isQQVersionBelow(activity, SystemUtils.QQ_VERSION_NAME_4_3_0)) {
                new AsynLoadImg(activity).save(string, new AnonymousClass_1(bundle, string2, string3, iUiListener, activity));
                f.c("openSDK_LOG.QQShare", "shareToMobileQQ() -- end");
            }
        }
        b(activity, bundle, iUiListener);
        f.c("openSDK_LOG.QQShare", "shareToMobileQQ() -- end");
    }

    private void b(Activity activity, Bundle bundle, IUiListener iUiListener) {
        f.c("openSDK_LOG.QQShare", "doShareToQQ() -- start");
        StringBuffer stringBuffer = new StringBuffer("mqqapi://share/to_fri?src_type=app&version=1&file_type=news");
        Object string = bundle.getString(SHARE_TO_QQ_IMAGE_URL);
        Object string2 = bundle.getString(SHARE_TO_QQ_TITLE);
        Object string3 = bundle.getString(SHARE_TO_QQ_SUMMARY);
        Object string4 = bundle.getString(SHARE_TO_QQ_TARGET_URL);
        Object string5 = bundle.getString(SHARE_TO_QQ_AUDIO_URL);
        int i = bundle.getInt(SHARE_TO_QQ_KEY_TYPE, SHARE_TO_QQ_TYPE_DEFAULT);
        int i2 = bundle.getInt(SHARE_TO_QQ_EXT_INT, 0);
        Object string6 = bundle.getString(SHARE_TO_QQ_EXT_STR);
        String applicationLable = Util.getApplicationLable(activity);
        if (applicationLable == null) {
            applicationLable = bundle.getString(SHARE_TO_QQ_APP_NAME);
        }
        Object string7 = bundle.getString(SHARE_TO_QQ_IMAGE_LOCAL_URL);
        Object appId = this.mToken.getAppId();
        Object openId = this.mToken.getOpenId();
        f.a("openSDK_LOG.QQShare", new StringBuilder("doShareToQQ -- openid: ").append(openId).toString());
        if (!TextUtils.isEmpty(string)) {
            stringBuffer.append(new StringBuilder("&image_url=").append(Base64.encodeToString(Util.getBytesUTF8(string), 2)).toString());
        }
        if (!TextUtils.isEmpty(string7)) {
            stringBuffer.append(new StringBuilder("&file_data=").append(Base64.encodeToString(Util.getBytesUTF8(string7), SHARE_TO_QQ_TYPE_AUDIO)).toString());
        }
        if (!TextUtils.isEmpty(string2)) {
            stringBuffer.append(new StringBuilder("&title=").append(Base64.encodeToString(Util.getBytesUTF8(string2), SHARE_TO_QQ_TYPE_AUDIO)).toString());
        }
        if (!TextUtils.isEmpty(string3)) {
            stringBuffer.append(new StringBuilder("&description=").append(Base64.encodeToString(Util.getBytesUTF8(string3), SHARE_TO_QQ_TYPE_AUDIO)).toString());
        }
        if (!TextUtils.isEmpty(appId)) {
            stringBuffer.append(new StringBuilder("&share_id=").append(appId).toString());
        }
        if (!TextUtils.isEmpty(string4)) {
            stringBuffer.append(new StringBuilder("&url=").append(Base64.encodeToString(Util.getBytesUTF8(string4), SHARE_TO_QQ_TYPE_AUDIO)).toString());
        }
        if (!TextUtils.isEmpty(applicationLable)) {
            if (applicationLable.length() > 20) {
                applicationLable = applicationLable.substring(0, R.styleable.Toolbar_navigationIcon) + "...";
            }
            stringBuffer.append(new StringBuilder("&app_name=").append(Base64.encodeToString(Util.getBytesUTF8(applicationLable), SHARE_TO_QQ_TYPE_AUDIO)).toString());
        }
        if (!TextUtils.isEmpty(openId)) {
            stringBuffer.append(new StringBuilder("&open_id=").append(Base64.encodeToString(Util.getBytesUTF8(openId), SHARE_TO_QQ_TYPE_AUDIO)).toString());
        }
        if (!TextUtils.isEmpty(string5)) {
            stringBuffer.append(new StringBuilder("&audioUrl=").append(Base64.encodeToString(Util.getBytesUTF8(string5), SHARE_TO_QQ_TYPE_AUDIO)).toString());
        }
        stringBuffer.append(new StringBuilder("&req_type=").append(Base64.encodeToString(Util.getBytesUTF8(String.valueOf(i)), SHARE_TO_QQ_TYPE_AUDIO)).toString());
        if (!TextUtils.isEmpty(string6)) {
            stringBuffer.append(new StringBuilder("&share_qq_ext_str=").append(Base64.encodeToString(Util.getBytesUTF8(string6), SHARE_TO_QQ_TYPE_AUDIO)).toString());
        }
        stringBuffer.append(new StringBuilder("&cflag=").append(Base64.encodeToString(Util.getBytesUTF8(String.valueOf(i2)), SHARE_TO_QQ_TYPE_AUDIO)).toString());
        f.a("openSDK_LOG.QQShare", new StringBuilder("doShareToQQ -- url: ").append(stringBuffer.toString()).toString());
        com.tencent.connect.a.a.a(Global.getContext(), this.mToken, "requireApi", "shareToNativeQQ");
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(stringBuffer.toString()));
        intent.putExtra("pkg_name", activity.getPackageName());
        if (Util.isQQVersionBelow(activity, SystemUtils.QQ_VERSION_NAME_4_6_0)) {
            f.c("openSDK_LOG.QQShare", "doShareToQQ, qqver below 4.6.");
            if (hasActivityForIntent(intent)) {
                UIListenerManager.getInstance().setListenerWithRequestcode(Constants.REQUEST_OLD_SHARE, iUiListener);
                startAssitActivity(activity, intent, Constants.REQUEST_OLD_SHARE);
            }
        } else {
            f.c("openSDK_LOG.QQShare", "doShareToQQ, qqver greater than 4.6.");
            if (UIListenerManager.getInstance().setListnerWithAction(SystemUtils.QQ_SHARE_CALLBACK_ACTION, iUiListener) != null) {
                f.c("openSDK_LOG.QQShare", "doShareToQQ, last listener is not null, cancel it.");
            }
            if (hasActivityForIntent(intent)) {
                startAssistActivity(activity, Constants.REQUEST_QQ_SHARE, intent, true);
            }
        }
        String str = Constants.VIA_REPORT_TYPE_SHARE_TO_QQ;
        if (i2 == 1) {
            str = Constants.VIA_REPORT_TYPE_SHARE_TO_QZONE;
        }
        if (hasActivityForIntent(intent)) {
            d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_SHARE_TO_QQ, str, MessageService.MSG_DB_NOTIFY_DISMISS, MessageService.MSG_DB_READY_REPORT, this.mViaShareQQType, MessageService.MSG_DB_READY_REPORT, MessageService.MSG_DB_NOTIFY_REACHED, MessageService.MSG_DB_READY_REPORT);
            d.a().a(0, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.mToken.getAppId(), MessageService.MSG_DB_READY_REPORT, Long.valueOf(SystemClock.elapsedRealtime()), 0, SHARE_TO_QQ_TYPE_DEFAULT, a.d);
        } else {
            d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_SHARE_TO_QQ, str, MessageService.MSG_DB_NOTIFY_DISMISS, MessageService.MSG_DB_NOTIFY_REACHED, this.mViaShareQQType, MessageService.MSG_DB_READY_REPORT, MessageService.MSG_DB_NOTIFY_REACHED, MessageService.MSG_DB_READY_REPORT);
            d.a().a(SHARE_TO_QQ_TYPE_DEFAULT, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.mToken.getAppId(), MessageService.MSG_DB_READY_REPORT, Long.valueOf(SystemClock.elapsedRealtime()), 0, SHARE_TO_QQ_TYPE_DEFAULT, "hasActivityForIntent fail");
        }
        f.c("openSDK_LOG.QQShare", "doShareToQQ() --end");
    }

    public void releaseResource() {
    }
}
