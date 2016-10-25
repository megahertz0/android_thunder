package com.umeng.message.util;

import com.umeng.message.util.HttpRequest.HttpRequestException;
import com.xunlei.xllib.R;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.zip.GZIPInputStream;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory;

public class HttpRequest {
    public static final String A = "POST";
    public static final String B = "PUT";
    public static final String C = "TRACE";
    public static final String D = "charset";
    private static final String E = "00content0boundary00";
    private static final String F = "multipart/form-data; boundary=00content0boundary00";
    private static final String G = "\r\n";
    private static final String[] H;
    private static SSLSocketFactory I = null;
    private static HostnameVerifier J = null;
    private static int K = 0;
    private static int L = 0;
    private static ConnectionFactory M = null;
    public static final String a = "UTF-8";
    public static final String b = "application/x-www-form-urlencoded";
    public static final String c = "application/json";
    public static final String d = "gzip";
    public static final String e = "Accept";
    public static final String f = "Accept-Charset";
    public static final String g = "Accept-Encoding";
    public static final String h = "Authorization";
    public static final String i = "Cache-Control";
    public static final String j = "Content-Encoding";
    public static final String k = "Content-Length";
    public static final String l = "Content-Type";
    public static final String m = "Date";
    public static final String n = "ETag";
    public static final String o = "Expires";
    public static final String p = "If-None-Match";
    public static final String q = "Last-Modified";
    public static final String r = "Location";
    public static final String s = "Proxy-Authorization";
    public static final String t = "Referer";
    public static final String u = "Server";
    public static final String v = "User-Agent";
    public static final String w = "DELETE";
    public static final String x = "GET";
    public static final String y = "HEAD";
    public static final String z = "OPTIONS";
    private HttpURLConnection N;
    private final URL O;
    private final String P;
    private e Q;
    private boolean R;
    private boolean S;
    private boolean T;
    private boolean U;
    private int V;
    private String W;
    private int X;

    protected static abstract class d<V> implements Callable<V> {
        protected abstract V b() throws HttpRequestException, IOException;

        protected abstract void c() throws IOException;

        protected d() {
        }

        public V call() throws com.umeng.message.util.HttpRequest.HttpRequestException {
            throw new UnsupportedOperationException("Method not decompiled: com.umeng.message.util.HttpRequest.d.call():V");
            /* JADX: method processing error */
/*
            Error: jadx.core.utils.exceptions.JadxRuntimeException: Try/catch wrap count limit reached in com.umeng.message.util.HttpRequest.d.call():V
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:54)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:40)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:16)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
	at jadx.core.ProcessClass.process(ProcessClass.java:22)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:209)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:133)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(Unknown Source)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(Unknown Source)
	at java.lang.Thread.run(Unknown Source)
*/
            /*
            this = this;
            r1 = 1;
            r2 = 0;
            r0 = r3.b();	 Catch:{ HttpRequestException -> 0x0011, IOException -> 0x0018, all -> 0x0028 }
            r3.c();	 Catch:{ IOException -> 0x000a }
            return r0;
        L_0x000a:
            r0 = move-exception;
            r1 = new com.umeng.message.util.HttpRequest$HttpRequestException;
            r1.<init>(r0);
            throw r1;
        L_0x0011:
            r0 = move-exception;
            throw r0;	 Catch:{ all -> 0x0013 }
        L_0x0013:
            r0 = move-exception;
        L_0x0014:
            r3.c();	 Catch:{ IOException -> 0x001f }
        L_0x0017:
            throw r0;
        L_0x0018:
            r0 = move-exception;
            r2 = new com.umeng.message.util.HttpRequest$HttpRequestException;	 Catch:{ all -> 0x0013 }
            r2.<init>(r0);	 Catch:{ all -> 0x0013 }
            throw r2;	 Catch:{ all -> 0x0013 }
        L_0x001f:
            r2 = move-exception;
            if (r1 != 0) goto L_0x0017;
        L_0x0022:
            r0 = new com.umeng.message.util.HttpRequest$HttpRequestException;
            r0.<init>(r2);
            throw r0;
        L_0x0028:
            r0 = move-exception;
            r1 = r2;
            goto L_0x0014;
            */
        }
    }

    protected static abstract class b<V> extends d<V> {
        private final Closeable a;
        private final boolean b;

        protected b(Closeable closeable, boolean z) {
            this.a = closeable;
            this.b = z;
        }

        protected void c() throws IOException {
            if (this.a instanceof Flushable) {
                ((Flushable) this.a).flush();
            }
            if (this.b) {
                try {
                    this.a.close();
                    return;
                } catch (IOException e) {
                }
            }
            this.a.close();
        }
    }

    class AnonymousClass_10 extends b<HttpRequest> {
        final /* synthetic */ Reader a;
        final /* synthetic */ Writer b;

        AnonymousClass_10(Closeable closeable, boolean z, Reader reader, Writer writer) {
            this.a = reader;
            this.b = writer;
            super(closeable, z);
        }

        public /* synthetic */ Object b() throws HttpRequestException, IOException {
            return a();
        }

        public HttpRequest a() throws IOException {
            char[] cArr = new char[HttpRequest.this.V];
            while (true) {
                int read = this.a.read(cArr);
                if (read == -1) {
                    return HttpRequest.this;
                }
                this.b.write(cArr, 0, read);
            }
        }
    }

    protected static abstract class c<V> extends d<V> {
        private final Flushable a;

        protected c(Flushable flushable) {
            this.a = flushable;
        }

        protected void c() throws IOException {
            this.a.flush();
        }
    }

