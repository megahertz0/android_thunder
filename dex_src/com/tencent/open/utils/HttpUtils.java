package com.tencent.open.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import anet.channel.util.HttpConstant;
import com.alipay.sdk.cons.b;
import com.alipay.sdk.packet.d;
import com.qq.e.comm.constants.Constants.KEYS;
import com.sina.weibo.sdk.component.GameManager;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.connect.dataprovider.ErrorCode;
import com.tencent.mm.sdk.modelbase.BaseResp.ErrCode;
import com.tencent.open.a.f;
import com.tencent.open.b.g;
import com.tencent.open.utils.HttpUtils.MyX509TrustManager;
import com.tencent.open.utils.Util.Statistic;
import com.tencent.open.yyb.AppbarJsBridge;
import com.tencent.tauth.IRequestListener;
import com.uc.addon.sdk.remote.Tabs;
import com.umeng.a;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.io.ByteArrayOutputStream;
import java.io.CharConversionException;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidClassException;
import java.io.InvalidObjectException;
import java.io.NotActiveException;
import java.io.NotSerializableException;
import java.io.OptionalDataException;
import java.io.StreamCorruptedException;
import java.io.SyncFailedException;
import java.io.UTFDataFormatException;
import java.io.UnsupportedEncodingException;
import java.io.WriteAbortedException;
import java.net.BindException;
import java.net.ConnectException;
import java.net.HttpRetryException;
import java.net.MalformedURLException;
import java.net.NoRouteToHostException;
import java.net.PortUnreachableException;
import java.net.ProtocolException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.FileLockInterruptionException;
import java.nio.charset.MalformedInputException;
import java.nio.charset.UnmappableCharacterException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.InvalidPropertiesFormatException;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLKeyException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLProtocolException;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import org.apache.http.ConnectionClosedException;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.MalformedChunkCodingException;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: ProGuard
public class HttpUtils {

    // compiled from: ProGuard
    final class AnonymousClass_1 extends Thread {
        final /* synthetic */ QQToken a;
        final /* synthetic */ Context b;
        final /* synthetic */ String c;
        final /* synthetic */ Bundle d;
        final /* synthetic */ String e;
        final /* synthetic */ IRequestListener f;

        AnonymousClass_1(QQToken qQToken, Context context, String str, Bundle bundle, String str2, IRequestListener iRequestListener) {
            this.a = qQToken;
            this.b = context;
            this.c = str;
            this.d = bundle;
            this.e = str2;
            this.f = iRequestListener;
        }

        public final void run() {
            try {
                JSONObject request = HttpUtils.request(this.a, this.b, this.c, this.d, this.e);
                if (this.f != null) {
                    this.f.onComplete(request);
                    f.b("openSDK_LOG.HttpUtils", "OpenApi onComplete");
                }
            } catch (Throwable e) {
                if (this.f != null) {
                    this.f.onMalformedURLException(e);
                    f.b("openSDK_LOG.HttpUtils", "OpenApi requestAsync MalformedURLException", e);
                }
            } catch (Throwable e2) {
                if (this.f != null) {
                    this.f.onConnectTimeoutException(e2);
                    f.b("openSDK_LOG.HttpUtils", "OpenApi requestAsync onConnectTimeoutException", e2);
                }
            } catch (Throwable e22) {
                if (this.f != null) {
                    this.f.onSocketTimeoutException(e22);
                    f.b("openSDK_LOG.HttpUtils", "OpenApi requestAsync onSocketTimeoutException", e22);
                }
            } catch (Throwable e222) {
                if (this.f != null) {
                    this.f.onNetworkUnavailableException(e222);
                    f.b("openSDK_LOG.HttpUtils", "OpenApi requestAsync onNetworkUnavailableException", e222);
                }
            } catch (Throwable e2222) {
                if (this.f != null) {
                    this.f.onHttpStatusException(e2222);
                    f.b("openSDK_LOG.HttpUtils", "OpenApi requestAsync onHttpStatusException", e2222);
                }
            } catch (Throwable e22222) {
                if (this.f != null) {
                    this.f.onIOException(e22222);
                    f.b("openSDK_LOG.HttpUtils", "OpenApi requestAsync IOException", e22222);
                }
            } catch (Throwable e222222) {
                if (this.f != null) {
                    this.f.onJSONException(e222222);
                    f.b("openSDK_LOG.HttpUtils", "OpenApi requestAsync JSONException", e222222);
                }
            } catch (Throwable e2222222) {
                if (this.f != null) {
                    this.f.onUnknowException(e2222222);
                    f.b("openSDK_LOG.HttpUtils", "OpenApi requestAsync onUnknowException", e2222222);
                }
            }
        }
    }

