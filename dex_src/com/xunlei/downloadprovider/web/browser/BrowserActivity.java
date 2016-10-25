package com.xunlei.downloadprovider.web.browser;

import android.content.ContentValues;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.AutoScrollHelper;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import com.tencent.open.SocialConstants;
import com.tencent.open.utils.SystemUtils;
import com.tencent.tauth.Tencent;
import com.xunlei.common.member.XLErrorCode;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.downloadprovider.download.center.DownloadCenterActivity;
import com.xunlei.downloadprovider.download.report.DLCenterEntry;
import com.xunlei.downloadprovider.frame.MainTabActivity;
import com.xunlei.downloadprovider.loading.k;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.model.o;
import com.xunlei.downloadprovider.model.p;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.Sniff;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.Sniff.ClickType;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.Sniff.SniffStartFrom;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g;
import com.xunlei.downloadprovider.service.DownloadService;
import com.xunlei.downloadprovider.service.downloads.TaskInfo;
import com.xunlei.downloadprovider.task.ThunderTask;
import com.xunlei.downloadprovider.url.DownData;
import com.xunlei.downloadprovider.util.sniff.f;
import com.xunlei.downloadprovider.vod.VodUtil;
import com.xunlei.downloadprovider.vod.ap;
import com.xunlei.downloadprovider.vod.protocol.VodSourceType;
import com.xunlei.downloadprovider.vod.protocol.VodVideoFormat;
import com.xunlei.downloadprovider.vod.protocol.h;
import com.xunlei.downloadprovider.web.BrowserUtil.StartFromType;
import com.xunlei.downloadprovider.web.sniff.SnifferResultsFragment;
import com.xunlei.tdlive.R;
import com.xunlei.thundersniffer.context.ClientInfo;
import com.xunlei.thundersniffer.context.ThunderSnifferContext;
import com.xunlei.thundersniffer.sniff.SniffingPageResource;
import com.xunlei.thundersniffer.sniff.SniffingResource;
import com.xunlei.thundersniffer.sniff.SniffingResourceGroup;
import com.xunlei.thundersniffer.sniff.sniffer.ThunderSniffer;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.android.agoo.message.MessageService;
import org.android.spdy.SpdyAgent;

public class BrowserActivity extends ThunderTask implements com.xunlei.downloadprovider.web.browser.BrowserTitleBarFragment.b, com.xunlei.downloadprovider.web.browser.BrowserToolBarFragment.b {
    public static final String a;
    private SniffingPageResource A;
    private String B;
    private String C;
    private boolean D;
    public Handler b;
    b c;
    d d;
    boolean e;
    protected boolean f;
    private DownloadListener g;
    private com.xunlei.downloadprovider.web.browser.a.d h;
    private WebView i;
    private ViewGroup j;
    private BrowserTitleBarFragment k;
    private BrowserToolBarFragment l;
    private boolean m;
    private boolean n;
    private InputAutoCompleteView o;
    private WebChromeClient p;
    private com.xunlei.downloadprovider.a.h.a q;
    private Handler r;
    private SnifferResultsFragment s;
    private com.xunlei.downloadprovider.web.sniff.SnifferResultsFragment.d t;
    private c u;
    private BrowserSniffer v;
    private WebViewClient w;
    private a x;
    private PopupWindow y;
    private String z;

    static class a {
        View a;
        View b;
        ImageView c;
        TextView d;
        TextView e;
        String f;
        OnClickListener g;

        a() {
            this.a = null;
            this.b = null;
            this.c = null;
            this.d = null;
            this.e = null;
        }

        public final void a(int i) {
            if (this.a != null) {
                this.a.setVisibility(i);
            }
        }

        public final void b(int i) {
            if (this.c != null) {
                this.c.setImageResource(i);
            }
        }

        public final void a(int i, int i2) {
            if (this.d != null) {
                this.d.setText(i);
            }
            if (this.e == null) {
                return;
            }
            if (i2 == 0) {
                this.e.setVisibility(XZBDevice.Wait);
                return;
            }
            this.e.setText(i2);
            this.e.setVisibility(0);
        }
    }

    private class b implements com.xunlei.downloadprovider.a.h.a {
        private b() {
        }

        public final void a(Message message) {
            switch (message.what) {
                case Tencent.REQUEST_LOGIN:
                    if (BrowserActivity.this.v != null) {
                        BrowserActivity.this.v.c();
                    }
                case 10002:
                    BrowserActivity.this.a(message.arg1, (String) message.obj);
                default:
                    break;
            }
        }
    }

    class c implements com.xunlei.downloadprovider.web.browser.BrowserSniffer.a, com.xunlei.downloadprovider.web.sniff.SnifferResultsFragment.b {
        c() {
        }

        public final void a() {
            if (com.xunlei.xllib.a.b.a(BrowserActivity.this)) {
                BrowserActivity.this.l.c(true);
            }
        }

        private void e() {
            BrowserActivity.this.l.c(false);
        }

