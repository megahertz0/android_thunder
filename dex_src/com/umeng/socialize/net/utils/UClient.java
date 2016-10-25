package com.umeng.socialize.net.utils;

import android.net.Uri.Builder;
import android.text.TextUtils;
import com.umeng.message.util.HttpRequest;
import com.umeng.socialize.Config;
import com.umeng.socialize.utils.Log;
import com.xunlei.common.encrypt.CharsetConvert;
import com.xunlei.tdlive.im.ChatMessage;
import com.xunlei.xiazaibao.BuildConfig;
import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.zip.GZIPInputStream;
import java.util.zip.InflaterInputStream;
import org.android.spdy.SpdyRequest;
import org.json.JSONObject;

public class UClient {
    private static final String END = "\r\n";
    private static final String TAG = "UClient";
    private Map<String, String> mHeaders;
    private StringBuilder mRequestInfo;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T extends com.umeng.socialize.net.utils.UResponse> T execute(com.umeng.socialize.net.utils.URequest r6, java.lang.Class<T> r7) {
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.socialize.net.utils.UClient.execute(com.umeng.socialize.net.utils.URequest, java.lang.Class):T");
        /*
        this = this;
        r1 = 0;
        r6.onPrepareRequest();
        r0 = r6.getHttpMethod();
        r0 = r0.trim();
        r5.verifyMethod(r0);
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r5.mRequestInfo = r2;
        r2 = com.umeng.socialize.net.utils.URequest.GET;
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x0026;
    L_0x001e:
        r0 = r5.httpGetRequest(r6);
    L_0x0022:
        if (r0 != 0) goto L_0x0035;
    L_0x0024:
        r0 = r1;
    L_0x0025:
        return r0;
    L_0x0026:
        r2 = com.umeng.socialize.net.utils.URequest.POST;
        r0 = r2.equals(r0);
        if (r0 == 0) goto L_0x0091;
    L_0x002e:
        r0 = r6.mBaseUrl;
        r0 = r5.httpPostRequest(r0, r6);
        goto L_0x0022;
    L_0x0035:
        r2 = 1;
        r2 = new java.lang.Class[r2];	 Catch:{ SecurityException -> 0x004e, NoSuchMethodException -> 0x005a, IllegalArgumentException -> 0x0065, InstantiationException -> 0x0070, IllegalAccessException -> 0x007b, InvocationTargetException -> 0x0086 }
        r3 = 0;
        r4 = org.json.JSONObject.class;
        r2[r3] = r4;	 Catch:{ SecurityException -> 0x004e, NoSuchMethodException -> 0x005a, IllegalArgumentException -> 0x0065, InstantiationException -> 0x0070, IllegalAccessException -> 0x007b, InvocationTargetException -> 0x0086 }
        r2 = r7.getConstructor(r2);	 Catch:{ SecurityException -> 0x004e, NoSuchMethodException -> 0x005a, IllegalArgumentException -> 0x0065, InstantiationException -> 0x0070, IllegalAccessException -> 0x007b, InvocationTargetException -> 0x0086 }
        r3 = 1;
        r3 = new java.lang.Object[r3];	 Catch:{ SecurityException -> 0x004e, NoSuchMethodException -> 0x005a, IllegalArgumentException -> 0x0065, InstantiationException -> 0x0070, IllegalAccessException -> 0x007b, InvocationTargetException -> 0x0086 }
        r4 = 0;
        r3[r4] = r0;	 Catch:{ SecurityException -> 0x004e, NoSuchMethodException -> 0x005a, IllegalArgumentException -> 0x0065, InstantiationException -> 0x0070, IllegalAccessException -> 0x007b, InvocationTargetException -> 0x0086 }
        r0 = r2.newInstance(r3);	 Catch:{ SecurityException -> 0x004e, NoSuchMethodException -> 0x005a, IllegalArgumentException -> 0x0065, InstantiationException -> 0x0070, IllegalAccessException -> 0x007b, InvocationTargetException -> 0x0086 }
        r0 = (com.umeng.socialize.net.utils.UResponse) r0;	 Catch:{ SecurityException -> 0x004e, NoSuchMethodException -> 0x005a, IllegalArgumentException -> 0x0065, InstantiationException -> 0x0070, IllegalAccessException -> 0x007b, InvocationTargetException -> 0x0086 }
        goto L_0x0025;
    L_0x004e:
        r0 = move-exception;
        r2 = "UClient";
        r3 = "SecurityException";
        com.umeng.socialize.utils.Log.e(r2, r3, r0);
    L_0x0058:
        r0 = r1;
        goto L_0x0025;
    L_0x005a:
        r0 = move-exception;
        r2 = "UClient";
        r3 = "NoSuchMethodException";
        com.umeng.socialize.utils.Log.e(r2, r3, r0);
        goto L_0x0058;
    L_0x0065:
        r0 = move-exception;
        r2 = "UClient";
        r3 = "IllegalArgumentException";
        com.umeng.socialize.utils.Log.e(r2, r3, r0);
        goto L_0x0058;
    L_0x0070:
        r0 = move-exception;
        r2 = "UClient";
        r3 = "InstantiationException";
        com.umeng.socialize.utils.Log.e(r2, r3, r0);
        goto L_0x0058;
    L_0x007b:
        r0 = move-exception;
        r2 = "UClient";
        r3 = "IllegalAccessException";
        com.umeng.socialize.utils.Log.e(r2, r3, r0);
        goto L_0x0058;
    L_0x0086:
        r0 = move-exception;
        r2 = "UClient";
        r3 = "InvocationTargetException";
        com.umeng.socialize.utils.Log.e(r2, r3, r0);
        goto L_0x0058;
    L_0x0091:
        r0 = r1;
        goto L_0x0022;
        */
    }

