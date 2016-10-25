package com.ta.utdid2.core.persistent;

import android.util.Xml;
import com.sina.weibo.sdk.register.mobile.SelectCountryActivity;
import com.xunlei.download.Downloads.Impl.RequestHeaders;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.android.agoo.message.MessageService;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

class XmlUtils {
    XmlUtils() {
    }

    public static void skipCurrentTag(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        int depth = xmlPullParser.getDepth();
        while (true) {
            int next = xmlPullParser.next();
            if (next == 1 || next != 3 || xmlPullParser.getDepth() <= depth) {
                return;
            }
        }
    }

    public static final int convertValueToList(CharSequence charSequence, String[] strArr, int i) {
        if (charSequence != null) {
            for (int i2 = 0; i2 < strArr.length; i2++) {
                if (charSequence.equals(strArr[i2])) {
                    return i2;
                }
            }
        }
        return i;
    }

    public static final boolean convertValueToBoolean(CharSequence charSequence, boolean z) {
        Object obj = null;
        if (charSequence == null) {
            return z;
        }
        if (charSequence.equals(MessageService.MSG_DB_NOTIFY_REACHED) || charSequence.equals("true") || charSequence.equals("TRUE")) {
            obj = 1;
        }
        return r0;
    }

    public static final int convertValueToInt(CharSequence charSequence, int i) {
        int i2 = 1;
        if (charSequence == null) {
            return i;
        }
        int i3;
        int i4;
        String toString = charSequence.toString();
        int length = toString.length();
        if ('-' == toString.charAt(0)) {
            i3 = -1;
        } else {
            i3 = 1;
            i2 = 0;
        }
        if ('0' == toString.charAt(i2)) {
            if (i2 == length - 1) {
                return 0;
            }
            char charAt = toString.charAt(i2 + 1);
            if ('x' == charAt || 'X' == charAt) {
                i4 = i2 + 2;
                i2 = 16;
            } else {
                i4 = i2 + 1;
                i2 = XZBDevice.Wait;
            }
        } else if ('#' == toString.charAt(i2)) {
            i4 = i2 + 1;
            i2 = 16;
        } else {
            i4 = i2;
            i2 = 10;
        }
        return Integer.parseInt(toString.substring(i4), i2) * i3;
    }

    public static final int convertValueToUnsignedInt(String str, int i) {
        return str == null ? i : parseUnsignedIntAttribute(str);
    }

    public static final int parseUnsignedIntAttribute(CharSequence charSequence) {
        int i;
        int i2 = R.styleable.Toolbar_titleMarginBottom;
        String toString = charSequence.toString();
        int length = toString.length();
        if ('0' == toString.charAt(0)) {
            if (length - 1 == 0) {
                return 0;
            }
            char charAt = toString.charAt(1);
            if ('x' == charAt || 'X' == charAt) {
                i = XZBDevice.DOWNLOAD_LIST_RECYCLE;
            } else {
                i2 = XZBDevice.Wait;
                i = 1;
            }
        } else if ('#' == toString.charAt(0)) {
            i = 1;
        } else {
            i2 = 10;
            i = 0;
        }
        return (int) Long.parseLong(toString.substring(i), i2);
    }

    public static final void writeMapXml(Map map, OutputStream outputStream) throws XmlPullParserException, IOException {
        XmlSerializer fastXmlSerializer = new FastXmlSerializer();
        fastXmlSerializer.setOutput(outputStream, "utf-8");
        fastXmlSerializer.startDocument(null, Boolean.valueOf(true));
        fastXmlSerializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
        writeMapXml(map, null, fastXmlSerializer);
        fastXmlSerializer.endDocument();
    }

    public static final void writeListXml(List list, OutputStream outputStream) throws XmlPullParserException, IOException {
        XmlSerializer newSerializer = Xml.newSerializer();
        newSerializer.setOutput(outputStream, "utf-8");
        newSerializer.startDocument(null, Boolean.valueOf(true));
        newSerializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
        writeListXml(list, null, newSerializer);
        newSerializer.endDocument();
    }

    public static final void writeMapXml(Map map, String str, XmlSerializer xmlSerializer) throws XmlPullParserException, IOException {
        if (map == null) {
            xmlSerializer.startTag(null, "null");
            xmlSerializer.endTag(null, "null");
            return;
        }
        xmlSerializer.startTag(null, "map");
        if (str != null) {
            xmlSerializer.attribute(null, SelectCountryActivity.EXTRA_COUNTRY_NAME, str);
        }
        for (Entry entry : map.entrySet()) {
            writeValueXml(entry.getValue(), (String) entry.getKey(), xmlSerializer);
        }
        xmlSerializer.endTag(null, "map");
    }