        public final void a(BrowserSniffer browserSniffer, com.xunlei.downloadprovider.web.browser.BrowserSniffer.c cVar, Object obj) {
            String str = a;
            new StringBuilder("onSnifferFinishSniffing: ").append(browserSniffer.d);
            if (!(BrowserActivity.this.i == null || BrowserActivity.this.i.getSettings() == null)) {
                BrowserActivity.this.i.getSettings().setBlockNetworkImage(false);
            }
            BrowserActivity.this.l.c(true);
            BrowserActivity.this.A = cVar.b;
            if (BrowserActivity.this.t != null) {
                BrowserActivity.this.t.a(BrowserActivity.this, cVar.b);
            }
        }

        public final void a(BrowserSniffer browserSniffer, float f, Object obj) {
            if (BrowserActivity.this.s != null && BrowserActivity.this.t != null) {
                BrowserActivity.this.t.a(f);
            }
        }

        public final void a(BrowserSniffer browserSniffer, com.xunlei.downloadprovider.web.browser.BrowserSniffer.b bVar, Object obj) {
            SniffingResourceGroup sniffingResourceGroup = BrowserActivity.this;
            String str = a;
            new StringBuilder("3 -- add group data --> resourceGroup.realUrl --> ").append(sniffingResourceGroup.realUrl).append(", resourceGroup.resources.size() --> ").append(sniffingResourceGroup.resources.size());
            if (BrowserActivity.this.isSniffingFromSearchPage() && BrowserActivity.this.t != null) {
                BrowserActivity.this.t.a(browserSniffer.i != null ? browserSniffer.i.getProgress() : AutoScrollHelper.RELATIVE_UNSPECIFIED);
                BrowserActivity.this.t.a(BrowserActivity.this, BrowserActivity.this.i.getUrl());
            }
        }

        public final void a(BrowserSniffer browserSniffer, Object obj) {
            String str = a;
            new StringBuilder("onSnifferStartSniffing: ").append(browserSniffer.d);
            if (BrowserActivity.this.t != null) {
                BrowserActivity.this.t.c();
            }
        }

        public final void b(BrowserSniffer browserSniffer, Object obj) {
            String str = a;
            new StringBuilder("onSnifferStartSniffing: ").append(browserSniffer.d);
            e();
            if (BrowserActivity.this.t != null) {
                BrowserActivity.this.t.b();
            }
        }

        public final void c(BrowserSniffer browserSniffer, Object obj) {
            String str = a;
            new StringBuilder("onBrowserSnifferReStart: ").append(browserSniffer.d);
            e();
            if (BrowserActivity.this.t != null) {
                BrowserActivity.this.t.a(true);
                BrowserActivity.this.t.b();
            }
        }

        public final void d(BrowserSniffer browserSniffer, Object obj) {
            if (BrowserActivity.this.t != null) {
                BrowserActivity.this.t.a();
            }
            a();
            BrowserActivity.this.i.getSettings().setBlockNetworkImage(false);
        }

        public final void a(SniffingResource sniffingResource) {
            BrowserActivity.b(BrowserActivity.this, sniffingResource);
        }

        public final void b(SniffingResource sniffingResource) {
            BrowserActivity.c(BrowserActivity.this, sniffingResource);
        }

        public final void a(int i, SniffingResourceGroup sniffingResourceGroup) {
            if (sniffingResourceGroup != null && !TextUtils.isEmpty(sniffingResourceGroup.realUrl)) {
                BrowserActivity.this.l.c(true);
                String a = BrowserActivity.this.a();
                int i2 = i + 1;
                String str = "sniff_3_folder_click";
                g a2 = g.a("android_sniff", str, str);
                str = "sniff_processid";
                if (a == null) {
                    a = com.umeng.a.d;
                }
                Sniff.a(a2.a(str, a, 1).b("position", (long) i2));
                a = a;
                new StringBuilder("Sniffer ResourceGroup: ").append(sniffingResourceGroup.realUrl).append(" AntiSniff: ").append(sniffingResourceGroup.isAntiSniff());
                if (sniffingResourceGroup.isAntiSniff()) {
                    if (BrowserActivity.this.v.g) {
                        BrowserActivity.this.v.c();
                    }
                    if (BrowserActivity.this.h.m()) {
                        BrowserActivity.this.v.p = true;
                    }
                    BrowserActivity.this.v.q = true;
                    BrowserActivity.this.v.a(BrowserActivity.this.i, sniffingResourceGroup.realUrl, SniffStartFrom.webpv, false);
                    if (BrowserActivity.this.t != null) {
                        BrowserActivity.this.t.a(true);
                    }
                    e();
                }
                BrowserActivity.b(BrowserActivity.this, sniffingResourceGroup.realUrl);
            }
        }

        public final void a(float f) {
            int height = BrowserActivity.this.l.getView().getHeight();
            if (height != 0) {
                BrowserActivity.this.l.getView().setVisibility(0);
            }
            BrowserActivity.this.l.getView().scrollTo(0, -((int) (((float) height) * (2.0f * f))));
        }

