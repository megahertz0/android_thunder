package com.xiaomi.channel.commonutils.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import anet.channel.util.HttpConstant;
import com.alipay.sdk.cons.b;
import com.alipay.sdk.sys.a;
import com.sina.weibo.sdk.component.GameManager;
import com.taobao.accs.utl.UtilityImpl;
import com.tencent.connect.common.Constants;
import com.tencent.open.SocialConstants;
import com.umeng.socialize.common.SocializeConstants;
import com.xiaomi.channel.commonutils.string.c;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

public class d {
    public static final Pattern a;
    public static final Pattern b;
    public static final Pattern c;

    static {
        a = Pattern.compile("([^\\s;]+)(.*)");
        b = Pattern.compile("(.*?charset\\s*=[^a-zA-Z0-9]*)([-a-zA-Z0-9]+)(.*)", XZBDevice.DOWNLOAD_LIST_RECYCLE);
        c = Pattern.compile("(\\<\\?xml\\s+.*?encoding\\s*=[^a-zA-Z0-9]*)([-a-zA-Z0-9]+)(.*)", XZBDevice.DOWNLOAD_LIST_RECYCLE);
    }

    public static int a(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return -1;
            }
            try {
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                return activeNetworkInfo == null ? -1 : activeNetworkInfo.getType();
            } catch (Exception e) {
                return -1;
            }
        } catch (Exception e2) {
            return -1;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.xiaomi.channel.commonutils.network.b a(android.content.Context r9, java.lang.String r10, java.lang.String r11, java.util.Map<java.lang.String, java.lang.String> r12, java.lang.String r13) {
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.channel.commonutils.network.d.a(android.content.Context, java.lang.String, java.lang.String, java.util.Map, java.lang.String):com.xiaomi.channel.commonutils.network.b");
        /*
        r3 = 0;
        r2 = 0;
        r4 = new com.xiaomi.channel.commonutils.network.b;
        r4.<init>();
        r0 = b(r10);	 Catch:{ IOException -> 0x0041, Throwable -> 0x00df, all -> 0x00fb }
        r5 = b(r9, r0);	 Catch:{ IOException -> 0x0041, Throwable -> 0x00df, all -> 0x00fb }
        r0 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        r5.setConnectTimeout(r0);	 Catch:{ IOException -> 0x0041, Throwable -> 0x00df, all -> 0x00fb }
        r0 = 15000; // 0x3a98 float:2.102E-41 double:7.411E-320;
        r5.setReadTimeout(r0);	 Catch:{ IOException -> 0x0041, Throwable -> 0x00df, all -> 0x00fb }
        if (r11 != 0) goto L_0x001e;
    L_0x001b:
        r11 = "GET";
    L_0x001e:
        r5.setRequestMethod(r11);	 Catch:{ IOException -> 0x0041, Throwable -> 0x00df, all -> 0x00fb }
        if (r12 == 0) goto L_0x0053;
    L_0x0023:
        r0 = r12.keySet();	 Catch:{ IOException -> 0x0041, Throwable -> 0x00df, all -> 0x00fb }
        r6 = r0.iterator();	 Catch:{ IOException -> 0x0041, Throwable -> 0x00df, all -> 0x00fb }
    L_0x002b:
        r0 = r6.hasNext();	 Catch:{ IOException -> 0x0041, Throwable -> 0x00df, all -> 0x00fb }
        if (r0 == 0) goto L_0x0053;
    L_0x0031:
        r0 = r6.next();	 Catch:{ IOException -> 0x0041, Throwable -> 0x00df, all -> 0x00fb }
        r0 = (java.lang.String) r0;	 Catch:{ IOException -> 0x0041, Throwable -> 0x00df, all -> 0x00fb }
        r1 = r12.get(r0);	 Catch:{ IOException -> 0x0041, Throwable -> 0x00df, all -> 0x00fb }
        r1 = (java.lang.String) r1;	 Catch:{ IOException -> 0x0041, Throwable -> 0x00df, all -> 0x00fb }
        r5.setRequestProperty(r0, r1);	 Catch:{ IOException -> 0x0041, Throwable -> 0x00df, all -> 0x00fb }
        goto L_0x002b;
    L_0x0041:
        r0 = move-exception;
        r1 = r2;
    L_0x0043:
        throw r0;	 Catch:{ all -> 0x0044 }
    L_0x0044:
        r0 = move-exception;
        r8 = r1;
        r1 = r2;
        r2 = r8;
    L_0x0048:
        if (r1 == 0) goto L_0x004d;
    L_0x004a:
        r1.close();	 Catch:{ IOException -> 0x00f8 }
    L_0x004d:
        if (r2 == 0) goto L_0x0052;
    L_0x004f:
        r2.close();	 Catch:{ IOException -> 0x00f8 }
    L_0x0052:
        throw r0;
    L_0x0053:
        r0 = android.text.TextUtils.isEmpty(r13);	 Catch:{ IOException -> 0x0041, Throwable -> 0x00df, all -> 0x00fb }
        if (r0 != 0) goto L_0x0070;
    L_0x0059:
        r0 = 1;
        r5.setDoOutput(r0);	 Catch:{ IOException -> 0x0041, Throwable -> 0x00df, all -> 0x00fb }
        r0 = r13.getBytes();	 Catch:{ IOException -> 0x0041, Throwable -> 0x00df, all -> 0x00fb }
        r1 = r5.getOutputStream();	 Catch:{ IOException -> 0x0041, Throwable -> 0x00df, all -> 0x00fb }
        r6 = 0;
        r7 = r0.length;	 Catch:{ IOException -> 0x010c, Throwable -> 0x0105 }
        r1.write(r0, r6, r7);	 Catch:{ IOException -> 0x010c, Throwable -> 0x0105 }
        r1.flush();	 Catch:{ IOException -> 0x010c, Throwable -> 0x0105 }
        r1.close();	 Catch:{ IOException -> 0x010c, Throwable -> 0x0105 }
    L_0x0070:
        r0 = r5.getResponseCode();	 Catch:{ IOException -> 0x0041, Throwable -> 0x00df, all -> 0x00fb }
        r4.a = r0;	 Catch:{ IOException -> 0x0041, Throwable -> 0x00df, all -> 0x00fb }
        r0 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x0041, Throwable -> 0x00df, all -> 0x00fb }
        r1 = "Http POST Response Code: ";
        r0.<init>(r1);	 Catch:{ IOException -> 0x0041, Throwable -> 0x00df, all -> 0x00fb }
        r1 = r4.a;	 Catch:{ IOException -> 0x0041, Throwable -> 0x00df, all -> 0x00fb }
        r0.append(r1);	 Catch:{ IOException -> 0x0041, Throwable -> 0x00df, all -> 0x00fb }
        r0 = r3;
    L_0x0084:
        r1 = r5.getHeaderFieldKey(r0);	 Catch:{ IOException -> 0x0041, Throwable -> 0x00df, all -> 0x00fb }
        r3 = r5.getHeaderField(r0);	 Catch:{ IOException -> 0x0041, Throwable -> 0x00df, all -> 0x00fb }
        if (r1 != 0) goto L_0x0090;
    L_0x008e:
        if (r3 == 0) goto L_0x009a;
    L_0x0090:
        r6 = r4.b;	 Catch:{ IOException -> 0x0041, Throwable -> 0x00df, all -> 0x00fb }
        r6.put(r1, r3);	 Catch:{ IOException -> 0x0041, Throwable -> 0x00df, all -> 0x00fb }
        r0 = r0 + 1;
        r0 = r0 + 1;
        goto L_0x0084;
    L_0x009a:
        r1 = new java.io.BufferedReader;	 Catch:{ IOException -> 0x00ca, Throwable -> 0x00df, all -> 0x00fb }
        r0 = new java.io.InputStreamReader;	 Catch:{ IOException -> 0x00ca, Throwable -> 0x00df, all -> 0x00fb }
        r3 = new com.xiaomi.channel.commonutils.network.d$a;	 Catch:{ IOException -> 0x00ca, Throwable -> 0x00df, all -> 0x00fb }
        r6 = r5.getInputStream();	 Catch:{ IOException -> 0x00ca, Throwable -> 0x00df, all -> 0x00fb }
        r3.<init>(r6);	 Catch:{ IOException -> 0x00ca, Throwable -> 0x00df, all -> 0x00fb }
        r0.<init>(r3);	 Catch:{ IOException -> 0x00ca, Throwable -> 0x00df, all -> 0x00fb }
        r1.<init>(r0);	 Catch:{ IOException -> 0x00ca, Throwable -> 0x00df, all -> 0x00fb }
    L_0x00ad:
        r0 = r1.readLine();	 Catch:{ IOException -> 0x0112, Throwable -> 0x0107, all -> 0x00ff }
        r3 = new java.lang.StringBuffer;	 Catch:{ IOException -> 0x0112, Throwable -> 0x0107, all -> 0x00ff }
        r3.<init>();	 Catch:{ IOException -> 0x0112, Throwable -> 0x0107, all -> 0x00ff }
        r5 = "line.separator";
        r5 = java.lang.System.getProperty(r5);	 Catch:{ IOException -> 0x0112, Throwable -> 0x0107, all -> 0x00ff }
    L_0x00bd:
        if (r0 == 0) goto L_0x00ee;
    L_0x00bf:
        r3.append(r0);	 Catch:{ IOException -> 0x0112, Throwable -> 0x0107, all -> 0x00ff }
        r3.append(r5);	 Catch:{ IOException -> 0x0112, Throwable -> 0x0107, all -> 0x00ff }
        r0 = r1.readLine();	 Catch:{ IOException -> 0x0112, Throwable -> 0x0107, all -> 0x00ff }
        goto L_0x00bd;
    L_0x00ca:
        r0 = move-exception;
        r1 = new java.io.BufferedReader;	 Catch:{ IOException -> 0x0041, Throwable -> 0x00df, all -> 0x00fb }
        r0 = new java.io.InputStreamReader;	 Catch:{ IOException -> 0x0041, Throwable -> 0x00df, all -> 0x00fb }
        r3 = new com.xiaomi.channel.commonutils.network.d$a;	 Catch:{ IOException -> 0x0041, Throwable -> 0x00df, all -> 0x00fb }
        r5 = r5.getErrorStream();	 Catch:{ IOException -> 0x0041, Throwable -> 0x00df, all -> 0x00fb }
        r3.<init>(r5);	 Catch:{ IOException -> 0x0041, Throwable -> 0x00df, all -> 0x00fb }
        r0.<init>(r3);	 Catch:{ IOException -> 0x0041, Throwable -> 0x00df, all -> 0x00fb }
        r1.<init>(r0);	 Catch:{ IOException -> 0x0041, Throwable -> 0x00df, all -> 0x00fb }
        goto L_0x00ad;
    L_0x00df:
        r0 = move-exception;
        r1 = r2;
    L_0x00e1:
        r3 = new java.io.IOException;	 Catch:{ all -> 0x00eb }
        r0 = r0.getMessage();	 Catch:{ all -> 0x00eb }
        r3.<init>(r0);	 Catch:{ all -> 0x00eb }
        throw r3;	 Catch:{ all -> 0x00eb }
    L_0x00eb:
        r0 = move-exception;
        goto L_0x0048;
    L_0x00ee:
        r0 = r3.toString();	 Catch:{ IOException -> 0x0112, Throwable -> 0x0107, all -> 0x00ff }
        r4.c = r0;	 Catch:{ IOException -> 0x0112, Throwable -> 0x0107, all -> 0x00ff }
        r1.close();	 Catch:{ IOException -> 0x0112, Throwable -> 0x0107, all -> 0x00ff }
        return r4;
    L_0x00f8:
        r1 = move-exception;
        goto L_0x0052;
    L_0x00fb:
        r0 = move-exception;
        r1 = r2;
        goto L_0x0048;
    L_0x00ff:
        r0 = move-exception;
        r8 = r1;
        r1 = r2;
        r2 = r8;
        goto L_0x0048;
    L_0x0105:
        r0 = move-exception;
        goto L_0x00e1;
    L_0x0107:
        r0 = move-exception;
        r8 = r1;
        r1 = r2;
        r2 = r8;
        goto L_0x00e1;
    L_0x010c:
        r0 = move-exception;
        r8 = r2;
        r2 = r1;
        r1 = r8;
        goto L_0x0043;
    L_0x0112:
        r0 = move-exception;
        goto L_0x0043;
        */
    }

    public static b a(Context context, String str, Map<String, String> map) {
        return a(context, str, Constants.HTTP_POST, null, a((Map) map));
    }

    public static InputStream a(Context context, URL url, boolean z, String str, String str2) {
        return a(context, url, z, str, str2, null, null);
    }

    public static InputStream a(Context context, URL url, boolean z, String str, String str2, Map<String, String> map, b bVar) {
        if (context == null) {
            throw new IllegalArgumentException("context");
        } else if (url == null) {
            throw new IllegalArgumentException(SocialConstants.PARAM_URL);
        } else {
            URL url2 = !z ? new URL(a(url.toString())) : url;
            try {
                HttpURLConnection.setFollowRedirects(true);
                HttpURLConnection b = b(context, url2);
                b.setConnectTimeout(10000);
                b.setReadTimeout(15000);
                if (!TextUtils.isEmpty(str)) {
                    b.setRequestProperty("User-Agent", str);
                }
                if (str2 != null) {
                    b.setRequestProperty("Cookie", str2);
                }
                if (map != null) {
                    for (String str3 : map.keySet()) {
                        b.setRequestProperty(str3, (String) map.get(str3));
                    }
                }
                if (bVar != null) {
                    if (url.getProtocol().equals(HttpConstant.HTTP) || url.getProtocol().equals(b.a)) {
                        bVar.a = b.getResponseCode();
                        if (bVar.b == null) {
                            bVar.b = new HashMap();
                        }
                        int i = 0;
                        while (true) {
                            CharSequence headerFieldKey = b.getHeaderFieldKey(i);
                            CharSequence headerField = b.getHeaderField(i);
                            if (headerFieldKey == null && headerField == null) {
                                break;
                            }
                            if (!TextUtils.isEmpty(headerFieldKey) && !TextUtils.isEmpty(headerField)) {
                                bVar.b.put(headerFieldKey, headerField);
                            }
                            i++;
                        }
                    }
                }
                return new a(b.getInputStream());
            } catch (IOException e) {
                throw e;
            }
        }
    }

    public static String a(Context context, URL url) {
        return a(context, url, false, null, GameManager.DEFAULT_CHARSET, null);
    }

    public static String a(Context context, URL url, boolean z, String str, String str2, String str3) {
        InputStream inputStream = null;
        try {
            inputStream = a(context, url, z, str, str3);
            StringBuilder stringBuilder = new StringBuilder(1024);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, str2));
            char[] cArr = new char[4096];
            while (true) {
                int read = bufferedReader.read(cArr);
                if (-1 == read) {
                    break;
                }
                stringBuilder.append(cArr, 0, read);
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    new StringBuilder("Failed to close responseStream").append(e.toString());
                }
            }
            return stringBuilder.toString();
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e2) {
                    new StringBuilder("Failed to close responseStream").append(e2.toString());
                }
            }
        }
    }

    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String str2 = new String();
        str2 = String.format("%sbe988a6134bc8254465424e5a70ef037", new Object[]{str});
        return String.format("%s&key=%s", new Object[]{str, c.a(str2)});
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(java.lang.String r8, java.util.Map<java.lang.String, java.lang.String> r9, java.io.File r10, java.lang.String r11) {
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.channel.commonutils.network.d.a(java.lang.String, java.util.Map, java.io.File, java.lang.String):java.lang.String");
        /*
        r3 = 0;
        r0 = r10.exists();
        if (r0 != 0) goto L_0x0009;
    L_0x0007:
        r0 = r3;
    L_0x0008:
        return r0;
    L_0x0009:
        r4 = r10.getName();
        r0 = new java.net.URL;	 Catch:{ IOException -> 0x006c, Throwable -> 0x014d, all -> 0x0160 }
        r0.<init>(r8);	 Catch:{ IOException -> 0x006c, Throwable -> 0x014d, all -> 0x0160 }
        r0 = r0.openConnection();	 Catch:{ IOException -> 0x006c, Throwable -> 0x014d, all -> 0x0160 }
        r0 = (java.net.HttpURLConnection) r0;	 Catch:{ IOException -> 0x006c, Throwable -> 0x014d, all -> 0x0160 }
        r1 = 15000; // 0x3a98 float:2.102E-41 double:7.411E-320;
        r0.setReadTimeout(r1);	 Catch:{ IOException -> 0x006c, Throwable -> 0x014d, all -> 0x0160 }
        r1 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        r0.setConnectTimeout(r1);	 Catch:{ IOException -> 0x006c, Throwable -> 0x014d, all -> 0x0160 }
        r1 = 1;
        r0.setDoInput(r1);	 Catch:{ IOException -> 0x006c, Throwable -> 0x014d, all -> 0x0160 }
        r1 = 1;
        r0.setDoOutput(r1);	 Catch:{ IOException -> 0x006c, Throwable -> 0x014d, all -> 0x0160 }
        r1 = 0;
        r0.setUseCaches(r1);	 Catch:{ IOException -> 0x006c, Throwable -> 0x014d, all -> 0x0160 }
        r1 = "POST";
        r0.setRequestMethod(r1);	 Catch:{ IOException -> 0x006c, Throwable -> 0x014d, all -> 0x0160 }
        r1 = "Connection";
        r2 = "Keep-Alive";
        r0.setRequestProperty(r1, r2);	 Catch:{ IOException -> 0x006c, Throwable -> 0x014d, all -> 0x0160 }
        r1 = "Content-Type";
        r2 = "multipart/form-data;boundary=*****";
        r0.setRequestProperty(r1, r2);	 Catch:{ IOException -> 0x006c, Throwable -> 0x014d, all -> 0x0160 }
        if (r9 == 0) goto L_0x0083;
    L_0x0048:
        r1 = r9.entrySet();	 Catch:{ IOException -> 0x006c, Throwable -> 0x014d, all -> 0x0160 }
        r5 = r1.iterator();	 Catch:{ IOException -> 0x006c, Throwable -> 0x014d, all -> 0x0160 }
    L_0x0050:
        r1 = r5.hasNext();	 Catch:{ IOException -> 0x006c, Throwable -> 0x014d, all -> 0x0160 }
        if (r1 == 0) goto L_0x0083;
    L_0x0056:
        r1 = r5.next();	 Catch:{ IOException -> 0x006c, Throwable -> 0x014d, all -> 0x0160 }
        r1 = (java.util.Map.Entry) r1;	 Catch:{ IOException -> 0x006c, Throwable -> 0x014d, all -> 0x0160 }
        r2 = r1.getKey();	 Catch:{ IOException -> 0x006c, Throwable -> 0x014d, all -> 0x0160 }
        r2 = (java.lang.String) r2;	 Catch:{ IOException -> 0x006c, Throwable -> 0x014d, all -> 0x0160 }
        r1 = r1.getValue();	 Catch:{ IOException -> 0x006c, Throwable -> 0x014d, all -> 0x0160 }
        r1 = (java.lang.String) r1;	 Catch:{ IOException -> 0x006c, Throwable -> 0x014d, all -> 0x0160 }
        r0.setRequestProperty(r2, r1);	 Catch:{ IOException -> 0x006c, Throwable -> 0x014d, all -> 0x0160 }
        goto L_0x0050;
    L_0x006c:
        r0 = move-exception;
        r1 = r3;
        r2 = r3;
    L_0x006f:
        throw r0;	 Catch:{ all -> 0x0070 }
    L_0x0070:
        r0 = move-exception;
        r4 = r2;
        r2 = r1;
    L_0x0073:
        if (r4 == 0) goto L_0x0078;
    L_0x0075:
        r4.close();	 Catch:{ IOException -> 0x015d }
    L_0x0078:
        if (r2 == 0) goto L_0x007d;
    L_0x007a:
        r2.close();	 Catch:{ IOException -> 0x015d }
    L_0x007d:
        if (r3 == 0) goto L_0x0082;
    L_0x007f:
        r3.close();	 Catch:{ IOException -> 0x015d }
    L_0x0082:
        throw r0;
    L_0x0083:
        r1 = r4.length();	 Catch:{ IOException -> 0x006c, Throwable -> 0x014d, all -> 0x0160 }
        r1 = r1 + 77;
        r4 = r10.length();	 Catch:{ IOException -> 0x006c, Throwable -> 0x014d, all -> 0x0160 }
        r2 = (int) r4;	 Catch:{ IOException -> 0x006c, Throwable -> 0x014d, all -> 0x0160 }
        r1 = r1 + r2;
        r2 = r11.length();	 Catch:{ IOException -> 0x006c, Throwable -> 0x014d, all -> 0x0160 }
        r1 = r1 + r2;
        r0.setFixedLengthStreamingMode(r1);	 Catch:{ IOException -> 0x006c, Throwable -> 0x014d, all -> 0x0160 }
        r2 = new java.io.DataOutputStream;	 Catch:{ IOException -> 0x006c, Throwable -> 0x014d, all -> 0x0160 }
        r1 = r0.getOutputStream();	 Catch:{ IOException -> 0x006c, Throwable -> 0x014d, all -> 0x0160 }
        r2.<init>(r1);	 Catch:{ IOException -> 0x006c, Throwable -> 0x014d, all -> 0x0160 }
        r1 = "--*****\r\n";
        r2.writeBytes(r1);	 Catch:{ IOException -> 0x0175, Throwable -> 0x016d, all -> 0x0165 }
        r1 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x0175, Throwable -> 0x016d, all -> 0x0165 }
        r4 = "Content-Disposition: form-data; name=\"";
        r1.<init>(r4);	 Catch:{ IOException -> 0x0175, Throwable -> 0x016d, all -> 0x0165 }
        r1 = r1.append(r11);	 Catch:{ IOException -> 0x0175, Throwable -> 0x016d, all -> 0x0165 }
        r4 = "\";filename=\"";
        r1 = r1.append(r4);	 Catch:{ IOException -> 0x0175, Throwable -> 0x016d, all -> 0x0165 }
        r4 = r10.getName();	 Catch:{ IOException -> 0x0175, Throwable -> 0x016d, all -> 0x0165 }
        r1 = r1.append(r4);	 Catch:{ IOException -> 0x0175, Throwable -> 0x016d, all -> 0x0165 }
        r4 = "\"\r\n";
        r1 = r1.append(r4);	 Catch:{ IOException -> 0x0175, Throwable -> 0x016d, all -> 0x0165 }
        r1 = r1.toString();	 Catch:{ IOException -> 0x0175, Throwable -> 0x016d, all -> 0x0165 }
        r2.writeBytes(r1);	 Catch:{ IOException -> 0x0175, Throwable -> 0x016d, all -> 0x0165 }
        r1 = "\r\n";
        r2.writeBytes(r1);	 Catch:{ IOException -> 0x0175, Throwable -> 0x016d, all -> 0x0165 }
        r4 = new java.io.FileInputStream;	 Catch:{ IOException -> 0x0175, Throwable -> 0x016d, all -> 0x0165 }
        r4.<init>(r10);	 Catch:{ IOException -> 0x0175, Throwable -> 0x016d, all -> 0x0165 }
        r1 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r1 = new byte[r1];	 Catch:{ IOException -> 0x00ed, Throwable -> 0x0170 }
    L_0x00de:
        r5 = r4.read(r1);	 Catch:{ IOException -> 0x00ed, Throwable -> 0x0170 }
        r6 = -1;
        if (r5 == r6) goto L_0x00f2;
    L_0x00e5:
        r6 = 0;
        r2.write(r1, r6, r5);	 Catch:{ IOException -> 0x00ed, Throwable -> 0x0170 }
        r2.flush();	 Catch:{ IOException -> 0x00ed, Throwable -> 0x0170 }
        goto L_0x00de;
    L_0x00ed:
        r0 = move-exception;
        r1 = r2;
        r2 = r4;
        goto L_0x006f;
    L_0x00f2:
        r1 = "\r\n";
        r2.writeBytes(r1);	 Catch:{ IOException -> 0x00ed, Throwable -> 0x0170 }
        r1 = "--";
        r2.writeBytes(r1);	 Catch:{ IOException -> 0x00ed, Throwable -> 0x0170 }
        r1 = "*****";
        r2.writeBytes(r1);	 Catch:{ IOException -> 0x00ed, Throwable -> 0x0170 }
        r1 = "--";
        r2.writeBytes(r1);	 Catch:{ IOException -> 0x00ed, Throwable -> 0x0170 }
        r1 = "\r\n";
        r2.writeBytes(r1);	 Catch:{ IOException -> 0x00ed, Throwable -> 0x0170 }
        r2.flush();	 Catch:{ IOException -> 0x00ed, Throwable -> 0x0170 }
        r5 = new java.lang.StringBuffer;	 Catch:{ IOException -> 0x00ed, Throwable -> 0x0170 }
        r5.<init>();	 Catch:{ IOException -> 0x00ed, Throwable -> 0x0170 }
        r1 = new java.io.BufferedReader;	 Catch:{ IOException -> 0x00ed, Throwable -> 0x0170 }
        r6 = new java.io.InputStreamReader;	 Catch:{ IOException -> 0x00ed, Throwable -> 0x0170 }
        r7 = new com.xiaomi.channel.commonutils.network.d$a;	 Catch:{ IOException -> 0x00ed, Throwable -> 0x0170 }
        r0 = r0.getInputStream();	 Catch:{ IOException -> 0x00ed, Throwable -> 0x0170 }
        r7.<init>(r0);	 Catch:{ IOException -> 0x00ed, Throwable -> 0x0170 }
        r6.<init>(r7);	 Catch:{ IOException -> 0x00ed, Throwable -> 0x0170 }
        r1.<init>(r6);	 Catch:{ IOException -> 0x00ed, Throwable -> 0x0170 }
    L_0x012b:
        r0 = r1.readLine();	 Catch:{ IOException -> 0x0135, Throwable -> 0x0172, all -> 0x0169 }
        if (r0 == 0) goto L_0x013b;
    L_0x0131:
        r5.append(r0);	 Catch:{ IOException -> 0x0135, Throwable -> 0x0172, all -> 0x0169 }
        goto L_0x012b;
    L_0x0135:
        r0 = move-exception;
        r3 = r1;
        r1 = r2;
        r2 = r4;
        goto L_0x006f;
    L_0x013b:
        r0 = r5.toString();	 Catch:{ IOException -> 0x0135, Throwable -> 0x0172, all -> 0x0169 }
        r4.close();	 Catch:{ IOException -> 0x014a }
        r2.close();	 Catch:{ IOException -> 0x014a }
        r1.close();	 Catch:{ IOException -> 0x014a }
        goto L_0x0008;
    L_0x014a:
        r1 = move-exception;
        goto L_0x0008;
    L_0x014d:
        r0 = move-exception;
        r2 = r3;
        r4 = r3;
    L_0x0150:
        r1 = new java.io.IOException;	 Catch:{ all -> 0x015a }
        r0 = r0.getMessage();	 Catch:{ all -> 0x015a }
        r1.<init>(r0);	 Catch:{ all -> 0x015a }
        throw r1;	 Catch:{ all -> 0x015a }
    L_0x015a:
        r0 = move-exception;
        goto L_0x0073;
    L_0x015d:
        r1 = move-exception;
        goto L_0x0082;
    L_0x0160:
        r0 = move-exception;
        r2 = r3;
        r4 = r3;
        goto L_0x0073;
    L_0x0165:
        r0 = move-exception;
        r4 = r3;
        goto L_0x0073;
    L_0x0169:
        r0 = move-exception;
        r3 = r1;
        goto L_0x0073;
    L_0x016d:
        r0 = move-exception;
        r4 = r3;
        goto L_0x0150;
    L_0x0170:
        r0 = move-exception;
        goto L_0x0150;
    L_0x0172:
        r0 = move-exception;
        r3 = r1;
        goto L_0x0150;
    L_0x0175:
        r0 = move-exception;
        r1 = r2;
        r2 = r3;
        goto L_0x006f;
        */
    }

    public static String a(URL url) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(url.getProtocol()).append("://10.0.0.172").append(url.getPath());
        if (!TextUtils.isEmpty(url.getQuery())) {
            stringBuilder.append("?").append(url.getQuery());
        }
        return stringBuilder.toString();
    }

    public static String a(Map<String, String> map) {
        if (map == null || map.size() <= 0) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (Entry entry : map.entrySet()) {
            if (entry.getKey() != null && entry.getValue() != null) {
                try {
                    stringBuffer.append(URLEncoder.encode((String) entry.getKey(), GameManager.DEFAULT_CHARSET));
                    stringBuffer.append("=");
                    stringBuffer.append(URLEncoder.encode((String) entry.getValue(), GameManager.DEFAULT_CHARSET));
                    stringBuffer.append(a.b);
                } catch (UnsupportedEncodingException e) {
                    new StringBuilder("Failed to convert from params map to string: ").append(e.toString());
                    new StringBuilder("map: ").append(map.toString());
                    return null;
                }
            }
        }
        return (stringBuffer.length() > 0 ? stringBuffer.deleteCharAt(stringBuffer.length() - 1) : stringBuffer).toString();
    }

    public static HttpURLConnection b(Context context, URL url) {
        if (!HttpConstant.HTTP.equals(url.getProtocol())) {
            return (HttpURLConnection) url.openConnection();
        }
        if (c(context)) {
            return (HttpURLConnection) url.openConnection(new Proxy(Type.HTTP, new InetSocketAddress("10.0.0.200", 80)));
        }
        if (!b(context)) {
            return (HttpURLConnection) url.openConnection();
        }
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(a(url)).openConnection();
        httpURLConnection.addRequestProperty("X-Online-Host", url.getHost());
        return httpURLConnection;
    }

    private static URL b(String str) {
        return new URL(str);
    }

    public static boolean b(Context context) {
        if (!"CN".equalsIgnoreCase(((TelephonyManager) context.getSystemService("phone")).getSimCountryIso())) {
            return false;
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return false;
            }
            try {
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                if (activeNetworkInfo == null) {
                    return false;
                }
                String extraInfo = activeNetworkInfo.getExtraInfo();
                return (TextUtils.isEmpty(extraInfo) || extraInfo.length() < 3 || extraInfo.contains("ctwap")) ? false : extraInfo.regionMatches(true, extraInfo.length() - 3, "wap", 0, XZBDevice.DOWNLOAD_LIST_FAILED);
            } catch (Exception e) {
                return false;
            }
        } catch (Exception e2) {
            return false;
        }
    }

    public static boolean c(Context context) {
        if (!"CN".equalsIgnoreCase(((TelephonyManager) context.getSystemService("phone")).getSimCountryIso())) {
            return false;
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return false;
            }
            try {
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                if (activeNetworkInfo == null) {
                    return false;
                }
                String extraInfo = activeNetworkInfo.getExtraInfo();
                return (TextUtils.isEmpty(extraInfo) || extraInfo.length() < 3) ? false : extraInfo.contains("ctwap");
            } catch (Exception e) {
                return false;
            }
        } catch (Exception e2) {
            return false;
        }
    }

    public static boolean d(Context context) {
        return a(context) >= 0;
    }

    public static boolean e(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return false;
            }
            try {
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                return activeNetworkInfo == null ? false : 1 == activeNetworkInfo.getType();
            } catch (Exception e) {
                return false;
            }
        } catch (Exception e2) {
            return false;
        }
    }

    public static String f(Context context) {
        if (e(context)) {
            return UtilityImpl.NET_TYPE_WIFI;
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return com.umeng.a.d;
            }
            try {
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                return activeNetworkInfo == null ? com.umeng.a.d : (activeNetworkInfo.getTypeName() + SocializeConstants.OP_DIVIDER_MINUS + activeNetworkInfo.getSubtypeName() + SocializeConstants.OP_DIVIDER_MINUS + activeNetworkInfo.getExtraInfo()).toLowerCase();
            } catch (Exception e) {
                return com.umeng.a.d;
            }
        } catch (Exception e2) {
            return com.umeng.a.d;
        }
    }
}
