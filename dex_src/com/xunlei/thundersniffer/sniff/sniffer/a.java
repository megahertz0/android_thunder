package com.xunlei.thundersniffer.sniff.sniffer;

import com.xunlei.thundersniffer.sniff.sniffer.ResLinkParser.HtmlParser.Callback;

final class a implements Callback {
    a() {
    }

    public final boolean handleHrefLink(String str, int i, String str2) {
        return ResLinkParser.a(str);
    }

    public final boolean handleVideoLink(String str, int i, String str2) {
        return true;
    }
}
