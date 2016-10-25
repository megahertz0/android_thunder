package com.xunlei.thundersniffer.sniff.sniffer;

import android.content.Context;
import android.text.TextUtils;
import com.android.volley.f;
import com.android.volley.p;
import com.umeng.message.proguard.j;
import com.xunlei.analytics.b.c;
import com.xunlei.common.encrypt.CharsetConvert;
import com.xunlei.thundersniffer.sniff.SniffingResource;
import com.xunlei.thundersniffer.sniff.SniffingResource$Category;
import com.xunlei.thundersniffer.sniff.SniffingResourceGroup;
import com.xunlei.thundersniffer.sniff.sniffer.Sniffer.d;
import com.xunlei.thundersniffer.sniff.sniffer.SnifferProtocol.GetKey;
import com.xunlei.thundersniffer.sniff.sniffer.SnifferProtocol.SetKey;
import com.xunlei.thundersniffer.sniff.sniffer.SnifferSvrSendGroup.FileInfo;
import com.xunlei.thundersniffer.sniff.sniffer.SniffingDetailPageTask.LocalCacheOperation.CacheResult;
import com.xunlei.thundersniffer.sniff.sniffer.internal.HtmlRequest;
import com.xunlei.thundersniffer.sniff.sniffer.internal.SnifferJsonObjectRequest;
import com.xunlei.thundersniffer.sniff.sniffer.internal.server.SvrProtocol.SHubBatchQueryKeys;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import org.android.spdy.SpdyProtocol;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class SniffingDetailPageTask extends SniffingTask {
    private static String p;
    private Context A;
    private int B;
    b a;
    aj b;
    at c;
    OnFinishGetLocalCacheCallback d;
    ArrayList<z> e;
    LocalCacheStorer f;
    private al q;
    private ax r;
    private long s;
    private LocalCacheOperation t;
    private Timer u;
    private final int v;
    private boolean w;
    private boolean x;
    private boolean y;
    private p z;

    public static class LocalCacheOperation implements Runnable {
        private String a;
        public OnFinishGetLocalCacheCallback mOnFinishGetLocalCacheCallback;
        public CacheResult mResult;

        public static class CacheResult {
            public ArrayList<z> content;
            public long flags;
            public boolean isValid;
            public String pageTitle;
            public String pageUrl;
            public String realUrl;

            public CacheResult() {
                this.pageUrl = null;
                this.realUrl = null;
                this.isValid = false;
                this.content = null;
                this.pageTitle = null;
                this.flags = 0;
            }
        }

        public static interface OnFinishGetLocalCacheCallback {
            void onFinishGetLocalCacheCallback(String str, CacheResult cacheResult);
        }

        public LocalCacheOperation(String str, OnFinishGetLocalCacheCallback onFinishGetLocalCacheCallback) {
            this.mResult = new CacheResult();
            this.mOnFinishGetLocalCacheCallback = onFinishGetLocalCacheCallback;
            this.mResult.pageUrl = str;
            this.a = str;
        }

        private void a(String str) {
            Object a = SniffingDetailPageTask.a(str);
            SnifferCacheDatabase a2 = SnifferCacheDatabase.a();
            if (TextUtils.isEmpty(a) || a2 == null) {
                a();
            } else {
                a2.a(a, new u(this, str));
            }
        }

        private void a() {
            if (this.mOnFinishGetLocalCacheCallback != null) {
                this.mOnFinishGetLocalCacheCallback.onFinishGetLocalCacheCallback(this.mResult.pageUrl, this.mResult);
            }
            this.mResult = null;
            this.a = null;
            this.mOnFinishGetLocalCacheCallback = null;
        }

        public void run() {
            a(this.a);
        }
    }

    public static class LocalCacheStorer {
        private boolean a;
        public boolean mCacheEmptyList;
        public ArrayList mCacheList;
        public long mEexpiration;
        public SniffingPageInfo mPageInfo;

        public LocalCacheStorer(SniffingPageInfo sniffingPageInfo) {
            this.mCacheList = null;
            this.mCacheEmptyList = false;
            this.mEexpiration = 0;
            this.a = false;
            this.mPageInfo = sniffingPageInfo;
        }

        public void saveCache() {
            long j = 7200;
            int i = 0;
            if ((!this.mCacheEmptyList && this.mCacheList == null) || this.mPageInfo == null || this.a) {
                this.mCacheList = null;
                this.mPageInfo = null;
                this.a = true;
                return;
            }
            this.a = true;
            Object a = SniffingDetailPageTask.a(this.mPageInfo.a);
            SnifferCacheDatabase a2 = SnifferCacheDatabase.a();
            if (!(a2 == null || TextUtils.isEmpty(a))) {
                int i2;
                String toString;
                if (!this.mCacheEmptyList) {
                    i2 = 0;
                } else if (this.mEexpiration <= 0) {
                    j = 1800;
                    i2 = 404;
                } else if (this.mEexpiration < 7200) {
                    j = this.mEexpiration;
                    i2 = 404;
                } else {
                    i2 = 404;
                }
                if (this.mCacheList != null) {
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.putOpt(SHubBatchQueryKeys.url, this.mPageInfo.a);
                        jSONObject.putOpt("realUrl", this.mPageInfo.b);
                        jSONObject.putOpt("pageTitle", this.mPageInfo.d);
                        JSONArray jSONArray = new JSONArray();
                        while (i < this.mCacheList.size()) {
                            z zVar = (z) this.mCacheList.get(i);
                            JSONObject jSONObject2 = new JSONObject();
                            jSONObject2.putOpt("download_url", zVar.b);
                            jSONObject2.putOpt(GetKey.ORIGINAL_DOWNLOAD_URL, zVar.e);
                            jSONArray.put(jSONObject2);
                            i++;
                        }
                        jSONObject.putOpt("res_link_list", jSONArray);
                        toString = jSONObject.toString();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    a2.a(a, toString, (long) i2, j);
                    a2.c();
                }
                toString = null;
                a2.a(a, toString, (long) i2, j);
                a2.c();
            }
            this.mCacheList = null;
            this.mPageInfo = null;
        }
    }

    public static class VideoTagExtExcludesMatcher {
        private static final String[] a;

        static {
            a = new String[]{".letv", ".m3u8"};
        }

        public boolean excludes(String str) {
            if (a.length <= 0 || TextUtils.isEmpty(str)) {
                return false;
            }
            String[] strArr = a;
            int length = strArr.length;
            int i = 0;
            while (i < length) {
                String str2 = strArr[i];
                if (!str.endsWith(str2) && !str.contains(str2 + "?")) {
                    i++;
                }
                return true;
            }
            return false;
        }
    }

    private static class a {
        public static boolean a(at atVar) {
            boolean z = false;
            if (!(atVar.i == null || atVar.i.isEmpty())) {
                int i;
                int size = atVar.i.size();
                Iterator it = atVar.i.iterator();
                int i2 = 0;
                while (it.hasNext()) {
                    String f;
                    SniffingResource sniffingResource = (SniffingResource) it.next();
                    String str = sniffingResource.downloadUrl;
                    if (sniffingResource.downloadUrl.startsWith("thunder://")) {
                        f = com.xunlei.c.b.f(sniffingResource.downloadUrl);
                    } else {
                        f = str;
                    }
                    aj.a();
                    if (!ResLinkParser.b(f)) {
                        i = i2 + 1;
                        if (i > 10) {
                            break;
                        }
                    } else {
                        i = i2;
                    }
                    i2 = i;
                }
                i = i2;
                if (i > 10 || ((double) size) * 0.3d < ((double) i)) {
                    z = true;
                }
                atVar.m = z;
            }
            return z;
        }
    }

    static interface b {
        void a(String str, String str2, boolean z);
    }

    static {
        p = "Sniffer.SniffingDetailPageTask";
    }

    public SniffingDetailPageTask(SniffingPageInfo sniffingPageInfo, SniffingTask$TaskStateChangeListener sniffingTask$TaskStateChangeListener, p pVar, Context context, al alVar) {
        super(sniffingTask$TaskStateChangeListener);
        this.r = new ax();
        this.s = 0;
        this.a = new n(this);
        this.v = 10000;
        this.w = false;
        this.x = false;
        this.d = new t(this);
        this.y = false;
        this.B = 0;
        this.m = sniffingPageInfo;
        this.z = pVar;
        this.A = context;
        this.q = alVar;
        this.r.a = this.q.b;
        this.r.a(p, new StringBuilder("Start Detail ").append(sniffingPageInfo.a).toString());
    }

    public SniffingDetailPageTask(SniffingPageInfo sniffingPageInfo, SniffingTask$TaskStateChangeListener sniffingTask$TaskStateChangeListener, Context context, al alVar) {
        super(sniffingTask$TaskStateChangeListener);
        this.r = new ax();
        this.s = 0;
        this.a = new n(this);
        this.v = 10000;
        this.w = false;
        this.x = false;
        this.d = new t(this);
        this.y = false;
        this.B = 0;
        this.m = sniffingPageInfo;
        this.B = 3;
        this.A = context;
        this.q = alVar;
        this.r.a = this.q.b;
        this.r.a(p, new StringBuilder("Start Detail ").append(sniffingPageInfo.a).toString());
    }

    static String a(String str) {
        aj.a();
        String c = aj.c(str);
        if (!TextUtils.isEmpty(c)) {
            return b(c);
        }
        aj.a();
        Object d = aj.d(str);
        return !TextUtils.isEmpty(d) ? b(com.xunlei.c.b.g(d)) : b(com.xunlei.c.b.g(str));
    }

    private static String b(String str) {
        try {
            Object toString = new BigInteger(MessageDigest.getInstance("MD5").digest(str.getBytes(CharsetConvert.UTF_8))).abs().toString(SpdyProtocol.CUSTOM);
            return !TextUtils.isEmpty(toString) ? toString + c.f : null;
        } catch (Exception e) {
            return !TextUtils.isEmpty(null) ? null + c.f : null;
        } catch (Throwable th) {
            if (!TextUtils.isEmpty(null)) {
                new StringBuilder().append(null).append(c.f);
            }
        }
    }

    public final void run() {
        this.s = Thread.currentThread().getId();
        a(j);
        if (this.B == 0) {
            this.B = 2;
            if (this.m.m) {
                if (this.b == null) {
                    this.b = aj.a();
                }
                if (!TextUtils.isEmpty(a(this.m.a))) {
                    this.B = 1;
                }
            }
        }
        if (!d()) {
            if (this.B == 2) {
                this.r.b(p, "GetPage Start");
                ar.a(this.m.a, new m(this));
            }
            if (this.B == 3) {
                a(this.m, null, false);
            } else if (this.B == 4) {
                a(this.m, this.e, true);
            } else if (this.B == 5) {
                a(k);
                a();
            } else if (this.B == 1) {
                this.r.b(p, "GetLocalCache Start");
                this.t = new LocalCacheOperation(this.m.a, this.d);
                this.t.run();
            }
        }
    }

    private void a(SniffingPageInfo sniffingPageInfo, ArrayList<z> arrayList, boolean z) {
        ArrayList arrayList2;
        ArrayList arrayList3;
        ArrayList arrayList4;
        SnifferSvrSendGroup snifferSvrSendGroup;
        String str;
        int i = 0;
        if (this.b == null) {
            this.b = aj.a();
        }
        String str2 = sniffingPageInfo.b;
        at atVar = new at();
        atVar.e = sniffingPageInfo.a;
        atVar.f = str2;
        atVar.a = 1;
        atVar.o = SniffingResourceGroup.MATCHSCORE_LOW;
        if (sniffingPageInfo != null) {
            String a = this.b.a(sniffingPageInfo.a);
            if (!TextUtils.isEmpty(a)) {
                new StringBuilder("[Title] frUrl: ").append(a).append(" frHtml: ").append(sniffingPageInfo.d);
                sniffingPageInfo.d = aj.a(sniffingPageInfo.d, a);
                new StringBuilder("[Title] frUrl: ").append(a).append(" frHtml: ").append(sniffingPageInfo.d);
            }
            atVar.n = sniffingPageInfo.d;
            atVar.c = sniffingPageInfo.i;
            boolean z2 = sniffingPageInfo.m;
            atVar.h = sniffingPageInfo.e;
            atVar.g = sniffingPageInfo.h;
            boolean z3 = z2;
        } else {
            int i2 = 0;
        }
        if (z3) {
            i = sniffingPageInfo.n;
        }
        atVar.d = i;
        if (!z) {
            this.r.b(p, "Sniffing Start");
            aa a2 = ResLinkParser.a(sniffingPageInfo.b, sniffingPageInfo.c);
            ArrayList arrayList5 = a2.a;
            ArrayList arrayList6 = a2.b;
            arrayList2 = a2.c;
            sniffingPageInfo.c = null;
            this.r.b(p, "Sniffing End");
            arrayList3 = arrayList6;
            arrayList4 = arrayList5;
        } else if (arrayList != null) {
            ArrayList<z> arrayList7 = arrayList;
            arrayList3 = null;
            arrayList4 = null;
        } else {
            arrayList2 = null;
            arrayList3 = null;
            arrayList4 = null;
        }
        if (!z3 || sniffingPageInfo == null) {
            snifferSvrSendGroup = null;
            str = null;
        } else {
            String i3 = this.b.i(sniffingPageInfo.h);
            SnifferSvrSendGroup snifferSvrSendGroup2 = new SnifferSvrSendGroup();
            snifferSvrSendGroup2.d = str2;
            snifferSvrSendGroup2.a = aj.c(sniffingPageInfo.a);
            snifferSvrSendGroup2.b = this.b.h(sniffingPageInfo.h);
            snifferSvrSendGroup2.g = sniffingPageInfo.d;
            snifferSvrSendGroup = snifferSvrSendGroup2;
            str = i3;
        }
        a(atVar, arrayList2, sniffingPageInfo, str2, str, z);
        a(atVar, arrayList3, arrayList4, str2);
        a(snifferSvrSendGroup, atVar, sniffingPageInfo, str);
        new StringBuilder("url --> ").append(sniffingPageInfo.b);
        this.r.b(p, "SniffingResult");
        this.c = atVar;
        if (!(this.q == null || this.q.a == null || !this.q.a.getXunleiVodplayEnabled() || atVar.i == null)) {
            d.a(atVar.i, atVar.t).start();
        }
        if (!(this.q == null || this.q.a == null || !this.q.a.getSHubInfoEnabled() || atVar.i == null)) {
            d.b(atVar.i, atVar.t).start();
        }
        if (a.a(atVar)) {
            new StringBuilder("AntiSniff Site: ").append(atVar.f);
        }
        if (Thread.currentThread().getId() == this.s) {
            this.B = 5;
            a(k);
            a();
            return;
        }
        this.B = 5;
        a(h);
    }

    private void a(SnifferSvrSendGroup snifferSvrSendGroup, at atVar, SniffingPageInfo sniffingPageInfo, String str) {
        if (str != null && snifferSvrSendGroup != null) {
            if (atVar.i != null) {
                Iterator it = atVar.i.iterator();
                while (it.hasNext()) {
                    SniffingResource sniffingResource = (SniffingResource) it.next();
                    if (sniffingResource.category == SniffingResource$Category.VIDEO || sniffingResource.category == SniffingResource$Category.BT) {
                        snifferSvrSendGroup.a(new FileInfo(sniffingResource.downloadUrl, sniffingResource.originalDownloadUrL));
                    }
                }
            }
            if (sniffingPageInfo != null && sniffingPageInfo.f == 2) {
                ao aoVar = new ao(this.z);
                if (str != null && snifferSvrSendGroup != null && snifferSvrSendGroup.d != null) {
                    int size;
                    StringBuilder stringBuilder = new StringBuilder("sniffer.set send body (");
                    if (snifferSvrSendGroup.h != null) {
                        size = snifferSvrSendGroup.h.size();
                    } else {
                        size = 0;
                    }
                    stringBuilder.append(size).append("): -------------------- title: ").append(str).append(" ----- PageTitle: ").append(snifferSvrSendGroup.g);
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put(SetKey.TITLE, str);
                        JSONArray jSONArray = new JSONArray();
                        jSONArray.put(snifferSvrSendGroup.a());
                        jSONObject.put(SetKey.REFURLINFO, jSONArray);
                        new StringBuilder("sniffer.set send body: ").append(jSONObject.toString());
                        SnifferJsonObjectRequest snifferJsonObjectRequest = new SnifferJsonObjectRequest(1, new StringBuilder("http://interface.m.sjzhushou.com/sniffer/set").append(String.format("?rd=%d", new Object[]{Long.valueOf(System.currentTimeMillis())})).toString(), jSONObject, new ap(aoVar), new aq(aoVar));
                        snifferJsonObjectRequest.setShouldCache(false);
                        snifferJsonObjectRequest.setRetryPolicy(new f(5000, 1, 1.0f));
                        aoVar.a.a(snifferJsonObjectRequest);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void a(at atVar, ArrayList<z> arrayList, SniffingPageInfo sniffingPageInfo, String str, String str2, boolean z) {
        if (arrayList != null && !arrayList.isEmpty()) {
            ai aiVar;
            boolean resourceNameDedupeEnabled;
            try {
                aiVar = new ai(str2);
            } catch (IllegalArgumentException e) {
                aiVar = null;
            }
            HashSet hashSet = new HashSet();
            if (this.q == null || this.q.a == null) {
                Object obj = null;
            } else {
                resourceNameDedupeEnabled = this.q.a.getResourceNameDedupeEnabled();
            }
            List arrayList2 = new ArrayList();
            List arrayList3 = new ArrayList();
            for (int i = 0; i < arrayList.size(); i++) {
                SniffingResource sniffingResource = new SniffingResource();
                z zVar = (z) arrayList.get(i);
                sniffingResource.downloadUrl = zVar.b;
                sniffingResource.originalDownloadUrL = zVar.e;
                sniffingResource.resourceName = ResLinkParser.d(sniffingResource.downloadUrl);
                sniffingResource.canDownload = true;
                sniffingResource.sourceUrl = str;
                sniffingResource.format = ab.c(sniffingResource.resourceName);
                sniffingResource.category = ab.b(sniffingResource.resourceName);
                if (!TextUtils.isEmpty(sniffingResource.resourceName)) {
                    boolean a = ar.a(sniffingResource.downloadUrl);
                    if (a) {
                        sniffingResource.category = SniffingResource$Category.BT;
                        sniffingResource.format = "magnet";
                    }
                    sniffingResource.resourceType = ar.b(sniffingResource.downloadUrl);
                    if (a || (sniffingResource.resourceName != null && sniffingResource.resourceName.endsWith(".torrent"))) {
                        sniffingResource.resourceType = 3;
                    }
                    if (TextUtils.isEmpty(sniffingResource.resourceName)) {
                        new StringBuilder("Skip URL:").append(sniffingResource.downloadUrl);
                    } else if (TextUtils.isEmpty(str2) || !sniffingResource.resourceName.contains(str2)) {
                        boolean z2 = true;
                        if (!(aiVar == null || a)) {
                            z2 = aiVar.a(sniffingResource.resourceName);
                        }
                        if (z2) {
                            z2 = a(sniffingResource.downloadUrl, sniffingResource.resourceName, sniffingResource.format, sniffingResource.category, sniffingResource.originalDownloadUrL);
                        }
                        if (!z2) {
                            new StringBuilder("Skip URL:(").append(sniffingResource.resourceName).append(j.t).append(sniffingResource.downloadUrl);
                        } else if (!resourceNameDedupeEnabled || sniffingResource.resourceType == 3) {
                            arrayList3.add(sniffingResource);
                        } else if (!hashSet.contains(sniffingResource.resourceName)) {
                            arrayList3.add(sniffingResource);
                            hashSet.add(sniffingResource.resourceName);
                        }
                    } else if (!a(sniffingResource.downloadUrl, sniffingResource.resourceName, sniffingResource.format, sniffingResource.category, sniffingResource.originalDownloadUrL)) {
                        new StringBuilder("Skip URL:(").append(sniffingResource.resourceName).append(j.t).append(sniffingResource.downloadUrl);
                    } else if (!resourceNameDedupeEnabled || sniffingResource.resourceType == 3) {
                        arrayList2.add(sniffingResource);
                    } else if (!hashSet.contains(sniffingResource.resourceName)) {
                        arrayList2.add(sniffingResource);
                        hashSet.add(sniffingResource.resourceName);
                    }
                }
            }
            if (g()) {
                this.r.b(p, "Sorting Start");
            }
            if (!arrayList2.isEmpty()) {
                atVar.o = SniffingResourceGroup.MATCHSCORE_NORMAL;
                if (g()) {
                    new StringBuilder("Sorting: ").append(arrayList2.size());
                    ar.a(arrayList2);
                }
            }
            if (!arrayList3.isEmpty() && g()) {
                new StringBuilder("Sorting: ").append(arrayList3.size());
                ar.a(arrayList3);
            }
            arrayList2.addAll(arrayList3);
            if (g()) {
                this.r.b(p, new StringBuilder("Sorting End - count: ").append(arrayList2.size()).toString());
            }
            new StringBuilder("Sniffing List(Page):").append(arrayList2.size());
            if (!(z || sniffingPageInfo == null)) {
                this.y = true;
                this.f = new LocalCacheStorer(sniffingPageInfo);
                this.f.mCacheList = arrayList;
            }
            atVar.i = arrayList2;
        } else if (!z && sniffingPageInfo != null) {
            this.y = true;
            this.f = new LocalCacheStorer(sniffingPageInfo);
            this.f.mCacheEmptyList = true;
            this.f.mCacheList = null;
            if (sniffingPageInfo.f != 2) {
                this.f.mEexpiration = 60;
            }
        }
    }

    private void a(at atVar, ArrayList<z> arrayList, ArrayList<String> arrayList2, String str) {
        if (arrayList != null && !arrayList.isEmpty()) {
            HashSet hashSet;
            atVar.a = 1;
            Object arrayList3 = new ArrayList();
            if (arrayList2 != null) {
                hashSet = new HashSet(arrayList2);
            } else {
                hashSet = null;
            }
            VideoTagExtExcludesMatcher videoTagExtExcludesMatcher = new VideoTagExtExcludesMatcher();
            for (int i = 0; i < arrayList.size(); i++) {
                z zVar = (z) arrayList.get(i);
                if (hashSet == null || !hashSet.contains(zVar.b)) {
                    Object obj;
                    SniffingResource sniffingResource = new SniffingResource();
                    sniffingResource.category = SniffingResource$Category.VIDEO;
                    sniffingResource.downloadUrl = zVar.b;
                    sniffingResource.originalDownloadUrL = zVar.e;
                    sniffingResource.resourceName = ResLinkParser.d(sniffingResource.downloadUrl);
                    sniffingResource.canDownload = true;
                    sniffingResource.sourceUrl = str;
                    sniffingResource.format = ab.c(sniffingResource.resourceName);
                    if (TextUtils.isEmpty(sniffingResource.resourceName)) {
                        sniffingResource.resourceName = ResLinkParser.d(sniffingResource.downloadUrl + ".mp4");
                        if (TextUtils.isEmpty(sniffingResource.resourceName)) {
                            sniffingResource.resourceName = new StringBuilder("\u672a\u77e5\u540d\u79f0").append(i + 1).append(".mp4").toString();
                        }
                        sniffingResource.format = "mp4";
                    }
                    if (videoTagExtExcludesMatcher.excludes(sniffingResource.resourceName)) {
                        obj = null;
                    } else {
                        int i2 = 1;
                    }
                    if (!a(sniffingResource.downloadUrl, sniffingResource.resourceName, sniffingResource.format, sniffingResource.category, sniffingResource.originalDownloadUrL)) {
                        obj = null;
                    }
                    if (obj != null) {
                        arrayList3.add(sniffingResource);
                    }
                }
            }
            if (atVar.i != null) {
                atVar.i.addAll(arrayList3);
            } else {
                atVar.i = arrayList3;
            }
        }
    }

    private boolean g() {
        return (this.q == null || this.q.a == null || !this.q.a.getResourceSortEnabled()) ? false : true;
    }

    final void a() {
        if (this.f != null && this.y) {
            this.f.saveCache();
        }
        this.f = null;
    }

    private boolean a(String str, String str2, String str3, SniffingResource$Category sniffingResource$Category, String str4) {
        return this.q.c != null ? this.q.c.accept(str, str2, str3, sniffingResource$Category, str4) : true;
    }

    static /* synthetic */ void a(SniffingDetailPageTask sniffingDetailPageTask, String str, b bVar) {
        long currentTimeMillis = System.currentTimeMillis();
        HtmlRequest htmlRequest = new HtmlRequest(0, str, new o(sniffingDetailPageTask, str, bVar, currentTimeMillis), new p(sniffingDetailPageTask, str, bVar, currentTimeMillis));
        htmlRequest.setRetryPolicy(new f(3000, 1, 1.0f));
        sniffingDetailPageTask.z.a(htmlRequest);
        sniffingDetailPageTask.u = new Timer();
        sniffingDetailPageTask.u.schedule(new q(sniffingDetailPageTask, str, bVar, currentTimeMillis), 10000);
    }

    static /* synthetic */ void b(SniffingDetailPageTask sniffingDetailPageTask) {
        if (sniffingDetailPageTask.u != null) {
            try {
                sniffingDetailPageTask.u.cancel();
                sniffingDetailPageTask.u.purge();
                sniffingDetailPageTask.u = null;
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
        }
    }

    static /* synthetic */ void b(SniffingDetailPageTask sniffingDetailPageTask, String str, b bVar) {
        HtmlRequest htmlRequest = new HtmlRequest(0, str, new r(sniffingDetailPageTask, bVar, str), new s(sniffingDetailPageTask, str, bVar));
        htmlRequest.setRetryPolicy(new f(3000, 1, 1.0f));
        sniffingDetailPageTask.z.a(htmlRequest);
    }
}
