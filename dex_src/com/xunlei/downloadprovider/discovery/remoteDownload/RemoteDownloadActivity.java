package com.xunlei.downloadprovider.discovery.remoteDownload;

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
import com.xunlei.downloadprovider.web.core.ThunderWebView;
import com.xunlei.downloadprovider.web.core.p;
import com.xunlei.xiazaibao.BuildConfig;
import com.xunlei.xllib.R;
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
        this.f = BuildConfig.VERSION_NAME;
        this.g = BuildConfig.VERSION_NAME;
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
        setContentView(com.xunlei.downloadprovider.R.layout.remote_download_layout);
        StatReporter.reportEnterRemoteDownload(LoginHelper.a().j);
        ThunderWebView thunderWebView = (ThunderWebView) findViewById(com.xunlei.downloadprovider.R.id.remote_download_webview);
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
        stringBuffer.append("javascript:").append(str + "('");
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
                if (this.f.equals(BuildConfig.VERSION_NAME)) {
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
        this.m.removeMessages(1042);
        this.m.removeMessages(1046);
        this.m.removeMessages(1047);
        this.m.removeMessages(1048);
        super.onDestroy();
    }

    static /* synthetic */ void a(RemoteDownloadActivity remoteDownloadActivity, String str) {
        new StringBuilder().append(remoteDownloadActivity.getClass()).append("---hanldeLoginAndCallback---json---").append(str).append(Thread.currentThread().getId());
        if (str != null && !str.equals(BuildConfig.VERSION_NAME)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                remoteDownloadActivity.f = jSONObject.getString("callback");
                new StringBuilder().append(remoteDownloadActivity.getClass()).append("---hanldeLoginAndCallback---which---").append(new StringBuilder("_").append(jSONObject.getString("which")).toString()).append("---").append(Thread.currentThread().getId());
                LoginHelper.a().a(remoteDownloadActivity, remoteDownloadActivity.k, R.styleable.Toolbar_titleMarginTop, remoteDownloadActivity.getClass().getSimpleName());
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
