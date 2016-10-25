package com.umeng.analytics.social;

import android.os.Build.VERSION;
import android.text.TextUtils;
import anet.channel.util.HttpConstant;
import com.tencent.bugly.Bugly;
import com.tencent.connect.common.Constants;
import com.umeng.analytics.a;
import com.xunlei.tdlive.sdk.IHost;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.zip.GZIPInputStream;
import java.util.zip.InflaterInputStream;

// compiled from: UMNetwork.java
public abstract class c {
    protected static String a(String str) {
        HttpURLConnection httpURLConnection = null;
        int nextInt = new Random().nextInt(IHost.HOST_NOFITY_REFRESH_LIST);
        try {
            String property = System.getProperty("line.separator");
            if (str.length() <= 1) {
                b.b(a.e, nextInt + ":\tInvalid baseUrl.");
                return null;
            }
            HttpURLConnection httpURLConnection2 = (HttpURLConnection) new URL(str).openConnection();
            try {
                httpURLConnection2.setConnectTimeout(10000);
                httpURLConnection2.setReadTimeout(com.alipay.sdk.data.a.d);
                httpURLConnection2.setRequestMethod(Constants.HTTP_GET);
                if (Integer.parseInt(VERSION.SDK) < 8) {
                    System.setProperty("http.keepAlive", Bugly.SDK_IS_DEV);
                }
                b.a(a.e, nextInt + ": GET_URL: " + str);
                if (httpURLConnection2.getResponseCode() == 200) {
                    InputStream gZIPInputStream;
                    InputStream inputStream = httpURLConnection2.getInputStream();
                    Object headerField = httpURLConnection2.getHeaderField(HttpConstant.CONTENT_ENCODING);
                    if (!TextUtils.isEmpty(headerField) && headerField.equalsIgnoreCase(HttpConstant.GZIP)) {
                        b.a(a.e, nextInt + "  Use GZIPInputStream get data....");
                        gZIPInputStream = new GZIPInputStream(inputStream);
                    } else if (TextUtils.isEmpty(headerField) || !headerField.equalsIgnoreCase("deflate")) {
                        gZIPInputStream = inputStream;
                    } else {
                        b.a(a.e, nextInt + "  Use InflaterInputStream get data....");
                        gZIPInputStream = new InflaterInputStream(inputStream);
                    }
                    String a = a(gZIPInputStream);
                    b.a(a.e, nextInt + ":  response: " + property + a);
                    if (a == null) {
                        if (httpURLConnection2 != null) {
                            httpURLConnection2.disconnect();
                        }
                        return null;
                    }
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    return a;
                }
                b.a(a.e, nextInt + ":  Failed to get message." + str);
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
                return null;
            } catch (Exception e) {
                Exception exception = e;
                r2 = httpURLConnection2;
                r0 = exception;
                try {
                    Exception exception2;
                    b.c(a.e, nextInt + ":\tException,Failed to send message." + str, exception2);
                    if (r2 != null) {
                        r2.disconnect();
                    }
                    return null;
                } catch (Throwable th) {
                    Throwable th2 = th;
                    httpURLConnection = r2;
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    throw th2;
                }
            } catch (Throwable th3) {
                Throwable th4 = th3;
                httpURLConnection = httpURLConnection2;
                th2 = th4;
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                throw th2;
            }
        } catch (Exception e2) {
            exception2 = e2;
            r2 = null;
            b.c(a.e, nextInt + ":\tException,Failed to send message." + str, exception2);
            HttpURLConnection httpURLConnection3;
            if (httpURLConnection3 != null) {
                httpURLConnection3.disconnect();
            }
            return null;
        } catch (Throwable th5) {
            th2 = th5;
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            throw th2;
        }
    }