    private JSONObject httpPostRequest(String str, URequest uRequest) {
        String str2;
        if (uRequest.toJson() == null) {
            str2 = BuildConfig.VERSION_NAME;
        } else {
            str2 = uRequest.toJson().toString();
        }
        int nextInt = new Random().nextInt(ChatMessage.FLAG_SYS_NOTIFY);
        Log.i(TAG, nextInt + ":\trequest: " + str + System.getProperty("line.separator") + str2);
        String toString = UUID.randomUUID().toString();
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            try {
                httpURLConnection.setConnectTimeout(Config.connectionTimeOut);
                httpURLConnection.setReadTimeout(Config.readSocketTimeOut);
                httpURLConnection.setRequestMethod(SpdyRequest.POST_METHOD);
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setRequestProperty(HttpRequest.l, new StringBuilder("multipart/form-data; boundary=").append(toString).toString());
                Map bodyPair = uRequest.getBodyPair();
                JSONObject parseResult;
                if (bodyPair == null || bodyPair.size() <= 0) {
                    httpURLConnection.setRequestProperty(HttpRequest.l, HttpRequest.b);
                    Builder builder = new Builder();
                    builder.appendQueryParameter("content", str2);
                    String encodedQuery = builder.build().getEncodedQuery();
                    OutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                    dataOutputStream.write(encodedQuery.getBytes());
                    dataOutputStream.flush();
                    dataOutputStream.close();
                    parseResult = parseResult(httpURLConnection);
                    if (httpURLConnection == null) {
                        return parseResult;
                    }
                    httpURLConnection.disconnect();
                    return parseResult;
                }
                OutputStream dataOutputStream2;
                StringBuilder stringBuilder = new StringBuilder();
                for (String encodedQuery2 : bodyPair.keySet()) {
                    if (bodyPair.get(encodedQuery2) != null) {
                        addFormField(stringBuilder, encodedQuery2, bodyPair.get(encodedQuery2).toString(), toString);
                    }
                }
                if (stringBuilder.length() > 0) {
                    dataOutputStream2 = new DataOutputStream(httpURLConnection.getOutputStream());
                    dataOutputStream2.write(stringBuilder.toString().getBytes());
                    int i = 1;
                } else {
                    Object obj = null;
                    dataOutputStream2 = null;
                }
                Map filePair = uRequest.getFilePair();
                if (filePair != null && filePair.size() > 0) {
                    stringBuilder = new StringBuilder();
                    for (String str3 : filePair.keySet()) {
                        byte[] bArr = ((URequest$FilePair) filePair.get(str3)).mBinaryData;
                        if (bArr != null && bArr.length > 0) {
                            addFilePart(stringBuilder, str3, bArr, toString, dataOutputStream2);
                        }
                    }
                }
                if (obj != null) {
                    finishWrite(dataOutputStream2, toString);
                }
                parseResult = parseResult(httpURLConnection);
                if (httpURLConnection == null) {
                    return parseResult;
                }
                httpURLConnection.disconnect();
                return parseResult;
            } catch (IOException e) {
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                return null;
            } catch (Throwable th) {
                Throwable th2 = th;
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                throw th2;
            }
        } catch (IOException e2) {
            httpURLConnection = null;
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            return null;
        } catch (Throwable th3) {
            th2 = th3;
            httpURLConnection = null;
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            throw th2;
        }
    }

    private JSONObject parseResult(HttpURLConnection httpURLConnection) throws IOException {
        if (httpURLConnection.getResponseCode() != 200) {
            return null;
        }
        String convertStreamToString = convertStreamToString(new BufferedInputStream(httpURLConnection.getInputStream()));
        try {
            return new JSONObject(convertStreamToString);
        } catch (Exception e) {
            try {
                return new JSONObject(AesHelper.decryptNoPadding(convertStreamToString, CharsetConvert.UTF_8).trim());
            } catch (Exception e2) {
                return null;
            }
        }
    }

    private JSONObject httpGetRequest(URequest uRequest) {
        Exception exception;
        HttpURLConnection httpURLConnection = null;
        int nextInt = new Random().nextInt(ChatMessage.FLAG_SYS_NOTIFY);
        try {
            if (uRequest.toGetUrl().length() <= 1) {
                Log.e(TAG, nextInt + ":\tInvalid baseUrl.");
                return null;
            }
            HttpURLConnection httpURLConnection2 = (HttpURLConnection) new URL(uRequest.toGetUrl()).openConnection();
            try {
                String trim;
                httpURLConnection2.setRequestProperty(HttpRequest.g, HttpRequest.d);
                httpURLConnection2.setConnectTimeout(Config.connectionTimeOut);
                httpURLConnection2.setReadTimeout(Config.readSocketTimeOut);
                if (this.mHeaders != null && this.mHeaders.size() > 0) {
                    for (String trim2 : this.mHeaders.keySet()) {
                        httpURLConnection2.setRequestProperty(trim2, (String) this.mHeaders.get(trim2));
                    }
                }
                httpURLConnection2.connect();
                if (httpURLConnection2.getResponseCode() == 200) {
                    InputStream gZIPInputStream;
                    String contentEncoding = httpURLConnection2.getContentEncoding();
                    if (contentEncoding.equalsIgnoreCase(HttpRequest.d)) {
                        gZIPInputStream = new GZIPInputStream(httpURLConnection2.getInputStream());
                    } else if (contentEncoding.equalsIgnoreCase("deflate")) {
                        gZIPInputStream = new InflaterInputStream(httpURLConnection2.getInputStream());
                    } else {
                        gZIPInputStream = null;
                    }
                    trim2 = AesHelper.decryptNoPadding(convertStreamToString(gZIPInputStream), CharsetConvert.UTF_8).trim();
                    if (trim2 == null) {
                        if (httpURLConnection2 != null) {
                            httpURLConnection2.disconnect();
                        }
                        return null;
                    }
                    JSONObject jSONObject = new JSONObject(trim2);
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    return jSONObject;
                }
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
                return null;
            } catch (Exception e) {
                Exception exception2 = e;
                r2 = httpURLConnection2;
                exception = exception2;
                try {
                    Log.e("UMhttprequest", new StringBuilder("error:").append(exception.getMessage()).toString());
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
                httpURLConnection = httpURLConnection2;
                th2 = th3;
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                throw th2;
            }
        } catch (Exception e2) {
            exception = e2;
            r2 = null;
            Log.e("UMhttprequest", new StringBuilder("error:").append(exception.getMessage()).toString());
            HttpURLConnection httpURLConnection3;
            if (httpURLConnection3 != null) {
                httpURLConnection3.disconnect();
            }
            return null;
        } catch (Throwable th4) {
            th2 = th4;
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            throw th2;
        }
    }

    public UClient setHeader(Map<String, String> map) {
        this.mHeaders = map;
        return this;
    }

    private void verifyMethod(String str) {
        if (TextUtils.isEmpty(str) || (URequest.GET.equals(str.trim()) ^ URequest.POST.equals(str.trim())) == 0) {
            throw new RuntimeException(new StringBuilder("\u9a8c\u8bc1\u8bf7\u6c42\u65b9\u5f0f\u5931\u8d25[").append(str).append("]").toString());
        }
    }

    private void outprint(String str) {
        this.mRequestInfo.append(str);
    }

    private static java.lang.String convertStreamToString(java.io.InputStream r5) {
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.socialize.net.utils.UClient.convertStreamToString(java.io.InputStream):java.lang.String");
        /* JADX: method processing error */
/*
        Error: jadx.core.utils.exceptions.JadxRuntimeException: Try/catch wrap count limit reached in com.umeng.socialize.net.utils.UClient.convertStreamToString(java.io.InputStream):java.lang.String
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
        r2 = "UClient";
        r3 = "Caught IOException in convertStreamToString()";
        com.umeng.socialize.utils.Log.e(r2, r3, r1);	 Catch:{ all -> 0x005c }
        r5.close();	 Catch:{ IOException -> 0x0051 }
    L_0x003d:
        return r0;
    L_0x003e:
        r5.close();	 Catch:{ IOException -> 0x0046 }
        r0 = r2.toString();
        goto L_0x003d;
    L_0x0046:
        r1 = move-exception;
        r2 = "UClient";
        r3 = "Caught IOException in convertStreamToString()";
        com.umeng.socialize.utils.Log.e(r2, r3, r1);
        goto L_0x003d;
    L_0x0051:
        r1 = move-exception;
        r2 = "UClient";
        r3 = "Caught IOException in convertStreamToString()";
        com.umeng.socialize.utils.Log.e(r2, r3, r1);
        goto L_0x003d;
    L_0x005c:
        r1 = move-exception;
        r5.close();	 Catch:{ IOException -> 0x0061 }
        throw r1;
    L_0x0061:
        r1 = move-exception;
        r2 = "UClient";
        r3 = "Caught IOException in convertStreamToString()";
        com.umeng.socialize.utils.Log.e(r2, r3, r1);
        goto L_0x003d;
        */
    }

    private void addFormField(StringBuilder stringBuilder, String str, String str2, String str3) {
        stringBuilder.append("--").append(str3).append("\r\nContent-Disposition: form-data; name=\"").append(str).append("\"\r\nContent-Type: text/plain; charset=UTF-8\r\n\r\n").append(str2).append(END);
    }

    private void addFilePart(StringBuilder stringBuilder, String str, byte[] bArr, String str2, OutputStream outputStream) throws IOException {
        stringBuilder.append("--").append(str2).append("\r\nContent-Disposition: form-data; name=\"").append(str).append("\"; filename=\"").append(str).append("\"\r\nContent-Type: application/octet-stream\r\nContent-Transfer-Encoding: binary\r\n\r\n");
        outputStream.write(stringBuilder.toString().getBytes());
        outputStream.write(bArr);
        outputStream.write(END.getBytes());
    }

    private void finishWrite(OutputStream outputStream, String str) throws IOException {
        outputStream.write(END.getBytes());
        outputStream.write(new StringBuilder("--").append(str).append("--").toString().getBytes());
        outputStream.write(END.getBytes());
        outputStream.flush();
        outputStream.close();
    }
}
