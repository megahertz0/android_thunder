package com.xunlei.downloadprovider.web.sniff;

import android.webkit.WebBackForwardList;
import android.webkit.WebHistoryItem;
import com.xunlei.downloadprovider.util.sniff.f;
import com.xunlei.downloadprovider.web.sniff.SnifferResultsFragment.d;
import com.xunlei.downloadprovider.web.sniff.a.a;
import com.xunlei.thundersniffer.sniff.SniffingPageResource;
import com.xunlei.thundersniffer.sniff.SniffingResourceGroup;
import com.xunlei.thundersniffer.sniff.sniffer.ThunderSnifferUtil;
import java.util.Iterator;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: SnifferResultsFragment.java
final class e implements d {
    float a;
    final /* synthetic */ SnifferResultsFragment b;
    private boolean c;

    e(SnifferResultsFragment snifferResultsFragment) {
        this.b = snifferResultsFragment;
    }

    public final void a() {
        this.c = false;
        SnifferResultsFragment.a(this.b, SimpleLog.LOG_LEVEL_OFF);
        SnifferResultsFragment.c(this.b);
    }

    public final void b() {
        if (!this.c) {
            SnifferResultsFragment.a(this.b, false);
            this.a = 0.0f;
            SnifferResultsFragment.a(this.b, 0);
            SnifferResultsFragment.c(this.b);
        }
    }

    public final void a(String str, String str2) {
        SnifferResultsFragment.f(this.b);
        SnifferResultsFragment.g(this.b);
        new StringBuilder("onPageStart url: ").append(str).append(" originUrl: ").append(str2);
        if (SnifferResultsFragment.h(this.b).getVisibilityState() != 10) {
            return;
        }
        if (str.equals(SnifferResultsFragment.i(this.b))) {
            SnifferResultsFragment.a(this.b, null);
            return;
        }
        SnifferResultsFragment.a(this.b, SimpleLog.LOG_LEVEL_FATAL);
        SnifferResultsFragment.c(this.b);
    }

    public final void b(String str, String str2) {
        new StringBuilder("onPageFinish url: ").append(str).append(" originUrl: ").append(str2);
    }

    public final void a(boolean z) {
        SnifferResultsFragment.a(this.b, z ? SpdyProtocol.PUBKEY_PSEQ_OPEN : SimpleLog.LOG_LEVEL_FATAL);
        SnifferResultsFragment.c(this.b);
    }

    public final void a(SniffingResourceGroup sniffingResourceGroup, String str) {
        if (sniffingResourceGroup != null && !com.xunlei.xllib.b.d.a(sniffingResourceGroup.resources)) {
            String searchWordFromUrl;
            new StringBuilder("4 -- add group data --> resGroup.realUrl --> ").append(sniffingResourceGroup.realUrl).append(", resGroup.size() --> ").append(sniffingResourceGroup.resources.size());
            if (SnifferResultsFragment.j(this.b) != 1) {
                SnifferResultsFragment.a(this.b, 1);
                SnifferResultsFragment.a(this.b, new SnifferResultsFragment$m(this.b, str));
            }
            if (sniffingResourceGroup.origin != null && sniffingResourceGroup.origin.pageNo < 0) {
                sniffingResourceGroup.origin.pageNo = 1;
            }
            if (f.e(ThunderSnifferUtil.getSearchWordFromUrl(str)) == null || !f.e(ThunderSnifferUtil.getSearchWordFromUrl(str)).trim().equals("\u8fc5\u96f7\u4e0b\u8f7d")) {
                searchWordFromUrl = ThunderSnifferUtil.getSearchWordFromUrl(str);
            } else {
                searchWordFromUrl = f.d(ThunderSnifferUtil.getSearchWordFromUrl(str));
            }
            if (!SnifferResultsFragment.k(this.b)) {
                SnifferResultsFragment snifferResultsFragment = this.b;
                snifferResultsFragment.g = !SnifferResultsFragment.a(snifferResultsFragment.f).equals(SnifferResultsFragment.a(searchWordFromUrl));
                snifferResultsFragment.f = searchWordFromUrl;
                SnifferResultsFragment.a(this.b, true);
            }
            if (sniffingResourceGroup.origin != null) {
                if (SnifferResultsFragment.l(this.b) == -1) {
                    SnifferResultsFragment.b(this.b, searchWordFromUrl);
                    SnifferResultsFragment.b(this.b, sniffingResourceGroup.origin.pageNo);
                }
                SnifferResultsFragment.c(this.b, sniffingResourceGroup.origin.pageNo);
            }
            new StringBuilder("resGroup.sniffingType --> ").append(sniffingResourceGroup.sniffingType);
            if (sniffingResourceGroup.sniffingType != 2) {
                ((SnifferResultsFragment$m) SnifferResultsFragment.m(this.b)).a(sniffingResourceGroup);
            } else if (SnifferResultsFragment.n(this.b)) {
                ((SnifferResultsFragment$m) SnifferResultsFragment.m(this.b)).a(sniffingResourceGroup);
            } else if (SnifferResultsFragment.o(this.b) || SnifferResultsFragment.p(this.b) == null || !SnifferResultsFragment.p(this.b).equals(SnifferResultsFragment.q(this.b))) {
                SnifferResultsFragment.r(this.b);
            } else {
                ((SnifferResultsFragment$m) SnifferResultsFragment.m(this.b)).a(sniffingResourceGroup);
            }
            SnifferResultsFragment.c(this.b);
        }
    }

