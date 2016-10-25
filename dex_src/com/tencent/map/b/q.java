package com.tencent.map.b;

import android.net.Proxy;
import com.alipay.sdk.util.h;
import com.tencent.connect.common.Constants;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy.Type;
import java.net.URL;
import org.android.agoo.message.MessageService;

// compiled from: ProGuard
public final class q {
    private static int a;
    private static boolean b;

    static {
        a = 0;
    }

    private static HttpURLConnection a(String str, boolean z) {
        HttpURLConnection httpURLConnection;
        Exception exception;
        Throwable th;
        String defaultHost;
        HttpURLConnection httpURLConnection2 = null;
        boolean z2 = false;
        try {
            boolean z3;
            URL url = new URL(str);
            if (l.c()) {
                z3 = false;
            } else {
                m.a();
                z3 = !b.a(Proxy.getDefaultHost());
            }
            if (z3) {
                int defaultPort;
                if (a == 0 && !b) {
                    b = true;
                    try {
                        URL url2 = new URL("http://ls.map.soso.com/monitor/monitor.html");
                        z2 = Proxy.getDefaultHost();
                        defaultPort = Proxy.getDefaultPort();
                        if (defaultPort == -1) {
                            defaultPort = R.styleable.AppCompatTheme_panelMenuListTheme;
                        }
                        try {
                            HttpURLConnection httpURLConnection3 = (HttpURLConnection) url2.openConnection(new java.net.Proxy(Type.HTTP, new InetSocketAddress(z2, defaultPort)));
                            try {
                                httpURLConnection3.setRequestMethod(Constants.HTTP_GET);
                                httpURLConnection3.setConnectTimeout(15000);
                                httpURLConnection3.setReadTimeout(45000);
                                httpURLConnection3.setRequestProperty("User-Agent", "QQ Map Mobile");
                                httpURLConnection3.setDoInput(true);
                                httpURLConnection3.setDoOutput(false);
                                httpURLConnection3.setUseCaches(false);
                                z2 = a(httpURLConnection3);
                                httpURLConnection3.connect();
                                if (z2) {
                                    a(1);
                                } else {
                                    a((int) XZBDevice.DOWNLOAD_LIST_RECYCLE);
                                }
                                if (httpURLConnection3 != null) {
                                    httpURLConnection3.disconnect();
                                }
                            } catch (Exception e) {
                                Exception exception2 = e;
                                httpURLConnection = httpURLConnection3;
                                exception = exception2;
                                try {
                                    exception.printStackTrace();
                                    a((int) XZBDevice.DOWNLOAD_LIST_RECYCLE);
                                    if (httpURLConnection != null) {
                                        httpURLConnection.disconnect();
                                    }
                                } catch (Throwable th2) {
                                    th = th2;
                                    httpURLConnection2 = httpURLConnection;
                                    if (httpURLConnection2 != null) {
                                        httpURLConnection2.disconnect();
                                    }
                                    throw th;
                                }
                                switch (a) {
                                    case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                                        return a(url, str);
                                    default:
                                        defaultHost = Proxy.getDefaultHost();
                                        defaultPort = Proxy.getDefaultPort();
                                        if (defaultPort == -1) {
                                            defaultPort = R.styleable.AppCompatTheme_panelMenuListTheme;
                                        }
                                        return (HttpURLConnection) url.openConnection(new java.net.Proxy(Type.HTTP, new InetSocketAddress(defaultHost, defaultPort)));
                                }
                            } catch (Throwable th3) {
                                Throwable th4 = th3;
                                httpURLConnection2 = httpURLConnection3;
                                th = th4;
                                if (httpURLConnection2 != null) {
                                    httpURLConnection2.disconnect();
                                }
                                throw th;
                            }
                        } catch (Exception e2) {
                            exception = e2;
                            httpURLConnection = null;
                            exception.printStackTrace();
                            a((int) XZBDevice.DOWNLOAD_LIST_RECYCLE);
                            if (httpURLConnection != null) {
                                httpURLConnection.disconnect();
                            }
                            switch (a) {
                                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                                    return a(url, str);
                                default:
                                    defaultHost = Proxy.getDefaultHost();
                                    defaultPort = Proxy.getDefaultPort();
                                    if (defaultPort == -1) {
                                        defaultPort = R.styleable.AppCompatTheme_panelMenuListTheme;
                                    }
                                    return (HttpURLConnection) url.openConnection(new java.net.Proxy(Type.HTTP, new InetSocketAddress(defaultHost, defaultPort)));
                            }
                        } catch (Throwable th5) {
                            th = th5;
                            if (httpURLConnection2 != null) {
                                httpURLConnection2.disconnect();
                            }
                            throw th;
                        }
                    } catch (MalformedURLException e3) {
                        b = z2;
                    }
                }
                try {
                    switch (a) {
                        case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                            return a(url, str);
                        default:
                            defaultHost = Proxy.getDefaultHost();
                            defaultPort = Proxy.getDefaultPort();
                            if (defaultPort == -1) {
                                defaultPort = R.styleable.AppCompatTheme_panelMenuListTheme;
                            }
                            return (HttpURLConnection) url.openConnection(new java.net.Proxy(Type.HTTP, new InetSocketAddress(defaultHost, defaultPort)));
                    }
                } catch (IOException e4) {
                    e4.printStackTrace();
                    return null;
                }
            }
            try {
                return (HttpURLConnection) url.openConnection();
            } catch (IOException e42) {
                e42.printStackTrace();
                return null;
            }
        } catch (MalformedURLException e5) {
            e5.printStackTrace();
            return null;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.tencent.map.b.n a(boolean r6, java.lang.String r7, java.lang.String r8, java.lang.String r9, byte[] r10, boolean r11, boolean r12) throws java.lang.Exception {
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.map.b.q.a(boolean, java.lang.String, java.lang.String, java.lang.String, byte[], boolean, boolean):com.tencent.map.b.n");
        /*
        r2 = 1;
        r0 = 0;
        r1 = 0;
        r3 = com.tencent.map.b.l.d();
        if (r3 != 0) goto L_0x000f;
    L_0x0009:
        r0 = new com.tencent.map.b.r;
        r0.<init>();
        throw r0;
    L_0x000f:
        r3 = a(r7, r12);	 Catch:{ p -> 0x0124, Exception -> 0x011e, all -> 0x0118 }
        r4 = 0;
        r4 = com.tencent.map.b.b.a(r4);	 Catch:{ p -> 0x00ad, Exception -> 0x00c9 }
        if (r4 == 0) goto L_0x00a4;
    L_0x001a:
        r4 = r3.getURL();	 Catch:{ p -> 0x00ad, Exception -> 0x00c9 }
        r4 = r4.getHost();	 Catch:{ p -> 0x00ad, Exception -> 0x00c9 }
        r4 = com.tencent.map.b.b.a(r4);	 Catch:{ p -> 0x00ad, Exception -> 0x00c9 }
        if (r4 == 0) goto L_0x0028;
    L_0x0028:
        if (r6 == 0) goto L_0x00c1;
    L_0x002a:
        r4 = "GET";
        r3.setRequestMethod(r4);	 Catch:{ p -> 0x00ad, Exception -> 0x00c9 }
    L_0x0030:
        r4 = com.tencent.map.b.k.a();	 Catch:{ p -> 0x00ad, Exception -> 0x00c9 }
        r3.setConnectTimeout(r4);	 Catch:{ p -> 0x00ad, Exception -> 0x00c9 }
        r4 = com.tencent.map.b.k.b();	 Catch:{ p -> 0x00ad, Exception -> 0x00c9 }
        r3.setReadTimeout(r4);	 Catch:{ p -> 0x00ad, Exception -> 0x00c9 }
        r4 = "User-Agent";
        r3.setRequestProperty(r4, r8);	 Catch:{ p -> 0x00ad, Exception -> 0x00c9 }
        r4 = 1;
        r3.setDoInput(r4);	 Catch:{ p -> 0x00ad, Exception -> 0x00c9 }
        if (r6 == 0) goto L_0x004b;
    L_0x004a:
        r2 = r0;
    L_0x004b:
        r3.setDoOutput(r2);	 Catch:{ p -> 0x00ad, Exception -> 0x00c9 }
        r2 = 0;
        r3.setUseCaches(r2);	 Catch:{ p -> 0x00ad, Exception -> 0x00c9 }
        if (r11 == 0) goto L_0x005d;
    L_0x0054:
        r2 = "Connection";
        r4 = "Keep-Alive";
        r3.setRequestProperty(r2, r4);	 Catch:{ p -> 0x00ad, Exception -> 0x00c9 }
    L_0x005d:
        com.tencent.map.b.k.a(r3);	 Catch:{ p -> 0x00ad, Exception -> 0x00c9 }
        r3.connect();	 Catch:{ p -> 0x00ad, Exception -> 0x00c9 }
        com.tencent.map.b.k.c();	 Catch:{ p -> 0x00ad, Exception -> 0x00c9 }
        if (r6 != 0) goto L_0x007f;
    L_0x0068:
        if (r10 == 0) goto L_0x007f;
    L_0x006a:
        r2 = r10.length;	 Catch:{ p -> 0x00ad, Exception -> 0x00c9 }
        if (r2 == 0) goto L_0x007f;
    L_0x006d:
        r2 = new java.io.DataOutputStream;	 Catch:{ p -> 0x00ad, Exception -> 0x00c9 }
        r4 = r3.getOutputStream();	 Catch:{ p -> 0x00ad, Exception -> 0x00c9 }
        r2.<init>(r4);	 Catch:{ p -> 0x00ad, Exception -> 0x00c9 }
        r2.write(r10);	 Catch:{ p -> 0x0127, Exception -> 0x0121, all -> 0x011b }
        r2.flush();	 Catch:{ p -> 0x0127, Exception -> 0x0121, all -> 0x011b }
        r2.close();	 Catch:{ p -> 0x0127, Exception -> 0x0121, all -> 0x011b }
    L_0x007f:
        r2 = r3.getResponseCode();	 Catch:{ p -> 0x00ad, Exception -> 0x00c9 }
        r4 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r2 == r4) goto L_0x008b;
    L_0x0087:
        r4 = 206; // 0xce float:2.89E-43 double:1.02E-321;
        if (r2 != r4) goto L_0x00d1;
    L_0x008b:
        com.tencent.map.b.k.d();	 Catch:{ p -> 0x00ad, Exception -> 0x00c9 }
        r2 = a(r3, r6);	 Catch:{ p -> 0x00ad, Exception -> 0x00c9 }
        if (r2 == 0) goto L_0x009b;
    L_0x0094:
        r4 = r2.a;	 Catch:{ p -> 0x00ad, Exception -> 0x00c9 }
        if (r4 == 0) goto L_0x009b;
    L_0x0098:
        r0 = r2.a;	 Catch:{ p -> 0x00ad, Exception -> 0x00c9 }
        r0 = r0.length;	 Catch:{ p -> 0x00ad, Exception -> 0x00c9 }
    L_0x009b:
        com.tencent.map.b.k.a(r0);	 Catch:{ p -> 0x00ad, Exception -> 0x00c9 }
        if (r3 == 0) goto L_0x00a3;
    L_0x00a0:
        r3.disconnect();
    L_0x00a3:
        return r2;
    L_0x00a4:
        r4 = "Host";
        r5 = 0;
        r3.setRequestProperty(r4, r5);	 Catch:{ p -> 0x00ad, Exception -> 0x00c9 }
        goto L_0x0028;
    L_0x00ad:
        r0 = move-exception;
        r2 = r3;
    L_0x00af:
        r3 = 1;
        com.tencent.map.b.k.a(r3);	 Catch:{ all -> 0x00b4 }
        throw r0;	 Catch:{ all -> 0x00b4 }
    L_0x00b4:
        r0 = move-exception;
        r3 = r2;
    L_0x00b6:
        if (r1 == 0) goto L_0x00bb;
    L_0x00b8:
        r1.close();
    L_0x00bb:
        if (r3 == 0) goto L_0x00c0;
    L_0x00bd:
        r3.disconnect();
    L_0x00c0:
        throw r0;
    L_0x00c1:
        r4 = "POST";
        r3.setRequestMethod(r4);	 Catch:{ p -> 0x00ad, Exception -> 0x00c9 }
        goto L_0x0030;
    L_0x00c9:
        r0 = move-exception;
    L_0x00ca:
        r2 = 0;
        com.tencent.map.b.k.a(r2);	 Catch:{ all -> 0x00cf }
        throw r0;	 Catch:{ all -> 0x00cf }
    L_0x00cf:
        r0 = move-exception;
        goto L_0x00b6;
    L_0x00d1:
        r0 = 202; // 0xca float:2.83E-43 double:1.0E-321;
        if (r2 == r0) goto L_0x00f9;
    L_0x00d5:
        r0 = 201; // 0xc9 float:2.82E-43 double:9.93E-322;
        if (r2 == r0) goto L_0x00f9;
    L_0x00d9:
        r0 = 204; // 0xcc float:2.86E-43 double:1.01E-321;
        if (r2 == r0) goto L_0x00f9;
    L_0x00dd:
        r0 = 205; // 0xcd float:2.87E-43 double:1.013E-321;
        if (r2 == r0) goto L_0x00f9;
    L_0x00e1:
        r0 = 304; // 0x130 float:4.26E-43 double:1.5E-321;
        if (r2 == r0) goto L_0x00f9;
    L_0x00e5:
        r0 = 305; // 0x131 float:4.27E-43 double:1.507E-321;
        if (r2 == r0) goto L_0x00f9;
    L_0x00e9:
        r0 = 408; // 0x198 float:5.72E-43 double:2.016E-321;
        if (r2 == r0) goto L_0x00f9;
    L_0x00ed:
        r0 = 502; // 0x1f6 float:7.03E-43 double:2.48E-321;
        if (r2 == r0) goto L_0x00f9;
    L_0x00f1:
        r0 = 504; // 0x1f8 float:7.06E-43 double:2.49E-321;
        if (r2 == r0) goto L_0x00f9;
    L_0x00f5:
        r0 = 503; // 0x1f7 float:7.05E-43 double:2.485E-321;
        if (r2 != r0) goto L_0x0102;
    L_0x00f9:
        r0 = new java.io.IOException;	 Catch:{ p -> 0x00ad, Exception -> 0x00c9 }
        r2 = "doGetOrPost retry";
        r0.<init>(r2);	 Catch:{ p -> 0x00ad, Exception -> 0x00c9 }
        throw r0;	 Catch:{ p -> 0x00ad, Exception -> 0x00c9 }
    L_0x0102:
        r0 = new com.tencent.map.b.p;	 Catch:{ p -> 0x00ad, Exception -> 0x00c9 }
        r4 = new java.lang.StringBuilder;	 Catch:{ p -> 0x00ad, Exception -> 0x00c9 }
        r5 = "response code is ";
        r4.<init>(r5);	 Catch:{ p -> 0x00ad, Exception -> 0x00c9 }
        r2 = r4.append(r2);	 Catch:{ p -> 0x00ad, Exception -> 0x00c9 }
        r2 = r2.toString();	 Catch:{ p -> 0x00ad, Exception -> 0x00c9 }
        r0.<init>(r2);	 Catch:{ p -> 0x00ad, Exception -> 0x00c9 }
        throw r0;	 Catch:{ p -> 0x00ad, Exception -> 0x00c9 }
    L_0x0118:
        r0 = move-exception;
        r3 = r1;
        goto L_0x00b6;
    L_0x011b:
        r0 = move-exception;
        r1 = r2;
        goto L_0x00b6;
    L_0x011e:
        r0 = move-exception;
        r3 = r1;
        goto L_0x00ca;
    L_0x0121:
        r0 = move-exception;
        r1 = r2;
        goto L_0x00ca;
    L_0x0124:
        r0 = move-exception;
        r2 = r1;
        goto L_0x00af;
    L_0x0127:
        r0 = move-exception;
        r1 = r2;
        r2 = r3;
        goto L_0x00af;
        */
    }

    private static n a(HttpURLConnection httpURLConnection, boolean z) throws IOException {
        Object obj = 1;
        int i = 0;
        InputStream inputStream = null;
        try {
            n nVar = new n();
            String contentType = httpURLConnection.getContentType();
            String str = "GBK";
            if (contentType != null) {
                String[] split = contentType.split(h.b);
                int length = split.length;
                int i2 = 0;
                while (i2 < length) {
                    String str2 = split[i2];
                    if (str2.contains("charset")) {
                        String[] split2 = str2.split("=");
                        if (split2.length > 1) {
                            str = split2[1].trim();
                        }
                    } else {
                        i2++;
                    }
                }
            }
            nVar.b = str;
            if (z) {
                int i3;
                if (contentType == null || !contentType.contains("vnd.wap.wml")) {
                    i3 = 0;
                }
                if (i3 != 0) {
                    httpURLConnection.disconnect();
                    httpURLConnection.connect();
                }
            }
            inputStream = httpURLConnection.getInputStream();
            if (inputStream != null) {
                nVar.a = new byte[0];
                obj = new Object[1024];
                int read;
                do {
                    read = inputStream.read(obj);
                    if (read > 0) {
                        i += read;
                        Object obj2 = new Object[i];
                        System.arraycopy(nVar.a, 0, obj2, 0, nVar.a.length);
                        System.arraycopy(obj, 0, obj2, nVar.a.length, read);
                        nVar.a = obj2;
                        continue;
                    }
                } while (read > 0);
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                }
            }
            return nVar;
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e2) {
                }
            }
        }
    }

    private static void a(int i) {
        if (a != i) {
            a = i;
        }
    }

    private static boolean a(HttpURLConnection httpURLConnection) throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = httpURLConnection.getInputStream();
            if (httpURLConnection.getContentType().equals("text/html")) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                while (inputStream.available() > 0) {
                    byteArrayOutputStream.write(inputStream.read());
                }
                boolean equals = new String(byteArrayOutputStream.toByteArray()).trim().equals(MessageService.MSG_DB_NOTIFY_REACHED);
                if (inputStream == null) {
                    return equals;
                }
                inputStream.close();
                return equals;
            }
            if (inputStream != null) {
                inputStream.close();
            }
            return false;
        } catch (Throwable th) {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    private static HttpURLConnection a(URL url, String str) throws IOException {
        String replaceFirst;
        int i = R.styleable.AppCompatTheme_panelMenuListTheme;
        String defaultHost = Proxy.getDefaultHost();
        int defaultPort = Proxy.getDefaultPort();
        if (defaultPort == -1) {
            defaultPort = 80;
        }
        String host = url.getHost();
        int port = url.getPort();
        if (port != -1) {
            i = port;
        }
        if (str.indexOf(new StringBuilder(String.valueOf(host)).append(":").append(i).toString()) != -1) {
            replaceFirst = str.replaceFirst(new StringBuilder(String.valueOf(host)).append(":").append(i).toString(), new StringBuilder(String.valueOf(defaultHost)).append(":").append(defaultPort).toString());
        } else {
            replaceFirst = str.replaceFirst(host, new StringBuilder(String.valueOf(defaultHost)).append(":").append(defaultPort).toString());
        }
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(replaceFirst).openConnection();
            httpURLConnection.setRequestProperty("X-Online-Host", new StringBuilder(String.valueOf(host)).append(":").append(i).toString());
            return httpURLConnection;
        } catch (MalformedURLException e) {
            return null;
        }
    }
}