    class AnonymousClass_2 extends c<HttpRequest> {
        final /* synthetic */ Reader a;
        final /* synthetic */ Writer b;

        AnonymousClass_2(Flushable flushable, Reader reader, Writer writer) {
            this.a = reader;
            this.b = writer;
            super(flushable);
        }

        protected /* synthetic */ Object b() throws HttpRequestException, IOException {
            return a();
        }

        protected HttpRequest a() throws IOException {
            return HttpRequest.this.a(this.a, this.b);
        }
    }

    final class AnonymousClass_4 implements PrivilegedAction<String> {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        AnonymousClass_4(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        public final /* synthetic */ Object run() {
            return a();
        }

        public final String a() {
            return System.setProperty(this.a, this.b);
        }
    }

    final class AnonymousClass_5 implements PrivilegedAction<String> {
        final /* synthetic */ String a;

        AnonymousClass_5(String str) {
            this.a = str;
        }

        public final /* synthetic */ Object run() {
            return a();
        }

        public final String a() {
            return System.clearProperty(this.a);
        }
    }

    class AnonymousClass_6 extends b<HttpRequest> {
        final /* synthetic */ OutputStream a;

        AnonymousClass_6(Closeable closeable, boolean z, OutputStream outputStream) {
            this.a = outputStream;
            super(closeable, z);
        }

        protected /* synthetic */ Object b() throws HttpRequestException, IOException {
            return a();
        }

        protected HttpRequest a() throws HttpRequestException, IOException {
            return HttpRequest.this.a(this.a);
        }
    }

    class AnonymousClass_7 extends b<HttpRequest> {
        final /* synthetic */ BufferedReader a;
        final /* synthetic */ Appendable b;

        AnonymousClass_7(Closeable closeable, boolean z, BufferedReader bufferedReader, Appendable appendable) {
            this.a = bufferedReader;
            this.b = appendable;
            super(closeable, z);
        }

        public /* synthetic */ Object b() throws HttpRequestException, IOException {
            return a();
        }

        public HttpRequest a() throws IOException {
            Object allocate = CharBuffer.allocate(HttpRequest.this.V);
            while (true) {
                int read = this.a.read(allocate);
                if (read == -1) {
                    return HttpRequest.this;
                }
                allocate.rewind();
                this.b.append(allocate, 0, read);
                allocate.rewind();
            }
        }
    }

    class AnonymousClass_8 extends b<HttpRequest> {
        final /* synthetic */ BufferedReader a;
        final /* synthetic */ Writer b;

        AnonymousClass_8(Closeable closeable, boolean z, BufferedReader bufferedReader, Writer writer) {
            this.a = bufferedReader;
            this.b = writer;
            super(closeable, z);
        }

        public /* synthetic */ Object b() throws HttpRequestException, IOException {
            return a();
        }

        public HttpRequest a() throws IOException {
            return HttpRequest.this.a(this.a, this.b);
        }
    }

    class AnonymousClass_9 extends b<HttpRequest> {
        final /* synthetic */ InputStream a;
        final /* synthetic */ OutputStream b;

        AnonymousClass_9(Closeable closeable, boolean z, InputStream inputStream, OutputStream outputStream) {
            this.a = inputStream;
            this.b = outputStream;
            super(closeable, z);
        }

        public /* synthetic */ Object b() throws HttpRequestException, IOException {
            return a();
        }

        public HttpRequest a() throws IOException {
            byte[] bArr = new byte[HttpRequest.this.V];
            while (true) {
                int read = this.a.read(bArr);
                if (read == -1) {
                    return HttpRequest.this;
                }
                this.b.write(bArr, 0, read);
            }
        }
    }

    public static interface ConnectionFactory {
        public static final com.umeng.message.util.HttpRequest.ConnectionFactory DEFAULT;

        HttpURLConnection create(URL url) throws IOException;

        HttpURLConnection create(URL url, Proxy proxy) throws IOException;

        static {
            DEFAULT = new com.umeng.message.util.HttpRequest.ConnectionFactory() {
                public final HttpURLConnection create(URL url) throws IOException {
                    return (HttpURLConnection) url.openConnection();
                }

                public final HttpURLConnection create(URL url, Proxy proxy) throws IOException {
                    return (HttpURLConnection) url.openConnection(proxy);
                }
            };
        }
    }

    public static class HttpRequestException extends RuntimeException {
        private static final long serialVersionUID = -1170466989781746231L;

        public /* synthetic */ Throwable getCause() {
            return a();
        }

        protected HttpRequestException(IOException iOException) {
            super(iOException);
        }

        public IOException a() {
            return (IOException) super.getCause();
        }
    }

    public static class a {
        private static final byte a = (byte) 61;
        private static final String b = "US-ASCII";
        private static final byte[] c;

