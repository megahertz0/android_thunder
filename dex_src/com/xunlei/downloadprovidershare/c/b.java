package com.xunlei.downloadprovidershare.c;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import com.tencent.connect.common.Constants;
import com.tencent.connect.share.QzoneShare;
import com.tencent.open.GameAppOperation;
import com.tencent.open.SocialConstants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMVideo;
import com.xunlei.downloadprovider.a.c;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovidershare.az;
import com.xunlei.downloadprovidershare.data.ShareBean;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.WebBrowserActivity;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.android.spdy.SpdyAgent;

// compiled from: QQShareProxy.java
public final class b extends a {
    public a a;
    private Tencent b;

    // compiled from: QQShareProxy.java
    class a implements IUiListener {
        UMShareListener a;

        public final void onComplete(Object obj) {
            this.a.onResult(SHARE_MEDIA.QQ);
        }

        public final void onError(UiError uiError) {
            this.a.onError(SHARE_MEDIA.QQ, null);
        }

        public final void onCancel() {
            this.a.onCancel(SHARE_MEDIA.QQ);
        }
    }

    public b() {
        this.b = Tencent.createInstance("1101105049", az.a().b);
        this.a = new a();
    }

    public final void b(SHARE_MEDIA share_media, Activity activity, ShareBean shareBean, UMShareListener uMShareListener) {
        String str;
        String str2;
        ShareAction shareAction;
        switch (AnonymousClass_1.a[share_media.ordinal()]) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                shareBean.toString();
                if (c.a(Constants.PACKAGE_QQ)) {
                    shareBean.f = "qq";
                    if (!az.a || "fun_home".equals(shareBean.e) || "fun_detail".equals(shareBean.e)) {
                        str = shareBean.c;
                        str2 = shareBean.d;
                        if (TextUtils.isEmpty(str)) {
                            str = "\u8fc5\u96f7\u5206\u4eab";
                        }
                        shareAction = new ShareAction(activity);
                        UMImage a = a(activity, shareBean);
                        shareAction.setPlatform(SHARE_MEDIA.QQ).setCallback(uMShareListener);
                        if (shareBean.g) {
                            shareAction.withText(str2);
                            UMVideo uMVideo = new UMVideo(shareBean.a);
                            uMVideo.setThumb(a);
                            uMVideo.setTitle(str);
                            shareAction.withMedia(uMVideo);
                        } else {
                            shareAction.withTitle(str);
                            shareAction.withText(str2);
                            shareAction.withTargetUrl(shareBean.a);
                            shareAction.withMedia(a);
                        }
                        shareAction.share();
                        return;
                    }
                    this.a.a = uMShareListener;
                    Bundle bundle = new Bundle();
                    str = shareBean.c;
                    str2 = shareBean.d;
                    if (TextUtils.isEmpty(str)) {
                        str = str2;
                    }
                    if (shareBean.g) {
                        bundle.putInt(GameAppOperation.QQFAV_DATALINE_REQTYPE, XZBDevice.DOWNLOAD_LIST_RECYCLE);
                        bundle.putString(SocialConstants.PARAM_IMAGE_URL, shareBean.b);
                    } else {
                        bundle.putInt(GameAppOperation.QQFAV_DATALINE_REQTYPE, 1);
                        bundle.putString(QzoneShare.SHARE_TO_QQ_IMAGE_LOCAL_URL, a(BitmapFactory.decodeResource(activity.getResources(), a(shareBean))));
                    }
                    bundle.putString(WebBrowserActivity.EXTRA_TITLE, str);
                    bundle.putString(SocialConstants.PARAM_TARGET_URL, shareBean.a);
                    new StringBuilder("  targetUrl: ").append(shareBean.a);
                    bundle.putString(SocialConstants.PARAM_SUMMARY, str2);
                    this.b.shareToQQ(activity, bundle, this.a);
                    return;
                }
                XLToast.b(activity, XLToastType.XLTOAST_TYPE_ALARM, "\u5c1a\u672a\u5b89\u88c5QQ");
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                shareBean.f = Constants.SOURCE_QZONE;
                if (c.a(Constants.PACKAGE_QQ)) {
                    str = shareBean.c;
                    str2 = shareBean.d;
                    if (TextUtils.isEmpty(str)) {
                        str = "\u8fc5\u96f7\u5206\u4eab";
                    }
                    shareAction = new ShareAction(activity);
                    shareAction.setPlatform(SHARE_MEDIA.QZONE).setCallback(uMShareListener).withTitle(str).withText(str2).withTargetUrl(shareBean.a).withMedia(a(activity, shareBean));
                    shareAction.share();
                    return;
                }
                XLToast.b(activity, XLToastType.XLTOAST_TYPE_ALARM, "\u5c1a\u672a\u5b89\u88c5QQ");
            default:
                break;
        }
    }

    private static String a(Bitmap bitmap) {
        Throwable th;
        StringBuilder stringBuilder = new StringBuilder();
        az.a();
        String toString = stringBuilder.append(com.xunlei.downloadprovider.businessutil.a.h()).append("shareImageIcon.png").toString();
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.PNG, R.styleable.AppCompatTheme_buttonStyle, byteArrayOutputStream);
        byte[] toByteArray = byteArrayOutputStream.toByteArray();
        BufferedOutputStream bufferedOutputStream = null;
        File file = new File(toString);
        try {
            if (file.exists()) {
                file.delete();
                file.createNewFile();
            } else {
                file.createNewFile();
            }
            BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(new FileOutputStream(file));
            try {
                bufferedOutputStream2.write(toByteArray, 0, toByteArray.length);
                bufferedOutputStream2.flush();
                try {
                    bufferedOutputStream2.close();
                } catch (IOException e) {
                }
            } catch (Exception e2) {
                e = e2;
                bufferedOutputStream = bufferedOutputStream2;
                e.printStackTrace();
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
                return toString;
            } catch (Throwable th2) {
                th = th2;
                bufferedOutputStream = bufferedOutputStream2;
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
            try {
                Exception e4;
                e4.printStackTrace();
                if (bufferedOutputStream != null) {
                    try {
                        bufferedOutputStream.close();
                    } catch (IOException e5) {
                    }
                }
            } catch (Throwable th3) {
                th = th3;
                if (bufferedOutputStream != null) {
                    try {
                        bufferedOutputStream.close();
                    } catch (IOException e6) {
                    }
                }
                throw th;
            }
            return toString;
        }
        return toString;
    }
}
