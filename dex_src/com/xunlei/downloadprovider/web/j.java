package com.xunlei.downloadprovider.web;

import android.text.TextUtils;
import android.webkit.WebView;
import com.umeng.a;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.model.g;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.model.protocol.report.b;
import com.xunlei.downloadprovider.url.DownData;
import com.xunlei.downloadprovider.web.core.p;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.List;

// compiled from: DetailPageBrowserActivity.java
final class j extends p {
    boolean a;
    final /* synthetic */ DetailPageBrowserActivity b;

    j(DetailPageBrowserActivity detailPageBrowserActivity) {
        this.b = detailPageBrowserActivity;
        this.a = false;
    }

    public final void b() {
        DetailPageBrowserActivity.c;
        new StringBuilder("onHideJavaScriptLoadingView , mWebView = ").append(this.b.d);
    }

    public final void b(WebView webView, String str) {
        if (this.b.i && !str.equals("\u699c\u5355") && !str.equals("\u8fc5\u96f7\u725bX\u6e38\u620f\u4e2d\u5fc3")) {
            this.b.e.i.setText(str);
        } else if (!webView.canGoBack() && !TextUtils.isEmpty(this.b.r) && webView.canGoForward()) {
            this.b.e.i.setText(this.b.r);
        } else if (!(str.equals("\u699c\u5355") || str.equals("\u8fc5\u96f7\u725bX\u6e38\u620f\u4e2d\u5fc3"))) {
            this.b.e.i.setText(str);
        }
        this.b.i = true;
    }

    public final void a(DownData downData) {
        DetailPageBrowserActivity.c;
        g gVar = new g(this.b.n, 2, downData.e, downData.s);
        gVar.d = "browser/browser";
        if (!(TextUtils.isEmpty(this.b.p) || TextUtils.isEmpty(this.b.o))) {
            StatReporter.reportAdEvent("adv_apkdetail_dl", a.d, a.d, a.d, a.d, this.b.o);
            com.xunlei.downloadprovider.model.protocol.report.a.a("adv_apkdetail_dl", new b.a().a("ad_id", this.b.p).a("from", this.b.o));
        }
        if (downData.z == null || downData.z.size() <= 0 || downData.y == null || downData.y.size() <= 0) {
            this.b.createTask(downData, this.b.y, gVar, false);
        } else {
            this.b.createTask(downData, this.b.y, gVar, true);
        }
        DetailPageBrowserActivity.c;
        if (this.b.n == 36) {
            StatReporter.reportFriendGroupListDownload(LoginHelper.a().j);
        }
    }

    public final void a(List<DownData> list) {
        DetailPageBrowserActivity.c;
        this.b.createTasks(this.b.n, list, this.b.y, XZBDevice.DOWNLOAD_LIST_RECYCLE, null);
        DetailPageBrowserActivity.c;
        if (!TextUtils.isEmpty(this.b.p) && !TextUtils.isEmpty(this.b.o)) {
            StatReporter.reportAdEvent("adv_apkdetail_dl", a.d, a.d, a.d, a.d, this.b.o);
            com.xunlei.downloadprovider.model.protocol.report.a.a("adv_apkdetail_dl", new b.a().a("ad_id", this.b.p));
        }
    }

    public final void a() {
        if (!(this.a || TextUtils.isEmpty(this.b.p) || TextUtils.isEmpty(this.b.o))) {
            DetailPageBrowserActivity.c;
            new StringBuilder("report onPageFinish: ").append(this.b.p).append(" mFromKey: ").append(this.b.o);
            StatReporter.reportAdEvent("adv_apkdetail_show", this.b.p, this.b.o);
            com.xunlei.downloadprovider.model.protocol.report.a.a("adv_apkdetail_show", new b.a().a("ad_id", this.b.p).a("from", this.b.o));
            this.a = true;
        }
        this.b.y.post(new k(this));
    }
}