        static {
            c = new byte[]{(byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, (byte) 70, (byte) 71, (byte) 72, (byte) 73, (byte) 74, (byte) 75, (byte) 76, (byte) 77, (byte) 78, (byte) 79, (byte) 80, (byte) 81, (byte) 82, (byte) 83, (byte) 84, (byte) 85, (byte) 86, (byte) 87, (byte) 88, (byte) 89, (byte) 90, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102, (byte) 103, (byte) 104, (byte) 105, (byte) 106, (byte) 107, (byte) 108, (byte) 109, (byte) 110, (byte) 111, (byte) 112, (byte) 113, (byte) 114, (byte) 115, (byte) 116, (byte) 117, (byte) 118, (byte) 119, (byte) 120, (byte) 121, (byte) 122, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 43, (byte) 47};
        }

        private a() {
        }

        private static byte[] a(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
            int i4 = 0;
            byte[] bArr3 = c;
            int i5 = (i2 > 1 ? (bArr[i + 1] << 24) >>> 16 : 0) | (i2 > 0 ? (bArr[i] << 24) >>> 8 : 0);
            if (i2 > 2) {
                i4 = (bArr[i + 2] << 24) >>> 24;
            }
            i4 |= i5;
            switch (i2) {
                case SimpleLog.LOG_LEVEL_TRACE:
                    bArr2[i3] = bArr3[i4 >>> 18];
                    bArr2[i3 + 1] = bArr3[(i4 >>> 12) & 63];
                    bArr2[i3 + 2] = (byte) 61;
                    bArr2[i3 + 3] = (byte) 61;
                    break;
                case SimpleLog.LOG_LEVEL_DEBUG:
                    bArr2[i3] = bArr3[i4 >>> 18];
                    bArr2[i3 + 1] = bArr3[(i4 >>> 12) & 63];
                    bArr2[i3 + 2] = bArr3[(i4 >>> 6) & 63];
                    bArr2[i3 + 3] = (byte) 61;
                    break;
                case MqttConnectOptions.MQTT_VERSION_3_1:
                    bArr2[i3] = bArr3[i4 >>> 18];
                    bArr2[i3 + 1] = bArr3[(i4 >>> 12) & 63];
                    bArr2[i3 + 2] = bArr3[(i4 >>> 6) & 63];
                    bArr2[i3 + 3] = bArr3[i4 & 63];
                    break;
            }
            return bArr2;
        }

        public static String a(String str) {
            byte[] bytes;
            try {
                bytes = str.getBytes(b);
            } catch (UnsupportedEncodingException e) {
                bytes = str.getBytes();
            }
            return a(bytes);
        }

        public static String a(byte[] bArr) {
            return a(bArr, 0, bArr.length);
        }

        public static String a(byte[] bArr, int i, int i2) {
            byte[] b = b(bArr, i, i2);
            try {
                return new String(b, b);
            } catch (UnsupportedEncodingException e) {
                return new String(b);
            }
        }

        public static byte[] b(byte[] bArr, int i, int i2) {
            if (bArr == null) {
                throw new NullPointerException("Cannot serialize a null array.");
            } else if (i < 0) {
                throw new IllegalArgumentException(new StringBuilder("Cannot have negative offset: ").append(i).toString());
            } else if (i2 < 0) {
                throw new IllegalArgumentException(new StringBuilder("Cannot have length offset: ").append(i2).toString());
            } else if (i + i2 > bArr.length) {
                throw new IllegalArgumentException(String.format("Cannot have offset of %d and length of %d with array of length %d", new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(bArr.length)}));
            } else {
                Object obj = new Object[((i2 % 3 > 0 ? MqttConnectOptions.MQTT_VERSION_3_1_1 : 0) + ((i2 / 3) * 4))];
                int i3 = i2 - 2;
                int i4 = 0;
                int i5 = 0;
                while (i5 < i3) {
                    a(bArr, i5 + i, MqttConnectOptions.MQTT_VERSION_3_1, obj, i4);
                    i5 += 3;
                    i4 += 4;
                }
                if (i5 < i2) {
                    a(bArr, i5 + i, i2 - i5, obj, i4);
                    i4 += 4;
                }
                if (i4 > obj.length - 1) {
                    return obj;
                }
                Object obj2 = new Object[i4];
                System.arraycopy(obj, 0, obj2, 0, i4);
                return obj2;
            }
        }
    }

    public static class e extends BufferedOutputStream {
        private final CharsetEncoder a;

        public e(OutputStream outputStream, String str, int i) {
            super(outputStream, i);
            this.a = Charset.forName(HttpRequest.v(str)).newEncoder();
        }

        public com.umeng.message.util.HttpRequest.e a(String str) throws IOException {
            ByteBuffer encode = this.a.encode(CharBuffer.wrap(str));
            super.write(encode.array(), 0, encode.limit());
            return this;
        }
    }

    static {
        H = new String[0];
        K = 3000;
        L = 3000;
        M = ConnectionFactory.DEFAULT;
    }

    private static String v(String str) {
        return (str == null || str.length() <= 0) ? a : str;
    }