    public static final void writeListXml(List list, String str, XmlSerializer xmlSerializer) throws XmlPullParserException, IOException {
        if (list == null) {
            xmlSerializer.startTag(null, "null");
            xmlSerializer.endTag(null, "null");
            return;
        }
        xmlSerializer.startTag(null, "list");
        if (str != null) {
            xmlSerializer.attribute(null, SelectCountryActivity.EXTRA_COUNTRY_NAME, str);
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            writeValueXml(list.get(i), null, xmlSerializer);
        }
        xmlSerializer.endTag(null, "list");
    }

    public static final void writeByteArrayXml(byte[] bArr, String str, XmlSerializer xmlSerializer) throws XmlPullParserException, IOException {
        if (bArr == null) {
            xmlSerializer.startTag(null, "null");
            xmlSerializer.endTag(null, "null");
            return;
        }
        xmlSerializer.startTag(null, "byte-array");
        if (str != null) {
            xmlSerializer.attribute(null, SelectCountryActivity.EXTRA_COUNTRY_NAME, str);
        }
        int length = bArr.length;
        xmlSerializer.attribute(null, "num", Integer.toString(length));
        StringBuilder stringBuilder = new StringBuilder(bArr.length * 2);
        for (int i = 0; i < length; i++) {
            byte b = bArr[i];
            int i2 = b >> 4;
            stringBuilder.append(i2 >= 10 ? (i2 + 97) - 10 : i2 + 48);
            i2 = b & 255;
            if (i2 >= 10) {
                i2 = (i2 + 97) - 10;
            } else {
                i2 += 48;
            }
            stringBuilder.append(i2);
        }
        xmlSerializer.text(stringBuilder.toString());
        xmlSerializer.endTag(null, "byte-array");
    }

    public static final void writeIntArrayXml(int[] iArr, String str, XmlSerializer xmlSerializer) throws XmlPullParserException, IOException {
        if (iArr == null) {
            xmlSerializer.startTag(null, "null");
            xmlSerializer.endTag(null, "null");
            return;
        }
        xmlSerializer.startTag(null, "int-array");
        if (str != null) {
            xmlSerializer.attribute(null, SelectCountryActivity.EXTRA_COUNTRY_NAME, str);
        }
        xmlSerializer.attribute(null, "num", Integer.toString(r1));
        for (int i : iArr) {
            xmlSerializer.startTag(null, "item");
            xmlSerializer.attribute(null, RequestHeaders.COLUMN_VALUE, Integer.toString(i));
            xmlSerializer.endTag(null, "item");
        }
        xmlSerializer.endTag(null, "int-array");
    }

    public static final void writeValueXml(Object obj, String str, XmlSerializer xmlSerializer) throws XmlPullParserException, IOException {
        if (obj == null) {
            xmlSerializer.startTag(null, "null");
            if (str != null) {
                xmlSerializer.attribute(null, SelectCountryActivity.EXTRA_COUNTRY_NAME, str);
            }
            xmlSerializer.endTag(null, "null");
        } else if (obj instanceof String) {
            xmlSerializer.startTag(null, "string");
            if (str != null) {
                xmlSerializer.attribute(null, SelectCountryActivity.EXTRA_COUNTRY_NAME, str);
            }
            xmlSerializer.text(obj.toString());
            xmlSerializer.endTag(null, "string");
        } else {
            String str2;
            if (obj instanceof Integer) {
                str2 = "int";
            } else if (obj instanceof Long) {
                str2 = "long";
            } else if (obj instanceof Float) {
                str2 = "float";
            } else if (obj instanceof Double) {
                str2 = "double";
            } else if (obj instanceof Boolean) {
                str2 = "boolean";
            } else if (obj instanceof byte[]) {
                writeByteArrayXml((byte[]) obj, str, xmlSerializer);
                return;
            } else if (obj instanceof int[]) {
                writeIntArrayXml((int[]) obj, str, xmlSerializer);
                return;
            } else if (obj instanceof Map) {
                writeMapXml((Map) obj, str, xmlSerializer);
                return;
            } else if (obj instanceof List) {
                writeListXml((List) obj, str, xmlSerializer);
                return;
            } else if (obj instanceof CharSequence) {
                xmlSerializer.startTag(null, "string");
                if (str != null) {
                    xmlSerializer.attribute(null, SelectCountryActivity.EXTRA_COUNTRY_NAME, str);
                }
                xmlSerializer.text(obj.toString());
                xmlSerializer.endTag(null, "string");
                return;
            } else {
                throw new RuntimeException(new StringBuilder("writeValueXml: unable to write value ").append(obj).toString());
            }
            xmlSerializer.startTag(null, str2);
            if (str != null) {
                xmlSerializer.attribute(null, SelectCountryActivity.EXTRA_COUNTRY_NAME, str);
            }
            xmlSerializer.attribute(null, RequestHeaders.COLUMN_VALUE, obj.toString());
            xmlSerializer.endTag(null, str2);
        }
    }

