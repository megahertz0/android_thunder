package com.xunlei.downloadprovidershare;

import android.app.Activity;
import android.app.Application;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface.OnDismissListener;
import android.content.DialogInterface.OnShowListener;
import android.content.Intent;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.tencent.tauth.Tencent;
import com.umeng.message.MsgConstant;
import com.umeng.socialize.Config;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.xunlei.downloadprovider.a.e;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.commonview.dialog.x;
import com.xunlei.downloadprovidershare.c.b;
import com.xunlei.downloadprovidershare.c.c;
import com.xunlei.downloadprovidershare.data.ShareBean;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

// compiled from: ShareHelper.java
public class d {
    private static Application d;
    private static d e;
    public com.xunlei.downloadprovidershare.a.a a;
    public OnDismissListener b;
    public OnShowListener c;
    private Activity f;
    private ShareBean g;
    private SHARE_MEDIA h;
    private a i;
    private com.xunlei.downloadprovidershare.c.d j;
    private b k;
    private c l;
    private x m;
    private UMShareListener n;

    // compiled from: ShareHelper.java
    public static interface a {
        void onShareComplete(int i, SHARE_MEDIA share_media, ShareBean shareBean);

        void onShareTargetClicked(SHARE_MEDIA share_media, ShareBean shareBean);
    }