    private static SSLSocketFactory R() throws HttpRequestException {
        if (I == null) {
            TrustManager[] trustManagerArr = new TrustManager[]{new X509TrustManager() {
                public final X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                public final void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
                }

                public final void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
                }
            }};
            try {
                SSLContext instance = SSLContext.getInstance(SSLSocketFactoryFactory.DEFAULT_PROTOCOL);
                instance.init(null, trustManagerArr, new SecureRandom());
                I = instance.getSocketFactory();
            } catch (Throwable e) {
                IOException iOException = new IOException("Security exception configuring SSL context");
                iOException.initCause(e);
                throw new HttpRequestException(iOException);
            }
        }
        return I;
    }

    private static HostnameVerifier S() {
        if (J == null) {
            J = new HostnameVerifier() {
                public final boolean verify(String str, SSLSession sSLSession) {
                    return true;
                }
            };
        }
        return J;
    }

    private static StringBuilder a(String str, StringBuilder stringBuilder) {
        if (str.indexOf(R.styleable.AppCompatTheme_toolbarStyle) + 2 == str.lastIndexOf(R.styleable.AppCompatTheme_spinnerDropDownItemStyle)) {
            stringBuilder.append('/');
        }
        return stringBuilder;
    }

    private static StringBuilder b(String str, StringBuilder stringBuilder) {
        int indexOf = str.indexOf(R.styleable.AppCompatTheme_editTextBackground);
        int length = stringBuilder.length() - 1;
        if (indexOf == -1) {
            stringBuilder.append('?');
        } else if (indexOf < length && str.charAt(length) != '&') {
            stringBuilder.append('&');
        }
        return stringBuilder;
    }

    public static void a(ConnectionFactory connectionFactory) {
        if (connectionFactory == null) {
            M = ConnectionFactory.DEFAULT;
        } else {
            M = connectionFactory;
        }
    }

    public static String a(CharSequence charSequence) throws HttpRequestException {
        try {
            URL url = new URL(charSequence.toString());
            String host = url.getHost();
            int port = url.getPort();
            if (port != -1) {
                host = host + ':' + Integer.toString(port);
            }
            try {
                String toASCIIString = new URI(url.getProtocol(), host, url.getPath(), url.getQuery(), null).toASCIIString();
                int indexOf = toASCIIString.indexOf(R.styleable.AppCompatTheme_editTextBackground);
                return (indexOf <= 0 || indexOf + 1 >= toASCIIString.length()) ? toASCIIString : toASCIIString.substring(0, indexOf + 1) + toASCIIString.substring(indexOf + 1).replace(MqttTopic.SINGLE_LEVEL_WILDCARD, "%2B");
            } catch (Throwable e) {
                IOException iOException = new IOException("Parsing URI failed");
                iOException.initCause(e);
                throw new HttpRequestException(iOException);
            }
        } catch (IOException e2) {
            throw new HttpRequestException(e2);
        }
    }

    public static String a(CharSequence charSequence, Map<?, ?> map) {
        String toString = charSequence.toString();
        if (map == null || map.isEmpty()) {
            return toString;
        }
        StringBuilder stringBuilder = new StringBuilder(toString);
        a(toString, stringBuilder);
        b(toString, stringBuilder);
        Iterator it = map.entrySet().iterator();
        Entry entry = (Entry) it.next();
        stringBuilder.append(entry.getKey().toString());
        stringBuilder.append('=');
        Object value = entry.getValue();
        if (value != null) {
            stringBuilder.append(value);
        }
        while (it.hasNext()) {
            stringBuilder.append('&');
            entry = (Entry) it.next();
            stringBuilder.append(entry.getKey().toString());
            stringBuilder.append('=');
            value = entry.getValue();
            if (value != null) {
                stringBuilder.append(value);
            }
        }
        return stringBuilder.toString();
    }

    public static String a(CharSequence charSequence, Object... objArr) {
        String toString = charSequence.toString();
        if (objArr == null || objArr.length == 0) {
            return toString;
        }
        if (objArr.length % 2 != 0) {
            throw new IllegalArgumentException("Must specify an even number of parameter names/values");
        }
        StringBuilder stringBuilder = new StringBuilder(toString);
        a(toString, stringBuilder);
        b(toString, stringBuilder);
        stringBuilder.append(objArr[0]);
        stringBuilder.append('=');
        Object obj = objArr[1];
        if (obj != null) {
            stringBuilder.append(obj);
        }
        for (int i = SimpleLog.LOG_LEVEL_DEBUG; i < objArr.length; i += 2) {
            stringBuilder.append('&');
            stringBuilder.append(objArr[i]);
            stringBuilder.append('=');
            Object obj2 = objArr[i + 1];
            if (obj2 != null) {
                stringBuilder.append(obj2);
            }
        }
        return stringBuilder.toString();
    }

    public static HttpRequest b(CharSequence charSequence) throws HttpRequestException {
        return new HttpRequest(charSequence, x);
    }

    public static HttpRequest a(URL url) throws HttpRequestException {
        return new HttpRequest(url, x);
    }

    public static HttpRequest a(CharSequence charSequence, Map<?, ?> map, boolean z) {
        CharSequence a = a(charSequence, (Map) map);
        if (z) {
            a = a(a);
        }
        return b(a);
    }

    public static HttpRequest a(CharSequence charSequence, boolean z, Object... objArr) {
        CharSequence a = a(charSequence, objArr);
        if (z) {
            a = a(a);
        }
        return b(a);
    }

    public static HttpRequest c(CharSequence charSequence) throws HttpRequestException {
        HttpRequest httpRequest = new HttpRequest(charSequence, A);
        httpRequest.a().setConnectTimeout(L);
        httpRequest.a().setReadTimeout(K);
        return httpRequest;
    }

    public static HttpRequest b(URL url) throws HttpRequestException {
        HttpRequest httpRequest = new HttpRequest(url, A);
        httpRequest.a().setConnectTimeout(L);
        httpRequest.a().setReadTimeout(K);
        return httpRequest;
    }

    public static HttpRequest b(CharSequence charSequence, Map<?, ?> map, boolean z) {
        CharSequence a = a(charSequence, (Map) map);
        if (z) {
            a = a(a);
        }
        return c(a);
    }

    public static HttpRequest b(CharSequence charSequence, boolean z, Object... objArr) {
        CharSequence a = a(charSequence, objArr);
        if (z) {
            a = a(a);
        }
        return c(a);
    }

    public static HttpRequest d(CharSequence charSequence) throws HttpRequestException {
        return new HttpRequest(charSequence, B);
    }

    public static HttpRequest c(URL url) throws HttpRequestException {
        return new HttpRequest(url, B);
    }

    public static HttpRequest c(CharSequence charSequence, Map<?, ?> map, boolean z) {
        CharSequence a = a(charSequence, (Map) map);
        if (z) {
            a = a(a);
        }
        return d(a);
    }

    public static HttpRequest c(CharSequence charSequence, boolean z, Object... objArr) {
        CharSequence a = a(charSequence, objArr);
        if (z) {
            a = a(a);
        }
        return d(a);
    }

    public static HttpRequest e(CharSequence charSequence) throws HttpRequestException {
        return new HttpRequest(charSequence, w);
    }

    public static HttpRequest d(URL url) throws HttpRequestException {
        return new HttpRequest(url, w);
    }

    public static HttpRequest d(CharSequence charSequence, Map<?, ?> map, boolean z) {
        CharSequence a = a(charSequence, (Map) map);
        if (z) {
            a = a(a);
        }
        return e(a);
    }

    public static HttpRequest d(CharSequence charSequence, boolean z, Object... objArr) {
        CharSequence a = a(charSequence, objArr);
        if (z) {
            a = a(a);
        }
        return e(a);
    }

    public static HttpRequest f(CharSequence charSequence) throws HttpRequestException {
        return new HttpRequest(charSequence, y);
    }

    public static HttpRequest e(URL url) throws HttpRequestException {
        return new HttpRequest(url, y);
    }

    public static HttpRequest e(CharSequence charSequence, Map<?, ?> map, boolean z) {
        CharSequence a = a(charSequence, (Map) map);
        if (z) {
            a = a(a);
        }
        return f(a);
    }

    public static HttpRequest e(CharSequence charSequence, boolean z, Object... objArr) {
        CharSequence a = a(charSequence, objArr);
        if (z) {
            a = a(a);
        }
        return f(a);
    }

    public static HttpRequest g(CharSequence charSequence) throws HttpRequestException {
        return new HttpRequest(charSequence, z);
    }

    public static HttpRequest f(URL url) throws HttpRequestException {
        return new HttpRequest(url, z);
    }

    public static HttpRequest h(CharSequence charSequence) throws HttpRequestException {
        return new HttpRequest(charSequence, C);
    }

    public static HttpRequest g(URL url) throws HttpRequestException {
        return new HttpRequest(url, C);
    }

    public static void a(boolean z) {
        j("http.keepAlive", Boolean.toString(z));
    }

    public static void a(String str) {
        j("http.proxyHost", str);
        j("https.proxyHost", str);
    }

    public static void a(int i) {
        String toString = Integer.toString(i);
        j("http.proxyPort", toString);
        j("https.proxyPort", toString);
    }

    public static void a(String... strArr) {
        if (strArr == null || strArr.length <= 0) {
            j("http.nonProxyHosts", null);
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        int length = strArr.length - 1;
        for (int i = 0; i < length; i++) {
            stringBuilder.append(strArr[i]).append('|');
        }
        stringBuilder.append(strArr[length]);
        j("http.nonProxyHosts", stringBuilder.toString());
    }

    private static String j(String str, String str2) {
        PrivilegedAction anonymousClass_4;
        if (str2 != null) {
            anonymousClass_4 = new AnonymousClass_4(str, str2);
        } else {
            anonymousClass_4 = new AnonymousClass_5(str);
        }
        return (String) AccessController.doPrivileged(anonymousClass_4);
    }

    public HttpRequest(CharSequence charSequence, String str) throws HttpRequestException {
        this.N = null;
        this.T = true;
        this.U = false;
        this.V = 8192;
        try {
            this.O = new URL(charSequence.toString());
            this.P = str;
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    public HttpRequest(URL url, String str) throws HttpRequestException {
        this.N = null;
        this.T = true;
        this.U = false;
        this.V = 8192;
        this.O = url;
        this.P = str;
    }

    private Proxy T() {
        return new Proxy(Type.HTTP, new InetSocketAddress(this.W, this.X));
    }

    private HttpURLConnection U() {
        try {
            HttpURLConnection create;
            if (this.W != null) {
                create = M.create(this.O, T());
            } else {
                create = M.create(this.O);
            }
            create.setRequestMethod(this.P);
            return create;
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    public String toString() {
        return Q() + ' ' + P();
    }

    public HttpURLConnection a() {
        if (this.N == null) {
            this.N = U();
        }
        return this.N;
    }

    public HttpRequest b(boolean z) {
        this.T = z;
        return this;
    }

    public boolean b() {
        return this.T;
    }

    public int c() throws HttpRequestException {
        try {
            I();
            return a().getResponseCode();
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    public HttpRequest a(AtomicInteger atomicInteger) throws HttpRequestException {
        atomicInteger.set(c());
        return this;
    }

    public boolean d() throws HttpRequestException {
        return 200 == c();
    }

    public boolean e() throws HttpRequestException {
        return 201 == c();
    }

    public boolean f() throws HttpRequestException {
        return 500 == c();
    }

    public boolean g() throws HttpRequestException {
        return 400 == c();
    }

    public boolean h() throws HttpRequestException {
        return 404 == c();
    }

    public boolean i() throws HttpRequestException {
        return 304 == c();
    }

    public String j() throws HttpRequestException {
        try {
            I();
            return a().getResponseMessage();
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    public HttpRequest k() {
        a().disconnect();
        return this;
    }

    public HttpRequest b(int i) {
        a().setChunkedStreamingMode(i);
        return this;
    }

    public HttpRequest c(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("Size must be greater than zero");
        }
        this.V = i;
        return this;
    }

    public int l() {
        return this.V;
    }

    public HttpRequest c(boolean z) {
        this.U = z;
        return this;
    }

    protected ByteArrayOutputStream m() {
        int G = G();
        return G > 0 ? new ByteArrayOutputStream(G) : new ByteArrayOutputStream();
    }

    public String b(String str) throws HttpRequestException {
        OutputStream m = m();
        try {
            a(q(), m);
            return m.toString(v(str));
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    public String n() throws HttpRequestException {
        return b(v());
    }

    public HttpRequest a(AtomicReference<String> atomicReference) throws HttpRequestException {
        atomicReference.set(n());
        return this;
    }

    public HttpRequest a(AtomicReference<String> atomicReference, String str) throws HttpRequestException {
        atomicReference.set(b(str));
        return this;
    }

    public boolean o() throws HttpRequestException {
        return G() == 0;
    }

    public byte[] p() throws HttpRequestException {
        OutputStream m = m();
        try {
            a(q(), m);
            return m.toByteArray();
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    public BufferedInputStream q() throws HttpRequestException {
        return new BufferedInputStream(r(), this.V);
    }

    public InputStream r() throws HttpRequestException {
        if (c() < 400) {
            try {
                InputStream inputStream = a().getInputStream();
            } catch (IOException e) {
                throw new HttpRequestException(e);
            }
        }
        inputStream = a().getErrorStream();
        if (inputStream == null) {
            try {
                inputStream = a().getInputStream();
            } catch (IOException e2) {
                throw new HttpRequestException(e2);
            }
        }
        if (!this.U || !d.equals(x())) {
            return inputStream;
        }
        try {
            return new GZIPInputStream(inputStream);
        } catch (IOException e22) {
            throw new HttpRequestException(e22);
        }
    }

    public InputStreamReader c(String str) throws HttpRequestException {
        try {
            return new InputStreamReader(r(), v(str));
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    public InputStreamReader s() throws HttpRequestException {
        return c(v());
    }

    public BufferedReader d(String str) throws HttpRequestException {
        return new BufferedReader(c(str), this.V);
    }

    public BufferedReader t() throws HttpRequestException {
        return d(v());
    }

    public HttpRequest a(File file) throws HttpRequestException {
        try {
            Object bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file), this.V);
            return (HttpRequest) new AnonymousClass_6(bufferedOutputStream, this.T, bufferedOutputStream).call();
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    public HttpRequest a(OutputStream outputStream) throws HttpRequestException {
        try {
            return a(q(), outputStream);
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    public HttpRequest a(PrintStream printStream) throws HttpRequestException {
        return a((OutputStream) printStream);
    }

    public HttpRequest a(Appendable appendable) throws HttpRequestException {
        Closeable t = t();
        return (HttpRequest) new AnonymousClass_7(t, this.T, t, appendable).call();
    }

    public HttpRequest a(Writer writer) throws HttpRequestException {
        Closeable t = t();
        return (HttpRequest) new AnonymousClass_8(t, this.T, t, writer).call();
    }

    public HttpRequest d(int i) {
        a().setReadTimeout(i);
        return this;
    }

    public HttpRequest e(int i) {
        a().setConnectTimeout(i);
        return this;
    }

    public HttpRequest a(String str, String str2) {
        a().setRequestProperty(str, str2);
        return this;
    }

    public HttpRequest a(String str, Number number) {
        return a(str, number != null ? number.toString() : null);
    }

    public HttpRequest a(Map<String, String> map) {
        if (!map.isEmpty()) {
            for (Entry entry : map.entrySet()) {
                a(entry);
            }
        }
        return this;
    }

    public HttpRequest a(Entry<String, String> entry) {
        return a((String) entry.getKey(), (String) entry.getValue());
    }

    public String e(String str) throws HttpRequestException {
        J();
        return a().getHeaderField(str);
    }

    public Map<String, List<String>> u() throws HttpRequestException {
        J();
        return a().getHeaderFields();
    }

    public long f(String str) throws HttpRequestException {
        return a(str, -1);
    }

    public long a(String str, long j) throws HttpRequestException {
        J();
        return a().getHeaderFieldDate(str, j);
    }

    public int g(String str) throws HttpRequestException {
        return a(str, -1);
    }

    public int a(String str, int i) throws HttpRequestException {
        J();
        return a().getHeaderFieldInt(str, i);
    }

    public String[] h(String str) {
        Map u = u();
        if (u == null || u.isEmpty()) {
            return H;
        }
        List list = (List) u.get(str);
        return (list == null || list.isEmpty()) ? H : (String[]) list.toArray(new String[list.size()]);
    }

    public String b(String str, String str2) {
        return c(e(str), str2);
    }

    public Map<String, String> i(String str) {
        return j(e(str));
    }

    protected Map<String, String> j(String str) {
        if (str == null || str.length() == 0) {
            return Collections.emptyMap();
        }
        int length = str.length();
        int indexOf = str.indexOf(R.styleable.AppCompatTheme_toolbarNavigationButtonStyle) + 1;
        if (indexOf == 0 || indexOf == length) {
            return Collections.emptyMap();
        }
        int indexOf2 = str.indexOf(R.styleable.AppCompatTheme_toolbarNavigationButtonStyle, indexOf);
        if (indexOf2 == -1) {
            indexOf2 = length;
        }
        Map<String, String> linkedHashMap = new LinkedHashMap();
        while (indexOf < indexOf2) {
            int indexOf3 = str.indexOf(R.styleable.AppCompatTheme_popupWindowStyle, indexOf);
            if (indexOf3 != -1 && indexOf3 < indexOf2) {
                String trim = str.substring(indexOf, indexOf3).trim();
                if (trim.length() > 0) {
                    String trim2 = str.substring(indexOf3 + 1, indexOf2).trim();
                    int length2 = trim2.length();
                    if (length2 != 0) {
                        if (length2 > 2 && '\"' == trim2.charAt(0) && '\"' == trim2.charAt(length2 - 1)) {
                            linkedHashMap.put(trim, trim2.substring(1, length2 - 1));
                        } else {
                            linkedHashMap.put(trim, trim2);
                        }
                    }
                }
            }
            indexOf = indexOf2 + 1;
            indexOf2 = str.indexOf(R.styleable.AppCompatTheme_toolbarNavigationButtonStyle, indexOf);
            if (indexOf2 == -1) {
                indexOf2 = length;
            }
        }
        return linkedHashMap;
    }

    protected String c(String str, String str2) {
        if (str == null || str.length() == 0) {
            return null;
        }
        int length = str.length();
        int indexOf = str.indexOf(R.styleable.AppCompatTheme_toolbarNavigationButtonStyle) + 1;
        if (indexOf == 0 || indexOf == length) {
            return null;
        }
        int indexOf2 = str.indexOf(R.styleable.AppCompatTheme_toolbarNavigationButtonStyle, indexOf);
        if (indexOf2 == -1) {
            indexOf2 = indexOf;
            indexOf = length;
        } else {
            int i = indexOf2;
            indexOf2 = indexOf;
            indexOf = i;
        }
        while (indexOf2 < indexOf) {
            int indexOf3 = str.indexOf(R.styleable.AppCompatTheme_popupWindowStyle, indexOf2);
            if (indexOf3 != -1 && indexOf3 < indexOf && str2.equals(str.substring(indexOf2, indexOf3).trim())) {
                String trim = str.substring(indexOf3 + 1, indexOf).trim();
                indexOf3 = trim.length();
                if (indexOf3 != 0) {
                    return (indexOf3 > 2 && '\"' == trim.charAt(0) && '\"' == trim.charAt(indexOf3 - 1)) ? trim.substring(1, indexOf3 - 1) : trim;
                }
            }
            indexOf++;
            indexOf2 = str.indexOf(R.styleable.AppCompatTheme_toolbarNavigationButtonStyle, indexOf);
            if (indexOf2 == -1) {
                indexOf2 = length;
            }
            i = indexOf2;
            indexOf2 = indexOf;
            indexOf = i;
        }
        return null;
    }

    public String v() {
        return b(l, D);
    }

    public HttpRequest k(String str) {
        return a(v, str);
    }

    public HttpRequest l(String str) {
        return a(t, str);
    }

    public HttpRequest d(boolean z) {
        a().setUseCaches(z);
        return this;
    }

    public HttpRequest m(String str) {
        return a(g, str);
    }

    public HttpRequest w() {
        return m(d);
    }

    public HttpRequest n(String str) {
        return a(f, str);
    }

    public String x() {
        return e(j);
    }

    public String y() {
        return e(u);
    }

    public long z() {
        return f(m);
    }

    public String A() {
        return e(i);
    }

    public String B() {
        return e(n);
    }

    public long C() {
        return f(o);
    }

    public long D() {
        return f(q);
    }

    public String E() {
        return e(r);
    }

    public HttpRequest o(String str) {
        return a(h, str);
    }

    public HttpRequest p(String str) {
        return a(s, str);
    }

    public HttpRequest d(String str, String str2) {
        return o(new StringBuilder("Basic ").append(a.a(str + ':' + str2)).toString());
    }

    public HttpRequest e(String str, String str2) {
        return p(new StringBuilder("Basic ").append(a.a(str + ':' + str2)).toString());
    }

    public HttpRequest a(long j) {
        a().setIfModifiedSince(j);
        return this;
    }

    public HttpRequest q(String str) {
        return a(p, str);
    }

    public HttpRequest r(String str) {
        return f(str, null);
    }

    public HttpRequest f(String str, String str2) {
        return (str2 == null || str2.length() <= 0) ? a(l, str) : a(l, str + "; charset=" + str2);
    }

    public String F() {
        return e(l);
    }

    public int G() {
        return g(k);
    }

    public HttpRequest s(String str) {
        return f(Integer.parseInt(str));
    }

    public HttpRequest f(int i) {
        a().setFixedLengthStreamingMode(i);
        return this;
    }

    public HttpRequest t(String str) {
        return a(e, str);
    }

    public HttpRequest H() {
        return t(c);
    }

    protected HttpRequest a(InputStream inputStream, OutputStream outputStream) throws IOException {
        return (HttpRequest) new AnonymousClass_9(inputStream, this.T, inputStream, outputStream).call();
    }

    protected HttpRequest a(Reader reader, Writer writer) throws IOException {
        return (HttpRequest) new AnonymousClass_10(reader, this.T, reader, writer).call();
    }

    protected HttpRequest I() throws IOException {
        if (this.Q != null) {
            if (this.R) {
                this.Q.a("\r\n--00content0boundary00--\r\n");
            }
            if (this.T) {
                try {
                    this.Q.close();
                } catch (IOException e) {
                }
            } else {
                this.Q.close();
            }
            this.Q = null;
        }
        return this;
    }

    protected HttpRequest J() throws HttpRequestException {
        try {
            return I();
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    protected HttpRequest K() throws IOException {
        if (this.Q == null) {
            a().setDoOutput(true);
            this.Q = new e(a().getOutputStream(), c(a().getRequestProperty(l), D), this.V);
        }
        return this;
    }

    protected HttpRequest L() throws IOException {
        if (this.R) {
            this.Q.a("\r\n--00content0boundary00\r\n");
        } else {
            this.R = true;
            r(F).K();
            this.Q.a("--00content0boundary00\r\n");
        }
        return this;
    }

    protected HttpRequest g(String str, String str2) throws IOException {
        return a(str, str2, null);
    }

    protected HttpRequest a(String str, String str2, String str3) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("form-data; name=\"").append(str);
        if (str2 != null) {
            stringBuilder.append("\"; filename=\"").append(str2);
        }
        stringBuilder.append('\"');
        i("Content-Disposition", stringBuilder.toString());
        if (str3 != null) {
            i(l, str3);
        }
        return i(G);
    }

    public HttpRequest h(String str, String str2) {
        return b(str, null, str2);
    }

    public HttpRequest b(String str, String str2, String str3) throws HttpRequestException {
        return a(str, str2, null, str3);
    }

    public HttpRequest a(String str, String str2, String str3, String str4) throws HttpRequestException {
        try {
            L();
            a(str, str2, str3);
            this.Q.a(str4);
            return this;
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    public HttpRequest b(String str, Number number) throws HttpRequestException {
        return a(str, null, number);
    }

    public HttpRequest a(String str, String str2, Number number) throws HttpRequestException {
        return b(str, str2, number != null ? number.toString() : null);
    }

    public HttpRequest a(String str, File file) throws HttpRequestException {
        return a(str, null, file);
    }

    public HttpRequest a(String str, String str2, File file) throws HttpRequestException {
        return a(str, str2, null, file);
    }

    public HttpRequest a(String str, String str2, String str3, File file) throws HttpRequestException {
        try {
            return a(str, str2, str3, new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    public HttpRequest a(String str, InputStream inputStream) throws HttpRequestException {
        return a(str, null, null, inputStream);
    }

    public HttpRequest a(String str, String str2, String str3, InputStream inputStream) throws HttpRequestException {
        try {
            L();
            a(str, str2, str3);
            a(inputStream, this.Q);
            return this;
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    public HttpRequest i(String str, String str2) throws HttpRequestException {
        return i((CharSequence) str).i((CharSequence) ": ").i((CharSequence) str2).i(G);
    }

    public HttpRequest b(File file) throws HttpRequestException {
        try {
            return a(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    public HttpRequest a(byte[] bArr) throws HttpRequestException {
        return a(new ByteArrayInputStream(bArr));
    }

    public HttpRequest a(InputStream inputStream) throws HttpRequestException {
        try {
            K();
            a(inputStream, this.Q);
            return this;
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    public HttpRequest a(Reader reader) throws HttpRequestException {
        try {
            K();
            Object outputStreamWriter = new OutputStreamWriter(this.Q, this.Q.a.charset());
            return (HttpRequest) new AnonymousClass_2(outputStreamWriter, reader, outputStreamWriter).call();
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    public HttpRequest i(CharSequence charSequence) throws HttpRequestException {
        try {
            K();
            this.Q.a(charSequence.toString());
            return this;
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    public OutputStreamWriter M() throws HttpRequestException {
        try {
            K();
            return new OutputStreamWriter(this.Q, this.Q.a.charset());
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    public HttpRequest b(Map<?, ?> map) throws HttpRequestException {
        return a((Map) map, a);
    }

    public HttpRequest b(Entry<?, ?> entry) throws HttpRequestException {
        return a((Entry) entry, a);
    }

    public HttpRequest a(Entry<?, ?> entry, String str) throws HttpRequestException {
        return a(entry.getKey(), entry.getValue(), str);
    }

    public HttpRequest a(Object obj, Object obj2) throws HttpRequestException {
        return a(obj, obj2, a);
    }

    public HttpRequest a(Object obj, Object obj2, String str) throws HttpRequestException {
        if (this.S) {
            Object obj3 = null;
        } else {
            boolean z = true;
        }
        if (z) {
            f(b, str);
            this.S = true;
        }
        String v = v(str);
        try {
            K();
            if (!z) {
                this.Q.write(R.styleable.AppCompatTheme_actionModeWebSearchDrawable);
            }
            this.Q.a(URLEncoder.encode(obj.toString(), v));
            this.Q.write(R.styleable.AppCompatTheme_popupWindowStyle);
            if (obj2 != null) {
                this.Q.a(URLEncoder.encode(obj2.toString(), v));
            }
            return this;
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    public HttpRequest a(Map<?, ?> map, String str) throws HttpRequestException {
        if (!map.isEmpty()) {
            for (Entry entry : map.entrySet()) {
                a(entry, str);
            }
        }
        return this;
    }

    public HttpRequest N() throws HttpRequestException {
        HttpURLConnection a = a();
        if (a instanceof HttpsURLConnection) {
            ((HttpsURLConnection) a).setSSLSocketFactory(R());
        }
        return this;
    }

    public HttpRequest O() {
        HttpURLConnection a = a();
        if (a instanceof HttpsURLConnection) {
            ((HttpsURLConnection) a).setHostnameVerifier(S());
        }
        return this;
    }

    public URL P() {
        return a().getURL();
    }

    public String Q() {
        return a().getRequestMethod();
    }

    public HttpRequest b(String str, int i) {
        if (this.N != null) {
            throw new IllegalStateException("The connection has already been created. This method must be called before reading or writing to the request.");
        }
        this.W = str;
        this.X = i;
        return this;
    }

    public HttpRequest e(boolean z) {
        a();
        HttpURLConnection.setFollowRedirects(z);
        return this;
    }
}
