package com.xunlei.downloadprovider.search.ui.search;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import com.android.volley.Request;
import com.android.volley.toolbox.t;
import com.tencent.open.SocialConstants;
import com.xunlei.downloadprovider.app.BaseActivity;
import com.xunlei.downloadprovider.model.protocol.d.a.a;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.Sniff.SniffStartFrom;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.f;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g;
import com.xunlei.downloadprovider.search.b.b;
import com.xunlei.downloadprovider.search.b.c;
import com.xunlei.downloadprovider.search.b.d;
import com.xunlei.downloadprovider.search.b.e;
import com.xunlei.downloadprovider.search.ui.search.SearchActivity.PageType;
import com.xunlei.downloadprovider.search.ui.widget.SearchTitleBar;
import com.xunlei.downloadprovider.web.BrowserUtil;
import com.xunlei.downloadprovider.web.BrowserUtil.StartFromType;
import com.xunlei.downloadprovider.web.base.core.CustomWebView;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

public class SearchActivity extends BaseActivity implements a {
    private SearchTitleBar a;
    private PageType b;
    private SearchMainFixFragment c;
    private SearchAssociativeFragment d;
    private boolean e;
    private CustomWebView f;
    private Handler g;

    static /* synthetic */ class AnonymousClass_1 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[PageType.values().length];
            try {
                a[PageType.SEARCH_ASSOCIATIVE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[PageType.SEARCH_MAIN.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[PageType.SEARCH_RESULT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    public enum PageType {
        NONE,
        SEARCH_MAIN,
        SEARCH_ASSOCIATIVE,
        SEARCH_RESULT;

        static {
            NONE = new com.xunlei.downloadprovider.search.ui.search.SearchActivity.PageType("NONE", 0);
            SEARCH_MAIN = new com.xunlei.downloadprovider.search.ui.search.SearchActivity.PageType("SEARCH_MAIN", 1);
            SEARCH_ASSOCIATIVE = new com.xunlei.downloadprovider.search.ui.search.SearchActivity.PageType("SEARCH_ASSOCIATIVE", 2);
            SEARCH_RESULT = new com.xunlei.downloadprovider.search.ui.search.SearchActivity.PageType("SEARCH_RESULT", 3);
            a = new com.xunlei.downloadprovider.search.ui.search.SearchActivity.PageType[]{NONE, SEARCH_MAIN, SEARCH_ASSOCIATIVE, SEARCH_RESULT};
        }
    }

    public SearchActivity() {
        this.b = PageType.NONE;
        this.e = true;
        this.g = new a(this);
    }

    protected void onCreate(Bundle bundle) {
        Object stringExtra = getIntent().getStringExtra("Keyword");
        if (TextUtils.isEmpty(stringExtra)) {
            getWindow().setSoftInputMode(R.styleable.AppCompatTheme_actionModeShareDrawable);
        } else {
            getWindow().setSoftInputMode(R.styleable.AppCompatTheme_actionModePasteDrawable);
        }
        super.onCreate(bundle);
        setContentView(2130968940);
        this.a = (SearchTitleBar) findViewById(2131755350);
        this.a.b = new b(this);
        this.a.setOnEditorActionListener(new c(this));
        this.a.setEditClickListener(new d(this));
        this.a.setCancelListener(new e(this));
        this.a.setDeleteClickListener(new f(this));
        Object obj = com.xunlei.downloadprovider.model.protocol.d.a.a().b;
        if (!TextUtils.isEmpty(obj)) {
            this.a.setEditHint(obj);
        }
        findViewById(2131756855).setOnTouchListener(new g(this));
        this.f = (CustomWebView) findViewById(R.id.webView);
        this.f.setProgressType(XZBDevice.DOWNLOAD_LIST_FAILED);
        this.g.sendEmptyMessageDelayed(1, 50);
        this.g.sendEmptyMessageDelayed(XZBDevice.DOWNLOAD_LIST_RECYCLE, 500);
        b();
        if (!TextUtils.isEmpty(stringExtra)) {
            b(getIntent().getStringExtra("From"), stringExtra);
        }
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.translate_between_interface_left_in, R.anim.translate_between_interface_right_out);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        finish();
        return true;
    }

    public void startSearch(String str, boolean z, Bundle bundle, boolean z2) {
        super.startSearch(str, z, bundle, z2);
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.g.hasMessages(XZBDevice.DOWNLOAD_LIST_RECYCLE)) {
            this.g.removeMessages(XZBDevice.DOWNLOAD_LIST_RECYCLE);
        }
    }

    private void a() {
        if (this.g.hasMessages(1)) {
            this.g.removeMessages(1);
        }
        if (this.g.hasMessages(XZBDevice.DOWNLOAD_LIST_RECYCLE)) {
            this.g.removeMessages(XZBDevice.DOWNLOAD_LIST_RECYCLE);
        }
        this.a.a();
    }

    private void b() {
        if (this.c == null) {
            this.c = new SearchMainFixFragment();
        }
        if (this.b != PageType.SEARCH_MAIN) {
            this.f.setVisibility(XZBDevice.Wait);
            this.f.h();
            a(this.c);
            this.b = PageType.SEARCH_MAIN;
        }
    }

    public final void b(String str, String str2) {
        if (TextUtils.isEmpty(str2) || str2.matches("[\\s\t\r\n]*")) {
            Object obj = 1;
        } else {
            boolean z = false;
        }
        if (!z) {
            this.e = false;
            this.a.setEditText(str2);
            a();
            String str3;
            g a;
            if (com.xunlei.downloadprovider.util.c.a.f(str2)) {
                BrowserUtil.a();
                BrowserUtil.a(this, 0, str2, false, StartFromType.favorite, false, SniffStartFrom.search_result);
                str3 = "search_website_submit";
                a = g.a("android_search", str3, str3);
                a.a("from", str, (int) XZBDevice.DOWNLOAD_LIST_FAILED);
                a.a(SocialConstants.PARAM_URL, str2, (int) XZBDevice.DOWNLOAD_LIST_FAILED);
                f.a(a);
                return;
            }
            com.xunlei.downloadprovider.search.a.a.a().a(str2);
            if (this.b != PageType.SEARCH_RESULT) {
                this.f.setVisibility(0);
                this.b = PageType.SEARCH_RESULT;
            }
            this.f.a(com.xunlei.downloadprovider.search.b.f.a(new StringBuilder("http://m.sjzhushou.com/v2/search/result_v14.html?type=video_search&key=").append(str2).toString()));
            str3 = "search_start_1";
            a = g.a("android_search", str3, str3);
            a.a("from", str, (int) XZBDevice.DOWNLOAD_LIST_FAILED);
            a.a("word", str2, (int) XZBDevice.DOWNLOAD_LIST_FAILED);
            f.a(a);
        }
    }

    public final void a(String str, SniffStartFrom sniffStartFrom) {
        boolean z;
        if (com.xunlei.downloadprovider.util.c.a.f(str)) {
            z = false;
        } else {
            com.xunlei.downloadprovider.search.a.a.a().a(str);
            z = true;
        }
        a();
        BrowserUtil.a();
        BrowserUtil.a(this, R.styleable.Toolbar_logoDescription, str, true, StartFromType.sniff_search_result_page, z, sniffStartFrom);
    }

    private void a(Fragment fragment) {
        if (fragment != null) {
            FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
            beginTransaction.replace(R.id.fragment_container, fragment);
            beginTransaction.addToBackStack(null);
            beginTransaction.commitAllowingStateLoss();
        }
    }

    public final void a(String str, String str2) {
        if (!TextUtils.isEmpty(str) && this.a != null) {
            this.a.setEditHint(str);
        }
    }

    public static void a(Context context, String str, String str2) {
        Intent intent = new Intent(context, SearchActivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(268435456);
        }
        if (!TextUtils.isEmpty(str2)) {
            intent.putExtra("Keyword", str2);
        }
        if (!TextUtils.isEmpty(str)) {
            intent.putExtra("From", str);
        }
        context.startActivity(intent);
    }

    public static void a(Context context) {
        a(context, com.umeng.a.d, com.umeng.a.d);
    }

    static /* synthetic */ void a(SearchActivity searchActivity) {
        SearchTitleBar searchTitleBar = searchActivity.a;
        searchTitleBar.a.requestFocus();
        ((InputMethodManager) searchTitleBar.a.getContext().getSystemService("input_method")).showSoftInput(searchTitleBar.a, 0);
    }

    static /* synthetic */ void a(SearchActivity searchActivity, String str) {
        if (searchActivity.d == null) {
            searchActivity.d = new SearchAssociativeFragment();
        }
        if (searchActivity.b != PageType.SEARCH_ASSOCIATIVE) {
            searchActivity.f.setVisibility(XZBDevice.Wait);
            searchActivity.f.h();
            searchActivity.a(searchActivity.d);
            searchActivity.b = PageType.SEARCH_ASSOCIATIVE;
            String str2 = "search_think_show";
            f.a(g.a("android_search", str2, str2));
        }
        SearchAssociativeFragment searchAssociativeFragment = searchActivity.d;
        searchAssociativeFragment.c = str;
        if (searchAssociativeFragment.b != null) {
            searchAssociativeFragment.a(searchAssociativeFragment.b.a, searchAssociativeFragment.b.b, str);
        }
        if (TextUtils.isEmpty(str)) {
            searchAssociativeFragment.a.a(null);
            return;
        }
        c a = c.a();
        b jVar = new j(searchAssociativeFragment);
        if (TextUtils.isEmpty(str)) {
            new StringBuilder("getAssociate--key=").append(str).append("|listener=").append(jVar);
        }
        Object a2 = c.a(str);
        if (!TextUtils.isEmpty(a2)) {
            Request tVar = new t(a2, new d(a, jVar), new e(a, jVar));
            tVar.setShouldCache(false);
            a.a(tVar);
        }
    }

    static /* synthetic */ String g(SearchActivity searchActivity) {
        String str = "search_prepare";
        switch (AnonymousClass_1.a[searchActivity.b.ordinal()]) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                return "search_think";
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                return "search_prepare";
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                return "search_result";
            default:
                return str;
        }
    }
}
