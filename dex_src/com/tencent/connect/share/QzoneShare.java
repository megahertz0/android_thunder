package com.tencent.connect.share;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Base64;
import com.alipay.sdk.util.h;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.Constants;
import com.tencent.connect.common.UIListenerManager;
import com.tencent.open.TDialog;
import com.tencent.open.a.f;
import com.tencent.open.b.d;
import com.tencent.open.utils.AsynLoadImgBack;
import com.tencent.open.utils.Global;
import com.tencent.open.utils.ServerSetting;
import com.tencent.open.utils.SystemUtils;
import com.tencent.open.utils.Util;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import com.umeng.a;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.net.URLEncoder;
import java.util.ArrayList;
import org.android.agoo.message.MessageService;

// compiled from: ProGuard
public class QzoneShare extends BaseApi {
    public static final String SHARE_TO_QQ_APP_NAME = "appName";
    public static final String SHARE_TO_QQ_AUDIO_URL = "audio_url";
    public static final String SHARE_TO_QQ_EXT_INT = "cflag";
    public static final String SHARE_TO_QQ_EXT_STR = "share_qq_ext_str";
    public static final String SHARE_TO_QQ_IMAGE_LOCAL_URL = "imageLocalUrl";
    public static final String SHARE_TO_QQ_IMAGE_URL = "imageUrl";
    public static final String SHARE_TO_QQ_SITE = "site";
    public static final String SHARE_TO_QQ_SUMMARY = "summary";
    public static final String SHARE_TO_QQ_TARGET_URL = "targetUrl";
    public static final String SHARE_TO_QQ_TITLE = "title";
    public static final String SHARE_TO_QZONE_KEY_TYPE = "req_type";
    public static final int SHARE_TO_QZONE_TYPE_APP = 6;
    public static final int SHARE_TO_QZONE_TYPE_IMAGE = 5;
    public static final int SHARE_TO_QZONE_TYPE_IMAGE_TEXT = 1;
    public static final int SHARE_TO_QZONE_TYPE_NO_TYPE = 0;
    private boolean a;
    private boolean b;
    private boolean c;
    private boolean d;
    public String mViaShareQzoneType;

    // compiled from: ProGuard
    class AnonymousClass_1 implements AsynLoadImgBack {
        final /* synthetic */ Bundle a;
        final /* synthetic */ Activity b;
        final /* synthetic */ IUiListener c;

        AnonymousClass_1(Bundle bundle, Activity activity, IUiListener iUiListener) {
            this.a = bundle;
            this.b = activity;
            this.c = iUiListener;
        }

        public void saved(int i, String str) {
        }

        public void batchSaved(int i, ArrayList<String> arrayList) {
            if (i == 0) {
                this.a.putStringArrayList(SHARE_TO_QQ_IMAGE_URL, arrayList);
            }
            QzoneShare.this.a(this.b, this.a, this.c);
        }
    }

    public QzoneShare(Context context, QQToken qQToken) {
        super(qQToken);
        this.mViaShareQzoneType = a.d;
        this.a = true;
        this.b = false;
        this.c = false;
        this.d = false;
    }