        public final void a(String str, String str2) {
            e();
            String a = BrowserActivity.this.a();
            ClickType clickType = ClickType.word;
            String str3 = "sniff_3_rec_click";
            g a2 = g.a("android_sniff", str3, str3);
            str3 = "sniff_processid";
            if (a == null) {
                a = com.umeng.a.d;
            }
            a2 = a2.a(str3, a, 1);
            str3 = "click_type";
            a = com.umeng.a.d;
            switch (AnonymousClass_1.c[clickType.ordinal()]) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    a = "link";
                    break;
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    a = "word";
                    break;
            }
            Sniff.a(a2.a(str3, a, (int) XZBDevice.DOWNLOAD_LIST_FAILED).a("word", str == null ? com.umeng.a.d : str).a("suffix", str2 == null ? com.umeng.a.d : str2, (int) XZBDevice.DOWNLOAD_LIST_FAILED));
            if (!(str2 == null || str == null)) {
                str = str + " " + str2;
            }
            BrowserActivity.this.a(str, true, SniffStartFrom.sniff_suffix_rec);
        }

        public final void b() {
            e();
            if (BrowserActivity.this.h.m()) {
                BrowserActivity.this.v.p = true;
            }
            BrowserActivity.this.v.a(BrowserActivity.this.i, BrowserActivity.this.h.g(), SniffStartFrom.webpv, false);
            SnifferResultsFragment e = BrowserActivity.this.s;
            e.c.a(BrowserActivity.this.h.g());
            BrowserActivity.l(BrowserActivity.this);
        }

        public final void a(boolean z) {
            if (z) {
                BrowserActivity.this.l.getView().setVisibility(0);
                BrowserActivity.this.l.getView().scrollTo(0, 0);
                return;
            }
            int height = BrowserActivity.this.l.getView().getHeight();
            if (height == 0) {
                BrowserActivity.this.l.getView().setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
                return;
            }
            BrowserActivity.this.l.getView().setVisibility(0);
            BrowserActivity.this.l.getView().scrollTo(0, -(height * 2));
        }

        public final void a(int i, int i2) {
            Map hashMap = new HashMap();
            hashMap.put(Integer.valueOf(XZBDevice.Stop), "min");
            hashMap.put(Integer.valueOf(XZBDevice.Success), "medium");
            hashMap.put(Integer.valueOf(XZBDevice.Fail), "max");
            if (!(i == i2 || i2 == 0)) {
                String str;
                if (BrowserActivity.this.v.g) {
                    str = "on";
                } else {
                    str = "off";
                }
                String str2 = (String) hashMap.get(Integer.valueOf(i2));
                String str3 = (String) hashMap.get(Integer.valueOf(i));
                String str4;
                if (i > i2) {
                    str4 = "sniff_7_up";
                    Sniff.a(g.a("android_sniff", str4, str4).a(Impl.COLUMN_STATUS, str, (int) XZBDevice.DOWNLOAD_LIST_FAILED).a("from_stat", str2, (int) XZBDevice.DOWNLOAD_LIST_FAILED).a("to_stat", str3, (int) XZBDevice.DOWNLOAD_LIST_FAILED));
                } else {
                    str4 = "sniff_7_down";
                    Sniff.a(g.a("android_sniff", str4, str4).a(Impl.COLUMN_STATUS, str, (int) XZBDevice.DOWNLOAD_LIST_FAILED).a("from_stat", str2, (int) XZBDevice.DOWNLOAD_LIST_FAILED).a("to_stat", str3, (int) XZBDevice.DOWNLOAD_LIST_FAILED));
                }
            }
            if (i == 10) {
                BrowserActivity.this.c();
                BrowserActivity.this.k.c();
            }
        }

        public final void c() {
            if (BrowserActivity.this.h.c()) {
                BrowserActivity.this.k.a();
                BrowserActivity.this.v.c();
                BrowserActivity.this.t.b(BrowserActivity.this.h.i());
                BrowserActivity.this.h.d();
                return;
            }
            BrowserActivity.this.v.c();
            BrowserActivity.this.h.a();
            BrowserActivity.this.finish();
        }

        public final void b(boolean z) {
            BrowserActivity.this.l.d(z);
        }

