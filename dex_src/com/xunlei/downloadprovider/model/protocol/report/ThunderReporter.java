package com.xunlei.downloadprovider.model.protocol.report;

import android.text.TextUtils;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.tencent.open.SocialConstants;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.download.tasklist.list.a.a.h;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.Sniff.ClickType;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.Sniff.SniffStartFrom;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g;
import com.xunlei.downloadprovider.web.BrowserUtil.StartFromType;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import org.android.agoo.message.MessageService;
import org.android.spdy.SpdyAgent;
import org.json.JSONException;
import org.json.JSONObject;

@Deprecated
public final class ThunderReporter {

    static /* synthetic */ class AnonymousClass_1 {
        static final /* synthetic */ int[] a;
        public static final /* synthetic */ int[] b;
        public static final /* synthetic */ int[] c;

        static {
            c = new int[ClickType.values().length];
            try {
                c[ClickType.link.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                c[ClickType.word.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            b = new int[StartFromType.values().length];
            try {
                b[StartFromType.detail_page.ordinal()] = 1;
            } catch (NoSuchFieldError e3) {
            }
            try {
                b[StartFromType.sniff_channel_detail.ordinal()] = 2;
            } catch (NoSuchFieldError e4) {
            }
            try {
                b[StartFromType.sniff_friend_detail.ordinal()] = 3;
            } catch (NoSuchFieldError e5) {
            }
            try {
                b[StartFromType.sniff_search_result_page.ordinal()] = 4;
            } catch (NoSuchFieldError e6) {
            }
            try {
                b[StartFromType.sniff_search_hot_top_list.ordinal()] = 5;
            } catch (NoSuchFieldError e7) {
            }
            try {
                b[StartFromType.sniff_home_page_hot_movies_recommend.ordinal()] = 6;
            } catch (NoSuchFieldError e8) {
            }
            try {
                b[StartFromType.download_detail_web.ordinal()] = 7;
            } catch (NoSuchFieldError e9) {
            }
            try {
                b[StartFromType.download_detail_search_again.ordinal()] = 8;
            } catch (NoSuchFieldError e10) {
            }
            try {
                b[StartFromType.user_input_website.ordinal()] = 9;
            } catch (NoSuchFieldError e11) {
            }
            a = new int[SniffStartFrom.values().length];
            try {
                a[SniffStartFrom.detail.ordinal()] = 1;
            } catch (NoSuchFieldError e12) {
            }
            try {
                a[SniffStartFrom.search_result.ordinal()] = 2;
            } catch (NoSuchFieldError e13) {
            }
            try {
                a[SniffStartFrom.webpv.ordinal()] = 3;
            } catch (NoSuchFieldError e14) {
            }
            try {
                a[SniffStartFrom.hot_video.ordinal()] = 4;
            } catch (NoSuchFieldError e15) {
            }
            try {
                a[SniffStartFrom.search_think_bottom.ordinal()] = 5;
            } catch (NoSuchFieldError e16) {
            }
            try {
                a[SniffStartFrom.sniff_suffix_rec.ordinal()] = 6;
            } catch (NoSuchFieldError e17) {
            }
            try {
                a[SniffStartFrom.browser_word.ordinal()] = 7;
            } catch (NoSuchFieldError e18) {
            }
            try {
                a[SniffStartFrom.search_hot_wait.ordinal()] = 8;
            } catch (NoSuchFieldError e19) {
            }
            try {
                a[SniffStartFrom.search_think_keyin.ordinal()] = 9;
            } catch (NoSuchFieldError e20) {
            }
            try {
                a[SniffStartFrom.download_detail_web.ordinal()] = 10;
            } catch (NoSuchFieldError e21) {
            }
            try {
                a[SniffStartFrom.download_detail_search_agin.ordinal()] = 11;
            } catch (NoSuchFieldError e22) {
            }
        }
    }

    public static class Sniff {

        public enum ClickType {
            link,
            word;

            static {
                link = new com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.Sniff.ClickType("link", 0);
                word = new com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.Sniff.ClickType("word", 1);
                a = new com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.Sniff.ClickType[]{link, word};
            }
        }

        public enum SniffStartFrom {
            detail,
            search_result,
            webpv,
            hot_video,
            search_think_bottom,
            sniff_suffix_rec,
            browser_word,
            search_hot_wait,
            search_think_keyin,
            home_collect,
            kandan,
            yingshi_detail,
            search_noresult,
            download_detail_web,
            download_detail_search_agin;

            static {
                detail = new com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.Sniff.SniffStartFrom(JsInterface.PAGE_DETAIL, 0);
                search_result = new com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.Sniff.SniffStartFrom("search_result", 1);
                webpv = new com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.Sniff.SniffStartFrom("webpv", 2);
                hot_video = new com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.Sniff.SniffStartFrom("hot_video", 3);
                search_think_bottom = new com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.Sniff.SniffStartFrom("search_think_bottom", 4);
                sniff_suffix_rec = new com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.Sniff.SniffStartFrom("sniff_suffix_rec", 5);
                browser_word = new com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.Sniff.SniffStartFrom("browser_word", 6);
                search_hot_wait = new com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.Sniff.SniffStartFrom("search_hot_wait", 7);
                search_think_keyin = new com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.Sniff.SniffStartFrom("search_think_keyin", 8);
                home_collect = new com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.Sniff.SniffStartFrom("home_collect", 9);
                kandan = new com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.Sniff.SniffStartFrom("kandan", 10);
                yingshi_detail = new com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.Sniff.SniffStartFrom("yingshi_detail", 11);
                search_noresult = new com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.Sniff.SniffStartFrom("search_noresult", 12);
                download_detail_web = new com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.Sniff.SniffStartFrom("download_detail_web", 13);
                download_detail_search_agin = new com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.Sniff.SniffStartFrom("download_detail_search_agin", 14);
                a = new com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.Sniff.SniffStartFrom[]{detail, search_result, webpv, hot_video, search_think_bottom, sniff_suffix_rec, browser_word, search_hot_wait, search_think_keyin, home_collect, kandan, yingshi_detail, search_noresult, download_detail_web, download_detail_search_agin};
            }
        }

        public static long a(long j) {
            long j2 = j <= 0 ? 1 : j;
            if (j2 < 300) {
                return j2;
            }
            return j2 < 3600 ? ((9 + j2) / 10) * 10 : 3600;
        }

        public static void a(g gVar) {
            gVar.a = "android_sniff";
            new StringBuilder("[STAT_EVENT] ").append(gVar.toString());
            ThunderReporter.a(gVar);
            ThunderReporter.a(gVar, true);
        }

        public static void a(String str, SniffStartFrom sniffStartFrom, boolean z, int i, String str2) {
            String str3;
            String str4;
            if (i == 1) {
                str3 = "baidu";
            } else if (i == 2) {
                str3 = "360";
            } else if (i == 3) {
                str3 = "shenma";
            } else if (i == 4) {
                str3 = "sougou";
            } else {
                str3 = "other";
            }
            String str5 = "sniff_1_start";
            g a = g.a("android_sniff", str5, str5);
            String str6 = "from";
            switch (AnonymousClass_1.a[sniffStartFrom.ordinal()]) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    str4 = JsInterface.PAGE_DETAIL;
                    break;
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    str4 = "search_result";
                    break;
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    str4 = "webpv";
                    break;
                case XZBDevice.DOWNLOAD_LIST_ALL:
                    str4 = "hot_video";
                    break;
                case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                    str4 = "search_think_bottom";
                    break;
                case R.styleable.Toolbar_contentInsetEnd:
                    str4 = "sniff_suffix_rec";
                    break;
                case R.styleable.Toolbar_contentInsetLeft:
                    str4 = "browser_word";
                    break;
                case XZBDevice.Wait:
                    str4 = "search_hot_wait";
                    break;
                case XZBDevice.Pause:
                    str4 = "search_think_keyin";
                    break;
                case XZBDevice.Stop:
                    str4 = "download_detail_view_web";
                    break;
                case XZBDevice.Success:
                    str4 = "download_detail_search";
                    break;
                default:
                    str4 = sniffStartFrom.name();
                    break;
            }
            a = a.a(str6, str4, (int) XZBDevice.DOWNLOAD_LIST_FAILED);
            str6 = "start_src";
            if (z) {
                str4 = "manual";
            } else {
                str4 = "active";
            }
            g a2 = a.a(str6, str4, (int) XZBDevice.DOWNLOAD_LIST_FAILED).a("start_page", str3, (int) XZBDevice.DOWNLOAD_LIST_FAILED).a("sniff_tech", "true", (int) XZBDevice.DOWNLOAD_LIST_FAILED);
            str4 = "sniff_processid";
            if (str == null) {
                str = com.umeng.a.d;
            }
            a2 = a2.a(str4, str, 1);
            str4 = "sniff_word";
            if (str2 == null) {
                str2 = com.umeng.a.d;
            }
            a(a2.a(str4, str2, 1));
        }

        public static void a() {
            String str = "sniff_6_offline_appear";
            a(g.a("android_sniff", str, str));
        }
    }

    public static class a {
        public static void a(String str, String str2, String str3, String str4) {
            new StringBuilder("reportADStatus  id: ").append(str2).append(" adtype: ").append(str3).append(" eventType: ").append(str);
            g a = g.a("android_advertise", str, str);
            String str5 = SocializeConstants.WEIBO_ID;
            if (str2 == null) {
                str2 = com.umeng.a.d;
            }
            a = a.a(str5, str2);
            str5 = "ad_type";
            if (str3 == null) {
                str3 = com.umeng.a.d;
            }
            a = a.a(str5, str3, (int) XZBDevice.DOWNLOAD_LIST_FAILED);
            str5 = "material";
            if (str4 == null) {
                str4 = com.umeng.a.d;
            }
            a(a.a(str5, str4, (int) XZBDevice.DOWNLOAD_LIST_FAILED));
        }

        public static void a(String str) {
            String str2 = "adv_launch_nopv";
            a(g.a("android_advertise", str2, str2).a("nopv_detail", str, (int) XZBDevice.DOWNLOAD_LIST_FAILED));
        }

        public static void a(g gVar) {
            new StringBuilder("[STAT_EVENT]").append(gVar);
            ThunderReporter.a(gVar, true);
        }

        public static void a(String str, int i, int i2) {
            a(g.a("android_advertise", str, str).a("tabid", h.a(i), (int) XZBDevice.DOWNLOAD_LIST_FAILED).b("errorcode", (long) i2).a("net_type", a()));
        }

        public static void b(String str) {
            String str2 = "adv_homeflow_ssp_request";
            a(g.a("android_advertise", str2, str2).a(JsInterface.FUNPLAY_AD_TRPE, str, (int) XZBDevice.DOWNLOAD_LIST_FAILED));
        }

        public static void a(String str, String str2, String str3) {
            a(g.a("android_advertise", str, str).a("errorcode", str3, (int) XZBDevice.DOWNLOAD_LIST_FAILED).a(JsInterface.FUNPLAY_AD_TRPE, str2, (int) XZBDevice.DOWNLOAD_LIST_FAILED).a("net_type", a(), (int) XZBDevice.DOWNLOAD_LIST_FAILED));
        }

        public static String a() {
            String c = com.xunlei.xllib.a.b.c(BrothersApplication.a());
            return (c == null || !c.equals("null")) ? c : MessageService.MSG_DB_READY_REPORT;
        }

        public static void c(String str) {
            String a = a();
            new StringBuilder("reportTaskDetailImageAdGDTFail errorcode: ").append(str).append(" net_type: ").append(a);
            String str2 = "adv_download_detail_tx_fail";
            a(g.a("android_advertise", str2, str2).a("errorcode", str, (int) XZBDevice.DOWNLOAD_LIST_FAILED).a("net_type", a, (int) XZBDevice.DOWNLOAD_LIST_FAILED));
        }

        public static void a(String str, String str2) {
            new StringBuilder("reportDownloadListStartLaod_AD ").append(str).append("tabid: ").append(str2);
            a(g.a("android_advertise", str, str).a("tabid", str2, (int) XZBDevice.DOWNLOAD_LIST_FAILED));
        }

        public static void a(String str, String str2, String str3, String str4, String str5) {
            new StringBuilder("reportDownloadCenter_AD_Show tabid: ").append(str).append(" ad_type: ").append(str2).append(" position: ").append(str4).append(" materialNum: ").append(str5);
            String str6 = "adv_downloadtab_show";
            g a = g.a("android_advertise", str6, str6).a("ad_type", str2, (int) XZBDevice.DOWNLOAD_LIST_FAILED).a("tabid", str, (int) XZBDevice.DOWNLOAD_LIST_FAILED).a("advid", str3, (int) XZBDevice.DOWNLOAD_LIST_FAILED).a("ad_position", str4, (int) XZBDevice.DOWNLOAD_LIST_FAILED);
            str6 = "material";
            if (str5 == null) {
                str5 = com.umeng.a.d;
            }
            a(a.a(str6, str5, (int) XZBDevice.DOWNLOAD_LIST_FAILED));
        }

        public static void b(String str, String str2, String str3, String str4, String str5) {
            new StringBuilder("reportDownloadCenter_AD_Click tabid: ").append(str).append(" ad_type: ").append(str2).append(" position: ").append(str4).append(" materialNum: ").append(str5);
            String str6 = "adv_downloadtab_click";
            g a = g.a("android_advertise", str6, str6).a("ad_type", str2, (int) XZBDevice.DOWNLOAD_LIST_FAILED).a("tabid", str, (int) XZBDevice.DOWNLOAD_LIST_FAILED).a("advid", str3, (int) XZBDevice.DOWNLOAD_LIST_FAILED).a("ad_position", str4, (int) XZBDevice.DOWNLOAD_LIST_FAILED);
            str6 = "material";
            if (str5 == null) {
                str5 = com.umeng.a.d;
            }
            a(a.a(str6, str5, (int) XZBDevice.DOWNLOAD_LIST_FAILED));
        }

        public static void c(String str, String str2, String str3, String str4, String str5) {
            new StringBuilder("reportDownloadCenter_AD_Close_Click tabid: ").append(str).append(" ad_type: ").append(str2).append(" position: ").append(str4).append(" materialNum: ").append(str5);
            String str6 = "adv_downloadplay_finish_close";
            g a = g.a("android_advertise", str6, str6).a("ad_type", str2, (int) XZBDevice.DOWNLOAD_LIST_FAILED).a("tabid", str, (int) XZBDevice.DOWNLOAD_LIST_FAILED).a("advid", str3, (int) XZBDevice.DOWNLOAD_LIST_FAILED).a("ad_position", str4, (int) XZBDevice.DOWNLOAD_LIST_FAILED);
            str6 = "material";
            if (str5 == null) {
                str5 = com.umeng.a.d;
            }
            a(a.a(str6, str5, (int) XZBDevice.DOWNLOAD_LIST_FAILED));
        }

        public static void b(String str, String str2, String str3) {
            ThunderReporter.a(g.a("android_advertise", str, str).a("launchertime", str2).a("launchType", str3), false);
        }
    }

    public static class b {
        public static void a(String str) {
            g a = g.a("android_browser", str, str);
            ThunderReporter.a(a);
            ThunderReporter.a(a, true);
        }

        public static void a(boolean z) {
            g a = g.a("android_browser", "browser_collect", "browser_collect");
            a.a(JsInterface.KEY_ACTION, z ? "cancel" : "collect", (int) XZBDevice.DOWNLOAD_LIST_FAILED);
            ThunderReporter.a(a);
            ThunderReporter.a(a, true);
        }

        public static void a(String str, String str2) {
            g a = g.a("android_browser", "browser_collect_altertitle", "browser_collect_altertitle");
            String str3 = "word_old";
            if (str == null) {
                str = com.umeng.a.d;
            }
            a.a(str3, str, 1);
            str3 = "word_new";
            if (str2 == null) {
                str2 = com.umeng.a.d;
            }
            a.a(str3, str2, 1);
            ThunderReporter.a(a);
            ThunderReporter.a(a, true);
        }
    }

    public static class c {
        public static void a(String str, String str2, String str3, int i, String str4) {
            String str5 = "fun_share_result";
            g a = g.a("android_fun", str5, str5);
            str5 = "from";
            if (str == null) {
                str = com.umeng.a.d;
            }
            a.a(str5, str);
            str5 = "to";
            if (str2 == null) {
                str2 = com.umeng.a.d;
            }
            a.a(str5, str2);
            str5 = "funid";
            if (str4 == null) {
                str4 = com.umeng.a.d;
            }
            a.a(str5, str4);
            str5 = "result";
            if (str3 == null) {
                str3 = com.umeng.a.d;
            }
            a.a(str5, str3);
            a.a("errorcode", (long) i);
            ThunderReporter.a(a, false);
        }

        public static void a(String str, String str2, String str3) {
            String str4 = "fun_share_to";
            g a = g.a("android_fun", str4, str4);
            str4 = "from";
            if (str == null) {
                str = com.umeng.a.d;
            }
            a.a(str4, str);
            str4 = "to";
            if (str2 == null) {
                str2 = com.umeng.a.d;
            }
            a.a(str4, str2);
            str4 = "funid";
            if (str3 == null) {
                str3 = com.umeng.a.d;
            }
            a.a(str4, str3);
            ThunderReporter.a(a, false);
        }
    }

    public static class f {
        public static void a(String str, String str2, String str3) {
            String str4 = "search_top_click";
            g a = g.a("android_search", str4, str4);
            a.a("from", str, (int) XZBDevice.DOWNLOAD_LIST_FAILED);
            a.a("to", str2, (int) XZBDevice.DOWNLOAD_LIST_FAILED);
            a.a("clickid", str3, (int) XZBDevice.DOWNLOAD_LIST_FAILED);
            a(a);
        }

        public static void b(String str, String str2, String str3) {
            String str4 = "search_prepare_click";
            g a = g.a("android_search", str4, str4);
            a.a(ParamKey.CARDID, str, (int) XZBDevice.DOWNLOAD_LIST_FAILED);
            a.a("clickid", str2, (int) XZBDevice.DOWNLOAD_LIST_FAILED);
            a.a("word", str3, (int) XZBDevice.DOWNLOAD_LIST_FAILED);
            a(a);
        }

        public static void a(String str) {
            String str2 = "search_tab_show";
            g a = g.a("android_search", str2, str2);
            a.a("tabid", str, (int) XZBDevice.DOWNLOAD_LIST_FAILED);
            a(a);
        }

        public static void c(String str, String str2, String str3) {
            String str4 = "search_hotsearchTab_click";
            g a = g.a("android_search", str4, str4);
            a.a(ParamKey.CARDID, str, (int) XZBDevice.DOWNLOAD_LIST_FAILED);
            a.a("clickid", str2, (int) XZBDevice.DOWNLOAD_LIST_FAILED);
            a.a("word", str3, (int) XZBDevice.DOWNLOAD_LIST_FAILED);
            a(a);
        }

        public static void d(String str, String str2, String str3) {
            String str4 = "search_websiteTab_click";
            g a = g.a("android_search", str4, str4);
            a.a(ParamKey.CARDID, str, (int) XZBDevice.DOWNLOAD_LIST_FAILED);
            a.a("clickid", str2, (int) XZBDevice.DOWNLOAD_LIST_FAILED);
            a.a(SocialConstants.PARAM_URL, str3, (int) XZBDevice.DOWNLOAD_LIST_FAILED);
            a(a);
        }

        public static void a(g gVar) {
            new StringBuilder("[STAT_EVENT]").append(gVar);
            ThunderReporter.a(gVar, true);
        }
    }

    public static class g {
        public String a;
        public String b;
        public String c;
        HashMap<String, String> d;
        HashMap<String, String> e;

        public g() {
            this.a = com.umeng.a.d;
            this.b = com.umeng.a.d;
            this.c = com.umeng.a.d;
            this.d = null;
            this.e = null;
        }

        public final com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g a(String str, String str2) {
            if (this.d == null) {
                this.d = new HashMap();
            }
            this.d.put(str, str2);
            return this;
        }

        public final com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g a(String str, long j) {
            return a(str, Long.toString(j));
        }

        public final com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g a(String str, String str2, int i) {
            if ((i & 1) != 0) {
                a(str, str2);
            }
            if ((i & 2) != 0) {
                b(str, str2);
            }
            return this;
        }

        public final com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g b(String str, long j) {
            a(str, j);
            c(str, j);
            return this;
        }

        public final com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g c(String str, long j) {
            return b(str, Long.toString(j));
        }

        private com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g b(String str, String str2) {
            if (this.e == null) {
                this.e = new HashMap();
            }
            this.e.put(str, str2);
            return this;
        }

        public static com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g a(String str, String str2, String str3) {
            com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g gVar = new com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g();
            gVar.a = str;
            gVar.b = str2;
            gVar.c = str3;
            return gVar;
        }

        public final com.xunlei.b.a a() {
            if (this.c == null || this.c.isEmpty()) {
                return null;
            }
            com.xunlei.b.a aVar = new com.xunlei.b.a(this.c);
            if (this.e != null) {
                for (Entry entry : this.e.entrySet()) {
                    aVar.a((String) entry.getKey(), (String) entry.getValue());
                }
            }
            return aVar;
        }

        public final String toString() {
            return new StringBuilder("StatFields [EventName=").append(this.a).append(", Attribute1=").append(this.b).append(", HubbleFields=").append(this.d).append(", ; UMengEventName=").append(this.c).append(", UMengFields=").append(this.e).append("]").toString();
        }
    }

    public static void a(g gVar, boolean z) {
        String str = gVar.a;
        String str2 = gVar.b;
        com.xunlei.downloadprovider.model.protocol.report.b.a aVar = new com.xunlei.downloadprovider.model.protocol.report.b.a();
        if (gVar.d != null) {
            for (Entry entry : gVar.d.entrySet()) {
                aVar.a((String) entry.getKey(), (String) entry.getValue());
            }
        }
        b.a(str, str2, aVar);
        if (z) {
            com.xunlei.b.a a = gVar.a();
            if (a != null) {
                StatReporter.sendReportParams(a);
            }
        }
    }

    public static void a(g gVar) {
        new StringBuilder("[STAT_EVENT]").append(gVar);
    }

    public static void a(String str) {
        try {
            String str2;
            JSONObject jSONObject = new JSONObject(str);
            int optInt = jSONObject.optInt("reportPlat");
            g a = g.a(jSONObject.optString("hubbleEventId"), jSONObject.optString("hubbleAttribute1"), jSONObject.optString("umengEventId"));
            JSONObject optJSONObject = jSONObject.optJSONObject("hubbleExData");
            if (optJSONObject != null) {
                Iterator keys = optJSONObject.keys();
                while (keys.hasNext()) {
                    str2 = (String) keys.next();
                    if (!optJSONObject.isNull(str2)) {
                        String optString = optJSONObject.optString(str2);
                        if (!TextUtils.isEmpty(optString)) {
                            a.a(str2, optString, 1);
                        }
                    }
                }
            }
            jSONObject = jSONObject.optJSONObject("umengExData");
            if (jSONObject != null) {
                Iterator keys2 = jSONObject.keys();
                while (keys2.hasNext()) {
                    str2 = (String) keys2.next();
                    if (!jSONObject.isNull(str2)) {
                        String optString2 = jSONObject.optString(str2);
                        if (!TextUtils.isEmpty(optString2)) {
                            a.a(str2, optString2, (int) XZBDevice.DOWNLOAD_LIST_RECYCLE);
                        }
                    }
                }
            }
            new StringBuilder("statFields=").append(a);
            if (optInt == 0) {
                a(a, true);
            } else if (optInt == 1) {
                a(a, false);
            } else if (optInt == 2) {
                com.xunlei.b.a a2 = a.a();
                if (a2 != null) {
                    StatReporter.sendReportParams(a2);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
