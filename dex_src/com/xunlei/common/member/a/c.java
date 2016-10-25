package com.xunlei.common.member.a;

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
import org.apache.http.conn.ssl.SSLSocketFactory;

// compiled from: SSLSocketFactoryEx.java
public final class c extends SSLSocketFactory {
    private SSLContext a;

    public c(KeyStore keyStore) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException {
        super(keyStore);
        this.a = SSLContext.getInstance("TLS");
        AnonymousClass_1 anonymousClass_1 = new X509TrustManager() {
            private /* synthetic */ c a;

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
