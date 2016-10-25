package com.xunlei.common.register.a;

import android.content.Context;
import com.xunlei.common.httpclient.AsyncHttpClient;
import com.xunlei.common.httpclient.BaseHttpClient;
import com.xunlei.common.httpclient.BaseHttpClientListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.http.Header;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory;

// compiled from: XLRegisterRequestHandler.java
public class a {
    private static String a = "zhuce.xunlei.com";
    private static int e = 0;
    private static int f = 1;
    private static a j;
    private final String b;
    private final String c;
    private final String d;
    private int g;
    private Context h;
    private BaseHttpClient i;

    // compiled from: XLRegisterRequestHandler.java
    public static class a extends SSLSocketFactory {
        private SSLContext a;

        public a(KeyStore keyStore) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException {
            super(keyStore);
            this.a = SSLContext.getInstance(SSLSocketFactoryFactory.DEFAULT_PROTOCOL);
            AnonymousClass_1 anonymousClass_1 = new X509TrustManager() {
                private /* synthetic */ com.xunlei.common.register.a.a.a a;

                public final X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public final void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
                }

                public final void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
                }
            };
            this.a.init(null, new TrustManager[]{anonymousClass_1}, null);
        }

        public final Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException, UnknownHostException {
            return this.a.getSocketFactory().createSocket(socket, str, i, z);
        }

        public final Socket createSocket() throws IOException {
            return this.a.getSocketFactory().createSocket();
        }
    }

    static {
        j = null;
    }

    private a() {
        this.g = 1;
        this.h = null;
        this.i = new AsyncHttpClient(c.a().e());
    }

    public static a a() {
        if (j != null) {
            return j;
        }
        synchronized (a.class) {
            if (j == null) {
                j = new a();
            }
        }
        return j;
    }

    public final boolean a(Context context) {
        KeyStore instance;
        SSLSocketFactory aVar;
        SSLSocketFactory sSLSocketFactory;
        KeyManagementException keyManagementException;
        BaseHttpClient baseHttpClient;
        KeyStoreException keyStoreException;
        this.h = context;
        try {
            instance = KeyStore.getInstance(KeyStore.getDefaultType());
            try {
                instance.load(null, null);
            } catch (Exception e) {
                Exception e2 = e;
                e2.printStackTrace();
                aVar = new a(instance);
                try {
                    aVar.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
                } catch (KeyManagementException e3) {
                    KeyManagementException keyManagementException2 = e3;
                    sSLSocketFactory = aVar;
                    keyManagementException = keyManagementException2;
                    keyManagementException.printStackTrace();
                    aVar = sSLSocketFactory;
                    baseHttpClient = this.i;
                    if (baseHttpClient instanceof AsyncHttpClient) {
                        ((AsyncHttpClient) baseHttpClient).setSSLSocketFactory(aVar);
                    }
                    return true;
                } catch (UnrecoverableKeyException e4) {
                    UnrecoverableKeyException unrecoverableKeyException = e4;
                    sSLSocketFactory = aVar;
                    r1 = unrecoverableKeyException;
                    r1.printStackTrace();
                    aVar = sSLSocketFactory;
                    baseHttpClient = this.i;
                    if (baseHttpClient instanceof AsyncHttpClient) {
                        ((AsyncHttpClient) baseHttpClient).setSSLSocketFactory(aVar);
                    }
                    return true;
                } catch (NoSuchAlgorithmException e5) {
                    NoSuchAlgorithmException noSuchAlgorithmException = e5;
                    sSLSocketFactory = aVar;
                    r1 = noSuchAlgorithmException;
                    r1.printStackTrace();
                    aVar = sSLSocketFactory;
                    baseHttpClient = this.i;
                    if (baseHttpClient instanceof AsyncHttpClient) {
                        ((AsyncHttpClient) baseHttpClient).setSSLSocketFactory(aVar);
                    }
                    return true;
                } catch (KeyStoreException e6) {
                    KeyStoreException keyStoreException2 = e6;
                    sSLSocketFactory = aVar;
                    keyStoreException = keyStoreException2;
                    keyStoreException.printStackTrace();
                    aVar = sSLSocketFactory;
                    baseHttpClient = this.i;
                    if (baseHttpClient instanceof AsyncHttpClient) {
                        ((AsyncHttpClient) baseHttpClient).setSSLSocketFactory(aVar);
                    }
                    return true;
                }
                baseHttpClient = this.i;
                if (baseHttpClient instanceof AsyncHttpClient) {
                    ((AsyncHttpClient) baseHttpClient).setSSLSocketFactory(aVar);
                }
                return true;
            }
        } catch (Exception e7) {
            e2 = e7;
            instance = null;
            e2.printStackTrace();
            try {
                aVar = new a(instance);
                aVar.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            } catch (KeyManagementException e32) {
                keyManagementException = e32;
                Object obj = null;
                keyManagementException.printStackTrace();
                aVar = sSLSocketFactory;
                baseHttpClient = this.i;
                if (baseHttpClient instanceof AsyncHttpClient) {
                    ((AsyncHttpClient) baseHttpClient).setSSLSocketFactory(aVar);
                }
                return true;
            } catch (UnrecoverableKeyException e42) {
                UnrecoverableKeyException unrecoverableKeyException2;
                unrecoverableKeyException2 = e42;
                obj = null;
                unrecoverableKeyException2.printStackTrace();
                aVar = sSLSocketFactory;
                baseHttpClient = this.i;
                if (baseHttpClient instanceof AsyncHttpClient) {
                    ((AsyncHttpClient) baseHttpClient).setSSLSocketFactory(aVar);
                }
                return true;
            } catch (NoSuchAlgorithmException e52) {
                NoSuchAlgorithmException noSuchAlgorithmException2;
                noSuchAlgorithmException2 = e52;
                obj = null;
                noSuchAlgorithmException2.printStackTrace();
                aVar = sSLSocketFactory;
                baseHttpClient = this.i;
                if (baseHttpClient instanceof AsyncHttpClient) {
                    ((AsyncHttpClient) baseHttpClient).setSSLSocketFactory(aVar);
                }
                return true;
            } catch (KeyStoreException e62) {
                keyStoreException = e62;
                obj = null;
                keyStoreException.printStackTrace();
                aVar = sSLSocketFactory;
                baseHttpClient = this.i;
                if (baseHttpClient instanceof AsyncHttpClient) {
                    ((AsyncHttpClient) baseHttpClient).setSSLSocketFactory(aVar);
                }
                return true;
            }
            baseHttpClient = this.i;
            if (baseHttpClient instanceof AsyncHttpClient) {
                ((AsyncHttpClient) baseHttpClient).setSSLSocketFactory(aVar);
            }
            return true;
        }
        aVar = new a(instance);
        aVar.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        baseHttpClient = this.i;
        if (baseHttpClient instanceof AsyncHttpClient) {
            ((AsyncHttpClient) baseHttpClient).setSSLSocketFactory(aVar);
        }
        return true;
    }

    public static boolean b() {
        return true;
    }

    private void a(int i) {
        this.g = i;
    }

    private int c() {
        return this.g;
    }

    private void a(BaseHttpClient baseHttpClient) {
        this.i = baseHttpClient;
    }

    private BaseHttpClient d() {
        return this.i;
    }

    public final void a(Header[] headerArr, String str, BaseHttpClientListener baseHttpClientListener) {
        String str2 = "https://zhuce.xunlei.com/regapi";
        if (this.g == 0) {
            str2 = "http://zhuce.xunlei.com/regapi";
        }
        this.i.post(this.h, str2, headerArr, str.getBytes(), baseHttpClientListener);
    }

    public final void b(Header[] headerArr, String str, BaseHttpClientListener baseHttpClientListener) {
        String str2 = "https://zhuce.xunlei.com/regapi";
        if (this.g == 0) {
            str2 = "http://zhuce.xunlei.com/regapi";
        }
        this.i.get(this.h, str2 + "?" + str, null, baseHttpClientListener);
    }

    private void e() {
        KeyStore instance;
        SSLSocketFactory aVar;
        SSLSocketFactory sSLSocketFactory;
        KeyManagementException keyManagementException;
        BaseHttpClient baseHttpClient;
        NoSuchAlgorithmException noSuchAlgorithmException;
        try {
            instance = KeyStore.getInstance(KeyStore.getDefaultType());
            try {
                instance.load(null, null);
            } catch (Exception e) {
                Exception e2 = e;
                e2.printStackTrace();
                aVar = new a(instance);
                try {
                    aVar.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
                } catch (KeyManagementException e3) {
                    KeyManagementException keyManagementException2 = e3;
                    sSLSocketFactory = aVar;
                    keyManagementException = keyManagementException2;
                    keyManagementException.printStackTrace();
                    aVar = sSLSocketFactory;
                    baseHttpClient = this.i;
                    if (!(baseHttpClient instanceof AsyncHttpClient)) {
                        ((AsyncHttpClient) baseHttpClient).setSSLSocketFactory(aVar);
                    }
                } catch (UnrecoverableKeyException e4) {
                    UnrecoverableKeyException unrecoverableKeyException = e4;
                    sSLSocketFactory = aVar;
                    r1 = unrecoverableKeyException;
                    r1.printStackTrace();
                    aVar = sSLSocketFactory;
                    baseHttpClient = this.i;
                    if (!(baseHttpClient instanceof AsyncHttpClient)) {
                        ((AsyncHttpClient) baseHttpClient).setSSLSocketFactory(aVar);
                    }
                } catch (NoSuchAlgorithmException e5) {
                    NoSuchAlgorithmException noSuchAlgorithmException2 = e5;
                    sSLSocketFactory = aVar;
                    noSuchAlgorithmException = noSuchAlgorithmException2;
                    noSuchAlgorithmException.printStackTrace();
                    aVar = sSLSocketFactory;
                    baseHttpClient = this.i;
                    if (!(baseHttpClient instanceof AsyncHttpClient)) {
                        ((AsyncHttpClient) baseHttpClient).setSSLSocketFactory(aVar);
                    }
                } catch (KeyStoreException e6) {
                    KeyStoreException keyStoreException = e6;
                    sSLSocketFactory = aVar;
                    r1 = keyStoreException;
                    r1.printStackTrace();
                    aVar = sSLSocketFactory;
                    baseHttpClient = this.i;
                    if (!(baseHttpClient instanceof AsyncHttpClient)) {
                        ((AsyncHttpClient) baseHttpClient).setSSLSocketFactory(aVar);
                    }
                }
                baseHttpClient = this.i;
                if (!(baseHttpClient instanceof AsyncHttpClient)) {
                    ((AsyncHttpClient) baseHttpClient).setSSLSocketFactory(aVar);
                }
            }
        } catch (Exception e7) {
            e2 = e7;
            instance = null;
            e2.printStackTrace();
            try {
                aVar = new a(instance);
                aVar.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            } catch (KeyManagementException e32) {
                keyManagementException = e32;
                Object obj = null;
                keyManagementException.printStackTrace();
                aVar = sSLSocketFactory;
                baseHttpClient = this.i;
                if (!(baseHttpClient instanceof AsyncHttpClient)) {
                    ((AsyncHttpClient) baseHttpClient).setSSLSocketFactory(aVar);
                }
            } catch (UnrecoverableKeyException e42) {
                UnrecoverableKeyException unrecoverableKeyException2;
                unrecoverableKeyException2 = e42;
                obj = null;
                unrecoverableKeyException2.printStackTrace();
                aVar = sSLSocketFactory;
                baseHttpClient = this.i;
                if (!(baseHttpClient instanceof AsyncHttpClient)) {
                    ((AsyncHttpClient) baseHttpClient).setSSLSocketFactory(aVar);
                }
            } catch (NoSuchAlgorithmException e52) {
                noSuchAlgorithmException = e52;
                obj = null;
                noSuchAlgorithmException.printStackTrace();
                aVar = sSLSocketFactory;
                baseHttpClient = this.i;
                if (!(baseHttpClient instanceof AsyncHttpClient)) {
                    ((AsyncHttpClient) baseHttpClient).setSSLSocketFactory(aVar);
                }
            } catch (KeyStoreException e62) {
                KeyStoreException keyStoreException2;
                keyStoreException2 = e62;
                obj = null;
                keyStoreException2.printStackTrace();
                aVar = sSLSocketFactory;
                baseHttpClient = this.i;
                if (!(baseHttpClient instanceof AsyncHttpClient)) {
                    ((AsyncHttpClient) baseHttpClient).setSSLSocketFactory(aVar);
                }
            }
            baseHttpClient = this.i;
            if (!(baseHttpClient instanceof AsyncHttpClient)) {
                ((AsyncHttpClient) baseHttpClient).setSSLSocketFactory(aVar);
            }
        }
        aVar = new a(instance);
        aVar.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        baseHttpClient = this.i;
        if (!(baseHttpClient instanceof AsyncHttpClient)) {
            ((AsyncHttpClient) baseHttpClient).setSSLSocketFactory(aVar);
        }
    }
}