    // compiled from: ProGuard
    public static class CustomSSLSocketFactory extends SSLSocketFactory {
        private final SSLContext a;

        public CustomSSLSocketFactory(KeyStore keyStore) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException {
            MyX509TrustManager myX509TrustManager;
            super(keyStore);
            this.a = SSLContext.getInstance("TLS");
            try {
                myX509TrustManager = new MyX509TrustManager();
            } catch (Exception e) {
                myX509TrustManager = null;
            }
            this.a.init(null, new TrustManager[]{myX509TrustManager}, null);
        }

        public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException, UnknownHostException {
            return this.a.getSocketFactory().createSocket(socket, str, i, z);
        }

        public Socket createSocket() throws IOException {
            return this.a.getSocketFactory().createSocket();
        }
    }

    // compiled from: ProGuard
    public static class HttpStatusException extends Exception {
        public static final String ERROR_INFO = "http status code error:";

        public HttpStatusException(String str) {
            super(str);
        }
    }

    // compiled from: ProGuard
    public static class MyX509TrustManager implements X509TrustManager {
        X509TrustManager a;

        MyX509TrustManager() throws Exception {
            Throwable th;
            try {
                KeyStore instance = KeyStore.getInstance("JKS");
            } catch (Exception e) {
                instance = null;
            }
            if (instance != null) {
                FileInputStream fileInputStream;
                try {
                    fileInputStream = new FileInputStream("trustedCerts");
                    try {
                        instance.load(fileInputStream, "passphrase".toCharArray());
                        TrustManagerFactory instance2 = TrustManagerFactory.getInstance("SunX509", "SunJSSE");
                        instance2.init(instance);
                        TrustManager[] trustManagers = instance2.getTrustManagers();
                        fileInputStream.close();
                    } catch (Throwable th2) {
                        th = th2;
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    fileInputStream = null;
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    throw th;
                }
            }
            TrustManagerFactory instance3 = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            instance3.init(null);
            trustManagers = instance3.getTrustManagers();
            for (int i = 0; i < trustManagers.length; i++) {
                if (trustManagers[i] instanceof X509TrustManager) {
                    this.a = (X509TrustManager) trustManagers[i];
                    return;
                }
            }
            throw new Exception("Couldn't initialize");
        }

        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            this.a.checkClientTrusted(x509CertificateArr, str);
        }

        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            this.a.checkServerTrusted(x509CertificateArr, str);
        }

