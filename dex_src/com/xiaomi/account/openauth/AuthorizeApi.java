package com.xiaomi.account.openauth;

import android.content.Context;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

public class AuthorizeApi {
    private static final int CONNECTION_TIMEOUT = 10000;
    private static final String HOST = "open.account.xiaomi.com";
    private static final String METHOD_GET = "GET";
    private static final String OAUTH_URL_BASE = "https://open.account.xiaomi.com";
    private static final int READ_TIMEOUT = 15000;
    private static final String UTF8 = "UTF-8";

    @Deprecated
    public static String doHttpGet(Context context, String str, long j, String str2) throws XMAuthericationException {
        return doHttpGet(str, j, str2, null, null);
    }

    public static String doHttpGet(String str, long j, String str2) throws XMAuthericationException {
        return doHttpGet(str, j, str2, null, null);
    }

    @Deprecated
    public static String doHttpGet(Context context, String str, long j, String str2, String str3, String str4) throws XMAuthericationException {
        return doHttpGet(str, j, str2, str3, str4);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String doHttpGet(java.lang.String r7, long r8, java.lang.String r10, java.lang.String r11, java.lang.String r12) throws com.xiaomi.account.openauth.XMAuthericationException {
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.account.openauth.AuthorizeApi.doHttpGet(java.lang.String, long, java.lang.String, java.lang.String, java.lang.String):java.lang.String");
        /*
        r1 = new java.util.ArrayList;
        r1.<init>();
        r0 = new org.apache.http.message.BasicNameValuePair;
        r2 = "clientId";
        r3 = java.lang.String.valueOf(r8);
        r0.<init>(r2, r3);
        r1.add(r0);
        r0 = new org.apache.http.message.BasicNameValuePair;
        r2 = "token";
        r0.<init>(r2, r10);
        r1.add(r0);
        r3 = 0;
        r0 = new java.net.URL;	 Catch:{ UnsupportedEncodingException -> 0x007b, IOException -> 0x009d, NoSuchAlgorithmException -> 0x00d1, InvalidKeyException -> 0x00d8 }
        r2 = new java.lang.StringBuilder;	 Catch:{ UnsupportedEncodingException -> 0x007b, IOException -> 0x009d, NoSuchAlgorithmException -> 0x00d1, InvalidKeyException -> 0x00d8 }
        r4 = "https://open.account.xiaomi.com";
        r2.<init>(r4);	 Catch:{ UnsupportedEncodingException -> 0x007b, IOException -> 0x009d, NoSuchAlgorithmException -> 0x00d1, InvalidKeyException -> 0x00d8 }
        r2 = r2.append(r7);	 Catch:{ UnsupportedEncodingException -> 0x007b, IOException -> 0x009d, NoSuchAlgorithmException -> 0x00d1, InvalidKeyException -> 0x00d8 }
        r2 = r2.toString();	 Catch:{ UnsupportedEncodingException -> 0x007b, IOException -> 0x009d, NoSuchAlgorithmException -> 0x00d1, InvalidKeyException -> 0x00d8 }
        r2 = com.xiaomi.account.openauth.AuthorizeHelper.generateUrl(r2, r1);	 Catch:{ UnsupportedEncodingException -> 0x007b, IOException -> 0x009d, NoSuchAlgorithmException -> 0x00d1, InvalidKeyException -> 0x00d8 }
        r0.<init>(r2);	 Catch:{ UnsupportedEncodingException -> 0x007b, IOException -> 0x009d, NoSuchAlgorithmException -> 0x00d1, InvalidKeyException -> 0x00d8 }
        r2 = 1;
        java.net.HttpURLConnection.setFollowRedirects(r2);	 Catch:{ UnsupportedEncodingException -> 0x007b, IOException -> 0x009d, NoSuchAlgorithmException -> 0x00d1, InvalidKeyException -> 0x00d8 }
        r0 = r0.openConnection();	 Catch:{ UnsupportedEncodingException -> 0x007b, IOException -> 0x009d, NoSuchAlgorithmException -> 0x00d1, InvalidKeyException -> 0x00d8 }
        r0 = (java.net.HttpURLConnection) r0;	 Catch:{ UnsupportedEncodingException -> 0x007b, IOException -> 0x009d, NoSuchAlgorithmException -> 0x00d1, InvalidKeyException -> 0x00d8 }
        r2 = 1;
        r0.setDoInput(r2);	 Catch:{ UnsupportedEncodingException -> 0x007b, IOException -> 0x009d, NoSuchAlgorithmException -> 0x00d1, InvalidKeyException -> 0x00d8 }
        r2 = "GET";
        r0.setRequestMethod(r2);	 Catch:{ UnsupportedEncodingException -> 0x007b, IOException -> 0x009d, NoSuchAlgorithmException -> 0x00d1, InvalidKeyException -> 0x00d8 }
        r2 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        r0.setConnectTimeout(r2);	 Catch:{ UnsupportedEncodingException -> 0x007b, IOException -> 0x009d, NoSuchAlgorithmException -> 0x00d1, InvalidKeyException -> 0x00d8 }
        r2 = 15000; // 0x3a98 float:2.102E-41 double:7.411E-320;
        r0.setReadTimeout(r2);	 Catch:{ UnsupportedEncodingException -> 0x007b, IOException -> 0x009d, NoSuchAlgorithmException -> 0x00d1, InvalidKeyException -> 0x00d8 }
        r4 = makeHeaders(r7, r10, r11, r12, r1);	 Catch:{ UnsupportedEncodingException -> 0x007b, IOException -> 0x009d, NoSuchAlgorithmException -> 0x00d1, InvalidKeyException -> 0x00d8 }
        if (r4 == 0) goto L_0x0089;
    L_0x005d:
        r1 = r4.keySet();	 Catch:{ UnsupportedEncodingException -> 0x007b, IOException -> 0x009d, NoSuchAlgorithmException -> 0x00d1, InvalidKeyException -> 0x00d8 }
        r5 = r1.iterator();	 Catch:{ UnsupportedEncodingException -> 0x007b, IOException -> 0x009d, NoSuchAlgorithmException -> 0x00d1, InvalidKeyException -> 0x00d8 }
    L_0x0065:
        r1 = r5.hasNext();	 Catch:{ UnsupportedEncodingException -> 0x007b, IOException -> 0x009d, NoSuchAlgorithmException -> 0x00d1, InvalidKeyException -> 0x00d8 }
        if (r1 == 0) goto L_0x0089;
    L_0x006b:
        r1 = r5.next();	 Catch:{ UnsupportedEncodingException -> 0x007b, IOException -> 0x009d, NoSuchAlgorithmException -> 0x00d1, InvalidKeyException -> 0x00d8 }
        r1 = (java.lang.String) r1;	 Catch:{ UnsupportedEncodingException -> 0x007b, IOException -> 0x009d, NoSuchAlgorithmException -> 0x00d1, InvalidKeyException -> 0x00d8 }
        r2 = r4.get(r1);	 Catch:{ UnsupportedEncodingException -> 0x007b, IOException -> 0x009d, NoSuchAlgorithmException -> 0x00d1, InvalidKeyException -> 0x00d8 }
        r2 = (java.lang.String) r2;	 Catch:{ UnsupportedEncodingException -> 0x007b, IOException -> 0x009d, NoSuchAlgorithmException -> 0x00d1, InvalidKeyException -> 0x00d8 }
        r0.setRequestProperty(r1, r2);	 Catch:{ UnsupportedEncodingException -> 0x007b, IOException -> 0x009d, NoSuchAlgorithmException -> 0x00d1, InvalidKeyException -> 0x00d8 }
        goto L_0x0065;
    L_0x007b:
        r0 = move-exception;
        r1 = r3;
    L_0x007d:
        r2 = new com.xiaomi.account.openauth.XMAuthericationException;	 Catch:{ all -> 0x0083 }
        r2.<init>(r0);	 Catch:{ all -> 0x0083 }
        throw r2;	 Catch:{ all -> 0x0083 }
    L_0x0083:
        r0 = move-exception;
        r3 = r1;
    L_0x0085:
        closeQuietly(r3);
        throw r0;
    L_0x0089:
        r0.connect();	 Catch:{ UnsupportedEncodingException -> 0x007b, IOException -> 0x009d, NoSuchAlgorithmException -> 0x00d1, InvalidKeyException -> 0x00d8 }
        r0 = r0.getInputStream();	 Catch:{ IOException -> 0x00a6, UnsupportedEncodingException -> 0x007b, NoSuchAlgorithmException -> 0x00d1, InvalidKeyException -> 0x00d8 }
    L_0x0090:
        if (r0 != 0) goto L_0x00ac;
    L_0x0092:
        r0 = new com.xiaomi.account.openauth.XMAuthericationException;	 Catch:{ UnsupportedEncodingException -> 0x007b, IOException -> 0x009d, NoSuchAlgorithmException -> 0x00d1, InvalidKeyException -> 0x00d8 }
        r1 = new java.io.IOException;	 Catch:{ UnsupportedEncodingException -> 0x007b, IOException -> 0x009d, NoSuchAlgorithmException -> 0x00d1, InvalidKeyException -> 0x00d8 }
        r1.<init>();	 Catch:{ UnsupportedEncodingException -> 0x007b, IOException -> 0x009d, NoSuchAlgorithmException -> 0x00d1, InvalidKeyException -> 0x00d8 }
        r0.<init>(r1);	 Catch:{ UnsupportedEncodingException -> 0x007b, IOException -> 0x009d, NoSuchAlgorithmException -> 0x00d1, InvalidKeyException -> 0x00d8 }
        throw r0;	 Catch:{ UnsupportedEncodingException -> 0x007b, IOException -> 0x009d, NoSuchAlgorithmException -> 0x00d1, InvalidKeyException -> 0x00d8 }
    L_0x009d:
        r0 = move-exception;
    L_0x009e:
        r1 = new com.xiaomi.account.openauth.XMAuthericationException;	 Catch:{ all -> 0x00a4 }
        r1.<init>(r0);	 Catch:{ all -> 0x00a4 }
        throw r1;	 Catch:{ all -> 0x00a4 }
    L_0x00a4:
        r0 = move-exception;
        goto L_0x0085;
    L_0x00a6:
        r1 = move-exception;
        r0 = r0.getErrorStream();	 Catch:{ UnsupportedEncodingException -> 0x007b, IOException -> 0x009d, NoSuchAlgorithmException -> 0x00d1, InvalidKeyException -> 0x00d8 }
        goto L_0x0090;
    L_0x00ac:
        r1 = new java.io.BufferedReader;	 Catch:{ UnsupportedEncodingException -> 0x007b, IOException -> 0x009d, NoSuchAlgorithmException -> 0x00d1, InvalidKeyException -> 0x00d8 }
        r2 = new java.io.InputStreamReader;	 Catch:{ UnsupportedEncodingException -> 0x007b, IOException -> 0x009d, NoSuchAlgorithmException -> 0x00d1, InvalidKeyException -> 0x00d8 }
        r2.<init>(r0);	 Catch:{ UnsupportedEncodingException -> 0x007b, IOException -> 0x009d, NoSuchAlgorithmException -> 0x00d1, InvalidKeyException -> 0x00d8 }
        r0 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r1.<init>(r2, r0);	 Catch:{ UnsupportedEncodingException -> 0x007b, IOException -> 0x009d, NoSuchAlgorithmException -> 0x00d1, InvalidKeyException -> 0x00d8 }
        r0 = new java.lang.StringBuilder;	 Catch:{ UnsupportedEncodingException -> 0x00c7, IOException -> 0x00e5, NoSuchAlgorithmException -> 0x00e2, InvalidKeyException -> 0x00df }
        r0.<init>();	 Catch:{ UnsupportedEncodingException -> 0x00c7, IOException -> 0x00e5, NoSuchAlgorithmException -> 0x00e2, InvalidKeyException -> 0x00df }
    L_0x00bd:
        r2 = r1.readLine();	 Catch:{ UnsupportedEncodingException -> 0x00c7, IOException -> 0x00e5, NoSuchAlgorithmException -> 0x00e2, InvalidKeyException -> 0x00df }
        if (r2 == 0) goto L_0x00c9;
    L_0x00c3:
        r0.append(r2);	 Catch:{ UnsupportedEncodingException -> 0x00c7, IOException -> 0x00e5, NoSuchAlgorithmException -> 0x00e2, InvalidKeyException -> 0x00df }
        goto L_0x00bd;
    L_0x00c7:
        r0 = move-exception;
        goto L_0x007d;
    L_0x00c9:
        r0 = r0.toString();	 Catch:{ UnsupportedEncodingException -> 0x00c7, IOException -> 0x00e5, NoSuchAlgorithmException -> 0x00e2, InvalidKeyException -> 0x00df }
        closeQuietly(r1);
        return r0;
    L_0x00d1:
        r0 = move-exception;
    L_0x00d2:
        r1 = new com.xiaomi.account.openauth.XMAuthericationException;	 Catch:{ all -> 0x00a4 }
        r1.<init>(r0);	 Catch:{ all -> 0x00a4 }
        throw r1;	 Catch:{ all -> 0x00a4 }
    L_0x00d8:
        r0 = move-exception;
    L_0x00d9:
        r1 = new com.xiaomi.account.openauth.XMAuthericationException;	 Catch:{ all -> 0x00a4 }
        r1.<init>(r0);	 Catch:{ all -> 0x00a4 }
        throw r1;	 Catch:{ all -> 0x00a4 }
    L_0x00df:
        r0 = move-exception;
        r3 = r1;
        goto L_0x00d9;
    L_0x00e2:
        r0 = move-exception;
        r3 = r1;
        goto L_0x00d2;
    L_0x00e5:
        r0 = move-exception;
        r3 = r1;
        goto L_0x009e;
        */
    }

    private static void closeQuietly(BufferedReader bufferedReader) {
        if (bufferedReader != null) {
            try {
                bufferedReader.close();
            } catch (IOException e) {
            }
        }
    }

    private static HashMap<String, String> makeHeaders(String str, String str2, String str3, String str4, List<NameValuePair> list) throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {
        if (str3 == null && str4 == null) {
            return null;
        }
        String generateNonce = AuthorizeHelper.generateNonce();
        return AuthorizeHelper.buildMacRequestHead(str2, generateNonce, AuthorizeHelper.getMacAccessTokenSignatureString(generateNonce, METHOD_GET, HOST, str, URLEncodedUtils.format(list, UTF8), str3, str4));
    }
}
