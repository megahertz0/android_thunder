package com.sina.weibo.sdk.net;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.text.TextUtils;
import android.webkit.URLUtil;
import anet.channel.util.HttpConstant;
import com.alipay.sdk.cons.b;
import com.alipay.sdk.packet.d;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.component.GameManager;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.exception.WeiboHttpException;
import com.sina.weibo.sdk.utils.LogUtil;
import com.sina.weibo.sdk.utils.NetworkHelper;
import com.sina.weibo.sdk.utils.Utility;
import com.tencent.connect.common.Constants;
import com.tencent.stat.DeviceInfo;
import com.umeng.a;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.tdlive.R;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.URI;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPInputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.ProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.RedirectHandler;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HttpContext;

public class HttpManager {
    private static final String BOUNDARY;
    private static final int BUFFER_SIZE = 8192;
    private static final int CONNECTION_TIMEOUT = 25000;
    private static final String END_MP_BOUNDARY;
    private static final String HTTP_METHOD_GET = "GET";
    private static final String HTTP_METHOD_POST = "POST";
    private static final String MP_BOUNDARY;
    private static final String MULTIPART_FORM_DATA = "multipart/form-data";
    private static final int SOCKET_TIMEOUT = 20000;
    private static final String TAG = "HttpManager";
    private static SSLSocketFactory sSSLSocketFactory;

    private static native String calcOauthSignNative(Context context, String str, String str2);

    static {
        System.loadLibrary("weibosdkcore");
        BOUNDARY = getBoundry();
        MP_BOUNDARY = new StringBuilder("--").append(BOUNDARY).toString();
        END_MP_BOUNDARY = new StringBuilder("--").append(BOUNDARY).append("--").toString();
    }

    public static String openUrl(Context context, String str, String str2, WeiboParameters weiboParameters) throws WeiboException {
        String readRsponse = readRsponse(requestHttpExecute(context, str, str2, weiboParameters));
        LogUtil.d(TAG, new StringBuilder("Response : ").append(readRsponse).toString());
        return readRsponse;
    }