    public static final HashMap readMapXml(InputStream inputStream) throws XmlPullParserException, IOException {
        XmlPullParser newPullParser = Xml.newPullParser();
        newPullParser.setInput(inputStream, null);
        return (HashMap) readValueXml(newPullParser, new String[1]);
    }

    public static final ArrayList readListXml(InputStream inputStream) throws XmlPullParserException, IOException {
        XmlPullParser newPullParser = Xml.newPullParser();
        newPullParser.setInput(inputStream, null);
        return (ArrayList) readValueXml(newPullParser, new String[1]);
    }

    public static final HashMap readThisMapXml(XmlPullParser xmlPullParser, String str, String[] strArr) throws XmlPullParserException, IOException {
        HashMap hashMap = new HashMap();
        int eventType = xmlPullParser.getEventType();
        do {
            if (eventType == 2) {
                Object readThisValueXml = readThisValueXml(xmlPullParser, strArr);
                if (strArr[0] != null) {
                    hashMap.put(strArr[0], readThisValueXml);
                } else {
                    throw new XmlPullParserException(new StringBuilder("Map value without name attribute: ").append(xmlPullParser.getName()).toString());
                }
            } else if (eventType == 3) {
                if (xmlPullParser.getName().equals(str)) {
                    return hashMap;
                }
                throw new XmlPullParserException(new StringBuilder("Expected ").append(str).append(" end tag at: ").append(xmlPullParser.getName()).toString());
            }
            eventType = xmlPullParser.next();
        } while (eventType != 1);
        throw new XmlPullParserException(new StringBuilder("Document ended before ").append(str).append(" end tag").toString());
    }

    public static final ArrayList readThisListXml(XmlPullParser xmlPullParser, String str, String[] strArr) throws XmlPullParserException, IOException {
        ArrayList arrayList = new ArrayList();
        int eventType = xmlPullParser.getEventType();
        do {
            if (eventType == 2) {
                arrayList.add(readThisValueXml(xmlPullParser, strArr));
            } else if (eventType == 3) {
                if (xmlPullParser.getName().equals(str)) {
                    return arrayList;
                }
                throw new XmlPullParserException(new StringBuilder("Expected ").append(str).append(" end tag at: ").append(xmlPullParser.getName()).toString());
            }
            eventType = xmlPullParser.next();
        } while (eventType != 1);
        throw new XmlPullParserException(new StringBuilder("Document ended before ").append(str).append(" end tag").toString());
    }

    public static final int[] readThisIntArrayXml(XmlPullParser xmlPullParser, String str, String[] strArr) throws XmlPullParserException, IOException {
        try {
            int[] iArr = new int[Integer.parseInt(xmlPullParser.getAttributeValue(null, "num"))];
            int i = 0;
            int eventType = xmlPullParser.getEventType();
            do {
                if (eventType == 2) {
                    if (xmlPullParser.getName().equals("item")) {
                        try {
                            iArr[i] = Integer.parseInt(xmlPullParser.getAttributeValue(null, RequestHeaders.COLUMN_VALUE));
                        } catch (NullPointerException e) {
                            throw new XmlPullParserException("Need value attribute in item");
                        } catch (NumberFormatException e2) {
                            throw new XmlPullParserException("Not a number in value attribute in item");
                        }
                    }
                    throw new XmlPullParserException(new StringBuilder("Expected item tag at: ").append(xmlPullParser.getName()).toString());
                } else if (eventType == 3) {
                    if (xmlPullParser.getName().equals(str)) {
                        return iArr;
                    }
                    if (xmlPullParser.getName().equals("item")) {
                        i++;
                    } else {
                        throw new XmlPullParserException(new StringBuilder("Expected ").append(str).append(" end tag at: ").append(xmlPullParser.getName()).toString());
                    }
                }
                eventType = xmlPullParser.next();
            } while (eventType != 1);
            throw new XmlPullParserException(new StringBuilder("Document ended before ").append(str).append(" end tag").toString());
        } catch (NullPointerException e3) {
            throw new XmlPullParserException("Need num attribute in byte-array");
        } catch (NumberFormatException e4) {
            throw new XmlPullParserException("Not a number in num attribute in byte-array");
        }
    }