    public void shareToQzone(Activity activity, Bundle bundle, IUiListener iUiListener) {
        f.c("openSDK_LOG.QzoneShare", "shareToQzone() -- start");
        if (bundle == null) {
            iUiListener.onError(new UiError(-6, Constants.MSG_PARAM_NULL_ERROR, null));
            f.e("openSDK_LOG.QzoneShare", "shareToQzone() params is null");
            d.a().a(SHARE_TO_QZONE_TYPE_IMAGE_TEXT, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.mToken.getAppId(), MessageService.MSG_ACCS_READY_REPORT, Long.valueOf(SystemClock.elapsedRealtime()), 0, SHARE_TO_QZONE_TYPE_IMAGE_TEXT, Constants.MSG_PARAM_NULL_ERROR);
            return;
        }
        String str;
        String string = bundle.getString(SHARE_TO_QQ_TITLE);
        String string2 = bundle.getString(SHARE_TO_QQ_SUMMARY);
        String string3 = bundle.getString(SHARE_TO_QQ_TARGET_URL);
        ArrayList stringArrayList = bundle.getStringArrayList(SHARE_TO_QQ_IMAGE_URL);
        Object applicationLable = Util.getApplicationLable(activity);
        if (applicationLable == null) {
            applicationLable = bundle.getString(SHARE_TO_QQ_APP_NAME);
        } else if (applicationLable.length() > 20) {
            applicationLable = applicationLable.substring(0, R.styleable.Toolbar_navigationIcon) + "...";
        }
        int i = bundle.getInt(SHARE_TO_QZONE_KEY_TYPE);
        switch (i) {
            case SHARE_TO_QZONE_TYPE_IMAGE_TEXT:
                this.mViaShareQzoneType = MessageService.MSG_DB_NOTIFY_REACHED;
                break;
            case SHARE_TO_QZONE_TYPE_IMAGE:
                this.mViaShareQzoneType = MessageService.MSG_DB_NOTIFY_CLICK;
                break;
            case SHARE_TO_QZONE_TYPE_APP:
                this.mViaShareQzoneType = MessageService.MSG_ACCS_READY_REPORT;
                break;
            default:
                this.mViaShareQzoneType = MessageService.MSG_DB_NOTIFY_REACHED;
                break;
        }
        switch (i) {
            case SHARE_TO_QZONE_TYPE_IMAGE_TEXT:
                this.a = true;
                break;
            case SHARE_TO_QZONE_TYPE_IMAGE:
                iUiListener.onError(new UiError(-5, Constants.MSG_SHARE_TYPE_ERROR, null));
                f.e("openSDK_LOG.QzoneShare", "shareToQzone() error--end\u8bf7\u9009\u62e9\u652f\u6301\u7684\u5206\u4eab\u7c7b\u578b");
                d.a().a(SHARE_TO_QZONE_TYPE_IMAGE_TEXT, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.mToken.getAppId(), MessageService.MSG_ACCS_READY_REPORT, Long.valueOf(SystemClock.elapsedRealtime()), 0, SHARE_TO_QZONE_TYPE_IMAGE_TEXT, "shareToQzone() \u8bf7\u9009\u62e9\u652f\u6301\u7684\u5206\u4eab\u7c7b\u578b");
                return;
            case SHARE_TO_QZONE_TYPE_APP:
                if (Util.isQQVersionBelow(activity, SystemUtils.QQ_VERSION_NAME_5_0_0)) {
                    iUiListener.onError(new UiError(-15, Constants.MSG_PARAM_APPSHARE_TOO_LOW, null));
                    f.e("openSDK_LOG.QzoneShare", "-->shareToQzone, app share is not support below qq5.0.");
                    d.a().a(SHARE_TO_QZONE_TYPE_IMAGE_TEXT, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.mToken.getAppId(), MessageService.MSG_ACCS_READY_REPORT, Long.valueOf(SystemClock.elapsedRealtime()), 0, SHARE_TO_QZONE_TYPE_IMAGE_TEXT, "shareToQzone, app share is not support below qq5.0.");
                    return;
                }
                string3 = String.format(ServerSetting.APP_DETAIL_PAGE, new Object[]{this.mToken.getAppId(), "mqq"});
                bundle.putString(SHARE_TO_QQ_TARGET_URL, string3);
                str = string3;
                string3 = string;
                string = str;
                if (Util.hasSDCard() && Util.isQQVersionBelow(activity, SystemUtils.QQ_VERSION_NAME_4_5_0)) {
                    iUiListener.onError(new UiError(-6, Constants.MSG_SHARE_NOSD_ERROR, null));
                    f.e("openSDK_LOG.QzoneShare", "shareToQzone() sdcard is null--end");
                    d.a().a(SHARE_TO_QZONE_TYPE_IMAGE_TEXT, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.mToken.getAppId(), MessageService.MSG_ACCS_READY_REPORT, Long.valueOf(SystemClock.elapsedRealtime()), 0, SHARE_TO_QZONE_TYPE_IMAGE_TEXT, Constants.MSG_SHARE_NOSD_ERROR);
                    return;
                }
                String str2;
                if (this.a) {
                    if (TextUtils.isEmpty(r1)) {
                        iUiListener.onError(new UiError(-5, Constants.MSG_PARAM_TARGETURL_NULL_ERROR, null));
                        f.e("openSDK_LOG.QzoneShare", "shareToQzone() targetUrl null error--end");
                        d.a().a(SHARE_TO_QZONE_TYPE_IMAGE_TEXT, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.mToken.getAppId(), MessageService.MSG_ACCS_READY_REPORT, Long.valueOf(SystemClock.elapsedRealtime()), 0, SHARE_TO_QZONE_TYPE_IMAGE_TEXT, Constants.MSG_PARAM_TARGETURL_NULL_ERROR);
                        return;
                    } else if (!Util.isValidUrl(r1)) {
                        iUiListener.onError(new UiError(-5, Constants.MSG_PARAM_TARGETURL_ERROR, null));
                        f.e("openSDK_LOG.QzoneShare", "shareToQzone() targetUrl error--end");
                        d.a().a(SHARE_TO_QZONE_TYPE_IMAGE_TEXT, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.mToken.getAppId(), MessageService.MSG_ACCS_READY_REPORT, Long.valueOf(SystemClock.elapsedRealtime()), 0, SHARE_TO_QZONE_TYPE_IMAGE_TEXT, Constants.MSG_PARAM_TARGETURL_ERROR);
                        return;
                    }
                }
                if (this.b) {
                    string = a.d;
                    string3 = a.d;
                    bundle.putString(SHARE_TO_QQ_TITLE, string);
                    bundle.putString(SHARE_TO_QQ_SUMMARY, string3);
                } else if (this.c || !Util.isEmpty(string3)) {
                    if (!Util.isEmpty(string3) && string3.length() > 200) {
                        bundle.putString(SHARE_TO_QQ_TITLE, Util.subString(string3, Impl.STATUS_SUCCESS, null, null));
                    }
                    if (!Util.isEmpty(string2) && string2.length() > 600) {
                        bundle.putString(SHARE_TO_QQ_SUMMARY, Util.subString(string2, Impl.STATUS_LX_VIP_ERROR_START, null, null));
                    }
                } else {
                    iUiListener.onError(new UiError(-6, Constants.MSG_PARAM_TITLE_NULL_ERROR, null));
                    f.e("openSDK_LOG.QzoneShare", "shareToQzone() title is null--end");
                    d.a().a(SHARE_TO_QZONE_TYPE_IMAGE_TEXT, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.mToken.getAppId(), MessageService.MSG_ACCS_READY_REPORT, Long.valueOf(SystemClock.elapsedRealtime()), 0, SHARE_TO_QZONE_TYPE_IMAGE_TEXT, "shareToQzone() title is null");
                    return;
                }
                if (!TextUtils.isEmpty(applicationLable)) {
                    bundle.putString(SHARE_TO_QQ_APP_NAME, applicationLable);
                }
                if (stringArrayList == null && (stringArrayList == null || stringArrayList.size() != 0)) {
                    for (int i2 = 0; i2 < stringArrayList.size(); i2++) {
                        str2 = (String) stringArrayList.get(i2);
                        if (!Util.isValidUrl(str2) && !Util.fileExists(str2)) {
                            stringArrayList.remove(i2);
                        }
                    }
                    if (stringArrayList.size() == 0) {
                        iUiListener.onError(new UiError(-6, Constants.MSG_PARAM_IMAGE_URL_FORMAT_ERROR, null));
                        f.e("openSDK_LOG.QzoneShare", "shareToQzone() MSG_PARAM_IMAGE_URL_FORMAT_ERROR--end");
                        d.a().a(SHARE_TO_QZONE_TYPE_IMAGE_TEXT, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.mToken.getAppId(), MessageService.MSG_ACCS_READY_REPORT, Long.valueOf(SystemClock.elapsedRealtime()), 0, SHARE_TO_QZONE_TYPE_IMAGE_TEXT, "shareToQzone() \u975e\u6cd5\u7684\u56fe\u7247\u5730\u5740!");
                        return;
                    }
                    bundle.putStringArrayList(SHARE_TO_QQ_IMAGE_URL, stringArrayList);
                } else if (this.d) {
                    iUiListener.onError(new UiError(-6, Constants.MSG_PARAM_IMAGE_ERROR, null));
                    f.e("openSDK_LOG.QzoneShare", "shareToQzone() imageUrl is null -- end");
                    d.a().a(SHARE_TO_QZONE_TYPE_IMAGE_TEXT, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.mToken.getAppId(), MessageService.MSG_ACCS_READY_REPORT, Long.valueOf(SystemClock.elapsedRealtime()), 0, SHARE_TO_QZONE_TYPE_IMAGE_TEXT, "shareToQzone() imageUrl is null");
                    return;
                }
                if (Util.isQQVersionBelow(activity, SystemUtils.QQ_VERSION_NAME_4_6_0)) {
                    f.c("openSDK_LOG.QzoneShare", "shareToQzone() qqver greater than 4.6.0");
                    a.a((Context) activity, stringArrayList, new AnonymousClass_1(bundle, activity, iUiListener));
                } else if (SystemUtils.compareQQVersion(activity, SystemUtils.QQ_VERSION_NAME_4_2_0) >= 0 || SystemUtils.compareQQVersion(activity, SystemUtils.QQ_VERSION_NAME_4_6_0) >= 0) {
                    f.d("openSDK_LOG.QzoneShare", "shareToQzone() qqver below 4.2.0, will show download dialog");
                    new TDialog(activity, a.d, getCommonDownloadQQUrl(a.d), null, this.mToken).show();
                } else {
                    f.d("openSDK_LOG.QzoneShare", "shareToQzone() qqver between 4.2.0 and 4.6.0, will use qqshare");
                    QQShare qQShare = new QQShare(activity, this.mToken);
                    if (stringArrayList != null && stringArrayList.size() > 0) {
                        str2 = (String) stringArrayList.get(0);
                        if (i != 5 || Util.fileExists(str2)) {
                            bundle.putString(SHARE_TO_QQ_IMAGE_LOCAL_URL, str2);
                        } else {
                            iUiListener.onError(new UiError(-6, Constants.MSG_PARAM_IMAGE_URL_MUST_BE_LOCAL, null));
                            f.e("openSDK_LOG.QzoneShare", "shareToQzone()\u624bQ\u7248\u672c\u8fc7\u4f4e\uff0c\u7eaf\u56fe\u5206\u4eab\u4e0d\u652f\u6301\u7f51\u8def\u56fe\u7247");
                            d.a().a(SHARE_TO_QZONE_TYPE_IMAGE_TEXT, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.mToken.getAppId(), MessageService.MSG_ACCS_READY_REPORT, Long.valueOf(SystemClock.elapsedRealtime()), 0, SHARE_TO_QZONE_TYPE_IMAGE_TEXT, "shareToQzone()\u624bQ\u7248\u672c\u8fc7\u4f4e\uff0c\u7eaf\u56fe\u5206\u4eab\u4e0d\u652f\u6301\u7f51\u8def\u56fe\u7247");
                            return;
                        }
                    }
                    if (!Util.isQQVersionBelow(activity, SystemUtils.QQ_VERSION_NAME_4_5_0)) {
                        bundle.putInt(SHARE_TO_QQ_EXT_INT, SHARE_TO_QZONE_TYPE_IMAGE_TEXT);
                    }
                    qQShare.shareToQQ(activity, bundle, iUiListener);
                }
                f.c("openSDK_LOG.QzoneShare", "shareToQzone() --end");
            default:
                if (Util.isEmpty(string) && Util.isEmpty(string2)) {
                    if (stringArrayList == null || stringArrayList.size() == 0) {
                        string = new StringBuilder("\u6765\u81ea").append(applicationLable).append("\u7684\u5206\u4eab").toString();
                    } else {
                        this.a = false;
                    }
                }
                this.a = true;
                break;
        }
        this.b = false;
        this.c = true;
        this.d = false;
        str = string3;
        string3 = string;
        string = str;
        if (Util.hasSDCard()) {
        }
        if (this.a) {
            if (TextUtils.isEmpty(r1)) {
                iUiListener.onError(new UiError(-5, Constants.MSG_PARAM_TARGETURL_NULL_ERROR, null));
                f.e("openSDK_LOG.QzoneShare", "shareToQzone() targetUrl null error--end");
                d.a().a(SHARE_TO_QZONE_TYPE_IMAGE_TEXT, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.mToken.getAppId(), MessageService.MSG_ACCS_READY_REPORT, Long.valueOf(SystemClock.elapsedRealtime()), 0, SHARE_TO_QZONE_TYPE_IMAGE_TEXT, Constants.MSG_PARAM_TARGETURL_NULL_ERROR);
                return;
            } else if (Util.isValidUrl(r1)) {
                iUiListener.onError(new UiError(-5, Constants.MSG_PARAM_TARGETURL_ERROR, null));
                f.e("openSDK_LOG.QzoneShare", "shareToQzone() targetUrl error--end");
                d.a().a(SHARE_TO_QZONE_TYPE_IMAGE_TEXT, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.mToken.getAppId(), MessageService.MSG_ACCS_READY_REPORT, Long.valueOf(SystemClock.elapsedRealtime()), 0, SHARE_TO_QZONE_TYPE_IMAGE_TEXT, Constants.MSG_PARAM_TARGETURL_ERROR);
                return;
            }
        }
        if (this.b) {
            string = a.d;
            string3 = a.d;
            bundle.putString(SHARE_TO_QQ_TITLE, string);
            bundle.putString(SHARE_TO_QQ_SUMMARY, string3);
        } else {
            if (this.c) {
            }
            bundle.putString(SHARE_TO_QQ_TITLE, Util.subString(string3, Impl.STATUS_SUCCESS, null, null));
            bundle.putString(SHARE_TO_QQ_SUMMARY, Util.subString(string2, Impl.STATUS_LX_VIP_ERROR_START, null, null));
        }
        if (TextUtils.isEmpty(applicationLable)) {
            bundle.putString(SHARE_TO_QQ_APP_NAME, applicationLable);
        }
        if (stringArrayList == null) {
        }
        if (this.d) {
            iUiListener.onError(new UiError(-6, Constants.MSG_PARAM_IMAGE_ERROR, null));
            f.e("openSDK_LOG.QzoneShare", "shareToQzone() imageUrl is null -- end");
            d.a().a(SHARE_TO_QZONE_TYPE_IMAGE_TEXT, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.mToken.getAppId(), MessageService.MSG_ACCS_READY_REPORT, Long.valueOf(SystemClock.elapsedRealtime()), 0, SHARE_TO_QZONE_TYPE_IMAGE_TEXT, "shareToQzone() imageUrl is null");
            return;
        }
        if (Util.isQQVersionBelow(activity, SystemUtils.QQ_VERSION_NAME_4_6_0)) {
            if (SystemUtils.compareQQVersion(activity, SystemUtils.QQ_VERSION_NAME_4_2_0) >= 0) {
            }
            f.d("openSDK_LOG.QzoneShare", "shareToQzone() qqver below 4.2.0, will show download dialog");
            new TDialog(activity, a.d, getCommonDownloadQQUrl(a.d), null, this.mToken).show();
        } else {
            f.c("openSDK_LOG.QzoneShare", "shareToQzone() qqver greater than 4.6.0");
            a.a((Context) activity, stringArrayList, new AnonymousClass_1(bundle, activity, iUiListener));
        }
        f.c("openSDK_LOG.QzoneShare", "shareToQzone() --end");
    }