    private static HttpResponse requestHttpExecute(Context context, String str, String str2, WeiboParameters weiboParameters) {
        Throwable e;
        HttpClient httpClient;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            HttpClient newHttpClient = getNewHttpClient();
            try {
                HttpUriRequest httpGet;
                newHttpClient.getParams().setParameter("http.route.default-proxy", NetStateManager.getAPN());
                setHttpCommonParam(context, weiboParameters);
                HttpResponse execute = newHttpClient.execute(httpGet);
                int statusCode = execute.getStatusLine().getStatusCode();
                if (statusCode != 200) {
                    throw new WeiboHttpException(readRsponse(execute), statusCode);
                }
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (IOException e2) {
                    }
                }
                shutdownHttpClient(newHttpClient);
                return execute;
            } catch (IOException e3) {
                e = e3;
                httpClient = newHttpClient;
                try {
                    e.printStackTrace();
                    throw new WeiboException(e);
                } catch (Throwable th) {
                    e = th;
                    newHttpClient = httpClient;
                }
            } catch (Throwable th2) {
                e = th2;
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (IOException e4) {
                    }
                }
                shutdownHttpClient(newHttpClient);
                throw e;
            }
            if (str2.equals(HTTP_METHOD_GET)) {
                String toString = new StringBuilder(String.valueOf(str)).append("?").append(weiboParameters.encodeUrl()).toString();
                httpGet = new HttpGet(toString);
                LogUtil.d(TAG, new StringBuilder("requestHttpExecute GET Url : ").append(toString).toString());
            } else if (str2.equals(HTTP_METHOD_POST)) {
                LogUtil.d(TAG, new StringBuilder("requestHttpExecute POST Url : ").append(str).toString());
                HttpPost httpPost = new HttpPost(str);
                OutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                OutputStream outputStream;
                try {
                    if (weiboParameters.hasBinaryData()) {
                        httpPost.setHeader("Content-Type", new StringBuilder("multipart/form-data; boundary=").append(BOUNDARY).toString());
                        buildParams(byteArrayOutputStream2, weiboParameters);
                    } else {
                        Object obj = weiboParameters.get(d.d);
                        if (obj == null || !(obj instanceof String)) {
                            httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
                        } else {
                            weiboParameters.remove(d.d);
                            httpPost.setHeader("Content-Type", (String) obj);
                        }
                        String encodeUrl = weiboParameters.encodeUrl();
                        LogUtil.d(TAG, new StringBuilder("requestHttpExecute POST postParam : ").append(encodeUrl).toString());
                        byteArrayOutputStream2.write(encodeUrl.getBytes(GameManager.DEFAULT_CHARSET));
                    }
                    httpPost.setEntity(new ByteArrayEntity(byteArrayOutputStream2.toByteArray()));
                    HttpPost httpPost2 = httpPost;
                    outputStream = byteArrayOutputStream2;
                } catch (IOException e5) {
                    e = e5;
                    outputStream = byteArrayOutputStream2;
                    httpClient = newHttpClient;
                    e.printStackTrace();
                    throw new WeiboException(e);
                } catch (Throwable th3) {
                    e = th3;
                    outputStream = byteArrayOutputStream2;
                    if (byteArrayOutputStream != null) {
                        byteArrayOutputStream.close();
                    }
                    shutdownHttpClient(newHttpClient);
                    throw e;
                }
            } else if (str2.equals("DELETE")) {
                httpGet = new HttpDelete(str);
            } else {
                httpGet = null;
            }
        } catch (IOException e6) {
            e = e6;
            ByteArrayOutputStream byteArrayOutputStream3 = null;
            e.printStackTrace();
            throw new WeiboException(e);
        } catch (Throwable th4) {
            e = th4;
            newHttpClient = null;
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            shutdownHttpClient(newHttpClient);
            throw e;
        }
    }

    private static void setHttpCommonParam(Context context, WeiboParameters weiboParameters) {
        String aid;
        Object obj = a.d;
        if (!TextUtils.isEmpty(weiboParameters.getAppKey())) {
            aid = Utility.getAid(context, weiboParameters.getAppKey());
            if (!TextUtils.isEmpty(aid)) {
                weiboParameters.put(DeviceInfo.TAG_ANDROID_ID, aid);
            }
        }
        String str = aid;
        String timestamp = getTimestamp();
        weiboParameters.put("oauth_timestamp", timestamp);
        String str2 = a.d;
        obj = weiboParameters.get(Constants.PARAM_ACCESS_TOKEN);
        Object obj2 = weiboParameters.get(Oauth2AccessToken.KEY_REFRESH_TOKEN);
        Object obj3 = weiboParameters.get("phone");
        aid = (obj == null || !(obj instanceof String)) ? (obj2 == null || !(obj2 instanceof String)) ? (obj3 == null || !(obj3 instanceof String)) ? str2 : (String) obj3 : (String) obj2 : (String) obj;
        weiboParameters.put("oauth_sign", getOauthSign(context, str, aid, weiboParameters.getAppKey(), timestamp));
    }

    public static void shutdownHttpClient(HttpClient httpClient) {
        if (httpClient != null) {
            try {
                httpClient.getConnectionManager().closeExpiredConnections();
            } catch (Exception e) {
            }
        }
    }

    public static String openUrl4RdirectURL(Context context, String str, String str2, WeiboParameters weiboParameters) throws WeiboException {
        HttpUriRequest httpUriRequest = null;
        try {
            DefaultHttpClient defaultHttpClient = (DefaultHttpClient) getNewHttpClient();
            try {
                defaultHttpClient.setRedirectHandler(new RedirectHandler() {
                    public boolean isRedirectRequested(HttpResponse httpResponse, HttpContext httpContext) {
                        LogUtil.d(TAG, "openUrl4RdirectURL isRedirectRequested method");
                        return false;
                    }

                    public URI getLocationURI(HttpResponse httpResponse, HttpContext httpContext) throws ProtocolException {
                        LogUtil.d(TAG, "openUrl4RdirectURL getLocationURI method");
                        return null;
                    }
                });
                setHttpCommonParam(context, weiboParameters);
                defaultHttpClient.getParams().setParameter("http.route.default-proxy", NetStateManager.getAPN());
                if (str2.equals(HTTP_METHOD_GET)) {
                    String toString = new StringBuilder(String.valueOf(str)).append("?").append(weiboParameters.encodeUrl()).toString();
                    LogUtil.d(TAG, new StringBuilder("openUrl4RdirectURL GET url : ").append(toString).toString());
                    httpUriRequest = new HttpGet(toString);
                } else if (str2.equals(HTTP_METHOD_POST)) {
                    httpUriRequest = new HttpPost(str);
                    LogUtil.d(TAG, new StringBuilder("openUrl4RdirectURL POST url : ").append(str).toString());
                }
                HttpResponse execute = defaultHttpClient.execute(httpUriRequest);
                int statusCode = execute.getStatusLine().getStatusCode();
                String value;
                if (statusCode == 301 || statusCode == 302) {
                    value = execute.getFirstHeader(HttpConstant.LOCATION).getValue();
                    LogUtil.d(TAG, new StringBuilder("RedirectURL = ").append(value).toString());
                    shutdownHttpClient(defaultHttpClient);
                    return value;
                } else if (statusCode == 200) {
                    value = readRsponse(execute);
                    shutdownHttpClient(defaultHttpClient);
                    String str3 = value;
                    return str3;
                } else {
                    throw new WeiboHttpException(readRsponse(execute), statusCode);
                }
            } catch (Throwable e) {
                Throwable th = e;
                DefaultHttpClient defaultHttpClient2 = defaultHttpClient;
                r0 = th;
            } catch (Throwable e2) {
                th = e2;
                HttpClient httpClient = r0;
                r0 = th;
                shutdownHttpClient(httpClient);
                throw r0;
            }
        } catch (IOException e3) {
            r0 = e3;
            try {
                Throwable th2;
                throw new WeiboException(th2);
            } catch (Throwable th3) {
                th2 = th3;
            }
        }
    }

    public static String openRedirectUrl4LocationUri(Context context, String str, String str2, WeiboParameters weiboParameters) {
        Throwable th;
        HttpUriRequest httpUriRequest = null;
        try {
            Object anonymousClass_2 = new CustomRedirectHandler() {
                public boolean shouldRedirectUrl(String str) {
                    return true;
                }

                public void onReceivedException() {
                }
            };
            DefaultHttpClient defaultHttpClient = (DefaultHttpClient) getNewHttpClient();
            try {
                defaultHttpClient.setRedirectHandler(anonymousClass_2);
                setHttpCommonParam(context, weiboParameters);
                defaultHttpClient.getParams().setParameter("http.route.default-proxy", NetStateManager.getAPN());
                if (str2.equals(HTTP_METHOD_GET)) {
                    httpUriRequest = new HttpGet(new StringBuilder(String.valueOf(str)).append("?").append(weiboParameters.encodeUrl()).toString());
                } else if (str2.equals(HTTP_METHOD_POST)) {
                    httpUriRequest = new HttpPost(str);
                }
                httpUriRequest.setHeader("User-Agent", NetworkHelper.generateUA(context));
                defaultHttpClient.execute(httpUriRequest);
                String redirectUrl = anonymousClass_2.getRedirectUrl();
                shutdownHttpClient(defaultHttpClient);
                return redirectUrl;
            } catch (Throwable e) {
                Throwable th2 = e;
                DefaultHttpClient defaultHttpClient2 = defaultHttpClient;
                th = th2;
                try {
                    throw new WeiboException(th);
                } catch (Throwable th3) {
                    th = th3;
                }
            } catch (Throwable e2) {
                th2 = e2;
                defaultHttpClient2 = defaultHttpClient;
                th = th2;
                shutdownHttpClient(r1);
                throw th;
            }
        } catch (IOException e3) {
            th = e3;
            throw new WeiboException(th);
        }
    }

    public static synchronized String downloadFile(Context context, String str, String str2, String str3) throws WeiboException {
        String path;
        synchronized (HttpManager.class) {
            try {
                HttpClient newHttpClient;
                File file = new File(str2);
                if (!file.exists()) {
                    file.mkdirs();
                }
                File file2 = new File(file, str3);
                if (file2.exists()) {
                    path = file2.getPath();
                } else if (URLUtil.isValidUrl(str)) {
                    newHttpClient = getNewHttpClient();
                    long j = 0;
                    File file3 = new File(str2, new StringBuilder(String.valueOf(str3)).append("_temp").toString());
                    try {
                        long parseLong;
                        long j2;
                        InputStream content;
                        if (file3.exists()) {
                            j = file3.length();
                        } else {
                            file3.createNewFile();
                        }
                        HttpUriRequest httpGet = new HttpGet(str);
                        httpGet.setHeader("RANGE", new StringBuilder("bytes=").append(j).append(SocializeConstants.OP_DIVIDER_MINUS).toString());
                        HttpResponse execute = newHttpClient.execute(httpGet);
                        int statusCode = execute.getStatusLine().getStatusCode();
                        if (statusCode == 206) {
                            Header[] headers = execute.getHeaders("Content-Range");
                            if (!(headers == null || headers.length == 0)) {
                                String value = headers[0].getValue();
                                parseLong = Long.parseLong(value.substring(value.indexOf(R.styleable.AppCompatTheme_spinnerDropDownItemStyle) + 1));
                                j2 = j;
                            }
                            parseLong = 0;
                            j2 = j;
                        } else if (statusCode == 200) {
                            j = 0;
                            Header firstHeader = execute.getFirstHeader(HttpConstant.CONTENT_LENGTH);
                            if (firstHeader != null) {
                                parseLong = (long) Integer.valueOf(firstHeader.getValue()).intValue();
                                j2 = 0;
                            }
                            parseLong = 0;
                            j2 = j;
                        } else {
                            throw new WeiboHttpException(readRsponse(execute), statusCode);
                        }
                        HttpEntity entity = execute.getEntity();
                        Header firstHeader2 = execute.getFirstHeader(HttpConstant.CONTENT_ENCODING);
                        if (firstHeader2 == null || firstHeader2.getValue().toLowerCase().indexOf(HttpConstant.GZIP) < 0) {
                            content = entity.getContent();
                        } else {
                            content = new GZIPInputStream(entity.getContent());
                        }
                        RandomAccessFile randomAccessFile = new RandomAccessFile(file3, "rw");
                        randomAccessFile.seek(j2);
                        byte[] bArr = new byte[1024];
                        while (true) {
                            int read = content.read(bArr);
                            if (read == -1) {
                                break;
                            }
                            randomAccessFile.write(bArr, 0, read);
                        }
                        randomAccessFile.close();
                        content.close();
                        if (parseLong == 0 || file3.length() < parseLong) {
                            file3.delete();
                            if (newHttpClient != null) {
                                newHttpClient.getConnectionManager().closeExpiredConnections();
                                newHttpClient.getConnectionManager().closeIdleConnections(300, TimeUnit.SECONDS);
                            }
                            path = a.d;
                        } else {
                            file3.renameTo(file2);
                            path = file2.getPath();
                            if (newHttpClient != null) {
                                newHttpClient.getConnectionManager().closeExpiredConnections();
                                newHttpClient.getConnectionManager().closeIdleConnections(300, TimeUnit.SECONDS);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        file3.delete();
                        if (newHttpClient != null) {
                            newHttpClient.getConnectionManager().closeExpiredConnections();
                            newHttpClient.getConnectionManager().closeIdleConnections(300, TimeUnit.SECONDS);
                        }
                    }
                } else {
                    path = a.d;
                }
            } catch (Throwable th) {
            }
        }
        return path;
    }

    public static HttpClient getNewHttpClient() {
        try {
            HttpParams basicHttpParams = new BasicHttpParams();
            HttpProtocolParams.setVersion(basicHttpParams, HttpVersion.HTTP_1_1);
            HttpProtocolParams.setContentCharset(basicHttpParams, GameManager.DEFAULT_CHARSET);
            SchemeRegistry schemeRegistry = new SchemeRegistry();
            schemeRegistry.register(new Scheme(HttpConstant.HTTP, PlainSocketFactory.getSocketFactory(), 80));
            schemeRegistry.register(new Scheme(b.a, getSSLSocketFactory(), 443));
            ClientConnectionManager threadSafeClientConnManager = new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry);
            HttpConnectionParams.setConnectionTimeout(basicHttpParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(basicHttpParams, SOCKET_TIMEOUT);
            return new DefaultHttpClient(threadSafeClientConnManager, basicHttpParams);
        } catch (Exception e) {
            return new DefaultHttpClient();
        }
    }

    public static void buildParams(OutputStream outputStream, WeiboParameters weiboParameters) throws WeiboException {
        try {
            Set<String> keySet = weiboParameters.keySet();
            for (String str : keySet) {
                if (weiboParameters.get(str) instanceof String) {
                    StringBuilder stringBuilder = new StringBuilder(100);
                    stringBuilder.setLength(0);
                    stringBuilder.append(MP_BOUNDARY).append("\r\n");
                    stringBuilder.append("content-disposition: form-data; name=\"").append(str).append("\"\r\n\r\n");
                    stringBuilder.append(weiboParameters.get(str)).append("\r\n");
                    outputStream.write(stringBuilder.toString().getBytes());
                }
            }
            for (String str2 : keySet) {
                Object obj = weiboParameters.get(str2);
                if (obj instanceof Bitmap) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(MP_BOUNDARY).append("\r\n");
                    stringBuilder.append("content-disposition: form-data; name=\"").append(str2).append("\"; filename=\"file\"\r\n");
                    stringBuilder.append("Content-Type: application/octet-stream; charset=utf-8\r\n\r\n");
                    outputStream.write(stringBuilder.toString().getBytes());
                    Bitmap bitmap = (Bitmap) obj;
                    OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(CompressFormat.PNG, R.styleable.AppCompatTheme_buttonStyle, byteArrayOutputStream);
                    outputStream.write(byteArrayOutputStream.toByteArray());
                    outputStream.write("\r\n".getBytes());
                } else if (obj instanceof ByteArrayOutputStream) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(MP_BOUNDARY).append("\r\n");
                    stringBuilder.append("content-disposition: form-data; name=\"").append(str2).append("\"; filename=\"file\"\r\n");
                    stringBuilder.append("Content-Type: application/octet-stream; charset=utf-8\r\n\r\n");
                    outputStream.write(stringBuilder.toString().getBytes());
                    ByteArrayOutputStream byteArrayOutputStream2 = (ByteArrayOutputStream) obj;
                    outputStream.write(byteArrayOutputStream2.toByteArray());
                    outputStream.write("\r\n".getBytes());
                    byteArrayOutputStream2.close();
                }
            }
            outputStream.write(new StringBuilder("\r\n").append(END_MP_BOUNDARY).toString().getBytes());
        } catch (Throwable e) {
            throw new WeiboException(e);
        }
    }

    public static String readRsponse(HttpResponse httpResponse) throws WeiboException {
        Throwable e;
        Throwable th;
        String str = null;
        if (httpResponse != null) {
            HttpEntity entity = httpResponse.getEntity();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            InputStream content;
            try {
                content = entity.getContent();
                try {
                    Header firstHeader = httpResponse.getFirstHeader(HttpConstant.CONTENT_ENCODING);
                    if (firstHeader != null && firstHeader.getValue().toLowerCase().indexOf(HttpConstant.GZIP) >= 0) {
                        content = new GZIPInputStream(content);
                    }
                    byte[] bArr = new byte[8192];
                    while (true) {
                        int read = content.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr, 0, read);
                    }
                    str = new String(byteArrayOutputStream.toByteArray(), GameManager.DEFAULT_CHARSET);
                    LogUtil.d(TAG, new StringBuilder("readRsponse result : ").append(str).toString());
                    if (content != null) {
                        try {
                            content.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                    try {
                        byteArrayOutputStream.close();
                    } catch (IOException e22) {
                        e22.printStackTrace();
                    }
                } catch (IOException e3) {
                    e = e3;
                }
            } catch (Throwable e4) {
                th = e4;
                content = null;
                e = th;
                try {
                    throw new WeiboException(e);
                } catch (Throwable th2) {
                    e = th2;
                }
            } catch (Throwable e42) {
                th = e42;
                content = null;
                e = th;
                if (content != null) {
                    try {
                        content.close();
                    } catch (IOException e222) {
                        e222.printStackTrace();
                    }
                }
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e2222) {
                    e2222.printStackTrace();
                }
                throw e;
            }
        }
        return str;
    }

    public static String getBoundry() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 1; i < 12; i++) {
            long currentTimeMillis = System.currentTimeMillis() + ((long) i);
            if (currentTimeMillis % 3 == 0) {
                stringBuffer.append(((char) ((int) currentTimeMillis)) % 9);
            } else if (currentTimeMillis % 3 == 1) {
                stringBuffer.append((char) ((int) ((currentTimeMillis % 26) + 65)));
            } else {
                stringBuffer.append((char) ((int) ((currentTimeMillis % 26) + 97)));
            }
        }
        return stringBuffer.toString();
    }

    private static SSLSocketFactory getSSLSocketFactory() {
        if (sSSLSocketFactory == null) {
            try {
                KeyStore instance = KeyStore.getInstance(KeyStore.getDefaultType());
                instance.load(null, null);
                Certificate certificate = getCertificate("cacert_cn.cer");
                Certificate certificate2 = getCertificate("cacert_com.cer");
                instance.setCertificateEntry("cnca", certificate);
                instance.setCertificateEntry("comca", certificate2);
                sSSLSocketFactory = new SSLSocketFactoryEx(instance);
                LogUtil.d(TAG, "getSSLSocketFactory noraml !!!!!");
            } catch (Exception e) {
                e.printStackTrace();
                sSSLSocketFactory = SSLSocketFactory.getSocketFactory();
                LogUtil.d(TAG, "getSSLSocketFactory error default !!!!!");
            }
        }
        return sSSLSocketFactory;
    }

    private static Certificate getCertificate(String str) throws CertificateException, IOException {
        CertificateFactory instance = CertificateFactory.getInstance("X.509");
        InputStream resourceAsStream = HttpManager.class.getResourceAsStream(str);
        try {
            Certificate generateCertificate = instance.generateCertificate(resourceAsStream);
            if (resourceAsStream != null) {
                resourceAsStream.close();
            }
            return generateCertificate;
        } catch (Throwable th) {
            if (resourceAsStream != null) {
                resourceAsStream.close();
            }
        }
    }

    private static String getTimestamp() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }

    private static String getOauthSign(Context context, String str, String str2, String str3, String str4) {
        StringBuilder stringBuilder = new StringBuilder(a.d);
        if (!TextUtils.isEmpty(str)) {
            stringBuilder.append(str);
        }
        if (!TextUtils.isEmpty(str2)) {
            stringBuilder.append(str2);
        }
        if (!TextUtils.isEmpty(str3)) {
            stringBuilder.append(str3);
        }
        return calcOauthSignNative(context, stringBuilder.toString(), str4);
    }
}
