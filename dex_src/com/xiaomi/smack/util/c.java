package com.xiaomi.smack.util;

import android.text.TextUtils;
import com.umeng.message.proguard.j;
import com.umeng.socialize.media.WeiXinShareContent;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.umeng.socialize.utils.OauthHelper;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xiaomi.push.service.ad;
import com.xiaomi.push.service.x;
import com.xiaomi.smack.k;
import com.xiaomi.smack.p;
import com.xiaomi.smack.packet.a;
import com.xiaomi.smack.packet.b;
import com.xiaomi.smack.packet.d;
import com.xiaomi.smack.packet.f;
import com.xiaomi.smack.packet.f$a;
import com.xiaomi.smack.packet.g;
import com.xiaomi.smack.packet.h;
import com.xiaomi.smack.packet.h$a;
import com.xunlei.xiazaibao.BuildConfig;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.android.agoo.common.AgooConstants;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class c {
    private static XmlPullParser a;

    static {
        a = null;
    }

    public static a a(String str, String str2, XmlPullParser xmlPullParser) {
        Object a = com.xiaomi.smack.provider.c.a().a("all", "xm:chat");
        return (a == null || !(a instanceof com.xiaomi.push.service.c)) ? null : ((com.xiaomi.push.service.c) a).b(xmlPullParser);
    }

    public static b a(XmlPullParser xmlPullParser, com.xiaomi.smack.a aVar) {
        b bVar;
        String attributeValue = xmlPullParser.getAttributeValue(BuildConfig.VERSION_NAME, AgooConstants.MESSAGE_ID);
        String attributeValue2 = xmlPullParser.getAttributeValue(BuildConfig.VERSION_NAME, SocializeProtocolConstants.PROTOCOL_KEY_TO);
        String attributeValue3 = xmlPullParser.getAttributeValue(BuildConfig.VERSION_NAME, "from");
        String attributeValue4 = xmlPullParser.getAttributeValue(BuildConfig.VERSION_NAME, "chid");
        b.a a = b.a.a(xmlPullParser.getAttributeValue(BuildConfig.VERSION_NAME, AgooConstants.MESSAGE_TYPE));
        Map hashMap = new HashMap();
        for (int i = 0; i < xmlPullParser.getAttributeCount(); i++) {
            String attributeName = xmlPullParser.getAttributeName(i);
            hashMap.put(attributeName, xmlPullParser.getAttributeValue(BuildConfig.VERSION_NAME, attributeName));
        }
        Object obj = null;
        h hVar = null;
        b bVar2 = null;
        while (obj == null) {
            int next = xmlPullParser.next();
            if (next == 2) {
                String name = xmlPullParser.getName();
                String namespace = xmlPullParser.getNamespace();
                if (name.equals(XiaomiOAuthConstants.EXTRA_ERROR_CODE_2)) {
                    hVar = e(xmlPullParser);
                } else {
                    bVar2 = new b();
                    bVar2.a(a(name, namespace, xmlPullParser));
                }
            } else if (next == 3 && xmlPullParser.getName().equals("iq")) {
                obj = 1;
            }
        }
        if (bVar2 != null) {
            bVar = bVar2;
        } else if (b.a.a == a || b.a.b == a) {
            d dVar = new d();
            dVar.k(attributeValue);
            dVar.m(attributeValue3);
            dVar.n(attributeValue2);
            dVar.a(b.a.d);
            dVar.l(attributeValue4);
            dVar.a(new h(h$a.e));
            aVar.a(dVar);
            com.xiaomi.channel.commonutils.logger.b.d("iq usage error. send packet in packet parser.");
            return null;
        } else {
            bVar = new e();
        }
        bVar.k(attributeValue);
        bVar.m(attributeValue2);
        bVar.l(attributeValue4);
        bVar.n(attributeValue3);
        bVar.a(a);
        bVar.a(hVar);
        bVar.a(hashMap);
        return bVar;
    }

    public static d a(XmlPullParser xmlPullParser) {
        Object obj = null;
        String str = null;
        String attributeValue;
        boolean z;
        if (com.xunlei.analytics.b.c.f.equals(xmlPullParser.getAttributeValue(BuildConfig.VERSION_NAME, "s"))) {
            attributeValue = xmlPullParser.getAttributeValue(BuildConfig.VERSION_NAME, "chid");
            String attributeValue2 = xmlPullParser.getAttributeValue(BuildConfig.VERSION_NAME, AgooConstants.MESSAGE_ID);
            String attributeValue3 = xmlPullParser.getAttributeValue(BuildConfig.VERSION_NAME, "from");
            String attributeValue4 = xmlPullParser.getAttributeValue(BuildConfig.VERSION_NAME, SocializeProtocolConstants.PROTOCOL_KEY_TO);
            String attributeValue5 = xmlPullParser.getAttributeValue(BuildConfig.VERSION_NAME, AgooConstants.MESSAGE_TYPE);
            x.b b = x.a().b(attributeValue, attributeValue4);
            x.b b2 = b == null ? x.a().b(attributeValue, attributeValue3) : b;
            if (b2 == null) {
                throw new p("the channel id is wrong while receiving a encrypted message");
            }
            Object obj2 = null;
            d dVar = null;
            while (!z) {
                int next = xmlPullParser.next();
                if (next == 2) {
                    if (!"s".equals(xmlPullParser.getName())) {
                        throw new p("error while receiving a encrypted message with wrong format");
                    } else if (xmlPullParser.next() != 4) {
                        throw new p("error while receiving a encrypted message with wrong format");
                    } else {
                        String text = xmlPullParser.getText();
                        if (!"5".equals(attributeValue) && !"6".equals(attributeValue)) {
                            a(ad.b(ad.a(b2.i, attributeValue2), text));
                            a.next();
                            dVar = a(a);
                        }
                        com.xiaomi.smack.packet.c cVar = new com.xiaomi.smack.packet.c();
                        cVar.l(attributeValue);
                        cVar.b(true);
                        cVar.n(attributeValue3);
                        cVar.m(attributeValue4);
                        cVar.k(attributeValue2);
                        cVar.f(attributeValue5);
                        a aVar = new a("s", null, null, null);
                        aVar.b(text);
                        cVar.a(aVar);
                        return cVar;
                    }
                } else if (next == 3 && xmlPullParser.getName().equals(j.C)) {
                    z = true;
                }
            }
            if (dVar != null) {
                return dVar;
            }
            throw new p("error while receiving a encrypted message with wrong format");
        }
        com.xiaomi.smack.packet.c cVar2 = new com.xiaomi.smack.packet.c();
        String attributeValue6 = xmlPullParser.getAttributeValue(BuildConfig.VERSION_NAME, AgooConstants.MESSAGE_ID);
        if (attributeValue6 == null) {
            attributeValue6 = "ID_NOT_AVAILABLE";
        }
        cVar2.k(attributeValue6);
        cVar2.m(xmlPullParser.getAttributeValue(BuildConfig.VERSION_NAME, SocializeProtocolConstants.PROTOCOL_KEY_TO));
        cVar2.n(xmlPullParser.getAttributeValue(BuildConfig.VERSION_NAME, "from"));
        cVar2.l(xmlPullParser.getAttributeValue(BuildConfig.VERSION_NAME, "chid"));
        cVar2.a(xmlPullParser.getAttributeValue(BuildConfig.VERSION_NAME, OauthHelper.APP_ID));
        try {
            obj2 = xmlPullParser.getAttributeValue(BuildConfig.VERSION_NAME, "transient");
        } catch (Exception e) {
            obj2 = null;
        }
        try {
            Object attributeValue7 = xmlPullParser.getAttributeValue(BuildConfig.VERSION_NAME, "seq");
            if (!TextUtils.isEmpty(attributeValue7)) {
                cVar2.b(attributeValue7);
            }
        } catch (Exception e2) {
        }
        try {
            attributeValue7 = xmlPullParser.getAttributeValue(BuildConfig.VERSION_NAME, "mseq");
            if (!TextUtils.isEmpty(attributeValue7)) {
                cVar2.c(attributeValue7);
            }
        } catch (Exception e3) {
        }
        try {
            attributeValue7 = xmlPullParser.getAttributeValue(BuildConfig.VERSION_NAME, "fseq");
            if (!TextUtils.isEmpty(attributeValue7)) {
                cVar2.d(attributeValue7);
            }
        } catch (Exception e4) {
        }
        try {
            attributeValue7 = xmlPullParser.getAttributeValue(BuildConfig.VERSION_NAME, "status");
            if (!TextUtils.isEmpty(attributeValue7)) {
                cVar2.e(attributeValue7);
            }
        } catch (Exception e5) {
        }
        z = !TextUtils.isEmpty(obj2) && obj2.equalsIgnoreCase("true");
        cVar2.a(z);
        cVar2.f(xmlPullParser.getAttributeValue(BuildConfig.VERSION_NAME, AgooConstants.MESSAGE_TYPE));
        attributeValue6 = g(xmlPullParser);
        if (attributeValue6 == null || BuildConfig.VERSION_NAME.equals(attributeValue6.trim())) {
            d.u();
        } else {
            cVar2.j(attributeValue6);
        }
        boolean z2;
        while (!z2) {
            int next2 = xmlPullParser.next();
            if (next2 == 2) {
                attributeValue = xmlPullParser.getName();
                attributeValue6 = xmlPullParser.getNamespace();
                if (TextUtils.isEmpty(attributeValue6)) {
                    attributeValue6 = "xm";
                }
                if (attributeValue.equals("subject")) {
                    g(xmlPullParser);
                    cVar2.g(f(xmlPullParser));
                } else if (attributeValue.equals(AgooConstants.MESSAGE_BODY)) {
                    obj2 = xmlPullParser.getAttributeValue(BuildConfig.VERSION_NAME, "encode");
                    attributeValue = f(xmlPullParser);
                    if (TextUtils.isEmpty(obj2)) {
                        cVar2.h(attributeValue);
                    } else {
                        cVar2.a(attributeValue, obj2);
                    }
                } else if (attributeValue.equals("thread")) {
                    if (str == null) {
                        str = xmlPullParser.nextText();
                    }
                } else if (attributeValue.equals(XiaomiOAuthConstants.EXTRA_ERROR_CODE_2)) {
                    cVar2.a(e(xmlPullParser));
                } else {
                    cVar2.a(a(attributeValue, attributeValue6, xmlPullParser));
                }
            } else {
                z = (next2 == 3 && xmlPullParser.getName().equals(j.C)) ? true : z2;
                z2 = z;
            }
        }
        cVar2.i(str);
        return cVar2;
    }

    private static void a(byte[] bArr) {
        if (a == null) {
            try {
                XmlPullParser newPullParser = XmlPullParserFactory.newInstance().newPullParser();
                a = newPullParser;
                newPullParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }
        }
        a.setInput(new InputStreamReader(new ByteArrayInputStream(bArr)));
    }

    public static f b(XmlPullParser xmlPullParser) {
        f.b bVar = f.b.a;
        String attributeValue = xmlPullParser.getAttributeValue(BuildConfig.VERSION_NAME, AgooConstants.MESSAGE_TYPE);
        if (!(attributeValue == null || attributeValue.equals(BuildConfig.VERSION_NAME))) {
            try {
                bVar = f.b.valueOf(attributeValue);
            } catch (IllegalArgumentException e) {
                System.err.println(new StringBuilder("Found invalid presence type ").append(attributeValue).toString());
            }
        }
        f fVar = new f(bVar);
        fVar.m(xmlPullParser.getAttributeValue(BuildConfig.VERSION_NAME, SocializeProtocolConstants.PROTOCOL_KEY_TO));
        fVar.n(xmlPullParser.getAttributeValue(BuildConfig.VERSION_NAME, "from"));
        fVar.l(xmlPullParser.getAttributeValue(BuildConfig.VERSION_NAME, "chid"));
        String attributeValue2 = xmlPullParser.getAttributeValue(BuildConfig.VERSION_NAME, AgooConstants.MESSAGE_ID);
        if (attributeValue2 == null) {
            attributeValue2 = "ID_NOT_AVAILABLE";
        }
        fVar.k(attributeValue2);
        int i = 0;
        while (i == 0) {
            int next = xmlPullParser.next();
            if (next == 2) {
                String name = xmlPullParser.getName();
                String namespace = xmlPullParser.getNamespace();
                if (name.equals("status")) {
                    fVar.a(xmlPullParser.nextText());
                } else if (name.equals("priority")) {
                    try {
                        fVar.a(Integer.parseInt(xmlPullParser.nextText()));
                    } catch (NumberFormatException e2) {
                    } catch (IllegalArgumentException e3) {
                        fVar.a(0);
                    }
                } else if (name.equals("show")) {
                    name = xmlPullParser.nextText();
                    try {
                        fVar.a(f$a.valueOf(name));
                    } catch (IllegalArgumentException e4) {
                        System.err.println(new StringBuilder("Found invalid presence mode ").append(name).toString());
                    }
                } else if (name.equals(XiaomiOAuthConstants.EXTRA_ERROR_CODE_2)) {
                    fVar.a(e(xmlPullParser));
                } else {
                    fVar.a(a(name, namespace, xmlPullParser));
                }
            } else if (next == 3 && xmlPullParser.getName().equals("presence")) {
                Object obj = 1;
            }
        }
        return fVar;
    }

    public static k.b c(XmlPullParser xmlPullParser) {
        k.b bVar = new k.b();
        String attributeValue = xmlPullParser.getAttributeValue(BuildConfig.VERSION_NAME, AgooConstants.MESSAGE_ID);
        String attributeValue2 = xmlPullParser.getAttributeValue(BuildConfig.VERSION_NAME, SocializeProtocolConstants.PROTOCOL_KEY_TO);
        String attributeValue3 = xmlPullParser.getAttributeValue(BuildConfig.VERSION_NAME, "from");
        String attributeValue4 = xmlPullParser.getAttributeValue(BuildConfig.VERSION_NAME, "chid");
        k.b.a a = k.b.a.a(xmlPullParser.getAttributeValue(BuildConfig.VERSION_NAME, AgooConstants.MESSAGE_TYPE));
        bVar.k(attributeValue);
        bVar.m(attributeValue2);
        bVar.n(attributeValue3);
        bVar.l(attributeValue4);
        bVar.a(a);
        Object obj = null;
        h hVar = null;
        while (obj == null) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals(XiaomiOAuthConstants.EXTRA_ERROR_CODE_2)) {
                    hVar = e(xmlPullParser);
                }
            } else if (next == 3 && xmlPullParser.getName().equals("bind")) {
                obj = 1;
            }
        }
        bVar.a(hVar);
        return bVar;
    }

    public static g d(XmlPullParser xmlPullParser) {
        g gVar = null;
        Object obj = null;
        while (obj == null) {
            int next = xmlPullParser.next();
            if (next == 2) {
                gVar = new g(xmlPullParser.getName());
            } else if (next == 3 && xmlPullParser.getName().equals(XiaomiOAuthConstants.EXTRA_ERROR_CODE_2)) {
                obj = 1;
            }
        }
        return gVar;
    }

    public static h e(XmlPullParser xmlPullParser) {
        String attributeValue;
        String attributeValue2;
        String str;
        Object obj = null;
        List arrayList = new ArrayList();
        String str2 = null;
        Object obj2 = null;
        String str3 = "-1";
        int i = 0;
        while (i < xmlPullParser.getAttributeCount()) {
            attributeValue = xmlPullParser.getAttributeName(i).equals(XiaomiOAuthConstants.EXTRA_CODE_2) ? xmlPullParser.getAttributeValue(BuildConfig.VERSION_NAME, XiaomiOAuthConstants.EXTRA_CODE_2) : str3;
            attributeValue2 = xmlPullParser.getAttributeName(i).equals(AgooConstants.MESSAGE_TYPE) ? xmlPullParser.getAttributeValue(BuildConfig.VERSION_NAME, AgooConstants.MESSAGE_TYPE) : str;
            if (xmlPullParser.getAttributeName(i).equals("reason")) {
                str2 = xmlPullParser.getAttributeValue(BuildConfig.VERSION_NAME, "reason");
            }
            i++;
            str = attributeValue2;
            str3 = attributeValue;
        }
        attributeValue2 = null;
        attributeValue = null;
        while (obj == null) {
            i = xmlPullParser.next();
            if (i == 2) {
                if (xmlPullParser.getName().equals(WeiXinShareContent.TYPE_TEXT)) {
                    attributeValue = xmlPullParser.nextText();
                } else {
                    String name = xmlPullParser.getName();
                    String namespace = xmlPullParser.getNamespace();
                    if ("urn:ietf:params:xml:ns:xmpp-stanzas".equals(namespace)) {
                        attributeValue2 = name;
                    } else {
                        arrayList.add(a(name, namespace, xmlPullParser));
                    }
                }
            } else if (i == 3) {
                if (xmlPullParser.getName().equals(XiaomiOAuthConstants.EXTRA_ERROR_CODE_2)) {
                    obj = 1;
                }
            } else if (i == 4) {
                attributeValue = xmlPullParser.getText();
            }
        }
        return new h(Integer.parseInt(str3), str == null ? "cancel" : str, str2, attributeValue2, attributeValue, arrayList);
    }

    private static String f(XmlPullParser xmlPullParser) {
        String str = BuildConfig.VERSION_NAME;
        int depth = xmlPullParser.getDepth();
        while (true) {
            if (xmlPullParser.next() == 3 && xmlPullParser.getDepth() == depth) {
                return str;
            }
            str = str + xmlPullParser.getText();
        }
    }

    private static String g(XmlPullParser xmlPullParser) {
        int i = 0;
        while (i < xmlPullParser.getAttributeCount()) {
            String attributeName = xmlPullParser.getAttributeName(i);
            if (!"xml:lang".equals(attributeName)) {
                if ("lang".equals(attributeName) && "xml".equals(xmlPullParser.getAttributePrefix(i))) {
                }
                i++;
            }
            return xmlPullParser.getAttributeValue(i);
        }
        return null;
    }
}