    private void a(Activity activity, Bundle bundle, IUiListener iUiListener) {
        f.c("openSDK_LOG.QzoneShare", "doshareToQzone() --start");
        StringBuffer stringBuffer = new StringBuffer("mqqapi://share/to_qzone?src_type=app&version=1&file_type=news");
        ArrayList stringArrayList = bundle.getStringArrayList(SHARE_TO_QQ_IMAGE_URL);
        Object string = bundle.getString(SHARE_TO_QQ_TITLE);
        Object string2 = bundle.getString(SHARE_TO_QQ_SUMMARY);
        Object string3 = bundle.getString(SHARE_TO_QQ_TARGET_URL);
        String string4 = bundle.getString(SHARE_TO_QQ_AUDIO_URL);
        int i = bundle.getInt(SHARE_TO_QZONE_KEY_TYPE, SHARE_TO_QZONE_TYPE_IMAGE_TEXT);
        Object string5 = bundle.getString(SHARE_TO_QQ_APP_NAME);
        int i2 = bundle.getInt(SHARE_TO_QQ_EXT_INT, 0);
        String string6 = bundle.getString(SHARE_TO_QQ_EXT_STR);
        Object appId = this.mToken.getAppId();
        String openId = this.mToken.getOpenId();
        f.a("openSDK_LOG.QzoneShare", new StringBuilder("openId:").append(openId).toString());
        if (stringArrayList != null) {
            StringBuffer stringBuffer2 = new StringBuffer();
            int size = stringArrayList.size() > 9 ? 9 : stringArrayList.size();
            for (int i3 = 0; i3 < size; i3++) {
                stringBuffer2.append(URLEncoder.encode((String) stringArrayList.get(i3)));
                if (i3 != size - 1) {
                    stringBuffer2.append(h.b);
                }
            }
            stringBuffer.append(new StringBuilder("&image_url=").append(Base64.encodeToString(Util.getBytesUTF8(stringBuffer2.toString()), XZBDevice.DOWNLOAD_LIST_RECYCLE)).toString());
        }
        if (!TextUtils.isEmpty(string)) {
            stringBuffer.append(new StringBuilder("&title=").append(Base64.encodeToString(Util.getBytesUTF8(string), XZBDevice.DOWNLOAD_LIST_RECYCLE)).toString());
        }
        if (!TextUtils.isEmpty(string2)) {
            stringBuffer.append(new StringBuilder("&description=").append(Base64.encodeToString(Util.getBytesUTF8(string2), XZBDevice.DOWNLOAD_LIST_RECYCLE)).toString());
        }
        if (!TextUtils.isEmpty(appId)) {
            stringBuffer.append(new StringBuilder("&share_id=").append(appId).toString());
        }
        if (!TextUtils.isEmpty(string3)) {
            stringBuffer.append(new StringBuilder("&url=").append(Base64.encodeToString(Util.getBytesUTF8(string3), XZBDevice.DOWNLOAD_LIST_RECYCLE)).toString());
        }
        if (!TextUtils.isEmpty(string5)) {
            stringBuffer.append(new StringBuilder("&app_name=").append(Base64.encodeToString(Util.getBytesUTF8(string5), XZBDevice.DOWNLOAD_LIST_RECYCLE)).toString());
        }
        if (!Util.isEmpty(openId)) {
            stringBuffer.append(new StringBuilder("&open_id=").append(Base64.encodeToString(Util.getBytesUTF8(openId), XZBDevice.DOWNLOAD_LIST_RECYCLE)).toString());
        }
        if (!Util.isEmpty(string4)) {
            stringBuffer.append(new StringBuilder("&audioUrl=").append(Base64.encodeToString(Util.getBytesUTF8(string4), XZBDevice.DOWNLOAD_LIST_RECYCLE)).toString());
        }
        stringBuffer.append(new StringBuilder("&req_type=").append(Base64.encodeToString(Util.getBytesUTF8(String.valueOf(i)), XZBDevice.DOWNLOAD_LIST_RECYCLE)).toString());
        if (!Util.isEmpty(string6)) {
            stringBuffer.append(new StringBuilder("&share_qq_ext_str=").append(Base64.encodeToString(Util.getBytesUTF8(string6), XZBDevice.DOWNLOAD_LIST_RECYCLE)).toString());
        }
        stringBuffer.append(new StringBuilder("&cflag=").append(Base64.encodeToString(Util.getBytesUTF8(String.valueOf(i2)), XZBDevice.DOWNLOAD_LIST_RECYCLE)).toString());
        f.a("openSDK_LOG.QzoneShare", new StringBuilder("doshareToQzone, url: ").append(stringBuffer.toString()).toString());
        com.tencent.connect.a.a.a(Global.getContext(), this.mToken, "requireApi", "shareToNativeQQ");
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(stringBuffer.toString()));
        intent.putExtra("pkg_name", activity.getPackageName());
        if (Util.isQQVersionBelow(activity, SystemUtils.QQ_VERSION_NAME_4_6_0)) {
            if (hasActivityForIntent(intent)) {
                UIListenerManager.getInstance().setListenerWithRequestcode(Constants.REQUEST_OLD_QZSHARE, iUiListener);
                startAssitActivity(activity, intent, Constants.REQUEST_OLD_QZSHARE);
            }
            f.c("openSDK_LOG.QzoneShare", "doShareToQzone() -- QQ Version is < 4.6.0");
        } else {
            f.c("openSDK_LOG.QzoneShare", "doShareToQzone() -- QQ Version is > 4.6.0");
            if (UIListenerManager.getInstance().setListnerWithAction(SystemUtils.QZONE_SHARE_CALLBACK_ACTION, iUiListener) != null) {
                f.c("openSDK_LOG.QzoneShare", "doShareToQzone() -- do listener onCancel()");
            }
            if (hasActivityForIntent(intent)) {
                startAssistActivity(activity, Constants.REQUEST_QZONE_SHARE, intent, false);
            }
        }
        if (hasActivityForIntent(intent)) {
            d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_SHARE_TO_QZONE, Constants.VIA_REPORT_TYPE_SHARE_TO_QZONE, MessageService.MSG_DB_NOTIFY_DISMISS, MessageService.MSG_DB_READY_REPORT, this.mViaShareQzoneType, MessageService.MSG_DB_READY_REPORT, MessageService.MSG_DB_NOTIFY_REACHED, MessageService.MSG_DB_READY_REPORT);
            d.a().a(0, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.mToken.getAppId(), MessageService.MSG_ACCS_READY_REPORT, Long.valueOf(SystemClock.elapsedRealtime()), 0, SHARE_TO_QZONE_TYPE_IMAGE_TEXT, a.d);
        } else {
            d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_SHARE_TO_QZONE, Constants.VIA_REPORT_TYPE_SHARE_TO_QZONE, MessageService.MSG_DB_NOTIFY_DISMISS, MessageService.MSG_DB_NOTIFY_REACHED, this.mViaShareQzoneType, MessageService.MSG_DB_READY_REPORT, MessageService.MSG_DB_NOTIFY_REACHED, MessageService.MSG_DB_READY_REPORT);
            d.a().a(SHARE_TO_QZONE_TYPE_IMAGE_TEXT, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.mToken.getAppId(), MessageService.MSG_ACCS_READY_REPORT, Long.valueOf(SystemClock.elapsedRealtime()), 0, SHARE_TO_QZONE_TYPE_IMAGE_TEXT, "hasActivityForIntent fail");
        }
        f.c("openSDK_LOG", "doShareToQzone() --end");
    }

    public void releaseResource() {
    }
}
