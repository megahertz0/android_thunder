package com.xunlei.downloadprovider.model.protocol.f;

import android.text.TextUtils;
import com.umeng.message.proguard.j;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.downloadprovider.b.c.h;
import com.xunlei.downloadprovider.model.protocol.f.c.a;
import com.xunlei.thundersniffer.sniff.sniffer.SnifferProtocol.SetKey;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.ArrayList;
import org.android.agoo.common.AgooConstants;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

// compiled from: UpdateInfoParser.java
public final class d extends h {
    private c c;
    private a d;

    public d() {
        this.c = new c();
        this.d = null;
    }

    public final void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
        super.startElement(str, str2, str3, attributes);
        if ("update_list".equals(str2)) {
            if (this.c.q == null) {
                this.c.q = new ArrayList();
            }
        } else if ("package_name".equals(str2)) {
            int parseInt;
            this.d = new a();
            try {
                parseInt = Integer.parseInt(attributes.getValue("seq"));
            } catch (NumberFormatException e) {
                NumberFormatException numberFormatException = e;
                parseInt = Integer.MAX_VALUE;
                numberFormatException.printStackTrace();
            }
            this.d.b = parseInt;
        }
    }

    public final void endElement(String str, String str2, String str3) throws SAXException {
        int i = 1;
        if (str2.equals("latest_version")) {
            this.c.a = this.b.toString().trim();
        } else if (str2.equals("this_version")) {
            this.c.b = this.b.toString().trim();
        } else if (str2.equals("value")) {
            trim = this.b.toString().trim();
            if (trim.equals(BuildConfig.VERSION_NAME)) {
                this.c.c = 0;
            } else {
                this.c.c = Integer.parseInt(trim);
            }
        } else if (str2.equals(SocializeProtocolConstants.PROTOCOL_KEY_DATA)) {
            this.c.d = this.b.toString().trim();
        } else if (str2.equals(AgooConstants.MESSAGE_FLAG)) {
            this.c.e = this.b.toString().trim();
        } else if (str2.equals("latest_url")) {
            this.c.f = this.b.toString().trim();
        } else if (str2.equals(j.C)) {
            this.c.h = this.b.toString().trim();
        } else if (str2.equals(SetKey.TITLE)) {
            this.c.g = this.b.toString().trim();
        } else if (str2.equals("bar_message")) {
            this.c.p = this.b.toString().trim();
        } else if (str2.equals("cancel_text")) {
            this.c.r = this.b.toString().trim();
        } else if (str2.equals("confirm_text")) {
            this.c.s = this.b.toString().trim();
        } else if (str2.equals("introduction")) {
            this.c.i = this.b.toString().trim();
        } else if (str2.equals("description")) {
            this.c.j = this.b.toString().trim();
        } else if (str2.equals("circle")) {
            trim = this.b.toString().trim();
            if (trim.equals(BuildConfig.VERSION_NAME)) {
                this.c.k = 1;
            } else {
                this.c.k = (long) Integer.parseInt(trim);
            }
        } else if (str2.equals("server_time")) {
            trim = this.b.toString().trim();
            if (trim.equals(BuildConfig.VERSION_NAME)) {
                this.c.l = 0;
            } else {
                this.c.l = (long) Integer.parseInt(trim);
            }
        } else if ("update_from".equals(str2)) {
            try {
                toString = this.b.toString();
                if (!TextUtils.isEmpty(toString)) {
                    i = Integer.parseInt(toString.trim());
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            this.c.m = i;
        } else if ("display_type".equals(str2)) {
            try {
                toString = this.b.toString();
                if (!TextUtils.isEmpty(toString)) {
                    i = Integer.parseInt(toString.trim());
                }
            } catch (NumberFormatException e2) {
                e2.printStackTrace();
            }
            this.c.n = i;
        } else if ("max_count".equals(str2)) {
            try {
                toString = this.b.toString();
                if (!TextUtils.isEmpty(toString)) {
                    i = Integer.parseInt(toString.trim());
                    this.c.o = i;
                }
            } catch (NumberFormatException e3) {
                e3.printStackTrace();
            }
            i = 3;
            this.c.o = i;
        } else if ("package_name".equals(str2)) {
            toString = this.b.toString();
            if (!(this.c.q == null || !(this.d instanceof a) || TextUtils.isEmpty(toString))) {
                this.d.a = toString.trim();
                this.c.q.add(this.d);
                this.d = null;
            }
        }
        super.endElement(str, str2, str3);
    }

    public final void endDocument() throws SAXException {
        super.endDocument();
        this.a = this.c;
    }
}
