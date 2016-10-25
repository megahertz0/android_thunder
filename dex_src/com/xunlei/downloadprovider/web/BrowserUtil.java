package com.xunlei.downloadprovider.web;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebView;
import com.tencent.open.SocialConstants;
import com.xunlei.downloadprovider.commonview.a.a;
import com.xunlei.downloadprovider.model.h;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.Sniff.SniffStartFrom;
import com.xunlei.downloadprovider.web.browser.BrowserActivity;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import com.xunlei.xllib.a.b;
import org.android.spdy.SpdyAgent;

public class BrowserUtil {
    private static final String a;
    private static BrowserUtil b;
    private h c;

    public enum StartFromType {
        unknow,
        from_website,
        detail_page,
        outside,
        outside_showed,
        homepage,
        collect,
        favorite,
        browser_history,
        sniff_home_page_hot_movies_recommend,
        sniff_search_hot_top_list,
        sniff_channel_detail,
        sniff_funplay_climax,
        sniff_search_result_page,
        sniff_friend_detail,
        hot_website,
        user_input_website,
        scan_qrcode,
        recommend_website,
        hot_download_url,
        bho_sdk,
        download_detail_web,
        download_detail_search_again;

        static {
            unknow = new com.xunlei.downloadprovider.web.BrowserUtil.StartFromType("unknow", 0);
            from_website = new com.xunlei.downloadprovider.web.BrowserUtil.StartFromType("from_website", 1);
            detail_page = new com.xunlei.downloadprovider.web.BrowserUtil.StartFromType("detail_page", 2);
            outside = new com.xunlei.downloadprovider.web.BrowserUtil.StartFromType("outside", 3);
            outside_showed = new com.xunlei.downloadprovider.web.BrowserUtil.StartFromType("outside_showed", 4);
            homepage = new com.xunlei.downloadprovider.web.BrowserUtil.StartFromType(JsInterface.PAGE_HOME, 5);
            collect = new com.xunlei.downloadprovider.web.BrowserUtil.StartFromType("collect", 6);
            favorite = new com.xunlei.downloadprovider.web.BrowserUtil.StartFromType("favorite", 7);
            browser_history = new com.xunlei.downloadprovider.web.BrowserUtil.StartFromType("browser_history", 8);
            sniff_home_page_hot_movies_recommend = new com.xunlei.downloadprovider.web.BrowserUtil.StartFromType("sniff_home_page_hot_movies_recommend", 9);
            sniff_search_hot_top_list = new com.xunlei.downloadprovider.web.BrowserUtil.StartFromType("sniff_search_hot_top_list", 10);
            sniff_channel_detail = new com.xunlei.downloadprovider.web.BrowserUtil.StartFromType("sniff_channel_detail", 11);
            sniff_funplay_climax = new com.xunlei.downloadprovider.web.BrowserUtil.StartFromType("sniff_funplay_climax", 12);
            sniff_search_result_page = new com.xunlei.downloadprovider.web.BrowserUtil.StartFromType("sniff_search_result_page", 13);
            sniff_friend_detail = new com.xunlei.downloadprovider.web.BrowserUtil.StartFromType("sniff_friend_detail", 14);
            hot_website = new com.xunlei.downloadprovider.web.BrowserUtil.StartFromType("hot_website", 15);
            user_input_website = new com.xunlei.downloadprovider.web.BrowserUtil.StartFromType("user_input_website", 16);
            scan_qrcode = new com.xunlei.downloadprovider.web.BrowserUtil.StartFromType("scan_qrcode", 17);
            recommend_website = new com.xunlei.downloadprovider.web.BrowserUtil.StartFromType("recommend_website", 18);
            hot_download_url = new com.xunlei.downloadprovider.web.BrowserUtil.StartFromType("hot_download_url", 19);
            bho_sdk = new com.xunlei.downloadprovider.web.BrowserUtil.StartFromType("bho_sdk", 20);
            download_detail_web = new com.xunlei.downloadprovider.web.BrowserUtil.StartFromType("download_detail_web", 21);
            download_detail_search_again = new com.xunlei.downloadprovider.web.BrowserUtil.StartFromType("download_detail_search_again", 22);
            a = new com.xunlei.downloadprovider.web.BrowserUtil.StartFromType[]{unknow, from_website, detail_page, outside, outside_showed, homepage, collect, favorite, browser_history, sniff_home_page_hot_movies_recommend, sniff_search_hot_top_list, sniff_channel_detail, sniff_funplay_climax, sniff_search_result_page, sniff_friend_detail, hot_website, user_input_website, scan_qrcode, recommend_website, hot_download_url, bho_sdk, download_detail_web, download_detail_search_again};
        }
    }

    static {
        a = BrowserUtil.class.getSimpleName();
        b = null;
    }

    private BrowserUtil() {
        this.c = null;
    }

    public static BrowserUtil a() {
        if (b == null) {
            b = new BrowserUtil();
        }
        return b;
    }

    public static void a(Context context, String str, String str2, Bundle bundle) {
        a(context, str, str2, 0, bundle);
    }

    public static void a(Context context, String str, String str2) {
        a(context, str, str2, 0, null);
    }

