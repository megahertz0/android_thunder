package com.xunlei.downloadprovider.ad.home.a;

import android.content.Context;
import com.xunlei.downloadprovider.ad.home.ui.ADItemView.AD_LAYOUT_TYPE;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: BaseHomeADExecutor.java
public abstract class a extends com.xunlei.downloadprovider.ad.common.b.a {
    protected int b;
    protected Context c;
    protected String d;
    private com.xunlei.downloadprovider.homepage.choiceness.a.a.a e;

    public a(Context context, com.xunlei.downloadprovider.homepage.choiceness.a.a.a aVar) {
        this.b = 3;
        this.e = aVar;
        this.c = context;
        this.d = aVar.d;
    }

    protected final String c() {
        switch (this.e.b) {
            case SimpleLog.LOG_LEVEL_ERROR:
                return "1117";
            case SimpleLog.LOG_LEVEL_FATAL:
                return "1116";
            case SpdyProtocol.CUSTOM:
                return "1136";
            default:
                return "1116";
        }
    }

    protected final AD_LAYOUT_TYPE d() {
        switch (this.e.b) {
            case SimpleLog.LOG_LEVEL_ERROR:
                return AD_LAYOUT_TYPE.SHORT_VOD_TYPE_VIEW;
            case SimpleLog.LOG_LEVEL_FATAL:
                return AD_LAYOUT_TYPE.IMAGE_TYPE_VIEW;
            case SpdyProtocol.CUSTOM:
                return AD_LAYOUT_TYPE.PLAY_VOD_TYPE_VIEW;
            default:
                return AD_LAYOUT_TYPE.IMAGE_TYPE_VIEW;
        }
    }

    public void b() {
    }
}
