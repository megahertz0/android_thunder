package com.xiaomi.smack;

import com.sina.weibo.sdk.component.GameManager;
import com.tencent.connect.common.Constants;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

public final class j {
    private static int a;
    private static int b;
    private static int c;
    private static int d;
    private static Vector<String> e;

    static {
        a = 5000;
        b = 330000;
        c = 300000;
        d = 330000;
        e = new Vector();
        try {
            InputStream inputStream;
            ClassLoader[] d = d();
            int length = d.length;
            for (int i = 0; i < length; i++) {
                Enumeration resources = d[i].getResources("META-INF/smack-config.xml");
                while (resources.hasMoreElements()) {
                    inputStream = null;
                    try {
                        inputStream = ((URL) resources.nextElement()).openStream();
                        XmlPullParser newPullParser = XmlPullParserFactory.newInstance().newPullParser();
                        newPullParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
                        newPullParser.setInput(inputStream, GameManager.DEFAULT_CHARSET);
                        int eventType = newPullParser.getEventType();
                        do {
                            if (eventType == 2) {
                                if (newPullParser.getName().equals("className")) {
                                    a(newPullParser);
                                } else if (newPullParser.getName().equals("packetReplyTimeout")) {
                                    a = a(newPullParser, a);
                                } else if (newPullParser.getName().equals("keepAliveInterval")) {
                                    b = a(newPullParser, b);
                                } else if (newPullParser.getName().equals("mechName")) {
                                    e.add(newPullParser.nextText());
                                }
                            }
                            eventType = newPullParser.next();
                        } while (eventType != 1);
                        try {
                            inputStream.close();
                        } catch (Exception e) {
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        try {
                            inputStream.close();
                        } catch (Exception e3) {
                        }
                    }
                }
            }
        } catch (Exception e22) {
            e22.printStackTrace();
        } catch (Throwable th) {
            try {
                inputStream.close();
            } catch (Exception e4) {
            }
        }
    }

    private j() {
    }

    private static int a(XmlPullParser xmlPullParser, int i) {
        try {
            return Integer.parseInt(xmlPullParser.nextText());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return i;
        }
    }

    public static String a() {
        return Constants.SDK_VERSION;
    }

    private static void a(XmlPullParser xmlPullParser) {
        String nextText = xmlPullParser.nextText();
        try {
            Class.forName(nextText);
        } catch (ClassNotFoundException e) {
            System.err.println(new StringBuilder("Error! A startup class specified in smack-config.xml could not be loaded: ").append(nextText).toString());
        }
    }

    public static int b() {
        return b;
    }

    public static int c() {
        return c;
    }

    private static ClassLoader[] d() {
        int i = 0;
        ClassLoader[] classLoaderArr = new ClassLoader[]{j.class.getClassLoader(), Thread.currentThread().getContextClassLoader()};
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
}
