package com.xiaomi.push.service;

import com.xiaomi.smack.packet.a;
import com.xiaomi.smack.provider.b;
import com.xiaomi.smack.util.g;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

public class c implements b {
    public static a a(XmlPullParser xmlPullParser) {
        List list = null;
        if (xmlPullParser.getEventType() != 2) {
            return null;
        }
        String[] strArr;
        String[] strArr2;
        int i;
        String str;
        String name = xmlPullParser.getName();
        String namespace = xmlPullParser.getNamespace();
        if (xmlPullParser.getAttributeCount() > 0) {
            strArr = new String[xmlPullParser.getAttributeCount()];
            strArr2 = new String[xmlPullParser.getAttributeCount()];
            for (i = 0; i < xmlPullParser.getAttributeCount(); i++) {
                strArr[i] = xmlPullParser.getAttributeName(i);
                strArr2[i] = g.b(xmlPullParser.getAttributeValue(i));
            }
            str = null;
        } else {
            str = null;
            strArr2 = null;
            strArr = null;
        }
        while (true) {
            i = xmlPullParser.next();
            if (i == 3) {
                return new a(name, namespace, strArr, strArr2, str, list);
            }
            if (i == 4) {
                str = xmlPullParser.getText().trim();
            } else if (i == 2) {
                if (list == null) {
                    list = new ArrayList();
                }
                a a = a(xmlPullParser);
                if (a != null) {
                    list.add(a);
                }
            }
        }
    }

    public void a() {
        com.xiaomi.smack.provider.c.a().a("all", "xm:chat", this);
    }

    public a b(XmlPullParser xmlPullParser) {
        int eventType = xmlPullParser.getEventType();
        while (eventType != 1 && eventType != 2) {
            eventType = xmlPullParser.next();
        }
        return eventType == 2 ? a(xmlPullParser) : null;
    }
}
