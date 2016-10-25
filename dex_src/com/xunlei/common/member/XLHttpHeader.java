package com.xunlei.common.member;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.ParseException;

public class XLHttpHeader implements Header {
    private String hName;
    private String hValue;

    public XLHttpHeader(String str, String str2) {
        this.hName = null;
        this.hValue = null;
        this.hName = str;
        this.hValue = str2;
    }

    public HeaderElement[] getElements() throws ParseException {
        return null;
    }

    public String getName() {
        return this.hName;
    }

    public String getValue() {
        return this.hValue;
    }
}