    public static final Object readValueXml(XmlPullParser xmlPullParser, String[] strArr) throws XmlPullParserException, IOException {
        int eventType = xmlPullParser.getEventType();
        while (eventType != 2) {
            if (eventType == 3) {
                throw new XmlPullParserException(new StringBuilder("Unexpected end tag at: ").append(xmlPullParser.getName()).toString());
            } else if (eventType == 4) {
                throw new XmlPullParserException(new StringBuilder("Unexpected text: ").append(xmlPullParser.getText()).toString());
            } else {
                try {
                    eventType = xmlPullParser.next();
                    if (eventType == 1) {
                        throw new XmlPullParserException("Unexpected end of document");
                    }
                } catch (Exception e) {
                    throw new XmlPullParserException(new StringBuilder("Unexpected call next(): ").append(xmlPullParser.getName()).toString());
                }
            }
        }
        return readThisValueXml(xmlPullParser, strArr);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.Object readThisValueXml(org.xmlpull.v1.XmlPullParser r9, java.lang.String[] r10) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        throw new UnsupportedOperationException("Method not decompiled: com.ta.utdid2.core.persistent.XmlUtils.readThisValueXml(org.xmlpull.v1.XmlPullParser, java.lang.String[]):java.lang.Object");
        /*
        r8 = 3;
        r7 = 2;
        r6 = 1;
        r5 = 0;
        r0 = 0;
        r1 = "name";
        r2 = r9.getAttributeValue(r0, r1);
        r3 = r9.getName();
        r1 = "null";
        r1 = r3.equals(r1);
        if (r1 == 0) goto L_0x003c;
    L_0x0019:
        r1 = r9.next();
        if (r1 != r6) goto L_0x0189;
    L_0x001f:
        r0 = new org.xmlpull.v1.XmlPullParserException;
        r1 = new java.lang.StringBuilder;
        r2 = "Unexpected end of document in <";
        r1.<init>(r2);
        r1 = r1.append(r3);
        r2 = ">";
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x003c:
        r1 = "string";
        r1 = r3.equals(r1);
        if (r1 == 0) goto L_0x00b8;
    L_0x0045:
        r0 = "";
    L_0x0048:
        r1 = r9.next();
        if (r1 != r6) goto L_0x0057;
    L_0x004e:
        r0 = new org.xmlpull.v1.XmlPullParserException;
        r1 = "Unexpected end of document in <string>";
        r0.<init>(r1);
        throw r0;
    L_0x0057:
        if (r1 != r8) goto L_0x0083;
    L_0x0059:
        r1 = r9.getName();
        r3 = "string";
        r1 = r1.equals(r3);
        if (r1 == 0) goto L_0x0069;
    L_0x0066:
        r10[r5] = r2;
    L_0x0068:
        return r0;
    L_0x0069:
        r0 = new org.xmlpull.v1.XmlPullParserException;
        r1 = new java.lang.StringBuilder;
        r2 = "Unexpected end tag in <string>: ";
        r1.<init>(r2);
        r2 = r9.getName();
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x0083:
        r3 = 4;
        if (r1 != r3) goto L_0x009c;
    L_0x0086:
        r1 = new java.lang.StringBuilder;
        r0 = java.lang.String.valueOf(r0);
        r1.<init>(r0);
        r0 = r9.getText();
        r0 = r1.append(r0);
        r0 = r0.toString();
        goto L_0x0048;
    L_0x009c:
        if (r1 != r7) goto L_0x0048;
    L_0x009e:
        r0 = new org.xmlpull.v1.XmlPullParserException;
        r1 = new java.lang.StringBuilder;
        r2 = "Unexpected start tag in <string>: ";
        r1.<init>(r2);
        r2 = r9.getName();
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x00b8:
        r1 = "int";
        r1 = r3.equals(r1);
        if (r1 == 0) goto L_0x00d2;
    L_0x00c1:
        r1 = "value";
        r0 = r9.getAttributeValue(r0, r1);
        r0 = java.lang.Integer.parseInt(r0);
        r0 = java.lang.Integer.valueOf(r0);
        goto L_0x0019;
    L_0x00d2:
        r1 = "long";
        r1 = r3.equals(r1);
        if (r1 == 0) goto L_0x00e8;
    L_0x00db:
        r1 = "value";
        r0 = r9.getAttributeValue(r0, r1);
        r0 = java.lang.Long.valueOf(r0);
        goto L_0x0019;
    L_0x00e8:
        r1 = "float";
        r1 = r3.equals(r1);
        if (r1 == 0) goto L_0x0100;
    L_0x00f1:
        r1 = new java.lang.Float;
        r4 = "value";
        r0 = r9.getAttributeValue(r0, r4);
        r1.<init>(r0);
        r0 = r1;
        goto L_0x0019;
    L_0x0100:
        r1 = "double";
        r1 = r3.equals(r1);
        if (r1 == 0) goto L_0x0118;
    L_0x0109:
        r1 = new java.lang.Double;
        r4 = "value";
        r0 = r9.getAttributeValue(r0, r4);
        r1.<init>(r0);
        r0 = r1;
        goto L_0x0019;
    L_0x0118:
        r1 = "boolean";
        r1 = r3.equals(r1);
        if (r1 == 0) goto L_0x012e;
    L_0x0121:
        r1 = "value";
        r0 = r9.getAttributeValue(r0, r1);
        r0 = java.lang.Boolean.valueOf(r0);
        goto L_0x0019;
    L_0x012e:
        r0 = "int-array";
        r0 = r3.equals(r0);
        if (r0 == 0) goto L_0x0145;
    L_0x0137:
        r9.next();
        r0 = "int-array";
        r0 = readThisIntArrayXml(r9, r0, r10);
        r10[r5] = r2;
        goto L_0x0068;
    L_0x0145:
        r0 = "map";
        r0 = r3.equals(r0);
        if (r0 == 0) goto L_0x015c;
    L_0x014e:
        r9.next();
        r0 = "map";
        r0 = readThisMapXml(r9, r0, r10);
        r10[r5] = r2;
        goto L_0x0068;
    L_0x015c:
        r0 = "list";
        r0 = r3.equals(r0);
        if (r0 == 0) goto L_0x0173;
    L_0x0165:
        r9.next();
        r0 = "list";
        r0 = readThisListXml(r9, r0, r10);
        r10[r5] = r2;
        goto L_0x0068;
    L_0x0173:
        r0 = new org.xmlpull.v1.XmlPullParserException;
        r1 = new java.lang.StringBuilder;
        r2 = "Unknown tag: ";
        r1.<init>(r2);
        r1 = r1.append(r3);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x0189:
        if (r1 != r8) goto L_0x01be;
    L_0x018b:
        r1 = r9.getName();
        r1 = r1.equals(r3);
        if (r1 == 0) goto L_0x0199;
    L_0x0195:
        r10[r5] = r2;
        goto L_0x0068;
    L_0x0199:
        r0 = new org.xmlpull.v1.XmlPullParserException;
        r1 = new java.lang.StringBuilder;
        r2 = "Unexpected end tag in <";
        r1.<init>(r2);
        r1 = r1.append(r3);
        r2 = ">: ";
        r1 = r1.append(r2);
        r2 = r9.getName();
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x01be:
        r4 = 4;
        if (r1 != r4) goto L_0x01e6;
    L_0x01c1:
        r0 = new org.xmlpull.v1.XmlPullParserException;
        r1 = new java.lang.StringBuilder;
        r2 = "Unexpected text in <";
        r1.<init>(r2);
        r1 = r1.append(r3);
        r2 = ">: ";
        r1 = r1.append(r2);
        r2 = r9.getName();
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x01e6:
        if (r1 != r7) goto L_0x0019;
    L_0x01e8:
        r0 = new org.xmlpull.v1.XmlPullParserException;
        r1 = new java.lang.StringBuilder;
        r2 = "Unexpected start tag in <";
        r1.<init>(r2);
        r1 = r1.append(r3);
        r2 = ">: ";
        r1 = r1.append(r2);
        r2 = r9.getName();
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
        */
    }

    public static final void beginDocument(XmlPullParser xmlPullParser, String str) throws XmlPullParserException, IOException {
        int next;
        do {
            next = xmlPullParser.next();
            if (next == 2) {
                break;
            }
        } while (next != 1);
        if (next != 2) {
            throw new XmlPullParserException("No start tag found");
        } else if (!xmlPullParser.getName().equals(str)) {
            throw new XmlPullParserException(new StringBuilder("Unexpected start tag: found ").append(xmlPullParser.getName()).append(", expected ").append(str).toString());
        }
    }

    public static final void nextElement(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        int next;
        do {
            next = xmlPullParser.next();
            if (next == 2) {
                return;
            }
        } while (next != 1);
    }
}
