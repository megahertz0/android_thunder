package com.xunlei.XLStat.g;

import com.xunlei.XLStat.XLStatLog.XLStatLog;
import java.util.HashMap;
import org.android.agoo.common.AgooConstants;
import org.xmlpull.v1.XmlPullParser;

public class b {
    private String a;

    public b() {
        this.a = "PullConfigFile";
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.lang.String r9, java.util.HashMap<java.lang.String, com.xunlei.XLStat.g.c.b> r10, java.util.HashMap<java.lang.Integer, java.lang.Integer> r11, java.util.HashMap<java.lang.String, java.lang.Integer> r12, com.xunlei.XLStat.g.c.a r13, com.xunlei.XLStat.g.c.d r14, com.xunlei.XLStat.g.c.c r15, android.content.Context r16) throws java.io.IOException {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.XLStat.g.b.a(java.lang.String, java.util.HashMap, java.util.HashMap, java.util.HashMap, com.xunlei.XLStat.g.c$a, com.xunlei.XLStat.g.c$d, com.xunlei.XLStat.g.c$c, android.content.Context):void");
        /*
        this = this;
        r2 = android.util.Xml.newPullParser();
        r1 = 0;
        r0 = r8.a;
        r3 = "path";
        com.xunlei.XLStat.XLStatLog.XLStatLog.e(r0, r3, r9);
        r0 = r16.getResources();	 Catch:{ FileNotFoundException -> 0x00a9 }
        r0 = r0.getAssets();	 Catch:{ FileNotFoundException -> 0x00a9 }
        r1 = r0.open(r9);	 Catch:{ FileNotFoundException -> 0x00a9 }
    L_0x0019:
        if (r1 != 0) goto L_0x00cc;
    L_0x001b:
        r3 = new java.io.File;
        r3.<init>(r9);
        r0 = r3.exists();
        if (r0 == 0) goto L_0x00c1;
    L_0x0026:
        r0 = new java.io.FileInputStream;	 Catch:{ FileNotFoundException -> 0x00ba }
        r0.<init>(r3);	 Catch:{ FileNotFoundException -> 0x00ba }
    L_0x002b:
        if (r0 == 0) goto L_0x0276;
    L_0x002d:
        r1 = "utf-8";
        r2.setInput(r0, r1);	 Catch:{ XmlPullParserException -> 0x0099 }
        r0 = r2.getEventType();	 Catch:{ XmlPullParserException -> 0x0099 }
        r1 = r8.a;	 Catch:{ XmlPullParserException -> 0x0099 }
        r3 = "parserConfig";
        r4 = new java.lang.StringBuilder;	 Catch:{ XmlPullParserException -> 0x0099 }
        r5 = "type: ";
        r4.<init>(r5);	 Catch:{ XmlPullParserException -> 0x0099 }
        r4 = r4.append(r0);	 Catch:{ XmlPullParserException -> 0x0099 }
        r5 = "  parser: ";
        r4 = r4.append(r5);	 Catch:{ XmlPullParserException -> 0x0099 }
        r4 = r4.append(r2);	 Catch:{ XmlPullParserException -> 0x0099 }
        r4 = r4.toString();	 Catch:{ XmlPullParserException -> 0x0099 }
        com.xunlei.XLStat.XLStatLog.XLStatLog.i(r1, r3, r4);	 Catch:{ XmlPullParserException -> 0x0099 }
    L_0x005a:
        r1 = 1;
        if (r0 == r1) goto L_0x00a8;
    L_0x005d:
        r1 = r2.getName();	 Catch:{ XmlPullParserException -> 0x0099 }
        r3 = r8.a;	 Catch:{ XmlPullParserException -> 0x0099 }
        r4 = "parserConfig";
        r5 = new java.lang.StringBuilder;	 Catch:{ XmlPullParserException -> 0x0099 }
        r6 = "nodeName: ";
        r5.<init>(r6);	 Catch:{ XmlPullParserException -> 0x0099 }
        r5 = r5.append(r1);	 Catch:{ XmlPullParserException -> 0x0099 }
        r5 = r5.toString();	 Catch:{ XmlPullParserException -> 0x0099 }
        com.xunlei.XLStat.XLStatLog.XLStatLog.i(r3, r4, r5);	 Catch:{ XmlPullParserException -> 0x0099 }
        switch(r0) {
            case 0: goto L_0x00cf;
            case 1: goto L_0x007c;
            case 2: goto L_0x00db;
            case 3: goto L_0x0256;
            default: goto L_0x007c;
        };	 Catch:{ XmlPullParserException -> 0x0099 }
    L_0x007c:
        r0 = r2.next();	 Catch:{ XmlPullParserException -> 0x0099 }
        r1 = r8.a;	 Catch:{ XmlPullParserException -> 0x0099 }
        r3 = "parserConfig";
        r4 = new java.lang.StringBuilder;	 Catch:{ XmlPullParserException -> 0x0099 }
        r5 = "type: ";
        r4.<init>(r5);	 Catch:{ XmlPullParserException -> 0x0099 }
        r4 = r4.append(r0);	 Catch:{ XmlPullParserException -> 0x0099 }
        r4 = r4.toString();	 Catch:{ XmlPullParserException -> 0x0099 }
        com.xunlei.XLStat.XLStatLog.XLStatLog.d(r1, r3, r4);	 Catch:{ XmlPullParserException -> 0x0099 }
        goto L_0x005a;
    L_0x0099:
        r0 = move-exception;
        r1 = r8.a;
        r2 = "parserConfig";
        r3 = "parser xml error ... ";
        com.xunlei.XLStat.XLStatLog.XLStatLog.d(r1, r2, r3);
        r0.printStackTrace();
    L_0x00a8:
        return;
    L_0x00a9:
        r0 = move-exception;
        r3 = r8.a;
        r4 = "parseConfig";
        r5 = "parse xml error ... ";
        com.xunlei.XLStat.XLStatLog.XLStatLog.e(r3, r4, r5);
        r0.printStackTrace();
        goto L_0x0019;
    L_0x00ba:
        r0 = move-exception;
        r0.printStackTrace();
        r0 = r1;
        goto L_0x002b;
    L_0x00c1:
        r0 = r8.a;
        r3 = "parseConfig";
        r4 = "file not exists ";
        com.xunlei.XLStat.XLStatLog.XLStatLog.e(r0, r3, r4);
    L_0x00cc:
        r0 = r1;
        goto L_0x002b;
    L_0x00cf:
        r0 = r8.a;	 Catch:{ XmlPullParserException -> 0x0099 }
        r1 = "parserConfig";
        r3 = "START_DOCUMENT";
        com.xunlei.XLStat.XLStatLog.XLStatLog.d(r0, r1, r3);	 Catch:{ XmlPullParserException -> 0x0099 }
        goto L_0x007c;
    L_0x00db:
        r0 = r8.a;	 Catch:{ XmlPullParserException -> 0x0099 }
        r3 = "parserConfig";
        r4 = "START_TAG";
        com.xunlei.XLStat.XLStatLog.XLStatLog.d(r0, r3, r4);	 Catch:{ XmlPullParserException -> 0x0099 }
        r0 = "config";
        r0 = r0.equals(r1);	 Catch:{ XmlPullParserException -> 0x0099 }
        if (r0 == 0) goto L_0x00fa;
    L_0x00ef:
        r0 = r8.a;	 Catch:{ XmlPullParserException -> 0x0099 }
        r3 = "parserConfig";
        r4 = "config";
        com.xunlei.XLStat.XLStatLog.XLStatLog.d(r0, r3, r4);	 Catch:{ XmlPullParserException -> 0x0099 }
    L_0x00fa:
        r0 = "server";
        r0 = r0.equals(r1);	 Catch:{ XmlPullParserException -> 0x0099 }
        if (r0 == 0) goto L_0x010e;
    L_0x0103:
        r0 = r8.a;	 Catch:{ XmlPullParserException -> 0x0099 }
        r3 = "parserConfig";
        r4 = "server";
        com.xunlei.XLStat.XLStatLog.XLStatLog.d(r0, r3, r4);	 Catch:{ XmlPullParserException -> 0x0099 }
    L_0x010e:
        r0 = "tcp";
        r0 = r0.equals(r1);	 Catch:{ XmlPullParserException -> 0x0099 }
        if (r0 == 0) goto L_0x018c;
    L_0x0117:
        r0 = 0;
        r0 = r2.getAttributeName(r0);	 Catch:{ XmlPullParserException -> 0x0099 }
        if (r0 == 0) goto L_0x013e;
    L_0x011e:
        r3 = "ip";
        r3 = r3.equals(r0);	 Catch:{ XmlPullParserException -> 0x0099 }
        if (r3 != 0) goto L_0x0130;
    L_0x0127:
        r3 = "host";
        r3 = r3.equals(r0);	 Catch:{ XmlPullParserException -> 0x0099 }
        if (r3 == 0) goto L_0x021c;
    L_0x0130:
        r0 = 0;
        r0 = r2.getAttributeValue(r0);	 Catch:{ XmlPullParserException -> 0x0099 }
        if (r0 == 0) goto L_0x013e;
    L_0x0137:
        r0 = 0;
        r0 = r2.getAttributeValue(r0);	 Catch:{ XmlPullParserException -> 0x0099 }
        r14.a = r0;	 Catch:{ XmlPullParserException -> 0x0099 }
    L_0x013e:
        r0 = 1;
        r0 = r2.getAttributeName(r0);	 Catch:{ XmlPullParserException -> 0x0099 }
        if (r0 == 0) goto L_0x0165;
    L_0x0145:
        r3 = "ip";
        r3 = r3.equals(r0);	 Catch:{ XmlPullParserException -> 0x0099 }
        if (r3 != 0) goto L_0x0157;
    L_0x014e:
        r3 = "host";
        r3 = r3.equals(r0);	 Catch:{ XmlPullParserException -> 0x0099 }
        if (r3 == 0) goto L_0x0239;
    L_0x0157:
        r0 = 1;
        r0 = r2.getAttributeValue(r0);	 Catch:{ XmlPullParserException -> 0x0099 }
        if (r0 == 0) goto L_0x0165;
    L_0x015e:
        r0 = 1;
        r0 = r2.getAttributeValue(r0);	 Catch:{ XmlPullParserException -> 0x0099 }
        r14.a = r0;	 Catch:{ XmlPullParserException -> 0x0099 }
    L_0x0165:
        r0 = r8.a;	 Catch:{ XmlPullParserException -> 0x0099 }
        r3 = "parserConfig";
        r4 = new java.lang.StringBuilder;	 Catch:{ XmlPullParserException -> 0x0099 }
        r5 = "tcp host: ";
        r4.<init>(r5);	 Catch:{ XmlPullParserException -> 0x0099 }
        r5 = r14.a;	 Catch:{ XmlPullParserException -> 0x0099 }
        r4 = r4.append(r5);	 Catch:{ XmlPullParserException -> 0x0099 }
        r5 = " port: ";
        r4 = r4.append(r5);	 Catch:{ XmlPullParserException -> 0x0099 }
        r5 = r14.b;	 Catch:{ XmlPullParserException -> 0x0099 }
        r4 = r4.append(r5);	 Catch:{ XmlPullParserException -> 0x0099 }
        r4 = r4.toString();	 Catch:{ XmlPullParserException -> 0x0099 }
        com.xunlei.XLStat.XLStatLog.XLStatLog.d(r0, r3, r4);	 Catch:{ XmlPullParserException -> 0x0099 }
    L_0x018c:
        r0 = "contexts";
        r0 = r0.equals(r1);	 Catch:{ XmlPullParserException -> 0x0099 }
        if (r0 == 0) goto L_0x01c1;
    L_0x0195:
        r0 = 0;
        r0 = r2.getAttributeValue(r0);	 Catch:{ XmlPullParserException -> 0x0099 }
        if (r0 == 0) goto L_0x01a7;
    L_0x019c:
        r0 = 0;
        r0 = r2.getAttributeValue(r0);	 Catch:{ XmlPullParserException -> 0x0099 }
        r0 = java.lang.Integer.parseInt(r0);	 Catch:{ XmlPullParserException -> 0x0099 }
        r13.a = r0;	 Catch:{ XmlPullParserException -> 0x0099 }
    L_0x01a7:
        r0 = r8.a;	 Catch:{ XmlPullParserException -> 0x0099 }
        r3 = "parserConfig";
        r4 = new java.lang.StringBuilder;	 Catch:{ XmlPullParserException -> 0x0099 }
        r5 = "contextPriority: ";
        r4.<init>(r5);	 Catch:{ XmlPullParserException -> 0x0099 }
        r5 = r13.a;	 Catch:{ XmlPullParserException -> 0x0099 }
        r4 = r4.append(r5);	 Catch:{ XmlPullParserException -> 0x0099 }
        r4 = r4.toString();	 Catch:{ XmlPullParserException -> 0x0099 }
        com.xunlei.XLStat.XLStatLog.XLStatLog.i(r0, r3, r4);	 Catch:{ XmlPullParserException -> 0x0099 }
    L_0x01c1:
        r0 = "context";
        r0 = r0.equals(r1);	 Catch:{ XmlPullParserException -> 0x0099 }
        if (r0 == 0) goto L_0x0202;
    L_0x01ca:
        r0 = 0;
        r0 = r2.getAttributeValue(r0);	 Catch:{ XmlPullParserException -> 0x0099 }
        r0 = java.lang.Integer.parseInt(r0);	 Catch:{ XmlPullParserException -> 0x0099 }
        r3 = 1;
        r3 = r2.getAttributeValue(r3);	 Catch:{ XmlPullParserException -> 0x0099 }
        r4 = java.lang.Integer.valueOf(r0);	 Catch:{ XmlPullParserException -> 0x0099 }
        r12.put(r3, r4);	 Catch:{ XmlPullParserException -> 0x0099 }
        r4 = r8.a;	 Catch:{ XmlPullParserException -> 0x0099 }
        r5 = "parserConfig";
        r6 = new java.lang.StringBuilder;	 Catch:{ XmlPullParserException -> 0x0099 }
        r7 = "context index: ";
        r6.<init>(r7);	 Catch:{ XmlPullParserException -> 0x0099 }
        r0 = r6.append(r0);	 Catch:{ XmlPullParserException -> 0x0099 }
        r6 = " key: ";
        r0 = r0.append(r6);	 Catch:{ XmlPullParserException -> 0x0099 }
        r0 = r0.append(r3);	 Catch:{ XmlPullParserException -> 0x0099 }
        r0 = r0.toString();	 Catch:{ XmlPullParserException -> 0x0099 }
        com.xunlei.XLStat.XLStatLog.XLStatLog.d(r4, r5, r0);	 Catch:{ XmlPullParserException -> 0x0099 }
    L_0x0202:
        r0 = "level";
        r0 = r0.equals(r1);	 Catch:{ XmlPullParserException -> 0x0099 }
        if (r0 == 0) goto L_0x020e;
    L_0x020b:
        r8.a(r2, r11);	 Catch:{ XmlPullParserException -> 0x0099 }
    L_0x020e:
        r0 = "event";
        r0 = r0.equals(r1);	 Catch:{ XmlPullParserException -> 0x0099 }
        if (r0 == 0) goto L_0x007c;
    L_0x0217:
        r8.b(r2, r10);	 Catch:{ XmlPullParserException -> 0x0099 }
        goto L_0x007c;
    L_0x021c:
        r3 = "port";
        r0 = r3.equals(r0);	 Catch:{ XmlPullParserException -> 0x0099 }
        if (r0 == 0) goto L_0x013e;
    L_0x0225:
        r0 = 0;
        r0 = r2.getAttributeValue(r0);	 Catch:{ XmlPullParserException -> 0x0099 }
        if (r0 == 0) goto L_0x013e;
    L_0x022c:
        r0 = 0;
        r0 = r2.getAttributeValue(r0);	 Catch:{ XmlPullParserException -> 0x0099 }
        r0 = java.lang.Integer.parseInt(r0);	 Catch:{ XmlPullParserException -> 0x0099 }
        r14.b = r0;	 Catch:{ XmlPullParserException -> 0x0099 }
        goto L_0x013e;
    L_0x0239:
        r3 = "port";
        r0 = r3.equals(r0);	 Catch:{ XmlPullParserException -> 0x0099 }
        if (r0 == 0) goto L_0x0165;
    L_0x0242:
        r0 = 1;
        r0 = r2.getAttributeValue(r0);	 Catch:{ XmlPullParserException -> 0x0099 }
        if (r0 == 0) goto L_0x0165;
    L_0x0249:
        r0 = 1;
        r0 = r2.getAttributeValue(r0);	 Catch:{ XmlPullParserException -> 0x0099 }
        r0 = java.lang.Integer.parseInt(r0);	 Catch:{ XmlPullParserException -> 0x0099 }
        r14.b = r0;	 Catch:{ XmlPullParserException -> 0x0099 }
        goto L_0x0165;
    L_0x0256:
        r0 = "server";
        r0.equals(r1);	 Catch:{ XmlPullParserException -> 0x0099 }
        r0 = "priority";
        r0.equals(r1);	 Catch:{ XmlPullParserException -> 0x0099 }
        r0 = "event";
        r0.equals(r1);	 Catch:{ XmlPullParserException -> 0x0099 }
        r0 = "level";
        r0.equals(r1);	 Catch:{ XmlPullParserException -> 0x0099 }
        r0 = "stat";
        r0.equals(r1);	 Catch:{ XmlPullParserException -> 0x0099 }
        goto L_0x007c;
    L_0x0276:
        r0 = r8.a;	 Catch:{ XmlPullParserException -> 0x0099 }
        r1 = "parseConfig";
        r2 = "xml file is invalide";
        com.xunlei.XLStat.XLStatLog.XLStatLog.d(r0, r1, r2);	 Catch:{ XmlPullParserException -> 0x0099 }
        goto L_0x00a8;
        */
    }

