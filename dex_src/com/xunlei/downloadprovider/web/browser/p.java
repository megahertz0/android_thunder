package com.xunlei.downloadprovider.web.browser;

import com.umeng.a;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.Sniff;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g;
import com.xunlei.downloadprovider.web.browser.BrowserSniffer.b;
import com.xunlei.thundersniffer.sniff.SniffingPageResource;
import com.xunlei.thundersniffer.sniff.SniffingResourceGroup;
import com.xunlei.thundersniffer.sniff.sniffer.SnifferListener;
import com.xunlei.thundersniffer.sniff.sniffer.ThunderSniffer;
import com.xunlei.thundersniffer.sniff.sniffer.ThunderSnifferUtil;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: BrowserSniffer.java
final class p implements SnifferListener {
    final /* synthetic */ BrowserSniffer a;

    p(BrowserSniffer browserSniffer) {
        this.a = browserSniffer;
    }

    public final void onSnifferFinishSniffing(ThunderSniffer thunderSniffer, SniffingPageResource sniffingPageResource) {
        this.a.a(sniffingPageResource);
        if (this.a.q) {
            this.a.q = false;
        }
    }

    public final void onSnifferStartPreSniffing(ThunderSniffer thunderSniffer, Object obj) {
        this.a.l.b(this.a, null);
    }

    public final void onSnifferProgress(ThunderSniffer thunderSniffer, float f, Object obj) {
        new StringBuilder("[ThunderSniffer] - onSnifferProgress: ").append(100.0f * f);
        if (this.a.g) {
            this.a.l.a(this.a, f, null);
        }
    }

    public final void onSnifferResourceFound(ThunderSniffer thunderSniffer, SniffingResourceGroup sniffingResourceGroup) {
        new StringBuilder("1 -- add group data --> resourceGroup.realUrl --> ").append(sniffingResourceGroup.realUrl).append(", resourceGroup.resources.size() --> ").append(sniffingResourceGroup.resources.size());
        if (this.a.a.d == 0) {
            String str;
            this.a.a.e();
            String a = this.a.a.a();
            int judgeSniffingPageTypeForUrl = ThunderSnifferUtil.judgeSniffingPageTypeForUrl(thunderSniffer.getOriginalUrl());
            long b = this.a.a.b();
            String searchWordFromUrl = ThunderSnifferUtil.getSearchWordFromUrl(thunderSniffer.getOriginalUrl());
            if (judgeSniffingPageTypeForUrl == 1) {
                str = "baidu";
            } else if (judgeSniffingPageTypeForUrl == 2) {
                str = "360";
            } else if (judgeSniffingPageTypeForUrl == 3) {
                str = "shenma";
            } else if (judgeSniffingPageTypeForUrl == 4) {
                str = "sougou";
            } else {
                str = "other";
            }
            String str2 = "sniff_2_get_result_1";
            g a2 = g.a("android_sniff", str2, str2);
            str2 = "sniff_processid";
            if (a == null) {
                a = a.d;
            }
            Sniff.a(a2.a(str2, a, 1).a("start_page", str, (int) XZBDevice.DOWNLOAD_LIST_FAILED).a("first_time", String.valueOf(((float) b) / 1000.0f)).c("first_time", Sniff.a(b / 1000)).a("sniff_word", searchWordFromUrl == null ? a.d : searchWordFromUrl, 1));
        }
        if (this.a.g) {
            this.a.l.a(this.a, new b(sniffingResourceGroup, this.a.a.a()), null);
        }
    }

    public final void onSnifferStartSniffing(ThunderSniffer thunderSniffer, Object obj) {
        this.a.l.b(this.a, null);
    }
}