    private static java.lang.String a(java.io.InputStream r5) {
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.social.c.a(java.io.InputStream):java.lang.String");
        /* JADX: method processing error */
/*
        Error: jadx.core.utils.exceptions.JadxRuntimeException: Try/catch wrap count limit reached in com.umeng.analytics.social.c.a(java.io.InputStream):java.lang.String
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:54)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:40)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:16)
	at jadx.core.ProcessClass.process(ProcessClass.java:22)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:209)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:133)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(Unknown Source)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(Unknown Source)
	at java.lang.Thread.run(Unknown Source)
*/
        /*
        r0 = 0;
        r1 = new java.io.BufferedReader;
        r2 = new java.io.InputStreamReader;
        r2.<init>(r5);
        r3 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
        r1.<init>(r2, r3);
        r2 = new java.lang.StringBuilder;
        r2.<init>();
    L_0x0012:
        r3 = r1.readLine();	 Catch:{ IOException -> 0x0030 }
        if (r3 == 0) goto L_0x003e;
    L_0x0018:
        r4 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x0030 }
        r4.<init>();	 Catch:{ IOException -> 0x0030 }
        r3 = r4.append(r3);	 Catch:{ IOException -> 0x0030 }
        r4 = "\n";
        r3 = r3.append(r4);	 Catch:{ IOException -> 0x0030 }
        r3 = r3.toString();	 Catch:{ IOException -> 0x0030 }
        r2.append(r3);	 Catch:{ IOException -> 0x0030 }
        goto L_0x0012;
    L_0x0030:
        r1 = move-exception;
        r2 = "MobclickAgent";
        r3 = "Caught IOException in convertStreamToString()";
        com.umeng.analytics.social.b.b(r2, r3, r1);	 Catch:{ all -> 0x005c }
        r5.close();	 Catch:{ IOException -> 0x0051 }
    L_0x003d:
        return r0;
    L_0x003e:
        r5.close();	 Catch:{ IOException -> 0x0046 }
        r0 = r2.toString();
        goto L_0x003d;
    L_0x0046:
        r1 = move-exception;
        r2 = "MobclickAgent";
        r3 = "Caught IOException in convertStreamToString()";
        com.umeng.analytics.social.b.b(r2, r3, r1);
        goto L_0x003d;
    L_0x0051:
        r1 = move-exception;
        r2 = "MobclickAgent";
        r3 = "Caught IOException in convertStreamToString()";
        com.umeng.analytics.social.b.b(r2, r3, r1);
        goto L_0x003d;
    L_0x005c:
        r1 = move-exception;
        r5.close();	 Catch:{ IOException -> 0x0061 }
        throw r1;
    L_0x0061:
        r1 = move-exception;
        r2 = "MobclickAgent";
        r3 = "Caught IOException in convertStreamToString()";
        com.umeng.analytics.social.b.b(r2, r3, r1);
        goto L_0x003d;
        */
    }

    protected static String a(String str, String str2) {
        HttpURLConnection httpURLConnection;
        Exception exception;
        Throwable th;
        int nextInt = new Random().nextInt(IHost.HOST_NOFITY_REFRESH_LIST);
        try {
            String property = System.getProperty("line.separator");
            HttpURLConnection httpURLConnection2 = (HttpURLConnection) new URL(str).openConnection();
            try {
                httpURLConnection2.setConnectTimeout(10000);
                httpURLConnection2.setReadTimeout(com.alipay.sdk.data.a.d);
                httpURLConnection2.setDoOutput(true);
                httpURLConnection2.setDoInput(true);
                httpURLConnection2.setUseCaches(false);
                httpURLConnection2.setChunkedStreamingMode(0);
                httpURLConnection2.setRequestMethod(Constants.HTTP_POST);
                b.a(a.e, nextInt + ": POST_URL: " + str);
                if (Integer.parseInt(VERSION.SDK) < 8) {
                    System.setProperty("http.keepAlive", Bugly.SDK_IS_DEV);
                }
                if (!TextUtils.isEmpty(str2)) {
                    b.a(a.e, nextInt + ": POST_BODY: " + str2);
                    List arrayList = new ArrayList();
                    arrayList.add(new StringBuilder("data=").append(str2).toString());
                    OutputStream outputStream = httpURLConnection2.getOutputStream();
                    outputStream.write(URLEncoder.encode(arrayList.toString()).getBytes());
                    outputStream.flush();
                    outputStream.close();
                }
                if (httpURLConnection2.getResponseCode() == 200) {
                    InputStream inputStream;
                    InputStream inputStream2 = httpURLConnection2.getInputStream();
                    Object headerField = httpURLConnection2.getHeaderField(HttpConstant.CONTENT_ENCODING);
                    if (TextUtils.isEmpty(headerField) || !headerField.equalsIgnoreCase(HttpConstant.GZIP)) {
                        inputStream = inputStream2;
                    } else {
                        inputStream = new InflaterInputStream(inputStream2);
                    }
                    String a = a(inputStream);
                    b.a(a.e, nextInt + ":  response: " + property + a);
                    if (a == null) {
                        if (httpURLConnection2 != null) {
                            httpURLConnection2.disconnect();
                        }
                        return null;
                    }
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    return a;
                }
                b.c(a.e, nextInt + ":  Failed to send message." + str);
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
                return null;
            } catch (Exception e) {
                Exception exception2 = e;
                httpURLConnection = httpURLConnection2;
                exception = exception2;
                b.c(a.e, nextInt + ":\tException,Failed to send message." + str, exception);
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                return null;
            } catch (Throwable th2) {
                httpURLConnection = httpURLConnection2;
                th = th2;
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                throw th;
            }
        } catch (Exception e2) {
            exception = e2;
            httpURLConnection = null;
            try {
                b.c(a.e, nextInt + ":\tException,Failed to send message." + str, exception);
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                return null;
            } catch (Throwable th3) {
                th = th3;
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            httpURLConnection = null;
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            throw th;
        }
    }
}