    public static void a(Context context, String str, String str2, int i, Bundle bundle) {
        Bundle bundle2 = new Bundle();
        bundle2.putString("key_url", str);
        bundle2.putString("key_title", str2);
        bundle2.putInt("from_entry", i);
        if (bundle != null) {
            Object string = bundle.getString(JsInterface.FROM_KEY);
            if (TextUtils.isEmpty(string)) {
                bundle2.putString(JsInterface.FROM_KEY, string);
            }
        }
        bundle2.putInt("from_entry", i);
        Intent intent = new Intent(context, DetailPageBrowserActivity.class);
        intent.addFlags(268435456);
        intent.putExtras(bundle2);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    public static void a(Context context, String str, boolean z, StartFromType startFromType) {
        b(context, str, z, startFromType);
    }

    public static void b(Context context, String str, boolean z, StartFromType startFromType) {
        Intent intent = new Intent();
        intent.putExtra(SocialConstants.PARAM_URL, str);
        intent.putExtra("zoom", z);
        intent.putExtra("intent_key_start_from", startFromType);
        intent.setClass(context, BrowserActivity.class);
        if (context instanceof Activity) {
            context.startActivity(intent);
            a.a((Activity) context);
            return;
        }
        intent.setFlags(268435456);
        context.startActivity(intent);
    }

    public static void a(Context context, int i, String str, boolean z, StartFromType startFromType, boolean z2) {
        a(context, i, str, z, startFromType, z2, null);
    }

    public static void a(Context context, int i, String str, boolean z, StartFromType startFromType, boolean z2, SniffStartFrom sniffStartFrom) {
        Intent intent = new Intent();
        intent.putExtra(SocialConstants.PARAM_URL, str);
        intent.putExtra("zoom", z);
        intent.putExtra("intent_key_start_from", startFromType);
        intent.putExtra("intent_key_sniff_start_from", sniffStartFrom);
        intent.putExtra("intent_key_need_smart_prefix_sniff", true);
        intent.putExtra("first_entry", i);
        if (b.a(context)) {
            intent.putExtra("intent_key_need_auto_sniff", z2);
        } else {
            intent.putExtra("intent_key_need_auto_sniff", z2);
        }
        intent.setClass(context, BrowserActivity.class);
        if (context instanceof Activity) {
            context.startActivity(intent);
            a.a((Activity) context);
            return;
        }
        intent.setFlags(268435456);
        context.startActivity(intent);
    }

    public static void a(Context context, String str, String str2, String str3, StartFromType startFromType, SniffStartFrom sniffStartFrom) {
        Intent intent = new Intent();
        intent.putExtra(SocialConstants.PARAM_URL, str);
        intent.putExtra("zoom", true);
        intent.putExtra("intent_key_start_from", startFromType);
        intent.putExtra("intent_key_sniff_start_from", sniffStartFrom);
        intent.putExtra("intent_key_need_smart_prefix_sniff", true);
        intent.putExtra("first_entry", 2074);
        intent.putExtra("download_detail_key_word", str2);
        intent.putExtra("download_detail_resource_ref_page_url", str3);
        if (b.a(context)) {
            intent.putExtra("intent_key_need_auto_sniff", true);
        }
        intent.setClass(context, BrowserActivity.class);
        if (context instanceof Activity) {
            context.startActivity(intent);
            a.a((Activity) context);
            return;
        }
        intent.setFlags(268435456);
        context.startActivity(intent);
    }

    public static void b(Context context, String str, String str2) {
        Intent intent = new Intent(context, DetailPageBrowserActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("key_url", str);
        bundle.putString("key_title", str2);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public static void a(Context context, String str, StartFromType startFromType) {
        Intent intent = new Intent();
        intent.putExtra(SocialConstants.PARAM_URL, str);
        intent.putExtra("download", true);
        intent.putExtra("intent_key_start_from", startFromType);
        intent.setClass(context, BrowserActivity.class);
        if (context instanceof Activity) {
            context.startActivity(intent);
            a.a((Activity) context);
            return;
        }
        intent.setFlags(268435456);
        context.startActivity(intent);
    }

    public static String a(String str) {
        return (TextUtils.isEmpty(str) || str.startsWith("http://") || str.startsWith("https://") || str.startsWith("ftp://")) ? str : new StringBuilder("http://").append(str).toString();
    }

    public static String a(StartFromType startFromType) {
        if (startFromType == null) {
            return null;
        }
        switch (AnonymousClass_1.a[startFromType.ordinal()]) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                return "website";
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                return "outside";
            case XZBDevice.DOWNLOAD_LIST_ALL:
                return "favorite";
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                return "browser_history";
            case R.styleable.Toolbar_contentInsetEnd:
                return "sniff_home_page_hot_movies_recommend";
            case R.styleable.Toolbar_contentInsetLeft:
                return "sniff_search_hot_top_list";
            case XZBDevice.Wait:
                return "sniff_channel_detail";
            case XZBDevice.Pause:
                return "sniff_funplay_climax";
            case XZBDevice.Stop:
                return "sniff_search_result_page";
            case XZBDevice.Success:
                return "sniff_friend_detail";
            case XZBDevice.Fail:
                return "hot_website";
            case XZBDevice.Upload:
                return "user_input_website";
            case XZBDevice.Predownload:
                return "recommend_website";
            case XZBDevice.Delete:
                return "hot_download_url";
            case R.styleable.Toolbar_titleMarginBottom:
                return "scan_qrcode";
            case R.styleable.Toolbar_maxButtonHeight:
                return "download_detail_web";
            case R.styleable.Toolbar_collapseIcon:
                return "download_search_agin";
            default:
                return "other";
        }
    }

    public static void a(WebView webView) {
        if (webView != null) {
            webView.setOverScrollMode(XZBDevice.DOWNLOAD_LIST_RECYCLE);
        }
    }

    public static boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.contains("m.sjzhushou.com") || str.startsWith("javascript") || str.contains("xunlei.com") || str.contains("act.vip.xunlei.com");
    }
}
