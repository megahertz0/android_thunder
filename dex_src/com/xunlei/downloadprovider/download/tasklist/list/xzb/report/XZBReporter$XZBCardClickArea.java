package com.xunlei.downloadprovider.download.tasklist.list.xzb.report;

import com.umeng.socialize.net.utils.SocializeProtocolConstants;

public enum XZBReporter$XZBCardClickArea {
    bar,
    button,
    extend;

    static {
        bar = new XZBReporter$XZBCardClickArea("bar", 0);
        button = new XZBReporter$XZBCardClickArea("button", 1);
        extend = new XZBReporter$XZBCardClickArea(SocializeProtocolConstants.PROTOCOL_KEY_EXTEND_ARGS, 2);
        a = new XZBReporter$XZBCardClickArea[]{bar, button, extend};
    }
}