        public final void d() {
            BrowserActivity.this.k.c();
        }
    }

    static class d {
        String a;
        boolean b;
        boolean c;
        boolean d;
        int e;
        StartFromType f;
        boolean g;
        String h;

        d() {
            this.b = false;
            this.c = false;
            this.d = false;
            this.e = 0;
            this.g = false;
        }
    }

    public BrowserActivity() {
        int a = k.a();
        if (f.d() < a) {
            f.a(false);
        }
        f.b(a);
        this.c = new b();
        this.d = new d();
        this.g = new a(this);
        this.h = new com.xunlei.downloadprovider.web.browser.a.d();
        this.e = false;
        this.m = false;
        this.n = false;
        this.p = new c(this);
        this.q = new d(this);
        this.r = new com.xunlei.downloadprovider.a.h.b(this.q);
        this.u = new c();
        this.v = new BrowserSniffer();
        this.w = new e(this);
        this.x = new a();
        this.f = false;
        this.B = com.umeng.a.d;
        this.C = com.umeng.a.d;
    }

    static {
        a = BrowserActivity.class.getSimpleName();
    }

    private void e() {
        if (this.n) {
            DownloadCenterActivity.a((Context) this, "fromBrowser");
            finish();
        }
    }

    public boolean handleTaskOperator(int i, int i2, long j, TaskInfo taskInfo) {
        if (this.s == null) {
            return false;
        }
        boolean z;
        SnifferResultsFragment snifferResultsFragment = this.s;
        if (snifferResultsFragment.b == null || snifferResultsFragment.b.getVisibility() != 0 || snifferResultsFragment.d == null || snifferResultsFragment.a.getVisibilityState() == 10) {
            z = false;
        } else {
            Object obj = 1;
        }
        return !z ? super.handleTaskOperator(i, i2, j, taskInfo) : false;
    }

    public void onClickDialogCancel() {
        super.onClickDialogCancel();
        e();
    }

    public final void a(int i, String str) {
        if (this.i != null) {
            this.i.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
        }
        this.x.f = str;
        this.x.a(0);
        if (i != 404) {
            this.x.b(R.drawable.bg_invalid_network);
            this.x.a(R.string.invalid_network, 2131231032);
        } else {
            this.x.b(R.drawable.bg_page_gone);
            this.x.a(2131232764, 0);
        }
        if (this.x.b != null) {
            this.x.b.requestFocus();
        }
    }

    public final void a() {
        this.x.a(XZBDevice.DOWNLOAD_LIST_ALL);
        if (this.i != null) {
            this.i.setVisibility(0);
        }
    }

    public final void b() {
        if (this.y != null) {
            this.y.dismiss();
            this.y = null;
        }
    }

    public final void c() {
        if (!this.k.b()) {
            this.f = false;
            if (!com.xunlei.downloadprovider.util.aa.a.b(this, "browser_sniff_guide_showed", false)) {
                View inflate = ((LayoutInflater) getSystemService("layout_inflater")).inflate(2130968808, null);
                View findViewById = inflate.findViewById(2131756186);
                if (findViewById != null) {
                    findViewById.setOnClickListener(new h(this));
                }
                inflate.setOnClickListener(new j(this));
                try {
                    this.y = new PopupWindow(inflate, -1, -1);
                    this.y.showAtLocation(getWindow().getDecorView().findViewById(16908290), 0, 0, 0);
                    com.xunlei.downloadprovider.util.aa.a.a(this, "browser_sniff_guide_showed", true);
                } catch (Exception e) {
                    this.y = null;
                }
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.b = new com.xunlei.downloadprovider.a.h.b(this.c);
        setContentView(2130968605);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.d.g = extras.getBoolean("zoom", true);
            this.d.g = true;
            this.d.a = extras.getString(SocialConstants.PARAM_URL);
            this.d.b = extras.getBoolean("intent_key_need_auto_sniff", false);
            this.d.c = extras.getBoolean("intent_key_need_smart_prefix_sniff", false);
            this.d.e = extras.getInt("first_entry", 0);
            this.d.h = extras.getString(com.xunlei.downloadprovider.thirdpart.a.a);
            this.d.d = extras.getBoolean("asDownloader", false);
            if (this.d.d) {
                this.d.b = false;
            }
            SniffStartFrom sniffStartFrom = (SniffStartFrom) getIntent().getSerializableExtra("intent_key_sniff_start_from");
            if (sniffStartFrom != null) {
                if (sniffStartFrom.equals(SniffStartFrom.download_detail_search_agin) || sniffStartFrom.equals(SniffStartFrom.download_detail_web)) {
                    this.D = true;
                    this.B = getIntent().getStringExtra("download_detail_key_word");
                    this.C = getIntent().getStringExtra("download_detail_resource_ref_page_url");
                }
            }
            try {
                StartFromType startFromType = (StartFromType) extras.getSerializable("intent_key_start_from");
                d dVar = this.d;
                if (startFromType == null) {
                    startFromType = StartFromType.unknow;
                }
                dVar.f = startFromType;
            } catch (Exception e) {
                this.d.f = StartFromType.unknow;
            }
        }
        BrowserSniffer browserSniffer = this.v;
        Context applicationContext = getApplicationContext();
        ClientInfo clientInfo = new ClientInfo();
        clientInfo.versionCode = com.xunlei.downloadprovider.a.b.x();
        clientInfo.version = com.xunlei.downloadprovider.a.b.w();
        clientInfo.productId = com.xunlei.downloadprovider.a.b.h();
        clientInfo.channelId = com.xunlei.downloadprovider.a.b.g();
        ThunderSnifferContext.setClientInfo(clientInfo);
        browserSniffer.i = new ThunderSniffer(applicationContext);
        browserSniffer.i.setSnifferListener(browserSniffer.j);
        browserSniffer.i.getSettings().setSniffingLogEnabled(false);
        browserSniffer.i.getSettings().setXunleiVodplayEnabled(false);
        browserSniffer.i.setSniffingFilter(f.a(applicationContext));
        browserSniffer.e = applicationContext;
        browserSniffer.c = true;
        this.v.a(this.u);
        this.k = (BrowserTitleBarFragment) getSupportFragmentManager().findFragmentById(2131755193);
        this.o = (InputAutoCompleteView) findViewById(2131755199);
        a aVar = this.k.e;
        aVar.b = this.o;
        if (aVar.b != null) {
            ViewGroup viewGroup = aVar.b;
            viewGroup.h = aVar.d.k;
            viewGroup.k = ((LayoutInflater) viewGroup.g.getSystemService("layout_inflater")).inflate(2130968634, viewGroup);
            ((LinearLayout) viewGroup.k.findViewById(2131755342)).setOnTouchListener(new ad(viewGroup));
            viewGroup.a();
            viewGroup.e.addAll(viewGroup.d);
            viewGroup.f = new a(viewGroup.g);
            viewGroup.i = (ListView) viewGroup.findViewById(2131755343);
            viewGroup.i.setOnTouchListener(new ae(viewGroup));
            viewGroup.i.setAdapter(viewGroup.f);
            viewGroup.h.setOnKeyListener(new af(viewGroup));
            viewGroup.j = new ArrayList();
            viewGroup.j.add(viewGroup.g.getString(2131231654));
            aVar.b.setUIClient(new ac(aVar));
        }
        this.l = (BrowserToolBarFragment) getSupportFragmentManager().findFragmentById(2131755198);
        this.l.a(false);
        this.l.b(false);
        this.l.c(true);
        this.l.a.a = new k(this);
        this.j = (ViewGroup) findViewById(2131755194);
        this.i = (WebView) findViewById(2131755196);
        WebView webView = this.i;
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setDatabasePath(getApplicationContext().getDir("database", 0).getPath());
        settings.setCacheMode(-1);
        settings.setLoadWithOverviewMode(true);
        settings.setRenderPriority(RenderPriority.HIGH);
        settings.setBlockNetworkImage(true);
        settings.setAppCacheEnabled(true);
        settings.setCacheMode(XZBDevice.DOWNLOAD_LIST_RECYCLE);
        settings.setDefaultTextEncodingName("GBK");
        settings.setUserAgentString(new StringBuilder("Android.Thunder.").append(settings.getUserAgentString()).toString());
        if (this.d.g) {
            settings.setSupportZoom(true);
            settings.setBuiltInZoomControls(true);
            settings.setDisplayZoomControls(false);
        }
        webView.setScrollBarStyle(XLErrorCode.OAUTH_FAILED);
        webView.setFocusableInTouchMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.requestFocus();
        this.h.a(webView);
        this.h.a(this.g);
        this.h.a(this.p);
        this.h.a(this.w);
        a aVar2 = this.x;
        View findViewById = findViewById(2131755195);
        aVar2.a = findViewById;
        aVar2.b = findViewById.findViewById(2131755659);
        aVar2.c = (ImageView) findViewById.findViewById(2131755656);
        aVar2.d = (TextView) findViewById.findViewById(2131755657);
        aVar2.e = (TextView) findViewById.findViewById(2131755658);
        if (aVar2.b != null) {
            aVar2.b.setOnClickListener(new n(aVar2));
        }
        this.x.g = new l(this);
        this.s = (SnifferResultsFragment) getSupportFragmentManager().findFragmentById(2131755197);
        this.s.getView().setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
        this.s.e = this.u;
        this.t = this.s.h;
        browserSniffer = this.v;
        WebView webView2 = this.i;
        if (webView2 != null) {
            webView2.addJavascriptInterface(browserSniffer.m, ThunderSniffer.JSINTERFACE_NAMESPACE);
        }
        f();
    }

    protected void onResume() {
        super.onResume();
        new StringBuilder("onResume: ").append(getTaskId());
        if (VERSION.SDK_INT >= 11 && this.i != null) {
            this.i.onResume();
        }
    }

    protected void onPause() {
        if (VERSION.SDK_INT >= 11 && this.i != null) {
            this.i.onPause();
        }
        new StringBuilder("onPause: ").append(getTaskId());
        super.onPause();
    }

    protected void onDestroy() {
        new StringBuilder("onDestroy: ").append(getTaskId());
        if (this.h != null) {
            this.h.a();
            this.h.l();
        }
        if (this.v != null) {
            WebView webView = this.i;
            if (webView != null && VERSION.SDK_INT >= 11) {
                webView.removeJavascriptInterface(ThunderSniffer.JSINTERFACE_NAMESPACE);
            }
            BrowserSniffer browserSniffer = this.v;
            if (browserSniffer.i != null) {
                browserSniffer.i.cancelSniffing();
                browserSniffer.i.setSnifferListener(null);
            }
            browserSniffer.b().clear();
            browserSniffer.c = false;
        }
        if (this.x != null) {
            a aVar = this.x;
            aVar.a = null;
            aVar.b = null;
        }
        if (this.i != null) {
            try {
                if (this.j != null) {
                    this.j.removeView(this.i);
                }
                this.i.removeAllViews();
                this.i.destroy();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (this.y != null) {
            this.y.dismiss();
        }
        super.onDestroy();
    }

    private void f() {
        SniffStartFrom sniffStartFrom = null;
        Object obj = this.d.a;
        if (this.d.d) {
            b(new DownData(null, obj, com.umeng.a.d), "browser/browser");
            StatReporter.reportOverallDownload("browser");
            return;
        }
        if (this.d.b) {
            sniffStartFrom = (SniffStartFrom) getIntent().getSerializableExtra("intent_key_sniff_start_from");
            if (sniffStartFrom == null) {
                switch (AnonymousClass_1.b[this.d.f.ordinal()]) {
                    case SpdyAgent.ACCS_ONLINE_SERVER:
                    case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    case XZBDevice.DOWNLOAD_LIST_FAILED:
                        sniffStartFrom = SniffStartFrom.detail;
                        break;
                    case XZBDevice.DOWNLOAD_LIST_ALL:
                    case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                        sniffStartFrom = SniffStartFrom.search_result;
                        break;
                    case R.styleable.Toolbar_contentInsetEnd:
                        sniffStartFrom = SniffStartFrom.hot_video;
                        break;
                    case R.styleable.Toolbar_contentInsetLeft:
                        sniffStartFrom = SniffStartFrom.download_detail_web;
                        break;
                    case XZBDevice.Wait:
                        sniffStartFrom = SniffStartFrom.download_detail_search_agin;
                        break;
                    default:
                        sniffStartFrom = SniffStartFrom.webpv;
                        break;
                }
            } else if (sniffStartFrom.equals(SniffStartFrom.download_detail_search_agin) || sniffStartFrom.equals(SniffStartFrom.download_detail_web)) {
                this.D = true;
                this.B = getIntent().getStringExtra("download_detail_key_word");
                this.C = getIntent().getStringExtra("download_detail_resource_ref_page_url");
            }
        } else {
            this.f = true;
        }
        if (!TextUtils.isEmpty(obj)) {
            a(obj, this.d.b, sniffStartFrom, true);
        }
    }

    private void a(String str, boolean z, SniffStartFrom sniffStartFrom) {
        a(str, z, sniffStartFrom, false);
    }

    private void a(String str, boolean z, SniffStartFrom sniffStartFrom, boolean z2) {
        String substring;
        if (!str.startsWith("thunder://")) {
            if (!str.startsWith("ed2k://")) {
                if (!str.startsWith("http://thunder://")) {
                    if (!str.startsWith("http://ed2k://")) {
                        String g;
                        if (com.xunlei.downloadprovider.util.c.a.f(str)) {
                            g = com.xunlei.downloadprovider.util.c.a.g(str);
                        } else {
                            f.a();
                            g = f.b(str);
                        }
                        if (z) {
                            this.h.k().a = true;
                            if (this.h.m()) {
                                this.v.p = true;
                            }
                            this.v.a.a = false;
                            this.v.a(this.i, g, sniffStartFrom, z2);
                            if (this.t != null) {
                                this.t.a(true);
                            }
                        } else if (this.t != null) {
                            this.t.a(false);
                            this.k.c();
                        }
                        new StringBuilder("loadOnSearch: ").append(str).append(" ").append(g);
                        this.k.a(g, true);
                        this.k.a();
                        this.h.f = true;
                        this.h.a(g);
                        this.h.j();
                        return;
                    }
                }
            }
        }
        if (str.startsWith("http://")) {
            substring = str.substring(R.styleable.Toolbar_contentInsetLeft);
        } else {
            substring = str;
        }
        createLocalTask(substring, null, 0, null, null, null, 1, new com.xunlei.downloadprovider.model.g(3, substring, null), this.r, false);
        if (!this.h.c()) {
            finish();
        }
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.translate_between_interface_left_in, R.anim.translate_between_interface_right_out);
    }

    public final void a(boolean z) {
        if (z) {
            b();
            this.k.a = this.h.g();
            return;
        }
        this.k.a(this.h.g());
    }

    public void onBrowserTitleBarBackButtonClick(View view) {
        com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.b.a("browser_exit");
        if (this.d.e == 43) {
            MainTabActivity.a((Context) this, "thunder");
            finish();
            return;
        }
        if (this.d.e == 42) {
            if (TextUtils.isEmpty(this.d.h)) {
                MainTabActivity.a((Context) this, "thunder");
            } else if ("shortcut_download".equals(this.d.h)) {
                DownloadCenterActivity.a((Context) this, DLCenterEntry.browser.toString());
            }
        }
        this.v.c();
        finish();
    }

    public final void a(String str) {
        if (TextUtils.isEmpty(str)) {
            Toast.makeText(this, 2131230900, 0).show();
            return;
        }
        this.u.a();
        if (com.xunlei.downloadprovider.util.c.a.f(str)) {
            a(str, false, null);
        } else {
            a(str, true, SniffStartFrom.browser_word);
        }
    }

    public void onBrowserRefreshButtonClick(View view) {
        com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.b.a("browser_fresh");
        if (this.h != null) {
            this.k.a();
            this.v.c();
            this.h.b();
            this.h.j();
            if (this.t != null) {
                this.t.a(this.h.g());
            }
        }
    }

    public void onBrowserToolBarButtonClick(View view) {
        switch (view.getId()) {
            case 2131755986:
                d();
            default:
                break;
        }
    }

    public final void d() {
        this.v.a.a = true;
        this.h.k().a = true;
        if (!this.v.g) {
            if (this.e) {
                this.v.a(this.i, this.h.g(), SniffStartFrom.webpv, false);
            } else {
                this.v.a(this.i, this.h.g(), SniffStartFrom.webpv);
            }
        }
        if (this.t != null) {
            this.t.a(true);
        }
    }

    public void onBrowserGoBackButtonClick(View view) {
        com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.b.a("browser_back");
        if (this.h != null) {
            this.k.a();
            this.v.c();
            this.t.b(this.h.i());
            this.h.f = true;
            this.h.d();
        }
    }

    public void onBrowserStopButtonClick(View view) {
        com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.b.a("browser_stopfresh");
        if (this.h != null) {
            this.v.c();
            this.h.a();
            this.k.b(false);
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4 && keyEvent.getRepeatCount() == 0) {
            this.h.a();
            if (this.h.c()) {
                this.k.a();
                if (this.v.g) {
                    this.v.c();
                }
                this.l.c(true);
                this.t.b(this.h.i());
                this.h.d();
                com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.b.a("browser_back");
                return true;
            }
        }
        return super.onKeyDown(i, keyEvent);
    }

    public void onBrowserGoForwardButtonClick(View view) {
        com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.b.a("browser_forward");
        if (this.h != null) {
            this.k.a();
            this.v.c();
            if (this.t != null) {
                this.t.a(this.h.i());
            }
            this.h.f = true;
            this.h.f();
        }
    }

    public final void a(DownData downData, String str) {
        if (downData != null) {
            com.xunlei.downloadprovider.model.g gVar = new com.xunlei.downloadprovider.model.g(5, downData.e, downData.s);
            gVar.d = str;
            createTask(downData, this.r, gVar, false);
            StatReporter.reportBrowserCollectPageDownload();
        }
    }

    protected void onCreateTask(boolean z, int i) {
        if (this.k != null) {
            BrowserTitleBarFragment browserTitleBarFragment = this.k;
            if (browserTitleBarFragment.c != null) {
                browserTitleBarFragment.c.a(false);
            }
        }
        if (this.d.d) {
            finish();
        } else {
            super.onCreateTask(z, i);
        }
    }

    public final void b(DownData downData, String str) {
        if (DownloadService.a() != null) {
            a(downData, str);
        } else {
            DownloadService.a(new m(this, downData, str));
        }
    }

    private void a(SniffingResource sniffingResource) {
        String str;
        DownData downData = new DownData();
        if (this.D) {
            downData.B = this.B;
            downData.C = this.C;
        } else {
            Object obj;
            str = com.umeng.a.d;
            if (this.A != null) {
                str = this.A.getSearchKeyword();
                if (TextUtils.isEmpty(str)) {
                    Object pageUrl = this.A.getPageUrl();
                    if (!TextUtils.isEmpty(pageUrl)) {
                        str = f.d(pageUrl);
                    }
                }
            }
            if (obj.contains(" ")) {
                obj = obj.split("[\\s\\+]")[0];
            }
            if (!TextUtils.isEmpty(sniffingResource.pageTitle) && sniffingResource.pageTitle.contains(obj)) {
                downData.B = obj;
            }
            if (TextUtils.isEmpty(sniffingResource.pageTitle)) {
                downData.C = this.z;
            } else {
                downData.C = sniffingResource.pageTitle;
            }
        }
        new StringBuilder("downloadSniffingResource: sniffKeyWord --> ").append(downData.B).append(", webSiteName --> ").append(downData.C);
        if (sniffingResource.userData instanceof DownData) {
            DownData downData2 = (DownData) sniffingResource.userData;
            downData.a = downData2.a;
            downData.b = downData2.b;
            downData.c = downData2.c;
            downData.d = downData2.d;
            downData.e = downData2.e;
            downData.f = downData2.f;
            downData.g = downData2.g;
            downData.h = downData2.h;
            downData.i = downData2.i;
            downData.j = downData2.j;
            downData.k = downData2.k;
            downData.l = downData2.l;
            downData.m = downData2.m;
            downData.n = downData2.n;
            downData.o = downData2.o;
            downData.p = downData2.p;
            downData.q = downData2.q;
            downData.r = downData2.r;
            downData.s = downData2.s;
            downData.t = downData2.t;
        } else {
            downData.a = sniffingResource.resourceName;
            downData.b = sniffingResource.downloadUrl;
            downData.e = sniffingResource.downloadUrl;
            downData.r = 0;
            downData.s = sniffingResource.sourceUrl;
            if (TextUtils.isEmpty(downData.s)) {
                downData.s = this.h != null ? this.h.g() : com.umeng.a.d;
            }
        }
        DownloadService a = DownloadService.a();
        if (a != null) {
            long a2 = a.a(sniffingResource.downloadUrl);
            if (a2 != -1) {
                DownloadCenterActivity.a(this, a2, "sniff");
                return;
            }
            str = this.v.a.a();
            String str2 = downData.e;
            String str3 = "sniff_4_download";
            g a3 = g.a("android_sniff", str3, str3);
            str3 = "sniff_processid";
            if (str == null) {
                str = com.umeng.a.d;
            }
            Sniff.a(a3.a(str3, str, 1));
            a(downData, "manual/sniff_choose_download");
            StatReporter.reportOverallDownload("sniff_choose_download");
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        if (this.i != null) {
            this.i.stopLoading();
        }
        com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.b.a("browser_exit");
        if (this.d.e == 43) {
            MainTabActivity.a((Context) this, "thunder");
            finish();
            return;
        }
        if (this.d.e == 42) {
            if (TextUtils.isEmpty(this.d.h)) {
                MainTabActivity.a((Context) this, "thunder");
            } else if ("shortcut_download".equals(this.d.h)) {
                DownloadCenterActivity.a((Context) this, DLCenterEntry.browser.toString());
            }
        }
        finish();
    }

    static /* synthetic */ void a(BrowserActivity browserActivity, String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && !str2.contains("about:blank") && !str2.contains(browserActivity.getResources().getString(2131230792))) {
            o oVar = new o();
            oVar.a = str;
            oVar.b = str2;
            if (!TextUtils.isEmpty(oVar.a) && !TextUtils.isEmpty(oVar.b)) {
                p a = p.a();
                try {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("sitename", oVar.a);
                    contentValues.put("urladdr", oVar.b);
                    contentValues.put("downloadcount", Integer.valueOf(oVar.c));
                    contentValues.put("accessTime", Long.valueOf(System.currentTimeMillis()));
                    a.a(contentValues);
                } catch (Exception e) {
                }
            }
        }
    }

    static /* synthetic */ void g(BrowserActivity browserActivity) {
        browserActivity.l.a(browserActivity.h.c());
        browserActivity.l.b(browserActivity.h.e());
        BrowserToolBarFragment browserToolBarFragment = browserActivity.l;
        browserToolBarFragment.a.b(browserActivity.h.g());
    }

    static /* synthetic */ void b(BrowserActivity browserActivity, SniffingResource sniffingResource) {
        if (DownloadService.a() != null) {
            browserActivity.a(sniffingResource);
            StatReporter.reportBrowserCollectSniffDownload();
            return;
        }
        DownloadService.a(new b(browserActivity, sniffingResource));
    }

    static /* synthetic */ void c(BrowserActivity browserActivity, SniffingResource sniffingResource) {
        ap apVar = new ap();
        h hVar = new h();
        hVar.c = sniffingResource.downloadUrl;
        hVar.q = null;
        hVar.o = 1;
        hVar.x = VodVideoFormat.flv;
        hVar.a = sniffingResource.resourceName;
        apVar.b = VodSourceType.sniffing_list;
        apVar.a(hVar);
        VodUtil.a();
        VodUtil.a((Context) browserActivity, apVar);
        String a = browserActivity.v.a.a();
        LoginHelper.a();
        boolean c = LoginHelper.c();
        boolean f = LoginHelper.a().f();
        String str = "sniff_4_play";
        g a2 = g.a("android_sniff", str, str);
        str = "sniff_processid";
        if (a == null) {
            a = com.umeng.a.d;
        }
        Sniff.a(a2.a(str, a, 1).a(SystemUtils.IS_LOGIN, c ? MessageService.MSG_DB_NOTIFY_REACHED : MessageService.MSG_DB_READY_REPORT, (int) XZBDevice.DOWNLOAD_LIST_FAILED).a("is_vip", f ? MessageService.MSG_DB_NOTIFY_REACHED : MessageService.MSG_DB_READY_REPORT, (int) XZBDevice.DOWNLOAD_LIST_FAILED));
    }

    static /* synthetic */ void b(BrowserActivity browserActivity, String str) {
        String g = com.xunlei.downloadprovider.util.c.a.g(str);
        browserActivity.m = false;
        browserActivity.k.a();
        browserActivity.h.a(g);
        browserActivity.h.j();
    }

    static /* synthetic */ void l(BrowserActivity browserActivity) {
        browserActivity.k.a();
        if (browserActivity.m) {
            browserActivity.m = false;
            browserActivity.h.a(browserActivity.h.g());
        } else {
            browserActivity.h.b();
        }
        browserActivity.h.j();
    }
}
