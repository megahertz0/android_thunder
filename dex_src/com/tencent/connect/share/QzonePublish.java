package com.tencent.connect.share;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Base64;
import com.alipay.sdk.util.h;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.Constants;
import com.tencent.open.TDialog;
import com.tencent.open.a.f;
import com.tencent.open.b.d;
import com.tencent.open.utils.Global;
import com.tencent.open.utils.SystemUtils;
import com.tencent.open.utils.Util;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import com.umeng.a;
import com.umeng.message.MsgConstant;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import org.android.agoo.message.MessageService;

// compiled from: ProGuard
public class QzonePublish extends BaseApi {
    public static final String PUBLISH_TO_QZONE_APP_NAME = "appName";
    public static final String PUBLISH_TO_QZONE_IMAGE_URL = "imageUrl";
    public static final String PUBLISH_TO_QZONE_KEY_TYPE = "req_type";
    public static final String PUBLISH_TO_QZONE_SUMMARY = "summary";
    public static final int PUBLISH_TO_QZONE_TYPE_PUBLISHMOOD = 3;
    public static final int PUBLISH_TO_QZONE_TYPE_PUBLISHVIDEO = 4;
    public static final String PUBLISH_TO_QZONE_VIDEO_DURATION = "videoDuration";
    public static final String PUBLISH_TO_QZONE_VIDEO_PATH = "videoPath";
    public static final String PUBLISH_TO_QZONE_VIDEO_SIZE = "videoSize";

    // compiled from: ProGuard
    class AnonymousClass_1 implements OnPreparedListener {
        final /* synthetic */ String a;
        final /* synthetic */ Bundle b;
        final /* synthetic */ Activity c;
        final /* synthetic */ IUiListener d;

        AnonymousClass_1(String str, Bundle bundle, Activity activity, IUiListener iUiListener) {
            this.a = str;
            this.b = bundle;
            this.c = activity;
            this.d = iUiListener;
        }

        public void onPrepared(MediaPlayer mediaPlayer) {
            long length = new File(this.a).length();
            int duration = mediaPlayer.getDuration();
            this.b.putString(PUBLISH_TO_QZONE_VIDEO_PATH, this.a);
            this.b.putInt(PUBLISH_TO_QZONE_VIDEO_DURATION, duration);
            this.b.putLong(PUBLISH_TO_QZONE_VIDEO_SIZE, length);
            QzonePublish.this.a(this.c, this.b, this.d);
            f.c("openSDK_LOG.QzonePublish", "publishToQzone() --end");
        }
    }

    // compiled from: ProGuard
    class AnonymousClass_2 implements OnErrorListener {
        final /* synthetic */ IUiListener a;

        AnonymousClass_2(IUiListener iUiListener) {
            this.a = iUiListener;
        }

