package com.inmobi.ads;

import java.util.Map;

// compiled from: NativeStrandTracker.java
final class ai {
    private b a;
    private String b;
    private int c;
    private a d;
    private Map<String, String> e;

    // compiled from: NativeStrandTracker.java
    enum a {
        TRACKER_EVENT_TYPE_UNKNOWN,
        TRACKER_EVENT_TYPE_LOAD,
        TRACKER_EVENT_TYPE_CLIENT_FILL,
        TRACKER_EVENT_TYPE_RENDER,
        TRACKER_EVENT_TYPE_PAGE_VIEW,
        TRACKER_EVENT_TYPE_CLICK;

        static {
            a = new a("TRACKER_EVENT_TYPE_UNKNOWN", 0);
            b = new a("TRACKER_EVENT_TYPE_LOAD", 1);
            c = new a("TRACKER_EVENT_TYPE_CLIENT_FILL", 2);
            d = new a("TRACKER_EVENT_TYPE_RENDER", 3);
            e = new a("TRACKER_EVENT_TYPE_PAGE_VIEW", 4);
            f = new a("TRACKER_EVENT_TYPE_CLICK", 5);
            g = new a[]{a, b, c, d, e, f};
        }
    }

    // compiled from: NativeStrandTracker.java
    enum b {
        TRACKER_TYPE_UNKNOWN_OR_UNSUPPORTED,
        TRACKER_TYPE_URL_PING,
        TRACKER_TYPE_URL_WEBVIEW_PING,
        TRACKER_TYPE_HTML_SCRIPT;

        static {
            a = new b("TRACKER_TYPE_UNKNOWN_OR_UNSUPPORTED", 0);
            b = new b("TRACKER_TYPE_URL_PING", 1);
            c = new b("TRACKER_TYPE_URL_WEBVIEW_PING", 2);
            d = new b("TRACKER_TYPE_HTML_SCRIPT", 3);
            e = new b[]{a, b, c, d};
        }
    }

    public ai(String str, int i, a aVar, Map<String, String> map) {
        this(b.b, str, i, aVar, map);
    }

    ai(b bVar, String str, int i, a aVar, Map<String, String> map) {
        this.a = bVar;
        this.b = str.trim();
        this.c = i;
        this.d = aVar;
        this.e = map;
    }

    public final String a() {
        return this.b;
    }

    public final a b() {
        return this.d;
    }

    public final Map<String, String> c() {
        return this.e;
    }
}
