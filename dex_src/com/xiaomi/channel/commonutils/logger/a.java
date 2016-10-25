package com.xiaomi.channel.commonutils.logger;

import org.android.agoo.common.AgooConstants;

public class a implements LoggerInterface {
    private String a;

    public a() {
        this.a = AgooConstants.MESSAGE_SYSTEM_SOURCE_XIAOMI;
    }

    public void log(String str) {
    }

    public void log(String str, Throwable th) {
    }

    public void setTag(String str) {
        this.a = str;
    }
}