        public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
            f.e("openSDK_LOG.QzonePublish", "publishToQzone() mediaplayer onError()");
            this.a.onError(new UiError(-5, Constants.MSG_PUBLISH_VIDEO_ERROR, null));
            return false;
        }
    }

    public QzonePublish(Context context, QQToken qQToken) {
        super(qQToken);
    }

    public void publishToQzone(Activity activity, Bundle bundle, IUiListener iUiListener) {
        int i = 0;
        f.c("openSDK_LOG.QzonePublish", "publishToQzone() -- start");
        if (bundle == null) {
            iUiListener.onError(new UiError(-6, Constants.MSG_PARAM_NULL_ERROR, null));
            f.e("openSDK_LOG.QzonePublish", "-->publishToQzone, params is null");
            d.a().a(1, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.mToken.getAppId(), MessageService.MSG_ACCS_READY_REPORT, Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, Constants.MSG_PARAM_NULL_ERROR);
        } else if (SystemUtils.compareQQVersion(activity, SystemUtils.QQ_VERSION_NAME_5_9_5) < 0) {
            iUiListener.onError(new UiError(-15, Constants.MSG_PARAM_VERSION_TOO_LOW, null));
            f.e("openSDK_LOG.QzonePublish", "-->publishToQzone, this is not support below qq 5.9.5");
            d.a().a(1, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.mToken.getAppId(), MessageService.MSG_ACCS_READY_REPORT, Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "publicToQzone, this is not support below qq 5.9.5");
            new TDialog(activity, a.d, getCommonDownloadQQUrl(a.d), null, this.mToken).show();
        } else {
            String string = bundle.getString(PUBLISH_TO_QZONE_SUMMARY);
            ArrayList stringArrayList = bundle.getStringArrayList(PUBLISH_TO_QZONE_IMAGE_URL);
            Object applicationLable = Util.getApplicationLable(activity);
            if (applicationLable == null) {
                applicationLable = bundle.getString(PUBLISH_TO_QZONE_APP_NAME);
            } else if (applicationLable.length() > 20) {
                applicationLable = applicationLable.substring(0, R.styleable.Toolbar_navigationIcon) + "...";
            }
            if (!TextUtils.isEmpty(applicationLable)) {
                bundle.putString(PUBLISH_TO_QZONE_APP_NAME, applicationLable);
            }
            bundle.putString(PUBLISH_TO_QZONE_SUMMARY, string);
            int i2 = bundle.getInt(PUBLISH_TO_QZONE_KEY_TYPE);
            if (i2 == 3) {
                if (stringArrayList != null && stringArrayList.size() > 0) {
                    while (i < stringArrayList.size()) {
                        if (!Util.fileExists((String) stringArrayList.get(i))) {
                            stringArrayList.remove(i);
                        }
                        i++;
                    }
                    bundle.putStringArrayList(PUBLISH_TO_QZONE_IMAGE_URL, stringArrayList);
                }
                a(activity, bundle, iUiListener);
                f.c("openSDK_LOG.QzonePublish", "publishToQzone() --end");
            } else if (i2 == 4) {
                string = bundle.getString(PUBLISH_TO_QZONE_VIDEO_PATH);
                if (Util.fileExists(string)) {
                    MediaPlayer mediaPlayer = new MediaPlayer();
                    mediaPlayer.setOnPreparedListener(new AnonymousClass_1(string, bundle, activity, iUiListener));
                    mediaPlayer.setOnErrorListener(new AnonymousClass_2(iUiListener));
                    try {
                        mediaPlayer.setDataSource(string);
                        mediaPlayer.prepareAsync();
                        return;
                    } catch (Exception e) {
                        f.e("openSDK_LOG.QzonePublish", "publishToQzone() exception(s) occurred when preparing mediaplayer");
                        iUiListener.onError(new UiError(-5, Constants.MSG_PUBLISH_VIDEO_ERROR, null));
                    }
                }
                f.e("openSDK_LOG.QzonePublish", "publishToQzone() video url invalid");
                iUiListener.onError(new UiError(-5, Constants.MSG_PUBLISH_VIDEO_ERROR, null));
            } else {
                iUiListener.onError(new UiError(-5, Constants.MSG_SHARE_TYPE_ERROR, null));
                f.e("openSDK_LOG.QzonePublish", "publishToQzone() error--end\u8bf7\u9009\u62e9\u652f\u6301\u7684\u5206\u4eab\u7c7b\u578b");
                d.a().a(1, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.mToken.getAppId(), MessageService.MSG_ACCS_READY_REPORT, Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "publishToQzone() \u8bf7\u9009\u62e9\u652f\u6301\u7684\u5206\u4eab\u7c7b\u578b");
            }
        }
    }

    private void a(Activity activity, Bundle bundle, IUiListener iUiListener) {
        f.c("openSDK_LOG.QzonePublish", "doPublishToQzone() --start");
        StringBuffer stringBuffer = new StringBuffer("mqqapi://qzone/publish?src_type=app&version=1&file_type=news");
        ArrayList stringArrayList = bundle.getStringArrayList(PUBLISH_TO_QZONE_IMAGE_URL);
        Object string = bundle.getString(PUBLISH_TO_QZONE_SUMMARY);
        int i = bundle.getInt(PUBLISH_TO_QZONE_KEY_TYPE, PUBLISH_TO_QZONE_TYPE_PUBLISHMOOD);
        Object string2 = bundle.getString(PUBLISH_TO_QZONE_APP_NAME);
        String string3 = bundle.getString(PUBLISH_TO_QZONE_VIDEO_PATH);
        int i2 = bundle.getInt(PUBLISH_TO_QZONE_VIDEO_DURATION);
        long j = bundle.getLong(PUBLISH_TO_QZONE_VIDEO_SIZE);
        Object appId = this.mToken.getAppId();
        String openId = this.mToken.getOpenId();
        f.a("openSDK_LOG.QzonePublish", new StringBuilder("openId:").append(openId).toString());
        String str = a.d;
        if (3 == i && stringArrayList != null) {
            String str2 = MsgConstant.MESSAGE_NOTIFY_ARRIVAL;
            StringBuffer stringBuffer2 = new StringBuffer();
            int size = stringArrayList.size();
            for (int i3 = 0; i3 < size; i3++) {
                stringBuffer2.append(URLEncoder.encode((String) stringArrayList.get(i3)));
                if (i3 != size - 1) {
                    stringBuffer2.append(h.b);
                }
            }
            stringBuffer.append(new StringBuilder("&image_url=").append(Base64.encodeToString(Util.getBytesUTF8(stringBuffer2.toString()), XZBDevice.DOWNLOAD_LIST_RECYCLE)).toString());
            str = str2;
        }
        if (4 == i) {
            str = MessageService.MSG_ACCS_NOTIFY_CLICK;
            stringBuffer.append(new StringBuilder("&videoPath=").append(Base64.encodeToString(Util.getBytesUTF8(string3), XZBDevice.DOWNLOAD_LIST_RECYCLE)).toString());
            stringBuffer.append(new StringBuilder("&videoDuration=").append(Base64.encodeToString(Util.getBytesUTF8(String.valueOf(i2)), XZBDevice.DOWNLOAD_LIST_RECYCLE)).toString());
            stringBuffer.append(new StringBuilder("&videoSize=").append(Base64.encodeToString(Util.getBytesUTF8(String.valueOf(j)), XZBDevice.DOWNLOAD_LIST_RECYCLE)).toString());
        }
        String str3 = str;
        if (!TextUtils.isEmpty(string)) {
            stringBuffer.append(new StringBuilder("&description=").append(Base64.encodeToString(Util.getBytesUTF8(string), XZBDevice.DOWNLOAD_LIST_RECYCLE)).toString());
        }
        if (!TextUtils.isEmpty(appId)) {
            stringBuffer.append(new StringBuilder("&share_id=").append(appId).toString());
        }
        if (!TextUtils.isEmpty(string2)) {
            stringBuffer.append(new StringBuilder("&app_name=").append(Base64.encodeToString(Util.getBytesUTF8(string2), XZBDevice.DOWNLOAD_LIST_RECYCLE)).toString());
        }
        if (!Util.isEmpty(openId)) {
            stringBuffer.append(new StringBuilder("&open_id=").append(Base64.encodeToString(Util.getBytesUTF8(openId), XZBDevice.DOWNLOAD_LIST_RECYCLE)).toString());
        }
        stringBuffer.append(new StringBuilder("&req_type=").append(Base64.encodeToString(Util.getBytesUTF8(String.valueOf(i)), XZBDevice.DOWNLOAD_LIST_RECYCLE)).toString());
        f.a("openSDK_LOG.QzonePublish", new StringBuilder("doPublishToQzone, url: ").append(stringBuffer.toString()).toString());
        com.tencent.connect.a.a.a(Global.getContext(), this.mToken, "requireApi", "shareToNativeQQ");
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(stringBuffer.toString()));
        intent.putExtra("pkg_name", activity.getPackageName());
        if (hasActivityForIntent(intent)) {
            startAssistActivity(activity, Constants.REQUEST_QZONE_SHARE, intent, false);
            d.a().a(0, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.mToken.getAppId(), MessageService.MSG_ACCS_READY_REPORT, Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "hasActivityForIntent success");
            d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_SHARE_TO_QZONE, Constants.VIA_REPORT_TYPE_SHARE_TO_QZONE, MessageService.MSG_DB_NOTIFY_DISMISS, MessageService.MSG_DB_NOTIFY_REACHED, str3, MessageService.MSG_DB_READY_REPORT, MessageService.MSG_DB_NOTIFY_REACHED, MessageService.MSG_DB_READY_REPORT);
        } else {
            f.e("openSDK_LOG.QzonePublish", "doPublishToQzone() target activity not found");
            d.a().a(1, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.mToken.getAppId(), MessageService.MSG_ACCS_READY_REPORT, Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "hasActivityForIntent fail");
            d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_SHARE_TO_QZONE, Constants.VIA_REPORT_TYPE_SHARE_TO_QZONE, MessageService.MSG_DB_NOTIFY_DISMISS, MessageService.MSG_DB_NOTIFY_REACHED, str3, MessageService.MSG_DB_READY_REPORT, MessageService.MSG_DB_NOTIFY_REACHED, MessageService.MSG_DB_READY_REPORT);
        }
        f.c("openSDK_LOG", "doPublishToQzone() --end");
    }
}
