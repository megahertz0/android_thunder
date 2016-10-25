package com.xiaomi.smack.packet;

import android.os.Bundle;
import android.os.Parcelable;
import com.umeng.socialize.common.SocializeConstants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class h {
    private int a;
    private String b;
    private String c;
    private String d;
    private String e;
    private List<a> f;

    public h(int i, String str, String str2, String str3, String str4, List<a> list) {
        this.f = null;
        this.a = i;
        this.b = str;
        this.d = str2;
        this.c = str3;
        this.e = str4;
        this.f = list;
    }

    public h(Bundle bundle) {
        this.f = null;
        this.a = bundle.getInt("ext_err_code");
        if (bundle.containsKey("ext_err_type")) {
            this.b = bundle.getString("ext_err_type");
        }
        this.c = bundle.getString("ext_err_cond");
        this.d = bundle.getString("ext_err_reason");
        this.e = bundle.getString("ext_err_msg");
        Parcelable[] parcelableArray = bundle.getParcelableArray("ext_exts");
        if (parcelableArray != null) {
            this.f = new ArrayList(parcelableArray.length);
            int length = parcelableArray.length;
            for (int i = 0; i < length; i++) {
                a a = a.a((Bundle) parcelableArray[i]);
                if (a != null) {
                    this.f.add(a);
                }
            }
        }
    }

    public h(a aVar) {
        this.f = null;
        a(aVar);
        this.e = null;
    }

    private void a(a aVar) {
        this.c = a.a(aVar);
    }

    public String a() {
        return this.d;
    }

    public String b() {
        return this.b;
    }

    public Bundle c() {
        Bundle bundle = new Bundle();
        if (this.b != null) {
            bundle.putString("ext_err_type", this.b);
        }
        bundle.putInt("ext_err_code", this.a);
        if (this.d != null) {
            bundle.putString("ext_err_reason", this.d);
        }
        if (this.c != null) {
            bundle.putString("ext_err_cond", this.c);
        }
        if (this.e != null) {
            bundle.putString("ext_err_msg", this.e);
        }
        if (this.f != null) {
            Parcelable[] parcelableArr = new Parcelable[this.f.size()];
            int i = 0;
            for (a aVar : this.f) {
                int i2;
                Bundle e = aVar.e();
                if (e != null) {
                    i2 = i + 1;
                    parcelableArr[i] = e;
                } else {
                    i2 = i;
                }
                i = i2;
            }
            bundle.putParcelableArray("ext_exts", parcelableArr);
        }
        return bundle;
    }

    public String d() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<error code=\"").append(this.a).append(com.alipay.sdk.util.h.f);
        if (this.b != null) {
            stringBuilder.append(" type=\"");
            stringBuilder.append(this.b);
            stringBuilder.append(com.alipay.sdk.util.h.f);
        }
        if (this.d != null) {
            stringBuilder.append(" reason=\"");
            stringBuilder.append(this.d);
            stringBuilder.append(com.alipay.sdk.util.h.f);
        }
        stringBuilder.append(">");
        if (this.c != null) {
            stringBuilder.append("<").append(this.c);
            stringBuilder.append(" xmlns=\"urn:ietf:params:xml:ns:xmpp-stanzas\"/>");
        }
        if (this.e != null) {
            stringBuilder.append("<text xml:lang=\"en\" xmlns=\"urn:ietf:params:xml:ns:xmpp-stanzas\">");
            stringBuilder.append(this.e);
            stringBuilder.append("</text>");
        }
        for (a aVar : e()) {
            stringBuilder.append(aVar.d());
        }
        stringBuilder.append("</error>");
        return stringBuilder.toString();
    }

    public synchronized List<a> e() {
        return this.f == null ? Collections.emptyList() : Collections.unmodifiableList(this.f);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.c != null) {
            stringBuilder.append(this.c);
        }
        stringBuilder.append(SocializeConstants.OP_OPEN_PAREN).append(this.a).append(SocializeConstants.OP_CLOSE_PAREN);
        if (this.e != null) {
            stringBuilder.append(" ").append(this.e);
        }
        return stringBuilder.toString();
    }
}