    // compiled from: ShareHelper.java
    static /* synthetic */ class AnonymousClass_1 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[SHARE_MEDIA.values().length];
            try {
                a[SHARE_MEDIA.WEIXIN.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[SHARE_MEDIA.WEIXIN_CIRCLE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[SHARE_MEDIA.SINA.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[SHARE_MEDIA.QQ.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[SHARE_MEDIA.QZONE.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    static /* synthetic */ void a(d dVar) {
        dVar.f = null;
        dVar.h = null;
        dVar.i = null;
    }

    public static void a(Application application) {
        d = application;
    }

    public static Application a() {
        return d;
    }

    private d() {
        this.n = new af(this);
        this.j = new com.xunlei.downloadprovidershare.c.d();
        this.k = new b();
        this.l = new c();
    }

    public static d b() {
        if (e == null) {
            synchronized (d.class) {
                if (e == null) {
                    az a = az.a();
                    a aVar = (a) d;
                    a.b = d;
                    a.c = aVar;
                    e = new d();
                }
            }
        }
        return e;
    }

    private void a(Activity activity, String str, boolean z) {
        if (z) {
            if (this.m == null) {
                this.m = new x(activity);
                this.m.setOnDismissListener(new e(this));
            }
            if (!TextUtils.isEmpty(str)) {
                this.m.a(str);
            }
            this.m.show();
        }
    }

    public final void c() {
        if (this.m != null) {
            this.m.dismiss();
        }
    }

    public static void a(ShareBean shareBean) {
        new StringBuilder("ShareBean=").append(shareBean);
        if ("fun_home".equals(shareBean.e) || "fun_detail".equals(shareBean.e)) {
            shareBean.d = "\u770b\u5185\u6db5\u641e\u7b11\u56fe\u7247,\u4e0a\u8fc5\u96f7APP\uff01";
        } else if (shareBean.g) {
            shareBean.d = "\u770b\u5185\u6db5\u641e\u7b11\u89c6\u9891,\u4e0a\u8fc5\u96f7APP\uff01";
        } else {
            shareBean.d = "\u6781\u901f\u4e0b\u8f7d\u4f53\u9a8c,\u4e0a\u8fc5\u96f7APP\uff01";
        }
        if (shareBean != null) {
            String str = shareBean.a;
            if (!TextUtils.isEmpty(str)) {
                if (!shareBean.a()) {
                    if (str.contains("?")) {
                        str = str + "&appVersion=" + com.xunlei.downloadprovider.a.b.f(d);
                    } else {
                        str = str + "?appVersion=" + com.xunlei.downloadprovider.a.b.f(d);
                    }
                    if (!str.contains("appType")) {
                        str = str + "&appType=android";
                    }
                }
                shareBean.a = str;
            }
        }
        if (shareBean != null && shareBean.g) {
            shareBean.b += "?imageMogr2/thumbnail/!200p/gravity/Center/crop/400x400";
        }
    }

    public final void a(int i, int i2, Intent intent) {
        new StringBuilder("onActivityResult--requestCode=").append(i).append("|resultCode=").append(i2).append("|data=").append(intent);
        if (this.h == null || this.h != SHARE_MEDIA.QQ) {
            UMShareAPI.get(this.f).onActivityResult(i, i2, intent);
        } else if (az.a) {
            b bVar = this.k;
            if (i == 10103) {
                Tencent.onActivityResultData(i, i2, intent, bVar.a);
            }
        } else {
            UMShareAPI.get(this.f).onActivityResult(i, i2, intent);
        }
    }

    public final void a(Activity activity, ShareBean shareBean, a aVar) {
        if (com.xunlei.xllib.a.b.a(activity)) {
            a(shareBean);
            this.a = new com.xunlei.downloadprovidershare.a.a(activity);
            this.a.setOnShowListener(this.c);
            this.a.setOnDismissListener(this.b);
            this.a.a = new f(this, activity, aVar, shareBean);
            this.a.show();
            com.xunlei.downloadprovidershare.a.a aVar2 = this.a;
            String str = shareBean.e;
            if ("download_detail_top".equals(str) || "button_press".equals(str)) {
                aVar2.a(0);
            } else {
                aVar2.a(XZBDevice.Wait);
            }
            if ("detail_shortvideo_foot".equals(str) || "detail_shortvideo_top".equals(str)) {
                aVar2.d(0);
                aVar2.b(0);
            } else {
                aVar2.d(XZBDevice.Wait);
                aVar2.b(XZBDevice.Wait);
            }
            if ("detail_shortvideo_foot".equals(str) || "detail_shortvideo_top".equals(str) || "channel_flow".equals(str) || "short_video".equals(str)) {
                aVar2.c(0);
            } else {
                aVar2.c(XZBDevice.Wait);
            }
            if ("short_video".equals(str) || "channel_flow".equals(str)) {
                aVar2.d(0);
                return;
            }
            return;
        }
        XLToast.b(activity, XLToastType.XLTOAST_TYPE_ALARM, "\u65e0\u7f51\u7edc\u8fde\u63a5");
    }

    private void a(Activity activity, a aVar, ShareBean shareBean, SHARE_MEDIA share_media) {
        this.f = activity;
        this.i = aVar;
        this.h = share_media;
        this.g = shareBean;
        if (share_media != null) {
            shareBean.a = shareBean.a;
        }
        Config.dialog = new x(this.f);
    }

    public final void a(Activity activity, ShareBean shareBean, SHARE_MEDIA share_media, a aVar) {
        if (!com.xunlei.xllib.a.b.a(activity)) {
            XLToast.b(activity, XLToastType.XLTOAST_TYPE_ALARM, "\u65e0\u7f51\u7edc\u8fde\u63a5");
        }
        if (shareBean.a()) {
            a(activity, com.umeng.a.d, false);
            a(shareBean);
            com.xunlei.downloadprovidershare.b.d.a().a(shareBean.j, new ad(this, activity, shareBean, share_media, aVar));
            return;
        }
        a(shareBean);
        b(activity, shareBean, share_media, aVar);
    }

    private void b(Activity activity, ShareBean shareBean, SHARE_MEDIA share_media, a aVar) {
        a(activity, aVar, shareBean, share_media);
        switch (AnonymousClass_1.a[share_media.ordinal()]) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                com.xunlei.downloadprovidershare.c.d.b(share_media, activity, shareBean, this.n);
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                c.a(activity, shareBean, this.n);
            case XZBDevice.DOWNLOAD_LIST_ALL:
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                this.k.b(share_media, activity, shareBean, this.n);
            default:
                break;
        }
    }

    public final void d() {
        if (this.h != null && this.g != null && this.f != null) {
            switch (AnonymousClass_1.a[this.h.ordinal()]) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    this.j.a(this.h, this.f, this.g, this.n);
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    this.l.a(this.h, this.f, this.g, this.n);
                default:
                    break;
            }
        }
    }

    public final void a(int i, int i2) {
        if (this.g != null && this.h != null) {
            new StringBuilder("sharestate=>").append(i).append(",errorCode=>").append(i2);
            az a = az.a();
            if (a.c != null) {
                a.c.a(i);
            }
            if (this.i != null) {
                this.i.onShareComplete(i, this.h, this.g);
            }
            this.f = null;
            this.h = null;
            this.g = null;
            this.i = null;
        }
    }

    public static String a(int i) {
        String str = com.umeng.a.d;
        switch (i) {
            case SpdyAgent.ACCS_TEST_SERVER:
                return MsgConstant.KEY_SUCCESS;
            case SpdyAgent.ACCS_ONLINE_SERVER:
                return MsgConstant.KEY_FAIL;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                return "cancel";
            default:
                return str;
        }
    }

    private static String b(ShareBean shareBean) {
        String str = shareBean.c;
        return new StringBuilder("\u3010").append(str).append("\u3011").append(shareBean.a).toString();
    }

    static /* synthetic */ void a(d dVar, ShareBean shareBean) {
        CharSequence b = b(shareBean);
        Context context = d;
        if (b != null) {
            if (VERSION.SDK_INT >= 11) {
                ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService("clipboard");
                clipboardManager.addPrimaryClipChangedListener(new e(clipboardManager, context));
                clipboardManager.setPrimaryClip(ClipData.newPlainText("thunder", b));
            } else {
                ((android.text.ClipboardManager) context.getSystemService("clipboard")).setText(b);
                XLToast.a(context, XLToastType.XLTOAST_TYPE_SUC, "\u590d\u5236\u6210\u529f");
            }
        }
        if (dVar.i != null) {
            dVar.i.onShareTargetClicked(null, shareBean);
        }
    }

    static /* synthetic */ void a(d dVar, Activity activity, ShareBean shareBean) {
        String b = b(shareBean);
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.TEXT", b);
        intent.setType("text/*");
        activity.startActivity(Intent.createChooser(intent, "\u5206\u4eab\u5230"));
        if (dVar.i != null) {
            dVar.i.onShareTargetClicked(null, shareBean);
        }
        dVar.f = null;
    }
}