    public final void a(int i) {
        if (i <= 100 && i >= 0) {
            int i2 = (int) (((double) ((float) i)) * 0.45d);
            if (this.a < ((float) i2)) {
                SnifferResultsFragment.s(this.b).setSmoothProgress(i2);
                this.a = (float) i2;
            }
        }
    }

    public final void a(float f) {
        if (SnifferResultsFragment.j(this.b) != 2 && f >= 0.0f && f <= 1.0f) {
            float f2 = ((f * 100.0f) + 100.0f) * 0.45f;
            if (this.a < f2) {
                SnifferResultsFragment.s(this.b).setSmoothProgress((int) f2);
                this.a = f2;
            }
        }
    }

    public final void c() {
        this.c = true;
    }

    public final void a(String str, SniffingPageResource sniffingPageResource) {
        this.c = false;
        a t = SnifferResultsFragment.t(this.b);
        t.a.put(str, sniffingPageResource);
        if (sniffingPageResource != null && sniffingPageResource.isGrouped) {
            Iterator it = sniffingPageResource.groups.iterator();
            while (it.hasNext()) {
                SniffingResourceGroup sniffingResourceGroup = (SniffingResourceGroup) it.next();
                if (!com.xunlei.xllib.b.d.a(sniffingResourceGroup.resources)) {
                    t.a.put(sniffingResourceGroup.realUrl, sniffingResourceGroup);
                }
            }
        }
        SnifferResultsFragment.a(this.b, SimpleLog.LOG_LEVEL_DEBUG);
        SnifferResultsFragment.a(this.b, new SnifferResultsFragment$j((byte) 0));
        SnifferResultsFragment$j snifferResultsFragment$j = (SnifferResultsFragment$j) SnifferResultsFragment.m(this.b);
        snifferResultsFragment$j.c = sniffingPageResource;
        new StringBuilder("setFinalPageRes error code: ").append(snifferResultsFragment$j.c.errorCode);
        if (sniffingPageResource != null) {
            if (sniffingPageResource.isGrouped) {
                snifferResultsFragment$j.b = 0;
            } else {
                snifferResultsFragment$j.b = 1;
            }
        }
        SnifferResultsFragment.m(this.b).a();
    }

    public final void a(String str) {
        SnifferResultsFragment.t(this.b).a(str);
        SnifferResultsFragment.a(this.b, SimpleLog.LOG_LEVEL_FATAL);
        SnifferResultsFragment.c(this.b);
    }

    public final void a(WebBackForwardList webBackForwardList) {
        if (webBackForwardList != null) {
            int size = webBackForwardList.getSize();
            int currentIndex = webBackForwardList.getCurrentIndex();
            if (currentIndex < size - 1) {
                WebHistoryItem itemAtIndex = webBackForwardList.getItemAtIndex(currentIndex + 1);
                SnifferResultsFragment$c snifferResultsFragment$g = new SnifferResultsFragment$g((byte) 0);
                snifferResultsFragment$g.b = itemAtIndex.getUrl();
                snifferResultsFragment$g.a();
                SnifferResultsFragment.a(this.b, snifferResultsFragment$g.b);
                SnifferResultsFragment.a(this.b, snifferResultsFragment$g);
            }
        }
    }

    public final void b(WebBackForwardList webBackForwardList) {
        if (webBackForwardList != null) {
            int currentIndex = webBackForwardList.getCurrentIndex();
            if (currentIndex > 0) {
                WebHistoryItem itemAtIndex = webBackForwardList.getItemAtIndex(currentIndex - 1);
                SnifferResultsFragment$c snifferResultsFragment$g = new SnifferResultsFragment$g((byte) 0);
                snifferResultsFragment$g.b = itemAtIndex.getUrl();
                SnifferResultsFragment.c(this.b, itemAtIndex.getUrl());
                snifferResultsFragment$g.a();
                SnifferResultsFragment.a(this.b, snifferResultsFragment$g.b);
                SnifferResultsFragment.a(this.b, snifferResultsFragment$g);
            }
        }
    }
}
