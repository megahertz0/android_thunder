package com.xunlei.downloadprovider.frame.remotectrl.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebView;
import com.xunlei.downloadprovider.a.h.b;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.member.login.LoginHelper.c;
import com.xunlei.downloadprovider.member.login.LoginHelper.d;
import com.xunlei.downloadprovider.member.login.LoginHelper.g;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.service.DownloadService;
import com.xunlei.downloadprovider.service.downloads.TaskInfo;
import com.xunlei.downloadprovider.task.ThunderTask;
import com.xunlei.downloadprovider.url.DownData;
import com.xunlei.downloadprovider.web.base.core.BaseJsInterface;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.downloadprovider.web.core.ThunderWebView;
import com.xunlei.downloadprovider.web.core.p;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class RemoteDownloadActivity extends ThunderTask {
    protected static final String a;
    private static ThunderWebView b;
    private boolean c;
    private boolean d;
    private boolean e;
    private String f;
    private String g;
    private ArrayList<String> h;
    private final g i;
    private final d j;
    private c k;
    private final com.xunlei.downloadprovider.a.h.a l;
    private b m;

    class a extends p {
        public final boolean a(WebView webView, String str) {
            RemoteDownloadActivity.this.c = str.equals("http://wap.yuancheng.xunlei.com/index.html");
            if (!str.equals("http://wap.yuancheng.xunlei.com/index.html")) {
                RemoteDownloadActivity.this.h.add(str);
            }
            return super.a(webView, str);
        }

        public final void a(DownData downData) {
            com.xunlei.downloadprovider.model.g gVar = new com.xunlei.downloadprovider.model.g(41, downData.e, downData.s);
            gVar.d = "nearby/nearby";
            if (DownloadService.a() == null) {
                DownloadService.a(new g(this, downData, gVar));
            } else {
                RemoteDownloadActivity.this.createTask(downData, null, gVar, false);
            }
            StatReporter.reportOverallDownload("nearby");
        }

        public final void a(List<DownData> list) {
            String str = a;
            if (DownloadService.a() == null) {
                DownloadService.a(new h(this, list));
            } else {
                RemoteDownloadActivity.this.createTasks(R.styleable.AppCompatTheme_textAppearanceSmallPopupMenu, list, RemoteDownloadActivity.this.m, 41, null);
            }
            str = a;
            StatReporter.reportOverallDownload("nearby");
        }
    }

    public RemoteDownloadActivity() {
        this.c = true;
        this.d = false;
        this.e = false;
        this.f = com.umeng.a.d;
        this.g = com.umeng.a.d;
        this.h = new ArrayList();
        this.i = new b(this);
        this.j = new c(this);
        this.k = new d(this);
        this.l = new e(this);
        this.m = new b(this.l);
    }

    static {
        a = RemoteDownloadActivity.class.getSimpleName();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130968931);
        StatReporter.reportEnterRemoteDownload(LoginHelper.a().j);
        ThunderWebView thunderWebView = (ThunderWebView) findViewById(2131756827);
        b = thunderWebView;
        thunderWebView.setJsCallbackMessageListener(this.l);
        b.setThunderWebViewClient(new a());
        b.setIsReportPage(true);
        b.a("http://wap.yuancheng.xunlei.com/index.html");
        b();
        LoginHelper.a().a(this.i);
        LoginHelper.a().a(this.j);
        b.setRefreshButtonListener(new a(this));
    }

    private void b() {
        b.a("http://wap.yuancheng.xunlei.com/index.html");
        this.h.add("http://wap.yuancheng.xunlei.com/index.html");
    }

    private static String b(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        long j = LoginHelper.a().j;
        String str2 = LoginHelper.a().n;
        String str3 = LoginHelper.a().k;
        String str4 = LoginHelper.a().i;
        String e = LoginHelper.a().e();
        stringBuffer.append(BaseJsInterface.JS_PREFIX).append(str + "('");
        stringBuffer.append(String.format("{\"userid\":\"%s\",\"jumpkey\":\"%s\",\"sessionid\":\"%s\",\"nickname\":\"%s\",\"username\":\"%s\"}", new Object[]{Long.valueOf(j), str2, str3, str4, e}));
        stringBuffer.append("')");
        return stringBuffer.toString();
    }

    protected void onResume() {
        super.onResume();
        if (this.d && !this.e) {
            LoginHelper.a();
            if (LoginHelper.c()) {
                this.d = false;
                if (this.f.equals(com.umeng.a.d)) {
                    b.a("http://wap.yuancheng.xunlei.com/index.html");
                } else {
                    b.a(b(this.f));
                }
            }
        }
        if (DownloadService.a() == null) {
            DownloadService.a(new f(this));
        }
        LoginHelper.a();
        if (!LoginHelper.c()) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(String.format("javascript:%s()", new Object[]{this.g}));
            b.a(stringBuffer.toString());
        }
    }

    public void onPause() {
        DownloadService.a().c(this.m);
        super.onPause();
    }

    public void onBackPressed() {
        if (b.c) {
            b.f();
        }
        if (ThunderWebView.getCurrentUrl().equals("http://wap.yuancheng.xunlei.com/index.html")) {
            this.h.clear();
            finish();
            return;
        }
        if (this.h.size() <= 1 || !b.d()) {
            this.c = true;
        } else {
            this.h.remove(this.h.size() - 1);
            this.c = false;
            b.e();
        }
        if (this.c) {
            this.h.clear();
            finish();
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i2 == 6 && i == 5 && !TextUtils.isEmpty(this.f)) {
            String stringExtra = intent.getStringExtra("bind_key");
            b.a(String.format("javascript:%s('%s')", new Object[]{this.f, stringExtra}));
        }
    }

    protected void onDestroy() {
        LoginHelper.a().b(this.i);
        LoginHelper.a().b(this.j);
        this.m.removeMessages(JsInterface.MSG_JS_GOTO_LOGIN_PAGE_AND_CALLBACK);
        this.m.removeMessages(JsInterface.MSG_JS_GO_TO_DOWNLOAD_LIST);
        this.m.removeMessages(JsInterface.MSG_JS_INSTALL_APK);
        this.m.removeMessages(JsInterface.MSG_JS_GO_TO_USER_INFO);
        super.onDestroy();
    }

    static /* synthetic */ void a(RemoteDownloadActivity remoteDownloadActivity, String str) {
        new StringBuilder().append(remoteDownloadActivity.getClass()).append("---hanldeLoginAndCallback---json---").append(str).append(Thread.currentThread().getId());
        if (str != null && !str.equals(com.umeng.a.d)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                remoteDownloadActivity.f = jSONObject.getString(com.alipay.sdk.authjs.a.c);
                new StringBuilder().append(remoteDownloadActivity.getClass()).append("---hanldeLoginAndCallback---which---").append(new StringBuilder("_").append(jSONObject.getString("which")).toString()).append("---").append(Thread.currentThread().getId());
                LoginHelper.a().a((Context) remoteDownloadActivity, remoteDownloadActivity.k, (int) XZBDevice.DOWNLOAD_LIST_RECYCLE, remoteDownloadActivity.getClass().getSimpleName());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    static /* synthetic */ void a(TaskInfo taskInfo) {
        if (taskInfo != null) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(String.format("javascript:IClient.addTaskSuccessBk('{\"url\":\"%s\",\"name\":\"%s\"}')", new Object[]{taskInfo.mUrl, taskInfo.mFileName}));
            b.a(stringBuffer.toString());
        }
    }
}