    void a(XmlPullParser xmlPullParser, HashMap<Integer, Integer> hashMap) {
        int i = 0;
        int i2 = -1000;
        int i3 = -1000;
        String attributeName = xmlPullParser.getAttributeName(0);
        Object attributeName2;
        while (attributeName2 != null) {
            if (AgooConstants.MESSAGE_ID.equals(attributeName2)) {
                i3 = Integer.parseInt(xmlPullParser.getAttributeValue(i));
            }
            if ("reporttime".equals(attributeName2)) {
                i2 = Integer.parseInt(xmlPullParser.getAttributeValue(i));
            }
            i++;
            if (i >= 2) {
                break;
            }
            attributeName2 = xmlPullParser.getAttributeName(i);
        }
        int i4 = i2;
        i2 = i3;
        i3 = i4;
        hashMap.put(Integer.valueOf(i2), Integer.valueOf(i3));
        XLStatLog.d(this.a, "parserConfig", new StringBuilder("level id: ").append(i2).append(" reportTime: ").append(i3).toString());
    }

    void b(XmlPullParser xmlPullParser, HashMap<String, com.xunlei.XLStat.g.c.b> hashMap) {
        String attributeValue;
        String attributeName = xmlPullParser.getAttributeName(0);
        int i = 0;
        Object obj = null;
        int i2 = -1000;
        int i3 = -1000;
        Object attributeName2;
        while (attributeName2 != null) {
            if ("index".equals(attributeName2)) {
                i2 = Integer.parseInt(xmlPullParser.getAttributeValue(i));
            }
            if ("key".equals(attributeName2)) {
                attributeValue = xmlPullParser.getAttributeValue(i);
            }
            if ("priority".equals(attributeName2)) {
                i3 = Integer.parseInt(xmlPullParser.getAttributeValue(i));
            }
            i++;
            if (i >= 3) {
                break;
            }
            attributeName2 = xmlPullParser.getAttributeName(i);
        }
        int i4 = i3;
        i3 = i2;
        String str = attributeValue;
        int i5 = i4;
        if (i3 == 10000 || i3 == 10001 || i3 == 10002) {
            XLStatLog.d(this.a, "parserConfig", new StringBuilder("event index is invlaid ... index: ").append(i3).toString());
            return;
        }
        com.xunlei.XLStat.g.c.b bVar = new com.xunlei.XLStat.g.c.b();
        bVar.a = i3;
        bVar.b = i5;
        if (!(str == null || i5 == -1000)) {
            hashMap.put(str, bVar);
        }
        XLStatLog.d(this.a, "parserConfig", new StringBuilder("event index: ").append(bVar.a).append(" key: ").append(str).append(" priority: ").append(bVar.b).toString());
    }
}
