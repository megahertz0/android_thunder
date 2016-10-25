package com.xiaomi.smack;

import android.text.TextUtils;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xiaomi.push.service.x.b;
import com.xiaomi.smack.packet.a;
import com.xiaomi.smack.packet.d;
import com.xiaomi.smack.util.g;
import com.xunlei.analytics.b.c;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.HashMap;
import java.util.Map;
import org.android.agoo.common.AgooConstants;

public class k$a extends d {
    public k$a(b bVar, String str, a aVar) {
        Object obj;
        String a;
        Map hashMap = new HashMap();
        hashMap.put("challenge", str);
        hashMap.put("token", bVar.c);
        hashMap.put("chid", bVar.h);
        hashMap.put("from", bVar.b);
        hashMap.put(AgooConstants.MESSAGE_ID, k());
        hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_TO, "xiaomi.com");
        if (bVar.e) {
            hashMap.put("kick", c.f);
        } else {
            hashMap.put("kick", "0");
        }
        if (aVar == null || aVar.l() <= 0) {
            obj = null;
        } else {
            obj = String.format("conn:%1$d,t:%2$d", new Object[]{Integer.valueOf(aVar.j()), Long.valueOf(aVar.l())});
            hashMap.put("pf", obj);
            aVar.k();
            aVar.m();
        }
        if (TextUtils.isEmpty(bVar.f)) {
            hashMap.put("client_attrs", BuildConfig.VERSION_NAME);
        } else {
            hashMap.put("client_attrs", bVar.f);
        }
        if (TextUtils.isEmpty(bVar.g)) {
            hashMap.put("cloud_attrs", BuildConfig.VERSION_NAME);
        } else {
            hashMap.put("cloud_attrs", bVar.g);
        }
        if (bVar.d.equals("XIAOMI-PASS") || bVar.d.equals("XMPUSH-PASS")) {
            a = com.xiaomi.channel.commonutils.string.b.a(bVar.d, null, hashMap, bVar.i);
        } else {
            bVar.d.equals("XIAOMI-SASL");
            a = null;
        }
        l(bVar.h);
        n(bVar.b);
        m("xiaomi.com");
        o(bVar.a);
        a aVar2 = new a("token", null, null, null);
        aVar2.b(bVar.c);
        a(aVar2);
        a aVar3 = new a("kick", null, null, null);
        aVar3.b(bVar.e ? c.f : "0");
        a(aVar3);
        aVar2 = new a("sig", null, null, null);
        aVar2.b(a);
        a(aVar2);
        a aVar4 = new a(com.xunlei.download.proguard.c.b, null, null, null);
        if (TextUtils.isEmpty(bVar.d)) {
            aVar4.b("XIAOMI-SASL");
        } else {
            aVar4.b(bVar.d);
        }
        a(aVar4);
        aVar2 = new a("client_attrs", null, null, null);
        aVar2.b(bVar.f == null ? BuildConfig.VERSION_NAME : g.a(bVar.f));
        a(aVar2);
        aVar2 = new a("cloud_attrs", null, null, null);
        aVar2.b(bVar.g == null ? BuildConfig.VERSION_NAME : g.a(bVar.g));
        a(aVar2);
        if (!TextUtils.isEmpty(obj)) {
            aVar4 = new a("pf", null, null, null);
            aVar4.b(obj);
            a(aVar4);
        }
    }

    public String a() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<bind ");
        if (k() != null) {
            stringBuilder.append(new StringBuilder("id=\"").append(k()).append("\" ").toString());
        }
        if (m() != null) {
            stringBuilder.append("to=\"").append(g.a(m())).append("\" ");
        }
        if (n() != null) {
            stringBuilder.append("from=\"").append(g.a(n())).append("\" ");
        }
        if (l() != null) {
            stringBuilder.append("chid=\"").append(g.a(l())).append("\">");
        }
        if (q() != null) {
            for (a aVar : q()) {
                stringBuilder.append(aVar.d());
            }
        }
        stringBuilder.append("</bind>");
        return stringBuilder.toString();
    }
}
