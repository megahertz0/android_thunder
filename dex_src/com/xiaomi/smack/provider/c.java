package com.xiaomi.smack.provider;

import com.xiaomi.smack.packet.b;
import com.xiaomi.smack.packet.e;
import com.xunlei.common.encrypt.CharsetConvert;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

public class c {
    private static c a;
    private Map<String, Object> b;
    private Map<String, Object> c;

    private c() {
        this.b = new ConcurrentHashMap();
        this.c = new ConcurrentHashMap();
        b();
    }

    public static synchronized c a() {
        c cVar;
        synchronized (c.class) {
            if (a == null) {
                a = new c();
            }
            cVar = a;
        }
        return cVar;
    }

    private String b(String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<").append(str).append("/>");
        if (str != null) {
            stringBuilder.append("<").append(str2).append("/>");
        }
        return stringBuilder.toString();
    }

    private ClassLoader[] c() {
        int i = 0;
        ClassLoader[] classLoaderArr = new ClassLoader[]{c.class.getClassLoader(), Thread.currentThread().getContextClassLoader()};
        List arrayList = new ArrayList();
        while (i < 2) {
            Object obj = classLoaderArr[i];
            if (obj != null) {
                arrayList.add(obj);
            }
            i++;
        }
        return (ClassLoader[]) arrayList.toArray(new ClassLoader[arrayList.size()]);
    }

    public Object a(String str, String str2) {
        return this.b.get(b(str, str2));
    }

    public void a(String str, String str2, Object obj) {
        if ((obj instanceof b) || (obj instanceof Class)) {
            this.b.put(b(str, str2), obj);
            return;
        }
        throw new IllegalArgumentException("Provider must be a PacketExtensionProvider or a Class instance.");
    }

    protected void b() {
        try {
            InputStream inputStream;
            ClassLoader[] c = c();
            int length = c.length;
            for (int i = 0; i < length; i++) {
                Enumeration resources = c[i].getResources("META-INF/smack.providers");
                while (resources.hasMoreElements()) {
                    inputStream = null;
                    inputStream = ((URL) resources.nextElement()).openStream();
                    XmlPullParser newPullParser = XmlPullParserFactory.newInstance().newPullParser();
                    newPullParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
                    newPullParser.setInput(inputStream, CharsetConvert.UTF_8);
                    int eventType = newPullParser.getEventType();
                    do {
                        if (eventType == 2) {
                            String nextText;
                            String nextText2;
                            String nextText3;
                            Class forName;
                            if (newPullParser.getName().equals("iqProvider")) {
                                newPullParser.next();
                                newPullParser.next();
                                nextText = newPullParser.nextText();
                                newPullParser.next();
                                newPullParser.next();
                                nextText2 = newPullParser.nextText();
                                newPullParser.next();
                                newPullParser.next();
                                nextText3 = newPullParser.nextText();
                                nextText = b(nextText, nextText2);
                                if (!this.c.containsKey(nextText)) {
                                    try {
                                        forName = Class.forName(nextText3);
                                        if (a.class.isAssignableFrom(forName)) {
                                            this.c.put(nextText, forName.newInstance());
                                        } else if (b.class.isAssignableFrom(forName)) {
                                            this.c.put(nextText, forName);
                                        }
                                    } catch (ClassNotFoundException e) {
                                        e.printStackTrace();
                                    }
                                }
                            } else if (newPullParser.getName().equals("extensionProvider")) {
                                newPullParser.next();
                                newPullParser.next();
                                nextText = newPullParser.nextText();
                                newPullParser.next();
                                newPullParser.next();
                                nextText2 = newPullParser.nextText();
                                newPullParser.next();
                                newPullParser.next();
                                nextText3 = newPullParser.nextText();
                                nextText = b(nextText, nextText2);
                                if (!this.b.containsKey(nextText)) {
                                    try {
                                        forName = Class.forName(nextText3);
                                        if (b.class.isAssignableFrom(forName)) {
                                            this.b.put(nextText, forName.newInstance());
                                        } else if (e.class.isAssignableFrom(forName)) {
                                            this.b.put(nextText, forName);
                                        }
                                    } catch (ClassNotFoundException e2) {
                                        e2.printStackTrace();
                                    }
                                }
                            }
                        }
                        eventType = newPullParser.next();
                    } while (eventType != 1);
                    try {
                        inputStream.close();
                    } catch (Exception e3) {
                    }
                }
            }
        } catch (Exception e4) {
            e4.printStackTrace();
        } catch (Throwable th) {
            try {
                inputStream.close();
            } catch (Exception e5) {
            }
        }
    }
}