        public X509Certificate[] getAcceptedIssuers() {
            return this.a.getAcceptedIssuers();
        }
    }

    // compiled from: ProGuard
    public static class NetworkProxy {
        public final String host;
        public final int port;

        private NetworkProxy(String str, int i) {
            this.host = str;
            this.port = i;
        }
    }

    // compiled from: ProGuard
    public static class NetworkUnavailableException extends Exception {
        public static final String ERROR_INFO = "network unavailable";

        public NetworkUnavailableException(String str) {
            super(str);
        }
    }

    private HttpUtils() {
    }

    public static JSONObject request(QQToken qQToken, Context context, String str, Bundle bundle, String str2) throws IOException, JSONException, NetworkUnavailableException, HttpStatusException {
        String str3;
        String str4;
        ConnectTimeoutException connectTimeoutException;
        SocketTimeoutException socketTimeoutException;
        JSONObject jSONObject;
        int i;
        long j;
        long j2;
        long j3;
        JSONObject jSONObject2;
        int i2;
        f.a("openSDK_LOG.HttpUtils", "OpenApi request");
        if (str.toLowerCase().startsWith(HttpConstant.HTTP)) {
            str3 = str;
            str4 = str;
        } else {
            str4 = ServerSetting.getInstance().getEnvUrl(context, ServerSetting.DEFAULT_URL_GRAPH_BASE) + str;
            str3 = ServerSetting.getInstance().getEnvUrl(context, ServerSetting.DEFAULT_URL_GRAPH_BASE) + str;
        }
        a(context, qQToken, str);
        long elapsedRealtime = SystemClock.elapsedRealtime();
        int i3 = OpenConfig.getInstance(context, qQToken.getAppId()).getInt("Common_HttpRetryCount");
        f.a("OpenConfig_test", new StringBuilder("config 1:Common_HttpRetryCount            config_value:").append(i3).append("   appid:").append(qQToken.getAppId()).append("     url:").append(str3).toString());
        int i4 = i3 == 0 ? 3 : i3;
        f.a("OpenConfig_test", new StringBuilder("config 1:Common_HttpRetryCount            result_value:").append(i4).append("   appid:").append(qQToken.getAppId()).append("     url:").append(str3).toString());
        long j4 = elapsedRealtime;
        Object obj = null;
        int i5 = 0;
        long j5 = j4;
        do {
            i5++;
            try {
                Statistic openUrl2 = openUrl2(context, str4, str2, bundle);
                JSONObject parseJson = Util.parseJson(openUrl2.response);
                try {
                    int i6 = parseJson.getInt(KEYS.RET);
                } catch (JSONException e) {
                    i6 = ErrCode.ERR_AUTH_DENIED;
                } catch (ConnectTimeoutException e2) {
                    connectTimeoutException = e2;
                } catch (SocketTimeoutException e3) {
                    socketTimeoutException = e3;
                    socketTimeoutException.printStackTrace();
                    if (i5 < i4) {
                        j5 = SystemClock.elapsedRealtime();
                        jSONObject = parseJson;
                        i = -8;
                        j = 0;
                        j2 = 0;
                        continue;
                        if (i5 >= i4) {
                            j3 = j2;
                            j4 = j;
                            jSONObject2 = jSONObject;
                            i2 = i;
                            elapsedRealtime = j4;
                            g.a().a(str3, j5, j3, elapsedRealtime, i2);
                            return jSONObject2;
                        }
                    }
                    g.a().a(str3, j5, 0, 0, ErrorCode.FileNotExist);
                    throw socketTimeoutException;
                } catch (HttpStatusException e4) {
                    r13 = e4;
                    r13.printStackTrace();
                    try {
                        HttpStatusException httpStatusException;
                        i2 = Integer.parseInt(httpStatusException.getMessage().replace(HttpStatusException.ERROR_INFO, a.d));
                    } catch (Exception e5) {
                        e5.printStackTrace();
                        i2 = ErrorCode.FileIsEmpty;
                    }
                    g.a().a(str3, j5, 0, 0, i2);
                    throw httpStatusException;
                } catch (NetworkUnavailableException e6) {
                    e6.printStackTrace();
                    throw e6;
                } catch (MalformedURLException e7) {
                    MalformedURLException malformedURLException = e7;
                    malformedURLException.printStackTrace();
                    g.a().a(str3, j5, 0, 0, AppbarJsBridge.Code_Java_Exception);
                    throw malformedURLException;
                } catch (IOException e8) {
                    IOException iOException = e8;
                    iOException.printStackTrace();
                    g.a().a(str3, j5, 0, 0, getErrorCodeFromException(iOException));
                    throw iOException;
                }
                try {
                    j = openUrl2.reqSize;
                    elapsedRealtime = openUrl2.rspSize;
                    j3 = j;
                    jSONObject2 = parseJson;
                    i2 = i6;
                    break;
                } catch (ConnectTimeoutException e22) {
                    connectTimeoutException = e22;
                } catch (SocketTimeoutException e32) {
                    socketTimeoutException = e32;
                    socketTimeoutException.printStackTrace();
                    if (i5 < i4) {
                        j5 = SystemClock.elapsedRealtime();
                        jSONObject = parseJson;
                        i = -8;
                        j = 0;
                        j2 = 0;
                        continue;
                        if (i5 >= i4) {
                            j3 = j2;
                            j4 = j;
                            jSONObject2 = jSONObject;
                            i2 = i;
                            elapsedRealtime = j4;
                            g.a().a(str3, j5, j3, elapsedRealtime, i2);
                            return jSONObject2;
                        }
                    }
                    g.a().a(str3, j5, 0, 0, ErrorCode.FileNotExist);
                    throw socketTimeoutException;
                } catch (HttpStatusException e42) {
                    httpStatusException = e42;
                    httpStatusException.printStackTrace();
                    try {
                        HttpStatusException httpStatusException2;
                        i2 = Integer.parseInt(httpStatusException2.getMessage().replace(HttpStatusException.ERROR_INFO, a.d));
                    } catch (Exception e52) {
                        e52.printStackTrace();
                        i2 = ErrorCode.FileIsEmpty;
                    }
                    g.a().a(str3, j5, 0, 0, i2);
                    throw httpStatusException2;
                } catch (NetworkUnavailableException e62) {
                    e62.printStackTrace();
                    throw e62;
                } catch (MalformedURLException e72) {
                    MalformedURLException malformedURLException2 = e72;
                    malformedURLException2.printStackTrace();
                    g.a().a(str3, j5, 0, 0, AppbarJsBridge.Code_Java_Exception);
                    throw malformedURLException2;
                } catch (IOException e82) {
                    IOException iOException2 = e82;
                    iOException2.printStackTrace();
                    g.a().a(str3, j5, 0, 0, getErrorCodeFromException(iOException2));
                    throw iOException2;
                } catch (JSONException e9) {
                    JSONException jSONException = e9;
                    jSONException.printStackTrace();
                    g.a().a(str3, j5, 0, 0, ErrCode.ERR_AUTH_DENIED);
                    throw jSONException;
                }
            } catch (ConnectTimeoutException e10) {
                connectTimeoutException = e10;
                parseJson = jSONObject;
                connectTimeoutException.printStackTrace();
                if (i5 < i4) {
                    j5 = SystemClock.elapsedRealtime();
                    jSONObject = parseJson;
                    i = -7;
                    j = 0;
                    j2 = 0;
                    continue;
                    if (i5 >= i4) {
                        j3 = j2;
                        j4 = j;
                        jSONObject2 = jSONObject;
                        i2 = i;
                        elapsedRealtime = j4;
                        g.a().a(str3, j5, j3, elapsedRealtime, i2);
                        return jSONObject2;
                    }
                }
                g.a().a(str3, j5, 0, 0, ErrorCode.PathIsNull);
                throw connectTimeoutException;
            } catch (SocketTimeoutException e11) {
                socketTimeoutException = e11;
                parseJson = jSONObject;
                socketTimeoutException.printStackTrace();
                if (i5 < i4) {
                    j5 = SystemClock.elapsedRealtime();
                    jSONObject = parseJson;
                    i = -8;
                    j = 0;
                    j2 = 0;
                    continue;
                    if (i5 >= i4) {
                        j3 = j2;
                        j4 = j;
                        jSONObject2 = jSONObject;
                        i2 = i;
                        elapsedRealtime = j4;
                        g.a().a(str3, j5, j3, elapsedRealtime, i2);
                        return jSONObject2;
                    }
                }
                g.a().a(str3, j5, 0, 0, ErrorCode.FileNotExist);
                throw socketTimeoutException;
            } catch (HttpStatusException e422) {
                httpStatusException2 = e422;
                httpStatusException2.printStackTrace();
                try {
                    HttpStatusException httpStatusException22;
                    i2 = Integer.parseInt(httpStatusException22.getMessage().replace(HttpStatusException.ERROR_INFO, a.d));
                } catch (Exception e522) {
                    e522.printStackTrace();
                    i2 = ErrorCode.FileIsEmpty;
                }
                g.a().a(str3, j5, 0, 0, i2);
                throw httpStatusException22;
            } catch (NetworkUnavailableException e622) {
                e622.printStackTrace();
                throw e622;
            } catch (MalformedURLException e722) {
                MalformedURLException malformedURLException22 = e722;
                malformedURLException22.printStackTrace();
                g.a().a(str3, j5, 0, 0, AppbarJsBridge.Code_Java_Exception);
                throw malformedURLException22;
            } catch (IOException e822) {
                IOException iOException22 = e822;
                iOException22.printStackTrace();
                g.a().a(str3, j5, 0, 0, getErrorCodeFromException(iOException22));
                throw iOException22;
            } catch (JSONException e92) {
                JSONException jSONException2 = e92;
                jSONException2.printStackTrace();
                g.a().a(str3, j5, 0, 0, ErrCode.ERR_AUTH_DENIED);
                throw jSONException2;
            }
        } while (i5 >= i4);
        j3 = j2;
        j4 = j;
        jSONObject2 = jSONObject;
        i2 = i;
        elapsedRealtime = j4;
        g.a().a(str3, j5, j3, elapsedRealtime, i2);
        return jSONObject2;
    }

    public static void requestAsync(QQToken qQToken, Context context, String str, Bundle bundle, String str2, IRequestListener iRequestListener) {
        f.a("openSDK_LOG.HttpUtils", "OpenApi requestAsync");
        new AnonymousClass_1(qQToken, context, str, bundle, str2, iRequestListener).start();
    }

    private static void a(Context context, QQToken qQToken, String str) {
        if (str.indexOf("add_share") >= 0 || str.indexOf("upload_pic") >= 0 || str.indexOf("add_topic") >= 0 || str.indexOf("set_user_face") >= 0 || str.indexOf("add_t") >= 0 || str.indexOf("add_pic_t") >= 0 || str.indexOf("add_pic_url") >= 0 || str.indexOf("add_video") >= 0) {
            com.tencent.connect.a.a.a(context, qQToken, "requireApi", str);
        }
    }

    public static int getErrorCodeFromException(IOException iOException) {
        if (iOException instanceof CharConversionException) {
            return -20;
        }
        if (iOException instanceof MalformedInputException) {
            return -21;
        }
        if (iOException instanceof UnmappableCharacterException) {
            return -22;
        }
        if (iOException instanceof HttpResponseException) {
            return -23;
        }
        if (iOException instanceof ClosedChannelException) {
            return -24;
        }
        if (iOException instanceof ConnectionClosedException) {
            return -25;
        }
        if (iOException instanceof EOFException) {
            return -26;
        }
        if (iOException instanceof FileLockInterruptionException) {
            return -27;
        }
        if (iOException instanceof FileNotFoundException) {
            return -28;
        }
        if (iOException instanceof HttpRetryException) {
            return -29;
        }
        if (iOException instanceof ConnectTimeoutException) {
            return ErrorCode.PathIsNull;
        }
        if (iOException instanceof SocketTimeoutException) {
            return ErrorCode.FileNotExist;
        }
        if (iOException instanceof InvalidPropertiesFormatException) {
            return -30;
        }
        if (iOException instanceof MalformedChunkCodingException) {
            return -31;
        }
        if (iOException instanceof MalformedURLException) {
            return AppbarJsBridge.Code_Java_Exception;
        }
        if (iOException instanceof NoHttpResponseException) {
            return -32;
        }
        if (iOException instanceof InvalidClassException) {
            return -33;
        }
        if (iOException instanceof InvalidObjectException) {
            return -34;
        }
        if (iOException instanceof NotActiveException) {
            return -35;
        }
        if (iOException instanceof NotSerializableException) {
            return -36;
        }
        if (iOException instanceof OptionalDataException) {
            return -37;
        }
        if (iOException instanceof StreamCorruptedException) {
            return -38;
        }
        if (iOException instanceof WriteAbortedException) {
            return -39;
        }
        if (iOException instanceof ProtocolException) {
            return -40;
        }
        if (iOException instanceof SSLHandshakeException) {
            return -41;
        }
        if (iOException instanceof SSLKeyException) {
            return -42;
        }
        if (iOException instanceof SSLPeerUnverifiedException) {
            return -43;
        }
        if (iOException instanceof SSLProtocolException) {
            return -44;
        }
        if (iOException instanceof BindException) {
            return -45;
        }
        if (iOException instanceof ConnectException) {
            return -46;
        }
        if (iOException instanceof NoRouteToHostException) {
            return -47;
        }
        if (iOException instanceof PortUnreachableException) {
            return -48;
        }
        if (iOException instanceof SyncFailedException) {
            return -49;
        }
        if (iOException instanceof UTFDataFormatException) {
            return -50;
        }
        if (iOException instanceof UnknownHostException) {
            return -51;
        }
        if (iOException instanceof UnknownServiceException) {
            return -52;
        }
        if (iOException instanceof UnsupportedEncodingException) {
            return -53;
        }
        return iOException instanceof ZipException ? -54 : Tabs.TAB_CREATE_REACH_MAX_COUNT;
    }

    public static Statistic openUrl2(Context context, String str, String str2, Bundle bundle) throws MalformedURLException, IOException, NetworkUnavailableException, HttpStatusException {
        Bundle bundle2;
        int size;
        if (context != null) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager != null) {
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
                    throw new NetworkUnavailableException(NetworkUnavailableException.ERROR_INFO);
                }
            }
        }
        if (bundle != null) {
            bundle2 = new Bundle(bundle);
        } else {
            bundle2 = new Bundle();
        }
        String string = bundle2.getString("appid_for_getting_config");
        bundle2.remove("appid_for_getting_config");
        HttpClient httpClient = getHttpClient(context, string, str);
        HttpUriRequest httpUriRequest = null;
        int i = 0;
        int length;
        if (str2.equals(Constants.HTTP_GET)) {
            String encodeUrl = encodeUrl(bundle2);
            length = encodeUrl.length() + 0;
            f.a("openSDK_LOG.HttpUtils", new StringBuilder("-->openUrl2 before url =").append(str).toString());
            if (str.indexOf("?") == -1) {
                string = str + "?";
            } else {
                string = str + com.alipay.sdk.sys.a.b;
            }
            f.a("openSDK_LOG.HttpUtils", new StringBuilder("-->openUrl2 encodedParam =").append(encodeUrl).append(" -- url = ").append(string).toString());
            HttpUriRequest httpGet = new HttpGet(string + encodeUrl);
            httpGet.addHeader(HttpConstant.ACCEPT_ENCODING, HttpConstant.GZIP);
            int i2 = length;
            httpUriRequest = httpGet;
            i = i2;
        } else if (str2.equals(Constants.HTTP_POST)) {
            HttpPost httpPost = new HttpPost(str);
            httpPost.addHeader(HttpConstant.ACCEPT_ENCODING, HttpConstant.GZIP);
            Bundle bundle3 = new Bundle();
            for (String string2 : bundle2.keySet()) {
                Object obj = bundle2.get(string2);
                if (obj instanceof byte[]) {
                    bundle3.putByteArray(string2, (byte[]) obj);
                }
            }
            if (!bundle2.containsKey(d.q)) {
                bundle2.putString(d.q, str2);
            }
            httpPost.setHeader("Content-Type", "multipart/form-data; boundary=3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f");
            httpPost.setHeader(HttpConstant.CONNECTION, "Keep-Alive");
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byteArrayOutputStream.write(Util.getBytesUTF8("--3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f\r\n"));
            byteArrayOutputStream.write(Util.getBytesUTF8(encodePostBody(bundle2, "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f")));
            if (!bundle3.isEmpty()) {
                size = bundle3.size();
                byteArrayOutputStream.write(Util.getBytesUTF8("\r\n--3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f\r\n"));
                length = -1;
                for (String string22 : bundle3.keySet()) {
                    length++;
                    byteArrayOutputStream.write(Util.getBytesUTF8(new StringBuilder("Content-Disposition: form-data; name=\"").append(string22).append("\"; filename=\"").append(string22).append("\"\r\n").toString()));
                    byteArrayOutputStream.write(Util.getBytesUTF8("Content-Type: content/unknown\r\n\r\n"));
                    byte[] byteArray = bundle3.getByteArray(string22);
                    if (byteArray != null) {
                        byteArrayOutputStream.write(byteArray);
                    }
                    if (length < size - 1) {
                        byteArrayOutputStream.write(Util.getBytesUTF8("\r\n--3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f\r\n"));
                    }
                }
            }
            byteArrayOutputStream.write(Util.getBytesUTF8("\r\n--3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f--\r\n"));
            byte[] toByteArray = byteArrayOutputStream.toByteArray();
            i = toByteArray.length + 0;
            byteArrayOutputStream.close();
            httpPost.setEntity(new ByteArrayEntity(toByteArray));
            HttpPost httpPost2 = httpPost;
        }
        HttpResponse execute = httpClient.execute(httpUriRequest);
        size = execute.getStatusLine().getStatusCode();
        if (size == 200) {
            return new Statistic(a(execute), i);
        }
        throw new HttpStatusException(new StringBuilder(HttpStatusException.ERROR_INFO).append(size).toString());
    }

    private static String a(HttpResponse httpResponse) throws IllegalStateException, IOException {
        InputStream inputStream;
        InputStream content = httpResponse.getEntity().getContent();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Header firstHeader = httpResponse.getFirstHeader(HttpConstant.CONTENT_ENCODING);
        if (firstHeader == null || firstHeader.getValue().toLowerCase().indexOf(HttpConstant.GZIP) < 0) {
            inputStream = content;
        } else {
            inputStream = new GZIPInputStream(content);
        }
        byte[] bArr = new byte[512];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                String str = new String(byteArrayOutputStream.toByteArray(), GameManager.DEFAULT_CHARSET);
                inputStream.close();
                return str;
            }
        }
    }

    public static HttpClient getHttpClient(Context context, String str, String str2) {
        OpenConfig instance;
        int i;
        int i2 = 0;
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme(HttpConstant.HTTP, PlainSocketFactory.getSocketFactory(), 80));
        if (VERSION.SDK_INT < 16) {
            try {
                KeyStore instance2 = KeyStore.getInstance(KeyStore.getDefaultType());
                instance2.load(null, null);
                SocketFactory customSSLSocketFactory = new CustomSSLSocketFactory(instance2);
                customSSLSocketFactory.setHostnameVerifier(SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
                schemeRegistry.register(new Scheme(b.a, customSSLSocketFactory, 443));
            } catch (Exception e) {
                schemeRegistry.register(new Scheme(b.a, SSLSocketFactory.getSocketFactory(), 443));
            }
        } else {
            schemeRegistry.register(new Scheme(b.a, SSLSocketFactory.getSocketFactory(), 443));
        }
        HttpParams basicHttpParams = new BasicHttpParams();
        if (context != null) {
            instance = OpenConfig.getInstance(context, str);
        } else {
            instance = null;
        }
        if (instance != null) {
            i = instance.getInt("Common_HttpConnectionTimeout");
            i2 = instance.getInt("Common_SocketConnectionTimeout");
        } else {
            i = 0;
        }
        if (i == 0) {
            i = 15000;
        }
        if (i2 == 0) {
            i2 = BuglyStrategy.a.MAX_USERDATA_VALUE_LENGTH;
        }
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, i);
        HttpConnectionParams.setSoTimeout(basicHttpParams, i2);
        HttpProtocolParams.setVersion(basicHttpParams, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(basicHttpParams, GameManager.DEFAULT_CHARSET);
        HttpProtocolParams.setUserAgent(basicHttpParams, new StringBuilder("AndroidSDK_").append(VERSION.SDK).append("_").append(Build.DEVICE).append("_").append(VERSION.RELEASE).toString());
        HttpClient defaultHttpClient = new DefaultHttpClient(new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry), basicHttpParams);
        NetworkProxy proxy = getProxy(context);
        if (proxy != null) {
            defaultHttpClient.getParams().setParameter("http.route.default-proxy", new HttpHost(proxy.host, proxy.port));
        }
        return defaultHttpClient;
    }

    public static String encodeUrl(Bundle bundle) {
        if (bundle == null) {
            return a.d;
        }
        StringBuilder stringBuilder = new StringBuilder();
        int i = 1;
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            if ((obj instanceof String) || (obj instanceof String[])) {
                Object obj2;
                if (obj instanceof String[]) {
                    if (obj2 != null) {
                        obj2 = null;
                    } else {
                        stringBuilder.append(com.alipay.sdk.sys.a.b);
                    }
                    stringBuilder.append(URLEncoder.encode(str) + "=");
                    String[] stringArray = bundle.getStringArray(str);
                    if (stringArray != null) {
                        for (int i2 = 0; i2 < stringArray.length; i2++) {
                            if (i2 == 0) {
                                stringBuilder.append(URLEncoder.encode(stringArray[i2]));
                            } else {
                                stringBuilder.append(URLEncoder.encode(new StringBuilder(MiPushClient.ACCEPT_TIME_SEPARATOR).append(stringArray[i2]).toString()));
                            }
                        }
                    }
                } else {
                    if (obj2 != null) {
                        obj2 = null;
                    } else {
                        stringBuilder.append(com.alipay.sdk.sys.a.b);
                    }
                    stringBuilder.append(URLEncoder.encode(str) + "=" + URLEncoder.encode(bundle.getString(str)));
                }
            }
        }
        return stringBuilder.toString();
    }

    public static String encodePostBody(Bundle bundle, String str) {
        if (bundle == null) {
            return a.d;
        }
        StringBuilder stringBuilder = new StringBuilder();
        int size = bundle.size();
        int i = -1;
        for (String str2 : bundle.keySet()) {
            int i2 = i + 1;
            Object obj = bundle.get(str2);
            if (obj instanceof String) {
                stringBuilder.append(new StringBuilder("Content-Disposition: form-data; name=\"").append(str2).append("\"\r\n\r\n").append((String) obj).toString());
                if (i2 < size - 1) {
                    stringBuilder.append(new StringBuilder("\r\n--").append(str).append("\r\n").toString());
                }
                i = i2;
            } else {
                i = i2;
            }
        }
        return stringBuilder.toString();
    }

    public static NetworkProxy getProxy(Context context) {
        if (context == null) {
            return null;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            return null;
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return null;
        }
        if (activeNetworkInfo.getType() == 0) {
            Object b = b(context);
            int a = a(context);
            if (!TextUtils.isEmpty(b) && a >= 0) {
                return new NetworkProxy(a, null);
            }
        }
        return null;
    }

    private static int a(Context context) {
        if (VERSION.SDK_INT >= 11) {
            Object property = System.getProperty("http.proxyPort");
            if (TextUtils.isEmpty(property)) {
                return -1;
            }
            try {
                return Integer.parseInt(property);
            } catch (NumberFormatException e) {
                return -1;
            }
        } else if (context == null) {
            return Proxy.getDefaultPort();
        } else {
            int port = Proxy.getPort(context);
            return port < 0 ? Proxy.getDefaultPort() : port;
        }
    }

    private static String b(Context context) {
        if (VERSION.SDK_INT >= 11) {
            return System.getProperty("http.proxyHost");
        }
        if (context == null) {
            return Proxy.getDefaultHost();
        }
        String host = Proxy.getHost(context);
        return TextUtils.isEmpty(host) ? Proxy.getDefaultHost() : host;
    }
}
